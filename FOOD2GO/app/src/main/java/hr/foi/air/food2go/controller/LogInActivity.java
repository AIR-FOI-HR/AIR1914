package hr.foi.air.food2go.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hr.foi.air.food2go.R;

public class LogInActivity extends AppCompatActivity {

    private EditText email, lozinka;
    private Button btn_prijava;
    private static String URL_LOGIN = "https://airfood2go.000webhostapp.com/Stranice/Prijava.php";

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
                    Prijava(korisnickoIme, password);
                }
                else{
                    email.setError("Molimo Vas unesite korisničko ime!");
                    lozinka.setError("Molimo Vas unesite lozinku!");
                }
            }
        });
    }

    private void Prijava(final String email, final String lozinka) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String uspjesno = jsonObject.getString("uspjesan");
                            JSONArray jsonArray = jsonObject.getJSONArray("prijava");

                            if(uspjesno.equals("1")){
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    //String ime = object.getString("ime").trim();
                                    String email = object.getString("email").trim();

                                    Toast.makeText(LogInActivity.this, "Uspjesna prijava. \nEMail: " + email, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(LogInActivity.this, "Pogreška!" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LogInActivity.this, "Pogreška!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
         @Override
         protected Map<String, String> getParams() throws AuthFailureError{
             Map<String, String> parametri = new HashMap<>();
             parametri.put("korisnickoIme", email);
             parametri.put("lozinka", lozinka);
             return parametri;
         }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
