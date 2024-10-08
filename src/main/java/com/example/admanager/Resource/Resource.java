package com.example.admanager.Resource;

import com.example.admanager.model.Ad;
import com.example.admanager.dtos.AdRequestDTO;
import com.example.admanager.dtos.AdResponseDTO;
import com.example.admanager.service.AdService;
import com.example.admanager.converter.AdConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/api/ads")
public class Resource {

    @Autowired
    private AdService adService;

    @PostMapping
    public AdResponseDTO createAd(@RequestBody AdRequestDTO adRequestDTO) {
        Ad ad = AdConverter.toDomain(adRequestDTO);
        Ad createdAd = adService.createAd(ad);
        return AdConverter.toDTO(createdAd);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdResponseDTO> updateAd(@PathVariable Long id, @RequestBody AdRequestDTO adRequestDTO) {
        Ad adDetails = AdConverter.toDomain(adRequestDTO);
        Ad updatedAd = adService.updateAd(id, adDetails);
        if (updatedAd != null) {
            return ResponseEntity.ok(AdConverter.toDTO(updatedAd));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/author")
    public ResponseEntity<AdResponseDTO> updateAdAuthor(@PathVariable Long id, @RequestBody String author) {
        Ad updatedAd = adService.updateAdAuthor(id, author);
        if (updatedAd != null) {
            return ResponseEntity.ok(AdConverter.toDTO(updatedAd));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<AdResponseDTO> getAllAds(@RequestParam(required = false) String title) {
        List<Ad> ads = (title == null) ? adService.getAllAds() : adService.getAdsByTitle(title);
        return ads.stream().map(AdConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdResponseDTO> getAdById(@PathVariable Long id) {
        Ad ad = adService.getAdById(id).orElse(null);
        if (ad != null) {
            return ResponseEntity.ok(AdConverter.toDTO(ad));
        }
        return ResponseEntity.notFound().build();
    }
}