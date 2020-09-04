package generated.meta.example.latency

class Client (var server: generated.meta.example.latency.Server, var reqTime: Double, var replyTime: Double) extends meta.deep.runtime.Actor with Serializable {
  var future_req: Option[meta.deep.runtime.Future[Int]] = scala.None
  var sentTime: Double = 0.0
  var receivedTime: Double = 0.0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.None.type = null;
  private var bindingMut_5: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_6: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Int = 0;
  private var bindingMut_14: meta.deep.runtime.Future[scala.Int] = null;
  private var bindingMut_15: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_16: java.lang.String = null;
  private var bindingMut_17: java.lang.String = null;
  private var bindingMut_18: scala.Long = 0L;
  private var bindingMut_19: scala.Double = 0.0;
  private var bindingMut_20: scala.Double = 0.0;
  private var bindingMut_21: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_22: generated.meta.example.latency.Server = null;
  private var bindingMut_23: java.lang.String = null;
  private var bindingMut_24: scala.Double = 0.0;
  private var bindingMut_25: java.lang.String = null;
  private var bindingMut_26: java.lang.String = null;
  private var bindingMut_27: scala.Long = 0L;
  private var bindingMut_28: scala.Double = 0.0;
  private var bindingMut_29: scala.Boolean = false;
  private var bindingMut_30: scala.Option[meta.deep.runtime.Future[scala.Int]] = null;
  private var bindingMut_31: scala.Double = 0.0;
  private var bindingMut_32: scala.Double = 0.0;
  private var bindingMut_33: java.lang.String = null;
  private var bindingMut_34: scala.Double = 0.0;
  private var bindingMut_35: java.lang.String = null;
  private var bindingMut_36: java.lang.String = null;
  private var bindingMut_37: scala.Long = 0L;
  private var bindingMut_38: scala.Double = 0.0;
  private var bindingMut_39: scala.Double = 0.0;
  private var bindingMut_40: java.lang.String = null;
  private var bindingMut_41: scala.Double = 0.0;
  private var bindingMut_42: java.lang.String = null;
  private var bindingMut_43: java.lang.String = null;
  private var bindingMut_44: scala.Long = 0L;
  private var positionVar_46: scala.Int = 0;
  
