package cn.yh.st.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.yh.st.dao.CustomerRepository;
import cn.yh.st.domain.TUser;
import cn.yh.st.service.CustomerServiceTxInAnnotation;
import cn.yh.st.service.CustomerServiceTxInCode;

import java.util.List;

/**
 * Created by mavlarn on 2018/1/20.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerResource {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerResource.class);

	@Autowired
	private CustomerServiceTxInAnnotation customerService;
	@Autowired
	private CustomerServiceTxInCode customerServiceInCode;
	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/annotation")
	public TUser createInAnnotation(@RequestBody TUser customer) {
		LOG.info("CustomerResource create in annotation create customer:{}", customer.getUsername());
		return customerService.create(customer);
	}

	@PostMapping("/code")
	public TUser createInCode(@RequestBody TUser customer) {
		LOG.info("CustomerResource create in code create customer:{}", customer.getUsername());
		return customerServiceInCode.create(customer);
	}

	@GetMapping("")
	public List<TUser> getAll() {
		return customerRepository.findAll();
	}

}
