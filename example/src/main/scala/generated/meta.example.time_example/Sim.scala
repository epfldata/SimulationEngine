package generated.meta.example.time_example

class Sim (var time: Double) extends meta.deep.runtime.Actor with Serializable {
  var delay: Int = 5
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: scala.Long = 0L;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Long = 0L;
  private var bindingMut_13: java.lang.String = null;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: scala.Double = 0.0;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: java.lang.String = null;
  private var bindingMut_18: scala.Long = 0L;
  private var bindingMut_19: scala.Boolean = false;
  private var bindingMut_20: scala.Double = 0.0;
  private var bindingMut_21: scala.Int = 0;
  private var positionVar_23: scala.Int = 0;
  
  val commands_213 = (() => {
  val data_24 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_24.update(0, (() => {
    positionVar_23 = 1;
    val x_25 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_26 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_25, 19);
    val x_27 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_26);
    resetData_1.prepend(x_27)
  }));
  data_24.update(1, (() => {
    val x_28 = this.delay;
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Int];
    bindingMut_21 = x_30;
    val x_31 = bindingMut_21;
    val x_32 = x_31.asInstanceOf[scala.Int];
    val x_33 = x_32.toDouble;
    resetData_0 = x_33;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Double];
    bindingMut_20 = x_35;
    val x_36 = ((this): meta.deep.runtime.Actor).id;
    val x_37 = ((this): meta.deep.runtime.Actor).id;
    val x_38 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_39 = meta.deep.runtime.RequestMessage.apply(x_36, x_37, 0, x_38);
    val x_40 = bindingMut_20;
    val x_41 = x_40.asInstanceOf[scala.Double];
    ((this): meta.deep.runtime.Actor).registerInterrupt(x_41, x_39);
    resetData_0 = null;
    positionVar_23 = 2
  }));
  data_24.update(2, (() => if (true)
    positionVar_23 = 3
  else
    positionVar_23 = 26));
  data_24.update(3, (() => {
    val x_42 = scala.util.Random.nextBoolean();
    resetData_0 = x_42;
    val x_43 = resetData_0;
    val x_44 = x_43.asInstanceOf[scala.Boolean];
    bindingMut_19 = x_44;
    positionVar_23 = 4
  }));
  data_24.update(4, (() => {
    val x_45 = bindingMut_19;
    val x_46 = x_45.asInstanceOf[scala.Boolean];
    if (x_46)
      {
        val x_47 = this.id;
        resetData_0 = x_47;
        val x_48 = resetData_0;
        val x_49 = x_48.asInstanceOf[scala.Long];
        bindingMut_18 = x_49;
        val x_50 = bindingMut_18;
        val x_51 = x_50.asInstanceOf[scala.Long];
        val x_52 = "Wait turn!  Id ".+(x_51);
        resetData_0 = x_52;
        val x_53 = resetData_0;
        val x_54 = x_53.asInstanceOf[java.lang.String];
        bindingMut_17 = x_54;
        val x_55 = bindingMut_17;
        val x_56 = x_55.asInstanceOf[java.lang.String];
        scala.Predef.println(x_56);
        resetData_0 = ();
        resetData_0 = 0;
        val x_57 = resetData_0;
        val x_58 = x_57.asInstanceOf[scala.Int];
        bindingMut_16 = x_58;
        positionVar_23 = 5
      }
    else
      positionVar_23 = 22
  }));
  data_24.update(5, (() => {
    val x_59 = bindingMut_16;
    val x_60 = x_59.asInstanceOf[scala.Int];
    val x_61 = (1).-(x_60);
    meta.deep.runtime.Actor.waitTurnList.append(x_61);
    resetData_0 = ();
    val x_62 = meta.deep.runtime.Actor.minTurn();
    val x_63 = bindingMut_16;
    val x_64 = x_63.asInstanceOf[scala.Int];
    val x_65 = x_64.+(x_62);
    resetData_0 = x_65;
    val x_66 = resetData_0;
    val x_67 = x_66.asInstanceOf[scala.Int];
    bindingMut_16 = x_67;
    positionVar_23 = 6;
    val x_68 = currentTurn;
    val x_69 = x_68.+(1);
    currentTurn = x_69
  }));
  data_24.update(6, (() => {
    val x_70 = bindingMut_16;
    val x_71 = x_70.asInstanceOf[scala.Int];
    val x_72 = x_71.<(1);
    if (x_72)
      positionVar_23 = 5
    else
      positionVar_23 = 7
  }));
  data_24.update(7, (() => {
    val x_73 = bindingMut_16;
    val x_74 = x_73.asInstanceOf[scala.Int];
    val x_75 = x_74.<(1);
    val x_76 = x_75.`unary_!`;
    if (x_76)
      positionVar_23 = 8
    else
      ()
  }));
  data_24.update(8, (() => {
    val x_77 = this.popRequestMessages;
    val x_78 = x_77.iterator;
    iterMut_6 = x_78;
    positionVar_23 = 9
  }));
  data_24.update(9, (() => {
    val x_79 = iterMut_6;
    val x_80 = x_79.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_81 = x_80.hasNext;
    if (x_81)
      {
        val x_82 = iterMut_6;
        val x_83 = x_82.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_84 = x_83.next();
        listValMut_5 = x_84;
        positionVar_23 = 10
      }
    else
      positionVar_23 = 15
  }));
  data_24.update(10, (() => {
    val x_85 = listValMut_5;
    val x_86 = x_85.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_87 = x_86.methodId;
    val x_88 = x_87.==(0);
    val x_89 = x_88.`unary_!`;
    if (x_89)
      positionVar_23 = 11
    else
      positionVar_23 = 14
  }));
  data_24.update(11, (() => {
    val x_90 = listValMut_5;
    val x_91 = x_90.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_92 = x_91.methodId;
    val x_93 = x_92.==(1);
    val x_94 = x_93.`unary_!`;
    if (x_94)
      {
        val x_95 = listValMut_5;
        val x_96 = x_95.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_97 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_96);
        val x_98 = this.addReceiveMessages(x_97);
        resetData_0 = x_98;
        positionVar_23 = 12
      }
    else
      positionVar_23 = 13
  }));
  data_24.update(12, (() => positionVar_23 = 9));
  data_24.update(13, (() => {
    val x_99 = listValMut_5;
    val x_100 = x_99.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_101 = x_100.methodId;
    val x_102 = x_101.==(1);
    if (x_102)
      positionVar_23 = 1
    else
      ();
    val x_103 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_104 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_103, 18);
    val x_105 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_104);
    resetData_1.prepend(x_105)
  }));
  data_24.update(14, (() => {
    val x_106 = listValMut_5;
    val x_107 = x_106.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_108 = x_107.methodId;
    val x_109 = x_108.==(0);
    if (x_109)
      {
        scala.Predef.println("Time is up!");
        resetData_0 = ();
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Any];
        bindingMut_4 = x_111;
        val x_112 = bindingMut_4;
        val x_113 = x_112.asInstanceOf[scala.Any];
        val x_114 = listValMut_5;
        val x_115 = x_114.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_115.reply(this, x_113);
        resetData_0 = ();
        positionVar_23 = 12
      }
    else
      ()
  }));
  data_24.update(15, (() => {
    val x_116 = iterMut_6;
    val x_117 = x_116.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_118 = x_117.hasNext;
    val x_119 = x_118.`unary_!`;
    if (x_119)
      positionVar_23 = 16
    else
      ()
  }));
  data_24.update(16, (() => if (true)
    positionVar_23 = 3
  else
    positionVar_23 = 17));
  data_24.update(17, (() => {
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
        positionVar_23 = x_127
      }
    else
      ()
  }));
  data_24.update(18, (() => {
    val x_128 = resetData_0;
    val x_129 = x_128.asInstanceOf[scala.Any];
    bindingMut_4 = x_129;
    val x_130 = bindingMut_4;
    val x_131 = x_130.asInstanceOf[scala.Any];
    val x_132 = listValMut_5;
    val x_133 = x_132.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_133.reply(this, x_131);
    resetData_0 = ();
    positionVar_23 = 12
  }));
  data_24.update(19, (() => positionVar_23 = 20));
  data_24.update(20, (() => {
    positionVar_23 = 21;
    val x_134 = currentTurn;
    val x_135 = x_134.+(1);
    currentTurn = x_135
  }));
  data_24.update(21, (() => positionVar_23 = 20));
  data_24.update(22, (() => {
    val x_136 = bindingMut_19;
    val x_137 = x_136.asInstanceOf[scala.Boolean];
    val x_138 = x_137.`unary_!`;
    if (x_138)
      {
        val x_139 = this.time;
        resetData_0 = x_139;
        val x_140 = resetData_0;
        val x_141 = x_140.asInstanceOf[scala.Double];
        bindingMut_15 = x_141;
        val x_142 = bindingMut_15;
        val x_143 = x_142.asInstanceOf[scala.Double];
        val x_144 = "Wait time ".+(x_143);
        resetData_0 = x_144;
        val x_145 = resetData_0;
        val x_146 = x_145.asInstanceOf[java.lang.String];
        bindingMut_14 = x_146;
        val x_147 = bindingMut_14;
        val x_148 = x_147.asInstanceOf[java.lang.String];
        val x_149 = x_148.+(" Id ");
        resetData_0 = x_149;
        val x_150 = resetData_0;
        val x_151 = x_150.asInstanceOf[java.lang.String];
        bindingMut_13 = x_151;
        val x_152 = this.id;
        resetData_0 = x_152;
        val x_153 = resetData_0;
        val x_154 = x_153.asInstanceOf[scala.Long];
        bindingMut_12 = x_154;
        val x_155 = bindingMut_12;
        val x_156 = x_155.asInstanceOf[scala.Long];
        val x_157 = bindingMut_13;
        val x_158 = x_157.asInstanceOf[java.lang.String];
        val x_159 = x_158.+(x_156);
        resetData_0 = x_159;
        val x_160 = resetData_0;
        val x_161 = x_160.asInstanceOf[java.lang.String];
        bindingMut_11 = x_161;
        val x_162 = bindingMut_11;
        val x_163 = x_162.asInstanceOf[java.lang.String];
        scala.Predef.println(x_163);
        resetData_0 = ();
        val x_164 = this.time;
        resetData_0 = x_164;
        val x_165 = resetData_0;
        val x_166 = x_165.asInstanceOf[scala.Double];
        bindingMut_10 = x_166;
        resetData_0 = 0.0;
        val x_167 = resetData_0;
        val x_168 = x_167.asInstanceOf[scala.Double];
        bindingMut_9 = x_168;
        positionVar_23 = 23
      }
    else
      ()
  }));
  data_24.update(23, (() => {
    val x_169 = meta.deep.runtime.Actor.proceedLabel;
    val x_170 = x_169("time");
    val x_171 = bindingMut_9;
    val x_172 = x_171.asInstanceOf[scala.Double];
    val x_173 = x_172.+(x_170);
    resetData_0 = x_173;
    val x_174 = resetData_0;
    val x_175 = x_174.asInstanceOf[scala.Double];
    bindingMut_9 = x_175;
    val x_176 = meta.deep.runtime.Actor.labelVals("time");
    val x_177 = bindingMut_9;
    val x_178 = x_177.asInstanceOf[scala.Double];
    val x_179 = bindingMut_10;
    val x_180 = x_179.asInstanceOf[scala.Double];
    val x_181 = x_180.-(x_178);
    x_176.append(x_181);
    resetData_0 = ();
    positionVar_23 = 24;
    val x_182 = currentTurn;
    val x_183 = x_182.+(1);
    currentTurn = x_183
  }));
  data_24.update(24, (() => {
    val x_184 = bindingMut_9;
    val x_185 = x_184.asInstanceOf[scala.Double];
    val x_186 = bindingMut_10;
    val x_187 = x_186.asInstanceOf[scala.Double];
    val x_188 = x_185.<(x_187);
    if (x_188)
      positionVar_23 = 23
    else
      positionVar_23 = 25
  }));
  data_24.update(25, (() => {
    val x_189 = bindingMut_9;
    val x_190 = x_189.asInstanceOf[scala.Double];
    val x_191 = bindingMut_10;
    val x_192 = x_191.asInstanceOf[scala.Double];
    val x_193 = x_190.<(x_192);
    val x_194 = x_193.`unary_!`;
    if (x_194)
      {
        val x_195 = this.id;
        resetData_0 = x_195;
        val x_196 = resetData_0;
        val x_197 = x_196.asInstanceOf[scala.Long];
        bindingMut_8 = x_197;
        val x_198 = bindingMut_8;
        val x_199 = x_198.asInstanceOf[scala.Long];
        val x_200 = "Wait time finished ".+(x_199);
        resetData_0 = x_200;
        val x_201 = resetData_0;
        val x_202 = x_201.asInstanceOf[java.lang.String];
        bindingMut_7 = x_202;
        val x_203 = bindingMut_7;
        val x_204 = x_203.asInstanceOf[java.lang.String];
        scala.Predef.println(x_204);
        resetData_0 = ();
        positionVar_23 = 8
      }
    else
      ()
  }));
  data_24.update(26, (() => {
    val x_205 = true.`unary_!`;
    if (x_205)
      {
        val x_206 = resetData_1.remove(0);
        val x_210 = x_206.find(((x_207: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_208 = x_207._1;
          val x_209 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_208.==(x_209)
        }));
        val x_211 = x_210.get;
        val x_212 = x_211._2;
        positionVar_23 = x_212
      }
    else
      ()
  }));
  data_24
}).apply();
  
  override def run_until(until_214: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_215 = currentTurn;
      val x_216 = x_215.<=(until_214);
      x_216.&&({
        val x_217 = positionVar_23;
        val x_218 = commands_213.length;
        x_217.<(x_218)
      })
    }) 
      {
        val x_219 = positionVar_23;
        val x_220 = commands_213.apply(x_219);
        x_220.apply()
      }
    ;
    this
  }
}
