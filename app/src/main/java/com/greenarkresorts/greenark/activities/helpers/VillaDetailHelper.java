package com.greenarkresorts.greenark.activities.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.greenarkresorts.greenark.R;
import com.greenarkresorts.greenark.dao.CottagesDBHelper;
import com.greenarkresorts.greenark.models.DetailModel;
import com.greenarkresorts.greenark.models.db.BookingDetailContract;

import java.util.ArrayList;

public class VillaDetailHelper {
    private String dateOfStay;
    private String villaName;
    private Context context;
    private SQLiteDatabase writableDb, readableDb;
    private boolean isInsert = true;

    public VillaDetailHelper(String dateOfStay, String villaName, Context context) {
        this.dateOfStay = dateOfStay;
        this.villaName = villaName;
        this.context = context;
        initialize();
    }

    private void initialize() {
        CottagesDBHelper cottagesDBHelper = new CottagesDBHelper(context);
        writableDb = cottagesDBHelper.getWritableDatabase();
        readableDb = cottagesDBHelper.getReadableDatabase();
    }

    public ArrayList<DetailModel> readFromDb() {
        ArrayList<DetailModel> detailModelArrayList = new ArrayList<>();
        String[] projection = {
                BookingDetailContract.BookingDetail.BOOKING_NAME_COLUMN,
                BookingDetailContract.BookingDetail.BOOKING_AGENT_COLUMN,
                BookingDetailContract.BookingDetail.AMOUNT_COLUMN,
                BookingDetailContract.BookingDetail.ADVANCE_COLUMN,
                BookingDetailContract.BookingDetail.HEADCOUNT_COLUMN,
                BookingDetailContract.BookingDetail.EXTRA_BED_COLUMN,
                BookingDetailContract.BookingDetail.DATE_BOOKED_COLUMN,
                BookingDetailContract.BookingDetail.DATE_OF_ADVANCE_PAY_COLUMN,
                BookingDetailContract.BookingDetail.DATE_OF_BALANCE_PAY_COLUMN,
                BookingDetailContract.BookingDetail.PLAN_TYPE_COLUMN
        };
        String selection = BookingDetailContract.BookingDetail.DATE_OF_STAY_COLUMN + " = '" + dateOfStay
                +"' AND " + BookingDetailContract.BookingDetail.VILLA_NAME_COLUMN + " = '" + villaName + "'";

        Cursor cursor = readableDb.query(BookingDetailContract.BookingDetail.TABLE_NAME,
                projection, selection, null, null, null, null);
        if (cursor.getCount() > 0) {
            isInsert = false;
            while (cursor.moveToNext()) {
                detailModelArrayList = createListFromDb(cursor);
            }
        } else {
            detailModelArrayList = createStaticList();
        }
        return detailModelArrayList;
    }

