package com.example.spring_pawn_app.DTO;

public class FinaceDTO {
    private Double currentFinace;
    private Double totalFinace;
    private Double investment;
    private Double totalExpectedInterest;

    public FinaceDTO(Double currentFinace, Double totalFinace, Double investment, Double totalExpectedInterest) {
        this.currentFinace = currentFinace;
        this.totalFinace = totalFinace;
        this.investment = investment;
        this.totalExpectedInterest = totalExpectedInterest;
    }

    public FinaceDTO() {
    }

    public Double getCurrentFinace() {
        return currentFinace;
    }

    public void setCurrentFinace(Double currentFinace) {
        this.currentFinace = currentFinace;
    }

    public Double getTotalFinace() {
        return totalFinace;
    }

    public void setTotalFinace(Double totalFinace) {
        this.totalFinace = totalFinace;
    }

    public Double getInvestment() {
        return investment;
    }

    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    public Double getInterest() {
        return totalExpectedInterest;
    }

    public void setInterest(Double totalExpectedInterest) {
        this.totalExpectedInterest = totalExpectedInterest;
    }
}
