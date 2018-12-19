package cn.yh.st;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yh.st.service.CusService;

@RestController
public class WebController {

	@Autowired
	private CusService cusService;

	@RequestMapping("save")
	public void saveData() {
		cusService.update();
	}
}
