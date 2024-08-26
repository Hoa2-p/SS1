package SS1;

class Resource {
    private int id;
    private double cost;
    private double benefit;
    // Thêm thuộc tính priority nếu cần

    public Resource(int id, double cost, double benefit) {
        this.id = id;
        this.cost = cost;
        this.benefit = benefit;
    }

    public int getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public double getBenefit() {
        return benefit;
    }
}



