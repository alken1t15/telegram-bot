import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    public static String gerWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+message+"&appid=1b9292c3a5864458f9ae6aae0ee24bef");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()){
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getArray = object.getJSONArray("weather");
        for(int i = 0; i < getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String)obj.get("main"));
        }

        return "City: " + model.getName() + "\n" +
                "Temperature: " + model.getTemperature() + "C" + "\n" +
                "Humidity: " + model.getHumidity() + "%" + "\n" +
                "Main: " + model.getMain() + "\n";
    }
}
