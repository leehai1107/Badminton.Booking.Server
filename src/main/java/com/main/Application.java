package com.main;

import com.main.badminton.booking.entity.Role;
import com.main.badminton.booking.entity.User;
import com.main.badminton.booking.repository.RoleRepo;
import com.main.badminton.booking.repository.UserRepo;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class Application  implements CommandLineRunner {
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.load();

		SpringApplication.run(Application.class, args);
	}
	public void run(String... args){
		if(roleRepo.count() == 0){
			List<Role> roles = new ArrayList<>();
			Role adminRole = Role.builder()
					.id(1)
					.name("ROLE_ADMIN")
					.build();
			Role userRole = Role.builder()
					.id(2)
					.name("ROLE_USER")
					.build();
			Role staffRole = Role.builder()
					.id(3)
					.name("ROLE_STAFF")
					.build();
			roles.add(adminRole);
			roles.add(userRole);
			roles.add(staffRole);
			roleRepo.saveAll(roles);
		}

		Role role = roleRepo.findById(1).orElseThrow();
		User adminAccount = userRepository.findByRole(role);

		if(null == adminAccount ){
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
