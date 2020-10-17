package generated.meta.example.monitor_example.agent_monitor

class object1 (var own_monitor: generated.meta.example.monitor_example.agent_monitor.monitorSim) extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Any = null;
  private var listValMut_4: meta.deep.runtime.RequestMessage = null;
  private var iterMut_5: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: generated.meta.example.monitor_example.agent_monitor.monitorSim = null;
  private var bindingMut_9: scala.Boolean = false;
  private var positionVar_11: scala.Int = 0;
  
  val commands_128 = (() => {
  val data_12 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_12.update(0, (() => {
    positionVar_11 = 1;
    val x_13 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_14 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_13, 16);
    val x_15 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_14);
    resetData_1.prepend(x_15)
  }));
  data_12.update(1, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 23));
  data_12.update(2, (() => {
    val x_16 = scala.util.Random.nextBoolean();
    resetData_0 = x_16;
    val x_17 = resetData_0;
    val x_18 = x_17.asInstanceOf[scala.Boolean];
    bindingMut_9 = x_18;
    positionVar_11 = 3
  }));
  data_12.update(3, (() => {
    val x_19 = bindingMut_9;
    val x_20 = x_19.asInstanceOf[scala.Boolean];
    val x_21 = x_20.`unary_!`;
    if (x_21)
      positionVar_11 = 4
    else
      positionVar_11 = 19
  }));
  data_12.update(4, (() => {
    val x_22 = 1.toDouble;
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[scala.Double];
    bindingMut_7 = x_24;
    resetData_0 = 0.0;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[scala.Double];
    bindingMut_6 = x_26;
    positionVar_11 = 5
  }));
  data_12.update(5, (() => {
    val x_27 = meta.deep.runtime.Actor.proceedLabel;
    val x_28 = x_27("turn");
    val x_29 = bindingMut_6;
    val x_30 = x_29.asInstanceOf[scala.Double];
    val x_31 = x_30.+(x_28);
    resetData_0 = x_31;
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[scala.Double];
    bindingMut_6 = x_33;
    val x_34 = meta.deep.runtime.Actor.labelVals("turn");
    val x_35 = bindingMut_6;
    val x_36 = x_35.asInstanceOf[scala.Double];
    val x_37 = bindingMut_7;
    val x_38 = x_37.asInstanceOf[scala.Double];
    val x_39 = x_38.-(x_36);
    x_34.append(x_39);
    resetData_0 = ();
    positionVar_11 = 6;
    val x_40 = currentTurn;
    val x_41 = x_40.+(1);
    currentTurn = x_41
  }));
  data_12.update(6, (() => {
    val x_42 = bindingMut_6;
    val x_43 = x_42.asInstanceOf[scala.Double];
    val x_44 = bindingMut_7;
    val x_45 = x_44.asInstanceOf[scala.Double];
    val x_46 = x_43.<(x_45);
    if (x_46)
      positionVar_11 = 5
    else
      positionVar_11 = 7
  }));
  data_12.update(7, (() => {
    val x_47 = bindingMut_6;
    val x_48 = x_47.asInstanceOf[scala.Double];
    val x_49 = bindingMut_7;
    val x_50 = x_49.asInstanceOf[scala.Double];
    val x_51 = x_48.<(x_50);
    val x_52 = x_51.`unary_!`;
    if (x_52)
      {
        val x_53 = this.popRequestMessages;
        val x_54 = x_53.iterator;
        iterMut_5 = x_54;
        positionVar_11 = 8
      }
    else
      ()
  }));
  data_12.update(8, (() => {
    val x_55 = iterMut_5;
    val x_56 = x_55.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_57 = x_56.hasNext;
    if (x_57)
      {
        val x_58 = iterMut_5;
        val x_59 = x_58.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_60 = x_59.next();
        listValMut_4 = x_60;
        positionVar_11 = 9
      }
    else
      positionVar_11 = 12
  }));
  data_12.update(9, (() => {
    val x_61 = listValMut_4;
    val x_62 = x_61.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_63 = x_62.methodId;
    val x_64 = x_63.==(0);
    val x_65 = x_64.`unary_!`;
    if (x_65)
      {
        val x_66 = listValMut_4;
        val x_67 = x_66.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_68 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_67);
        val x_69 = this.addReceiveMessages(x_68);
        resetData_0 = x_69;
        positionVar_11 = 10
      }
    else
      positionVar_11 = 11
  }));
  data_12.update(10, (() => positionVar_11 = 8));
  data_12.update(11, (() => {
    val x_70 = listValMut_4;
    val x_71 = x_70.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_72 = x_71.methodId;
    val x_73 = x_72.==(0);
    if (x_73)
      positionVar_11 = 1
    else
      ();
    val x_74 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_75 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_74, 15);
    val x_76 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_75);
    resetData_1.prepend(x_76)
  }));
  data_12.update(12, (() => {
    val x_77 = iterMut_5;
    val x_78 = x_77.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_79 = x_78.hasNext;
    val x_80 = x_79.`unary_!`;
    if (x_80)
      positionVar_11 = 13
    else
      ()
  }));
  data_12.update(13, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 14));
  data_12.update(14, (() => {
    val x_81 = true.`unary_!`;
    if (x_81)
      {
        val x_82 = resetData_1.remove(0);
        val x_86 = x_82.find(((x_83: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_84 = x_83._1;
          val x_85 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_84.==(x_85)
        }));
        val x_87 = x_86.get;
        val x_88 = x_87._2;
        positionVar_11 = x_88
      }
    else
      ()
  }));
  data_12.update(15, (() => {
    val x_89 = resetData_0;
    val x_90 = x_89.asInstanceOf[scala.Any];
    bindingMut_3 = x_90;
    val x_91 = bindingMut_3;
    val x_92 = x_91.asInstanceOf[scala.Any];
    val x_93 = listValMut_4;
    val x_94 = x_93.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_94.reply(this, x_92);
    resetData_0 = ();
    positionVar_11 = 10
  }));
  data_12.update(16, (() => positionVar_11 = 17));
  data_12.update(17, (() => {
    positionVar_11 = 18;
    val x_95 = currentTurn;
    val x_96 = x_95.+(1);
    currentTurn = x_96
  }));
  data_12.update(18, (() => positionVar_11 = 17));
  data_12.update(19, (() => {
    val x_97 = bindingMut_9;
    val x_98 = x_97.asInstanceOf[scala.Boolean];
    if (x_98)
      {
        scala.Predef.println("Object1 wins the game!");
        resetData_0 = ();
        val x_99 = this.own_monitor;
        resetData_0 = x_99;
        val x_100 = resetData_0;
        val x_101 = x_100.asInstanceOf[generated.meta.example.monitor_example.agent_monitor.monitorSim];
        bindingMut_8 = x_101;
        val x_102 = ((this): meta.deep.runtime.Actor).id;
        val x_104 = {
          val x_103 = bindingMut_8;
          x_103.asInstanceOf[generated.meta.example.monitor_example.agent_monitor.monitorSim]
        };
        val x_105 = x_104.id;
        val x_106 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_107 = meta.deep.runtime.RequestMessage.apply(x_102, x_105, 1, x_106);
        ((this): meta.deep.runtime.Actor).sendMessage(x_107);
        val x_108 = x_107.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_108, ((response_109: meta.deep.runtime.Message) => {
          val x_110 = response_109.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_110
        }));
        resetData_0 = null;
        positionVar_11 = 20
      }
    else
      ()
  }));
  data_12.update(20, (() => {
    positionVar_11 = 21;
    val x_111 = currentTurn;
    val x_112 = x_111.+(1);
    currentTurn = x_112
  }));
  data_12.update(21, (() => {
    val x_113 = resetData_2;
    val x_114 = x_113.==(null);
    if (x_114)
      {
        val x_115 = meta.deep.runtime.Actor.labelVals("turn");
        x_115.append(1.0);
        positionVar_11 = 20
      }
    else
      positionVar_11 = 22
  }));
  data_12.update(22, (() => {
    val x_116 = resetData_2;
    val x_117 = x_116.!=(null);
    if (x_117)
      {
        val x_118 = resetData_2;
        val x_119 = x_118.arg;
        resetData_0 = x_119;
        resetData_2 = null;
        positionVar_11 = 4
      }
    else
      ()
  }));
  data_12.update(23, (() => {
    val x_120 = true.`unary_!`;
    if (x_120)
      {
        val x_121 = resetData_1.remove(0);
        val x_125 = x_121.find(((x_122: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_123 = x_122._1;
          val x_124 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_123.==(x_124)
        }));
        val x_126 = x_125.get;
        val x_127 = x_126._2;
        positionVar_11 = x_127
      }
    else
      ()
  }));
  data_12
}).apply();
  
  override def run_until(until_129: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_130 = currentTurn;
      val x_131 = x_130.<=(until_129);
      x_131.&&({
        val x_132 = positionVar_11;
        val x_133 = commands_128.length;
        x_132.<(x_133)
      })
    }) 
      {
        val x_134 = positionVar_11;
        val x_135 = commands_128.apply(x_134);
        x_135.apply()
      }
    ;
    this
  }
}
