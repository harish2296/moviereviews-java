package com.moviereview.moviereview.repository;

import com.moviereview.moviereview.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    @Query(value = "select * from users where user_name = ?1 and password = ?2", nativeQuery = true)
    UserProfile getAuthenticated(String userName, String password);
}
