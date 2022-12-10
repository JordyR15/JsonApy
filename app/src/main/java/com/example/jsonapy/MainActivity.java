package com.example.jsonapy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtLista2=(TextView)findViewById(R.id.txtLista);
        String lista="";
        JSONObject objectJson = new JSONObject(result);
        JSONArray JSONlista = objectJson.getJSONArray("data");
        JSONObject banco;
        for(int i=0; i< JSONlista.length();i++){
            banco = JSONlista.getJSONObject(i);
            lista = lista + "id: "+ banco.getString("id").toString() +
                    "name: "+ banco.getString("name").toString() +
                    "email: "+ banco.getString("email").toString() +
                    "gender: "+ banco.getString("gender").toString() +
                    "status: "+ banco.getString("status").toString() + "\n";
        }
        txtLista2.setText(lista);
    }

    public void Listar(View view){
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET", "Public-Merchant-Id", "84e1d0de1fbf437e9779fd6a52a9ca18");
    }
}