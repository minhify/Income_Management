package com.minhify.lab13;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    EditText txtname, txtamount;
    DatePicker datePicker;
    TimePicker timePicker;
    RadioButton rdincome, rdspending;
    Button btnsave, btnclear;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    Activities activityInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.main_color));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        txtname = findViewById(R.id.txtEnterName);
        txtamount = findViewById(R.id.txtAmount);
        datePicker = findViewById(R.id.txtDate);
        timePicker = findViewById(R.id.txtTime);
        rdincome = findViewById(R.id.rdIncome);
        rdspending = findViewById(R.id.rdSpending);
        btnsave = findViewById(R.id.btnSave);
        btnclear = findViewById(R.id.btnClear);
        timePicker.setIs24HourView(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Activities");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(txtname.getText())){
                    Toast.makeText(getApplicationContext(), "Enter activity name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(txtamount.getText())){
                    Toast.makeText(getApplicationContext(), "Enter amount", Toast.LENGTH_SHORT).show();
                    return;
                }
                insertActivityData();
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertActivityData(){
        String name = txtname.getText().toString();
        int type = 0;
        if(rdincome.isChecked()) type = 1;
        else if(rdspending.isChecked()) type = 2;
        else type = 1;
        int date = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();
        String stdate, stmonth, styear;
        if (date < 10)
            stdate = "0"+String.valueOf(date);
        else stdate = String.valueOf(date);
        if (month < 10)
            stmonth = "0"+String.valueOf(month);
        else stmonth = String.valueOf(month);
        styear = String.valueOf(year);
        String noteDate = stdate+"/" +stmonth + "/"+ styear;
        int hour = 0, minute = 0;
        String sthour, stminute;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        }
        if (hour < 10)
            sthour = "0"+String.valueOf(hour);
        else sthour = String.valueOf(hour);
        if (minute < 10)
            stminute = "0" +String.valueOf(minute);
        else stminute = String.valueOf(minute);
        String noteTime = sthour + ":" + stminute;

        int amount = Integer.parseInt(txtamount.getText().toString());

        Activities act = new Activities(name,type,noteDate, noteTime,amount, user.getEmail());
        databaseReference.push().setValue(act);
        Toast.makeText(getApplicationContext(), "Activity inserted", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }
}