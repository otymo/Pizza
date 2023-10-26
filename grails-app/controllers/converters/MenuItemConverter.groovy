package converters

import commands.MenuItemCommand
import enums.TypeMenuItem
import org.springframework.core.convert.converter.Converter
import pizza.MenuItem


class MenuItemConverter implements Converter<MenuItemCommand, MenuItem> {
    @Override
    MenuItem convert(MenuItemCommand source) {
        MenuItem menuItem = new MenuItem();
        menuItem.name = source.nameMenuItem;
        menuItem.composition = source.compositionMenuItem;
        menuItem.type = TypeMenuItem.valueOf(source.typeMenuItem);
        return menuItem;
    }
}


