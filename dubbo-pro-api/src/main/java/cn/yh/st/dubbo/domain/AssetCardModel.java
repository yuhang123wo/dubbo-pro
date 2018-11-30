package cn.yh.st.dubbo.domain;

import java.util.Date;

public class AssetCardModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int transactionId;
	private String domain;
	private String globalTxId;
	private String branchQualifier;
	private String content;
	private int status;
	private int transactionType;
	private int retriedCount;
	private Date createTime;
	private Date lastUpdateTime;
	private int version;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getGlobalTxId() {
		return globalTxId;
	}

	public void setGlobalTxId(String globalTxId) {
		this.globalTxId = globalTxId;
	}

	public String getBranchQualifier() {
		return branchQualifier;
	}

	public void setBranchQualifier(String branchQualifier) {
		this.branchQualifier = branchQualifier;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}

	public int getRetriedCount() {
		return retriedCount;
	}

	public void setRetriedCount(int retriedCount) {
		this.retriedCount = retriedCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
