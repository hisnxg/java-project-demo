package com.nxg.demo.client;

import com.nxg.demo.entity.Payment;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author nxg
 * date 2022/1/26
 * @apiNote
 */
//@FeignClient(value = "cloud-payment-service",fallback = PaymentClient.MyFallback.class)
@FeignClient(value = "cloud-payment-service",fallbackFactory = PaymentClient.MyFallbackFactory.class)
public interface PaymentClient {

    @GetMapping("/payment/{id}")
    public Payment payment(@PathVariable("id") Integer id);

    /**
     * 熔断降级
     */
    @Component
    static class MyFallback implements PaymentClient{

        @Override
        public Payment payment(Integer id) {
            Payment payment = new Payment(id,"熔断降级方法返回");
            return payment;
        }
    }

    /**
     * fallback 熔断降级工厂
     */
    @Component //注册到spring容器中
    static class MyFallbackFactory implements FallbackFactory<MyFallback> {

        @Override
        public MyFallback create(Throwable cause) {
            cause.printStackTrace();
            return new MyFallback();
        }
    }
}
