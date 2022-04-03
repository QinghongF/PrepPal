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


import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private String time;

    String[] meals = {"Breakfast", "Lunch", "Dinner"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        initWidgets();
        Spinner spino = findViewById(R.id.eventTimeSpinner);
        spino.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        ArrayAdapter ad = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                meals);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        spino.setAdapter(ad);

        time = "Breakfast";
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
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
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // make toastof name of course
        // which is selected in spinner
        Toast.makeText(getApplicationContext(),
                meals[i],
                Toast.LENGTH_LONG)
                .show();
        time = meals[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}