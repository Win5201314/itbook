package callback;

public class ClassB {

    void some(CallBack callBack) {
        System.out.println("-------------------------B");
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callBack.doSomeThing("now");
    }
}
