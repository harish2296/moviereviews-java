package com.moviereview.moviereview.Dao;

import com.moviereview.moviereview.model.Movie;
import com.moviereview.moviereview.repository.MovieRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);
    public static final String ErrorMessage = "unable to save data";

    @Autowired
    MovieRepository movieRepository;

    public JSONObject createMovie(JSONObject movie) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("data", ErrorMessage);
        try {
            Movie movieFile = new Movie();
            movieFile.setMovieName((String) movie.getOrDefault("movieName", ""));
            movieFile.setSynopsis((String) movie.getOrDefault("synopsis", ""));
            Movie movieSavedObj = movieRepository.save(movieFile);
            if(movieSavedObj != null) {
                LOGGER.info("Movie Created by {} , movieName {}", movie.getOrDefault("userName", ""), movie.getOrDefault("movieName", ""));
                Integer userType = movieSavedObj.getId();
                jsonObject.put("status", "success");
                jsonObject.put("data", userType);
            }
        } catch (Exception e) {
            LOGGER.error("Exception while saving, for movieName {}, exception {}", movie.getOrDefault("movieName", ""), e);
        }
        return jsonObject;
    }

    public JSONObject getAllMovies(JSONObject userName) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("data", ErrorMessage);
        try {
            LOGGER.info("Movie details fetched by {}", userName.getOrDefault("userName", ""));
            List<Movie> listOfMovies = movieRepository.findAll();
            if(listOfMovies.size() != 0) {
                jsonObject.put("status", "success");
                jsonObject.put("data", listOfMovies);
            }
        } catch (Exception e) {
            LOGGER.error("Exception while getting movies, for user {}, exception {}", userName.getOrDefault("userName", ""), e);
        }
        return jsonObject;
    }
}
