package converters

import commands.UpdateMenuItemCommand
import enums.TypeMenuItem
import org.springframework.core.convert.converter.Converter
import pizza.MenuItem

class UpdateMenuItemConverter implements Converter<UpdateMenuItemCommand, MenuItem> {
    @Override
    MenuItem convert(UpdateMenuItemCommand source) {
        MenuItem menuItem=new MenuItem()
        menuItem.id=source.idMenuItem
        menuItem.name=source.nameMenuItem
        menuItem.composition=source.compositionMenuItem
        menuItem.type=TypeMenuItem.valueOf(source.typeMenuItem)
        return menuItem
    }
}
