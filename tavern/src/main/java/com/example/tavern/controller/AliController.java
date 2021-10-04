package com.example.tavern.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.tavern.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/pay")
public class AliController {



    @RequestMapping(value = "/aliindex.html")
    public String index( Model model,String s) {
        //return "/system/alipay/index";
        return "alipay/index";
    }

    @RequestMapping(value = "/notify_urljsp")
    public String notify_url(Model model,String s) {
        return "/system/alipay/notify_url";
    }

    @RequestMapping(value = "/return_urljsp")
    public String return_url( Model model,String s) {
        return "/system/alipay/return_url";
    }

    @RequestMapping(value = "/alipaytradeclosejsp")
    public String close( Model model,String s) {
        return "/system/alipay/alipay.trade.close";
    }

    @RequestMapping(value = "/alipaytradefastpayrefundqueryjsp")
    public String query( Model model,String s) {
        return "/system/alipay/alipay.trade.fastpay.refund.query";
    }


    @RequestMapping(value = "/alipaytradepagepayjsp.html", produces = {MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String  pay(Model model, String s, HttpServletRequest request, HttpServletResponse response) {
        String result="";
        try {
                //获得初始化的AlipayClient
                AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

                //设置请求参数
                AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
                alipayRequest.setReturnUrl(AlipayConfig.return_url);
                alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

                //商户订单号，商户网站订单系统中唯一订单号，必填
                String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
                //付款金额，必填
                String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
                //订单名称，必填
                String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
                //商品描述，可空
                String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

                alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                        + "\"total_amount\":\"" + total_amount + "\","
                        + "\"subject\":\"" + subject + "\","
                        + "\"body\":\"" + body + "\","
                        + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

                //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
                //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                //		+ "\"total_amount\":\""+ total_amount +"\","
                //		+ "\"subject\":\""+ subject +"\","
                //		+ "\"body\":\""+ body +"\","
                //		+ "\"timeout_express\":\"10m\","
                //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
                //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

                //请求
                result = alipayClient.pageExecute(alipayRequest).getBody();


            }
          catch (Exception e) {
                    e.printStackTrace();
          }
         return result.toString();

    }

    @RequestMapping(value = "/alipaytradequeryjsp")
    public String tradequery( Model model,String s) {
        return "/system/alipay/alipay.trade.query";
    }

    @RequestMapping(value = "/alipaytraderefundjsp")
    public String refund( Model model,String s) {
        return "/system/alipay/alipay.trade.refund";
    }



}
