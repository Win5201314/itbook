import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class TestNumber {

    public static void main(String[] args) {
        String number = UUID.randomUUID().toString();
        System.out.println(number + "--->" + number.length());
    }

    @Test
    public void test() {
        Assert.assertTrue(1 > 8);
    }
}
