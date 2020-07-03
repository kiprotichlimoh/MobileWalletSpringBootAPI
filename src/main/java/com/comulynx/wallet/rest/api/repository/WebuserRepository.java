package com.comulynx.wallet.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Webuser;

@Repository
public interface WebuserRepository extends JpaRepository<Webuser, Long>{
	
	Optional<Webuser> findByEmployeeId(String employeeId);
    //check if webuser exist with the provided details
	@Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Webuser w WHERE w.username = :userName OR w.email = :email OR w.employeeId = :employeeId OR w.customerId = :customerId")
	boolean exists(@Param("userName") String userName, @Param("email") String email, @Param("employeeId") String employeeId,
			@Param("customerId") String customerId);
	

}
