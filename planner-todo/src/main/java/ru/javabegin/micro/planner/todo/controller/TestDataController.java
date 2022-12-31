package ru.javabegin.micro.planner.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.micro.planner.entity.*;
import ru.javabegin.micro.planner.todo.service.*;
import ru.javabegin.micro.planner.utils.rest.webclient.UserWebClientBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

// для заполнения тестовыми данными

@RestController
@RequestMapping("/data") // базовый URI

public class TestDataController {

    private final TestDataService testDataService;


    // используем автоматическое внедрение экземпляра класса через конструктор
    // не используем @Autowired ля переменной класса, т.к. "Field injection is not recommended "
    public TestDataController(TestDataService testDataService) {
        this.testDataService = testDataService;
    }



    @PostMapping("/init")
    public ResponseEntity<Boolean> init(@RequestBody String userId) {

        testDataService.initTestData(userId);

        // если пользователя НЕ существует
        return ResponseEntity.ok(true);

    }


}
