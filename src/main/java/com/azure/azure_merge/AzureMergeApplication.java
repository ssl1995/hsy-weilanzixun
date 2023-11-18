package com.azure.azure_merge;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan("com.azure.azure_merge.mapper")
@ServletComponentScan
@EnableTransactionManagement
public class AzureMergeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzureMergeApplication.class, args);
	}

}
