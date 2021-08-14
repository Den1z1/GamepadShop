package com.example.gamepadshop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order:");

        Intent orderIntent = getIntent();

        String userName = orderIntent.getStringExtra("userName");
        TextView userNameText = findViewById(R.id.userNameText);

        if (userName.isEmpty()){
            userNameText.setText("Hello!");
        } else {
            userNameText.setText("Hello, " + userName + "!");
        }

        String padName = orderIntent.getStringExtra("padName");
        TextView padNameText = findViewById(R.id.padNameText);
        padNameText.setText("Gamepad name: " + padName);

        int quantity = orderIntent.getIntExtra("quantity",0);
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText("Quantity: " + quantity);

        int priceForOne = orderIntent.getIntExtra("priceForOne", 0);
        TextView priceForOneText = findViewById(R.id.priceForOneText);
        priceForOneText.setText("Price for one: " + priceForOne + "$");

        int priceForAll = orderIntent.getIntExtra("priceForAll", 0);
        TextView priceForAllText = findViewById(R.id.priceForAllText);
        priceForAllText .setText("Price for one: " + priceForAll + "$");
    }

    public void notApp(View view) {
        Toast.makeText(this, "The application is still in development((", Toast.LENGTH_SHORT).show();
    }
}