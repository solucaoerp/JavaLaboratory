package com.crud.ibrplanner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {

	@RequestMapping("cadastrar-evento")
	public String form() {
		return "evento/formEvento";
	}
}
