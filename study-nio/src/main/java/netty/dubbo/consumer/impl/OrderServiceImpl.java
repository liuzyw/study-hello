package netty.dubbo.consumer.impl;

import netty.dubbo.consumer.Order;
import netty.dubbo.consumer.OrderService;

/**
 * Created on 2018-11-17
 *
 * @author liuzhaoyuan
 */
public class OrderServiceImpl implements OrderService {


    @Override
    public Order getOrderById(int id) {

        Order order = new Order();
        order.setId(id);
        order.setType(1);
        order.setName("苹果");

        return order;
    }
}
