import java.util.*;
import java.io.*;

public class Prog4 
{
    private Map<String, HotelInfo> hotels;
    private Map<String, HotelMember> members;

    /**
     * constructor for Prog4
     * creates a HashMap using single characters as a key for hotels
     * @param csvfile the file with the Key, Value <- in that format on each line
     */
    public Prog4(String csvfile) 
    {

        //local variables and initialize the hotels HashMap
        Scanner scan, read;
        hotels = new HashMap<>();
        String letter, hotelName;
       

        // try/catch for reading the file
        try {

            //create scanner for new file & set endFile to false
            scan = new Scanner(new File(csvfile));
            boolean endFile = false;

            //loop through each line until end of file
            while (!endFile) {
                try {

                    //initialize read (scanner), change delimiter, set endLine to false
                    read = new Scanner(scan.nextLine());
                    read.useDelimiter(",");
                    boolean endLine = false;

                    //read through the line, create HotelInfo object, and appropriately store into hotels Hashmap with Key
                    while (!endLine) 
                    {
                        try 
                        {
                            letter = read.next();
                            hotelName = read.next();
                            HotelInfo newHotel = new HotelInfo(hotelName);
                            hotels.put(letter, newHotel);
                        }

                        catch (NoSuchElementException e) 
                        {
                            endLine = true;
                        }
                    }
                    read.close(); // close read scanner
                }

                catch (NoSuchElementException e)  // set endFile true when execption is reached
                {
                    endFile = true;
                }
                
            }
            scan.close(); // close scan scanner
        }

        catch (FileNotFoundException e) // panic bc the file is not valid
        {
            System.out.println("that file is not real. hallucination.");
        }

    }

  
    /**
     * actions method
     * adds, deletes, and searches for members staying in the hotels.
     * can also give a summary of a given hotel with the number of guests and total fees
     * appropriately outputs error messages if there is an issue with the request
     * creates a HashMap with all the registered guests
     * 
     * @param dataFile the file with the commands and appropriate information required on each line
     * @return a formatted string with all of the information from each request in order on each line
     */
    public String actions(String dataFile) 
    {
       //local variables and initializing members HashMap
        Scanner scan, read;
        members = new HashMap<>();
        String actionRequest, guestHotel, category, surname, name, identification, output, StringBuilder;
        StringBuilder = "";
        output = "";
        
        // try/catch for reading the file
        try 
        {
            scan = new Scanner(new File(dataFile));
            boolean endFile = false;

            //loop through each line until end of file
            while (!endFile) 
            {
                try 
                {
                    //initialize read (scanner), change delimiter, set endLine to false
                    read = new Scanner(scan.nextLine());
                    boolean endLine = false;

                    while (!endLine) 
                    {
                        try 
                        {
                            actionRequest = read.next().toUpperCase(); // read in the action to be processed 


                            // processing ADD action
                            if (actionRequest.equals("ADD")) 
                            {
                                guestHotel = read.next();
                                surname = read.next();
                                name = read.next();
                                category = read.next();
                                identification = read.next();

                                //error message if person is already registered at a hotel
                                if (members.containsKey(identification))
                                    output = errorMessage(identification, actionRequest);

                                else
                                    output = addMember(guestHotel, surname, name, category, identification);

                            }

                            // process DELETE or SEARCH action
                            else if (actionRequest.equals("DELETE") || actionRequest.equals("SEARCH")) 
                            {
                                // reads in the ID of the member to be modified/found and searches for them 
                                String mID = read.next();
                                if (!members.containsKey(mID))
                                    output = errorMessage(mID, actionRequest);
                                
                                //if the ID exists, deletes or finds them depending on what the initial request was
                                else
                                {
                                    HotelMember person = members.get(mID);

                                    if(actionRequest.equals("DELETE"))
                                        output = deleteMember(person);

                                    else 
                                        output = search(person);
                                }
                            }

                            //processes SUMMARY action
                            else if (actionRequest.equals("SUMMARY")) 
                                output = hotelSummary(hotels.get(read.next()));

                            else
                                output = "something is wrong with the action request on this line."; // extra error message

                        } 
                        catch (NoSuchElementException e)  //set endLine to true
                        {
                            endLine = true;
                        }

                    }

                    // make the StringBuilder longer and then close scanner and go to beginning of loop
                    StringBuilder += output + "\n";
                    read.close();
                }

                catch (NoSuchElementException e) // set endFile to true
                {
                    endFile = true;
                }

            }
            scan.close(); // close scan scanner
        }

        catch (FileNotFoundException e)  // again. panic if there is an issue with the file.
        {
            return "that file is not real. hallucination.";
        }

        return StringBuilder;
    }


