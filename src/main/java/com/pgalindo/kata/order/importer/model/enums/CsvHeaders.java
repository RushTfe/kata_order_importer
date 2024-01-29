package com.pgalindo.kata.order.importer.model.enums;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public enum CsvHeaders {
    ORDERID("orderId"),
    ORDERPRIORITY("orderPriority"),
    ORDERDATE("orderDate"),
    REGION("region"),
    COUNTRY("country"),
    ITEMTYPE("itemType"),
    SALESCHANNEL("salesChannel"),
    SHIPDATE("shipDate"),
    UNITSSOLD("unitsSold"),
    UNITPRICE("unitPrice"),
    UNITCOST("unitCost"),
    TOTALREVENUE("totalRevenue"),
    TOTALCOST("totalCost"),
    TOTALPROFIT("totalProfit");

    private final String value;

    public static List<String> getHeaderValues() {
        return Arrays.stream(CsvHeaders.values())
                .map(header -> header.value)
                .toList();
    }

}
