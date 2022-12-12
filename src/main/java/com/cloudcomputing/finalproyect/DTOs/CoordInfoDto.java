package com.cloudcomputing.finalproyect.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoordInfoDto {

    private String ip;
    private String hostname;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private String postal;
    private String timezone;
    private String readme;

    private Optional<Float> latitude = Optional.empty();
    private Optional<Float> longitude = Optional.empty();

    public Float getLatitude() {
        if (latitude.isPresent()) return latitude.get();

        setLatitude(Optional.of(Float.valueOf(getLoc().split(",")[0])));

        return getLatitude();
    }

    public Float getLongitude() {
        if (longitude.isPresent()) return longitude.get();

        setLongitude(Optional.of(Float.valueOf(getLoc().split(",")[1])));

        return getLongitude();
    }
}
