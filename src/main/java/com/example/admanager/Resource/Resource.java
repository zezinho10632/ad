package com.example.admanager.Resource;

import com.example.admanager.model.Ad;
import com.example.admanager.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/ads")
public class Resource {

    @Autowired
    private AdService adService;

    @PostMapping
    public Ad createAd(@RequestBody Ad ad) {
        return adService.createAd(ad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable Long id, @RequestBody Ad adDetails) {
        Ad updatedAd = adService.updateAd(id, adDetails);
        if (updatedAd != null) {
            return ResponseEntity.ok(updatedAd);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/author")
    public ResponseEntity<Ad> updateAdAuthor(@PathVariable Long id, @RequestBody String author) {
        Ad updatedAd = adService.updateAdAuthor(id, author);
        if (updatedAd != null) {
            return ResponseEntity.ok(updatedAd);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Ad> getAllAds(@RequestParam(required = false) String title) {
        if (title != null) {
            return adService.getAdsByTitle(title);
        }
        return adService.getAllAds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable Long id) {
        Optional<Ad> ad = adService.getAdById(id);
        if (ad.isPresent()) {
            return ResponseEntity.ok(ad.get());
        }
        return ResponseEntity.notFound().build();
    }
}