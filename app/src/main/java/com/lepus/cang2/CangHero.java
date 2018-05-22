package com.lepus.cang2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wang on 2018/5/21.
 */

public class CangHero {

    static class HeroData {

        public static final int FIND_ALL = -1;
        public static final int FIND_BY_MAP_NAME = 0;
        public static final int FIND_BY_MAP_ORDER = 1;
        public static final int FIND_BY_HERO_NAME = 2;

        public static final int COUNT_ALL = 0;
        public static final int COUNT_BY_HERO_NAME = 1;

        List<String> list = new ArrayList<>();

        public List<String> showAll(){
            List<String> list = count(COUNT_ALL, null);
            List<String> target = new ArrayList<>();
            for(String s : list) {
                int idx = s.indexOf("_");
                if(idx != -1){
                    target.add(s.substring(0, idx));
                }
            }
            return target;
        }

        // count in 0, 1
        public List<String> count(int count, String key){

            Set<String> nameSet = new HashSet<>();

            for(String line : list){
                String[] arr = line.split("_");
                if(arr.length > 2){
                    String[] names = arr[2].split("\\+");
                    for(String name : Arrays.asList(names)){
                        if (count == COUNT_ALL || (COUNT_BY_HERO_NAME == 1 && name.equals(key))) {
                            nameSet.add(name);
                        }
                    }
                }
            }

            List<Counter> find = new ArrayList<>();
            for(String name : nameSet){
                List<String> l = find(FIND_BY_HERO_NAME, name);
                find.add(Counter.newInstance(name, l.size()));
            }

            Collections.sort(find, (o1, o2) -> o1.count - o2.count);

            List<String> result = new ArrayList<>();
            for(Counter c : find){
                result.add(c.name + "_" + c.count);
            }

            return result;
        }

        // pos in -1, 0, 1, 2
        public List<String> find(int pos, String key){

            List<String> find = new ArrayList<>();

            if (pos == -1) {
                find.addAll(list);
            } else {
                for(String line : list){
                    String[] arr = line.split("_");
                    if (pos < arr.length && arr[pos].contains(key)) {
                        find.add(line);
                    }
                }
//                list.stream().forEach(line -> {
//                    String[] arr = line.split("_");
//                    if (pos < arr.length && arr[pos].contains(key)) {
//                        find.add(line);
//                    }
//                });
            }

            return find;
        }

        public HeroData(){
            add("1.弥赛亚_1.1_杰克+珊朵拉+潘多拉");
            add("1.弥赛亚_1.2_桑尼+罗兰+尼尔法");
            add("1.弥赛亚_1.3_哥伦布+珊朵拉+罗兰");
            add("1.弥赛亚_1.4_莉可丽丝+尼尔法+罗兰");
            add("1.弥赛亚_1.5_珊朵拉+尼尔法+格莱明");
            add("2.赛连_2.1_薇薇安+潘多拉+罗兰");
            add("2.赛连_2.2_卡缇+杰克+瓦恩");
            add("2.赛连_2.3_格莱明+尼尔法+路西菲尔");
            add("2.赛连_2.4_齐格飞+哥伦布+路西菲尔");
            add("2.赛连_2.5_路西菲尔+哥伦布+薇薇安");
            add("3.沃尔达_3.1_黛丝+桑尼+莉可丽丝");
            add("3.沃尔达_3.2_薛定谔+潘多拉+珊朵拉");
            add("3.沃尔达_3.3_");
            add("3.沃尔达_3.4_尼尔法+罗兰+哥伦布");
            add("3.沃尔达_3.5_伊莎贝拉+尼尔法+黛丝");
            add("4.暮光城_4.1_特斯拉+格莱明+珊朵拉");
            add("4.暮光城_4.2_菲娅+薛定谔+薇薇安");
            add("4.暮光城_4.3_美杜莎+潘多拉+薇薇安");
            add("4.暮光城_4.4_杰克+潘多拉+罗兰");
            add("4.暮光城_4.5_桑尼+罗兰+尼尔法");
            add("5.曼彻斯特_5.1_哥伦布+珊朵拉+罗兰");
            add("5.曼彻斯特_5.2_莉可丽丝+尼尔法+罗兰");
            add("5.曼彻斯特_5.3_珊朵拉+尼尔法+格莱明");
            add("5.曼彻斯特_5.4_薇薇安+潘多拉+罗兰");
            add("5.曼彻斯特_5.5_卡缇+杰克+瓦恩");
            add("6.温泉谷_6.1_格莱明+尼尔法+路西菲尔");
            add("6.温泉谷_6.2_齐格飞+哥伦布+路西菲尔");
            add("6.温泉谷_6.3_路西菲尔+哥伦布+薇薇安");
            add("6.温泉谷_6.4_黛丝+桑尼+莉可丽丝");
            add("6.温泉谷_6.5_薛定谔+潘多拉+珊朵拉");
            add("6.温泉谷_6.6_疾风+瓦恩+卡缇");
            add("6.温泉谷_6.7_");
            add("6.温泉谷_6.8_薇薇安+薛定谔+美杜莎");
            add("6.温泉谷_6.9_");
            add("6.温泉谷_6.10_亚巴顿+瓦恩+罗兰");
        }

        List<String> add(String value){
            list.add(value);
            return list;
        }
    }

}
