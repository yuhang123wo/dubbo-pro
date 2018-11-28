package cn.yh.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yh.st.dao.one.MuserDao;
import cn.yh.st.dao.two.UserDao;
import cn.yh.st.domain.MUser;
import cn.yh.st.domain.User;
import cn.yh.st.service.MuserService;

@Service
public class MuserServiceImpl implements MuserService {
	@Autowired
	private MuserDao muserDao;
	@Autowired
	private UserDao userDao;

	@Override
	public int addMuser(MUser muser) {
		User user = new User();
		user.setName("gg");
		userDao.insert(user);
		int i=1/0;
		return muserDao.insertSelective(muser);
	}
}
