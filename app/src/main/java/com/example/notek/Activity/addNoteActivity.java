package com.example.notek.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notek.R;


import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.Date;

public class addNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "com.example.notek.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.example.notek.EXTRA_TITLE";
    public static final String EXTRA_DESC = "com.example.notek.EXTRA_DESC";
    Boolean isLast = false;
    String lastText;


    private EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addn);


        editTextDesc = findViewById(R.id.editDiscr);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(intent.getStringExtra(Intent.EXTRA_TITLE));
            editTextDesc.setText(intent.getStringExtra(EXTRA_DESC));
            lastText = intent.getStringExtra(EXTRA_DESC);
            isLast = true;
        } else {
            setTitle("Yeni Not");
        }

        editTextDesc.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (isLast == true) {
                    if (lastText.equals(editTextDesc.getText().toString())) {

                    } else {
                        saveNote();
                    }

                } else {
                    saveNote();
                }
            }
        });


    }


    private void saveNote() {
        String title = editTextDesc.getText().toString();
        String desc = editTextDesc.getText().toString();

        String titleArray[] = title.split(" ");
        title = titleArray[0];

        if (title.trim().isEmpty() || desc.trim().isEmpty()) {
            return;
        }
        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESC, desc);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            case R.id.bold_text:
                Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

                editTextDesc.setTypeface(boldTypeface);
                return true;
            case R.id.exit_notsave:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
