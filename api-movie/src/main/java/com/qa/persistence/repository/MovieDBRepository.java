package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.business.service.MovieService;
import com.qa.persistence.domain.Movie;
import com.qa.persistence.domain.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class MovieDBRepository implements MovieRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;


	public String getAllMovies() {
		Query query = manager.createQuery("Select a FROM Movie a");
		Collection<Movie> movies = (Collection<Movie>) query.getResultList();
		return util.getJSONForObject(movies);
	}
	

	@Transactional(REQUIRED)
	public String createMovie(String accout) {
		Movie aMovie = util.getObjectForJSON(accout, Movie.class);
		manager.persist(aMovie);
		return "{\"message\": \"movie has been sucessfully added\"}";
	}


	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		Movie movieInDB = findMovie(id);
		if (movieInDB != null) {
			manager.remove(movieInDB);
		}
		return "{\"message\": \"movie sucessfully deleted\"}";
	}

	private Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
	
}

