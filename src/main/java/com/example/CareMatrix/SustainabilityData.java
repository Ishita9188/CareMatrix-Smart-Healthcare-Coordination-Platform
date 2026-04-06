package com.example.CareMatrix;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SustainabilityData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String month;
    private int year;

    private double electricityUsage;
    private double generatorFuel;
    private double solarEnergy;
    private double energyCost;

    private double biomedicalWaste;
    private double totalMedicinesPurchased;
    private double expiredMedicines;
    private double plasticWaste;
    private double recycledWaste;

    private double waterUsage;
    private double rainwaterHarvested;
    private double wastewaterRecycled;

    private double carbonEmission;
    private double medicineWastePercent;
    private double recyclingRate;
    private double renewableEnergyPercent;
    private double sustainabilityScore;
    private String sustainabilityGrade;

    private LocalDateTime submittedAt;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getElectricityUsage() {
		return electricityUsage;
	}

	public void setElectricityUsage(double electricityUsage) {
		this.electricityUsage = electricityUsage;
	}

	public double getGeneratorFuel() {
		return generatorFuel;
	}

	public void setGeneratorFuel(double generatorFuel) {
		this.generatorFuel = generatorFuel;
	}

	public double getSolarEnergy() {
		return solarEnergy;
	}

	public void setSolarEnergy(double solarEnergy) {
		this.solarEnergy = solarEnergy;
	}

	public double getEnergyCost() {
		return energyCost;
	}

	public void setEnergyCost(double energyCost) {
		this.energyCost = energyCost;
	}

	public double getBiomedicalWaste() {
		return biomedicalWaste;
	}

	public void setBiomedicalWaste(double biomedicalWaste) {
		this.biomedicalWaste = biomedicalWaste;
	}

	public double getTotalMedicinesPurchased() {
		return totalMedicinesPurchased;
	}

	public void setTotalMedicinesPurchased(double totalMedicinesPurchased) {
		this.totalMedicinesPurchased = totalMedicinesPurchased;
	}

	public double getExpiredMedicines() {
		return expiredMedicines;
	}

	public void setExpiredMedicines(double expiredMedicines) {
		this.expiredMedicines = expiredMedicines;
	}

	public double getPlasticWaste() {
		return plasticWaste;
	}

	public void setPlasticWaste(double plasticWaste) {
		this.plasticWaste = plasticWaste;
	}

	public double getRecycledWaste() {
		return recycledWaste;
	}

	public void setRecycledWaste(double recycledWaste) {
		this.recycledWaste = recycledWaste;
	}

	public double getWaterUsage() {
		return waterUsage;
	}

	public void setWaterUsage(double waterUsage) {
		this.waterUsage = waterUsage;
	}

	public double getRainwaterHarvested() {
		return rainwaterHarvested;
	}

	public void setRainwaterHarvested(double rainwaterHarvested) {
		this.rainwaterHarvested = rainwaterHarvested;
	}

	public double getWastewaterRecycled() {
		return wastewaterRecycled;
	}

	public void setWastewaterRecycled(double wastewaterRecycled) {
		this.wastewaterRecycled = wastewaterRecycled;
	}

	public double getCarbonEmission() {
		return carbonEmission;
	}

	public void setCarbonEmission(double carbonEmission) {
		this.carbonEmission = carbonEmission;
	}

	public double getMedicineWastePercent() {
		return medicineWastePercent;
	}

	public void setMedicineWastePercent(double medicineWastePercent) {
		this.medicineWastePercent = medicineWastePercent;
	}

	public double getRecyclingRate() {
		return recyclingRate;
	}

	public void setRecyclingRate(double recyclingRate) {
		this.recyclingRate = recyclingRate;
	}

	public double getRenewableEnergyPercent() {
		return renewableEnergyPercent;
	}

	public void setRenewableEnergyPercent(double renewableEnergyPercent) {
		this.renewableEnergyPercent = renewableEnergyPercent;
	}

	public double getSustainabilityScore() {
		return sustainabilityScore;
	}

	public void setSustainabilityScore(double sustainabilityScore) {
		this.sustainabilityScore = sustainabilityScore;
	}

	public String getSustainabilityGrade() {
		return sustainabilityGrade;
	}

	public void setSustainabilityGrade(String sustainabilityGrade) {
		this.sustainabilityGrade = sustainabilityGrade;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

}