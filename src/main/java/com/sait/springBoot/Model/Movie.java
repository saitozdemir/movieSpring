package com.sait.springBoot.Model;

public class Movie {
	private String Title;
	private String Year;
	private String ImdbID;
	private String Type;
	private String Poster;
	
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getImdbID() {
		return ImdbID;
	}
	public void setImdbID(String imdbID) {
		ImdbID = imdbID;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
}
