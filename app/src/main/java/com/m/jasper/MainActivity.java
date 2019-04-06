package com.m.jasper;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.m.jasper.NavActivities.ContactUs;
import com.m.jasper.NavActivities.Home.Home;
import com.m.jasper.NavActivities.MessMenu.MessMenu;
import com.m.jasper.NavActivities.Settings;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
FirebaseAuth firebaseAuth;
    ImageView imageView;
    TextView userName;
    TextView email;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        /*tabLayout=findViewById(R.id.tab_layout);*/
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        View view=navigationView.getHeaderView(0);
        imageView=view.findViewById(R.id.imageView);
        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imageView);
        userName=view.findViewById(R.id.user);
        userName.setText(firebaseUser.getDisplayName());
        email=view.findViewById(R.id.email);
        email.setText(firebaseUser.getEmail());

        startNewTask(new Home());

        
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            startNewTask(new Home());
            // Handle the camera action
        } else if (id == R.id.nav_mess_menu) {
            startNewTask(new MessMenu());
        } else if (id == R.id.nav_settings) {
            startNewTask(new Settings());
        } else if (id == R.id.nav_contact_us) {
            startNewTask(new ContactUs());
        }else if(id==R.id.sign_out)
        {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        }else if (id==R.id.nav_contact)
        {
            CustomTabsIntent.Builder builder=new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent=builder.build();
            customTabsIntent.launchUrl(this, Uri.parse("https://drive.google.com/file/d/1mqRLvSgtOHaVtI1EXcGesXcyswVnP_uq/view"));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void startNewTask(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.navfrag_container, fragment).commit();
    }
}
