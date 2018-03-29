package repo;

import model.Post;

import java.util.Collection;

public interface PostRepository {
    Post findById(long id);
    Collection<Post> findAll();
    Collection<Post> findAll(long from, int size);
    void add(Post post);
    void update(Post post);
    void delete(long id);
}
