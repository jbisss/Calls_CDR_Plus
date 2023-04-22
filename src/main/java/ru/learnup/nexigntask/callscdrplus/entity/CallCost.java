package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * Класс-сущность, представяющий записи из таблицы call_cost
 */
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

    @OneToMany(mappedBy = "inCallCostId", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Tariff> tariff_1;

    @OneToMany(mappedBy = "outCallCostId", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Tariff> tariff_2;

    @Override
    public String toString(){
        return "{id= " + id
                + ", defaultMinutesCost= " + defaultMinuteCost
                + ", benefitMinutesCost= " + benefitMinuteCost
                + "}";
    }
}
