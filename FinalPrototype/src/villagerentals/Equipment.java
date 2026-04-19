public class Equipment {
    private int equipmentID;
    private String category;
    private String name;
    private String description;
    private double dailyRentalCost;

    public Equipment(int equipmentID, String category, String name, String description, double dailyRentalCost) {
        this.equipmentID = equipmentID;
        this.category = category;
        this.name = name;
        this.description = description;
        this.dailyRentalCost = dailyRentalCost;
    }

    public int getEquipmentID() { return equipmentID; }
    public String getCategory() { return category; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getDailyRentalCost() { return dailyRentalCost; }

    @Override
    public String toString() {
        return equipmentID + " - " + name + " (" + category + ") - $" + dailyRentalCost + "/day";
    }
}