package com.example.absensi.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.absensi.R;
import com.example.absensi.model.Users;
import com.example.absensi.session.SystemDataLocal;
import com.example.absensi.ui.fragment.HomeFragment;
import com.example.absensi.ui.fragment.PresensiFragment;
import com.example.absensi.ui.fragment.ProfileFragment;
import com.example.absensi.ui.fragment.ReportFragment;
import com.example.absensi.ui.login.LoginActivity;
import com.example.absensi.ui.profile.activity.SettingActivity;
import com.example.absensi.ui.scanner.ScannerActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private SystemDataLocal systemDataLocal;
    final Fragment fragmentHome = new HomeFragment();
    final Fragment fragmentPresensi = new PresensiFragment();
    final Fragment fragmentProfile = new ProfileFragment();
    final Fragment fragmentReport = new ReportFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragmentHome;
    BottomNavigationView navigationView;
    FrameLayout containerFragment;
    TextView title;
    ImageView iv_logout,logout;

    private long getTime() throws Exception {
        String url = "https://time.is/Unix_time_now";
        Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        String[] tags = new String[] {
                "div[id=time_section]",
                "div[id=clock0_bg]"
        };
        Elements elements= doc.select(tags[0]);
        for (int i = 0; i <tags.length; i++) {
            elements = elements.select(tags[i]);
        }
        Long time = Long.parseLong(elements.text());
        Toast.makeText(getApplicationContext(),String.valueOf(time),Toast.LENGTH_LONG).show();
        return Long.parseLong(elements.text());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        systemDataLocal = new SystemDataLocal(this);
        navigationView = findViewById(R.id.nav_view);
        containerFragment = findViewById(R.id.containerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        title = findViewById(R.id.title);
        iv_logout = findViewById(R.id.iv_logout);
        logout = findViewById(R.id.logout);
        logout.setVisibility(View.VISIBLE);
        Users users = systemDataLocal.getLoginData();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setElevation(0);
        }
        iv_logout.setOnClickListener(this);
        logout.setOnClickListener(this);
        try {
            getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(savedInstanceState != null){
            switch (savedInstanceState.getInt("fragState")){
                case R.id.nav_home:
                    active = fragmentHome;

                    fm.beginTransaction().add(R.id.containerView,fragmentPresensi,fragmentPresensi.getClass().getSimpleName()).hide(fragmentPresensi).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();

                    break;

                case R.id.nav_presensi:
                    active = fragmentPresensi;
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    break;

                case  R.id.nav_laporan:
                    active = fragmentReport;
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentPresensi,fragmentPresensi.getClass().getSimpleName()).hide(fragmentPresensi).commit();
                    break;

                case R.id.nav_profile:
                    active = fragmentProfile;
                    fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).hide(fragmentHome).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
                    fm.beginTransaction().add(R.id.containerView,fragmentPresensi,fragmentPresensi.getClass().getSimpleName()).hide(fragmentPresensi).commit();
                    break;

            }
            fm.beginTransaction().add(R.id.containerView,active,active.getClass().getSimpleName()).commit();
        }else{
            fm.beginTransaction().add(R.id.containerView,fragmentProfile,fragmentProfile.getClass().getSimpleName()).hide(fragmentProfile).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentReport,fragmentReport.getClass().getSimpleName()).hide(fragmentReport).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentPresensi,fragmentPresensi.getClass().getSimpleName()).hide(fragmentPresensi).commit();
            fm.beginTransaction().add(R.id.containerView,fragmentHome,fragmentHome.getClass().getSimpleName()).commit();
        }
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fm.beginTransaction().hide(active).show(fragmentHome).commit();
                        active = fragmentHome;
                        title.setText("Home");
                        iv_logout.setVisibility(View.GONE);


                        return true;

                    case R.id.nav_presensi:
                        fm.beginTransaction().hide(active).show(fragmentPresensi).commit();
                        active = fragmentPresensi;
                        title.setText("Data Presensi");
                        iv_logout.setVisibility(View.GONE);

                        return true;

                    case R.id.nav_profile:
                        fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                        active = fragmentProfile;
                        title.setText("Profile");
                        iv_logout.setVisibility(View.VISIBLE);


                        return true;

                    case R.id.nav_laporan:
                        fm.beginTransaction().hide(active).show(fragmentReport).commit();
                        active = fragmentReport;
                        title.setText("Laporan");
                        iv_logout.setVisibility(View.GONE);

                        return true;

                    case R.id.nav_absensi:
                        startActivity(new Intent(HomeActivity.this, ScannerActivity.class));
                        return  true;

                    default:return false;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        outState.putInt("fragState",navigationView.getSelectedItemId());
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.iv_logout){
           startActivity(new Intent(HomeActivity.this, SettingActivity.class));
        }else if(v.getId() == R.id.logout){
            systemDataLocal.destroySessionLogin();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}