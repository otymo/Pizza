package converters

import commands.MenuCommand
import enums.Unit
import org.springframework.core.convert.converter.Converter
import pizza.Menu

class MenuConverter implements Converter<MenuCommand, Menu> {
    @Override
    Menu convert(MenuCommand source) {
        Menu menu = new Menu();
        menu.id = source.idMenu
        menu.cost = source.costMenuItem;
        menu.portion = source.portionMenuItem;
        menu.unit = Unit.valueOf(source.getUnit());
        return menu;
    }
}

