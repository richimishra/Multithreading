package com.crud.Controller;

import com.crud.Service.UserService;
import com.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class userController {
    @Autowired
    UserService userService;

    //create

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) throws InterruptedException {
            userService.create(user);
    }


    //read
    @GetMapping("/findById")
    public Optional<User> read(@RequestParam String id) {
        return userService.read(id);
    }



    //delete
    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestParam String id) throws InterruptedException {
        userService.delete(id);
    }

   /* @GetMapping("/startPCExample")
    public void startPCExample(){
        System.out.println("I am called");
        try {
            userService.startPCExample();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

}
