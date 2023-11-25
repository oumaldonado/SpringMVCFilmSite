package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;

@Controller

public class FilmController {

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = "home.do" )
	public String goToHome() {

		return "WEB-INF/home.jsp";
	}
	@RequestMapping(path = "findFilmById.do" , method = RequestMethod.GET, params = "filmId" )
	public ModelAndView goToFindId(@RequestParam int filmId) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/IDResult.jsp");
		mv.addObject("film",filmDao.findById(filmId));
		return mv;
	}
	@RequestMapping (path ="findFilmsByKeyword.do", method = RequestMethod.POST, params = "keyword")
	public ModelAndView goToFindKeyword(@RequestParam String keyword) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/KeywordResult.jsp");
		mv.addObject("films", filmDao.findFilmsByKeyword(keyword));
		return mv;
	}
	
}