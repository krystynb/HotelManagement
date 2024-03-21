public class HotelInfo
{
    private int guests;
    private double totalFees;
    private String hotel;

    /**
     * HotelInfo constructor
     * creates a new hotel using the Hotel Name and sets the guests and fees to start at 0
     * @param hotelName
     */
    public HotelInfo(String hotelName)
    {
        hotel = hotelName;
        guests = 0;
        totalFees= 0.00;

    }

    /**
     * getGuests method
     * returns the total number of guests at the hotel
     * @return the total number of guests at a specific hotel
     */
    public int getGuests() 
    {
        return guests;
    }
    
    /**
     * setGuests method
     * changes the number of guests at a hotel
     * @param num the new number of guests at the hotel
     */
    public void setGuests(int num) 
    {
        guests = num;
    }
    
    /**
     * getTotalFees method
     * returns the total fees from all the guests at the hotel
     * @return the total fees from all the current guests
     */
    public double getTotalFees() 
    {
        return totalFees;
    }
    
    /**
     * setTotalFees method
     * changes the total fees at a hotel
     * @param fees the new amount of fees at a hotel
     */
    public void setTotalFees(double fees) 
    {
        totalFees = fees;
    }
    
    /**
     * getHotel method
     * retrieves the name of the hotel
     * @return the full title of the hotel
     */
    public String getHotel() 
    {
        return hotel;
    }
    
    /**
     * setHotel method
     * changes the title of the hotel (unlikely to be needed)
     * @param hotelName the new name of the hotel
     */
    public void setHotel(String hotelName) 
    {
        hotel = hotelName; 
    }
    
}
