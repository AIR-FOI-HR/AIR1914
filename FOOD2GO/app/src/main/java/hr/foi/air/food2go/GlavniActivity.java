package hr.foi.air.food2go;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import butterknife.OnClick;
import hr.foi.air.food2go.controller.Internet;

public class GlavniActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.kategorije,
                R.id.trenutna_narudzba, R.id.moje_narudzbe, R.id.nagrade,
                R.id.stanje_bodova, R.id.postavke, R.id.odjava)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @OnClick(R.id.hranaKategorija)
    public void KlikHrana(View v){
        if(Internet.isNetworkAvailable(this) == true) {
            Toast.makeText(getApplicationContext(), "Odabrana kategorija: Hrana", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    @OnClick(R.id.piceKategorija)
    public void KlikPice(View v){
        if(Internet.isNetworkAvailable(this) == true) {
            Toast.makeText(getApplicationContext(), "Odabrana kategorija: Piće", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
            alertDialog.setTitle("Pogreška u internet vezi");
            alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako bi ste se prijavili u aplikaciju.");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
}
