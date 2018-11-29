package cn.yh.st.dubbo.domain;

import cn.yh.st.common.entity.Entity;

public class User extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
