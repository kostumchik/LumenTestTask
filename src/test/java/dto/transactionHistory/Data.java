
package dto.transactionHistory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private String network;
    private List<Tx> txs;
}
