package com.tenders.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.tenders.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public UserDetailsService userDetailService() {

//		authentication

//		UserDetails admin = User.withUsername("Meghana")
//				.password(encoder.encode("meghana"))
//				.roles("ADMIN").build();
//
//		UserDetails user = User.withUsername("puppy")
//				.password(encoder.encode("puppy"))
//				.roles("USER").build();
//
//		return new InMemoryUserDetailsManager(admin, user);

		return new UserInfoUserDetailsService();
	}

	// Authorization
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//		return http.csrf().disable().authorizeHttpRequests()
//				.requestMatchers("/tenders/authenticate","/tenders/user-details","/tenders/logout","/tenders/save-userdept"
//						,"/tenders/get-userdept","/tenders/editbom/{id}","/tenders/bom","/tenders/update/{id}"
//						,"/transaction/save-txn","/list/item-list/{listName}")
//				.permitAll().and()
//				.authorizeHttpRequests().requestMatchers("/tenders/save"
//						,"/tenders/notes/{userName}/{tendernum}","/tenders/bomDescTenNum/{id}","/tenders/get","/tenders/note/{id}","/tenders/states","/tenders/verticals","/tenders/assignedTo","/tenders/tender-status")
//				.authenticated().and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.authenticationProvider(authenticationProvider())
//				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
//
////		return null;
//
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/tenders/authenticate", "/users/user-details", "/tenders/logout",
						"/userDept/save-userdept", "/userDept/get-userdept", "/bom/editbom/{id}", "/bom/getbom",
						"/tenders/update/{id}", "/bom/deletebom/{id}", "/employees/save-emp", "/employees/get-emp",
						"/employees/update/{id}", "/list/system-list", "/list/item-list", "/tenders/live-tenders",
						"/list/save-list", "/list/update/{id}", "/tenders/get/{verticals}", "/tenders/state/{states}",
						"/list/save-list/{name}")
				.permitAll().and().authorizeHttpRequests()
				.requestMatchers("/tenders/save", "/notes/notes/{userName}/{tendernum}", "/tenders/bomDescTenNum/{id}",
						"/tenders/get", "/notes/note/{id}", "/tenders/states", "/tenders/verticals",
						"/tenders/assignedTo", "/tenders/tender-status")
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

//		return null;

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();

	}

	protected void configure1(HttpSecurity http) throws Exception {

		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
				// other configurations
				.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

}
