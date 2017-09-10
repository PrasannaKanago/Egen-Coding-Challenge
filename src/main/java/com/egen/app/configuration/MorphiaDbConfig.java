package com.egen.app.configuration;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.stereotype.Component;

import com.egen.app.common.ApplicationConstants;
import com.mongodb.MongoClient;

/**
 * This class configures connection with mongoDb "coding_challenge"
 * 
 * @author Prasanna Kanago
 *
 */
@Component
public class MorphiaDbConfig {

	private static MorphiaDbConfig morphiaDbConfig = new MorphiaDbConfig();

	private Datastore datastore = null;

	// initialize the configuration to connect to mongo DB
	private MorphiaDbConfig() {
		Morphia morphia = new Morphia();
		morphia.mapPackage(ApplicationConstants.MONGO_DB_MODEL_PACKAGE);
		datastore = morphia.createDatastore(new MongoClient(), ApplicationConstants.MONGO_DB_NAME);
		datastore.ensureIndexes();
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public static MorphiaDbConfig getInstance() {
		return morphiaDbConfig;
	}
}
