package generated.meta.example.helloWorld

class TimidPerson (var name: String) extends meta.deep.runtime.Actor with meta.example.helloWorld.Person with Serializable {
  var outgoing: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: scala.Any = null;
  private var listValMut_8: meta.deep.runtime.RequestMessage = null;
  private var iterMut_9: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_10: scala.Int = 0;
  private var positionVar_12: scala.Int = 0;
  
  val commands_119 = (() => {
  val data_13 = new scala.Array[scala.Function0[scala.Unit]](20);
  data_13.update(0, (() => {
    positionVar_12 = 1;
    val x_14 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_15 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_14, 16);
    val x_16 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_15);
    resetData_1.prepend(x_16)
  }));
  data_13.update(1, (() => if (true)
    positionVar_12 = 2
  else
    positionVar_12 = 19));
  data_13.update(2, (() => {
    resetData_0 = 0;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Int];
    bindingMut_10 = x_18;
    positionVar_12 = 3
  }));
  data_13.update(3, (() => {
    val x_19 = bindingMut_10;
    val x_20 = x_19.asInstanceOf[scala.Int];
    val x_21 = (3).-(x_20);
    meta.deep.runtime.Actor.waitTurnList.append(x_21);
    resetData_0 = ();
    val x_22 = meta.deep.runtime.Actor.minTurn();
    val x_23 = bindingMut_10;
    val x_24 = x_23.asInstanceOf[scala.Int];
    val x_25 = x_24.+(x_22);
    resetData_0 = x_25;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Int];
    bindingMut_10 = x_27;
    positionVar_12 = 4;
    val x_28 = currentTurn;
    val x_29 = x_28.+(1);
    currentTurn = x_29
  }));
  data_13.update(4, (() => {
    val x_30 = bindingMut_10;
    val x_31 = x_30.asInstanceOf[scala.Int];
    val x_32 = x_31.<(3);
    if (x_32)
      positionVar_12 = 3
    else
      positionVar_12 = 5
  }));
  data_13.update(5, (() => {
    val x_33 = bindingMut_10;
    val x_34 = x_33.asInstanceOf[scala.Int];
    val x_35 = x_34.<(3);
    val x_36 = x_35.`unary_!`;
    if (x_36)
      {
        val x_37 = this.popRequestMessages;
        val x_38 = x_37.iterator;
        iterMut_9 = x_38;
        positionVar_12 = 6
      }
    else
      ()
  }));
  data_13.update(6, (() => {
    val x_39 = iterMut_9;
    val x_40 = x_39.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_41 = x_40.hasNext;
    if (x_41)
      {
        val x_42 = iterMut_9;
        val x_43 = x_42.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_44 = x_43.next();
        listValMut_8 = x_44;
        positionVar_12 = 7
      }
    else
      positionVar_12 = 12
  }));
  data_13.update(7, (() => {
    val x_45 = listValMut_8;
    val x_46 = x_45.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_47 = x_46.methodId;
    val x_48 = x_47.==(0);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      positionVar_12 = 8
    else
      positionVar_12 = 11
  }));
  data_13.update(8, (() => {
    val x_50 = listValMut_8;
    val x_51 = x_50.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_52 = x_51.methodId;
    val x_53 = x_52.==(1);
    val x_54 = x_53.`unary_!`;
    if (x_54)
      {
        val x_55 = listValMut_8;
        val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_57 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_56);
        val x_58 = this.addReceiveMessages(x_57);
        resetData_0 = x_58;
        positionVar_12 = 9
      }
    else
      positionVar_12 = 10
  }));
  data_13.update(9, (() => positionVar_12 = 6));
  data_13.update(10, (() => {
    val x_59 = listValMut_8;
    val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_61 = x_60.methodId;
    val x_62 = x_61.==(1);
    if (x_62)
      positionVar_12 = 1
    else
      ();
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 15);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_13.update(11, (() => {
    val x_66 = listValMut_8;
    val x_67 = x_66.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_68 = x_67.methodId;
    val x_69 = x_68.==(0);
    if (x_69)
      {
        val x_70 = this.name;
        resetData_0 = x_70;
        val x_71 = resetData_0;
        val x_72 = x_71.asInstanceOf[java.lang.String];
        bindingMut_6 = x_72;
        val x_73 = bindingMut_6;
        val x_74 = x_73.asInstanceOf[java.lang.String];
        val x_75 = "My name is ".+(x_74);
        resetData_0 = x_75;
        val x_76 = resetData_0;
        val x_77 = x_76.asInstanceOf[java.lang.String];
        bindingMut_5 = x_77;
        val x_78 = bindingMut_5;
        val x_79 = x_78.asInstanceOf[java.lang.String];
        val x_80 = x_79.+(" and I am shy");
        resetData_0 = x_80;
        val x_81 = resetData_0;
        val x_82 = x_81.asInstanceOf[java.lang.String];
        bindingMut_4 = x_82;
        val x_83 = bindingMut_4;
        val x_84 = x_83.asInstanceOf[java.lang.String];
        scala.Predef.println(x_84);
        resetData_0 = ();
        val x_85 = resetData_0;
        val x_86 = x_85.asInstanceOf[scala.Any];
        bindingMut_7 = x_86;
        val x_87 = bindingMut_7;
        val x_88 = x_87.asInstanceOf[scala.Any];
        val x_89 = listValMut_8;
        val x_90 = x_89.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_90.reply(this, x_88);
        resetData_0 = ();
        positionVar_12 = 9
      }
    else
      ()
  }));
  data_13.update(12, (() => {
    val x_91 = iterMut_9;
    val x_92 = x_91.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_93 = x_92.hasNext;
    val x_94 = x_93.`unary_!`;
    if (x_94)
      positionVar_12 = 13
    else
      ()
  }));
  data_13.update(13, (() => if (true)
    positionVar_12 = 2
  else
    positionVar_12 = 14));
  data_13.update(14, (() => {
    val x_95 = true.`unary_!`;
    if (x_95)
      {
        val x_96 = resetData_1.remove(0);
        val x_100 = x_96.find(((x_97: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_98 = x_97._1;
          val x_99 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_98.==(x_99)
        }));
        val x_101 = x_100.get;
        val x_102 = x_101._2;
        positionVar_12 = x_102
      }
    else
      ()
  }));
  data_13.update(15, (() => {
    val x_103 = resetData_0;
    val x_104 = x_103.asInstanceOf[scala.Any];
    bindingMut_7 = x_104;
    val x_105 = bindingMut_7;
    val x_106 = x_105.asInstanceOf[scala.Any];
    val x_107 = listValMut_8;
    val x_108 = x_107.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_108.reply(this, x_106);
    resetData_0 = ();
    positionVar_12 = 9
  }));
  data_13.update(16, (() => positionVar_12 = 17));
  data_13.update(17, (() => {
    positionVar_12 = 18;
    val x_109 = currentTurn;
    val x_110 = x_109.+(1);
    currentTurn = x_110
  }));
  data_13.update(18, (() => positionVar_12 = 17));
  data_13.update(19, (() => {
    val x_111 = true.`unary_!`;
    if (x_111)
      {
        val x_112 = resetData_1.remove(0);
        val x_116 = x_112.find(((x_113: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_114 = x_113._1;
          val x_115 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_114.==(x_115)
        }));
        val x_117 = x_116.get;
        val x_118 = x_117._2;
        positionVar_12 = x_118
      }
    else
      ()
  }));
  data_13
}).apply();
  
  override def run_until(until_120: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_121 = currentTurn;
      val x_122 = x_121.<=(until_120);
      x_122.&&({
        val x_123 = positionVar_12;
        val x_124 = commands_119.length;
        x_123.<(x_124)
      })
    }) 
      {
        val x_125 = positionVar_12;
        val x_126 = commands_119.apply(x_125);
        x_126.apply()
      }
    ;
    this
  }
}
