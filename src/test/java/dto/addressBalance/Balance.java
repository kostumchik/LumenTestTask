
package dto.addressBalance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {

    private String address;
    @JsonProperty("available_balance")
    private String availableBalance;
    private String label;
    @JsonProperty("pending_received_balance")
    private String pendingReceivedBalance;
    @JsonProperty("user_id")
    private Long userId;
}
