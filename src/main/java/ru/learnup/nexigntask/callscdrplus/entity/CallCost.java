package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "call_cost")
@Getter
@Setter
public class CallCost {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "default_minute_cost")
    private Double defaultMinuteCost;

    @Column(name = "benefit_minute_cost")
    private Double benefitMinuteCost;

    @JoinColumn
    @OneToOne
    private Tariff tariff;
}
