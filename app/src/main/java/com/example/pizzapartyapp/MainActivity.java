package com.example.pizzapartyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int slices_per_pizza = 8;
    private final int small_pizza_cost = 10;
    private final int medium_pizza_cost = 15;
    private final int large_pizza_cost = 20;



    private EditText NoOfPeople;
    private TextView NoOfPizzas;
    private EditText orderName;
    private RadioGroup mHowHungryRadioGroup;
    private RadioGroup pizzaSizeRadioGroup;
    private Button calculateButton;
    private Button clearButton;
    private TextView moneyCost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NoOfPeople = findViewById(R.id.NumberOfPeople);
        NoOfPizzas = findViewById(R.id.totalPizzas);
        mHowHungryRadioGroup = findViewById(R.id.hungry_radio_group);
        calculateButton = findViewById(R.id.calculate_button);
        pizzaSizeRadioGroup = findViewById(R.id.size_radio_group);
        orderName = findViewById(R.id.order_name);
        clearButton = findViewById(R.id.clear_button);
        moneyCost = findViewById(R.id.cost);


        calculateButton.setOnClickListener(v -> {
            if(orderName.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please provide order name", Toast.LENGTH_SHORT).show();
            }
            else if(NoOfPeople.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Please total number of people in the party", Toast.LENGTH_SHORT).show();
            }
            else{calculateClick();}

        });

        clearButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                NoOfPeople.getText().clear();
                orderName.getText().clear();
                pizzaSizeRadioGroup.clearCheck();
                mHowHungryRadioGroup.clearCheck();
                NoOfPizzas.setText("Total Pizzas : ?");
                moneyCost.setText("Total Cost : 0$");

            }
        });







    }

    public void calculateClick(){
        int TotalPeople = Integer.parseInt(NoOfPeople.getText().toString());

        int slicesPerPerson = 0;
        int checkedId = mHowHungryRadioGroup.getCheckedRadioButtonId();
        if(checkedId == R.id.light_radio_button){
            slicesPerPerson = 2;
        }
        else if (checkedId == R.id.medium_radio_button){
            slicesPerPerson = 3;
        }
        else if (checkedId == R.id.ravenous_radio_button){
            slicesPerPerson = 4;
        }
        int totalPizzas = (int) Math.ceil(TotalPeople * slicesPerPerson / (double) slices_per_pizza);

        String pizza_size = "";
        int costValue = 0;
        int checkedIdSize = pizzaSizeRadioGroup.getCheckedRadioButtonId();
        if(checkedIdSize == R.id.small_radio_button){
            pizza_size = "small";
            costValue = small_pizza_cost* totalPizzas;
        }
        else if (checkedIdSize == R.id.medium_pizza_radio_button){
            pizza_size = "medium";
            costValue = medium_pizza_cost* totalPizzas;
        }
        else if (checkedIdSize == R.id.large_radio_button){
            pizza_size = "large";
            costValue = large_pizza_cost* totalPizzas;
        }

        String name = orderName.getText().toString();




        NoOfPizzas.setText("Total Pizzas: " + totalPizzas+ " " + pizza_size + " pizzas for " + name);
        moneyCost.setText("Total Price :"+String.valueOf(costValue)+"$");






    }

}