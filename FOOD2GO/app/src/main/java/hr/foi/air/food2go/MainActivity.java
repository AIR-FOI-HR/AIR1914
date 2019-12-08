package hr.foi.air.food2go;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import hr.foi.air.food2go.controller.RegistracijaActivity;
import hr.foi.air.food2go.controller.LogInActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prijava = findViewById(R.id.uiActionPrijaviSe);
        btn_registracija = findViewById(R.id.uiActionRegistrirajSe);

        btn_prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        btn_registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, RegistracijaActivity.class));
            }
        });
    }
}