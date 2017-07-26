package julienbirabent.musicsharringappprotoype.models;

/**
 * Created by julbi on 2017-07-26.
 */

public class Comment {

    private UserProfile commentOwner;
    private String body;

    public Comment(UserProfile commentOwner, String body) {
        this.commentOwner = commentOwner;
        this.body = body;
    }

    public Comment(UserProfile commentOwner) {
        this.commentOwner = commentOwner;
    }

    public UserProfile getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(UserProfile commentOwner) {
        this.commentOwner = commentOwner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
