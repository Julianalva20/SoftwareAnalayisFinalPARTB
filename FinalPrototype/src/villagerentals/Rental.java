package villagerentals;

public class Rental {
	private String rentalId;
	private String customerId;
	private String equipmentId;
	private String rentalDate;
	private String returnDate;
	private double totalCost;
	
	// Constructor
	
	public Rental (String rentalId, String customerId, String equipmentId,
			String rentalDate, String returnDate, double totalCost) {
		
		this.rentalId = rentalId;
		this.customerId = customerId;
		this.equipmentId = equipmentId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.totalCost = totalCost;
		
	}
	
	// Getters
	
	public String getRentalId() {
		return rentalId;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public String getEquipmentId() {
		return equipmentId;
	}
	
	public String getRentalDate() {
		return rentalDate;
	}
	
	public String getReturnDate() {
		return returnDate;
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	// Setters
	

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return rentalId + "," + customerId + "," + equipmentId + "," +
               rentalDate + "," + returnDate + "," + totalCost;
    }
	
}
