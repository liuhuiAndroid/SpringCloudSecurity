package com.noc.security.order;

import com.noc.security.server.resource.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     *  @AuthenticationPrincipal(expression = "#this.id") Long id
     */
    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo orderInfo, @AuthenticationPrincipal User user) {
        log.info("user is " + user.getUsername());
//        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9060/prices/" + orderInfo.getProductId(),
//                PriceInfo.class);
//        log.info("price is " + priceInfo.getPrice());
        return orderInfo;
    }

    @GetMapping("/{id}")
    public OrderInfo getOrderInfo(@PathVariable Long id, @AuthenticationPrincipal User user) {
        log.info("orderId is " + id);
        log.info("user is " + user.getUsername());
        return new OrderInfo();
    }

}
