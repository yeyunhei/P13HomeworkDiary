package sg.edu.rp.c346.id20011806.homeworkdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelperActivity extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "activities.db";
    private static final String TABLE_ACTIVITY = "activity";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_ADD_DATE = "addDate";
    private static final String COLUMN_DUE_DATE = "dueDate";
    private static final String COLUMN_SUBJECT_ID = "subjectId";

    public DBHelperActivity(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_ACTIVITY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ADD_DATE + " TEXT,"
                + COLUMN_DUE_DATE + " TEXT,"
        + COLUMN_SUBJECT_ID + " INTEGER )";
        db.execSQL(createTableSql);
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
            // Create table(s) again
            onCreate(db);
        }

    public long insertActivity(String description, String addDate, String dueDate, int subjectId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_ADD_DATE, addDate);
        values.put(COLUMN_DUE_DATE, dueDate);
        values.put(COLUMN_SUBJECT_ID, subjectId);
        long result = db.insert(TABLE_ACTIVITY, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Activity> getAllActivities() {
        ArrayList<Activity> ALActivities = new ArrayList<Activity>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_DESCRIPTION + ","
                + COLUMN_ADD_DATE + ","
                + COLUMN_DUE_DATE + ","
                + COLUMN_SUBJECT_ID + " FROM " + TABLE_ACTIVITY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String desc = cursor.getString(1);
                String addDate = cursor.getString(2);
                String dueDate = cursor.getString(3);
                int subjectId = cursor.getInt(4);
                Activity activity = new Activity(id, desc, addDate, dueDate, subjectId);
                ALActivities.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ALActivities;
    }

    public ArrayList<Activity> getAllActivities(int keyword) {
        ArrayList<Activity> ALActivities = new ArrayList<Activity>();


        String[] columns= {COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_ADD_DATE, COLUMN_DUE_DATE, COLUMN_SUBJECT_ID};
        String condition = COLUMN_SUBJECT_ID + " = ?";
        String[] args = { "" +  keyword + ""};


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, columns, condition, args,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String desc = cursor.getString(1);
                String addDate = cursor.getString(2);
                String dueDate = cursor.getString(3);
                int subjectId = cursor.getInt(4);
                Activity activity = new Activity(id, desc, addDate, dueDate, subjectId);
                ALActivities.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ALActivities;
    }

}
