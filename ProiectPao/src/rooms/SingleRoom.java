package rooms;

public class SingleRoom extends HotelRoom {

    int extraBeds = 0;
    Boolean withView = false;
    public SingleRoom(int id, int _capacity) {
        super(id, _capacity);
    }

    public SingleRoom(int id, int _capacity, int extraBeds) {
        this(id, _capacity);

        this.extraBeds = extraBeds;
    }

    public SingleRoom(int id, int _capacity, int extraBeds, Boolean withView) {
        this(id, _capacity, extraBeds);
        this.withView = withView;
    }

    @Override
    public int calculatePrice() {
        if (withView == false)
        return standardPrice + 50 * extraBeds; // the single room
        else
            return standardPrice + 50 * extraBeds + 100;
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}
