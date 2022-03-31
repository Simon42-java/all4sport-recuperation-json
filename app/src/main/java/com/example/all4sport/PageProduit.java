package com.example.all4sport;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PageProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String product = null;

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("value");
        List<Item> items = new ArrayList<Item>();

        URL url1;
        boolean aproduit = false;
        String line = "";

        try {
            String token = null;
            url1 = new URL("http://192.168.56.1:3000/produits");
            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            line = rd.readLine();
            JSONArray produits1 = new JSONArray();

            try {
                produits1 = new JSONArray(line);
                for (int i = 0; i < produits1.length(); i++) {
                    //Log.e("aaaa", produits.getJSONObject(i).getString("nom"));
                    String nom_produit = produits1.getJSONObject(i).getString("pr_description");
                    String quantite = produits1.getJSONObject(i).getString("pr_reference"); //remplacer par le champs nb_produit
                    int photo = produits1.getJSONObject(i).getInt("pr_cout_unitaire_HT"); //remplacer par le champs ph_img
                    items.add(new Item(nom_produit, photo, quantite));
                    if (quantite.equals(str)) {
                        aproduit = true;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (aproduit == true) {


                String url = "http://192.168.238.40:3000/produits/get/" + str;
                token = APIConnection.get(url);
                // Toast toast = Toast.makeText(PageProduit.this, token, Toast.LENGTH_LONG);
                // toast.show();
                JSONObject jObj = null;

                JSONArray mJsonArray = new JSONArray(token);
                JSONObject mJsonObject = mJsonArray.getJSONObject(0);

                String ref = mJsonObject.getString("pr_reference");
                String cout = mJsonObject.getString("pr_cout_unitaire_HT");
                String nom = mJsonObject.getString("pr_description");

                //JSONObject json = new JSONObject("{\"pr_reference\":1,\"pr_cout_unitaire_HT\":1,\"pr_description\":\"aaaa\",\"fk_ba\":1,\"fk_mo\":1,\"fk_ra\":\"1\",\"fk_se\":1,\"fk_et\":1,\"fk_fournisseur\":1,\"fk_rayon\":1\"}");
                //JSONArray json = new JSONArray(token);
                //{"pr_description": "aaa"}
                //j'ai ça
                //{"pr_reference":1,"pr_cout_unitaire_HT":1,"pr_description":"aaaa","fk_ba":1,"fk_mo":1,"fk_ra":"1","fk_se":1,"fk_et":1,"fk_fournisseur":1,"fk_rayon":1}
                //je veut recuperer ça
                //"{\"pr_reference\":1,\"pr_cout_unitaire_HT\":1,\"pr_description\":\"aaaa\",\"fk_ba\":1,\"fk_mo\":1,\"fk_ra\":\"1\",\"fk_se\":1,\"fk_et\":1,\"fk_fournisseur\":1,\"fk_rayon\":1\"}"
                //String test = json.getString("fk_ba");


                setContentView(R.layout.activity_page_produit);
                TextView tv_ref = (TextView) findViewById(R.id.tv_ref);
                tv_ref.setText(ref);

                TextView tv_nom = (TextView) findViewById(R.id.tv_nom);
                tv_nom.setText(nom);

                TextView tv_cout = (TextView) findViewById(R.id.tv_cout);
                tv_cout.setText(cout);
            } else {
                TextView tv_nom = (TextView) findViewById(R.id.tv_nom);
                tv_nom.setText("non");
            }


            Log.e("aaaaaaa", token);
        } catch (IOException | JSONException e) {
            Log.e("OEEEEEEEEEE C'EST LA D", e.toString());
        }


    }
}