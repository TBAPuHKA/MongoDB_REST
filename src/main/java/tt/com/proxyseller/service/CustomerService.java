package tt.com.proxyseller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.com.proxyseller.model.Customer;
import tt.com.proxyseller.repository.CustomerRepository;

@Slf4j
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		super();
		this.customerRepository = repository;
	}

	public Customer updateCustomer(String id, Customer customer) {
		Customer customerDto = getCustomerById(id);

		if(customerDto!=null) {
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			try {
				customerRepository.save(customerDto);
				log.info("CustomerID: " + id + " | save to db | SUCCESS");
				return customerDto;
			} catch (Exception e) {
				log.warn("CustomerID: " + id + " | save to db | FAIL");
				return null;
			}
		} else {
			log.warn("CustomerID: " + id + " | NOT found");
			return null;
		}
	}

	public Customer addCustomer(Customer customer) {
		Pattern pattern = Pattern.compile("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.([a-z]{2}|com|org|net|edu|gov|info)$");
		Matcher matcher = pattern.matcher(customer.getEmail());
		if (!matcher.matches()) {
			log.warn("Customer | create customer | FAIL");
			return null;
		} else {
			try {
				customerRepository.save(customer);
				log.warn("Customer | save to db | SUCCESS");
				return customer;
			} catch (Exception e) {
				log.warn("Customer | save to db | FAIL");
				return null;
			}
		}
	}

	public List<Customer> getAllCustomers() {

		return new ArrayList<>(customerRepository.findAll());
	}

	public void deleteById(String id) {

		try {
			customerRepository.deleteById(id);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	public Customer getCustomerById(String id) {
		Optional<Customer> findCustomer = customerRepository.findById(id);
		return findCustomer.get();
	}

}
