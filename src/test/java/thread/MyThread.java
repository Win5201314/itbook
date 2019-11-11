package thread;

public class MyThread implements Runnable {
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
