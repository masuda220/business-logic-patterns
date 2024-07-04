package com.example.domain.model.jjugccc2024.advanced.routing;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

class PlaceList {
    List<Place> places;

    PlaceList(List<Place> places) {
        this.places = places;
    }

    static PlaceList of(List<Place> places) {
        return new PlaceList(places);
    }

    List<Place> 除外(Set<Place> 除外する地点) {
        return places.stream()
                .filter(Predicate.not(除外する地点::contains))
                .toList();
    }

    int size() {
        return places.size();
    }

}
