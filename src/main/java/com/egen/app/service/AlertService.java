package com.egen.app.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egen.app.dao.AlertDAO;
import com.egen.app.model.Alert;


@Component
public class AlertService {


    @Autowired
    private AlertDAO alertDAO;

    public AlertService() {
    }

    public ObjectId create (Alert alert) {
        return alertDAO.create(alert);
    }

    public List<Alert> read () {
        return alertDAO.read();
    }

    public List<Alert> readByRange(long startTime, long endTime) {
        return alertDAO.readByRange(startTime, endTime);
    }
}
