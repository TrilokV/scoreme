package com.scoreme_Cricket.command;

import java.util.ArrayList;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.scoreme_Cricket.mongo.awayTeamConnectionProvider;

public class listawayteams {

	public ArrayList<DBObject> execute(){
		awayTeamConnectionProvider awayConn = new awayTeamConnectionProvider();
		DBCollection awayCollection = awayConn.getCollection();
		
		DBCursor cursor = awayCollection.find();
		
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
		listawayteams listAwayTeam = new listawayteams();
		ArrayList<DBObject> list = listAwayTeam.execute();
		System.out.println(list);

	}

}
