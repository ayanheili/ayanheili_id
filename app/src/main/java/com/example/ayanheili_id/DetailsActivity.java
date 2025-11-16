package com.example.ayanheili_id;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dbHelper = new DatabaseHelper(this);

        int id = getIntent().getIntExtra("questId", -1);

        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView xp = findViewById(R.id.xp);

        if (id == -1) {
            title.setText("Error: No quest ID provided");
            return;
        }

        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM Quest WHERE id=?", new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex("title");
            int descriptionIndex = cursor.getColumnIndex("description");
            int xpIndex = cursor.getColumnIndex("xp");

            title.setText("Title: " + cursor.getString(titleIndex));
            description.setText("Description: " + cursor.getString(descriptionIndex));
            xp.setText("XP: " + cursor.getInt(xpIndex));
        } else {
            title.setText("No quest found for ID " + id);
        }

        cursor.close();
    }
}
