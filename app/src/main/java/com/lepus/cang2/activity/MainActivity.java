package com.lepus.cang2.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.lepus.cang2.Data;
import com.lepus.cang2.ObjectSerializer;
import com.lepus.cang2.R;
import com.lepus.cang2.Rune;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MainActivity extends BaseActivity {

	ObjectSerializer serializer = ObjectSerializer.newInstance(this);

	GridView gv;

	Data data;

	Data dataDisplay;

	List<Rune> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerReceiver(receiver, new IntentFilter(ObjectSerializer.INTENT_SERIALIZE_SUCCESS));

    }

    @Override
    protected void onResume() {

        data = serializer.deserialize(ObjectSerializer.FILE_NAME_DATA);

        if(data == null) {
            Log.e(TAG, "onCreate: data is null");
            return;
        }

        list = data.list(Color.MAGENTA);

        dataDisplay = new Data();
        dataDisplay.list = list;

        gv = findViewById(R.id.gv_main);
        gv.setAdapter(new ArrayAdapter<Rune>(getContext(), R.layout.item, list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = getLayoutInflater().inflate(R.layout.item, null);
                Rune r = list.get(position);
                TextView tv_name = view.findViewById(R.id.tv_name);
                tv_name.setText(r.name);
                tv_name.setTextColor(r.color);
                TextView tv_weight = view.findViewById(R.id.tv_weight);
                tv_weight.setText(r.weight());
                tv_weight.getPaint().setFakeBoldText(true);
                ((TextView) view.findViewById(R.id.tv_count)).setText(r.count());
                ((TextView) view.findViewById(R.id.tv_sum)).setText(r.sum());
                return view;
            }
        });
        gv.setOnItemClickListener((parent, view, position, id) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("object", dataDisplay);
            bundle.putInt("position", position);
            startActivity(RuneActivity.class, bundle);
        });

        super.onResume();
    }

    @Override
	protected void onDestroy() {
		unregisterReceiver(receiver);
		super.onDestroy();
	}

	private BroadcastReceiver receiver = new MyBroadcastReceiver();

	class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(ObjectSerializer.INTENT_SERIALIZE_SUCCESS)){
				Rune r = (Rune) intent.getSerializableExtra("object");
				for(int i=0, len=data.list.size(); i<len; i++){
					if(r.idx == data.list.get(i).idx){
						data.list.set(i, r);
                    }
				}
				serializer.serialize(ObjectSerializer.FILE_NAME_DATA, data);

                ((ArrayAdapter) gv.getAdapter()).notifyDataSetChanged();

                EventBus.getDefault().post(r);
			}
		}

	}

}
