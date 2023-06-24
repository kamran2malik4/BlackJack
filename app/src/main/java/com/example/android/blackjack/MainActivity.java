package com.example.android.blackjack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView playGame = findViewById(R.id.start_play_game);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlayGameActivity.class);
                startActivity(intent);
            }
        });

        TextView howToPlay = findViewById(R.id.how_to_play);
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlertBrowser();
            }
        });

        TextView quitGame = findViewById(R.id.quit_game);
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAlertQuit();
            }
        });

    }

    private void displayAlertBrowser(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Open Browser");
        alert.setMessage("This will open link to Wikipedia!");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String url = "https://en.wikipedia.org/wiki/Blackjack";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

    private void displayAlertQuit(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quit Game");
        alert.setMessage("Do you want to quit?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.show();
    }

}
