package com.packagename.myapp.backend.service;

import com.packagename.myapp.backend.entity.Banner;
import org.springframework.stereotype.Service;
import com.packagename.myapp.backend.repository.BannerRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public  int getRarity1total(String searchTerm){
        return bannerRepository.getrarity1(searchTerm);
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


}
