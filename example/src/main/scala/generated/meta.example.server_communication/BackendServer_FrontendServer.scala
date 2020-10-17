package generated.meta.example.server_communication_merged

class BackendServer_FrontendServer () extends meta.deep.runtime.Actor {
  var backendServer: generated.meta.example.server_communication_merged.BackendServer = null
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var resetData_3: scala.Any = null;
  private val resetData_4 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_5: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_6: scala.Long = 0L;
  private var bindingMut_7: scala.Any = null;
  private var listValMut_8: meta.deep.runtime.RequestMessage = null;
  private var iterMut_9: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: scala.Double = 0.0;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Int = 0;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: java.lang.String = null;
  private var bindingMut_16: scala.Long = 0L;
  private var bindingMut_17: java.lang.String = null;
  private var bindingMut_18: java.lang.String = null;
  private var bindingMut_19: scala.Any = null;
  private var listValMut_20: meta.deep.runtime.RequestMessage = null;
  private var iterMut_21: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_22: scala.Double = 0.0;
  private var bindingMut_23: scala.Double = 0.0;
  private var bindingMut_24: java.lang.String = null;
  private var bindingMut_25: java.lang.String = null;
  private var positionVar_27: scala.Int = 0;
  
  val commands_1288 = (() => {
  val data_28 = new scala.Array[scala.Function0[scala.Unit]](163);
  data_28.update(0, (() => {
    positionVar_27 = 1;
    val x_29 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_30 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_29, 41);
    val x_31 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_32 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_31, 66);
    val x_33 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_34 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_33, 132);
    val x_35 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_34);
    val x_36 = x_35.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_32);
    val x_37 = x_36.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_30);
    resetData_1.prepend(x_37)
  }));
  data_28.update(1, (() => if (true)
    positionVar_27 = 2
  else
    positionVar_27 = 131));
  data_28.update(2, (() => {
    val x_38 = this.id;
    resetData_0 = x_38;
    val x_39 = resetData_0;
    val x_40 = x_39.asInstanceOf[scala.Long];
    bindingMut_16 = x_40;
    val x_41 = bindingMut_16;
    val x_42 = x_41.asInstanceOf[scala.Long];
    val x_43 = "Hello world! Backend ".+(x_42);
    resetData_0 = x_43;
    val x_44 = resetData_0;
    val x_45 = x_44.asInstanceOf[java.lang.String];
    bindingMut_15 = x_45;
    val x_46 = bindingMut_15;
    val x_47 = x_46.asInstanceOf[java.lang.String];
    val x_48 = x_47.+(" Turn ");
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[java.lang.String];
    bindingMut_14 = x_50;
    val x_51 = this.currentTurn;
    resetData_0 = x_51;
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.Int];
    bindingMut_13 = x_53;
    val x_54 = bindingMut_13;
    val x_55 = x_54.asInstanceOf[scala.Int];
    val x_56 = bindingMut_14;
    val x_57 = x_56.asInstanceOf[java.lang.String];
    val x_58 = x_57.+(x_55);
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[java.lang.String];
    bindingMut_12 = x_60;
    val x_61 = bindingMut_12;
    val x_62 = x_61.asInstanceOf[java.lang.String];
    scala.Predef.println(x_62);
    resetData_0 = ();
    val x_63 = 1.toDouble;
    resetData_0 = x_63;
    val x_64 = resetData_0;
    val x_65 = x_64.asInstanceOf[scala.Double];
    bindingMut_11 = x_65;
    resetData_0 = 0.0;
    val x_66 = resetData_0;
    val x_67 = x_66.asInstanceOf[scala.Double];
    bindingMut_10 = x_67;
    val x_68 = meta.deep.runtime.Actor.proceedLabel;
    val x_69 = x_68("turn");
    val x_70 = bindingMut_10;
    val x_71 = x_70.asInstanceOf[scala.Double];
    val x_72 = x_71.+(x_69);
    resetData_0 = x_72;
    val x_73 = resetData_0;
    val x_74 = x_73.asInstanceOf[scala.Double];
    bindingMut_10 = x_74;
    val x_75 = meta.deep.runtime.Actor.labelVals("turn");
    val x_76 = bindingMut_10;
    val x_77 = x_76.asInstanceOf[scala.Double];
    val x_78 = bindingMut_11;
    val x_79 = x_78.asInstanceOf[scala.Double];
    val x_80 = x_79.-(x_77);
    x_75.append(x_80);
    resetData_0 = ();
    positionVar_27 = 3
  }));
  data_28.update(3, (() => {
    positionVar_27 = 4;
    val x_81 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_82 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_81, 86);
    val x_83 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_84 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_83, 105);
    val x_85 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_86 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_85, 26);
    val x_87 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_88 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_87, 149);
    val x_89 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_90 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_89, 127);
    val x_91 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_90);
    val x_92 = x_91.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_88);
    val x_93 = x_92.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_86);
    val x_94 = x_93.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_84);
    val x_95 = x_94.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_82);
    resetData_4.prepend(x_95)
  }));
  data_28.update(4, (() => if (true)
    positionVar_27 = 5
  else
    positionVar_27 = 130));
  data_28.update(5, (() => {
    positionVar_27 = 6;
    val x_96 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_97 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_96, 83);
    val x_98 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_99 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_98, 102);
    val x_100 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_101 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_100, 23);
    val x_102 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_103 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_102, 137);
    val x_104 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_105 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_104, 7);
    val x_106 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_105);
    val x_107 = x_106.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_103);
    val x_108 = x_107.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_101);
    val x_109 = x_108.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_99);
    val x_110 = x_109.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_97);
    resetData_4.prepend(x_110)
  }));
  data_28.update(6, (() => {
    val x_111 = java.lang.System.nanoTime();
    resetData_3 = x_111;
    val x_112 = resetData_3;
    val x_113 = x_112.asInstanceOf[scala.Long];
    bindingMut_6 = x_113;
    val x_114 = bindingMut_6;
    val x_115 = x_114.asInstanceOf[scala.Long];
    val x_116 = x_115.toString();
    resetData_3 = x_116;
    val x_117 = resetData_3;
    val x_118 = x_117.asInstanceOf[java.lang.String];
    bindingMut_18 = x_118;
    val x_119 = bindingMut_18;
    val x_120 = x_119.asInstanceOf[java.lang.String];
    val x_121 = "<html>".+(x_120);
    resetData_3 = x_121;
    val x_122 = resetData_3;
    val x_123 = x_122.asInstanceOf[java.lang.String];
    bindingMut_17 = x_123;
    val x_124 = bindingMut_17;
    val x_125 = x_124.asInstanceOf[java.lang.String];
    val x_126 = x_125.+("</html>");
    resetData_3 = x_126;
    val x_127 = resetData_4.remove(0);
    val x_131 = x_127.find(((x_128: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_129 = x_128._1;
      val x_130 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
      x_129.==(x_130)
    }));
    val x_132 = x_131.get;
    val x_133 = x_132._2;
    positionVar_27 = x_133
  }));
  data_28.update(7, (() => {
    val x_134 = resetData_3;
    val x_135 = x_134.asInstanceOf[java.lang.String];
    bindingMut_25 = x_135;
    val x_136 = bindingMut_25;
    val x_137 = x_136.asInstanceOf[java.lang.String];
    val x_138 = "requestPage content is ".+(x_137);
    resetData_3 = x_138;
    val x_139 = resetData_3;
    val x_140 = x_139.asInstanceOf[java.lang.String];
    bindingMut_24 = x_140;
    val x_141 = bindingMut_24;
    val x_142 = x_141.asInstanceOf[java.lang.String];
    scala.Predef.println(x_142);
    resetData_3 = ();
    val x_143 = 1.toDouble;
    resetData_3 = x_143;
    val x_144 = resetData_3;
    val x_145 = x_144.asInstanceOf[scala.Double];
    bindingMut_23 = x_145;
    resetData_3 = 0.0;
    val x_146 = resetData_3;
    val x_147 = x_146.asInstanceOf[scala.Double];
    bindingMut_22 = x_147;
    val x_148 = meta.deep.runtime.Actor.proceedLabel;
    val x_149 = x_148("turn");
    val x_150 = bindingMut_22;
    val x_151 = x_150.asInstanceOf[scala.Double];
    val x_152 = x_151.+(x_149);
    resetData_3 = x_152;
    val x_153 = resetData_3;
    val x_154 = x_153.asInstanceOf[scala.Double];
    bindingMut_22 = x_154;
    val x_155 = meta.deep.runtime.Actor.labelVals("turn");
    val x_156 = bindingMut_22;
    val x_157 = x_156.asInstanceOf[scala.Double];
    val x_158 = bindingMut_23;
    val x_159 = x_158.asInstanceOf[scala.Double];
    val x_160 = x_159.-(x_157);
    x_155.append(x_160);
    resetData_3 = ();
    positionVar_27 = 8;
    val x_161 = currentTurn;
    val x_162 = x_161.+(1);
    currentTurn = x_162
  }));
  data_28.update(8, (() => positionVar_27 = 9));
  data_28.update(9, (() => {
    val x_163 = bindingMut_10;
    val x_164 = x_163.asInstanceOf[scala.Double];
    val x_165 = bindingMut_11;
    val x_166 = x_165.asInstanceOf[scala.Double];
    val x_167 = x_164.<(x_166);
    if (x_167)
      positionVar_27 = 10
    else
      positionVar_27 = 57
  }));
  data_28.update(10, (() => {
    val x_168 = meta.deep.runtime.Actor.proceedLabel;
    val x_169 = x_168("turn");
    val x_170 = bindingMut_10;
    val x_171 = x_170.asInstanceOf[scala.Double];
    val x_172 = x_171.+(x_169);
    resetData_0 = x_172;
    val x_173 = resetData_0;
    val x_174 = x_173.asInstanceOf[scala.Double];
    bindingMut_10 = x_174;
    val x_175 = meta.deep.runtime.Actor.labelVals("turn");
    val x_176 = bindingMut_10;
    val x_177 = x_176.asInstanceOf[scala.Double];
    val x_178 = bindingMut_11;
    val x_179 = x_178.asInstanceOf[scala.Double];
    val x_180 = x_179.-(x_177);
    x_175.append(x_180);
    resetData_0 = ();
    positionVar_27 = 11
  }));
  data_28.update(11, (() => {
    val x_181 = bindingMut_22;
    val x_182 = x_181.asInstanceOf[scala.Double];
    val x_183 = bindingMut_23;
    val x_184 = x_183.asInstanceOf[scala.Double];
    val x_185 = x_182.<(x_184);
    if (x_185)
      positionVar_27 = 12
    else
      positionVar_27 = 14
  }));
  data_28.update(12, (() => {
    val x_186 = meta.deep.runtime.Actor.proceedLabel;
    val x_187 = x_186("turn");
    val x_188 = bindingMut_22;
    val x_189 = x_188.asInstanceOf[scala.Double];
    val x_190 = x_189.+(x_187);
    resetData_3 = x_190;
    val x_191 = resetData_3;
    val x_192 = x_191.asInstanceOf[scala.Double];
    bindingMut_22 = x_192;
    val x_193 = meta.deep.runtime.Actor.labelVals("turn");
    val x_194 = bindingMut_22;
    val x_195 = x_194.asInstanceOf[scala.Double];
    val x_196 = bindingMut_23;
    val x_197 = x_196.asInstanceOf[scala.Double];
    val x_198 = x_197.-(x_195);
    x_193.append(x_198);
    resetData_3 = ();
    positionVar_27 = 13;
    val x_199 = currentTurn;
    val x_200 = x_199.+(1);
    currentTurn = x_200
  }));
  data_28.update(13, (() => positionVar_27 = 9));
  data_28.update(14, (() => {
    val x_201 = bindingMut_22;
    val x_202 = x_201.asInstanceOf[scala.Double];
    val x_203 = bindingMut_23;
    val x_204 = x_203.asInstanceOf[scala.Double];
    val x_205 = x_202.<(x_204);
    val x_206 = x_205.`unary_!`;
    if (x_206)
      {
        val x_207 = this.popRequestMessages;
        val x_208 = x_207.iterator;
        iterMut_21 = x_208;
        positionVar_27 = 15
      }
    else
      ()
  }));
  data_28.update(15, (() => {
    val x_209 = iterMut_21;
    val x_210 = x_209.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_211 = x_210.hasNext;
    if (x_211)
      {
        val x_212 = iterMut_21;
        val x_213 = x_212.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_214 = x_213.next();
        listValMut_20 = x_214;
        positionVar_27 = 16
      }
    else
      positionVar_27 = 54
  }));
  data_28.update(16, (() => {
    val x_215 = listValMut_20;
    val x_216 = x_215.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_217 = x_216.methodId;
    val x_218 = x_217.==(2);
    val x_219 = x_218.`unary_!`;
    if (x_219)
      positionVar_27 = 17
    else
      positionVar_27 = 53
  }));
  data_28.update(17, (() => {
    val x_220 = listValMut_20;
    val x_221 = x_220.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_222 = x_221.methodId;
    val x_223 = x_222.==(3);
    val x_224 = x_223.`unary_!`;
    if (x_224)
      {
        val x_225 = listValMut_20;
        val x_226 = x_225.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_227 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_226);
        val x_228 = this.addReceiveMessages(x_227);
        resetData_3 = x_228;
        positionVar_27 = 18
      }
    else
      positionVar_27 = 19
  }));
  data_28.update(18, (() => positionVar_27 = 15));
  data_28.update(19, (() => {
    val x_229 = listValMut_20;
    val x_230 = x_229.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_231 = x_230.methodId;
    val x_232 = x_231.==(3);
    if (x_232)
      positionVar_27 = 20
    else
      ();
    val x_233 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_234 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_233, 88);
    val x_235 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_236 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_235, 107);
    val x_237 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_238 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_237, 52);
    val x_239 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_240 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_239, 151);
    val x_241 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_242 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_241, 129);
    val x_243 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_242);
    val x_244 = x_243.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_240);
    val x_245 = x_244.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_238);
    val x_246 = x_245.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_236);
    val x_247 = x_246.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_234);
    resetData_4.prepend(x_247)
  }));
  data_28.update(20, (() => if (true)
    positionVar_27 = 21
  else
    positionVar_27 = 25));
  data_28.update(21, (() => {
    positionVar_27 = 22;
    val x_248 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_249 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_248, 83);
    val x_250 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_251 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_250, 102);
    val x_252 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_253 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_252, 23);
    val x_254 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_255 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_254, 137);
    val x_256 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_257 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_256, 7);
    val x_258 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_257);
    val x_259 = x_258.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_255);
    val x_260 = x_259.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_253);
    val x_261 = x_260.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_251);
    val x_262 = x_261.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_249);
    resetData_4.prepend(x_262)
  }));
  data_28.update(22, (() => {
    val x_263 = java.lang.System.nanoTime();
    resetData_3 = x_263;
    val x_264 = resetData_3;
    val x_265 = x_264.asInstanceOf[scala.Long];
    bindingMut_6 = x_265;
    val x_266 = bindingMut_6;
    val x_267 = x_266.asInstanceOf[scala.Long];
    val x_268 = x_267.toString();
    resetData_3 = x_268;
    val x_269 = resetData_3;
    val x_270 = x_269.asInstanceOf[java.lang.String];
    bindingMut_18 = x_270;
    val x_271 = bindingMut_18;
    val x_272 = x_271.asInstanceOf[java.lang.String];
    val x_273 = "<html>".+(x_272);
    resetData_3 = x_273;
    val x_274 = resetData_3;
    val x_275 = x_274.asInstanceOf[java.lang.String];
    bindingMut_17 = x_275;
    val x_276 = bindingMut_17;
    val x_277 = x_276.asInstanceOf[java.lang.String];
    val x_278 = x_277.+("</html>");
    resetData_3 = x_278;
    val x_279 = resetData_4.remove(0);
    val x_283 = x_279.find(((x_280: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_281 = x_280._1;
      val x_282 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
      x_281.==(x_282)
    }));
    val x_284 = x_283.get;
    val x_285 = x_284._2;
    positionVar_27 = x_285
  }));
  data_28.update(23, (() => {
    val x_286 = resetData_3;
    val x_287 = x_286.asInstanceOf[java.lang.String];
    bindingMut_25 = x_287;
    val x_288 = bindingMut_25;
    val x_289 = x_288.asInstanceOf[java.lang.String];
    val x_290 = "requestPage content is ".+(x_289);
    resetData_3 = x_290;
    val x_291 = resetData_3;
    val x_292 = x_291.asInstanceOf[java.lang.String];
    bindingMut_24 = x_292;
    val x_293 = bindingMut_24;
    val x_294 = x_293.asInstanceOf[java.lang.String];
    scala.Predef.println(x_294);
    resetData_3 = ();
    val x_295 = 1.toDouble;
    resetData_3 = x_295;
    val x_296 = resetData_3;
    val x_297 = x_296.asInstanceOf[scala.Double];
    bindingMut_23 = x_297;
    resetData_3 = 0.0;
    val x_298 = resetData_3;
    val x_299 = x_298.asInstanceOf[scala.Double];
    bindingMut_22 = x_299;
    positionVar_27 = 12
  }));
  data_28.update(24, (() => {
    val x_300 = resetData_3;
    val x_301 = x_300.asInstanceOf[scala.Any];
    bindingMut_19 = x_301;
    val x_302 = bindingMut_19;
    val x_303 = x_302.asInstanceOf[scala.Any];
    val x_304 = listValMut_20;
    val x_305 = x_304.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_305.reply(this, x_303);
    resetData_3 = ();
    positionVar_27 = 18
  }));
  data_28.update(25, (() => {
    val x_306 = true.`unary_!`;
    if (x_306)
      {
        val x_307 = resetData_4.remove(0);
        val x_311 = x_307.find(((x_308: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_309 = x_308._1;
          val x_310 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
          x_309.==(x_310)
        }));
        val x_312 = x_311.get;
        val x_313 = x_312._2;
        positionVar_27 = x_313
      }
    else
      ()
  }));
  data_28.update(26, (() => {
    positionVar_27 = 27;
    val x_314 = currentTurn;
    val x_315 = x_314.+(1);
    currentTurn = x_315
  }));
  data_28.update(27, (() => positionVar_27 = 28));
  data_28.update(28, (() => {
    val x_316 = bindingMut_10;
    val x_317 = x_316.asInstanceOf[scala.Double];
    val x_318 = bindingMut_11;
    val x_319 = x_318.asInstanceOf[scala.Double];
    val x_320 = x_317.<(x_319);
    if (x_320)
      positionVar_27 = 29
    else
      positionVar_27 = 32
  }));
  data_28.update(29, (() => {
    val x_321 = meta.deep.runtime.Actor.proceedLabel;
    val x_322 = x_321("turn");
    val x_323 = bindingMut_10;
    val x_324 = x_323.asInstanceOf[scala.Double];
    val x_325 = x_324.+(x_322);
    resetData_0 = x_325;
    val x_326 = resetData_0;
    val x_327 = x_326.asInstanceOf[scala.Double];
    bindingMut_10 = x_327;
    val x_328 = meta.deep.runtime.Actor.labelVals("turn");
    val x_329 = bindingMut_10;
    val x_330 = x_329.asInstanceOf[scala.Double];
    val x_331 = bindingMut_11;
    val x_332 = x_331.asInstanceOf[scala.Double];
    val x_333 = x_332.-(x_330);
    x_328.append(x_333);
    resetData_0 = ();
    positionVar_27 = 30
  }));
  data_28.update(30, (() => {
    positionVar_27 = 31;
    val x_334 = currentTurn;
    val x_335 = x_334.+(1);
    currentTurn = x_335
  }));
  data_28.update(31, (() => positionVar_27 = 28));
  data_28.update(32, (() => {
    val x_336 = bindingMut_10;
    val x_337 = x_336.asInstanceOf[scala.Double];
    val x_338 = bindingMut_11;
    val x_339 = x_338.asInstanceOf[scala.Double];
    val x_340 = x_337.<(x_339);
    val x_341 = x_340.`unary_!`;
    if (x_341)
      {
        val x_342 = this.popRequestMessages;
        val x_343 = x_342.iterator;
        iterMut_9 = x_343;
        positionVar_27 = 33
      }
    else
      ()
  }));
  data_28.update(33, (() => {
    val x_344 = iterMut_9;
    val x_345 = x_344.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_346 = x_345.hasNext;
    if (x_346)
      {
        val x_347 = iterMut_9;
        val x_348 = x_347.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_349 = x_348.next();
        listValMut_8 = x_349;
        positionVar_27 = 34
      }
    else
      positionVar_27 = 49
  }));
  data_28.update(34, (() => {
    val x_350 = listValMut_8;
    val x_351 = x_350.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_352 = x_351.methodId;
    val x_353 = x_352.==(0);
    val x_354 = x_353.`unary_!`;
    if (x_354)
      positionVar_27 = 35
    else
      positionVar_27 = 48
  }));
  data_28.update(35, (() => {
    val x_355 = listValMut_8;
    val x_356 = x_355.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_357 = x_356.methodId;
    val x_358 = x_357.==(1);
    val x_359 = x_358.`unary_!`;
    if (x_359)
      {
        val x_360 = listValMut_8;
        val x_361 = x_360.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_362 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_361);
        val x_363 = this.addReceiveMessages(x_362);
        resetData_0 = x_363;
        positionVar_27 = 36
      }
    else
      positionVar_27 = 37
  }));
  data_28.update(36, (() => positionVar_27 = 33));
  data_28.update(37, (() => {
    val x_364 = listValMut_8;
    val x_365 = x_364.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_366 = x_365.methodId;
    val x_367 = x_366.==(1);
    if (x_367)
      positionVar_27 = 38
    else
      ();
    val x_368 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_369 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_368, 47);
    val x_370 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_371 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_370, 112);
    val x_372 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_373 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_372, 153);
    val x_374 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_373);
    val x_375 = x_374.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_371);
    val x_376 = x_375.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_369);
    resetData_1.prepend(x_376)
  }));
  data_28.update(38, (() => if (true)
    positionVar_27 = 39
  else
    positionVar_27 = 40));
  data_28.update(39, (() => {
    val x_377 = this.id;
    resetData_0 = x_377;
    val x_378 = resetData_0;
    val x_379 = x_378.asInstanceOf[scala.Long];
    bindingMut_16 = x_379;
    val x_380 = bindingMut_16;
    val x_381 = x_380.asInstanceOf[scala.Long];
    val x_382 = "Hello world! Backend ".+(x_381);
    resetData_0 = x_382;
    val x_383 = resetData_0;
    val x_384 = x_383.asInstanceOf[java.lang.String];
    bindingMut_15 = x_384;
    val x_385 = bindingMut_15;
    val x_386 = x_385.asInstanceOf[java.lang.String];
    val x_387 = x_386.+(" Turn ");
    resetData_0 = x_387;
    val x_388 = resetData_0;
    val x_389 = x_388.asInstanceOf[java.lang.String];
    bindingMut_14 = x_389;
    val x_390 = this.currentTurn;
    resetData_0 = x_390;
    val x_391 = resetData_0;
    val x_392 = x_391.asInstanceOf[scala.Int];
    bindingMut_13 = x_392;
    val x_393 = bindingMut_13;
    val x_394 = x_393.asInstanceOf[scala.Int];
    val x_395 = bindingMut_14;
    val x_396 = x_395.asInstanceOf[java.lang.String];
    val x_397 = x_396.+(x_394);
    resetData_0 = x_397;
    val x_398 = resetData_0;
    val x_399 = x_398.asInstanceOf[java.lang.String];
    bindingMut_12 = x_399;
    val x_400 = bindingMut_12;
    val x_401 = x_400.asInstanceOf[java.lang.String];
    scala.Predef.println(x_401);
    resetData_0 = ();
    val x_402 = 1.toDouble;
    resetData_0 = x_402;
    val x_403 = resetData_0;
    val x_404 = x_403.asInstanceOf[scala.Double];
    bindingMut_11 = x_404;
    resetData_0 = 0.0;
    val x_405 = resetData_0;
    val x_406 = x_405.asInstanceOf[scala.Double];
    bindingMut_10 = x_406;
    positionVar_27 = 29
  }));
  data_28.update(40, (() => {
    val x_407 = true.`unary_!`;
    if (x_407)
      {
        val x_408 = resetData_1.remove(0);
        val x_412 = x_408.find(((x_409: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_410 = x_409._1;
          val x_411 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
          x_410.==(x_411)
        }));
        val x_413 = x_412.get;
        val x_414 = x_413._2;
        positionVar_27 = x_414
      }
    else
      ()
  }));
  data_28.update(41, (() => positionVar_27 = 42));
  data_28.update(42, (() => {
    positionVar_27 = 43;
    val x_415 = currentTurn;
    val x_416 = x_415.+(1);
    currentTurn = x_416
  }));
  data_28.update(43, (() => positionVar_27 = 44));
  data_28.update(44, (() => positionVar_27 = 45));
  data_28.update(45, (() => {
    positionVar_27 = 46;
    val x_417 = currentTurn;
    val x_418 = x_417.+(1);
    currentTurn = x_418
  }));
  data_28.update(46, (() => positionVar_27 = 44));
  data_28.update(47, (() => {
    val x_419 = resetData_0;
    val x_420 = x_419.asInstanceOf[scala.Any];
    bindingMut_7 = x_420;
    val x_421 = bindingMut_7;
    val x_422 = x_421.asInstanceOf[scala.Any];
    val x_423 = listValMut_8;
    val x_424 = x_423.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_424.reply(this, x_422);
    resetData_0 = ();
    positionVar_27 = 36
  }));
  data_28.update(48, (() => {
    val x_425 = listValMut_8;
    val x_426 = x_425.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_427 = x_426.methodId;
    val x_428 = x_427.==(0);
    if (x_428)
      {
        val x_429 = java.lang.System.nanoTime();
        resetData_0 = x_429;
        val x_430 = resetData_0;
        val x_431 = x_430.asInstanceOf[scala.Long];
        bindingMut_6 = x_431;
        val x_432 = bindingMut_6;
        val x_433 = x_432.asInstanceOf[scala.Long];
        val x_434 = x_433.toString();
        resetData_0 = x_434;
        val x_435 = resetData_0;
        val x_436 = x_435.asInstanceOf[scala.Any];
        bindingMut_7 = x_436;
        val x_437 = bindingMut_7;
        val x_438 = x_437.asInstanceOf[scala.Any];
        val x_439 = listValMut_8;
        val x_440 = x_439.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_440.reply(this, x_438);
        resetData_0 = ();
        positionVar_27 = 36
      }
    else
      ()
  }));
  data_28.update(49, (() => {
    val x_441 = iterMut_9;
    val x_442 = x_441.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_443 = x_442.hasNext;
    val x_444 = x_443.`unary_!`;
    if (x_444)
      positionVar_27 = 50
    else
      ()
  }));
  data_28.update(50, (() => if (true)
    positionVar_27 = 39
  else
    positionVar_27 = 51));
  data_28.update(51, (() => {
    val x_445 = true.`unary_!`;
    if (x_445)
      {
        val x_446 = resetData_1.remove(0);
        val x_450 = x_446.find(((x_447: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_448 = x_447._1;
          val x_449 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
          x_448.==(x_449)
        }));
        val x_451 = x_450.get;
        val x_452 = x_451._2;
        positionVar_27 = x_452
      }
    else
      ()
  }));
  data_28.update(52, (() => {
    val x_453 = resetData_3;
    val x_454 = x_453.asInstanceOf[scala.Any];
    bindingMut_19 = x_454;
    val x_455 = bindingMut_19;
    val x_456 = x_455.asInstanceOf[scala.Any];
    val x_457 = listValMut_20;
    val x_458 = x_457.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_458.reply(this, x_456);
    resetData_3 = ();
    positionVar_27 = 18
  }));
  data_28.update(53, (() => {
    val x_459 = listValMut_20;
    val x_460 = x_459.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_461 = x_460.methodId;
    val x_462 = x_461.==(2);
    if (x_462)
      positionVar_27 = 22
    else
      ();
    val x_463 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_464 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_463, 84);
    val x_465 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_466 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_465, 103);
    val x_467 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_468 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_467, 24);
    val x_469 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_470 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_469, 139);
    val x_471 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_472 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_471, 117);
    val x_473 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_472);
    val x_474 = x_473.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_470);
    val x_475 = x_474.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_468);
    val x_476 = x_475.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_466);
    val x_477 = x_476.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_464);
    resetData_4.prepend(x_477)
  }));
  data_28.update(54, (() => {
    val x_478 = iterMut_21;
    val x_479 = x_478.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_480 = x_479.hasNext;
    val x_481 = x_480.`unary_!`;
    if (x_481)
      positionVar_27 = 55
    else
      ()
  }));
  data_28.update(55, (() => if (true)
    positionVar_27 = 21
  else
    positionVar_27 = 56));
  data_28.update(56, (() => {
    val x_482 = true.`unary_!`;
    if (x_482)
      {
        val x_483 = resetData_4.remove(0);
        val x_487 = x_483.find(((x_484: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_485 = x_484._1;
          val x_486 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
          x_485.==(x_486)
        }));
        val x_488 = x_487.get;
        val x_489 = x_488._2;
        positionVar_27 = x_489
      }
    else
      ()
  }));
  data_28.update(57, (() => {
    val x_490 = bindingMut_10;
    val x_491 = x_490.asInstanceOf[scala.Double];
    val x_492 = bindingMut_11;
    val x_493 = x_492.asInstanceOf[scala.Double];
    val x_494 = x_491.<(x_493);
    val x_495 = x_494.`unary_!`;
    if (x_495)
      {
        val x_496 = this.popRequestMessages;
        val x_497 = x_496.iterator;
        iterMut_9 = x_497;
        positionVar_27 = 58
      }
    else
      ()
  }));
  data_28.update(58, (() => {
    val x_498 = iterMut_9;
    val x_499 = x_498.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_500 = x_499.hasNext;
    if (x_500)
      {
        val x_501 = iterMut_9;
        val x_502 = x_501.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_503 = x_502.next();
        listValMut_8 = x_503;
        positionVar_27 = 59
      }
    else
      positionVar_27 = 114
  }));
  data_28.update(59, (() => {
    val x_504 = listValMut_8;
    val x_505 = x_504.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_506 = x_505.methodId;
    val x_507 = x_506.==(0);
    val x_508 = x_507.`unary_!`;
    if (x_508)
      positionVar_27 = 60
    else
      positionVar_27 = 113
  }));
  data_28.update(60, (() => {
    val x_509 = listValMut_8;
    val x_510 = x_509.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_511 = x_510.methodId;
    val x_512 = x_511.==(1);
    val x_513 = x_512.`unary_!`;
    if (x_513)
      {
        val x_514 = listValMut_8;
        val x_515 = x_514.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_516 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_515);
        val x_517 = this.addReceiveMessages(x_516);
        resetData_0 = x_517;
        positionVar_27 = 61
      }
    else
      positionVar_27 = 62
  }));
  data_28.update(61, (() => positionVar_27 = 58));
  data_28.update(62, (() => {
    val x_518 = listValMut_8;
    val x_519 = x_518.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_520 = x_519.methodId;
    val x_521 = x_520.==(1);
    if (x_521)
      positionVar_27 = 63
    else
      ();
    val x_522 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_523 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_522, 47);
    val x_524 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_525 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_524, 112);
    val x_526 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_527 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_526, 153);
    val x_528 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_527);
    val x_529 = x_528.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_525);
    val x_530 = x_529.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_523);
    resetData_1.prepend(x_530)
  }));
  data_28.update(63, (() => if (true)
    positionVar_27 = 64
  else
    positionVar_27 = 65));
  data_28.update(64, (() => {
    val x_531 = this.id;
    resetData_0 = x_531;
    val x_532 = resetData_0;
    val x_533 = x_532.asInstanceOf[scala.Long];
    bindingMut_16 = x_533;
    val x_534 = bindingMut_16;
    val x_535 = x_534.asInstanceOf[scala.Long];
    val x_536 = "Hello world! Backend ".+(x_535);
    resetData_0 = x_536;
    val x_537 = resetData_0;
    val x_538 = x_537.asInstanceOf[java.lang.String];
    bindingMut_15 = x_538;
    val x_539 = bindingMut_15;
    val x_540 = x_539.asInstanceOf[java.lang.String];
    val x_541 = x_540.+(" Turn ");
    resetData_0 = x_541;
    val x_542 = resetData_0;
    val x_543 = x_542.asInstanceOf[java.lang.String];
    bindingMut_14 = x_543;
    val x_544 = this.currentTurn;
    resetData_0 = x_544;
    val x_545 = resetData_0;
    val x_546 = x_545.asInstanceOf[scala.Int];
    bindingMut_13 = x_546;
    val x_547 = bindingMut_13;
    val x_548 = x_547.asInstanceOf[scala.Int];
    val x_549 = bindingMut_14;
    val x_550 = x_549.asInstanceOf[java.lang.String];
    val x_551 = x_550.+(x_548);
    resetData_0 = x_551;
    val x_552 = resetData_0;
    val x_553 = x_552.asInstanceOf[java.lang.String];
    bindingMut_12 = x_553;
    val x_554 = bindingMut_12;
    val x_555 = x_554.asInstanceOf[java.lang.String];
    scala.Predef.println(x_555);
    resetData_0 = ();
    val x_556 = 1.toDouble;
    resetData_0 = x_556;
    val x_557 = resetData_0;
    val x_558 = x_557.asInstanceOf[scala.Double];
    bindingMut_11 = x_558;
    resetData_0 = 0.0;
    val x_559 = resetData_0;
    val x_560 = x_559.asInstanceOf[scala.Double];
    bindingMut_10 = x_560;
    positionVar_27 = 10
  }));
  data_28.update(65, (() => {
    val x_561 = true.`unary_!`;
    if (x_561)
      {
        val x_562 = resetData_1.remove(0);
        val x_566 = x_562.find(((x_563: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_564 = x_563._1;
          val x_565 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
          x_564.==(x_565)
        }));
        val x_567 = x_566.get;
        val x_568 = x_567._2;
        positionVar_27 = x_568
      }
    else
      ()
  }));
  data_28.update(66, (() => positionVar_27 = 67));
  data_28.update(67, (() => {
    val x_569 = bindingMut_22;
    val x_570 = x_569.asInstanceOf[scala.Double];
    val x_571 = bindingMut_23;
    val x_572 = x_571.asInstanceOf[scala.Double];
    val x_573 = x_570.<(x_572);
    if (x_573)
      positionVar_27 = 68
    else
      positionVar_27 = 93
  }));
  data_28.update(68, (() => {
    val x_574 = meta.deep.runtime.Actor.proceedLabel;
    val x_575 = x_574("turn");
    val x_576 = bindingMut_22;
    val x_577 = x_576.asInstanceOf[scala.Double];
    val x_578 = x_577.+(x_575);
    resetData_3 = x_578;
    val x_579 = resetData_3;
    val x_580 = x_579.asInstanceOf[scala.Double];
    bindingMut_22 = x_580;
    val x_581 = meta.deep.runtime.Actor.labelVals("turn");
    val x_582 = bindingMut_22;
    val x_583 = x_582.asInstanceOf[scala.Double];
    val x_584 = bindingMut_23;
    val x_585 = x_584.asInstanceOf[scala.Double];
    val x_586 = x_585.-(x_583);
    x_581.append(x_586);
    resetData_3 = ();
    positionVar_27 = 69;
    val x_587 = currentTurn;
    val x_588 = x_587.+(1);
    currentTurn = x_588
  }));
  data_28.update(69, (() => positionVar_27 = 70));
  data_28.update(70, (() => positionVar_27 = 71));
  data_28.update(71, (() => {
    val x_589 = bindingMut_22;
    val x_590 = x_589.asInstanceOf[scala.Double];
    val x_591 = bindingMut_23;
    val x_592 = x_591.asInstanceOf[scala.Double];
    val x_593 = x_590.<(x_592);
    if (x_593)
      positionVar_27 = 72
    else
      positionVar_27 = 74
  }));
  data_28.update(72, (() => {
    val x_594 = meta.deep.runtime.Actor.proceedLabel;
    val x_595 = x_594("turn");
    val x_596 = bindingMut_22;
    val x_597 = x_596.asInstanceOf[scala.Double];
    val x_598 = x_597.+(x_595);
    resetData_3 = x_598;
    val x_599 = resetData_3;
    val x_600 = x_599.asInstanceOf[scala.Double];
    bindingMut_22 = x_600;
    val x_601 = meta.deep.runtime.Actor.labelVals("turn");
    val x_602 = bindingMut_22;
    val x_603 = x_602.asInstanceOf[scala.Double];
    val x_604 = bindingMut_23;
    val x_605 = x_604.asInstanceOf[scala.Double];
    val x_606 = x_605.-(x_603);
    x_601.append(x_606);
    resetData_3 = ();
    positionVar_27 = 73;
    val x_607 = currentTurn;
    val x_608 = x_607.+(1);
    currentTurn = x_608
  }));
  data_28.update(73, (() => positionVar_27 = 70));
  data_28.update(74, (() => {
    val x_609 = bindingMut_22;
    val x_610 = x_609.asInstanceOf[scala.Double];
    val x_611 = bindingMut_23;
    val x_612 = x_611.asInstanceOf[scala.Double];
    val x_613 = x_610.<(x_612);
    val x_614 = x_613.`unary_!`;
    if (x_614)
      {
        val x_615 = this.popRequestMessages;
        val x_616 = x_615.iterator;
        iterMut_21 = x_616;
        positionVar_27 = 75
      }
    else
      ()
  }));
  data_28.update(75, (() => {
    val x_617 = iterMut_21;
    val x_618 = x_617.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_619 = x_618.hasNext;
    if (x_619)
      {
        val x_620 = iterMut_21;
        val x_621 = x_620.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_622 = x_621.next();
        listValMut_20 = x_622;
        positionVar_27 = 76
      }
    else
      positionVar_27 = 90
  }));
  data_28.update(76, (() => {
    val x_623 = listValMut_20;
    val x_624 = x_623.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_625 = x_624.methodId;
    val x_626 = x_625.==(2);
    val x_627 = x_626.`unary_!`;
    if (x_627)
      positionVar_27 = 77
    else
      positionVar_27 = 89
  }));
  data_28.update(77, (() => {
    val x_628 = listValMut_20;
    val x_629 = x_628.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_630 = x_629.methodId;
    val x_631 = x_630.==(3);
    val x_632 = x_631.`unary_!`;
    if (x_632)
      {
        val x_633 = listValMut_20;
        val x_634 = x_633.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_635 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_634);
        val x_636 = this.addReceiveMessages(x_635);
        resetData_3 = x_636;
        positionVar_27 = 78
      }
    else
      positionVar_27 = 79
  }));
  data_28.update(78, (() => positionVar_27 = 75));
  data_28.update(79, (() => {
    val x_637 = listValMut_20;
    val x_638 = x_637.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_639 = x_638.methodId;
    val x_640 = x_639.==(3);
    if (x_640)
      positionVar_27 = 80
    else
      ();
    val x_641 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_642 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_641, 88);
    val x_643 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_644 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_643, 107);
    val x_645 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_646 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_645, 52);
    val x_647 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_648 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_647, 151);
    val x_649 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_650 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_649, 129);
    val x_651 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_650);
    val x_652 = x_651.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_648);
    val x_653 = x_652.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_646);
    val x_654 = x_653.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_644);
    val x_655 = x_654.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_642);
    resetData_4.prepend(x_655)
  }));
  data_28.update(80, (() => if (true)
    positionVar_27 = 81
  else
    positionVar_27 = 85));
  data_28.update(81, (() => {
    positionVar_27 = 82;
    val x_656 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_657 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_656, 83);
    val x_658 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_659 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_658, 102);
    val x_660 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_661 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_660, 23);
    val x_662 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_663 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_662, 137);
    val x_664 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_665 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_664, 7);
    val x_666 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_665);
    val x_667 = x_666.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_663);
    val x_668 = x_667.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_661);
    val x_669 = x_668.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_659);
    val x_670 = x_669.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_657);
    resetData_4.prepend(x_670)
  }));
  data_28.update(82, (() => {
    val x_671 = java.lang.System.nanoTime();
    resetData_3 = x_671;
    val x_672 = resetData_3;
    val x_673 = x_672.asInstanceOf[scala.Long];
    bindingMut_6 = x_673;
    val x_674 = bindingMut_6;
    val x_675 = x_674.asInstanceOf[scala.Long];
    val x_676 = x_675.toString();
    resetData_3 = x_676;
    val x_677 = resetData_3;
    val x_678 = x_677.asInstanceOf[java.lang.String];
    bindingMut_18 = x_678;
    val x_679 = bindingMut_18;
    val x_680 = x_679.asInstanceOf[java.lang.String];
    val x_681 = "<html>".+(x_680);
    resetData_3 = x_681;
    val x_682 = resetData_3;
    val x_683 = x_682.asInstanceOf[java.lang.String];
    bindingMut_17 = x_683;
    val x_684 = bindingMut_17;
    val x_685 = x_684.asInstanceOf[java.lang.String];
    val x_686 = x_685.+("</html>");
    resetData_3 = x_686;
    val x_687 = resetData_4.remove(0);
    val x_691 = x_687.find(((x_688: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_689 = x_688._1;
      val x_690 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
      x_689.==(x_690)
    }));
    val x_692 = x_691.get;
    val x_693 = x_692._2;
    positionVar_27 = x_693
  }));
  data_28.update(83, (() => {
    val x_694 = resetData_3;
    val x_695 = x_694.asInstanceOf[java.lang.String];
    bindingMut_25 = x_695;
    val x_696 = bindingMut_25;
    val x_697 = x_696.asInstanceOf[java.lang.String];
    val x_698 = "requestPage content is ".+(x_697);
    resetData_3 = x_698;
    val x_699 = resetData_3;
    val x_700 = x_699.asInstanceOf[java.lang.String];
    bindingMut_24 = x_700;
    val x_701 = bindingMut_24;
    val x_702 = x_701.asInstanceOf[java.lang.String];
    scala.Predef.println(x_702);
    resetData_3 = ();
    val x_703 = 1.toDouble;
    resetData_3 = x_703;
    val x_704 = resetData_3;
    val x_705 = x_704.asInstanceOf[scala.Double];
    bindingMut_23 = x_705;
    resetData_3 = 0.0;
    val x_706 = resetData_3;
    val x_707 = x_706.asInstanceOf[scala.Double];
    bindingMut_22 = x_707;
    positionVar_27 = 72
  }));
  data_28.update(84, (() => {
    val x_708 = resetData_3;
    val x_709 = x_708.asInstanceOf[scala.Any];
    bindingMut_19 = x_709;
    val x_710 = bindingMut_19;
    val x_711 = x_710.asInstanceOf[scala.Any];
    val x_712 = listValMut_20;
    val x_713 = x_712.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_713.reply(this, x_711);
    resetData_3 = ();
    positionVar_27 = 78
  }));
  data_28.update(85, (() => {
    val x_714 = true.`unary_!`;
    if (x_714)
      {
        val x_715 = resetData_4.remove(0);
        val x_719 = x_715.find(((x_716: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_717 = x_716._1;
          val x_718 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
          x_717.==(x_718)
        }));
        val x_720 = x_719.get;
        val x_721 = x_720._2;
        positionVar_27 = x_721
      }
    else
      ()
  }));
  data_28.update(86, (() => {
    positionVar_27 = 87;
    val x_722 = currentTurn;
    val x_723 = x_722.+(1);
    currentTurn = x_723
  }));
  data_28.update(87, (() => positionVar_27 = 44));
  data_28.update(88, (() => {
    val x_724 = resetData_3;
    val x_725 = x_724.asInstanceOf[scala.Any];
    bindingMut_19 = x_725;
    val x_726 = bindingMut_19;
    val x_727 = x_726.asInstanceOf[scala.Any];
    val x_728 = listValMut_20;
    val x_729 = x_728.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_729.reply(this, x_727);
    resetData_3 = ();
    positionVar_27 = 78
  }));
  data_28.update(89, (() => {
    val x_730 = listValMut_20;
    val x_731 = x_730.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_732 = x_731.methodId;
    val x_733 = x_732.==(2);
    if (x_733)
      positionVar_27 = 82
    else
      ();
    val x_734 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_735 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_734, 84);
    val x_736 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_737 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_736, 103);
    val x_738 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_739 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_738, 24);
    val x_740 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_741 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_740, 139);
    val x_742 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_743 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_742, 117);
    val x_744 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_743);
    val x_745 = x_744.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_741);
    val x_746 = x_745.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_739);
    val x_747 = x_746.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_737);
    val x_748 = x_747.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_735);
    resetData_4.prepend(x_748)
  }));
  data_28.update(90, (() => {
    val x_749 = iterMut_21;
    val x_750 = x_749.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_751 = x_750.hasNext;
    val x_752 = x_751.`unary_!`;
    if (x_752)
      positionVar_27 = 91
    else
      ()
  }));
  data_28.update(91, (() => if (true)
    positionVar_27 = 81
  else
    positionVar_27 = 92));
  data_28.update(92, (() => {
    val x_753 = true.`unary_!`;
    if (x_753)
      {
        val x_754 = resetData_4.remove(0);
        val x_758 = x_754.find(((x_755: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_756 = x_755._1;
          val x_757 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
          x_756.==(x_757)
        }));
        val x_759 = x_758.get;
        val x_760 = x_759._2;
        positionVar_27 = x_760
      }
    else
      ()
  }));
  data_28.update(93, (() => {
    val x_761 = bindingMut_22;
    val x_762 = x_761.asInstanceOf[scala.Double];
    val x_763 = bindingMut_23;
    val x_764 = x_763.asInstanceOf[scala.Double];
    val x_765 = x_762.<(x_764);
    val x_766 = x_765.`unary_!`;
    if (x_766)
      {
        val x_767 = this.popRequestMessages;
        val x_768 = x_767.iterator;
        iterMut_21 = x_768;
        positionVar_27 = 94
      }
    else
      ()
  }));
  data_28.update(94, (() => {
    val x_769 = iterMut_21;
    val x_770 = x_769.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_771 = x_770.hasNext;
    if (x_771)
      {
        val x_772 = iterMut_21;
        val x_773 = x_772.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_774 = x_773.next();
        listValMut_20 = x_774;
        positionVar_27 = 95
      }
    else
      positionVar_27 = 109
  }));
  data_28.update(95, (() => {
    val x_775 = listValMut_20;
    val x_776 = x_775.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_777 = x_776.methodId;
    val x_778 = x_777.==(2);
    val x_779 = x_778.`unary_!`;
    if (x_779)
      positionVar_27 = 96
    else
      positionVar_27 = 108
  }));
  data_28.update(96, (() => {
    val x_780 = listValMut_20;
    val x_781 = x_780.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_782 = x_781.methodId;
    val x_783 = x_782.==(3);
    val x_784 = x_783.`unary_!`;
    if (x_784)
      {
        val x_785 = listValMut_20;
        val x_786 = x_785.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_787 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_786);
        val x_788 = this.addReceiveMessages(x_787);
        resetData_3 = x_788;
        positionVar_27 = 97
      }
    else
      positionVar_27 = 98
  }));
  data_28.update(97, (() => positionVar_27 = 94));
  data_28.update(98, (() => {
    val x_789 = listValMut_20;
    val x_790 = x_789.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_791 = x_790.methodId;
    val x_792 = x_791.==(3);
    if (x_792)
      positionVar_27 = 99
    else
      ();
    val x_793 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_794 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_793, 88);
    val x_795 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_796 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_795, 107);
    val x_797 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_798 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_797, 52);
    val x_799 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_800 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_799, 151);
    val x_801 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_802 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_801, 129);
    val x_803 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_802);
    val x_804 = x_803.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_800);
    val x_805 = x_804.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_798);
    val x_806 = x_805.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_796);
    val x_807 = x_806.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_794);
    resetData_4.prepend(x_807)
  }));
  data_28.update(99, (() => if (true)
    positionVar_27 = 100
  else
    positionVar_27 = 104));
  data_28.update(100, (() => {
    positionVar_27 = 101;
    val x_808 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_809 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_808, 83);
    val x_810 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_811 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_810, 102);
    val x_812 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_813 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_812, 23);
    val x_814 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_815 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_814, 137);
    val x_816 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_817 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_816, 7);
    val x_818 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_817);
    val x_819 = x_818.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_815);
    val x_820 = x_819.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_813);
    val x_821 = x_820.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_811);
    val x_822 = x_821.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_809);
    resetData_4.prepend(x_822)
  }));
  data_28.update(101, (() => {
    val x_823 = java.lang.System.nanoTime();
    resetData_3 = x_823;
    val x_824 = resetData_3;
    val x_825 = x_824.asInstanceOf[scala.Long];
    bindingMut_6 = x_825;
    val x_826 = bindingMut_6;
    val x_827 = x_826.asInstanceOf[scala.Long];
    val x_828 = x_827.toString();
    resetData_3 = x_828;
    val x_829 = resetData_3;
    val x_830 = x_829.asInstanceOf[java.lang.String];
    bindingMut_18 = x_830;
    val x_831 = bindingMut_18;
    val x_832 = x_831.asInstanceOf[java.lang.String];
    val x_833 = "<html>".+(x_832);
    resetData_3 = x_833;
    val x_834 = resetData_3;
    val x_835 = x_834.asInstanceOf[java.lang.String];
    bindingMut_17 = x_835;
    val x_836 = bindingMut_17;
    val x_837 = x_836.asInstanceOf[java.lang.String];
    val x_838 = x_837.+("</html>");
    resetData_3 = x_838;
    val x_839 = resetData_4.remove(0);
    val x_843 = x_839.find(((x_840: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_841 = x_840._1;
      val x_842 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
      x_841.==(x_842)
    }));
    val x_844 = x_843.get;
    val x_845 = x_844._2;
    positionVar_27 = x_845
  }));
  data_28.update(102, (() => {
    val x_846 = resetData_3;
    val x_847 = x_846.asInstanceOf[java.lang.String];
    bindingMut_25 = x_847;
    val x_848 = bindingMut_25;
    val x_849 = x_848.asInstanceOf[java.lang.String];
    val x_850 = "requestPage content is ".+(x_849);
    resetData_3 = x_850;
    val x_851 = resetData_3;
    val x_852 = x_851.asInstanceOf[java.lang.String];
    bindingMut_24 = x_852;
    val x_853 = bindingMut_24;
    val x_854 = x_853.asInstanceOf[java.lang.String];
    scala.Predef.println(x_854);
    resetData_3 = ();
    val x_855 = 1.toDouble;
    resetData_3 = x_855;
    val x_856 = resetData_3;
    val x_857 = x_856.asInstanceOf[scala.Double];
    bindingMut_23 = x_857;
    resetData_3 = 0.0;
    val x_858 = resetData_3;
    val x_859 = x_858.asInstanceOf[scala.Double];
    bindingMut_22 = x_859;
    positionVar_27 = 68
  }));
  data_28.update(103, (() => {
    val x_860 = resetData_3;
    val x_861 = x_860.asInstanceOf[scala.Any];
    bindingMut_19 = x_861;
    val x_862 = bindingMut_19;
    val x_863 = x_862.asInstanceOf[scala.Any];
    val x_864 = listValMut_20;
    val x_865 = x_864.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_865.reply(this, x_863);
    resetData_3 = ();
    positionVar_27 = 97
  }));
  data_28.update(104, (() => {
    val x_866 = true.`unary_!`;
    if (x_866)
      {
        val x_867 = resetData_4.remove(0);
        val x_871 = x_867.find(((x_868: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_869 = x_868._1;
          val x_870 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
          x_869.==(x_870)
        }));
        val x_872 = x_871.get;
        val x_873 = x_872._2;
        positionVar_27 = x_873
      }
    else
      ()
  }));
  data_28.update(105, (() => {
    positionVar_27 = 106;
    val x_874 = currentTurn;
    val x_875 = x_874.+(1);
    currentTurn = x_875
  }));
  data_28.update(106, (() => positionVar_27 = 44));
  data_28.update(107, (() => {
    val x_876 = resetData_3;
    val x_877 = x_876.asInstanceOf[scala.Any];
    bindingMut_19 = x_877;
    val x_878 = bindingMut_19;
    val x_879 = x_878.asInstanceOf[scala.Any];
    val x_880 = listValMut_20;
    val x_881 = x_880.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_881.reply(this, x_879);
    resetData_3 = ();
    positionVar_27 = 97
  }));
  data_28.update(108, (() => {
    val x_882 = listValMut_20;
    val x_883 = x_882.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_884 = x_883.methodId;
    val x_885 = x_884.==(2);
    if (x_885)
      positionVar_27 = 101
    else
      ();
    val x_886 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_887 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_886, 84);
    val x_888 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_889 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_888, 103);
    val x_890 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_891 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_890, 24);
    val x_892 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_893 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_892, 139);
    val x_894 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_895 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_894, 117);
    val x_896 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_895);
    val x_897 = x_896.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_893);
    val x_898 = x_897.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_891);
    val x_899 = x_898.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_889);
    val x_900 = x_899.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_887);
    resetData_4.prepend(x_900)
  }));
  data_28.update(109, (() => {
    val x_901 = iterMut_21;
    val x_902 = x_901.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_903 = x_902.hasNext;
    val x_904 = x_903.`unary_!`;
    if (x_904)
      positionVar_27 = 110
    else
      ()
  }));
  data_28.update(110, (() => if (true)
    positionVar_27 = 100
  else
    positionVar_27 = 111));
  data_28.update(111, (() => {
    val x_905 = true.`unary_!`;
    if (x_905)
      {
        val x_906 = resetData_4.remove(0);
        val x_910 = x_906.find(((x_907: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_908 = x_907._1;
          val x_909 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
          x_908.==(x_909)
        }));
        val x_911 = x_910.get;
        val x_912 = x_911._2;
        positionVar_27 = x_912
      }
    else
      ()
  }));
  data_28.update(112, (() => {
    val x_913 = resetData_0;
    val x_914 = x_913.asInstanceOf[scala.Any];
    bindingMut_7 = x_914;
    val x_915 = bindingMut_7;
    val x_916 = x_915.asInstanceOf[scala.Any];
    val x_917 = listValMut_8;
    val x_918 = x_917.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_918.reply(this, x_916);
    resetData_0 = ();
    positionVar_27 = 61
  }));
  data_28.update(113, (() => {
    val x_919 = listValMut_8;
    val x_920 = x_919.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_921 = x_920.methodId;
    val x_922 = x_921.==(0);
    if (x_922)
      {
        val x_923 = java.lang.System.nanoTime();
        resetData_0 = x_923;
        val x_924 = resetData_0;
        val x_925 = x_924.asInstanceOf[scala.Long];
        bindingMut_6 = x_925;
        val x_926 = bindingMut_6;
        val x_927 = x_926.asInstanceOf[scala.Long];
        val x_928 = x_927.toString();
        resetData_0 = x_928;
        val x_929 = resetData_0;
        val x_930 = x_929.asInstanceOf[scala.Any];
        bindingMut_7 = x_930;
        val x_931 = bindingMut_7;
        val x_932 = x_931.asInstanceOf[scala.Any];
        val x_933 = listValMut_8;
        val x_934 = x_933.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_934.reply(this, x_932);
        resetData_0 = ();
        positionVar_27 = 61
      }
    else
      ()
  }));
  data_28.update(114, (() => {
    val x_935 = iterMut_9;
    val x_936 = x_935.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_937 = x_936.hasNext;
    val x_938 = x_937.`unary_!`;
    if (x_938)
      positionVar_27 = 115
    else
      ()
  }));
  data_28.update(115, (() => if (true)
    positionVar_27 = 64
  else
    positionVar_27 = 116));
  data_28.update(116, (() => {
    val x_939 = true.`unary_!`;
    if (x_939)
      {
        val x_940 = resetData_1.remove(0);
        val x_944 = x_940.find(((x_941: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_942 = x_941._1;
          val x_943 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
          x_942.==(x_943)
        }));
        val x_945 = x_944.get;
        val x_946 = x_945._2;
        positionVar_27 = x_946
      }
    else
      ()
  }));
  data_28.update(117, (() => {
    val x_947 = resetData_3;
    val x_948 = x_947.asInstanceOf[scala.Any];
    bindingMut_19 = x_948;
    val x_949 = bindingMut_19;
    val x_950 = x_949.asInstanceOf[scala.Any];
    val x_951 = listValMut_20;
    val x_952 = x_951.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_952.reply(this, x_950);
    resetData_3 = ();
    positionVar_27 = 118
  }));
  data_28.update(118, (() => positionVar_27 = 119));
  data_28.update(119, (() => {
    val x_953 = iterMut_21;
    val x_954 = x_953.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_955 = x_954.hasNext;
    if (x_955)
      {
        val x_956 = iterMut_21;
        val x_957 = x_956.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_958 = x_957.next();
        listValMut_20 = x_958;
        positionVar_27 = 120
      }
    else
      positionVar_27 = 124
  }));
  data_28.update(120, (() => {
    val x_959 = listValMut_20;
    val x_960 = x_959.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_961 = x_960.methodId;
    val x_962 = x_961.==(2);
    val x_963 = x_962.`unary_!`;
    if (x_963)
      positionVar_27 = 121
    else
      positionVar_27 = 123
  }));
  data_28.update(121, (() => {
    val x_964 = listValMut_20;
    val x_965 = x_964.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_966 = x_965.methodId;
    val x_967 = x_966.==(3);
    val x_968 = x_967.`unary_!`;
    if (x_968)
      {
        val x_969 = listValMut_20;
        val x_970 = x_969.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_971 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_970);
        val x_972 = this.addReceiveMessages(x_971);
        resetData_3 = x_972;
        positionVar_27 = 118
      }
    else
      positionVar_27 = 122
  }));
  data_28.update(122, (() => {
    val x_973 = listValMut_20;
    val x_974 = x_973.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_975 = x_974.methodId;
    val x_976 = x_975.==(3);
    if (x_976)
      positionVar_27 = 4
    else
      ();
    val x_977 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_978 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_977, 88);
    val x_979 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_980 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_979, 107);
    val x_981 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_982 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_981, 52);
    val x_983 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_984 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_983, 151);
    val x_985 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_986 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_985, 129);
    val x_987 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_986);
    val x_988 = x_987.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_984);
    val x_989 = x_988.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_982);
    val x_990 = x_989.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_980);
    val x_991 = x_990.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_978);
    resetData_4.prepend(x_991)
  }));
  data_28.update(123, (() => {
    val x_992 = listValMut_20;
    val x_993 = x_992.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_994 = x_993.methodId;
    val x_995 = x_994.==(2);
    if (x_995)
      positionVar_27 = 6
    else
      ();
    val x_996 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_997 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_996, 84);
    val x_998 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_999 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_998, 103);
    val x_1000 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_1001 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1000, 24);
    val x_1002 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_1003 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1002, 139);
    val x_1004 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_1005 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1004, 117);
    val x_1006 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1005);
    val x_1007 = x_1006.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1003);
    val x_1008 = x_1007.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1001);
    val x_1009 = x_1008.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_999);
    val x_1010 = x_1009.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_997);
    resetData_4.prepend(x_1010)
  }));
  data_28.update(124, (() => {
    val x_1011 = iterMut_21;
    val x_1012 = x_1011.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1013 = x_1012.hasNext;
    val x_1014 = x_1013.`unary_!`;
    if (x_1014)
      positionVar_27 = 125
    else
      ()
  }));
  data_28.update(125, (() => if (true)
    positionVar_27 = 5
  else
    positionVar_27 = 126));
  data_28.update(126, (() => {
    val x_1015 = true.`unary_!`;
    if (x_1015)
      {
        val x_1016 = resetData_4.remove(0);
        val x_1020 = x_1016.find(((x_1017: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1018 = x_1017._1;
          val x_1019 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
          x_1018.==(x_1019)
        }));
        val x_1021 = x_1020.get;
        val x_1022 = x_1021._2;
        positionVar_27 = x_1022
      }
    else
      ()
  }));
  data_28.update(127, (() => {
    positionVar_27 = 128;
    val x_1023 = currentTurn;
    val x_1024 = x_1023.+(1);
    currentTurn = x_1024
  }));
  data_28.update(128, (() => positionVar_27 = 28));
  data_28.update(129, (() => {
    val x_1025 = resetData_3;
    val x_1026 = x_1025.asInstanceOf[scala.Any];
    bindingMut_19 = x_1026;
    val x_1027 = bindingMut_19;
    val x_1028 = x_1027.asInstanceOf[scala.Any];
    val x_1029 = listValMut_20;
    val x_1030 = x_1029.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1030.reply(this, x_1028);
    resetData_3 = ();
    positionVar_27 = 118
  }));
  data_28.update(130, (() => {
    val x_1031 = true.`unary_!`;
    if (x_1031)
      {
        val x_1032 = resetData_4.remove(0);
        val x_1036 = x_1032.find(((x_1033: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1034 = x_1033._1;
          val x_1035 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
          x_1034.==(x_1035)
        }));
        val x_1037 = x_1036.get;
        val x_1038 = x_1037._2;
        positionVar_27 = x_1038
      }
    else
      ()
  }));
  data_28.update(131, (() => {
    val x_1039 = true.`unary_!`;
    if (x_1039)
      {
        val x_1040 = resetData_1.remove(0);
        val x_1044 = x_1040.find(((x_1041: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1042 = x_1041._1;
          val x_1043 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
          x_1042.==(x_1043)
        }));
        val x_1045 = x_1044.get;
        val x_1046 = x_1045._2;
        positionVar_27 = x_1046
      }
    else
      ()
  }));
  data_28.update(132, (() => positionVar_27 = 133));
  data_28.update(133, (() => {
    positionVar_27 = 134;
    val x_1047 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_1048 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1047, 86);
    val x_1049 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_1050 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1049, 105);
    val x_1051 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_1052 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1051, 26);
    val x_1053 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_1054 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1053, 149);
    val x_1055 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_1056 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1055, 127);
    val x_1057 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1056);
    val x_1058 = x_1057.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1054);
    val x_1059 = x_1058.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1052);
    val x_1060 = x_1059.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1050);
    val x_1061 = x_1060.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1048);
    resetData_4.prepend(x_1061)
  }));
  data_28.update(134, (() => if (true)
    positionVar_27 = 135
  else
    positionVar_27 = 152));
  data_28.update(135, (() => {
    positionVar_27 = 136;
    val x_1062 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_1063 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1062, 83);
    val x_1064 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_1065 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1064, 102);
    val x_1066 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_1067 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1066, 23);
    val x_1068 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_1069 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1068, 137);
    val x_1070 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_1071 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1070, 7);
    val x_1072 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1071);
    val x_1073 = x_1072.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1069);
    val x_1074 = x_1073.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1067);
    val x_1075 = x_1074.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1065);
    val x_1076 = x_1075.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1063);
    resetData_4.prepend(x_1076)
  }));
  data_28.update(136, (() => {
    val x_1077 = java.lang.System.nanoTime();
    resetData_3 = x_1077;
    val x_1078 = resetData_3;
    val x_1079 = x_1078.asInstanceOf[scala.Long];
    bindingMut_6 = x_1079;
    val x_1080 = bindingMut_6;
    val x_1081 = x_1080.asInstanceOf[scala.Long];
    val x_1082 = x_1081.toString();
    resetData_3 = x_1082;
    val x_1083 = resetData_3;
    val x_1084 = x_1083.asInstanceOf[java.lang.String];
    bindingMut_18 = x_1084;
    val x_1085 = bindingMut_18;
    val x_1086 = x_1085.asInstanceOf[java.lang.String];
    val x_1087 = "<html>".+(x_1086);
    resetData_3 = x_1087;
    val x_1088 = resetData_3;
    val x_1089 = x_1088.asInstanceOf[java.lang.String];
    bindingMut_17 = x_1089;
    val x_1090 = bindingMut_17;
    val x_1091 = x_1090.asInstanceOf[java.lang.String];
    val x_1092 = x_1091.+("</html>");
    resetData_3 = x_1092;
    val x_1093 = resetData_4.remove(0);
    val x_1097 = x_1093.find(((x_1094: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_1095 = x_1094._1;
      val x_1096 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
      x_1095.==(x_1096)
    }));
    val x_1098 = x_1097.get;
    val x_1099 = x_1098._2;
    positionVar_27 = x_1099
  }));
  data_28.update(137, (() => {
    val x_1100 = resetData_3;
    val x_1101 = x_1100.asInstanceOf[java.lang.String];
    bindingMut_25 = x_1101;
    val x_1102 = bindingMut_25;
    val x_1103 = x_1102.asInstanceOf[java.lang.String];
    val x_1104 = "requestPage content is ".+(x_1103);
    resetData_3 = x_1104;
    val x_1105 = resetData_3;
    val x_1106 = x_1105.asInstanceOf[java.lang.String];
    bindingMut_24 = x_1106;
    val x_1107 = bindingMut_24;
    val x_1108 = x_1107.asInstanceOf[java.lang.String];
    scala.Predef.println(x_1108);
    resetData_3 = ();
    val x_1109 = 1.toDouble;
    resetData_3 = x_1109;
    val x_1110 = resetData_3;
    val x_1111 = x_1110.asInstanceOf[scala.Double];
    bindingMut_23 = x_1111;
    resetData_3 = 0.0;
    val x_1112 = resetData_3;
    val x_1113 = x_1112.asInstanceOf[scala.Double];
    bindingMut_22 = x_1113;
    val x_1114 = meta.deep.runtime.Actor.proceedLabel;
    val x_1115 = x_1114("turn");
    val x_1116 = bindingMut_22;
    val x_1117 = x_1116.asInstanceOf[scala.Double];
    val x_1118 = x_1117.+(x_1115);
    resetData_3 = x_1118;
    val x_1119 = resetData_3;
    val x_1120 = x_1119.asInstanceOf[scala.Double];
    bindingMut_22 = x_1120;
    val x_1121 = meta.deep.runtime.Actor.labelVals("turn");
    val x_1122 = bindingMut_22;
    val x_1123 = x_1122.asInstanceOf[scala.Double];
    val x_1124 = bindingMut_23;
    val x_1125 = x_1124.asInstanceOf[scala.Double];
    val x_1126 = x_1125.-(x_1123);
    x_1121.append(x_1126);
    resetData_3 = ();
    positionVar_27 = 138;
    val x_1127 = currentTurn;
    val x_1128 = x_1127.+(1);
    currentTurn = x_1128
  }));
  data_28.update(138, (() => positionVar_27 = 70));
  data_28.update(139, (() => {
    val x_1129 = resetData_3;
    val x_1130 = x_1129.asInstanceOf[scala.Any];
    bindingMut_19 = x_1130;
    val x_1131 = bindingMut_19;
    val x_1132 = x_1131.asInstanceOf[scala.Any];
    val x_1133 = listValMut_20;
    val x_1134 = x_1133.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1134.reply(this, x_1132);
    resetData_3 = ();
    positionVar_27 = 140
  }));
  data_28.update(140, (() => positionVar_27 = 141));
  data_28.update(141, (() => {
    val x_1135 = iterMut_21;
    val x_1136 = x_1135.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1137 = x_1136.hasNext;
    if (x_1137)
      {
        val x_1138 = iterMut_21;
        val x_1139 = x_1138.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_1140 = x_1139.next();
        listValMut_20 = x_1140;
        positionVar_27 = 142
      }
    else
      positionVar_27 = 146
  }));
  data_28.update(142, (() => {
    val x_1141 = listValMut_20;
    val x_1142 = x_1141.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1143 = x_1142.methodId;
    val x_1144 = x_1143.==(2);
    val x_1145 = x_1144.`unary_!`;
    if (x_1145)
      positionVar_27 = 143
    else
      positionVar_27 = 145
  }));
  data_28.update(143, (() => {
    val x_1146 = listValMut_20;
    val x_1147 = x_1146.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1148 = x_1147.methodId;
    val x_1149 = x_1148.==(3);
    val x_1150 = x_1149.`unary_!`;
    if (x_1150)
      {
        val x_1151 = listValMut_20;
        val x_1152 = x_1151.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_1153 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_1152);
        val x_1154 = this.addReceiveMessages(x_1153);
        resetData_3 = x_1154;
        positionVar_27 = 140
      }
    else
      positionVar_27 = 144
  }));
  data_28.update(144, (() => {
    val x_1155 = listValMut_20;
    val x_1156 = x_1155.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1157 = x_1156.methodId;
    val x_1158 = x_1157.==(3);
    if (x_1158)
      positionVar_27 = 134
    else
      ();
    val x_1159 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_1160 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1159, 88);
    val x_1161 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_1162 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1161, 107);
    val x_1163 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_1164 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1163, 52);
    val x_1165 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_1166 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1165, 151);
    val x_1167 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_1168 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1167, 129);
    val x_1169 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1168);
    val x_1170 = x_1169.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1166);
    val x_1171 = x_1170.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1164);
    val x_1172 = x_1171.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1162);
    val x_1173 = x_1172.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1160);
    resetData_4.prepend(x_1173)
  }));
  data_28.update(145, (() => {
    val x_1174 = listValMut_20;
    val x_1175 = x_1174.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1176 = x_1175.methodId;
    val x_1177 = x_1176.==(2);
    if (x_1177)
      positionVar_27 = 136
    else
      ();
    val x_1178 = scala.Tuple2.apply[scala.Int, scala.Int](3, 47);
    val x_1179 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1178, 84);
    val x_1180 = scala.Tuple2.apply[scala.Int, scala.Int](1, 47);
    val x_1181 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1180, 103);
    val x_1182 = scala.Tuple2.apply[scala.Int, scala.Int](1, 23);
    val x_1183 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1182, 24);
    val x_1184 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
    val x_1185 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1184, 139);
    val x_1186 = scala.Tuple2.apply[scala.Int, scala.Int](0, 23);
    val x_1187 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1186, 117);
    val x_1188 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1187);
    val x_1189 = x_1188.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1185);
    val x_1190 = x_1189.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1183);
    val x_1191 = x_1190.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1181);
    val x_1192 = x_1191.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1179);
    resetData_4.prepend(x_1192)
  }));
  data_28.update(146, (() => {
    val x_1193 = iterMut_21;
    val x_1194 = x_1193.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1195 = x_1194.hasNext;
    val x_1196 = x_1195.`unary_!`;
    if (x_1196)
      positionVar_27 = 147
    else
      ()
  }));
  data_28.update(147, (() => if (true)
    positionVar_27 = 135
  else
    positionVar_27 = 148));
  data_28.update(148, (() => {
    val x_1197 = true.`unary_!`;
    if (x_1197)
      {
        val x_1198 = resetData_4.remove(0);
        val x_1202 = x_1198.find(((x_1199: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1200 = x_1199._1;
          val x_1201 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
          x_1200.==(x_1201)
        }));
        val x_1203 = x_1202.get;
        val x_1204 = x_1203._2;
        positionVar_27 = x_1204
      }
    else
      ()
  }));
  data_28.update(149, (() => {
    positionVar_27 = 150;
    val x_1205 = currentTurn;
    val x_1206 = x_1205.+(1);
    currentTurn = x_1206
  }));
  data_28.update(150, (() => positionVar_27 = 44));
  data_28.update(151, (() => {
    val x_1207 = resetData_3;
    val x_1208 = x_1207.asInstanceOf[scala.Any];
    bindingMut_19 = x_1208;
    val x_1209 = bindingMut_19;
    val x_1210 = x_1209.asInstanceOf[scala.Any];
    val x_1211 = listValMut_20;
    val x_1212 = x_1211.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1212.reply(this, x_1210);
    resetData_3 = ();
    positionVar_27 = 140
  }));
  data_28.update(152, (() => {
    val x_1213 = true.`unary_!`;
    if (x_1213)
      {
        val x_1214 = resetData_4.remove(0);
        val x_1218 = x_1214.find(((x_1215: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1216 = x_1215._1;
          val x_1217 = scala.Tuple2.apply[scala.Int, scala.Int](0, 47);
          x_1216.==(x_1217)
        }));
        val x_1219 = x_1218.get;
        val x_1220 = x_1219._2;
        positionVar_27 = x_1220
      }
    else
      ()
  }));
  data_28.update(153, (() => {
    val x_1221 = resetData_0;
    val x_1222 = x_1221.asInstanceOf[scala.Any];
    bindingMut_7 = x_1222;
    val x_1223 = bindingMut_7;
    val x_1224 = x_1223.asInstanceOf[scala.Any];
    val x_1225 = listValMut_8;
    val x_1226 = x_1225.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1226.reply(this, x_1224);
    resetData_0 = ();
    positionVar_27 = 154
  }));
  data_28.update(154, (() => positionVar_27 = 155));
  data_28.update(155, (() => {
    val x_1227 = iterMut_9;
    val x_1228 = x_1227.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1229 = x_1228.hasNext;
    if (x_1229)
      {
        val x_1230 = iterMut_9;
        val x_1231 = x_1230.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_1232 = x_1231.next();
        listValMut_8 = x_1232;
        positionVar_27 = 156
      }
    else
      positionVar_27 = 160
  }));
  data_28.update(156, (() => {
    val x_1233 = listValMut_8;
    val x_1234 = x_1233.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1235 = x_1234.methodId;
    val x_1236 = x_1235.==(0);
    val x_1237 = x_1236.`unary_!`;
    if (x_1237)
      positionVar_27 = 157
    else
      positionVar_27 = 159
  }));
  data_28.update(157, (() => {
    val x_1238 = listValMut_8;
    val x_1239 = x_1238.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1240 = x_1239.methodId;
    val x_1241 = x_1240.==(1);
    val x_1242 = x_1241.`unary_!`;
    if (x_1242)
      {
        val x_1243 = listValMut_8;
        val x_1244 = x_1243.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_1245 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_1244);
        val x_1246 = this.addReceiveMessages(x_1245);
        resetData_0 = x_1246;
        positionVar_27 = 154
      }
    else
      positionVar_27 = 158
  }));
  data_28.update(158, (() => {
    val x_1247 = listValMut_8;
    val x_1248 = x_1247.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1249 = x_1248.methodId;
    val x_1250 = x_1249.==(1);
    if (x_1250)
      positionVar_27 = 1
    else
      ();
    val x_1251 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_1252 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1251, 47);
    val x_1253 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_1254 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1253, 112);
    val x_1255 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_1256 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1255, 153);
    val x_1257 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1256);
    val x_1258 = x_1257.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1254);
    val x_1259 = x_1258.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1252);
    resetData_1.prepend(x_1259)
  }));
  data_28.update(159, (() => {
    val x_1260 = listValMut_8;
    val x_1261 = x_1260.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1262 = x_1261.methodId;
    val x_1263 = x_1262.==(0);
    if (x_1263)
      {
        val x_1264 = java.lang.System.nanoTime();
        resetData_0 = x_1264;
        val x_1265 = resetData_0;
        val x_1266 = x_1265.asInstanceOf[scala.Long];
        bindingMut_6 = x_1266;
        val x_1267 = bindingMut_6;
        val x_1268 = x_1267.asInstanceOf[scala.Long];
        val x_1269 = x_1268.toString();
        resetData_0 = x_1269;
        val x_1270 = resetData_0;
        val x_1271 = x_1270.asInstanceOf[scala.Any];
        bindingMut_7 = x_1271;
        val x_1272 = bindingMut_7;
        val x_1273 = x_1272.asInstanceOf[scala.Any];
        val x_1274 = listValMut_8;
        val x_1275 = x_1274.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_1275.reply(this, x_1273);
        resetData_0 = ();
        positionVar_27 = 154
      }
    else
      ()
  }));
  data_28.update(160, (() => {
    val x_1276 = iterMut_9;
    val x_1277 = x_1276.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1278 = x_1277.hasNext;
    val x_1279 = x_1278.`unary_!`;
    if (x_1279)
      positionVar_27 = 161
    else
      ()
  }));
  data_28.update(161, (() => if (true)
    positionVar_27 = 2
  else
    positionVar_27 = 162));
  data_28.update(162, (() => {
    val x_1280 = true.`unary_!`;
    if (x_1280)
      {
        val x_1281 = resetData_1.remove(0);
        val x_1285 = x_1281.find(((x_1282: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1283 = x_1282._1;
          val x_1284 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
          x_1283.==(x_1284)
        }));
        val x_1286 = x_1285.get;
        val x_1287 = x_1286._2;
        positionVar_27 = x_1287
      }
    else
      ()
  }));
  data_28
}).apply();
  
  override def run_until(until_1289: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_1290 = currentTurn;
      val x_1291 = x_1290.<=(until_1289);
      x_1291.&&({
        val x_1292 = positionVar_27;
        val x_1293 = commands_1288.length;
        x_1292.<(x_1293)
      })
    }) 
      {
        val x_1294 = positionVar_27;
        val x_1295 = commands_1288.apply(x_1294);
        x_1295.apply()
      }
    ;
    this
  }
}
