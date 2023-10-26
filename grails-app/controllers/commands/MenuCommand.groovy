package commands

import grails.validation.Validateable

class MenuCommand implements Validateable {
    Long idMenu;
    Long idMenuItem;
    BigDecimal costMenuItem;
    String portionMenuItem;
    String unit;

    static constraints = {
        idMenu validator: { val, object ->
            if (val < 0) return false
        }
        idMenuItem validator: { val, object ->
            if (val <= 0) return false
        }
        costMenuItem validator: { val, obj ->
            if (val <= 0) return false
        }
        portionMenuItem(nullable: true)
        unit(nullable: true)

    }
}
