package ru.javabegin.micro.planner.todo.mq.legacy;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import ru.javabegin.micro.planner.todo.service.TestDataService;

//@EnableBinding(TodoBinding.class)
//@Component
public class MessageConsumer {

private TestDataService testDataService;

//    public MessageConsumer(TestDataService testDataService) {
//        this.testDataService = testDataService;
//    }
//@StreamListener(target = TodoBinding.INPUT_CHANNEL)
//    public void newUserAction(Long id) throws Exception {
//        throw  new Exception("test dql");
//
//
////        testDataService.initTestData(id);
//    }
}
