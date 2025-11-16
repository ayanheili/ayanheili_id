package com.example.ayanheili_id;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class AddNewActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        dbHelper = new DatabaseHelper(this);

        EditText title = findViewById(R.id.title_field);
        EditText desc = findViewById(R.id.desc_field);
        EditText xp = findViewById(R.id.xp_field);
        Button saveBtn = findViewById(R.id.save_btn);

        saveBtn.setOnClickListener(v -> {
            String t = title.getText().toString();
            String d = desc.getText().toString();
            int x = Integer.parseInt(xp.getText().toString());

            dbHelper.addQuest(t, d, x);
            Toast.makeText(this, "Quest added!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(AddNewActivity.this, RecordsListActivity.class));
            finish();
        });
    }
}
