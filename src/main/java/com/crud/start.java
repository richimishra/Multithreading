package com.crud;

import com.crud.Controller.userController;
import com.crud.Service.UserService;
import com.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class start {

    @Autowired
    UserService userService;
    public static void main(String[]args)
    {
         SpringApplication.run(start.class,args);


    }


}
