package hu.katzler.pirateships.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.katzler.pirateships.model.Ship;

public class PirateShipDownloader {
    @Inject
    HttpHandler httpHandler;
    List<Ship> shipList;

    public PirateShipDownloader(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    public List<Ship> downloadList() {
        List<Ship> list = new ArrayList<>();
        String response = httpHandler.makeServiceCall("http://assets.shpock.com/android/interview-test/pirateships");
        Log.i(getClass().getSimpleName(), "response: " + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean success = jsonObject.optBoolean("success", false);
            if (success) {
                JSONArray ships = jsonObject.optJSONArray("ships");
                int length = ships.length();
                for (int i = 0; i < length; i++) {
                    try {
                        JSONObject jsonShip = ships.optJSONObject(i);
                        Ship ship = new Ship(jsonShip);
                        list.add(ship);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        shipList = list;
        return list;
    }

    public List<Ship> getShipList() {
        return shipList;
    }

    public Ship getShip(int id) {
        for (Ship ship : shipList) {
            if (ship.getId() == id) {
                return ship;
            }
        }
        return null;
    }
}
