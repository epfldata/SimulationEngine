package generated.meta.example.server_communication_merged

class FrontendServer () extends meta.deep.runtime.Actor with Serializable {
  var backendServer: generated.meta.example.server_communication_merged.BackendServer = null
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: generated.meta.example.server_communication_merged.BackendServer = null;
  private var bindingMut_7: scala.Any = null;
  private var listValMut_8: meta.deep.runtime.RequestMessage = null;
  private var iterMut_9: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var positionVar_14: scala.Int = 0;
  
  val commands_157 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](26);
  data_15.update(0, (() => {
    positionVar_14 = 1;
    val x_16 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_17 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_16, 18);
    val x_18 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_17);
    resetData_1.prepend(x_18)
  }));
  data_15.update(1, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 25));
  data_15.update(2, (() => {
    positionVar_14 = 3;
    val x_19 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_20 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_19, 21);
    val x_21 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_20);
    resetData_1.prepend(x_21)
  }));
  data_15.update(3, (() => {
    val x_22 = this.backendServer;
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[generated.meta.example.server_communication_merged.BackendServer];
    bindingMut_6 = x_24;
    val x_25 = ((this): meta.deep.runtime.Actor).id;
    val x_27 = {
      val x_26 = bindingMut_6;
      x_26.asInstanceOf[generated.meta.example.server_communication_merged.BackendServer]
    };
    val x_28 = x_27.id;
    val x_29 = meta.deep.runtime.RequestMessage.apply(x_25, x_28, 0, scala.collection.immutable.Nil);
    ((this): meta.deep.runtime.Actor).sendMessage(x_29);
    val x_30 = x_29.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_30, ((response_31: meta.deep.runtime.Message) => {
      val x_32 = response_31.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_32
    }));
    resetData_0 = null;
    positionVar_14 = 4
  }));
  data_15.update(4, (() => {
    positionVar_14 = 5;
    val x_33 = currentTurn;
    val x_34 = x_33.+(1);
    currentTurn = x_34
  }));
  data_15.update(5, (() => {
    val x_35 = resetData_2;
    val x_36 = x_35.==(null);
    if (x_36)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_14 = 4
      }
    else
      positionVar_14 = 6
  }));
  data_15.update(6, (() => {
    val x_37 = resetData_2;
    val x_38 = x_37.!=(null);
    if (x_38)
      {
        val x_39 = resetData_2;
        val x_40 = x_39.arg;
        resetData_0 = x_40;
        resetData_2 = null;
        val x_41 = resetData_0;
        val x_42 = x_41.asInstanceOf[java.lang.String];
        bindingMut_5 = x_42;
        val x_43 = bindingMut_5;
        val x_44 = x_43.asInstanceOf[java.lang.String];
        val x_45 = "<html>".+(x_44);
        resetData_0 = x_45;
        val x_46 = resetData_0;
        val x_47 = x_46.asInstanceOf[java.lang.String];
        bindingMut_4 = x_47;
        val x_48 = bindingMut_4;
        val x_49 = x_48.asInstanceOf[java.lang.String];
        val x_50 = x_49.+("</html>");
        resetData_0 = x_50;
        val x_51 = resetData_1.remove(0);
        val x_55 = x_51.find(((x_52: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_53 = x_52._1;
          val x_54 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_53.==(x_54)
        }));
        val x_56 = x_55.get;
        val x_57 = x_56._2;
        positionVar_14 = x_57
      }
    else
      ()
  }));
  data_15.update(7, (() => {
    val x_58 = resetData_0;
    val x_59 = x_58.asInstanceOf[scala.Any];
    bindingMut_7 = x_59;
    val x_60 = bindingMut_7;
    val x_61 = x_60.asInstanceOf[scala.Any];
    val x_62 = listValMut_8;
    val x_63 = x_62.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_63.reply(this, x_61);
    resetData_0 = ();
    positionVar_14 = 8
  }));
  data_15.update(8, (() => positionVar_14 = 9));
  data_15.update(9, (() => {
    val x_64 = iterMut_9;
    val x_65 = x_64.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_66 = x_65.hasNext;
    if (x_66)
      {
        val x_67 = iterMut_9;
        val x_68 = x_67.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_69 = x_68.next();
        listValMut_8 = x_69;
        positionVar_14 = 10
      }
    else
      positionVar_14 = 14
  }));
  data_15.update(10, (() => {
    val x_70 = listValMut_8;
    val x_71 = x_70.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_72 = x_71.methodId;
    val x_73 = x_72.==(2);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      positionVar_14 = 11
    else
      positionVar_14 = 13
  }));
  data_15.update(11, (() => {
    val x_75 = listValMut_8;
    val x_76 = x_75.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_77 = x_76.methodId;
    val x_78 = x_77.==(3);
    val x_79 = x_78.`unary_!`;
    if (x_79)
      {
        val x_80 = listValMut_8;
        val x_81 = x_80.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_82 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_81);
        val x_83 = this.addReceiveMessages(x_82);
        resetData_0 = x_83;
        positionVar_14 = 8
      }
    else
      positionVar_14 = 12
  }));
  data_15.update(12, (() => {
    val x_84 = listValMut_8;
    val x_85 = x_84.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_86 = x_85.methodId;
    val x_87 = x_86.==(3);
    if (x_87)
      positionVar_14 = 1
    else
      ();
    val x_88 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_89 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_88, 17);
    val x_90 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_89);
    resetData_1.prepend(x_90)
  }));
  data_15.update(13, (() => {
    val x_91 = listValMut_8;
    val x_92 = x_91.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_93 = x_92.methodId;
    val x_94 = x_93.==(2);
    if (x_94)
      positionVar_14 = 3
    else
      ();
    val x_95 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_96 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_95, 7);
    val x_97 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_96);
    resetData_1.prepend(x_97)
  }));
  data_15.update(14, (() => {
    val x_98 = iterMut_9;
    val x_99 = x_98.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_100 = x_99.hasNext;
    val x_101 = x_100.`unary_!`;
    if (x_101)
      positionVar_14 = 15
    else
      ()
  }));
  data_15.update(15, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 16));
  data_15.update(16, (() => {
    val x_102 = true.`unary_!`;
    if (x_102)
      {
        val x_103 = resetData_1.remove(0);
        val x_107 = x_103.find(((x_104: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_105 = x_104._1;
          val x_106 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_105.==(x_106)
        }));
        val x_108 = x_107.get;
        val x_109 = x_108._2;
        positionVar_14 = x_109
      }
    else
      ()
  }));
  data_15.update(17, (() => {
    val x_110 = resetData_0;
    val x_111 = x_110.asInstanceOf[scala.Any];
    bindingMut_7 = x_111;
    val x_112 = bindingMut_7;
    val x_113 = x_112.asInstanceOf[scala.Any];
    val x_114 = listValMut_8;
    val x_115 = x_114.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_115.reply(this, x_113);
    resetData_0 = ();
    positionVar_14 = 8
  }));
  data_15.update(18, (() => positionVar_14 = 19));
  data_15.update(19, (() => {
    positionVar_14 = 20;
    val x_116 = currentTurn;
    val x_117 = x_116.+(1);
    currentTurn = x_117
  }));
  data_15.update(20, (() => positionVar_14 = 19));
  data_15.update(21, (() => {
    val x_118 = resetData_0;
    val x_119 = x_118.asInstanceOf[java.lang.String];
    bindingMut_12 = x_119;
    val x_120 = bindingMut_12;
    val x_121 = x_120.asInstanceOf[java.lang.String];
    val x_122 = "requestPage content is ".+(x_121);
    resetData_0 = x_122;
    val x_123 = resetData_0;
    val x_124 = x_123.asInstanceOf[java.lang.String];
    bindingMut_11 = x_124;
    val x_125 = bindingMut_11;
    val x_126 = x_125.asInstanceOf[java.lang.String];
    scala.Predef.println(x_126);
    resetData_0 = ();
    resetData_0 = 0;
    val x_127 = resetData_0;
    val x_128 = x_127.asInstanceOf[scala.Int];
    bindingMut_10 = x_128;
    positionVar_14 = 22
  }));
  data_15.update(22, (() => {
    val x_129 = bindingMut_10;
    val x_130 = x_129.asInstanceOf[scala.Int];
    val x_131 = (1).-(x_130);
    meta.deep.runtime.Actor.waitTurnList.append(x_131);
    resetData_0 = ();
    val x_132 = meta.deep.runtime.Actor.minTurn();
    val x_133 = bindingMut_10;
    val x_134 = x_133.asInstanceOf[scala.Int];
    val x_135 = x_134.+(x_132);
    resetData_0 = x_135;
    val x_136 = resetData_0;
    val x_137 = x_136.asInstanceOf[scala.Int];
    bindingMut_10 = x_137;
    positionVar_14 = 23;
    val x_138 = currentTurn;
    val x_139 = x_138.+(1);
    currentTurn = x_139
  }));
  data_15.update(23, (() => {
    val x_140 = bindingMut_10;
    val x_141 = x_140.asInstanceOf[scala.Int];
    val x_142 = x_141.<(1);
    if (x_142)
      positionVar_14 = 22
    else
      positionVar_14 = 24
  }));
  data_15.update(24, (() => {
    val x_143 = bindingMut_10;
    val x_144 = x_143.asInstanceOf[scala.Int];
    val x_145 = x_144.<(1);
    val x_146 = x_145.`unary_!`;
    if (x_146)
      {
        val x_147 = this.popRequestMessages;
        val x_148 = x_147.iterator;
        iterMut_9 = x_148;
        positionVar_14 = 9
      }
    else
      ()
  }));
  data_15.update(25, (() => {
    val x_149 = true.`unary_!`;
    if (x_149)
      {
        val x_150 = resetData_1.remove(0);
        val x_154 = x_150.find(((x_151: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_152 = x_151._1;
          val x_153 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_152.==(x_153)
        }));
        val x_155 = x_154.get;
        val x_156 = x_155._2;
        positionVar_14 = x_156
      }
    else
      ()
  }));
  data_15
}).apply();
  
  override def run_until(until_158: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_159 = currentTurn;
      val x_160 = x_159.<=(until_158);
      x_160.&&({
        val x_161 = positionVar_14;
        val x_162 = commands_157.length;
        x_161.<(x_162)
      })
    }) 
      {
        val x_163 = positionVar_14;
        val x_164 = commands_157.apply(x_163);
        x_164.apply()
      }
    ;
    this
  }
}
