package com.amadeusproject.TravelToFuture.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity(name = "roles")
@Table(name = "roles")
@Getter
@Setter
public class Role {


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

}
