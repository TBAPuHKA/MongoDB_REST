package tt.com.proxyseller.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import tt.com.proxyseller.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
