package repo;

import model.Post;
import utils.Utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPostRepository implements PostRepository {

    private final Map<Long, Post> posts;

    public InMemoryPostRepository() {
        this.posts = new HashMap<>();
    }

    public Post findById(long id) {
        Utils.nyi();
        return null;
    }

    public Collection<Post> findAll() {
        Utils.nyi();
        return null;
    }

    public Collection<Post> findAll(long from, int size) {
        Utils.nyi();
        return null;
    }

    public long add(Post post) {
        Utils.nyi();
        return 0;
    }

    public void update(Post post) {
        Utils.nyi();
    }

    public void delete(long id) {
        Utils.nyi();
    }
}
