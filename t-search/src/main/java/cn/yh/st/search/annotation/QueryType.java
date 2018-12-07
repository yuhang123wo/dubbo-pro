package cn.yh.st.search.annotation;

public enum QueryType {

	REGEXPEQUERY {
		public String getName() {
			return "字符串匹配";
		}
	},
	PHRASEQUERY {
		public String getName() {
			return "字符串匹配";
		}
	},
	PHRASEQUERY_NEST {
		public String getName() {
			return "嵌套字符串匹配";
		}
	},
	TERMQUERY {
		public String getName() {
			return "完全匹配";
		}
	},
	NOTNULLQUERY {
		public String getName() {
			return "非空匹配";
		}
	},
	EQNULLQUERY {
		public String getName() {
			return "空匹配";
		}
	},
	IDQUERY {
		public String getName() {
			return "主键匹配";
		}
	},
	ANDOPERQUERY {
		public String getName() {
			return "与预算";
		}
	},
	NEQANDOPERQUERY {
		public String getName() {
			return "非与预算";
		}
	},
	TERMQUERYBATCH {
		public String getName() {
			return "完全匹配";
		}
	},
	TERMQUERY_NEST {
		public String getName() {
			return "完全匹配";
		}
	},
	RECEIPT_QUERY {
		public String getName() {
			return "订单是否已结算";
		}
	},
	TIMEQUERY { // QUERY_NAME默认为开始时间
		public String getName() {
			return "时间查询(开始结束)";
		}
	},
	BACTHQUERY_NEST { // 多个参数用,隔开
		public String getName() {
			return "嵌套字符串批量查询";
		}
	};
	public abstract String getName();

}
