package com.store.eating.model;



import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Component
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String chef;

    @NotEmpty
    @Size(min = 5)
    private String name;

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @ManyToOne
    @JoinColumn(name = "Store")
    private Store store;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public void setId(Long id) {
        this.id = id;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", chef='" + chef + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
