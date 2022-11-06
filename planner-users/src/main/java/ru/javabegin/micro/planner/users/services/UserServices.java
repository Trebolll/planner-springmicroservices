package ru.javabegin.micro.planner.users.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.javabegin.micro.planner.entity.User;
import ru.javabegin.micro.planner.users.repo.UserRepository;

import javax.transaction.Transactional;
@Service
@Transactional
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User add(User user){
        return userRepository.save(user);
    }

    public User update(User user){
        return userRepository.save(user);
    }

    public void deleteByUserId(Long id){
        userRepository.deleteById(id);
    }

    public void deleteByUserEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public User findById(Long id){
      return   userRepository.findById(id).get();
    }

    public Page<User> findByParams(String email, String username, PageRequest pageRequest){
        return userRepository.findByParams(email,username,pageRequest);
    }

}
