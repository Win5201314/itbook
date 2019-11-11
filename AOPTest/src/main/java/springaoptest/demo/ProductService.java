package springaoptest.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private AuthService authService;

    @AdminOnly
    public void insert(Product product) {
        //authService.checkAccess();
        System.out.println("-----------------插入");
    }

    public void delete() {
        //authService.checkAccess();
        System.out.println("-------------------删除");
    }


    public void selectProduct(long productId) {

    }

}
