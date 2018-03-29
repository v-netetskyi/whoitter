package model;

import lombok.Data;

@Data
public class Post {
    private final long id;
    private final long createdAtEpoch;
    private final String text;
}
