package rooms;

public class DoubleRoom extends HotelRoom {
    int extraBeds = 0;
    Boolean withView = false;

    public DoubleRoom(int id, int _capacity) {
        super(id, _capacity);
        standardPrice = 200;
    }

    public DoubleRoom(int id, int _capacity, int extraBeds) {
        this(id, _capacity);
        this.extraBeds = extraBeds;
    }

    public DoubleRoom(int id, int _capacity, int extraBeds, Boolean withView) {
        this(id, _capacity, extraBeds);
        this.withView = withView;
    }

    @Override
    public int calculatePrice() {
        if (withView == false)
            return standardPrice + 50*extraBeds;
        else
            return standardPrice + 50 * extraBeds + 100;
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }

    @Override
    public String getType() {
        return "double";
    }
}
