package cn.yh.st.search.vo;

import java.util.Date;

import cn.yh.st.search.annotation.field.EsDate;
import cn.yh.st.search.annotation.field.EsNumber;
import cn.yh.st.search.annotation.field.EsText;
import cn.yh.st.search.annotation.field.NumberEnum;

public class NestVo {

	@EsText
	private String voName;

	@EsNumber(type = NumberEnum.LONG)
	private long voId;

	@EsText
	private String voAddress;

	@EsDate
	private Date voTime;

	public String getVoName() {
		return voName;
	}

	public void setVoName(String voName) {
		this.voName = voName;
	}

	public long getVoId() {
		return voId;
	}

	public void setVoId(long voId) {
		this.voId = voId;
	}

	public String getVoAddress() {
		return voAddress;
	}

	public void setVoAddress(String voAddress) {
		this.voAddress = voAddress;
	}

	public Date getVoTime() {
		return voTime;
	}

	public void setVoTime(Date voTime) {
		this.voTime = voTime;
	}

}
