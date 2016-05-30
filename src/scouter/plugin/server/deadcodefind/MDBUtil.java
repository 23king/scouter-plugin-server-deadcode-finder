package scouter.plugin.server.deadcodefind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import scouter.server.Configure;

public class MDBUtil {
	MongoClientURI uri;
	MongoClient mongo;
	MongoDatabase database;
	MongoCollection<Document> collection;
	private static final String ext_plugin_mongodb_host = "ext_plugin_mongodb_host";
    private static final String ext_plugin_mongodb_port = "ext_plugin_mongodb_port";
    private static final String ext_plugin_mongodb_user = "ext_plugin_mongodb_user";
    private static final String ext_plugin_mongodb_pwd = "ext_plugin_mongodb_pwd";
    private static final String ext_plugin_mongodb_dbname = "ext_plugin_mongodb_dbname";
    private static final String ext_plugin_mongodb_collection = "ext_plugin_mongodb_collection";
	
	public MDBUtil(Configure conf){
		uri = new MongoClientURI("mongodb://"+conf.getValue(ext_plugin_mongodb_user)+":"+conf.getValue(ext_plugin_mongodb_pwd)+"@"+conf.getValue(ext_plugin_mongodb_host,"localhost")+":"+conf.getInt(ext_plugin_mongodb_port,27017)+"/"+conf.getValue(ext_plugin_mongodb_dbname));
		mongo = new MongoClient(uri);
		database = mongo.getDatabase(conf.getValue(ext_plugin_mongodb_dbname));
		collection = database.getCollection(conf.getValue(ext_plugin_mongodb_collection));
	}
	
	public void insertMethod(String methods){
		String[] ms = methods.split(",");
		List<Document> datas=new ArrayList<Document>();
		Set<String> uniqueMethod = new HashSet<String>();
		
		for(String m : ms)
			if(m != null)
				uniqueMethod.add(m);
				
		for(String m : uniqueMethod)
			datas.add(new Document("method",m));
		
		collection.insertMany(datas);
	}
}
