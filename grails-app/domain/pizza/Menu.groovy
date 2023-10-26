package pizza

import enums.Unit

class Menu {
    String portion;
    BigDecimal cost;
    Unit unit;
    MenuItem menuItem;

    static mapping = {
        table "Menus"
        id column: "id_menu", generator: "identity"
        portion length: 5
        unit enumType: "string"
        version false
    }

    static constraints = {
        portion(blank: false, size: 2..5)
        cost validator: { val, obj ->
            if (val <= 0) return false
        }
    }

    static belongsTo = [menuItem: MenuItem]
    static hasMany = [orderMenu: OrderMenu, pizzeriaMenus: PizzeriaMenu]


}
