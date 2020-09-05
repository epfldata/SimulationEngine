package generated.meta.example.monitor_example.agent_monitor

class object1 (var own_monitor: generated.meta.example.monitor_example.agent_monitor.monitorSim) extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: generated.meta.example.monitor_example.agent_monitor.monitorSim = null;
  private var bindingMut_9: scala.Boolean = false;
  private var positionVar_11: scala.Int = 0;
  
  val commands_116 = (() => {
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
    resetData_0 = 0;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Int];
    bindingMut_7 = x_23;
    positionVar_11 = 5
  }));
  data_12.update(5, (() => {
    val x_24 = bindingMut_7;
    val x_25 = x_24.asInstanceOf[scala.Int];
    val x_26 = (1).-(x_25);
    meta.deep.runtime.Actor.waitTurnList.append(x_26);
    resetData_0 = ();
    val x_27 = meta.deep.runtime.Actor.minTurn();
    val x_28 = bindingMut_7;
    val x_29 = x_28.asInstanceOf[scala.Int];
    val x_30 = x_29.+(x_27);
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[scala.Int];
    bindingMut_7 = x_32;
    positionVar_11 = 6;
    val x_33 = currentTurn;
    val x_34 = x_33.+(1);
    currentTurn = x_34
  }));
  data_12.update(6, (() => {
    val x_35 = bindingMut_7;
    val x_36 = x_35.asInstanceOf[scala.Int];
    val x_37 = x_36.<(1);
    if (x_37)
      positionVar_11 = 5
    else
      positionVar_11 = 7
  }));
  data_12.update(7, (() => {
    val x_38 = bindingMut_7;
    val x_39 = x_38.asInstanceOf[scala.Int];
    val x_40 = x_39.<(1);
    val x_41 = x_40.`unary_!`;
    if (x_41)
      {
        val x_42 = this.popRequestMessages;
        val x_43 = x_42.iterator;
        iterMut_6 = x_43;
        positionVar_11 = 8
      }
    else
      ()
  }));
  data_12.update(8, (() => {
    val x_44 = iterMut_6;
    val x_45 = x_44.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_46 = x_45.hasNext;
    if (x_46)
      {
        val x_47 = iterMut_6;
        val x_48 = x_47.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_49 = x_48.next();
        listValMut_5 = x_49;
        positionVar_11 = 9
      }
    else
      positionVar_11 = 12
  }));
  data_12.update(9, (() => {
    val x_50 = listValMut_5;
    val x_51 = x_50.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_52 = x_51.methodId;
    val x_53 = x_52.==(0);
    val x_54 = x_53.`unary_!`;
    if (x_54)
      {
        val x_55 = listValMut_5;
        val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_57 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_56);
        val x_58 = this.addReceiveMessages(x_57);
        resetData_0 = x_58;
        positionVar_11 = 10
      }
    else
      positionVar_11 = 11
  }));
  data_12.update(10, (() => positionVar_11 = 8));
  data_12.update(11, (() => {
    val x_59 = listValMut_5;
    val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_61 = x_60.methodId;
    val x_62 = x_61.==(0);
    if (x_62)
      positionVar_11 = 1
    else
      ();
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 15);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_12.update(12, (() => {
    val x_66 = iterMut_6;
    val x_67 = x_66.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_68 = x_67.hasNext;
    val x_69 = x_68.`unary_!`;
    if (x_69)
      positionVar_11 = 13
    else
      ()
  }));
  data_12.update(13, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 14));
  data_12.update(14, (() => {
    val x_70 = true.`unary_!`;
    if (x_70)
      {
        val x_71 = resetData_1.remove(0);
        val x_75 = x_71.find(((x_72: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_73 = x_72._1;
          val x_74 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_73.==(x_74)
        }));
        val x_76 = x_75.get;
        val x_77 = x_76._2;
        positionVar_11 = x_77
      }
    else
      ()
  }));
  data_12.update(15, (() => {
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[scala.Any];
    bindingMut_4 = x_79;
    val x_80 = bindingMut_4;
    val x_81 = x_80.asInstanceOf[scala.Any];
    val x_82 = listValMut_5;
    val x_83 = x_82.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_83.reply(this, x_81);
    resetData_0 = ();
    positionVar_11 = 10
  }));
  data_12.update(16, (() => positionVar_11 = 17));
  data_12.update(17, (() => {
    positionVar_11 = 18;
    val x_84 = currentTurn;
    val x_85 = x_84.+(1);
    currentTurn = x_85
  }));
  data_12.update(18, (() => positionVar_11 = 17));
  data_12.update(19, (() => {
    val x_86 = bindingMut_9;
    val x_87 = x_86.asInstanceOf[scala.Boolean];
    if (x_87)
      {
        scala.Predef.println("Object1 wins the game!");
        resetData_0 = ();
        val x_88 = this.own_monitor;
        resetData_0 = x_88;
        val x_89 = resetData_0;
        val x_90 = x_89.asInstanceOf[generated.meta.example.monitor_example.agent_monitor.monitorSim];
        bindingMut_8 = x_90;
        val x_91 = ((this): meta.deep.runtime.Actor).id;
        val x_93 = {
          val x_92 = bindingMut_8;
          x_92.asInstanceOf[generated.meta.example.monitor_example.agent_monitor.monitorSim]
        };
        val x_94 = x_93.id;
        val x_95 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_96 = meta.deep.runtime.RequestMessage.apply(x_91, x_94, 1, x_95);
        ((this): meta.deep.runtime.Actor).sendMessage(x_96);
        val x_97 = x_96.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_97, ((response_98: meta.deep.runtime.Message) => {
          val x_99 = response_98.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_99
        }));
        resetData_0 = null;
        positionVar_11 = 20
      }
    else
      ()
  }));
  data_12.update(20, (() => {
    positionVar_11 = 21;
    val x_100 = currentTurn;
    val x_101 = x_100.+(1);
    currentTurn = x_101
  }));
  data_12.update(21, (() => {
    val x_102 = resetData_2;
    val x_103 = x_102.==(null);
    if (x_103)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_11 = 20
      }
    else
      positionVar_11 = 22
  }));
  data_12.update(22, (() => {
    val x_104 = resetData_2;
    val x_105 = x_104.!=(null);
    if (x_105)
      {
        val x_106 = resetData_2;
        val x_107 = x_106.arg;
        resetData_0 = x_107;
        resetData_2 = null;
        positionVar_11 = 4
      }
    else
      ()
  }));
  data_12.update(23, (() => {
    val x_108 = true.`unary_!`;
    if (x_108)
      {
        val x_109 = resetData_1.remove(0);
        val x_113 = x_109.find(((x_110: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_111 = x_110._1;
          val x_112 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_111.==(x_112)
        }));
        val x_114 = x_113.get;
        val x_115 = x_114._2;
        positionVar_11 = x_115
      }
    else
      ()
  }));
  data_12
}).apply();
  
  override def run_until(until_117: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_118 = currentTurn;
      val x_119 = x_118.<=(until_117);
      x_119.&&({
        val x_120 = positionVar_11;
        val x_121 = commands_116.length;
        x_120.<(x_121)
      })
    }) 
      {
        val x_122 = positionVar_11;
        val x_123 = commands_116.apply(x_122);
        x_123.apply()
      }
    ;
    this
  }
}
