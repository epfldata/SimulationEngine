Items and newItemsMap are auto-generated, one for each article defined in categories, assuming age=0. 
If more items are needed with different age or action, one can either create from existing ones or modify `genItems` accordingly with proper itemIds. 
Please also update the item count, totalItems. Map object does NOT need to be updated if the article of the item is already defined in the map. 
(e.g. One can create a new Broccoli called Item26 with age=3. Please change `totalItems` to 26 but leave the map as is.)
If more articles or categories are needed, please change categories.scala and generate new goods first and rerun genItems. 
In order to generate new actors during the simulation, we need to assume certain initial state of each new item, and these pre-generated items serve this purpose. 
Please *do not* remove any of these Items to ensure the correctness of the simulation. 
