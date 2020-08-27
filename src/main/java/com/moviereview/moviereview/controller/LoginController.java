package com.moviereview.moviereview.controller;

import com.moviereview.moviereview.Dao.LoginDao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/userProfile")
@RestController
public class LoginController {

    @Autowired
    LoginDao loginDao;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> getAuthenticated(@RequestBody JSONObject loginObject){
        JSONObject response = new JSONObject();
        try {
            response = loginDao.getAuthenticated(loginObject);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("status", "unauthorized");
            return new ResponseEntity<Object>(response, HttpStatus.FORBIDDEN);
        }
    }
}
