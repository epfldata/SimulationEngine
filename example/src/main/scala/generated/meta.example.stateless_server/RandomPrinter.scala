package generated.meta.example.stateless_server

class RandomPrinter () extends meta.deep.runtime.Actor {
  var server: generated.meta.example.stateless_server.RandomNumberServer = null
  var i: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Tuple2[scala.Int, scala.Int] = null;
  private var bindingMut_4: scala.Int = 0;
  private var bindingMut_5: generated.meta.example.stateless_server.RandomNumberServer = null;
  private var bindingMut_6: scala.Int = 0;
  private var bindingMut_7: scala.Any = null;
  private var listValMut_8: meta.deep.runtime.RequestMessage = null;
  private var iterMut_9: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: scala.Double = 0.0;
  private var positionVar_13: scala.Int = 0;
  
  val commands_164 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](26);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 18);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 25));
  data_14.update(2, (() => {
    positionVar_13 = 3;
    val x_18 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_19 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_18, 21);
    val x_20 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_19);
    resetData_1.prepend(x_20)
  }));
  data_14.update(3, (() => {
    val x_21 = this.i;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Int];
    bindingMut_6 = x_23;
    val x_24 = this.server;
    resetData_0 = x_24;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[generated.meta.example.stateless_server.RandomNumberServer];
    bindingMut_5 = x_26;
    val x_27 = ((this): meta.deep.runtime.Actor).id;
    val x_29 = {
      val x_28 = bindingMut_5;
      x_28.asInstanceOf[generated.meta.example.stateless_server.RandomNumberServer]
    };
    val x_30 = x_29.id;
    val x_31 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_32 = meta.deep.runtime.RequestMessage.apply(x_27, x_30, 2, x_31);
    ((this): meta.deep.runtime.Actor).sendMessage(x_32);
    val x_33 = x_32.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_33, ((response_34: meta.deep.runtime.Message) => {
      val x_35 = response_34.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_35
    }));
    resetData_0 = null;
    positionVar_13 = 4
  }));
  data_14.update(4, (() => {
    positionVar_13 = 5;
    val x_36 = currentTurn;
    val x_37 = x_36.+(1);
    currentTurn = x_37
  }));
  data_14.update(5, (() => {
    val x_38 = resetData_2;
    val x_39 = x_38.==(null);
    if (x_39)
      {
        val x_40 = meta.deep.runtime.Actor.labelVals("turn");
        x_40.append(1.0);
        positionVar_13 = 4
      }
    else
      positionVar_13 = 6
  }));
  data_14.update(6, (() => {
    val x_41 = resetData_2;
    val x_42 = x_41.!=(null);
    if (x_42)
      {
        val x_43 = resetData_2;
        val x_44 = x_43.arg;
        resetData_0 = x_44;
        resetData_2 = null;
        val x_45 = resetData_0;
        val x_46 = x_45.asInstanceOf[scala.Int];
        bindingMut_4 = x_46;
        val x_47 = bindingMut_4;
        val x_48 = x_47.asInstanceOf[scala.Int];
        val x_49 = bindingMut_6;
        val x_50 = x_49.asInstanceOf[scala.Int];
        val x_51 = scala.Tuple2.apply[scala.Int, scala.Int](x_50, x_48);
        resetData_0 = x_51;
        val x_52 = resetData_0;
        val x_53 = x_52.asInstanceOf[scala.Tuple2[scala.Int, scala.Int]];
        bindingMut_3 = x_53;
        val x_54 = bindingMut_3;
        val x_55 = x_54.asInstanceOf[scala.Tuple2[scala.Int, scala.Int]];
        scala.Predef.println(x_55);
        resetData_0 = ();
        val x_56 = resetData_1.remove(0);
        val x_60 = x_56.find(((x_57: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_58 = x_57._1;
          val x_59 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_58.==(x_59)
        }));
        val x_61 = x_60.get;
        val x_62 = x_61._2;
        positionVar_13 = x_62
      }
    else
      ()
  }));
  data_14.update(7, (() => {
    val x_63 = resetData_0;
    val x_64 = x_63.asInstanceOf[scala.Any];
    bindingMut_7 = x_64;
    val x_65 = bindingMut_7;
    val x_66 = x_65.asInstanceOf[scala.Any];
    val x_67 = listValMut_8;
    val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_68.reply(this, x_66);
    resetData_0 = ();
    positionVar_13 = 8
  }));
  data_14.update(8, (() => positionVar_13 = 9));
  data_14.update(9, (() => {
    val x_69 = iterMut_9;
    val x_70 = x_69.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_71 = x_70.hasNext;
    if (x_71)
      {
        val x_72 = iterMut_9;
        val x_73 = x_72.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_74 = x_73.next();
        listValMut_8 = x_74;
        positionVar_13 = 10
      }
    else
      positionVar_13 = 14
  }));
  data_14.update(10, (() => {
    val x_75 = listValMut_8;
    val x_76 = x_75.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_77 = x_76.methodId;
    val x_78 = x_77.==(0);
    val x_79 = x_78.`unary_!`;
    if (x_79)
      positionVar_13 = 11
    else
      positionVar_13 = 13
  }));
  data_14.update(11, (() => {
    val x_80 = listValMut_8;
    val x_81 = x_80.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_82 = x_81.methodId;
    val x_83 = x_82.==(1);
    val x_84 = x_83.`unary_!`;
    if (x_84)
      {
        val x_85 = listValMut_8;
        val x_86 = x_85.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_87 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_86);
        val x_88 = this.addReceiveMessages(x_87);
        resetData_0 = x_88;
        positionVar_13 = 8
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(12, (() => {
    val x_89 = listValMut_8;
    val x_90 = x_89.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_91 = x_90.methodId;
    val x_92 = x_91.==(1);
    if (x_92)
      positionVar_13 = 1
    else
      ();
    val x_93 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_94 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_93, 17);
    val x_95 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_94);
    resetData_1.prepend(x_95)
  }));
  data_14.update(13, (() => {
    val x_96 = listValMut_8;
    val x_97 = x_96.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_98 = x_97.methodId;
    val x_99 = x_98.==(0);
    if (x_99)
      positionVar_13 = 3
    else
      ();
    val x_100 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_101 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_100, 7);
    val x_102 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_101);
    resetData_1.prepend(x_102)
  }));
  data_14.update(14, (() => {
    val x_103 = iterMut_9;
    val x_104 = x_103.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_105 = x_104.hasNext;
    val x_106 = x_105.`unary_!`;
    if (x_106)
      positionVar_13 = 15
    else
      ()
  }));
  data_14.update(15, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 16));
  data_14.update(16, (() => {
    val x_107 = true.`unary_!`;
    if (x_107)
      {
        val x_108 = resetData_1.remove(0);
        val x_112 = x_108.find(((x_109: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_110 = x_109._1;
          val x_111 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_110.==(x_111)
        }));
        val x_113 = x_112.get;
        val x_114 = x_113._2;
        positionVar_13 = x_114
      }
    else
      ()
  }));
  data_14.update(17, (() => {
    val x_115 = resetData_0;
    val x_116 = x_115.asInstanceOf[scala.Any];
    bindingMut_7 = x_116;
    val x_117 = bindingMut_7;
    val x_118 = x_117.asInstanceOf[scala.Any];
    val x_119 = listValMut_8;
    val x_120 = x_119.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_120.reply(this, x_118);
    resetData_0 = ();
    positionVar_13 = 8
  }));
  data_14.update(18, (() => positionVar_13 = 19));
  data_14.update(19, (() => {
    positionVar_13 = 20;
    val x_121 = currentTurn;
    val x_122 = x_121.+(1);
    currentTurn = x_122
  }));
  data_14.update(20, (() => positionVar_13 = 19));
  data_14.update(21, (() => {
    val x_123 = 1.toDouble;
    resetData_0 = x_123;
    val x_124 = resetData_0;
    val x_125 = x_124.asInstanceOf[scala.Double];
    bindingMut_11 = x_125;
    resetData_0 = 0.0;
    val x_126 = resetData_0;
    val x_127 = x_126.asInstanceOf[scala.Double];
    bindingMut_10 = x_127;
    positionVar_13 = 22
  }));
  data_14.update(22, (() => {
    val x_128 = meta.deep.runtime.Actor.proceedLabel;
    val x_129 = x_128("turn");
    val x_130 = bindingMut_10;
    val x_131 = x_130.asInstanceOf[scala.Double];
    val x_132 = x_131.+(x_129);
    resetData_0 = x_132;
    val x_133 = resetData_0;
    val x_134 = x_133.asInstanceOf[scala.Double];
    bindingMut_10 = x_134;
    val x_135 = meta.deep.runtime.Actor.labelVals("turn");
    val x_136 = bindingMut_10;
    val x_137 = x_136.asInstanceOf[scala.Double];
    val x_138 = bindingMut_11;
    val x_139 = x_138.asInstanceOf[scala.Double];
    val x_140 = x_139.-(x_137);
    x_135.append(x_140);
    resetData_0 = ();
    positionVar_13 = 23;
    val x_141 = currentTurn;
    val x_142 = x_141.+(1);
    currentTurn = x_142
  }));
  data_14.update(23, (() => {
    val x_143 = bindingMut_10;
    val x_144 = x_143.asInstanceOf[scala.Double];
    val x_145 = bindingMut_11;
    val x_146 = x_145.asInstanceOf[scala.Double];
    val x_147 = x_144.<(x_146);
    if (x_147)
      positionVar_13 = 22
    else
      positionVar_13 = 24
  }));
  data_14.update(24, (() => {
    val x_148 = bindingMut_10;
    val x_149 = x_148.asInstanceOf[scala.Double];
    val x_150 = bindingMut_11;
    val x_151 = x_150.asInstanceOf[scala.Double];
    val x_152 = x_149.<(x_151);
    val x_153 = x_152.`unary_!`;
    if (x_153)
      {
        val x_154 = this.popRequestMessages;
        val x_155 = x_154.iterator;
        iterMut_9 = x_155;
        positionVar_13 = 9
      }
    else
      ()
  }));
  data_14.update(25, (() => {
    val x_156 = true.`unary_!`;
    if (x_156)
      {
        val x_157 = resetData_1.remove(0);
        val x_161 = x_157.find(((x_158: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_159 = x_158._1;
          val x_160 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_159.==(x_160)
        }));
        val x_162 = x_161.get;
        val x_163 = x_162._2;
        positionVar_13 = x_163
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_165: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_166 = currentTurn;
      val x_167 = x_166.<=(until_165);
      x_167.&&({
        val x_168 = positionVar_13;
        val x_169 = commands_164.length;
        x_168.<(x_169)
      })
    }) 
      {
        val x_170 = positionVar_13;
        val x_171 = commands_164.apply(x_170);
        x_171.apply()
      }
    ;
    this
  }
}
