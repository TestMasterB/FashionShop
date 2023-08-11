package com.example.shoptrue.loginModule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shoptrue.MainActivity;
import com.example.shoptrue.database.DatabaseInstance;
import com.example.shoptrue.database.LoginTable;
import com.example.shoptrue.databinding.ActivityLoginScreenBinding;


public class LoginScreen extends AppCompatActivity {


    // ActivityLoginScreenBinding binding = autogenerierte Klasse, die auf die Elemente der xml der activity_login_screen zugreift
    ActivityLoginScreenBinding binding;
    String username = "";
    String password = "";
    DatabaseInstance instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        instance = DatabaseInstance.getInstance(this);

        // Beginn der Login - Button Funktionalität
        // CheckCredentials(username, password) überpüft, ob AnmeldeInformationen in der Datenbank vorhanden sind

        binding.loginLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.loginUsernameEdtxt.getText().toString();
                password = binding.loginPasswordEdtxt.getText().toString();
                if (!username.trim().equals("")) {
                    if(CheckCredentials(username, password)){
                        Toast.makeText(LoginScreen.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent inm = new Intent(LoginScreen.this, MainActivity.class);
                        startActivity(inm);
                    }else {
                        Toast.makeText(LoginScreen.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                } else if (username.trim().isEmpty()) {
                    Toast.makeText(LoginScreen.this, "Please Write Username!", Toast.LENGTH_SHORT).show();
                } else if (password.trim().isEmpty()) {
                    Toast.makeText(LoginScreen.this, "Please Write Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Wenn CREATE Button gedrückt wird, wird CreateUsername aktiviert
        binding.loginCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inm = new Intent(LoginScreen.this, CreateUsername.class);
                startActivity(inm);
            }
        });



    }

    // private = boolean überprüft nur innerhalb der Klasse LoginScreen
    // boolean CheckCredentials - überprüft, ob die Informationen in der DB TRUE sind

    private Boolean CheckCredentials(String username, String password) {
        LoginTable model = instance.databaseDao().getLoginDetails(username);
        return model != null;
    }
}
