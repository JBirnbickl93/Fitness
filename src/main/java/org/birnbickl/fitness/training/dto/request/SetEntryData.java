package org.birnbickl.fitness.training.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class SetEntryData {



    @Min(value = 0, message = "Set-Nummer darf nicht negativ sein") @Max(value = 100, message = "Maximale Anzahl an Sets pro Übung überschritten")
    private int setNumber;
    @Min(value = 0, message = "Wiederholungen dürfen nicht negativ sein") @Max(value = 100, message = "Maximale Anzahl an Wiederholungen überschritten")
    private int repetitions;
    @Min(value = 0, message = "Gewicht darf nicht negativ sein") @Max(value = 1000, message = "Maximales Gewicht überschritten")
    private double weight;

    protected SetEntryData() {}

    public SetEntryData(int setNumber, int repetitions, double weight) {
        this.setNumber = setNumber;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public double getWeight() {
        return weight;
    }
}
