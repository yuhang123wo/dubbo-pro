package cn.yh.st.search.annotation.field;

public enum NumberEnum {

	LONG("long"), INTEGER("integer"), SCALEDFLOAT("scaled_float");

	private String value;

	private NumberEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
