package com.example.justjava3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int noOfCoffee = 0;
    int costOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //submit order
    public void submitOrder(View view) {
        //TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        //int totalPrice = noOfCoffee * costOfCoffee;
        //priceTextView.setText("Total: $" + totalPrice + "\n" + "Thank You!");

        //1. Get User's Name
        //Create An Object EditText
        EditText nameText = (EditText) findViewById(R.id.name_field);
        String name = nameText.getText().toString();

        //2. Check If The User Wants Whipped Cream Or Chocolate
        CheckBox wCreamCB = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = wCreamCB.isChecked();

        CheckBox cCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = cCheckBox.isChecked();

        //3. Calculate The Price Of The Coffee
        int price = calculatePrice(hasWhippedCream,hasChocolate);

        //4. Create A Message That Can Be Send As An Intent
        String message = createOrderSummary(name,hasWhippedCream,hasChocolate,noOfCoffee,price);

        //5. Start A New Intent And Pass The Message To The New Activity
        Intent intent = new Intent(this,DisplayOrderDetails.class);
        intent.putExtra("name",name);
        intent.putExtra("message",message);
        intent.putExtra("totalPrice",Integer.toString(price));
        startActivity(intent);
    }

    //Method To Calculate The Price Of Coffee
    public int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        //Price Of A Coffee
        if (addWhippedCream == true){
            costOfCoffee = costOfCoffee+1;
        }
        if (addChocolate == true){
            costOfCoffee = costOfCoffee+2;
        }
        return (noOfCoffee*costOfCoffee);
    }

    public String createOrderSummary(String name, boolean addWhippedCream, boolean addChocolate, int nof, int price) {
        String priceMessage = "Name: "+name+"\n"+
                "Add Whipped Cream? "+addWhippedCream+"\n"+
                "Add Chocolate? "+addChocolate+"\n"+
                "Quantity: "+nof+"\n"+
                "Price: $"+price+"\n"+
                "Thank You!";
        return priceMessage;
    }

    //return Quantity
    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void plusCoffee(View view) {
        noOfCoffee++;
        if ((noOfCoffee < 0) || (noOfCoffee > 15))
            noOfCoffee = 0;
        display(noOfCoffee);
    }

    public void minusCoffee(View view) {
        noOfCoffee--;
        if ((noOfCoffee < 0) || (noOfCoffee > 15))
            noOfCoffee = 0;
        display(noOfCoffee);
    }

}