package com.zsl.xiangqing.exception.user;

/**
 * 用户名不在指定范围内
 */
public class UserUsernameNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserUsernameNotMatchException() {
        super("user.username.not.match", null);
    }
}
