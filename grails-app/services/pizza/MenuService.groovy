package pizza

import converters.TransformerFactory
import dto.MenuDto
import dto.NumberTypeMenuDto
import dto.SumTypeMenuDto
import enums.TypeMenuItem
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import org.hibernate.Query
import org.hibernate.SQLQuery
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import pizza.Query.MenuQeuries

@Transactional
class MenuService {
    SessionFactory sessionFactory
    Sql groovySql

    Menu saveMenu(Menu menu) {
        menu.save()
    }

    void removeMenu(Long idMenu) {
        Menu.findById(idMenu).delete()
    }

    List<Menu> getAllMenu() {
        Menu.list()
    }

/*SQL
*********************************************************************************
 */

    List<GroovyRowResult> showAll() {
        return groovySql.rows(MenuQeuries.GET_ALL_MENU)

    }

    List<GroovyRowResult> getMenuByType(String typeMenuItem) {
        return groovySql.rows(MenuQeuries.GET_MENU_BY_TYPE, typeMenuItem)
    }

    List<GroovyRowResult> getMenuBetweenCost(BigDecimal min, BigDecimal max) {
        List<BigDecimal> params = [min, max]
        return groovySql.rows(MenuQeuries.GET_MENU_BETWEEN_COST, params)
    }

    def getMenuSortedByCost(String typeMenuItem) {
        Session session = sessionFactory.currentSession
        SQLQuery sqlQuery = session.createSQLQuery(MenuQeuries.GET_MENU_SORTED_BY_COST)
        List<Object> result = sqlQuery.with {
            setText("type", typeMenuItem)
            list()
        }
        return result
    }

    def getNumberMenuByTypeInPizzeria(String namePizzeria) {
        final Session session = sessionFactory.currentSession
        final SQLQuery sqlQuery = session.createSQLQuery(MenuQeuries.GET_NUMBER_TYPE_MENU_IN_PIZZERIA)
        List<Object> result = sqlQuery.with {
            setText("namePizzeria", namePizzeria)
            list()
        }
        return result
    }

    def getSumMenuByTypeInPizzeria(String namePizzeria) {
        final Session session = sessionFactory.currentSession
        final SQLQuery sqlQuery = session.createSQLQuery(MenuQeuries.GET_SUM_MENU_BY_TYPE_IN_PIZZERIA)
        List<Object> result = sqlQuery.with {
            setText("namePizzeria", namePizzeria)
            list()
        }
        return result
    }

/*HQL
*********************************************************************************
 */

    List<MenuDto> getAllMenuHql() {
        final Session session = sessionFactory.currentSession
        String query = """SELECT 
                              menu.menuItem.name, 
                              menu.menuItem.composition,
                              menu.portion,
                              menu.cost,
                              menu.unit
                          FROM Menu menu
                          """
        Query queryMenu =
                session.createQuery(query)
                        .setResultTransformer(new TransformerFactory().getMenuDtoResultTransformer());
        return queryMenu.list()
    }

