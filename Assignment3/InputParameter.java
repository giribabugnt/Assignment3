package assignment3;

public class InputParameter {

	private double startingSalary;
	private double incrementPercentage;
	private Frequency incrementFrquency;
	private double deductionOnIncome;
	private Frequency deductionFrequency;
	private int predictionYears;
	private double amount;
	
	
	public double getStartingSalary() {
		return startingSalary;
	}
	public void setStartingSalary(double startingSalary) {
		this.startingSalary = startingSalary;
	}
	public double getIncrementPercentage() {
		return incrementPercentage;
	}
	public void setIncrementPercentage(double incrementPercentage) {
		this.incrementPercentage = incrementPercentage;
	}
	public Frequency getIncrementFrquency() {
		return incrementFrquency;
	}
	public void setIncrementFrquency(Frequency incrementFrquency) {
		this.incrementFrquency = incrementFrquency;
	}
	public double getDeductionOnIncome() {
		return deductionOnIncome;
	}
	public void setDeductionOnIncome(double deductionOnIncome) {
		this.deductionOnIncome = deductionOnIncome;
	}
	public Frequency getDeductionFrequency() {
		return deductionFrequency;
	}
	public void setDeductionFrequency(Frequency deductionFrequency) {
		this.deductionFrequency = deductionFrequency;
	}
	public int getPredictionYears() {
		return predictionYears;
	}
	public void setPredictionYears(int predictionYears) {
		this.predictionYears = predictionYears;
	}
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "InputParameter [startingSalary=" + startingSalary + ", incrementPercentage=" + incrementPercentage
				+ ", incrementFrquency=" + incrementFrquency + ", deductionOnIncome=" + deductionOnIncome
				+ ", deductionFrequency=" + deductionFrequency + ", predictionYears=" + predictionYears + ", amount="
				+ amount + "]";
	}
	
	
	
	
	
}
