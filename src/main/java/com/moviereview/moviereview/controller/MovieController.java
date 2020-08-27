package com.moviereview.moviereview.controller;

import com.moviereview.moviereview.Dao.MovieDao;
import com.moviereview.moviereview.model.Movie;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/movie")
@RestController
public class MovieController {
    public static final String ErrorMessageSave = "unable to save data";
    public static final String ErrorMessageFetch = "unable to fetch data";

    @Autowired
    MovieDao movieDao;

    @PostMapping(value = "/addMovie")
    public ResponseEntity<Object> createMovie(@RequestBody JSONObject movie){
        JSONObject response = new JSONObject();
        try {
            response = movieDao.createMovie(movie);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("status", ErrorMessageSave);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/getMovies")
    public ResponseEntity<Object> getAllMovies (@RequestBody JSONObject userName){
        JSONObject response = new JSONObject();
        try {
            response = movieDao.getAllMovies(userName);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("status", ErrorMessageFetch);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
