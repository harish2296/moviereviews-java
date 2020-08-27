package com.moviereview.moviereview.controller;

import com.moviereview.moviereview.Dao.ReviewDao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/review")
@RestController
public class ReviewController {

    public static final String ErrorMessageSave = "unable to save data";

    @Autowired
    ReviewDao reviewDao;

    @PostMapping(value = "/createReview/{movie_id}")
    public ResponseEntity<Object> createReviewByMovieId(@PathVariable("movie_id") Integer movieId,
                                                    @RequestBody JSONObject review){
        JSONObject response = new JSONObject();
        try {
            response = reviewDao.createReview(review, movieId);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("status", ErrorMessageSave);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}

