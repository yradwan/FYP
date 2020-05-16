package com.packagename.myapp.backend.repository;

import com.packagename.myapp.backend.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    @Query("select b from Banner b " +
            "where lower(b.gameID) like lower(concat('%', :searchTerm, '%')) ") //
    List<Banner> search(@Param("searchTerm") String searchTerm);

    @Query("select SUM(b.rarity1value) from Banner b" + "where lower(b.gameID) like lower(concat('%', :raritySearch1, '%')) ") //
    int getrarity1(@Param("raritySearch1") String raritySearchTerm1);
}
