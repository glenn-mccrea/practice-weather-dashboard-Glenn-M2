package com.ApiLesson2.ApiLesson2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class WeatherService {
    private String apiKey;

    public WeatherService() {
        try {
            Dotenv dotenv = Dotenv.load();
            this.apiKey = dotenv.get("OPENWEATHER_API_KEY");
        } catch (Exception e) {
            System.out.println("Could not load .env file.");
            this.apiKey = "YOUR_API_KEY_HERE";
        }
    }

    public Weather getWeather(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city
                + "&appid=" + apiKey + "&units=imperial";

        Map response = restTemplate.getForObject(url, Map.class);

        Weather weather = new Weather();
        weather.setTemp((double) ((Map) response.get("main")).get("temp"));
        weather.setHumidity(((Number) ((Map) response.get("main")).get("humidity")).intValue());
        weather.setCondition((String) ((Map) ((java.util.List) response.get("weather")).get(0)).get("main"));

        return weather;
    }
}