  val commands_360 = (() => {
  val data_47 = new scala.Array[scala.Function0[scala.Unit]](26);
  data_47.update(0, (() => positionVar_46 = 1));
  data_47.update(1, (() => if (true)
    positionVar_46 = 2
  else
    positionVar_46 = 25));
  data_47.update(2, (() => {
    val x_48 = this.id;
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[scala.Long];
    bindingMut_37 = x_50;
    val x_51 = bindingMut_37;
    val x_52 = x_51.asInstanceOf[scala.Long];
    val x_53 = "Client ".+(x_52);
    resetData_0 = x_53;
    val x_54 = resetData_0;
    val x_55 = x_54.asInstanceOf[java.lang.String];
    bindingMut_36 = x_55;
    val x_56 = bindingMut_36;
    val x_57 = x_56.asInstanceOf[java.lang.String];
    val x_58 = x_57.+(" processing; take ");
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[java.lang.String];
    bindingMut_35 = x_60;
    val x_61 = this.reqTime;
    resetData_0 = x_61;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Double];
    bindingMut_34 = x_63;
    val x_64 = bindingMut_34;
    val x_65 = x_64.asInstanceOf[scala.Double];
    val x_66 = bindingMut_35;
    val x_67 = x_66.asInstanceOf[java.lang.String];
    val x_68 = x_67.+(x_65);
    resetData_0 = x_68;
    val x_69 = resetData_0;
    val x_70 = x_69.asInstanceOf[java.lang.String];
    bindingMut_33 = x_70;
    val x_71 = bindingMut_33;
    val x_72 = x_71.asInstanceOf[java.lang.String];
    scala.Predef.println(x_72);
    resetData_0 = ();
    val x_73 = this.reqTime;
    resetData_0 = x_73;
    val x_74 = resetData_0;
    val x_75 = x_74.asInstanceOf[scala.Double];
    bindingMut_32 = x_75;
    resetData_0 = 0.0;
    val x_76 = resetData_0;
    val x_77 = x_76.asInstanceOf[scala.Double];
    bindingMut_31 = x_77;
    positionVar_46 = 3
  }));
  data_47.update(3, (() => {
    val x_78 = meta.deep.runtime.Actor.proceedLabel;
    val x_79 = x_78("time");
    val x_80 = bindingMut_31;
    val x_81 = x_80.asInstanceOf[scala.Double];
    val x_82 = x_81.+(x_79);
    resetData_0 = x_82;
    val x_83 = resetData_0;
    val x_84 = x_83.asInstanceOf[scala.Double];
    bindingMut_31 = x_84;
    val x_85 = meta.deep.runtime.Actor.labelVals("time");
    val x_86 = bindingMut_31;
    val x_87 = x_86.asInstanceOf[scala.Double];
    val x_88 = bindingMut_32;
    val x_89 = x_88.asInstanceOf[scala.Double];
    val x_90 = x_89.-(x_87);
    x_85.append(x_90);
    resetData_0 = ();
    positionVar_46 = 4;
    val x_91 = currentTurn;
    val x_92 = x_91.+(1);
    currentTurn = x_92
  }));
  data_47.update(4, (() => {
    val x_93 = bindingMut_31;
    val x_94 = x_93.asInstanceOf[scala.Double];
    val x_95 = bindingMut_32;
    val x_96 = x_95.asInstanceOf[scala.Double];
    val x_97 = x_94.<(x_96);
    if (x_97)
      positionVar_46 = 3
    else
      positionVar_46 = 5
  }));
  data_47.update(5, (() => {
    val x_98 = bindingMut_31;
    val x_99 = x_98.asInstanceOf[scala.Double];
    val x_100 = bindingMut_32;
    val x_101 = x_100.asInstanceOf[scala.Double];
    val x_102 = x_99.<(x_101);
    val x_103 = x_102.`unary_!`;
    if (x_103)
      {
        val x_104 = this.future_req;
        resetData_0 = x_104;
        val x_105 = resetData_0;
        val x_106 = x_105.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_30 = x_106;
        val x_107 = bindingMut_30;
        val x_108 = x_107.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        val x_109 = x_108.==(scala.None);
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Boolean];
        bindingMut_29 = x_111;
        positionVar_46 = 6
      }
    else
      ()
  }));
  data_47.update(6, (() => {
    val x_112 = bindingMut_29;
    val x_113 = x_112.asInstanceOf[scala.Boolean];
    val x_114 = x_113.`unary_!`;
    if (x_114)
      positionVar_46 = 7
    else
      positionVar_46 = 24
  }));
  data_47.update(7, (() => {
    val x_115 = this.future_req;
    val x_116 = x_115.get;
    val x_117 = this.isCompleted(x_116);
    val x_118 = x_117.`unary_!`;
    if (x_118)
      positionVar_46 = 8
    else
      positionVar_46 = 23
  }));
  data_47.update(8, (() => {
    resetData_0 = 0.0;
    val x_119 = resetData_0;
    val x_120 = x_119.asInstanceOf[scala.Double];
    bindingMut_20 = x_120;
    positionVar_46 = 9
  }));
  data_47.update(9, (() => {
    val x_121 = meta.deep.runtime.Actor.proceedLabel;
    val x_122 = x_121("time");
    val x_123 = bindingMut_20;
    val x_124 = x_123.asInstanceOf[scala.Double];
    val x_125 = x_124.+(x_122);
    resetData_0 = x_125;
    val x_126 = resetData_0;
    val x_127 = x_126.asInstanceOf[scala.Double];
    bindingMut_20 = x_127;
    val x_128 = meta.deep.runtime.Actor.labelVals("time");
    val x_129 = bindingMut_20;
    val x_130 = x_129.asInstanceOf[scala.Double];
    val x_131 = 0.1.-(x_130);
    x_128.append(x_131);
    resetData_0 = ();
    positionVar_46 = 10;
    val x_132 = currentTurn;
    val x_133 = x_132.+(1);
    currentTurn = x_133
  }));
  data_47.update(10, (() => {
    val x_134 = bindingMut_20;
    val x_135 = x_134.asInstanceOf[scala.Double];
    val x_136 = x_135.<(0.1);
    if (x_136)
      positionVar_46 = 9
    else
      positionVar_46 = 11
  }));
  data_47.update(11, (() => {
    val x_137 = bindingMut_20;
    val x_138 = x_137.asInstanceOf[scala.Double];
    val x_139 = x_138.<(0.1);
    val x_140 = x_139.`unary_!`;
    if (x_140)
      positionVar_46 = 12
    else
      ()
  }));
  data_47.update(12, (() => {
    val x_141 = this.future_req;
    val x_142 = x_141.get;
    val x_143 = this.isCompleted(x_142);
    val x_144 = x_143.`unary_!`;
    if (x_144)
      positionVar_46 = 8
    else
      positionVar_46 = 13
  }));
  data_47.update(13, (() => {
    val x_145 = this.future_req;
    val x_146 = x_145.get;
    val x_147 = this.isCompleted(x_146);
    val x_148 = x_147.`unary_!`;
    val x_149 = x_148.`unary_!`;
    if (x_149)
      positionVar_46 = 14
    else
      ()
  }));
  data_47.update(14, (() => {
    val x_150 = this.currentTime;
    resetData_0 = x_150;
    val x_151 = resetData_0;
    val x_152 = x_151.asInstanceOf[scala.Double];
    bindingMut_19 = x_152;
    val x_153 = bindingMut_19;
    val x_154 = x_153.asInstanceOf[scala.Double];
    this.`receivedTime_=`(x_154);
    resetData_0 = ();
    val x_155 = this.id;
    resetData_0 = x_155;
    val x_156 = resetData_0;
    val x_157 = x_156.asInstanceOf[scala.Long];
    bindingMut_18 = x_157;
    val x_158 = bindingMut_18;
    val x_159 = x_158.asInstanceOf[scala.Long];
    val x_160 = "Client ".+(x_159);
    resetData_0 = x_160;
    val x_161 = resetData_0;
    val x_162 = x_161.asInstanceOf[java.lang.String];
    bindingMut_17 = x_162;
    val x_163 = bindingMut_17;
    val x_164 = x_163.asInstanceOf[java.lang.String];
    val x_165 = x_164.+(" received reply! ");
    resetData_0 = x_165;
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[java.lang.String];
    bindingMut_16 = x_167;
    val x_168 = this.future_req;
    resetData_0 = x_168;
    val x_169 = resetData_0;
    val x_170 = x_169.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    bindingMut_15 = x_170;
    val x_171 = bindingMut_15;
    val x_172 = x_171.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    val x_173 = x_172.get;
    resetData_0 = x_173;
    val x_174 = resetData_0;
    val x_175 = x_174.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
    bindingMut_14 = x_175;
    val x_176 = bindingMut_14;
    val x_177 = x_176.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
    val x_178 = this.getFutureValue[scala.Int](x_177);
    resetData_0 = x_178;
    val x_179 = resetData_0;
    val x_180 = x_179.asInstanceOf[scala.Int];
    bindingMut_13 = x_180;
    val x_181 = bindingMut_13;
    val x_182 = x_181.asInstanceOf[scala.Int];
    val x_183 = bindingMut_16;
    val x_184 = x_183.asInstanceOf[java.lang.String];
    val x_185 = x_184.+(x_182);
    resetData_0 = x_185;
    val x_186 = resetData_0;
    val x_187 = x_186.asInstanceOf[java.lang.String];
    bindingMut_12 = x_187;
    val x_188 = bindingMut_12;
    val x_189 = x_188.asInstanceOf[java.lang.String];
    val x_190 = x_189.+("  Latency: ");
    resetData_0 = x_190;
    val x_191 = resetData_0;
    val x_192 = x_191.asInstanceOf[java.lang.String];
    bindingMut_11 = x_192;
    val x_193 = this.receivedTime;
    resetData_0 = x_193;
    val x_194 = resetData_0;
    val x_195 = x_194.asInstanceOf[scala.Double];
    bindingMut_10 = x_195;
    val x_196 = this.sentTime;
    resetData_0 = x_196;
    val x_197 = resetData_0;
    val x_198 = x_197.asInstanceOf[scala.Double];
    bindingMut_9 = x_198;
    val x_199 = bindingMut_9;
    val x_200 = x_199.asInstanceOf[scala.Double];
    val x_201 = bindingMut_10;
    val x_202 = x_201.asInstanceOf[scala.Double];
    val x_203 = x_202.-(x_200);
    resetData_0 = x_203;
    val x_204 = resetData_0;
    val x_205 = x_204.asInstanceOf[scala.Double];
    bindingMut_8 = x_205;
    val x_206 = bindingMut_8;
    val x_207 = x_206.asInstanceOf[scala.Double];
    val x_208 = bindingMut_11;
    val x_209 = x_208.asInstanceOf[java.lang.String];
    val x_210 = x_209.+(x_207);
    resetData_0 = x_210;
    val x_211 = resetData_0;
    val x_212 = x_211.asInstanceOf[java.lang.String];
    bindingMut_7 = x_212;
    val x_213 = bindingMut_7;
    val x_214 = x_213.asInstanceOf[java.lang.String];
    scala.Predef.println(x_214);
    resetData_0 = ();
    val x_215 = this.future_req;
    resetData_0 = x_215;
    val x_216 = resetData_0;
    val x_217 = x_216.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    bindingMut_6 = x_217;
    val x_218 = bindingMut_6;
    val x_219 = x_218.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
    val x_220 = x_219.get;
    resetData_0 = x_220;
    val x_221 = resetData_0;
    val x_222 = x_221.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
    bindingMut_5 = x_222;
    val x_223 = bindingMut_5;
    val x_224 = x_223.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
    val x_225 = this.clearFutureObj(x_224);
    resetData_0 = x_225;
    val x_226 = resetData_0;
    val x_227 = x_226.asInstanceOf[scala.None.type];
    bindingMut_4 = x_227;
    val x_228 = bindingMut_4;
    val x_229 = x_228.asInstanceOf[scala.None.type];
    this.`future_req_=`(x_229);
    resetData_0 = ();
    val x_230 = this.id;
    resetData_0 = x_230;
    val x_231 = resetData_0;
    val x_232 = x_231.asInstanceOf[scala.Long];
    bindingMut_44 = x_232;
    val x_233 = bindingMut_44;
    val x_234 = x_233.asInstanceOf[scala.Long];
    val x_235 = "Client ".+(x_234);
    resetData_0 = x_235;
    val x_236 = resetData_0;
    val x_237 = x_236.asInstanceOf[java.lang.String];
    bindingMut_43 = x_237;
    val x_238 = bindingMut_43;
    val x_239 = x_238.asInstanceOf[java.lang.String];
    val x_240 = x_239.+(" analyzing reply; take ");
    resetData_0 = x_240;
    val x_241 = resetData_0;
    val x_242 = x_241.asInstanceOf[java.lang.String];
    bindingMut_42 = x_242;
    val x_243 = this.replyTime;
    resetData_0 = x_243;
    val x_244 = resetData_0;
    val x_245 = x_244.asInstanceOf[scala.Double];
    bindingMut_41 = x_245;
    val x_246 = bindingMut_41;
    val x_247 = x_246.asInstanceOf[scala.Double];
    val x_248 = bindingMut_42;
    val x_249 = x_248.asInstanceOf[java.lang.String];
    val x_250 = x_249.+(x_247);
    resetData_0 = x_250;
    val x_251 = resetData_0;
    val x_252 = x_251.asInstanceOf[java.lang.String];
    bindingMut_40 = x_252;
    val x_253 = bindingMut_40;
    val x_254 = x_253.asInstanceOf[java.lang.String];
    scala.Predef.println(x_254);
    resetData_0 = ();
    val x_255 = this.replyTime;
    resetData_0 = x_255;
    val x_256 = resetData_0;
    val x_257 = x_256.asInstanceOf[scala.Double];
    bindingMut_39 = x_257;
    resetData_0 = 0.0;
    val x_258 = resetData_0;
    val x_259 = x_258.asInstanceOf[scala.Double];
    bindingMut_38 = x_259;
    positionVar_46 = 15
  }));
  data_47.update(15, (() => {
    val x_260 = meta.deep.runtime.Actor.proceedLabel;
    val x_261 = x_260("time");
    val x_262 = bindingMut_38;
    val x_263 = x_262.asInstanceOf[scala.Double];
    val x_264 = x_263.+(x_261);
    resetData_0 = x_264;
    val x_265 = resetData_0;
    val x_266 = x_265.asInstanceOf[scala.Double];
    bindingMut_38 = x_266;
    val x_267 = meta.deep.runtime.Actor.labelVals("time");
    val x_268 = bindingMut_38;
    val x_269 = x_268.asInstanceOf[scala.Double];
    val x_270 = bindingMut_39;
    val x_271 = x_270.asInstanceOf[scala.Double];
    val x_272 = x_271.-(x_269);
    x_267.append(x_272);
    resetData_0 = ();
    positionVar_46 = 16;
    val x_273 = currentTurn;
    val x_274 = x_273.+(1);
    currentTurn = x_274
  }));
  data_47.update(16, (() => {
    val x_275 = bindingMut_38;
    val x_276 = x_275.asInstanceOf[scala.Double];
    val x_277 = bindingMut_39;
    val x_278 = x_277.asInstanceOf[scala.Double];
    val x_279 = x_276.<(x_278);
    if (x_279)
      positionVar_46 = 15
    else
      positionVar_46 = 17
  }));
  data_47.update(17, (() => {
    val x_280 = bindingMut_38;
    val x_281 = x_280.asInstanceOf[scala.Double];
    val x_282 = bindingMut_39;
    val x_283 = x_282.asInstanceOf[scala.Double];
    val x_284 = x_281.<(x_283);
    val x_285 = x_284.`unary_!`;
    if (x_285)
      positionVar_46 = 18
    else
      ()
  }));
  data_47.update(18, (() => if (true)
    positionVar_46 = 2
  else
    positionVar_46 = 19));
  data_47.update(19, (() => {
    val x_286 = true.`unary_!`;
    if (x_286)
      positionVar_46 = 20
    else
      ()
  }));
  data_47.update(20, (() => positionVar_46 = 21));
  data_47.update(21, (() => {
    positionVar_46 = 22;
    val x_287 = currentTurn;
    val x_288 = x_287.+(1);
    currentTurn = x_288
  }));
  data_47.update(22, (() => positionVar_46 = 21));
  data_47.update(23, (() => {
    val x_289 = this.future_req;
    val x_290 = x_289.get;
    val x_291 = this.isCompleted(x_290);
    val x_292 = x_291.`unary_!`;
    val x_293 = x_292.`unary_!`;
    if (x_293)
      positionVar_46 = 14
    else
      ()
  }));
  data_47.update(24, (() => {
    val x_294 = bindingMut_29;
    val x_295 = x_294.asInstanceOf[scala.Boolean];
    if (x_295)
      {
        val x_296 = this.currentTime;
        resetData_0 = x_296;
        val x_297 = resetData_0;
        val x_298 = x_297.asInstanceOf[scala.Double];
        bindingMut_28 = x_298;
        val x_299 = bindingMut_28;
        val x_300 = x_299.asInstanceOf[scala.Double];
        this.`sentTime_=`(x_300);
        resetData_0 = ();
        val x_301 = this.id;
        resetData_0 = x_301;
        val x_302 = resetData_0;
        val x_303 = x_302.asInstanceOf[scala.Long];
        bindingMut_27 = x_303;
        val x_304 = bindingMut_27;
        val x_305 = x_304.asInstanceOf[scala.Long];
        val x_306 = "Client ".+(x_305);
        resetData_0 = x_306;
        val x_307 = resetData_0;
        val x_308 = x_307.asInstanceOf[java.lang.String];
        bindingMut_26 = x_308;
        val x_309 = bindingMut_26;
        val x_310 = x_309.asInstanceOf[java.lang.String];
        val x_311 = x_310.+(" sent msg at time ");
        resetData_0 = x_311;
        val x_312 = resetData_0;
        val x_313 = x_312.asInstanceOf[java.lang.String];
        bindingMut_25 = x_313;
        val x_314 = this.sentTime;
        resetData_0 = x_314;
        val x_315 = resetData_0;
        val x_316 = x_315.asInstanceOf[scala.Double];
        bindingMut_24 = x_316;
        val x_317 = bindingMut_24;
        val x_318 = x_317.asInstanceOf[scala.Double];
        val x_319 = bindingMut_25;
        val x_320 = x_319.asInstanceOf[java.lang.String];
        val x_321 = x_320.+(x_318);
        resetData_0 = x_321;
        val x_322 = resetData_0;
        val x_323 = x_322.asInstanceOf[java.lang.String];
        bindingMut_23 = x_323;
        val x_324 = bindingMut_23;
        val x_325 = x_324.asInstanceOf[java.lang.String];
        scala.Predef.println(x_325);
        resetData_0 = ();
        val x_326 = this.server;
        resetData_0 = x_326;
        val x_327 = resetData_0;
        val x_328 = x_327.asInstanceOf[generated.meta.example.latency.Server];
        bindingMut_22 = x_328;
        val x_329 = ((this): meta.deep.runtime.Actor).id;
        val x_331 = {
          val x_330 = bindingMut_22;
          x_330.asInstanceOf[generated.meta.example.latency.Server]
        };
        val x_332 = x_331.id;
        val x$1_333 = this.id;
        val x_334 = scala.collection.immutable.Nil.::[scala.Any](x$1_333);
        val x_335 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_334);
        val x_336 = meta.deep.runtime.RequestMessage.apply(x_329, x_332, 0, x_335);
        val x_337 = x_336.future;
        val x_338 = x_337.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
        x_336.`future_=`(x_338);
        ((this): meta.deep.runtime.Actor).sendMessage(x_336);
        val x_339 = x_336.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_339, ((response_340: meta.deep.runtime.Message) => {
          val x_341 = x_336.future;
          val x_342 = response_340.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_343 = x_342.arg;
          val x_344 = x_341.setValue[scala.Any](x_343);
          val x_345 = x_344.asInstanceOf[meta.deep.runtime.Future[scala.Int]];
          x_336.`future_=`(x_345);
          val x_346 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_347 = x_336.future;
          val x_348 = x_347.id;
          val x_349 = scala.Predef.ArrowAssoc[java.lang.String](x_348);
          val x_350 = x_336.future;
          val x_351 = x_349.->[meta.deep.runtime.Future[scala.Any]](x_350);
          val x_352 = x_346.+[meta.deep.runtime.Future[scala.Any]](x_351);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_352)
        }));
        val x_353 = x_336.future;
        val x_354 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_353);
        resetData_0 = x_354;
        val x_355 = resetData_0;
        val x_356 = x_355.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        bindingMut_21 = x_356;
        val x_357 = bindingMut_21;
        val x_358 = x_357.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Int]]];
        this.`future_req_=`(x_358);
        resetData_0 = ();
        positionVar_46 = 7
      }
    else
      ()
  }));
  data_47.update(25, (() => {
    val x_359 = true.`unary_!`;
    if (x_359)
      positionVar_46 = 20
    else
      ()
  }));
  data_47
}).apply();
  
  override def run_until(until_361: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_362 = currentTurn;
      val x_363 = x_362.<=(until_361);
      x_363.&&({
        val x_364 = positionVar_46;
        val x_365 = commands_360.length;
        x_364.<(x_365)
      })
    }) 
      {
        val x_366 = positionVar_46;
        val x_367 = commands_360.apply(x_366);
        x_367.apply()
      }
    ;
    this
  }
}
