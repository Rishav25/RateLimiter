import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import org.junit.jupiter.api.Test;

class RateLimiterTest {
	
	@Test
	void testRequests() {
		RateLimiter.REQUESTS = 0;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:01:00";
		
		try {
			assertEquals(true , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testRequestsInOneMinute() {
		RateLimiter.REQUESTS = 18;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:01:00";
		
		try {
			assertEquals(true , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRequestsInOneMinuteFailing() {
		RateLimiter.REQUESTS = 22;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:01:00";
		
		try {
			assertEquals(false , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRequestsInMultipleMinute() {
		RateLimiter.REQUESTS = 50;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:02:30";
		
		try {
			assertEquals(true , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRequestsInMultipleMinuteFailing() {
		RateLimiter.REQUESTS = 98;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:04:12";
		
		try {
			assertEquals(false , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRequestsRandom() {
		RateLimiter.REQUESTS = 56;
		RateLimiter.BEFORE_REQUEST_TIME = "11-01-2022 13:00:00";
		RateLimiter.AFTER_REQUEST_TIME = "11-01-2022 13:03:00";
		
		try {
			assertEquals(true , RateLimiter.checkRateLimiter(RateLimiter.REQUESTS));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
