package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String name;
    @Column(unique = true)
    private String email;


}
