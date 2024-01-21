package com.pgalindo.kata.order.importer.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "`order`")
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
    private LocalDate shipDate;

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
    private BigDecimal totalProfit;

    public Order() {
    }

    public Order(UUID uuid,
                 Country country,
                 ItemType itemType,
                 SalesChannel salesChannel,
                 Priority priority,
                 LocalDate date,
                 LocalDate shipDate,
                 Integer unitsSold,
                 BigDecimal unitPrice,
                 BigDecimal unitCost,
                 BigDecimal totalRevenue,
                 BigDecimal totalCost,
                 BigDecimal totalProfit) {
        this.uuid = uuid;
        this.country = country;
        this.itemType = itemType;
        this.salesChannel = salesChannel;
        this.priority = priority;
        this.date = date;
        this.shipDate = shipDate;
        this.unitsSold = unitsSold;
        this.unitPrice = unitPrice;
        this.unitCost = unitCost;
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.totalProfit = totalProfit;
    }

    public Integer getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    public Integer getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(Integer unitsSold) {
        this.unitsSold = unitsSold;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }
}
