package com.lepus.cang2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by WANG on 2018/5/15.
 */

public class BaseActivity extends AppCompatActivity {

	protected String TAG = getClass().getSimpleName();

	protected Context getContext(){
		return this;
	}

	protected void startActivity(Class<?> cls){
		Intent intent = new Intent(getApplicationContext(), cls);
		startActivity(intent);
	}

	protected void makeText(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
