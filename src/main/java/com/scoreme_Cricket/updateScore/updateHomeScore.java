package com.scoreme_Cricket.updateScore;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.scoreme_Cricket.model.homeTeam;
import com.scoreme_Cricket.mongo.homeTeamConnectionProvider;


public class updateHomeScore {
	
	private int ballCount ;

	public ArrayList<homeTeam> execute(String homeTeamName, String Command) {
		homeTeamConnectionProvider hometeamConn = new homeTeamConnectionProvider();
		DBCollection hometeamCollection = hometeamConn.getCollection("hometeamscore");

		// DBCursor cursor = hometeamCollection.find();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("homeTeamName", homeTeamName);
		DBObject temp = hometeamCollection.findOne(searchQuery);
		ArrayList<homeTeam> hometeam = new ArrayList<homeTeam>();
		
		int runs = (int) temp.get("homeruns");
		
		double overs =  (double) temp.get("homeovers");

		String overslimit= Double.toString(overs);
		
		if(overslimit.contains(".6")){
			overs = overs + 0.4;
		}
	
		
		/**
		 * This is a part where we are formatting the double to ##.# format.
		 * Overs : 0.1, 0.2, 0.3, 0.4, 0.5, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 2.0 
		 */
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(1);
		df.format(overs);
		System.out.print("Overs: " +df.format(overs));
		BasicDBObject newDocument = new BasicDBObject();
		BasicDBObject newDocument1 = new BasicDBObject();
		if (Command == "Runs1") {
			runs++;
			overs = overs + 0.1;
			newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
			newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
			temp.put("homeruns", runs);
			temp.put("homeovers", overs);
			hometeamCollection.update(searchQuery, newDocument);
			hometeamCollection.update(searchQuery, newDocument1);
		} else if (Command == "Runs2") {
			runs += 2;
			overs = overs + 0.1;
			newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
			newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
			temp.put("homeruns", runs);
			temp.put("homeovers", overs);
			hometeamCollection.update(searchQuery, newDocument);
			hometeamCollection.update(searchQuery, newDocument1);
		} else if (Command == "Runs3") {
			runs += 3;
			overs = overs + 0.1;
			newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
			newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
			temp.put("homeruns", runs);
			temp.put("homeovers", overs);
			System.out.print("" + searchQuery.toString() + " " + overs);
			hometeamCollection.update(searchQuery, newDocument);
			hometeamCollection.update(searchQuery, newDocument1);
		} else if (Command == "Runs4") {
			runs += 4;
			overs += 0.1;
			newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
			newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
			temp.put("homeruns", runs);
			temp.put("homeovers", overs);
			hometeamCollection.update(searchQuery, newDocument);
			hometeamCollection.update(searchQuery, newDocument1);
		} else if (Command == "Runs6") {
			runs += 6;
			overs += 0.1;
			newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
			newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
			temp.put("homeruns", runs);
			temp.put("homeovers", overs);
			hometeamCollection.update(searchQuery, newDocument);
			hometeamCollection.update(searchQuery, newDocument1);
		}
		return hometeam;
	}

	public static void main(String[] args) {
		updateHomeScore u = new updateHomeScore();
		u.execute("CCS", "Runs1");

	}

}
