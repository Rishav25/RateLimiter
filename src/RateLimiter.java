import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RateLimiter {
	
	/**
	 * We have number of requests
	 * Time before we start requesting
	 * Time after we start requesting
	 */
	static int REQUESTS = 0;
	static String BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
	static String AFTER_REQUEST_TIME = "11-01-2022 13:02:00";
	
	/**
	 * Constructor
	 * @param requests
	 */
	public RateLimiter(int requests) {
		RateLimiter.REQUESTS = requests;
	}
	
	/**
	 * 
	 * @param requests - number of requests
	 * @return true if it is valid and false if it is not
	 * @throws ParseException
	 */
	public static boolean checkRateLimiter(int requests) throws ParseException{
		Date initialTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(BEFORE_REQUEST_TIME);
		Date currTime = new SimpleDateFormat("dd-MM-yyy HH:mm:ss").parse(AFTER_REQUEST_TIME);
		
		long timeBeforeRequests = initialTime.getTime();
		long timeAfterRequests = currTime.getTime();
		
		long differenceInMilliSec = timeAfterRequests - timeBeforeRequests;
		long differenceInSeconds = differenceInMilliSec / 1000;
		
		int noOfRequests = requests;
		
		/**
		 * Here we calculate number of requests per minute
		 */
		double requestsPerMinute = (double)(noOfRequests * 60) / (double)differenceInSeconds;
		requestsPerMinute = Math.round(requestsPerMinute*100.0)/100.0;
		
		/**
		 * If request is within the allowed number we send true else we send false
		 */
		if(requestsPerMinute <= 20) {
			System.out.println("Requests Can be Processed");
			System.out.println("Rate : " + requestsPerMinute + " requests/min\n");
			return true;
		}
		else {
			System.out.println("Overflow of requests");
			System.out.println("Rate : " + requestsPerMinute + " requests/min\n");
			return false;
		}
	}
}
