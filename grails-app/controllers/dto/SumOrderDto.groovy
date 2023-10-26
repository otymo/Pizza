package dto

class SumOrderDto {

    String numberOrder
    BigDecimal price

    static constraints = {
        numberOrder(blank: false)

    }

    SumOrderDto(String numberOrder, BigDecimal price) {
        this.numberOrder = numberOrder
        this.price = price
    }


}
