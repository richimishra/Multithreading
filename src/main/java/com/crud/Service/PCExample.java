package com.crud.Service;

import com.crud.model.User;
import com.crud.repository.UserRepo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.SynchronousQueue;

@Service
public class PCExample {

    long capacity = 5L;

    public PCExample() {

    }

    @Autowired
    UserRepo userRepo;

    public void produce(User user) throws InterruptedException {


        int value = 0;
        while (true) {
            synchronized (this) {
                System.out.println(userRepo.count());
                while (userRepo.count() == capacity)
                    wait();

                System.out.println("Producer produced "+ value);

                userRepo.save(user);

                notify();

                Thread.sleep(1000);


            }
        }

    }


    public void consume(User user) throws InterruptedException{
        while(true){
            synchronized (this){
                while(userRepo.count() ==0)
                    wait();

                userRepo.delete(user);

                System.out.println(user.getName() + " deleted");

                notify();

                Thread.sleep(1000);
            }

        }
    }
}
