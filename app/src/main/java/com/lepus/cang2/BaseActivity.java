package com.lepus.cang2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by WANG on 2018/5/15.
 */

public class BaseActivity extends AppCompatActivity {

	protected String TAG = getClass().getSimpleName();

	protected Context getContext(){
		return this;
	}

}
