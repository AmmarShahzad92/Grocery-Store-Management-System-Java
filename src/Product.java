public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;
    private String category;
    private String description;
    private int soldCount;
    private static final int THRESHOLD = 10;

    public Product (int id, String name, int price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = "";
        this.soldCount = 0;
    }

    public String toString() {
        String note = "";
        if(lowStock()) {
            note = "(Only " + stock + " left!)";
        }
        return id + " " + name + " " + price + " " + note;
    }

    public boolean lowStock() {
        return stock <= THRESHOLD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }
}
