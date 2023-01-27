/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


class Taxi{
  
    
    char currentspot;  // current spot of taxi
    int freetime;      // freetime of the taxi
    int total_earning; //total earning for taxi
    int taxi_id;
    ArrayList<String> trips;  // String for store customer details
    int distance;
    int customerid;
    
    public Taxi(){
        currentspot='A';
        freetime=9;
        total_earning=0;
        taxi_id=0;
        trips=new ArrayList<String>();
        distance=0;
        customerid=1;
        
    }
    public void data_entry_and_print(char pickuppoint,char droppoint,int pickuptime){
        //calcualtion
        
        int distance=Math.abs(pickuppoint-droppoint);  //values between pickup and drop
        int current_amount=((distance*15)-5)*10+100;  //amount earn in each trip
        int droptime=distance+pickuptime;  //taxi reach a destination
        
        //print
        System.out.println("---------------------------------------");
        System.out.println("Taxi "+taxi_id+" Booked");
        System.out.println("Total Distance Travelled : "+distance*15);
        System.out.println("Amount you Pay : "+current_amount);
        System.out.println("Drop Time : "+droptime);
        System.out.println("---------------------------------------");
        
        //add details in constructor
        
        String s=taxi_id+"     "+customerid+"     "+customerid+"     "+pickuppoint+"     "+droppoint+"     "+pickuppoint+"     "+droptime+"     "+current_amount;
        trips.add(s);
        currentspot=droppoint;
        freetime=droptime;
        total_earning=total_earning+current_amount;
        customerid=customerid+1;
        return;
    }
}
public class Main
{
    
    public static ArrayList<Taxi> samelocation(char pickuppoint,ArrayList<Taxi> taxis,int pickuptime){
        
        ArrayList<Taxi> sort_taxi=new ArrayList<Taxi>();
        for(Taxi t : taxis)
            if( t.currentspot==pickuppoint && pickuptime>=t.freetime )
                sort_taxi.add(t);

        //sort the object
                
        Collections.sort(sort_taxi,(a,b)->a.total_earning - b.total_earning); 
        //System.out.println("check 01");
        //System.out.println(sort_taxi.size());
        
      
        return sort_taxi;
    }
    
    public static ArrayList<Taxi> difflocation(char pickuppoint,ArrayList<Taxi> taxis,int pickuptime){
        
        ArrayList<Taxi> sort_taxi=new ArrayList<Taxi>();
        for(Taxi t : taxis)
        {
            int distance=Math.abs(t.currentspot-pickuppoint);
            t.distance=distance;
            if(pickuptime>=t.freetime+distance)
            {   
                
                sort_taxi.add(t);
                System.out.println(t.taxi_id+"--"+t.distance);
                
            }
            
        }
        
        
        Collections.sort(sort_taxi,(a,b)->a.distance - b.distance); 
        //System.out.println("check 02");
        //System.out.println(sort_taxi.size());

        return sort_taxi;
        
        
        
        
    }
    
	public static void main(String[] args) {
	    Scanner sc=new Scanner(System.in);
	    
	    ArrayList<Taxi> taxis=new ArrayList<Taxi>();  // create object for each taxi and store in List
	    for(int i=0;i<3;i++)
	        taxis.add(new Taxi());
	        
	   int count=1 ;
	   for(Taxi t:taxis){
	        t.taxi_id=count;
	        count=count+1;}
	        
	    while(true)
	    {
	    System.out.println("0 -> Taxi Booking");
	    System.out.println("1 -> Print Taxi Details");
	    int choice=sc.nextInt();
	    
	    switch(choice){
	        
	    case 0:
	    {
	        
	    System.out.print("Enter Pickup point : ");
	    char pickuppoint =sc.next().charAt(0);
	    
	    System.out.print("Enter Drop Point : ");
	    char droppoint =sc.next().charAt(0);

	    System.out.print("Enter pickup time : ");
	    int pickuptime=sc.nextInt();
	    
	    if(pickuppoint < 'A' || pickuppoint > 'F' || droppoint < 'A' || droppoint > 'F')
	    {
	        System.out.println("Invalid Details");
	        break;
	    }
	    
	    ArrayList<Taxi> sort_taxi=new ArrayList<Taxi>();
	    
	    sort_taxi=samelocation(pickuppoint,taxis,pickuptime); //same location taxi
	    
	    if(sort_taxi.size()==0)
	        sort_taxi=difflocation(pickuppoint,taxis,pickuptime);  //nearest location taxi
	    if(sort_taxi.size()==0){
	        System.out.println("Taxi not Available");
	        break;}
	    

	    


	    sort_taxi.get(0).data_entry_and_print(pickuppoint,droppoint,pickuptime);

	        
	    
	        
	    
	    
	  
	        
	    

	        
	    }// case 0
	    
	    } //switch case
	    
	    }
	        
	    
	    
	    
		
	}
}
