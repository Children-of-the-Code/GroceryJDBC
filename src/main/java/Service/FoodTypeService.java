package Service;

import DAO.FoodBank;
import Model.FoodType;

import java.util.List;

public class FoodTypeService {
    FoodBank repository;
    public FoodTypeService(){
        repository = new FoodBank();
    }
    public void createFoodType(FoodType f){
        if(!repository.checkIfFoodTypeExistsByName(f.getFoodTypeName())) {
            repository.createFoodType(f);
        }
    }
    public void updateFoodType(FoodType f){
        repository.updateFoodTypeCanEat(f);
    }
    public List<FoodType> getAllFoodTypes(){
        return repository.getAllFoodTypes();
    }
    public FoodType getFoodTypeByName(String name){
        if(repository.checkIfFoodTypeExistsByName(name)) {
            return repository.getFoodTypeByName(name);
        }else{
            return null;
        }
    }

}
