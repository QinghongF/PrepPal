package com.ui.preppal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity
{


    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    public int dayNum = 1;
    private String time;

    String[] meals = {"Breakfast", "Lunch", "Dinner"};
    String[] days = {"1", "2", "3", "4", "5", "6", "7"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();

        //meal spinner
        Spinner spino = findViewById(R.id.eventTimeSpinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meals);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spino.setAdapter(ad);
        spino.setOnItemSelectedListener(new mealsSpinnerClass());

        // numDaysSpinner
        Spinner spinNum = (Spinner) findViewById(R.id.numDaysSpinner);
        ArrayAdapter<String> numD = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        numD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinNum.setAdapter(numD);
        spinNum.setOnItemSelectedListener(new NumDaysSpinnerClass());


        time = "Breakfast";
        eventDateTV.setText("Start Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        //eventTimeTV.setText("Meal: " + time);
    }



    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        //eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        LocalDate current =  CalendarUtils.selectedDate;
        for(int i = 0; i < dayNum; i++) {
            String eventName = eventNameET.getText().toString();
            Event newEvent = new Event(eventName, current, time);
            Event.eventsList.add(newEvent);
            current = current.plusDays(1);
        }
        finish();
    }

    public void backEventAction(View view)
    {
        setContentView(R.layout.activity_week_view);
        finish();

    }

    class mealsSpinnerClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            // make toastof name of course
            // which is selected in spinner
            Toast.makeText(view.getContext(), meals[i], Toast.LENGTH_LONG).show();
            time = meals[i];

        }


        public void onNothingSelected(AdapterView<?> adapterView) {

        }

    }

    class NumDaysSpinnerClass implements AdapterView.OnItemSelectedListener{

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(view.getContext(), days[i], Toast.LENGTH_LONG).show();
            dayNum = Integer.parseInt(days[i]);

        }

        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }


}