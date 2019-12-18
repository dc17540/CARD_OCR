//libaries to import
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.function.Supplier;



class ContactInfo{
public static String getName(List<String> document){
    //The line of the resume the code is parsing through 
    String theLine = "";

    //List of names to use to parse info. In futher iterations include more names in order to catch more cases
    //There are many edge cases in order to identify a name and It wont always appear first in a Bisunes card 
List<String> nameList = new ArrayList<String>();
    nameList.add("mike");
    nameList.add("lisa");
    nameList.add("arthur");
    nameList.add("joe");
    nameList.add("sarah");

    String theName = "";

//Iterate through every line
for (int i = 0; i < document.size(); i++) {
    theLine  = document.get(i);
    //split based off white space
    String[] splitStr = theLine.split("\\s+");

   //iterate through the strings that were split 
    for(int j = 0; j < splitStr.length; j++){
    
    //if firstname is in the list of names for refrence, last name shoud be the next string 
    if(nameList.contains(splitStr[j].toLowerCase())){
    
       theName = splitStr[j] + " " + splitStr[j+1];
    
    }
    
    }
   }
return theName;
}
 
public static String getPhoneNumber(List<String> document){
//initalize values
//Phone: and Tel: are edge cases that will help determine where the number is
   String theLine = "";
   List<String> edgeList = new ArrayList<String>();
    edgeList.add("Phone:");
    edgeList.add("Tel:");
    String theNumber = "";

//iterate through each line of the resume  and split off whitespace
for (int i = 0; i < document.size(); i++) {
    theLine  = document.get(i);

    String[] splitStr = theLine.split("\\s+");

    //iterate trough list based off whitespace
    for(int j = 0; j < splitStr.length; j++){ 


	    //Cases for if the number Contains Phone:,Tel: or +1 before the full number 
    if(edgeList.contains(splitStr[j].toLowerCase()) || edgeList.contains(splitStr[j]) || splitStr[j].equals("+1")){
      
       theNumber = splitStr[j+1];
       //Cases for if they're spacses inbetween the digits of the numbers instead of -
    }if(theNumber.length() > 0 && theNumber.length() < 10 && splitStr[j].equals("+1")){
    
      theNumber  = theNumber + splitStr[j+2];
    }
//Cases if the number begins with (
    if(splitStr[j].length() !=  0){
    if(splitStr[j].substring(0, 1).equals("(") && theNumber.length() == 0){
    theNumber = splitStr[j];
    
      } 
    }

  }
 }
//remome non numeric charcters 
theNumber = theNumber.replaceAll("[^\\d.]", "");
return theNumber;
}
 
public static String getEmailAddress(List<String> document){
    //initalize values 
    //@ and at will emcompass cases
    String theLine = "";
    List<String> edgeList = new ArrayList<String>();
    edgeList.add("@");
    edgeList.add("at");
    String theEmail = "";

//iterate through every line of the reusme
 for (int i = 0; i < document.size(); i++) {
    theLine  = document.get(i);
//split based off white space
    String[] splitStr = theLine.split("\\s+");

    //iterate through the splitted strings and check if they contain @ or at
    for(int j = 0; j < splitStr.length; j++){
        for(int k = 0; k < edgeList.size(); k++){
            if(splitStr[j].contains(edgeList.get(k))){

                 theEmail = splitStr[j];

            }

        }
    }
 }

return theEmail;
}
 
 

}
 

//BusinessCardParser class- retuns a ContactInfo object
class BusinessCardParser{
   public static ContactInfo getContactInfo(List<String> document){

  //create contactInfo object and call functions to get informaiton 	   
  ContactInfo newContactInfo = new ContactInfo();
 
  String personName   = newContactInfo.getName(document);
  String personNumber = newContactInfo.getPhoneNumber(document);
  String personAdress = newContactInfo.getEmailAddress(document);

  //output results
  System.out.println("Name: " + personName);
  System.out.println("Number: " + personNumber);
  System.out.println("Adress: " + personAdress);

  return newContactInfo;

   }
}




 
class Main {
 
 public static void main(String[] args){
   System.out.println("business card parser");
   System.out.println("What is the name of the file?");
  
   //define string list in order to contain information from resume 
   List<String> sList = new ArrayList<String>(); 
try{

//reading in the user input of the name of the resume 
String theFullDoc =  "";
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

theFullDoc = br.readLine();

br.close();

BufferedReader br2 = new BufferedReader(new FileReader(theFullDoc));

String theLine = "";
while (((theLine = br2.readLine()) != null)) {sList.add(theLine);}



if (br2 != null) {br2.close();};

//catch if input is invalid 
}catch(IOException e) {
 e.printStackTrace();
}
 
//parse through the StringList and output results
BusinessCardParser theResults = new BusinessCardParser();
theResults.getContactInfo(sList);

 }
}


