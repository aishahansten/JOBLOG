package com.ssafy.joblog.domain.user.repository;

import com.ssafy.joblog.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

    @Modifying
    @Query("UPDATE User user SET user.isDelete = true WHERE user.id = :userId")
    void markDeletedUser(@Param("userId") int userId);

    @Modifying
    @Query("UPDATE User user SET user.isDelete = false WHERE user.id = :userId")
    void markUndeletedUser(@Param("userId") int userId);

}
