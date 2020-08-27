package com.moviereview.moviereview.Dao;


import com.moviereview.moviereview.model.UserProfile;
import com.moviereview.moviereview.repository.UserProfileRepository;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginDao.class);

    @Autowired
    UserProfileRepository userProfileRepository;



    public JSONObject getAuthenticated(JSONObject loginObject) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "error");
        jsonObject.put("data", "");
      try{
            UserProfile profile = new UserProfile();
            //System.out.println(userProfileRepository.findAll());
            profile = userProfileRepository.getAuthenticated((String) loginObject.getOrDefault("userName", ""), (String) loginObject.getOrDefault("password", ""));
            UserProfile.Type userType = profile.getProfileStatus();
            jsonObject.put("status", "success");
            jsonObject.put("data", userType);
        } catch (Exception e) {
            LOGGER.error("Exception while authenticating, for userName {}, exception {}", loginObject.getOrDefault("userName", ""), e);
        }
        return jsonObject;
    }
}
