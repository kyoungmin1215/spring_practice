package com.example.practice;

import com.example.practice.config.OS_env;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) throws FileNotFoundException {

		SpringApplication.run(PracticeApplication.class, args);
	}

}
