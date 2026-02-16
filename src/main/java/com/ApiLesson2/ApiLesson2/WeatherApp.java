package com.ApiLesson2.ApiLesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

public class WeatherApp {
    public static void main(String[] args) {
        WeatherService service = new WeatherService();
        Scanner scanner = new Scanner(System.in);

        String[] cities = {"St. Louis", "Edwardsville, IL", "Chicago"};
        boolean running = true;

        while (running) {
            displayMenu(cities);
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 4) {
                running = false;
                System.out.println("Goodbye!");
            } else if (choice >= 1 && choice <= 3) {
                String city = cities[choice - 1];
                displayWeather(service, city);
            } else {
                System.out.println("Invalid choice. Try again.\n");
            }
        }

        scanner.close();
    }

    private static void displayMenu(String[] cities) {
        for (int i = 0; i < cities.length; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
        System.out.println("4. Exit");
        System.out.print("Choose a city: ");
    }

    private static void displayWeather(WeatherService service, String city) {
        try {
            Weather weather = service.getWeather(city);

            System.out.println("\n--- " + city + " Weather ---");
            System.out.println("Condition: " + weather.getCondition());
            System.out.println("Temperature: " + weather.getTemp() + "°F");
            System.out.println("Humidity: " + weather.getHumidity() + "%");
            System.out.println("------------------------");
        } catch (Exception e) {
            System.out.println("Error fetching weather for " + city);
        }
    }
}