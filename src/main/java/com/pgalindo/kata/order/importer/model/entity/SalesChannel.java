package com.pgalindo.kata.order.importer.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sales_channel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
