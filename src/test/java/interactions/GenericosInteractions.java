package interactions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions; // Import correto

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericosInteractions {

    private Playwright playwright;
    private APIRequest apiRequest;
    private APIRequestContext requestContext;
    private APIResponse response;

    private String baseUrl;
    private Map<String, String> headers = new HashMap<>();
    private String body;

    public GenericosInteractions() {
        playwright = Playwright.create();
        apiRequest = playwright.request();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void initRequest() {
        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions();
        if (baseUrl != null) options.setBaseURL(baseUrl);
        if (!headers.isEmpty()) options.setExtraHTTPHeaders(headers);
        requestContext = apiRequest.newContext(options);
    }

    public void addHeaders(Map<String, String> headers) {
        if (headers != null) this.headers.putAll(headers);
    }

    public void setBody(Map<String, String> body) {
        try {
            this.body = new ObjectMapper().writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBody(String jsonBody) {
        this.body = jsonBody;
    }

    public APIResponse sendRequest(String method, String endpoint) {
        if (requestContext == null) initRequest();
        String url = endpoint;
        RequestOptions reqOptions = null;
        if (body != null && !body.isEmpty() && !method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("DELETE")) {
            reqOptions = RequestOptions.create().setData(body);
        }

        switch (method.toUpperCase()) {
            case "POST":
                response = reqOptions != null ? requestContext.post(url, reqOptions)
                        : requestContext.post(url);
                break;
            case "PUT":
                response = reqOptions != null ? requestContext.put(url, reqOptions)
                        : requestContext.put(url);
                break;
            case "GET":
                response = requestContext.get(url);
                break;
            case "DELETE":
                response = requestContext.delete(url);
                break;
            case "PATCH":
                response = reqOptions != null ? requestContext.patch(url, reqOptions)
                        : requestContext.patch(url);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
        return response;
    }

    public APIResponse sendRequestWithQueryParams(String method, String endpoint, Map<String, String> queryParams) {
        if (requestContext == null) initRequest();
        StringBuilder url = new StringBuilder(endpoint);
        if (queryParams != null && !queryParams.isEmpty()) {
            url.append("?");
            queryParams.forEach((k, v) -> url.append(k).append("=").append(v).append("&"));
            url.setLength(url.length() - 1);
        }
        return sendRequest(method, url.toString());
    }

    public void assertItemFieldValue(APIResponse response, String chaveBusca, String valorBusca, String campoValidar, String valorEsperado) throws JsonProcessingException {
        String json = response.text();
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> itens = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
        Map<String, Object> itemEncontrado = itens.stream()
                .filter(m -> valorBusca.equals(
                        m.getOrDefault(chaveBusca, "").toString()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Nenhum item encontrado com " + chaveBusca + "=" + valorBusca));
        Object valor = itemEncontrado.get(campoValidar);
        assertEquals(valorEsperado, valor != null ? valor.toString() : null);
    }

    public APIResponse getResponse() {
        return response;
    }
}