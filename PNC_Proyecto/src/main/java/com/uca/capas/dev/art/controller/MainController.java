package com.uca.capas.dev.art.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class MainController {
	@RequestMapping("/")
	public ModelAndView inicio() 
	{
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("index");
		return mav;
	}
}
