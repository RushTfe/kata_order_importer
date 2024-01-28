package com.pgalindo.kata.order.importer.repository;

import com.pgalindo.kata.order.importer.model.entity.Order;
import com.pgalindo.kata.order.importer.model.entity.SummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query(value = "DELETE FROM Order")
    void deleteOrders();

    @Query(value = """
            SELECT
                count(o.id) as totalCount,
                sum(o.total_cost) as totalCost,
                sum(o.total_profit) as totalProfit,
                c.name as name
            FROM
                `order` o
            JOIN
                country c
            ON
                o.country_id = c.id
            GROUP BY
                c.id
            ORDER BY
                c.name
            """,
    nativeQuery = true)
    List<SummaryProjection> findCountrySummaries();
    List<Order> findAllByOrderByOriginalOrderIdAsc();
}
