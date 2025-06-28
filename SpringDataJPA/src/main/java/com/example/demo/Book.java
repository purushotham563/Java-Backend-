package com.example.demo;

import javax.persistence.*;
import javax.sound.midi.Sequence;
import java.time.LocalDateTime;

@Entity(name = "Book")
@Table(name="book")
public class Book {
    @Id
    @SequenceGenerator(name = "book_sequence",sequenceName
            = "book_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_sequence")
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "created_at",nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;
    @Column(name="book_name",nullable = false)
    private String bookName;
    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false,referencedColumnName = "id",foreignKey =
    @ForeignKey(name = "student_book_fk"))
    private Student student;

    public Book() {
    }

    public Book( String bookName,LocalDateTime createdAt ) {
        this.createdAt = createdAt;
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                ", student=" + student +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public Student getStudent() {
        return student;
    }
}
