package com.moviereview.moviereview.Dao;

import com.moviereview.moviereview.model.Movie;
import com.moviereview.moviereview.model.Review;
import com.moviereview.moviereview.repository.MovieRepository;
import com.moviereview.moviereview.repository.ReviewRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);
    public static final String ErrorMessage = "unable to save data";

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MovieRepository movieRepository;



    public JSONObject createReview(JSONObject request, Integer movieId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("data", ErrorMessage);
        try {
            Review review = new Review();
            review.setReviews((String) request.getOrDefault("reviews", ""));
            review.setUserName((String) request.getOrDefault("userName", ""));
            Movie movie = new Movie();
            movie = movieRepository.findById(movieId).get();
            review.setMovie(movie);
            Review reviewObj = reviewRepository.save(review);
            if(reviewObj != null) {
                LOGGER.info("Review Created by {} , movieId {}", request.getOrDefault("userName", ""), movieId);
                jsonObject.put("status", "success");
                jsonObject.put("data", "Created Successfully");
            }
        } catch (Exception e) {
            LOGGER.error("Exception while saving review, for movieId {}, exception {}", movieId, e);
        }
        return jsonObject;
    }
}
