package com.example.smarthomegcapp;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class Screen3 extends AppCompatActivity {
    private Uri vPath;
    int cnt = 0, CAMERA_REQUEST = 1;
    String gid, fn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s3);
        Bundle bd = getIntent().getExtras();
        gid = bd.getString("gid");
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, setUri());
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.CAMERA}, CAMERA_REQUEST);
        }
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private Uri setUri(){
        fn = gid + "_PRACTICE_" + cnt + ".mp4";
        String s = this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + "/" + fn;
        return FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", new File(s));
    }
    public void retakeOnClick(View v){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, setUri());
        startActivityForResult(intent, CAMERA_REQUEST);
    }
    public void uploadOnClick(View v){
        up();
        Toast.makeText(this, "Uploaded.", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            vPath = intent.getData();
            VideoView vv = (VideoView) findViewById(R.id.preview);
            vv.setVideoURI(vPath);
            vv.start();
        }
    }
    public void up(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                File fi = new File("/storage/emulated/0/Android/data/com.example.smarthomegcapp/files/DCIM/" + fn);
                String url = "http://192.168.68.50:5000";
                OkHttpClient clie = new OkHttpClient();
                RequestBody formB = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", fi.getName(), RequestBody.create(MediaType.parse("video/mp4"), fi))
                        .build();
                Request requ = new Request.Builder().url(url).post(formB).build();
                try {
                    Response response = clie.newCall(requ).execute();
                }catch(Exception ex){}
            }
        }).start();
    }
}