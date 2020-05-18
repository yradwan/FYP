package com.packagename.myapp.backend.service;

import com.packagename.myapp.backend.entity.Banner;
import com.packagename.myapp.backend.entity.Game;
import org.springframework.stereotype.Service;
import com.packagename.myapp.backend.repository.BannerRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BannerService {
    private static final Logger LOGGER = Logger.getLogger(BannerService.class.getName());
    private BannerRepository bannerRepository;


    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;

    }

    public List<Banner> findAll() {
        return bannerRepository.findAll();
    }

    public List<Banner> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return bannerRepository.findAll();
        } else {
            return bannerRepository.search(stringFilter);
        }
    }

    public Banner find(String name){
        List<Banner> list = findAll();
        int id;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName() == name)
                return list.get(i);
        }
        return new Banner();
    }
    public long count() {
        return bannerRepository.count();
    }

    public void delete(Banner banner) {
        bannerRepository.delete(banner);
    }

    public void save(Banner banner) {
        if (banner == null) {
            LOGGER.log(Level.SEVERE,
                    "Banner is null. Are you sure you have connected your form to the application?");
            return;
        }
        bannerRepository.save(banner);
    }
    public  Long getRarity1total(String searchTerm){
        return bannerRepository.getrarity1(searchTerm);
    }
    public  Long getRarity2total(String searchTerm){
        return bannerRepository.getrarity2(searchTerm);
    }
    public  Long getRarity3total(String searchTerm){
        return bannerRepository.getrarity3(searchTerm);
    }
    public  Long getRarity4total(String searchTerm){
        return bannerRepository.getrarity4(searchTerm);
    }
    public  Long getRarity5total(String searchTerm){
        return bannerRepository.getrarity5(searchTerm);
    }
    public  Long getRarity6total(String searchTerm){
        return bannerRepository.getrarity6(searchTerm);
    }
    public  Long getRarity7total(String searchTerm){
        return bannerRepository.getrarity7(searchTerm);
    }
    public  Long getRarity8total(String searchTerm){
        return bannerRepository.getrarity8(searchTerm);
    }
    public  Long getRarity9total(String searchTerm) { return bannerRepository.getrarity9(searchTerm); }
    public  Long getRarity10total(String searchTerm){
        return bannerRepository.getrarity10(searchTerm);
    }

    @PostConstruct
    public void populateTestData() {

        if (bannerRepository.count() == 0) {
            int i = 0;
            Random r = new Random(0);
            List<Banner> banners;
            bannerRepository.saveAll(Stream.of("gbf0520 yradwan", "fgo0520 yradwan", "fif0520 test", "gbf0520 test", "fgo0520 test", "fif0520 yradwan", "gbf0520 sderno", "fgo0520 sderno", "fif0520 sderno", "gbf0520 admin", "fgo0520 admin", "fif0520 admin" )
                    .map(name -> {
                        String[] split = name.split(" ");
                        Banner banner = new Banner();
                        banner.setGameID(split[0]);
                        banner.setUserName(split[1]);
                        banner.setRarity1("R");
                        banner.setRarity2("SR");
                        banner.setRarity3("SSR");
                        banner.setRarity1Value(r.nextInt(100));
                        banner.setRarity2Value(r.nextInt(30));
                        banner.setRarity3Value(r.nextInt(3));
                        return banner;
                    }).collect(Collectors.toList()));

        }
    }
}
