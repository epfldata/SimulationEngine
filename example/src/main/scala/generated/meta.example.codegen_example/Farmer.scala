package generated.meta.example.codegen_example

class Farmer () extends meta.deep.runtime.Actor with Serializable {
  var happiness: Int = ((1): scala.Int)
  var peers: List[generated.meta.example.codegen_example.Farmer] = scala.collection.immutable.Nil
  var market: generated.meta.example.codegen_example.Market = ((null): generated.meta.example.codegen_example.Market)
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_6: scala.Int = 0;
  private var listValMut_7: generated.meta.example.codegen_example.Farmer = null;
  private var iterMut_8: scala.collection.Iterator[generated.meta.example.codegen_example.Farmer] = null;
  private var bindingMut_9: scala.collection.immutable.List[generated.meta.example.codegen_example.Farmer] = null;
  private var methodArgsMut_10: scala.Int = 0;
  private var methodArgsMut_11: meta.deep.runtime.Actor = null;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: scala.Int = 0;
  private var bindingMut_14: scala.Int = 0;
  private var bindingMut_15: scala.Tuple2[java.lang.String, scala.Int] = null;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: generated.meta.example.codegen_example.Market = null;
  private var positionVar_19: scala.Int = 0;
  
  val commands_75 = (() => {
  val data_20 = new scala.Array[scala.Function0[scala.Unit]](15);
  data_20.update(0, (() => positionVar_19 = 1));
  data_20.update(1, (() => if (true)
    positionVar_19 = 2
  else
    positionVar_19 = 14));
  data_20.update(2, (() => {
    val x_21 = this.market;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[generated.meta.example.codegen_example.Market];
    bindingMut_17 = x_23;
    val x_24 = ((this): meta.deep.runtime.Actor).id;
    val x_26 = {
      val x_25 = bindingMut_17;
      x_25.asInstanceOf[generated.meta.example.codegen_example.Market]
    };
    val x_27 = x_26.id;
    val x$5_28 = scala.collection.immutable.Nil.::[scala.Any](500);
    val x_29 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x$5_28);
    val x_30 = meta.deep.runtime.RequestMessage.apply(x_24, x_27, 1, x_29);
    ((this): meta.deep.runtime.Actor).sendMessage(x_30);
    val x_31 = x_30.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_31, ((response_32: meta.deep.runtime.Message) => {
      val x_33 = response_32.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_33
    }));
    resetData_0 = null;
    positionVar_19 = 3
  }));
  data_20.update(3, (() => {
    positionVar_19 = 4;
    val x_34 = currentTurn;
    val x_35 = x_34.+(1);
    currentTurn = x_35
  }));
  data_20.update(4, (() => {
    val x_36 = resetData_2;
    val x_37 = x_36.==(null);
    if (x_37)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_19 = 3
      }
    else
      positionVar_19 = 5
  }));
  data_20.update(5, (() => {
    val x_38 = resetData_2;
    val x_39 = x_38.!=(null);
    if (x_39)
      {
        val x_40 = resetData_2;
        val x_41 = x_40.arg;
        resetData_0 = x_41;
        resetData_2 = null;
        val x_42 = resetData_0;
        val x_43 = x_42.asInstanceOf[scala.Int];
        bindingMut_16 = x_43;
        val x_44 = bindingMut_16;
        val x_45 = x_44.asInstanceOf[scala.Int];
        val x_46 = scala.Tuple2.apply[java.lang.String, scala.Int]("TEST_VAR", x_45);
        resetData_0 = x_46;
        val x_47 = resetData_0;
        val x_48 = x_47.asInstanceOf[scala.Tuple2[java.lang.String, scala.Int]];
        bindingMut_15 = x_48;
        val x_49 = bindingMut_15;
        val x_50 = x_49.asInstanceOf[scala.Tuple2[java.lang.String, scala.Int]];
        scala.Predef.println(x_50);
        resetData_0 = ();
        resetData_0 = 0;
        val x_51 = resetData_0;
        val x_52 = x_51.asInstanceOf[scala.Int];
        bindingMut_14 = x_52;
        positionVar_19 = 6
      }
    else
      ()
  }));
  data_20.update(6, (() => {
    val x_53 = bindingMut_14;
    val x_54 = x_53.asInstanceOf[scala.Int];
    val x_55 = (1).-(x_54);
    meta.deep.runtime.Actor.waitTurnList.append(x_55);
    resetData_0 = ();
    val x_56 = meta.deep.runtime.Actor.minTurn();
    val x_57 = bindingMut_14;
    val x_58 = x_57.asInstanceOf[scala.Int];
    val x_59 = x_58.+(x_56);
    resetData_0 = x_59;
    val x_60 = resetData_0;
    val x_61 = x_60.asInstanceOf[scala.Int];
    bindingMut_14 = x_61;
    positionVar_19 = 7;
    val x_62 = currentTurn;
    val x_63 = x_62.+(1);
    currentTurn = x_63
  }));
  data_20.update(7, (() => {
    val x_64 = bindingMut_14;
    val x_65 = x_64.asInstanceOf[scala.Int];
    val x_66 = x_65.<(1);
    if (x_66)
      positionVar_19 = 6
    else
      positionVar_19 = 8
  }));
  data_20.update(8, (() => {
    val x_67 = bindingMut_14;
    val x_68 = x_67.asInstanceOf[scala.Int];
    val x_69 = x_68.<(1);
    val x_70 = x_69.`unary_!`;
    if (x_70)
      positionVar_19 = 9
    else
      ()
  }));
  data_20.update(9, (() => if (true)
    positionVar_19 = 2
  else
    positionVar_19 = 10));
  data_20.update(10, (() => {
    val x_71 = true.`unary_!`;
    if (x_71)
      positionVar_19 = 11
    else
      ()
  }));
  data_20.update(11, (() => positionVar_19 = 12));
  data_20.update(12, (() => {
    positionVar_19 = 13;
    val x_72 = currentTurn;
    val x_73 = x_72.+(1);
    currentTurn = x_73
  }));
  data_20.update(13, (() => positionVar_19 = 12));
  data_20.update(14, (() => {
    val x_74 = true.`unary_!`;
    if (x_74)
      positionVar_19 = 11
    else
      ()
  }));
  data_20
}).apply();
  
  override def run_until(until_76: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_77 = currentTurn;
      val x_78 = x_77.<=(until_76);
      x_78.&&({
        val x_79 = positionVar_19;
        val x_80 = commands_75.length;
        x_79.<(x_80)
      })
    }) 
      {
        val x_81 = positionVar_19;
        val x_82 = commands_75.apply(x_81);
        x_82.apply()
      }
    ;
    this
  }
}
