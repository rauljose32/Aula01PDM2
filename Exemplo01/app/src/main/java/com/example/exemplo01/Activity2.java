package com.example.exemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private String dadosJson;
    private ListView listView;
    private List<Estudante> lista;
    private ArrayAdapter<Estudante> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        dadosJson = getIntent().getStringExtra("dados");
        listView = findViewById(R.id.listaViewDados);
        lista = consumirJson();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Activity2.this, lista.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private List<Estudante> consumirJson() {

        List<Estudante> estudanteList = null;
        if(dadosJson!=null){
            Gson gson = new Gson();
            Type type = new TypeToken<List<Estudante>>(){}.getType();
            estudanteList = gson.fromJson(dadosJson, type);
            Toast.makeText(getApplicationContext(),estudanteList.toString(), Toast.LENGTH_LONG).show();
        }
    return estudanteList;

        /*List<Estudante> estudanteList = null;
        if (dadosJson != null) {
            estudanteList = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(dadosJson);
                JSONArray jsonArray = jsonObject.getJSONArray("estudantes");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    Estudante estudante = new Estudante();
                    estudante.setNome(object.getString("nomeEstudante"));
                    estudante.setDisciplina(object.getString("disciplinaEstudante"));
                    estudante.setNota(object.getDouble("notaEstudante"));
                    estudanteList.add(estudante);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return estudanteList;*/

    }
}