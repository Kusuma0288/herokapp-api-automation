package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.stepdefinitions.ApigeeSharedData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected ApigeeSharedData sharedData;
  protected List<Header> headerList;
  ObjectMapper mapper = new ObjectMapper();

  public BaseHelper() {
    this.headerList = new LinkedList<>();
    this.sharedData = ApigeeApplicationContext.getSharedData();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }


  public long convertToEpochTime() {
    Date today = Calendar.getInstance().getTime();

    // Constructs a SimpleDateFormat using the given pattern
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");

    // format() formats a Date into a date/time string.
    String currentDateAndTime = dateFormat.format(today);
    long epochTime;

    try {

      // parse() parses text from the beginning of the given string to produce a date.
      Date date = dateFormat.parse(currentDateAndTime);

      // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.
      epochTime = date.getTime();

    } catch (ParseException e) {
      epochTime = 0;
      e.printStackTrace();
    }

    return epochTime;
  }
}
