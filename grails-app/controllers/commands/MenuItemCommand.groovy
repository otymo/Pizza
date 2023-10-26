package commands

import grails.validation.Validateable

class MenuItemCommand implements Validateable {
    String nameMenuItem;
    String compositionMenuItem;
    String typeMenuItem;

    static constraints = {
        nameMenuItem(blank: false)
        compositionMenuItem(blank: false)
        typeMenuItem(blank: false)

    }
}
