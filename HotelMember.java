/**
    HotelMember class creates objects consisting of a person's hotel key (single character),
    first and last name, classification/category (O for officer, F for full member, S for student), 
    and ID String. It includes all getters and setters and a default constructor. Meant to be used in Prog4 
    to store information in a HashMap. 
      
**/
public class HotelMember {
    private String lastName, firstName, memberID, category, hotelKey;

    /**
     * HotelMember constructor
     * creates an object storing a person's hotel key, full name, category, and ID
     * @param hotelC
     * @param name2
     * @param name1
     * @param cat
     * @param ID
     */
    public HotelMember( String hotelC, String name2,String name1 ,String cat, String ID)
    {
        lastName = name2;
        firstName = name1;
        hotelKey = hotelC;
        memberID = ID;
        category = cat;

    }

    /**
     * default HotelMember constructor
     * sets all the parameters to mythical creatures 
     * @param hotelC
     * @param name2
     * @param name1
     * @param cat
     * @param ID
     */
    public HotelMember( )
    {
        lastName = "unicorn";
        firstName = "phoenix";
        hotelKey = "x";
        memberID = "snail";
        category = "bibble from fairytopia";
    }

    /**
     * setLastName method
     * changes the last name stored
     * @param name2 the new name to be set
     */
    public void setLastName(String name2) {
        lastName = name2;}

    /**
     * getLastName method
     * retrieves the stored last name
     * @return the last name attached to the object
     */
    public String getLastName() {
        return lastName;}

    /**
     * setFirstName method
     * changes the first name stored
     * @param name1 the new name to be set
     */
    public void setFirstName(String name1) {
        firstName = name1;}

    /**
     * getFirstName method
     * retrieves the stored first name
     * @return the first name attached to the object
     */
    public String getFirstName() {
        return firstName;}

    /**
     * setMemberID method
     * changes the ID of a member (emergency in case of collision somehow)
     * @param ID the ID of the person 
     */
    public void setMemberID(String ID) {
        memberID = ID;}

    /**
     * getMemberID method
     * retrieves the ID of the person
     * @return the person's ID
     */
    public String getMemberID() {
        return memberID;}

    /**
     * @param hotelC
     */
    public void setHotelKey(String hotelC) {
        hotelKey = hotelC;}

    /**
     * @return
     */
    public String getHotelKey() {
        return hotelKey;}

    /**
     * @param hotelC
     */
    public void setCategory(String cat) {
    category = cat;}

    /**
    * @return
    */
    public String getCategory() {
        return category;}













}