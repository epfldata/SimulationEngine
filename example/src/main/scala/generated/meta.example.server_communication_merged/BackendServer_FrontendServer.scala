package generated.meta.example.server_communication_merged

class BackendServer_FrontendServer () extends meta.deep.runtime.Actor with Serializable {
  var backendServer: generated.meta.example.server_communication_merged.BackendServer = null
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var resetData_4: scala.Any = null;
  private val resetData_5 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_6: meta.deep.runtime.ResponseMessage = null;
  private val resetData_7 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_8: scala.Long = 0L;
  private var bindingMut_9: scala.Any = null;
  private var listValMut_10: meta.deep.runtime.RequestMessage = null;
  private var iterMut_11: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: java.lang.String = null;
  private var bindingMut_14: scala.Int = 0;
  private var bindingMut_15: java.lang.String = null;
  private var bindingMut_16: java.lang.String = null;
  private var bindingMut_17: scala.Long = 0L;
  private var bindingMut_18: java.lang.String = null;
  private var bindingMut_19: java.lang.String = null;
  private var bindingMut_20: scala.Any = null;
  private var listValMut_21: meta.deep.runtime.RequestMessage = null;
  private var iterMut_22: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_23: scala.Int = 0;
  private var bindingMut_24: java.lang.String = null;
  private var bindingMut_25: java.lang.String = null;
  private var positionVar_27: scala.Int = 0;
  
