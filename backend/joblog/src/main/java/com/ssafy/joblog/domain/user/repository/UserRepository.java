package com.ssafy.joblog.domain.user.repository;

import com.ssafy.joblog.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

<<<<<<< HEAD
=======
//    public User findById(int id);

>>>>>>> e4deec38b544d2472615973aac3d417658b6423e
}
