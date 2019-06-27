package com.nidecai.managerndc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan(basePackages = "com.nidecai.managerndc.mapper")


public class ManagerNdcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerNdcApplication.class, args);
	}

}
