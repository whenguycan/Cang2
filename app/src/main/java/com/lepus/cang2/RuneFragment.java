package com.lepus.cang2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by wang on 2018/5/19.
 */

public class RuneFragment extends Fragment {

    Context mContext;
    Rune rune;

    TextView tv7;
    TextView tv6;
    TextView tv5;
    TextView tv4;
    TextView tv3;

    public static RuneFragment newInstance(Context context, Rune rune){
        RuneFragment fragment = new RuneFragment();
        fragment.mContext = context;
        fragment.rune = rune;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rune, container, false);

        TextView tv = view.findViewById(R.id.tv_name);
        tv.setText(rune.name);

        tv7 = view.findViewById(R.id.tv_7);
        tv6 = view.findViewById(R.id.tv_6);
        tv5 = view.findViewById(R.id.tv_5);
        tv4 = view.findViewById(R.id.tv_4);
        tv3 = view.findViewById(R.id.tv_3);
        final TextView[] tvArr = {tv7, tv6, tv5, tv4, tv3};

        ImageView minusView7 = view.findViewById(R.id.iv_minus7);
        ImageView minusView6 = view.findViewById(R.id.iv_minus6);
        ImageView minusView5 = view.findViewById(R.id.iv_minus5);
        ImageView minusView4 = view.findViewById(R.id.iv_minus4);
        ImageView minusView3 = view.findViewById(R.id.iv_minus3);
        final ImageView[] ivMinusArr = {minusView7, minusView6, minusView5, minusView4, minusView3};

        ImageView plusView7 = view.findViewById(R.id.iv_plus7);
        ImageView plusView6 = view.findViewById(R.id.iv_plus6);
        ImageView plusView5 = view.findViewById(R.id.iv_plus5);
        ImageView plusView4 = view.findViewById(R.id.iv_plus4);
        ImageView plusView3 = view.findViewById(R.id.iv_plus3);
        final ImageView[] ivPlusArr = {plusView7, plusView6, plusView5, plusView4, plusView3};

        for(int i=0, len=5; i<len; i++){
            final int idx = i;
            tvArr[i].setText(rune.count(i));
            ivMinusArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    minus(tvArr[idx]);
                }
            });
            ivMinusArr[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ivMinusArr[idx].setImageResource(R.drawable.ic_remove_circle_blue_24dp);
                            break;
                        case MotionEvent.ACTION_UP:
                            ivMinusArr[idx].setImageResource(R.drawable.ic_remove_circle_outline_blue_24dp);
                            break;
                    }
                    return false;
                }
            });
            ivPlusArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    plus(tvArr[idx]);
                }
            });
            ivPlusArr[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ivPlusArr[idx].setImageResource(R.drawable.ic_add_circle_blue_24dp);
                            break;
                        case MotionEvent.ACTION_UP:
                            ivPlusArr[idx].setImageResource(R.drawable.ic_add_circle_outline_blue_24dp);
                            break;
                    }
                    return false;
                }
            });
        }

        view.findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        view.findViewById(R.id.btn_save_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().onBackPressed();
                    }
                }, 200);
            }
        });

        return view;
    }

    private void minus(TextView tv){
        String text = tv.getText().toString();
        if(StringUtils.isEmpty(text))
            tv.setText("0");
        int num = Integer.parseInt(text);
        num--;
        num = num < 0 ? 0 : num;
        tv.setText(String.valueOf(num));
    }

    private void plus(TextView tv){
        String text = tv.getText().toString();
        if(StringUtils.isEmpty(text))
            tv.setText("1");
        int num = Integer.parseInt(text);
        num++;
        num = num < 256 ? num : 255;
        tv.setText(String.valueOf(num));
    }

    private void update(){
        rune.set(tv7, tv6, tv5, tv4, tv3);
        Intent intent = new Intent(ObjectSerializer.INTENT_SERIALIZE_SUCCESS);
        intent.putExtra("object", rune);
        mContext.sendBroadcast(intent);
        Toast.makeText(mContext, "save success", Toast.LENGTH_SHORT).show();
    }

}
