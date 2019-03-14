package com.example.AES.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	public EntrenadorRepositoryAuthProvider entrenadorauthenticationProvider;
	
	public DeportistaRepositoryAuthProvider deportistaauthenticationProvider;
	@Override
	 protected void configure(HttpSecurity http) throws Exception {

	 //PAGINAS PUBLICAS
	 http.authorizeRequests().antMatchers("/").permitAll();
	 http.authorizeRequests().antMatchers("/login").permitAll();
	 http.authorizeRequests().antMatchers("/loginerror").permitAll();
	 http.authorizeRequests().antMatchers("/quienessomos").permitAll();
	 http.authorizeRequests().antMatchers("/registroGeneral").permitAll();
	 http.authorizeRequests().antMatchers("/registroEntrenador").permitAll();
	 http.authorizeRequests().antMatchers("/registroDeportista").permitAll();
	 //PAGINAS PRIVADAS
	 http.authorizeRequests().antMatchers("/entrenador").hasAnyRole("ENTRENADOR");
	 http.authorizeRequests().antMatchers("/deportista").hasAnyRole("DEPORTISTA");
	 // Login form
	 http.formLogin().loginPage("/login");
	 http.formLogin().usernameParameter("email");
	 http.formLogin().passwordParameter("pass");
	 http.formLogin().defaultSuccessUrl("/");
	 http.formLogin().failureUrl("/loginerror");
	 // Logout
	 //http.logout().logoutUrl("/logout");
	 http.logout().logoutSuccessUrl("/");

	 // Disable CSRF at the moment
	 //http.csrf().disable();
	}
	
	 @Override
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 // User
		 auth.inMemoryAuthentication().withUser("Deportista").password("pass").roles("DEPORTISTA");
		 // Trainer
		 auth.inMemoryAuthentication().withUser("Entrenador").password("pass").roles("ENTRENADOR");
		 // Admin 
		// auth.inMemoryAuthentication().withUser("Admin").password("adminpass").roles("CLIENT", "TRAINER", "ADMIN");
		 
		 
		 //Database authentication provider 
		 auth.authenticationProvider(entrenadorauthenticationProvider);
		 auth.authenticationProvider(deportistaauthenticationProvider);
	}
}