package com.example.ayanheili_id;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText searchField;
    Button showAllBtn, addNewBtn, goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Temporary: delete the unwanted "text" record
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.getWritableDatabase().delete("Quest", "title = ?", new String[]{"Test"});

        searchField = findViewById(R.id.search_field);
        showAllBtn = findViewById(R.id.show_all_btn);
        addNewBtn = findViewById(R.id.add_new_btn);
        goBtn = findViewById(R.id.go_btn);

        showAllBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RecordsListActivity.class));
        });

        addNewBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewActivity.class));
        });

        goBtn.setOnClickListener(v -> {
            String query = searchField.getText().toString().trim();
            Intent intent = new Intent(MainActivity.this, RecordsListActivity.class);
            intent.putExtra("searchQuery", query);
            startActivity(intent);
        });
    }

}
