package base;

public class HelloWorld {

    public static void main(String[] args) {
        printArray(new String[] {"1", "6"});
        System.out.print(Integer.MAX_VALUE*2);
        System.out.print(Integer.MIN_VALUE*2);
    }

    /**
     * 泛型方法
     */
    public static <T> void printArray(T[] array) {
        for (T t : array) {
            System.out.println("----" + t);
        }
    }

}
