package Model;

public class Grocery {
    private String groceryName;
    private int groceryID;
    private int foodType;

    public Grocery(){

    }
    public Grocery(int groceryID, String groceryName, int foodType) {
        this.groceryName = groceryName;
        this.groceryID = groceryID;
        this.foodType = foodType;
    }

    public String getGroceryName() {
        return groceryName;
    }

    public void setGroceryName(String groceryName) {
        this.groceryName = groceryName;
    }

    public int getGroceryID() {
        return groceryID;
    }

    public void setGroceryID(int groceryID) {
        this.groceryID = groceryID;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }
}
