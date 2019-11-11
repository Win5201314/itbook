package springaoptest.demo.Singleton;

/**
 * 饿汉模式
 * 线程安全，比较常用，但容易产生垃圾，因为一开始就初始化
 */
public class SingletonEH {

    private static SingletonEH instance = new SingletonEH();

    private SingletonEH() {}

    public static SingletonEH getInstance() {
        return instance;
    }
}
