package generated.example.epistemicLogicVC

class Process () extends meta.deep.runtime.Actor {
  var vectorClock: example.epistemicLogicVC.VectorClock = new example.epistemicLogicVC.VectorClock()
  var localTime: Int = 0
  var initTime: Int = 0
  var others: List[generated.example.epistemicLogicVC.Process] = scala.collection.immutable.Nil
  var neighborIds: List[Long] = scala.collection.immutable.Nil
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_5: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_6: lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime] = null;
  private var bindingMut_7: example.epistemicLogicVC.VCHelper.ProcessTime = null;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: scala.Long = 0L;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_12: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_13: scala.Long = 0L;
  private var bindingMut_14: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_15: lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime] = null;
  private var bindingMut_16: example.epistemicLogicVC.VCHelper.ProcessTime = null;
  private var bindingMut_17: scala.Int = 0;
  private var bindingMut_18: scala.Long = 0L;
  private var bindingMut_19: scala.Int = 0;
  private var bindingMut_20: example.epistemicLogicVC.VectorClock = null;
  private var listValMut_21: generated.example.epistemicLogicVC.Process = null;
  private var iterMut_22: scala.collection.Iterator[generated.example.epistemicLogicVC.Process] = null;
  private var bindingMut_23: scala.collection.immutable.List[generated.example.epistemicLogicVC.Process] = null;
  private var bindingMut_24: scala.collection.mutable.ListBuffer[scala.Long] = null;
  private var bindingMut_25: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_26: scala.Int = 0;
  private var bindingMut_27: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_28: lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime] = null;
  private var bindingMut_29: example.epistemicLogicVC.VCHelper.ProcessTime = null;
  private var bindingMut_30: scala.Int = 0;
  private var bindingMut_31: scala.Long = 0L;
  private var bindingMut_32: scala.Int = 0;
  private var bindingMut_33: scala.Int = 0;
  private var bindingMut_34: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_35: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_36: scala.Boolean = false;
  private var bindingMut_37: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_38: lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime] = null;
  private var bindingMut_39: example.epistemicLogicVC.VCHelper.ProcessTime = null;
  private var bindingMut_40: scala.Int = 0;
  private var bindingMut_41: scala.Long = 0L;
  private var methodArgsMut_42: generated.example.epistemicLogicVC.Process = null;
  private var bindingMut_43: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_44: example.epistemicLogicVC.VectorClock = null;
  private var methodArgsMut_45: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_46: java.lang.String = null;
  private var bindingMut_47: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_48: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_49: java.lang.String = null;
  private var bindingMut_50: java.lang.String = null;
  private var bindingMut_51: scala.Long = 0L;
  private var bindingMut_52: example.epistemicLogicVC.VectorClock = null;
  private var listValMut_53: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var iterMut_54: scala.collection.Iterator[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_55: scala.collection.immutable.List[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_56: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_57: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_58: java.lang.String = null;
  private var bindingMut_59: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_60: example.epistemicLogicVC.VectorClock = null;
  private var bindingMut_61: java.lang.String = null;
  private var bindingMut_62: java.lang.String = null;
  private var bindingMut_63: scala.Long = 0L;
  private var bindingMut_64: java.lang.String = null;
  private var bindingMut_65: java.lang.String = null;
  private var bindingMut_66: java.lang.String = null;
  private var bindingMut_67: scala.Long = 0L;
  private var bindingMut_68: scala.Double = 0.0;
  private var bindingMut_69: scala.Double = 0.0;
  private var bindingMut_70: scala.Any = null;
  private var listValMut_71: meta.deep.runtime.RequestMessage = null;
  private var iterMut_72: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_73: generated.example.epistemicLogicVC.Process = null;
  private var bindingMut_74: scala.collection.immutable.List[generated.example.epistemicLogicVC.Process] = null;
  private var bindingMut_75: java.lang.String = null;
  private var bindingMut_76: scala.Long = 0L;
  private var bindingMut_77: scala.Boolean = false;
  private var bindingMut_78: scala.Int = 0;
  private var bindingMut_79: scala.collection.immutable.List[generated.example.epistemicLogicVC.Process] = null;
  private var bindingMut_80: scala.Int = 0;
  private var bindingMut_81: scala.Int = 0;
  private var bindingMut_82: scala.collection.immutable.List[generated.example.epistemicLogicVC.Process] = null;
  private var positionVar_84: scala.Int = 0;
  
  val commands_753 = (() => {
  val data_85 = new scala.Array[scala.Function0[scala.Unit]](52);
  data_85.update(0, (() => {
    positionVar_84 = 1;
    val x_86 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_87 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_86, 46);
    val x_88 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_87);
    resetData_1.prepend(x_88)
  }));
  data_85.update(1, (() => {
    positionVar_84 = 2;
    val x_89 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_90 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_89, 49);
    val x_91 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_90);
    resetData_1.prepend(x_91)
  }));
  data_85.update(2, (() => {
    val x_92 = new scala.collection.mutable.ListBuffer[scala.Long]();
    resetData_0 = x_92;
    val x_93 = resetData_0;
    val x_94 = x_93.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
    bindingMut_24 = x_94;
    val x_95 = this.others;
    resetData_0 = x_95;
    val x_96 = resetData_0;
    val x_97 = x_96.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    bindingMut_23 = x_97;
    val x_98 = bindingMut_23;
    val x_99 = x_98.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    val x_100 = x_99.iterator;
    iterMut_22 = x_100;
    positionVar_84 = 3
  }));
  data_85.update(3, (() => {
    val x_101 = iterMut_22;
    val x_102 = x_101.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicVC.Process]];
    val x_103 = x_102.hasNext;
    if (x_103)
      {
        val x_104 = iterMut_22;
        val x_105 = x_104.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicVC.Process]];
        val x_106 = x_105.next();
        listValMut_21 = x_106;
        val x_107 = this.vectorClock;
        resetData_0 = x_107;
        val x_108 = resetData_0;
        val x_109 = x_108.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_20 = x_109;
        val x_110 = this.localTime;
        resetData_0 = x_110;
        val x_111 = resetData_0;
        val x_112 = x_111.asInstanceOf[scala.Int];
        bindingMut_19 = x_112;
        val x_113 = listValMut_21;
        val x_114 = x_113.asInstanceOf[generated.example.epistemicLogicVC.Process];
        val x_115 = x_114.id;
        resetData_0 = x_115;
        val x_116 = resetData_0;
        val x_117 = x_116.asInstanceOf[scala.Long];
        bindingMut_18 = x_117;
        val x_118 = this.initTime;
        resetData_0 = x_118;
        val x_119 = resetData_0;
        val x_120 = x_119.asInstanceOf[scala.Int];
        bindingMut_17 = x_120;
        val x_121 = bindingMut_17;
        val x_122 = x_121.asInstanceOf[scala.Int];
        val x_123 = bindingMut_18;
        val x_124 = x_123.asInstanceOf[scala.Long];
        val x_125 = example.epistemicLogicVC.VCHelper.ProcessTime.apply(x_124, x_122);
        resetData_0 = x_125;
        val x_126 = resetData_0;
        val x_127 = x_126.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        bindingMut_16 = x_127;
        val x_128 = bindingMut_16;
        val x_129 = x_128.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        val x_130 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicVC.VCHelper.ProcessTime](x_129);
        resetData_0 = x_130;
        val x_131 = resetData_0;
        val x_132 = x_131.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        bindingMut_15 = x_132;
        val x_133 = bindingMut_15;
        val x_134 = x_133.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        val x_135 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_134);
        resetData_0 = x_135;
        val x_136 = resetData_0;
        val x_137 = x_136.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_14 = x_137;
        val x_138 = bindingMut_14;
        val x_139 = x_138.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_140 = bindingMut_19;
        val x_141 = x_140.asInstanceOf[scala.Int];
        val x_142 = bindingMut_20;
        val x_143 = x_142.asInstanceOf[example.epistemicLogicVC.VectorClock];
        x_143.recordLearning(x_141, x_139);
        resetData_0 = ();
        val x_144 = listValMut_21;
        val x_145 = x_144.asInstanceOf[generated.example.epistemicLogicVC.Process];
        val x_146 = x_145.id;
        resetData_0 = x_146;
        val x_147 = resetData_0;
        val x_148 = x_147.asInstanceOf[scala.Long];
        bindingMut_13 = x_148;
        val x_149 = bindingMut_13;
        val x_150 = x_149.asInstanceOf[scala.Long];
        val x_151 = bindingMut_24;
        val x_152 = x_151.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        x_152.append(x_150);
        resetData_0 = ();
        positionVar_84 = 3
      }
    else
      positionVar_84 = 4
  }));
  data_85.update(4, (() => {
    val x_153 = iterMut_22;
    val x_154 = x_153.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicVC.Process]];
    val x_155 = x_154.hasNext;
    val x_156 = x_155.`unary_!`;
    if (x_156)
      {
        val x_157 = bindingMut_24;
        val x_158 = x_157.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        val x_159 = x_158.toList;
        resetData_0 = x_159;
        val x_160 = resetData_0;
        val x_161 = x_160.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_12 = x_161;
        val x_162 = bindingMut_12;
        val x_163 = x_162.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        this.`neighborIds_=`(x_163);
        resetData_0 = ();
        val x_164 = this.vectorClock;
        resetData_0 = x_164;
        val x_165 = resetData_0;
        val x_166 = x_165.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_11 = x_166;
        val x_167 = this.localTime;
        resetData_0 = x_167;
        val x_168 = resetData_0;
        val x_169 = x_168.asInstanceOf[scala.Int];
        bindingMut_10 = x_169;
        val x_170 = this.id;
        resetData_0 = x_170;
        val x_171 = resetData_0;
        val x_172 = x_171.asInstanceOf[scala.Long];
        bindingMut_9 = x_172;
        val x_173 = this.localTime;
        resetData_0 = x_173;
        val x_174 = resetData_0;
        val x_175 = x_174.asInstanceOf[scala.Int];
        bindingMut_8 = x_175;
        val x_176 = bindingMut_8;
        val x_177 = x_176.asInstanceOf[scala.Int];
        val x_178 = bindingMut_9;
        val x_179 = x_178.asInstanceOf[scala.Long];
        val x_180 = example.epistemicLogicVC.VCHelper.ProcessTime.apply(x_179, x_177);
        resetData_0 = x_180;
        val x_181 = resetData_0;
        val x_182 = x_181.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        bindingMut_7 = x_182;
        val x_183 = bindingMut_7;
        val x_184 = x_183.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        val x_185 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicVC.VCHelper.ProcessTime](x_184);
        resetData_0 = x_185;
        val x_186 = resetData_0;
        val x_187 = x_186.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        bindingMut_6 = x_187;
        val x_188 = bindingMut_6;
        val x_189 = x_188.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        val x_190 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_189);
        resetData_0 = x_190;
        val x_191 = resetData_0;
        val x_192 = x_191.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_5 = x_192;
        val x_193 = bindingMut_5;
        val x_194 = x_193.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_195 = bindingMut_10;
        val x_196 = x_195.asInstanceOf[scala.Int];
        val x_197 = bindingMut_11;
        val x_198 = x_197.asInstanceOf[example.epistemicLogicVC.VectorClock];
        x_198.recordLearning(x_196, x_194);
        resetData_0 = ();
        val x_199 = resetData_1.remove(0);
        val x_203 = x_199.find(((x_200: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_201 = x_200._1;
          val x_202 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_201.==(x_202)
        }));
        val x_204 = x_203.get;
        val x_205 = x_204._2;
        positionVar_84 = x_205
      }
    else
      ()
  }));
  data_85.update(5, (() => {
    val x_206 = resetData_0;
    val x_207 = x_206.asInstanceOf[scala.Any];
    bindingMut_70 = x_207;
    val x_208 = bindingMut_70;
    val x_209 = x_208.asInstanceOf[scala.Any];
    val x_210 = listValMut_71;
    val x_211 = x_210.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_211.reply(this, x_209);
    resetData_0 = ();
    positionVar_84 = 6
  }));
  data_85.update(6, (() => positionVar_84 = 7));
  data_85.update(7, (() => {
    val x_212 = iterMut_72;
    val x_213 = x_212.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_214 = x_213.hasNext;
    if (x_214)
      {
        val x_215 = iterMut_72;
        val x_216 = x_215.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_217 = x_216.next();
        listValMut_71 = x_217;
        positionVar_84 = 8
      }
    else
      positionVar_84 = 36
  }));
  data_85.update(8, (() => {
    val x_218 = listValMut_71;
    val x_219 = x_218.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_220 = x_219.methodId;
    val x_221 = x_220.==(0);
    val x_222 = x_221.`unary_!`;
    if (x_222)
      positionVar_84 = 9
    else
      positionVar_84 = 35
  }));
  data_85.update(9, (() => {
    val x_223 = listValMut_71;
    val x_224 = x_223.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_225 = x_224.methodId;
    val x_226 = x_225.==(1);
    val x_227 = x_226.`unary_!`;
    if (x_227)
      positionVar_84 = 10
    else
      positionVar_84 = 34
  }));
  data_85.update(10, (() => {
    val x_228 = listValMut_71;
    val x_229 = x_228.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_230 = x_229.methodId;
    val x_231 = x_230.==(2);
    val x_232 = x_231.`unary_!`;
    if (x_232)
      positionVar_84 = 11
    else
      positionVar_84 = 32
  }));
  data_85.update(11, (() => {
    val x_233 = listValMut_71;
    val x_234 = x_233.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_235 = x_234.methodId;
    val x_236 = x_235.==(3);
    val x_237 = x_236.`unary_!`;
    if (x_237)
      positionVar_84 = 12
    else
      positionVar_84 = 30
  }));
  data_85.update(12, (() => {
    val x_238 = listValMut_71;
    val x_239 = x_238.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_240 = x_239.methodId;
    val x_241 = x_240.==(4);
    val x_242 = x_241.`unary_!`;
    if (x_242)
      positionVar_84 = 13
    else
      positionVar_84 = 15
  }));
  data_85.update(13, (() => {
    val x_243 = listValMut_71;
    val x_244 = x_243.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_245 = x_244.methodId;
    val x_246 = x_245.==(5);
    val x_247 = x_246.`unary_!`;
    if (x_247)
      {
        val x_248 = listValMut_71;
        val x_249 = x_248.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_250 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_249);
        val x_251 = this.addReceiveMessages(x_250);
        resetData_0 = x_251;
        positionVar_84 = 6
      }
    else
      positionVar_84 = 14
  }));
  data_85.update(14, (() => {
    val x_252 = listValMut_71;
    val x_253 = x_252.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_254 = x_253.methodId;
    val x_255 = x_254.==(5);
    if (x_255)
      positionVar_84 = 1
    else
      ();
    val x_256 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_257 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_256, 45);
    val x_258 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_257);
    resetData_1.prepend(x_258)
  }));
  data_85.update(15, (() => {
    val x_259 = listValMut_71;
    val x_260 = x_259.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_261 = x_260.methodId;
    val x_262 = x_261.==(4);
    if (x_262)
      {
        val x_263 = listValMut_71;
        val x_264 = x_263.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_265 = x_264.argss;
        val x_266 = x_265(0);
        val x_267 = x_266(0);
        x_4.prepend(x_267);
        val x_268 = listValMut_71;
        val x_269 = x_268.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_270 = x_269.argss;
        val x_271 = x_270(0);
        val x_272 = x_271(0);
        val x_273 = x_272.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        methodArgsMut_45 = x_273;
        val x_274 = this.id;
        resetData_0 = x_274;
        val x_275 = resetData_0;
        val x_276 = x_275.asInstanceOf[scala.Long];
        bindingMut_67 = x_276;
        val x_277 = bindingMut_67;
        val x_278 = x_277.asInstanceOf[scala.Long];
        val x_279 = "Agent ".+(x_278);
        resetData_0 = x_279;
        val x_280 = resetData_0;
        val x_281 = x_280.asInstanceOf[java.lang.String];
        bindingMut_66 = x_281;
        val x_282 = bindingMut_66;
        val x_283 = x_282.asInstanceOf[java.lang.String];
        val x_284 = x_283.+(" received a message! ");
        resetData_0 = x_284;
        val x_285 = resetData_0;
        val x_286 = x_285.asInstanceOf[java.lang.String];
        bindingMut_65 = x_286;
        val x_287 = methodArgsMut_45;
        val x_288 = x_287.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_289 = bindingMut_65;
        val x_290 = x_289.asInstanceOf[java.lang.String];
        val x_291 = x_290.+(x_288);
        resetData_0 = x_291;
        val x_292 = resetData_0;
        val x_293 = x_292.asInstanceOf[java.lang.String];
        bindingMut_64 = x_293;
        val x_294 = bindingMut_64;
        val x_295 = x_294.asInstanceOf[java.lang.String];
        scala.Predef.println(x_295);
        resetData_0 = ();
        val x_296 = this.id;
        resetData_0 = x_296;
        val x_297 = resetData_0;
        val x_298 = x_297.asInstanceOf[scala.Long];
        bindingMut_63 = x_298;
        val x_299 = bindingMut_63;
        val x_300 = x_299.asInstanceOf[scala.Long];
        val x_301 = "Agent ".+(x_300);
        resetData_0 = x_301;
        val x_302 = resetData_0;
        val x_303 = x_302.asInstanceOf[java.lang.String];
        bindingMut_62 = x_303;
        val x_304 = bindingMut_62;
        val x_305 = x_304.asInstanceOf[java.lang.String];
        val x_306 = x_305.+(" current knowledge base: ");
        resetData_0 = x_306;
        val x_307 = resetData_0;
        val x_308 = x_307.asInstanceOf[java.lang.String];
        bindingMut_61 = x_308;
        val x_309 = this.vectorClock;
        resetData_0 = x_309;
        val x_310 = resetData_0;
        val x_311 = x_310.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_60 = x_311;
        val x_312 = bindingMut_60;
        val x_313 = x_312.asInstanceOf[example.epistemicLogicVC.VectorClock];
        val x_314 = x_313.knowledgeBase;
        resetData_0 = x_314;
        val x_315 = resetData_0;
        val x_316 = x_315.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_59 = x_316;
        val x_317 = bindingMut_59;
        val x_318 = x_317.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_319 = bindingMut_61;
        val x_320 = x_319.asInstanceOf[java.lang.String];
        val x_321 = x_320.+(x_318);
        resetData_0 = x_321;
        val x_322 = resetData_0;
        val x_323 = x_322.asInstanceOf[java.lang.String];
        bindingMut_58 = x_323;
        val x_324 = bindingMut_58;
        val x_325 = x_324.asInstanceOf[java.lang.String];
        scala.Predef.println(x_325);
        resetData_0 = ();
        positionVar_84 = 16
      }
    else
      ();
    val x_326 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_327 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_326, 25);
    val x_328 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_327);
    resetData_1.prepend(x_328)
  }));
  data_85.update(16, (() => {
    val x_329 = this.id;
    resetData_0 = x_329;
    val x_330 = resetData_0;
    val x_331 = x_330.asInstanceOf[scala.Long];
    bindingMut_41 = x_331;
    val x_332 = this.localTime;
    resetData_0 = x_332;
    val x_333 = resetData_0;
    val x_334 = x_333.asInstanceOf[scala.Int];
    bindingMut_40 = x_334;
    val x_335 = bindingMut_40;
    val x_336 = x_335.asInstanceOf[scala.Int];
    val x_337 = bindingMut_41;
    val x_338 = x_337.asInstanceOf[scala.Long];
    val x_339 = example.epistemicLogicVC.VCHelper.ProcessTime.apply(x_338, x_336);
    resetData_0 = x_339;
    val x_340 = resetData_0;
    val x_341 = x_340.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
    bindingMut_39 = x_341;
    val x_342 = bindingMut_39;
    val x_343 = x_342.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
    val x_344 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicVC.VCHelper.ProcessTime](x_343);
    resetData_0 = x_344;
    val x_345 = resetData_0;
    val x_346 = x_345.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
    bindingMut_38 = x_346;
    val x_347 = this.vectorClock;
    resetData_0 = x_347;
    val x_348 = resetData_0;
    val x_349 = x_348.asInstanceOf[example.epistemicLogicVC.VectorClock];
    bindingMut_37 = x_349;
    val x_350 = bindingMut_37;
    val x_351 = x_350.asInstanceOf[example.epistemicLogicVC.VectorClock];
    val x_353 = {
      val x_352 = bindingMut_38;
      x_352.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]]
    };
    val x_354 = x_351.know(x_353);
    resetData_0 = x_354;
    val x_355 = resetData_0;
    val x_356 = x_355.asInstanceOf[scala.Boolean];
    bindingMut_36 = x_356;
    positionVar_84 = 17
  }));
  data_85.update(17, (() => {
    val x_357 = bindingMut_36;
    val x_358 = x_357.asInstanceOf[scala.Boolean];
    val x_359 = x_358.`unary_!`;
    if (x_359)
      {
        scala.Predef.println("Error at increasing local time!");
        resetData_0 = ();
        val x_360 = resetData_1.remove(0);
        val x_364 = x_360.find(((x_361: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_362 = x_361._1;
          val x_363 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_362.==(x_363)
        }));
        val x_365 = x_364.get;
        val x_366 = x_365._2;
        positionVar_84 = x_366
      }
    else
      positionVar_84 = 29
  }));
  data_85.update(18, (() => {
    val x_367 = resetData_1.remove(0);
    val x_371 = x_367.find(((x_368: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_369 = x_368._1;
      val x_370 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_369.==(x_370)
    }));
    val x_372 = x_371.get;
    val x_373 = x_372._2;
    positionVar_84 = x_373
  }));
  data_85.update(19, (() => {
    val x_374 = resetData_0;
    val x_375 = x_374.asInstanceOf[scala.Any];
    bindingMut_70 = x_375;
    val x_376 = bindingMut_70;
    val x_377 = x_376.asInstanceOf[scala.Any];
    val x_378 = listValMut_71;
    val x_379 = x_378.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_379.reply(this, x_377);
    resetData_0 = ();
    positionVar_84 = 6
  }));
  data_85.update(20, (() => positionVar_84 = 21));
  data_85.update(21, (() => {
    val x_380 = this.popRequestMessages;
    val x_381 = x_380.iterator;
    iterMut_72 = x_381;
    positionVar_84 = 7
  }));
  data_85.update(22, (() => {
    val x_382 = this.vectorClock;
    resetData_0 = x_382;
    val x_383 = resetData_0;
    val x_384 = x_383.asInstanceOf[example.epistemicLogicVC.VectorClock];
    bindingMut_44 = x_384;
    val x_385 = bindingMut_44;
    val x_386 = x_385.asInstanceOf[example.epistemicLogicVC.VectorClock];
    val x_387 = x_386.getKnowledgeBase;
    resetData_0 = x_387;
    val x_388 = resetData_0;
    val x_389 = x_388.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_43 = x_389;
    val x_390 = ((this): meta.deep.runtime.Actor).id;
    val x_392 = {
      val x_391 = methodArgsMut_42;
      x_391.asInstanceOf[generated.example.epistemicLogicVC.Process]
    };
    val x_393 = x_392.id;
    val x_394 = bindingMut_43;
    val x_395 = x_394.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_396 = scala.collection.immutable.Nil.::[scala.Any](x_395);
    val x_397 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_396);
    val x_398 = meta.deep.runtime.RequestMessage.apply(x_390, x_393, 4, x_397);
    val x_399 = x_398.future;
    val x_400 = x_399.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
    x_398.`future_=`(x_400);
    ((this): meta.deep.runtime.Actor).sendMessage(x_398);
    val x_401 = x_398.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_401, ((response_402: meta.deep.runtime.Message) => {
      val x_403 = x_398.future;
      val x_404 = response_402.asInstanceOf[meta.deep.runtime.ResponseMessage];
      val x_405 = x_404.arg;
      val x_406 = x_403.setValue[scala.Any](x_405);
      val x_407 = x_406.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
      x_398.`future_=`(x_407);
      val x_408 = ((this): meta.deep.runtime.Actor).async_messages;
      val x_409 = x_398.future;
      val x_410 = x_409.id;
      val x_411 = scala.Predef.ArrowAssoc[java.lang.String](x_410);
      val x_412 = x_398.future;
      val x_413 = x_411.->[meta.deep.runtime.Future[scala.Any]](x_412);
      val x_414 = x_408.+[meta.deep.runtime.Future[scala.Any]](x_413);
      ((this): meta.deep.runtime.Actor).`async_messages_=`(x_414)
    }));
    val x_415 = x_398.future;
    val x_416 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_415);
    resetData_0 = x_416;
    val x_417 = resetData_1.remove(0);
    val x_421 = x_417.find(((x_418: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_419 = x_418._1;
      val x_420 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_419.==(x_420)
    }));
    val x_422 = x_421.get;
    val x_423 = x_422._2;
    positionVar_84 = x_423
  }));
  data_85.update(23, (() => {
    x_3.remove(0);
    val x_424 = x_3.isEmpty;
    val x_425 = x_424.`unary_!`;
    if (x_425)
      {
        val x_426 = x_3(0);
        val x_427 = x_426.asInstanceOf[generated.example.epistemicLogicVC.Process];
        methodArgsMut_42 = x_427
      }
    else
      ();
    val x_428 = resetData_0;
    val x_429 = x_428.asInstanceOf[scala.Any];
    bindingMut_70 = x_429;
    val x_430 = bindingMut_70;
    val x_431 = x_430.asInstanceOf[scala.Any];
    val x_432 = listValMut_71;
    val x_433 = x_432.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_433.reply(this, x_431);
    resetData_0 = ();
    positionVar_84 = 6
  }));
  data_85.update(24, (() => {
    x_3.remove(0);
    val x_434 = x_3.isEmpty;
    val x_435 = x_434.`unary_!`;
    if (x_435)
      {
        val x_436 = x_3(0);
        val x_437 = x_436.asInstanceOf[generated.example.epistemicLogicVC.Process];
        methodArgsMut_42 = x_437
      }
    else
      ();
    positionVar_84 = 21
  }));
  data_85.update(25, (() => {
    val x_438 = this.vectorClock;
    resetData_0 = x_438;
    val x_439 = resetData_0;
    val x_440 = x_439.asInstanceOf[example.epistemicLogicVC.VectorClock];
    bindingMut_57 = x_440;
    val x_441 = methodArgsMut_45;
    val x_442 = x_441.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_443 = bindingMut_57;
    val x_444 = x_443.asInstanceOf[example.epistemicLogicVC.VectorClock];
    val x_445 = x_444.learn(x_442);
    resetData_0 = x_445;
    val x_446 = resetData_0;
    val x_447 = x_446.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_56 = x_447;
    val x_448 = bindingMut_56;
    val x_449 = x_448.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_450 = x_449.toList;
    resetData_0 = x_450;
    val x_451 = resetData_0;
    val x_452 = x_451.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_55 = x_452;
    val x_453 = bindingMut_55;
    val x_454 = x_453.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_455 = x_454.iterator;
    iterMut_54 = x_455;
    positionVar_84 = 26
  }));
  data_85.update(26, (() => {
    val x_456 = iterMut_54;
    val x_457 = x_456.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_458 = x_457.hasNext;
    if (x_458)
      {
        val x_459 = iterMut_54;
        val x_460 = x_459.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_461 = x_460.next();
        listValMut_53 = x_461;
        val x_462 = this.vectorClock;
        resetData_0 = x_462;
        val x_463 = resetData_0;
        val x_464 = x_463.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_52 = x_464;
        val x_465 = bindingMut_52;
        val x_466 = x_465.asInstanceOf[example.epistemicLogicVC.VectorClock];
        val x_467 = listValMut_53;
        val x_468 = x_467.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
        x_466.updateTimingInfo(x_468);
        resetData_0 = ();
        positionVar_84 = 26
      }
    else
      positionVar_84 = 27
  }));
  data_85.update(27, (() => {
    val x_469 = iterMut_54;
    val x_470 = x_469.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_471 = x_470.hasNext;
    val x_472 = x_471.`unary_!`;
    if (x_472)
      {
        val x_473 = this.id;
        resetData_0 = x_473;
        val x_474 = resetData_0;
        val x_475 = x_474.asInstanceOf[scala.Long];
        bindingMut_51 = x_475;
        val x_476 = bindingMut_51;
        val x_477 = x_476.asInstanceOf[scala.Long];
        val x_478 = "Agent ".+(x_477);
        resetData_0 = x_478;
        val x_479 = resetData_0;
        val x_480 = x_479.asInstanceOf[java.lang.String];
        bindingMut_50 = x_480;
        val x_481 = bindingMut_50;
        val x_482 = x_481.asInstanceOf[java.lang.String];
        val x_483 = x_482.+(" updated knowledge base: ");
        resetData_0 = x_483;
        val x_484 = resetData_0;
        val x_485 = x_484.asInstanceOf[java.lang.String];
        bindingMut_49 = x_485;
        val x_486 = this.vectorClock;
        resetData_0 = x_486;
        val x_487 = resetData_0;
        val x_488 = x_487.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_48 = x_488;
        val x_489 = bindingMut_48;
        val x_490 = x_489.asInstanceOf[example.epistemicLogicVC.VectorClock];
        val x_491 = x_490.knowledgeBase;
        resetData_0 = x_491;
        val x_492 = resetData_0;
        val x_493 = x_492.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_47 = x_493;
        val x_494 = bindingMut_47;
        val x_495 = x_494.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_496 = bindingMut_49;
        val x_497 = x_496.asInstanceOf[java.lang.String];
        val x_498 = x_497.+(x_495);
        resetData_0 = x_498;
        val x_499 = resetData_0;
        val x_500 = x_499.asInstanceOf[java.lang.String];
        bindingMut_46 = x_500;
        val x_501 = bindingMut_46;
        val x_502 = x_501.asInstanceOf[java.lang.String];
        scala.Predef.println(x_502);
        resetData_0 = ();
        x_4.remove(0);
        val x_503 = x_4.isEmpty;
        val x_504 = x_503.`unary_!`;
        if (x_504)
          {
            val x_505 = x_4(0);
            val x_506 = x_505.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
            methodArgsMut_45 = x_506
          }
        else
          ();
        val x_507 = resetData_0;
        val x_508 = x_507.asInstanceOf[scala.Any];
        bindingMut_70 = x_508;
        val x_509 = bindingMut_70;
        val x_510 = x_509.asInstanceOf[scala.Any];
        val x_511 = listValMut_71;
        val x_512 = x_511.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_512.reply(this, x_510);
        resetData_0 = ();
        positionVar_84 = 6
      }
    else
      ()
  }));
  data_85.update(28, (() => {
    val x_513 = resetData_0;
    val x_514 = x_513.asInstanceOf[scala.Any];
    bindingMut_70 = x_514;
    val x_515 = bindingMut_70;
    val x_516 = x_515.asInstanceOf[scala.Any];
    val x_517 = listValMut_71;
    val x_518 = x_517.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_518.reply(this, x_516);
    resetData_0 = ();
    positionVar_84 = 6
  }));
  data_85.update(29, (() => {
    val x_519 = bindingMut_36;
    val x_520 = x_519.asInstanceOf[scala.Boolean];
    if (x_520)
      {
        val x_521 = this.vectorClock;
        resetData_0 = x_521;
        val x_522 = resetData_0;
        val x_523 = x_522.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_35 = x_523;
        val x_525 = {
          val x_524 = bindingMut_38;
          x_524.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]]
        };
        val x_526 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_525);
        resetData_0 = x_526;
        val x_527 = resetData_0;
        val x_528 = x_527.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_34 = x_528;
        val x_529 = bindingMut_34;
        val x_530 = x_529.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_531 = bindingMut_35;
        val x_532 = x_531.asInstanceOf[example.epistemicLogicVC.VectorClock];
        x_532.forget(x_530);
        resetData_0 = ();
        val x_533 = this.localTime;
        resetData_0 = x_533;
        val x_534 = resetData_0;
        val x_535 = x_534.asInstanceOf[scala.Int];
        bindingMut_33 = x_535;
        val x_536 = bindingMut_33;
        val x_537 = x_536.asInstanceOf[scala.Int];
        val x_538 = x_537.+(1);
        resetData_0 = x_538;
        val x_539 = resetData_0;
        val x_540 = x_539.asInstanceOf[scala.Int];
        bindingMut_32 = x_540;
        val x_541 = bindingMut_32;
        val x_542 = x_541.asInstanceOf[scala.Int];
        this.`localTime_=`(x_542);
        resetData_0 = ();
        val x_543 = this.id;
        resetData_0 = x_543;
        val x_544 = resetData_0;
        val x_545 = x_544.asInstanceOf[scala.Long];
        bindingMut_31 = x_545;
        val x_546 = this.localTime;
        resetData_0 = x_546;
        val x_547 = resetData_0;
        val x_548 = x_547.asInstanceOf[scala.Int];
        bindingMut_30 = x_548;
        val x_549 = bindingMut_30;
        val x_550 = x_549.asInstanceOf[scala.Int];
        val x_551 = bindingMut_31;
        val x_552 = x_551.asInstanceOf[scala.Long];
        val x_553 = example.epistemicLogicVC.VCHelper.ProcessTime.apply(x_552, x_550);
        resetData_0 = x_553;
        val x_554 = resetData_0;
        val x_555 = x_554.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        bindingMut_29 = x_555;
        val x_556 = bindingMut_29;
        val x_557 = x_556.asInstanceOf[example.epistemicLogicVC.VCHelper.ProcessTime];
        val x_558 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicVC.VCHelper.ProcessTime](x_557);
        resetData_0 = x_558;
        val x_559 = resetData_0;
        val x_560 = x_559.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        bindingMut_28 = x_560;
        val x_561 = this.vectorClock;
        resetData_0 = x_561;
        val x_562 = resetData_0;
        val x_563 = x_562.asInstanceOf[example.epistemicLogicVC.VectorClock];
        bindingMut_27 = x_563;
        val x_564 = this.localTime;
        resetData_0 = x_564;
        val x_565 = resetData_0;
        val x_566 = x_565.asInstanceOf[scala.Int];
        bindingMut_26 = x_566;
        val x_567 = bindingMut_28;
        val x_568 = x_567.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicVC.VCHelper.ProcessTime]];
        val x_569 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_568);
        resetData_0 = x_569;
        val x_570 = resetData_0;
        val x_571 = x_570.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_25 = x_571;
        val x_572 = bindingMut_25;
        val x_573 = x_572.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_574 = bindingMut_26;
        val x_575 = x_574.asInstanceOf[scala.Int];
        val x_576 = bindingMut_27;
        val x_577 = x_576.asInstanceOf[example.epistemicLogicVC.VectorClock];
        x_577.recordLearning(x_575, x_573);
        resetData_0 = ();
        val x_578 = resetData_1.remove(0);
        val x_582 = x_578.find(((x_579: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_580 = x_579._1;
          val x_581 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_580.==(x_581)
        }));
        val x_583 = x_582.get;
        val x_584 = x_583._2;
        positionVar_84 = x_584
      }
    else
      ()
  }));
  data_85.update(30, (() => {
    val x_585 = listValMut_71;
    val x_586 = x_585.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_587 = x_586.methodId;
    val x_588 = x_587.==(3);
    if (x_588)
      {
        val x_589 = listValMut_71;
        val x_590 = x_589.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_591 = x_590.argss;
        val x_592 = x_591(0);
        val x_593 = x_592(0);
        x_3.prepend(x_593);
        val x_594 = listValMut_71;
        val x_595 = x_594.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_596 = x_595.argss;
        val x_597 = x_596(0);
        val x_598 = x_597(0);
        val x_599 = x_598.asInstanceOf[generated.example.epistemicLogicVC.Process];
        methodArgsMut_42 = x_599;
        positionVar_84 = 31
      }
    else
      ();
    val x_600 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_601 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_600, 23);
    val x_602 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_601);
    resetData_1.prepend(x_602)
  }));
  data_85.update(31, (() => {
    positionVar_84 = 16;
    val x_603 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_604 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_603, 22);
    val x_605 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_604);
    resetData_1.prepend(x_605)
  }));
  data_85.update(32, (() => {
    val x_606 = listValMut_71;
    val x_607 = x_606.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_608 = x_607.methodId;
    val x_609 = x_608.==(2);
    if (x_609)
      positionVar_84 = 33
    else
      ();
    val x_610 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_611 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_610, 19);
    val x_612 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_611);
    resetData_1.prepend(x_612)
  }));
  data_85.update(33, (() => {
    positionVar_84 = 16;
    val x_613 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_614 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_613, 18);
    val x_615 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_614);
    resetData_1.prepend(x_615)
  }));
  data_85.update(34, (() => {
    val x_616 = listValMut_71;
    val x_617 = x_616.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_618 = x_617.methodId;
    val x_619 = x_618.==(1);
    if (x_619)
      positionVar_84 = 16
    else
      ();
    val x_620 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_621 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_620, 28);
    val x_622 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_621);
    resetData_1.prepend(x_622)
  }));
  data_85.update(35, (() => {
    val x_623 = listValMut_71;
    val x_624 = x_623.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_625 = x_624.methodId;
    val x_626 = x_625.==(0);
    if (x_626)
      positionVar_84 = 2
    else
      ();
    val x_627 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_628 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_627, 5);
    val x_629 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_628);
    resetData_1.prepend(x_629)
  }));
  data_85.update(36, (() => {
    val x_630 = iterMut_72;
    val x_631 = x_630.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_632 = x_631.hasNext;
    val x_633 = x_632.`unary_!`;
    if (x_633)
      {
        val x_634 = 1.toDouble;
        resetData_0 = x_634;
        val x_635 = resetData_0;
        val x_636 = x_635.asInstanceOf[scala.Double];
        bindingMut_69 = x_636;
        resetData_0 = 0.0;
        val x_637 = resetData_0;
        val x_638 = x_637.asInstanceOf[scala.Double];
        bindingMut_68 = x_638;
        positionVar_84 = 37
      }
    else
      ()
  }));
  data_85.update(37, (() => {
    val x_639 = meta.deep.runtime.Actor.proceedLabel;
    val x_640 = x_639("turn");
    val x_641 = bindingMut_68;
    val x_642 = x_641.asInstanceOf[scala.Double];
    val x_643 = x_642.+(x_640);
    resetData_0 = x_643;
    val x_644 = resetData_0;
    val x_645 = x_644.asInstanceOf[scala.Double];
    bindingMut_68 = x_645;
    val x_646 = meta.deep.runtime.Actor.labelVals("turn");
    val x_647 = bindingMut_68;
    val x_648 = x_647.asInstanceOf[scala.Double];
    val x_649 = bindingMut_69;
    val x_650 = x_649.asInstanceOf[scala.Double];
    val x_651 = x_650.-(x_648);
    x_646.append(x_651);
    resetData_0 = ();
    positionVar_84 = 38;
    val x_652 = currentTurn;
    val x_653 = x_652.+(1);
    currentTurn = x_653
  }));
  data_85.update(38, (() => {
    val x_654 = bindingMut_68;
    val x_655 = x_654.asInstanceOf[scala.Double];
    val x_656 = bindingMut_69;
    val x_657 = x_656.asInstanceOf[scala.Double];
    val x_658 = x_655.<(x_657);
    if (x_658)
      positionVar_84 = 37
    else
      positionVar_84 = 39
  }));
  data_85.update(39, (() => {
    val x_659 = bindingMut_68;
    val x_660 = x_659.asInstanceOf[scala.Double];
    val x_661 = bindingMut_69;
    val x_662 = x_661.asInstanceOf[scala.Double];
    val x_663 = x_660.<(x_662);
    val x_664 = x_663.`unary_!`;
    if (x_664)
      positionVar_84 = 40
    else
      ()
  }));
  data_85.update(40, (() => if (true)
    positionVar_84 = 41
  else
    positionVar_84 = 44));
  data_85.update(41, (() => {
    val x_665 = this.others;
    resetData_0 = x_665;
    val x_666 = resetData_0;
    val x_667 = x_666.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    bindingMut_82 = x_667;
    val x_668 = bindingMut_82;
    val x_669 = x_668.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    val x_670 = x_669.length;
    resetData_0 = x_670;
    val x_671 = resetData_0;
    val x_672 = x_671.asInstanceOf[scala.Int];
    bindingMut_81 = x_672;
    val x_673 = bindingMut_81;
    val x_674 = x_673.asInstanceOf[scala.Int];
    val x_675 = scala.util.Random.nextInt(x_674);
    resetData_0 = x_675;
    val x_676 = resetData_0;
    val x_677 = x_676.asInstanceOf[scala.Int];
    bindingMut_80 = x_677;
    val x_678 = this.others;
    resetData_0 = x_678;
    val x_679 = resetData_0;
    val x_680 = x_679.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    bindingMut_79 = x_680;
    val x_681 = bindingMut_79;
    val x_682 = x_681.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
    val x_683 = x_682.length;
    resetData_0 = x_683;
    val x_684 = resetData_0;
    val x_685 = x_684.asInstanceOf[scala.Int];
    bindingMut_78 = x_685;
    val x_686 = bindingMut_78;
    val x_687 = x_686.asInstanceOf[scala.Int];
    val x_688 = bindingMut_80;
    val x_689 = x_688.asInstanceOf[scala.Int];
    val x_690 = x_689.==(x_687);
    resetData_0 = x_690;
    val x_691 = resetData_0;
    val x_692 = x_691.asInstanceOf[scala.Boolean];
    bindingMut_77 = x_692;
    positionVar_84 = 42
  }));
  data_85.update(42, (() => {
    val x_693 = bindingMut_77;
    val x_694 = x_693.asInstanceOf[scala.Boolean];
    if (x_694)
      {
        val x_695 = this.id;
        resetData_0 = x_695;
        val x_696 = resetData_0;
        val x_697 = x_696.asInstanceOf[scala.Long];
        bindingMut_76 = x_697;
        val x_698 = bindingMut_76;
        val x_699 = x_698.asInstanceOf[scala.Long];
        val x_700 = x_699.+(" Internal action ");
        resetData_0 = x_700;
        val x_701 = resetData_0;
        val x_702 = x_701.asInstanceOf[java.lang.String];
        bindingMut_75 = x_702;
        val x_703 = bindingMut_75;
        val x_704 = x_703.asInstanceOf[java.lang.String];
        scala.Predef.println(x_704);
        resetData_0 = ();
        positionVar_84 = 33
      }
    else
      positionVar_84 = 43;
    val x_705 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_706 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_705, 20);
    val x_707 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_706);
    resetData_1.prepend(x_707)
  }));
  data_85.update(43, (() => {
    val x_708 = bindingMut_77;
    val x_709 = x_708.asInstanceOf[scala.Boolean];
    val x_710 = x_709.`unary_!`;
    if (x_710)
      {
        val x_711 = this.others;
        resetData_0 = x_711;
        val x_712 = resetData_0;
        val x_713 = x_712.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
        bindingMut_74 = x_713;
        val x_714 = bindingMut_74;
        val x_715 = x_714.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicVC.Process]];
        val x_716 = bindingMut_80;
        val x_717 = x_716.asInstanceOf[scala.Int];
        val x_718 = x_715(x_717);
        resetData_0 = x_718;
        val x_719 = resetData_0;
        val x_720 = x_719.asInstanceOf[generated.example.epistemicLogicVC.Process];
        bindingMut_73 = x_720;
        val x_721 = bindingMut_73;
        val x_722 = x_721.asInstanceOf[generated.example.epistemicLogicVC.Process];
        x_3.prepend(x_722);
        val x_723 = bindingMut_73;
        val x_724 = x_723.asInstanceOf[generated.example.epistemicLogicVC.Process];
        val x_725 = x_724.asInstanceOf[generated.example.epistemicLogicVC.Process];
        methodArgsMut_42 = x_725;
        positionVar_84 = 31
      }
    else
      ();
    val x_726 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_727 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_726, 24);
    val x_728 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_727);
    resetData_1.prepend(x_728)
  }));
  data_85.update(44, (() => {
    val x_729 = true.`unary_!`;
    if (x_729)
      {
        val x_730 = resetData_1.remove(0);
        val x_734 = x_730.find(((x_731: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_732 = x_731._1;
          val x_733 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_732.==(x_733)
        }));
        val x_735 = x_734.get;
        val x_736 = x_735._2;
        positionVar_84 = x_736
      }
    else
      ()
  }));
  data_85.update(45, (() => {
    val x_737 = resetData_0;
    val x_738 = x_737.asInstanceOf[scala.Any];
    bindingMut_70 = x_738;
    val x_739 = bindingMut_70;
    val x_740 = x_739.asInstanceOf[scala.Any];
    val x_741 = listValMut_71;
    val x_742 = x_741.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_742.reply(this, x_740);
    resetData_0 = ();
    positionVar_84 = 6
  }));
  data_85.update(46, (() => positionVar_84 = 47));
  data_85.update(47, (() => {
    positionVar_84 = 48;
    val x_743 = currentTurn;
    val x_744 = x_743.+(1);
    currentTurn = x_744
  }));
  data_85.update(48, (() => positionVar_84 = 47));
  data_85.update(49, (() => positionVar_84 = 50));
  data_85.update(50, (() => if (true)
    positionVar_84 = 41
  else
    positionVar_84 = 51));
  data_85.update(51, (() => {
    val x_745 = true.`unary_!`;
    if (x_745)
      {
        val x_746 = resetData_1.remove(0);
        val x_750 = x_746.find(((x_747: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_748 = x_747._1;
          val x_749 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_748.==(x_749)
        }));
        val x_751 = x_750.get;
        val x_752 = x_751._2;
        positionVar_84 = x_752
      }
    else
      ()
  }));
  data_85
}).apply();
  
  override def run_until(until_754: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_755 = currentTurn;
      val x_756 = x_755.<=(until_754);
      x_756.&&({
        val x_757 = positionVar_84;
        val x_758 = commands_753.length;
        x_757.<(x_758)
      })
    }) 
      {
        val x_759 = positionVar_84;
        val x_760 = commands_753.apply(x_759);
        x_760.apply()
      }
    ;
    this
  }
}
