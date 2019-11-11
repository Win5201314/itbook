package springaoptest.demo;

public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get() {
        return holder.get() == null ? "未知" : holder.get();
    }

    public static void set(String user) {
        holder.set(user);
    }
}
