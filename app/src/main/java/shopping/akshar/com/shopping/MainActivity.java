package shopping.akshar.com.shopping;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import shopping.akshar.com.shopping.activity.AuthenticationActivity;
import shopping.akshar.com.shopping.activity.LoginActivity;
import shopping.akshar.com.shopping.activity.RegisterActivity;
import shopping.akshar.com.shopping.fragment.CartFragment;
import shopping.akshar.com.shopping.fragment.HomeFragment;
import shopping.akshar.com.shopping.fragment.IncrementFragment;
import shopping.akshar.com.shopping.fragment.ProfileFragment;
import shopping.akshar.com.shopping.utlis.PreferenseManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView username;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    SharedPreferences sharedPreferences;


    private static final String PREF_NAME = "Shopping";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_USER_ID = "User UID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_main,new HomeFragment());
        ft.commit();

        username = findViewById(R.id.username);
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        sharedPreferences = getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.search_view:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                break;

            case R.id.notification:
                Toast.makeText(this,"Message",Toast.LENGTH_SHORT).show();
                break;



                default:
                    break;
        }



        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.logout){
            /*Logout code*/

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(KEY_EMAIL);
            Log.d("LoggedOut",KEY_EMAIL);
            editor.remove(KEY_USER_ID);
            Log.d("Key",firebaseUser.getUid());
            editor.apply();

            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.increment){
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main,new IncrementFragment());
            ft.addToBackStack(null);
            ft.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main,new HomeFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_profile) {
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main,new ProfileFragment());
            ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.nav_cart){
            android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frame_main,new CartFragment());
            ft.addToBackStack(null);
            ft.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
