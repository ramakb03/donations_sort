package Assignment4;
import java.io.*;
import java.util.Scanner;
public class Assignment4 {
	public static void main(String[] args) throws Exception {
		/* 
		 * Ramatoulaye Kebe
		 * Program sorts a donation list by Id of donor
		 * Then by amount donated
		 * And finds the median value donated
		 * Program also allows users to add data into the list after first sort
		 */
			PrintWriter outFile = new PrintWriter("myOutput04.txt");
			Scanner keyboard = new Scanner(System.in);
			
			outFile.println("Ramatoulaye Kebe");
			outFile.println("Program #4");
			outFile.println();
			
			double medianValue;
			int[]idNum = new int[50];
			double[]donationAmount = new double[50];
			int donorCount = 0;
			
				//call readData() method
				donorCount = readData(idNum, donationAmount, outFile);
				
				//call printData() method
				printData(idNum, donationAmount, donorCount, outFile);
				
				//call sortByID()method
				outFile.println("Sorted by ID:");
				sortByID(idNum,donationAmount, donorCount);
				
				//call printData() method again
				printData(idNum, donationAmount, donorCount, outFile);
				
				outFile.println("There are requests to process:");
				outFile.println();
				donorCount = addData(idNum, donationAmount, 
						donorCount, keyboard);
			
				outFile.println("Sorted by ID after additions:");
				sortByID(idNum,donationAmount, donorCount);
				printData(idNum, donationAmount, donorCount, outFile);
				
				//call sortByDonation() method
				outFile.println("Sorted by donation:");
				sortByDonation(idNum, donationAmount, donorCount);
				
				//call printData() method again
				printData(idNum, donationAmount, donorCount, outFile);
				
				//print who donated the most and how much they donated
				outFile.println();
				outFile.println("Donor " + idNum[0] + " had the highest donation: $"
						+ donationAmount[0]);
				
				//find and print median donation amount
				if((donorCount % 2) == 0) { //if even
					medianValue = (donationAmount[(donorCount / 2) - 1] 
							+ donationAmount[donorCount / 2]) / 2;
				}
				else {
					medianValue = donationAmount[donorCount / 2];
					}
				outFile.printf("The median donation amount is: $%.2f", medianValue);	

				//close PrintWriter and Scanner
				outFile.close();
				keyboard.close();	
	}
	/*
	 * readData() method receives 3 parameters:
	 * an array of integers (idNumbers)
	 * an array of type double numbers (donations]
	 * and the PrintWriter outFile
	 * Method reads data from the input file for the # of donations & arrays
	 */
		public static int readData(int[]idNumbers, double[]donations, PrintWriter outFile) throws Exception{
			 
			Scanner inFile = new Scanner(new File("project4Input.dat"));
			int i = 0, donorCount = 0;
		
			while(inFile.hasNext()) {
					donorCount++;
					idNumbers[i] = inFile.nextInt();
					donations[i] = inFile.nextDouble();
					i++;
				}
			inFile.close();
			return donorCount;
		}
		/*
		 * printData() method receives 4 parameters:
		 * an array of integers(id)
		 * an array of type double numbers(amount)
		 * an integer(n)
		 * and the PrintWriter outFile
		 * Method prints data for each of the arrays
		 */
		public static void printData(int[]id, double[] amount, int n, PrintWriter outFile) {	 
			outFile.println("\tDonations summary table");
			outFile.print("ID number");
			outFile.print("          Amount donated");
			outFile.println();
			for(int i = 0; i < n; i++) {
				outFile.print(id[i]);
				outFile.printf("                  $%.2f\n", amount[i]);
				
			}
			outFile.println();
		}
		/*
		 * sortByID() method receives 3 parameters:
		 * an array of integers (donor)
		 * an array of type double numbers(donations)
		 * an integer (n)
		 * Method sorts the the donor array in ascending order
		 * While keeping the match-up between the ID and the donation amount
		 */
		public static void sortByID(int[]donor, double[]donations, int n) {	 
			int tempDonor;
			double tempAmount;
			
			for(int pos = 0; pos < n - 1; pos++) {
				for(int cand = pos + 1; cand < n; cand++) {
					if(donor[cand] < donor[pos]) {
						tempDonor = donor[cand];
						tempAmount = donations[cand];
						
						donor[cand] = donor[pos];
						donor[pos] = tempDonor;
						
						donations[cand] = donations[pos];
						donations[pos] = tempAmount; 
					}
				}
			}
		}
		/*
		 * sortByDonation() method receives 3 parameters:
		 * an array of integers (donor)
		 * an array of type double numbers(donations)
		 * an integer (n)
		 * Method sorts the the donations array in a descending order
		 * While keeping the match-up between the ID and the donation amount
		 */
		public static void sortByDonation(int[]donor, double[]donations, int n) { 
			int tempDonor;
			double tempAmount;
			
			boolean swapped;
			do {
				swapped = false;
				for(int pos = 0; pos < n - 1; pos++) {
					if(donations[pos + 1] > donations[pos]) {
						tempAmount = donations[pos];
						tempDonor = donor[pos];
					
					    donations[pos] = donations[pos + 1];
					    donations[pos + 1] = tempAmount;
				
					    donor[pos] = donor[pos + 1];
					    donor[pos + 1] = tempDonor;
					    
					    swapped = true;
			}
				}
			}
			while(swapped);
		}
		/*
		 * addData() method receives 4 parameters:
		 * an array of integers(donorsArray)
		 * an array of type double numbers(donationsArray) 
		 * an integer(n) for the size of the arrays
		 * and the Scanner keyboard
		 * Method allows user to add data into the arrays
		 * if the arrays haven't reached their max capacity yet
		 * Method returns new value for n
		 */
		public static int addData(int[]donorsArray, double[] donationsArray, int n, Scanner keyboard) {
			 int searchVal, tempID;
			System.out.println("Would you like to add "
					+ "more donors to the list?");
			System.out.println("Type Yes or No");
			String userResponse = keyboard.next();
		
			while(userResponse.equalsIgnoreCase("yes")){
				System.out.println("Enter ID number: ");
				tempID = keyboard.nextInt();
						
				for(int orig = 0; orig < n; orig++) {
					searchVal = tempID;
					/* Check if user-entered ID is already in the array
					 * If it is, print the ID number
					 * along w/ matching donation amount
					 * If it isn't,
					 * then user-entered ID is read into the array
					 */
					if(searchVal == donorsArray[orig]) {
						System.out.println(donorsArray[orig] + 
								" " + donationsArray[orig]);
								
						System.out.println();
						System.out.println("Would you like to add "
						+ "more donors to the list?");
						System.out.println("Type Yes or No");
						userResponse = keyboard.next();
						
						if(userResponse.equalsIgnoreCase("yes")) {
							System.out.println("Enter ID number");
							tempID = keyboard.nextInt();
								}
						else {
							System.out.println("The list now contains " 
						+ n + " donors");
							return n;
										}
							}
					else {
						//Check if array size < max size
						if( n > 50) {
							System.out.println("No donors may be added"
						+ " to the list");
							return n; 					
							}
						else  {
							donorsArray[n] = tempID;
							}
						}
				}
				System.out.println("Enter a donation amount: ");
				donationsArray[n] = keyboard.nextDouble();
				System.out.print("Donor " + donorsArray[n] + 
						" was added to the list.");
				System.out.printf(" They donated $%.2f\n", 
						donationsArray[n]);
				        
				n++; //update array size
				    
				System.out.println();
				System.out.println("Would you like to add "
				+ "more donors to the list?");
				System.out.println("Type Yes or No");
				userResponse = keyboard.next();
				}
			System.out.println("The list now contains "
			+ n + " donors");
			return n;
		}
}



		

			
			