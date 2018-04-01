package resource;

import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.PostRepository;

import java.util.Collection;

@RestController
public class PostResource {

    private final PostRepository postRepository;

    @Autowired
    public PostResource(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> addPost(@RequestBody Post post) {
        System.out.println("Received addPost request");
        final long id = this.postRepository.add(post);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        System.out.println("Received getPostById request");
        return new ResponseEntity<>(this.postRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<Collection<Post>> getPosts(@RequestParam Long from, @RequestParam Integer size) {
        System.out.println("Received getPosts request. Params: from " + from + ", size = " + size);
        final Collection<Post> posts;
        if (from != null && size != null) {
            posts = this.postRepository.findAll(from, size);
        } else {
            posts = this.postRepository.findAll();
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
