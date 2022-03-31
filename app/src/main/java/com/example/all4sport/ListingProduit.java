package com.example.all4sport;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListingProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_produit);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        List<Item> items = new ArrayList<Item>();

        String url;
        String line = "";
        try {
            String token = null;
            url = "http://192.168.238.40:3000/produits";
            token = APIConnection.get(url);
            Toast toast = Toast.makeText(ListingProduit.this, token, Toast.LENGTH_LONG);
            toast.show();
            Log.e("aaaaaaa", token);
        } catch (IOException e) {
            Log.e("OEEEEEEEEEE C'EST LA D", e.toString());
        }
        //Connexion BDD

        JSONArray produits = new JSONArray();

        try {
            produits = new JSONArray(line);
            for (int i = 0; i< produits.length(); i++) {
                String nom_produit = produits.getJSONObject(i).getString("pr_description");
                String quantite = produits.getJSONObject(i).getString("pr_reference");
                int photo = produits.getJSONObject(i).getInt("pr_cout_unitaire_HT");

                items.add(new Item(nom_produit, photo, quantite));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), items));
    }}