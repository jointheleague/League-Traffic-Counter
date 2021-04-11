package org.jointheleague.traffic;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Monitor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraManager cm = (CameraManager) getBaseContext().getSystemService(Context.CAMERA_SERVICE);
        try {
            cm.openCamera("0", new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    Log.d("camera", "Opened");
                }

                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {
                    Log.d("camera", "Disconnected");
                    System.exit(0);
                }

                @Override
                public void onError(@NonNull CameraDevice camera, int error) {
                    Log.d("camera", "Error: " + error);
                    System.exit(0);
                }
            }, new Handler());
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}