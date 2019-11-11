package com.inxedu.os.edu.util.qq.rewrite;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QQConnectResponse implements Serializable {
    private static final long serialVersionUID = 3519962197957449562L;

    public QQConnectResponse() {
    }

    public QQConnectResponse(Response res) {
    }

    protected static String getString(String name, JSONObject json, boolean decode) {
        String returnValue = null;

        try {
            returnValue = json.getString(name);
            if(decode) {
                try {
                    returnValue = URLDecoder.decode(returnValue, "UTF-8");
                } catch (UnsupportedEncodingException var5) {
                    ;
                }
            }
        } catch (JSONException var6) {
            ;
        }

        return returnValue;
    }

    protected static int getInt(String key, JSONObject json) throws JSONException {
        String str = json.getString(key);
        return null != str && !"".equals(str) && !"null".equals(str)?Integer.parseInt(str):-1;
    }
}
