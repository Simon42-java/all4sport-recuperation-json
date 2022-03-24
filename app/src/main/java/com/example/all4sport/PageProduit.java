package com.example.all4sport;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PageProduit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String product = null;

        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("value");


        try {
            String token = null;
            String url = "http://192.168.13.40:3000/produits/get/" + str;
            token = APIConnection.get(url);
            Toast toast = Toast.makeText(PageProduit.this, token, Toast.LENGTH_LONG);
            toast.show();
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




            Log.e("aaaaaaa", token);
        } catch (IOException | JSONException e) {
            Log.e("OEEEEEEEEEE C'EST LA D", e.toString());
        }


    }

}