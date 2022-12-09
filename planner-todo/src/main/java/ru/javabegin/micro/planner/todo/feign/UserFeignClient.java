package ru.javabegin.micro.planner.todo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.javabegin.micro.planner.entity.User;

@FeignClient(name="planner-users",fallback = UserFeignClientFallBack.class)
public interface UserFeignClient {

    @PostMapping("/user/id")
    ResponseEntity<User> findUserById(@RequestBody Long id);
}
@Component
class UserFeignClientFallBack implements UserFeignClient{

    @Override
    public ResponseEntity<User> findUserById(Long id) {
        return null;
    }
}
