package com.pgalindo.kata.order.importer.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
