package commands

import grails.validation.Validateable

class DeleteMenuItemCommand implements Validateable {
    Long idMenuItem

    static constraints = {
        idMenuItem validator: { val, object ->
            if (val <= 0) return false
        }
    }
}
