package com.lepus.cang2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * powered by xys199719
 * Created by wang on 2018/5/22.
 */

public class BottomDialog {

    public static class Builder {
        final int DELAY_DEFAULT = -1;
        Context context;
        int styleResId;
        int layoutResId;
        int gravity = Gravity.BOTTOM;
        int[] ids;
        long delay = DELAY_DEFAULT;
        OnItemClickListener onItemClickListener;
        public Builder(Context context){
            this.context = context;
        }
        public Builder style(int styleResId){
            this.styleResId = styleResId;
            return this;
        }
        public Builder layout(int layoutResId){
            this.layoutResId = layoutResId;
            return this;
        }
        public Builder ids(int[] ids){
            if(ids != null)
                this.ids = ids;
            return this;
        }
        public Builder gravity(int gravity){
            this.gravity = gravity;
            return this;
        }
        public Builder closeDelay(long delay){
            this.delay = delay;
            return this;
        }
        public Builder setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener = onItemClickListener;
            return this;
        }
        public Dialog create(){
            Dialog dialog = new Dialog(context, styleResId);
            View view = LayoutInflater.from(context).inflate(layoutResId, null);
            view.measure(0, 0);
            dialog.setContentView(view);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.x = 0;
            lp.y = 0;
            lp.width = context.getResources().getDisplayMetrics().widthPixels;
            lp.height = view.getMeasuredHeight();
            lp.alpha = 0.9f;
            dialog.getWindow().setAttributes(lp);
            for(int id : ids){
                view.findViewById(id).setOnClickListener(v -> onItemClickListener.onClick(dialog, v));
                if(delay != DELAY_DEFAULT)
                    new Handler().postDelayed(() -> dialog.dismiss(), delay);
            }
            return dialog;
        }
    }

    public interface OnItemClickListener {
        void onClick(Dialog dialog, View view);
    }

}
