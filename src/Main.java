import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class Main {
	public static void main(String[] args) {	
		
		new GUI();
		
		//CREATING FILE CODE//
//		ArrayList<Location> p = new ArrayList<>();
//		int[] rent1 = {2,10,30,90,160,250};
//		int[] rent2 = {4,20,60,180,320,450};
//		int[] rent3 = {6,30,90,270,400,550};
//		int[] rent4 = {6,30,90,270,400,550};
//		int[] rent5 = {8,40,100,300,450,600};
//		int[] rent6 = {10,50,150,450,625,750};
//		int[] rent7 = {10,50,150,450,625,750};
//		int[] rent8 = {12,60,180,500,700,900};
//		int[] rent9 = {14,70,200,550,750,950};
//		int[] rent10 = {14,70,200,550,750,950};
//		int[] rent11 = {16,80,220,600,800,1000};
//		int[] rent12 = {18,90,250,700,875,1050};
//		int[] rent13 = {18,90,250,700,875,1050};
//		int[] rent14 = {20,100,300,750,925,1100};
//		int[] rent15 = {22,110,330,800,975,1150};
//		int[] rent16 = {22,110,330,800,975,1150};
//		int[] rent17 = {24,120,360,850,1025,1200};
//		int[] rent18 = {24,120,360,850,1025,1200};
//		int[] rent19 = {26,130,390,900,1100,1275};
//		int[] rent20 = {28,150,450,1000,1200,1400};
//		int[] rent21 = {35,175,500,1100,1300,1500};
//		int[] rent22 = {50,200,600,1400,1700,2000};
//		
//		
//		Location l1 = new Location();
//		p.add(l1);
//		Street s1 = new Street("Mediterranean Avenue","1.png", null,60,30,false,"Brown",2,rent1,0,0,50,250);
//		p.add(s1);
//		ChanceAndCommunityChest c = new ChanceAndCommunityChest();
//		p.add(c);
//		Street s2 = new Street("Baltic Avenue","2.png", null,60,30,false,"Brown",2,rent2,0,0,50,250);
//		p.add(s2);
//		Tax t1 = new Tax();
//		p.add(t1);
//		Railroad r1 = new Railroad("Reading Railroad","3.png", null,200,100,false,25);
//		p.add(r1);
//		Street s3 = new Street("Oriental Avenue","4.png", null,100,50,false,"Cyan",3,rent3,0,0,50,250);
//		p.add(s3);
//		ChanceAndCommunityChest c1 = new ChanceAndCommunityChest();
//		p.add(c1);
//		Street s4 = new Street("Vermont Avenue","5.png", null,100,50,false,"Cyan",3,rent4,0,0,50,250);
//		p.add(s4);
//		Street s5 = new Street("Connecticut Avenue","6.png", null,120,60,false,"Cyan",3,rent5,0,0,50,250);
//		p.add(s5);
//		Location l3 = new Location();
//		p.add(l3);
//		Street s6 = new Street("St. Charles Place","7.png", null,140,70,false,"Pink",3,rent6,0,0,100,500);
//		p.add(s6);
//		Utility u1 = new Utility("Electric Company","8.png", null,150,75,false,0);
//		p.add(u1);
//		Street s7 = new Street("States Avenue","9.png", null,140,70,false,"Pink",3,rent7,0,0,100,500);
//		p.add(s7);	
//		Street s8 = new Street("Virginia Avenue","10.png", null,160,80,false,"Pink",3,rent8,0,0,100,500);
//		p.add(s8);
//		Railroad r2 = new Railroad("Pennsylvania Railroad","11.png", null,200,100,false,25);
//		p.add(r2);
//		Street s9 = new Street("St. James Place","12.png", null,180,90,false,"Orange",3,rent9,0,0,100,500);
//		p.add(s9);
//		ChanceAndCommunityChest c2 = new ChanceAndCommunityChest();
//		p.add(c2);
//		Street s10 = new Street("Tennessee Avenue","13.png", null,180,90,false,"Orange",3,rent10,0,0,100,500);
//		p.add(s10);
//		Street s11 = new Street("New York Avenue","14.png", null,200,100,false,"Orange",3,rent11,0,0,100,500);
//		p.add(s11);
//		Location l2 = new Location();
//		p.add(l2);
//		Street s12 = new Street("Kentucky Avenue","15.png", null,220,110,false,"Red",3,rent12,0,0,150,750);
//		p.add(s12);
//		ChanceAndCommunityChest c3 = new ChanceAndCommunityChest();
//		p.add(c3);
//		Street s13 = new Street("Indiana Avenue","16.png", null,220,110,false,"Red",3,rent13,0,0,150,750);
//		p.add(s13);
//		Street s14 = new Street("Illinois Avenue","17.png", null,240,120,false,"Red",3,rent14,0,0,150,750);
//		p.add(s14);
//		Railroad r3 = new Railroad("B. & O. Railroad","18.png", null,200,100,false,25);
//		p.add(r3);
//		Street s15 = new Street("Atlantic Avenue","19.png", null,260,130,false,"Yellow",3,rent15,0,0,150,750);
//		p.add(s15);
//		Street s16 = new Street("Ventnor Avenue","20.png", null,260,130,false,"Yellow",3,rent16,0,0,150,750);
//		p.add(s16);
//		Utility u2 = new Utility("Water Works","21.png", null,150,75,false,0);
//		p.add(u2);
//		Street s17 = new Street("Marvin Gardens","22.png", null,280,140,false,"Yellow",3,rent17,0,0,150,750);
//		p.add(s17);
//		GoToJail j1 = new GoToJail();
//		p.add(j1);
//		Street s18 = new Street("Pacific Avenue","23.png", null,300,150,false,"Green",3,rent18,0,0,200,1000);
//		p.add(s18);
//		Street s19 = new Street("North Carolina Avenue","24.png", null,300,150,false,"Green",3,rent19,0,0,200,1000);
//		p.add(s19);
//		ChanceAndCommunityChest c4 = new ChanceAndCommunityChest();
//		p.add(c4);
//		Street s20 = new Street("Pennsylvania Avenue","25.png", null,320,160,false,"Green",3,rent20,0,0,200,1000);
//		p.add(s20);
//		Railroad r4 = new Railroad("Short Line","26.png", null,200,100,false,25);
//		p.add(r4);
//		ChanceAndCommunityChest c5 = new ChanceAndCommunityChest();
//		p.add(c5);
//		Street s21 = new Street("Park Place","27.png", null,350,175,false,"Blue",2,rent21,0,0,200,1000);
//		p.add(s21);
//		Tax t2 = new Tax();
//		p.add(t2);
//		Street s22 = new Street("Boardwalk","28.png", null,400,200,false,"Blue",2,rent22,0,0,200,1000);
//		p.add(s22);
//		
//		File file = new File("Locations.ser");
//		
//		try {
//			FileOutputStream fOut = new FileOutputStream(file);
//			ObjectOutputStream out = new ObjectOutputStream(fOut);
//			
//			out.writeObject(p);
//			
//			out.close();
//			fOut.close();
//			System.out.println("Locations have been stored");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
