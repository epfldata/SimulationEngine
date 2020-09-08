package generated.meta.example.time_example

class Sim (var time: Double) extends meta.deep.runtime.Actor with Serializable {
  var delay: Int = 5
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: org.slf4j.Logger = null;
  private var bindingMut_6: scala.Any = null;
  private var listValMut_7: meta.deep.runtime.RequestMessage = null;
  private var iterMut_8: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Long = 0L;
  private var bindingMut_11: org.slf4j.Logger = null;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: scala.Long = 0L;
  private var bindingMut_16: java.lang.String = null;
  private var bindingMut_17: java.lang.String = null;
  private var bindingMut_18: scala.Double = 0.0;
  private var bindingMut_19: org.slf4j.Logger = null;
  private var bindingMut_20: scala.Int = 0;
  private var bindingMut_21: java.lang.String = null;
  private var bindingMut_22: scala.Long = 0L;
  private var bindingMut_23: org.slf4j.Logger = null;
  private var bindingMut_24: scala.Boolean = false;
  private var bindingMut_25: scala.Double = 0.0;
  private var bindingMut_26: scala.Int = 0;
  private var positionVar_28: scala.Int = 0;
  
  val commands_243 = (() => {
  val data_29 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_29.update(0, (() => {
    positionVar_28 = 1;
    val x_30 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_31 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_30, 19);
    val x_32 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_31);
    resetData_1.prepend(x_32)
  }));
  data_29.update(1, (() => {
    val x_33 = this.delay;
    resetData_0 = x_33;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Int];
    bindingMut_26 = x_35;
    val x_36 = bindingMut_26;
    val x_37 = x_36.asInstanceOf[scala.Int];
    val x_38 = x_37.toDouble;
    resetData_0 = x_38;
    val x_39 = resetData_0;
    val x_40 = x_39.asInstanceOf[scala.Double];
    bindingMut_25 = x_40;
    val x_41 = ((this): meta.deep.runtime.Actor).id;
    val x_42 = ((this): meta.deep.runtime.Actor).id;
    val x_43 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_44 = meta.deep.runtime.RequestMessage.apply(x_41, x_42, 0, x_43);
    val x_45 = bindingMut_25;
    val x_46 = x_45.asInstanceOf[scala.Double];
    ((this): meta.deep.runtime.Actor).registerInterrupt(x_46, x_44);
    resetData_0 = null;
    positionVar_28 = 2
  }));
  data_29.update(2, (() => if (true)
    positionVar_28 = 3
  else
    positionVar_28 = 26));
  data_29.update(3, (() => {
    val x_47 = scala.util.Random.nextBoolean();
    resetData_0 = x_47;
    val x_48 = resetData_0;
    val x_49 = x_48.asInstanceOf[scala.Boolean];
    bindingMut_24 = x_49;
    positionVar_28 = 4
  }));
  data_29.update(4, (() => {
    val x_50 = bindingMut_24;
    val x_51 = x_50.asInstanceOf[scala.Boolean];
    if (x_51)
      {
        val x_52 = this.logger;
        resetData_0 = x_52;
        val x_53 = resetData_0;
        val x_54 = x_53.asInstanceOf[org.slf4j.Logger];
        bindingMut_23 = x_54;
        val x_55 = this.id;
        resetData_0 = x_55;
        val x_56 = resetData_0;
        val x_57 = x_56.asInstanceOf[scala.Long];
        bindingMut_22 = x_57;
        val x_58 = bindingMut_22;
        val x_59 = x_58.asInstanceOf[scala.Long];
        val x_60 = "Wait turn!  Id ".+(x_59);
        resetData_0 = x_60;
        val x_61 = resetData_0;
        val x_62 = x_61.asInstanceOf[java.lang.String];
        bindingMut_21 = x_62;
        val x_63 = bindingMut_21;
        val x_64 = x_63.asInstanceOf[java.lang.String];
        val x_65 = bindingMut_23;
        val x_66 = x_65.asInstanceOf[org.slf4j.Logger];
        x_66.debug(x_64);
        resetData_0 = ();
        resetData_0 = 0;
        val x_67 = resetData_0;
        val x_68 = x_67.asInstanceOf[scala.Int];
        bindingMut_20 = x_68;
        positionVar_28 = 5
      }
    else
      positionVar_28 = 22
  }));
  data_29.update(5, (() => {
    val x_69 = bindingMut_20;
    val x_70 = x_69.asInstanceOf[scala.Int];
    val x_71 = (1).-(x_70);
    meta.deep.runtime.Actor.waitTurnList.append(x_71);
    resetData_0 = ();
    val x_72 = meta.deep.runtime.Actor.minTurn();
    val x_73 = bindingMut_20;
    val x_74 = x_73.asInstanceOf[scala.Int];
    val x_75 = x_74.+(x_72);
    resetData_0 = x_75;
    val x_76 = resetData_0;
    val x_77 = x_76.asInstanceOf[scala.Int];
    bindingMut_20 = x_77;
    positionVar_28 = 6;
    val x_78 = currentTurn;
    val x_79 = x_78.+(1);
    currentTurn = x_79
  }));
  data_29.update(6, (() => {
    val x_80 = bindingMut_20;
    val x_81 = x_80.asInstanceOf[scala.Int];
    val x_82 = x_81.<(1);
    if (x_82)
      positionVar_28 = 5
    else
      positionVar_28 = 7
  }));
  data_29.update(7, (() => {
    val x_83 = bindingMut_20;
    val x_84 = x_83.asInstanceOf[scala.Int];
    val x_85 = x_84.<(1);
    val x_86 = x_85.`unary_!`;
    if (x_86)
      positionVar_28 = 8
    else
      ()
  }));
  data_29.update(8, (() => {
    val x_87 = this.popRequestMessages;
    val x_88 = x_87.iterator;
    iterMut_8 = x_88;
    positionVar_28 = 9
  }));
  data_29.update(9, (() => {
    val x_89 = iterMut_8;
    val x_90 = x_89.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_91 = x_90.hasNext;
    if (x_91)
      {
        val x_92 = iterMut_8;
        val x_93 = x_92.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_94 = x_93.next();
        listValMut_7 = x_94;
        positionVar_28 = 10
      }
    else
      positionVar_28 = 15
  }));
  data_29.update(10, (() => {
    val x_95 = listValMut_7;
    val x_96 = x_95.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_97 = x_96.methodId;
    val x_98 = x_97.==(0);
    val x_99 = x_98.`unary_!`;
    if (x_99)
      positionVar_28 = 11
    else
      positionVar_28 = 14
  }));
  data_29.update(11, (() => {
    val x_100 = listValMut_7;
    val x_101 = x_100.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_102 = x_101.methodId;
    val x_103 = x_102.==(1);
    val x_104 = x_103.`unary_!`;
    if (x_104)
      {
        val x_105 = listValMut_7;
        val x_106 = x_105.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_107 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_106);
        val x_108 = this.addReceiveMessages(x_107);
        resetData_0 = x_108;
        positionVar_28 = 12
      }
    else
      positionVar_28 = 13
  }));
  data_29.update(12, (() => positionVar_28 = 9));
  data_29.update(13, (() => {
    val x_109 = listValMut_7;
    val x_110 = x_109.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_111 = x_110.methodId;
    val x_112 = x_111.==(1);
    if (x_112)
      positionVar_28 = 1
    else
      ();
    val x_113 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_114 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_113, 18);
    val x_115 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_114);
    resetData_1.prepend(x_115)
  }));
  data_29.update(14, (() => {
    val x_116 = listValMut_7;
    val x_117 = x_116.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_118 = x_117.methodId;
    val x_119 = x_118.==(0);
    if (x_119)
      {
        val x_120 = this.logger;
        resetData_0 = x_120;
        val x_121 = resetData_0;
        val x_122 = x_121.asInstanceOf[org.slf4j.Logger];
        bindingMut_5 = x_122;
        val x_123 = meta.example.time_example.logInfo.interruptMsg;
        resetData_0 = x_123;
        val x_124 = resetData_0;
        val x_125 = x_124.asInstanceOf[java.lang.String];
        bindingMut_4 = x_125;
        val x_126 = bindingMut_4;
        val x_127 = x_126.asInstanceOf[java.lang.String];
        val x_128 = bindingMut_5;
        val x_129 = x_128.asInstanceOf[org.slf4j.Logger];
        x_129.info(x_127);
        resetData_0 = ();
        val x_130 = resetData_0;
        val x_131 = x_130.asInstanceOf[scala.Any];
        bindingMut_6 = x_131;
        val x_132 = bindingMut_6;
        val x_133 = x_132.asInstanceOf[scala.Any];
        val x_134 = listValMut_7;
        val x_135 = x_134.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_135.reply(this, x_133);
        resetData_0 = ();
        positionVar_28 = 12
      }
    else
      ()
  }));
  data_29.update(15, (() => {
    val x_136 = iterMut_8;
    val x_137 = x_136.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_138 = x_137.hasNext;
    val x_139 = x_138.`unary_!`;
    if (x_139)
      positionVar_28 = 16
    else
      ()
  }));
  data_29.update(16, (() => if (true)
    positionVar_28 = 3
  else
    positionVar_28 = 17));
  data_29.update(17, (() => {
    val x_140 = true.`unary_!`;
    if (x_140)
      {
        val x_141 = resetData_1.remove(0);
        val x_145 = x_141.find(((x_142: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_143 = x_142._1;
          val x_144 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_143.==(x_144)
        }));
        val x_146 = x_145.get;
        val x_147 = x_146._2;
        positionVar_28 = x_147
      }
    else
      ()
  }));
  data_29.update(18, (() => {
    val x_148 = resetData_0;
    val x_149 = x_148.asInstanceOf[scala.Any];
    bindingMut_6 = x_149;
    val x_150 = bindingMut_6;
    val x_151 = x_150.asInstanceOf[scala.Any];
    val x_152 = listValMut_7;
    val x_153 = x_152.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_153.reply(this, x_151);
    resetData_0 = ();
    positionVar_28 = 12
  }));
  data_29.update(19, (() => positionVar_28 = 20));
  data_29.update(20, (() => {
    positionVar_28 = 21;
    val x_154 = currentTurn;
    val x_155 = x_154.+(1);
    currentTurn = x_155
  }));
  data_29.update(21, (() => positionVar_28 = 20));
  data_29.update(22, (() => {
    val x_156 = bindingMut_24;
    val x_157 = x_156.asInstanceOf[scala.Boolean];
    val x_158 = x_157.`unary_!`;
    if (x_158)
      {
        val x_159 = this.logger;
        resetData_0 = x_159;
        val x_160 = resetData_0;
        val x_161 = x_160.asInstanceOf[org.slf4j.Logger];
        bindingMut_19 = x_161;
        val x_162 = this.time;
        resetData_0 = x_162;
        val x_163 = resetData_0;
        val x_164 = x_163.asInstanceOf[scala.Double];
        bindingMut_18 = x_164;
        val x_165 = bindingMut_18;
        val x_166 = x_165.asInstanceOf[scala.Double];
        val x_167 = "Wait time ".+(x_166);
        resetData_0 = x_167;
        val x_168 = resetData_0;
        val x_169 = x_168.asInstanceOf[java.lang.String];
        bindingMut_17 = x_169;
        val x_170 = bindingMut_17;
        val x_171 = x_170.asInstanceOf[java.lang.String];
        val x_172 = x_171.+(" Id ");
        resetData_0 = x_172;
        val x_173 = resetData_0;
        val x_174 = x_173.asInstanceOf[java.lang.String];
        bindingMut_16 = x_174;
        val x_175 = this.id;
        resetData_0 = x_175;
        val x_176 = resetData_0;
        val x_177 = x_176.asInstanceOf[scala.Long];
        bindingMut_15 = x_177;
        val x_178 = bindingMut_15;
        val x_179 = x_178.asInstanceOf[scala.Long];
        val x_180 = bindingMut_16;
        val x_181 = x_180.asInstanceOf[java.lang.String];
        val x_182 = x_181.+(x_179);
        resetData_0 = x_182;
        val x_183 = resetData_0;
        val x_184 = x_183.asInstanceOf[java.lang.String];
        bindingMut_14 = x_184;
        val x_185 = bindingMut_14;
        val x_186 = x_185.asInstanceOf[java.lang.String];
        val x_187 = bindingMut_19;
        val x_188 = x_187.asInstanceOf[org.slf4j.Logger];
        x_188.debug(x_186);
        resetData_0 = ();
        val x_189 = this.time;
        resetData_0 = x_189;
        val x_190 = resetData_0;
        val x_191 = x_190.asInstanceOf[scala.Double];
        bindingMut_13 = x_191;
        resetData_0 = 0.0;
        val x_192 = resetData_0;
        val x_193 = x_192.asInstanceOf[scala.Double];
        bindingMut_12 = x_193;
        positionVar_28 = 23
      }
    else
      ()
  }));
  data_29.update(23, (() => {
    val x_194 = meta.deep.runtime.Actor.proceedLabel;
    val x_195 = x_194("time");
    val x_196 = bindingMut_12;
    val x_197 = x_196.asInstanceOf[scala.Double];
    val x_198 = x_197.+(x_195);
    resetData_0 = x_198;
    val x_199 = resetData_0;
    val x_200 = x_199.asInstanceOf[scala.Double];
    bindingMut_12 = x_200;
    val x_201 = meta.deep.runtime.Actor.labelVals("time");
    val x_202 = bindingMut_12;
    val x_203 = x_202.asInstanceOf[scala.Double];
    val x_204 = bindingMut_13;
    val x_205 = x_204.asInstanceOf[scala.Double];
    val x_206 = x_205.-(x_203);
    x_201.append(x_206);
    resetData_0 = ();
    positionVar_28 = 24;
    val x_207 = currentTurn;
    val x_208 = x_207.+(1);
    currentTurn = x_208
  }));
  data_29.update(24, (() => {
    val x_209 = bindingMut_12;
    val x_210 = x_209.asInstanceOf[scala.Double];
    val x_211 = bindingMut_13;
    val x_212 = x_211.asInstanceOf[scala.Double];
    val x_213 = x_210.<(x_212);
    if (x_213)
      positionVar_28 = 23
    else
      positionVar_28 = 25
  }));
  data_29.update(25, (() => {
    val x_214 = bindingMut_12;
    val x_215 = x_214.asInstanceOf[scala.Double];
    val x_216 = bindingMut_13;
    val x_217 = x_216.asInstanceOf[scala.Double];
    val x_218 = x_215.<(x_217);
    val x_219 = x_218.`unary_!`;
    if (x_219)
      {
        val x_220 = this.logger;
        resetData_0 = x_220;
        val x_221 = resetData_0;
        val x_222 = x_221.asInstanceOf[org.slf4j.Logger];
        bindingMut_11 = x_222;
        val x_223 = this.id;
        resetData_0 = x_223;
        val x_224 = resetData_0;
        val x_225 = x_224.asInstanceOf[scala.Long];
        bindingMut_10 = x_225;
        val x_226 = bindingMut_10;
        val x_227 = x_226.asInstanceOf[scala.Long];
        val x_228 = "Wait time finished ".+(x_227);
        resetData_0 = x_228;
        val x_229 = resetData_0;
        val x_230 = x_229.asInstanceOf[java.lang.String];
        bindingMut_9 = x_230;
        val x_231 = bindingMut_9;
        val x_232 = x_231.asInstanceOf[java.lang.String];
        val x_233 = bindingMut_11;
        val x_234 = x_233.asInstanceOf[org.slf4j.Logger];
        x_234.debug(x_232);
        resetData_0 = ();
        positionVar_28 = 8
      }
    else
      ()
  }));
  data_29.update(26, (() => {
    val x_235 = true.`unary_!`;
    if (x_235)
      {
        val x_236 = resetData_1.remove(0);
        val x_240 = x_236.find(((x_237: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_238 = x_237._1;
          val x_239 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_238.==(x_239)
        }));
        val x_241 = x_240.get;
        val x_242 = x_241._2;
        positionVar_28 = x_242
      }
    else
      ()
  }));
  data_29
}).apply();
  
  override def run_until(until_244: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_245 = currentTurn;
      val x_246 = x_245.<=(until_244);
      x_246.&&({
        val x_247 = positionVar_28;
        val x_248 = commands_243.length;
        x_247.<(x_248)
      })
    }) 
      {
        val x_249 = positionVar_28;
        val x_250 = commands_243.apply(x_249);
        x_250.apply()
      }
    ;
    this
  }
}
