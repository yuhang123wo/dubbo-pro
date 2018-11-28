package cn.yh.st.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.yh.st.common.entity.Entity;

@Table(name = "m_user")
public class MUser extends Entity {

	private static final long serialVersionUID = 1L;

	@Length(max = 50, message = "用户名长度不能超过50")
	@NotEmpty(message = "用户名必填")
	@Column(name = "username")
	private String username; // 用户名

	@Length(max = 15, min = 6, message = "密码长度最长为6-15")
	@NotEmpty(message = "密码必填")
	@Column(name = "password")
	private String password; // 密码

	@Column(name = "realname")
	private String realname; // 真实名

	@Column(name = "mobile")
	@NotEmpty(message = "电话必填")
	private String mobile; // 电话

	@Column(name = "email")
	private String email; // 邮箱

	@Column(name = "sex")
	private int sex; // 性别

	@Column(name = "address")
	@NotEmpty(message = "地址必填")
	@Length(max = 50, message = "地址最长为50")
	private String address; // 地址

	@Column(name = "status")
	private int status; // 状态

	@Column(name = "type")
	private int type; // 类型

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "create_time")
	private Date createTime;

	@Transient
	private long roleId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSexCN() {
		if (sex == 1) {
			return "男";
		}
		return "女";
	}

	public String getStatusCN() {
		if (status == 1) {
			return "启用";
		}
		return "禁用";
	}

}
