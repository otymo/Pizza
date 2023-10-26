package converters

import dto.SavedMenuItemDto
import org.springframework.core.convert.converter.Converter
import pizza.MenuItem

class SavedMenuItemConverter implements Converter<MenuItem, SavedMenuItemDto> {
    @Override
    SavedMenuItemDto convert(MenuItem source) {
        SavedMenuItemDto dto=new SavedMenuItemDto()
        dto.idMenuItem=source.id
        dto.nameMenuItem=source.name
        dto.compositionMenuItem=source.composition
        dto.typeMenuItem=source.type.name()
        return dto
    }
}
