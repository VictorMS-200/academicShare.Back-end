package br.com.una.academicShare;

import br.com.una.academicShare.model.domain.*;
import br.com.una.academicShare.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AcademicShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademicShareApplication.class, args);
	}
}
