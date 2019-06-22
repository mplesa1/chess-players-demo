package org.company.demo.chess;

import org.company.demo.chess.models.Match;
import org.company.demo.chess.models.Player;
import org.company.demo.chess.repositories.MatchRepository;
import org.company.demo.chess.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChessPlayersDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessPlayersDemoApplication.class, args);
	}
	
	private String PGN = "1. e4 d6 2. d4 Nf6 3. Nc3 g6 4. Be3 Bg7 5. Qd2 c6 6. f3 b5\n" + 
			"7. Nge2 Nbd7 8. Bh6 Bxh6 9. Qxh6 Bb7 10. a3 e5 11. O-O-O Qe7\n" + 
			"12. Kb1 a6 13. Nc1 O-O-O 14. Nb3 exd4 15. Rxd4 c5 16. Rd1 Nb6\n" + 
			"17. g3 Kb8 18. Na5 Ba8 19. Bh3 d5 20. Qf4+ Ka7 21. Rhe1 d4\n" + 
			"22. Nd5 Nbxd5 23. exd5 Qd6 24. Rxd4 cxd4 25. Re7+ Kb6\n" + 
			"26. Qxd4+ Kxa5 27. b4+ Ka4 28. Qc3 Qxd5 29. Ra7 Bb7 30. Rxb7\n" + 
			"Qc4 31. Qxf6 Kxa3 32. Qxa6+ Kxb4 33. c3+ Kxc3 34. Qa1+ Kd2\n" + 
			"35. Qb2+ Kd1 36. Bf1 Rd2 37. Rd7 Rxd7 38. Bxc4 bxc4 39. Qxh8\n" + 
			"Rd3 40. Qa8 c3 41. Qa4+ Ke1 42. f4 f5 43. Kc1 Rd2 44. Qa7 1-0"; 

	
	@Bean
	public CommandLineRunner init(PlayerRepository playerRepository, MatchRepository matchRepository) {
		return (args) -> {
			Player player = new Player("Garry", "Kasparov ", 732);
			Player player2 = new Player("Veselin", "Topalov", 692);
			Player player3 = new Player("Pero", "Peric", 1231);
			Player player4 = new Player("Marko", "Markic", 1000);
			Player player5 = new Player("Jurica", "Juric", 950);
			playerRepository.save(player);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);
			playerRepository.save(player5);
			matchRepository.save(new Match(player3, player2, Match.Result.BLACK_WIN, PGN));
			matchRepository.save(new Match(player2, player4, Match.Result.BLACK_WIN, PGN));
		
		};
	}
	

}
