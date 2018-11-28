package cn.yh.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yh.st.dao.two.UserDao;
import cn.yh.st.domain.User;
import cn.yh.st.service.UserService;

//@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(long id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int addUser(User user) {
		return userDao.insert(user);
	}

}
