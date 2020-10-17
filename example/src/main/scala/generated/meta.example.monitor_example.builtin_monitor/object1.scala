package generated.meta.example.monitor_example.builtin_monitor

class object1 () extends meta.deep.runtime.Actor {
  var name: String = "object 1"
  var isInfected: Boolean = false
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
  private var bindingMut_13: scala.Boolean = false;
  private var positionVar_15: scala.Int = 0;
  
  val commands_140 = (() => {
  val data_16 = new scala.Array[scala.Function0[scala.Unit]](21);
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
    positionVar_15 = 20));
  data_16.update(2, (() => {
    val x_20 = scala.util.Random.nextBoolean();
    resetData_0 = x_20;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Boolean];
    bindingMut_13 = x_22;
    val x_23 = bindingMut_13;
    val x_24 = x_23.asInstanceOf[scala.Boolean];
    this.`isInfected_=`(x_24);
    resetData_0 = ();
    val x_25 = this.isInfected;
    resetData_0 = x_25;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_27;
    positionVar_15 = 3
  }));
  data_16.update(3, (() => {
    val x_28 = bindingMut_12;
    val x_29 = x_28.asInstanceOf[scala.Boolean];
    val x_30 = x_29.`unary_!`;
    if (x_30)
      positionVar_15 = 4
    else
      positionVar_15 = 19
  }));
  data_16.update(4, (() => {
    val x_31 = 1.toDouble;
    resetData_0 = x_31;
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[scala.Double];
    bindingMut_7 = x_33;
    resetData_0 = 0.0;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Double];
    bindingMut_6 = x_35;
    positionVar_15 = 5
  }));
  data_16.update(5, (() => {
    val x_36 = meta.deep.runtime.Actor.proceedLabel;
    val x_37 = x_36("turn");
    val x_38 = bindingMut_6;
    val x_39 = x_38.asInstanceOf[scala.Double];
    val x_40 = x_39.+(x_37);
    resetData_0 = x_40;
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[scala.Double];
    bindingMut_6 = x_42;
    val x_43 = meta.deep.runtime.Actor.labelVals("turn");
    val x_44 = bindingMut_6;
    val x_45 = x_44.asInstanceOf[scala.Double];
    val x_46 = bindingMut_7;
    val x_47 = x_46.asInstanceOf[scala.Double];
    val x_48 = x_47.-(x_45);
    x_43.append(x_48);
    resetData_0 = ();
    positionVar_15 = 6;
    val x_49 = currentTurn;
    val x_50 = x_49.+(1);
    currentTurn = x_50
  }));
  data_16.update(6, (() => {
    val x_51 = bindingMut_6;
    val x_52 = x_51.asInstanceOf[scala.Double];
    val x_53 = bindingMut_7;
    val x_54 = x_53.asInstanceOf[scala.Double];
    val x_55 = x_52.<(x_54);
    if (x_55)
      positionVar_15 = 5
    else
      positionVar_15 = 7
  }));
  data_16.update(7, (() => {
    val x_56 = bindingMut_6;
    val x_57 = x_56.asInstanceOf[scala.Double];
    val x_58 = bindingMut_7;
    val x_59 = x_58.asInstanceOf[scala.Double];
    val x_60 = x_57.<(x_59);
    val x_61 = x_60.`unary_!`;
    if (x_61)
      {
        val x_62 = this.popRequestMessages;
        val x_63 = x_62.iterator;
        iterMut_5 = x_63;
        positionVar_15 = 8
      }
    else
      ()
  }));
  data_16.update(8, (() => {
    val x_64 = iterMut_5;
    val x_65 = x_64.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_66 = x_65.hasNext;
    if (x_66)
      {
        val x_67 = iterMut_5;
        val x_68 = x_67.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_69 = x_68.next();
        listValMut_4 = x_69;
        positionVar_15 = 9
      }
    else
      positionVar_15 = 12
  }));
  data_16.update(9, (() => {
    val x_70 = listValMut_4;
    val x_71 = x_70.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_72 = x_71.methodId;
    val x_73 = x_72.==(0);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      {
        val x_75 = listValMut_4;
        val x_76 = x_75.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_77 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_76);
        val x_78 = this.addReceiveMessages(x_77);
        resetData_0 = x_78;
        positionVar_15 = 10
      }
    else
      positionVar_15 = 11
  }));
  data_16.update(10, (() => positionVar_15 = 8));
  data_16.update(11, (() => {
    val x_79 = listValMut_4;
    val x_80 = x_79.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_81 = x_80.methodId;
    val x_82 = x_81.==(0);
    if (x_82)
      positionVar_15 = 1
    else
      ();
    val x_83 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_84 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_83, 15);
    val x_85 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_84);
    resetData_1.prepend(x_85)
  }));
  data_16.update(12, (() => {
    val x_86 = iterMut_5;
    val x_87 = x_86.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_88 = x_87.hasNext;
    val x_89 = x_88.`unary_!`;
    if (x_89)
      positionVar_15 = 13
    else
      ()
  }));
  data_16.update(13, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 14));
  data_16.update(14, (() => {
    val x_90 = true.`unary_!`;
    if (x_90)
      {
        val x_91 = resetData_1.remove(0);
        val x_95 = x_91.find(((x_92: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_93 = x_92._1;
          val x_94 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_93.==(x_94)
        }));
        val x_96 = x_95.get;
        val x_97 = x_96._2;
        positionVar_15 = x_97
      }
    else
      ()
  }));
  data_16.update(15, (() => {
    val x_98 = resetData_0;
    val x_99 = x_98.asInstanceOf[scala.Any];
    bindingMut_3 = x_99;
    val x_100 = bindingMut_3;
    val x_101 = x_100.asInstanceOf[scala.Any];
    val x_102 = listValMut_4;
    val x_103 = x_102.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_103.reply(this, x_101);
    resetData_0 = ();
    positionVar_15 = 10
  }));
  data_16.update(16, (() => positionVar_15 = 17));
  data_16.update(17, (() => {
    positionVar_15 = 18;
    val x_104 = currentTurn;
    val x_105 = x_104.+(1);
    currentTurn = x_105
  }));
  data_16.update(18, (() => positionVar_15 = 17));
  data_16.update(19, (() => {
    val x_106 = bindingMut_12;
    val x_107 = x_106.asInstanceOf[scala.Boolean];
    if (x_107)
      {
        scala.Predef.println("Object1 infected!");
        resetData_0 = ();
        val x_108 = this.monitor;
        resetData_0 = x_108;
        val x_109 = resetData_0;
        val x_110 = x_109.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_11 = x_110;
        val x_111 = bindingMut_11;
        val x_112 = x_111.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_113 = x_112.logAggregate$default$2;
        resetData_0 = x_113;
        val x_114 = resetData_0;
        val x_115 = x_114.asInstanceOf[scala.Double];
        bindingMut_10 = x_115;
        val x_116 = bindingMut_10;
        val x_117 = x_116.asInstanceOf[scala.Double];
        val x_118 = bindingMut_11;
        val x_119 = x_118.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_119.logAggregate("Infectious", x_117);
        resetData_0 = ();
        val x_120 = this.monitor;
        resetData_0 = x_120;
        val x_121 = resetData_0;
        val x_122 = x_121.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_9 = x_122;
        val x_123 = bindingMut_9;
        val x_124 = x_123.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_125 = x_124.logTimeseries$default$2;
        resetData_0 = x_125;
        val x_126 = resetData_0;
        val x_127 = x_126.asInstanceOf[scala.Double];
        bindingMut_8 = x_127;
        val x_128 = bindingMut_8;
        val x_129 = x_128.asInstanceOf[scala.Double];
        val x_130 = bindingMut_9;
        val x_131 = x_130.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_131.logTimeseries("Infectious", x_129);
        resetData_0 = ();
        positionVar_15 = 4
      }
    else
      ()
  }));
  data_16.update(20, (() => {
    val x_132 = true.`unary_!`;
    if (x_132)
      {
        val x_133 = resetData_1.remove(0);
        val x_137 = x_133.find(((x_134: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_135 = x_134._1;
          val x_136 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_135.==(x_136)
        }));
        val x_138 = x_137.get;
        val x_139 = x_138._2;
        positionVar_15 = x_139
      }
    else
      ()
  }));
  data_16
}).apply();
  
  override def run_until(until_141: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_142 = currentTurn;
      val x_143 = x_142.<=(until_141);
      x_143.&&({
        val x_144 = positionVar_15;
        val x_145 = commands_140.length;
        x_144.<(x_145)
      })
    }) 
      {
        val x_146 = positionVar_15;
        val x_147 = commands_140.apply(x_146);
        x_147.apply()
      }
    ;
    this
  }
}
