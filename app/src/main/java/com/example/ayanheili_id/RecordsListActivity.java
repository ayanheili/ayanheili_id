package com.example.ayanheili_id;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class RecordsListActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_list);

        listView = findViewById(R.id.listView);
        dbHelper = new DatabaseHelper(this);

        String query = getIntent().getStringExtra("searchQuery");

        Cursor cursor;
        if (query != null && !query.isEmpty()) {
            cursor = dbHelper.getQuestByTitle(query);
        } else {
            cursor = dbHelper.getAllQuests();
        }

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No results were found!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getInt(0));
                titles.add(cursor.getString(1));
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("questId", ids.get(position));
            startActivity(intent);
        });
    }
}
