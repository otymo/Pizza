import grails.core.GrailsApplication
import pizza.MenuItem
import pizza.Role
import pizza.User
import pizza.UserRole

class BootStrap {
    def groovySql

    def init = { servletContext ->
        String sqlString = new File("./import.sql.txt").text;
        groovySql.execute(sqlString)
        groovySql.close();

        Role roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        Role roleUser = new Role(authority: 'ROLE_USER').save()

        User adminUser = new User(
                username: "admin",
                password: "password").save()

        def simpleUser = new User(
                username: "user",
                password: "password").save()

        UserRole userRole = new UserRole()
        userRole.create(adminUser, roleAdmin)
        userRole.create(simpleUser, roleUser)

//additional tasks
        /*List<MenuItem> items = MenuItem.findAll()
        Map<Long, String> itemMap = items.collectEntries { MenuItem menuItem ->
            [menuItem.id, menuItem.name]
        }
        items = items + null
        Map<Long, String> map = items.collectEntries(itemMap) { index -> [items.size(), items[items.size() - 1]] }*/
        //      List<> keys = map.findAll { it.value == null }.collect { it?.key }
        //    keys.each { it -> map.remove(it) }

        /* Map<Long, String> subMap = map.findAll { it.key <= 10 }
         Map<?> subMapGroup = map.groupBy { it.key <= 2 }
         String str = subMap.toMapString()

         assert subMap.any { entry -> entry.key == 5 }

         Map<String, Integer> newMap = [a: 1, b: 2].withDefault { k -> k.toCharacter().isLowerCase() ? 10 : -10 }
         Map<String, Integer> expected = [a: 1, b: 2, c: 10, D: -10]
         assert expected.every { e -> e.value == newMap[e.key] }*/

        def destroy = {
        }


    }


}