    List<MenuDto> getAllMenuByTypeHql(String typeMenuItem) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                          menu.menuItem.name, 
                          menu.menuItem.composition,
                          menu.portion,
                          menu.cost,
                          menu.unit 
                          from Menu menu
                             where menu.menuItem.type in (:type)"""
        Query queryMenu =
                session.createQuery(query)
                        .setText("type", typeMenuItem)
                        .setResultTransformer(new TransformerFactory().getMenuDtoResultTransformer());
        return queryMenu.list()
    }

    List<MenuDto> getMenuBetweenCostHql(Map map) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                          menu.menuItem.name, 
                          menu.menuItem.composition,
                          menu.portion,
                          menu.cost,
                          menu.unit 
                          from Menu menu
                             where menu.cost between (:min) and (:max)"""
        Query queryMenu =
                session.createQuery(query)
                        .setProperties(map)
                        .setResultTransformer(new TransformerFactory().getMenuDtoResultTransformer());
        return queryMenu.list()
    }

    List<MenuDto> getMenuSortedByCostHql(String typeMenuItem) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                          menu.menuItem.name, 
                          menu.menuItem.composition,
                          menu.portion,
                          menu.cost, 
                          menu.unit
                          from Menu menu
                            where menu.menuItem.type in (:type)
                            order by cost desc"""
        Query queryMenu =
                session.createQuery(query)
                        .setText("type", typeMenuItem)
                        .setResultTransformer(new TransformerFactory().getMenuDtoResultTransformer());
        return queryMenu.list()
    }

    List<NumberTypeMenuDto> getNumberTypeMenuInPizzeriaHql(String namePizzeria) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                          menu.menuItem.type as type,
                          count(*)
                          from Menu menu
                          join menu.pizzeriaMenus pizzeriaMenu
                          join pizzeriaMenu.pizzeria pizzeria
                          where pizzeria.name in (:namePizzeria)
                             group by menu.menuItem.type"""
        Query queryMenu =
                session.createQuery(query)
                        .setText("namePizzeria", namePizzeria)
                        .setResultTransformer(new TransformerFactory().getNumberTypeMenuDtoResultTransformer())
        return queryMenu.list()
    }

    List<SumTypeMenuDto> getSumTypeMenuInPizzeriaHql(String namePizzeria) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                          menu.menuItem.type as type,
                          sum(menu.cost) as total
                          from Menu menu
                          join menu.pizzeriaMenus pizzeriaMenu
                          join pizzeriaMenu.pizzeria pizzeria
                          where pizzeria.name in (:namePizzeria)
                             group by menu.menuItem.type"""
        Query queryMenu =
                session.createQuery(query)
                        .setText("namePizzeria", namePizzeria)
                        .setResultTransformer(new TransformerFactory().getSumTypeMenuDtoResultTransformer())
        return queryMenu.list()
    }

    /*
    Criteria
    *******************************************************************************************
     */

    List<MenuDto> getAllMenuCriteria() {
        (List<MenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getMenuDtoResultTransformer())
            createAlias('menuItem', 'menuItem')
            projections {
                property("menuItem.name", "menuItem.name")
                property("menuItem.composition", "menuItem.composition")
                property("portion", "portion")
                property("cost", "cost")
                property("unit", "unit")
            }
        }

    }

    List<MenuDto> getAllMenuByTypeCriteria(String typeMenuItem) {
        (List<MenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getMenuDtoResultTransformer())
            createAlias('menuItem', 'menuItem')
            eq "menuItem.type", TypeMenuItem.valueOf(typeMenuItem)
            projections {
                property("menuItem.name", "menuItem.name")
                property("menuItem.composition", "menuItem.composition")
                property("portion", "portion")
                property("cost", "cost")
                property("unit", "unit")
            }
        }

    }

    List<MenuDto> getMenuSortedByCostCriteria(String typeMenuItem) {
        (List<MenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getMenuDtoResultTransformer())
            eq "menuItem.type", TypeMenuItem.valueOf(typeMenuItem)
            order("cost", "desc")
            createAlias('menuItem', 'menuItem')
            projections {
                property("menuItem.name", "menuItem.name")
                property("menuItem.composition", "menuItem.composition")
                property("portion", "portion")
                property("cost", "cost")
                property("unit", "unit")
            }
        }
    }

    List<MenuDto> getMenuBetweenCostCriteria(LinkedHashMap<String, BigDecimal> param) {
        (List<MenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getMenuDtoResultTransformer())
            createAlias('menuItem', 'menuItem')
            between("cost", param.min, param.max)
            projections {
                property("menuItem.name", "menuItem.name")
                property("menuItem.composition", "menuItem.composition")
                property("portion", "portion")
                property("cost", "cost")
                property("unit", "unit")
            }
        }
    }

    List<NumberTypeMenuDto> getNumberTypeMenuInPizzeriaCriteria(String pizzeriaName) {
        (List<NumberTypeMenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getNumberTypeMenuDtoResultTransformer())
            createAlias('menuItem', 'menuItem')
            pizzeriaMenus {
                pizzeria {
                    eq "name", pizzeriaName
                }
            }
            projections {
                groupProperty("menuItem.type")
                count("menuItem.type")
            }
            order("menuItem.type", "desc")
        }
    }

    List<SumTypeMenuDto> getSumTypeMenuInPizzeriaCriteria(String pizzeriaName) {
        (List<SumTypeMenuDto>) Menu.createCriteria().list {
            resultTransformer(new TransformerFactory().getSumTypeMenuDtoResultTransformer())
            createAlias('menuItem', 'menuItem')
            pizzeriaMenus {
                pizzeria {
                    eq "name", pizzeriaName
                }
            }
            projections {
                groupProperty("menuItem.type")
                sum("cost", "price")
            }
            order("menuItem.type", "desc")
        }
    }
}
