package ru.learnup.nexigntask.callscdrplus.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс-сущность, представляющий данные из таблицы clients
 */
@Entity
@Table(name = "clients")
@Getter
@Setter
public class Client {

    @Id
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

    public Client(Long id, String phoneNumber, Double balance, Integer benefitMinutesLeft, Tariff tariff) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.benefitMinutesLeft = benefitMinutesLeft;
        this.tariff = tariff;
    }

    public Client() {

    }

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
