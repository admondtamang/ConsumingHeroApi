package com.softwarica.herosandvillan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class loadImgStrict extends AppCompatActivity {
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_img_strict);
        imgPhoto=findViewById(R.id.imgPhoto);
        loadFormUrl();
    }

    private void loadFormUrl() {
        strictMode();
        try {
            String imgUrl="http://gateway.example.com/loginpages/tmpl_imgs6/login_template/cropped-Softwarica-logo.png";
            URL url=new URL(imgUrl);
            imgPhoto.setImageBitmap(BitmapFactory.decodeStream((InputStream)url.getContent()));
        }
        catch (IOException e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void strictMode() {
        android.os.StrictMode.ThreadPolicy policy=new android.os.StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }
}
