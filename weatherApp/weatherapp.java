import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class weatherapp {
    public static void main(String[] args) {
        String apiKey = "c5eb1ba91dfa551543441bf7b3c4f652"; 
        String cityID = "2643743";
        String urlString = "https://api.openweathermap.org/data/2.5/weather?id=" + cityID + "&appid=" + apiKey + "&units=metric";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("API Response: " + response.toString());
            JSONObject json = new JSONObject(response.toString());
            System.out.println("City: " + json.getString("name"));
            System.out.println("Temperature: " + json.getJSONObject("main").getDouble("temp") + "°C");
            System.out.println("Weather: " + json.getJSONArray("weather").getJSONObject(0).getString("description"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}