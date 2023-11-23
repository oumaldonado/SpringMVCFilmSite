package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Film;

public class FilmDaoJdbcImpl implements FilmDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private final static String USERNAME = "student";
	private final static String PWD = "student";
	
	
	public FilmDaoJdbcImpl() {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	    }
	    catch (ClassNotFoundException e) {
	      e.printStackTrace();
	      System.err.println("Error loading MySQL Driver");
	      throw new RuntimeException("Unable to load MySQL Driver class");
	    }
	  }

	
	
	@Override
	public Film findById(int filmId)  {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PWD);

			String sql = "SELECT film.*, language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, filmId);

			ResultSet filmResult = stmt.executeQuery();

			if (filmResult.next()) {

				film = new Film();

				film.setFilmId(filmResult.getInt("id"));
				film.setTitle(filmResult.getString("title"));
				film.setDescription(filmResult.getString("description"));
				film.setReleaseYear(filmResult.getShort("release_year"));
				film.setLangId(filmResult.getInt("language_id"));
				film.setLanguage(filmResult.getString("name"));
				film.setRentDur(filmResult.getInt("rental_duration"));
				film.setRate(filmResult.getDouble("rental_rate"));
				film.setLength(filmResult.getInt("length"));
				film.setRepCost(filmResult.getDouble("replacement_cost"));
				film.setRating(filmResult.getString("rating"));
				film.setFeatures(filmResult.getString("special_features"));

			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return film;
	
	}

	@Override
	public List<Film> findFilmsByKeyword(String keyword) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USERNAME, PWD);
			String sql = "SELECT film.*, language.name FROM film JOIN language ON language.id =film.language_id WHERE film.title LIKE ? OR film.description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				String language = rs.getString("name");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				Film film = new Film(filmId, title, description, releaseYear, langId, language, rentDur, rate, length,
						repCost, rating, features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return films;

	}


}
