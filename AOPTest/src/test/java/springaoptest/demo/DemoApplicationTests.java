package springaoptest.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
        CurrentUserHolder.set("admin");
        productService.insert(new Product());
    }

    @Test
    void test() {
        Product product = new Product();
        product.setName("苹果");
        //productService.selectProduct(product);
    }

}
