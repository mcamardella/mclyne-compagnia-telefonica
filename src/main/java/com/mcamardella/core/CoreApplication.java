package com.mcamardella.core;

import com.mcamardella.core.entity.ClientEntity;
import com.mcamardella.core.entity.PhoneProfileEntity;
import com.mcamardella.core.entity.TrackingEntity;
import com.mcamardella.core.enumeration.GenderEnum;
import com.mcamardella.core.enumeration.StatusPhoneLineEnum;
import com.mcamardella.core.enumeration.TypologyLineEnum;
import com.mcamardella.core.repository.ClientRepository;
import com.mcamardella.core.repository.PhoneProfileRepository;
import com.mcamardella.core.repository.TrackingRepository;
import com.mcamardella.core.utility.Utility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ClientRepository clientRepository, PhoneProfileRepository phoneProfileRepository, TrackingRepository trackingRepository) {

		return args -> {
			PhoneProfileEntity phoneProfile001 = new PhoneProfileEntity(
					"+39",
					"3981819024",
					TypologyLineEnum.MOBILE_LINE,
					StatusPhoneLineEnum.ACTIVATION,
					LocalDateTime.now(),
					LocalDateTime.now()
			);
			phoneProfileRepository.save(phoneProfile001);

			ClientEntity clientEntity001 =  new ClientEntity(
					"Steve",
					"Jobs",
					LocalDate.of(1955, 5, 24),
					GenderEnum.MALE,
					"STVJBS55B24W(08X",
					"St. Apple",
					"California",
					"Californi",
					"00235",
					"USA",
					"stevejobs@apple.it",
					true,
					LocalDateTime.now(),
					LocalDateTime.now()
			);
			clientRepository.save(clientEntity001);

			TrackingEntity trackingEntity001 = new TrackingEntity(
				clientRepository.findById(2).orElse(null),
					LocalDateTime.now(),
					LocalDateTime.now().plusSeconds(55),
					Utility.durationToSring(LocalDateTime.now(), LocalDateTime.now().plusSeconds(55)),
					Utility.durationSeconds(LocalDateTime.now(), LocalDateTime.now().plusSeconds(55))
			);
			trackingRepository.save(trackingEntity001);
		};
	}
}
