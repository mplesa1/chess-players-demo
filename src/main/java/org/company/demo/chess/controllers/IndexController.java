package org.company.demo.chess.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class IndexController {

    @GetMapping("/")
	public String index() {
		return "index";
	}
    
    @GetMapping("/match")
  	public String matches() {
  		return "match";
  	}

}
