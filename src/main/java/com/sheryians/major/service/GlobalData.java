package com.sheryians.major.service;

import java.util.ArrayList;
import java.util.List;

import com.sheryians.major.entity.TblProducts;

public class GlobalData {

	public static List<TblProducts> cart;

	static {
		cart=new ArrayList<TblProducts>();
	}

}
