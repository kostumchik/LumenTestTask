import com.github.javafaker.Faker;
import dto.addressBalance.GetAddressBalanceDTO;
import dto.newAddress.GetNewAddressDTO;
import dto.transactionHistory.GetTransactionHistoryDTO;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import utils.EntityReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BitcoinTestNetTest {

    private static final RestHelper restHelper = new RestHelper();
    private static final String BASE_PATH = "/api/v2/";
    private static final String GET_NEW_ADDRESS_PATH = "get_new_address/";
    private static final String GET_ADDRESS_BALANCE_PATH = "get_address_balance/";
    private static final String GET_TRANSACTIONS_PATH = "get_transactions/";
    private static final String API_KEY = "da41-d42e-2726-1c32";
    private static final String ADDRESS = "2Mu5KYeNVdE5tKSfmiFUFvcA9RMZWENvCdH";
    private static final String TYPE = "received";

    @Test
    public void getNewAddressTest() {
        Faker faker = new Faker();
        String label = faker.name().lastName();
        Map<String, Object> params = new HashMap<>();
        params.put("api_key", API_KEY);
        params.put("label", label);
        Response response = restHelper.sendGetRequest(BASE_PATH + GET_NEW_ADDRESS_PATH, params);
        assertThat(response.asString())
                .doesNotContain("fail")
                .doesNotContain("error_message");

        GetNewAddressDTO getNewAddressResponse = EntityReader
                .getEntityByClassName(response, GetNewAddressDTO.class);
        GetNewAddressDTO expectedGetNewAddressResponse = EntityReader
                .getEntityFromFile("json/getNewAddressResponse.json", GetNewAddressDTO.class);

        assertThat(getNewAddressResponse)
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes("data.label", "data.address", "data.userId")
                .isEqualTo(expectedGetNewAddressResponse);

        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(getNewAddressResponse.getData().getLabel())
                    .isEqualTo(label);
            soft.assertThat(getNewAddressResponse.getData().getUserId())
                    .isNotNull();
            soft.assertThat(getNewAddressResponse.getData().getAddress())
                    .isNotNull();
        });

    }

    @Test
    public void getAddressBalanceTest() {
        List<String> addresses = new ArrayList<>();
        addresses.add(ADDRESS);
        Map<String, Object> params = new HashMap<>();
        params.put("api_key", API_KEY);
        params.put("addresses", addresses);
        Response response = restHelper.sendGetRequest(BASE_PATH + GET_ADDRESS_BALANCE_PATH, params);

        GetAddressBalanceDTO getAddressBalanceResponse = EntityReader
                .getEntityByClassName(response, GetAddressBalanceDTO.class);
        GetAddressBalanceDTO expectedGetAddressBalanceResponse = EntityReader
                .getEntityFromFile("json/getAddressBalanceResponse.json", GetAddressBalanceDTO.class);

        assertThat(getAddressBalanceResponse)
                .usingRecursiveComparison()
                .isEqualTo(expectedGetAddressBalanceResponse);
    }

    @Test
    public void getTransactionsHistoryTest() {
        List<String> addresses = new ArrayList<>();
        addresses.add(ADDRESS);
        Map<String, Object> params = new HashMap<>();
        params.put("api_key", API_KEY);
        params.put("type", TYPE);
        params.put("addresses", addresses);
        Response response = restHelper.sendGetRequest(BASE_PATH + GET_TRANSACTIONS_PATH, params);
        System.out.println(response.jsonPath().toString());

        GetTransactionHistoryDTO getTransactionHistoryResponse = EntityReader
                .getEntityByClassName(response, GetTransactionHistoryDTO.class);
        GetTransactionHistoryDTO expectedTransactionHistoryResponse = EntityReader
                .getEntityFromFile("json/getTransactionHistoryResponse.json", GetTransactionHistoryDTO.class);

        assertThat(getTransactionHistoryResponse)
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes("data.txs.confirmations")
                .isEqualTo(expectedTransactionHistoryResponse);
    }
}
