package com.crud.Service;

import com.crud.model.User;
import com.crud.repository.UserRepo;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    Long capacity = 5L;

    @Autowired
    UserRepo userRepo;


    public void create(User obj) throws InterruptedException {

       Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this)
                {
                    try{



                            System.out.println(userRepo.count());
                            while (userRepo.count() == capacity) {
                                System.out.println("Producer waiting");
                                wait();

                            }
                            userRepo.save(obj);
                            System.out.println("Producer produced "+ obj.getId());




                            notify();

                            Thread.sleep(3000);




                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }}
        });
       t1.start();
       t1.join();


    }

    public Optional<User> read(String id)
    {
        return userRepo.findById(id);
    }
    public void delete(String id) throws InterruptedException {
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this)
                {
                    try{

                        int value =0;


                            while(userRepo.count() ==0) {
                                wait();
                                System.out.println("Consumer waiting");
                            }

                            userRepo.deleteById(id);
                            System.out.println("Consumer consumed "+ id);


                            notify();

                            Thread.sleep(2000);



                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }}
        });
        t2.start();
         t2.join();



    }



   /*  public void startPCExample() throws InterruptedException{
        final PCExample pcExample = new PCExample();
        Random rand = new Random();
        User user = new User("1","sahib");
        User user1 = new User("2","richi");
        User user2 = new User("3","ashwin");
        User user3 = new User("4","gaurav");
        User user4 = new User("5","raksha");
        List<User> usersList = new ArrayList<>();
        usersList.add(user);
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersList.add(user4);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this)
                {
                try{
                    int value = 0;
                    while (true) {

                            System.out.println(userRepo.count());
                            while (userRepo.count() == capacity) {

                                wait();
                                System.out.println("Producer waiting");
                            }
                            value = rand.nextInt(5);
                            System.out.println("Producer produced "+ value);

                            userRepo.save(usersList.get(value));


                            notify();

                            Thread.sleep(3000);



                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }}
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this)
                {
                try{

                    int value =0;
                    while(true){

                            while(userRepo.count() ==0) {
                                wait();
                                System.out.println("Consumer waiting");
                            }

                            value = rand.nextInt(5);
                            userRepo.delete(usersList.get(value));

                            System.out.println(usersList.get(value).toString() + " deleted " + value);

                            notify();

                            Thread.sleep(2000);
                        }


                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }}
        });

        t1.setPriority(5);
        t2.setPriority(5);

        synchronized (this) {
            t1.start();
            t2.start();
        }

        t1.join();
        t2.join();

    }*/
}
