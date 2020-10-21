package au.com.woolworths.model.iris.graphql.changeMyOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SubTotal {
    @JsonProperty("__typename")
    private String typename;
    private String title;
    private String amount;

}
