package com.pgalindo.kata.order.importer.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "item_type_id")
    private ItemType itemType;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_channel_id")
    private SalesChannel salesChannel;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @Column(name = "date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "ship_date", columnDefinition = "DATE")
    private LocalDate ship_date;

    @Column(name = "units_sold")
    private Integer unitsSold;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "unit_cost")
    private BigDecimal unitCost;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

    @Column(name = "total_profit")
    private BigDecimal unitProfit;

}
