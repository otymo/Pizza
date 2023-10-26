package dto

import enums.TypeMenuItem

class NumberTypeMenuDto {
    String type;
    Integer number

    NumberTypeMenuDto(TypeMenuItem type, Integer number) {
        this.type = type.name()
        this.number = number
    }
}
