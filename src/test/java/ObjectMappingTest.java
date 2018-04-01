import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfigs;
import junit.framework.TestCase;
import model.Post;
import org.junit.Test;

import java.io.IOException;

public class ObjectMappingTest {

    @Test
    public void postMapping() throws IOException {
        final AppConfigs appConfigs = new AppConfigs();
        final ObjectMapper objectMapper = appConfigs.objectMapper();

        final Post post1 = Post.of("some text");
        final Post post2 = new Post(null, "some text2", System.currentTimeMillis());
        final Post post3 = new Post(128L, "some text2", null);
        final Post post4 = new Post(128L, "some text2", System.currentTimeMillis());

        final String json1 = objectMapper.writeValueAsString(post1);
        final String json2 = objectMapper.writeValueAsString(post2);
        final String json3 = objectMapper.writeValueAsString(post3);
        final String json4 = objectMapper.writeValueAsString(post4);

        TestCase.assertEquals(post1, objectMapper.readValue(json1, Post.class));
        TestCase.assertEquals(post2, objectMapper.readValue(json2, Post.class));
        TestCase.assertEquals(post3, objectMapper.readValue(json3, Post.class));
        TestCase.assertEquals(post4, objectMapper.readValue(json4, Post.class));
    }
}
