
package dto.transactionHistory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tx {

    @JsonProperty("amounts_received")
    private List<AmountsReceived> amountsReceived;
    private Double confidence;
    private Long confirmations;
    @JsonProperty("from_green_address")
    private Boolean fromGreenAddress;
    @JsonProperty("propagated_by_nodes")
    private Object propagatedByNodes;
    private List<String> senders;
    private Long time;
    private String txid;

}
