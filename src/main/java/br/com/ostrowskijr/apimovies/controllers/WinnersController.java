package br.com.ostrowskijr.apimovies.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ostrowskijr.apimovies.data.CollectionWinners;
import br.com.ostrowskijr.apimovies.model.Winner;

@RestController
@RequestMapping("/winners")
public class WinnersController {
    
    private final CollectionWinners collectionWinners;

    @Autowired
    public WinnersController(CollectionWinners collectionWinners) {
        this.collectionWinners = collectionWinners;
    }

    @GetMapping()
    public List<Winner> getWinners() {
        return collectionWinners.getWinners();
    }
 }
