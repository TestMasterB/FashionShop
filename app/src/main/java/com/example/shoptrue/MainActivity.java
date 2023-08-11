package com.example.shoptrue;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.shoptrue.Utilities.ImageListFragment;
import com.example.shoptrue.databinding.ActivityMainBinding;
import com.example.shoptrue.loginModule.LoginScreen;
import com.example.shoptrue.pucharse.BuyNow;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;
    ViewPager viewPager;
    static TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= ActivityMainBinding.inflate(LayoutInflater.from (this));
        setContentView(binding.getRoot());

        viewPager = findViewById(R.id.view_pager);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // drawerLayout wird verwendet, um die Navigation zu öffnen und zu schließen
        DrawerLayout drawerLayout=binding.draweLayout;
        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(
                this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_closed
        );

        // Listener nimmt wahr was der User macht
        // syncState() verändert den Listener, wenn der User klickt
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tableLayout = (TabLayout) findViewById(R.id.tabs);


        // wenn PageViewer NICHT null, dann wird die Reihenfolge der Fragmente bestimmt
        // (viewPager) - Apapter enthält die Fragmente weist sie (viewPager) zu
        // tableLayout - enthält die Tabs und mit (viewPager) verbunden
        // Fragmente sind Ansichten innerhalb einer Aktivität
        // setupViewPager fügt 2 Fragmente hinzu: 1. ImageListFragment & 2. tableLayout
        // Warum (viewPager !=null)? Antwort: Vermeidung einer NullPointer -Exception
        // soll vermeiden, dass der viewPager aufgerufen wird, bevor on onCreate initialisiert wurde
        // verhindert App Abstürze
        // ADAPTER = dient dazu die Fragmente zu laden mit den jeweiligen Informationen
        // VIEWPAGER = zeigt die Fragmente und ermöglicht zwischen ihnen zu wischen
        // TABLELAYOUT = dient als Navigationsmenü
        // Pre - Defined Method
        if (viewPager !=null) {
            setupViewPager(viewPager);
            tableLayout.setupWithViewPager(viewPager);
        }
    }

    // Standard - aber onResume () bestimmt was passieren soll, wenn der User von einer App in diese zurückkehrt
    // wichtig: invalidateOptionsMenu(); - aktualisiert die ActionBar - Menüliste
    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }


    // Bestimmt was passieren soll, wenn der User Zurück - Taste klickt
    // Hier: User kommt zur Navigationsleiste zurück
    // if (drawer.isDrawerOpen(GravityCompat.START)) - überprüft ob Navigationsleiste offen ist
    // Wenn JA: drawer.closeDrawer - Navigation schließen
    // Wenn NEIN: User geht ganz normal eine Navigation zurück
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = binding.draweLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();



        }
    }

    // Erschafft das Menü - erwartet ein menu Objekt und einen Boolean zurück
    // Wir verbinden den Layout nav_menu mit OnNavigationItemSelectedListener (Zeile 26 )
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }


    // Pre - Defined - muss erstmal so stehen bleiben
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    // ist für die ImageListFragmentList notwendig
    // getSupportFragmentManager() - dient der Verwaltung von Adaptern
    // fragment = new ImageListFragment() - neue Instanz wird der Variable fragment zugewiesen
    // es werden 2 fragments erstellt, aber der Bundle wird unterschiedlich bestimmt
    // wichtig um unterschieldliche Tabs zu zeigen
    // Bundle - Datenstruktur, bestimmt mit type die Art und 1 die Untergruppe

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        ImageListFragment fragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, "Offer");
        fragment = new ImageListFragment();
        bundle = new Bundle();
        viewPager.setAdapter(adapter);
    }

    // LOGOUT
    // if (item.getItemId() == R.id.my_logout): überprüft, ob die Menü -Elemente die ID "logout" hat
    // Wenn JA: User kann sich ausloggen
    // finish (): dient dazu die MainActivity endgültig zu schließen, sodass der User sich neu anmelden muss
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.my_logout) {
            onBackPressed();
            Toast.makeText(this, "Logout",Toast.LENGTH_SHORT).show();
            Intent inm = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(inm);
            finish();
        } else {
            Intent inm = new Intent(MainActivity.this, BuyNow.class);
            startActivity(inm);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawe_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // ADAPTER = dient dazu die Fragmente zu laden mit den jeweiligen Informationen
    // Zeigt alle Bilder im main - content

    // private final List<Fragment> mFragments = new ArrayList<>(); -erhöt die Anzajl der Screen, wenn nach rechts gewischt
    // mFragmentTitles - bestinmmt nicht den Namen des Produktes, sondern nur den Titel Beispiel "Offer"
    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();


        // Konstruktor der Adapter Klasse - FragmentPagerAdapter mit FragmentManager aufgerufen wird
        // FragmentManager ist verantwortlich für die Aktualisierung der Fragmente
        public Adapter(FragmentManager fm) {
            super(fm);
        }


        // Methode um einem Fragment und Titel einzufügen
        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }


        // Methode um einem Fragment nach seiner POSITION zu erhalten
        // Da nur 1 Fragment, gibt es keine offiziellen Positionen
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }


        // Methode um die ANZAHL der Fragmente in der Liste zu erhalten
        @Override
        public int getCount() {
            return mFragments.size();
        }


        // Methode um den TITEL eines Fragmentes nach seiner Position zu erhalten
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}