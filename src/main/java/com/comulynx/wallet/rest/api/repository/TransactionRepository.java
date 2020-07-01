package com.comulynx.wallet.rest.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;
import com.comulynx.wallet.rest.api.model.Transaction;
=======

import com.comulynx.wallet.rest.api.model.Transaction;

>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Optional<List<Transaction>> findTransactionsByCustomerId(String customerId);

	Optional<List<Transaction>> findTransactionsByTransactionId(String transactionId);

	Optional<List<Transaction>> findTransactionsByCustomerIdOrTransactionId(String transactionId, String customerId);
<<<<<<< HEAD
	//get transactions by Transaction ID or Account No
	Optional<List<Transaction>> findTransactionsByCustomerIdOrAccountNo(String transactionId, String accountNo);
	
=======

>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
	// TODO : Change below Query to return the last 5 transactions
	// TODO : Change below Query to use Named Parameters instead of indexed
	// parameters
	// TODO : Change below function to return Optional<List<Transaction>>
<<<<<<< HEAD
	/**
	 * Should return last 5 transactions from the database
	 * Achived it by enabling nativeQuery and changing the query from Jpa to native sql statement with limit of 5 and order by id in descending order
	 */

	@Query(value = "SELECT * FROM TRANSACTIONS WHERE CUSTOMER_ID = :customer_id AND ACCOUNT_NO = :account_no ORDER BY id DESC LIMIT 5", nativeQuery = true)
	Optional<List<Transaction>> getMiniStatementUsingCustomerIdAndAccountNo(@Param("customer_id") String customerid, @Param("account_no") String account);
=======
	@Query("SELECT t FROM Transaction t WHERE t.customerId =?1 AND  t.accountNo =?2")
	List<Transaction> getMiniStatementUsingCustomerIdAndAccountNo(String customer_id, String account_no);
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082

}
