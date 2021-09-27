package twitterProject;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class AllTweets {
	private HashMap<Long, Tweet> allTweets;
	private String fileName;
	private String outFile;
	private int size;
	
	public AllTweets() {
		allTweets = new HashMap<Long, Tweet>();
		fileName = null;
		outFile = null;
		size = 0;
	}
	
	public AllTweets(String fn, String fn2) {
		allTweets = new HashMap<Long, Tweet>();
		fileName = fn;
		addDataFromFile();
		outFile = fn2;
	}
	
	public void addTweet(Tweet t) {
		allTweets.put(t.getID(), t);
		size++;
	}
	
	public void addTweetFromKeyboard() {
		long id;
		String tweet;
		int polarity;
		String user;
		Scanner readLine = new Scanner (System.in);
		boolean correctInput = true;
		
		do {
			System.out.print("You have opted to enter a tweet into the data set." + "\nPlease enter the polarity of the tweet: ");
			polarity = readLine.nextInt();
			if (polarity != 0 && polarity != 2 && polarity != 4)
				correctInput = false;
		}while (correctInput == false);
		
		System.out.print("Please enter the id of the tweet: ");
		id = readLine.nextLong();
		System.out.print("Please enter the user who tweeted the tweet: ");
		user = readLine.next();
		readLine.nextLine();
		System.out.print("Please enter the tweet: ");
		tweet = readLine.nextLine();
		
		addTweet(new Tweet(polarity, id, user, tweet));
		
		readLine.close();
	}
	
	public void remove(Tweet t) {
		try {
			allTweets.remove(t.getID(), t);
			size--;
		} catch (Exception e) {
			System.err.println("\nThe tweet id you entered is invalid.");
		}
	}
	
	private void addDataFromFile() {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
				String[] tokens = line.split(",");
				int polarity = Integer.parseInt(tokens[0]);
				long id = Long.parseLong(tokens[1]);
				
				addTweet(new Tweet(polarity, id, tokens[2], tokens[3]));
			}
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
			try {
				lineReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName.substring(1))));
				String line = null;
				while ((line = lineReader.readLine())!=null) {
					String[] tokens = line.split(",");
					int polarity = Integer.parseInt(tokens[0]);
					long id = Long.parseLong(tokens[1]);
					
					addTweet(new Tweet(polarity, id, tokens[2], tokens[3]));			}
			} catch (Exception e2) {
				System.err.println("there was a problem with the file reader, try again.  either no such file or format error");
			} finally {
				if (lineReader != null)
					try {
						lineReader.close();
					} catch (IOException e2) {
						System.err.println("could not close BufferedReader");
					}
			}			
		} finally {
			if (lineReader != null)
				try {
					lineReader.close();
				} catch (IOException e) {
					System.err.println("could not close BufferedReader");
				}
		}
	}
	
	public void writeData() {
		try
		{
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter myOutFile = new BufferedWriter(fw);			
			
			for (Tweet t : allTweets.values()) {
				myOutFile.write(t.toStringPolarity() + "\n");
			}
			
			myOutFile.flush();
			myOutFile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fileName);
		}
		
		try
		{
			FileWriter fw = new FileWriter(outFile);
			BufferedWriter myOutFile = new BufferedWriter(fw);			
			
			myOutFile.write("Tweets with Predictions: " + "\n");
			for (Tweet t : allTweets.values()) {
				myOutFile.write(t.toStringPrediction() + "\n");
			}
			
			myOutFile.flush();
			myOutFile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + outFile);
		}
	}
	
	public String toString() {
		String toReturn = new String("");
		
		for (HashMap.Entry<Long, Tweet> e : allTweets.entrySet()) {
			toReturn += e.getValue().toString() + "\n";
		}
		
		return toReturn;
	}
	
	public ArrayList<Long> getIDs() {
		ArrayList<Long> allIds = new ArrayList<Long>();
		
		for (Tweet t : allTweets.values()) {
			allIds.add(t.getID());
		}
		
		return allIds;
	}
	
	public ArrayList<Long> getIDsByUser(String user) {
		ArrayList<Long> someIds = new ArrayList<Long>();
		
		for (Tweet t : allTweets.values()) {
			if (t.getUser().equals(user)) {
				someIds.add(t.getID());
			}
		}
		
		return someIds;
	}
	
	public Tweet getTweet(long id) {
		return allTweets.get(id);
	}
	
	public double collectionAccuracy() {
		double accuracy;
		double correctCounter = 0;
		int total = 0;
		
		for (Tweet t: allTweets.values()) {
			allTweets.get(t.getID()).setPrediction();
			
			if (allTweets.get(t.getID()).getPolarity() == allTweets.get(t.getID()).getPrediction()) {
				correctCounter++;
			}
			total++;
		}
		
		accuracy = correctCounter / total;
		
		return accuracy;
	}
}
