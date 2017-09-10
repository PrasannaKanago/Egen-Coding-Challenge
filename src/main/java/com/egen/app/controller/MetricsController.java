package com.egen.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.app.common.AppUtil;
import com.egen.app.common.ApplicationConstants;
import com.egen.app.model.Metric;
import com.egen.app.service.MetricService;

/**
 * This class provides api support for reading, creating and fetching metric
 * details for a given time range.
 * 
 * @author Prasanna Kanago
 *
 */

@RestController
public class MetricsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricsController.class);

	@Autowired
	private MetricService metricService;

	@Autowired
	private AppUtil appUtil;

	/**
	 * This method is the REST end point for creating the metrics
	 * 
	 * @param metric
	 *            Metric object
	 * @param baseWeight
	 * 			  baseWeight of the person
	 * @return ResponseEntity<Metric> Metric object created during this operation
	 */
	@RequestMapping(value = ApplicationConstants.METRIC, method = RequestMethod.POST)
	public ResponseEntity<Metric> create(@RequestBody Metric metric, @PathVariable Long baseWeight) {
		LOGGER.debug("MetricsController - create method - entered ");

		if (metric == null || baseWeight <= 0L) {
			// input metric should not be null and base weight should be greater than zero
			return new ResponseEntity<Metric>(metric, HttpStatus.BAD_REQUEST);
		}
		LOGGER.debug("metric "+metric.getValue() + ", "+metric.getTimeStamp());
		metricService.createMetric(metric, baseWeight);

		LOGGER.debug("MetricsController - create method - ends ");

		return new ResponseEntity<Metric>(metric, HttpStatus.CREATED);
	}

	/**
	 * This method is the REST end point for reading all the metrics from DB
	 * 
	 * @return list of Metric objects wrapped inside the ResponseEntity
	 */
	@RequestMapping(value = ApplicationConstants.METRICS)
	public ResponseEntity<List<Metric>> read() {
		LOGGER.debug("MetricsController - read method - entered");
		List<Metric> metricList = metricService.read();

		if (metricList.size() == 0) {
			LOGGER.debug("MetricsController - read method - ends");
			return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);
		}

		LOGGER.debug("MetricsController - read method - ends");
		return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
	}

	/**
	 * This method is responsible for reading the metrics from the DB for the given
	 * timestamp range
	 * 
	 * @param startTime
	 * @param endTime
	 * @return list of Metric objects wrapped inside the ResponseEntity
	 */
	@RequestMapping(value = ApplicationConstants.METRICS_TIME_RANGE)
	public ResponseEntity<List<Metric>> readByTimeRange(@PathVariable Long startTimeStamp,
			@PathVariable Long endTimeStamp) {
		LOGGER.debug("MetricsController - readByTimeRange method - entered");

		// here instead of utility method for validating input, a spring request
		// validator can be used as well
		// just for the simplicity utility method is used here.
		if (appUtil.validateTimeRange(startTimeStamp, endTimeStamp))
			return new ResponseEntity<List<Metric>>(new ArrayList<Metric>(), HttpStatus.BAD_REQUEST);

		List<Metric> metricList = metricService.readByRange(startTimeStamp, endTimeStamp);

		if (metricList.size() == 0) {
			LOGGER.debug("MetricsController - readByTimeRange method - ends");
			return new ResponseEntity<List<Metric>>(metricList, HttpStatus.NO_CONTENT);
		}

		LOGGER.debug("MetricsController - readByTimeRange method - ends");
		return new ResponseEntity<List<Metric>>(metricList, HttpStatus.OK);
	}
}
