package com.egen.app.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.egen.app.EgenCodingChallengeApplication;
import com.egen.app.model.Alert;
import com.egen.app.model.Metric;
import com.egen.app.service.AlertService;
import com.egen.app.service.MetricService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EgenCodingChallengeApplication.class)
@WebAppConfiguration
public class EgenBeChallengeApplicationTests {


	@Autowired
	private MetricService metricService;


	@Autowired
	private AlertService alertService;

	@Before
	@Test
	public void checkNoAlertMetricInsert() {

		long timeStamp = 1463327802613l;

		Metric metric = new Metric();
		Long baseWeight = 120L;
		metric.setValue((int)(1.02 * baseWeight));
		metric.setTimeStamp(timeStamp);


		metricService.createMetric(metric, baseWeight);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
				if (alert.getTimeStamp() == timeStamp){
					assert (false);
			}
		}
		
		assert (true);
	}
	
	
	@Before
	@Test
	public void checkAlertOverweight() {

		long timeStamp = 1463327802614l;

		Metric metric = new Metric();
		Long baseWeight = 120L;
		metric.setValue((int)(1.2 * baseWeight));
		metric.setTimeStamp(timeStamp);


		metricService.createMetric(metric, baseWeight);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
				if (alert.getTimeStamp() == timeStamp && alert.getType().equals("OVER_WEIGHT")) {
				assert (true);
				return;
			}
		}
		
		assert (false);
	}
	
	
	@Before
	@Test
	public void checkAlertUnderweight() {

		long timeStamp = 1463327802615l;

		Metric metric = new Metric();
		Long baseWeight = 150L;
		metric.setValue((int)(0.8 * baseWeight));

		metric.setTimeStamp(timeStamp);


		metricService.createMetric(metric, baseWeight);

		List<Alert> alertList = alertService.read();
		for (Alert alert : alertList) {
			if (alert.getTimeStamp() == timeStamp && alert.getType().equals("UNDER_WEIGHT")) {
				assert (true);
				return;
			}
		}

		assert (false);
	}



	@Test
	public void getAllMetrics() {
		List<Metric> metrics = metricService.read();
		if(null == metrics) {
			assert (false);
		}else {
			assert (true);
		}
	}
	
	@Test
	public void getAllAlerts() {
		List<Alert> alerts = alertService.read();
		if(null == alerts) {
			assert (false);
		}else {
			assert (true);
		}
	}
	
	
	@Test
	public void getAllMetricsInTimeRange() {
		List<Metric> metrics = metricService.readByRange(1463327802613l, 1463327802615l);
		if(null == metrics) {
			assert (false);
		}else {
			assert (true);
		}
	}
	
	
	@Test
	public void getAllAlertsInTimeRange() {
		List<Alert> alerts = alertService.readByRange(1463327802613l, 1463327802615l);
		if(null == alerts) {
			assert (false);
		}else {
			assert (true);
		}
	}

}
