package model;

import lombok.Data;

@Data
public class Post {
    private final Long id;
    private final String text;
    private final long createdAtEpochMillis;

    public static Post of(String text) {
        return new Post(null, text, System.currentTimeMillis());
    }

    public Post withId(long postId) {
        return new Post(postId, this.text, this.createdAtEpochMillis);
    }
}
