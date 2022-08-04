package com.example.exemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextDisciplina, editTextNota;
    private Button buttonAdd, buttonGerar, buttonConsumir;
    private TextView textViewResultado;
    private List<Estudante> dados;
    private String retorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome=findViewById(R.id.editTextNome);
        editTextDisciplina=findViewById(R.id.editTextDisciplina);
        editTextNota=findViewById(R.id.editTextNota);
        buttonAdd=findViewById(R.id.buttonAdd);
        buttonConsumir=findViewById(R.id.buttonConsumir);
        buttonGerar=findViewById(R.id.buttonConsumir);
        textViewResultado = findViewById(R.id.textViewResultado);

        dados = new ArrayList<>();
    }

    public void criarLista(View view) {
        dados.add(new Estudante(editTextNome.getText().toString(),
                editTextDisciplina.getText().toString(),
                Double.parseDouble(editTextNota.getText().toString()))
        );
        Toast.makeText(this, "item Inserido", Toast.LENGTH_SHORT).show();
    }

    public String criarJson(List<Estudante> dados){

        Gson gson = new Gson();
        String stringJson = gson.toJson(dados);
        return stringJson;

        /*JSONArray jsonArray = new JSONArray();
        for (int i = 0; i<dados.size();i++){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("nomeEstudante", dados.get(i).getNome());
                jsonObject.put("disciplinaEstudante", dados.get(i).getDisciplina());
                jsonObject.put("notaEstudante", dados.get(i).getNota());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "{estudantes:"+jsonArray.toString()+"}";*/
    }

    public void gerarJSON(View view) {
        retorno = criarJson(dados);
        textViewResultado.setText(retorno);
    }

    public void abrirTela(View view) {

        Intent i = new Intent(getApplicationContext(), Activity2.class);
        i.putExtra("dados", retorno);
        startActivity(i);

    }
}