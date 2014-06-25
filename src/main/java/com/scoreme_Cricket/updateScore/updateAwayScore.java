package com.scoreme_Cricket.updateScore;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.scoreme_Cricket.model.awayTeam;
import com.scoreme_Cricket.mongo.awayTeamConnectionProvider;

public class updateAwayScore {

	public ArrayList<awayTeam> execute(String awayTeamName, String Command) {
		awayTeamConnectionProvider awayteamConn = new awayTeamConnectionProvider();
		DBCollection awayteamCollection = awayteamConn.getCollection("awayteamscore");

		// DBCursor cursor = awayteamCollection.find();
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("awayTeamName", awayTeamName);
		DBObject temp = awayteamCollection.findOne(searchQuery);
		ArrayList<awayTeam> awayteam = new ArrayList<awayTeam>();

		int runs = (int) temp.get("awayruns");

		double overs = (double) temp.get("awayovers");
		double runrate = (double) temp.get("awayrunrate");
		double gamelimit = (double) temp.get("awayoverslimit");
		int extras = (int) temp.get("awayextras");
		int wickets = (int) temp.get("awaywickets");

		String overslimit = Double.toString(overs);

		BasicDBObject newDocument = new BasicDBObject(); // for overs
		BasicDBObject newDocument1 = new BasicDBObject(); // for runs
		BasicDBObject newDocument2 = new BasicDBObject(); // for extras
		BasicDBObject newDocument3 = new BasicDBObject(); // for Wickets
		BasicDBObject newDocument4 = new BasicDBObject(); // for RunRate
		if (overs == gamelimit || wickets == 10) {

			// if the granted overs equals number of overs bowled
			// or if all get out, close one innings or finish the game.
			return awayteam;
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

				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs2") {
				runs += 2;
				overs = overs + 0.1;
				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs3") {
				runs += 3;
				overs = overs + 0.1;
				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				// System.out.print("" + searchQuery.toString() + " " + overs);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs4") {
				runs += 4;
				overs += 0.1;
				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Runs6") {
				runs += 6;
				overs += 0.1;
				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Extras") {
				runs++;
				extras++;
				newDocument1.append("$set",new BasicDBObject().append("awayruns", runs));
				newDocument2.append("$set",new BasicDBObject().append("awayextras", extras));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awayruns", runs);
				temp.put("awayextras", extras);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument1);
				awayteamCollection.update(searchQuery, newDocument2);
				awayteamCollection.update(searchQuery, newDocument4);
			} else if (Command == "Wickets") {
				wickets++;
				overs += 0.1;

				newDocument.append("$set",new BasicDBObject().append("awayovers", overs));
				newDocument3.append("$set",new BasicDBObject().append("awaywickets", wickets));
				newDocument4.append("$set",new BasicDBObject().append("awayrunrate", runrate));
				temp.put("awaywickets", wickets);
				temp.put("awayovers", overs);
				temp.put("awayrunrate", runrate);
				awayteamCollection.update(searchQuery, newDocument);
				awayteamCollection.update(searchQuery, newDocument3);
				awayteamCollection.update(searchQuery, newDocument4);
			}

		}
		return awayteam;
	}

	public static void main(String[] args) {
		updateAwayScore u = new updateAwayScore();
		u.execute("WHCC", "Runs1");

	}

}
