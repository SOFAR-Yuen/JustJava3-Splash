package com.example.justjava3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayOrderDetails extends AppCompatActivity {

    String message;
    String name;
    String totalPrice;
    CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new CoffeeDBHandler(this, null, null, 1);
        //Get The Intent From The MainActivity And Extract The String
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        totalPrice = intent.getStringExtra("totalPrice");

        //Caputring The TextView And Set As A String
        TextView tv = (TextView) findViewById(R.id.message);
        tv.setText(message);
    }

    //Method To Savs Data To SQLite Database
    public void addButtonClicked(View view){
        //Start The New Intent Here
        Order order = new Order(name,Integer.parseInt(totalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(),"Data Saved!",Toast.LENGTH_SHORT).show();
    }

    //Create The Method Will Send The Message Off To Gmail
    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order For "+name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //Method To Display The Details From The DB To A New Activity
    public void salesReport(View view){
        //Read The Details From The Database To Produce The Report
        String dbString = dbHandler.databaseToString();
        //Start The New Intent Here
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }
}