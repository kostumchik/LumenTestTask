
package dto.addressBalance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    @JsonProperty("available_balance")
    private String availableBalance;
    private List<Balance> balances;
    private String network;
    @JsonProperty("pending_received_balance")
    private String pendingReceivedBalance;


}
