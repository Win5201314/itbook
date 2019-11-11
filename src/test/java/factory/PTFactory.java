package factory;

public class PTFactory {

    public Sender creatSender(String type) {
        if (type.equals("email")) {
            return new EmailSender();
        } else if (type.equals("shortMessage")) {
            return new ShortMessageSender();
        }
        return null;
    }

    public static Sender creatEmail() {
        return new EmailSender();
    }

    public static Sender creatShortMessage() {
        return new ShortMessageSender();
    }
}
