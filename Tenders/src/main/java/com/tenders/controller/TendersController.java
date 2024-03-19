package com.tenders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenders.dto.ApiResponse;
import com.tenders.dto.AuthRequest;
import com.tenders.dto.TendersDto;
import com.tenders.security.JwtService;
import com.tenders.service.TendersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/tenders")

public class TendersController {

	@Autowired
	TendersService tenderService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtservice;

	@PostMapping("/save")
	public ApiResponse saveTenders(@RequestBody TendersDto tendersDto) {
		return tenderService.saveTenders(tendersDto);

	}

	@GetMapping("/get")
//	@PreAuthorize("hasRole('ROLE_USER')")
	public ApiResponse getTenders() {
		return tenderService.getTenders();

	}

	@PatchMapping("/update/{id}")
	public ApiResponse updateTenders(@PathVariable("id") Long id, @RequestBody TendersDto tendersDto) {
		return tenderService.updateTenders(id, tendersDto);

	}

	@GetMapping("/bomDescTenNum/{id}")
	public ApiResponse getBOMDescTenNum(@PathVariable("id") Long id) {
		return tenderService.getBOMDescTenNum(id);

	}

	@PostMapping("/authenticate")
	public String authenticateAndetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();

			return jwtservice.generateToken(userDetails);
		} else
			throw new UsernameNotFoundException("Invalid User");
	}

	@GetMapping("/states")
	public ApiResponse getStates() {
		return tenderService.getStates();

	}

	@GetMapping("/verticals")
	public ApiResponse getVerticals() {
		return tenderService.getVerticals();

	}

	@GetMapping("/assignedTo")
	public ApiResponse getAssignedTo() {
		return tenderService.getAssignedTo();

	}

	@GetMapping("/tender-status")
	public ApiResponse getTenderStatus() {
		return tenderService.getTenderStatus();

	}

	@GetMapping("/logout")
	public String getLogoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "logout successful";

	}

	@GetMapping("/live-tenders")
	public ApiResponse fetchDataExcludingStaticValues() {
		return tenderService.fetchDataExcludingStaticValues();

	}

	// API's for DashBoard
	@GetMapping("/get/{verticals}")
	public ApiResponse fetchByVerticalsData(@PathVariable("verticals") String verticals) {
		return tenderService.fetchVerticals(verticals);

	}

	@GetMapping("/state/{states}")
	public ApiResponse fetchByStates(@PathVariable("states") String states) {
		return tenderService.fetchByStates(states);

	}

	@GetMapping("/fetch/{states}/{verticals}")
	public ApiResponse fetchByStatesAndVerticals(@PathVariable("states") String states,
			@PathVariable("verticals") String verticals) {
		return tenderService.fetchByVerticalsAndStates(states, verticals);

	}

	@GetMapping("/get-tenders/{verticals}")
	public ApiResponse fetchTendersByVerticals(@PathVariable("verticals") String verticals) {
		return tenderService.getTendersByVerticals(verticals);

	}

	@GetMapping("/get-tenderss/{states}")
	public ApiResponse fetchTendersByStates(@PathVariable("states") String states) {
		return tenderService.getTendersByStates(states);

	}

	@GetMapping("/get-tendersss/{states}/{verticals}")
	public ApiResponse fetchTendersByStates(@PathVariable("states") String states,
			@PathVariable("verticals") String verticals) {
		return tenderService.getTendersByStatesAndVerticals(states, verticals);

	}
}
