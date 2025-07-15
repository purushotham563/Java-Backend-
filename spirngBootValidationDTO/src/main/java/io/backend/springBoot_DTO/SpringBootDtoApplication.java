package io.backend.springBoot_DTO;

import io.backend.springBoot_DTO.Model.Location;
import io.backend.springBoot_DTO.Model.LocationRepository;
import io.backend.springBoot_DTO.Model.User;
import io.backend.springBoot_DTO.Model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDtoApplication implements CommandLineRunner   {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDtoApplication.class, args);
	}
    @Autowired
	private LocationRepository locationRepository;
   @Autowired
   private UserRepository userRepository;
	@Override
	public void run(String... args) throws Exception {
		Location location=new Location();
		location.setPlace("Bengaluru");
		location.setDescription("Bengaluru" +
				" will have good weather through the year");
		location.setLatitude(97.3);
		location.setLongitude(90.2);
		locationRepository.save(location);
		User user =new User();
		user.setFirstName("Purushotham");
		user.setLastName("Reddy");
		user.setEmail("purushotham@gmail.com");
		user.setPassword("some-personal-password");
		user.setLocation(location);
		userRepository.save(user);



	}
}
