package generated.example.rumor

class Env () extends meta.deep.runtime.Actor {
  var rumorReach: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Int = 0;
  private var bindingMut_4: scala.Int = 0;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.Int = 0;
  private var bindingMut_7: scala.Any = null;
  private var listValMut_8: meta.deep.runtime.RequestMessage = null;
  private var iterMut_9: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: scala.Double = 0.0;
  private var positionVar_13: scala.Int = 0;
  
  val commands_136 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](20);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 16);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 19));
  data_14.update(2, (() => {
    val x_18 = 1.toDouble;
    resetData_0 = x_18;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[scala.Double];
    bindingMut_11 = x_20;
    resetData_0 = 0.0;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Double];
    bindingMut_10 = x_22;
    positionVar_13 = 3
  }));
  data_14.update(3, (() => {
    val x_23 = meta.deep.runtime.Actor.proceedLabel;
    val x_24 = x_23("turn");
    val x_25 = bindingMut_10;
    val x_26 = x_25.asInstanceOf[scala.Double];
    val x_27 = x_26.+(x_24);
    resetData_0 = x_27;
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[scala.Double];
    bindingMut_10 = x_29;
    val x_30 = meta.deep.runtime.Actor.labelVals("turn");
    val x_31 = bindingMut_10;
    val x_32 = x_31.asInstanceOf[scala.Double];
    val x_33 = bindingMut_11;
    val x_34 = x_33.asInstanceOf[scala.Double];
    val x_35 = x_34.-(x_32);
    x_30.append(x_35);
    resetData_0 = ();
    positionVar_13 = 4;
    val x_36 = currentTurn;
    val x_37 = x_36.+(1);
    currentTurn = x_37
  }));
  data_14.update(4, (() => {
    val x_38 = bindingMut_10;
    val x_39 = x_38.asInstanceOf[scala.Double];
    val x_40 = bindingMut_11;
    val x_41 = x_40.asInstanceOf[scala.Double];
    val x_42 = x_39.<(x_41);
    if (x_42)
      positionVar_13 = 3
    else
      positionVar_13 = 5
  }));
  data_14.update(5, (() => {
    val x_43 = bindingMut_10;
    val x_44 = x_43.asInstanceOf[scala.Double];
    val x_45 = bindingMut_11;
    val x_46 = x_45.asInstanceOf[scala.Double];
    val x_47 = x_44.<(x_46);
    val x_48 = x_47.`unary_!`;
    if (x_48)
      {
        val x_49 = this.popRequestMessages;
        val x_50 = x_49.iterator;
        iterMut_9 = x_50;
        positionVar_13 = 6
      }
    else
      ()
  }));
  data_14.update(6, (() => {
    val x_51 = iterMut_9;
    val x_52 = x_51.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_53 = x_52.hasNext;
    if (x_53)
      {
        val x_54 = iterMut_9;
        val x_55 = x_54.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_56 = x_55.next();
        listValMut_8 = x_56;
        positionVar_13 = 7
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(7, (() => {
    val x_57 = listValMut_8;
    val x_58 = x_57.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_59 = x_58.methodId;
    val x_60 = x_59.==(5);
    val x_61 = x_60.`unary_!`;
    if (x_61)
      positionVar_13 = 8
    else
      positionVar_13 = 11
  }));
  data_14.update(8, (() => {
    val x_62 = listValMut_8;
    val x_63 = x_62.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_64 = x_63.methodId;
    val x_65 = x_64.==(6);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      {
        val x_67 = listValMut_8;
        val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_69 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_68);
        val x_70 = this.addReceiveMessages(x_69);
        resetData_0 = x_70;
        positionVar_13 = 9
      }
    else
      positionVar_13 = 10
  }));
  data_14.update(9, (() => positionVar_13 = 6));
  data_14.update(10, (() => {
    val x_71 = listValMut_8;
    val x_72 = x_71.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_73 = x_72.methodId;
    val x_74 = x_73.==(6);
    if (x_74)
      positionVar_13 = 1
    else
      ();
    val x_75 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_76 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_75, 15);
    val x_77 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_76);
    resetData_1.prepend(x_77)
  }));
  data_14.update(11, (() => {
    val x_78 = listValMut_8;
    val x_79 = x_78.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_80 = x_79.methodId;
    val x_81 = x_80.==(5);
    if (x_81)
      {
        val x_82 = this.rumorReach;
        resetData_0 = x_82;
        val x_83 = resetData_0;
        val x_84 = x_83.asInstanceOf[scala.Int];
        bindingMut_4 = x_84;
        val x_85 = bindingMut_4;
        val x_86 = x_85.asInstanceOf[scala.Int];
        val x_87 = x_86.+(1);
        resetData_0 = x_87;
        val x_88 = resetData_0;
        val x_89 = x_88.asInstanceOf[scala.Int];
        bindingMut_3 = x_89;
        val x_90 = bindingMut_3;
        val x_91 = x_90.asInstanceOf[scala.Int];
        this.`rumorReach_=`(x_91);
        resetData_0 = ();
        val x_92 = resetData_0;
        val x_93 = x_92.asInstanceOf[scala.Any];
        bindingMut_7 = x_93;
        val x_94 = bindingMut_7;
        val x_95 = x_94.asInstanceOf[scala.Any];
        val x_96 = listValMut_8;
        val x_97 = x_96.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_97.reply(this, x_95);
        resetData_0 = ();
        positionVar_13 = 9
      }
    else
      ()
  }));
  data_14.update(12, (() => {
    val x_98 = iterMut_9;
    val x_99 = x_98.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_100 = x_99.hasNext;
    val x_101 = x_100.`unary_!`;
    if (x_101)
      {
        val x_102 = this.rumorReach;
        resetData_0 = x_102;
        val x_103 = resetData_0;
        val x_104 = x_103.asInstanceOf[scala.Int];
        bindingMut_6 = x_104;
        val x_105 = bindingMut_6;
        val x_106 = x_105.asInstanceOf[scala.Int];
        val x_107 = "Current rumor count: ".+(x_106);
        resetData_0 = x_107;
        val x_108 = resetData_0;
        val x_109 = x_108.asInstanceOf[java.lang.String];
        bindingMut_5 = x_109;
        val x_110 = bindingMut_5;
        val x_111 = x_110.asInstanceOf[java.lang.String];
        scala.Predef.println(x_111);
        resetData_0 = ();
        positionVar_13 = 13
      }
    else
      ()
  }));
  data_14.update(13, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 14));
  data_14.update(14, (() => {
    val x_112 = true.`unary_!`;
    if (x_112)
      {
        val x_113 = resetData_1.remove(0);
        val x_117 = x_113.find(((x_114: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_115 = x_114._1;
          val x_116 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_115.==(x_116)
        }));
        val x_118 = x_117.get;
        val x_119 = x_118._2;
        positionVar_13 = x_119
      }
    else
      ()
  }));
  data_14.update(15, (() => {
    val x_120 = resetData_0;
    val x_121 = x_120.asInstanceOf[scala.Any];
    bindingMut_7 = x_121;
    val x_122 = bindingMut_7;
    val x_123 = x_122.asInstanceOf[scala.Any];
    val x_124 = listValMut_8;
    val x_125 = x_124.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_125.reply(this, x_123);
    resetData_0 = ();
    positionVar_13 = 9
  }));
  data_14.update(16, (() => positionVar_13 = 17));
  data_14.update(17, (() => {
    positionVar_13 = 18;
    val x_126 = currentTurn;
    val x_127 = x_126.+(1);
    currentTurn = x_127
  }));
  data_14.update(18, (() => positionVar_13 = 17));
  data_14.update(19, (() => {
    val x_128 = true.`unary_!`;
    if (x_128)
      {
        val x_129 = resetData_1.remove(0);
        val x_133 = x_129.find(((x_130: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_131 = x_130._1;
          val x_132 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_131.==(x_132)
        }));
        val x_134 = x_133.get;
        val x_135 = x_134._2;
        positionVar_13 = x_135
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_137: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_138 = currentTurn;
      val x_139 = x_138.<=(until_137);
      x_139.&&({
        val x_140 = positionVar_13;
        val x_141 = commands_136.length;
        x_140.<(x_141)
      })
    }) 
      {
        val x_142 = positionVar_13;
        val x_143 = commands_136.apply(x_142);
        x_143.apply()
      }
    ;
    this
  }
}
