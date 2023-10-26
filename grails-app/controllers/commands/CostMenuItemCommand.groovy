package commands

import grails.validation.Validateable

class CostMenuItemCommand implements Validateable {

    BigDecimal minCostMenuItem;
    BigDecimal maxCostMenuItem;

    static constraints = {
        minCostMenuItem validator: { val, object ->
            if (val <= 0) return false
        }
        maxCostMenuItem validator: { val, object ->
            if (val <= 0) return false
        }
    }
}
