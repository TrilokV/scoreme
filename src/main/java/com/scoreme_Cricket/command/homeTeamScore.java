package com.scoreme_Cricket.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.scoreme_Cricket.model.homeTeam;
import com.scoreme_Cricket.mongo.homeTeamConnectionProvider;

public class homeTeamScore {
	
	public boolean execute(homeTeam home) {
		homeTeamConnectionProvider homeconn = new homeTeamConnectionProvider();
		DBCollection collection = homeconn.getCollection("hometeamscore");

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(home));
			collection.insert(dbObject);
			
			
		} catch (Exception e) {
			System.out.println("ERROR during mapping team name to Mongo Object");
			return false;
		}
		return true;
	}
	
	//testing the connection and the db creation.
	public static void main(String[] args) {
		homeTeamScore team = new homeTeamScore();
		homeTeam home = new homeTeam();
		home.setHomeTeamName("UBS");
		home.setHomeovers(50.0);
		if (team.execute(home)) {
			System.out.println("SUCCESS:Home Team Created");
		} else {
			System.out.println("ERROR:Failed to create Home Team");
		}

		

	}

}
