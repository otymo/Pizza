package pizza

class OrderMenu {

    Integer qtyMenuItem;

    static mapping = {
        table "Orders_Menus"
        id column: "id_order_menu", generator: "identity"
        qtyMenuItem column: "quantity", defaultValue: 0
        version false
    }

    static constraints = {
        qtyMenuItem(min: 1)
    }

    static belongsTo = [menu: Menu, order: Orders]
}