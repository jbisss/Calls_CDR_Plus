package ru.learnup.nexigntask.callscdrplus.entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * Класс-сущность, представляющий данные из таблицы tariff
 */
@Entity
@Table(name = "tariff")
@Getter
@Setter
public class Tariff {

    @Id
    @Column(name = "tariff_id")
    private String tariffId;

    @Column(name = "tariff_name")
    private String tariffName;

    @Column(name = "subscription_fee")
    private Integer subscriptionFee;

    @Column(name = "benefit_minutes")
    private Integer benefitMinutes;

    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Client> client;

    @ManyToOne
    @JoinColumn(name = "in_call_cost_id")
    private CallCost inCallCostId;

    @ManyToOne
    @JoinColumn(name = "out_call_cost_id")
    private CallCost outCallCostId;

    @Override
    public String toString(){
        return " {id= " + tariffId
                + ", tariffName= " + tariffName
                + ", subscriptionFee= " + subscriptionFee
                + ", benefitMinutes= " + benefitMinutes
                + inCallCostId
                + outCallCostId
                + "}";
    }
}
