package com.edb.quarkus.plugin.rocketmq.produce;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * mq 生产者
 */

public class RocketMqProducer  {

    private Logger log = LoggerFactory.getLogger(RocketMqProducer.class);
    /**
     * 构建一个初始化对象
     */
    public RocketMqProducer(){
    }


}
