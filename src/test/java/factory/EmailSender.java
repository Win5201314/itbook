package factory;

public class EmailSender implements Sender {
    @Override
    public void send(String s) {
        System.out.println("---------->email send:" + s);
    }
}
