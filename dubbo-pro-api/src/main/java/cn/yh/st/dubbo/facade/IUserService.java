package cn.yh.st.dubbo.facade;

import cn.yh.st.dubbo.domain.User;

public interface IUserService {

	User findUserById(long id);
}
