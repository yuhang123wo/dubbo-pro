package cn.yh.st.search.user;

import cn.yh.st.search.EsManagerImpl;
import cn.yh.st.search.UserEsVo;

public class UserEsVoServiceImpl extends EsManagerImpl<UserEsVo> implements UserEsVoService {

	@Override
	public String getIndexName() {
		return "users";
	}
}
