package com.inxedu.os.edu.service.mobile;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.inxedu.os.common.util.DateUtils;
import com.inxedu.os.common.util.ObjectUtils;

/**
 * 多线程批量发送短信
 */
public class SmsBatchThread implements Runnable{
    private String content;
    @Getter
    private static int sumNum=0;
    @Getter
    private static List<String> list = new ArrayList();
    @Getter
    private static String result = "";
    public SmsBatchThread(List<String> list,String content) {
        this.content = content;
        SmsBatchThread.list.addAll(list);
        sumNum+=list.size();
    }
    public SmsBatchThread() {
    }
    @Override
    public void run() {
        try {
            //每100个手机批量发一次，发完休息1秒，直到发完为止
            if(ObjectUtils.isNotNull(list)){
                while(true){
                    if(list.size()>0){
                        List l = queryList(50);
                        String[] arr = (String[])l.toArray(new String[l.size()]);
                        if(ObjectUtils.isNotNull(arr)){
                            for(String str:arr){
                                SmsUtil smsUtil  = new SmsUtil();
                                smsUtil.setDestNumber(str.trim());
                                smsUtil.setMsgContent(content);
                                SmsThread smsThread = new SmsThread(smsUtil);
                                ExecutorService es = Executors.newFixedThreadPool(1);
                                Future future = es.submit(smsThread);
                                result = future.get().toString();
                                Thread.sleep(100);
                            }
                        }
                    }else{
                        sumNum =0;
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获得要发送的list加锁
    public synchronized List queryList(int num){
        List newList = new ArrayList();
        if(ObjectUtils.isNotNull(list)){
            if(list.size()<=num){
                for(int i=0;i<list.size();i++){
                    newList.add(list.get(i));
                }
                list = new ArrayList();
                return newList;
            }else{
                for(int i=0;i<num;i++){
                    newList.add(list.get(i));
                }
                for(int i=0;i<num;i++){
                    list.remove(0);
                }

            }
        }
        return newList;
    }
}
