package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameObject = jsonObject.getJSONObject(NAME);
            String mainName = nameObject.getString(MAIN_NAME);
            JSONArray alsoKnownAsArray = nameObject.getJSONArray(ALSO_KNOWN_AS);
            List<String> alsoKnownAs = convertJSONArrayToList(alsoKnownAsArray);
            String placeOfOrigin = jsonObject.getString(PLACE_OF_ORIGIN);
            String description = jsonObject.getString(DESCRIPTION);
            String image = jsonObject.getString(IMAGE);
            JSONArray ingredientsArray = jsonObject.getJSONArray(INGREDIENTS);
            List<String> ingredients = convertJSONArrayToList(ingredientsArray);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin,
                    description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> convertJSONArrayToList(JSONArray jsonArray) throws JSONException {
        List<String> list = new ArrayList<>();
        if (jsonArray != null) {
            int size = jsonArray.length();
            for (int i = 0; i < size; i++) {
                list.add(jsonArray.getString(i));
            }
        }
        return list;
    }
}
