package repo;

import model.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


public class InMemoryPostRepositoryTest {

    final Post post1 = Post.of("post1");
    final Post post2 = Post.of("post2");
    final Post post3 = Post.of("post3");

    private PostRepository postRepository;

    @Before
    public void setUp(){
        this.postRepository = new InMemoryPostRepository();
    }

    @Test
    public void testAddGet() {
        final Post post1WithId = post1.withId(this.postRepository.add(post1));
        Post post = this.postRepository.findById(post1WithId.getId());
        assertEquals(post1WithId, post);
    }

    @Test
    public void update() {
        final Post post1WithId = post1.withId(this.postRepository.add(post1));
        Post postUpd = new Post(post1WithId.getId(), "post update", System.currentTimeMillis());
        this.postRepository.update(postUpd);
        assertEquals(postUpd, this.postRepository.findById(post1WithId.getId()));
    }

    @Test
    public void getAll() {
        long id1 = this.postRepository.add(post1);
        long id2 = this.postRepository.add(post2);
        long id3 = this.postRepository.add(post3);
        Collection<Post> posts = this.postRepository.findAll();
        assertEquals(3, posts.size());
        assertThat(posts, hasItems(post1.withId(id1), post2.withId(id2), post3.withId(id3)));
    }

    @Test
    public void getAllWithLimit() {
        final Post post1WithId = post1.withId(this.postRepository.add(post1));
        final Post post2WithId = post2.withId(this.postRepository.add(post2));
        final Post post3WithId = post3.withId(this.postRepository.add(post3));
        Collection<Post> posts = this.postRepository.findAll(0, 2);
        assertEquals(2, posts.size());
        assertThat(posts, hasItems(post1WithId, post2WithId));

        posts = this.postRepository.findAll(2, 5);
        assertEquals(1, posts.size());
        assertThat(posts, hasItems(post3WithId));
    }

    @Test
    public void remove() {
        final Post post1WithId = post1.withId(this.postRepository.add(post1));
        final Post post2WithId = post2.withId(this.postRepository.add(post2));
        final Post post3WithId = post3.withId(this.postRepository.add(post3));

        assertThat(this.postRepository.findAll(), hasItems(post1WithId, post2WithId, post3WithId));
        this.postRepository.delete(post2WithId.getId());
        assertThat(this.postRepository.findAll(), hasItems(post1WithId, post3WithId));
        this.postRepository.delete(post1WithId.getId());
        assertThat(this.postRepository.findAll(), hasItems(post3WithId));
        this.postRepository.delete(post3WithId.getId());
        assertTrue(this.postRepository.findAll().isEmpty());
    }
}
