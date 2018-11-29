package cn.yh.st.dubbo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yh.st.dubbo.dao.UserDao;
import cn.yh.st.dubbo.domain.User;
import cn.yh.st.dubbo.facade.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findUserById(long id) {
		return userDao.selectByPrimaryKey(id);
	}

}
