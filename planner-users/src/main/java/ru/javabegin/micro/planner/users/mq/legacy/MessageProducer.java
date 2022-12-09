package ru.javabegin.micro.planner.users.mq.legacy;



import org.springframework.cloud.stream.annotation.EnableBinding;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//@Component
//@EnableBinding(TodoBinding.class)
public class MessageProducer {

//    private TodoBinding todoBinding;
//
//    public MessageProducer(TodoBinding todoBinding){
//        this.todoBinding = todoBinding;
//    }
//
//    public void initUserData(Long id) {
//
//        Message message = MessageBuilder.withPayload(id).build();
//
//        todoBinding.todoOutputChannel().send(message);
//    }
}
