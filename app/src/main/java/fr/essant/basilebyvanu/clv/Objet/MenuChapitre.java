package fr.essant.basilebyvanu.clv.Objet;

import android.content.Context;
import android.telephony.AccessNetworkConstants;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuChapitre {

    private HashMap<Integer, Chapitre> listChapitres;

    public MenuChapitre(Context context) {

        listChapitres = new HashMap<>();
        readFromJson(context);


    }

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
        JsonObject jsonObject = new JsonObject();


        for (int i = 0; i < jsonArray.size(); i++){

            Log.d("parseJsonFile", jsonArray.get(i).toString());

            jsonObject = (JsonObject) jsonArray.get(i);
            Chapitre chp = new Chapitre(
                    jsonObject.get("nom").toString(),
                    jsonObject.get("temps").getAsInt(),
                    jsonObject.get("numero").getAsInt(),
                    jsonObject.get("lien").toString());
            Log.d("parseJsonFile",chp.getNom());

            this.addChapitres(jsonObject.get("temps").getAsInt(), chp);
        }
/*
        for (int i = 0; i < jsonChapitre.size(); i ++){
            jsonArray = (JsonArray) parser.parse(jsonChapitre.get(i));
            Chapitre chp = new Chapitre(jsonArray.get(0).toString(), jsonArray.get(1).getAsInt(),jsonArray.get(2).getAsInt());
            this.addChapitres(jsonArray.get(1).getAsInt(), chp);
        } */
    }

    public HashMap<Integer, Chapitre> getListChapitres() {
        return listChapitres;
    }


    public Chapitre getChapitre(int temps){
        return listChapitres.get(temps);
    }

    public void addChapitres(int temps, Chapitre chapitre) {
        this.listChapitres.put(temps, chapitre);
    }
}
