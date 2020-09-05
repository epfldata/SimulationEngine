package generated.meta.example.nb_methods_example

class Object1 (var n1: generated.meta.example.nb_methods_example.Object2) extends meta.deep.runtime.Actor with Serializable {
  var future_obj1: Option[meta.deep.runtime.Future[Int]] = scala.None
  var secretToken: Double = 0.5
  var secretToken2: Double = meta.example.nb_methods_example.expSetup.someVal
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: scala.Long = 0L;
  private var bindingMut_6: scala.None.type = null;
  private var bindingMut_7: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_8: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Long = 0L;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Int = 0;
  private var bindingMut_14: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_15: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_16: scala.Boolean = false;
  private var bindingMut_17: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_18: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_19: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_20: generated.meta.example.nb_methods_example.Object2 = null;
  private var bindingMut_21: java.lang.String = null;
  private var bindingMut_22: scala.Long = 0L;
  private var bindingMut_23: scala.Boolean = false;
  private var bindingMut_24: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_25: scala.Int = 0;
  private var bindingMut_26: scala.Boolean = false;
  private var bindingMut_27: scala.Int = 0;
  private var bindingMut_28: scala.collection.mutable.Map[java.lang.String, meta.deep.runtime.Future[scala.Any]] = null;
  private var bindingMut_29: scala.Any = null;
  private var listValMut_30: meta.deep.runtime.RequestMessage = null;
  private var iterMut_31: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_33: scala.Int = 0;
  
