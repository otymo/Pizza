package pizza

class Address {

    String city;
    String street;
    String home;

    static mapping = {
        table "Addresses"
        id column: "id_address", generator: "identity"
        city length: 50
        street length: 50
        home length: 5
        version false
    }

    static constraints = {
        city(blank: false, size: 3..50)
        street(blank: false, size: 3..50)
        home(blank: false, size: 1..5)
    }

    static belongsTo = Pizzeria
}
