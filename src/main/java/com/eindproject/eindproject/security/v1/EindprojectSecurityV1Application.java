package com.eindproject.eindproject.security.v1;

import com.eindproject.eindproject.security.v1.property.DocumentStorageProperties;
import com.eindproject.eindproject.security.v1.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties({
		DocumentStorageProperties.class
})
@ComponentScan(basePackages = {"com.eindproject.eindproject.security.v1"})
public class EindprojectSecurityV1Application {

	public static void main(String[] args) {
		SpringApplication.run(EindprojectSecurityV1Application.class, args);
	}



}
