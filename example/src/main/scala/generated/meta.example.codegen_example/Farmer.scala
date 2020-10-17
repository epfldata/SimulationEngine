package generated.meta.example.codegen_example

class Farmer () extends meta.deep.runtime.Actor {
  var market: generated.meta.example.codegen_example.Market = null
  var happiness: Int = 0
  var peers: List[generated.meta.example.codegen_example.Farmer] = scala.collection.immutable.Nil
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var listValMut_5: generated.meta.example.codegen_example.Farmer = null;
  private var iterMut_6: scala.collection.Iterator[generated.meta.example.codegen_example.Farmer] = null;
  private var methodArgsMut_7: scala.Int = 0;
  private var methodArgsMut_8: meta.deep.runtime.Actor = null;
  private var bindingMut_9: scala.Int = 0;
  private var bindingMut_10: scala.Any = null;
  private var listValMut_11: meta.deep.runtime.RequestMessage = null;
  private var iterMut_12: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_14: scala.Int = 0;
  
  val commands_15 = (() => new scala.Array[scala.Function0[scala.Unit]](0)).apply();
  
  override def run_until(until_16: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_17 = currentTurn;
      val x_18 = x_17.<=(until_16);
      x_18.&&({
        val x_19 = positionVar_14;
        val x_20 = commands_15.length;
        x_19.<(x_20)
      })
    }) 
      {
        val x_21 = positionVar_14;
        val x_22 = commands_15.apply(x_21);
        x_22.apply()
      }
    ;
    this
  }
}
