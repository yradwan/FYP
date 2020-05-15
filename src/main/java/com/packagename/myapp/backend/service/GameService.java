package com.packagename.myapp.backend.service;

import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GameService {

    private static final Logger LOGGER = Logger.getLogger(GameService.class.getName());
    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }
    public List<Game> findAll() {
        return gameRepository.findAll();
    }
    public long count() {
        return gameRepository.count();
    }
    public void delete(Game game) {
        gameRepository.delete(game);
    }
    public void save(Game game) {
        if (game == null) {
            LOGGER.log(Level.SEVERE,
                    "Game is null. Are you sure you have connected your form to the application?");
            return;
        }
        gameRepository.save(game);
    }

    public List<Game> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return gameRepository.findAll();
        } else {
            return gameRepository.search(stringFilter);
        }
    }

    @PostConstruct
    public void populateTestData() {

        if (gameRepository.count() == 0) {
            int i = 0;
            Random r = new Random(0);
            gameRepository.saveAll(
                    Stream.of("Granblue Fantasy (2020/05)", "Fate/Grand Order (2020/05)", "Fifa 2020 (2020/05)")
                            .map(name -> {
                                Game game = new Game();
                                game.setGameName(name);
                                game.setCostPerIGC(r.nextInt(100));
                                game.setCostPerSingleRoll(r.nextInt(5));
                                game.setGameID(name.charAt(0) + ""+ Integer.toString(r.nextInt(90)));
                                return game;
                            }).collect(Collectors.toList()));
        }
    }
    //
    //to add data loader
    //

}
