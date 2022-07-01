package com.sait.springBoot.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sait.springBoot.Model.Movie;

@Component
public class MovieService implements IMovieService{

	@Autowired
	RestTemplate client;
	
	@Override
	public List<Movie> searchName(String movieName) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");		
		headers.add("authorization", "apikey 7scbKtC4r940AMybcSTv7k:1WEWiomotpO2JCNR7BMAMo");
		String url = "https://api.collectapi.com/imdb/imdbSearchByName?query" + movieName;
		HttpEntity<?> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, requestEntity, String.class);
		System.out.println(response.getBody());
		String resp = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		List<Movie> movies = new ArrayList<Movie>();
		try {
			JsonNode node = objectMapper.readTree(resp);
			JsonNode resultNode = node.get("result");
			if (resultNode.isArray()) {
				ArrayNode moviesNode = (ArrayNode) resultNode;
				for (int i = 0; i < moviesNode.size(); i++) {
					JsonNode singleMovie = moviesNode.get(i);
					String title = singleMovie.get("Title").toString();
					String year = singleMovie.get("Year").toString();
					String imdbId = singleMovie.get("ImdbID").toString();
					String type = singleMovie.get("Type").toString();
					Movie m = new Movie();
					m.setTitle(title);
					m.setYear(year);
					m.setImdbID(imdbId);
					m.setType(type);
					movies.add(m);					
				}
			}
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return movies;
	}
	
	@Override
	public Movie findById(String id) {

		HttpHeaders headers=new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("authorization", "apikey 7scbKtC4r940AMybcSTv7k:1WEWiomotpO2JCNR7BMAMo");
		String url="https://api.collectapi.com/imdb/imdbSearchById?movieId=" + id;
		HttpEntity<?> requestEntity=new HttpEntity<>(headers);
		ResponseEntity<String> response=client.exchange(url, HttpMethod.GET,requestEntity,String.class);
		String resp=response.getBody();
		ObjectMapper objectMapper=new ObjectMapper();
		Movie m =new Movie();
		try {
			JsonNode node=objectMapper.readTree(resp);
			JsonNode resultNode=node.get("result");
			String title=resultNode.get("Title").toString();
			String year=resultNode.get("Year").toString();
			String imdbId=resultNode.get("imdbID").toString();
			String type=resultNode.get("Type").toString();
			m.setImdbID(imdbId);
			m.setTitle(title);
			m.setType(type);
			m.setYear(year);
			
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return m;
	}
}
