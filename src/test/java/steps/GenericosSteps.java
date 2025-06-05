package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import interactions.GenericosInteractions;
import interactions.ScenarioContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.microsoft.playwright.APIResponse;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericosSteps {

    private final ScenarioContext contexto;
    private final GenericosInteractions interactions;

    public GenericosSteps() {
        this.contexto = new ScenarioContext();
        this.interactions = new GenericosInteractions();
    }

    @Given("the base URL is {string}")
    public void the_base_url_is(String url) {
        interactions.setBaseUrl(url);
        contexto.setBaseUrl(url);
    }

    @Given("I set the request headers:")
    public void i_set_the_request_headers(DataTable dataTable) {
        Map<String, String> headers = dataTable.asMap(String.class, String.class);
        interactions.addHeaders(headers);
    }

    @And("I set the request query parameters:")
    public void i_set_the_request_query_parameters(DataTable dataTable) {
        Map<String, String> params = new HashMap<>(dataTable.asMap(String.class, String.class));
        contexto.setQueryParams(params);
    }

    @And("I set the request body with fields:")
    public void i_set_the_request_body_with_fields(DataTable dataTable) {
        Map<String, String> bodyMap = dataTable.asMap(String.class, String.class);
        interactions.setBody(bodyMap);
    }

    @When("I send a {string} request to {string}")
    public void i_send_a_request_to(String method, String endpoint) {
        String url = contexto.getBaseUrl() + endpoint;
        if (contexto.getQueryParams() == null || contexto.getQueryParams().isEmpty()) {
            interactions.sendRequest(method, url);
        } else {
            interactions.sendRequestWithQueryParams(method, url, contexto.getQueryParams());
        }
        try {
            System.out.println(interactions.getResponse().text());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {
        APIResponse response = interactions.getResponse();
        assertEquals(statusCode.intValue(), response.status());
    }

    @Then("the item with {string} equal to {string} should have the field {string} with value {string}")
    public void validateItemDynamically(String chaveBusca, String valorBusca, String campoValidar, String valorEsperado) throws JsonProcessingException {
        APIResponse response = interactions.getResponse();
        interactions.assertItemFieldValue(response, chaveBusca, valorBusca, campoValidar, valorEsperado);
    }
}