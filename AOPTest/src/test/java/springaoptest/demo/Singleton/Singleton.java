package springaoptest.demo.Singleton;

/**
 * 懒汉模式
 *线程不安全，延迟初始化，严格意义上不是不是单例模式
 */
public class Singleton {

    private static Singleton singleton;
    private Singleton() {}

    public static Singleton getInstance() {
        if (singleton == null) {
            return new Singleton();
        }
        return singleton;
    }
}
