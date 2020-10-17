package generated.meta.example.server_communication_merged

class BackendServer () extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Long = 0L;
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Long = 0L;
  private var positionVar_15: scala.Int = 0;
  
  val commands_149 = (() => {
  val data_16 = new scala.Array[scala.Function0[scala.Unit]](20);
  data_16.update(0, (() => {
    positionVar_15 = 1;
    val x_17 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_18 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_17, 16);
    val x_19 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_18);
    resetData_1.prepend(x_19)
  }));
  data_16.update(1, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 19));
  data_16.update(2, (() => {
    val x_20 = this.id;
    resetData_0 = x_20;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Long];
    bindingMut_13 = x_22;
    val x_23 = bindingMut_13;
    val x_24 = x_23.asInstanceOf[scala.Long];
    val x_25 = "Hello world! Backend ".+(x_24);
    resetData_0 = x_25;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[java.lang.String];
    bindingMut_12 = x_27;
    val x_28 = bindingMut_12;
    val x_29 = x_28.asInstanceOf[java.lang.String];
    val x_30 = x_29.+(" Turn ");
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[java.lang.String];
    bindingMut_11 = x_32;
    val x_33 = this.currentTurn;
    resetData_0 = x_33;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Int];
    bindingMut_10 = x_35;
    val x_36 = bindingMut_10;
    val x_37 = x_36.asInstanceOf[scala.Int];
    val x_38 = bindingMut_11;
    val x_39 = x_38.asInstanceOf[java.lang.String];
    val x_40 = x_39.+(x_37);
    resetData_0 = x_40;
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[java.lang.String];
    bindingMut_9 = x_42;
    val x_43 = bindingMut_9;
    val x_44 = x_43.asInstanceOf[java.lang.String];
    scala.Predef.println(x_44);
    resetData_0 = ();
    val x_45 = 1.toDouble;
    resetData_0 = x_45;
    val x_46 = resetData_0;
    val x_47 = x_46.asInstanceOf[scala.Double];
    bindingMut_8 = x_47;
    resetData_0 = 0.0;
    val x_48 = resetData_0;
    val x_49 = x_48.asInstanceOf[scala.Double];
    bindingMut_7 = x_49;
    positionVar_15 = 3
  }));
  data_16.update(3, (() => {
    val x_50 = meta.deep.runtime.Actor.proceedLabel;
    val x_51 = x_50("turn");
    val x_52 = bindingMut_7;
    val x_53 = x_52.asInstanceOf[scala.Double];
    val x_54 = x_53.+(x_51);
    resetData_0 = x_54;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.Double];
    bindingMut_7 = x_56;
    val x_57 = meta.deep.runtime.Actor.labelVals("turn");
    val x_58 = bindingMut_7;
    val x_59 = x_58.asInstanceOf[scala.Double];
    val x_60 = bindingMut_8;
    val x_61 = x_60.asInstanceOf[scala.Double];
    val x_62 = x_61.-(x_59);
    x_57.append(x_62);
    resetData_0 = ();
    positionVar_15 = 4;
    val x_63 = currentTurn;
    val x_64 = x_63.+(1);
    currentTurn = x_64
  }));
  data_16.update(4, (() => {
    val x_65 = bindingMut_7;
    val x_66 = x_65.asInstanceOf[scala.Double];
    val x_67 = bindingMut_8;
    val x_68 = x_67.asInstanceOf[scala.Double];
    val x_69 = x_66.<(x_68);
    if (x_69)
      positionVar_15 = 3
    else
      positionVar_15 = 5
  }));
  data_16.update(5, (() => {
    val x_70 = bindingMut_7;
    val x_71 = x_70.asInstanceOf[scala.Double];
    val x_72 = bindingMut_8;
    val x_73 = x_72.asInstanceOf[scala.Double];
    val x_74 = x_71.<(x_73);
    val x_75 = x_74.`unary_!`;
    if (x_75)
      {
        val x_76 = this.popRequestMessages;
        val x_77 = x_76.iterator;
        iterMut_6 = x_77;
        positionVar_15 = 6
      }
    else
      ()
  }));
  data_16.update(6, (() => {
    val x_78 = iterMut_6;
    val x_79 = x_78.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_80 = x_79.hasNext;
    if (x_80)
      {
        val x_81 = iterMut_6;
        val x_82 = x_81.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_83 = x_82.next();
        listValMut_5 = x_83;
        positionVar_15 = 7
      }
    else
      positionVar_15 = 12
  }));
  data_16.update(7, (() => {
    val x_84 = listValMut_5;
    val x_85 = x_84.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_86 = x_85.methodId;
    val x_87 = x_86.==(0);
    val x_88 = x_87.`unary_!`;
    if (x_88)
      positionVar_15 = 8
    else
      positionVar_15 = 11
  }));
  data_16.update(8, (() => {
    val x_89 = listValMut_5;
    val x_90 = x_89.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_91 = x_90.methodId;
    val x_92 = x_91.==(1);
    val x_93 = x_92.`unary_!`;
    if (x_93)
      {
        val x_94 = listValMut_5;
        val x_95 = x_94.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_96 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_95);
        val x_97 = this.addReceiveMessages(x_96);
        resetData_0 = x_97;
        positionVar_15 = 9
      }
    else
      positionVar_15 = 10
  }));
  data_16.update(9, (() => positionVar_15 = 6));
  data_16.update(10, (() => {
    val x_98 = listValMut_5;
    val x_99 = x_98.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_100 = x_99.methodId;
    val x_101 = x_100.==(1);
    if (x_101)
      positionVar_15 = 1
    else
      ();
    val x_102 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_103 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_102, 15);
    val x_104 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_103);
    resetData_1.prepend(x_104)
  }));
  data_16.update(11, (() => {
    val x_105 = listValMut_5;
    val x_106 = x_105.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_107 = x_106.methodId;
    val x_108 = x_107.==(0);
    if (x_108)
      {
        val x_109 = java.lang.System.nanoTime();
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Long];
        bindingMut_3 = x_111;
        val x_112 = bindingMut_3;
        val x_113 = x_112.asInstanceOf[scala.Long];
        val x_114 = x_113.toString();
        resetData_0 = x_114;
        val x_115 = resetData_0;
        val x_116 = x_115.asInstanceOf[scala.Any];
        bindingMut_4 = x_116;
        val x_117 = bindingMut_4;
        val x_118 = x_117.asInstanceOf[scala.Any];
        val x_119 = listValMut_5;
        val x_120 = x_119.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_120.reply(this, x_118);
        resetData_0 = ();
        positionVar_15 = 9
      }
    else
      ()
  }));
  data_16.update(12, (() => {
    val x_121 = iterMut_6;
    val x_122 = x_121.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_123 = x_122.hasNext;
    val x_124 = x_123.`unary_!`;
    if (x_124)
      positionVar_15 = 13
    else
      ()
  }));
  data_16.update(13, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 14));
  data_16.update(14, (() => {
    val x_125 = true.`unary_!`;
    if (x_125)
      {
        val x_126 = resetData_1.remove(0);
        val x_130 = x_126.find(((x_127: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_128 = x_127._1;
          val x_129 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_128.==(x_129)
        }));
        val x_131 = x_130.get;
        val x_132 = x_131._2;
        positionVar_15 = x_132
      }
    else
      ()
  }));
  data_16.update(15, (() => {
    val x_133 = resetData_0;
    val x_134 = x_133.asInstanceOf[scala.Any];
    bindingMut_4 = x_134;
    val x_135 = bindingMut_4;
    val x_136 = x_135.asInstanceOf[scala.Any];
    val x_137 = listValMut_5;
    val x_138 = x_137.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_138.reply(this, x_136);
    resetData_0 = ();
    positionVar_15 = 9
  }));
  data_16.update(16, (() => positionVar_15 = 17));
  data_16.update(17, (() => {
    positionVar_15 = 18;
    val x_139 = currentTurn;
    val x_140 = x_139.+(1);
    currentTurn = x_140
  }));
  data_16.update(18, (() => positionVar_15 = 17));
  data_16.update(19, (() => {
    val x_141 = true.`unary_!`;
    if (x_141)
      {
        val x_142 = resetData_1.remove(0);
        val x_146 = x_142.find(((x_143: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_144 = x_143._1;
          val x_145 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_144.==(x_145)
        }));
        val x_147 = x_146.get;
        val x_148 = x_147._2;
        positionVar_15 = x_148
      }
    else
      ()
  }));
  data_16
}).apply();
  
  override def run_until(until_150: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_151 = currentTurn;
      val x_152 = x_151.<=(until_150);
      x_152.&&({
        val x_153 = positionVar_15;
        val x_154 = commands_149.length;
        x_153.<(x_154)
      })
    }) 
      {
        val x_155 = positionVar_15;
        val x_156 = commands_149.apply(x_155);
        x_156.apply()
      }
    ;
    this
  }
}
