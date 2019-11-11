package factory;

public class ShortMessageSender implements Sender {
    @Override
    public void send(String s) {
        System.out.println("----------------------> short send:" + s);
    }
}
