package ru.learnup.nexigntask.callscdrplus.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Разграничение прав, выделяем два типа пользователей - abonent и manager
     *
     * @return данные о правах пользователей, хранящиеся внутри программы
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails abonent = User.withDefaultPasswordEncoder()
                .username("abonent")
                .password("abonent")
                .roles("ABONENT")
                .build();
        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("manager")
                .password("manager")
                .roles("MANAGER")
                .build();
        return new InMemoryUserDetailsManager(abonent, manager);
    }

    /**
     * Определяем какие url доступны ролям abonent и manager
     *
     * @param http httpSecurity
     * @return данные о "области видимости" http-запросов по ролям
     * @throws Exception exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/abonent/**").hasRole("ABONENT")
                .requestMatchers("/manager/**").hasRole("MANAGER")
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }
}
