package com.comulynx.wallet.rest.api.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comulynx.wallet.rest.api.exception.ResouceFoundExeption;
import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import com.comulynx.wallet.rest.api.model.Account;
import com.comulynx.wallet.rest.api.model.Customer;
import com.comulynx.wallet.rest.api.repository.AccountRepository;
import com.comulynx.wallet.rest.api.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;

	/**
	 * 
	 * Login
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> customerLogin(@RequestBody String request) {
		try {

			return ResponseEntity.status(200).body(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	/*
	gets all customer details with customer account details by combining account table details and customer accounts details
	*/

	@GetMapping("getCustomerAndAccountDetails")
	public List<?> getCustomerAndAccountDetails() {
		return customerRepository.getFromCustomerAndAccountDetails();
	}
	@GetMapping("getCustomerAndAccountDetails/{customerId}")
	public List<?> getCustomerAndAccountDetailsBycustId(@PathVariable(value = "customerId") String customerId) {
		return customerRepository.getFromCustomerAndAccountDetailsPerCustomer(customerId);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerByCustomerId(@PathVariable(value = "customerId") String customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
		try {
			// TODO : Add logic to Hash Customer PIN here
			// TODO : Add logic to check if Customer with provided username, or
			// customerId exists. If exists, throw a Customer with [?] exists
			// Exception.

			//check if customer exist by customerId or Email 
			String customerId = customer.getCustomerId();
			String email = customer.getEmail();
			boolean customerExist = customerRepository.exists(customerId, email);
			if (customerExist){
				throw new ResouceFoundExeption("Customer Already exist.");
			}
			else{
				String accountNo = generateAccountNo(customer.getCustomerId());
				//hash pin function
				String pin = HashCustomerPin(customer.getPin(), customer.getEmail());
				//update pin from plaintext to hashed pin
				customer.setPin(pin);
				Account account = new Account();
				account.setCustomerId(customer.getCustomerId());
				account.setAccountNo(accountNo);
				account.setBalance(0.0);
				accountRepository.save(account);
				return ResponseEntity.ok().body(customerRepository.save(customer));

			}

		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") String customerId,
			@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customer.setEmail(customerDetails.getEmail());
		customer.setLastName(customerDetails.getLastName());
		customer.setFirstName(customerDetails.getFirstName());
		final Customer updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{customerId}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "customerId") String customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));

		customerRepository.delete(customer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * generate a random but unique Account No (NB: Account No should be unique
	 * in your accounts table)
	 * 
	 */
	private String generateAccountNo(String customerId) {
		// TODO : Add logic here - generate a random but unique Account No (NB:
		// Account No should be unique in the accounts table)
		//woking with the sample account ACT1331 and cust id CUST1331 then account number is derived from cust id
		String accounNumber = "ACT"+ customerId.substring(customerId.length() -4);
		return accounNumber;
	}

	/**
	 * Hash customer pin 
	 * Uses sha512 takes pin and concatinates with as username (email) then hashes it as hashed pin
	 */
	private String HashCustomerPin(String pin, String email) {
		String hash = Hashing.sha512().hashString(pin + email, StandardCharsets.UTF_8).toString();
		return hash;
	}
}
