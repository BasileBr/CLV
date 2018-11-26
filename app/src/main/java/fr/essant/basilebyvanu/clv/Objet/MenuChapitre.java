package fr.essant.basilebyvanu.clv.Objet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class MenuChapitre {

    private ArrayList<Chapitre> listChapitres;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    public MenuChapitre(){

    }

    public static ArrayList<Chapitre> readFromJsonFile(String filename){
        ArrayList<Chapitre> result = new ArrayList<>();
        try {
            String text = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);

            JSONObject object = new JSONObject(text);
            JSONArray arr = object.getJSONArray("chapitre");

            for (int i = 0; i < arr.length(); i++ ) {

                String nom = arr.getJSONObject(i).getString("nom");
                int temps = arr.getJSONObject(i).getInt("temps");
                int numero = arr.getJSONObject(i).getInt("numero");

                Chapitre chapitre = new Chapitre(nom, temps, numero);
                result.add(chapitre);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
    public ArrayList<Chapitre> getListChapitres() {
        return listChapitres;
    }

    public void addChapitres(Chapitre chapitres) {
        this.listChapitres.add(chapitres);
    }
}
