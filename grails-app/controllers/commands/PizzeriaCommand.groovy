package commands

import grails.validation.Validateable

class PizzeriaCommand implements Validateable {
    String namePizzeria

    static constraints = {
        namePizzeria(blank: false)
    }
}
