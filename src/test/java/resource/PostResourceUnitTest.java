package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfigs;
import config.WebConfigs;
import model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import repo.PostRepository;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfigs.class, AppConfigs.class, PostResourceUnitTest.TestConfigs.class})
@WebAppConfiguration
public class PostResourceUnitTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppConfiguration;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
    }

    @Test
    public void addPost() throws Exception {
        when(postRepository.add(any())).thenReturn(1L);
        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":128,\"text\":\"some text2\",\"createdAtEpochMillis\":1522589992848}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void getPost() throws Exception {
        final Post post = Post.of("some post");
        when(postRepository.findById(1L)).thenReturn(post.withId(1L));
        mockMvc.perform(get("/post/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(post.withId(1L))));
    }

    @Test
    public void getPosts() throws Exception {
        final Collection<Post> posts = Arrays.asList(Post.of("text1").withId(1L), Post.of("text").withId(2L), Post.of("text3").withId(3L));
        when(postRepository.findAll(0, 50)).thenReturn(posts);
        mockMvc.perform(get("/posts?from=0&size=50")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(posts)));
    }

    @Profile("test")
    @Configuration
    public static class TestConfigs {

        @Bean
        @Primary
        public PostRepository postRepository() {
            return Mockito.mock(PostRepository.class);
        }
    }
}