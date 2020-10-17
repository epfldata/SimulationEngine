package generated.meta.example.codegen_example

class ControlFlowTestObject () extends meta.deep.runtime.Actor {
  var x: Int = 0
  var y: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var positionVar_4: scala.Int = 0;
  
  val commands_25 = (() => {
  val data_5 = new scala.Array[scala.Function0[scala.Unit]](11);
  data_5.update(0, (() => {
    val x_6 = this.x;
    val x_7 = x_6.<(0);
    val x_8 = x_7.`unary_!`;
    if (x_8)
      positionVar_4 = 1
    else
      positionVar_4 = 9
  }));
  data_5.update(1, (() => {
    val x_9 = this.y;
    val x_10 = x_9.<(0);
    val x_11 = x_10.`unary_!`;
    if (x_11)
      positionVar_4 = 2
    else
      positionVar_4 = 7
  }));
  data_5.update(2, (() => {
    positionVar_4 = 3;
    val x_12 = currentTurn;
    val x_13 = x_12.+(1);
    currentTurn = x_13
  }));
  data_5.update(3, (() => if (true)
    positionVar_4 = 0
  else
    positionVar_4 = 4));
  data_5.update(4, (() => {
    val x_14 = true.`unary_!`;
    if (x_14)
      positionVar_4 = 5
    else
      ()
  }));
  data_5.update(5, (() => {
    positionVar_4 = 6;
    val x_15 = currentTurn;
    val x_16 = x_15.+(1);
    currentTurn = x_16
  }));
  data_5.update(6, (() => positionVar_4 = 5));
  data_5.update(7, (() => {
    val x_17 = this.y;
    val x_18 = x_17.<(0);
    if (x_18)
      positionVar_4 = 8
    else
      ();
    val x_19 = currentTurn;
    val x_20 = x_19.+(1);
    currentTurn = x_20
  }));
  data_5.update(8, (() => positionVar_4 = 2));
  data_5.update(9, (() => {
    val x_21 = this.x;
    val x_22 = x_21.<(0);
    if (x_22)
      positionVar_4 = 10
    else
      ();
    val x_23 = currentTurn;
    val x_24 = x_23.+(1);
    currentTurn = x_24
  }));
  data_5.update(10, (() => positionVar_4 = 2));
  data_5
}).apply();
  
  override def run_until(until_26: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_27 = currentTurn;
      val x_28 = x_27.<=(until_26);
      x_28.&&({
        val x_29 = positionVar_4;
        val x_30 = commands_25.length;
        x_29.<(x_30)
      })
    }) 
      {
        val x_31 = positionVar_4;
        val x_32 = commands_25.apply(x_31);
        x_32.apply()
      }
    ;
    this
  }
}
