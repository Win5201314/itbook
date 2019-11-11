package com.inxedu.os.edu.util.qq.rewrite;

import com.qq.connect.QQConnectException;
import com.qq.connect.utils.json.JSONArray;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class Response {
    private static final boolean DEBUG = Configuration.getDebug();
    static Logger log = Logger.getLogger(Response.class.getName());
    private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal() {
        protected DocumentBuilder initialValue() {
            try {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder();
            } catch (ParserConfigurationException var2) {
                throw new ExceptionInInitializerError(var2);
            }
        }
    };
    private int statusCode;
    private Document responseAsDocument = null;
    private String responseAsString = null;
    private InputStream is;
    private HttpURLConnection con;
    private boolean streamConsumed = false;
    private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");

    public Response() {
    }

    public Response(HttpURLConnection con) throws IOException {
        this.con = con;
        this.statusCode = con.getResponseCode();
        if(null == (this.is = con.getErrorStream())) {
            this.is = con.getInputStream();
        }

        if(null != this.is && "gzip".equals(con.getContentEncoding())) {
            this.is = new GZIPInputStream(this.is);
        }

    }

    Response(String content) {
        this.responseAsString = content;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseHeader(String name) {
        return this.con != null?this.con.getHeaderField(name):null;
    }

    public InputStream asStream() {
        if(this.streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        } else {
            return this.is;
        }
    }

    public String asString() throws QQConnectException {
        if(null == this.responseAsString) {
            try {
                InputStream ioe = this.asStream();
                if(null == ioe) {
                    return null;
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(ioe, "UTF-8"));
                StringBuffer buf = new StringBuffer();

                String line;
                while(null != (line = br.readLine())) {
                    buf.append(line).append("\n");
                }

                this.responseAsString = buf.toString();
                if(Configuration.isDalvik()) {
                    this.responseAsString = unescape(this.responseAsString);
                }

                this.log(this.responseAsString);
                ioe.close();
                this.con.disconnect();
                this.streamConsumed = true;
            } catch (NullPointerException var5) {
                throw new QQConnectException(var5.getMessage(), var5);
            } catch (IOException var6) {
                throw new QQConnectException(var6.getMessage(), var6);
            }
        }

        return this.responseAsString;
    }

    public Document asDocument() throws QQConnectException {
        if(null == this.responseAsDocument) {
            try {
                this.responseAsDocument = ((DocumentBuilder)builders.get()).parse(new ByteArrayInputStream(this.asString().getBytes("UTF-8")));
            } catch (SAXException var2) {
                throw new QQConnectException("The response body was not well-formed:\n" + this.responseAsString, var2);
            } catch (IOException var3) {
                throw new QQConnectException("There\'s something with the connection.", var3);
            }
        }

        return this.responseAsDocument;
    }

    public JSONObject asJSONObject() throws QQConnectException {
        try {
            return new JSONObject(this.asString());
        } catch (JSONException var2) {
            throw new QQConnectException(var2.getMessage() + ":" + this.responseAsString, var2);
        }
    }

    public JSONArray asJSONArray() throws QQConnectException {
        try {
            return new JSONArray(this.asString());
        } catch (Exception var2) {
            throw new QQConnectException(var2.getMessage() + ":" + this.responseAsString, var2);
        }
    }

    public InputStreamReader asReader() {
        try {
            return new InputStreamReader(this.is, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            return new InputStreamReader(this.is);
        }
    }

    public void disconnect() {
        this.con.disconnect();
    }

    public static String unescape(String original) {
        Matcher mm = escaped.matcher(original);
        StringBuffer unescaped = new StringBuffer();

        while(mm.find()) {
            mm.appendReplacement(unescaped, Character.toString((char)Integer.parseInt(mm.group(1), 10)));
        }

        mm.appendTail(unescaped);
        return unescaped.toString();
    }

    public String toString() {
        return null != this.responseAsString?this.responseAsString:"Response{statusCode=" + this.statusCode + ", response=" + this.responseAsDocument + ", responseString=\'" + this.responseAsString + '\'' + ", is=" + this.is + ", con=" + this.con + '}';
    }

    private void log(String message) {
        if(DEBUG) {
            log.debug("[" + new Date() + "]" + message);
        }

    }

    private void log(String message, String message2) {
        if(DEBUG) {
            this.log(message + message2);
        }

    }

    public String getResponseAsString() {
        return this.responseAsString;
    }

    public void setResponseAsString(String responseAsString) {
        this.responseAsString = responseAsString;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

