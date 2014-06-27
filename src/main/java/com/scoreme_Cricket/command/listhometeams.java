package com.scoreme_Cricket.command;

import java.util.ArrayList;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.scoreme_Cricket.mongo.homeTeamConnectionProvider;

public class listhometeams {

	public ArrayList<DBObject> execute(){
		homeTeamConnectionProvider homeConn = new homeTeamConnectionProvider();
		DBCollection homeCollection = homeConn.getCollection();
		
		DBCursor cursor = homeCollection.find();
		
		ArrayList<DBObject> away = new ArrayList<DBObject>();
		try {
		   while(cursor.hasNext()) {
			   away.add(cursor.next());
		   }
		} finally {
		   cursor.close();
		}
		return away;
		
	}
	public static void main(String[] args) {
		listhometeams listHomeTeam = new listhometeams();
		ArrayList<DBObject> list = listHomeTeam.execute();
		System.out.println(list);

	}

}
