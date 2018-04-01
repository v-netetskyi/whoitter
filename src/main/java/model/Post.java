package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(onConstructor = @__(@JsonCreator))
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private final Long id;
    private final String text;
    private final Long createdAtEpochMillis;

    public static Post of(String text) {
        return new Post(null, text, System.currentTimeMillis());
    }

    public Post withId(long postId) {
        return new Post(postId, this.text, this.createdAtEpochMillis);
    }
}
