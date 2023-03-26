package com.example.sportifyadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class AdminHome extends AppCompatActivity {

    private CodeScannerView codeScannerView;
    private CodeScanner codeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        codeScannerView = findViewById(R.id.QRScanner);

//        if(!permission())
//            reqPermission();

        codeScanner = new CodeScanner(AdminHome.this, codeScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AdminHome.this, result.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });

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

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();

    }

    @Override
    protected void onResume() {

        super.onResume();
        codeScanner.startPreview();
    }
}