package com.example.springcloud.example.transactional;

public interface RocketMQConstants {
    String NAMESRV_ADDR = "localhost:9876";
    String TRANSACTION_CONSUMER_GROUP = "orderGroup";
    String TRANSACTION_TOPIC_NAME = "order";
}
