package org.company.demo.chess.controllers;

import org.company.demo.chess.exceptions.PlayerHasMatchesException;
import org.company.demo.chess.repositories.MatchRepository;
import org.company.demo.chess.repositories.PlayerRepository;
import org.company.demo.chess.exceptions.BadRequestException;
import org.company.demo.chess.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private MatchRepository matchRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Player findPlayer(@PathVariable int id) {
		return playerRepository.findById(id).get();
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Player> findPlayers() {
		return playerRepository.findAllByOrderByScoreDesc();
	}

	@RequestMapping(method = RequestMethod.POST)
	public Player save(@RequestBody Player player) {
		playerRepository.save(player);
		return player;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") int id, @RequestBody Player player) {
		playerRepository.findById(id).get();
		if (player.getId() == id) {
			player.setId(id);
			playerRepository.save(player);
			return new ResponseEntity<Object>("{\"success\" : true }", new HttpHeaders(), HttpStatus.ACCEPTED);
		} else {
			BadRequestException ex = new BadRequestException("Id in body is not equal with id in url",
					"Id_is_not_equal");
			return ex.throwBadRequestException();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePlayer(@PathVariable int id) {
		System.out.println(matchRepository.countPlayerMatches(id));
		if (id != 0) {
			if (matchRepository.countPlayerMatches(id) == 0) {
				playerRepository.findById(id);
				playerRepository.deleteById(id);
			} else {
				PlayerHasMatchesException ex = new PlayerHasMatchesException("Player has matches!", "has_matches");
				return ex.throwBadRequestException();
			}
		} 
		return new ResponseEntity<Object>("{\"success\" : true }", new HttpHeaders(), HttpStatus.ACCEPTED);
	}

}
