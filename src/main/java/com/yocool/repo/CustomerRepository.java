package com.yocool.repo;

import com.yocool.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Author tangzq.
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

     Customer findByFirstName(String firstName);
     List<Customer> findByLastName(String lastName);

}
