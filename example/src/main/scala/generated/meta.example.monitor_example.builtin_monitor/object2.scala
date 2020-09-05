package generated.meta.example.monitor_example.builtin_monitor

class object2 () extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: scala.Int = 0;
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
  
  val commands_160 = (() => {
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
    resetData_0 = 0;
    val x_36 = resetData_0;
    val x_37 = x_36.asInstanceOf[scala.Int];
    bindingMut_7 = x_37;
    positionVar_19 = 7
  }));
  data_20.update(7, (() => {
    val x_38 = bindingMut_7;
    val x_39 = x_38.asInstanceOf[scala.Int];
    val x_40 = (1).-(x_39);
    meta.deep.runtime.Actor.waitTurnList.append(x_40);
    resetData_0 = ();
    val x_41 = meta.deep.runtime.Actor.minTurn();
    val x_42 = bindingMut_7;
    val x_43 = x_42.asInstanceOf[scala.Int];
    val x_44 = x_43.+(x_41);
    resetData_0 = x_44;
    val x_45 = resetData_0;
    val x_46 = x_45.asInstanceOf[scala.Int];
    bindingMut_7 = x_46;
    positionVar_19 = 8;
    val x_47 = currentTurn;
    val x_48 = x_47.+(1);
    currentTurn = x_48
  }));
  data_20.update(8, (() => {
    val x_49 = bindingMut_7;
    val x_50 = x_49.asInstanceOf[scala.Int];
    val x_51 = x_50.<(1);
    if (x_51)
      positionVar_19 = 7
    else
      positionVar_19 = 9
  }));
  data_20.update(9, (() => {
    val x_52 = bindingMut_7;
    val x_53 = x_52.asInstanceOf[scala.Int];
    val x_54 = x_53.<(1);
    val x_55 = x_54.`unary_!`;
    if (x_55)
      {
        val x_56 = this.popRequestMessages;
        val x_57 = x_56.iterator;
        iterMut_6 = x_57;
        positionVar_19 = 10
      }
    else
      ()
  }));
  data_20.update(10, (() => {
    val x_58 = iterMut_6;
    val x_59 = x_58.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_60 = x_59.hasNext;
    if (x_60)
      {
        val x_61 = iterMut_6;
        val x_62 = x_61.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_63 = x_62.next();
        listValMut_5 = x_63;
        positionVar_19 = 11
      }
    else
      positionVar_19 = 14
  }));
  data_20.update(11, (() => {
    val x_64 = listValMut_5;
    val x_65 = x_64.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_66 = x_65.methodId;
    val x_67 = x_66.==(1);
    val x_68 = x_67.`unary_!`;
    if (x_68)
      {
        val x_69 = listValMut_5;
        val x_70 = x_69.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_71 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_70);
        val x_72 = this.addReceiveMessages(x_71);
        resetData_0 = x_72;
        positionVar_19 = 12
      }
    else
      positionVar_19 = 13
  }));
  data_20.update(12, (() => positionVar_19 = 10));
  data_20.update(13, (() => {
    val x_73 = listValMut_5;
    val x_74 = x_73.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_75 = x_74.methodId;
    val x_76 = x_75.==(1);
    if (x_76)
      positionVar_19 = 1
    else
      ();
    val x_77 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_78 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_77, 17);
    val x_79 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_78);
    resetData_1.prepend(x_79)
  }));
  data_20.update(14, (() => {
    val x_80 = iterMut_6;
    val x_81 = x_80.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_82 = x_81.hasNext;
    val x_83 = x_82.`unary_!`;
    if (x_83)
      positionVar_19 = 15
    else
      ()
  }));
  data_20.update(15, (() => if (true)
    positionVar_19 = 2
  else
    positionVar_19 = 16));
  data_20.update(16, (() => {
    val x_84 = true.`unary_!`;
    if (x_84)
      {
        val x_85 = resetData_1.remove(0);
        val x_89 = x_85.find(((x_86: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_87 = x_86._1;
          val x_88 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_87.==(x_88)
        }));
        val x_90 = x_89.get;
        val x_91 = x_90._2;
        positionVar_19 = x_91
      }
    else
      ()
  }));
  data_20.update(17, (() => {
    val x_92 = resetData_0;
    val x_93 = x_92.asInstanceOf[scala.Any];
    bindingMut_4 = x_93;
    val x_94 = bindingMut_4;
    val x_95 = x_94.asInstanceOf[scala.Any];
    val x_96 = listValMut_5;
    val x_97 = x_96.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_97.reply(this, x_95);
    resetData_0 = ();
    positionVar_19 = 12
  }));
  data_20.update(18, (() => positionVar_19 = 19));
  data_20.update(19, (() => {
    positionVar_19 = 20;
    val x_98 = currentTurn;
    val x_99 = x_98.+(1);
    currentTurn = x_99
  }));
  data_20.update(20, (() => positionVar_19 = 19));
  data_20.update(21, (() => {
    val x_100 = bindingMut_12;
    val x_101 = x_100.asInstanceOf[scala.Boolean];
    if (x_101)
      {
        scala.Predef.println("Object2 Recovered!");
        resetData_0 = ();
        val x_102 = this.monitor;
        resetData_0 = x_102;
        val x_103 = resetData_0;
        val x_104 = x_103.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_11 = x_104;
        val x_105 = bindingMut_11;
        val x_106 = x_105.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_107 = x_106.logAggregate$default$2;
        resetData_0 = x_107;
        val x_108 = resetData_0;
        val x_109 = x_108.asInstanceOf[scala.Double];
        bindingMut_10 = x_109;
        val x_110 = bindingMut_10;
        val x_111 = x_110.asInstanceOf[scala.Double];
        val x_112 = bindingMut_11;
        val x_113 = x_112.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_113.logAggregate("Recovered", x_111);
        resetData_0 = ();
        val x_114 = this.monitor;
        resetData_0 = x_114;
        val x_115 = resetData_0;
        val x_116 = x_115.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_9 = x_116;
        val x_117 = bindingMut_9;
        val x_118 = x_117.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_119 = x_118.logTimeseries$default$2;
        resetData_0 = x_119;
        val x_120 = resetData_0;
        val x_121 = x_120.asInstanceOf[scala.Double];
        bindingMut_8 = x_121;
        val x_122 = bindingMut_8;
        val x_123 = x_122.asInstanceOf[scala.Double];
        val x_124 = bindingMut_9;
        val x_125 = x_124.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_125.logTimeseries("Recovered", x_123);
        resetData_0 = ();
        positionVar_19 = 6
      }
    else
      ()
  }));
  data_20.update(22, (() => {
    val x_126 = bindingMut_17;
    val x_127 = x_126.asInstanceOf[scala.Boolean];
    if (x_127)
      {
        scala.Predef.println("Object2 infected!");
        resetData_0 = ();
        val x_128 = this.monitor;
        resetData_0 = x_128;
        val x_129 = resetData_0;
        val x_130 = x_129.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_16 = x_130;
        val x_131 = bindingMut_16;
        val x_132 = x_131.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_133 = x_132.logAggregate$default$2;
        resetData_0 = x_133;
        val x_134 = resetData_0;
        val x_135 = x_134.asInstanceOf[scala.Double];
        bindingMut_15 = x_135;
        val x_136 = bindingMut_15;
        val x_137 = x_136.asInstanceOf[scala.Double];
        val x_138 = bindingMut_16;
        val x_139 = x_138.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_139.logAggregate("Infectious", x_137);
        resetData_0 = ();
        val x_140 = this.monitor;
        resetData_0 = x_140;
        val x_141 = resetData_0;
        val x_142 = x_141.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_14 = x_142;
        val x_143 = bindingMut_14;
        val x_144 = x_143.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_145 = x_144.logTimeseries$default$2;
        resetData_0 = x_145;
        val x_146 = resetData_0;
        val x_147 = x_146.asInstanceOf[scala.Double];
        bindingMut_13 = x_147;
        val x_148 = bindingMut_13;
        val x_149 = x_148.asInstanceOf[scala.Double];
        val x_150 = bindingMut_14;
        val x_151 = x_150.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_151.logTimeseries("Infectious", x_149);
        resetData_0 = ();
        positionVar_19 = 4
      }
    else
      ()
  }));
  data_20.update(23, (() => {
    val x_152 = true.`unary_!`;
    if (x_152)
      {
        val x_153 = resetData_1.remove(0);
        val x_157 = x_153.find(((x_154: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_155 = x_154._1;
          val x_156 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_155.==(x_156)
        }));
        val x_158 = x_157.get;
        val x_159 = x_158._2;
        positionVar_19 = x_159
      }
    else
      ()
  }));
  data_20
}).apply();
  
  override def run_until(until_161: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_162 = currentTurn;
      val x_163 = x_162.<=(until_161);
      x_163.&&({
        val x_164 = positionVar_19;
        val x_165 = commands_160.length;
        x_164.<(x_165)
      })
    }) 
      {
        val x_166 = positionVar_19;
        val x_167 = commands_160.apply(x_166);
        x_167.apply()
      }
    ;
    this
  }
}
