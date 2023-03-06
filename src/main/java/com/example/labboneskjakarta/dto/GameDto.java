package com.example.labboneskjakarta.dto;

import com.example.labboneskjakarta.entity.Game;

public class GameDto {
    private Long id;
    private String name;
    private String shortening;

    public GameDto(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.shortening = game.getShortening();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortening() {
        return shortening;
    }

    public void setShortening(String shortening) {
        this.shortening = shortening;
    }
}
