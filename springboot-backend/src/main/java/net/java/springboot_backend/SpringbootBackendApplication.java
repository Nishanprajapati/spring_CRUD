package net.java.springboot_backend;

import net.java.springboot_backend.model.Employee;
import net.java.springboot_backend.repository.EmployeeRepository;
import net.java.springboot_backend.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@EnableTransactionManagement
@SpringBootApplication
@EnableAsync
@EnableCaching
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);


	}


	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
       Employee employee = new Employee();
	   employee.setFirstName("Nishan");
	   employee.setLastName("Prajapati");
	   employee.setEmailId("nisan11prajapati@gmail.com");
	   employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setFirstName("ishan");
		employee1.setLastName("Prajapati1");
		employee1.setEmailId("isan11prajapati@gmail.com");
		employeeRepository.save(employee1);


	}

//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("employeeCache");
//	}

}
