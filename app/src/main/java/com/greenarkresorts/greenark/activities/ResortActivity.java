package com.greenarkresorts.greenark.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.greenarkresorts.greenark.R;
import org.joda.time.DateTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ResortActivity extends AppCompatActivity implements View.OnClickListener {

    private Calendar calendar;
    private TextView dateTextField;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort);
        DateTime dateTime = new DateTime();
        df = new SimpleDateFormat("dd MMM, yyyy");
        String formattedDate = df.format(dateTime.toDate());

        calendar = Calendar.getInstance();

        dateTextField = (TextView) findViewById(R.id.dateTextField);
        dateTextField.setTextAppearance(this, android.R.style.TextAppearance_Large);
        dateTextField.setTextColor(getResources().getColor(R.color.dateTextColor));
        dateTextField.setText(formattedDate);

        setDateTimeField();
    }

    private void setDateTimeField() {
        dateTextField.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateTextField.setText(df.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == dateTextField) {
            datePickerDialog.show();
        }
    }

    public void villaClicked(View view) {
        Intent intent = new Intent(this, VillaDetailActivity.class);
        ImageButton superiorRoomImageButton = (ImageButton) findViewById(R.id.superiorRoomImageButton),
                superiorVillaImageButton = (ImageButton) findViewById(R.id.superiorVillaImageButton),
                doubleDeluxeImageButton = (ImageButton) findViewById(R.id.deluxeDoubleBedroomImageButton),
                stoneVillaImageButton = (ImageButton) findViewById(R.id.stoneVillaImageButton),
                woodHouseImageButton = (ImageButton) findViewById(R.id.woodHouseImageButton),
                bricksVillaImageButton = (ImageButton) findViewById(R.id.brickVillaImageButton);
        String date = (String) dateTextField.getText();
        Bundle bundle = new Bundle();
        bundle.putString("date", date);
        if (view == superiorRoomImageButton) {
            bundle.putString("name", "Superior Room");
        } else if (view == superiorVillaImageButton) {
            bundle.putString("name", "Superior Villa");
        } else if (view == doubleDeluxeImageButton) {
            bundle.putString("name", "Double Deluxe Room");
        } else if (view == stoneVillaImageButton) {
            bundle.putString("name", "Stone Villa");
        } else if (view == woodHouseImageButton) {
            bundle.putString("name", "Wood House");
        } else if (view == bricksVillaImageButton) {
            bundle.putString("name", "Bricks Villa");
        }
        intent.putExtra("bundle", bundle);
        this.startActivity(intent);
    }
}
