import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class LoadTest {
    @Test
    public void loadTest() throws InterruptedException {
        RestTemplate template = new RestTemplate();
        List<Long> ids = List.of(-1L, 100001L, 100002L, 100003L, 100004L, 100005L);
        int counter = 0;
        while (true) {
            Long id = ids.get(counter % 6);
            String url = id == -1 ? "http://localhost:8080/books" : String.format("http://localhost:8080/books/%d", id);
            String result = template.exchange(url, HttpMethod.GET, null, String.class).getBody();
            System.out.println(result);
            counter++;
            Thread.sleep(1000);
        }
    }
}
