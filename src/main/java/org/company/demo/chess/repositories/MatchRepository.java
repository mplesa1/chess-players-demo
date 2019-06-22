package org.company.demo.chess.repositories;

import org.company.demo.chess.models.Match;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<Match, Integer> {
	@Query(value = "SELECT * FROM match m WHERE m.black = :playerId OR m.white = :playerId", nativeQuery = true)
	public Iterable<Match> findPlayerMatches(@Param("playerId") int playerId);

	@Query(value = "SELECT COUNT(*) FROM match m WHERE m.black = :playerId OR m.white = :playerId", nativeQuery = true)
	public int countPlayerMatches(@Param("playerId") int playerId);
}
