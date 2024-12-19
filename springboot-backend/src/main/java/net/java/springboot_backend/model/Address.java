package net.java.springboot_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addr")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "emp_id")
//   private Employee emp;

    public Address(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Address() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
