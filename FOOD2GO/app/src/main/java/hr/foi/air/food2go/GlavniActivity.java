package hr.foi.air.food2go;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import hr.foi.air.food2go.fragmenti.kategorije.KategorijeViewModel;
import hr.foi.air.food2go.fragmenti.moje_narudzbe.MojeNarudzbeViewModel;
import hr.foi.air.food2go.fragmenti.nagrade.NagradeViewModel;
import hr.foi.air.food2go.fragmenti.odjava.OdjavaViewModel;
import hr.foi.air.food2go.fragmenti.postavke.PostavkeViewModel;
import hr.foi.air.food2go.fragmenti.stanje_bodova.StanjeBodovaViewModel;
import hr.foi.air.food2go.fragmenti.trenutna_narudzba.TrenutnaNarudzbaViewModel;

public class GlavniActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni);

        initializeLayout();
        showMainFragment();
    }

    private void initializeLayout()
    {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showMainFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, new KategorijeViewModel())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id)
        {
            case R.id.kategorije:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new KategorijeViewModel())
                        .commit();
                break;
            case R.id.trenutna_narudzba:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new TrenutnaNarudzbaViewModel())
                        .commit();
                break;
            case R.id.moje_narudzbe:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new MojeNarudzbeViewModel())
                        .commit();
                break;
            case R.id.nagrade:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new NagradeViewModel())
                        .commit();
                break;
            case R.id.stanje_bodova:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new StanjeBodovaViewModel())
                        .commit();
                break;
            case R.id.postavke:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new PostavkeViewModel())
                        .commit();
                break;
            case R.id.odjava:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment, new OdjavaViewModel())
                        .commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
