package com.inixindo.library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired 
	private UserDetailsService userDetailsService;
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http
//		.authorizeRequests()
//		.antMatchers("/","/register").permitAll()
//		.antMatchers("/todo/edit/**","/todo/delete/**").hasAuthority("ADMIN")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/login")
//		.defaultSuccessUrl("/todo/list-all")
//		.permitAll()
//		.and()
//        .logout().invalidateHttpSession(true)
//		.clearAuthentication(true)
//		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login")
//        .permitAll()
//        .and().csrf().disable();
		
		
		http
		.authorizeRequests()
		.antMatchers("/register","/save_borrower", "/").permitAll()
		.antMatchers("/book","/loans").hasAuthority("LIBRARIAN")
		.antMatchers("/user_dashboard").hasAuthority("BORROWER")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/")
//		.failureUrl("/")
		.defaultSuccessUrl("/default", true)
		.permitAll()
		.and()
        .logout().invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
        .permitAll()
        .and().csrf().disable();
	}
		
		
//        String loginPage = "/login";
//        String logoutPage = "/logout";
//        
//        http.authorizeRequests()
//        
//        // All
//        .antMatchers("/register", "/save_borrower").permitAll()
//        
//        // Borrower
//        .antMatchers("/").hasAnyAuthority("LIBRARIAN", "BORROWER")
//        .antMatchers("/book_borrower/**").hasAnyAuthority("LIBRARIAN", "BORROWER")
//        
//        // Librarian
//        .antMatchers("/book/**").hasAnyAuthority("LIBRARIAN")        
//        .antMatchers("/edit_book/**").hasAnyAuthority("LIBRARIAN")
//        .antMatchers("/delete_book/**").hasAuthority("LIBRARIAN")
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().permitAll()
//        .loginPage("/login")
//        .defaultSuccessUrl("/book")
////        .defaultSuccessUrl("/default")
//        .and()
//        .logout().permitAll()
//        .and()
//        .exceptionHandling().accessDeniedPage("/403")
//        .and().logout()
//        .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
//        .logoutSuccessUrl(loginPage).and().exceptionHandling();
//        ;
//	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/vendor/**","/fonts/**");
    }
	
}

