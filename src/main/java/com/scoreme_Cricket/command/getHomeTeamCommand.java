package com.scoreme_Cricket.command;


import com.scoreme_Cricket.mongo.homeTeamConnectionProvider;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class getHomeTeamCommand {
	public DBObject execute(String homeTeamName) {
		homeTeamConnectionProvider homeTeamConn = new homeTeamConnectionProvider();
		DBCollection homeTeamCollection = homeTeamConn.getCollection();

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("homeTeamName", homeTeamName);
		DBCursor cursor = homeTeamCollection.find(searchQuery);
		DBObject homeTeam = cursor.next();
		
		return homeTeam;
	}
	public static void main(String[] args) {
		getHomeTeamCommand command = new getHomeTeamCommand();
		System.out.println(command.execute("CCS"));

	}

}
