package com.comulynx.wallet.rest.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

<<<<<<< HEAD
import com.comulynx.wallet.rest.api.exception.ResouceFoundExeption;
=======
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import com.comulynx.wallet.rest.api.model.Webuser;
import com.comulynx.wallet.rest.api.repository.WebuserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/webusers")
public class WebuserController {

	@Autowired
	private WebuserRepository webuserRepository;

	/**
	 * TODO : Fix Webuser Login functionality
	 * 
	 * Login
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
<<<<<<< HEAD
	public ResponseEntity<?> webuserLogin(@RequestBody HashMap<String, String> webUser) {
		try {

=======
	public ResponseEntity<?> webuserLogin(@RequestBody String request) {
		try {


>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
			// TODO : Add Webuser login logic here. Login using employeeId and
			// password
			//NB: We are using plain text password for testing Webuser login
			//If username doesn't exists throw an error "User does not exist"
			//If password do not match throw an error "Invalid credentials"
			
<<<<<<< HEAD
			String employeeId = webUser.get("customerId");
			String pin = webUser.get("pin");
			Webuser webusers = webuserRepository.findByEmployeeId(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException(
							"User does not exist"));
			String storeddbPin = webusers.getPassword();
			if (pin.equals(storeddbPin)) {
				return ResponseEntity.ok().body(webusers);
			}
			else{
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Invalid credentials");
			}
=======
			

			return ResponseEntity.status(200).body(HttpStatus.OK);

>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/")
	public List<Webuser> getAllWebusers() {
		return webuserRepository.findAll();
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Webuser> getWebuserByEmployeeId(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(webuser);
	}

	@PostMapping("/")
	public ResponseEntity<?> createWebuser(@Valid @RequestBody Webuser webuser) {
		try {
			// TODO : Add logic to check if Webuser with provided username, or
			// email, or employeeId, or customerId exists.
			// If exists, throw a Webuser with [?] exists Exception.
<<<<<<< HEAD
			boolean webUserExist = webuserRepository.exists(webuser.getUsername(), webuser.getEmail(), webuser.getEmployeeId(),  webuser.getCustomerId());
			if (webUserExist){
				throw new ResouceFoundExeption("Web user Already exist.");
			}
			else{
			return ResponseEntity.ok().body(webuserRepository.save(webuser));
			}
=======

			return ResponseEntity.ok().body(webuserRepository.save(webuser));
>>>>>>> b4cb3987d1307c027db60af3d7222695825d7082
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Webuser> updateWebuser(@PathVariable(value = "employeeId") String employeeId,
			@Valid @RequestBody Webuser webuserDetails) throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuser.setEmail(webuserDetails.getEmail());
		webuser.setLastName(webuserDetails.getLastName());
		webuser.setFirstName(webuserDetails.getFirstName());
		final Webuser updatedWebuser = webuserRepository.save(webuser);
		return ResponseEntity.ok(updatedWebuser);
	}

	@DeleteMapping("/{employeeId}")
	public Map<String, Boolean> deleteWebuser(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuserRepository.delete(webuser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
