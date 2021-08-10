package com.example.springcloud.example.transactional;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class OrderTransactionListener  implements TransactionListener {

    private static final Map<String, Boolean> results = new ConcurrentHashMap<>();

    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        String orderId = (String) o;

        //记录本地事务执行结果
        boolean success = persistTransactionResult(orderId);
        System.err.println("订单服务执行本地事务下单,orderId: " + orderId + ", result: " + success);
        //只有事务执行成功的订单才会通知商品服务进行减库存。
        return success ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }

    // LocalTransactionState.UNKNOW 会导致该方法调用.
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        String orderId = messageExt.getKeys();
        System.err.println("执行事务消息回查,orderId: " + orderId+ " messageExt="+messageExt);
        return Boolean.TRUE.equals(results.get(orderId)) ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }


    private boolean persistTransactionResult(String orderId) {
        boolean success = Math.abs(Objects.hash(orderId)) % 2 == 0;
        results.put(orderId, success);
        return success;
    }
}
