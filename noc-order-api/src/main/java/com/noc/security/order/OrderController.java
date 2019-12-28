package com.noc.security.order;

//import com.noc.security.server.resource.User;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 网关授权，会把信息放到Header中
     */
    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo orderInfo,@RequestHeader String username) {
        log.info("user is " + username);
        return orderInfo;
    }

    /**
     * 过时，逻辑挪到网关
     */
    @GetMapping("/{id}")
    public OrderInfo getOrderInfo(@PathVariable Long id, @RequestHeader String username) {
        log.info("orderId is " + id);
        log.info("user is " + username);
        return new OrderInfo();
    }

//    /**
//     *  过时，逻辑挪到网关
//     *  @AuthenticationPrincipal(expression = "#this.id") Long id
//     */
//    @PostMapping("/222")
//    public OrderInfo create2(@RequestBody OrderInfo orderInfo, @AuthenticationPrincipal User user) {
//        log.info("user is " + user.getUsername());
////        PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9060/prices/" + orderInfo.getProductId(),
////                PriceInfo.class);
////        log.info("price is " + priceInfo.getPrice());
//        return orderInfo;
//    }
//
//    /**
//     * 过时
//     */
//    @GetMapping("/1/{id}")
//    public OrderInfo getOrderInfo(@PathVariable Long id, @AuthenticationPrincipal User user) {
//        log.info("orderId is " + id);
//        log.info("user is " + user.getUsername());
//        return new OrderInfo();
//    }

}
