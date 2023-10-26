package converters

import dto.MenuItemDto
import org.springframework.core.convert.converter.Converter
import pizza.MenuItem

class MenuItemDtoConverter implements Converter<MenuItem, MenuItemDto> {
    @Override
    MenuItemDto convert(MenuItem source) {
        MenuItemDto dto = new MenuItemDto();
        dto.idMenuItem = source.id
        dto.nameMenuItem = source.name;
        dto.compositionMenuItem = source.composition;
        dto.typeMenuItem = source.type.name();
        return dto;
    }
}
