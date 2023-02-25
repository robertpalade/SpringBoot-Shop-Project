package com.shop.project.models;

import java.util.List;

public enum Status {
    BEGGINER("Begginer", 0.0, List.of("b1", "b2")),
    INTERMEDIATE("Intermediate", 2500.0, List.of("b3", "b4")),
    ADVANCED("Advanced", 7500.0, List.of("b5", "b6"));
    String name;
    Double plafon;
    List<String> benefits;

    Status(String name, Double plafon, List<String> benefits) {
        this.name = name;
        this.plafon = plafon;
        this.benefits = benefits;
    }

    public static Status setPlafon(Double plafon) {
        if(plafon >= Status.ADVANCED.plafon){
            return Status.ADVANCED;
        }
        if (plafon >= Status.INTERMEDIATE.plafon) {
            return Status.INTERMEDIATE;
        }
        return Status.BEGGINER;
    }
}
