package pizza

import commands.PizzeriaCommand
import commands.TotalOrdersCommand
import dto.SumOrderDto
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.http.HttpStatus

class OrderController {
    OrderService orderService

    def index() {}

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def showTotalOrderInPizzeria(TotalOrdersCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        LinkedHashMap param =
                [namePizzeria: command.namePizzeria,
                 min         : command.minTotalOrder,
                 max         : command.maxTotalOtder]
        List<Object> list = orderService.getTotalOrderInPizzeria(param)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def showTotalOrderInPizzeriaHql(TotalOrdersCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        LinkedHashMap param =
                [namePizzeria: command.namePizzeria,
                 min         : command.minTotalOrder,
                 max         : command.maxTotalOtder]
        List<SumOrderDto> list = orderService.getTotalOrderInPizzeriaHql(param)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def showTotalOrderInPizzeriaCriteria(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<SumOrderDto> list = orderService.getTotalOrderInPizzeriaCriteria(command.namePizzeria);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }


}
