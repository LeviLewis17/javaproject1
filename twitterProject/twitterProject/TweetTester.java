package twitterProject;

import java.util.Scanner;

public class TweetTester {

	public static void main(String[] args) {
		//For the sake of this project, I am reading from the trainingProcessed file and writing
		//the tweets with their predictions into outputFile. Additionally, the trainingProcessed
		//file will be updated each run to reflect any removes that could have taken place.
		AllTweets tweetLibrary = new AllTweets("C:\\Users\\Levi Lewis\\git\\javaproject1\\twitterProject\\trainingProcessed.txt", 
				"C:\\Users\\Levi Lewis\\git\\javaproject1\\outputFile.txt");
		Scanner readLineMain = new Scanner(System.in);
		
		//Test functionality to add tweet
/*		String yesOrNo;
		System.out.print("Would you like to add a tweet to the data set? <y/n>: ");
		yesOrNo = readLineMain.next();
		if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
			do {
				tweetLibrary.addTweetFromKeyboard();
				System.out.print("Would you like to enter another tweet? <y/n>: ");
				yesOrNo = readLineMain.next();
				readLineMain.nextLine();
			}while(yesOrNo.equals("y") || yesOrNo.equals("Y"));
		}
*/	
		
		//Testing the functionality to retrieve all ids from the collection
//		System.out.println("Here is a collection of all of the ids in the Tweet Library: ");
//		System.out.println(tweetLibrary.getIDs().toString());
		//The code above does work correctly, but for the sake of not frying my laptop I will only test it once.
		
		//Testing the size() method for AllTweets
		System.out.println("The number of tweets in the collection is: " + tweetLibrary.size());
		
		//Testing functionality of the getUsersById method
		String tempUser = new String("pookieex");
		System.out.println("Printing tweet IDs for the user " + tempUser + ": ");
		System.out.println(tweetLibrary.getIDsByUser(tempUser).toString());
		
		//Check accuracy of the collection predictions
		System.out.print("The accuracy of the predictions is: ");
		System.out.print((tweetLibrary.collectionAccuracy() * 100) + "%\n");
		
		//Testing remove functionality
		int id;
		String yesOrNo;
		System.out.print("Would you like to remove a tweet from the data set? <y/n>: ");
		yesOrNo = readLineMain.next();
		readLineMain.nextLine();
		if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
			do {
				System.out.print("Please enter the id of the tweet to be removed: ");
				id = readLineMain.nextInt();
				tweetLibrary.remove(tweetLibrary.getTweet(id));
				System.out.print("\nWould you like to remove another tweet? <y/n>: ");
				yesOrNo = readLineMain.next();
				readLineMain.nextLine();
			}while(yesOrNo.equals("y") || yesOrNo.equals("Y"));
		}
		
		//Print out all of the tweets into output file
		tweetLibrary.writeData();

		readLineMain.close();
	}

}
