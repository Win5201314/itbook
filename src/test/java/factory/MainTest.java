package factory;

public class MainTest {

    public static void main(String[] args) {
        PTFactory factory = new PTFactory();
        Sender sender = factory.creatSender("email");
        sender.send("hello!");

        //sender = factory.creatShortMessage();
        //sender.send("================");
        sender = PTFactory.creatShortMessage();
        sender.send("静态工厂");
    }
}
