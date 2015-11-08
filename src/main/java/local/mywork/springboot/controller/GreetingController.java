package local.mywork.springboot.controller;

import local.mywork.springboot.form.CustomerForm;
import local.mywork.springboot.model.Customer;
import local.mywork.springboot.model.Greeting;
import local.mywork.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
public class GreetingController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@Autowired
	private CustomerService service;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping("/customer")
	public Customer customer(@RequestParam(value = "name", defaultValue = "Bauer") String name) {
		return service.getFirstCustomers(name);
	}

	@RequestMapping(value = "/customer2", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Customer customer(@RequestBody @Valid CustomerForm form, BindingResult result) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			errors.forEach(error -> log.info("{}: {}", error.getObjectName(), error.getDefaultMessage()));
			return new Customer("error", "error");
		}

		return service.getFirstCustomers(form.getLastName());
	}

	public List<Customer> all() {
		return service.getAllCustomers();
	}
}