  val commands_305 = (() => {
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
    bindingMut_24 = x_76;
    val x_77 = bindingMut_24;
    val x_78 = x_77.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    val x_79 = x_78.==(scala.None);
    resetData_0 = x_79;
    val x_80 = resetData_0;
    val x_81 = x_80.asInstanceOf[scala.Boolean];
    bindingMut_23 = x_81;
    positionVar_33 = 10
  }));
  data_34.update(10, (() => {
    val x_82 = bindingMut_23;
    val x_83 = x_82.asInstanceOf[scala.Boolean];
    if (x_83)
      {
        val x_84 = this.id;
        resetData_0 = x_84;
        val x_85 = resetData_0;
        val x_86 = x_85.asInstanceOf[scala.Long];
        bindingMut_22 = x_86;
        val x_87 = bindingMut_22;
        val x_88 = x_87.asInstanceOf[scala.Long];
        val x_89 = "Send async msg1 ".+(x_88);
        resetData_0 = x_89;
        val x_90 = resetData_0;
        val x_91 = x_90.asInstanceOf[java.lang.String];
        bindingMut_21 = x_91;
        val x_92 = bindingMut_21;
        val x_93 = x_92.asInstanceOf[java.lang.String];
        scala.Predef.println(x_93);
        resetData_0 = ();
        val x_94 = this.n1;
        resetData_0 = x_94;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[generated.meta.example.nb_methods_example.Object2];
        bindingMut_20 = x_96;
        val x_97 = ((this): meta.deep.runtime.Actor).id;
        val x_99 = {
          val x_98 = bindingMut_20;
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
        bindingMut_19 = x_128;
        val x_129 = bindingMut_19;
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
    resetData_0 = 0;
    val x_153 = resetData_0;
    val x_154 = x_153.asInstanceOf[scala.Int];
    bindingMut_25 = x_154;
    positionVar_33 = 12
  }));
  data_34.update(12, (() => {
    val x_155 = bindingMut_25;
    val x_156 = x_155.asInstanceOf[scala.Int];
    val x_157 = (1).-(x_156);
    meta.deep.runtime.Actor.waitTurnList.append(x_157);
    resetData_0 = ();
    val x_158 = meta.deep.runtime.Actor.minTurn();
    val x_159 = bindingMut_25;
    val x_160 = x_159.asInstanceOf[scala.Int];
    val x_161 = x_160.+(x_158);
    resetData_0 = x_161;
    val x_162 = resetData_0;
    val x_163 = x_162.asInstanceOf[scala.Int];
    bindingMut_25 = x_163;
    positionVar_33 = 13;
    val x_164 = currentTurn;
    val x_165 = x_164.+(1);
    currentTurn = x_165
  }));
  data_34.update(13, (() => {
    val x_166 = bindingMut_25;
    val x_167 = x_166.asInstanceOf[scala.Int];
    val x_168 = x_167.<(1);
    if (x_168)
      positionVar_33 = 12
    else
      positionVar_33 = 14
  }));
  data_34.update(14, (() => {
    val x_169 = bindingMut_25;
    val x_170 = x_169.asInstanceOf[scala.Int];
    val x_171 = x_170.<(1);
    val x_172 = x_171.`unary_!`;
    if (x_172)
      positionVar_33 = 15
    else
      ()
  }));
  data_34.update(15, (() => if (true)
    positionVar_33 = 2
  else
    positionVar_33 = 16));
  data_34.update(16, (() => {
    val x_173 = true.`unary_!`;
    if (x_173)
      {
        val x_174 = resetData_1.remove(0);
        val x_178 = x_174.find(((x_175: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_176 = x_175._1;
          val x_177 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_176.==(x_177)
        }));
        val x_179 = x_178.get;
        val x_180 = x_179._2;
        positionVar_33 = x_180
      }
    else
      ()
  }));
  data_34.update(17, (() => {
    val x_181 = resetData_0;
    val x_182 = x_181.asInstanceOf[scala.Any];
    bindingMut_29 = x_182;
    val x_183 = bindingMut_29;
    val x_184 = x_183.asInstanceOf[scala.Any];
    val x_185 = listValMut_30;
    val x_186 = x_185.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_186.reply(this, x_184);
    resetData_0 = ();
    positionVar_33 = 6
  }));
  data_34.update(18, (() => positionVar_33 = 19));
  data_34.update(19, (() => {
    positionVar_33 = 20;
    val x_187 = currentTurn;
    val x_188 = x_187.+(1);
    currentTurn = x_188
  }));
  data_34.update(20, (() => positionVar_33 = 19));
  data_34.update(21, (() => {
    val x_189 = resetData_0;
    val x_190 = x_189.asInstanceOf[scala.Any];
    bindingMut_29 = x_190;
    val x_191 = bindingMut_29;
    val x_192 = x_191.asInstanceOf[scala.Any];
    val x_193 = listValMut_30;
    val x_194 = x_193.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_194.reply(this, x_192);
    resetData_0 = ();
    positionVar_33 = 6
  }));
  data_34.update(22, (() => {
    val x_195 = bindingMut_23;
    val x_196 = x_195.asInstanceOf[scala.Boolean];
    val x_197 = x_196.`unary_!`;
    if (x_197)
      {
        val x_198 = this.future_obj1;
        resetData_0 = x_198;
        val x_199 = resetData_0;
        val x_200 = x_199.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_18 = x_200;
        val x_201 = bindingMut_18;
        val x_202 = x_201.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_203 = x_202.get;
        resetData_0 = x_203;
        val x_204 = resetData_0;
        val x_205 = x_204.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_17 = x_205;
        val x_206 = bindingMut_17;
        val x_207 = x_206.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_208 = this.isCompleted(x_207);
        resetData_0 = x_208;
        val x_209 = resetData_0;
        val x_210 = x_209.asInstanceOf[scala.Boolean];
        bindingMut_16 = x_210;
        positionVar_33 = 23
      }
    else
      ()
  }));
  data_34.update(23, (() => {
    val x_211 = bindingMut_16;
    val x_212 = x_211.asInstanceOf[scala.Boolean];
    if (x_212)
      {
        val x_213 = this.future_obj1;
        resetData_0 = x_213;
        val x_214 = resetData_0;
        val x_215 = x_214.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_15 = x_215;
        val x_216 = bindingMut_15;
        val x_217 = x_216.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_218 = x_217.get;
        resetData_0 = x_218;
        val x_219 = resetData_0;
        val x_220 = x_219.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_14 = x_220;
        val x_221 = bindingMut_14;
        val x_222 = x_221.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_223 = this.getFutureValue[scala.Int](x_222);
        resetData_0 = x_223;
        val x_224 = resetData_0;
        val x_225 = x_224.asInstanceOf[scala.Int];
        bindingMut_13 = x_225;
        val x_226 = bindingMut_13;
        val x_227 = x_226.asInstanceOf[scala.Int];
        val x_228 = "Response received ".+(x_227);
        resetData_0 = x_228;
        val x_229 = resetData_0;
        val x_230 = x_229.asInstanceOf[java.lang.String];
        bindingMut_12 = x_230;
        val x_231 = bindingMut_12;
        val x_232 = x_231.asInstanceOf[java.lang.String];
        val x_233 = x_232.+(" Id: ");
        resetData_0 = x_233;
        val x_234 = resetData_0;
        val x_235 = x_234.asInstanceOf[java.lang.String];
        bindingMut_11 = x_235;
        val x_236 = this.id;
        resetData_0 = x_236;
        val x_237 = resetData_0;
        val x_238 = x_237.asInstanceOf[scala.Long];
        bindingMut_10 = x_238;
        val x_239 = bindingMut_10;
        val x_240 = x_239.asInstanceOf[scala.Long];
        val x_241 = bindingMut_11;
        val x_242 = x_241.asInstanceOf[java.lang.String];
        val x_243 = x_242.+(x_240);
        resetData_0 = x_243;
        val x_244 = resetData_0;
        val x_245 = x_244.asInstanceOf[java.lang.String];
        bindingMut_9 = x_245;
        val x_246 = bindingMut_9;
        val x_247 = x_246.asInstanceOf[java.lang.String];
        scala.Predef.println(x_247);
        resetData_0 = ();
        val x_248 = this.future_obj1;
        resetData_0 = x_248;
        val x_249 = resetData_0;
        val x_250 = x_249.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_8 = x_250;
        val x_251 = bindingMut_8;
        val x_252 = x_251.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_253 = x_252.get;
        resetData_0 = x_253;
        val x_254 = resetData_0;
        val x_255 = x_254.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        bindingMut_7 = x_255;
        val x_256 = bindingMut_7;
        val x_257 = x_256.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        val x_258 = this.clearFutureObj(x_257);
        resetData_0 = x_258;
        val x_259 = resetData_0;
        val x_260 = x_259.asInstanceOf[scala.None.type];
        bindingMut_6 = x_260;
        val x_261 = bindingMut_6;
        val x_262 = x_261.asInstanceOf[scala.None.type];
        this.`future_obj1_=`(x_262);
        resetData_0 = ();
        val x_263 = resetData_1.remove(0);
        val x_267 = x_263.find(((x_264: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_265 = x_264._1;
          val x_266 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_265.==(x_266)
        }));
        val x_268 = x_267.get;
        val x_269 = x_268._2;
        positionVar_33 = x_269
      }
    else
      positionVar_33 = 24
  }));
  data_34.update(24, (() => {
    val x_270 = bindingMut_16;
    val x_271 = x_270.asInstanceOf[scala.Boolean];
    val x_272 = x_271.`unary_!`;
    if (x_272)
      {
        val x_273 = this.id;
        resetData_0 = x_273;
        val x_274 = resetData_0;
        val x_275 = x_274.asInstanceOf[scala.Long];
        bindingMut_5 = x_275;
        val x_276 = bindingMut_5;
        val x_277 = x_276.asInstanceOf[scala.Long];
        val x_278 = "msg not completed! ".+(x_277);
        resetData_0 = x_278;
        val x_279 = resetData_0;
        val x_280 = x_279.asInstanceOf[java.lang.String];
        bindingMut_4 = x_280;
        val x_281 = bindingMut_4;
        val x_282 = x_281.asInstanceOf[java.lang.String];
        scala.Predef.println(x_282);
        resetData_0 = ();
        val x_283 = resetData_1.remove(0);
        val x_287 = x_283.find(((x_284: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_285 = x_284._1;
          val x_286 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_285.==(x_286)
        }));
        val x_288 = x_287.get;
        val x_289 = x_288._2;
        positionVar_33 = x_289
      }
    else
      ()
  }));
  data_34.update(25, (() => {
    val x_290 = iterMut_31;
    val x_291 = x_290.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_292 = x_291.hasNext;
    val x_293 = x_292.`unary_!`;
    if (x_293)
      positionVar_33 = 9
    else
      ();
    val x_294 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_295 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_294, 11);
    val x_296 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_295);
    resetData_1.prepend(x_296)
  }));
  data_34.update(26, (() => {
    val x_297 = true.`unary_!`;
    if (x_297)
      {
        val x_298 = resetData_1.remove(0);
        val x_302 = x_298.find(((x_299: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_300 = x_299._1;
          val x_301 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_300.==(x_301)
        }));
        val x_303 = x_302.get;
        val x_304 = x_303._2;
        positionVar_33 = x_304
      }
    else
      ()
  }));
  data_34
}).apply();
  
  override def run_until(until_306: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_307 = currentTurn;
      val x_308 = x_307.<=(until_306);
      x_308.&&({
        val x_309 = positionVar_33;
        val x_310 = commands_305.length;
        x_309.<(x_310)
      })
    }) 
      {
        val x_311 = positionVar_33;
        val x_312 = commands_305.apply(x_311);
        x_312.apply()
      }
    ;
    this
  }
}
