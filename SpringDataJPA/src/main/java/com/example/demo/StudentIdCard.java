package com.example.demo;

import javax.persistence.*;

@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",uniqueConstraints = {
        @UniqueConstraint(name = "student_unique_card_number",columnNames = "card_number")
})
public class StudentIdCard {
    @Id
    @SequenceGenerator(name="student_id_card_sequence",sequenceName = "student_id_card_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="student_id_card_sequence")
    @Column(name="id",updatable = false)
    private Long id;
    @Column(name = "card_number",nullable = false,length = 15)
    private String cardNumber;
    @OneToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id",
            referencedColumnName = "id",foreignKey = @ForeignKey(name = "student_id_fk"))
    private Student student;
    public StudentIdCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
