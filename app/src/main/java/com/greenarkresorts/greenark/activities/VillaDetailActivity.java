package com.greenarkresorts.greenark.activities;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.greenarkresorts.greenark.R;
import com.greenarkresorts.greenark.activities.helpers.VillaDetailHelper;
import com.greenarkresorts.greenark.adapters.DetailListAdapter;
import com.greenarkresorts.greenark.models.DetailModel;
import com.greenarkresorts.greenark.interfaces.EditTextDialogListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class VillaDetailActivity extends AppCompatActivity {
    private Resources resources;
    private SimpleDateFormat df = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);
    VillaDetailHelper villaDetailHelper;

    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        resources = getResources();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.villa_detail_layout);

        Toolbar actionToolbar = (Toolbar) findViewById(R.id.villaDetailToolbar);
        String villaName = bundle.getString("name");
        actionToolbar.setTitle(villaName);
        String dateOfStay = bundle.getString("dateOfStay");
        setSupportActionBar(actionToolbar);
        villaDetailHelper = new VillaDetailHelper(dateOfStay, villaName, this);
        createDetailListView();
    }

    private void alertBoxCreator(final int position, final DetailListAdapter detailListAdapter) {
        final DetailModel detailModel = (DetailModel) detailListAdapter.getItem(position);
        LayoutInflater li = LayoutInflater.from(VillaDetailActivity.this);
        View promptsView = li.inflate(R.layout.edit_text_layout, null);
        final EditTextDialogListener noticeDialogListener = new EditTextDialogListener() {
            public void onDialogPositiveClick(String changedValue) {
                ((DetailModel) detailListAdapter.getItem(position)).setValue(changedValue);
            }
        };

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(VillaDetailActivity.this);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextValue);
        if (detailModel.getType().equals("number")) {
            userInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
        TextView headerTextView = (TextView) promptsView.findViewById(R.id.editTextHeader);
        String header = String.format(resources.getString(R.string.edit_text), detailModel.getKey());
        headerTextView.setText(header);
        alertDialogBuilder
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                noticeDialogListener.onDialogPositiveClick(userInput.getText().toString());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void createDetailListView() {
        final ArrayList<DetailModel> detailModelArrayList = villaDetailHelper.readFromDb();
        final DetailListAdapter detailListAdapter = new DetailListAdapter(this, detailModelArrayList);
        final ListView listView = (ListView) findViewById(R.id.villaDetailListView);
        listView.setAdapter(detailListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                DetailModel detailModel = (DetailModel) detailListAdapter.getItem(position);
                String type = detailModel.getType();
                if (type.equals("string") || type.equals("number")) {
                    alertBoxCreator(position, detailListAdapter);
                } else if (type.equals("date")) {
                    createDatePickerDialog(view);
                }
            }
        });
    }

    private void createDatePickerDialog(View view) {
        Calendar newCalendar = Calendar.getInstance();
        final TextView variableTextView = (TextView) view.findViewById(R.id.variableTextView);
        try {
            newCalendar.setTime(df.parse(String.valueOf(variableTextView.getText())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                variableTextView.setText(df.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                ListView listView = (ListView) findViewById(R.id.villaDetailListView);
                ContentValues contentValues = villaDetailHelper.getContentFromList(listView);
                System.out.println(villaDetailHelper.insertIntoBookingDetail(contentValues));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_item_menu, menu);
        return true;
    }
}
