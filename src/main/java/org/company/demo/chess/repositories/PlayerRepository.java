package org.company.demo.chess.repositories;

import org.company.demo.chess.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
	Iterable<Player> findAllByOrderByScoreDesc();
}
