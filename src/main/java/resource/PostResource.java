package resource;

import lombok.extern.slf4j.Slf4j;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repo.PostRepository;

import java.util.Collection;

@RestController
@Slf4j
public class PostResource {

    private final PostRepository postRepository;

    @Autowired
    public PostResource(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> addPost(@RequestBody Post post) {
        log.info("Received POST addPost request. Params [{}]", post);
        final long id = this.postRepository.add(post);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> getPost(@PathVariable long id) {
        log.info("Received GET getPostById request. Params [{}]", id);
        return new ResponseEntity<>(this.postRepository.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<Collection<Post>> getPosts(@RequestParam Long from, @RequestParam Integer size) {
        log.info("Received GET getPosts request. Params: from {}, size {}", from, size);
        final Collection<Post> posts;
        if (from != null && size != null) {
            posts = this.postRepository.findAll(from, size);
        } else {
            posts = this.postRepository.findAll();
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
