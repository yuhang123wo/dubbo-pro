package cn.yh.st.service;

import cn.yh.st.domain.User;

public interface UserService {

	User getUserById(long id);

	int addUser(User user);
}
