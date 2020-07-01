package com.comulynx.wallet.rest.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
=======
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
import org.springframework.stereotype.Repository;

import com.comulynx.wallet.rest.api.model.Webuser;

@Repository
public interface WebuserRepository extends JpaRepository<Webuser, Long>{
	
	Optional<Webuser> findByEmployeeId(String employeeId);
<<<<<<< HEAD
    //check if webuser exist with the provided details
	@Query("SELECT CASE WHEN COUNT(w) > 0 THEN true ELSE false END FROM Webuser w WHERE w.username = :userName OR w.email = :email OR w.employeeId = :employeeId OR w.customerId = :customerId")
	boolean exists(@Param("userName") String userName, @Param("email") String email, @Param("employeeId") String employeeId,
			@Param("customerId") String customerId);
=======
	
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082

}
