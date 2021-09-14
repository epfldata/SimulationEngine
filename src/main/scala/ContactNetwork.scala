package contact

import Owner.Seller
import Securities.Commodities.Commodity

/**
 * The Goal of this class is to reflect the fact that people have usual suppliers in real life.
 * When a Buyer wants some commodities, it checks the possible seller on the SellerMarket
 * It chose the best seller not only based on price of products, but also on the clientScore
 * (You may want to buy at higher price to your friends, but not to much so you still look at other prices)
 * It basically just contains a list of Seller, and a clientScore + some method to update this list
 */
class ContactNetwork {

  // Just store a list of Seller, together with their clientScore
  var contacts = scala.collection.mutable.Set[(Seller, Int)]()
  // For each commodity, the list of seller that are already known (= has make an exchange with before)
  var comToContacts = scala.collection.mutable.Map[Commodity, scala.collection.mutable.Set[Seller]]()

  def getContactScore(contact: Seller): Int = {
    contacts.find(_._1 == contact) match {
      case Some((seller, score)) => score
      case None => 0
    }
  }

  /** automatically add the contact if does not exists yet */
  def increaseScore(contact: Seller, item: Commodity): Unit = {
    val oldScore = getContactScore(contact)
    contacts -= ((contact,oldScore))
    contacts += ((contact, Math.min(oldScore + 1,10)))
    comToContacts.update(item, comToContacts.getOrElse(item, scala.collection.mutable.Set[Seller]()) + contact)
  }

  /** remove the contact if its score reach -1 */
  def decreaseScore(contact: Seller): Unit = {
    val oldScore = getContactScore(contact)
    contacts -= ((contact,oldScore))
    if(oldScore  > 0) {
      contacts += ((contact, oldScore - 1))
    }
  }

  //Return the seller from whom the owner has already buy a type of commodity from
  def contactsSellingCom(com: Commodity): List[Seller] = {
    comToContacts.getOrElse(com, Set[Seller]()).toList
  }

  def stats: Unit = {
    println("Contacts are: " + contacts.toString())
  }
}