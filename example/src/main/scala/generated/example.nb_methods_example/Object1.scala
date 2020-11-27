package generated.example.nb_methods_example

class Object1 (var n1: generated.example.nb_methods_example.Object2) extends meta.deep.runtime.Actor {
  var future_obj1: Option[meta.deep.runtime.Future[Int]] = scala.None
  var secretToken: Double = 0.5
  var secretToken2: Double = example.nb_methods_example.expSetup.someVal
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
  private var bindingMut_19: java.lang.String = null;
  private var bindingMut_20: scala.Long = 0L;
  private var bindingMut_21: scala.Boolean = false;
  private var bindingMut_22: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_23: scala.Double = 0.0;
  private var bindingMut_24: scala.Double = 0.0;
  private var bindingMut_25: scala.Boolean = false;
  private var bindingMut_26: scala.Int = 0;
  private var bindingMut_27: scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]] = null;
  private var bindingMut_28: scala.Any = null;
  private var listValMut_29: meta.deep.runtime.RequestMessage = null;
  private var iterMut_30: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_32: scala.Int = 0;
  
  val commands_311 = (() => {
  val data_33 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_33.update(0, (() => {
    positionVar_32 = 1;
    val x_34 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_35 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_34, 18);
    val x_36 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_35);
    resetData_1.prepend(x_36)
  }));
  data_33.update(1, (() => if (true)
    positionVar_32 = 2
  else
    positionVar_32 = 26));
  data_33.update(2, (() => {
    val x_37 = this.popRequestMessages;
    val x_38 = x_37.iterator;
    iterMut_30 = x_38;
    positionVar_32 = 3
  }));
  data_33.update(3, (() => {
    val x_39 = iterMut_30;
    val x_40 = x_39.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_41 = x_40.hasNext;
    if (x_41)
      {
        val x_42 = iterMut_30;
        val x_43 = x_42.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_44 = x_43.next();
        listValMut_29 = x_44;
        positionVar_32 = 4
      }
    else
      positionVar_32 = 25
  }));
  data_33.update(4, (() => {
    val x_45 = listValMut_29;
    val x_46 = x_45.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_47 = x_46.methodId;
    val x_48 = x_47.==(2);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      positionVar_32 = 5
    else
      positionVar_32 = 8
  }));
  data_33.update(5, (() => {
    val x_50 = listValMut_29;
    val x_51 = x_50.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_52 = x_51.methodId;
    val x_53 = x_52.==(3);
    val x_54 = x_53.`unary_!`;
    if (x_54)
      {
        val x_55 = listValMut_29;
        val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_57 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_56);
        val x_58 = this.addReceiveMessages(x_57);
        resetData_0 = x_58;
        positionVar_32 = 6
      }
    else
      positionVar_32 = 7
  }));
  data_33.update(6, (() => positionVar_32 = 3));
  data_33.update(7, (() => {
    val x_59 = listValMut_29;
    val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_61 = x_60.methodId;
    val x_62 = x_61.==(3);
    if (x_62)
      positionVar_32 = 1
    else
      ();
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 17);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_33.update(8, (() => {
    val x_66 = listValMut_29;
    val x_67 = x_66.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_68 = x_67.methodId;
    val x_69 = x_68.==(2);
    if (x_69)
      positionVar_32 = 9
    else
      ();
    val x_70 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_71 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_70, 21);
    val x_72 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_71);
    resetData_1.prepend(x_72)
  }));
  data_33.update(9, (() => {
    val x_73 = this.future_obj1;
    resetData_0 = x_73;
    val x_74 = resetData_0;
    val x_75 = x_74.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    bindingMut_22 = x_75;
    val x_76 = bindingMut_22;
    val x_77 = x_76.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    val x_78 = x_77.==(scala.None);
    resetData_0 = x_78;
    val x_79 = resetData_0;
    val x_80 = x_79.asInstanceOf[scala.Boolean];
    bindingMut_21 = x_80;
    positionVar_32 = 10
  }));
  data_33.update(10, (() => {
    val x_81 = bindingMut_21;
    val x_82 = x_81.asInstanceOf[scala.Boolean];
    if (x_82)
      {
        val x_83 = this.id;
        resetData_0 = x_83;
        val x_84 = resetData_0;
        val x_85 = x_84.asInstanceOf[scala.Long];
        bindingMut_20 = x_85;
        val x_86 = bindingMut_20;
        val x_87 = x_86.asInstanceOf[scala.Long];
        val x_88 = "Send async msg1 ".+(x_87);
        resetData_0 = x_88;
        val x_89 = resetData_0;
        val x_90 = x_89.asInstanceOf[java.lang.String];
        bindingMut_19 = x_90;
        val x_91 = bindingMut_19;
        val x_92 = x_91.asInstanceOf[java.lang.String];
        scala.Predef.println(x_92);
        resetData_0 = ();
        val receiver_93 = this.n1;
        val x_94 = ((this): meta.deep.runtime.Actor).id;
        val x_95 = receiver_93.id;
        val x$1_96 = this.id;
        val x$1_97 = this.secretToken;
        val x$1_98 = this.secretToken2;
        val x_99 = scala.collection.immutable.Nil.::[scala.Any](x$1_98);
        val x_100 = x_99.::[scala.Any](x$1_97);
        val x_101 = x_100.::[scala.Any](x$1_96);
        val x_102 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_101);
        val x_103 = meta.deep.runtime.RequestMessage.apply(x_94, x_95, 0, x_102);
        val x_104 = x_103.future;
        val x_105 = x_104.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        x_103.`future_=`(x_105);
        ((this): meta.deep.runtime.Actor).sendMessage(x_103);
        val x_106 = x_103.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_106, ((response_107: meta.deep.runtime.Message) => {
          val x_108 = x_103.future;
          val x_109 = response_107.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_110 = x_109.arg;
          val x_111 = x_108.setValue[scala.Any](x_110);
          val x_112 = x_111.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
          x_103.`future_=`(x_112);
          val x_113 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_114 = x_103.future;
          val x_115 = x_114.id;
          val x_116 = scala.Predef.ArrowAssoc[java.lang.String](x_115);
          val x_117 = x_103.future;
          val x_118 = x_116.->[meta.deep.runtime.Future[scala.Any]](x_117);
          val x_119 = x_113.+[meta.deep.runtime.Future[scala.Any]](x_118);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_119)
        }));
        val x_120 = x_103.future;
        val x_121 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_120);
        resetData_0 = x_121;
        val x_122 = resetData_0;
        val x_123 = x_122.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_18 = x_123;
        val x_124 = bindingMut_18;
        val x_125 = x_124.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        this.`future_obj1_=`(x_125);
        resetData_0 = ();
        val x_126 = resetData_1.remove(0);
        val x_130 = x_126.find(((x_127: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_128 = x_127._1;
          val x_129 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_128.==(x_129)
        }));
        val x_131 = x_130.get;
        val x_132 = x_131._2;
        positionVar_32 = x_132
      }
    else
      positionVar_32 = 22
  }));
  data_33.update(11, (() => {
    val x_133 = this.async_messages;
    resetData_0 = x_133;
    val x_134 = resetData_0;
    val x_135 = x_134.asInstanceOf[scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]]];
    bindingMut_27 = x_135;
    val x_136 = bindingMut_27;
    val x_137 = x_136.asInstanceOf[scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]]];
    val x_138 = x_137.size;
    resetData_0 = x_138;
    val x_139 = resetData_0;
    val x_140 = x_139.asInstanceOf[scala.Int];
    bindingMut_26 = x_140;
    val x_141 = bindingMut_26;
    val x_142 = x_141.asInstanceOf[scala.Int];
    val x_143 = x_142.==(0);
    resetData_0 = x_143;
    val x_144 = resetData_0;
    val x_145 = x_144.asInstanceOf[scala.Boolean];
    bindingMut_25 = x_145;
    val x_146 = bindingMut_25;
    val x_147 = x_146.asInstanceOf[scala.Boolean];
    scala.Predef.assert(x_147);
    resetData_0 = ();
    val x_148 = 1.toDouble;
    resetData_0 = x_148;
    val x_149 = resetData_0;
    val x_150 = x_149.asInstanceOf[scala.Double];
    bindingMut_24 = x_150;
    resetData_0 = 0.0;
    val x_151 = resetData_0;
    val x_152 = x_151.asInstanceOf[scala.Double];
    bindingMut_23 = x_152;
    positionVar_32 = 12
  }));
  data_33.update(12, (() => {
    val x_153 = meta.deep.runtime.Actor.proceedLabel;
    val x_154 = x_153("turn");
    val x_155 = bindingMut_23;
    val x_156 = x_155.asInstanceOf[scala.Double];
    val x_157 = x_156.+(x_154);
    resetData_0 = x_157;
    val x_158 = resetData_0;
    val x_159 = x_158.asInstanceOf[scala.Double];
    bindingMut_23 = x_159;
    val x_160 = meta.deep.runtime.Actor.labelVals("turn");
    val x_161 = bindingMut_23;
    val x_162 = x_161.asInstanceOf[scala.Double];
    val x_163 = bindingMut_24;
    val x_164 = x_163.asInstanceOf[scala.Double];
    val x_165 = x_164.-(x_162);
    x_160.append(x_165);
    resetData_0 = ();
    positionVar_32 = 13;
    val x_166 = currentTurn;
    val x_167 = x_166.+(1);
    currentTurn = x_167
  }));
  data_33.update(13, (() => {
    val x_168 = bindingMut_23;
    val x_169 = x_168.asInstanceOf[scala.Double];
    val x_170 = bindingMut_24;
    val x_171 = x_170.asInstanceOf[scala.Double];
    val x_172 = x_169.<(x_171);
    if (x_172)
      positionVar_32 = 12
    else
      positionVar_32 = 14
  }));
  data_33.update(14, (() => {
    val x_173 = bindingMut_23;
    val x_174 = x_173.asInstanceOf[scala.Double];
    val x_175 = bindingMut_24;
    val x_176 = x_175.asInstanceOf[scala.Double];
    val x_177 = x_174.<(x_176);
    val x_178 = x_177.`unary_!`;
    if (x_178)
      positionVar_32 = 15
    else
      ()
  }));
  data_33.update(15, (() => if (true)
    positionVar_32 = 2
  else
    positionVar_32 = 16));
  data_33.update(16, (() => {
    val x_179 = true.`unary_!`;
    if (x_179)
      {
        val x_180 = resetData_1.remove(0);
        val x_184 = x_180.find(((x_181: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_182 = x_181._1;
          val x_183 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_182.==(x_183)
        }));
        val x_185 = x_184.get;
        val x_186 = x_185._2;
        positionVar_32 = x_186
      }
    else
      ()
  }));
  data_33.update(17, (() => {
    val x_187 = resetData_0;
    val x_188 = x_187.asInstanceOf[scala.Any];
    bindingMut_28 = x_188;
    val x_189 = bindingMut_28;
    val x_190 = x_189.asInstanceOf[scala.Any];
    val x_191 = listValMut_29;
    val x_192 = x_191.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_192.reply(this, x_190);
    resetData_0 = ();
    positionVar_32 = 6
  }));
  data_33.update(18, (() => positionVar_32 = 19));
  data_33.update(19, (() => {
    positionVar_32 = 20;
    val x_193 = currentTurn;
    val x_194 = x_193.+(1);
    currentTurn = x_194
  }));
  data_33.update(20, (() => positionVar_32 = 19));
  data_33.update(21, (() => {
    val x_195 = resetData_0;
    val x_196 = x_195.asInstanceOf[scala.Any];
    bindingMut_28 = x_196;
    val x_197 = bindingMut_28;
    val x_198 = x_197.asInstanceOf[scala.Any];
    val x_199 = listValMut_29;
    val x_200 = x_199.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_200.reply(this, x_198);
    resetData_0 = ();
    positionVar_32 = 6
  }));
  data_33.update(22, (() => {
    val x_201 = bindingMut_21;
    val x_202 = x_201.asInstanceOf[scala.Boolean];
    val x_203 = x_202.`unary_!`;
    if (x_203)
      {
        val x_204 = this.future_obj1;
        resetData_0 = x_204;
        val x_205 = resetData_0;
        val x_206 = x_205.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_17 = x_206;
        val x_207 = bindingMut_17;
        val x_208 = x_207.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_209 = x_208.get;
        resetData_0 = x_209;
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_16 = x_211;
        val x_212 = bindingMut_16;
        val x_213 = x_212.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_214 = this.isCompleted(x_213);
        resetData_0 = x_214;
        val x_215 = resetData_0;
        val x_216 = x_215.asInstanceOf[scala.Boolean];
        bindingMut_15 = x_216;
        positionVar_32 = 23
      }
    else
      ()
  }));
  data_33.update(23, (() => {
    val x_217 = bindingMut_15;
    val x_218 = x_217.asInstanceOf[scala.Boolean];
    if (x_218)
      {
        val x_219 = this.future_obj1;
        resetData_0 = x_219;
        val x_220 = resetData_0;
        val x_221 = x_220.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_14 = x_221;
        val x_222 = bindingMut_14;
        val x_223 = x_222.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_224 = x_223.get;
        resetData_0 = x_224;
        val x_225 = resetData_0;
        val x_226 = x_225.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_13 = x_226;
        val x_227 = bindingMut_13;
        val x_228 = x_227.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_229 = this.getFutureValue[scala.Int](x_228);
        resetData_0 = x_229;
        val x_230 = resetData_0;
        val x_231 = x_230.asInstanceOf[scala.Int];
        bindingMut_12 = x_231;
        val x_232 = bindingMut_12;
        val x_233 = x_232.asInstanceOf[scala.Int];
        val x_234 = "Response received ".+(x_233);
        resetData_0 = x_234;
        val x_235 = resetData_0;
        val x_236 = x_235.asInstanceOf[java.lang.String];
        bindingMut_11 = x_236;
        val x_237 = bindingMut_11;
        val x_238 = x_237.asInstanceOf[java.lang.String];
        val x_239 = x_238.+(" Id: ");
        resetData_0 = x_239;
        val x_240 = resetData_0;
        val x_241 = x_240.asInstanceOf[java.lang.String];
        bindingMut_10 = x_241;
        val x_242 = this.id;
        resetData_0 = x_242;
        val x_243 = resetData_0;
        val x_244 = x_243.asInstanceOf[scala.Long];
        bindingMut_9 = x_244;
        val x_245 = bindingMut_9;
        val x_246 = x_245.asInstanceOf[scala.Long];
        val x_247 = bindingMut_10;
        val x_248 = x_247.asInstanceOf[java.lang.String];
        val x_249 = x_248.+(x_246);
        resetData_0 = x_249;
        val x_250 = resetData_0;
        val x_251 = x_250.asInstanceOf[java.lang.String];
        bindingMut_8 = x_251;
        val x_252 = bindingMut_8;
        val x_253 = x_252.asInstanceOf[java.lang.String];
        scala.Predef.println(x_253);
        resetData_0 = ();
        val x_254 = this.future_obj1;
        resetData_0 = x_254;
        val x_255 = resetData_0;
        val x_256 = x_255.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_7 = x_256;
        val x_257 = bindingMut_7;
        val x_258 = x_257.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_259 = x_258.get;
        resetData_0 = x_259;
        val x_260 = resetData_0;
        val x_261 = x_260.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_6 = x_261;
        val x_262 = bindingMut_6;
        val x_263 = x_262.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_264 = this.clearFutureObj(x_263);
        resetData_0 = x_264;
        val x_265 = resetData_0;
        val x_266 = x_265.asInstanceOf[scala.None.type];
        bindingMut_5 = x_266;
        val x_267 = bindingMut_5;
        val x_268 = x_267.asInstanceOf[scala.None.type];
        this.`future_obj1_=`(x_268);
        resetData_0 = ();
        val x_269 = resetData_1.remove(0);
        val x_273 = x_269.find(((x_270: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_271 = x_270._1;
          val x_272 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_271.==(x_272)
        }));
        val x_274 = x_273.get;
        val x_275 = x_274._2;
        positionVar_32 = x_275
      }
    else
      positionVar_32 = 24
  }));
  data_33.update(24, (() => {
    val x_276 = bindingMut_15;
    val x_277 = x_276.asInstanceOf[scala.Boolean];
    val x_278 = x_277.`unary_!`;
    if (x_278)
      {
        val x_279 = this.id;
        resetData_0 = x_279;
        val x_280 = resetData_0;
        val x_281 = x_280.asInstanceOf[scala.Long];
        bindingMut_4 = x_281;
        val x_282 = bindingMut_4;
        val x_283 = x_282.asInstanceOf[scala.Long];
        val x_284 = "msg not completed! ".+(x_283);
        resetData_0 = x_284;
        val x_285 = resetData_0;
        val x_286 = x_285.asInstanceOf[java.lang.String];
        bindingMut_3 = x_286;
        val x_287 = bindingMut_3;
        val x_288 = x_287.asInstanceOf[java.lang.String];
        scala.Predef.println(x_288);
        resetData_0 = ();
        val x_289 = resetData_1.remove(0);
        val x_293 = x_289.find(((x_290: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_291 = x_290._1;
          val x_292 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_291.==(x_292)
        }));
        val x_294 = x_293.get;
        val x_295 = x_294._2;
        positionVar_32 = x_295
      }
    else
      ()
  }));
  data_33.update(25, (() => {
    val x_296 = iterMut_30;
    val x_297 = x_296.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_298 = x_297.hasNext;
    val x_299 = x_298.`unary_!`;
    if (x_299)
      positionVar_32 = 9
    else
      ();
    val x_300 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_301 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_300, 11);
    val x_302 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_301);
    resetData_1.prepend(x_302)
  }));
  data_33.update(26, (() => {
    val x_303 = true.`unary_!`;
    if (x_303)
      {
        val x_304 = resetData_1.remove(0);
        val x_308 = x_304.find(((x_305: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_306 = x_305._1;
          val x_307 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_306.==(x_307)
        }));
        val x_309 = x_308.get;
        val x_310 = x_309._2;
        positionVar_32 = x_310
      }
    else
      ()
  }));
  data_33
}).apply();
  
  override def run_until(until_312: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_313 = currentTurn;
      val x_314 = x_313.<=(until_312);
      x_314.&&({
        val x_315 = positionVar_32;
        val x_316 = commands_311.length;
        x_315.<(x_316)
      })
    }) 
      {
        val x_317 = positionVar_32;
        val x_318 = commands_311.apply(x_317);
        x_318.apply()
      }
    ;
    this
  }
}
