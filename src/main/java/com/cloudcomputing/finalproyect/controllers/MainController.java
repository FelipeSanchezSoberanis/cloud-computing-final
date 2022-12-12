package com.cloudcomputing.finalproyect.controllers;

import com.cloudcomputing.finalproyect.DTOs.CoordInfoDto;
import com.cloudcomputing.finalproyect.DTOs.IpDataDto;
import com.cloudcomputing.finalproyect.DTOs.UrlDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping
    public UrlDto getDataFromIp() {
        RestTemplate restTemplate = new RestTemplate();

        IpDataDto ipData =
                restTemplate.getForObject("https://api.ipify.org/?format=json", IpDataDto.class);

        CoordInfoDto coordInfo =
                restTemplate.getForObject(
                        String.format("https://ipinfo.io/%s/geo", ipData.getIp()),
                        CoordInfoDto.class);

        Float latMin = coordInfo.getLatitude() - 0.005F;
        Float latMax = coordInfo.getLatitude() + 0.005F;
        Float longMin = coordInfo.getLongitude() - 0.005F;
        Float longMax = coordInfo.getLongitude() + 0.005F;

        StringBuilder mapUrlBuilder = new StringBuilder();
        mapUrlBuilder.append("https://www.openstreetmap.org/export/embed.html?bbox=");
        mapUrlBuilder.append(longMin);
        mapUrlBuilder.append(",");
        mapUrlBuilder.append(latMin);
        mapUrlBuilder.append(",");
        mapUrlBuilder.append(longMax);
        mapUrlBuilder.append(",");
        mapUrlBuilder.append(latMax);
        mapUrlBuilder.append("&layer=mapnik&marker=");
        mapUrlBuilder.append(coordInfo.getLatitude());
        mapUrlBuilder.append(",");
        mapUrlBuilder.append(coordInfo.getLongitude());

        String mapUrl = mapUrlBuilder.toString();

        UrlDto urlDto = new UrlDto(mapUrl);

        return urlDto;
    }
}
