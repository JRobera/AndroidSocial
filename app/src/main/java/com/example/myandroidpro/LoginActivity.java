package com.example.myandroidpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private JsonData jsonData;
    private EditText login_email, login_password;
    private Button login;
    private TextView create_account;

    private static final String SHARED_PREFS =  "sharedPrefs";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private String saved_email, saved_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_btn);
        create_account = findViewById(R.id.new_account);

//TODO retrofit instance to connect to the API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonData = retrofit.create(JsonData.class);

    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            checkUser();
            // save user preferences
            saveData();
        }
    });

    create_account.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    });

    loadData();
    updateViews();

    }
    public void checkUser(){
        Call<List<UserModel>> call = jsonData.getUsers();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<UserModel> users = response.body();

                for(UserModel user : users){
                    if(!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()){
                        Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }else{
                        if(login_email.getText().toString().equals(user.getUser_email()) && login_password.getText().toString().equals(user.getPassword())){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                        }else {
                            Toast.makeText(LoginActivity.this, "User does not exist "+login_email.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, login_email.getText().toString());
        editor.putString(PASSWORD, login_password.getText().toString());
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }
    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        saved_email= sharedPreferences.getString(EMAIL,"");
        saved_password = sharedPreferences.getString(PASSWORD, "");
    }
    public void updateViews(){
        login_email.setText(saved_email);
        login_password.setText(saved_password);
        checkUser();
    }
}