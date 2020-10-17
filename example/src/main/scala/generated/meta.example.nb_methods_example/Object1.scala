package generated.meta.example.nb_methods_example

class Object1 (var n1: generated.meta.example.nb_methods_example.Object2) extends meta.deep.runtime.Actor {
  var future_obj1: Option[meta.deep.runtime.Future[Int]] = scala.None
  var secretToken: Double = 0.5
  var secretToken2: Double = meta.example.nb_methods_example.expSetup.someVal
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: java.lang.String = null;
  private var bindingMut_4: scala.Long = 0L;
  private var bindingMut_5: scala.None.type = null;
  private var bindingMut_6: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_7: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: scala.Long = 0L;
  private var bindingMut_10: java.lang.String = null;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_14: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_15: scala.Boolean = false;
  private var bindingMut_16: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_17: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_18: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_19: generated.meta.example.nb_methods_example.Object2 = null;
  private var bindingMut_20: java.lang.String = null;
  private var bindingMut_21: scala.Long = 0L;
  private var bindingMut_22: scala.Boolean = false;
  private var bindingMut_23: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_24: scala.Double = 0.0;
  private var bindingMut_25: scala.Double = 0.0;
  private var bindingMut_26: scala.Boolean = false;
  private var bindingMut_27: scala.Int = 0;
  private var bindingMut_28: scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]] = null;
  private var bindingMut_29: scala.Any = null;
  private var listValMut_30: meta.deep.runtime.RequestMessage = null;
  private var iterMut_31: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_33: scala.Int = 0;
  
  val commands_316 = (() => {
  val data_34 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_34.update(0, (() => {
    positionVar_33 = 1;
    val x_35 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_36 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_35, 18);
    val x_37 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_36);
    resetData_1.prepend(x_37)
  }));
  data_34.update(1, (() => if (true)
    positionVar_33 = 2
  else
    positionVar_33 = 26));
  data_34.update(2, (() => {
    val x_38 = this.popRequestMessages;
    val x_39 = x_38.iterator;
    iterMut_31 = x_39;
    positionVar_33 = 3
  }));
  data_34.update(3, (() => {
    val x_40 = iterMut_31;
    val x_41 = x_40.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_42 = x_41.hasNext;
    if (x_42)
      {
        val x_43 = iterMut_31;
        val x_44 = x_43.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_45 = x_44.next();
        listValMut_30 = x_45;
        positionVar_33 = 4
      }
    else
      positionVar_33 = 25
  }));
  data_34.update(4, (() => {
    val x_46 = listValMut_30;
    val x_47 = x_46.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_48 = x_47.methodId;
    val x_49 = x_48.==(2);
    val x_50 = x_49.`unary_!`;
    if (x_50)
      positionVar_33 = 5
    else
      positionVar_33 = 8
  }));
  data_34.update(5, (() => {
    val x_51 = listValMut_30;
    val x_52 = x_51.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_53 = x_52.methodId;
    val x_54 = x_53.==(3);
    val x_55 = x_54.`unary_!`;
    if (x_55)
      {
        val x_56 = listValMut_30;
        val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_58 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_57);
        val x_59 = this.addReceiveMessages(x_58);
        resetData_0 = x_59;
        positionVar_33 = 6
      }
    else
      positionVar_33 = 7
  }));
  data_34.update(6, (() => positionVar_33 = 3));
  data_34.update(7, (() => {
    val x_60 = listValMut_30;
    val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_62 = x_61.methodId;
    val x_63 = x_62.==(3);
    if (x_63)
      positionVar_33 = 1
    else
      ();
    val x_64 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_65 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_64, 17);
    val x_66 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_65);
    resetData_1.prepend(x_66)
  }));
  data_34.update(8, (() => {
    val x_67 = listValMut_30;
    val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_69 = x_68.methodId;
    val x_70 = x_69.==(2);
    if (x_70)
      positionVar_33 = 9
    else
      ();
    val x_71 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_72 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_71, 21);
    val x_73 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_72);
    resetData_1.prepend(x_73)
  }));
  data_34.update(9, (() => {
    val x_74 = this.future_obj1;
    resetData_0 = x_74;
    val x_75 = resetData_0;
    val x_76 = x_75.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    bindingMut_23 = x_76;
    val x_77 = bindingMut_23;
    val x_78 = x_77.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    val x_79 = x_78.==(scala.None);
    resetData_0 = x_79;
    val x_80 = resetData_0;
    val x_81 = x_80.asInstanceOf[scala.Boolean];
    bindingMut_22 = x_81;
    positionVar_33 = 10
  }));
  data_34.update(10, (() => {
    val x_82 = bindingMut_22;
    val x_83 = x_82.asInstanceOf[scala.Boolean];
    if (x_83)
      {
        val x_84 = this.id;
        resetData_0 = x_84;
        val x_85 = resetData_0;
        val x_86 = x_85.asInstanceOf[scala.Long];
        bindingMut_21 = x_86;
        val x_87 = bindingMut_21;
        val x_88 = x_87.asInstanceOf[scala.Long];
        val x_89 = "Send async msg1 ".+(x_88);
        resetData_0 = x_89;
        val x_90 = resetData_0;
        val x_91 = x_90.asInstanceOf[java.lang.String];
        bindingMut_20 = x_91;
        val x_92 = bindingMut_20;
        val x_93 = x_92.asInstanceOf[java.lang.String];
        scala.Predef.println(x_93);
        resetData_0 = ();
        val x_94 = this.n1;
        resetData_0 = x_94;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[generated.meta.example.nb_methods_example.Object2];
        bindingMut_19 = x_96;
        val x_97 = ((this): meta.deep.runtime.Actor).id;
        val x_99 = {
          val x_98 = bindingMut_19;
          x_98.asInstanceOf[generated.meta.example.nb_methods_example.Object2]
        };
        val x_100 = x_99.id;
        val x$1_101 = this.id;
        val x$1_102 = this.secretToken;
        val x$1_103 = this.secretToken2;
        val x_104 = scala.collection.immutable.Nil.::[scala.Any](x$1_103);
        val x_105 = x_104.::[scala.Any](x$1_102);
        val x_106 = x_105.::[scala.Any](x$1_101);
        val x_107 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_106);
        val x_108 = meta.deep.runtime.RequestMessage.apply(x_97, x_100, 0, x_107);
        val x_109 = x_108.future;
        val x_110 = x_109.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        x_108.`future_=`(x_110);
        ((this): meta.deep.runtime.Actor).sendMessage(x_108);
        val x_111 = x_108.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_111, ((response_112: meta.deep.runtime.Message) => {
          val x_113 = x_108.future;
          val x_114 = response_112.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_115 = x_114.arg;
          val x_116 = x_113.setValue[scala.Any](x_115);
          val x_117 = x_116.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
          x_108.`future_=`(x_117);
          val x_118 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_119 = x_108.future;
          val x_120 = x_119.id;
          val x_121 = scala.Predef.ArrowAssoc[java.lang.String](x_120);
          val x_122 = x_108.future;
          val x_123 = x_121.->[meta.deep.runtime.Future[scala.Any]](x_122);
          val x_124 = x_118.+[meta.deep.runtime.Future[scala.Any]](x_123);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_124)
        }));
        val x_125 = x_108.future;
        val x_126 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_125);
        resetData_0 = x_126;
        val x_127 = resetData_0;
        val x_128 = x_127.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_18 = x_128;
        val x_129 = bindingMut_18;
        val x_130 = x_129.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        this.`future_obj1_=`(x_130);
        resetData_0 = ();
        val x_131 = resetData_1.remove(0);
        val x_135 = x_131.find(((x_132: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_133 = x_132._1;
          val x_134 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_133.==(x_134)
        }));
        val x_136 = x_135.get;
        val x_137 = x_136._2;
        positionVar_33 = x_137
      }
    else
      positionVar_33 = 22
  }));
  data_34.update(11, (() => {
    val x_138 = this.async_messages;
    resetData_0 = x_138;
    val x_139 = resetData_0;
    val x_140 = x_139.asInstanceOf[scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]]];
    bindingMut_28 = x_140;
    val x_141 = bindingMut_28;
    val x_142 = x_141.asInstanceOf[scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]]];
    val x_143 = x_142.size;
    resetData_0 = x_143;
    val x_144 = resetData_0;
    val x_145 = x_144.asInstanceOf[scala.Int];
    bindingMut_27 = x_145;
    val x_146 = bindingMut_27;
    val x_147 = x_146.asInstanceOf[scala.Int];
    val x_148 = x_147.==(0);
    resetData_0 = x_148;
    val x_149 = resetData_0;
    val x_150 = x_149.asInstanceOf[scala.Boolean];
    bindingMut_26 = x_150;
    val x_151 = bindingMut_26;
    val x_152 = x_151.asInstanceOf[scala.Boolean];
    scala.Predef.assert(x_152);
    resetData_0 = ();
    val x_153 = 1.toDouble;
    resetData_0 = x_153;
    val x_154 = resetData_0;
    val x_155 = x_154.asInstanceOf[scala.Double];
    bindingMut_25 = x_155;
    resetData_0 = 0.0;
    val x_156 = resetData_0;
    val x_157 = x_156.asInstanceOf[scala.Double];
    bindingMut_24 = x_157;
    positionVar_33 = 12
  }));
  data_34.update(12, (() => {
    val x_158 = meta.deep.runtime.Actor.proceedLabel;
    val x_159 = x_158("turn");
    val x_160 = bindingMut_24;
    val x_161 = x_160.asInstanceOf[scala.Double];
    val x_162 = x_161.+(x_159);
    resetData_0 = x_162;
    val x_163 = resetData_0;
    val x_164 = x_163.asInstanceOf[scala.Double];
    bindingMut_24 = x_164;
    val x_165 = meta.deep.runtime.Actor.labelVals("turn");
    val x_166 = bindingMut_24;
    val x_167 = x_166.asInstanceOf[scala.Double];
    val x_168 = bindingMut_25;
    val x_169 = x_168.asInstanceOf[scala.Double];
    val x_170 = x_169.-(x_167);
    x_165.append(x_170);
    resetData_0 = ();
    positionVar_33 = 13;
    val x_171 = currentTurn;
    val x_172 = x_171.+(1);
    currentTurn = x_172
  }));
  data_34.update(13, (() => {
    val x_173 = bindingMut_24;
    val x_174 = x_173.asInstanceOf[scala.Double];
    val x_175 = bindingMut_25;
    val x_176 = x_175.asInstanceOf[scala.Double];
    val x_177 = x_174.<(x_176);
    if (x_177)
      positionVar_33 = 12
    else
      positionVar_33 = 14
  }));
  data_34.update(14, (() => {
    val x_178 = bindingMut_24;
    val x_179 = x_178.asInstanceOf[scala.Double];
    val x_180 = bindingMut_25;
    val x_181 = x_180.asInstanceOf[scala.Double];
    val x_182 = x_179.<(x_181);
    val x_183 = x_182.`unary_!`;
    if (x_183)
      positionVar_33 = 15
    else
      ()
  }));
  data_34.update(15, (() => if (true)
    positionVar_33 = 2
  else
    positionVar_33 = 16));
  data_34.update(16, (() => {
    val x_184 = true.`unary_!`;
    if (x_184)
      {
        val x_185 = resetData_1.remove(0);
        val x_189 = x_185.find(((x_186: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_187 = x_186._1;
          val x_188 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_187.==(x_188)
        }));
        val x_190 = x_189.get;
        val x_191 = x_190._2;
        positionVar_33 = x_191
      }
    else
      ()
  }));
  data_34.update(17, (() => {
    val x_192 = resetData_0;
    val x_193 = x_192.asInstanceOf[scala.Any];
    bindingMut_29 = x_193;
    val x_194 = bindingMut_29;
    val x_195 = x_194.asInstanceOf[scala.Any];
    val x_196 = listValMut_30;
    val x_197 = x_196.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_197.reply(this, x_195);
    resetData_0 = ();
    positionVar_33 = 6
  }));
  data_34.update(18, (() => positionVar_33 = 19));
  data_34.update(19, (() => {
    positionVar_33 = 20;
    val x_198 = currentTurn;
    val x_199 = x_198.+(1);
    currentTurn = x_199
  }));
  data_34.update(20, (() => positionVar_33 = 19));
  data_34.update(21, (() => {
    val x_200 = resetData_0;
    val x_201 = x_200.asInstanceOf[scala.Any];
    bindingMut_29 = x_201;
    val x_202 = bindingMut_29;
    val x_203 = x_202.asInstanceOf[scala.Any];
    val x_204 = listValMut_30;
    val x_205 = x_204.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_205.reply(this, x_203);
    resetData_0 = ();
    positionVar_33 = 6
  }));
  data_34.update(22, (() => {
    val x_206 = bindingMut_22;
    val x_207 = x_206.asInstanceOf[scala.Boolean];
    val x_208 = x_207.`unary_!`;
    if (x_208)
      {
        val x_209 = this.future_obj1;
        resetData_0 = x_209;
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_17 = x_211;
        val x_212 = bindingMut_17;
        val x_213 = x_212.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_214 = x_213.get;
        resetData_0 = x_214;
        val x_215 = resetData_0;
        val x_216 = x_215.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_16 = x_216;
        val x_217 = bindingMut_16;
        val x_218 = x_217.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_219 = this.isCompleted(x_218);
        resetData_0 = x_219;
        val x_220 = resetData_0;
        val x_221 = x_220.asInstanceOf[scala.Boolean];
        bindingMut_15 = x_221;
        positionVar_33 = 23
      }
    else
      ()
  }));
  data_34.update(23, (() => {
    val x_222 = bindingMut_15;
    val x_223 = x_222.asInstanceOf[scala.Boolean];
    if (x_223)
      {
        val x_224 = this.future_obj1;
        resetData_0 = x_224;
        val x_225 = resetData_0;
        val x_226 = x_225.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_14 = x_226;
        val x_227 = bindingMut_14;
        val x_228 = x_227.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_229 = x_228.get;
        resetData_0 = x_229;
        val x_230 = resetData_0;
        val x_231 = x_230.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_13 = x_231;
        val x_232 = bindingMut_13;
        val x_233 = x_232.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_234 = this.getFutureValue[scala.Int](x_233);
        resetData_0 = x_234;
        val x_235 = resetData_0;
        val x_236 = x_235.asInstanceOf[scala.Int];
        bindingMut_12 = x_236;
        val x_237 = bindingMut_12;
        val x_238 = x_237.asInstanceOf[scala.Int];
        val x_239 = "Response received ".+(x_238);
        resetData_0 = x_239;
        val x_240 = resetData_0;
        val x_241 = x_240.asInstanceOf[java.lang.String];
        bindingMut_11 = x_241;
        val x_242 = bindingMut_11;
        val x_243 = x_242.asInstanceOf[java.lang.String];
        val x_244 = x_243.+(" Id: ");
        resetData_0 = x_244;
        val x_245 = resetData_0;
        val x_246 = x_245.asInstanceOf[java.lang.String];
        bindingMut_10 = x_246;
        val x_247 = this.id;
        resetData_0 = x_247;
        val x_248 = resetData_0;
        val x_249 = x_248.asInstanceOf[scala.Long];
        bindingMut_9 = x_249;
        val x_250 = bindingMut_9;
        val x_251 = x_250.asInstanceOf[scala.Long];
        val x_252 = bindingMut_10;
        val x_253 = x_252.asInstanceOf[java.lang.String];
        val x_254 = x_253.+(x_251);
        resetData_0 = x_254;
        val x_255 = resetData_0;
        val x_256 = x_255.asInstanceOf[java.lang.String];
        bindingMut_8 = x_256;
        val x_257 = bindingMut_8;
        val x_258 = x_257.asInstanceOf[java.lang.String];
        scala.Predef.println(x_258);
        resetData_0 = ();
        val x_259 = this.future_obj1;
        resetData_0 = x_259;
        val x_260 = resetData_0;
        val x_261 = x_260.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_7 = x_261;
        val x_262 = bindingMut_7;
        val x_263 = x_262.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_264 = x_263.get;
        resetData_0 = x_264;
        val x_265 = resetData_0;
        val x_266 = x_265.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_6 = x_266;
        val x_267 = bindingMut_6;
        val x_268 = x_267.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_269 = this.clearFutureObj(x_268);
        resetData_0 = x_269;
        val x_270 = resetData_0;
        val x_271 = x_270.asInstanceOf[scala.None.type];
        bindingMut_5 = x_271;
        val x_272 = bindingMut_5;
        val x_273 = x_272.asInstanceOf[scala.None.type];
        this.`future_obj1_=`(x_273);
        resetData_0 = ();
        val x_274 = resetData_1.remove(0);
        val x_278 = x_274.find(((x_275: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_276 = x_275._1;
          val x_277 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_276.==(x_277)
        }));
        val x_279 = x_278.get;
        val x_280 = x_279._2;
        positionVar_33 = x_280
      }
    else
      positionVar_33 = 24
  }));
  data_34.update(24, (() => {
    val x_281 = bindingMut_15;
    val x_282 = x_281.asInstanceOf[scala.Boolean];
    val x_283 = x_282.`unary_!`;
    if (x_283)
      {
        val x_284 = this.id;
        resetData_0 = x_284;
        val x_285 = resetData_0;
        val x_286 = x_285.asInstanceOf[scala.Long];
        bindingMut_4 = x_286;
        val x_287 = bindingMut_4;
        val x_288 = x_287.asInstanceOf[scala.Long];
        val x_289 = "msg not completed! ".+(x_288);
        resetData_0 = x_289;
        val x_290 = resetData_0;
        val x_291 = x_290.asInstanceOf[java.lang.String];
        bindingMut_3 = x_291;
        val x_292 = bindingMut_3;
        val x_293 = x_292.asInstanceOf[java.lang.String];
        scala.Predef.println(x_293);
        resetData_0 = ();
        val x_294 = resetData_1.remove(0);
        val x_298 = x_294.find(((x_295: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_296 = x_295._1;
          val x_297 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_296.==(x_297)
        }));
        val x_299 = x_298.get;
        val x_300 = x_299._2;
        positionVar_33 = x_300
      }
    else
      ()
  }));
  data_34.update(25, (() => {
    val x_301 = iterMut_31;
    val x_302 = x_301.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_303 = x_302.hasNext;
    val x_304 = x_303.`unary_!`;
    if (x_304)
      positionVar_33 = 9
    else
      ();
    val x_305 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_306 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_305, 11);
    val x_307 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_306);
    resetData_1.prepend(x_307)
  }));
  data_34.update(26, (() => {
    val x_308 = true.`unary_!`;
    if (x_308)
      {
        val x_309 = resetData_1.remove(0);
        val x_313 = x_309.find(((x_310: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_311 = x_310._1;
          val x_312 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_311.==(x_312)
        }));
        val x_314 = x_313.get;
        val x_315 = x_314._2;
        positionVar_33 = x_315
      }
    else
      ()
  }));
  data_34
}).apply();
  
  override def run_until(until_317: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_318 = currentTurn;
      val x_319 = x_318.<=(until_317);
      x_319.&&({
        val x_320 = positionVar_33;
        val x_321 = commands_316.length;
        x_320.<(x_321)
      })
    }) 
      {
        val x_322 = positionVar_33;
        val x_323 = commands_316.apply(x_322);
        x_323.apply()
      }
    ;
    this
  }
}
