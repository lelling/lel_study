package com.log4j;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

public class ExtPatternLayout extends PatternLayout{
	
	public ExtPatternLayout(){
		super();
	}
	public ExtPatternLayout(String pattern){
		super(pattern);
	}
	
	@Override  
    protected PatternParser createPatternParser(String pattern) {  
     return new ExPatternParser(pattern);  
    } 
	
}
