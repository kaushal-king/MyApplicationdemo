package com.example.myapplicationdemo.logindrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplicationdemo.R;
import com.example.myapplicationdemo.logindrawer.ui.gallery.GalleryFragment;
import com.example.myapplicationdemo.logindrawer.ui.home.HomeFragment;
import com.example.myapplicationdemo.logindrawer.ui.send.SendFragment;
import com.example.myapplicationdemo.logindrawer.ui.share.ShareFragment;
import com.example.myapplicationdemo.logindrawer.ui.slideshow.SlideshowFragment;
import com.example.myapplicationdemo.logindrawer.ui.tools.ToolsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class navi extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences p;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String tname="name";
    public static final String mail="mail";
    TextView t1,t2;
    Fragment fragment;
    FragmentManager manager;
    FragmentTransaction transaction;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "kem dabau be??", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        p= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String s1=p.getString(tname,"");
        String s2=p.getString(mail,"");
        View hView = navigationView.getHeaderView(0);
        t1 = (TextView)hView.findViewById(R.id.kname);
        t2 = (TextView)hView.findViewById(R.id.kmail);
        t1.setText(s1);
        t2.setText(s2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(this, "Activity is refresh", Toast.LENGTH_SHORT).show();
            fragment = new HomeFragment();


        } else if (id == R.id.nav_gallery) {
            fragment = new GalleryFragment();


        } else if (id == R.id.nav_slideshow) {
            fragment = new SlideshowFragment();

        } else if (id == R.id.nav_tools) {
            fragment = new ToolsFragment();

        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();

        } else if (id == R.id.nav_send) {
            fragment = new SendFragment();


        }
        transaction = manager.beginTransaction();
        transaction.add(R.id.nav_host_fragment, fragment, "A");
        transaction.addToBackStack("addA");
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
