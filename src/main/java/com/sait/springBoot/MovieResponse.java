package com.sait.springBoot;

import java.util.List;

import com.sait.springBoot.Model.Movie;

public class MovieResponse {
	private boolean succsess;
	private List<Movie> result;
	
	public List<Movie> getResult() {
		return result;
	}
	public void setResult(List<Movie> result) {
		this.result = result;
	}
	
	public boolean isSuccsess() {
		return succsess;
	}
	public void setSuccsess(boolean succsess) {
		this.succsess = succsess;
	}
	
}
