package com.example.mechri_baderdine_mesure_glycemie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tvAge, tvres;
    private SeekBar sbAge;
    private RadioButton rbOui,rbNon;
    private EditText edit_text;
    private Button btnConsulter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnConsulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Appeler la méthode "calculer"
                Calculer(v);
            }
        });
        sbAge.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Log.i( "information","onProgressChange"+i);
                        tvAge.setText("votre age"+i);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

    }
    private  void init(){
        tvAge =(TextView) findViewById(R.id.tvAge);
        sbAge =(SeekBar) findViewById((R.id.sbAge));
        rbOui = (RadioButton) findViewById(R.id.rbOui);
        rbOui = (RadioButton) findViewById(R.id.rbNon);
        edit_text =(EditText) findViewById(R.id.edit_text);
        btnConsulter= (Button) findViewById(R.id.btnConsulter);
        tvres =(TextView) findViewById(R.id.tvres);
    }
    public  void Calculer(View v){
        int age;
        float valeurMesuré;
        boolean verifierAge= false;
        boolean verifierValeur=false;
        if(sbAge.getProgress()!=0)
            verifierAge=true;
        else
            Toast.makeText(MainActivity.this,"veullier verifier votre age",Toast.LENGTH_SHORT);
        if(edit_text.getText().toString().isEmpty())
            verifierValeur=true;
        else
            Toast.makeText(MainActivity.this,"vellier verifier votre valeur",Toast.LENGTH_LONG);
        if(verifierAge && verifierValeur){
            age = sbAge.getProgress();
            valeurMesuré= Float.valueOf(edit_text.getText().toString());
            if(rbOui.isChecked())
                if(age>= 13)
                    if(valeurMesuré < 5.0)
                        tvres.setText("le niveau de glycemie est bas");
                    else if (valeurMesuré>=5.0  && valeurMesuré<=7.2) {
                        tvres.setText("le niveau de glycemie est normale");
                    }
                    else
                        tvres.setText("le niveau de glycemie est elevee");
            else if (age >=6 && age<=12)
                if(valeurMesuré<5.0)
                    tvres.setText("niveau glycemie et bas");
                else if (valeurMesuré >=5.0 && valeurMesuré<=10.0 )
                    tvres.setText("niveau glycemie est minimal");
                else
                    tvres.setText("niveau eleve");
            else
                if(valeurMesuré<5.5)
                    tvres.setText("niveau tres bas");
                else if(valeurMesuré>=5.5 && valeurMesuré<=10.0)
                    tvres.setText("niv est minimal");
                else
                    tvres.setText("niveau elevé");
            else
                if (valeurMesuré<=10.5)
                    tvres.setText("niv minimal");
                else
                    tvres.setText("niveleve");

        }

    }

}
