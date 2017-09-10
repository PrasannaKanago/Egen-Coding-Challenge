package com.egen.app.service;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import java.util.List;

import org.bson.types.ObjectId;
import org.easyrules.api.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.app.dao.AlertDAO;
import com.egen.app.dao.MetricDAO;
import com.egen.app.model.Metric;
import com.egen.app.rules.OverweightRule;
import com.egen.app.rules.UnderweightRule;

@Component
public class MetricService {

	private RulesEngine rulesEngine;

	@Autowired
	private MetricDAO metricDAO;
	
	@Autowired
	private AlertDAO alertDAO;
	
	MetricService() {
		rulesEngine = aNewRulesEngine().build();
	}

	public ObjectId createMetric(Metric metric, Long baseWeight) {
		// first trigger the rules before processing the request to DB.
		processEasyRules(metric, baseWeight);

		return metricDAO.createMetric(metric);
	}

	/**
	 * Below method registers and fires the rules configured for the given request.
	 * 
	 * @param metric
	 */
	private void processEasyRules(Metric metric, Long baseWeight) {
		rulesEngine.registerRule(new UnderweightRule(metric, baseWeight, alertDAO));
		rulesEngine.registerRule(new OverweightRule(metric, baseWeight, alertDAO));
		
		rulesEngine.fireRules();
		rulesEngine.clearRules();
	}

	public List<Metric> read() {
		return metricDAO.read();
	}

	public List<Metric> readByRange(long startTime, long endTime) {
		return metricDAO.readByRange(startTime, endTime);
	}
}
