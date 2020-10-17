package generated.meta.example.time_example

class Sim (var time: Double) extends meta.deep.runtime.Actor {
  var delay: Int = 5
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: java.lang.String = null;
  private var bindingMut_4: org.slf4j.Logger = null;
  private var bindingMut_5: scala.Any = null;
  private var listValMut_6: meta.deep.runtime.RequestMessage = null;
  private var iterMut_7: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: scala.Long = 0L;
  private var bindingMut_10: org.slf4j.Logger = null;
  private var bindingMut_11: scala.Double = 0.0;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: java.lang.String = null;
  private var bindingMut_14: scala.Long = 0L;
  private var bindingMut_15: java.lang.String = null;
  private var bindingMut_16: java.lang.String = null;
  private var bindingMut_17: scala.Double = 0.0;
  private var bindingMut_18: org.slf4j.Logger = null;
  private var bindingMut_19: scala.Double = 0.0;
  private var bindingMut_20: scala.Double = 0.0;
  private var bindingMut_21: java.lang.String = null;
  private var bindingMut_22: scala.Long = 0L;
  private var bindingMut_23: org.slf4j.Logger = null;
  private var bindingMut_24: scala.Boolean = false;
  private var bindingMut_25: scala.Double = 0.0;
  private var bindingMut_26: scala.Int = 0;
  private var positionVar_28: scala.Int = 0;
  
