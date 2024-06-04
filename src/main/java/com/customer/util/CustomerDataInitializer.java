package com.customer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.customer.model.CustomerDetails;
import com.customer.service.CustomerService;

@Component
public class CustomerDataInitializer implements CommandLineRunner {

	private final CustomerService service;

	public CustomerDataInitializer(CustomerService service) {
		this.service = service;
	}

	//To initialize in memory database
	@Override
	public void run(String... args) throws Exception {
		String input = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n"
				+ "3244332,2346,us_east,RedTeam,ProjectApple,3456s\n"
				+ "1233456,2346,us_west,BlueTeam,ProjectBanana,3312s\n"
				+ "3244132,2347,us_east,RedTeam,ProjectCarrot,3445s\n"
				+ "1233457,2347,us_west,BlueTeam,ProjectBanana,2445s";

		List<CustomerDetails> records = parseInput(input);
		service.saveAll(records);
	}

	private List<CustomerDetails> parseInput(String input) {
		List<CustomerDetails> records = new ArrayList<>();
		String[] lines = input.split("\n");

		//Pattern pattern = Pattern.compile("(\\d+)\\s+(\\d+)\\s+(\\w+)\\s+(\\w+)\\s+(\\w+)\\s+(\\d+)s");
		Pattern pattern = Pattern.compile("(\\d+),(\\d+),(\\w+),(\\w+),(\\w+),(\\d+)s");

		for (String line : lines) {
			Matcher matcher = pattern.matcher(line.trim());
			if (matcher.matches()) {
				int customerId = Integer.parseInt(matcher.group(1));
				int contractId = Integer.parseInt(matcher.group(2));
				String geozone = matcher.group(3);
				String teamCode = matcher.group(4);
				String projectCode = matcher.group(5);
				int buildDuration = Integer.parseInt(matcher.group(6));

				records.add(new CustomerDetails(null, customerId, contractId, geozone, teamCode, projectCode,
						buildDuration));
			}
		}

		return records;
	}
}
