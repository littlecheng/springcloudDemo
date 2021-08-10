package com.example.springcloud.example.transactional;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.*;

public class OrderService {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer(RocketMQConstants.TRANSACTION_CONSUMER_GROUP);
        producer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);

        //自定义线程池,执行事务操作
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("order Transaction Massage Thread");
                return thread;
            }
        });

        producer.setExecutorService(executorService);

        //设置事务消息监听器
        producer.setTransactionListener(new OrderTransactionListener());

        producer.setRetryTimesWhenSendFailed(4);

        producer.start();

        System.err.println("OrderService Start");

        for (int i = 0;i < 10;i++){
            String orderId = UUID.randomUUID().toString();
            String content = "下单内容:orderId: " + orderId;
            String tags = "Tag";

            //发送事务消息
            try {
                Message message  = new Message(RocketMQConstants.TRANSACTION_TOPIC_NAME, tags, orderId, content.getBytes(RemotingHelper.DEFAULT_CHARSET));
                TransactionSendResult  result = producer.sendMessageInTransaction(message, orderId);
                System.err.println("发送事务消息,发送结果: " + result);
                Thread.sleep(10);
            } catch (MQClientException|UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        producer.shutdown();
    }
}
