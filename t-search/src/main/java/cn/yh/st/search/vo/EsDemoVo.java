package cn.yh.st.search.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.yh.st.search.EsEntity;
import cn.yh.st.search.annotation.field.EsDate;
import cn.yh.st.search.annotation.field.EsNested;
import cn.yh.st.search.annotation.field.EsNumber;
import cn.yh.st.search.annotation.field.EsText;
import cn.yh.st.search.annotation.field.NumberEnum;

public class EsDemoVo extends EsEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EsText
	private String orderNo;
	
	@EsText
	private String userName;
	
	@EsNumber(type = NumberEnum.INTEGER)
	private int sex;
	
	@EsText
	private String address;
	
	@EsDate
	private Date createTime;
	
	@EsNumber(type = NumberEnum.LONG)
	private long userId;
	
	@EsNumber(type = NumberEnum.SCALEDFLOAT)
	private double price;
	
	@EsNumber(type = NumberEnum.SCALEDFLOAT)
	private BigDecimal amount;
	
	@EsNested
	private List<NestVo> vo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<NestVo> getVo() {
		return vo;
	}

	public void setVo(List<NestVo> vo) {
		this.vo = vo;
	}

}
