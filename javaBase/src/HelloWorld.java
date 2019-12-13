public class HelloWorld {

    //main方法详解：https://www.cnblogs.com/dodocie/p/7439651.html
    public static void main(String[] args) {
        Integer i = 5;
        System.out.println(i.intValue());
        Character c = new Character('5');
        System.out.println(Character.isDigit('5'));
        printArray(new String[] {"1", "6"});
    }
    //难点
    //1.java修饰符
    //2.java 面向对象知识
    //3.
    //
    //

    public static <T> void printArray(T[] array) {
        for (T t : array) {
            System.out.println("----" + t);
        }
    }
}
