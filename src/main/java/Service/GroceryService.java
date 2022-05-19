package Service;

import DAO.GroceryRepository;
import Model.Grocery;

import java.util.List;

public class GroceryService {
//    previously we used an arraylist in our service classes..
//    but this wasn't persistent, so we're replacing that with another class
//    whose job it is to manage our interactions with the database
    GroceryRepository repository;
    public GroceryService(){
        repository = new GroceryRepository();
    }
    public void createGrocery(Grocery g){
        repository.addGrocery(g);
    }
    public void deleteGroceryByID(int id){

    }
    public List<Grocery> getAllGroceries(){
        return null;
    }
    public List<Grocery> getAllGroceriesByFoodType(String name){
        return repository.getAllGroceriesByFoodType(name);
    }
    public List<Grocery> getAllEdibleGroceries(){
        return repository.getAllEdibleGroceries();
    }
    public Grocery getGroceryByName(String name){
        return null;
    }

}
