package springaoptest.demo;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    private AuthService authService;

    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly() {

    }

    @Before("adminOnly()")
    public void check() {
        authService.checkAccess();
    }
    ///////////////////

    @Pointcut("within(springaoptest.demo.ProductService)")
    public void matchType() {

    }

    @Before("matchType()")
    public void mt() {
        System.out.println("within注解使用");
    }
}
