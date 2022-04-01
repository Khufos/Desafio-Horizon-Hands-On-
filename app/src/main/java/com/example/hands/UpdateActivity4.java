package com.example.hands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hands.database.DBHandler;
import com.google.android.material.snackbar.Snackbar;

public class UpdateActivity4 extends AppCompatActivity {
    TextView id_nome ,id_idade,id_temperatura ,id_tosse, id_dor, id_pais, id_semanas, id_status;


    Button update_button, deletar_update;

    String  nome,idade,temperatura,tosse,dor,pais,semanas,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update4);
        // Onde pego o id para poder modificar na tabela .
        String id;
        getIntent().hasExtra("id");
        id = getIntent().getStringExtra("id");
        //-----------------------------------------

        id_nome = findViewById(R.id.id_nome_edit);
        id_idade = findViewById(R.id.id_idade_edit);
        id_temperatura= findViewById(R.id.id_temperatura_edit);
        id_tosse = findViewById(R.id.id_tosse_edit);
        id_dor = findViewById(R.id.id_dor_edit);
        id_pais = findViewById(R.id.id_pais_edit);
        id_semanas = findViewById(R.id.id_semanas_edit);
        id_status = findViewById(R.id.id_status_edit);
        update_button = findViewById(R.id.update_button);
        deletar_update = findViewById(R.id.deletar_button);
        //Call de passa dos pra editar
        getIntentData();
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome = id_nome.getText().toString().trim();
                idade = id_idade.getText().toString().trim();
                temperatura = id_temperatura.getText().toString().trim();
                tosse = id_tosse.getText().toString().trim();
                dor = id_dor.getText().toString().trim();
                pais = id_pais.getText().toString().trim();
                semanas = id_semanas.getText().toString().trim();
                status = id_status.getText().toString().trim();
                DBHandler myDB = new DBHandler(UpdateActivity4.this);
                myDB.updateData(id,nome,idade,temperatura,tosse,dor,pais,semanas,status);
                Snackbar.make(view, "Campo Modificado com sucesso!", Snackbar.LENGTH_SHORT).show();


                }
        });
        deletar_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler myDB = new DBHandler(UpdateActivity4.this);
                myDB.deleteOneRow(id);
            }
        });





    }



    void getIntentData() {
        if (getIntent().hasExtra("nome") &&
                getIntent().hasExtra("idade") &&
                getIntent().hasExtra("temperatura") &&
                getIntent().hasExtra("tosse")&&
                getIntent().hasExtra("dor") &&
                getIntent().hasExtra("pais") &&
                getIntent().hasExtra("semanas")&&
                getIntent().hasExtra("status")) {

            //-------------------------------------------//
            nome = getIntent().getStringExtra("nome");
            idade = getIntent().getStringExtra("idade");
            temperatura = getIntent().getStringExtra("temperatura");
            tosse = getIntent().getStringExtra("tosse");
            dor = getIntent().getStringExtra("dor");
            pais = getIntent().getStringExtra("pais");
            semanas = getIntent().getStringExtra("semanas");
            status = getIntent().getStringExtra("status");

            //-------------------------------------------//

            //Set data
            id_nome.setText(nome);
            id_idade.setText(idade);
            id_temperatura.setText(temperatura);
            id_tosse.setText(tosse);
            id_dor.setText(dor);
            id_pais.setText(pais);
            id_semanas.setText(semanas);
            id_status.setText(status);



        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}