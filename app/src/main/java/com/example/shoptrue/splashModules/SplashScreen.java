package com.example.shoptrue.splashModules;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoptrue.R;
import com.example.shoptrue.loginModule.LoginScreen;

/* warum extended: ist ein pre-defined rule, wird automatisch
beim Erstellen einer neuen Datei erstellt */

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

/* handler Funktion wird benutzt, um die Zeit zu bestimmen
es ist die Zeit, in der Screen von einem zu einem anderen
wechseln
 */
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                /* nachdem wir bestimmt haben die Zeit, kommt intent inm ins Spiel
                dort wird bestimmt zu welchem Screen wir uns bewegen sollen
                 */
            Intent inm = new Intent(SplashScreen.this, LoginScreen.class);
            startActivity(inm);

            }
        }, 2000 );

    }


}