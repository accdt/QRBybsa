package com.example.pablo.qrbybsa;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenAnterior = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = findViewById(R.id.camera_view);
        initQR();
    }

    public void initQR() {

        //Creacion del lector
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this).
                        setBarcodeFormats(Barcode.ALL_FORMATS).build();

        //Creacion de la camara
        cameraSource = new CameraSource.Builder(this, barcodeDetector).
                setRequestedPreviewSize(1600, 1024).
                setAutoFocusEnabled(true).build();

        //Ciclo de vida de la camara
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_CAMERA);}
                    }
                } else {
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        //Preparacion del detector
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() > 0) {
                    //token del lector que va a asignar el equipo y enviar los datos por correo
                    token = barcodes.valueAt(0).displayValue;

                    if (!token.equals(tokenAnterior)) {
                        tokenAnterior = token;
                        Log.i("token", token);

                        switch (token) {

                            case "0024": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 0024");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                            case "1150": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 0025");
                                intent.putExtra("body", "TB/MTS-5800 V2 DUAL 10G CON PDH    WMME0084240024\n" +
                                        "JSH 42L4DDI    SE3051610029\n" +
                                        "SPF 2.5G OC-48, 1310NM   FE285562009F\n" +
                                        "FTLX1472M3BCL    US31A6H\n" +
                                        "FTLF8528P2BCV-S1   PNL2A60\n" +
                                        "FTLX1472M3BCL  US30712\n" +
                                        "AC/DC POWER ADAPTER    11-14040031-00931\n");
                                startActivity(intent);
                            }
                            break;

                            case "1907": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 1907");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                            case "115": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 1150");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                            case "1151": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 1151");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                            case "2504": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 2504");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                            case "30371": {
                                Intent intent = new Intent(Intent.ACTION_SENDTO);
                                Uri uri = Uri.parse("mailto:pablo_perez@bybsatelecomunicaciones.com");
                                intent.setData(uri);
                                intent.putExtra("subject", "Salida de analizador 30371");
                                intent.putExtra("body", "Prueba");
                                startActivity(intent);
                            }
                            break;

                        }

                        //apertura del navegador
                        /*if(URLUtil.isValidUrl(token)) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(token));
                            startActivity(browserIntent);
                        } else {
                            // compartir en otras apps
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, token);
                            shareIntent.setType("text/plain");
                            startActivity(shareIntent);
                        }*/

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(500);
                                        //elimina el token anterior
                                        tokenAnterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    Log.e("Error", "Esperar no funciono!!");
                                    e.printStackTrace();
                                }

                            }
                        }).start();
                    }
                }
            }
        });


    }
}