package com.xbb.cases.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunLoadPage implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8080/login.html");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
