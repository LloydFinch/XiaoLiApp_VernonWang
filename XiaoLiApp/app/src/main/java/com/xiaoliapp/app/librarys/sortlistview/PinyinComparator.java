package com.xiaoliapp.app.librarys.sortlistview;

import com.xiaoliapp.app.entity.Friend;

import java.util.Comparator;
public class PinyinComparator implements Comparator<Friend> {

	public int compare(Friend o1, Friend o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
