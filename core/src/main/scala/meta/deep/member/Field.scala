package meta.deep.member

/**
  * Class representation of a field for code generation
  * @param name: the symbol of the field in string 
  * @param init: the initial value of the field 
  * @param mutable: a boolean indicating whether the field is mutable 
  * @param parameter: a boolean indicating whether the field is in the parameter 
  * @param isCopiedToSubclass: a boolean
  */
case class Field (
    name: String,
    tpeRep: String, 
    init: String, 
    mutable: Boolean, 
    parameter: Boolean) extends FieldOrMethod {
      // add "override" to the modifier in replicated fields
      def replica(): Field = {
        val ans = Field(name, tpeRep, init, mutable, parameter)
        "override" +=: ans.modifiers
        ans
      }
    } 