package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmDAO {
	Film findById(int filmId) ;
	
	List<Film> findFilmsByKeyword(String keyword);

	List<Actor> findActorsByFilmId(int filmId);
	
}
