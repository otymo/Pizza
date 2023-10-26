package pizza

import java.sql.Date

class Orders {

    String number;
    Date dateCreated;
    boolean isPaid;

    static mapping = {
        id column: "id_order", generator: "identity"
        number length: 10
        isPaid defaultValue: "0"
        version false
        dateCreated defaultValue: "now()"
    }

    static constraints = {
        number(blank: false, unique: true,size: 1..10)
    }

    static belongsTo = [pizzeria:Pizzeria]
    static hasMany = [orderMenus: OrderMenu]
}
