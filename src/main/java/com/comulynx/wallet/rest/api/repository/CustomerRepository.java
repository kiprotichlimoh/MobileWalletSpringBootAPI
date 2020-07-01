package com.comulynx.wallet.rest.api.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerId(String customerId);

<<<<<<< HEAD
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.customerId = :customerId OR c.email = :email")
	boolean exists(@Param("customerId") String customerId, @Param("email") String email);

	// TODO : Implement the query and function below to delete a customer using Customer Id
	@Modifying
	@Query("delete from Customer c where c.customerId=:customer_id")
	  int deleteCustomerByCustomerId(@Param("customer_id") String customer_id);

	// TODO : Implement the query and function below to update customer firstName using Customer Id
	@Modifying
	@Query("update Customer c set c.firstName = :firstName WHERE c.customerId=:customer_id")
	int updateCustomerByCustomerId(@Param("firstName") String firstName, @Param("customer_id") String customer_id);
	
	// TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	 @Query("SELECT c FROM Customer c WHERE c.email LIKE '%gmail%'")
	 List<Customer> findAllCustomersWhoseEmailContainsGmail();
     //get all customers and acoount details
	 @Query(value = "SELECT c.CUSTOMER_ID ,c.EMAIL,c.FIRST_NAME,c.LAST_NAME, a.ACCOUNT_NO, a.BALANCE   FROM CUSTOMERS c INNER JOIN ACCOUNTS a ON c.CUSTOMER_ID = a.CUSTOMER_ID", nativeQuery = true)
	 	List<Map<String, String>> getFromCustomerAndAccountDetails();

	// get customer and acoount detail 
	@Query(value = "SELECT c.CUSTOMER_ID ,c.EMAIL,c.FIRST_NAME,c.LAST_NAME, a.ACCOUNT_NO, a.BALANCE   FROM CUSTOMERS c INNER JOIN ACCOUNTS a ON c.CUSTOMER_ID = a.CUSTOMER_ID WHERE c.CUSTOMER_ID = :customer_id", nativeQuery = true)
	List<Map<String, String>> getFromCustomerAndAccountDetailsPerCustomer(@Param("customer_id") String customer_id);

=======
	// TODO : Implement the query and function below to delete a customer using Customer Id
	// @Query("?")
	// int deleteCustomerByCustomerId(String customer_id);

	// TODO : Implement the query and function below to update customer firstName using Customer Id
	// @Query("?")
	// int updateCustomerByCustomerId(String firstName, String customer_id);
	
	// TODO : Implement the query and function below and to return all customers whose Email contains  'gmail'
	// @Query("?")
	// List<Customer> findAllCustomersWhoseEmailContainsGmail();
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
}
