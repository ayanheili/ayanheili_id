package com.example.ayanheili_id;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studyquest.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // User table
        db.execSQL("CREATE TABLE User(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)");

        // Quest table
        db.execSQL("CREATE TABLE Quest(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, xp INTEGER)");

        // UserQuest table (relation)
        db.execSQL("CREATE TABLE UserQuest(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, quest_id INTEGER, FOREIGN KEY(user_id) REFERENCES User(id), FOREIGN KEY(quest_id) REFERENCES Quest(id))");

        // Sample data
        db.execSQL("INSERT INTO Quest (title, description, xp) VALUES ('Math Basics', 'Solve basic arithmetic problems', 100)");
        db.execSQL("INSERT INTO Quest (title, description, xp) VALUES ('History Quiz', 'Answer 10 questions on history', 150)");
        db.execSQL("INSERT INTO Quest (title, description, xp) VALUES ('Science Challenge', 'Complete a science trivia', 200)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS UserQuest");
        db.execSQL("DROP TABLE IF EXISTS Quest");
        db.execSQL("DROP TABLE IF EXISTS User");
        onCreate(db);
    }

    // CRUD methods
    public Cursor getAllQuests() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Quest", null);
    }

    public Cursor getQuestByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Quest WHERE title=?", new String[]{title});
    }

    public void addQuest(String title, String description, int xp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("xp", xp);
        db.insert("Quest", null, values);
    }
}
