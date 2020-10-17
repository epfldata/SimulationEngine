package generated.meta.example.server_communication_merged

class FrontendServer () extends meta.deep.runtime.Actor {
  var backendServer: generated.meta.example.server_communication_merged.BackendServer = null
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: java.lang.String = null;
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: generated.meta.example.server_communication_merged.BackendServer = null;
  private var bindingMut_6: scala.Any = null;
  private var listValMut_7: meta.deep.runtime.RequestMessage = null;
  private var iterMut_8: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var positionVar_14: scala.Int = 0;
  
  val commands_169 = (() => {
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
    bindingMut_5 = x_24;
    val x_25 = ((this): meta.deep.runtime.Actor).id;
    val x_27 = {
      val x_26 = bindingMut_5;
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
        val x_37 = meta.deep.runtime.Actor.labelVals("turn");
        x_37.append(1.0);
        positionVar_14 = 4
      }
    else
      positionVar_14 = 6
  }));
  data_15.update(6, (() => {
    val x_38 = resetData_2;
    val x_39 = x_38.!=(null);
    if (x_39)
      {
        val x_40 = resetData_2;
        val x_41 = x_40.arg;
        resetData_0 = x_41;
        resetData_2 = null;
        val x_42 = resetData_0;
        val x_43 = x_42.asInstanceOf[java.lang.String];
        bindingMut_4 = x_43;
        val x_44 = bindingMut_4;
        val x_45 = x_44.asInstanceOf[java.lang.String];
        val x_46 = "<html>".+(x_45);
        resetData_0 = x_46;
        val x_47 = resetData_0;
        val x_48 = x_47.asInstanceOf[java.lang.String];
        bindingMut_3 = x_48;
        val x_49 = bindingMut_3;
        val x_50 = x_49.asInstanceOf[java.lang.String];
        val x_51 = x_50.+("</html>");
        resetData_0 = x_51;
        val x_52 = resetData_1.remove(0);
        val x_56 = x_52.find(((x_53: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_54 = x_53._1;
          val x_55 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_54.==(x_55)
        }));
        val x_57 = x_56.get;
        val x_58 = x_57._2;
        positionVar_14 = x_58
      }
    else
      ()
  }));
  data_15.update(7, (() => {
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[scala.Any];
    bindingMut_6 = x_60;
    val x_61 = bindingMut_6;
    val x_62 = x_61.asInstanceOf[scala.Any];
    val x_63 = listValMut_7;
    val x_64 = x_63.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_64.reply(this, x_62);
    resetData_0 = ();
    positionVar_14 = 8
  }));
  data_15.update(8, (() => positionVar_14 = 9));
  data_15.update(9, (() => {
    val x_65 = iterMut_8;
    val x_66 = x_65.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_67 = x_66.hasNext;
    if (x_67)
      {
        val x_68 = iterMut_8;
        val x_69 = x_68.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_70 = x_69.next();
        listValMut_7 = x_70;
        positionVar_14 = 10
      }
    else
      positionVar_14 = 14
  }));
  data_15.update(10, (() => {
    val x_71 = listValMut_7;
    val x_72 = x_71.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_73 = x_72.methodId;
    val x_74 = x_73.==(2);
    val x_75 = x_74.`unary_!`;
    if (x_75)
      positionVar_14 = 11
    else
      positionVar_14 = 13
  }));
  data_15.update(11, (() => {
    val x_76 = listValMut_7;
    val x_77 = x_76.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_78 = x_77.methodId;
    val x_79 = x_78.==(3);
    val x_80 = x_79.`unary_!`;
    if (x_80)
      {
        val x_81 = listValMut_7;
        val x_82 = x_81.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_83 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_82);
        val x_84 = this.addReceiveMessages(x_83);
        resetData_0 = x_84;
        positionVar_14 = 8
      }
    else
      positionVar_14 = 12
  }));
  data_15.update(12, (() => {
    val x_85 = listValMut_7;
    val x_86 = x_85.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_87 = x_86.methodId;
    val x_88 = x_87.==(3);
    if (x_88)
      positionVar_14 = 1
    else
      ();
    val x_89 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_90 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_89, 17);
    val x_91 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_90);
    resetData_1.prepend(x_91)
  }));
  data_15.update(13, (() => {
    val x_92 = listValMut_7;
    val x_93 = x_92.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_94 = x_93.methodId;
    val x_95 = x_94.==(2);
    if (x_95)
      positionVar_14 = 3
    else
      ();
    val x_96 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_97 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_96, 7);
    val x_98 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_97);
    resetData_1.prepend(x_98)
  }));
  data_15.update(14, (() => {
    val x_99 = iterMut_8;
    val x_100 = x_99.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_101 = x_100.hasNext;
    val x_102 = x_101.`unary_!`;
    if (x_102)
      positionVar_14 = 15
    else
      ()
  }));
  data_15.update(15, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 16));
  data_15.update(16, (() => {
    val x_103 = true.`unary_!`;
    if (x_103)
      {
        val x_104 = resetData_1.remove(0);
        val x_108 = x_104.find(((x_105: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_106 = x_105._1;
          val x_107 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_106.==(x_107)
        }));
        val x_109 = x_108.get;
        val x_110 = x_109._2;
        positionVar_14 = x_110
      }
    else
      ()
  }));
  data_15.update(17, (() => {
    val x_111 = resetData_0;
    val x_112 = x_111.asInstanceOf[scala.Any];
    bindingMut_6 = x_112;
    val x_113 = bindingMut_6;
    val x_114 = x_113.asInstanceOf[scala.Any];
    val x_115 = listValMut_7;
    val x_116 = x_115.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_116.reply(this, x_114);
    resetData_0 = ();
    positionVar_14 = 8
  }));
  data_15.update(18, (() => positionVar_14 = 19));
  data_15.update(19, (() => {
    positionVar_14 = 20;
    val x_117 = currentTurn;
    val x_118 = x_117.+(1);
    currentTurn = x_118
  }));
  data_15.update(20, (() => positionVar_14 = 19));
  data_15.update(21, (() => {
    val x_119 = resetData_0;
    val x_120 = x_119.asInstanceOf[java.lang.String];
    bindingMut_12 = x_120;
    val x_121 = bindingMut_12;
    val x_122 = x_121.asInstanceOf[java.lang.String];
    val x_123 = "requestPage content is ".+(x_122);
    resetData_0 = x_123;
    val x_124 = resetData_0;
    val x_125 = x_124.asInstanceOf[java.lang.String];
    bindingMut_11 = x_125;
    val x_126 = bindingMut_11;
    val x_127 = x_126.asInstanceOf[java.lang.String];
    scala.Predef.println(x_127);
    resetData_0 = ();
    val x_128 = 1.toDouble;
    resetData_0 = x_128;
    val x_129 = resetData_0;
    val x_130 = x_129.asInstanceOf[scala.Double];
    bindingMut_10 = x_130;
    resetData_0 = 0.0;
    val x_131 = resetData_0;
    val x_132 = x_131.asInstanceOf[scala.Double];
    bindingMut_9 = x_132;
    positionVar_14 = 22
  }));
  data_15.update(22, (() => {
    val x_133 = meta.deep.runtime.Actor.proceedLabel;
    val x_134 = x_133("turn");
    val x_135 = bindingMut_9;
    val x_136 = x_135.asInstanceOf[scala.Double];
    val x_137 = x_136.+(x_134);
    resetData_0 = x_137;
    val x_138 = resetData_0;
    val x_139 = x_138.asInstanceOf[scala.Double];
    bindingMut_9 = x_139;
    val x_140 = meta.deep.runtime.Actor.labelVals("turn");
    val x_141 = bindingMut_9;
    val x_142 = x_141.asInstanceOf[scala.Double];
    val x_143 = bindingMut_10;
    val x_144 = x_143.asInstanceOf[scala.Double];
    val x_145 = x_144.-(x_142);
    x_140.append(x_145);
    resetData_0 = ();
    positionVar_14 = 23;
    val x_146 = currentTurn;
    val x_147 = x_146.+(1);
    currentTurn = x_147
  }));
  data_15.update(23, (() => {
    val x_148 = bindingMut_9;
    val x_149 = x_148.asInstanceOf[scala.Double];
    val x_150 = bindingMut_10;
    val x_151 = x_150.asInstanceOf[scala.Double];
    val x_152 = x_149.<(x_151);
    if (x_152)
      positionVar_14 = 22
    else
      positionVar_14 = 24
  }));
  data_15.update(24, (() => {
    val x_153 = bindingMut_9;
    val x_154 = x_153.asInstanceOf[scala.Double];
    val x_155 = bindingMut_10;
    val x_156 = x_155.asInstanceOf[scala.Double];
    val x_157 = x_154.<(x_156);
    val x_158 = x_157.`unary_!`;
    if (x_158)
      {
        val x_159 = this.popRequestMessages;
        val x_160 = x_159.iterator;
        iterMut_8 = x_160;
        positionVar_14 = 9
      }
    else
      ()
  }));
  data_15.update(25, (() => {
    val x_161 = true.`unary_!`;
    if (x_161)
      {
        val x_162 = resetData_1.remove(0);
        val x_166 = x_162.find(((x_163: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_164 = x_163._1;
          val x_165 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_164.==(x_165)
        }));
        val x_167 = x_166.get;
        val x_168 = x_167._2;
        positionVar_14 = x_168
      }
    else
      ()
  }));
  data_15
}).apply();
  
  override def run_until(until_170: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_171 = currentTurn;
      val x_172 = x_171.<=(until_170);
      x_172.&&({
        val x_173 = positionVar_14;
        val x_174 = commands_169.length;
        x_173.<(x_174)
      })
    }) 
      {
        val x_175 = positionVar_14;
        val x_176 = commands_169.apply(x_175);
        x_176.apply()
      }
    ;
    this
  }
}
