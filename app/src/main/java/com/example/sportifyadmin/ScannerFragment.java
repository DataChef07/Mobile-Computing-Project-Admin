package com.example.sportifyadmin;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScannerFragment extends Fragment {
    private CodeScannerView codeScannerView;
    private CodeScanner codeScanner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        codeScannerView = view.findViewById(R.id.QRScanner);


//        if(!permission())
//            reqPermission();
        Handler mHandler = new Handler(Looper.getMainLooper());

        codeScanner = new CodeScanner(getContext(), codeScannerView);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {


                        Toast.makeText(getContext(), result.getText(), Toast.LENGTH_SHORT).show();
                        Dialog dialog=new Dialog(getContext());
                        dialog.setContentView(R.layout.camera_dialogue);
                        TextView t1,t2,t3;
                        String s1= result.getText();
                        t1=dialog.findViewById(R.id.email);
                        t2=dialog.findViewById(R.id.sport);
                        t3=dialog.findViewById(R.id.time);
                        String [] s2=s1.split("$");
                        t1.setText(s2[0]);
//                        t2.setText(s2[1]);
//                        t3.setText(s2[2]);



                        dialog.show();
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

    @Override
    public void onPause() {
        codeScanner.releaseResources();
        super.onPause();

    }

    @Override
    public void onResume() {

        super.onResume();
        codeScanner.startPreview();
    }
}