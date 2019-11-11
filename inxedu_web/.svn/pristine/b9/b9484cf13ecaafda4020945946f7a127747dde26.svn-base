package com.inxedu.os.edu.util.alipay;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckURL {
    public CheckURL() {
    }

    public static String check(String urlvalue) {
        String inputLine = "";
        try {
            URL e = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection)e.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return inputLine;
    }
}