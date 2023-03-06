package com.example.labboneskjakarta.controller;

import com.example.labboneskjakarta.dto.GameDto;
import com.example.labboneskjakarta.dto.GameMapper;
import com.example.labboneskjakarta.entity.Game;
import com.example.labboneskjakarta.repository.GameRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Path("/games")
public class GameController {
    @Inject
    GameRepository repository;
    @Inject
    GameMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GameDto> getAll() {
        return mapper.map(repository.findAll());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") Long id) {
        var game = repository.findOne(id);
        if (game.isPresent())
            return Response.ok().entity(mapper.map(game.get())).build();
        throw new NotFoundException(Response.status(404).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOne(@Valid Game game) {
        repository.insertGame(game);
        return Response.created(URI.create("/games" + game.getId())).build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        if (repository.findOne(id).isPresent()) {
            repository.deleteGame(id);
            return;
        }
        throw new InternalServerErrorException(Response.status(INTERNAL_SERVER_ERROR).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGame(@PathParam("id") Long id, GameDto gameDto) {
        var game = repository.findOne(id);
        if (game.isPresent())
            return Response.ok().entity(mapper.map(repository.update(id, mapper.map(gameDto)))).build();
        throw new NotFoundException(Response.status(404).build());
    }
}
