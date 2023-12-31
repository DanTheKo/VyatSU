//package com.prototype.configuration;
//import com.prototype.services.JdbcUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import javax.sql.DataSource;
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//    private DataSource dataSource;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        return new JdbcUserDetailsService(dataSource);
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin(withDefaults());
//        return http.build();
//    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (2)
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser(users.username("user1").password("pass1").roles("USER", "ADMIN"))
//                .withUser(users.username("user2").password("pass2").roles("USER"));
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().permitAll()
//                .antMatchers("/secured/**").hasAnyRole("ADMIN")
//                .and()
//                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/authenticateTheUser")
//                .permitAll();
//    }
//}
