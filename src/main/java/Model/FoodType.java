package Model;

public class FoodType {
    int foodTypeID;
    String foodTypeName;
    boolean canEat;

    public FoodType(){

    }

    public FoodType(int foodTypeID, String foodTypeName, boolean canEat) {
        this.foodTypeID = foodTypeID;
        this.foodTypeName = foodTypeName;
        this.canEat = canEat;
    }

    public int getFoodTypeID() {
        return foodTypeID;
    }

    public void setFoodTypeID(int foodTypeID) {
        this.foodTypeID = foodTypeID;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public boolean isCanEat() {
        return canEat;
    }

    public void setCanEat(boolean canEat) {
        this.canEat = canEat;
    }
}
