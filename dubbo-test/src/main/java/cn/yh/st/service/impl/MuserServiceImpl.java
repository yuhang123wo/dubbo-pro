package cn.yh.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.yh.st.dao.one.MuserDao;
import cn.yh.st.domain.MUser;
import cn.yh.st.service.MuserService;

@Service
public class MuserServiceImpl implements MuserService {
	@Autowired
	private MuserDao muserDao;

	@Override
	public int addMuser(MUser muser) {
		return muserDao.insertSelective(muser);
	}

}
