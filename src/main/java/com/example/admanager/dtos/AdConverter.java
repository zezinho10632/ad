package com.example.admanager.converter;

import com.example.admanager.dtos.AdRequestDTO;
import com.example.admanager.dtos.AdResponseDTO;
import com.example.admanager.model.Ad;

public class AdConverter {
    public static AdResponseDTO toDTO(Ad ad) {
        AdResponseDTO dto = new AdResponseDTO();
        dto.setId(ad.getId());
        dto.setTitle(ad.getTitle());
        dto.setDescription(ad.getDescription());
        dto.setAuthor(ad.getAuthor());
        return dto;
    }

    public static Ad toDomain(AdRequestDTO adRequestDTO) {
        Ad ad = new Ad();
        ad.setTitle(adRequestDTO.getTitle());
        ad.setDescription(adRequestDTO.getDescription());
        ad.setAuthor(adRequestDTO.getAuthor());
        return ad;
    }
}