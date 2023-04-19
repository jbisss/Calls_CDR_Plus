package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "benefit_minutes_left")
    private Integer benefitMinutesLeft;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Override
    public String toString(){
        return "\n{id= " + id
                + ", phone_number= " + phoneNumber
                + ", balance= " + balance
                + ", benefit_minutes_left= " + benefitMinutesLeft
                + tariff
                + "}";
    }
}
