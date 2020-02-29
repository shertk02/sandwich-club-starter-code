package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName = null;
        List<String> alsoKnowAs = null;
        String description = null;
        String placeOfOrigin = null;
        String imageURL = null;
        List<String> ingredients = null;
        try{
            JSONObject rootJSON = new JSONObject(json);
            JSONObject nameJSON = rootJSON.getJSONObject("name");
            JSONArray akaArray = nameJSON.getJSONArray("alsoKnownAs");
            JSONArray ingredientsJSON = rootJSON.getJSONArray("ingredients");

            mainName = nameJSON.optString("mainName");
            alsoKnowAs = arrayToString(akaArray);
            description =  rootJSON.optString("description");
            placeOfOrigin = rootJSON.optString("placeOfOrigin");
            imageURL = rootJSON.optString("image");
            ingredients = arrayToString(ingredientsJSON);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName,alsoKnowAs,placeOfOrigin,description,imageURL,ingredients);

    }
    private static List<String> arrayToString(JSONArray harry){
        List<String> result = new ArrayList<>();
        for(int i = 0; i < harry.length(); i++){
                result.add(harry.optString(i));
        }
        return result;
    }
}
