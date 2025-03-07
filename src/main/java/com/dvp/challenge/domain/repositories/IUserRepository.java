package com.dvp.challenge.domain.repositories;


import com.dvp.challenge.domain.AccessTokenSerializer;
import com.dvp.challenge.domain.User;

import java.util.List;

public interface IUserRepository {
   User create(User user);

   User update(String id, User user);

   List<User> find();

   User findUserById(String id);

   AccessTokenSerializer doLogin(String username, String password);
}
