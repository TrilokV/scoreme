package com.scoreme_Cricket.services;

import com.scoreme_Cricket.command.*;
import com.scoreme_Cricket.model.*;
import com.scoreme_Cricket.updateScore.*;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.DBObject;

@Path("/teams")
public class teamServices {
	ObjectMapper mapper = new ObjectMapper();
	//ArrayList awayTeams = new ArrayList();
	//awayTeam away = new awayTeam();
	@GET
	@Path("/awayteam")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAwayTeams() {
		listawayteams listawayTeams = new listawayteams();
		ArrayList<DBObject> list = listawayTeams.execute();
		return Response.status(200).entity(list).build();
	}
	@GET
	@Path("/hometeam")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listHomeTeams() {
		listhometeams listhomeTeams = new listhometeams();
		ArrayList<DBObject> list = listhomeTeams.execute();
		return Response.status(200).entity(list).build();
	}
	@GET
	@Path("awayteam/{awayTeamName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAwayTeam(@PathParam("awayTeamName") String awayTeamName) {
		getAwayTeamCommand getawayCommand = new getAwayTeamCommand();
		DBObject away = getawayCommand.execute(awayTeamName);
		return Response.status(200).entity(away).build();
	}
	@GET
	@Path("hometeam/{homeTeamName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHomeTeam(@PathParam("homeTeamName") String homeTeamName) {
		getHomeTeamCommand gethomeCommand = new getHomeTeamCommand();
		DBObject away = gethomeCommand.execute(homeTeamName);
		return Response.status(200).entity(away).build();
	}
	
	@GET
	@Path("updateaway/{awayTeamName}/{awaycommand}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAwayScore(@PathParam("awayTeamName") String awayTeamName,
									@PathParam("awaycommand") String command){
		updateAwayScore updateawayscore = new updateAwayScore();
		boolean state = updateawayscore.execute(awayTeamName, command);
		//DBObject atObj = (DBObject) at;
		return Response.status(200).entity(state).build();		
	}
	
	@GET
	@Path("updatehome/{homeTeamName}/{homecommand}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateHomeScore(@PathParam("homeTeamName") String homeTeamName,
									@PathParam("homecommand") String command){
		updateHomeScore updatehomescore = new updateHomeScore();
		boolean state = updatehomescore.execute(homeTeamName, command);
		//DBObject atObj = (DBObject) at;
		return Response.status(200).entity(state).build();		
	}
	
	@PUT
	@Path("/createawayteam")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response createawayTeam(String awayTeamName) {

		try {
			awayTeamScore create = new awayTeamScore();
			awayTeam awayTeamobj = mapper.readValue(awayTeamName, awayTeam.class);
			//awayTeamobj= mapper.readValue(oversLimit, awayTeam.class);
			boolean success = create.execute(awayTeamobj);
			String awayTeamJSON = mapper.writeValueAsString(awayTeamobj);
			if (success) {
				return Response.status(201).entity(awayTeamJSON).build();
			} else
				return Response.status(500).entity("").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}
	
	@PUT
	@Path("/createhometeam")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public Response createhomeTeam(String homeTeamName) {

		try {
			homeTeamScore homecreate = new homeTeamScore();
			homeTeam homeTeamobj = mapper.readValue(homeTeamName, homeTeam.class);
			//awayTeamobj= mapper.readValue(oversLimit, awayTeam.class);
			boolean success = homecreate.execute(homeTeamobj);
			String homeTeamJSON = mapper.writeValueAsString(homeTeamobj);
			if (success) {
				return Response.status(201).entity(homeTeamJSON).build();
			} else
				return Response.status(500).entity("").build();
		} catch (Exception e) {
			return Response.status(500).entity(e.toString()).build();
		}
	}
	
}
