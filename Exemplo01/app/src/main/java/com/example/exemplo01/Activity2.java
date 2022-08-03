package com.example.exemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    }

    private List<Estudante> consumirJson() {
        List<Estudante> estudanteList = null;
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
        return estudanteList;
    }
}