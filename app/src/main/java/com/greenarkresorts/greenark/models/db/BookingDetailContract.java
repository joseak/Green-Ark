package com.greenarkresorts.greenark.models.db;

import android.provider.BaseColumns;

public final class BookingDetailContract {

    private BookingDetailContract() {}

    public static class BookingDetail implements BaseColumns {
        public static final String TABLE_NAME = "bookingDetail";
        public static final String DATE_OF_STAY_COLUMN = "dateOfStay";
        public static final String VILLA_NAME_COLUMN = "villaName";
        public static final String BOOKING_NAME_COLUMN = "bookingName";
        public static final String BOOKING_AGENT_COLUMN = "bookingAgent";
        public static final String AMOUNT_COLUMN = "amount";
        public static final String ADVANCE_COLUMN = "advance";
        public static final String HEADCOUNT_COLUMN = "headcount";
        public static final String EXTRA_BED_COLUMN = "extraBed";
        public static final String DATE_BOOKED_COLUMN = "dateBooked";
        public static final String DATE_OF_ADVANCE_PAY_COLUMN = "dateAdvancePaid";
        public static final String DATE_OF_BALANCE_PAY_COLUMN = "dateBalancePaid";
        public static final String PLAN_TYPE_COLUMN = "planType";
    }

    public static final String CREATE_TABLE_BOOKING_DETAIL_SQL = "CREATE TABLE " +
            BookingDetail.TABLE_NAME + " (" +
            BookingDetail.DATE_OF_STAY_COLUMN + " TEXT," +
            BookingDetail.VILLA_NAME_COLUMN + " TEXT," +
            BookingDetail.BOOKING_NAME_COLUMN + " TEXT," +
            BookingDetail.BOOKING_AGENT_COLUMN + " TEXT," +
            BookingDetail.AMOUNT_COLUMN + " INTEGER," +
            BookingDetail.ADVANCE_COLUMN + " INTEGER," +
            BookingDetail.HEADCOUNT_COLUMN + " INTEGER," +
            BookingDetail.EXTRA_BED_COLUMN+ " INTEGER," +
            BookingDetail.DATE_BOOKED_COLUMN + " TEXT," +
            BookingDetail.DATE_OF_ADVANCE_PAY_COLUMN + " TEXT," +
            BookingDetail.DATE_OF_BALANCE_PAY_COLUMN + " TEXT," +
            BookingDetail.PLAN_TYPE_COLUMN + " TEXT, " +
            "PRIMARY KEY (" + BookingDetail.DATE_OF_STAY_COLUMN + ", " +
            BookingDetail.VILLA_NAME_COLUMN + "))";

    public static final String DELETE_TABLE_BOOKING_DETAIL_SQL =
            "DROP TABLE IF EXISTS " + BookingDetail.TABLE_NAME;
}
