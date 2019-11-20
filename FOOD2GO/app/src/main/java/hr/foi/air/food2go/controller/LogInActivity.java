package hr.foi.air.food2go.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.foi.air.food2go.MainActivity;
import hr.foi.air.food2go.R;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogInActivity extends AppCompatActivity {

    private EditText email, lozinka;
    private Button btn_prijava;
    private static String URL_LOGIN = "https://airfood2go.000webhostapp.com/Android/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email = findViewById(R.id.korisnickoIme);
        lozinka = findViewById(R.id.lozinka);
        btn_prijava = findViewById(R.id.uiActionPrijaviSe);

        btn_prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String korisnickoIme = email.getText().toString().trim();
                String password = lozinka.getText().toString().trim();

                if(!korisnickoIme.isEmpty() || !password.isEmpty()){
                    new LoginUser().execute(korisnickoIme, password);
                    Toast.makeText(LogInActivity.this, "Ispravno!", Toast.LENGTH_SHORT).show();
                }
                else{
                    email.setError("Molimo Vas unesite korisničko ime!");
                    lozinka.setError("Molimo Vas unesite lozinku!");
                }
            }
        });
    }

    private class LoginUser extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            String email = strings[0];
            String password = strings[1];

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("userId", email)
                    .add("userPassword", password)
                    .build();
            Request request = new Request.Builder()
                    .url(URL_LOGIN)
                    .post(formBody)
                    .build();
            Response response = null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result = response.body().string();
                    if(result.equalsIgnoreCase("login")){
                        Intent i = new Intent(LogInActivity.this,
                                MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(LogInActivity.this, "Email ili lozinka ne valjaju!", Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

}
