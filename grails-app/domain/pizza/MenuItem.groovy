package pizza

import enums.TypeMenuItem

class MenuItem {
    String name;
    String composition;
    TypeMenuItem type;


    static mapping = {
        table "MenuItem"
        id column: "id_menuitem", generator: "identity"
        name length: 50
        composition length: 500, type: "text"
        type enumType: "string"
        version false
    }

    static constraints = {
        name(blank: false, unique: true)
        composition(nullable: true)
        menus(nullable: true)
    }

    static hasMany = [menus: Menu]

}
