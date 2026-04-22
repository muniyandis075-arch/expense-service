package uk.lset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import uk.lset.repository.ExpenseRepository;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = ExpenseRepository.class)
public class JpaConfig {

//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//               .filter(Authentication::isAuthenticated)
//             .map(Authentication::getName);
//   }
}
