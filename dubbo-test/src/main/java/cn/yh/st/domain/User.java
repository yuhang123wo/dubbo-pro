package cn.yh.st.domain;

import javax.persistence.Column;
import javax.persistence.Table;

import cn.yh.st.common.entity.Entity;

@Table(name = "user")
public class User extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
