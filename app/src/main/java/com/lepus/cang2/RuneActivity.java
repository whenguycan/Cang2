package com.lepus.cang2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by WANG on 2018/5/15.
 */

public class RuneActivity extends BaseActivity {

    private int len;

    private int position;

    private Data data;

    private float startX;

    private float endX;

    private float pixelMonitor = 150;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rune);

        data = (Data) getIntent().getSerializableExtra("object");
        position = getIntent().getIntExtra("position", -1);
        len = data.list.size();

        replace(data.list.get(position), 0);

	}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean move = true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getRawX();
                if(endX - startX > pixelMonitor || startX - endX > pixelMonitor){
                    if(endX - startX > 0){
                        if(position <= 0){
                            move = false;
                            Toast.makeText(getApplicationContext(), "已经到第一个了", Toast.LENGTH_SHORT).show();
                        }else{
                            position--;
                        }
                    }else if(endX - startX < 0){
                        if(position >= len - 1){
                            move = false;
                            Toast.makeText(getApplicationContext(), "已经到最后一个了", Toast.LENGTH_SHORT).show();
                        }else{
                            position++;
                        }
                    }
                    if(move) {
                        replace(data.list.get(position), (int) (endX - startX));
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void replace(Rune rune, int direction){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int enter;
        int exit;
        if(direction < 0){
            enter = R.animator.fragment_slide_right_in;
            exit = R.animator.fragment_slide_left_out;
            transaction.setCustomAnimations(enter, exit);
        }else if(direction > 0){
            enter = R.animator.fragment_slide_left_in;
            exit = R.animator.fragment_slide_right_out;
            transaction.setCustomAnimations(enter, exit);
        }
        transaction.replace(R.id.frame, RuneFragment.newInstance(getApplicationContext(), rune)).commit();
    }

}
