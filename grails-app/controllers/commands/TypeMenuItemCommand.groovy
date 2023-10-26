package commands

import grails.validation.Validateable

class TypeMenuItemCommand implements Validateable {
    String typeMenuItem;

    static constraints = {
        typeMenuItem(blank: false)
    }
}
