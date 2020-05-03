package application.example.covid_19_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import javax.xml.transform.Templates;

import application.example.covid_19_app.ui.country.CountryFragment;
import application.example.covid_19_app.ui.home.HomeFragment;
import application.example.covid_19_app.ui.usa.USAFragment;

public class MainActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private CountryFragment countryFragment;
    private USAFragment usaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        homeFragment = new HomeFragment();
        countryFragment = new CountryFragment();
        usaFragment = new USAFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        // begin placement of the fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //set temp & dis fragment
        fragmentTransaction.add(R.id.placeHolderLayout, homeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (item.getItemId() == R.id.navigation_home) {
            Toast.makeText(this, "Home Selected.", Toast.LENGTH_LONG).show();
            fragmentTransaction.replace(R.id.placeHolderLayout, homeFragment);
        } else if (item.getItemId() == R.id.navigation_country) {
            Toast.makeText(this, "Country Selected.", Toast.LENGTH_LONG).show();
            fragmentTransaction.replace(R.id.placeHolderLayout, countryFragment);
        } else if (item.getItemId() == R.id.navigation_usa) {
            Toast.makeText(this, "USA Selected.", Toast.LENGTH_LONG).show();
            fragmentTransaction.replace(R.id.placeHolderLayout, usaFragment);}
        else {
            return super.onOptionsItemSelected(item);
        }
        fragmentTransaction.commit();
        return true;
    }

}

