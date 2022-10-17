package com.grupo2.appgestioneventos;

import android.graphics.drawable.AnimationDrawable;
import android.media.tv.TvView;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.type.DateTime;

import java.sql.Time;

public class CalendarioActivity extends MainActivity{
    //Enlace de donde he sacado todo
    //https://developer.android.com/guide/topics/providers/calendar-provider?hl=es-419
    // Projection array. Creating indices for this array instead of doing
    // dynamic lookups improves performance.
    public static final String[] EVENT_PROJECTION = new String[] {
            Calendars._ID,                           // 0
            Calendars.ACCOUNT_NAME,                  // 1
            Calendars.CALENDAR_DISPLAY_NAME,         // 2
            Calendars.OWNER_ACCOUNT                  // 3
    };

    // The indices for the projection array above.
    private static final int PROJECTION_ID_INDEX = 0;
    private static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    private static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    private static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    // Run query
    Cursor cur = null;
    ContentResolver cr = getContentResolver();
    Uri uri = Calendars.CONTENT_URI;
    String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
            + Calendars.ACCOUNT_TYPE + " = ?) AND ("
            + Calendars.OWNER_ACCOUNT + " = ?))";
    String[] selectionArgs = new String[] {"hera@example.com", "com.example",
            "hera@example.com"};
    // Submit the query and get a Cursor object back.
    cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);
    // Use the cursor to step through the returned records
    while (cur.moveToNext()) {
        long calID = 0;
        String displayName = null;
        String accountName = null;
        String ownerName = null;

        // Get the field values
        calID = cur.getLong(PROJECTION_ID_INDEX);
        displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
        accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
        ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

        // Do something with the values...
    }
    //Modificacion de un calendario
    private static final String DEBUG_TAG = "MyActivity";
    long calID = 2;
    ContentValues values = new ContentValues();
    // The new display name for the calendar
    values.put(Calendars.CALENDAR_DISPLAY_NAME, "Trevor's Calendar");
    Uri updateUri = ContentUris.withAppendedId(Calendars.CONTENT_URI, calID);
    int rows = getContentResolver().update(updateUri, values, null, null);
    Log.i(DEBUG_TAG, "Rows updated: " + rows);

    //Agregar eventos
    long calID = 3;
    long startMillis = 0;
    long endMillis = 0;
    Calendar beginTime = Calendar.getInstance();
    beginTime.set(2012, 9, 14, 7, 30);
    startMillis = beginTime.getTimeInMillis();
    Calendar endTime = Calendar.getInstance();
    endTime.set(2012, 9, 14, 8, 45);
    endMillis = endTime.getTimeInMillis();

    ContentResolver cr = getContentResolver();
    ContentValues values = new ContentValues();
    values.put(Events.DTSTART, startMillis);
    values.put(Events.DTEND, endMillis);
    values.put(Events.TITLE, "Jazzercise");
    values.put(Events.DESCRIPTION, "Group workout");
    values.put(Events.CALENDAR_ID, calID);
    values.put(Events.EVENT_TIMEZONE, "America/Los_Angeles");
    Uri uri = cr.insert(Events.CONTENT_URI, values);

    // get the event ID that is the last element in the Uri
    long eventID = Long.parseLong(uri.getLastPathSegment());
    //
    // ... do something with event ID
    //
    //
    //Actualizar eventos
    private static final String DEBUG_TAG = "MyActivity";
    long eventID = 188;
    ContentResolver cr = getContentResolver();
    ContentValues values = new ContentValues();
    Uri updateUri = null;
    // The new title for the event
    values.put(Events.TITLE, "Kickboxing");
    updateUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
    int rows = cr.update(updateUri, values, null, null);
    Log.i(DEBUG_TAG, "Rows updated: " + rows);

    //Borrar eventos
    private static final String DEBUG_TAG = "MyActivity";
    long eventID = 201;
    ContentResolver cr = getContentResolver();
    Uri deleteUri = null;
    deleteUri = ContentUris.withAppendedId(Events.CONTENT_URI, eventID);
    int rows = cr.delete(deleteUri, null, null);
    Log.i(DEBUG_TAG, "Rows deleted: " + rows);

    //Agregar recordatorios
    long eventID = 221;
    ContentResolver cr = getContentResolver();
    ContentValues values = new ContentValues();
    values.put(Reminders.MINUTES, 15);
    values.put(Reminders.EVENT_ID, eventID);
    values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
    Uri uri = cr.insert(Reminders.CONTENT_URI, values);

    //Fondo paguina
    protected void fondo(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_login);
        ConstraintLayout constraintLayout = findViewById(R.id.activity_login_id);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }
}
