class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")

        "/menuItem/"(controller: "MenuItemController", action: "save", method: ["POST","PUT"])
        "/menuIitem/"(controller: "MenuItemController", action: "delete", method: ["DELETE", "POST"])
        "/menuIitem/"(controller: "MenuItemController", action: "update", method: ["PUT", "POST"])
        "/menuItem/"(controller: "MenuItemController", action: "showAll", method: ["GET"])

        "/menu/"(controller: "MenuController", action: "save", method: "POST,PUT")
        "/menu/"(controller: "MenuController", action: "delete", method: "POST,DELETE")

        "/menu/"(controller: "MenuController", action: "showAllMenuWithSql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuByTypeWithSql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuBetweenCost", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuSortedByCost", method: "GET")
        "/menu/"(controller: "MenuController", action: "showSumMenuByTypeInPizzeria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showNumberMenuByTypeInPizzeria", method: "GET")
        "/menu/"(controller: "MenuController", action: "save", method: ["POST","PUT"])

        "/menu/"(controller: "MenuController", action: "showAllMenuHql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuByTypeHql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuBetweenCostHql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuSortedByCostHql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showNumberTypeMenuInPizzeriaHql", method: "GET")
        "/menu/"(controller: "MenuController", action: "showSumTypeMenuInPizzeriaHql", method: "GET")

        "/menu/"(controller: "MenuController", action: "showAllMenuCriteria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuByTypeCriteria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuBetweenCostCriteria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showMenuSortedByCostCriteria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showNumberTypeMenuInPizzeriaCriteria", method: "GET")
        "/menu/"(controller: "MenuController", action: "showSumTypeMenuInPizzeriaCriteria", method: "GET")

        "/order/"(controller: "OrderController", action: "showTotalOrderInPizzeria", method: "GET")
        "/order/"(controller: "OrderController", action: "showTotalOrderInPizzeriaHql", method: "GET")
        "/order/"(controller: "OrderController", action: "showTotalOrderInPizzeriaCriteria", method: "GET")

        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
