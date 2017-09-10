package com.egen.app.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.egen.app.configuration.MorphiaDbConfig;
import com.egen.app.model.Metric;
import com.mongodb.MongoQueryException;
import com.mongodb.MongoWriteException;

@Component
public class MetricDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricDAO.class);

	Datastore datastore;

	MetricDAO() {
		datastore = MorphiaDbConfig.getInstance().getDatastore();
	}

	public ObjectId createMetric(Metric metric) {
		try {
			datastore.save(metric);

		} catch (MongoWriteException e) {

			LOGGER.error("Exception while inserting metric to DB", e);
			e.printStackTrace();
		}
		return metric.getId();
	}

	public List<Metric> read() {
		Query<Metric> query = datastore.createQuery(Metric.class);
		List<Metric> metricListFromDb = null;
		try {
			metricListFromDb = query.asList();
		} catch (MongoQueryException e) {
			LOGGER.error("Exception while reading metrics from DB", e);
			e.printStackTrace();
		}
		return metricListFromDb;
	}

	public List<Metric> readByRange(long startTime, long endTime) {
		Query<Metric> query = datastore.createQuery(Metric.class).filter("timeStamp >=", startTime)
				.filter("timeStamp <=", endTime);

		List<Metric> metricListFromDb = null;
		try {
			metricListFromDb = query.asList();
		} catch (MongoQueryException e) {
			LOGGER.error("Exception while reading metrics from DB", e);
			e.printStackTrace();
		}
		return metricListFromDb;
	}
}
