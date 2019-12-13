package hr.foi.air.food2go;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import hr.foi.air.food2go.controller.Internet;
import hr.foi.air.food2go.controller.LogInActivity;
import hr.foi.air.food2go.fragmenti.kategorije.KategorijeViewModel;
import hr.foi.air.food2go.fragmenti.moje_narudzbe.MojeNarudzbeViewModel;
import hr.foi.air.food2go.fragmenti.nagrade.NagradeViewModel;
import hr.foi.air.food2go.fragmenti.postavke.PostavkeViewModel;
import hr.foi.air.food2go.fragmenti.stanje_bodova.StanjeBodovaViewModel;
import hr.foi.air.food2go.fragmenti.trenutna_narudzba.TrenutnaNarudzbaViewModel;

public class GlavniActivity extends AppCompatActivity  {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private boolean prijavljen = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       try{
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_glavni);

           initializeLayout();
      /*  getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, new KategorijeViewModel())
                .commit();*/
       }catch (Exception ex){
           Log.e("AIR",ex.getMessage());
       }
       }


    private void initializeLayout() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.kategorije, R.id.trenutna_narudzba, R.id.moje_narudzbe,
                R.id.nagrade, R.id.stanje_bodova, R.id.postavke,R.id.odjava)
                .setDrawerLayout(drawerLayout)
                .build();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navController= Navigation.findNavController(this, R.id.nav_host_fragment);
        navigationView = findViewById(R.id.nav_view);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
/*
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id) {
            case R.id.kategorije:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new KategorijeViewModel())
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
            case R.id.trenutna_narudzba:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new TrenutnaNarudzbaViewModel())
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
            case R.id.moje_narudzbe:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new MojeNarudzbeViewModel())
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
            case R.id.nagrade:
                if (Internet.isNetworkAvailable(this) == true) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.nav_host_fragment, new NagradeViewModel())
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
                            .replace(R.id.nav_host_fragment, new StanjeBodovaViewModel())
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
                            .replace(R.id.nav_host_fragment, new PostavkeViewModel())
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
                                        Intent i = new Intent(GlavniActivity.this, LogInActivity.class);
                                        startActivityForResult(i, 1);
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
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
}
