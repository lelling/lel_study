package com.basis.oom;

import java.util.ArrayList;
import java.util.List;

public class ConstantOom {
	public static void main(String[] args) {
		int count = 1;
		List list = new ArrayList<>();
		String str = "0";
		while (true) {
			str = str+(count++);
			list.add(str.intern());
		}
	}
}
