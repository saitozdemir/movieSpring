package com.sait.springBoot.Service;


import java.util.List;

import com.sait.springBoot.Model.Movie;

public interface IMovieService {
	public List<Movie> searchName(String movieName);
	public Movie findById(String id);	
}
