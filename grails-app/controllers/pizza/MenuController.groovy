package pizza

import commands.CostMenuItemCommand
import commands.DeleteMenuCommand
import commands.MenuCommand
import commands.PizzeriaCommand
import commands.TypeMenuItemCommand
import dto.MenuDto
import dto.NumberTypeMenuDto
import dto.SumTypeMenuDto
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.sql.GroovyRowResult
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus


class MenuController {

    MenuService menuService
    MenuItemService menuItemService
    ConversionService conversionService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        List<Menu> list = menuService.getAllMenu()
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        List<MenuDto> result = list.collect { conversionService.convert(it, MenuDto.class) }
        render result as JSON
        respond result
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(MenuCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        Menu menu = conversionService.convert(command, Menu.class)
        MenuItem menuItem = menuItemService.getMenuItem(command.idMenuItem)
        menu.menuItem = menuItem
        Menu result = menuService.saveMenu(menu)

        if (result == null) {
            response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        redirect action: "index", method: "GET"
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(DeleteMenuCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        menuService.removeMenu(command.idMenu);
        redirect action: "index", method: "GET"
    }
/*
SQL
**************************************************************************************************
 */

    @Secured('ROLE_ADMIN')
    def showAllMenuWithSql() {
        List<GroovyRowResult> list = menuService.showAll()
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuByTypeWithSql(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<GroovyRowResult> list = menuService.getMenuByType(command.typeMenuItem)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuBetweenCost(CostMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<GroovyRowResult> list = menuService.getMenuBetweenCost(command.minCostMenuItem, command.maxCostMenuItem)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuSortedByCost(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<Object> list = menuService.getMenuSortedByCost(command.typeMenuItem)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showNumberMenuByTypeInPizzeria(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<Object> list = menuService.getNumberMenuByTypeInPizzeria(command.namePizzeria)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showSumMenuByTypeInPizzeria(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<Object> list = menuService.getSumMenuByTypeInPizzeria(command.namePizzeria)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }
/*
HQL
**************************************************************************************************
 */

    @Secured('ROLE_ADMIN')
    def showAllMenuHql() {
        List<MenuDto> list = menuService.getAllMenuHql();
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuByTypeHql(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<MenuDto> list = menuService.getAllMenuByTypeHql(command.typeMenuItem)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuBetweenCostHql(CostMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        LinkedHashMap<String, BigDecimal> param = [min: command.minCostMenuItem,
                                                   max: command.maxCostMenuItem]
        List<MenuDto> list = menuService.getMenuBetweenCostHql(param)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuSortedByCostHql(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<MenuDto> list = menuService.getMenuSortedByCostHql(command.typeMenuItem)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showNumberTypeMenuInPizzeriaHql(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<NumberTypeMenuDto> list = menuService.getNumberTypeMenuInPizzeriaHql(command.namePizzeria)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showSumTypeMenuInPizzeriaHql(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<SumTypeMenuDto> list = menuService.getSumTypeMenuInPizzeriaHql(command.namePizzeria)
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    /*
Criteria
**************************************************************************************************
 */

    @Secured('ROLE_ADMIN')
    def showAllMenuCriteria() {
        List<MenuDto> list = menuService.getAllMenuCriteria();
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured(['ROLE_USER'])
    def showMenuByTypeCriteria(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<MenuDto> list = menuService.getAllMenuByTypeCriteria(command.typeMenuItem);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuSortedByCostCriteria(TypeMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<MenuDto> list = menuService.getMenuSortedByCostCriteria(command.typeMenuItem);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showMenuBetweenCostCriteria(CostMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        LinkedHashMap<String, BigDecimal> param = [min: command.minCostMenuItem,
                                                   max: command.maxCostMenuItem]
        List<MenuDto> list = menuService.getMenuBetweenCostCriteria(param);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showNumberTypeMenuInPizzeriaCriteria(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<NumberTypeMenuDto> list = menuService.getNumberTypeMenuInPizzeriaCriteria(command.namePizzeria);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }

    @Secured('ROLE_ADMIN')
    def showSumTypeMenuInPizzeriaCriteria(PizzeriaCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        List<SumTypeMenuDto> list = menuService.getSumTypeMenuInPizzeriaCriteria(command.namePizzeria);
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render list as JSON
        respond list
    }
}
