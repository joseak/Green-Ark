package com.greenarkresorts.greenark.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.greenarkresorts.greenark.R;
import com.greenarkresorts.greenark.adapters.DetailListAdapter;
import com.greenarkresorts.greenark.models.DetailModel;

import java.util.ArrayList;

public class VillaDetailActivity extends AppCompatActivity {
    private Intent intent = null;
    private Bundle bundle = new Bundle();
    private Resources resources;

    public void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        resources = getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.villa_detail_layout);
        bundle = intent.getBundleExtra("bundle");

        TextView villaTextView = (TextView) findViewById(R.id.villaNameTextView);
        villaTextView.setText(bundle.getString("name"));
        villaTextView.setTextColor(Color.BLACK);
        createDetailListView();
    }

    private ArrayList<DetailModel> createList() {
        ArrayList<DetailModel> detailModelArrayList = new ArrayList<>();
        detailModelArrayList.add(new DetailModel("Name", "Jose"));
        detailModelArrayList.add(new DetailModel("Agent", "MMT"));
        detailModelArrayList.add(new DetailModel("Amount", "2800"));
        detailModelArrayList.add(new DetailModel("Advance", "2000"));
        detailModelArrayList.add(new DetailModel("No. of People", "2"));
        detailModelArrayList.add(new DetailModel("Extra Bed", "0"));
        detailModelArrayList.add(new DetailModel("Date Booked", "03 Feb, 2017"));
        detailModelArrayList.add(new DetailModel("Date Advance Paid", "03 Feb, 2017"));
        detailModelArrayList.add(new DetailModel("Date of Balance Pay", "28 Feb, 2017"));
        detailModelArrayList.add(new DetailModel("Plan Type", "CP"));

        return detailModelArrayList;
    }

    private void alertBoxCreator(String key) {
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edit_text_layout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextValue);
        TextView headerTextView = (TextView) promptsView.findViewById(R.id.editTextHeader);
        String header = String.format(resources.getString(R.string.edit_text), key);
        headerTextView.setText(header);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                Toast.makeText(getBaseContext(), userInput.getText(), Toast.LENGTH_SHORT).show();                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void createDetailListView() {
        final DetailListAdapter detailListAdapter = new DetailListAdapter(this, createList());
        ListView listView = (ListView) findViewById(R.id.villaDetailListView);
        listView.setAdapter(detailListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String positionStg = String.valueOf(position);
                Toast.makeText(getBaseContext(), positionStg, Toast.LENGTH_SHORT).show();
                DetailModel detailModel = (DetailModel) detailListAdapter.getItem(position);
                alertBoxCreator(detailModel.getKey());
            }
        });
    }
}
