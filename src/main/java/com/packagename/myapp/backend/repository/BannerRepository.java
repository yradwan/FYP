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
    //need to fix jpql
    @Query(value = "select sum(b.rarity1Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch1, '%')) ", nativeQuery = true) //
    Long getrarity1(@Param("raritySearch1") String raritySearch1);

    @Query(value = "select sum(b.rarity2Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch2, '%')) ", nativeQuery = true) //
    Long getrarity2(@Param("raritySearch2") String raritySearch2);

    @Query(value = "select sum(b.rarity3Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch3, '%')) ", nativeQuery = true) //
    Long getrarity3(@Param("raritySearch3") String raritySearch3);

    @Query(value = "select sum(b.rarity4Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch4, '%')) ", nativeQuery = true) //
    Long getrarity4(@Param("raritySearch4") String raritySearch4);

    @Query(value = "select sum(b.rarity5Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch5, '%')) ", nativeQuery = true) //
    Long getrarity5(@Param("raritySearch5") String raritySearch5);

    @Query(value = "select sum(b.rarity6Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch6, '%')) ", nativeQuery = true) //
    Long getrarity6(@Param("raritySearch6") String raritySearch6);

    @Query(value = "select sum(b.rarity7Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch7, '%')) ", nativeQuery = true) //
    Long getrarity7(@Param("raritySearch7") String raritySearch7);

    @Query(value = "select sum(b.rarity8Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch8, '%')) ", nativeQuery = true) //
    Long getrarity8(@Param("raritySearch8") String raritySearch8);

    @Query(value = "select sum(b.rarity9Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch9, '%')) ", nativeQuery = true) //
    Long getrarity9(@Param("raritySearch9") String raritySearch9);

    @Query(value = "select sum(b.rarity10Value) from Banner b" +
            "where lower(b.gameID) like lower(concat('%', :raritySearch10, '%')) ", nativeQuery = true) //
    Long getrarity10(@Param("raritySearch10") String raritySearch10);
}