    /**
     * registrationFee method (private)
     * calculates the registration fee for an individual registered at a hotel
     * @param p the HotelMember for which the registration fee is to be calculated
     * @return the registration fee for the HotelMember dependent on their category/classification
     */
    private int registrationFee(HotelMember p) {
        String memberCat = p.getCategory();

        if (memberCat.equals("O"))
            return 200;

        else if (memberCat.equals("F"))
            return 150;

        else if (memberCat.equals("S"))
            return 50;

        return 9999; // scary number to indicate something went wrong

    }



    /**
     * addMember method (private)
     * adds a HotelMember to the members HashMap using the given ID as the key
     * also increments number of total guests and adds the appropriate amount to the total fees
     * @param guestHotel the hotel key for the hotel the HotelMember will be staying in (one character read in as a string)
     * @param surname   the last name of the HotelMember
     * @param name      the first name of the HotelMember
     * @param category  category of the HotelMember (O,F,S)
     * @param identification ID of the HotelMember
     * @return a message indicating the successful addition to the members HashMap
     */
    private String addMember(String guestHotel, String surname, String name, String category, String identification)
    {
        //create HotelMember object and add to members HashMap
        HotelMember newMember = new HotelMember(guestHotel, surname, name, category , identification);
        members.put(identification, newMember);

        // modify the number of guests and total fees at the designated hotel
        HotelInfo modHotel = hotels.get(guestHotel);
        modHotel.setGuests(modHotel.getGuests() + 1);
        modHotel.setTotalFees(modHotel.getTotalFees()+ registrationFee(newMember));

        return identification + " added to the " + hotels.get(guestHotel).getHotel();
    }


    /**
     * deleteMember method
     * deletes a member from the members HashMap so they are no longer associated with any hotel and modifies the hotel's info
     * @param x the HotelMember to be deleted
     * @return a message indicating the member has been successfully deleted from the HashMap (using ID and hotel)
     */
    private String deleteMember(HotelMember x)
    {
        String registeredHotel = hotels.get(x.getHotelKey()).getHotel();
        HotelInfo modHotel = hotels.get(x.getHotelKey());
        modHotel.setGuests(modHotel.getGuests() - 1 );
        modHotel.setTotalFees(modHotel.getTotalFees() - registrationFee(x));
        String mID = x.getMemberID();
        members.remove(x.getMemberID());
        return mID + " deleted from the " + registeredHotel;
    }

    /**
     * search method
     * searches the members HashMap to retrieve information about a member
     * @param x the HotelMember whose information is to be retrieved
     * @return using the member's ID, retrieves their full name and the hotel they are registered at
     */
    private String search(HotelMember x)
    {
        return x.getMemberID() + ": " + x.getFirstName() + " " + x.getLastName() + " registered at the " + hotels.get(x.getHotelKey()).getHotel();
    }


    /**
     * hotelSummary method
     * gives a summary of the Hotel's info including total guests and fees
     * @param h the HotelInfo object to retrieve info from
     * @return the hotel's full title along with total current guests and fees
     */
    private String hotelSummary(HotelInfo h)
    {
        return h.getHotel() + ": " + h.getGuests()+ " guests with a total of $" + String.format("%.2f", h.getTotalFees()) + " in fees";
    }

    /**
     * errorMessage method
     * outputs and error message dependent on what was trying to be processed
     * @param ID the ID of the HotelMember that is to be modified 
     * @param action the action attempting to be processed
     * @return the issue that occured based on the member and action being processed
     */
    private String errorMessage(String ID, String action)
    {
        HotelMember p = members.get(ID);

        if(action.equals("ADD"))
            return "ERROR: " + p.getMemberID() + " already registered at the " + hotels.get(members.get(ID).getHotelKey()).getHotel();
    
        else if(action.equals("DELETE") || action.equals("SEARCH"))
            return "ERROR: " + ID + " not registered ";

        return "not an expected error. panic.";
    }
    
}
