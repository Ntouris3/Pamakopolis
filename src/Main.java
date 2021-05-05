import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class Main {
	
	public static ArrayList<Player> allPlayers = new ArrayList<Player>();
	public static Queue<Card> allChances = new LinkedList<Card>();
	public static Queue<Card> allCommunityChests = new LinkedList<Card>();
	public static ArrayList<Location> locations = new ArrayList<Location>();
   
	
	public static void main(String[] args) {	

		//allChances.add(new GetOutOfJailCard("ATestName"));
		
//		File file = new File("Locations.ser");
//		
//		try {
//			FileInputStream fIn = new FileInputStream(file);
//			ObjectInputStream in = new ObjectInputStream(fIn);
//			
//			locations = (ArrayList<Location>) in.readObject();
//			in.close();
//			fIn.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//
//		//Loading the card files
//		File file1 = new File("AllChances.ser");
//				
//		try {
//			FileInputStream fIn = new FileInputStream(file1);
//			ObjectInputStream in = new ObjectInputStream(fIn);
//					
//			allChances = (Stack<Card>) in.readObject();
//			in.close();
//			fIn.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		File file2 = new File("allCommunityChests.ser");
//				
//		try {
//			FileInputStream fIn = new FileInputStream(file2);
//			ObjectInputStream in = new ObjectInputStream(fIn);
//			
//			allCommunityChests = (Stack<Card>) in.readObject();
//			in.close();
//			fIn.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		createData();
		//new LoginScreenGUI();

		new GUI();

	}
	
	private static void createData() {

		//create location data
		
		int[] rent1 = {2,10,30,90,160,250};
		int[] rent2 = {4,20,60,180,320,450};
		int[] rent3 = {6,30,90,270,400,550};
		int[] rent4 = {6,30,90,270,400,550};
		int[] rent5 = {8,40,100,300,450,600};
		int[] rent6 = {10,50,150,450,625,750};
		int[] rent7 = {10,50,150,450,625,750};
		int[] rent8 = {12,60,180,500,700,900};
		int[] rent9 = {14,70,200,550,750,950};
		int[] rent10 = {14,70,200,550,750,950};
		int[] rent11 = {16,80,220,600,800,1000};
		int[] rent12 = {18,90,250,700,875,1050};
		int[] rent13 = {18,90,250,700,875,1050};
		int[] rent14 = {20,100,300,750,925,1100};
		int[] rent15 = {22,110,330,800,975,1150};
		int[] rent16 = {22,110,330,800,975,1150};
		int[] rent17 = {24,120,360,850,1025,1200};
		int[] rent18 = {24,120,360,850,1025,1200};
		int[] rent19 = {26,130,390,900,1100,1275};
		int[] rent20 = {28,150,450,1000,1200,1400};
		int[] rent21 = {35,175,500,1100,1300,1500};
		int[] rent22 = {50,200,600,1400,1700,2000};
		
		
		Location l1 = new Location();
		locations.add(l1);
		Street s1 = new Street("Mediterranean Avenue","1.png",null,60,30,false,"Brown",2,rent1,0,0,50,250);
		locations.add(s1);
		ChanceAndCommunityChest c = new ChanceAndCommunityChest();
		locations.add(c);
		Street s2 = new Street("Baltic Avenue","2.png", null,60,30,false,"Brown",2,rent2,0,0,50,250);
		locations.add(s2);
		Tax t1 = new Tax();
		locations.add(t1);
		Railroad r1 = new Railroad("Reading Railroad","3.png", null,200,100,false,25);
		locations.add(r1);
		Street s3 = new Street("Oriental Avenue","4.png", null,100,50,false,"Cyan",3,rent3,0,0,50,250);
		locations.add(s3);
		ChanceAndCommunityChest c1 = new ChanceAndCommunityChest();
		locations.add(c1);
		Street s4 = new Street("Vermont Avenue","5.png", null,100,50,false,"Cyan",3,rent4,0,0,50,250);
		locations.add(s4);
		Street s5 = new Street("Connecticut Avenue","6.png", null,120,60,false,"Cyan",3,rent5,0,0,50,250);
		locations.add(s5);
		Location l3 = new Location();
		locations.add(l3);
		Street s6 = new Street("St. Charles Place","7.png", null,140,70,false,"Pink",3,rent6,0,0,100,500);
		locations.add(s6);
		Utility u1 = new Utility("Electric Company","8.png", null,150,75,false,0);
		locations.add(u1);
		Street s7 = new Street("States Avenue","9.png", null,140,70,false,"Pink",3,rent7,0,0,100,500);
		locations.add(s7);	
		Street s8 = new Street("Virginia Avenue","10.png", null,160,80,false,"Pink",3,rent8,0,0,100,500);
		locations.add(s8);
		Railroad r2 = new Railroad("Pennsylvania Railroad","11.png", null,200,100,false,25);
		locations.add(r2);
		Street s9 = new Street("St. James Place","12.png", null,180,90,false,"Orange",3,rent9,0,0,100,500);
		locations.add(s9);
		ChanceAndCommunityChest c2 = new ChanceAndCommunityChest();
		locations.add(c2);
		Street s10 = new Street("Tennessee Avenue","13.png", null,180,90,false,"Orange",3,rent10,0,0,100,500);
		locations.add(s10);
		Street s11 = new Street("New York Avenue","14.png", null,200,100,false,"Orange",3,rent11,0,0,100,500);
		locations.add(s11);
		Location l2 = new Location();
		locations.add(l2);
		Street s12 = new Street("Kentucky Avenue","15.png", null,220,110,false,"Red",3,rent12,0,0,150,750);
		locations.add(s12);
		ChanceAndCommunityChest c3 = new ChanceAndCommunityChest();
		locations.add(c3);
		Street s13 = new Street("Indiana Avenue","16.png", null,220,110,false,"Red",3,rent13,0,0,150,750);
		locations.add(s13);
		Street s14 = new Street("Illinois Avenue","17.png", null,240,120,false,"Red",3,rent14,0,0,150,750);
		locations.add(s14);
		Railroad r3 = new Railroad("B. & O. Railroad","18.png", null,200,100,false,25);
		locations.add(r3);
		Street s15 = new Street("Atlantic Avenue","19.png", null,260,130,false,"Yellow",3,rent15,0,0,150,750);
		locations.add(s15);
		Street s16 = new Street("Ventnor Avenue","20.png", null,260,130,false,"Yellow",3,rent16,0,0,150,750);
		locations.add(s16);
		Utility u2 = new Utility("Water Works","21.png", null,150,75,false,0);
		locations.add(u2);
		Street s17 = new Street("Marvin Gardens","22.png", null,280,140,false,"Yellow",3,rent17,0,0,150,750);
		locations.add(s17);
		GoToJail j1 = new GoToJail();
		locations.add(j1);
		Street s18 = new Street("Pacific Avenue","23.png", null,300,150,false,"Green",3,rent18,0,0,200,1000);
		locations.add(s18);
		Street s19 = new Street("North Carolina Avenue","24.png", null,300,150,false,"Green",3,rent19,0,0,200,1000);
		locations.add(s19);
		ChanceAndCommunityChest c4 = new ChanceAndCommunityChest();
		locations.add(c4);
		Street s20 = new Street("Pennsylvania Avenue","25.png", null,320,160,false,"Green",3,rent20,0,0,200,1000);
		locations.add(s20);
		Railroad r4 = new Railroad("Short Line","26.png", null,200,100,false,25);
		locations.add(r4);
		ChanceAndCommunityChest c5 = new ChanceAndCommunityChest();
		locations.add(c5);
		Street s21 = new Street("Park Place","27.png", null,350,175,false,"Blue",2,rent21,0,0,200,1000);
		locations.add(s21);
		Tax t2 = new Tax();
		locations.add(t2);
		Street s22 = new Street("Boardwalk","28.png", null,400,200,false,"Blue",2,rent22,0,0,200,1000);
		locations.add(s22);
		
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
		
		//create cards data
		GetOutOfJailCard card1 = new GetOutOfJailCard("Chance_GOOJF.png");
        GetOutOfJailCard card2 = new GetOutOfJailCard("Community_Chest_GOOJF.png");
        TransactionCard card3 = new TransactionCard("Chance_YHBAECOTB.png", 50);
        TransactionCard card4 = new TransactionCard("Community_Chest_GOO.png", 50);
        TransactionCard card5 = new TransactionCard("Chance_BPYD.png", 50,true);
        TransactionCard c6 = new TransactionCard("Chance_YBALM.png", 150,true);
        TransactionCard c7 = new TransactionCard("Community_Chest_BEIYF.png",200,true);
        TransactionCard c8 = new TransactionCard("Community_Chest_FSOS.png", 45,true);
        TransactionCard c9 = new TransactionCard("Community_Chest_ITR.png",20,true);
        TransactionCard c10 = new TransactionCard("Community_Chest_LIM.png", 100,true);
        TransactionCard c11 = new TransactionCard("Community_Chest_RFS.png", 25,true);
        TransactionCard c12 = new TransactionCard("Community_Chest_YHWSPIABC.png", 10,true);
        TransactionCard c13 = new TransactionCard("Community_Chest_YI.png", 100,true);
        TransactionCard c14 = new TransactionCard("Chance_PPT.png",15,false);
        TransactionCard c15 = new TransactionCard("Community_Chest_DF.png",50,false);
        TransactionCard c16 = new TransactionCard("Community_Chest_PH.png",100,false);
        TransactionCard c17 = new TransactionCard("Community_Chest_PST.png",150,false);
        TransportationCard c18 = new TransportationCard("Chance_GB3S.png",3);
        TransportationCard c19 = new TransportationCard("Advance_To_Boardwalk.png",39);
        TransportationCard c20 = new TransportationCard("Advance_To_Go.png",0);
        TransportationCard c21 = new TransportationCard("CC_Advance_to_Go.png",0);
        TransportationCard c22 = new TransportationCard("Chance_ATIA.png",24);
        TransportationCard c23 = new TransportationCard("Chance_ATSCP.png",11);
        TransportationCard c24 = new TransportationCard("Chance_GTJ.png",10);
        TransportationCard c25 = new TransportationCard("Chance_TAROTR.png",5);
        TransportationCard c26 = new TransportationCard("Community_Chest_GTJ.png",10);
        
        
        
        allChances.add(card1);
        allCommunityChests.add(card2);
        allChances.add(card3);
        allCommunityChests.add(card4);
        allChances.add(card5);
        allChances.add(c6);
        allCommunityChests.add(c7);
        allCommunityChests.add(c8);
        allCommunityChests.add(c9);
        allCommunityChests.add(c10);
        allCommunityChests.add(c11);
        allCommunityChests.add(c12);
        allCommunityChests.add(c13);
        allChances.add(c14);
        allCommunityChests.add(c15);
        allCommunityChests.add(c16);
        allCommunityChests.add(c17);
        allChances.add(c18);
        allChances.add(c19);
        allChances.add(c20);
        allCommunityChests.add(c21);
        allChances.add(c22);
        allChances.add(c23);
        allChances.add(c24);
        allChances.add(c25);
        allCommunityChests.add(c26);
		
	}
	
	
	
	
}