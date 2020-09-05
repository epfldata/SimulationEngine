package generated.meta.example.monitor_example.builtin_monitor

class object1 () extends meta.deep.runtime.Actor with Serializable {
  var name: String = "object 1"
  var isInfected: Boolean = false
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
  private var bindingMut_13: scala.Boolean = false;
  private var positionVar_15: scala.Int = 0;
  
  val commands_129 = (() => {
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
    resetData_0 = 0;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[scala.Int];
    bindingMut_7 = x_32;
    positionVar_15 = 5
  }));
  data_16.update(5, (() => {
    val x_33 = bindingMut_7;
    val x_34 = x_33.asInstanceOf[scala.Int];
    val x_35 = (1).-(x_34);
    meta.deep.runtime.Actor.waitTurnList.append(x_35);
    resetData_0 = ();
    val x_36 = meta.deep.runtime.Actor.minTurn();
    val x_37 = bindingMut_7;
    val x_38 = x_37.asInstanceOf[scala.Int];
    val x_39 = x_38.+(x_36);
    resetData_0 = x_39;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Int];
    bindingMut_7 = x_41;
    positionVar_15 = 6;
    val x_42 = currentTurn;
    val x_43 = x_42.+(1);
    currentTurn = x_43
  }));
  data_16.update(6, (() => {
    val x_44 = bindingMut_7;
    val x_45 = x_44.asInstanceOf[scala.Int];
    val x_46 = x_45.<(1);
    if (x_46)
      positionVar_15 = 5
    else
      positionVar_15 = 7
  }));
  data_16.update(7, (() => {
    val x_47 = bindingMut_7;
    val x_48 = x_47.asInstanceOf[scala.Int];
    val x_49 = x_48.<(1);
    val x_50 = x_49.`unary_!`;
    if (x_50)
      {
        val x_51 = this.popRequestMessages;
        val x_52 = x_51.iterator;
        iterMut_6 = x_52;
        positionVar_15 = 8
      }
    else
      ()
  }));
  data_16.update(8, (() => {
    val x_53 = iterMut_6;
    val x_54 = x_53.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_55 = x_54.hasNext;
    if (x_55)
      {
        val x_56 = iterMut_6;
        val x_57 = x_56.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_58 = x_57.next();
        listValMut_5 = x_58;
        positionVar_15 = 9
      }
    else
      positionVar_15 = 12
  }));
  data_16.update(9, (() => {
    val x_59 = listValMut_5;
    val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_61 = x_60.methodId;
    val x_62 = x_61.==(0);
    val x_63 = x_62.`unary_!`;
    if (x_63)
      {
        val x_64 = listValMut_5;
        val x_65 = x_64.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_66 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_65);
        val x_67 = this.addReceiveMessages(x_66);
        resetData_0 = x_67;
        positionVar_15 = 10
      }
    else
      positionVar_15 = 11
  }));
  data_16.update(10, (() => positionVar_15 = 8));
  data_16.update(11, (() => {
    val x_68 = listValMut_5;
    val x_69 = x_68.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_70 = x_69.methodId;
    val x_71 = x_70.==(0);
    if (x_71)
      positionVar_15 = 1
    else
      ();
    val x_72 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_73 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_72, 15);
    val x_74 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_73);
    resetData_1.prepend(x_74)
  }));
  data_16.update(12, (() => {
    val x_75 = iterMut_6;
    val x_76 = x_75.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_77 = x_76.hasNext;
    val x_78 = x_77.`unary_!`;
    if (x_78)
      positionVar_15 = 13
    else
      ()
  }));
  data_16.update(13, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 14));
  data_16.update(14, (() => {
    val x_79 = true.`unary_!`;
    if (x_79)
      {
        val x_80 = resetData_1.remove(0);
        val x_84 = x_80.find(((x_81: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_82 = x_81._1;
          val x_83 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_82.==(x_83)
        }));
        val x_85 = x_84.get;
        val x_86 = x_85._2;
        positionVar_15 = x_86
      }
    else
      ()
  }));
  data_16.update(15, (() => {
    val x_87 = resetData_0;
    val x_88 = x_87.asInstanceOf[scala.Any];
    bindingMut_4 = x_88;
    val x_89 = bindingMut_4;
    val x_90 = x_89.asInstanceOf[scala.Any];
    val x_91 = listValMut_5;
    val x_92 = x_91.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_92.reply(this, x_90);
    resetData_0 = ();
    positionVar_15 = 10
  }));
  data_16.update(16, (() => positionVar_15 = 17));
  data_16.update(17, (() => {
    positionVar_15 = 18;
    val x_93 = currentTurn;
    val x_94 = x_93.+(1);
    currentTurn = x_94
  }));
  data_16.update(18, (() => positionVar_15 = 17));
  data_16.update(19, (() => {
    val x_95 = bindingMut_12;
    val x_96 = x_95.asInstanceOf[scala.Boolean];
    if (x_96)
      {
        scala.Predef.println("Object1 infected!");
        resetData_0 = ();
        val x_97 = this.monitor;
        resetData_0 = x_97;
        val x_98 = resetData_0;
        val x_99 = x_98.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_11 = x_99;
        val x_100 = bindingMut_11;
        val x_101 = x_100.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_102 = x_101.logAggregate$default$2;
        resetData_0 = x_102;
        val x_103 = resetData_0;
        val x_104 = x_103.asInstanceOf[scala.Double];
        bindingMut_10 = x_104;
        val x_105 = bindingMut_10;
        val x_106 = x_105.asInstanceOf[scala.Double];
        val x_107 = bindingMut_11;
        val x_108 = x_107.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_108.logAggregate("Infectious", x_106);
        resetData_0 = ();
        val x_109 = this.monitor;
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[meta.deep.runtime.Monitor.type];
        bindingMut_9 = x_111;
        val x_112 = bindingMut_9;
        val x_113 = x_112.asInstanceOf[meta.deep.runtime.Monitor.type];
        val x_114 = x_113.logTimeseries$default$2;
        resetData_0 = x_114;
        val x_115 = resetData_0;
        val x_116 = x_115.asInstanceOf[scala.Double];
        bindingMut_8 = x_116;
        val x_117 = bindingMut_8;
        val x_118 = x_117.asInstanceOf[scala.Double];
        val x_119 = bindingMut_9;
        val x_120 = x_119.asInstanceOf[meta.deep.runtime.Monitor.type];
        x_120.logTimeseries("Infectious", x_118);
        resetData_0 = ();
        positionVar_15 = 4
      }
    else
      ()
  }));
  data_16.update(20, (() => {
    val x_121 = true.`unary_!`;
    if (x_121)
      {
        val x_122 = resetData_1.remove(0);
        val x_126 = x_122.find(((x_123: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_124 = x_123._1;
          val x_125 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_124.==(x_125)
        }));
        val x_127 = x_126.get;
        val x_128 = x_127._2;
        positionVar_15 = x_128
      }
    else
      ()
  }));
  data_16
}).apply();
  
  override def run_until(until_130: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_131 = currentTurn;
      val x_132 = x_131.<=(until_130);
      x_132.&&({
        val x_133 = positionVar_15;
        val x_134 = commands_129.length;
        x_133.<(x_134)
      })
    }) 
      {
        val x_135 = positionVar_15;
        val x_136 = commands_129.apply(x_135);
        x_136.apply()
      }
    ;
    this
  }
}
