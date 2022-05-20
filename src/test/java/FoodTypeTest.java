import DAO.FoodBank;
import DAO.GroceryRepository;
import Model.FoodType;
import Model.Grocery;
import Service.FoodTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.never;

public class FoodTypeTest {
    FoodBank mockRepository;
    FoodType mockFoodType;
    FoodTypeService fs;
    @BeforeEach
    public void setUp(){

//        Mockito.mock creates for us a object that does nothing on its own
//        but, we can still call its methods (which do nothing)
//        and mockito allows us to redefine what those specific method calls should do
        mockRepository = Mockito.mock(FoodBank.class);
        mockFoodType = Mockito.mock(FoodType.class);
//        the reason we do this is because when we are testing, we care about only a single objects behavior:
//        we don't want the non-functioning of the DAO class to affect our development of the service class
        fs = new FoodTypeService(mockRepository);
    }
    @Test
    public void getFoodTypeByNameExistsTest(){
        Mockito.when(mockRepository.checkIfFoodTypeExistsByName("Dairy")).thenReturn(true);
        Mockito.when(mockRepository.getFoodTypeByName("Dairy")).thenReturn(mockFoodType);
        Mockito.when(mockFoodType.getFoodTypeName()).thenReturn("Dairy");
        FoodType returnedFoodType =fs.getFoodTypeByName("Dairy");
        Assertions.assertTrue(returnedFoodType.equals(mockFoodType));
    }
    @Test
    public void getFoodTypeByNameTestDoesNotExistTest(){
        Mockito.when(mockRepository.checkIfFoodTypeExistsByName("Bread")).thenReturn(false);
        FoodType returnedFoodType =fs.getFoodTypeByName("Bread");
        Assertions.assertNull(returnedFoodType);
    }
    @Test
    public void verifyAddFoodTypeCalledIfDoesNotExist(){
        Mockito.when(mockFoodType.getFoodTypeName()).thenReturn("Pasta");
        Mockito.when(mockRepository.checkIfFoodTypeExistsByName("Pasta")).thenReturn(false);
        fs.createFoodType(mockFoodType);
        Mockito.verify(mockRepository).createFoodType(mockFoodType);
    }
    @Test
    public void verifyAddFoodTypeNotCalledIfExists(){
        Mockito.when(mockFoodType.getFoodTypeName()).thenReturn("Pasta");
        Mockito.when(mockRepository.checkIfFoodTypeExistsByName("Pasta")).thenReturn(true);
        fs.createFoodType(mockFoodType);
        Mockito.verify(mockRepository, never()).createFoodType(mockFoodType);
    }
    /*
    Mockito allows us to mock objects such that we can isolate testing behavior
    (its an additional dependency)
    ie testing service does not depend on DAO
    do you have to use mockito for project?
    No... if you prefer, you can just use mock data that is saved within the database
    in that case you may want to reset your mock data in a junit BeforeAll method
     */
}
