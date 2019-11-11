package com.inxedu.os.edu.util.qq.rewrite;

import com.qq.connect.QQConnectException;
import com.qq.connect.utils.http.ImageItem;
import com.qq.connect.utils.http.MySSLSocketFactory;
import com.qq.connect.utils.http.PostParameter;
import com.qq.connect.utils.json.JSONException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.*;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class HttpClient implements Serializable {
    private static final long serialVersionUID = 1458439729090743687L;
    private static final int OK = 200;
    private static final int NOT_MODIFIED = 304;
    private static final int BAD_REQUEST = 400;
    private static final int NOT_AUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int NOT_ACCEPTABLE = 406;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private String proxyHost;
    private int proxyPort;
    private String proxyAuthUser;
    private String proxyAuthPassword;
    private String token;
    private String openID;
    private static final boolean DEBUG = Configuration.getDebug();
    static Logger log = Logger.getLogger(HttpClient.class.getName());
    org.apache.commons.httpclient.HttpClient client;
    private MultiThreadedHttpConnectionManager connectionManager;
    private int maxSize;

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getOpenID() throws QQConnectException {
        if(this.openID != null && !this.openID.equals("")) {
            return this.openID;
        } else {
            throw new QQConnectException("please invoke the setOpenID and setToken first!");
        }
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public String getProxyAuthUser() {
        return this.proxyAuthUser;
    }

    public String getProxyAuthPassword() {
        return this.proxyAuthPassword;
    }

    public String setToken(String token) {
        this.token = token;
        return this.token;
    }

    public HttpClient() {
        this(150, 30000, 30000, 1048576);
    }

    public HttpClient(int maxConPerHost, int conTimeOutMs, int soTimeOutMs, int maxSize) {
        this.proxyHost = null;
        this.proxyPort = 0;
        this.proxyAuthUser = null;
        this.proxyAuthPassword = null;
        this.token = null;
        this.openID = null;
        this.client = null;
        this.connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = this.connectionManager.getParams();
        params.setDefaultMaxConnectionsPerHost(maxConPerHost);
        params.setConnectionTimeout(conTimeOutMs);
        params.setSoTimeout(soTimeOutMs);
        HttpClientParams clientParams = new HttpClientParams();
        clientParams.setCookiePolicy("ignoreCookies");
        this.client = new org.apache.commons.httpclient.HttpClient(clientParams, this.connectionManager);
        Protocol myhttps = new Protocol("https", new MySSLSocketFactory(), 443);
        Protocol.registerProtocol("https", myhttps);
        this.maxSize = maxSize;
        if(this.proxyHost != null && !this.proxyHost.equals("")) {
            this.client.getHostConfiguration().setProxy(this.proxyHost, this.proxyPort);
            this.client.getParams().setAuthenticationPreemptive(true);
            if(this.proxyAuthUser != null && !this.proxyAuthUser.equals("")) {
                this.client.getState().setProxyCredentials(AuthScope.ANY, new UsernamePasswordCredentials(this.proxyAuthUser, this.proxyAuthPassword));
                log("Proxy AuthUser: " + this.proxyAuthUser);
                log("Proxy AuthPassword: " + this.proxyAuthPassword);
            }
        }

    }

    private static void log(String message) {
        if(DEBUG) {
            log.debug(message);
        }

    }

    public Response get(String url) throws QQConnectException {
        return this.get(url, new PostParameter[0]);
    }

    public Response get(String url, PostParameter[] params) throws QQConnectException {
        log("Request:");
        log("GET:" + url);
        if(null != params && params.length > 0) {
            String getmethod = encodeParameters(params);
            log("get request parameter : " + getmethod);
            if(-1 == url.indexOf("?")) {
                url = url + "?" + getmethod;
            } else {
                url = url + "&" + getmethod;
            }
        }

        GetMethod getmethod1 = new GetMethod(url);
        return this.httpRequest(getmethod1);
    }

    public Response delete(String url, PostParameter[] params) throws QQConnectException {
        if(0 != params.length) {
            String deleteMethod = encodeParameters(params);
            if(-1 == url.indexOf("?")) {
                url = url + "?" + deleteMethod;
            } else {
                url = url + "&" + deleteMethod;
            }
        }

        DeleteMethod deleteMethod1 = new DeleteMethod(url);
        return this.httpRequest(deleteMethod1);
    }

    public Response post(String url, PostParameter[] params) throws QQConnectException {
        return this.post(url, params, Boolean.valueOf(false));
    }

    public Response post(String url, PostParameter[] params, Boolean WithTokenHeader) throws QQConnectException {
        log("Request:");
        log("POST" + url);
        PostMethod postMethod = new PostMethod(url);

        for(int param = 0; param < params.length; ++param) {
            postMethod.addParameter(params[param].getName(), params[param].getValue());
        }

        HttpMethodParams var6 = postMethod.getParams();
        var6.setContentCharset("UTF-8");
        return WithTokenHeader.booleanValue()?this.httpRequest(postMethod):this.httpRequest(postMethod, WithTokenHeader);
    }

    public Response multPartURL(String url, PostParameter[] params, ImageItem item) throws QQConnectException {
        PostMethod postMethod = new PostMethod(url);

        try {
            Part[] ex = null;
            if(params == null) {
                ex = new Part[1];
            } else {
                ex = new Part[params.length + 1];
            }

            if(params != null) {
                int i = 0;
                PostParameter[] arr$ = params;
                int len$ = params.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    PostParameter entry = arr$[i$];
                    ex[i++] = new StringPart(entry.getName(), entry.getValue(), "UTF-8");
                }

                ex[ex.length - 1] = new HttpClient.ByteArrayPart(item.getContent(), item.getName(), item.getContentType());
            }

            postMethod.setRequestEntity(new MultipartRequestEntity(ex, postMethod.getParams()));
            return this.httpRequest(postMethod);
        } catch (Exception var11) {
            throw new QQConnectException(var11.getMessage(), var11, -1);
        }
    }

    private Response multPartURL(String fileParamName, String url, PostParameter[] params, File file, boolean authenticated) throws QQConnectException {
        PostMethod postMethod = new PostMethod(url);

        try {
            Part[] ex = null;
            if(params == null) {
                ex = new Part[1];
            } else {
                ex = new Part[params.length + 1];
            }

            if(params != null) {
                int filePart = 0;
                PostParameter[] arr$ = params;
                int len$ = params.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    PostParameter entry = arr$[i$];
                    ex[filePart++] = new StringPart(entry.getName(), entry.getValue());
                }
            }

            FilePart var14 = new FilePart(fileParamName, file.getName(), file, (new MimetypesFileTypeMap()).getContentType(file), "UTF-8");
            var14.setTransferEncoding("binary");
            ex[ex.length - 1] = var14;
            postMethod.setRequestEntity(new MultipartRequestEntity(ex, postMethod.getParams()));
            return this.httpRequest(postMethod);
        } catch (Exception var13) {
            throw new QQConnectException(var13.getMessage(), var13, -1);
        }
    }

    public Response httpRequest(HttpMethod method) throws QQConnectException {
        return this.httpRequest(method, Boolean.valueOf(false));
    }

    public Response httpRequest(HttpMethod method, Boolean WithTokenHeader) throws QQConnectException {
        byte responseCode = -1;

        Response var22;
        try {
            InetAddress ipaddr = InetAddress.getLocalHost();
            ArrayList ioe = new ArrayList();
            if(WithTokenHeader.booleanValue()) {
                ioe.add(new Header("Authorization", "OAuth2 "));
                ioe.add(new Header("API-RemoteIP", ipaddr.getHostAddress()));
                this.client.getHostConfiguration().getParams().setParameter("http.default-headers", ioe);
                Iterator resHeader = ioe.iterator();

                while(resHeader.hasNext()) {
                    Header response = (Header)resHeader.next();
                    log(response.getName() + ": " + response.getValue());
                }
            }

            method.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler(3, false));
            this.client.executeMethod(method);
            Header[] var19 = method.getResponseHeaders();
            int var18 = method.getStatusCode();
            log("Response:");
            log("https StatusCode:" + String.valueOf(var18));
            Header[] var20 = var19;
            int e = var19.length;

            for(int i$ = 0; i$ < e; ++i$) {
                Header header = var20[i$];
                log(header.getName() + ":" + header.getValue());
            }

            Response var21 = new Response();
            var21.setResponseAsString(new String(method.getResponseBody(), "utf-8"));
            log(var21.toString() + "\n");
            if(var18 != 200) {
                try {
                    throw new QQConnectException(getCause(var18), var21.asJSONObject(), method.getStatusCode());
                } catch (JSONException var15) {
                    var15.printStackTrace();
                }
            }

            var22 = var21;
        } catch (IOException var16) {
            throw new QQConnectException(var16.getMessage(), var16, responseCode);
        } finally {
            method.releaseConnection();
        }

        return var22;
    }

    private static String encodeParameters(PostParameter[] postParams) {
        StringBuffer buf = new StringBuffer();

        for(int j = 0; j < postParams.length; ++j) {
            if(j != 0) {
                buf.append("&");
            }

            try {
                buf.append(URLEncoder.encode(postParams[j].getName(), "UTF-8")).append("=").append(URLEncoder.encode(postParams[j].getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException var4) {
                ;
            }
        }

        return buf.toString();
    }

    private static String getCause(int statusCode) {
        String cause = null;
        switch(statusCode) {
            case 304:
                break;
            case 400:
                cause = "请求无效";
                break;
            case 401:
                cause = "未获得授权";
                break;
            case 403:
                cause = "无权限访问当前资源";
                break;
            case 404:
                cause = "资源不存在";
                break;
            case 406:
                cause = "请检查请求参数";
                break;
            case 500:
                cause = "服务器出错了";
                break;
            case 502:
                cause = "服务器出错了";
                break;
            case 503:
                cause = "当前访问量过大，请稍后重试";
                break;
            default:
                cause = "";
        }

        return statusCode + ":" + cause;
    }

    public String getToken() throws QQConnectException {
        if(this.token != null && !this.token.equals("")) {
            return this.token;
        } else {
            throw new QQConnectException("please invoke the setToken first !");
        }
    }

    private static class ByteArrayPart extends PartBase {
        private byte[] mData;
        private String mName;

        public ByteArrayPart(byte[] data, String name, String type) throws IOException {
            super(name, type, "UTF-8", "binary");
            this.mName = name;
            this.mData = data;
        }

        protected void sendData(OutputStream out) throws IOException {
            out.write(this.mData);
        }

        protected long lengthOfData() throws IOException {
            return (long)this.mData.length;
        }

        protected void sendDispositionHeader(OutputStream out) throws IOException {
            super.sendDispositionHeader(out);
            StringBuilder buf = new StringBuilder();
            buf.append("; filename=\"").append(this.mName).append("\"");
            out.write(buf.toString().getBytes());
        }
    }
}
