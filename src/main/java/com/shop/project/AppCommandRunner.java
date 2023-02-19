package com.shop.project;

import com.shop.project.repos.CustomerRepo;
import com.shop.project.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
public class AppCommandRunner implements CommandLineRunner {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        log.info("Application started");
        customerRepo.findAll()
                .forEach(c -> {
                            customerService.changeStatus(c);
                            log.info(c.toString());
                        }
                );
    }

}
