package com.example.practice;

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
/*		Reader yamlFile = new FileReader("C:\\Users\\wkm25\\Desktop\\practice\\me\\springboot\\practice\\src\\main\\resources\\properties\\securityUserInfo.yml");
		Map<String, ArrayList> yamlMaps = new Yaml().load(yamlFile);
		System.out.println(yamlMaps);
		System.out.println(yamlMaps.get("user"));

		for(int i=0; i<yamlMaps.get("user").size(); i++) {
			System.out.println(yamlMaps.get("user").get(i));
			LinkedHashMap a = (LinkedHashMap)(yamlMaps.get("user").get(i));
			System.out.println(a.get("name"));
		}

		for(Object a : yamlMaps.get("user")) {
			System.out.println(a);
		}

		yamlMaps.get("user").forEach((k) -> System.out.print(k));

		for(Object cd : (ArrayList)(yamlMaps.get("user"))) {
			LinkedHashMap abc = (LinkedHashMap)cd;
			String cdd = (String)abc.get("name");
			System.out.println(cdd);
		}

		System.out.println("------------");
		for(Object cd : (ArrayList)(yamlMaps.get("user"))) {
			String cdd = (String)((LinkedHashMap)cd).get("name");
			System.out.println(cdd);
		}
		System.out.println("-------");
		System.out.println(((LinkedHashMap<?, ?>) yamlMaps.get("user").get(0)).get("name"));*/

		SpringApplication.run(PracticeApplication.class, args);
	}

}
