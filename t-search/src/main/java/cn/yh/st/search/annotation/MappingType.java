package cn.yh.st.search.annotation;

public enum MappingType {

	ANALYZER(0), NOT_ANALYZER(1);

	private int code;

	private MappingType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
