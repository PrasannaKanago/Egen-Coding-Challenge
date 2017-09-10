package com.egen.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egen.app.common.ApplicationConstants;
import com.egen.app.model.Alert;
import com.egen.app.service.AlertService;

/**
 * This class provides Rest API end points for reading alerts stored in mongoDb.
 * 
 * @author Prasanna Kanago
 *
 */
@RestController
public class AlertController {

	@Autowired
	private AlertService alertService;

	/**
	 * This method reads all the alerts from the mongoDb.
	 * 
	 * @return list of Alerts
	 */
	@RequestMapping(value = ApplicationConstants.ALERTS)
	public ResponseEntity<List<Alert>> read() {

		List<Alert> alertList = alertService.read();

		if (alertList.size() == 0)
			return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
	}

	/**
	 * This method reads alerts which were created between a given time range and
	 * returns the list of Alerts.
	 * 
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @return
	 */
	@RequestMapping(value = ApplicationConstants.ALERTS_TIME_RANGE)
	public ResponseEntity<List<Alert>> readByTimeRange(@PathVariable Long startTimeStamp,
			@PathVariable Long endTimeStamp) {

		if (startTimeStamp == null || endTimeStamp == null || (endTimeStamp < startTimeStamp))
			return new ResponseEntity<List<Alert>>(new ArrayList<Alert>(), HttpStatus.BAD_REQUEST);

		List<Alert> alertList = alertService.readByRange(startTimeStamp, endTimeStamp);

		if (alertList.size() == 0)
			return new ResponseEntity<List<Alert>>(alertList, HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Alert>>(alertList, HttpStatus.OK);
	}

}
