package au.com.woolworths.apigee.model;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

public class CheckoutRequest {
    private int window;
    private String date;

    @Override
    public String toString() {
        return "CheckoutRequest{" +
                "window='" + window + '\'' +
                "date='" + date + '\'' +
                '}';
    }

    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }

    public int getWindow()
    {
        return window;
    }
    public void setWindow(int window)
    {
        this.window = window;
    }

}
