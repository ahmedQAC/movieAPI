package com.qa.persistence.repository;

public interface MovieRepository {
	
	String getAllMovies();
	String createMovie(String accout);
	String deleteMovie(Long id);
}
