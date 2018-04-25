package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        String mainName, origin, description, image;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients =  new ArrayList<>();

        try {
            JSONObject baseObject = new JSONObject(json);
            JSONObject nameDetails = baseObject.getJSONObject("name");
            if(nameDetails != null){
                mainName = nameDetails.getString("mainName");
                JSONArray aliases = nameDetails.getJSONArray("alsoKnownAs");
                if(aliases != null){
                    for(int i = 0; i < aliases.length(); i++){
                        alsoKnownAs.add(aliases.getString(i));
                    }
                }
            }else {
                throw new IllegalArgumentException("Sandwitch must have a name!");
            }
            origin = baseObject.getString("placeOfOrigin");
            description = baseObject.getString("description");
            image = baseObject.getString("image");
            JSONArray ing = baseObject.getJSONArray("ingredients");
            if(ing != null){
                for(int i = 0; i < ing.length(); i++){
                    ingredients.add(ing.getString(i));
                }
            }
        }
        catch (JSONException e){
            Log.e(TAG, "parseSandwichJson: Error in parsing Json", e);
            return null;
        }
        return new Sandwich(mainName, alsoKnownAs, origin, description, image, ingredients);
    }
}
