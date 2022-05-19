package Controller;

import Model.FoodType;
import Model.Grocery;
import Service.FoodTypeService;
import Service.GroceryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

public class GroceryController {

    /*
    Let's say that for now I just want to implement some CRUD operations on
    Groceries and foodtype

    CREATE foodtypes and CREATE groceries
    UPDATE foodtypes (to change canEat)
    DELETE groceries
    GET all groceries
    GET all foodtypes
    GET all groceries by food type
    GET all edible groceries
     */
    public GroceryController(){

    }
    public void startController(){
        Javalin app = Javalin.create();
        app.start(9000);
        FoodTypeService fs = new FoodTypeService();
        GroceryService gs = new GroceryService();
        app.get("foodType/all", context -> {
            context.json(fs.getAllFoodTypes());
        });
        app.get("foodType/name/{name}", context -> {
            String name = context.pathParam("name");
            context.json(fs.getFoodTypeByName(name));
        });
        app.post("foodType", context -> {
            ObjectMapper mapper = new ObjectMapper();
            FoodType f = mapper.readValue(context.body(), FoodType.class);
            fs.createFoodType(f);
        });
        app.put("foodType", context -> {
            ObjectMapper mapper = new ObjectMapper();
            FoodType f = mapper.readValue(context.body(), FoodType.class);
            fs.updateFoodType(f);
        });
        app.get("grocery/byType/{name}", context -> {
            String name = context.pathParam("name");
            context.json(gs.getAllGroceriesByFoodType(name));
        });
        app.get("grocery/edible", context-> {
            context.json(gs.getAllEdibleGroceries());
        });
        app.post("grocery", context -> {
            ObjectMapper mapper = new ObjectMapper();
            Grocery g = mapper.readValue(context.body(), Grocery.class);
            gs.createGrocery(g);
        });
    }

}
