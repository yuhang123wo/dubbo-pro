package cn.yh.st.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CusService {

	@Autowired
	@Qualifier("userJdbcTemplate")
	private JdbcTemplate userJdbcTemplate;

	@Autowired
	@Qualifier("orderJdbcTemplate")
	private JdbcTemplate orderJdbcTemplate;

	@Transactional
	public void update() {

		userJdbcTemplate.update("insert t_user(username) values(?)", "yuhang");
		orderJdbcTemplate.update("insert t_order(amount) values(?)", "1");
		int i = 1 / 0;
	}

}
