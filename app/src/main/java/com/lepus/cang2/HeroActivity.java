package com.lepus.cang2;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HeroActivity extends BaseActivity {

    GridView gv;
    ListView lv;
    CangHero.HeroData heroData = new CangHero.HeroData();

    Set<String> selected = new LinkedHashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero);

        gv = findViewById(R.id.gv_hero);
        lv = findViewById(R.id.lv_hero);

        final List<String> list = heroData.showAll();

        int btnTextColorDefault = -570425344;   //默认灰 #DE000000
        String btnTextColorBlue = "#00BFFF";   //深天蓝

        gv.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_hero_gv, list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = getLayoutInflater().inflate(R.layout.item_hero_gv, null);
                Button btn = view.findViewById(R.id.btn_hero);
                String name = list.get(position);
                btn.setText(name);
                btn.setOnClickListener(v -> {
                    selected.add(name);
                    if(btn.getCurrentTextColor() == btnTextColorDefault){
                        btn.setTextColor(Color.parseColor(btnTextColorBlue));
                        selected.add(name);
                    }else{
                        btn.setTextColor(btnTextColorDefault);
                        selected.remove(name);
                    }
                    reloadListView();
                });
                return view;
            }
        });

    }

    private void reloadListView(){
        List<String> result = new ArrayList<>();
        if(!selected.isEmpty()){
            for(String name : selected){
                result.addAll(heroData.find(2, name));
            }
        }
        lv.setAdapter(new ArrayAdapter<>(getContext(), R.layout.item_hero_lv, R.id.tv_hero, result));
    }

}
