package pizza

class Pizzeria {
    String name;
    Address address

    static mapping = {
        table "Pizzerias"
        id column: "id_pizzeria", generator: "identity"
        name  length: 30
        version false
    }

    static constraints = {
        name(blank: false, size: 3..30, unique: true)

    }

    static hasMany = [orders: Orders, pizzeriaMenu:PizzeriaMenu]

}

