package cn.yh.st.search;

import java.util.ArrayList;
import java.util.List;

public class QueryInfo {

	/**
	 * 排序字段
	 */
	private List<String> sortField = new ArrayList<String>();

	/**
	 * 排序类型
	 */
	private List<String> sortOrder = new ArrayList<String>();

	/**
	 * 相等条件
	 */
	private List<QueryAttribute> equals = new ArrayList<QueryAttribute>();

	/**
	 * 模糊匹配条件
	 */
	private List<QueryAttribute> like = new ArrayList<QueryAttribute>();

	/**
	 * 大于等于条件
	 */
	private List<QueryAttribute> ge = new ArrayList<QueryAttribute>();

	/**
	 * 小于等于条件
	 */
	private List<QueryAttribute> le = new ArrayList<QueryAttribute>();

	public List<String> getSortField() {
		return sortField;
	}

	public void setSortField(List<String> sortField) {
		this.sortField = sortField;
	}

	public List<String> getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(List<String> sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<QueryAttribute> getEquals() {
		return equals;
	}

	public void setEquals(List<QueryAttribute> equals) {
		this.equals = equals;
	}

	public List<QueryAttribute> getLike() {
		return like;
	}

	public void setLike(List<QueryAttribute> like) {
		this.like = like;
	}

	public List<QueryAttribute> getGe() {
		return ge;
	}

	public void setGe(List<QueryAttribute> ge) {
		this.ge = ge;
	}

	public List<QueryAttribute> getLe() {
		return le;
	}

	public void setLe(List<QueryAttribute> le) {
		this.le = le;
	}

}
