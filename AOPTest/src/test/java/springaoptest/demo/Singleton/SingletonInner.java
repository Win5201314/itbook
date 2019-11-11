package springaoptest.demo.Singleton;

/**
 * 静态内部类单例模式
 */
public class SingletonInner {

    private SingletonInner() {}

    public static SingletonInner getInstance() {
        return Inner.instance;
    }

    public static class Inner {
        private static final SingletonInner instance = new SingletonInner();
    }
}
