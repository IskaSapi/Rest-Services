package kz.bitlab.toDo.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "todos")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "is_done",nullable = false)
    private boolean isDone;

    @Column(name = "created_at",nullable = false)
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        this.isDone = false;
        this.createdAt = new Date();
    }
}
