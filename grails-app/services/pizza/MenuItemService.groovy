package pizza

import org.springframework.transaction.annotation.Transactional

@Transactional
class MenuItemService {

    MenuItem saveMenuItem(MenuItem menuItem) {
        return menuItem.merge()
    }

    void removeMenuItem(Long id) {
        MenuItem.get(id).delete()
    }

    MenuItem updateMenuItem(MenuItem menuItem) {
        return menuItem.merge()
    }

    List<MenuItem> getAllMenuItem() {
        return MenuItem.list();
    }

    MenuItem getMenuItem(Long id) {
        return MenuItem.get(id);
    }
}
