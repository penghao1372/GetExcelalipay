package com.vy.playUitl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/alipay")
public class AliPlay {
    //沙箱应用的APPID
    public static String app_id="2016101400682083";
    //商户私钥
    public  static String merchant_private_key="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCsETJNJ/GX7/iTcYW4BLbTZDVFH7mvrdWo7sYAt0XqqznWb61p1atl+cLemZ96luczuTHoe+18ouDlkQG7ATI4icqHkYnUTAD7x1bw4R7K/Iuipc96UJLRrNipudxWpMDhVUGjUIYMETd+4fBYy9cO3fXWK1oxBnSz5RQ3/0Zo880p9Dra/uYQsh8wk/UqJkJLs43oWK8b7+JYVgB/tbHD0Cr0yEF353CHflz1a5gTFuSZHpOhLbQlTUs9vcyRdP42drhc+L+vHZl/NnmBtuW9Zjv9gVukr9NnIz5Ne5tqWuct5oiFZ1XyK8eTSAhWnk3yyB5z8mToSuP66F6I+lOzAgMBAAECggEARLI05/L7lJJ9JribD2XqbClEz00kQExh0ilKKf0Bz/f+hmtdy7Q/0t3NfDvPHQ1kTxUwJMZdgXYx3dVv4G5Q05vHnSpw4a5acisavd7TAW2kfhI+7HGAvG+KrPCEVJ9AVnZIV2CNtAGnq2OfJoRzXv8k6BW003/lVfEuilDDlqCffjJ43s9lH/NE49x/Ppzz1LX1xVo0UQB7/dxZbIvo3ANpeZpFQji5l16tdv56x4nHPbwQVhpO2JRpp0wP6n5g8Ji5qTaxjWhVKC2JmP1gTuIjnsLFCsSsMnYLT9WeHeXw4Zj9casSq2NFDfENABTqjJAaeHj/IHYZB1iwwXP6KQKBgQDi8ad/QVzJUqBDyeu9LJD3iRWUFxlBoO9IQUfsPmVDx05RSAsmmt13ybxauSUatF7f4yeXRX7gvqBtM3hKuEX8cYQIsyC9S35yZoRG1+wfQ2MxZbv4+LuUmCuY0yEsmUDlzzYcw2p9u1TSU4wN3//3l/A2+X0L56/HRw1Gdbg0jwKBgQDCGOSrOoeRmZTE9ICuJs+wUnRjjPmNmXtNzONyegd0xHijgpJmG5uygqPRFWJd4nww5oAjWJM3NZsMXFfPpVNAq9rrDaXFPN4+n0Th/Q4CKqikOSLBtXULB6dj0/xZjQ2IZ3x8mXeBCyKGmVNvrNUzPrUzJwZt/paTyjISx9honQKBgQCoRCS85em2G91YZc60ZObXonytYLMJRhokqMJ7F4hdyNj4y0H5TboHpzxQyBYKxtHRjss8DfsVCEJKtW7rd9XsZv5OtQIBWIhYqkpJfK9GPuC9L4FACKyMcIdiuKhQORE1U337taivsMYjbG7bmspe+fjRygo9WXurdbMSGxaJYQKBgHBfpyx3c3vZlic9g67QK5hi9J2aRfU0Mg5GHDzv55c0FMBd3VKek1MTjbKElfHTKaHKIc/U/U6Oi5jpcbF3fy7HELTbruj1p5pL7b6FGvIoLoD9z2ClJQFgygni9owGPRqHB6y/0U912eRhqsvSHZFQsenOo0tVhWpFOx5ic6mFAoGBANnHfKUdKHq3wlnDAtIKPiDdFCFyhqeFjn1m0LkxJS2/larUxz6wVtvDriDBAixNfRuon9339dyttyl6nLuMA1LEIwTJsIpSJ3Z3ejD8rGv0KCKc0jIwDzUZVA5Bxd6Lc5lGJN3HGImhfqYmXF09Oj3wEdGsjohjDZPDnczckLap";
    //支付宝公钥
    public  static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAggmGkPtQBhMqI0xRfpIfHki2vM09NIm7yD6pYEEama9ILLicL8xTGRh3Z4u6VlnwNHnzWkYxoULTrw2/CSAcxIKrL8PcmRABUhuPhgGC1MHnGcLjNsbiJJOt4WjkzCVesK5H1v4hKUAuzEmFcDdjncSu2zfgmd1t9MlCNF+X1AkThImv8ZCttwvLHxWS0QMtKR2m0dIH7kIk7p2jlJAX8XAHTGH69oOBFxA2jJj3fBTd3iGvVgK5fuwbP/CEm86dbtYUxM5a1XAB/WRBe0dOczF3IMCKLBYlyCoJlSTADY8plHQjHuvp6yyJQL07NIHn00NXO3zz1pqPO+nPN1bGsQIDAQAB";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1/alipay/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://127.0.0.1/alipay/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    //沙箱网关
    public static String gatewayUrl ="https://openapi.alipaydev.com/gateway.do";

    // 仅支持JSON
    public static String format = "JSON";


   // @ApiOperation(value = "发起支付", notes = "支付宝")
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public void pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset,
                alipay_public_key, sign_type); // 获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);// 在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"2015032001010100"+(int)(Math.random()*1000)+"\","
                + "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":"+(int)(Math.random()*10)+0.88+","
                + "    \"subject\":\"Iphone6 16G\"," + "    \"body\":\"Iphone6 16G 耐克金\","
                + "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","
                + "    \"extend_params\":{" + "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");// 填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + charset);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
  //  @ApiOperation(value = "支付同步回调", notes = "支付宝")
    @RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
    public void returnUrl(HttpServletRequest request, HttpServletResponse response)
            throws IOException, AlipayApiException {
        System.out.println("=================================同步回调=====================================");

        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type);

        // ——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号=" + out_trade_no);
            System.out.println("支付宝交易号=" + trade_no);
            System.out.println("付款金额=" + total_amount);

            response.getWriter().write(
                    "trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
        } else {
            response.getWriter().write("验签失败");
        }
        response.getWriter().flush();
        response.getWriter().close();

    }
    //@ApiOperation(value = "支付异步回调", notes = "支付宝")
    @RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response)
            throws AlipayApiException, IOException {
        System.out.println("#################################异步回调######################################");

        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        System.out.println(params);
        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type); // 调用SDK验证签名

        // ——请在这里编写您的程序（以下代码仅作参考）——

        /*
         * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
         * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
         * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
         * 4、验证app_id是否为该商户本身。
         */
        if (signVerified) {// 验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("商户订单号="+out_trade_no);
            System.out.println("支付宝交易号="+trade_no);
            System.out.println("交易状态="+trade_status);
            if (trade_status.equals("TRADE_FINISHED")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序

                // 注意：
                // 付款完成后，支付宝系统发送该交易状态通知
            }

            System.out.println("异步回调验证成功");
            response.getWriter().write("success");

        } else {// 验证失败
            System.out.println("异步回调验证失败");
            response.getWriter().write("fail");

            // 调试用，写文本函数记录程序运行情况是否正常
            // String sWord = AlipaySignature.getSignCheckContentV1(params);
            // AlipayConfig.logResult(sWord);
        }
        response.getWriter().flush();
        response.getWriter().close();
    }
}
