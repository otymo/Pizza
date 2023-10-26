import converters.MenuItemConverter
import converters.MenuItemDtoConverter
import converters.MenuConverter
import converters.MenuDtoConverter
import converters.UpdateMenuItemConverter
import org.springframework.context.support.ConversionServiceFactoryBean

// Place your Spring DSL code here
beans = {
    menuItemConverter(MenuItemConverter) { bean ->
        bean.scope = 'prototype';
        bean.autowire = "byName"
    }

    menuItemDtoConverter(MenuItemDtoConverter) { bean ->
        bean.scope = 'prototype';
        bean.autowire = "byName"
    }
    menuDtoConverter(MenuDtoConverter) { bean ->
        bean.scope = 'prototype';
        bean.autowire = "byName"
    }
    menuConverter(MenuConverter) { bean ->
        bean.scope = 'prototype';
        bean.autowire = "byName"
    }

    updateMenuItemConverter(UpdateMenuItemConverter) { bean ->
        bean.scope = 'prototype';
        bean.autowire = "byName"
    }


    conversionService(ConversionServiceFactoryBean) {
        converters = [ref("menuItemConverter"),
                      ref("menuItemDtoConverter"),
                      ref("menuConverter"),
                      ref("menuDtoConverter"),
                      ref("updateMenuItemConverter")
        ]
    }

    groovySql(groovy.sql.Sql, ref('dataSource'))

}
