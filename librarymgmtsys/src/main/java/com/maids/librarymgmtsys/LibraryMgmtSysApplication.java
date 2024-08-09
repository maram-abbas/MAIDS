package com.maids.librarymgmtsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LibraryMgmtSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryMgmtSysApplication.class, args);
	}

}
