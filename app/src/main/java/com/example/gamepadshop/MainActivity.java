package com.example.gamepadshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity=0;
    Spinner spinner;
    ArrayList spinnerList;
    ArrayAdapter spinnerAd;

    HashMap goodsPads;
    String namePad;
    int prices;
    EditText userNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNames = findViewById(R.id.personName);

        createSpinner();
        createMap();

        setTitle("GamePad Shop");
    }

    void createSpinner() {
        spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerList = new ArrayList();

        spinnerList.add("Xbox one");
        spinnerList.add("Xbox 360");
        spinnerList.add("PS5");
        spinnerList.add("PS4");

        spinnerAd = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        spinnerAd.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAd);
    }

    void createMap(){
        goodsPads=new HashMap();
        goodsPads.put("Xbox one",50);
        goodsPads.put("Xbox 360",35);
        goodsPads.put("PS5",100);
        goodsPads.put("PS4",55);
    }


    public void plusClick(View view) {
        quantity=quantity+1;

        if (quantity>10){
            quantity=10;
        }

        TextView quantityText = findViewById(R.id.quantity);
        quantityText.setText("" + quantity);
        TextView priceText=findViewById(R.id.price);
        priceText.setText("" + quantity * prices);
    }

    public void minusClick(View view) {
        quantity=quantity-1;

        if (quantity<0){
            quantity=0;
        }

        TextView quantityText = findViewById(R.id.quantity);
        quantityText.setText("" + quantity);
        TextView priceText=findViewById(R.id.price);
        priceText.setText("" + quantity * prices);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View View, int position, long id){
        namePad=spinner.getSelectedItem().toString();
        prices = (int)goodsPads.get(namePad);
        TextView priceText=findViewById(R.id.price);
        priceText.setText("" + quantity * prices);

        ImageView padImage = findViewById(R.id.padImage);

        switch (namePad){
            case "Xbox one":
                padImage.setImageResource(R.drawable.xgame1);
                break;
            case "Xbox 360":
                padImage.setImageResource(R.drawable.xgame2);
                break;
            case "PS5":
                padImage.setImageResource(R.drawable.sgame1);
                break;
            case "PS4":
                padImage.setImageResource(R.drawable.sgame2);
                break;
            default:
                padImage.setImageResource(R.drawable.xgame1);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){
    }

    public void addToCard(View view) {
        Order order = new Order();

        order.userName = userNames.getText().toString();
        order.padName = namePad;
        order.quantity = quantity;
        order.priceForOne = prices;
        order.priceForAll = quantity * prices;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);

        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("padName", order.padName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("priceForOne", order.priceForOne);
        orderIntent.putExtra("priceForAll", order.priceForAll);

        startActivity(orderIntent);
    }
}