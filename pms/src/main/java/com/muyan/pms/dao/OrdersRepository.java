//package com.muyan.pms.dao;
//
///**
// * @author My_muyan
// * @create 2023/5/11 0:04
// */
//import cn.enaium.model.entity.GoodsFetcher;
//import cn.enaium.model.entity.Order;
//import cn.enaium.model.entity.OrderFetcher;
//import cn.enaium.model.entity.OrderTable;
//import lombok.val;
//import org.babyfish.jimmer.spring.repository.JRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
///**
// * @author Enaium
// */
//@Repository
//public interface OrdersRepository extends JRepository<Order, Long> {
//    default Optional<Order> findByOrderNo(String orderNo) {
//        val orders = sql().createQuery(OrderTable.$).where(OrderTable.$.orderNo().eq(orderNo)).select(OrderTable.$.fetch(OrderFetcher.$.allScalarFields().goods(GoodsFetcher.$.allScalarFields()))).execute();
//        if (orders.isEmpty()) {
//            return Optional.empty();
//        } else {
//            return Optional.of(orders.get(0));
//        }
//    }
//}
