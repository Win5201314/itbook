package springaoptest.demo.proxy;

public class PrinterProxy implements Printeable {

    private String name;
    private Printer printer;

    @Override
    public synchronized void setName(String name) {
        if (printer != null) {
            printer.setName(name);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void print(String s) {
        if (printer == null) {
            printer = new Printer(s);
        }
        printer.print(s);
    }
}
