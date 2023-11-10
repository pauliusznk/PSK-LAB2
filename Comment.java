package model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String CommentText;

    @ManyToOne
    private Comment ParentComment;
    @OneToMany(mappedBy = "ParentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Manager manager;

    @OneToOne
    private Forum forum;

    public Comment(String title, String commentText) {
        this.title = title;
        this.CommentText = commentText;
    }

    public Comment(String title, String commentText, Comment parentComment) {
        this.title = title;
        this.CommentText = commentText;
        this.replies = new ArrayList<>();
        this.CommentText = String.valueOf(parentComment);
    }

    public Comment(String title, String commentText, Comment parentComment, List<Comment> replies, Driver driver, Forum forum) {
        this.title = title;
        this.CommentText = commentText;
        this.ParentComment = parentComment;
        this.replies = replies;
        this.driver = driver;
        this.forum = forum;
    }

    public Comment(String title, String commentText, Comment parentComment, Comment answer, List<Comment> replies, Driver driver, Manager manager, Forum forum) {


        this.title = title;
        this.CommentText = commentText;
        this.ParentComment = parentComment;
        this.replies = replies;
        this.driver = driver;
        this.manager = manager;
        this.forum = forum;
    }

    @Override
    public String toString() {
        if(manager == null)
        return  driver.getLogin()+" (Driver)\n" + "Title: " + title + "\nMessage: " + CommentText;
        else
            return  manager.getLogin()+" (Manager)\n" + "Title: " + title + "\nMessage: " + CommentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        boolean ObjectFound = Objects.equals(id, comment.id);
        return ObjectFound;
    }

    @Override
    public int hashCode() {
        //For later
        return 0;
    }
}