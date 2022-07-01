package com.sait.springBoot.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sait.springBoot.Model.Movie;
import com.sait.springBoot.Service.IMovieService;

@RestController
public class MovieController {
	
	@Autowired
	private IMovieService service;
	
	@RequestMapping(path = "/movie/search", method = RequestMethod.GET)
	public List<Movie> search(@RequestParam(name = "movie_name") String name){		
		return this.service.searchName(name);
	}
	
	@RequestMapping(path = "/movie/save", method = RequestMethod.GET)
	public boolean addToList(@RequestParam(name = "id") String id) throws IOException{		
		Movie movie = this.service.findById(id);
		String fileLine = movie.getImdbID() + "," + movie.getTitle() + "," + movie.getYear() + "," + movie.getPoster();
		BufferedWriter write = new BufferedWriter(new FileWriter(new File("movie.txt")));
		write.write(fileLine);
		write.newLine();
		write.close();
		return true;
	}
	@SuppressWarnings("resource")
	@RequestMapping(path = "/movies/detail/{id}", method = RequestMethod.GET)
	public Movie getMovieById(@RequestParam(name = "id") String id) throws IOException{		
		
		BufferedReader read =new BufferedReader(new FileReader("movie.txt"));
		String fileLine = read.readLine();
		while (fileLine != null) {
			String[] parts=fileLine.split(",");
			if(parts[0].equals(id)) {
				Movie m =new Movie();
				m.setImdbID(parts[0]);
				m.setTitle(parts[1]);
				m.setType(parts[2]);
				m.setYear(parts[3]);
				m.setPoster(parts[4]);
				read.close();
				return m;
			}else {
				fileLine = read.readLine();
			}
		}
		return this.service.findById(id);
	}     
	
}
