package com.example.admanager.service;

import com.example.admanager.model.Ad;
import com.example.admanager.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    public Ad createAd(Ad ad) {
        return adRepository.save(ad);
    }

    public Ad updateAd(Long id, Ad adDetails) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            ad.setAuthor(adDetails.getAuthor());
            ad.setTitle(adDetails.getTitle());
            ad.setDescription(adDetails.getDescription());
            return adRepository.save(ad);
        }
        return null;
    }

    public Ad updateAdAuthor(Long id, String author) {
        Optional<Ad> optionalAd = adRepository.findById(id);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            ad.setAuthor(author);
            return adRepository.save(ad);
        }
        return null;
    }

    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }

    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    public Optional<Ad> getAdById(Long id) {
        return adRepository.findById(id);
    }

    public List<Ad> getAdsByTitle(String title) {
        return adRepository.findByTitleContainingIgnoreCase(title);
    }
}