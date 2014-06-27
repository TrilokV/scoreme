package com.scoreme_Cricket.services;

import com.scoreme_Cricket.command.*;
import com.scoreme_Cricket.model.*;
import com.scoreme_Cricket.mongo.*;
import com.scoreme_Cricket.updateScore.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

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
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAwayTeams() {
		listawayteams listawayTeams = new listawayteams();
		ArrayList<DBObject> list = listawayTeams.execute();
		return Response.status(200).entity(list).build();
	}
	@GET
	@Path("/{awayTeamName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAwayTeam(@PathParam("awayTeamName") String awayTeamName) {
		getAwayTeamCommand getawayCommand = new getAwayTeamCommand();
		DBObject away = getawayCommand.execute(awayTeamName);
		return Response.status(200).entity(away).build();
	}
	/*@GET
	@Path("/{homeTeamName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHomeTeam(@PathParam("homeTeamName") String homeTeamName) {
		getHomeTeamCommand gethomeCommand = new getHomeTeamCommand();
		DBObject away = gethomeCommand.execute(homeTeamName);
		return Response.status(200).entity(away).build();
	}*/
	@PUT
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
	
}
