package com.example.hands;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hands.database.DBHandler;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    Button btnAdd,selectDate;
    EditText idnome, idnasc,idtemp,idtosse,iddor,idsem;
    //RadioButton iditalia, idchina,idindonesia,idportugal,ideua,idoutros;
    RadioGroup radioGroup;
    RadioButton radioButton,iditalia;;
    DBHandler DB;

    TextView mostraTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        idnome = findViewById(R.id.idnome);
        idnasc = findViewById(R.id.idnasc);
        idtemp = findViewById(R.id.idtemp);
        idtosse = findViewById(R.id.idtosse);
        iddor  = findViewById(R.id.iddor);
        idsem = findViewById(R.id.idsem);
        radioGroup = findViewById(R.id.radioGroup);

        DB = new DBHandler(this);



    }
    public void btnAdd(View v) {
        String nomep = idnome.getText().toString();
        String idadep = idnasc.getText().toString();
        String temperaturap = idtemp.getText().toString();
        String tossep = idtosse.getText().toString();
        String dorp = iddor.getText().toString();
        String semanap = idsem.getText().toString();

        if(nomep.isEmpty() || idadep.isEmpty() || temperaturap.isEmpty() || tossep.isEmpty() || dorp.isEmpty() || semanap.isEmpty()){

            Snackbar.make(v,"Você esqueceu de preencher um campo",Snackbar.LENGTH_SHORT).show();

        }else{
            btc(v);
            RadioButtonSelecionar(v);
        }


    }


    public void btc(View view){
        String nomep = idnome.getText().toString();
        DBHandler myDB = new DBHandler(MainActivity2.this);
        myDB.revien(nomep);
        Snackbar.make(view,"Login Duplicado",Snackbar.LENGTH_SHORT).show();
    }



    private void RadioButtonSelecionar(View view) {
        String nome = idnome.getText().toString();
        int idade = Integer.parseInt(idnasc.getText().toString());
        int temperatura = Integer.parseInt(idtemp.getText().toString());
        int tosse = Integer.parseInt(idtosse.getText().toString());
        int dor = Integer.parseInt(iddor.getText().toString());
        int semana = Integer.parseInt(idsem.getText().toString());

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        String paisp = radioButton.getText().toString();
        String[] items = {"Itália","China","Indonésia","Portugal","Eua","Outros"};
        List<String> lista = Arrays.asList(items);

        if (lista.contains(paisp) && semana == 6 && temperatura > 37 && tosse > 5 && dor > 5) {
             DB.addTB(nome,idade,temperatura,tosse,dor,paisp ,semana,"O paciente deve ser internado para tratamento");
            Snackbar.make(view, "O paciente deve ser enviado à quarentena 1 " , Snackbar.LENGTH_SHORT).show();
        } else if (lista.contains(paisp) & semana == 6 & dor > 3 &  tosse > 5 & temperatura > 3 && idade > 60 | idade < 10) {
            DB.addTB(nome,idade,temperatura,tosse,dor,"Italia",semana,"O paciente deve ser enviado à quarentena");
            Snackbar.make(view, " O paciente deve ser enviado à quarentena  2 ", Snackbar.LENGTH_SHORT).show();
        } else if (lista.contains(paisp) & semana == 6 & dor > 5 & tosse > 5 & idade >= 10 & idade <= 60) {
            Snackbar.make(view, " O paciente deve ser enviado à quarentena  3", Snackbar.LENGTH_SHORT).show();
            DB.addTB(nome,idade,temperatura,tosse,dor,"Italia",semana,"O paciente deve ser enviado à quarentena lv2");
        } else if(lista.contains(paisp) && semana < 6 && dor < 3 & tosse < 5 & temperatura < 3 & temperatura < 37 & idade > 1 ){
            Snackbar.make(view, "O paciente deve ser liberado", Snackbar.LENGTH_SHORT).show();
            DB.addTB(nome,idade,temperatura,tosse,dor,paisp,semana,"Paciente Liberado");
        }else{
            Snackbar.make(view, "O paciente deve ser liberado", Snackbar.LENGTH_SHORT).show();
            DB.addTB(nome,idade,temperatura,tosse,dor,paisp,semana,"Paciente Liberado");
        }



    }


}
