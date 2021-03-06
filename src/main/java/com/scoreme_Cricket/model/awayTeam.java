package com.scoreme_Cricket.model;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class awayTeam {

	private String awayTeamName;
	private int awayruns, awaywickets, awayextras;
	private double awayovers, awayrunrate, awayoverslimit;
	public String getAwayTeamName() {
		return awayTeamName;
	}
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}
	public int getAwayruns() {
		return awayruns;
	}
	public void setAwayruns(int awayruns) {
		this.awayruns = awayruns;
	}
	public int getAwaywickets() {
		return awaywickets;
	}
	public void setAwaywickets(int awaywickets) {
		this.awaywickets = awaywickets;
	}
	public int getAwayextras() {
		return awayextras;
	}
	public void setAwayextras(int awayextras) {
		this.awayextras = awayextras;
	}
	public double getAwayovers() {
		return awayovers;
	}
	public void setAwayovers(double awayovers) {
		this.awayovers = awayovers;
	}
	public double getAwayrunrate() {
		return awayrunrate;
	}
	public void setAwayrunrate(double awayrunrate) {
		this.awayrunrate = awayrunrate;
	}
	public double getAwayoverslimit() {
		return awayoverslimit;
	}
	public void setAwayoverslimit(double awayoverslimit) {
		this.awayoverslimit = awayoverslimit;
	}
	

}
