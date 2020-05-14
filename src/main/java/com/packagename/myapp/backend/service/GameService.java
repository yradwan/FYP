package com.packagename.myapp.backend.service;

import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        gameRepository.save(game);
    }
    //
    //to add data loader
    //

}
