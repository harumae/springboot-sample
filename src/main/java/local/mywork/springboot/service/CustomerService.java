package local.mywork.springboot.service;

import local.mywork.springboot.model.Customer;
import local.mywork.springboot.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer getFirstCustomers(String name) {
        return repository.findByLastName(name).get(0);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}
