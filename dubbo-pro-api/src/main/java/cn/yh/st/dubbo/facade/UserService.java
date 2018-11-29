package cn.yh.st.dubbo.facade;

import cn.yh.st.dubbo.domain.User;

public interface UserService {

	User findUserById(long id);
}
