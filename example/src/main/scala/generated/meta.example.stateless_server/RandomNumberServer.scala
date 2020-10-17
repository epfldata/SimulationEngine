package generated.meta.example.stateless_server

class RandomNumberServer () extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Double = 0.0;
  private var bindingMut_4: scala.Double = 0.0;
  private var bindingMut_5: scala.Any = null;
  private var listValMut_6: meta.deep.runtime.RequestMessage = null;
  private var iterMut_7: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: scala.Double = 0.0;
  private var positionVar_11: scala.Int = 0;
  
  val commands_162 = (() => {
  val data_12 = new scala.Array[scala.Function0[scala.Unit]](25);
  data_12.update(0, (() => {
    positionVar_11 = 1;
    val x_13 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_14 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_13, 21);
    val x_15 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_14);
    resetData_1.prepend(x_15)
  }));
  data_12.update(1, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 24));
  data_12.update(2, (() => {
    val x_16 = 1.toDouble;
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Double];
    bindingMut_9 = x_18;
    resetData_0 = 0.0;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[scala.Double];
    bindingMut_8 = x_20;
    positionVar_11 = 3
  }));
  data_12.update(3, (() => {
    val x_21 = meta.deep.runtime.Actor.proceedLabel;
    val x_22 = x_21("turn");
    val x_23 = bindingMut_8;
    val x_24 = x_23.asInstanceOf[scala.Double];
    val x_25 = x_24.+(x_22);
    resetData_0 = x_25;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Double];
    bindingMut_8 = x_27;
    val x_28 = meta.deep.runtime.Actor.labelVals("turn");
    val x_29 = bindingMut_8;
    val x_30 = x_29.asInstanceOf[scala.Double];
    val x_31 = bindingMut_9;
    val x_32 = x_31.asInstanceOf[scala.Double];
    val x_33 = x_32.-(x_30);
    x_28.append(x_33);
    resetData_0 = ();
    positionVar_11 = 4;
    val x_34 = currentTurn;
    val x_35 = x_34.+(1);
    currentTurn = x_35
  }));
  data_12.update(4, (() => {
    val x_36 = bindingMut_8;
    val x_37 = x_36.asInstanceOf[scala.Double];
    val x_38 = bindingMut_9;
    val x_39 = x_38.asInstanceOf[scala.Double];
    val x_40 = x_37.<(x_39);
    if (x_40)
      positionVar_11 = 3
    else
      positionVar_11 = 5
  }));
  data_12.update(5, (() => {
    val x_41 = bindingMut_8;
    val x_42 = x_41.asInstanceOf[scala.Double];
    val x_43 = bindingMut_9;
    val x_44 = x_43.asInstanceOf[scala.Double];
    val x_45 = x_42.<(x_44);
    val x_46 = x_45.`unary_!`;
    if (x_46)
      {
        val x_47 = this.popRequestMessages;
        val x_48 = x_47.iterator;
        iterMut_7 = x_48;
        positionVar_11 = 6
      }
    else
      ()
  }));
  data_12.update(6, (() => {
    val x_49 = iterMut_7;
    val x_50 = x_49.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_51 = x_50.hasNext;
    if (x_51)
      {
        val x_52 = iterMut_7;
        val x_53 = x_52.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_54 = x_53.next();
        listValMut_6 = x_54;
        positionVar_11 = 7
      }
    else
      positionVar_11 = 17
  }));
  data_12.update(7, (() => {
    val x_55 = listValMut_6;
    val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_57 = x_56.methodId;
    val x_58 = x_57.==(2);
    val x_59 = x_58.`unary_!`;
    if (x_59)
      positionVar_11 = 8
    else
      positionVar_11 = 16
  }));
  data_12.update(8, (() => {
    val x_60 = listValMut_6;
    val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_62 = x_61.methodId;
    val x_63 = x_62.==(3);
    val x_64 = x_63.`unary_!`;
    if (x_64)
      positionVar_11 = 9
    else
      positionVar_11 = 12
  }));
  data_12.update(9, (() => {
    val x_65 = listValMut_6;
    val x_66 = x_65.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_67 = x_66.methodId;
    val x_68 = x_67.==(4);
    val x_69 = x_68.`unary_!`;
    if (x_69)
      {
        val x_70 = listValMut_6;
        val x_71 = x_70.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_72 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_71);
        val x_73 = this.addReceiveMessages(x_72);
        resetData_0 = x_73;
        positionVar_11 = 10
      }
    else
      positionVar_11 = 11
  }));
  data_12.update(10, (() => positionVar_11 = 6));
  data_12.update(11, (() => {
    val x_74 = listValMut_6;
    val x_75 = x_74.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_76 = x_75.methodId;
    val x_77 = x_76.==(4);
    if (x_77)
      positionVar_11 = 1
    else
      ();
    val x_78 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_79 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_78, 20);
    val x_80 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_79);
    resetData_1.prepend(x_80)
  }));
  data_12.update(12, (() => {
    val x_81 = listValMut_6;
    val x_82 = x_81.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_83 = x_82.methodId;
    val x_84 = x_83.==(3);
    if (x_84)
      {
        val x_85 = 1.toDouble;
        resetData_0 = x_85;
        val x_86 = resetData_0;
        val x_87 = x_86.asInstanceOf[scala.Double];
        bindingMut_4 = x_87;
        resetData_0 = 0.0;
        val x_88 = resetData_0;
        val x_89 = x_88.asInstanceOf[scala.Double];
        bindingMut_3 = x_89;
        positionVar_11 = 13
      }
    else
      ()
  }));
  data_12.update(13, (() => {
    val x_90 = meta.deep.runtime.Actor.proceedLabel;
    val x_91 = x_90("turn");
    val x_92 = bindingMut_3;
    val x_93 = x_92.asInstanceOf[scala.Double];
    val x_94 = x_93.+(x_91);
    resetData_0 = x_94;
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[scala.Double];
    bindingMut_3 = x_96;
    val x_97 = meta.deep.runtime.Actor.labelVals("turn");
    val x_98 = bindingMut_3;
    val x_99 = x_98.asInstanceOf[scala.Double];
    val x_100 = bindingMut_4;
    val x_101 = x_100.asInstanceOf[scala.Double];
    val x_102 = x_101.-(x_99);
    x_97.append(x_102);
    resetData_0 = ();
    positionVar_11 = 14;
    val x_103 = currentTurn;
    val x_104 = x_103.+(1);
    currentTurn = x_104
  }));
  data_12.update(14, (() => {
    val x_105 = bindingMut_3;
    val x_106 = x_105.asInstanceOf[scala.Double];
    val x_107 = bindingMut_4;
    val x_108 = x_107.asInstanceOf[scala.Double];
    val x_109 = x_106.<(x_108);
    if (x_109)
      positionVar_11 = 13
    else
      positionVar_11 = 15
  }));
  data_12.update(15, (() => {
    val x_110 = bindingMut_3;
    val x_111 = x_110.asInstanceOf[scala.Double];
    val x_112 = bindingMut_4;
    val x_113 = x_112.asInstanceOf[scala.Double];
    val x_114 = x_111.<(x_113);
    val x_115 = x_114.`unary_!`;
    if (x_115)
      {
        val x_116 = scala.util.Random.nextInt(50);
        resetData_0 = x_116;
        val x_117 = resetData_0;
        val x_118 = x_117.asInstanceOf[scala.Any];
        bindingMut_5 = x_118;
        val x_119 = bindingMut_5;
        val x_120 = x_119.asInstanceOf[scala.Any];
        val x_121 = listValMut_6;
        val x_122 = x_121.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_122.reply(this, x_120);
        resetData_0 = ();
        positionVar_11 = 10
      }
    else
      ()
  }));
  data_12.update(16, (() => {
    val x_123 = listValMut_6;
    val x_124 = x_123.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_125 = x_124.methodId;
    val x_126 = x_125.==(2);
    if (x_126)
      {
        val x_127 = scala.util.Random.nextInt(1000);
        resetData_0 = x_127;
        val x_128 = resetData_0;
        val x_129 = x_128.asInstanceOf[scala.Any];
        bindingMut_5 = x_129;
        val x_130 = bindingMut_5;
        val x_131 = x_130.asInstanceOf[scala.Any];
        val x_132 = listValMut_6;
        val x_133 = x_132.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_133.reply(this, x_131);
        resetData_0 = ();
        positionVar_11 = 10
      }
    else
      ()
  }));
  data_12.update(17, (() => {
    val x_134 = iterMut_7;
    val x_135 = x_134.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_136 = x_135.hasNext;
    val x_137 = x_136.`unary_!`;
    if (x_137)
      positionVar_11 = 18
    else
      ()
  }));
  data_12.update(18, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 19));
  data_12.update(19, (() => {
    val x_138 = true.`unary_!`;
    if (x_138)
      {
        val x_139 = resetData_1.remove(0);
        val x_143 = x_139.find(((x_140: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_141 = x_140._1;
          val x_142 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_141.==(x_142)
        }));
        val x_144 = x_143.get;
        val x_145 = x_144._2;
        positionVar_11 = x_145
      }
    else
      ()
  }));
  data_12.update(20, (() => {
    val x_146 = resetData_0;
    val x_147 = x_146.asInstanceOf[scala.Any];
    bindingMut_5 = x_147;
    val x_148 = bindingMut_5;
    val x_149 = x_148.asInstanceOf[scala.Any];
    val x_150 = listValMut_6;
    val x_151 = x_150.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_151.reply(this, x_149);
    resetData_0 = ();
    positionVar_11 = 10
  }));
  data_12.update(21, (() => positionVar_11 = 22));
  data_12.update(22, (() => {
    positionVar_11 = 23;
    val x_152 = currentTurn;
    val x_153 = x_152.+(1);
    currentTurn = x_153
  }));
  data_12.update(23, (() => positionVar_11 = 22));
  data_12.update(24, (() => {
    val x_154 = true.`unary_!`;
    if (x_154)
      {
        val x_155 = resetData_1.remove(0);
        val x_159 = x_155.find(((x_156: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_157 = x_156._1;
          val x_158 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_157.==(x_158)
        }));
        val x_160 = x_159.get;
        val x_161 = x_160._2;
        positionVar_11 = x_161
      }
    else
      ()
  }));
  data_12
}).apply();
  
  override def run_until(until_163: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_164 = currentTurn;
      val x_165 = x_164.<=(until_163);
      x_165.&&({
        val x_166 = positionVar_11;
        val x_167 = commands_162.length;
        x_166.<(x_167)
      })
    }) 
      {
        val x_168 = positionVar_11;
        val x_169 = commands_162.apply(x_168);
        x_169.apply()
      }
    ;
    this
  }
}
