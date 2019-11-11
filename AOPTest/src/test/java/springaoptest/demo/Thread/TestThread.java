package springaoptest.demo.Thread;

public class TestThread {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();

    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
        }
    }
}
