The goal of this example is to understand causes for food wastage, both at supermarket and at home,
and to explore ways to reduce it, such as the effectiveness of individual item pricing. To this end, we focus on a supermarket
in which the interactions among customers and items take place

On the one hand, we come up with attributes that distinguish each customer, such as shopping frequency, price consciousness, food preference, 
diverse meal plans, and etc. Each customer is modeled as a combination of these features. On the other hand, each food item in 
the supermarket is associated with an age, expiration date (freshUntil), its current state (onDisplay, isPurchased, isConsumed, isExpired, etc), 
stock amount, visibility, and etc. When an item is purchased from the supermarket, its state transits from onDisplay to isPurchased. 
We also model wastage at home, e.g. a cargo of egg is half consumed before it gets expired 

Please use **/generated/main/scala/supermarketSimulation** for this simulation, which includes garbage collection to remove invalid actors and 
run-time generation of new actors tailored for this example. 

`Folders` and **files** in this directory
- `customers` assembles generated files from **genCustomers**
  - Each customer is a subtype of People (in **people/PeopleTrait**) and its abstract fields are defined in the rest of its parent classes 
  e.g. Consider ```class Customer1 extends People with Weekly with MealPlan1 with ImpulseShopper```
  The abstract field *frequency* defined in People gets implemented in *Weekly*. Other abstract fields are similarly implemented
  - Customer differs in their shopping frequency, shopping habbit, and meal plans, which are specified in 
  **people/shoppingFrequency**, **people/shopperType**, **people/mealPlan** respectively. If you would like 
  to define your own Customer which is not included, you can do so by adjusting aforementioned files. 
  Please note that **mealPlan** is generated from specs in **Carnivore** and **Vegetarian** using **genMealPlans**. 
  If you accidentally run the generating file without reflecting your changes in **Carnivore** and **Vegetarian**, 
  any modification to **mealPlan** will be lost 
  
- `goods` holds **ItemTrait** and **ItemState** along with generated files from **genGoods** 
  - Each goods implements the abstract fields defined in Item (in **ItemTrait**)
  - If you wish to add more goods, please update **categories** and rerun **genGoods**. 
  Changes to any generated file in this directory will not change **categories.summary** which will result in incorrect simulation behavior 

- `items` is the output folder of generating file **genItems** 
  - **ItemId** Each item is a subtype of Item and its abstract fields are defined in the other parent class, similarly to Customer
  - **newItemsMap**  
    - Trait *newItem* enables the generation of new actors at run-time of the simulation by setting timeVar to current timer in lieu of zero
    - *itemMap* is a mapping between goods' name and itemId
    
- `people` is made up of **PeopleTrait** and files describing customer behavior

- `testItemOnly` is a sample output of **genExample** 
  - Input: item instance is 1 and customer instance is 0  
  - Intended to test state transitions of items as well as wastage summary update 
  
- `testCustomers` is another example of **genExample** 
  - *custIds: List[Int] = List(1, 77)* in **userSpecificGenExample**
  - Input: item instance is 5 and customer instance is 1  
  - Intended to test customer purchasing and consuming behavior  
    
- `utils` collects data structures specified for the simulation and helper functions

- **supermarket** implements a single instance of Supermarket object in the simulation 
