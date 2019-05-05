package rooms;

public class Apartment extends HotelRoom {

    public Apartment(int id, int _capacity) {
        super(id, _capacity);
        standardPrice = 450;
    }

    @Override
    public int calculatePrice() {
        return standardPrice ;
    }

    @Override
    public String getRoomType() {
        return "Apartment";
    }

    @Override
    public String getType() {
        return "apartment";
    }
}
