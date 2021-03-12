package generated.meta.test.inheritance

class child1 () extends meta.runtime.Actor {

  val var2: String = "h";
  val var1: Int = 10;
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private var bindingMut_3: java.lang.String = null;
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.Int = 0;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Any = null;
  private var listValMut_9: meta.runtime.RequestMessage = null;
  @transient private var iterMut_10: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_12: scala.Int = 0;
  
  val commands_121 = (() => {
  val data_13 = new scala.Array[scala.Function0[scala.Unit]](21);
  data_13.update(0, (() => if (true)
    positionVar_12 = 1
  else
    positionVar_12 = 20));
  data_13.update(1, (() => {
    scala.Predef.println("This is child1! ");
    resetData_0 = ();
    positionVar_12 = 2;
    val x_14 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_15 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_14, 19);
    val x_16 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_15);
    resetData_1.prepend(x_16)
  }));
  data_13.update(2, (() => {
    val x_17 = this.var2;
    resetData_0 = x_17;
    val x_18 = resetData_0;
    val x_19 = x_18.asInstanceOf[java.lang.String];
    bindingMut_4 = x_19;
    val x_20 = bindingMut_4;
    val x_21 = x_20.asInstanceOf[java.lang.String];
    val x_22 = "another pMtd ".+(x_21);
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[java.lang.String];
    bindingMut_3 = x_24;
    val x_25 = bindingMut_3;
    val x_26 = x_25.asInstanceOf[java.lang.String];
    scala.Predef.println(x_26);
    resetData_0 = ();
    val x_27 = resetData_1.remove(0);
    val x_31 = x_27.find(((x_28: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_29 = x_28._1;
      val x_30 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_29.==(x_30)
    }));
    val x_32 = x_31.get;
    val x_33 = x_32._2;
    positionVar_12 = x_33
  }));
  data_13.update(3, (() => {
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Any];
    bindingMut_8 = x_35;
    val x_36 = bindingMut_8;
    val x_37 = x_36.asInstanceOf[scala.Any];
    val x_38 = listValMut_9;
    val x_39 = x_38.asInstanceOf[meta.runtime.RequestMessage];
    x_39.reply(this, x_37);
    resetData_0 = ();
    positionVar_12 = 4
  }));
  data_13.update(4, (() => positionVar_12 = 5));
  data_13.update(5, (() => {
    val x_40 = iterMut_10;
    val x_41 = x_40.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_42 = x_41.hasNext;
    if (x_42)
      {
        val x_43 = iterMut_10;
        val x_44 = x_43.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_45 = x_44.next();
        listValMut_9 = x_45;
        positionVar_12 = 6
      }
    else
      positionVar_12 = 10
  }));
  data_13.update(6, (() => {
    val x_46 = listValMut_9;
    val x_47 = x_46.asInstanceOf[meta.runtime.RequestMessage];
    val x_48 = x_47.methodId;
    val x_49 = x_48.==(5);
    val x_50 = x_49.`unary_!`;
    if (x_50)
      positionVar_12 = 7
    else
      positionVar_12 = 9
  }));
  data_13.update(7, (() => {
    val x_51 = listValMut_9;
    val x_52 = x_51.asInstanceOf[meta.runtime.RequestMessage];
    val x_53 = x_52.methodId;
    val x_54 = x_53.==(4);
    val x_55 = x_54.`unary_!`;
    if (x_55)
      {
        val x_56 = listValMut_9;
        val x_57 = x_56.asInstanceOf[meta.runtime.RequestMessage];
        val x_58 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_57);
        val x_59 = this.addReceiveMessages(x_58);
        resetData_0 = x_59;
        positionVar_12 = 4
      }
    else
      positionVar_12 = 8
  }));
  data_13.update(8, (() => {
    val x_60 = listValMut_9;
    val x_61 = x_60.asInstanceOf[meta.runtime.RequestMessage];
    val x_62 = x_61.methodId;
    val x_63 = x_62.==(4);
    if (x_63)
      positionVar_12 = 2
    else
      ();
    val x_64 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_65 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_64, 3);
    val x_66 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_65);
    resetData_1.prepend(x_66)
  }));
  data_13.update(9, (() => {
    val x_67 = listValMut_9;
    val x_68 = x_67.asInstanceOf[meta.runtime.RequestMessage];
    val x_69 = x_68.methodId;
    val x_70 = x_69.==(5);
    if (x_70)
      {
        val x_71 = this.var1;
        resetData_0 = x_71;
        val x_72 = resetData_0;
        val x_73 = x_72.asInstanceOf[scala.Int];
        bindingMut_6 = x_73;
        val x_74 = bindingMut_6;
        val x_75 = x_74.asInstanceOf[scala.Int];
        val x_76 = "Pmtd 1: ".+(x_75);
        resetData_0 = x_76;
        val x_77 = resetData_0;
        val x_78 = x_77.asInstanceOf[java.lang.String];
        bindingMut_5 = x_78;
        val x_79 = bindingMut_5;
        val x_80 = x_79.asInstanceOf[java.lang.String];
        scala.Predef.println(x_80);
        resetData_0 = ();
        val x_81 = resetData_0;
        val x_82 = x_81.asInstanceOf[scala.Any];
        bindingMut_8 = x_82;
        val x_83 = bindingMut_8;
        val x_84 = x_83.asInstanceOf[scala.Any];
        val x_85 = listValMut_9;
        val x_86 = x_85.asInstanceOf[meta.runtime.RequestMessage];
        x_86.reply(this, x_84);
        resetData_0 = ();
        positionVar_12 = 4
      }
    else
      ()
  }));
  data_13.update(10, (() => {
    val x_87 = iterMut_10;
    val x_88 = x_87.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_89 = x_88.hasNext;
    val x_90 = x_89.`unary_!`;
    if (x_90)
      {
        resetData_0 = 0.0;
        val x_91 = resetData_0;
        val x_92 = x_91.asInstanceOf[scala.Double];
        bindingMut_7 = x_92;
        positionVar_12 = 11
      }
    else
      ()
  }));
  data_13.update(11, (() => {
    val x_93 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_94 = meta.runtime.SimRuntime.labelVals(x_93);
    val x_95 = bindingMut_7;
    val x_96 = x_95.asInstanceOf[scala.Double];
    val x_97 = 1.0.-(x_96);
    x_94.append(x_97);
    resetData_0 = ();
    positionVar_12 = 12;
    val x_98 = currentTurn;
    val x_99 = x_98.+(1);
    currentTurn = x_99
  }));
  data_13.update(12, (() => {
    val x_100 = meta.runtime.SimRuntime.proceedLabel;
    val x_101 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_102 = x_100(x_101);
    val x_103 = bindingMut_7;
    val x_104 = x_103.asInstanceOf[scala.Double];
    val x_105 = x_104.+(x_102);
    resetData_0 = x_105;
    val x_106 = resetData_0;
    val x_107 = x_106.asInstanceOf[scala.Double];
    bindingMut_7 = x_107;
    positionVar_12 = 13
  }));
  data_13.update(13, (() => {
    val x_108 = bindingMut_7;
    val x_109 = x_108.asInstanceOf[scala.Double];
    val x_110 = x_109.<(1.0);
    if (x_110)
      positionVar_12 = 11
    else
      positionVar_12 = 14
  }));
  data_13.update(14, (() => {
    val x_111 = bindingMut_7;
    val x_112 = x_111.asInstanceOf[scala.Double];
    val x_113 = x_112.<(1.0);
    val x_114 = x_113.`unary_!`;
    if (x_114)
      positionVar_12 = 15
    else
      ()
  }));
  data_13.update(15, (() => if (true)
    positionVar_12 = 1
  else
    positionVar_12 = 16));
  data_13.update(16, (() => {
    val x_115 = true.`unary_!`;
    if (x_115)
      positionVar_12 = 17
    else
      ()
  }));
  data_13.update(17, (() => {
    positionVar_12 = 18;
    val x_116 = currentTurn;
    val x_117 = x_116.+(1);
    currentTurn = x_117
  }));
  data_13.update(18, (() => positionVar_12 = 17));
  data_13.update(19, (() => {
    scala.Predef.println("I can access my parents\' methods as if my own!");
    resetData_0 = ();
    val x_118 = this.popRequestMessages;
    val x_119 = x_118.iterator;
    iterMut_10 = x_119;
    positionVar_12 = 5
  }));
  data_13.update(20, (() => {
    val x_120 = true.`unary_!`;
    if (x_120)
      positionVar_12 = 17
    else
      ()
  }));
  data_13
}).apply();
  
  override def run_until(until_122: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_123 = currentTurn;
      val x_124 = x_123.<=(until_122);
      x_124.&&({
        val x_125 = positionVar_12;
        val x_126 = commands_121.length;
        x_125.<(x_126)
      })
    }) 
      {
        val x_127 = positionVar_12;
        val x_128 = commands_121.apply(x_127);
        x_128.apply()
      }
    ;
    this
  }
}
