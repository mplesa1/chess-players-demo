package org.company.demo.chess.controllers;

import org.company.demo.chess.repositories.MatchRepository;
import org.company.demo.chess.models.Match;
import org.company.demo.chess.models.Match.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {

	@Autowired
	private MatchRepository matchRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Match findMatch(@PathVariable int id) {
		return matchRepository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Match> findMatches() {
		return matchRepository.findAll();
	}
	
	@RequestMapping(value = "player/{playerId}",method = RequestMethod.GET)
	public Iterable<Match> findPlayerMatches(@PathVariable int playerId) {
		return matchRepository.findPlayerMatches(playerId);
	}
	
	@RequestMapping(value = "/getResults", method = RequestMethod.GET)
	public Result[] findResults() {
		return Match.Result.values();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Match save(@RequestBody Match match) {
//		match.setBlackId(match.getBlackId());
//		match.setWhiteId(match.getWhiteId());
		matchRepository.save(match);
		return match;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") int id, @RequestBody Match match) {
		matchRepository.findById(id).get();
		if (match.getId() == id) {
			match.setId(id);
			matchRepository.save(match);
		} 
//		else {
//			throw new BadRequestException("Id in body is not equal with id in url", "f");
//		}
	}

}
