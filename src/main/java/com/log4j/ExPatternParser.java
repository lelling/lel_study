package com.log4j;

import org.apache.log4j.helpers.FormattingInfo;
import org.apache.log4j.helpers.PatternConverter;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.spi.LoggingEvent;

public class ExPatternParser extends PatternParser{

	public ExPatternParser(String pattern) {
		super(pattern);
	}

	@Override
	protected void finalizeConverter(char c){
		if (c == 'T') {  
	        this.addConverter(new ExPatternConverter(this.formattingInfo));  
	       } else {  
	        super.finalizeConverter(c);  
	       } 
	}
	
	private static class ExPatternConverter extends PatternConverter {

		public ExPatternConverter(FormattingInfo fi){
			super(fi);
		}
		@Override
		protected String convert(LoggingEvent event) {
			return String.valueOf(Thread.currentThread().getId());
		} 
		
	}
}
