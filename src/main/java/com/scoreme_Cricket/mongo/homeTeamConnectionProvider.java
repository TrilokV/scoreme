package com.scoreme_Cricket.mongo;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;


public class homeTeamConnectionProvider {
	/**
	 * TODO:modify this method to allow passing the collection name to it
	 * @param string 
	 * @return
	 */

	public DBCollection getCollection(String string) {
		try {

			MongoClient mongo = new MongoClient("kahana.mongohq.com", 10089);

			DB db = mongo.getDB("scoreme");
			if (db == null) {
				System.out.println("Could not connect to Database");
			}

			boolean auth = db.authenticate("scoreme1", "scoreme1".toCharArray());
			if (auth == false) {
				System.out.println("Could not authenticate");
			}

			DBCollection teamColl = db.getCollection("hometeamscore");
			return teamColl;

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static void main(String[] args) {
		homeTeamConnectionProvider home = new homeTeamConnectionProvider();
		DBCollection hometeamscore = home.getCollection("hometeamscore");
		if(hometeamscore == null){
			System.out.println("ERROR:No Connection");
		}
		else{
			System.out.println("SUCCESS:Connected");
		}

	}

}