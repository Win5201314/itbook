package springaoptest.demo.proxy;

public class Printer implements Printeable {

    private String name;
    public Printer(String name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------------打印机准备好了");
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print(String s) {
        System.out.println("-------------------------------开始打印" + s);
    }
}
