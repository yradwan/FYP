package com.packagename.myapp.backend.repository;

import com.packagename.myapp.backend.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends  JpaRepository<Game, Long>{

    @Query("select g from Game g " +
            "where lower(g.gameName) like lower(concat('%', :searchTerm, '%')) ") //
    List<Game> search(@Param("searchTerm") String searchTerm); //

}
