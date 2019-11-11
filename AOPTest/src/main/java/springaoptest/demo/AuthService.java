package springaoptest.demo;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public void checkAccess() {
        String user = CurrentUserHolder.get();
        if (!"admin".equals(user)) {
            throw new RuntimeException("操作不允许!");
        }
    }
}
