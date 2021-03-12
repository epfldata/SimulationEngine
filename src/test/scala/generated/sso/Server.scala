package generated.meta.test.SSO

class Server (val randServer: generated.meta.test.SSO.RandomServer) extends meta.runtime.Actor {

  val var1: Int = 10;
  val l: scala.collection.mutable.ListBuffer[Int] = new scala.collection.mutable.ListBuffer[scala.Int]();
  var var2: Double = 20.0;
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: scala.Int = 0;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: scala.collection.mutable.ListBuffer[scala.Int] = null;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: scala.Double = 0.0;
  private var methodArgsMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Any = null;
  private var listValMut_14: meta.runtime.RequestMessage = null;
  @transient private var iterMut_15: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_17: scala.Int = 0;
  
  val commands_149 = (() => {
  val data_18 = new scala.Array[scala.Function0[scala.Unit]](18);
  data_18.update(0, (() => if (true)
    positionVar_17 = 1
  else
    positionVar_17 = 17));
  data_18.update(1, (() => {
    val x_19 = this.popRequestMessages;
    val x_20 = x_19.iterator;
    iterMut_15 = x_20;
    positionVar_17 = 2
  }));
  data_18.update(2, (() => {
    val x_21 = iterMut_15;
    val x_22 = x_21.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_23 = x_22.hasNext;
    if (x_23)
      {
        val x_24 = iterMut_15;
        val x_25 = x_24.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_26 = x_25.next();
        listValMut_14 = x_26;
        positionVar_17 = 3
      }
    else
      positionVar_17 = 8
  }));
  data_18.update(3, (() => {
    val x_27 = listValMut_14;
    val x_28 = x_27.asInstanceOf[meta.runtime.RequestMessage];
    val x_29 = x_28.methodId;
    val x_30 = x_29.==(1);
    val x_31 = x_30.`unary_!`;
    if (x_31)
      positionVar_17 = 4
    else
      positionVar_17 = 7
  }));
  data_18.update(4, (() => {
    val x_32 = listValMut_14;
    val x_33 = x_32.asInstanceOf[meta.runtime.RequestMessage];
    val x_34 = x_33.methodId;
    val x_35 = x_34.==(0);
    val x_36 = x_35.`unary_!`;
    if (x_36)
      {
        val x_37 = listValMut_14;
        val x_38 = x_37.asInstanceOf[meta.runtime.RequestMessage];
        val x_39 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_38);
        val x_40 = this.addReceiveMessages(x_39);
        resetData_0 = x_40;
        positionVar_17 = 5
      }
    else
      positionVar_17 = 6
  }));
  data_18.update(5, (() => positionVar_17 = 2));
  data_18.update(6, (() => {
    val x_41 = listValMut_14;
    val x_42 = x_41.asInstanceOf[meta.runtime.RequestMessage];
    val x_43 = x_42.methodId;
    val x_44 = x_43.==(0);
    if (x_44)
      {
        scala.Predef.println("Stateful mtd called!");
        resetData_0 = ();
        val x_45 = this.var2;
        resetData_0 = x_45;
        val x_46 = resetData_0;
        val x_47 = x_46.asInstanceOf[scala.Double];
        bindingMut_9 = x_47;
        val x_48 = bindingMut_9;
        val x_49 = x_48.asInstanceOf[scala.Double];
        val x_50 = "Mutable variable ".+(x_49);
        resetData_0 = x_50;
        val x_51 = resetData_0;
        val x_52 = x_51.asInstanceOf[java.lang.String];
        bindingMut_8 = x_52;
        val x_53 = bindingMut_8;
        val x_54 = x_53.asInstanceOf[java.lang.String];
        scala.Predef.println(x_54);
        resetData_0 = ();
        val x_55 = this.l;
        resetData_0 = x_55;
        val x_56 = resetData_0;
        val x_57 = x_56.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Int]];
        bindingMut_7 = x_57;
        val x_58 = bindingMut_7;
        val x_59 = x_58.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Int]];
        val x_60 = "Mutable list buffer with immutable decl ".+(x_59);
        resetData_0 = x_60;
        val x_61 = resetData_0;
        val x_62 = x_61.asInstanceOf[java.lang.String];
        bindingMut_6 = x_62;
        val x_63 = bindingMut_6;
        val x_64 = x_63.asInstanceOf[java.lang.String];
        scala.Predef.println(x_64);
        resetData_0 = ();
        val x_65 = this.var1;
        resetData_0 = x_65;
        val x_66 = resetData_0;
        val x_67 = x_66.asInstanceOf[scala.Int];
        bindingMut_5 = x_67;
        val x_68 = bindingMut_5;
        val x_69 = x_68.asInstanceOf[scala.Int];
        val x_70 = "Immutable variable ".+(x_69);
        resetData_0 = x_70;
        val x_71 = resetData_0;
        val x_72 = x_71.asInstanceOf[java.lang.String];
        bindingMut_4 = x_72;
        val x_73 = bindingMut_4;
        val x_74 = x_73.asInstanceOf[java.lang.String];
        scala.Predef.println(x_74);
        resetData_0 = ();
        val x_75 = this.var1;
        resetData_0 = x_75;
        val x_76 = resetData_0;
        val x_77 = x_76.asInstanceOf[scala.Any];
        bindingMut_13 = x_77;
        val x_78 = bindingMut_13;
        val x_79 = x_78.asInstanceOf[scala.Any];
        val x_80 = listValMut_14;
        val x_81 = x_80.asInstanceOf[meta.runtime.RequestMessage];
        x_81.reply(this, x_79);
        resetData_0 = ();
        positionVar_17 = 5
      }
    else
      ()
  }));
  data_18.update(7, (() => {
    val x_82 = listValMut_14;
    val x_83 = x_82.asInstanceOf[meta.runtime.RequestMessage];
    val x_84 = x_83.methodId;
    val x_85 = x_84.==(1);
    if (x_85)
      {
        val x_86 = listValMut_14;
        val x_87 = x_86.asInstanceOf[meta.runtime.RequestMessage];
        val x_88 = x_87.argss;
        val x_89 = x_88(0);
        val x_90 = x_89(0);
        x_3.prepend(x_90);
        val x_91 = listValMut_14;
        val x_92 = x_91.asInstanceOf[meta.runtime.RequestMessage];
        val x_93 = x_92.argss;
        val x_94 = x_93(0);
        val x_95 = x_94(0);
        val x_96 = x_95.asInstanceOf[scala.Int];
        methodArgsMut_10 = x_96;
        val x_97 = methodArgsMut_10;
        val x_98 = x_97.asInstanceOf[scala.Int];
        val x_99 = "Stateless mtd invoked! Args: ".+(x_98);
        resetData_0 = x_99;
        val x_100 = resetData_0;
        val x_101 = x_100.asInstanceOf[java.lang.String];
        bindingMut_11 = x_101;
        val x_102 = bindingMut_11;
        val x_103 = x_102.asInstanceOf[java.lang.String];
        scala.Predef.println(x_103);
        resetData_0 = ();
        val x_104 = methodArgsMut_10;
        val x_105 = x_104.asInstanceOf[scala.Int];
        val x_106 = (42).+(x_105);
        resetData_0 = x_106;
        x_3.remove(0);
        val x_107 = x_3.isEmpty;
        val x_108 = x_107.`unary_!`;
        if (x_108)
          {
            val x_109 = x_3(0);
            val x_110 = x_109.asInstanceOf[scala.Int];
            methodArgsMut_10 = x_110
          }
        else
          ();
        val x_111 = resetData_0;
        val x_112 = x_111.asInstanceOf[scala.Any];
        bindingMut_13 = x_112;
        val x_113 = bindingMut_13;
        val x_114 = x_113.asInstanceOf[scala.Any];
        val x_115 = listValMut_14;
        val x_116 = x_115.asInstanceOf[meta.runtime.RequestMessage];
        x_116.reply(this, x_114);
        resetData_0 = ();
        positionVar_17 = 5
      }
    else
      ()
  }));
  data_18.update(8, (() => {
    val x_117 = iterMut_15;
    val x_118 = x_117.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_119 = x_118.hasNext;
    val x_120 = x_119.`unary_!`;
    if (x_120)
      {
        resetData_0 = 0.0;
        val x_121 = resetData_0;
        val x_122 = x_121.asInstanceOf[scala.Double];
        bindingMut_12 = x_122;
        positionVar_17 = 9
      }
    else
      ()
  }));
  data_18.update(9, (() => {
    val x_123 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_124 = meta.runtime.SimRuntime.labelVals(x_123);
    val x_125 = bindingMut_12;
    val x_126 = x_125.asInstanceOf[scala.Double];
    val x_127 = 1.0.-(x_126);
    x_124.append(x_127);
    resetData_0 = ();
    positionVar_17 = 10;
    val x_128 = currentTurn;
    val x_129 = x_128.+(1);
    currentTurn = x_129
  }));
  data_18.update(10, (() => {
    val x_130 = meta.runtime.SimRuntime.proceedLabel;
    val x_131 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_132 = x_130(x_131);
    val x_133 = bindingMut_12;
    val x_134 = x_133.asInstanceOf[scala.Double];
    val x_135 = x_134.+(x_132);
    resetData_0 = x_135;
    val x_136 = resetData_0;
    val x_137 = x_136.asInstanceOf[scala.Double];
    bindingMut_12 = x_137;
    positionVar_17 = 11
  }));
  data_18.update(11, (() => {
    val x_138 = bindingMut_12;
    val x_139 = x_138.asInstanceOf[scala.Double];
    val x_140 = x_139.<(1.0);
    if (x_140)
      positionVar_17 = 9
    else
      positionVar_17 = 12
  }));
  data_18.update(12, (() => {
    val x_141 = bindingMut_12;
    val x_142 = x_141.asInstanceOf[scala.Double];
    val x_143 = x_142.<(1.0);
    val x_144 = x_143.`unary_!`;
    if (x_144)
      positionVar_17 = 13
    else
      ()
  }));
  data_18.update(13, (() => if (true)
    positionVar_17 = 1
  else
    positionVar_17 = 14));
  data_18.update(14, (() => {
    val x_145 = true.`unary_!`;
    if (x_145)
      positionVar_17 = 15
    else
      ()
  }));
  data_18.update(15, (() => {
    positionVar_17 = 16;
    val x_146 = currentTurn;
    val x_147 = x_146.+(1);
    currentTurn = x_147
  }));
  data_18.update(16, (() => positionVar_17 = 15));
  data_18.update(17, (() => {
    val x_148 = true.`unary_!`;
    if (x_148)
      positionVar_17 = 15
    else
      ()
  }));
  data_18
}).apply();
  
  override def run_until(until_150: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_151 = currentTurn;
      val x_152 = x_151.<=(until_150);
      x_152.&&({
        val x_153 = positionVar_17;
        val x_154 = commands_149.length;
        x_153.<(x_154)
      })
    }) 
      {
        val x_155 = positionVar_17;
        val x_156 = commands_149.apply(x_155);
        x_156.apply()
      }
    ;
    this
  }
}
