package com.hawi.lukman.project_kamus_made_dicoding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_VOCABULARY = "kosakata";
    public static final String ITEM_ARTI = "arti";
    public static final String ITEM_CATEGORY = "category";

    TextView tvWord, tvTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle(getIntent().getStringExtra(ITEM_VOCABULARY));
        getSupportActionBar().setSubtitle(getIntent().getStringExtra(ITEM_CATEGORY));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvWord = findViewById(R.id.tv_word_detail);
        tvTranslate = findViewById(R.id.tv_translate_detail);

        tvWord.setText(getIntent().getStringExtra(ITEM_VOCABULARY));
        tvTranslate.setText(getIntent().getStringExtra(ITEM_ARTI));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
