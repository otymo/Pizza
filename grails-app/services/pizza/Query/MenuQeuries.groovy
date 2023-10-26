package pizza.Query

class MenuQeuries {
    static String GET_ALL_MENU = """
           SELECT
               name,
               composition,
               cost,
               CONCAT(portion, unit) AS Portion
           FROM menuitem
           INNER JOIN menus ON menuitem.id_menuitem = menus.menu_item_id"""

    static String GET_MENU_BY_TYPE = """
            SELECT
            name,
            composition,
            cost,
            CONCAT(portion, unit) AS portion
               FROM menuitem
               INNER JOIN menus ON menuitem.id_menuitem = menus.menu_item_id
               WHERE TYPE=?"""

    static String GET_MENU_BETWEEN_COST = """
            SELECT
            name,
            composition,
            cost,
            CONCAT(portion, unit) as portion
               FROM menuitem
               INNER JOIN menus ON menuitem.id_menuitem = menus.menгu_item_id
               WHERE COST BETWEEN ? AND ?"""


    static String GET_MENU_SORTED_BY_COST = """
            SELECT
            name,
            composition,
            cost,
            CONCAT(portion, unit) as portion
            FROM menuitem
            INNER JOIN menus ON menuitem.id_menuitem = menus.menu_item_id
               WHERE type=(:type)
               ORDER BY cost ASC"""


    static String GET_NUMBER_TYPE_MENU_IN_PIZZERIA = """
            SELECT
            menuitem.type,COUNT(*)AS total_number
            FROM menuitem
            INNER JOIN menus  ON menuitem.id_menuitem = menus.menu_item_id
            INNER JOIN  pizzerias_menus  ON menus.id_menu = pizzerias_menus.menu_id
            INNER JOIN pizzerias ON pizzerias_menus.pizzeria_id = pizzerias.id_pizzeria
              WHERE pizzerias.name=(:namePizzeria)
               GROUP BY pizzerias.name, menuitem.type"""

    static String GET_SUM_MENU_BY_TYPE_IN_PIZZERIA = """
            SELECT
            menuitem.type,
            SUM(menus.cost)
            FROM menuitem
            INNER JOIN menus  ON menuitem.id_menuitem = menus.menu_item_id
            INNER JOIN  pizzerias_menus  ON menus.id_menu = pizzerias_menus.menu_id
            INNER JOIN pizzerias ON pizzerias_menus.pizzeria_id = pizzerias.id_pizzeria
              WHERE pizzerias.name=(:namepizzeria)
               GROUP BY pizzerias.name,  menuitem.type
                """

    static String GET_TOTAL_ORDERS_IN_PIZZERIA = """
            SELECT
            orders.number AS order_number,
            SUM(orders_menus.quantity*menus.cost) 
            FROM orders
            INNER JOIN orders_menus ON orders.id_order = orders_menus.order_id
            INNER JOIN menus ON orders_menus.menu_id = menus.id_menu
            INNER JOIN menuitem ON menus.menu_item_id = menuitem.id_menuitem
            INNER JOIN pizzerias ON orders.pizzeria_id = pizzerias.id_pizzeria
             WHERE  pizzerias.name=(:namePizzeria) 
               GROUP BY orders.number
               HAVING SUM(orders_menus.quantity*menus.cost) BETWEEN (:min) AND (:max) """


}