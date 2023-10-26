package pizza

import commands.DeleteMenuItemCommand
import commands.MenuItemCommand
import commands.UpdateMenuItemCommand
import dto.MenuItemDto
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.core.convert.ConversionService
import org.springframework.http.HttpStatus

class MenuItemController {

    MenuItemService menuItemService;
    ConversionService conversionService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        List<MenuItem> list = menuItemService.getAllMenuItem();
        if (!list) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        List<MenuItemDto> listDto = list.collect { conversionService.convert(it, MenuItemDto.class) }
        if (!listDto) {
            response.status = HttpStatus.NO_CONTENT.value()
        }
        render listDto as JSON
     //   respond listDto
    }

    @Secured(['ROLE_ADMIN'])
    def save(MenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List errors = command.errors.allErrors.collect {
                message(error: it)
            }
            respond errors
        }
        MenuItem menuItem = conversionService.convert(command, MenuItem.class)
        MenuItem result = menuItemService.saveMenuItem(menuItem)
        if (result.hasErrors()) {
            response.status = HttpStatus.NO_CONTENT.value()
            List errors = result.errors.allErrors.collect {
                message(error: it)
            }
            respond errors
        }
        redirect action: "index", method: "GET"
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(DeleteMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List errors = command.errors.allErrors.collect {
                message(error: it)
            }
            respond errors
        }
        menuItemService.removeMenuItem(command.idMenuItem);
        redirect action: "index", method: "GET"
    }

    def update(UpdateMenuItemCommand command) {
        command.validate()
        if (command.hasErrors()) {
            response.status = HttpStatus.BAD_REQUEST.value()
            List<Closure> errors = command.errors.allErrors.collect {
                message(error: it)
            } as List<Closure>
            respond errors
        }
        MenuItem menuItem = conversionService.convert(command, MenuItem.class)
        MenuItem result = menuItemService.updateMenuItem(menuItem)
        if (result.hasErrors()) {
            response.status = HttpStatus.NO_CONTENT.value()
            List<Closure> errors = result.errors.allErrors.collect {
                message(error: it)
            }as List<Closure>
            respond errors
        }
        redirect action: "index", method: "GET"
    }
}
