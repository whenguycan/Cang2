package com.lepus.cang2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lepus.cang2.R;

/**
 * Created by WANG on 2018/5/15.
 */

public class BaseActivity extends AppCompatActivity {

	protected String TAG = getClass().getSimpleName();

	protected Context getContext(){
		return this;
	}

	protected void startActivity(Class<?> cls){
		startActivity(cls, null);
	}

	protected void startActivity(Class<?> cls, Bundle bundle){
		Intent intent = new Intent(getApplicationContext(), cls);
		if(bundle != null)
			intent.putExtras(bundle);
		startActivity(intent);
		overridePendingTransition(R.anim.anim_activity_right_in, R.anim.anim_activity_left_out);
	}

	protected void makeText(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.anim_activity_left_in, R.anim.anim_activity_right_out);
	}
}
