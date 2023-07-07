//package com.muyan.pms.service;
//
///**
// * @author My_muyan
// * @create 2023/5/10 23:54
// */
//import cn.enaium.configuration.AlipayConfiguration;
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradeCloseRequest;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.request.AlipayTradeRefundRequest;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.AllArgsConstructor;
//import lombok.val;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
///**
// * @author Enaium
// */
//@Service
//@AllArgsConstructor
//public class OrdersService {
//    private final AlipayConfiguration configuration;
//
//    public String createOrder(String out_trade_no, String total_amount, String subject) throws JsonProcessingException, AlipayApiException {
//        val client = new DefaultAlipayClient(configuration.gateway, configuration.appId, configuration.privateKey, "json", StandardCharsets.UTF_8.name(), configuration.alipayPublicKey, configuration.signType);
//        val request = new AlipayTradePagePayRequest();
//        request.setNotifyUrl(configuration.notifyUrl);
//        request.setReturnUrl(configuration.returnUrl);
//        request.setBizContent(new ObjectMapper().writeValueAsString(Map.of(
//                "out_trade_no", out_trade_no,
//                "total_amount", total_amount,
//                "subject", subject,
//                "product_code", "FAST_INSTANT_TRADE_PAY"
//        )));
//        val response = client.pageExecute(request);
//        return response.getBody();
//    }
//
//    public void closeOrder(String out_trade_no) throws JsonProcessingException, AlipayApiException {
//        val client = new DefaultAlipayClient(configuration.gateway, configuration.appId, configuration.privateKey, "json", StandardCharsets.UTF_8.name(), configuration.alipayPublicKey, configuration.signType);
//        val request = new AlipayTradeCloseRequest();
//        request.setBizContent(new ObjectMapper().writeValueAsString(Map.of(
//                "out_trade_no", out_trade_no
//        )));
//        client.execute(request);
//    }
//
//    public void refund(String out_trade_no, BigDecimal amount) throws JsonProcessingException, AlipayApiException {
//        val client = new DefaultAlipayClient(configuration.gateway, configuration.appId, configuration.privateKey, "json", StandardCharsets.UTF_8.name(), configuration.alipayPublicKey, configuration.signType);
//        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
//        request.setBizContent(new ObjectMapper().writeValueAsString(Map.of(
//                "out_trade_no", out_trade_no,
//                "refund_amount", amount.toString()
//        )));
//        client.execute(request);
//    }
//}