    private ArrayList<DetailModel> createListFromDb(Cursor cursor) {
        ArrayList<DetailModel> detailModelArrayList = new ArrayList<>();
        detailModelArrayList.add(new DetailModel("Name", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.BOOKING_NAME_COLUMN)), "string"));
        detailModelArrayList.add(new DetailModel("Agent", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.BOOKING_AGENT_COLUMN)), "string"));
        detailModelArrayList.add(new DetailModel("Amount", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.AMOUNT_COLUMN)), "number"));
        detailModelArrayList.add(new DetailModel("Advance", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.ADVANCE_COLUMN)), "number"));
        detailModelArrayList.add(new DetailModel("No. of People", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.HEADCOUNT_COLUMN)), "number"));
        detailModelArrayList.add(new DetailModel("Extra Bed", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.EXTRA_BED_COLUMN)), "number"));
        detailModelArrayList.add(new DetailModel("Date Booked", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.DATE_BOOKED_COLUMN)), "date"));
        detailModelArrayList.add(new DetailModel("Date Advance Paid", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.DATE_OF_ADVANCE_PAY_COLUMN)), "date"));
        detailModelArrayList.add(new DetailModel("Date of Balance Pay", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.DATE_OF_BALANCE_PAY_COLUMN)), "date"));
        detailModelArrayList.add(new DetailModel("Plan Type", cursor.getString(
                cursor.getColumnIndex(BookingDetailContract.BookingDetail.PLAN_TYPE_COLUMN)), "picker"));
        System.out.println(detailModelArrayList.size());
        return detailModelArrayList;
    }

    private ArrayList<DetailModel> createStaticList() {
        ArrayList<DetailModel> detailModelArrayList = new ArrayList<>();
        detailModelArrayList.add(new DetailModel("Name", "Jose", "string"));
        detailModelArrayList.add(new DetailModel("Agent", "MMT", "string"));
        detailModelArrayList.add(new DetailModel("Amount", "2800", "number"));
        detailModelArrayList.add(new DetailModel("Advance", "2000", "number"));
        detailModelArrayList.add(new DetailModel("No. of People", "2", "number"));
        detailModelArrayList.add(new DetailModel("Extra Bed", "0", "number"));
        detailModelArrayList.add(new DetailModel("Date Booked", dateOfStay, "date"));
        detailModelArrayList.add(new DetailModel("Date Advance Paid", dateOfStay, "date"));
        detailModelArrayList.add(new DetailModel("Date of Balance Pay", dateOfStay, "date"));
        detailModelArrayList.add(new DetailModel("Plan Type", "CP", "picker"));

        return detailModelArrayList;
    }

    public ContentValues getContentFromList(ListView listView) {
        View childView;
        TextView variableTextView;
        ContentValues contentValues = new ContentValues();
        for (int listIndex = 0; listIndex < listView.getCount(); listIndex++) {
            childView = listView.getChildAt(listIndex);
            variableTextView  = (TextView) childView.findViewById(R.id.variableTextView);
            String column;
            switch (listIndex) {
                case 0: column = BookingDetailContract.BookingDetail.BOOKING_NAME_COLUMN; break;
                case 1: column = BookingDetailContract.BookingDetail.BOOKING_AGENT_COLUMN; break;
                case 2: column = BookingDetailContract.BookingDetail.AMOUNT_COLUMN; break;
                case 3: column = BookingDetailContract.BookingDetail.ADVANCE_COLUMN; break;
                case 4: column = BookingDetailContract.BookingDetail.HEADCOUNT_COLUMN; break;
                case 5: column = BookingDetailContract.BookingDetail.EXTRA_BED_COLUMN; break;
                case 6: column = BookingDetailContract.BookingDetail.DATE_BOOKED_COLUMN; break;
                case 7: column = BookingDetailContract.BookingDetail.DATE_OF_ADVANCE_PAY_COLUMN; break;
                case 8: column = BookingDetailContract.BookingDetail.DATE_OF_BALANCE_PAY_COLUMN; break;
                case 9: column = BookingDetailContract.BookingDetail.PLAN_TYPE_COLUMN; break;
                default: column = "";
            }
            contentValues.put(column, variableTextView.getText().toString());
            contentValues.put(BookingDetailContract.BookingDetail.DATE_OF_STAY_COLUMN, dateOfStay);
            contentValues.put(BookingDetailContract.BookingDetail.VILLA_NAME_COLUMN, villaName);
        }
        return contentValues;
    }

    public long insertIntoBookingDetail(ContentValues contentValues) {
        if (isInsert) {
            return writableDb.insert(BookingDetailContract.BookingDetail.TABLE_NAME, null, contentValues);
        } else {
            String whereClause = BookingDetailContract.BookingDetail.DATE_OF_STAY_COLUMN + " = '" + dateOfStay
                    +"' AND " + BookingDetailContract.BookingDetail.VILLA_NAME_COLUMN + " = '" + villaName + "'";
            return writableDb.update(BookingDetailContract.BookingDetail.TABLE_NAME, contentValues, whereClause, null);
        }
    }
}
