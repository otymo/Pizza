package dto

import grails.validation.Validateable

class MenuItemDto implements Validateable {
    Long idMenuItem
    String nameMenuItem;
    String compositionMenuItem;
    String typeMenuItem;


    static constraints = {
        nameMenuItem(blank: false)
        compositionMenuItem(blank: false)
        typeMenuItem(blank: false)
    }

}
