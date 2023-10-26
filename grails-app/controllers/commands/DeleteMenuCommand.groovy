package commands

import grails.validation.Validateable

class DeleteMenuCommand implements Validateable{
    Long idMenu

    static constraints = {
        idMenu validator: { val, object ->
            if (val <= 0) return false
        }
    }
}
