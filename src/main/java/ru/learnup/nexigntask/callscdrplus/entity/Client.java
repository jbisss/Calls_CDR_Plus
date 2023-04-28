package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.learnup.nexigntask.callscdrplus.listener.ClientChangeListener;

/**
 * Класс-сущность, представляющий данные из таблицы clients
 */
@Entity
@Table(name = "clients")
@EntityListeners(ClientChangeListener.class)
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
