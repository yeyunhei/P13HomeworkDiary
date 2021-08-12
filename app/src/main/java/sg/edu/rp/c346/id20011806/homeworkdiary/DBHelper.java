package sg.edu.rp.c346.id20011806.homeworkdiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "subjects.db";
    private static final String TABLE_SUBJECT = "subject";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SUBJECT +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT )";
        db.execSQL(createTableSql);

        /*for (int i = 0; i < 4; i++) {
            String stars = "*";
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, "Title: " + i);
            values.put(COLUMN_SINGERS, "Singers: " + i);
            values.put(COLUMN_YEAR, "Year: " + i);
            values.put(COLUMN_STARS, "Stars: " + (stars+= "*****".substring(0, i)));
            db.insert(TABLE_SONG, null, values);
        }*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        // Create table(s) again
        onCreate(db);
    }

    public long insertSubject(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_SUBJECT, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> ALSubjects = new ArrayList<Subject>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_NAME + ","
                + COLUMN_DESCRIPTION + " FROM " + TABLE_SUBJECT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                Subject subject = new Subject(id, name, desc);
                ALSubjects.add(subject);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ALSubjects;
    }

   /* public int updateSong(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }

    public int deleteSong(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }

    public ArrayList<Song> getAll5StarSongs() {
        ArrayList<Song> ALSongs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + ","
                + COLUMN_SINGERS + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SONG
                + " WHERE " + COLUMN_STARS + " == 5 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singers, year, stars);
                ALSongs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ALSongs;
    }
    public ArrayList<Song> getAllSongs(int keyword) {
        ArrayList<Song> ALSongs = new ArrayList<Song>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS };
        String condition = COLUMN_YEAR + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_SONG, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id, title, singers, year, stars);
                ALSongs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ALSongs;
    }*/

}
