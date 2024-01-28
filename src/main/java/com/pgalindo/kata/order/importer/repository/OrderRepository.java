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
                COUNT(o.id) as totalCount,
                SUM(o.totalCost) as totalCost,
                SUM(o.totalProfit) as totalProfit,
                c.name as name
            FROM
                Order o
            JOIN
                o.country c
            GROUP BY
                c.id
            ORDER BY
                c.name
            """)
    List<SummaryProjection> findCountrySummaries();

    @Query(value = """
            SELECT
                COUNT(o.id) as totalCount,
                SUM(o.totalCost) as totalCost,
                SUM(o.totalProfit) as totalProfit,
                r.name as name
            FROM
                Order o
            JOIN
                o.country c
            JOIN
                c.region r
            GROUP BY
                r.id
            ORDER BY
                r.name
            """)
    List<SummaryProjection> findRegionSummaries();

    @Query(value = """
            SELECT
                COUNT(o.id) as totalCount,
                SUM(o.totalCost) as totalCost,
                SUM(o.totalProfit) as totalProfit,
                it.name as name
            FROM
                Order o
            JOIN
                o.itemType it
            GROUP BY
                it.id
            ORDER BY
                it.name
            """)
    List<SummaryProjection> findItemTypeSummaries();

    @Query(value = """
            SELECT
                COUNT(o.id) as totalCount,
                SUM(o.totalCost) as totalCost,
                SUM(o.totalProfit) as totalProfit,
                sc.name as name
            FROM
                Order o
            JOIN
                o.salesChannel sc
            GROUP BY
                sc.id
            ORDER BY
                sc.name
            """)
    List<SummaryProjection> findSalesChannelSummaries();

    @Query(value = """
            SELECT
                COUNT(o.id) as totalCount,
                SUM(o.totalCost) as totalCost,
                SUM(o.totalProfit) as totalProfit,
                p.name as name
            FROM
                Order o
            JOIN
                o.priority p
            GROUP BY
                p.id
            ORDER BY
                p.name
            """)
    List<SummaryProjection> findPrioritySummaries();
    List<Order> findAllByOrderByOriginalOrderIdAsc();
}
