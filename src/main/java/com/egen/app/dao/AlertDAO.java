package com.egen.app.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.egen.app.configuration.MorphiaDbConfig;
import com.egen.app.model.Alert;
import com.mongodb.MongoQueryException;
import com.mongodb.MongoWriteException;

@Component
public class AlertDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlertDAO.class);

	Datastore datastore;

	public AlertDAO() {
		datastore = MorphiaDbConfig.getInstance().getDatastore();
	}

	public ObjectId create(Alert alert) {

		try {
			datastore.save(alert);

		} catch (MongoWriteException e) {

			LOGGER.error("Exception while inserting alert to DB", e);
			e.printStackTrace();
		}

		return alert.getId();
	}

	public List<Alert> read() {
		Query<Alert> query = datastore.createQuery(Alert.class);

		List<Alert> alertListFromDb = null;
		try {
			alertListFromDb = query.asList();
		} catch (MongoQueryException e) {
			LOGGER.error("Exception while reading alerts from DB", e);
			e.printStackTrace();
		}
		return alertListFromDb;
	}

	public List<Alert> readByRange(long startTime, long endTime) {
		Query<Alert> query = datastore.createQuery(Alert.class).filter("timeStamp >=", startTime).filter("timeStamp <=",
				endTime);

		List<Alert> alertListFromDb = null;
		try {
			alertListFromDb = query.asList();
		} catch (MongoQueryException e) {
			LOGGER.error("Exception while reading alerts from DB", e);
			e.printStackTrace();
		}
		return alertListFromDb;
	}

}