  val commands_1212 = (() => {
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
    bindingMut_17 = x_40;
    val x_41 = bindingMut_17;
    val x_42 = x_41.asInstanceOf[scala.Long];
    val x_43 = "Hello world! Backend ".+(x_42);
    resetData_0 = x_43;
    val x_44 = resetData_0;
    val x_45 = x_44.asInstanceOf[java.lang.String];
    bindingMut_16 = x_45;
    val x_46 = bindingMut_16;
    val x_47 = x_46.asInstanceOf[java.lang.String];
    val x_48 = x_47.+(" Turn ");
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[java.lang.String];
    bindingMut_15 = x_50;
    val x_51 = this.currentTurn;
    resetData_0 = x_51;
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.Int];
    bindingMut_14 = x_53;
    val x_54 = bindingMut_14;
    val x_55 = x_54.asInstanceOf[scala.Int];
    val x_56 = bindingMut_15;
    val x_57 = x_56.asInstanceOf[java.lang.String];
    val x_58 = x_57.+(x_55);
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[java.lang.String];
    bindingMut_13 = x_60;
    val x_61 = bindingMut_13;
    val x_62 = x_61.asInstanceOf[java.lang.String];
    scala.Predef.println(x_62);
    resetData_0 = ();
    resetData_0 = 0;
    val x_63 = resetData_0;
    val x_64 = x_63.asInstanceOf[scala.Int];
    bindingMut_12 = x_64;
    val x_65 = bindingMut_12;
    val x_66 = x_65.asInstanceOf[scala.Int];
    val x_67 = (1).-(x_66);
    meta.deep.runtime.Actor.waitTurnList.append(x_67);
    resetData_0 = ();
    val x_68 = meta.deep.runtime.Actor.minTurn();
    val x_69 = bindingMut_12;
    val x_70 = x_69.asInstanceOf[scala.Int];
    val x_71 = x_70.+(x_68);
    resetData_0 = x_71;
    val x_72 = resetData_0;
    val x_73 = x_72.asInstanceOf[scala.Int];
    bindingMut_12 = x_73;
    positionVar_27 = 3
  }));
  data_28.update(3, (() => {
    positionVar_27 = 4;
    val x_74 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_75 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_74, 86);
    val x_76 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_77 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_76, 105);
    val x_78 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_79 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_78, 26);
    val x_80 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_81 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_80, 149);
    val x_82 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_83 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_82, 127);
    val x_84 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_83);
    val x_85 = x_84.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_81);
    val x_86 = x_85.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_79);
    val x_87 = x_86.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_77);
    val x_88 = x_87.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_75);
    resetData_5.prepend(x_88)
  }));
  data_28.update(4, (() => if (true)
    positionVar_27 = 5
  else
    positionVar_27 = 130));
  data_28.update(5, (() => {
    positionVar_27 = 6;
    val x_89 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_90 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_89, 83);
    val x_91 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_92 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_91, 102);
    val x_93 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_94 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_93, 23);
    val x_95 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_96 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_95, 137);
    val x_97 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_98 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_97, 7);
    val x_99 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_98);
    val x_100 = x_99.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_96);
    val x_101 = x_100.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_94);
    val x_102 = x_101.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_92);
    val x_103 = x_102.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_90);
    resetData_5.prepend(x_103)
  }));
  data_28.update(6, (() => {
    val x_104 = java.lang.System.nanoTime();
    resetData_4 = x_104;
    val x_105 = resetData_4;
    val x_106 = x_105.asInstanceOf[scala.Long];
    bindingMut_8 = x_106;
    val x_107 = bindingMut_8;
    val x_108 = x_107.asInstanceOf[scala.Long];
    val x_109 = x_108.toString();
    resetData_4 = x_109;
    val x_110 = resetData_4;
    val x_111 = x_110.asInstanceOf[java.lang.String];
    bindingMut_19 = x_111;
    val x_112 = bindingMut_19;
    val x_113 = x_112.asInstanceOf[java.lang.String];
    val x_114 = "<html>".+(x_113);
    resetData_4 = x_114;
    val x_115 = resetData_4;
    val x_116 = x_115.asInstanceOf[java.lang.String];
    bindingMut_18 = x_116;
    val x_117 = bindingMut_18;
    val x_118 = x_117.asInstanceOf[java.lang.String];
    val x_119 = x_118.+("</html>");
    resetData_4 = x_119;
    val x_120 = resetData_5.remove(0);
    val x_124 = x_120.find(((x_121: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_122 = x_121._1;
      val x_123 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
      x_122.==(x_123)
    }));
    val x_125 = x_124.get;
    val x_126 = x_125._2;
    positionVar_27 = x_126
  }));
  data_28.update(7, (() => {
    val x_127 = resetData_4;
    val x_128 = x_127.asInstanceOf[java.lang.String];
    bindingMut_25 = x_128;
    val x_129 = bindingMut_25;
    val x_130 = x_129.asInstanceOf[java.lang.String];
    val x_131 = "requestPage content is ".+(x_130);
    resetData_4 = x_131;
    val x_132 = resetData_4;
    val x_133 = x_132.asInstanceOf[java.lang.String];
    bindingMut_24 = x_133;
    val x_134 = bindingMut_24;
    val x_135 = x_134.asInstanceOf[java.lang.String];
    scala.Predef.println(x_135);
    resetData_4 = ();
    resetData_4 = 0;
    val x_136 = resetData_4;
    val x_137 = x_136.asInstanceOf[scala.Int];
    bindingMut_23 = x_137;
    val x_138 = bindingMut_23;
    val x_139 = x_138.asInstanceOf[scala.Int];
    val x_140 = (1).-(x_139);
    meta.deep.runtime.Actor.waitTurnList.append(x_140);
    resetData_4 = ();
    val x_141 = meta.deep.runtime.Actor.minTurn();
    val x_142 = bindingMut_23;
    val x_143 = x_142.asInstanceOf[scala.Int];
    val x_144 = x_143.+(x_141);
    resetData_4 = x_144;
    val x_145 = resetData_4;
    val x_146 = x_145.asInstanceOf[scala.Int];
    bindingMut_23 = x_146;
    positionVar_27 = 8;
    val x_147 = currentTurn;
    val x_148 = x_147.+(1);
    currentTurn = x_148
  }));
  data_28.update(8, (() => positionVar_27 = 9));
  data_28.update(9, (() => {
    val x_149 = bindingMut_12;
    val x_150 = x_149.asInstanceOf[scala.Int];
    val x_151 = x_150.<(1);
    if (x_151)
      positionVar_27 = 10
    else
      positionVar_27 = 57
  }));
  data_28.update(10, (() => {
    val x_152 = bindingMut_12;
    val x_153 = x_152.asInstanceOf[scala.Int];
    val x_154 = (1).-(x_153);
    meta.deep.runtime.Actor.waitTurnList.append(x_154);
    resetData_0 = ();
    val x_155 = meta.deep.runtime.Actor.minTurn();
    val x_156 = bindingMut_12;
    val x_157 = x_156.asInstanceOf[scala.Int];
    val x_158 = x_157.+(x_155);
    resetData_0 = x_158;
    val x_159 = resetData_0;
    val x_160 = x_159.asInstanceOf[scala.Int];
    bindingMut_12 = x_160;
    positionVar_27 = 11
  }));
  data_28.update(11, (() => {
    val x_161 = bindingMut_23;
    val x_162 = x_161.asInstanceOf[scala.Int];
    val x_163 = x_162.<(1);
    if (x_163)
      positionVar_27 = 12
    else
      positionVar_27 = 14
  }));
  data_28.update(12, (() => {
    val x_164 = bindingMut_23;
    val x_165 = x_164.asInstanceOf[scala.Int];
    val x_166 = (1).-(x_165);
    meta.deep.runtime.Actor.waitTurnList.append(x_166);
    resetData_4 = ();
    val x_167 = meta.deep.runtime.Actor.minTurn();
    val x_168 = bindingMut_23;
    val x_169 = x_168.asInstanceOf[scala.Int];
    val x_170 = x_169.+(x_167);
    resetData_4 = x_170;
    val x_171 = resetData_4;
    val x_172 = x_171.asInstanceOf[scala.Int];
    bindingMut_23 = x_172;
    positionVar_27 = 13;
    val x_173 = currentTurn;
    val x_174 = x_173.+(1);
    currentTurn = x_174
  }));
  data_28.update(13, (() => positionVar_27 = 9));
  data_28.update(14, (() => {
    val x_175 = bindingMut_23;
    val x_176 = x_175.asInstanceOf[scala.Int];
    val x_177 = x_176.<(1);
    val x_178 = x_177.`unary_!`;
    if (x_178)
      {
        val x_179 = this.popRequestMessages;
        val x_180 = x_179.iterator;
        iterMut_22 = x_180;
        positionVar_27 = 15
      }
    else
      ()
  }));
  data_28.update(15, (() => {
    val x_181 = iterMut_22;
    val x_182 = x_181.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_183 = x_182.hasNext;
    if (x_183)
      {
        val x_184 = iterMut_22;
        val x_185 = x_184.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_186 = x_185.next();
        listValMut_21 = x_186;
        positionVar_27 = 16
      }
    else
      positionVar_27 = 54
  }));
  data_28.update(16, (() => {
    val x_187 = listValMut_21;
    val x_188 = x_187.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_189 = x_188.methodId;
    val x_190 = x_189.==(2);
    val x_191 = x_190.`unary_!`;
    if (x_191)
      positionVar_27 = 17
    else
      positionVar_27 = 53
  }));
  data_28.update(17, (() => {
    val x_192 = listValMut_21;
    val x_193 = x_192.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_194 = x_193.methodId;
    val x_195 = x_194.==(3);
    val x_196 = x_195.`unary_!`;
    if (x_196)
      {
        val x_197 = listValMut_21;
        val x_198 = x_197.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_199 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_198);
        val x_200 = this.addReceiveMessages(x_199);
        resetData_4 = x_200;
        positionVar_27 = 18
      }
    else
      positionVar_27 = 19
  }));
  data_28.update(18, (() => positionVar_27 = 15));
  data_28.update(19, (() => {
    val x_201 = listValMut_21;
    val x_202 = x_201.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_203 = x_202.methodId;
    val x_204 = x_203.==(3);
    if (x_204)
      positionVar_27 = 20
    else
      ();
    val x_205 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_206 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_205, 88);
    val x_207 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_208 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_207, 107);
    val x_209 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_210 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_209, 52);
    val x_211 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_212 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_211, 151);
    val x_213 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_214 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_213, 129);
    val x_215 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_214);
    val x_216 = x_215.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_212);
    val x_217 = x_216.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_210);
    val x_218 = x_217.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_208);
    val x_219 = x_218.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_206);
    resetData_5.prepend(x_219)
  }));
  data_28.update(20, (() => if (true)
    positionVar_27 = 21
  else
    positionVar_27 = 25));
  data_28.update(21, (() => {
    positionVar_27 = 22;
    val x_220 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_221 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_220, 83);
    val x_222 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_223 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_222, 102);
    val x_224 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_225 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_224, 23);
    val x_226 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_227 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_226, 137);
    val x_228 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_229 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_228, 7);
    val x_230 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_229);
    val x_231 = x_230.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_227);
    val x_232 = x_231.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_225);
    val x_233 = x_232.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_223);
    val x_234 = x_233.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_221);
    resetData_5.prepend(x_234)
  }));
  data_28.update(22, (() => {
    val x_235 = java.lang.System.nanoTime();
    resetData_4 = x_235;
    val x_236 = resetData_4;
    val x_237 = x_236.asInstanceOf[scala.Long];
    bindingMut_8 = x_237;
    val x_238 = bindingMut_8;
    val x_239 = x_238.asInstanceOf[scala.Long];
    val x_240 = x_239.toString();
    resetData_4 = x_240;
    val x_241 = resetData_4;
    val x_242 = x_241.asInstanceOf[java.lang.String];
    bindingMut_19 = x_242;
    val x_243 = bindingMut_19;
    val x_244 = x_243.asInstanceOf[java.lang.String];
    val x_245 = "<html>".+(x_244);
    resetData_4 = x_245;
    val x_246 = resetData_4;
    val x_247 = x_246.asInstanceOf[java.lang.String];
    bindingMut_18 = x_247;
    val x_248 = bindingMut_18;
    val x_249 = x_248.asInstanceOf[java.lang.String];
    val x_250 = x_249.+("</html>");
    resetData_4 = x_250;
    val x_251 = resetData_5.remove(0);
    val x_255 = x_251.find(((x_252: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_253 = x_252._1;
      val x_254 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
      x_253.==(x_254)
    }));
    val x_256 = x_255.get;
    val x_257 = x_256._2;
    positionVar_27 = x_257
  }));
  data_28.update(23, (() => {
    val x_258 = resetData_4;
    val x_259 = x_258.asInstanceOf[java.lang.String];
    bindingMut_25 = x_259;
    val x_260 = bindingMut_25;
    val x_261 = x_260.asInstanceOf[java.lang.String];
    val x_262 = "requestPage content is ".+(x_261);
    resetData_4 = x_262;
    val x_263 = resetData_4;
    val x_264 = x_263.asInstanceOf[java.lang.String];
    bindingMut_24 = x_264;
    val x_265 = bindingMut_24;
    val x_266 = x_265.asInstanceOf[java.lang.String];
    scala.Predef.println(x_266);
    resetData_4 = ();
    resetData_4 = 0;
    val x_267 = resetData_4;
    val x_268 = x_267.asInstanceOf[scala.Int];
    bindingMut_23 = x_268;
    positionVar_27 = 12
  }));
  data_28.update(24, (() => {
    val x_269 = resetData_4;
    val x_270 = x_269.asInstanceOf[scala.Any];
    bindingMut_20 = x_270;
    val x_271 = bindingMut_20;
    val x_272 = x_271.asInstanceOf[scala.Any];
    val x_273 = listValMut_21;
    val x_274 = x_273.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_274.reply(this, x_272);
    resetData_4 = ();
    positionVar_27 = 18
  }));
  data_28.update(25, (() => {
    val x_275 = true.`unary_!`;
    if (x_275)
      {
        val x_276 = resetData_5.remove(0);
        val x_280 = x_276.find(((x_277: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_278 = x_277._1;
          val x_279 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
          x_278.==(x_279)
        }));
        val x_281 = x_280.get;
        val x_282 = x_281._2;
        positionVar_27 = x_282
      }
    else
      ()
  }));
  data_28.update(26, (() => {
    positionVar_27 = 27;
    val x_283 = currentTurn;
    val x_284 = x_283.+(1);
    currentTurn = x_284
  }));
  data_28.update(27, (() => positionVar_27 = 28));
  data_28.update(28, (() => {
    val x_285 = bindingMut_12;
    val x_286 = x_285.asInstanceOf[scala.Int];
    val x_287 = x_286.<(1);
    if (x_287)
      positionVar_27 = 29
    else
      positionVar_27 = 32
  }));
  data_28.update(29, (() => {
    val x_288 = bindingMut_12;
    val x_289 = x_288.asInstanceOf[scala.Int];
    val x_290 = (1).-(x_289);
    meta.deep.runtime.Actor.waitTurnList.append(x_290);
    resetData_0 = ();
    val x_291 = meta.deep.runtime.Actor.minTurn();
    val x_292 = bindingMut_12;
    val x_293 = x_292.asInstanceOf[scala.Int];
    val x_294 = x_293.+(x_291);
    resetData_0 = x_294;
    val x_295 = resetData_0;
    val x_296 = x_295.asInstanceOf[scala.Int];
    bindingMut_12 = x_296;
    positionVar_27 = 30
  }));
  data_28.update(30, (() => {
    positionVar_27 = 31;
    val x_297 = currentTurn;
    val x_298 = x_297.+(1);
    currentTurn = x_298
  }));
  data_28.update(31, (() => positionVar_27 = 28));
  data_28.update(32, (() => {
    val x_299 = bindingMut_12;
    val x_300 = x_299.asInstanceOf[scala.Int];
    val x_301 = x_300.<(1);
    val x_302 = x_301.`unary_!`;
    if (x_302)
      {
        val x_303 = this.popRequestMessages;
        val x_304 = x_303.iterator;
        iterMut_11 = x_304;
        positionVar_27 = 33
      }
    else
      ()
  }));
  data_28.update(33, (() => {
    val x_305 = iterMut_11;
    val x_306 = x_305.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_307 = x_306.hasNext;
    if (x_307)
      {
        val x_308 = iterMut_11;
        val x_309 = x_308.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_310 = x_309.next();
        listValMut_10 = x_310;
        positionVar_27 = 34
      }
    else
      positionVar_27 = 49
  }));
  data_28.update(34, (() => {
    val x_311 = listValMut_10;
    val x_312 = x_311.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_313 = x_312.methodId;
    val x_314 = x_313.==(0);
    val x_315 = x_314.`unary_!`;
    if (x_315)
      positionVar_27 = 35
    else
      positionVar_27 = 48
  }));
  data_28.update(35, (() => {
    val x_316 = listValMut_10;
    val x_317 = x_316.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_318 = x_317.methodId;
    val x_319 = x_318.==(1);
    val x_320 = x_319.`unary_!`;
    if (x_320)
      {
        val x_321 = listValMut_10;
        val x_322 = x_321.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_323 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_322);
        val x_324 = this.addReceiveMessages(x_323);
        resetData_0 = x_324;
        positionVar_27 = 36
      }
    else
      positionVar_27 = 37
  }));
  data_28.update(36, (() => positionVar_27 = 33));
  data_28.update(37, (() => {
    val x_325 = listValMut_10;
    val x_326 = x_325.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_327 = x_326.methodId;
    val x_328 = x_327.==(1);
    if (x_328)
      positionVar_27 = 38
    else
      ();
    val x_329 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_330 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_329, 47);
    val x_331 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_332 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_331, 112);
    val x_333 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_334 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_333, 153);
    val x_335 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_334);
    val x_336 = x_335.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_332);
    val x_337 = x_336.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_330);
    resetData_1.prepend(x_337)
  }));
  data_28.update(38, (() => if (true)
    positionVar_27 = 39
  else
    positionVar_27 = 40));
  data_28.update(39, (() => {
    val x_338 = this.id;
    resetData_0 = x_338;
    val x_339 = resetData_0;
    val x_340 = x_339.asInstanceOf[scala.Long];
    bindingMut_17 = x_340;
    val x_341 = bindingMut_17;
    val x_342 = x_341.asInstanceOf[scala.Long];
    val x_343 = "Hello world! Backend ".+(x_342);
    resetData_0 = x_343;
    val x_344 = resetData_0;
    val x_345 = x_344.asInstanceOf[java.lang.String];
    bindingMut_16 = x_345;
    val x_346 = bindingMut_16;
    val x_347 = x_346.asInstanceOf[java.lang.String];
    val x_348 = x_347.+(" Turn ");
    resetData_0 = x_348;
    val x_349 = resetData_0;
    val x_350 = x_349.asInstanceOf[java.lang.String];
    bindingMut_15 = x_350;
    val x_351 = this.currentTurn;
    resetData_0 = x_351;
    val x_352 = resetData_0;
    val x_353 = x_352.asInstanceOf[scala.Int];
    bindingMut_14 = x_353;
    val x_354 = bindingMut_14;
    val x_355 = x_354.asInstanceOf[scala.Int];
    val x_356 = bindingMut_15;
    val x_357 = x_356.asInstanceOf[java.lang.String];
    val x_358 = x_357.+(x_355);
    resetData_0 = x_358;
    val x_359 = resetData_0;
    val x_360 = x_359.asInstanceOf[java.lang.String];
    bindingMut_13 = x_360;
    val x_361 = bindingMut_13;
    val x_362 = x_361.asInstanceOf[java.lang.String];
    scala.Predef.println(x_362);
    resetData_0 = ();
    resetData_0 = 0;
    val x_363 = resetData_0;
    val x_364 = x_363.asInstanceOf[scala.Int];
    bindingMut_12 = x_364;
    positionVar_27 = 29
  }));
  data_28.update(40, (() => {
    val x_365 = true.`unary_!`;
    if (x_365)
      {
        val x_366 = resetData_1.remove(0);
        val x_370 = x_366.find(((x_367: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_368 = x_367._1;
          val x_369 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
          x_368.==(x_369)
        }));
        val x_371 = x_370.get;
        val x_372 = x_371._2;
        positionVar_27 = x_372
      }
    else
      ()
  }));
  data_28.update(41, (() => positionVar_27 = 42));
  data_28.update(42, (() => {
    positionVar_27 = 43;
    val x_373 = currentTurn;
    val x_374 = x_373.+(1);
    currentTurn = x_374
  }));
  data_28.update(43, (() => positionVar_27 = 44));
  data_28.update(44, (() => positionVar_27 = 45));
  data_28.update(45, (() => {
    positionVar_27 = 46;
    val x_375 = currentTurn;
    val x_376 = x_375.+(1);
    currentTurn = x_376
  }));
  data_28.update(46, (() => positionVar_27 = 44));
  data_28.update(47, (() => {
    val x_377 = resetData_0;
    val x_378 = x_377.asInstanceOf[scala.Any];
    bindingMut_9 = x_378;
    val x_379 = bindingMut_9;
    val x_380 = x_379.asInstanceOf[scala.Any];
    val x_381 = listValMut_10;
    val x_382 = x_381.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_382.reply(this, x_380);
    resetData_0 = ();
    positionVar_27 = 36
  }));
  data_28.update(48, (() => {
    val x_383 = listValMut_10;
    val x_384 = x_383.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_385 = x_384.methodId;
    val x_386 = x_385.==(0);
    if (x_386)
      {
        val x_387 = java.lang.System.nanoTime();
        resetData_0 = x_387;
        val x_388 = resetData_0;
        val x_389 = x_388.asInstanceOf[scala.Long];
        bindingMut_8 = x_389;
        val x_390 = bindingMut_8;
        val x_391 = x_390.asInstanceOf[scala.Long];
        val x_392 = x_391.toString();
        resetData_0 = x_392;
        val x_393 = resetData_0;
        val x_394 = x_393.asInstanceOf[scala.Any];
        bindingMut_9 = x_394;
        val x_395 = bindingMut_9;
        val x_396 = x_395.asInstanceOf[scala.Any];
        val x_397 = listValMut_10;
        val x_398 = x_397.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_398.reply(this, x_396);
        resetData_0 = ();
        positionVar_27 = 36
      }
    else
      ()
  }));
  data_28.update(49, (() => {
    val x_399 = iterMut_11;
    val x_400 = x_399.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_401 = x_400.hasNext;
    val x_402 = x_401.`unary_!`;
    if (x_402)
      positionVar_27 = 50
    else
      ()
  }));
  data_28.update(50, (() => if (true)
    positionVar_27 = 39
  else
    positionVar_27 = 51));
  data_28.update(51, (() => {
    val x_403 = true.`unary_!`;
    if (x_403)
      {
        val x_404 = resetData_1.remove(0);
        val x_408 = x_404.find(((x_405: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_406 = x_405._1;
          val x_407 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
          x_406.==(x_407)
        }));
        val x_409 = x_408.get;
        val x_410 = x_409._2;
        positionVar_27 = x_410
      }
    else
      ()
  }));
  data_28.update(52, (() => {
    val x_411 = resetData_4;
    val x_412 = x_411.asInstanceOf[scala.Any];
    bindingMut_20 = x_412;
    val x_413 = bindingMut_20;
    val x_414 = x_413.asInstanceOf[scala.Any];
    val x_415 = listValMut_21;
    val x_416 = x_415.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_416.reply(this, x_414);
    resetData_4 = ();
    positionVar_27 = 18
  }));
  data_28.update(53, (() => {
    val x_417 = listValMut_21;
    val x_418 = x_417.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_419 = x_418.methodId;
    val x_420 = x_419.==(2);
    if (x_420)
      positionVar_27 = 22
    else
      ();
    val x_421 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_422 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_421, 84);
    val x_423 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_424 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_423, 103);
    val x_425 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_426 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_425, 24);
    val x_427 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_428 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_427, 139);
    val x_429 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_430 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_429, 117);
    val x_431 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_430);
    val x_432 = x_431.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_428);
    val x_433 = x_432.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_426);
    val x_434 = x_433.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_424);
    val x_435 = x_434.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_422);
    resetData_5.prepend(x_435)
  }));
  data_28.update(54, (() => {
    val x_436 = iterMut_22;
    val x_437 = x_436.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_438 = x_437.hasNext;
    val x_439 = x_438.`unary_!`;
    if (x_439)
      positionVar_27 = 55
    else
      ()
  }));
  data_28.update(55, (() => if (true)
    positionVar_27 = 21
  else
    positionVar_27 = 56));
  data_28.update(56, (() => {
    val x_440 = true.`unary_!`;
    if (x_440)
      {
        val x_441 = resetData_5.remove(0);
        val x_445 = x_441.find(((x_442: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_443 = x_442._1;
          val x_444 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
          x_443.==(x_444)
        }));
        val x_446 = x_445.get;
        val x_447 = x_446._2;
        positionVar_27 = x_447
      }
    else
      ()
  }));
  data_28.update(57, (() => {
    val x_448 = bindingMut_12;
    val x_449 = x_448.asInstanceOf[scala.Int];
    val x_450 = x_449.<(1);
    val x_451 = x_450.`unary_!`;
    if (x_451)
      {
        val x_452 = this.popRequestMessages;
        val x_453 = x_452.iterator;
        iterMut_11 = x_453;
        positionVar_27 = 58
      }
    else
      ()
  }));
  data_28.update(58, (() => {
    val x_454 = iterMut_11;
    val x_455 = x_454.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_456 = x_455.hasNext;
    if (x_456)
      {
        val x_457 = iterMut_11;
        val x_458 = x_457.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_459 = x_458.next();
        listValMut_10 = x_459;
        positionVar_27 = 59
      }
    else
      positionVar_27 = 114
  }));
  data_28.update(59, (() => {
    val x_460 = listValMut_10;
    val x_461 = x_460.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_462 = x_461.methodId;
    val x_463 = x_462.==(0);
    val x_464 = x_463.`unary_!`;
    if (x_464)
      positionVar_27 = 60
    else
      positionVar_27 = 113
  }));
  data_28.update(60, (() => {
    val x_465 = listValMut_10;
    val x_466 = x_465.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_467 = x_466.methodId;
    val x_468 = x_467.==(1);
    val x_469 = x_468.`unary_!`;
    if (x_469)
      {
        val x_470 = listValMut_10;
        val x_471 = x_470.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_472 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_471);
        val x_473 = this.addReceiveMessages(x_472);
        resetData_0 = x_473;
        positionVar_27 = 61
      }
    else
      positionVar_27 = 62
  }));
  data_28.update(61, (() => positionVar_27 = 58));
  data_28.update(62, (() => {
    val x_474 = listValMut_10;
    val x_475 = x_474.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_476 = x_475.methodId;
    val x_477 = x_476.==(1);
    if (x_477)
      positionVar_27 = 63
    else
      ();
    val x_478 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_479 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_478, 47);
    val x_480 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_481 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_480, 112);
    val x_482 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_483 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_482, 153);
    val x_484 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_483);
    val x_485 = x_484.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_481);
    val x_486 = x_485.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_479);
    resetData_1.prepend(x_486)
  }));
  data_28.update(63, (() => if (true)
    positionVar_27 = 64
  else
    positionVar_27 = 65));
  data_28.update(64, (() => {
    val x_487 = this.id;
    resetData_0 = x_487;
    val x_488 = resetData_0;
    val x_489 = x_488.asInstanceOf[scala.Long];
    bindingMut_17 = x_489;
    val x_490 = bindingMut_17;
    val x_491 = x_490.asInstanceOf[scala.Long];
    val x_492 = "Hello world! Backend ".+(x_491);
    resetData_0 = x_492;
    val x_493 = resetData_0;
    val x_494 = x_493.asInstanceOf[java.lang.String];
    bindingMut_16 = x_494;
    val x_495 = bindingMut_16;
    val x_496 = x_495.asInstanceOf[java.lang.String];
    val x_497 = x_496.+(" Turn ");
    resetData_0 = x_497;
    val x_498 = resetData_0;
    val x_499 = x_498.asInstanceOf[java.lang.String];
    bindingMut_15 = x_499;
    val x_500 = this.currentTurn;
    resetData_0 = x_500;
    val x_501 = resetData_0;
    val x_502 = x_501.asInstanceOf[scala.Int];
    bindingMut_14 = x_502;
    val x_503 = bindingMut_14;
    val x_504 = x_503.asInstanceOf[scala.Int];
    val x_505 = bindingMut_15;
    val x_506 = x_505.asInstanceOf[java.lang.String];
    val x_507 = x_506.+(x_504);
    resetData_0 = x_507;
    val x_508 = resetData_0;
    val x_509 = x_508.asInstanceOf[java.lang.String];
    bindingMut_13 = x_509;
    val x_510 = bindingMut_13;
    val x_511 = x_510.asInstanceOf[java.lang.String];
    scala.Predef.println(x_511);
    resetData_0 = ();
    resetData_0 = 0;
    val x_512 = resetData_0;
    val x_513 = x_512.asInstanceOf[scala.Int];
    bindingMut_12 = x_513;
    positionVar_27 = 10
  }));
  data_28.update(65, (() => {
    val x_514 = true.`unary_!`;
    if (x_514)
      {
        val x_515 = resetData_1.remove(0);
        val x_519 = x_515.find(((x_516: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_517 = x_516._1;
          val x_518 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
          x_517.==(x_518)
        }));
        val x_520 = x_519.get;
        val x_521 = x_520._2;
        positionVar_27 = x_521
      }
    else
      ()
  }));
  data_28.update(66, (() => positionVar_27 = 67));
  data_28.update(67, (() => {
    val x_522 = bindingMut_23;
    val x_523 = x_522.asInstanceOf[scala.Int];
    val x_524 = x_523.<(1);
    if (x_524)
      positionVar_27 = 68
    else
      positionVar_27 = 93
  }));
  data_28.update(68, (() => {
    val x_525 = bindingMut_23;
    val x_526 = x_525.asInstanceOf[scala.Int];
    val x_527 = (1).-(x_526);
    meta.deep.runtime.Actor.waitTurnList.append(x_527);
    resetData_4 = ();
    val x_528 = meta.deep.runtime.Actor.minTurn();
    val x_529 = bindingMut_23;
    val x_530 = x_529.asInstanceOf[scala.Int];
    val x_531 = x_530.+(x_528);
    resetData_4 = x_531;
    val x_532 = resetData_4;
    val x_533 = x_532.asInstanceOf[scala.Int];
    bindingMut_23 = x_533;
    positionVar_27 = 69;
    val x_534 = currentTurn;
    val x_535 = x_534.+(1);
    currentTurn = x_535
  }));
  data_28.update(69, (() => positionVar_27 = 70));
  data_28.update(70, (() => positionVar_27 = 71));
  data_28.update(71, (() => {
    val x_536 = bindingMut_23;
    val x_537 = x_536.asInstanceOf[scala.Int];
    val x_538 = x_537.<(1);
    if (x_538)
      positionVar_27 = 72
    else
      positionVar_27 = 74
  }));
  data_28.update(72, (() => {
    val x_539 = bindingMut_23;
    val x_540 = x_539.asInstanceOf[scala.Int];
    val x_541 = (1).-(x_540);
    meta.deep.runtime.Actor.waitTurnList.append(x_541);
    resetData_4 = ();
    val x_542 = meta.deep.runtime.Actor.minTurn();
    val x_543 = bindingMut_23;
    val x_544 = x_543.asInstanceOf[scala.Int];
    val x_545 = x_544.+(x_542);
    resetData_4 = x_545;
    val x_546 = resetData_4;
    val x_547 = x_546.asInstanceOf[scala.Int];
    bindingMut_23 = x_547;
    positionVar_27 = 73;
    val x_548 = currentTurn;
    val x_549 = x_548.+(1);
    currentTurn = x_549
  }));
  data_28.update(73, (() => positionVar_27 = 70));
  data_28.update(74, (() => {
    val x_550 = bindingMut_23;
    val x_551 = x_550.asInstanceOf[scala.Int];
    val x_552 = x_551.<(1);
    val x_553 = x_552.`unary_!`;
    if (x_553)
      {
        val x_554 = this.popRequestMessages;
        val x_555 = x_554.iterator;
        iterMut_22 = x_555;
        positionVar_27 = 75
      }
    else
      ()
  }));
  data_28.update(75, (() => {
    val x_556 = iterMut_22;
    val x_557 = x_556.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_558 = x_557.hasNext;
    if (x_558)
      {
        val x_559 = iterMut_22;
        val x_560 = x_559.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_561 = x_560.next();
        listValMut_21 = x_561;
        positionVar_27 = 76
      }
    else
      positionVar_27 = 90
  }));
  data_28.update(76, (() => {
    val x_562 = listValMut_21;
    val x_563 = x_562.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_564 = x_563.methodId;
    val x_565 = x_564.==(2);
    val x_566 = x_565.`unary_!`;
    if (x_566)
      positionVar_27 = 77
    else
      positionVar_27 = 89
  }));
  data_28.update(77, (() => {
    val x_567 = listValMut_21;
    val x_568 = x_567.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_569 = x_568.methodId;
    val x_570 = x_569.==(3);
    val x_571 = x_570.`unary_!`;
    if (x_571)
      {
        val x_572 = listValMut_21;
        val x_573 = x_572.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_574 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_573);
        val x_575 = this.addReceiveMessages(x_574);
        resetData_4 = x_575;
        positionVar_27 = 78
      }
    else
      positionVar_27 = 79
  }));
  data_28.update(78, (() => positionVar_27 = 75));
  data_28.update(79, (() => {
    val x_576 = listValMut_21;
    val x_577 = x_576.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_578 = x_577.methodId;
    val x_579 = x_578.==(3);
    if (x_579)
      positionVar_27 = 80
    else
      ();
    val x_580 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_581 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_580, 88);
    val x_582 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_583 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_582, 107);
    val x_584 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_585 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_584, 52);
    val x_586 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_587 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_586, 151);
    val x_588 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_589 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_588, 129);
    val x_590 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_589);
    val x_591 = x_590.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_587);
    val x_592 = x_591.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_585);
    val x_593 = x_592.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_583);
    val x_594 = x_593.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_581);
    resetData_5.prepend(x_594)
  }));
  data_28.update(80, (() => if (true)
    positionVar_27 = 81
  else
    positionVar_27 = 85));
  data_28.update(81, (() => {
    positionVar_27 = 82;
    val x_595 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_596 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_595, 83);
    val x_597 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_598 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_597, 102);
    val x_599 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_600 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_599, 23);
    val x_601 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_602 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_601, 137);
    val x_603 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_604 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_603, 7);
    val x_605 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_604);
    val x_606 = x_605.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_602);
    val x_607 = x_606.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_600);
    val x_608 = x_607.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_598);
    val x_609 = x_608.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_596);
    resetData_5.prepend(x_609)
  }));
  data_28.update(82, (() => {
    val x_610 = java.lang.System.nanoTime();
    resetData_4 = x_610;
    val x_611 = resetData_4;
    val x_612 = x_611.asInstanceOf[scala.Long];
    bindingMut_8 = x_612;
    val x_613 = bindingMut_8;
    val x_614 = x_613.asInstanceOf[scala.Long];
    val x_615 = x_614.toString();
    resetData_4 = x_615;
    val x_616 = resetData_4;
    val x_617 = x_616.asInstanceOf[java.lang.String];
    bindingMut_19 = x_617;
    val x_618 = bindingMut_19;
    val x_619 = x_618.asInstanceOf[java.lang.String];
    val x_620 = "<html>".+(x_619);
    resetData_4 = x_620;
    val x_621 = resetData_4;
    val x_622 = x_621.asInstanceOf[java.lang.String];
    bindingMut_18 = x_622;
    val x_623 = bindingMut_18;
    val x_624 = x_623.asInstanceOf[java.lang.String];
    val x_625 = x_624.+("</html>");
    resetData_4 = x_625;
    val x_626 = resetData_5.remove(0);
    val x_630 = x_626.find(((x_627: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_628 = x_627._1;
      val x_629 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
      x_628.==(x_629)
    }));
    val x_631 = x_630.get;
    val x_632 = x_631._2;
    positionVar_27 = x_632
  }));
  data_28.update(83, (() => {
    val x_633 = resetData_4;
    val x_634 = x_633.asInstanceOf[java.lang.String];
    bindingMut_25 = x_634;
    val x_635 = bindingMut_25;
    val x_636 = x_635.asInstanceOf[java.lang.String];
    val x_637 = "requestPage content is ".+(x_636);
    resetData_4 = x_637;
    val x_638 = resetData_4;
    val x_639 = x_638.asInstanceOf[java.lang.String];
    bindingMut_24 = x_639;
    val x_640 = bindingMut_24;
    val x_641 = x_640.asInstanceOf[java.lang.String];
    scala.Predef.println(x_641);
    resetData_4 = ();
    resetData_4 = 0;
    val x_642 = resetData_4;
    val x_643 = x_642.asInstanceOf[scala.Int];
    bindingMut_23 = x_643;
    positionVar_27 = 72
  }));
  data_28.update(84, (() => {
    val x_644 = resetData_4;
    val x_645 = x_644.asInstanceOf[scala.Any];
    bindingMut_20 = x_645;
    val x_646 = bindingMut_20;
    val x_647 = x_646.asInstanceOf[scala.Any];
    val x_648 = listValMut_21;
    val x_649 = x_648.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_649.reply(this, x_647);
    resetData_4 = ();
    positionVar_27 = 78
  }));
  data_28.update(85, (() => {
    val x_650 = true.`unary_!`;
    if (x_650)
      {
        val x_651 = resetData_5.remove(0);
        val x_655 = x_651.find(((x_652: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_653 = x_652._1;
          val x_654 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
          x_653.==(x_654)
        }));
        val x_656 = x_655.get;
        val x_657 = x_656._2;
        positionVar_27 = x_657
      }
    else
      ()
  }));
  data_28.update(86, (() => {
    positionVar_27 = 87;
    val x_658 = currentTurn;
    val x_659 = x_658.+(1);
    currentTurn = x_659
  }));
  data_28.update(87, (() => positionVar_27 = 44));
  data_28.update(88, (() => {
    val x_660 = resetData_4;
    val x_661 = x_660.asInstanceOf[scala.Any];
    bindingMut_20 = x_661;
    val x_662 = bindingMut_20;
    val x_663 = x_662.asInstanceOf[scala.Any];
    val x_664 = listValMut_21;
    val x_665 = x_664.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_665.reply(this, x_663);
    resetData_4 = ();
    positionVar_27 = 78
  }));
  data_28.update(89, (() => {
    val x_666 = listValMut_21;
    val x_667 = x_666.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_668 = x_667.methodId;
    val x_669 = x_668.==(2);
    if (x_669)
      positionVar_27 = 82
    else
      ();
    val x_670 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_671 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_670, 84);
    val x_672 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_673 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_672, 103);
    val x_674 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_675 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_674, 24);
    val x_676 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_677 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_676, 139);
    val x_678 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_679 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_678, 117);
    val x_680 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_679);
    val x_681 = x_680.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_677);
    val x_682 = x_681.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_675);
    val x_683 = x_682.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_673);
    val x_684 = x_683.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_671);
    resetData_5.prepend(x_684)
  }));
  data_28.update(90, (() => {
    val x_685 = iterMut_22;
    val x_686 = x_685.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_687 = x_686.hasNext;
    val x_688 = x_687.`unary_!`;
    if (x_688)
      positionVar_27 = 91
    else
      ()
  }));
  data_28.update(91, (() => if (true)
    positionVar_27 = 81
  else
    positionVar_27 = 92));
  data_28.update(92, (() => {
    val x_689 = true.`unary_!`;
    if (x_689)
      {
        val x_690 = resetData_5.remove(0);
        val x_694 = x_690.find(((x_691: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_692 = x_691._1;
          val x_693 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
          x_692.==(x_693)
        }));
        val x_695 = x_694.get;
        val x_696 = x_695._2;
        positionVar_27 = x_696
      }
    else
      ()
  }));
  data_28.update(93, (() => {
    val x_697 = bindingMut_23;
    val x_698 = x_697.asInstanceOf[scala.Int];
    val x_699 = x_698.<(1);
    val x_700 = x_699.`unary_!`;
    if (x_700)
      {
        val x_701 = this.popRequestMessages;
        val x_702 = x_701.iterator;
        iterMut_22 = x_702;
        positionVar_27 = 94
      }
    else
      ()
  }));
  data_28.update(94, (() => {
    val x_703 = iterMut_22;
    val x_704 = x_703.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_705 = x_704.hasNext;
    if (x_705)
      {
        val x_706 = iterMut_22;
        val x_707 = x_706.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_708 = x_707.next();
        listValMut_21 = x_708;
        positionVar_27 = 95
      }
    else
      positionVar_27 = 109
  }));
  data_28.update(95, (() => {
    val x_709 = listValMut_21;
    val x_710 = x_709.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_711 = x_710.methodId;
    val x_712 = x_711.==(2);
    val x_713 = x_712.`unary_!`;
    if (x_713)
      positionVar_27 = 96
    else
      positionVar_27 = 108
  }));
  data_28.update(96, (() => {
    val x_714 = listValMut_21;
    val x_715 = x_714.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_716 = x_715.methodId;
    val x_717 = x_716.==(3);
    val x_718 = x_717.`unary_!`;
    if (x_718)
      {
        val x_719 = listValMut_21;
        val x_720 = x_719.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_721 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_720);
        val x_722 = this.addReceiveMessages(x_721);
        resetData_4 = x_722;
        positionVar_27 = 97
      }
    else
      positionVar_27 = 98
  }));
  data_28.update(97, (() => positionVar_27 = 94));
  data_28.update(98, (() => {
    val x_723 = listValMut_21;
    val x_724 = x_723.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_725 = x_724.methodId;
    val x_726 = x_725.==(3);
    if (x_726)
      positionVar_27 = 99
    else
      ();
    val x_727 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_728 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_727, 88);
    val x_729 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_730 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_729, 107);
    val x_731 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_732 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_731, 52);
    val x_733 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_734 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_733, 151);
    val x_735 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_736 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_735, 129);
    val x_737 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_736);
    val x_738 = x_737.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_734);
    val x_739 = x_738.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_732);
    val x_740 = x_739.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_730);
    val x_741 = x_740.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_728);
    resetData_5.prepend(x_741)
  }));
  data_28.update(99, (() => if (true)
    positionVar_27 = 100
  else
    positionVar_27 = 104));
  data_28.update(100, (() => {
    positionVar_27 = 101;
    val x_742 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_743 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_742, 83);
    val x_744 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_745 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_744, 102);
    val x_746 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_747 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_746, 23);
    val x_748 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_749 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_748, 137);
    val x_750 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_751 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_750, 7);
    val x_752 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_751);
    val x_753 = x_752.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_749);
    val x_754 = x_753.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_747);
    val x_755 = x_754.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_745);
    val x_756 = x_755.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_743);
    resetData_5.prepend(x_756)
  }));
  data_28.update(101, (() => {
    val x_757 = java.lang.System.nanoTime();
    resetData_4 = x_757;
    val x_758 = resetData_4;
    val x_759 = x_758.asInstanceOf[scala.Long];
    bindingMut_8 = x_759;
    val x_760 = bindingMut_8;
    val x_761 = x_760.asInstanceOf[scala.Long];
    val x_762 = x_761.toString();
    resetData_4 = x_762;
    val x_763 = resetData_4;
    val x_764 = x_763.asInstanceOf[java.lang.String];
    bindingMut_19 = x_764;
    val x_765 = bindingMut_19;
    val x_766 = x_765.asInstanceOf[java.lang.String];
    val x_767 = "<html>".+(x_766);
    resetData_4 = x_767;
    val x_768 = resetData_4;
    val x_769 = x_768.asInstanceOf[java.lang.String];
    bindingMut_18 = x_769;
    val x_770 = bindingMut_18;
    val x_771 = x_770.asInstanceOf[java.lang.String];
    val x_772 = x_771.+("</html>");
    resetData_4 = x_772;
    val x_773 = resetData_5.remove(0);
    val x_777 = x_773.find(((x_774: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_775 = x_774._1;
      val x_776 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
      x_775.==(x_776)
    }));
    val x_778 = x_777.get;
    val x_779 = x_778._2;
    positionVar_27 = x_779
  }));
  data_28.update(102, (() => {
    val x_780 = resetData_4;
    val x_781 = x_780.asInstanceOf[java.lang.String];
    bindingMut_25 = x_781;
    val x_782 = bindingMut_25;
    val x_783 = x_782.asInstanceOf[java.lang.String];
    val x_784 = "requestPage content is ".+(x_783);
    resetData_4 = x_784;
    val x_785 = resetData_4;
    val x_786 = x_785.asInstanceOf[java.lang.String];
    bindingMut_24 = x_786;
    val x_787 = bindingMut_24;
    val x_788 = x_787.asInstanceOf[java.lang.String];
    scala.Predef.println(x_788);
    resetData_4 = ();
    resetData_4 = 0;
    val x_789 = resetData_4;
    val x_790 = x_789.asInstanceOf[scala.Int];
    bindingMut_23 = x_790;
    positionVar_27 = 68
  }));
  data_28.update(103, (() => {
    val x_791 = resetData_4;
    val x_792 = x_791.asInstanceOf[scala.Any];
    bindingMut_20 = x_792;
    val x_793 = bindingMut_20;
    val x_794 = x_793.asInstanceOf[scala.Any];
    val x_795 = listValMut_21;
    val x_796 = x_795.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_796.reply(this, x_794);
    resetData_4 = ();
    positionVar_27 = 97
  }));
  data_28.update(104, (() => {
    val x_797 = true.`unary_!`;
    if (x_797)
      {
        val x_798 = resetData_5.remove(0);
        val x_802 = x_798.find(((x_799: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_800 = x_799._1;
          val x_801 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
          x_800.==(x_801)
        }));
        val x_803 = x_802.get;
        val x_804 = x_803._2;
        positionVar_27 = x_804
      }
    else
      ()
  }));
  data_28.update(105, (() => {
    positionVar_27 = 106;
    val x_805 = currentTurn;
    val x_806 = x_805.+(1);
    currentTurn = x_806
  }));
  data_28.update(106, (() => positionVar_27 = 44));
  data_28.update(107, (() => {
    val x_807 = resetData_4;
    val x_808 = x_807.asInstanceOf[scala.Any];
    bindingMut_20 = x_808;
    val x_809 = bindingMut_20;
    val x_810 = x_809.asInstanceOf[scala.Any];
    val x_811 = listValMut_21;
    val x_812 = x_811.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_812.reply(this, x_810);
    resetData_4 = ();
    positionVar_27 = 97
  }));
  data_28.update(108, (() => {
    val x_813 = listValMut_21;
    val x_814 = x_813.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_815 = x_814.methodId;
    val x_816 = x_815.==(2);
    if (x_816)
      positionVar_27 = 101
    else
      ();
    val x_817 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_818 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_817, 84);
    val x_819 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_820 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_819, 103);
    val x_821 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_822 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_821, 24);
    val x_823 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_824 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_823, 139);
    val x_825 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_826 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_825, 117);
    val x_827 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_826);
    val x_828 = x_827.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_824);
    val x_829 = x_828.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_822);
    val x_830 = x_829.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_820);
    val x_831 = x_830.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_818);
    resetData_5.prepend(x_831)
  }));
  data_28.update(109, (() => {
    val x_832 = iterMut_22;
    val x_833 = x_832.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_834 = x_833.hasNext;
    val x_835 = x_834.`unary_!`;
    if (x_835)
      positionVar_27 = 110
    else
      ()
  }));
  data_28.update(110, (() => if (true)
    positionVar_27 = 100
  else
    positionVar_27 = 111));
  data_28.update(111, (() => {
    val x_836 = true.`unary_!`;
    if (x_836)
      {
        val x_837 = resetData_5.remove(0);
        val x_841 = x_837.find(((x_838: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_839 = x_838._1;
          val x_840 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
          x_839.==(x_840)
        }));
        val x_842 = x_841.get;
        val x_843 = x_842._2;
        positionVar_27 = x_843
      }
    else
      ()
  }));
  data_28.update(112, (() => {
    val x_844 = resetData_0;
    val x_845 = x_844.asInstanceOf[scala.Any];
    bindingMut_9 = x_845;
    val x_846 = bindingMut_9;
    val x_847 = x_846.asInstanceOf[scala.Any];
    val x_848 = listValMut_10;
    val x_849 = x_848.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_849.reply(this, x_847);
    resetData_0 = ();
    positionVar_27 = 61
  }));
  data_28.update(113, (() => {
    val x_850 = listValMut_10;
    val x_851 = x_850.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_852 = x_851.methodId;
    val x_853 = x_852.==(0);
    if (x_853)
      {
        val x_854 = java.lang.System.nanoTime();
        resetData_0 = x_854;
        val x_855 = resetData_0;
        val x_856 = x_855.asInstanceOf[scala.Long];
        bindingMut_8 = x_856;
        val x_857 = bindingMut_8;
        val x_858 = x_857.asInstanceOf[scala.Long];
        val x_859 = x_858.toString();
        resetData_0 = x_859;
        val x_860 = resetData_0;
        val x_861 = x_860.asInstanceOf[scala.Any];
        bindingMut_9 = x_861;
        val x_862 = bindingMut_9;
        val x_863 = x_862.asInstanceOf[scala.Any];
        val x_864 = listValMut_10;
        val x_865 = x_864.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_865.reply(this, x_863);
        resetData_0 = ();
        positionVar_27 = 61
      }
    else
      ()
  }));
  data_28.update(114, (() => {
    val x_866 = iterMut_11;
    val x_867 = x_866.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_868 = x_867.hasNext;
    val x_869 = x_868.`unary_!`;
    if (x_869)
      positionVar_27 = 115
    else
      ()
  }));
  data_28.update(115, (() => if (true)
    positionVar_27 = 64
  else
    positionVar_27 = 116));
  data_28.update(116, (() => {
    val x_870 = true.`unary_!`;
    if (x_870)
      {
        val x_871 = resetData_1.remove(0);
        val x_875 = x_871.find(((x_872: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_873 = x_872._1;
          val x_874 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
          x_873.==(x_874)
        }));
        val x_876 = x_875.get;
        val x_877 = x_876._2;
        positionVar_27 = x_877
      }
    else
      ()
  }));
  data_28.update(117, (() => {
    val x_878 = resetData_4;
    val x_879 = x_878.asInstanceOf[scala.Any];
    bindingMut_20 = x_879;
    val x_880 = bindingMut_20;
    val x_881 = x_880.asInstanceOf[scala.Any];
    val x_882 = listValMut_21;
    val x_883 = x_882.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_883.reply(this, x_881);
    resetData_4 = ();
    positionVar_27 = 118
  }));
  data_28.update(118, (() => positionVar_27 = 119));
  data_28.update(119, (() => {
    val x_884 = iterMut_22;
    val x_885 = x_884.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_886 = x_885.hasNext;
    if (x_886)
      {
        val x_887 = iterMut_22;
        val x_888 = x_887.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_889 = x_888.next();
        listValMut_21 = x_889;
        positionVar_27 = 120
      }
    else
      positionVar_27 = 124
  }));
  data_28.update(120, (() => {
    val x_890 = listValMut_21;
    val x_891 = x_890.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_892 = x_891.methodId;
    val x_893 = x_892.==(2);
    val x_894 = x_893.`unary_!`;
    if (x_894)
      positionVar_27 = 121
    else
      positionVar_27 = 123
  }));
  data_28.update(121, (() => {
    val x_895 = listValMut_21;
    val x_896 = x_895.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_897 = x_896.methodId;
    val x_898 = x_897.==(3);
    val x_899 = x_898.`unary_!`;
    if (x_899)
      {
        val x_900 = listValMut_21;
        val x_901 = x_900.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_902 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_901);
        val x_903 = this.addReceiveMessages(x_902);
        resetData_4 = x_903;
        positionVar_27 = 118
      }
    else
      positionVar_27 = 122
  }));
  data_28.update(122, (() => {
    val x_904 = listValMut_21;
    val x_905 = x_904.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_906 = x_905.methodId;
    val x_907 = x_906.==(3);
    if (x_907)
      positionVar_27 = 4
    else
      ();
    val x_908 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_909 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_908, 88);
    val x_910 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_911 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_910, 107);
    val x_912 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_913 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_912, 52);
    val x_914 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_915 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_914, 151);
    val x_916 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_917 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_916, 129);
    val x_918 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_917);
    val x_919 = x_918.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_915);
    val x_920 = x_919.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_913);
    val x_921 = x_920.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_911);
    val x_922 = x_921.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_909);
    resetData_5.prepend(x_922)
  }));
  data_28.update(123, (() => {
    val x_923 = listValMut_21;
    val x_924 = x_923.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_925 = x_924.methodId;
    val x_926 = x_925.==(2);
    if (x_926)
      positionVar_27 = 6
    else
      ();
    val x_927 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_928 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_927, 84);
    val x_929 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_930 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_929, 103);
    val x_931 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_932 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_931, 24);
    val x_933 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_934 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_933, 139);
    val x_935 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_936 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_935, 117);
    val x_937 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_936);
    val x_938 = x_937.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_934);
    val x_939 = x_938.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_932);
    val x_940 = x_939.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_930);
    val x_941 = x_940.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_928);
    resetData_5.prepend(x_941)
  }));
  data_28.update(124, (() => {
    val x_942 = iterMut_22;
    val x_943 = x_942.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_944 = x_943.hasNext;
    val x_945 = x_944.`unary_!`;
    if (x_945)
      positionVar_27 = 125
    else
      ()
  }));
  data_28.update(125, (() => if (true)
    positionVar_27 = 5
  else
    positionVar_27 = 126));
  data_28.update(126, (() => {
    val x_946 = true.`unary_!`;
    if (x_946)
      {
        val x_947 = resetData_5.remove(0);
        val x_951 = x_947.find(((x_948: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_949 = x_948._1;
          val x_950 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
          x_949.==(x_950)
        }));
        val x_952 = x_951.get;
        val x_953 = x_952._2;
        positionVar_27 = x_953
      }
    else
      ()
  }));
  data_28.update(127, (() => {
    positionVar_27 = 128;
    val x_954 = currentTurn;
    val x_955 = x_954.+(1);
    currentTurn = x_955
  }));
  data_28.update(128, (() => positionVar_27 = 28));
  data_28.update(129, (() => {
    val x_956 = resetData_4;
    val x_957 = x_956.asInstanceOf[scala.Any];
    bindingMut_20 = x_957;
    val x_958 = bindingMut_20;
    val x_959 = x_958.asInstanceOf[scala.Any];
    val x_960 = listValMut_21;
    val x_961 = x_960.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_961.reply(this, x_959);
    resetData_4 = ();
    positionVar_27 = 118
  }));
  data_28.update(130, (() => {
    val x_962 = true.`unary_!`;
    if (x_962)
      {
        val x_963 = resetData_5.remove(0);
        val x_967 = x_963.find(((x_964: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_965 = x_964._1;
          val x_966 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
          x_965.==(x_966)
        }));
        val x_968 = x_967.get;
        val x_969 = x_968._2;
        positionVar_27 = x_969
      }
    else
      ()
  }));
  data_28.update(131, (() => {
    val x_970 = true.`unary_!`;
    if (x_970)
      {
        val x_971 = resetData_1.remove(0);
        val x_975 = x_971.find(((x_972: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_973 = x_972._1;
          val x_974 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
          x_973.==(x_974)
        }));
        val x_976 = x_975.get;
        val x_977 = x_976._2;
        positionVar_27 = x_977
      }
    else
      ()
  }));
  data_28.update(132, (() => positionVar_27 = 133));
  data_28.update(133, (() => {
    positionVar_27 = 134;
    val x_978 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_979 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_978, 86);
    val x_980 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_981 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_980, 105);
    val x_982 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_983 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_982, 26);
    val x_984 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_985 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_984, 149);
    val x_986 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_987 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_986, 127);
    val x_988 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_987);
    val x_989 = x_988.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_985);
    val x_990 = x_989.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_983);
    val x_991 = x_990.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_981);
    val x_992 = x_991.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_979);
    resetData_5.prepend(x_992)
  }));
  data_28.update(134, (() => if (true)
    positionVar_27 = 135
  else
    positionVar_27 = 152));
  data_28.update(135, (() => {
    positionVar_27 = 136;
    val x_993 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_994 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_993, 83);
    val x_995 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_996 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_995, 102);
    val x_997 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_998 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_997, 23);
    val x_999 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_1000 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_999, 137);
    val x_1001 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_1002 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1001, 7);
    val x_1003 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1002);
    val x_1004 = x_1003.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1000);
    val x_1005 = x_1004.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_998);
    val x_1006 = x_1005.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_996);
    val x_1007 = x_1006.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_994);
    resetData_5.prepend(x_1007)
  }));
  data_28.update(136, (() => {
    val x_1008 = java.lang.System.nanoTime();
    resetData_4 = x_1008;
    val x_1009 = resetData_4;
    val x_1010 = x_1009.asInstanceOf[scala.Long];
    bindingMut_8 = x_1010;
    val x_1011 = bindingMut_8;
    val x_1012 = x_1011.asInstanceOf[scala.Long];
    val x_1013 = x_1012.toString();
    resetData_4 = x_1013;
    val x_1014 = resetData_4;
    val x_1015 = x_1014.asInstanceOf[java.lang.String];
    bindingMut_19 = x_1015;
    val x_1016 = bindingMut_19;
    val x_1017 = x_1016.asInstanceOf[java.lang.String];
    val x_1018 = "<html>".+(x_1017);
    resetData_4 = x_1018;
    val x_1019 = resetData_4;
    val x_1020 = x_1019.asInstanceOf[java.lang.String];
    bindingMut_18 = x_1020;
    val x_1021 = bindingMut_18;
    val x_1022 = x_1021.asInstanceOf[java.lang.String];
    val x_1023 = x_1022.+("</html>");
    resetData_4 = x_1023;
    val x_1024 = resetData_5.remove(0);
    val x_1028 = x_1024.find(((x_1025: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_1026 = x_1025._1;
      val x_1027 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
      x_1026.==(x_1027)
    }));
    val x_1029 = x_1028.get;
    val x_1030 = x_1029._2;
    positionVar_27 = x_1030
  }));
  data_28.update(137, (() => {
    val x_1031 = resetData_4;
    val x_1032 = x_1031.asInstanceOf[java.lang.String];
    bindingMut_25 = x_1032;
    val x_1033 = bindingMut_25;
    val x_1034 = x_1033.asInstanceOf[java.lang.String];
    val x_1035 = "requestPage content is ".+(x_1034);
    resetData_4 = x_1035;
    val x_1036 = resetData_4;
    val x_1037 = x_1036.asInstanceOf[java.lang.String];
    bindingMut_24 = x_1037;
    val x_1038 = bindingMut_24;
    val x_1039 = x_1038.asInstanceOf[java.lang.String];
    scala.Predef.println(x_1039);
    resetData_4 = ();
    resetData_4 = 0;
    val x_1040 = resetData_4;
    val x_1041 = x_1040.asInstanceOf[scala.Int];
    bindingMut_23 = x_1041;
    val x_1042 = bindingMut_23;
    val x_1043 = x_1042.asInstanceOf[scala.Int];
    val x_1044 = (1).-(x_1043);
    meta.deep.runtime.Actor.waitTurnList.append(x_1044);
    resetData_4 = ();
    val x_1045 = meta.deep.runtime.Actor.minTurn();
    val x_1046 = bindingMut_23;
    val x_1047 = x_1046.asInstanceOf[scala.Int];
    val x_1048 = x_1047.+(x_1045);
    resetData_4 = x_1048;
    val x_1049 = resetData_4;
    val x_1050 = x_1049.asInstanceOf[scala.Int];
    bindingMut_23 = x_1050;
    positionVar_27 = 138;
    val x_1051 = currentTurn;
    val x_1052 = x_1051.+(1);
    currentTurn = x_1052
  }));
  data_28.update(138, (() => positionVar_27 = 70));
  data_28.update(139, (() => {
    val x_1053 = resetData_4;
    val x_1054 = x_1053.asInstanceOf[scala.Any];
    bindingMut_20 = x_1054;
    val x_1055 = bindingMut_20;
    val x_1056 = x_1055.asInstanceOf[scala.Any];
    val x_1057 = listValMut_21;
    val x_1058 = x_1057.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1058.reply(this, x_1056);
    resetData_4 = ();
    positionVar_27 = 140
  }));
  data_28.update(140, (() => positionVar_27 = 141));
  data_28.update(141, (() => {
    val x_1059 = iterMut_22;
    val x_1060 = x_1059.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1061 = x_1060.hasNext;
    if (x_1061)
      {
        val x_1062 = iterMut_22;
        val x_1063 = x_1062.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_1064 = x_1063.next();
        listValMut_21 = x_1064;
        positionVar_27 = 142
      }
    else
      positionVar_27 = 146
  }));
  data_28.update(142, (() => {
    val x_1065 = listValMut_21;
    val x_1066 = x_1065.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1067 = x_1066.methodId;
    val x_1068 = x_1067.==(2);
    val x_1069 = x_1068.`unary_!`;
    if (x_1069)
      positionVar_27 = 143
    else
      positionVar_27 = 145
  }));
  data_28.update(143, (() => {
    val x_1070 = listValMut_21;
    val x_1071 = x_1070.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1072 = x_1071.methodId;
    val x_1073 = x_1072.==(3);
    val x_1074 = x_1073.`unary_!`;
    if (x_1074)
      {
        val x_1075 = listValMut_21;
        val x_1076 = x_1075.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_1077 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_1076);
        val x_1078 = this.addReceiveMessages(x_1077);
        resetData_4 = x_1078;
        positionVar_27 = 140
      }
    else
      positionVar_27 = 144
  }));
  data_28.update(144, (() => {
    val x_1079 = listValMut_21;
    val x_1080 = x_1079.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1081 = x_1080.methodId;
    val x_1082 = x_1081.==(3);
    if (x_1082)
      positionVar_27 = 134
    else
      ();
    val x_1083 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_1084 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1083, 88);
    val x_1085 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_1086 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1085, 107);
    val x_1087 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_1088 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1087, 52);
    val x_1089 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_1090 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1089, 151);
    val x_1091 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_1092 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1091, 129);
    val x_1093 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1092);
    val x_1094 = x_1093.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1090);
    val x_1095 = x_1094.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1088);
    val x_1096 = x_1095.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1086);
    val x_1097 = x_1096.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1084);
    resetData_5.prepend(x_1097)
  }));
  data_28.update(145, (() => {
    val x_1098 = listValMut_21;
    val x_1099 = x_1098.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1100 = x_1099.methodId;
    val x_1101 = x_1100.==(2);
    if (x_1101)
      positionVar_27 = 136
    else
      ();
    val x_1102 = scala.Tuple2.apply[scala.Int, scala.Int](3, 45);
    val x_1103 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1102, 84);
    val x_1104 = scala.Tuple2.apply[scala.Int, scala.Int](1, 45);
    val x_1105 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1104, 103);
    val x_1106 = scala.Tuple2.apply[scala.Int, scala.Int](1, 21);
    val x_1107 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1106, 24);
    val x_1108 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
    val x_1109 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1108, 139);
    val x_1110 = scala.Tuple2.apply[scala.Int, scala.Int](0, 21);
    val x_1111 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1110, 117);
    val x_1112 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1111);
    val x_1113 = x_1112.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1109);
    val x_1114 = x_1113.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1107);
    val x_1115 = x_1114.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1105);
    val x_1116 = x_1115.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1103);
    resetData_5.prepend(x_1116)
  }));
  data_28.update(146, (() => {
    val x_1117 = iterMut_22;
    val x_1118 = x_1117.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1119 = x_1118.hasNext;
    val x_1120 = x_1119.`unary_!`;
    if (x_1120)
      positionVar_27 = 147
    else
      ()
  }));
  data_28.update(147, (() => if (true)
    positionVar_27 = 135
  else
    positionVar_27 = 148));
  data_28.update(148, (() => {
    val x_1121 = true.`unary_!`;
    if (x_1121)
      {
        val x_1122 = resetData_5.remove(0);
        val x_1126 = x_1122.find(((x_1123: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1124 = x_1123._1;
          val x_1125 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
          x_1124.==(x_1125)
        }));
        val x_1127 = x_1126.get;
        val x_1128 = x_1127._2;
        positionVar_27 = x_1128
      }
    else
      ()
  }));
  data_28.update(149, (() => {
    positionVar_27 = 150;
    val x_1129 = currentTurn;
    val x_1130 = x_1129.+(1);
    currentTurn = x_1130
  }));
  data_28.update(150, (() => positionVar_27 = 44));
  data_28.update(151, (() => {
    val x_1131 = resetData_4;
    val x_1132 = x_1131.asInstanceOf[scala.Any];
    bindingMut_20 = x_1132;
    val x_1133 = bindingMut_20;
    val x_1134 = x_1133.asInstanceOf[scala.Any];
    val x_1135 = listValMut_21;
    val x_1136 = x_1135.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1136.reply(this, x_1134);
    resetData_4 = ();
    positionVar_27 = 140
  }));
  data_28.update(152, (() => {
    val x_1137 = true.`unary_!`;
    if (x_1137)
      {
        val x_1138 = resetData_5.remove(0);
        val x_1142 = x_1138.find(((x_1139: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1140 = x_1139._1;
          val x_1141 = scala.Tuple2.apply[scala.Int, scala.Int](0, 45);
          x_1140.==(x_1141)
        }));
        val x_1143 = x_1142.get;
        val x_1144 = x_1143._2;
        positionVar_27 = x_1144
      }
    else
      ()
  }));
  data_28.update(153, (() => {
    val x_1145 = resetData_0;
    val x_1146 = x_1145.asInstanceOf[scala.Any];
    bindingMut_9 = x_1146;
    val x_1147 = bindingMut_9;
    val x_1148 = x_1147.asInstanceOf[scala.Any];
    val x_1149 = listValMut_10;
    val x_1150 = x_1149.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1150.reply(this, x_1148);
    resetData_0 = ();
    positionVar_27 = 154
  }));
  data_28.update(154, (() => positionVar_27 = 155));
  data_28.update(155, (() => {
    val x_1151 = iterMut_11;
    val x_1152 = x_1151.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1153 = x_1152.hasNext;
    if (x_1153)
      {
        val x_1154 = iterMut_11;
        val x_1155 = x_1154.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_1156 = x_1155.next();
        listValMut_10 = x_1156;
        positionVar_27 = 156
      }
    else
      positionVar_27 = 160
  }));
  data_28.update(156, (() => {
    val x_1157 = listValMut_10;
    val x_1158 = x_1157.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1159 = x_1158.methodId;
    val x_1160 = x_1159.==(0);
    val x_1161 = x_1160.`unary_!`;
    if (x_1161)
      positionVar_27 = 157
    else
      positionVar_27 = 159
  }));
  data_28.update(157, (() => {
    val x_1162 = listValMut_10;
    val x_1163 = x_1162.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1164 = x_1163.methodId;
    val x_1165 = x_1164.==(1);
    val x_1166 = x_1165.`unary_!`;
    if (x_1166)
      {
        val x_1167 = listValMut_10;
        val x_1168 = x_1167.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_1169 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_1168);
        val x_1170 = this.addReceiveMessages(x_1169);
        resetData_0 = x_1170;
        positionVar_27 = 154
      }
    else
      positionVar_27 = 158
  }));
  data_28.update(158, (() => {
    val x_1171 = listValMut_10;
    val x_1172 = x_1171.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1173 = x_1172.methodId;
    val x_1174 = x_1173.==(1);
    if (x_1174)
      positionVar_27 = 1
    else
      ();
    val x_1175 = scala.Tuple2.apply[scala.Int, scala.Int](2, -1);
    val x_1176 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1175, 47);
    val x_1177 = scala.Tuple2.apply[scala.Int, scala.Int](1, -1);
    val x_1178 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1177, 112);
    val x_1179 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
    val x_1180 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1179, 153);
    val x_1181 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1180);
    val x_1182 = x_1181.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1178);
    val x_1183 = x_1182.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1176);
    resetData_1.prepend(x_1183)
  }));
  data_28.update(159, (() => {
    val x_1184 = listValMut_10;
    val x_1185 = x_1184.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1186 = x_1185.methodId;
    val x_1187 = x_1186.==(0);
    if (x_1187)
      {
        val x_1188 = java.lang.System.nanoTime();
        resetData_0 = x_1188;
        val x_1189 = resetData_0;
        val x_1190 = x_1189.asInstanceOf[scala.Long];
        bindingMut_8 = x_1190;
        val x_1191 = bindingMut_8;
        val x_1192 = x_1191.asInstanceOf[scala.Long];
        val x_1193 = x_1192.toString();
        resetData_0 = x_1193;
        val x_1194 = resetData_0;
        val x_1195 = x_1194.asInstanceOf[scala.Any];
        bindingMut_9 = x_1195;
        val x_1196 = bindingMut_9;
        val x_1197 = x_1196.asInstanceOf[scala.Any];
        val x_1198 = listValMut_10;
        val x_1199 = x_1198.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_1199.reply(this, x_1197);
        resetData_0 = ();
        positionVar_27 = 154
      }
    else
      ()
  }));
  data_28.update(160, (() => {
    val x_1200 = iterMut_11;
    val x_1201 = x_1200.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1202 = x_1201.hasNext;
    val x_1203 = x_1202.`unary_!`;
    if (x_1203)
      positionVar_27 = 161
    else
      ()
  }));
  data_28.update(161, (() => if (true)
    positionVar_27 = 2
  else
    positionVar_27 = 162));
  data_28.update(162, (() => {
    val x_1204 = true.`unary_!`;
    if (x_1204)
      {
        val x_1205 = resetData_1.remove(0);
        val x_1209 = x_1205.find(((x_1206: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1207 = x_1206._1;
          val x_1208 = scala.Tuple2.apply[scala.Int, scala.Int](0, -1);
          x_1207.==(x_1208)
        }));
        val x_1210 = x_1209.get;
        val x_1211 = x_1210._2;
        positionVar_27 = x_1211
      }
    else
      ()
  }));
  data_28
}).apply();
  
  override def run_until(until_1213: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_1214 = currentTurn;
      val x_1215 = x_1214.<=(until_1213);
      x_1215.&&({
        val x_1216 = positionVar_27;
        val x_1217 = commands_1212.length;
        x_1216.<(x_1217)
      })
    }) 
      {
        val x_1218 = positionVar_27;
        val x_1219 = commands_1212.apply(x_1218);
        x_1219.apply()
      }
    ;
    this
  }
}
