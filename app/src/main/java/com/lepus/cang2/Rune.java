package com.lepus.cang2;

import android.widget.TextView;

import java.io.Serializable;

public class Rune implements Serializable{
	private static final long serialVersionUID = -5693000217434761989L;

	public static final int[] W = {81, 27, 9, 3, 1};

	int idx;
	String name;
	int color;
	String sum;

	public Rune(){

	}

	private String Int2Hex(int i){
		String str = Integer.toHexString(i);
		return str.length() == 1 ? "0" + str : str;
	}

	private int Hex2Int(String h){
		if(h == null || "".equals(h) || h.length() != 2)
			return -1;
		return Integer.parseInt(h, 16);
	}

	private int Hex2Int(char high, char low){
		return Integer.parseInt(String.valueOf(high) + String.valueOf(low), 16);
	}

	public Rune(int idx, String name, int color, int c7, int c6, int c5, int c4, int c3){
		this.idx = idx;
		this.name = name;
		this.color = color;
		this.sum = Int2Hex(c7) + Int2Hex(c6) + Int2Hex(c5) + Int2Hex(c4) + Int2Hex(c3);
	}

	public void set(TextView... arr){
		if(arr.length == 5){
			String s = "";
			for(int i=0, len=arr.length; i<len; i++){
				String x = arr[i].getText().toString();
				s += "".equals(x) ? "00" : Int2Hex(Integer.parseInt(x));
			}
			this.sum = s;
		}
	}

	public String weight(){
		int sum = 0;
		for(int i=0, len=this.sum.length(); i<len; i++){
			if(i % 2 == 0){
				char high = this.sum.charAt(i);
				char low = this.sum.charAt(i + 1);
				sum += Hex2Int(high, low) * W[i / 2];
			}
		}
		return String.valueOf(sum);
	}

	public String count(){
		int sum = 0;
		for(int i=0, len=this.sum.length(); i<len; i++){
			if(i % 2 == 0){
				char high = this.sum.charAt(i);
				char low = this.sum.charAt(i + 1);
				sum += Hex2Int(high, low);
			}
		}
		return String.valueOf(sum);
	}

	public String count(int index){
		char high = sum.charAt(index * 2);
		char low = sum.charAt(index * 2 + 1);
		return String.valueOf(Hex2Int(high, low));
	}

	public String sum(){
		String s = "";
		for(int i=0, len=this.sum.length(); i<len; i++){
			if(i % 2 == 0){
				char high = sum.charAt(i);
				char low = sum.charAt(i + 1);
				s += "" + Hex2Int(high, low);
			}
			if(i % 2 == 1 && i != this.sum.length() - 1){
				s += "/";
			}
		}
		return s;
	}

}
