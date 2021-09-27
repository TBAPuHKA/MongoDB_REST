package tt.com.proxyseller.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tt.com.proxyseller.model.Customer;
import tt.com.proxyseller.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		super();
		this.repository = repository;
	}

	public Customer updateCustomer(String id, Customer customer) {
		Optional<Customer> findPersonQuery = repository.findById(id);
		Customer customerValues = findPersonQuery.get();
		customerValues.setId(customer.getId());
		customerValues.setName(customer.getName());
		customerValues.setEmail(customer.getEmail());
		return repository.save(customerValues);
	}

	public Customer addCustomer(Customer customer) {
		Pattern pattern = Pattern.compile("^[\\w\\.-]+@[a-zA-Z0-9-]+\\.([a-z]{2}|com|org|net|edu|gov|info)$");
		Matcher matcher = pattern.matcher(customer.getEmail());
		if (!matcher.matches()) {
			return null;
		} else {
			return repository.insert(customer);
		}
	}

	public List<Customer> getAllCustomers() {
		return repository.findAll().stream().collect(Collectors.toList());
	}

	public void deleteById(String id) {
		try {
			repository.deleteById(id);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}

	}

	public Optional<Customer> getCustomerById(String id) {
		return repository.findById(id);
	}

}
