package com.lepus.cang2.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lepus.cang2.R;
import com.lepus.cang2.dialog.BottomDialog;

public class EntranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        findViewById(R.id.navigator_rune).setOnClickListener(view -> startActivity(MainActivity.class));

        findViewById(R.id.navigator_hero).setOnClickListener(view -> startActivity(HeroActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.menu_more == item.getItemId()){
            showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog(){

        new BottomDialog.Builder(getContext())
                .style(R.style.bottomDialog)
                .layout(R.layout.dialog_bottom)
                .ids(new int[]{R.id.btn_rune, R.id.btn_hero})
                .setOnItemClickListener((dialog, view) -> {
                    switch (view.getId()) {
                        case R.id.btn_rune:
                            startActivity(MainActivity.class);
                            break;
                        case R.id.btn_hero:
                            startActivity(HeroActivity.class);
                            break;
                    }
                })
                .create()
                .show();

    }

}
