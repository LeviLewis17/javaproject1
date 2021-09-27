package twitterProject;

import java.util.Scanner;

public class TweetTester {

	public static void main(String[] args) {
		AllTweets tweetLibrary = new AllTweets("D:\\ProgramFiles\\eclipse\\project1\\twitterProject\\trainingProcessed.txt", 
				"D:\\ProgramFiles\\eclipse\\project1\\twitterProject\\outputFile.txt");
		Scanner readLine = new Scanner(System.in);
		
		//Test functionality to add tweet
		String yesOrNo;
		System.out.print("Would you like to add a tweet to the data set? <y/n>: ");
		yesOrNo = readLine.next();
		readLine.nextLine();
		if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
			do {
				tweetLibrary.addTweetFromKeyboard();
				System.out.print("Would you like to enter another tweet? <y/n>: ");
				yesOrNo = readLine.next();
				readLine.nextLine();
			}while(yesOrNo.equals("y") || yesOrNo.equals("Y"));
		}
		
		//Testing the functionality to retrieve all ids from the collection
//		System.out.println("Here is a collection of all of the ids in the Tweet Library: ");
//		System.out.println(tweetLibrary.getIDs().toString());
		//The code above does work correctly, but for the sake of not frying my laptop I will only test it once.
		
		//Testing functionality of the getUsersById method
		String tempUser = new String("pookieex");
		System.out.println("Printing tweet IDs for the user " + tempUser + ": ");
		System.out.println(tweetLibrary.getIDsByUser(tempUser).toString());
		
		//Check accuracy of the collection predictions
		System.out.print("The accuracy of the predictions is: ");
		System.out.print((tweetLibrary.collectionAccuracy() * 100) + "%\n");
		
		//Testing remove functionality
		int id;
		System.out.print("Would you like to remove a tweet to the data set? <y/n>: ");
		yesOrNo = readLine.next();
		readLine.nextLine();
		if (yesOrNo.equals("y") || yesOrNo.equals("Y")) {
			do {
				System.out.print("Please enter the id of the tweet to be removed: ");
				id = readLine.nextInt();
				tweetLibrary.remove(tweetLibrary.getTweet(id));
				System.out.print("\nWould you like to remove another tweet? <y/n>: ");
				yesOrNo = readLine.next();
				readLine.nextLine();
			}while(yesOrNo.equals("y") || yesOrNo.equals("Y"));
		}
		
		//Print out all of the tweets into output file
		tweetLibrary.writeData();

		readLine.close();
	}

}
