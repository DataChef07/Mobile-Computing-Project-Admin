package com.example.sportifyadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.Result;

public class AdminHome extends AppCompatActivity {

    //    private CodeScannerView codeScannerView;
//    private CodeScanner codeScanner;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);

//        getSupportFragmentManager().beginTransaction().replace(R.id.Container, new ScannerFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int nav_id = item.getItemId();
                if(nav_id == R.id.nav_scanner)
                {
                    // load scanner fragment
                    loadFragment(new ScannerFragment());
                } else if (nav_id == R.id.nav_registry) {
                    // load registry fragment
                    loadFragment(new RegistryFragment());
                } else if (nav_id == R.id.nav_complaints) {
                    //load complaints fragment
                    loadFragment(new ComplaintsFragment());
                }else {
                    // load equipment fragment
                    loadFragment(new EquipmentFragment());
                }
                return true;
            }
        });
        // by default we are loading registry fragment
        bottomNavigationView.setSelectedItemId(R.id.nav_registry);

    }

    public void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.Container, fragment);
        ft.commit();
    }

//    private boolean permission(){
//        int camera = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA_SERVICE);
//        int vibrate = ContextCompat.checkSelfPermission(getApplicationContext(), VIBRATOR_SERVICE);
//        if( camera == PackageManager.PERMISSION_GRANTED && vibrate == PackageManager.PERMISSION_GRANTED)
//            return true;
//        return false;
//    }
//
//    private void reqPermission(){
//        int code = 200;  //permission code
//        ActivityCompat.requestPermissions(AdminHome.this, new String[]{CAMERA_SERVICE, VIBRATOR_SERVICE}, code);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        boolean camAccepted = false;
//        boolean vibAccepted = false;
//        if(grantResults.length >0){
//            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                camAccepted = true;
//            if(grantResults[1] == PackageManager.PERMISSION_GRANTED)
//                vibAccepted = true;
//        }
//
//        if(camAccepted && vibAccepted){
//            Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
//        }
//        else{
//            Toast.makeText(this, "PERMISSION DENIED. Provide all permission to continue", Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    protected void onPause() {
//        codeScanner.releaseResources();
//        super.onPause();
//
//    }
//
//    @Override
//    protected void onResume() {
//
//        super.onResume();
//        codeScanner.startPreview();
//    }
}