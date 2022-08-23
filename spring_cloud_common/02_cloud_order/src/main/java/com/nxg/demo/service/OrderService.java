package com.nxg.demo.service;

import com.nxg.demo.entity.Payment;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author nxg
 * date 2022/1/28
 * @apiNote
 */
@Service
@Slf4j
public class OrderService {

    @Bulkhead(name = "backendA",type = Bulkhead.Type.SEMAPHORE)
    public CompletableFuture<Payment> getPayment() throws InterruptedException {
        log.info("now i enter the method!!!");
        Thread.sleep(10000L);
        log.info("now i exist the method!!!");
        return CompletableFuture.supplyAsync(() -> new Payment(123, "线程池隔离回退。。。"));
    }
}
