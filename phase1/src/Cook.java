import java.util.ArrayList;

/*
 * A cook decides whether the dish can be prepared and prepares the dish.
 */
public class Cook implements IWorker, ServingTableListener {
    private boolean isOccupied;
    private String name; // Name of the cook.

  public Cook(String name, ServingTable screen) {
        this.name = name;
        this.isOccupied = false;
    }

    /**
     * Adds the dishes from the order to dishesInMaking which are being prepared.
     * @param dish The Dish that is to be added to dishesInMaking.
     */
    public boolean prepareDish(Dish dish, Inventory inventory, ServingTable screen) {
        if(canBePrepared(dish,inventory)){
            // REMOVE THE INGREDIENTS FROM THE INVENTORY AND ADD DISH TO READY.
            for(String ingredient: dish.getIngredients().keySet()){
                inventory.removeStock(ingredient,dish.getIngredientAmounts().get(ingredient));
            }
          screen.addToBeServed(dish);
            return true;
        }
        else{
            //  DOES COOK DECIDE IF A DISH CAN BE MADE OR COMPUTER IS A QUESTION.
            return false;
        }
    }

    /**
     * Adds the dish to dishesReady after being prepared.
     * @param dish The Dish that is to be added.
     */
    public void dishReady(Dish dish, ServingTable screen) {
        // The dish  prepared is added to the Servers list of dishes ready to be served.
      screen.addToBeServed(dish);
    }

    /**
     * The server receives and adds ingredients to the inventory.
     */
    public void scanStock(Inventory inventory, String ingredient, int amount) {
        inventory.addStock(ingredient,amount);
    }

    /**
     * Getter for isOccupied to see if this cook is vacant or busy.
     */
    public boolean isOccupied(){
        return isOccupied;
    }

    /**
     * Returns a boolean whether a dish can be prepared or no.
     * @param dish the dish.
     * @return boolean whether a dish can be prepared or no.
     */
    private boolean canBePrepared(Dish dish, Inventory inventory) {
        return inventory.hasEnoughIngredients(dish.getIngredientAmounts());
    }

  /**
   * Notify the cook that a dish needs to be prepared
   */
  public void update(Dish dish) {
    String text = "Table ";
    text += dish.getTableName() + "dish: " + dish.getName() + " ready to be served.";
    System.out.println(text);
  }
}