  val commands_261 = (() => {
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
    val x_45 = ((this): meta.deep.runtime.Actor).interrupts;
    val x_46 = bindingMut_25;
    val x_47 = x_46.asInstanceOf[scala.Double];
    val x_48 = x_45.getOrElse[scala.collection.immutable.List[meta.deep.runtime.Message]](x_47, scala.collection.immutable.Nil);
    val x_49 = ((this): meta.deep.runtime.Actor).interrupts;
    val x_50 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_44);
    val x_51 = x_50.:::[meta.deep.runtime.Message](x_48);
    val x_52 = bindingMut_25;
    val x_53 = x_52.asInstanceOf[scala.Double];
    x_49.update(x_53, x_51);
    resetData_0 = null;
    positionVar_28 = 2
  }));
  data_29.update(2, (() => if (true)
    positionVar_28 = 3
  else
    positionVar_28 = 26));
  data_29.update(3, (() => {
    val x_54 = scala.util.Random.nextBoolean();
    resetData_0 = x_54;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.Boolean];
    bindingMut_24 = x_56;
    positionVar_28 = 4
  }));
  data_29.update(4, (() => {
    val x_57 = bindingMut_24;
    val x_58 = x_57.asInstanceOf[scala.Boolean];
    if (x_58)
      {
        val x_59 = this.logger;
        resetData_0 = x_59;
        val x_60 = resetData_0;
        val x_61 = x_60.asInstanceOf[org.slf4j.Logger];
        bindingMut_23 = x_61;
        val x_62 = this.id;
        resetData_0 = x_62;
        val x_63 = resetData_0;
        val x_64 = x_63.asInstanceOf[scala.Long];
        bindingMut_22 = x_64;
        val x_65 = bindingMut_22;
        val x_66 = x_65.asInstanceOf[scala.Long];
        val x_67 = "Wait turn!  Id ".+(x_66);
        resetData_0 = x_67;
        val x_68 = resetData_0;
        val x_69 = x_68.asInstanceOf[java.lang.String];
        bindingMut_21 = x_69;
        val x_70 = bindingMut_21;
        val x_71 = x_70.asInstanceOf[java.lang.String];
        val x_72 = bindingMut_23;
        val x_73 = x_72.asInstanceOf[org.slf4j.Logger];
        x_73.debug(x_71);
        resetData_0 = ();
        val x_74 = 1.toDouble;
        resetData_0 = x_74;
        val x_75 = resetData_0;
        val x_76 = x_75.asInstanceOf[scala.Double];
        bindingMut_20 = x_76;
        resetData_0 = 0.0;
        val x_77 = resetData_0;
        val x_78 = x_77.asInstanceOf[scala.Double];
        bindingMut_19 = x_78;
        positionVar_28 = 5
      }
    else
      positionVar_28 = 22
  }));
  data_29.update(5, (() => {
    val x_79 = meta.deep.runtime.Actor.proceedLabel;
    val x_80 = x_79("turn");
    val x_81 = bindingMut_19;
    val x_82 = x_81.asInstanceOf[scala.Double];
    val x_83 = x_82.+(x_80);
    resetData_0 = x_83;
    val x_84 = resetData_0;
    val x_85 = x_84.asInstanceOf[scala.Double];
    bindingMut_19 = x_85;
    val x_86 = meta.deep.runtime.Actor.labelVals("turn");
    val x_87 = bindingMut_19;
    val x_88 = x_87.asInstanceOf[scala.Double];
    val x_89 = bindingMut_20;
    val x_90 = x_89.asInstanceOf[scala.Double];
    val x_91 = x_90.-(x_88);
    x_86.append(x_91);
    resetData_0 = ();
    positionVar_28 = 6;
    val x_92 = currentTurn;
    val x_93 = x_92.+(1);
    currentTurn = x_93
  }));
  data_29.update(6, (() => {
    val x_94 = bindingMut_19;
    val x_95 = x_94.asInstanceOf[scala.Double];
    val x_96 = bindingMut_20;
    val x_97 = x_96.asInstanceOf[scala.Double];
    val x_98 = x_95.<(x_97);
    if (x_98)
      positionVar_28 = 5
    else
      positionVar_28 = 7
  }));
  data_29.update(7, (() => {
    val x_99 = bindingMut_19;
    val x_100 = x_99.asInstanceOf[scala.Double];
    val x_101 = bindingMut_20;
    val x_102 = x_101.asInstanceOf[scala.Double];
    val x_103 = x_100.<(x_102);
    val x_104 = x_103.`unary_!`;
    if (x_104)
      positionVar_28 = 8
    else
      ()
  }));
  data_29.update(8, (() => {
    val x_105 = this.popRequestMessages;
    val x_106 = x_105.iterator;
    iterMut_7 = x_106;
    positionVar_28 = 9
  }));
  data_29.update(9, (() => {
    val x_107 = iterMut_7;
    val x_108 = x_107.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_109 = x_108.hasNext;
    if (x_109)
      {
        val x_110 = iterMut_7;
        val x_111 = x_110.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_112 = x_111.next();
        listValMut_6 = x_112;
        positionVar_28 = 10
      }
    else
      positionVar_28 = 15
  }));
  data_29.update(10, (() => {
    val x_113 = listValMut_6;
    val x_114 = x_113.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_115 = x_114.methodId;
    val x_116 = x_115.==(0);
    val x_117 = x_116.`unary_!`;
    if (x_117)
      positionVar_28 = 11
    else
      positionVar_28 = 14
  }));
  data_29.update(11, (() => {
    val x_118 = listValMut_6;
    val x_119 = x_118.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_120 = x_119.methodId;
    val x_121 = x_120.==(1);
    val x_122 = x_121.`unary_!`;
    if (x_122)
      {
        val x_123 = listValMut_6;
        val x_124 = x_123.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_125 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_124);
        val x_126 = this.addReceiveMessages(x_125);
        resetData_0 = x_126;
        positionVar_28 = 12
      }
    else
      positionVar_28 = 13
  }));
  data_29.update(12, (() => positionVar_28 = 9));
  data_29.update(13, (() => {
    val x_127 = listValMut_6;
    val x_128 = x_127.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_129 = x_128.methodId;
    val x_130 = x_129.==(1);
    if (x_130)
      positionVar_28 = 1
    else
      ();
    val x_131 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_132 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_131, 18);
    val x_133 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_132);
    resetData_1.prepend(x_133)
  }));
  data_29.update(14, (() => {
    val x_134 = listValMut_6;
    val x_135 = x_134.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_136 = x_135.methodId;
    val x_137 = x_136.==(0);
    if (x_137)
      {
        val x_138 = this.logger;
        resetData_0 = x_138;
        val x_139 = resetData_0;
        val x_140 = x_139.asInstanceOf[org.slf4j.Logger];
        bindingMut_4 = x_140;
        val x_141 = meta.example.time_example.logInfo.interruptMsg;
        resetData_0 = x_141;
        val x_142 = resetData_0;
        val x_143 = x_142.asInstanceOf[java.lang.String];
        bindingMut_3 = x_143;
        val x_144 = bindingMut_3;
        val x_145 = x_144.asInstanceOf[java.lang.String];
        val x_146 = bindingMut_4;
        val x_147 = x_146.asInstanceOf[org.slf4j.Logger];
        x_147.info(x_145);
        resetData_0 = ();
        val x_148 = resetData_0;
        val x_149 = x_148.asInstanceOf[scala.Any];
        bindingMut_5 = x_149;
        val x_150 = bindingMut_5;
        val x_151 = x_150.asInstanceOf[scala.Any];
        val x_152 = listValMut_6;
        val x_153 = x_152.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_153.reply(this, x_151);
        resetData_0 = ();
        positionVar_28 = 12
      }
    else
      ()
  }));
  data_29.update(15, (() => {
    val x_154 = iterMut_7;
    val x_155 = x_154.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_156 = x_155.hasNext;
    val x_157 = x_156.`unary_!`;
    if (x_157)
      positionVar_28 = 16
    else
      ()
  }));
  data_29.update(16, (() => if (true)
    positionVar_28 = 3
  else
    positionVar_28 = 17));
  data_29.update(17, (() => {
    val x_158 = true.`unary_!`;
    if (x_158)
      {
        val x_159 = resetData_1.remove(0);
        val x_163 = x_159.find(((x_160: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_161 = x_160._1;
          val x_162 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_161.==(x_162)
        }));
        val x_164 = x_163.get;
        val x_165 = x_164._2;
        positionVar_28 = x_165
      }
    else
      ()
  }));
  data_29.update(18, (() => {
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[scala.Any];
    bindingMut_5 = x_167;
    val x_168 = bindingMut_5;
    val x_169 = x_168.asInstanceOf[scala.Any];
    val x_170 = listValMut_6;
    val x_171 = x_170.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_171.reply(this, x_169);
    resetData_0 = ();
    positionVar_28 = 12
  }));
  data_29.update(19, (() => positionVar_28 = 20));
  data_29.update(20, (() => {
    positionVar_28 = 21;
    val x_172 = currentTurn;
    val x_173 = x_172.+(1);
    currentTurn = x_173
  }));
  data_29.update(21, (() => positionVar_28 = 20));
  data_29.update(22, (() => {
    val x_174 = bindingMut_24;
    val x_175 = x_174.asInstanceOf[scala.Boolean];
    val x_176 = x_175.`unary_!`;
    if (x_176)
      {
        val x_177 = this.logger;
        resetData_0 = x_177;
        val x_178 = resetData_0;
        val x_179 = x_178.asInstanceOf[org.slf4j.Logger];
        bindingMut_18 = x_179;
        val x_180 = this.time;
        resetData_0 = x_180;
        val x_181 = resetData_0;
        val x_182 = x_181.asInstanceOf[scala.Double];
        bindingMut_17 = x_182;
        val x_183 = bindingMut_17;
        val x_184 = x_183.asInstanceOf[scala.Double];
        val x_185 = "Wait time ".+(x_184);
        resetData_0 = x_185;
        val x_186 = resetData_0;
        val x_187 = x_186.asInstanceOf[java.lang.String];
        bindingMut_16 = x_187;
        val x_188 = bindingMut_16;
        val x_189 = x_188.asInstanceOf[java.lang.String];
        val x_190 = x_189.+(" Id ");
        resetData_0 = x_190;
        val x_191 = resetData_0;
        val x_192 = x_191.asInstanceOf[java.lang.String];
        bindingMut_15 = x_192;
        val x_193 = this.id;
        resetData_0 = x_193;
        val x_194 = resetData_0;
        val x_195 = x_194.asInstanceOf[scala.Long];
        bindingMut_14 = x_195;
        val x_196 = bindingMut_14;
        val x_197 = x_196.asInstanceOf[scala.Long];
        val x_198 = bindingMut_15;
        val x_199 = x_198.asInstanceOf[java.lang.String];
        val x_200 = x_199.+(x_197);
        resetData_0 = x_200;
        val x_201 = resetData_0;
        val x_202 = x_201.asInstanceOf[java.lang.String];
        bindingMut_13 = x_202;
        val x_203 = bindingMut_13;
        val x_204 = x_203.asInstanceOf[java.lang.String];
        val x_205 = bindingMut_18;
        val x_206 = x_205.asInstanceOf[org.slf4j.Logger];
        x_206.debug(x_204);
        resetData_0 = ();
        val x_207 = this.time;
        resetData_0 = x_207;
        val x_208 = resetData_0;
        val x_209 = x_208.asInstanceOf[scala.Double];
        bindingMut_12 = x_209;
        resetData_0 = 0.0;
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[scala.Double];
        bindingMut_11 = x_211;
        positionVar_28 = 23
      }
    else
      ()
  }));
  data_29.update(23, (() => {
    val x_212 = meta.deep.runtime.Actor.proceedLabel;
    val x_213 = x_212("time");
    val x_214 = bindingMut_11;
    val x_215 = x_214.asInstanceOf[scala.Double];
    val x_216 = x_215.+(x_213);
    resetData_0 = x_216;
    val x_217 = resetData_0;
    val x_218 = x_217.asInstanceOf[scala.Double];
    bindingMut_11 = x_218;
    val x_219 = meta.deep.runtime.Actor.labelVals("time");
    val x_220 = bindingMut_11;
    val x_221 = x_220.asInstanceOf[scala.Double];
    val x_222 = bindingMut_12;
    val x_223 = x_222.asInstanceOf[scala.Double];
    val x_224 = x_223.-(x_221);
    x_219.append(x_224);
    resetData_0 = ();
    positionVar_28 = 24;
    val x_225 = currentTurn;
    val x_226 = x_225.+(1);
    currentTurn = x_226
  }));
  data_29.update(24, (() => {
    val x_227 = bindingMut_11;
    val x_228 = x_227.asInstanceOf[scala.Double];
    val x_229 = bindingMut_12;
    val x_230 = x_229.asInstanceOf[scala.Double];
    val x_231 = x_228.<(x_230);
    if (x_231)
      positionVar_28 = 23
    else
      positionVar_28 = 25
  }));
  data_29.update(25, (() => {
    val x_232 = bindingMut_11;
    val x_233 = x_232.asInstanceOf[scala.Double];
    val x_234 = bindingMut_12;
    val x_235 = x_234.asInstanceOf[scala.Double];
    val x_236 = x_233.<(x_235);
    val x_237 = x_236.`unary_!`;
    if (x_237)
      {
        val x_238 = this.logger;
        resetData_0 = x_238;
        val x_239 = resetData_0;
        val x_240 = x_239.asInstanceOf[org.slf4j.Logger];
        bindingMut_10 = x_240;
        val x_241 = this.id;
        resetData_0 = x_241;
        val x_242 = resetData_0;
        val x_243 = x_242.asInstanceOf[scala.Long];
        bindingMut_9 = x_243;
        val x_244 = bindingMut_9;
        val x_245 = x_244.asInstanceOf[scala.Long];
        val x_246 = "Wait time finished ".+(x_245);
        resetData_0 = x_246;
        val x_247 = resetData_0;
        val x_248 = x_247.asInstanceOf[java.lang.String];
        bindingMut_8 = x_248;
        val x_249 = bindingMut_8;
        val x_250 = x_249.asInstanceOf[java.lang.String];
        val x_251 = bindingMut_10;
        val x_252 = x_251.asInstanceOf[org.slf4j.Logger];
        x_252.debug(x_250);
        resetData_0 = ();
        positionVar_28 = 8
      }
    else
      ()
  }));
  data_29.update(26, (() => {
    val x_253 = true.`unary_!`;
    if (x_253)
      {
        val x_254 = resetData_1.remove(0);
        val x_258 = x_254.find(((x_255: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_256 = x_255._1;
          val x_257 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_256.==(x_257)
        }));
        val x_259 = x_258.get;
        val x_260 = x_259._2;
        positionVar_28 = x_260
      }
    else
      ()
  }));
  data_29
}).apply();
  
  override def run_until(until_262: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_263 = currentTurn;
      val x_264 = x_263.<=(until_262);
      x_264.&&({
        val x_265 = positionVar_28;
        val x_266 = commands_261.length;
        x_265.<(x_266)
      })
    }) 
      {
        val x_267 = positionVar_28;
        val x_268 = commands_261.apply(x_267);
        x_268.apply()
      }
    ;
    this
  }
}
