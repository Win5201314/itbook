package callback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassA implements CallBack {

    public static void main(String[] args) {
        //new ClassA().mack();
        //new ClassA().mack2();
        String messages = "22213是你的 Twitter 确认码是 。";
        //Pattern pattern = Pattern.compile("(?<=[^\\d])\\d{5}(?=[^\\d])");
        Pattern pattern = Pattern.compile("(?<![0-9])([0-9]{" + 5 + "})(?![0-9])");
        Matcher m = pattern.matcher(messages);
        if (m.find()) {
            System.out.println("--------------" + m.group());
        } else {
            System.out.println("---------rdfr---------------");
        }
        String f = ZD[0].split(":")[2];
        System.out.println("-------------------------" + f);
        System.out.println("------------------------" + "success|13888888888".split("\\|")[1]);
    }
    private static String[] ZD = {"IR:伊朗:+98", "KW:科威特:+965", "IQ:伊拉克:+964", "OM:阿曼:+968", "QA:卡塔尔:+974", "BH:巴林:+973",
            "TR:土耳其:+90", "IL:以色列:+972", "SY:叙利亚:+963", "LB:黎巴嫩:+961", "JO:约旦:+962", "YE:也门:+967", "CY:塞浦路斯:+357",
            "SD:苏丹:+249", "EG:埃及:+20", "LY:利比亚:+218", "TN:突尼斯:+216", "DZ:阿尔及利亚:+213", "MA:摩洛哥:+212"};
    //同步回调
    void mack() {
        System.out.println("-------------------------first");
        ClassB classB = new ClassB();
        classB.some(this);
        System.out.println("------------------------fff");
    }

    //异步回调
    void mack2() {
        System.out.println("-------------------------first");
        new Thread(new Runnable() {
            @Override
            public void run() {
                ClassB classB = new ClassB();
                classB.some(ClassA.this);
            }
        }).start();
        System.out.println("------------------------fff");
    }

    @Override
    public void doSomeThing(String name) {
        System.out.println("------------------" + name);
    }
}
