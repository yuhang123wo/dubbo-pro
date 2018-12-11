package cn.yh.st.search.user;

import cn.yh.st.search.EsManagerImpl;
import cn.yh.st.search.vo.EsDemoVo;

public class EsDemoVoServiceImpl extends EsManagerImpl<EsDemoVo> implements EsDemoVoService {

	@Override
	public String getIndexName() {
		return "index3";
	}

}
