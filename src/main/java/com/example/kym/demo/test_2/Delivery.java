package com.example.kym.demo.test_2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "delivery")
public class Delivery extends AbstractPersistable<Long> {

    @Column
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "delivery", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Pizza> pizzaList = new ArrayList<Pizza>(5);

    public Delivery addPizzas(Pizza... p) {

        pizzaList.addAll(List.of(p));
        return this;

    }

    public Delivery addPizzaColl(Collection<Pizza> pizzas) {

        pizzaList.addAll(pizzas);
        return this;
    }


    public Delivery removeAllInColl(Collection<Pizza> pizzas) {
        pizzaList.removeAll(pizzas);
        return this;
    }

    public List<Pizza> getPizzas() {
        List<Pizza> p = Collections.unmodifiableList(pizzaList);
        return p;
    }

    public Delivery removeAll() {
        pizzaList.clear();
        return this;
    }

}
