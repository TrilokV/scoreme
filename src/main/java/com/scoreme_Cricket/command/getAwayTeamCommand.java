package com.scoreme_Cricket.command;


import com.scoreme_Cricket.mongo.awayTeamConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class getAwayTeamCommand {
	public DBObject execute(String awayTeamName) {
		awayTeamConnectionProvider awayTeamConn = new awayTeamConnectionProvider();
		DBCollection awayTeamCollection = awayTeamConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("awayTeamName", awayTeamName);
		DBCursor cursor = awayTeamCollection.find(searchQuery);
		DBObject awayTeam = cursor.next();
		
		return awayTeam;
	}
	public static void main(String[] args) {
		getAwayTeamCommand command = new getAwayTeamCommand();
		System.out.println(command.execute("JPL"));

	}

}
