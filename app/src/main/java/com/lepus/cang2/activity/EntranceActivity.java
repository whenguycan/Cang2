package com.lepus.cang2.activity;

import android.os.Bundle;

import com.lepus.cang2.R;

public class EntranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.btn_rune).setOnClickListener(view -> startActivity(MainActivity.class));

        findViewById(R.id.btn_hero).setOnClickListener(view -> startActivity(HeroActivity.class));

    }

}
