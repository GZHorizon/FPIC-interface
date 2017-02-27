package com.yqsh.ifmp.pub;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yqsh.ifmp.service.BusinessManager;
import com.yqsh.ifmp.util.AnsiiX99MacUtil;
import com.yqsh.ifmp.util.JSONUtils;
import com.yqsh.ifmp.util.PubMethod;
import com.yqsh.ifmp.util.ZipUtils;

@Controller
@RequestMapping("/services")
public class IfmpController {

    private static final Logger log = Logger.getLogger(IfmpController.class);

    private static final String INTERFACE_VERSION = "001";
    private static final String SYN_VERSION = "002";

    @Autowired
    private BusinessManager businessManager;

    @Autowired
    @Qualifier("macKey")
    private String macKey;

    @Autowired
    @Qualifier("synMac")
    private String synMac;

    @Autowired
    @Qualifier("isGdMac")
    private Boolean isGdMac = false;

    @RequestMapping(value = "/thirdInterface", method = RequestMethod.POST)
    public void pubMethod(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        log.debug("进入pubmethod方法");
        String result = "";
        String ver = "";
        String requestType = "";
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            ServletInputStream inputStream = request.getInputStream();
            String body = analyzeStream(inputStream);
            log.debug("解压后报文：" + body);
            requestType = getRequestType(body);//获取请求类型
            log.debug("请求类型：" + requestType);
            ver = getVer(body);//获取版本
            log.debug("版本：" + ver);
            String mac = getMac(body);//获取mac验证
            log.debug("请求验证MAC:" + mac);
            if (INTERFACE_VERSION.equals(ver)) {
                if (isGdMac) {
                    if (synMac.equals(mac)) {//采用固定MAC校验
                        String replyJsonStr = method(PubMethod.getMethod(requestType), getJsonBody(body));
                        result = requestType + mac + ver + replyJsonStr;
                    } else {
                        result = errorMac(ver, requestType);
                    }
                } else {
                    byte[] bodybyte = checkBody(body).getBytes();
                    String cmac = AnsiiX99MacUtil.getMac(bodybyte, macKey);
                    log.debug("计算出的MAC:" + cmac);
                    if (mac.equals(cmac)) {
                        String replyJsonStr = method(PubMethod.getMethod(requestType), getJsonBody(body));
                        byte[] rbodybyte = (requestType + ver + replyJsonStr).getBytes();
                        String rmac = AnsiiX99MacUtil.getMac(rbodybyte, macKey);
                        result = requestType + rmac + ver + replyJsonStr;
                    } else {
                        result = errorMac(ver, requestType);
                    }
                }
            } else if (SYN_VERSION.equals(ver)) {
                log.debug("固定MAC:" + synMac);
                if (synMac.equals(mac)) {//采用固定MAC校验
                    String replyJsonStr = method(PubMethod.getSynMethod(requestType), getJsonBody(body));
                    result = requestType + mac + ver + replyJsonStr;
                } else {
                    result = errorMac(ver, requestType);
                }
            } else {
                map.put("result", "0006");
                map.put("msg", "版本信息错误");
                log.warn("版本信息错误");
                byte[] rbodybyte = (requestType + ver + JSONUtils.objToJsonStr(map)).getBytes();
                String rmac = AnsiiX99MacUtil.getMac(rbodybyte, macKey);
                result = requestType + rmac + ver + JSONUtils.objToJsonStr(map);
            }
            log.debug("退出pubmethod方法");
        } catch (Exception e) {
            log.error("处理pubmethod方法异常", e);
            map.put("result", "9999");
            map.put("msg", "未知错误");
            //回复结果：请求类型+MAC+版本+JSON字符串
            byte[] rbodybyte = (requestType + ver + JSONUtils.objToJsonStr(map)).getBytes();
            String rmac = AnsiiX99MacUtil.getMac(rbodybyte, macKey);
            result = requestType + rmac + ver + JSONUtils.objToJsonStr(map);
        }
        log.debug("压缩前报文：" + result);
        result = ZipUtils.getBase64Encoder(result.getBytes("UTF-8"));
        result = result.replaceAll("\r\n", "");
        log.debug("回复结果：" + result);

        PrintWriter writer = response.getWriter();
        writer.write(result);
        writer.flush();
        writer.close();


    }

    private String errorMac(String ver, String requestType) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("result", "0004");
        map.put("msg", "MAC验证失败");
        log.warn("MAC验证失败");
        byte[] rbodybyte = (requestType + ver + JSONUtils.objToJsonStr(map)).getBytes();
        String rmac = AnsiiX99MacUtil.getMac(rbodybyte, macKey);
        String result = requestType + rmac + ver + JSONUtils.objToJsonStr(map);
        return result;
    }

    private String getRequestType(String body) {
        return body.substring(0, 4);
    }

    private String getMac(String body) {
        return body.substring(4, 20);
    }

    private String getJsonBody(String body) {
        return body.substring(20);
    }

    private String getVer(String body) {
        return body.substring(20, 23);
    }

    private String checkBody(String body) {
        return getRequestType(body) + body.substring(20);
    }

    private String analyzeStream(InputStream in) throws IOException {
        StringBuffer sbuffer = new StringBuffer();
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");
        int charCount = -1;
        while ((charCount = reader.read()) != -1) {
            sbuffer.append((char) charCount);
        }
        log.debug("解压前报文：" + sbuffer.toString());
        byte[] strByte = ZipUtils.getBase64Decoder(sbuffer.toString());
        return new String(strByte, "UTF-8");
    }

    private String method(String methodName, String jsonStr) throws Exception {
        Method method = businessManager.getClass().getMethod(methodName, String.class);
        Object obj = method.invoke(businessManager, jsonStr);
        return obj.toString();
    }

}
