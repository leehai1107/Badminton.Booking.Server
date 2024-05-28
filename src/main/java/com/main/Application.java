package com.main;

import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.RoleRepo;
import com.main.badminton.booking.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application  implements CommandLineRunner {
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	public void run(String... args){
		Role role = roleRepo.findById(1).orElseThrow();
		User adminAccount = userRepository.findByRole(role);

		if(null == adminAccount){
			User user = new User();
			user.setCreateBy(0);
			user.setUsername("admin");
			user.setEmail("admin@gmail.com");
			user.setRole(role);
			user.setPassword(new BCryptPasswordEncoder().encode("123"));
			userRepository.save(user);
		}
	}
}
