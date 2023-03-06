package com.example.labboneskjakarta.dto;

import com.example.labboneskjakarta.entity.Game;

import java.util.List;

public class GameMapper {
    public List<GameDto> map(List<Game> all) {
        return all.stream().map(GameDto::new).toList();
    }

    public GameDto map(Game game) {
        return new GameDto(game);
    }

    public Game map(GameDto game) {
        var g = new Game();
        g.setId(game.getId());
        g.setName(game.getName());
        g.setShortening(game.getShortening());
        return g;
    }
}
