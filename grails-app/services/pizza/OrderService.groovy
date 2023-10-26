package pizza

import converters.TransformerFactory
import dto.SumOrderDto
import org.hibernate.Query
import org.hibernate.SQLQuery
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.springframework.transaction.annotation.Transactional
import pizza.Query.MenuQeuries

@Transactional
class OrderService {
    SessionFactory sessionFactory

    List<SumOrderDto> getTotalOrderInPizzeria(Map map) {
        final Session session = sessionFactory.currentSession
        SQLQuery sqlQuery =
                session.createSQLQuery(MenuQeuries.GET_TOTAL_ORDERS_IN_PIZZERIA)
        def result = sqlQuery.with {
            setProperties(map)
            list()
        }
        return result
    }

    List<SumOrderDto> getTotalOrderInPizzeriaHql(Map map) {
        final Session session = sessionFactory.currentSession
        String query = """select 
                        orders.number as numberOrder,
                        sum(orderMenus.qtyMenuItem*menu.cost) 
                        from Orders orders
                        join orders.orderMenus orderMenus
                        join orderMenus.menu menu
                        join orders.pizzeria pizzeria
                        where pizzeria.name in (:namePizzeria) 
                           group by orders.number 
                           having  sum(orderMenus.qtyMenuItem*menu.cost) between (:min) and (:max)"""

        Query queryMenu =
                session.createQuery(query)
                        .setProperties(map)
        return queryMenu.list()
    }

    List<SumOrderDto> getTotalOrderInPizzeriaCriteria(String pizzeriaName) {
        (List<SumOrderDto>) Orders.createCriteria().list {
            resultTransformer(new TransformerFactory().getSumOrderDtoResultTransformer())
            pizzeria {
                eq "name", pizzeriaName
            }
            projections {
                groupProperty("number", "numberOrder")
                orderMenus {
                    menu {
                        sum("cost", "price")
                    }
                }
            }
        }
    }
}


