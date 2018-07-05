package hu.katzler.pirateships.model;

import org.json.JSONObject;

public class Ship {
    private int id, price;
    private String title, description, image, greeting_type;

    public Ship(JSONObject jsonObject) {
        id = jsonObject.optInt("id", 0);
        price = jsonObject.optInt("price", 0);
        title = !jsonObject.isNull("title") ? jsonObject.optString("title", "No title") : "No title";
        description = jsonObject.optString("description", "No description");
        image = jsonObject.optString("image", null);
        greeting_type = jsonObject.optString("greeting_type", "Ahoi!");
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
