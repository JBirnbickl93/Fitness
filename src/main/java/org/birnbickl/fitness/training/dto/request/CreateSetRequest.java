package org.birnbickl.fitness.training.dto.request;

import jakarta.validation.constraints.Min;

public class CreateSetRequest {

    @Min(value=1, message = "Satznummer muss mindestens 1 sein!")
    private int setNumber;

    @Min(value=1, message = "Wiederholungen müssen mindestens 1 sein!")
    private int repetitions;

    @Min(value=0, message = "Gewicht muss positiv sein!")
    private double weight;

    public CreateSetRequest() {
    }

    public CreateSetRequest(int setNumber, int repetitions, double weight) {
        this.setNumber = setNumber;
        this.repetitions = repetitions;
        this.weight = weight;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }




}
