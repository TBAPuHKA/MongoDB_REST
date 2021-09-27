package tt.com.proxyseller.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import tt.com.proxyseller.model.Customer;
import tt.com.proxyseller.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer addCustomer(@RequestBody Customer customer) {
		return service.addCustomer(customer);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Customer updateCustomer(@RequestParam String id, @RequestBody Customer customer) {
		return service.updateCustomer(id, customer);
	}

	@RequestMapping(value = "/getcustomers", method = RequestMethod.GET)
	public List<Customer> getAllCustomers() {
		return service.getAllCustomers();
	}

	@RequestMapping(value = "/getcustomer", method = RequestMethod.GET)
	public Optional<Customer> getCustomerById(@RequestParam String id) {
		return service.getCustomerById(id);
	}

	@RequestMapping(value = "/deletecustomer", method = RequestMethod.DELETE)
	public void deleteCustomerById(@RequestParam String id) {
		service.deleteById(id);
	}

}
