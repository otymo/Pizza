package enums

enum TypeMenuItem {

    PIZZA("PIZZA"),
    DESSERT("DESSERT"),
    COLDDRINK("COLDDRINK"),
    HOTDRINK("HOTDRINK"),
    COCTAIL("COCTAIL"),
    WINE("WINE"),
    SALAD("SALAD"),
    BURGER("BURGER")

     String value;

    TypeMenuItem(String type) {
        value = type
    }

    TypeMenuItem getType(String name) {
        return TypeMenuItem.valueOf(name)

    }

}