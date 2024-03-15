import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoadTest {
    private final RestTemplate template = new RestTemplate();

    @Test
    public void loadTest() throws InterruptedException, IOException {
        List<Long> ids = prepareIds();
        int counter = 0;
        while (true) {
            long id = ids.get(counter % ids.size());
            System.out.println(sendRequest(id));
            counter++;
            Thread.sleep(1000);
        }
    }

    private String sendRequest(long id) {
        String url = id == -1 ? "http://localhost:8080/books" : String.format("http://localhost:8080/books/%d", id);
        return template.exchange(url, HttpMethod.GET, null, String.class).getBody();
    }

    private List<Long> prepareIds() throws IOException {
        List<Long> ids = new ArrayList<>();
        ids.add(-1L);
        byte[] json = sendRequest(-1).getBytes(StandardCharsets.UTF_8);
        JsonNode rootNode = new ObjectMapper().readTree(json);
        rootNode.iterator().forEachRemaining(node -> {
            ids.add(node.path("book").path("id").asLong());
        });
        return ids;
    }
}

