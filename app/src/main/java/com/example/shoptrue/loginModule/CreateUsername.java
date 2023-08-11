package com.example.shoptrue.loginModule;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoptrue.database.DatabaseInstance;
import com.example.shoptrue.database.LoginTable;
import com.example.shoptrue.databinding.ActivityCreateUsernameBinding;


public class CreateUsername extends AppCompatActivity {

    // Ähnlicher Aufbau, wie LoginScreen
    // Zusätzlich findet ein @Insert in die LoginTable um User & Password zu speichern (Zeile 40)

    ActivityCreateUsernameBinding binding;
    DatabaseInstance databaseInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreateUsernameBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());



        databaseInstance=DatabaseInstance.getInstance(this);

        binding.loginCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.loginUsernameEdtxt.getText().toString();
                String password = binding.loginPasswordEdtxt.getText().toString();
                if (!username.trim().equals("")) {
                    databaseInstance.databaseDao().Insert(new LoginTable(username,password));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(CheckCredentials(username, password)){
                                Toast.makeText(CreateUsername.this, "Created Successful", Toast.LENGTH_SHORT).show();
                                Intent inm = new Intent(CreateUsername.this, LoginScreen.class);
                                startActivity(inm);
                            }else {
                                Toast.makeText(CreateUsername.this, "Created Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },200);



                } else if (username.isEmpty()) {
                    Toast.makeText(CreateUsername.this, "Please Write Username!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(CreateUsername.this, "Please Write Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean CheckCredentials(String username, String password) {
        LoginTable model = databaseInstance.databaseDao().getLoginDetails(username);
        return model != null;
    }
}
