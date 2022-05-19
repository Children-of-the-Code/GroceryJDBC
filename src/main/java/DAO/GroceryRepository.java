package DAO;

import Model.FoodType;
import Model.Grocery;
import Util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroceryRepository {
    Connection conn;
    public GroceryRepository(){
        conn = ConnectionFactory.getConnection();
        try {
            conn.setAutoCommit(false);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public List<Grocery> getAllGroceriesByFoodType(String type){
        List<Grocery> groceries = new ArrayList<>();
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM GROCERY INNER JOIN FoodType on " +
                    "Grocery.FoodType = FoodType.FoodTypeID WHERE FoodTypeName = ?");
            int parameterIndex = 0;
            statement.setString(++parameterIndex, type);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Grocery g = new Grocery(rs.getInt("GroceryID"), rs.getString("GroceryName"),
                        rs.getInt("FoodType"));
                groceries.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groceries;
    }
    public List<Grocery> getAllEdibleGroceries(){
        List<Grocery> groceries = new ArrayList<>();
        try{

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM GROCERY INNER JOIN FOODTYPE " +
                    "ON GROCERY.FOODTYPE = FOODTYPE.FOODTYPEID WHERE CanEat = 1");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Grocery g = new Grocery(rs.getInt("GroceryID"), rs.getString("GroceryName"),
                        rs.getInt("FoodType"));
                groceries.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groceries;
    }
    public void addGrocery(Grocery g){
        try{
            PreparedStatement statement = conn.prepareStatement("INSERT INTO GROCERY (GroceryID, GroceryName, FoodType) " +
                    "VALUES(?, ?, ?)");
            int parameterIndex = 0;
            statement.setInt(++parameterIndex, g.getGroceryID());
            statement.setString(++parameterIndex, g.getGroceryName());
            statement.setInt(++parameterIndex, g.getFoodType());
            statement.executeQuery();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
