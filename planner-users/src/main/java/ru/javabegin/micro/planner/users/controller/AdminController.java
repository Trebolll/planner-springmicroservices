package ru.javabegin.micro.planner.users.controller;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.javabegin.micro.planner.users.keycloak.KeycloakUtils;
import ru.javabegin.micro.planner.users.mq.func.MessageFuncActions;

import ru.javabegin.micro.planner.users.userDTO.UserDTO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/admin/user")
public class AdminController {
    public static final String ID_COLUMN = "id";
    private static final int CONFLICT = 409;
    private static final String USER_ROLE_NAME = "user";
    private final KeycloakUtils keycloakUtils;
    private MessageFuncActions messageFuncActions;

    public AdminController(KeycloakUtils keycloakUtils, MessageFuncActions messageFuncActions) {
        this.messageFuncActions = messageFuncActions;
        this.keycloakUtils = keycloakUtils;
    }


    // добавление
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserDTO userDTO) {

        if (userDTO.getId() != null && userDTO.getId().trim().length() != 0) {
            // id создается автоматически в БД (autoincrement), поэтому его передавать не нужно, иначе может быть конфликт уникальности значения
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userDTO.getPassword() == null || userDTO.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().length() == 0) {
            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }
        Response createdResponse = keycloakUtils.createKeycloakUser(userDTO);
        if (createdResponse.getStatus() == CONFLICT) {
            return new ResponseEntity("user or email already exists " + userDTO.getEmail(), HttpStatus.CONFLICT);
        }
        String userId = CreatedResponseUtil.getCreatedId(createdResponse);
        System.out.printf("User created with userId: %s%n", userId);
        List<String> defaultRoles = new ArrayList<>();
        defaultRoles.add(USER_ROLE_NAME);
        defaultRoles.add("admin");
        keycloakUtils.addRoles(userId, defaultRoles);
        return ResponseEntity.status(createdResponse.getStatus()).build();
    }

    // обновление
    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        if (userDTO.getId().isBlank()) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }
        keycloakUtils.updateKeycloakUser(userDTO);
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)

    }


    @PostMapping("/deletebyid")
    public ResponseEntity deleteByUserId(@RequestBody String userId) {
        keycloakUtils.deleteKeyCloakUsers(userId);
        return new ResponseEntity(HttpStatus.OK); // просто отправляем статус 200 (операция прошла успешно)
    }

    @PostMapping("/id")
    public ResponseEntity<UserRepresentation> findById(@RequestBody String userId) {
        return ResponseEntity.ok(keycloakUtils.findUserById(userId));
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserRepresentation>> search(@RequestBody String text) {
        return ResponseEntity.ok(keycloakUtils.searchKeyCloakUsers(text));

    }
}
