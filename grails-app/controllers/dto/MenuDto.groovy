package dto

import enums.Unit

class MenuDto {

    String nameMenuItem
    String compositionMenuItem;
    String portion;
    BigDecimal cost;
    String unit;

   class MenuDtoBuilder {
        String nameMenuItem
        String compositionMenuItem;
        String portion;
        BigDecimal cost;
        String unit;

        MenuDtoBuilder name(String name) {
            this.nameMenuItem = name;
            return this
        }

        MenuDtoBuilder composition(String composition) {
            this.compositionMenuItem = composition;
            return this
        }

        MenuDtoBuilder portion(String portion) {
            this.portion = portion;
            return this
        }

        MenuDtoBuilder cost(BigDecimal cost) {
            this.cost = cost;
            return this
        }

        MenuDtoBuilder unit(Unit unit) {
            this.unit = unit.name();
            return this
        }

        MenuDto build() {
           MenuDto dto=new MenuDto()
            dto.nameMenuItem=this.nameMenuItem
            dto.compositionMenuItem=this.compositionMenuItem
            dto.portion=this.portion
            dto.cost=this.cost
            dto.unit=this.unit

            return dto
        }
    }


}
