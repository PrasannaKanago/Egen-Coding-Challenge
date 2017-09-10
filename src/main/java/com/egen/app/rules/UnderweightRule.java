package com.egen.app.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.egen.app.dao.AlertDAO;
import com.egen.app.model.Alert;
import com.egen.app.model.Metric;

@Rule (name = "Under-Weight")
public class UnderweightRule {


    private AlertDAO alertDAO;

    private Metric metric;
    
    private Long baseWeight;
    
	
    public UnderweightRule(Metric metric, Long baseWeight, AlertDAO alertDAO) {
        this.metric = metric;
    	this.baseWeight = baseWeight;
    	this.alertDAO = alertDAO;
    }

    @Condition
    public boolean when() {
        double percent = ((double) metric.getValue()) / baseWeight;

        return percent < 0.9;

    }

    @Action
    public void then() {
        Alert alert = new Alert("UNDER_WEIGHT", metric.getTimeStamp(), metric.getValue());

        alertDAO.create(alert);
    }
}
