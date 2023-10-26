package dto

import enums.TypeMenuItem

class SumTypeMenuDto {
    String type;
    BigDecimal price

    SumTypeMenuDto(TypeMenuItem type, BigDecimal price) {
        this.type = type.name()
        this.price = price
    }
}
