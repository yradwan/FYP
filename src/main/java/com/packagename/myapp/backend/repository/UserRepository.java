package com.packagename.myapp.backend.repository;

import com.packagename.myapp.backend.entity.Game;
import com.packagename.myapp.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "where lower(u.userName) like lower(concat('%', :searchTerm, '%')) ") //
    List<User> search(@Param("searchTerm") String searchTerm); //
}
