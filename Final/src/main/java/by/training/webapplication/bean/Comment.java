package by.training.webapplication.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment extends Entity implements Serializable {
    private User user;
    private LocalDateTime localDateTime;
    private String review;

    public Comment() {
    }

    public Comment(int id, User user, LocalDateTime localDateTime, String review) {
        super(id);
        this.user = user;
        this.localDateTime = localDateTime;
        this.review = review;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review= review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        if (!super.equals(o)) return false;
        Comment comment = (Comment) o;
        if (user != null ? !user.equals(comment.user) : comment.user != null) return false;
        if (localDateTime != null ? !localDateTime.equals(comment.localDateTime) : comment.localDateTime != null)
            return false;
        return review != null ? review.equals(comment.review) : comment.review == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (localDateTime != null ? localDateTime.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Comment user: " + user +
                ", localDateTime: " + localDateTime +
                ", review: " + review;
    }
}
