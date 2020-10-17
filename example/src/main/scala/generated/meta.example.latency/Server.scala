package generated.meta.example.latency

class Server (var processTime: Double) extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_4: scala.Long = 0L;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Boolean = false;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: scala.Double = 0.0;
  private var bindingMut_16: scala.Double = 0.0;
  private var bindingMut_17: scala.Double = 0.0;
  private var bindingMut_18: scala.Any = null;
  private var listValMut_19: meta.deep.runtime.RequestMessage = null;
  private var iterMut_20: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_22: scala.Int = 0;
  
  val commands_242 = (() => {
  val data_23 = new scala.Array[scala.Function0[scala.Unit]](29);
  data_23.update(0, (() => {
    positionVar_22 = 1;
    val x_24 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_25 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_24, 25);
    val x_26 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_25);
    resetData_1.prepend(x_26)
  }));
  data_23.update(1, (() => if (true)
    positionVar_22 = 2
  else
    positionVar_22 = 28));
  data_23.update(2, (() => {
    val x_27 = this.popRequestMessages;
    val x_28 = x_27.iterator;
    iterMut_20 = x_28;
    positionVar_22 = 3
  }));
  data_23.update(3, (() => {
    val x_29 = iterMut_20;
    val x_30 = x_29.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_31 = x_30.hasNext;
    if (x_31)
      {
        val x_32 = iterMut_20;
        val x_33 = x_32.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_34 = x_33.next();
        listValMut_19 = x_34;
        positionVar_22 = 4
      }
    else
      positionVar_22 = 15
  }));
  data_23.update(4, (() => {
    val x_35 = listValMut_19;
    val x_36 = x_35.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_37 = x_36.methodId;
    val x_38 = x_37.==(0);
    val x_39 = x_38.`unary_!`;
    if (x_39)
      positionVar_22 = 5
    else
      positionVar_22 = 8
  }));
  data_23.update(5, (() => {
    val x_40 = listValMut_19;
    val x_41 = x_40.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_42 = x_41.methodId;
    val x_43 = x_42.==(1);
    val x_44 = x_43.`unary_!`;
    if (x_44)
      {
        val x_45 = listValMut_19;
        val x_46 = x_45.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_47 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_46);
        val x_48 = this.addReceiveMessages(x_47);
        resetData_0 = x_48;
        positionVar_22 = 6
      }
    else
      positionVar_22 = 7
  }));
  data_23.update(6, (() => positionVar_22 = 3));
  data_23.update(7, (() => {
    val x_49 = listValMut_19;
    val x_50 = x_49.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_51 = x_50.methodId;
    val x_52 = x_51.==(1);
    if (x_52)
      positionVar_22 = 1
    else
      ();
    val x_53 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_54 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_53, 24);
    val x_55 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_54);
    resetData_1.prepend(x_55)
  }));
  data_23.update(8, (() => {
    val x_56 = listValMut_19;
    val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_58 = x_57.methodId;
    val x_59 = x_58.==(0);
    if (x_59)
      {
        val x_60 = listValMut_19;
        val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_62 = x_61.argss;
        val x_63 = x_62(0);
        val x_64 = x_63(0);
        x_3.prepend(x_64);
        val x_65 = listValMut_19;
        val x_66 = x_65.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_67 = x_66.argss;
        val x_68 = x_67(0);
        val x_69 = x_68(0);
        val x_70 = x_69.asInstanceOf[scala.Long];
        methodArgsMut_4 = x_70;
        val x_71 = methodArgsMut_4;
        val x_72 = x_71.asInstanceOf[scala.Long];
        val x_73 = "Server processing request for ".+(x_72);
        resetData_0 = x_73;
        val x_74 = resetData_0;
        val x_75 = x_74.asInstanceOf[java.lang.String];
        bindingMut_14 = x_75;
        val x_76 = bindingMut_14;
        val x_77 = x_76.asInstanceOf[java.lang.String];
        scala.Predef.println(x_77);
        resetData_0 = ();
        val x_78 = scala.util.Random.nextBoolean();
        resetData_0 = x_78;
        val x_79 = resetData_0;
        val x_80 = x_79.asInstanceOf[scala.Boolean];
        bindingMut_13 = x_80;
        positionVar_22 = 9
      }
    else
      ()
  }));
  data_23.update(9, (() => {
    val x_81 = bindingMut_13;
    val x_82 = x_81.asInstanceOf[scala.Boolean];
    val x_83 = x_82.`unary_!`;
    if (x_83)
      positionVar_22 = 10
    else
      positionVar_22 = 11
  }));
  data_23.update(10, (() => {
    val x_84 = scala.util.Random.nextInt(100);
    resetData_0 = x_84;
    val x_85 = resetData_0;
    val x_86 = x_85.asInstanceOf[scala.Int];
    bindingMut_8 = x_86;
    val x_87 = methodArgsMut_4;
    val x_88 = x_87.asInstanceOf[scala.Long];
    val x_89 = "Response for client id ".+(x_88);
    resetData_0 = x_89;
    val x_90 = resetData_0;
    val x_91 = x_90.asInstanceOf[java.lang.String];
    bindingMut_7 = x_91;
    val x_92 = bindingMut_7;
    val x_93 = x_92.asInstanceOf[java.lang.String];
    val x_94 = x_93.+(" is ");
    resetData_0 = x_94;
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[java.lang.String];
    bindingMut_6 = x_96;
    val x_97 = bindingMut_6;
    val x_98 = x_97.asInstanceOf[java.lang.String];
    val x_99 = bindingMut_8;
    val x_100 = x_99.asInstanceOf[scala.Int];
    val x_101 = x_98.+(x_100);
    resetData_0 = x_101;
    val x_102 = resetData_0;
    val x_103 = x_102.asInstanceOf[java.lang.String];
    bindingMut_5 = x_103;
    val x_104 = bindingMut_5;
    val x_105 = x_104.asInstanceOf[java.lang.String];
    scala.Predef.println(x_105);
    resetData_0 = ();
    val x_106 = bindingMut_8;
    val x_107 = x_106.asInstanceOf[scala.Int];
    resetData_0 = x_107;
    x_3.remove(0);
    val x_108 = x_3.isEmpty;
    val x_109 = x_108.`unary_!`;
    if (x_109)
      {
        val x_110 = x_3(0);
        val x_111 = x_110.asInstanceOf[scala.Long];
        methodArgsMut_4 = x_111
      }
    else
      ();
    val x_112 = resetData_0;
    val x_113 = x_112.asInstanceOf[scala.Any];
    bindingMut_18 = x_113;
    val x_114 = bindingMut_18;
    val x_115 = x_114.asInstanceOf[scala.Any];
    val x_116 = listValMut_19;
    val x_117 = x_116.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_117.reply(this, x_115);
    resetData_0 = ();
    positionVar_22 = 6
  }));
  data_23.update(11, (() => {
    val x_118 = bindingMut_13;
    val x_119 = x_118.asInstanceOf[scala.Boolean];
    if (x_119)
      {
        val x_120 = this.processTime;
        resetData_0 = x_120;
        val x_121 = resetData_0;
        val x_122 = x_121.asInstanceOf[scala.Double];
        bindingMut_12 = x_122;
        val x_123 = bindingMut_12;
        val x_124 = x_123.asInstanceOf[scala.Double];
        val x_125 = "Please wait! ; take ".+(x_124);
        resetData_0 = x_125;
        val x_126 = resetData_0;
        val x_127 = x_126.asInstanceOf[java.lang.String];
        bindingMut_11 = x_127;
        val x_128 = bindingMut_11;
        val x_129 = x_128.asInstanceOf[java.lang.String];
        scala.Predef.println(x_129);
        resetData_0 = ();
        val x_130 = this.processTime;
        resetData_0 = x_130;
        val x_131 = resetData_0;
        val x_132 = x_131.asInstanceOf[scala.Double];
        bindingMut_10 = x_132;
        resetData_0 = 0.0;
        val x_133 = resetData_0;
        val x_134 = x_133.asInstanceOf[scala.Double];
        bindingMut_9 = x_134;
        positionVar_22 = 12
      }
    else
      ()
  }));
  data_23.update(12, (() => {
    val x_135 = meta.deep.runtime.Actor.proceedLabel;
    val x_136 = x_135("time");
    val x_137 = bindingMut_9;
    val x_138 = x_137.asInstanceOf[scala.Double];
    val x_139 = x_138.+(x_136);
    resetData_0 = x_139;
    val x_140 = resetData_0;
    val x_141 = x_140.asInstanceOf[scala.Double];
    bindingMut_9 = x_141;
    val x_142 = meta.deep.runtime.Actor.labelVals("time");
    val x_143 = bindingMut_9;
    val x_144 = x_143.asInstanceOf[scala.Double];
    val x_145 = bindingMut_10;
    val x_146 = x_145.asInstanceOf[scala.Double];
    val x_147 = x_146.-(x_144);
    x_142.append(x_147);
    resetData_0 = ();
    positionVar_22 = 13;
    val x_148 = currentTurn;
    val x_149 = x_148.+(1);
    currentTurn = x_149
  }));
  data_23.update(13, (() => {
    val x_150 = bindingMut_9;
    val x_151 = x_150.asInstanceOf[scala.Double];
    val x_152 = bindingMut_10;
    val x_153 = x_152.asInstanceOf[scala.Double];
    val x_154 = x_151.<(x_153);
    if (x_154)
      positionVar_22 = 12
    else
      positionVar_22 = 14
  }));
  data_23.update(14, (() => {
    val x_155 = bindingMut_9;
    val x_156 = x_155.asInstanceOf[scala.Double];
    val x_157 = bindingMut_10;
    val x_158 = x_157.asInstanceOf[scala.Double];
    val x_159 = x_156.<(x_158);
    val x_160 = x_159.`unary_!`;
    if (x_160)
      positionVar_22 = 10
    else
      ()
  }));
  data_23.update(15, (() => {
    val x_161 = iterMut_20;
    val x_162 = x_161.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_163 = x_162.hasNext;
    val x_164 = x_163.`unary_!`;
    if (x_164)
      {
        resetData_0 = 0.0;
        val x_165 = resetData_0;
        val x_166 = x_165.asInstanceOf[scala.Double];
        bindingMut_17 = x_166;
        positionVar_22 = 16
      }
    else
      ()
  }));
  data_23.update(16, (() => {
    val x_167 = meta.deep.runtime.Actor.proceedLabel;
    val x_168 = x_167("time");
    val x_169 = bindingMut_17;
    val x_170 = x_169.asInstanceOf[scala.Double];
    val x_171 = x_170.+(x_168);
    resetData_0 = x_171;
    val x_172 = resetData_0;
    val x_173 = x_172.asInstanceOf[scala.Double];
    bindingMut_17 = x_173;
    val x_174 = meta.deep.runtime.Actor.labelVals("time");
    val x_175 = bindingMut_17;
    val x_176 = x_175.asInstanceOf[scala.Double];
    val x_177 = 0.1.-(x_176);
    x_174.append(x_177);
    resetData_0 = ();
    positionVar_22 = 17;
    val x_178 = currentTurn;
    val x_179 = x_178.+(1);
    currentTurn = x_179
  }));
  data_23.update(17, (() => {
    val x_180 = bindingMut_17;
    val x_181 = x_180.asInstanceOf[scala.Double];
    val x_182 = x_181.<(0.1);
    if (x_182)
      positionVar_22 = 16
    else
      positionVar_22 = 18
  }));
  data_23.update(18, (() => {
    val x_183 = bindingMut_17;
    val x_184 = x_183.asInstanceOf[scala.Double];
    val x_185 = x_184.<(0.1);
    val x_186 = x_185.`unary_!`;
    if (x_186)
      {
        val x_187 = 1.toDouble;
        resetData_0 = x_187;
        val x_188 = resetData_0;
        val x_189 = x_188.asInstanceOf[scala.Double];
        bindingMut_16 = x_189;
        resetData_0 = 0.0;
        val x_190 = resetData_0;
        val x_191 = x_190.asInstanceOf[scala.Double];
        bindingMut_15 = x_191;
        positionVar_22 = 19
      }
    else
      ()
  }));
  data_23.update(19, (() => {
    val x_192 = meta.deep.runtime.Actor.proceedLabel;
    val x_193 = x_192("turn");
    val x_194 = bindingMut_15;
    val x_195 = x_194.asInstanceOf[scala.Double];
    val x_196 = x_195.+(x_193);
    resetData_0 = x_196;
    val x_197 = resetData_0;
    val x_198 = x_197.asInstanceOf[scala.Double];
    bindingMut_15 = x_198;
    val x_199 = meta.deep.runtime.Actor.labelVals("turn");
    val x_200 = bindingMut_15;
    val x_201 = x_200.asInstanceOf[scala.Double];
    val x_202 = bindingMut_16;
    val x_203 = x_202.asInstanceOf[scala.Double];
    val x_204 = x_203.-(x_201);
    x_199.append(x_204);
    resetData_0 = ();
    positionVar_22 = 20;
    val x_205 = currentTurn;
    val x_206 = x_205.+(1);
    currentTurn = x_206
  }));
  data_23.update(20, (() => {
    val x_207 = bindingMut_15;
    val x_208 = x_207.asInstanceOf[scala.Double];
    val x_209 = bindingMut_16;
    val x_210 = x_209.asInstanceOf[scala.Double];
    val x_211 = x_208.<(x_210);
    if (x_211)
      positionVar_22 = 19
    else
      positionVar_22 = 21
  }));
  data_23.update(21, (() => {
    val x_212 = bindingMut_15;
    val x_213 = x_212.asInstanceOf[scala.Double];
    val x_214 = bindingMut_16;
    val x_215 = x_214.asInstanceOf[scala.Double];
    val x_216 = x_213.<(x_215);
    val x_217 = x_216.`unary_!`;
    if (x_217)
      positionVar_22 = 22
    else
      ()
  }));
  data_23.update(22, (() => if (true)
    positionVar_22 = 2
  else
    positionVar_22 = 23));
  data_23.update(23, (() => {
    val x_218 = true.`unary_!`;
    if (x_218)
      {
        val x_219 = resetData_1.remove(0);
        val x_223 = x_219.find(((x_220: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_221 = x_220._1;
          val x_222 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_221.==(x_222)
        }));
        val x_224 = x_223.get;
        val x_225 = x_224._2;
        positionVar_22 = x_225
      }
    else
      ()
  }));
  data_23.update(24, (() => {
    val x_226 = resetData_0;
    val x_227 = x_226.asInstanceOf[scala.Any];
    bindingMut_18 = x_227;
    val x_228 = bindingMut_18;
    val x_229 = x_228.asInstanceOf[scala.Any];
    val x_230 = listValMut_19;
    val x_231 = x_230.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_231.reply(this, x_229);
    resetData_0 = ();
    positionVar_22 = 6
  }));
  data_23.update(25, (() => positionVar_22 = 26));
  data_23.update(26, (() => {
    positionVar_22 = 27;
    val x_232 = currentTurn;
    val x_233 = x_232.+(1);
    currentTurn = x_233
  }));
  data_23.update(27, (() => positionVar_22 = 26));
  data_23.update(28, (() => {
    val x_234 = true.`unary_!`;
    if (x_234)
      {
        val x_235 = resetData_1.remove(0);
        val x_239 = x_235.find(((x_236: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_237 = x_236._1;
          val x_238 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_237.==(x_238)
        }));
        val x_240 = x_239.get;
        val x_241 = x_240._2;
        positionVar_22 = x_241
      }
    else
      ()
  }));
  data_23
}).apply();
  
  override def run_until(until_243: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_244 = currentTurn;
      val x_245 = x_244.<=(until_243);
      x_245.&&({
        val x_246 = positionVar_22;
        val x_247 = commands_242.length;
        x_246.<(x_247)
      })
    }) 
      {
        val x_248 = positionVar_22;
        val x_249 = commands_242.apply(x_248);
        x_249.apply()
      }
    ;
    this
  }
}
