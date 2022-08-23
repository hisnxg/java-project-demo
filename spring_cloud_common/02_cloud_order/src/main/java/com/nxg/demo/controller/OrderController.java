package com.nxg.demo.controller;

import com.nxg.demo.client.PaymentClient;
import com.nxg.demo.entity.Payment;
import com.nxg.demo.service.OrderService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author nxg
 * date 2022/1/19
 * @apiNote
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    //import org.springframework.cloud.client.loadbalancer.LoadBalanced;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private OrderService orderService;

    /**
     * 限流
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/payment/rate/{id}")
    @RateLimiter(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<Payment> getPaymentById5(@PathVariable("id") Integer id) throws InterruptedException {
        log.info("now i enter the method!!!");

        Thread.sleep(10000L); //阻塞10秒，已测试慢调用比例熔断

        String url = "http://cloud-payment-service/payment/" + id;
        Payment payment = restTemplate.getForObject(url, Payment.class);

        log.info("now i exist the method!!!");

        return ResponseEntity.ok(payment);
    }
    /**
     * 线程池隔离
     * @param id
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @GetMapping("/payment/thread/{id}")
    public ResponseEntity<Payment> getPaymentById4(@PathVariable("id") Integer id) throws InterruptedException, ExecutionException {
        return ResponseEntity.ok(orderService.getPayment().get());
    }
    /**
     * 信号量隔离
     * @param id
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/payment/bulkhead/{id}")
    @Bulkhead(name = "backendA", fallbackMethod = "fallback", type = Bulkhead.Type.SEMAPHORE)
    public ResponseEntity<Payment> getPaymentById3(@PathVariable("id") Integer id) throws InterruptedException {
        log.info("now i enter the method!!!");

        Thread.sleep(10000L); //阻塞10秒，已测试慢调用比例熔断

        String url = "http://cloud-payment-service/payment/" + id;
        Payment payment = restTemplate.getForObject(url, Payment.class);

        log.info("now i exist the method!!!");

        return ResponseEntity.ok(payment);
    }

    /**
     * 慢比例调用熔断
     * @param id
     * @return
     */
    @GetMapping(value = "/slf4j/payment/{id}")
    @CircuitBreaker(name = "backendA",fallbackMethod = "fallback")
    public ResponseEntity<Payment> getPaymentById2(@PathVariable("id") Integer id) throws InterruptedException {
        log.info("进入订单服务。。。。");

        Thread.sleep(10000);

        String url =  "http://cloud-payment-service/payment/"+id;
        Payment payment = restTemplate.getForObject(url, Payment.class);
        log.info("离开");
        return ResponseEntity.ok(payment);
    }
    public ResponseEntity<Payment> fallback(Integer id, Throwable e) {
        e.printStackTrace();
        Payment payment = new Payment();
        payment.setId(id);
        payment.setMessage("fallback...");
        return new ResponseEntity<>(payment, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable("id") Integer id){
        //String url = "http://localhost:9001/payment/"+id;

//        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/payment/"+id;

         String url =  "http://cloud-payment-service/payment/"+id;

        Payment payment = restTemplate.getForObject(url, Payment.class);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/test-load-balancer")
    public String testLoadBalancer() {
        ServiceInstance instance = loadBalancerClient.choose("cloud-payment-service");
       // LoadBalancerRequestFactory
       // LoadBalancerInterceptor
        //BlockingLoadBalancerClient

        return instance.getHost() + ":"+instance.getPort();
    }

    //@Autowired
    @Resource
    private PaymentClient paymentClient;

    @GetMapping("/feign/payment/{id}")
    public ResponseEntity<Payment> getPaymentByFeign(@PathVariable("id") Integer id){
        Payment payment = paymentClient.payment(id);
        return  ResponseEntity.ok(payment);
    }
}
