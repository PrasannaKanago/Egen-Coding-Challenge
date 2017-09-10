package com.egen.app.common;

import org.springframework.stereotype.Component;

@Component
public class AppUtil {

	public boolean validateTimeRange(Long startTimeStamp, Long endTimeStamp) {

		if (startTimeStamp == null || endTimeStamp == null || (endTimeStamp < startTimeStamp)) {
			return true;
		}
		
		return false;
	}

}
