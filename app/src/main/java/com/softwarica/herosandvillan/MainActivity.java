package com.softwarica.herosandvillan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.softwarica.herosandvillan.API.HeroesAPI;
import com.softwarica.herosandvillan.Model.Heroes;
import com.softwarica.herosandvillan.URL.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etName,etDesc;
    private Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.etName);
        etDesc=findViewById(R.id.etDesc);
        btnAdd=findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }

            private void save() {
                String name=etName.getText().toString();
                String desc=etDesc.getText().toString();
                Heroes heroes=new Heroes(name,desc);

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Url.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                HeroesAPI heroesAPI=retrofit.create(HeroesAPI.class);
                Call<Void> heroesCall=heroesAPI.addHero(heroes);
                heroesCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "Sucessfully added", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }
}
