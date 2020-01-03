import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    private static Logger logger = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        // System.out.println("This is println message.");
        double i = 1100 + 400 + 600 + 1205.14 + 575.08 + 9.98 + 298.5;//6-28 已经给了
        System.out.println("-------1-------->" + i);
        double m = 400 + 600 + 93.4 + 545.62;//6-4 给了
        System.out.println("--------2------->" + m);
        double n = i + m;
        System.out.println("-------5-6-------->" + n);
        double j = 400 + 600;//2019-7-18
        System.out.println("--------------->" + j);
        double count = i + j + m;
        System.out.println("--------------->" + count);
        double pop = m + j;
        System.out.println("--------------->" + pop);
        //8月份
        double a = 400 + 600 + 671.82 + 10 + 570.8 + 39 + 5 + 300;//WhatsApp短信接码 给了 2019-9-3
        System.out.println("--------------->" + a);
        //9月份
        double b = 150 + 677.59 + 400 + 600 + 32.5 + 44 + 570.8 + 38.5 + 35 + 32 +28.5 + 25 + 34.5 + 36.5 + 50 + 11000;//给了
        System.out.println("--------------->" + b);
        //10月份
        double c = 400 + 33 + 638.12 + 570.8 + 1700 + 300 + 600 + 600;//1700公司财务审计 给了 2019-10-31
        System.out.println("--------------->" + c);
        double d = 400 + 394.05 + 566.52 + 19.9 + 299.4 + 600;//11月份 + 垃圾袋 + 299.4（月宽带费）给了
        System.out.println("------------------------>" + d);
        double ff = 400 + 297.29 + 546.6 + 600 + 11000;//12月份给了 2019-12-16
        System.out.println("------------------------>" + ff);
        double ll = 186.75 + 400;//2020-1
        System.out.println("------------------------>" + ff);
        // 记录debug级别的信息
        //logger.debug("This is debug message.");
        // 记录info级别的信息
        //logger.info("This is info message.");
        // 记录error级别的信息
        //logger.error("This is error message.");
        printArray(new String[] {"1", "6"});
    }

    @org.junit.Test
    public void main1() {
        String msg = "85745是你的验证码";
        Pattern pattern = Pattern.compile("(?<![0-9])([0-9]{" + 5 + "})(?![0-9])");
        Matcher m = pattern.matcher(msg);
        if (m.find()) {
            String shortMessage = m.group();
            System.out.println(shortMessage);
        }
    }

    public static <T> void printArray(T[] array) {
        for (T t : array) {
            System.out.println("----" + t);
        }
    }
}
