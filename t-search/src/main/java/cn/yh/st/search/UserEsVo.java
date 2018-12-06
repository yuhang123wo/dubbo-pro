package cn.yh.st.search;

import java.util.Date;

public class UserEsVo extends EsEntity {

	private String userName;
	private String address;
	private int age;
	private Date createTime;

	public UserEsVo() {
	}

	public UserEsVo(long userId, String userName, String address, int age, Date createTime) {
		this.userName = userName;
		this.address = address;
		this.age = age;
		this.createTime = createTime;
		this.id = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
