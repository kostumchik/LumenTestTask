
package dto.newAddress;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {

    private String address;
    private String label;
    private String network;
    @JsonProperty("user_id")
    private Long userId;
}
