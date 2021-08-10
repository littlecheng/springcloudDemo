package com.example.springcloud.example.transactional;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class ProductionService {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(RocketMQConstants.NAMESRV_ADDR);
        consumer.setConsumerGroup(RocketMQConstants.TRANSACTION_CONSUMER_GROUP);
        consumer.subscribe(RocketMQConstants.TRANSACTION_TOPIC_NAME, "*");
        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt>  msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                try {
                    for (MessageExt msg:msgs) {
                        System.out.printf("收到订单号%s,订单信息:%s ,事务id:%s%n ", msg.getKeys(), new String(msg.getBody()),msg.getTransactionId());
                        //收到订单号,可以处理业务逻辑,减库存
                    }
                    //模拟异常
                    int i = 10/0;
                }catch (Exception e){
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;//重试
                }


                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.err.println("ProductService Start");
    }

}
