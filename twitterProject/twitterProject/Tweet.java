package twitterProject;

public class Tweet {
	private long id;
	private String tweet;
	private int polarity;
	private String user;
	private int prediction;
	
	public Tweet() {
		id = 0;
		tweet = new String("");
		polarity = 0;
		user = new String("");
		prediction = 2;
	}
	
	public Tweet(int polarity, long id, String user, String tweet) {
		this.id = id;
		this.polarity = polarity;
		this.user = user;
		this.tweet = tweet;
	}
	
	public String toStringPolarity() {
		String toReturn = new String("");
		toReturn += polarity + ",";
		toReturn += Long.toString(id) + ",";
		toReturn += user + ",";
		toReturn += tweet;
		
		return toReturn;
	}
	
	public String toStringPrediction() {
		String toReturn = new String("");
		toReturn += prediction + ",";
		toReturn += Long.toString(id) + ",";
		toReturn += user + ",";
		toReturn += tweet;
		
		return toReturn;
	}
	
	public boolean equals(Tweet t) {
		boolean toReturn = false;
		if (t.getID() == getID())
			toReturn = true;
			
		return toReturn;
	}
	
	public void setPrediction() {
		prediction = predict();
	}
	
	public int predict() {	
		return 4;
	}
	
	public long getID() {
		return id;
	}
	
	public String getText() {
		return tweet;
	}
	
	public int getPolarity() {
		return polarity;
	}

	public String getUser() {
		return user;
	}
	
	public int getPrediction() {
		return prediction;
	}

}
