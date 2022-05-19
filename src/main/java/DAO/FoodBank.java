package DAO;

import Model.FoodType;
import Util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodBank {
    Connection conn;
    public FoodBank(){
        conn = ConnectionFactory.getConnection();
    }
    public List<FoodType> getAllFoodTypes(){
        List<FoodType> foodTypes = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * From FoodType");
//            a result set is not quite as nice as the sets we worked with last week-
//            it's very very primitive and all we can do is iterate through its items
//            (each item being a row returned from a query)
//            result set rows will iterate itself every iteration of this for loop
            while(rs.next()){
//                load a food type object from the row returned by a result set
                FoodType f = new FoodType(rs.getInt("FoodTypeID"), rs.getString("FoodTypeName"),
                        rs.getInt("CanEat") == 1);
//                add it to my list of returned food types
                foodTypes.add(f);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return foodTypes;
    }
    public FoodType getFoodTypeByName(String name){
        try{
            /*
            Statement statement = conn.createStatement();
            statement.executeQuery("Select * from FoodType where FoodTypeName = "+name);
            This will result in being vulnerable to sql injection (someone could send in sql commands as a grocery name)
             */
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM FoodType where FoodTypeName = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            rs.next();
            FoodType f = new FoodType(rs.getInt("FoodTypeID"), rs.getString("FoodTypeName"),
                    rs.getInt("CanEat") == 1);
            return f;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public void createFoodType(FoodType f){
        try{
            PreparedStatement statement = conn.prepareStatement("INSERT INTO FoodType (FoodTypeName, CanEat) " +
                    "VALUES(?, ?)" );
            int parameterIndex = 0;
            statement.setString(++parameterIndex, f.getFoodTypeName());
            if(f.isCanEat()){
                statement.setInt(++parameterIndex, 1);
            }else{
                statement.setInt(++parameterIndex, 0);
            }
            statement.executeQuery();


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean checkIfFoodTypeExistsByName(String name){
        try{
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM FoodType Where FoodTypeName = ?");
            int parameterIndex = 0;
            statement.setString(++parameterIndex, name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateFoodTypeCanEat(FoodType f) {

        try{

            PreparedStatement statement = conn.prepareStatement("UPDATE FoodType " +
                    "SET CanEat = ? WHERE FoodTypeName = ?" );
            int parameterIndex = 0;
            if(f.isCanEat()){
                statement.setInt(++parameterIndex, 1);
            }else{
                statement.setInt(++parameterIndex, 0);
            }
            statement.setString(++parameterIndex, f.getFoodTypeName());
            statement.executeQuery();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}