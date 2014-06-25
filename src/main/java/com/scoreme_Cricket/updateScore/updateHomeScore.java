package com.scoreme_Cricket.updateScore;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.scoreme_Cricket.model.homeTeam;
import com.scoreme_Cricket.mongo.homeTeamConnectionProvider;

public class updateHomeScore {

	public ArrayList<homeTeam> execute(String homeTeamName, String Command) {
		homeTeamConnectionProvider hometeamConn = new homeTeamConnectionProvider();
		DBCollection hometeamCollection = hometeamConn.getCollection("hometeamscore");

		// DBCursor cursor = hometeamCollection.find();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("homeTeamName", homeTeamName);
		DBObject temp = hometeamCollection.findOne(searchQuery);
		ArrayList<homeTeam> hometeam = new ArrayList<homeTeam>();

		int runs = Integer.parseInt(temp.get("homeruns").toString());
		
		double overs = Double.parseDouble(temp.get("homeovers").toString());
		double runrate = Double.parseDouble(temp.get("homerunrate").toString());
		double gamelimit = Double.parseDouble(temp.get("homeoverslimit").toString());
		int extras = Integer.parseInt(temp.get("homeextras").toString());
		int wickets = Integer.parseInt(temp.get("homewickets").toString());

		String overslimit = Double.toString(overs);

		BasicDBObject newDocument = new BasicDBObject(); // for overs
		BasicDBObject newDocument1 = new BasicDBObject(); // for runs
		BasicDBObject newDocument2 = new BasicDBObject(); // for extras
		BasicDBObject newDocument3 = new BasicDBObject(); // for Wickets
		BasicDBObject newDocument4 = new BasicDBObject(); // for RunRate
		if (overs == gamelimit || wickets == 10) {

			// if the granted overs equals number of overs bowled
			// or if all get out, close one innings or finish the game.
			return hometeam;
		}

		/*
		 * This is the logic for calculating the run rate for the match of home
		 * team.
		 */
		else {
			/**
			 * This is a part where we are formatting the double to ##.# format.
			 * Overs : 0.1, 0.2, 0.3, 0.4, 0.5, 1.0, 1.1, 1.2, 1.3, 1.4, 1.5,
			 * 2.0
			 */
			if (overslimit.contains(".6")) {
				overs = overs + 0.4;
			}
			if (overs == 0.0 || runs == 0) {
				runrate = 0.0;
			} else {
				runrate = (double) (runs / overs);
			}
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(1);
			df.setMaximumFractionDigits(1);
			df.format(overs);

			// System.out.print("Overs: " +df.format(overs));
			/*
			 * My Business logic for calculating and updating the DB for Home
			 * Team.
			 */
			if (Command == "Runs1") {
				runs++;
				overs = overs + 0.1;

				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs2") {
				runs += 2;
				overs = overs + 0.1;
				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs3") {
				runs += 3;
				overs = overs + 0.1;
				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				// System.out.print("" + searchQuery.toString() + " " + overs);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs4") {
				runs += 4;
				overs += 0.1;
				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs6") {
				runs += 6;
				overs += 0.1;
				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Extras") {
				runs++;
				extras++;
				newDocument1.append("$set",new BasicDBObject().append("homeruns", runs));
				newDocument2.append("$set",new BasicDBObject().append("homeextras", extras));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homeruns", runs);
				temp.put("homeextras", extras);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument1);
				hometeamCollection.update(searchQuery, newDocument2);
				hometeamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Wickets") {
				wickets++;
				overs += 0.1;

				newDocument.append("$set",new BasicDBObject().append("homeovers", overs));
				newDocument3.append("$set",new BasicDBObject().append("homewickets", wickets));
				newDocument4.append("$set",new BasicDBObject().append("homerunrate", runrate));
				temp.put("homewickets", wickets);
				temp.put("homeovers", overs);
				temp.put("homerunrate", runrate);
				hometeamCollection.update(searchQuery, newDocument);
				hometeamCollection.update(searchQuery, newDocument3);
				hometeamCollection.update(searchQuery, newDocument4);
			}

		}
		return hometeam;
	}

	public static void main(String[] args) {
		updateHomeScore u = new updateHomeScore();
		u.execute("CCS", "Wickets");

	}

}
