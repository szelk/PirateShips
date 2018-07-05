package hu.katzler.pirateships.model;

import org.json.JSONObject;

public class Ship {
    private int id, price;
    private String title, description, image, greeting_type;

    public Ship(JSONObject jsonObject) {
        id = jsonObject.optInt("id", 0);
        price = jsonObject.optInt("price", 0);
        title = getString(jsonObject, "title", "No title");
        description = getString(jsonObject, "description", "No description");
        image = getString(jsonObject, "image", null);
        greeting_type = getString(jsonObject, "greeting_type", "Ahoi");
    }

    private String getString(JSONObject jsonObject, String name, String defaultValue) {
        return !jsonObject.isNull(name) ? jsonObject.optString(name, defaultValue) : defaultValue;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getGreetingType() {
        return greeting_type;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
