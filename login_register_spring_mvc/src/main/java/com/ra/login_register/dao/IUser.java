package com.ra.login_register.dao;

import com.ra.login_register.model.Users;

public interface IUser {
    Boolean save(Users users);
    Boolean login(Users users);
    Users findUserbyUsername(String username);
}
