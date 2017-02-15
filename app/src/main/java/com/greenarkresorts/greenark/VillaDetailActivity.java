package com.greenarkresorts.greenark;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class VillaDetailActivity extends AppCompatActivity {
    private Intent intent = null;
    private Bundle bundle = new Bundle();
    private String[] detailListKey = {"Name", "Agent", "Amount", "Advance",
            "No. of People", "Extra Bed", "Date Booked", "Date Advance Paid", "Date of Balance Pay", "Plan Type"};
    private String[] detailListValue = {"Jose", "MMT", "2800", "2000",
            "2", "0", "03 Feb, 2017", "03 Feb, 2017", "28 Feb, 2017", "CP"};

    public void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.villa_detail_layout);
        bundle = intent.getBundleExtra("bundle");

        TextView villaTextView = (TextView) findViewById(R.id.villaNameTextView);
        villaTextView.setText(bundle.getString("name"));
        createTableLayout();
    }

    private void createTableLayout() {
        TextView constantTextView, variableTextView;
        TableRow row;
        TableLayout.LayoutParams tableLParams = new TableLayout.LayoutParams();
        tableLParams.height = 0;
        tableLParams.weight = 8;
        tableLParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        tableLParams.gravity = Gravity.CENTER;

        TableLayout villaDetailTableLayout = (TableLayout) findViewById(R.id.villaDetailTableLayout);
        villaDetailTableLayout.setLayoutParams(tableLParams);
        TableLayout.LayoutParams rowLParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams constantTVLParams = new TableRow.LayoutParams(1);
//        int width = TableRow.LayoutParams.WRAP_CONTENT + 10;
//        int widthInDP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources().getDisplayMetrics());
//        constantTVLParams.width = widthInDP;


        for (int index = 0; index < detailListKey.length; index++) {
            constantTextView = new TextView(this);
            constantTextView.setText(detailListKey[index]);
            constantTextView.setGravity(Gravity.START);
            constantTextView.setTextColor(Color.BLACK);
            constantTextView.setLayoutParams(constantTVLParams);

            variableTextView = new TextView(this);
            variableTextView.setText(detailListValue[index]);
            variableTextView.setPadding(3, 3, 3, 3);
            variableTextView.setGravity(Gravity.START);
            variableTextView.setTextColor(Color.BLACK);
            variableTextView.setLayoutParams(new TableRow.LayoutParams(2));

            row = new TableRow(this);
            row.addView(constantTextView);
            row.addView(variableTextView);
            row.setLayoutParams(rowLParams);
            villaDetailTableLayout.addView(row);
        }
    }
}
