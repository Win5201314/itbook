package com.inxedu.os.edu.util.alipay;


import com.inxedu.os.common.alipay.Md5Encrypt;

import java.util.*;

public class SignatureHelper {
    public SignatureHelper() {
    }

    /**
     * 验证加密签名
     * @param params
     * @param privateKey
     * @return
     */
    public static String sign(Map params, String privateKey) {
        Properties properties = new Properties();
        Iterator content = params.keySet().iterator();

        while(content.hasNext()) {
            String name = (String)content.next();
            Object value = params.get(name);
            if(name != null && !name.equalsIgnoreCase("sign") && !name.equalsIgnoreCase("sign_type")) {
                properties.setProperty(name, value.toString());
            }
        }

        String content1 = getSignatureContent(properties);
        return sign(content1, privateKey);
    }

    public static String getSignatureContent(Properties properties) {
        StringBuffer content = new StringBuffer();
        ArrayList keys = new ArrayList(properties.keySet());
        Collections.sort(keys);

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = properties.getProperty(key);
            content.append((i != 0?"&":"") + key + "=" + value);
        }

        return content.toString();
    }

    public static String sign(String content, String privateKey) {
        if(privateKey == null) {
            return null;
        } else {
            String signBefore = content + privateKey;
            return Md5Encrypt.md5(signBefore);
        }
    }
}
