package com.example.all4sport;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            setContentView(R.layout.activity_page_produit);
            TextView tv = (TextView) findViewById(R.id.textproduit);
            tv.setText(token);
            Log.e("aaaaaaa", token);
        } catch (IOException e) {
            Log.e("OEEEEEEEEEE C'EST LA D", e.toString());
        }


    }

}