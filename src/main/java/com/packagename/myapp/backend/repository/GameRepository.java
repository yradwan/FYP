package com.packagename.myapp.backend.repository;

import com.packagename.myapp.backend.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends  JpaRepository<Game, Long>{

}
