package com.googleguicetest;

/**
 * 两个线程对同一个实例的count属性进行修改，内存一致性问题
 * 锁-》对象锁 类锁
 */
public class AsyTest implements Runnable {

    static AsyTest asyTest1 = new AsyTest();
    /**
     * 就相当于两把不同的锁（对象锁）
     */
    Object object1 = new Object();
    Object object2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(asyTest1);
        Thread thread2 = new Thread(asyTest1);
        thread1.setName("悟空");
        thread2.setName("八戒");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("-----finished-----" + count);
    }

    static int count = 0;

    @Override
    public void run() {
        /*for (int i = 0; i < 1000000; i++) {
            count++;
        }*/
        /*synchronized (this) {
            for (int i = 0; i < 1000000; i++) {
                count++;
            }
        }*/

        synchronized (object1) {
            System.out.println(Thread.currentThread().getName() + "------开始--------");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "------结束--------");
        }
        synchronized (object1) {
            System.out.println(Thread.currentThread().getName() + "------开始2--------");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "------结束2--------");
        }
    }
}
