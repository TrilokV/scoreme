package com.scoreme_Cricket.command;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.scoreme_Cricket.model.awayTeam;
import com.scoreme_Cricket.mongo.awayTeamConnectionProvider;

public class awayTeamScore {
	
	public boolean execute(awayTeam away) {
		awayTeamConnectionProvider awayconn = new awayTeamConnectionProvider();
		DBCollection collection = awayconn.getCollection("awayteamscore");

		ObjectMapper mapper = new ObjectMapper();
		try {
			DBObject dbObject = (DBObject) JSON.parse(mapper
					.writeValueAsString(away));
			collection.insert(dbObject);
			
			
		} catch (Exception e) {
			System.out.println("ERROR during mapping team name to Mongo Object");
			return false;
		}
		return true;
	}

	
	//testing the connection and the db creation.
	public static void main(String[] args) {
		awayTeamScore team = new awayTeamScore();
		awayTeam away = new awayTeam();
		away.setAwayTeamName("WHCC");
		away.setAwayovers(0.0);
		away.setAwayoverslimit(40.0);
		if (team.execute(away)) {
			System.out.println("SUCCESS:Away Team Created");
		} else {
			System.out.println("ERROR:Failed to create away Team");
		}

		

	}

}
