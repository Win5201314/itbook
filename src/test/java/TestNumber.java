import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TestNumber {

    public static void main(String[] args) {
        String number = UUID.randomUUID().toString();
        System.out.println(number + "--->" + number.length());
        String countStr = "3,244";
        int i = Integer.parseInt(countStr.replaceAll(",", ""));
        System.out.println(i);
    }

    @Test
    public void test() {
        Assert.assertTrue(1 > 8);
    }
}
