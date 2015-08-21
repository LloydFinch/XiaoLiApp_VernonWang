package com.xiaoliapp.app.entity;


import java.util.Comparator;

public class Friend implements Comparable<Friend>{

	private String sortLetters;  //显示数据拼音的首字母

	private String head;
	private String name;
	private String number;
	private float grade;
	private Gifts gifts;
	private Impress impress;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public Gifts getGifts() {
		return gifts;
	}

	public void setGifts(Gifts gifts) {
		this.gifts = gifts;
	}

	public Impress getImpress() {
		return impress;
	}

	public void setImpress(Impress impress) {
		this.impress = impress;
	}

	public int compareTo(Friend another) {
		return 0;
	}
}
