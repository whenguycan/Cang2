package com.lepus.cang2;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANG on 2018/5/15.
 */

public class Data implements Serializable {
    private static final long serialVersionUID = -5693000217434751989L;

    public List<Rune> list = new ArrayList<>();

    public List<Rune> list(int... colors){
        if(colors == null || colors.length == 0)
            return this.list;
        List<Rune> target = new ArrayList<>();
        for(Rune r : this.list){
            for(int c : colors){
                if(r.color == c){
                    target.add(r);
                    break;
                }
            }
        }
        return target;
    }

    public void init(){
        list.add(new Rune(0, "堕天", Color.GRAY, 1, 2, 1, 2, 0));
        list.add(new Rune(1, "统治", Color.GRAY, 1, 1, 0, 11, 6));
        list.add(new Rune(2, "死亡", Color.GRAY, 1, 0, 0, 7, 9));
        list.add(new Rune(3, "抵御", Color.GRAY, 0, 1, 2, 11, 11));
        list.add(new Rune(4, "恶魔", Color.GRAY, 1, 0, 1, 15, 8));
        list.add(new Rune(5, "宿命", Color.BLUE, 0, 0, 9, 14, 6));
        list.add(new Rune(6, "灼烧", Color.BLUE, 0, 0, 9, 21, 0));
        list.add(new Rune(7, "宝藏", Color.MAGENTA, 0, 0, 1, 2, 0));
        list.add(new Rune(8, "凛霜", Color.MAGENTA, 0, 0, 0, 3, 1));
        list.add(new Rune(9, "裁决", Color.MAGENTA, 0, 1, 1, 0, 0));
        list.add(new Rune(10, "法则", Color.MAGENTA, 0, 0, 2, 1, 1));
        list.add(new Rune(11, "魔法", Color.MAGENTA, 0, 2, 0, 2, 2));
        list.add(new Rune(12, "暴力", Color.MAGENTA, 0, 0, 2, 1, 2));
        list.add(new Rune(13, "天使", Color.GRAY, 1, 1, 0, 3, 1));
        list.add(new Rune(14, "王权", Color.GRAY, 1, 2, 5, 9, 1));
        list.add(new Rune(15, "安息", Color.GRAY, 1, 0, 0, 0, 7));
        list.add(new Rune(16, "守护", Color.GRAY, 0, 1, 4, 13, 11));
        list.add(new Rune(17, "魔鬼", Color.GRAY, 1, 0, 0, 4, 0));
        list.add(new Rune(18, "命运", Color.BLUE, 0, 0, 12, 13, 4));
        list.add(new Rune(19, "热诚", Color.BLUE, 0, 0, 11, 14, 0));
        list.add(new Rune(20, "圣杯", Color.MAGENTA, 0, 0, 1, 1, 0));
        list.add(new Rune(21, "冰霜", Color.MAGENTA, 0, 2, 1, 1, 0));
        list.add(new Rune(22, "审判", Color.MAGENTA, 0, 1, 0, 0, 0));
        list.add(new Rune(23, "正义", Color.MAGENTA, 0, 1, 1, 3, 0));
        list.add(new Rune(24, "圣言", Color.MAGENTA, 0, 0, 0, 2, 1));
        list.add(new Rune(25, "力量", Color.MAGENTA, 0, 1, 2, 0, 1));
    }

}
