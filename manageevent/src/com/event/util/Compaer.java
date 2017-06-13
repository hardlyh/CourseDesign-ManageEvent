package com.event.util;

import java.util.Comparator;

import com.event.domain.TEvent;

public class Compaer implements Comparator<TEvent>{

	@Override
	public int compare(TEvent o1, TEvent o2) {
		if(o1.getEventStarttime().after(o2.getEventStarttime())){
			return 1;
		}else{
			return -1;
		}
		
	}
	
	
	

}
