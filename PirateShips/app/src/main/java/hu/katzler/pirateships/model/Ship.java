package hu.katzler.pirateships.model;

import org.json.JSONObject;

public class Ship {
    private int id, price;
    private String title, description, image, greeting_type;

    public Ship(JSONObject jsonObject) {
        id = jsonObject.optInt("id", 0);
        price = jsonObject.optInt("price", 0);
        title = jsonObject.optString("title", null);
        description = jsonObject.optString("description", null);
        image = jsonObject.optString("image", null);
        greeting_type = jsonObject.optString("greeting_type", null);
    }
}
