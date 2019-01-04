package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistence.repository.MovieRepository;

public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieRepository repo;
	
	public String getAllMovies() {
		return repo.getAllMovies();
	}

	public String addMovie(String movie) {
		if (movie.equalsIgnoreCase("titanic")) {
			return "{\"message\": \"Titanic is awful we are not adding this\"}";
		}
		return repo.createMovie(movie);
	}

	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}
	
	public void setRepo(MovieRepository repo) {
		this.repo = repo;
	}
}
