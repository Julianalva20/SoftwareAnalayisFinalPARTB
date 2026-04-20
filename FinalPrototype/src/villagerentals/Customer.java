package villageRental;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean isBanned;

    public Customer(int customerId, String firstName, String lastName, String phone, String email, boolean isBanned) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.isBanned = isBanned;
    }

    public int getCustomerId() 
    { 
    	return customerId;
    }
    
    public String getFirstName() 
    { 
    	return firstName; 
    }
    
    public String getLastName() 
    { 
    	return lastName; 
    }
    
    public String getPhone() 
    {
    	return phone; 
    }
    
    public String getEmail() 
    { 
    	return email; 
    }
    
    public boolean isBanned() 
    { 
    	return isBanned;
    }

    @Override 
    public String toString() {
        return "Customer Id: " + customerId +
               " | First Name: " + firstName +
               " | Last Name: " + lastName +
               " | Phone: " + phone +
               " | Email: " + email +
               " | Status: " + (isBanned ? "YES" : "NO");
    }

}
