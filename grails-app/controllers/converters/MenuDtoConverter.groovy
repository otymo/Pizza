package converters

import dto.MenuDto
import org.springframework.core.convert.converter.Converter
import pizza.Menu

class MenuDtoConverter implements Converter<Menu, MenuDto> {
    @Override
    MenuDto convert(Menu source) {
        MenuDto dto = new MenuDto();
        dto.nameMenuItem = source.menuItem.name
        dto.compositionMenuItem = source.menuItem.composition;
        dto.cost = source.cost;
        dto.portion = source.portion;
        dto.unit = source.unit.name();
        return dto;
    }


}
