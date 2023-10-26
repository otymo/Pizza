package pizza

class PizzeriaMenu {

    static mapping = {
        table "Pizzerias_Menus"
        id column: "id_pizzeriamenu", generator: "identity"
        version false
    }

    static belongsTo = [pizzeria: Pizzeria, menu: Menu]
}
