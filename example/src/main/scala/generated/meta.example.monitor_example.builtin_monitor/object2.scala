package generated.meta.example.monitor_example.builtin_monitor

class object2 () extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Any = null;
  private var listValMut_4: meta.deep.runtime.RequestMessage = null;
  private var iterMut_5: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: meta.deep.runtime.Monitor.type = null;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: meta.deep.runtime.Monitor.type = null;
  private var bindingMut_12: scala.Boolean = false;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: meta.deep.runtime.Monitor.type = null;
  private var bindingMut_15: scala.Double = 0.0;
  private var bindingMut_16: meta.deep.runtime.Monitor.type = null;
  private var bindingMut_17: scala.Boolean = false;
  private var positionVar_19: scala.Int = 0;
  
  val commands_171 = (() => {
  val data_20 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_20.update(0, (() => {
    positionVar_19 = 1;
    val x_21 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_22 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_21, 18);
    val x_23 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_22);
    resetData_1.prepend(x_23)
  }));
  data_20.update(1, (() => if (true)
    positionVar_19 = 2
  else
    positionVar_19 = 23));
  data_20.update(2, (() => {
    val x_24 = scala.util.Random.nextBoolean();
    resetData_0 = x_24;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[scala.Boolean];
    bindingMut_17 = x_26;
    positionVar_19 = 3
  }));
  data_20.update(3, (() => {
    val x_27 = bindingMut_17;
    val x_28 = x_27.asInstanceOf[scala.Boolean];
    val x_29 = x_28.`unary_!`;
    if (x_29)
      positionVar_19 = 4
    else
      positionVar_19 = 22
  }));
  data_20.update(4, (() => {
    val x_30 = scala.util.Random.nextBoolean();
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_32;
    positionVar_19 = 5
  }));
  data_20.update(5, (() => {
    val x_33 = bindingMut_12;
    val x_34 = x_33.asInstanceOf[scala.Boolean];
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_19 = 6
    else
      positionVar_19 = 21
  }));
  data_20.update(6, (() => {
    val x_36 = 1.toDouble;
    resetData_0 = x_36;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Double];
    bindingMut_7 = x_38;
    resetData_0 = 0.0;
    val x_39 = resetData_0;
    val x_40 = x_39.asInstanceOf[scala.Double];
    bindingMut_6 = x_40;
    positionVar_19 = 7
  }));
  data_20.update(7, (() => {
    val x_41 = meta.deep.runtime.Actor.proceedLabel;
    val x_42 = x_41("turn");
    val x_43 = bindingMut_6;
    val x_44 = x_43.asInstanceOf[scala.Double];
    val x_45 = x_44.+(x_42);
    resetData_0 = x_45;
    val x_46 = resetData_0;
    val x_47 = x_46.asInstanceOf[scala.Double];
    bindingMut_6 = x_47;
    val x_48 = meta.deep.runtime.Actor.labelVals("turn");
    val x_49 = bindingMut_6;
    val x_50 = x_49.asInstanceOf[scala.Double];
    val x_51 = bindingMut_7;
    val x_52 = x_51.asInstanceOf[scala.Double];
    val x_53 = x_52.-(x_50);
    x_48.append(x_53);
    resetData_0 = ();
    positionVar_19 = 8;
    val x_54 = currentTurn;
    val x_55 = x_54.+(1);
    currentTurn = x_55
  }));
  data_20.update(8, (() => {
    val x_56 = bindingMut_6;
    val x_57 = x_56.asInstanceOf[scala.Double];
    val x_58 = bindingMut_7;
    val x_59 = x_58.asInstanceOf[scala.Double];
    val x_60 = x_57.<(x_59);
    if (x_60)
      positionVar_19 = 7
    else
      positionVar_19 = 9
  }));
  data_20.update(9, (() => {
    val x_61 = bindingMut_6;
    val x_62 = x_61.asInstanceOf[scala.Double];
    val x_63 = bindingMut_7;
    val x_64 = x_63.asInstanceOf[scala.Double];
    val x_65 = x_62.<(x_64);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      {
        val x_67 = this.popRequestMessages;
        val x_68 = x_67.iterator;
        iterMut_5 = x_68;
        positionVar_19 = 10
      }
    else
      ()
  }));
  data_20.update(10, (() => {
    val x_69 = iterMut_5;
    val x_70 = x_69.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_71 = x_70.hasNext;
    if (x_71)
      {
        val x_72 = iterMut_5;
        val x_73 = x_72.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_74 = x_73.next();
        listValMut_4 = x_74;
        positionVar_19 = 11
      }
    else
      positionVar_19 = 14
  }));
  data_20.update(11, (() => {
    val x_75 = listValMut_4;
    val x_76 = x_75.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_77 = x_76.methodId;
    val x_78 = x_77.==(1);
    val x_79 = x_78.`unary_!`;
    if (x_79)
      {
        val x_80 = listValMut_4;
        val x_81 = x_80.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_82 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_81);
        val x_83 = this.addReceiveMessages(x_82);
        resetData_0 = x_83;
        positionVar_19 = 12
      }
    else
      positionVar_19 = 13
  }));
  data_20.update(12, (() => positionVar_19 = 10));
  data_20.update(13, (() => {
    val x_84 = listValMut_4;
    val x_85 = x_84.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_86 = x_85.methodId;
    val x_87 = x_86.==(1);
    if (x_87)
      positionVar_19 = 1
    else
      ();
    val x_88 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_89 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_88, 17);
    val x_90 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_89);
    resetData_1.prepend(x_90)
  }));
  data_20.update(14, (() => {
    val x_91 = iterMut_5;
    val x_92 = x_91.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_93 = x_92.hasNext;
    val x_94 = x_93.`unary_!`;
    if (x_94)
      positionVar_19 = 15
    else
      ()
  }));
  data_20.update(15, (() => if (true)
    positionVar_19 = 2
  else
    positionVar_19 = 16));
  data_20.update(16, (() => {
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
        positionVar_19 = x_102
      }
    else
      ()
  }));
  data_20.update(17, (() => {
    val x_103 = resetData_0;
    val x_104 = x_103.asInstanceOf[scala.Any];
    bindingMut_3 = x_104;
    val x_105 = bindingMut_3;
    val x_106 = x_105.asInstanceOf[scala.Any];
    val x_107 = listValMut_4;
    val x_108 = x_107.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_108.reply(this, x_106);
    resetData_0 = ();
    positionVar_19 = 12
  }));
  data_20.update(18, (() => positionVar_19 = 19));
  data_20.update(19, (() => {
    positionVar_19 = 20;
    val x_109 = currentTurn;
    val x_110 = x_109.+(1);
    currentTurn = x_110
  }));
  data_20.update(20, (() => positionVar_19 = 19));
  data_20.update(21, (() => {
    val x_111 = bindingMut_12;
    val x_112 = x_111.asInstanceOf[scala.Boolean];
    if (x_112)
      {
        scala.Predef.println("Object2 Recovered!");
        resetData_0 = ();
        val x_113 = this.monitor;
        resetData_0 = x_113;
        val x_114 = resetData_0;
        val x_115 = x_114.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_11 = x_115;
        val x_116 = bindingMut_11;
        val x_117 = x_116.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_118 = x_117.logAggregate$default$2;
        resetData_0 = x_118;
        val x_119 = resetData_0;
        val x_120 = x_119.asInstanceOf[scala.Double];
        bindingMut_10 = x_120;
        val x_121 = bindingMut_10;
        val x_122 = x_121.asInstanceOf[scala.Double];
        val x_123 = bindingMut_11;
        val x_124 = x_123.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_124.logAggregate("Recovered", x_122);
        resetData_0 = ();
        val x_125 = this.monitor;
        resetData_0 = x_125;
        val x_126 = resetData_0;
        val x_127 = x_126.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_9 = x_127;
        val x_128 = bindingMut_9;
        val x_129 = x_128.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_130 = x_129.logTimeseries$default$2;
        resetData_0 = x_130;
        val x_131 = resetData_0;
        val x_132 = x_131.asInstanceOf[scala.Double];
        bindingMut_8 = x_132;
        val x_133 = bindingMut_8;
        val x_134 = x_133.asInstanceOf[scala.Double];
        val x_135 = bindingMut_9;
        val x_136 = x_135.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_136.logTimeseries("Recovered", x_134);
        resetData_0 = ();
        positionVar_19 = 6
      }
    else
      ()
  }));
  data_20.update(22, (() => {
    val x_137 = bindingMut_17;
    val x_138 = x_137.asInstanceOf[scala.Boolean];
    if (x_138)
      {
        scala.Predef.println("Object2 infected!");
        resetData_0 = ();
        val x_139 = this.monitor;
        resetData_0 = x_139;
        val x_140 = resetData_0;
        val x_141 = x_140.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_16 = x_141;
        val x_142 = bindingMut_16;
        val x_143 = x_142.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_144 = x_143.logAggregate$default$2;
        resetData_0 = x_144;
        val x_145 = resetData_0;
        val x_146 = x_145.asInstanceOf[scala.Double];
        bindingMut_15 = x_146;
        val x_147 = bindingMut_15;
        val x_148 = x_147.asInstanceOf[scala.Double];
        val x_149 = bindingMut_16;
        val x_150 = x_149.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_150.logAggregate("Infectious", x_148);
        resetData_0 = ();
        val x_151 = this.monitor;
        resetData_0 = x_151;
        val x_152 = resetData_0;
        val x_153 = x_152.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_14 = x_153;
        val x_154 = bindingMut_14;
        val x_155 = x_154.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_156 = x_155.logTimeseries$default$2;
        resetData_0 = x_156;
        val x_157 = resetData_0;
        val x_158 = x_157.asInstanceOf[scala.Double];
        bindingMut_13 = x_158;
        val x_159 = bindingMut_13;
        val x_160 = x_159.asInstanceOf[scala.Double];
        val x_161 = bindingMut_14;
        val x_162 = x_161.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_162.logTimeseries("Infectious", x_160);
        resetData_0 = ();
        positionVar_19 = 4
      }
    else
      ()
  }));
  data_20.update(23, (() => {
    val x_163 = true.`unary_!`;
    if (x_163)
      {
        val x_164 = resetData_1.remove(0);
        val x_168 = x_164.find(((x_165: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_166 = x_165._1;
          val x_167 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_166.==(x_167)
        }));
        val x_169 = x_168.get;
        val x_170 = x_169._2;
        positionVar_19 = x_170
      }
    else
      ()
  }));
  data_20
}).apply();
  
  override def run_until(until_172: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_173 = currentTurn;
      val x_174 = x_173.<=(until_172);
      x_174.&&({
        val x_175 = positionVar_19;
        val x_176 = commands_171.length;
        x_175.<(x_176)
      })
    }) 
      {
        val x_177 = positionVar_19;
        val x_178 = commands_171.apply(x_177);
        x_178.apply()
      }
    ;
    this
  }
}
