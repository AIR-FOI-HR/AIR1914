package hr.foi.air.food2go;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import hr.foi.air.core.Korisnik;
import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.controller.LogInActivity;
import hr.foi.air.food2go.fragmenti.kategorije.KategorijeFragment;
import hr.foi.air.food2go.fragmenti.moje_narudzbe.MojeNarudzbeFragment;
import hr.foi.air.food2go.fragmenti.nagrade.NagradeFragment;
import hr.foi.air.food2go.fragmenti.postavke.PostavkeFragment;
import hr.foi.air.food2go.fragmenti.stanje_bodova.StanjeBodovaFragment;
import hr.foi.air.food2go.fragmenti.trenutna_narudzba.TrenutnaNarudzbaFragment;

public class GlavniActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private AppBarConfiguration mAppBarConfiguration;
    boolean prijavljen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_glavni);
        try {
            initializeLayout();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void initializeLayout() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        drawerLayout=findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.kategorije, R.id.trenutna_narudzba, R.id.moje_narudzbe,
                R.id.nagrade, R.id.stanje_bodova, R.id.postavke, R.id.odjava)
                .setDrawerLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View headView = navigationView.getHeaderView(0);
        TextView prijavljeniKorisnik = headView.findViewById(R.id.prijavljeniKorisnik);
        prijavljeniKorisnik.setText(Korisnik.getPrijavljeniKorisnik().vratiImeiPrezime());

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.kategorije:

                break;
            case R.id.trenutna_narudzba:

                break;
            case R.id.moje_narudzbe:

                break;
            case R.id.nagrade:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new NagradeFragment())
                            .commit();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
                    alertDialog.setTitle("Pogreška u internet vezi");
                    alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste koristili aplikaciju.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;
            case R.id.stanje_bodova:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new StanjeBodovaFragment())
                            .commit();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
                    alertDialog.setTitle("Pogreška u internet vezi");
                    alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste koristili aplikaciju.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;
            case R.id.postavke:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new PostavkeFragment())
                            .commit();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
                    alertDialog.setTitle("Pogreška u internet vezi");
                    alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste koristili aplikaciju.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;
            case R.id.odjava:
                if (Internet.isNetworkAvailable(this) == true) {
                    deleteSharedPrefs();
                    if (checkLoginPersistence() == false) {
                        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                        alertDialog.setTitle("Uspješno ste se odjavili");
                        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Korisnik.setPrijavljeniKorisnik(null);
                                        Intent i = new Intent(GlavniActivity.this, LogInActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivityForResult(i, 1);
                                        finish();
                                    }
                                });
                        alertDialog.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Neuspješna odjava", Toast.LENGTH_LONG).show();
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(GlavniActivity.this).create();
                    alertDialog.setTitle("Pogreška u internet vezi");
                    alertDialog.setMessage("Molimo Vas omogućite internetsku vezu kako biste se odjavili iz aplikacije.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void deleteSharedPrefs() {//potrebno kasnije za odjavu
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("prijavljen", false);
        editor.remove("korisnickoIme");
        editor.remove("lozinka");
        editor.apply();
    }

    private Boolean checkLoginPersistence() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prijavljen = prefs.getBoolean("prijavljen", true);
        return prijavljen;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
