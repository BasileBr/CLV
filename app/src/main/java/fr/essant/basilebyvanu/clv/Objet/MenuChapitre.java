package fr.essant.basilebyvanu.clv.Objet;

import android.content.Context;
import android.telephony.AccessNetworkConstants;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

public class MenuChapitre {

    private ArrayList<Chapitre> listChapitres;


    public void readFromJson(Context context){
        String json = null;

        try {
            InputStream is = context.getAssets().open("Chapitre.json");
            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer,"UTF-8");


        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("readFromJSon", json);
        parseJsonFile(json);

    }

    public void parseJsonFile(String json){
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(json);

        for (int i = 0; i < jsonArray.size(); i++){

            Log.d("parseJsonFile", jsonArray.get(i).toString());
        }
    }

    public ArrayList<Chapitre> getListChapitres() {
        return listChapitres;
    }

    public void addChapitres(Chapitre chapitres) {
        this.listChapitres.add(chapitres);
    }
}
