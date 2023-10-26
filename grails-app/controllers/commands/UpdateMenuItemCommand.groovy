package commands

import grails.validation.Validateable

class UpdateMenuItemCommand implements Validateable {

    Long idMenuItem
    String nameMenuItem;
    String compositionMenuItem;
    String typeMenuItem;

    static constraints = {
        idMenuItem validator: { val, object ->
            if (val <= 0) return false
        }
        nameMenuItem(blank: false)
        compositionMenuItem(blank: false)
        typeMenuItem(blank: false)

    }
}
