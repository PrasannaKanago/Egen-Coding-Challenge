package com.egen.app.common;

public class ApplicationConstants {
	
	//URL constants - Alerts
	public static final String ALERTS = "/alerts";
	public static final String ALERTS_TIME_RANGE = "/alerts/timerange/{startTimeStamp}/{endTimeStamp}";

	//URL constants - Metrics
	public static final String METRICS = "/metrics";
	public static final String METRIC = "/metric/baseweight/{baseWeight}";
	public static final String METRICS_TIME_RANGE = "/metrics/timerange/{startTimeStamp}/{endTimeStamp}";
	
	
	//Environment specific constants (can be part of properties file instead)
	public static final String MONGO_DB_MODEL_PACKAGE="com.egen.app.model";
	public static final String MONGO_DB_NAME="coding_challenge";

}
