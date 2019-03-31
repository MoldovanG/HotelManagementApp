package rooms;

public abstract  class HotelRoom {
    protected int id;
    protected int  standardPrice = 175;
    protected int capacity = 1;
    public abstract int calculatePrice();
    public abstract String getRoomType();
    @Override
    public String toString() {
        return "Camera cu id-ul : "+Integer.toString(id) + " are pretul de baza "+Integer.toString(standardPrice)+ " si capacitatea de "+Integer.toString(capacity)+" persoane";
    }

    public HotelRoom(int id, int _capacity) {
        this.id = id;
        this.capacity = _capacity;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
