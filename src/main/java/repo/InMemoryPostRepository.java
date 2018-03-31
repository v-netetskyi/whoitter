package repo;

import model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {

    private final Map<Long, Post> posts;
    private AtomicLong id = new AtomicLong(0);

    public InMemoryPostRepository() {
        this.posts = new HashMap<>();
    }

    public Post findById(long id) {
        return this.posts.get(id);
    }

    public Collection<Post> findAll() {
        return this.posts.values();
    }

    public Collection<Post> findAll(long from, int size) {
        return this.posts.values().stream().skip(from).limit(size).collect(Collectors.toList());
    }

    public long add(Post post) {
        long postId = this.id.incrementAndGet();
        this.posts.put(postId, post.withId(postId));
        return postId;
    }

    public void update(Post post) {
        this.posts.computeIfPresent(post.getId(), (key, value) -> value = post);
    }

    public void delete(long id) {
        this.posts.remove(id);
    }
}
