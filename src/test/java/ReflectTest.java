import com.itbook.entity.Boo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.itbook.entity.Boo");
            System.out.println(c.getName());
            System.out.println(c.getSimpleName());
            Field[] fields = c.getFields();
            for (Field field : fields) {
                System.out.println(field);
            }
            Field[] fields1 = c.getDeclaredFields();
            for (Field field : fields1) {
                System.out.println(field);
            }
            Field field = c.getField("age");
            System.out.println(field);
            field = c.getDeclaredField("name");
            System.out.println(field);
            System.out.println("---------------------------");
            Constructor[] constructors = c.getConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }
            constructors = c.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }
            System.out.println("---------------------------");
            Constructor constructor = c.getConstructor();
            System.out.println(constructor);
            Boo boo = (Boo) constructor.newInstance();
            System.out.println(boo.getName());
            System.out.println("---------------------------");
            constructor = c.getDeclaredConstructor(String.class);
            System.out.println(constructor);
            System.out.println("---------------------------");
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
            methods = c.getDeclaredMethods();
            System.out.println("---------------------------");
            for (Method method : methods) {
                System.out.println(method);
            }
        } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
