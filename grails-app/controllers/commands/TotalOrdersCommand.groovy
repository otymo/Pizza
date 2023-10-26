package commands

import grails.validation.Validateable

class TotalOrdersCommand implements Validateable {
    String namePizzeria;
    BigDecimal minTotalOrder;
    BigDecimal maxTotalOtder;

    static constraints = {
        namePizzeria(blank: false)

        minTotalOrder validator: { val, object ->
            if (val <= 0) return false
        }

        maxTotalOtder validator: { val, obj ->
            if (val <= obj.minTotalOrder) {
                return false
            }
        }
    }
}

