package generated.example.epistemicLogicMC

class Child (val isMuddy: Boolean) extends meta.deep.runtime.Actor {
  var neighbors: List[generated.example.epistemicLogicMC.Child] = scala.collection.immutable.Nil
  var neighborIds: scala.collection.mutable.ListBuffer[Long] = new scala.collection.mutable.ListBuffer[scala.Long]()
  var epoch: Int = 0
  var isForward: Boolean = false
  var knowledgeBase: lib.EpistemicLogic.KnowledgeBase = new lib.EpistemicLogic.KnowledgeBase()
  var future_objs: scala.collection.mutable.ListBuffer[Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = new scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]()
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_4: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_5: scala.Int = 0;
  private var bindingMut_6: scala.Boolean = false;
  private var bindingMut_7: scala.Boolean = false;
  private var bindingMut_8: scala.Long = 0L;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: java.lang.String = null;
  private var bindingMut_11: scala.Long = 0L;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: java.lang.String = null;
  private var bindingMut_14: scala.Long = 0L;
  private var bindingMut_15: scala.Boolean = false;
  private var bindingMut_16: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_17: scala.Long = 0L;
  private var bindingMut_18: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_19: scala.Boolean = false;
  private var bindingMut_20: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_21: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_22: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_23: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_24: scala.Boolean = false;
  private var bindingMut_25: scala.Long = 0L;
  private var bindingMut_26: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_27: scala.Boolean = false;
  private var bindingMut_28: scala.Long = 0L;
  private var bindingMut_29: scala.Boolean = false;
  private var bindingMut_30: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_31: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_32: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_33: scala.collection.mutable.ListBuffer[scala.Long] = null;
  private var bindingMut_34: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_35: scala.Long = 0L;
  private var bindingMut_36: scala.collection.mutable.ListBuffer[scala.Long] = null;
  private var bindingMut_37: scala.Int = 0;
  private var bindingMut_38: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_39: scala.Boolean = false;
  private var bindingMut_40: scala.Boolean = false;
  private var bindingMut_41: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_42: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent] = null;
  private var bindingMut_43: example.epistemicLogicMC.MCHelper.HearParent = null;
  private var bindingMut_44: scala.Int = 0;
  private var bindingMut_45: scala.Int = 0;
  private var bindingMut_46: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_47: scala.Int = 0;
  private var bindingMut_48: scala.Int = 0;
  private var bindingMut_49: scala.Boolean = false;
  private var bindingMut_50: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_51: lib.EpistemicLogic.KnowledgeBase = null;
  private var methodArgsMut_52: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var bindingMut_53: scala.Int = 0;
  private var bindingMut_54: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_55: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_56: scala.Int = 0;
  private var bindingMut_57: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_58: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_59: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_60: scala.collection.mutable.ListBuffer[scala.Long] = null;
  private var bindingMut_61: scala.Long = 0L;
  private var bindingMut_62: scala.Long = 0L;
  private var bindingMut_63: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_64: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_65: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_66: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_67: lib.EpistemicLogic.Sentence.NotE = null;
  private var bindingMut_68: scala.Long = 0L;
  private var bindingMut_69: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_70: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_71: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_72: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_73: scala.Long = 0L;
  private var bindingMut_74: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_75: scala.Int = 0;
  private var bindingMut_76: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_77: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_78: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_79: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_80: scala.Boolean = false;
  private var bindingMut_81: scala.Long = 0L;
  private var bindingMut_82: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_83: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_84: scala.Boolean = false;
  private var bindingMut_85: scala.Long = 0L;
  private var bindingMut_86: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_87: scala.Boolean = false;
  private var bindingMut_88: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_89: scala.Boolean = false;
  private var bindingMut_90: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_91: scala.Boolean = false;
  private var bindingMut_92: scala.Int = 0;
  private var bindingMut_93: scala.Int = 0;
  private var bindingMut_94: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_95: scala.Boolean = false;
  private var bindingMut_96: scala.Boolean = false;
  private var bindingMut_97: scala.Boolean = false;
  private var bindingMut_98: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_99: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent] = null;
  private var bindingMut_100: example.epistemicLogicMC.MCHelper.HearParent = null;
  private var bindingMut_101: scala.Int = 0;
  private var bindingMut_102: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_103: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_104: scala.Long = 0L;
  private var bindingMut_105: scala.Int = 0;
  private var bindingMut_106: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_107: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_108: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_109: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_110: scala.Boolean = false;
  private var bindingMut_111: scala.Long = 0L;
  private var bindingMut_112: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_113: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_114: scala.Boolean = false;
  private var bindingMut_115: scala.Long = 0L;
  private var bindingMut_116: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_117: scala.Boolean = false;
  private var bindingMut_118: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var listValMut_119: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var iterMut_120: scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_121: scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_122: scala.Boolean = false;
  private var bindingMut_123: scala.Int = 0;
  private var bindingMut_124: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_125: scala.Int = 0;
  private var bindingMut_126: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_127: meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var listValMut_128: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var iterMut_129: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_130: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_131: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_132: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var bindingMut_133: meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var listValMut_134: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var iterMut_135: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_136: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_137: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_138: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_139: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_140: lib.EpistemicLogic.Sentence.EpistemicSentence = null;
  private var bindingMut_141: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_142: scala.Any = null;
  private var listValMut_143: meta.deep.runtime.RequestMessage = null;
  private var iterMut_144: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_145: scala.Double = 0.0;
  private var bindingMut_146: scala.Double = 0.0;
  private var bindingMut_147: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var bindingMut_148: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var listValMut_149: generated.example.epistemicLogicMC.Child = null;
  private var iterMut_150: scala.collection.Iterator[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_151: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_152: scala.Any = null;
  private var listValMut_153: meta.deep.runtime.RequestMessage = null;
  private var iterMut_154: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var listValMut_155: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var iterMut_156: scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_157: scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_158: scala.Long = 0L;
  private var bindingMut_159: scala.collection.mutable.ListBuffer[scala.Long] = null;
  private var listValMut_160: generated.example.epistemicLogicMC.Child = null;
  private var iterMut_161: scala.collection.Iterator[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_162: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var positionVar_164: scala.Int = 0;
  
  val commands_1404 = (() => {
  val data_165 = new scala.Array[scala.Function0[scala.Unit]](104);
  data_165.update(0, (() => {
    positionVar_164 = 1;
    val x_166 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_167 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_166, 66);
    val x_168 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_167);
    resetData_1.prepend(x_168)
  }));
  data_165.update(1, (() => {
    val x_169 = this.neighbors;
    resetData_0 = x_169;
    val x_170 = resetData_0;
    val x_171 = x_170.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    bindingMut_162 = x_171;
    val x_172 = bindingMut_162;
    val x_173 = x_172.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    val x_174 = x_173.iterator;
    iterMut_161 = x_174;
    positionVar_164 = 2
  }));
  data_165.update(2, (() => {
    val x_175 = iterMut_161;
    val x_176 = x_175.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_177 = x_176.hasNext;
    val x_178 = x_177.`unary_!`;
    if (x_178)
      positionVar_164 = 3
    else
      positionVar_164 = 103
  }));
  data_165.update(3, (() => if (true)
    positionVar_164 = 4
  else
    positionVar_164 = 102));
  data_165.update(4, (() => {
    positionVar_164 = 5;
    val x_179 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_180 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_179, 95);
    val x_181 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_180);
    resetData_1.prepend(x_181)
  }));
  data_165.update(5, (() => {
    val x_182 = this.neighbors;
    resetData_0 = x_182;
    val x_183 = resetData_0;
    val x_184 = x_183.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    bindingMut_151 = x_184;
    val x_185 = bindingMut_151;
    val x_186 = x_185.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    val x_187 = x_186.iterator;
    iterMut_150 = x_187;
    positionVar_164 = 6
  }));
  data_165.update(6, (() => {
    val x_188 = iterMut_150;
    val x_189 = x_188.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_190 = x_189.hasNext;
    val x_191 = x_190.`unary_!`;
    if (x_191)
      positionVar_164 = 7
    else
      positionVar_164 = 101
  }));
  data_165.update(7, (() => {
    val x_192 = this.future_objs;
    val x_193 = x_192.toList;
    val x_196 = x_193.forall(((x_194: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_195 = x_194.get;
      this.isCompleted(x_195)
    }));
    val x_197 = x_196.`unary_!`;
    if (x_197)
      positionVar_164 = 8
    else
      positionVar_164 = 100
  }));
  data_165.update(8, (() => {
    val x_198 = 1.toDouble;
    resetData_0 = x_198;
    val x_199 = resetData_0;
    val x_200 = x_199.asInstanceOf[scala.Double];
    bindingMut_146 = x_200;
    resetData_0 = 0.0;
    val x_201 = resetData_0;
    val x_202 = x_201.asInstanceOf[scala.Double];
    bindingMut_145 = x_202;
    positionVar_164 = 9
  }));
  data_165.update(9, (() => {
    val x_203 = meta.deep.runtime.Actor.proceedLabel;
    val x_204 = x_203("turn");
    val x_205 = bindingMut_145;
    val x_206 = x_205.asInstanceOf[scala.Double];
    val x_207 = x_206.+(x_204);
    resetData_0 = x_207;
    val x_208 = resetData_0;
    val x_209 = x_208.asInstanceOf[scala.Double];
    bindingMut_145 = x_209;
    val x_210 = meta.deep.runtime.Actor.labelVals("turn");
    val x_211 = bindingMut_145;
    val x_212 = x_211.asInstanceOf[scala.Double];
    val x_213 = bindingMut_146;
    val x_214 = x_213.asInstanceOf[scala.Double];
    val x_215 = x_214.-(x_212);
    x_210.append(x_215);
    resetData_0 = ();
    positionVar_164 = 10;
    val x_216 = currentTurn;
    val x_217 = x_216.+(1);
    currentTurn = x_217
  }));
  data_165.update(10, (() => {
    val x_218 = bindingMut_145;
    val x_219 = x_218.asInstanceOf[scala.Double];
    val x_220 = bindingMut_146;
    val x_221 = x_220.asInstanceOf[scala.Double];
    val x_222 = x_219.<(x_221);
    if (x_222)
      positionVar_164 = 9
    else
      positionVar_164 = 11
  }));
  data_165.update(11, (() => {
    val x_223 = bindingMut_145;
    val x_224 = x_223.asInstanceOf[scala.Double];
    val x_225 = bindingMut_146;
    val x_226 = x_225.asInstanceOf[scala.Double];
    val x_227 = x_224.<(x_226);
    val x_228 = x_227.`unary_!`;
    if (x_228)
      {
        val x_229 = this.popRequestMessages;
        val x_230 = x_229.iterator;
        iterMut_144 = x_230;
        positionVar_164 = 12
      }
    else
      ()
  }));
  data_165.update(12, (() => {
    val x_231 = iterMut_144;
    val x_232 = x_231.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_233 = x_232.hasNext;
    if (x_233)
      {
        val x_234 = iterMut_144;
        val x_235 = x_234.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_236 = x_235.next();
        listValMut_143 = x_236;
        positionVar_164 = 13
      }
    else
      positionVar_164 = 84
  }));
  data_165.update(13, (() => {
    val x_237 = listValMut_143;
    val x_238 = x_237.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_239 = x_238.methodId;
    val x_240 = x_239.==(3);
    val x_241 = x_240.`unary_!`;
    if (x_241)
      positionVar_164 = 14
    else
      positionVar_164 = 83
  }));
  data_165.update(14, (() => {
    val x_242 = listValMut_143;
    val x_243 = x_242.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_244 = x_243.methodId;
    val x_245 = x_244.==(4);
    val x_246 = x_245.`unary_!`;
    if (x_246)
      positionVar_164 = 15
    else
      positionVar_164 = 82
  }));
  data_165.update(15, (() => {
    val x_247 = listValMut_143;
    val x_248 = x_247.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_249 = x_248.methodId;
    val x_250 = x_249.==(5);
    val x_251 = x_250.`unary_!`;
    if (x_251)
      positionVar_164 = 16
    else
      positionVar_164 = 21
  }));
  data_165.update(16, (() => {
    val x_252 = listValMut_143;
    val x_253 = x_252.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_254 = x_253.methodId;
    val x_255 = x_254.==(6);
    val x_256 = x_255.`unary_!`;
    if (x_256)
      positionVar_164 = 17
    else
      positionVar_164 = 20
  }));
  data_165.update(17, (() => {
    val x_257 = listValMut_143;
    val x_258 = x_257.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_259 = x_258.methodId;
    val x_260 = x_259.==(7);
    val x_261 = x_260.`unary_!`;
    if (x_261)
      {
        val x_262 = listValMut_143;
        val x_263 = x_262.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_264 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_263);
        val x_265 = this.addReceiveMessages(x_264);
        resetData_0 = x_265;
        positionVar_164 = 18
      }
    else
      positionVar_164 = 19
  }));
  data_165.update(18, (() => positionVar_164 = 12));
  data_165.update(19, (() => {
    val x_266 = listValMut_143;
    val x_267 = x_266.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_268 = x_267.methodId;
    val x_269 = x_268.==(7);
    if (x_269)
      positionVar_164 = 1
    else
      ();
    val x_270 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_271 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_270, 69);
    val x_272 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_271);
    resetData_1.prepend(x_272)
  }));
  data_165.update(20, (() => {
    val x_273 = listValMut_143;
    val x_274 = x_273.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_275 = x_274.methodId;
    val x_276 = x_275.==(6);
    if (x_276)
      positionVar_164 = 5
    else
      ();
    val x_277 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_278 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_277, 93);
    val x_279 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_278);
    resetData_1.prepend(x_279)
  }));
  data_165.update(21, (() => {
    val x_280 = listValMut_143;
    val x_281 = x_280.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_282 = x_281.methodId;
    val x_283 = x_282.==(5);
    if (x_283)
      {
        val x_284 = listValMut_143;
        val x_285 = x_284.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_286 = x_285.argss;
        val x_287 = x_286(0);
        val x_288 = x_287(0);
        x_3.prepend(x_288);
        val x_289 = listValMut_143;
        val x_290 = x_289.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_291 = x_290.argss;
        val x_292 = x_291(0);
        val x_293 = x_292(0);
        val x_294 = x_293.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_294;
        positionVar_164 = 22
      }
    else
      ();
    val x_295 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_296 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_295, 28);
    val x_297 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_296);
    resetData_1.prepend(x_297)
  }));
  data_165.update(22, (() => {
    val x_298 = this.epoch;
    resetData_0 = x_298;
    val x_299 = resetData_0;
    val x_300 = x_299.asInstanceOf[scala.Int];
    bindingMut_101 = x_300;
    val x_301 = bindingMut_101;
    val x_302 = x_301.asInstanceOf[scala.Int];
    val x_303 = example.epistemicLogicMC.MCHelper.HearParent(x_302);
    resetData_0 = x_303;
    val x_304 = resetData_0;
    val x_305 = x_304.asInstanceOf[example.epistemicLogicMC.MCHelper.HearParent];
    bindingMut_100 = x_305;
    val x_306 = bindingMut_100;
    val x_307 = x_306.asInstanceOf[example.epistemicLogicMC.MCHelper.HearParent];
    val x_308 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.HearParent](x_307);
    resetData_0 = x_308;
    val x_309 = resetData_0;
    val x_310 = x_309.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent]];
    bindingMut_99 = x_310;
    val x_311 = this.knowledgeBase;
    resetData_0 = x_311;
    val x_312 = resetData_0;
    val x_313 = x_312.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_98 = x_313;
    val x_314 = bindingMut_98;
    val x_315 = x_314.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_317 = {
      val x_316 = bindingMut_99;
      x_316.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent]]
    };
    val x_318 = x_315.know(x_317);
    resetData_0 = x_318;
    val x_319 = resetData_0;
    val x_320 = x_319.asInstanceOf[scala.Boolean];
    bindingMut_97 = x_320;
    positionVar_164 = 23
  }));
  data_165.update(23, (() => {
    val x_321 = bindingMut_97;
    val x_322 = x_321.asInstanceOf[scala.Boolean];
    val x_323 = x_322.`unary_!`;
    if (x_323)
      {
        resetData_0 = false;
        positionVar_164 = 24
      }
    else
      positionVar_164 = 81
  }));
  data_165.update(24, (() => {
    val x_324 = resetData_0;
    val x_325 = x_324.asInstanceOf[scala.Boolean];
    bindingMut_95 = x_325;
    positionVar_164 = 25
  }));
  data_165.update(25, (() => {
    val x_326 = bindingMut_95;
    val x_327 = x_326.asInstanceOf[scala.Boolean];
    val x_328 = x_327.`unary_!`;
    if (x_328)
      {
        resetData_0 = false;
        positionVar_164 = 26
      }
    else
      positionVar_164 = 80
  }));
  data_165.update(26, (() => {
    val x_329 = resetData_0;
    val x_330 = x_329.asInstanceOf[scala.Boolean];
    bindingMut_91 = x_330;
    positionVar_164 = 27
  }));
  data_165.update(27, (() => {
    val x_331 = bindingMut_91;
    val x_332 = x_331.asInstanceOf[scala.Boolean];
    val x_333 = x_332.`unary_!`;
    if (x_333)
      {
        val x_334 = resetData_1.remove(0);
        val x_338 = x_334.find(((x_335: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_336 = x_335._1;
          val x_337 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_336.==(x_337)
        }));
        val x_339 = x_338.get;
        val x_340 = x_339._2;
        positionVar_164 = x_340
      }
    else
      positionVar_164 = 73
  }));
  data_165.update(28, (() => {
    x_3.remove(0);
    val x_341 = x_3.isEmpty;
    val x_342 = x_341.`unary_!`;
    if (x_342)
      {
        val x_343 = x_3(0);
        val x_344 = x_343.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_344
      }
    else
      ();
    val x_345 = resetData_0;
    val x_346 = x_345.asInstanceOf[scala.Any];
    bindingMut_142 = x_346;
    val x_347 = bindingMut_142;
    val x_348 = x_347.asInstanceOf[scala.Any];
    val x_349 = listValMut_143;
    val x_350 = x_349.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_350.reply(this, x_348);
    resetData_0 = ();
    positionVar_164 = 18
  }));
  data_165.update(29, (() => {
    x_3.remove(0);
    val x_351 = x_3.isEmpty;
    val x_352 = x_351.`unary_!`;
    if (x_352)
      {
        val x_353 = x_3(0);
        val x_354 = x_353.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_354
      }
    else
      ();
    val x_355 = resetData_0;
    val x_356 = x_355.asInstanceOf[scala.Any];
    bindingMut_152 = x_356;
    val x_357 = bindingMut_152;
    val x_358 = x_357.asInstanceOf[scala.Any];
    val x_359 = listValMut_153;
    val x_360 = x_359.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_360.reply(this, x_358);
    resetData_0 = ();
    positionVar_164 = 30
  }));
  data_165.update(30, (() => positionVar_164 = 31));
  data_165.update(31, (() => {
    val x_361 = iterMut_154;
    val x_362 = x_361.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_363 = x_362.hasNext;
    if (x_363)
      {
        val x_364 = iterMut_154;
        val x_365 = x_364.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_366 = x_365.next();
        listValMut_153 = x_366;
        positionVar_164 = 32
      }
    else
      positionVar_164 = 62
  }));
  data_165.update(32, (() => {
    val x_367 = listValMut_153;
    val x_368 = x_367.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_369 = x_368.methodId;
    val x_370 = x_369.==(3);
    val x_371 = x_370.`unary_!`;
    if (x_371)
      positionVar_164 = 33
    else
      positionVar_164 = 58
  }));
  data_165.update(33, (() => {
    val x_372 = listValMut_153;
    val x_373 = x_372.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_374 = x_373.methodId;
    val x_375 = x_374.==(4);
    val x_376 = x_375.`unary_!`;
    if (x_376)
      positionVar_164 = 34
    else
      positionVar_164 = 40
  }));
  data_165.update(34, (() => {
    val x_377 = listValMut_153;
    val x_378 = x_377.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_379 = x_378.methodId;
    val x_380 = x_379.==(5);
    val x_381 = x_380.`unary_!`;
    if (x_381)
      positionVar_164 = 35
    else
      positionVar_164 = 39
  }));
  data_165.update(35, (() => {
    val x_382 = listValMut_153;
    val x_383 = x_382.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_384 = x_383.methodId;
    val x_385 = x_384.==(6);
    val x_386 = x_385.`unary_!`;
    if (x_386)
      positionVar_164 = 36
    else
      positionVar_164 = 38
  }));
  data_165.update(36, (() => {
    val x_387 = listValMut_153;
    val x_388 = x_387.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_389 = x_388.methodId;
    val x_390 = x_389.==(7);
    val x_391 = x_390.`unary_!`;
    if (x_391)
      {
        val x_392 = listValMut_153;
        val x_393 = x_392.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_394 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_393);
        val x_395 = this.addReceiveMessages(x_394);
        resetData_0 = x_395;
        positionVar_164 = 30
      }
    else
      positionVar_164 = 37
  }));
  data_165.update(37, (() => {
    val x_396 = listValMut_153;
    val x_397 = x_396.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_398 = x_397.methodId;
    val x_399 = x_398.==(7);
    if (x_399)
      positionVar_164 = 1
    else
      ();
    val x_400 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_401 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_400, 65);
    val x_402 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_401);
    resetData_1.prepend(x_402)
  }));
  data_165.update(38, (() => {
    val x_403 = listValMut_153;
    val x_404 = x_403.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_405 = x_404.methodId;
    val x_406 = x_405.==(6);
    if (x_406)
      positionVar_164 = 5
    else
      ();
    val x_407 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_408 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_407, 94);
    val x_409 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_408);
    resetData_1.prepend(x_409)
  }));
  data_165.update(39, (() => {
    val x_410 = listValMut_153;
    val x_411 = x_410.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_412 = x_411.methodId;
    val x_413 = x_412.==(5);
    if (x_413)
      {
        val x_414 = listValMut_153;
        val x_415 = x_414.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_416 = x_415.argss;
        val x_417 = x_416(0);
        val x_418 = x_417(0);
        x_3.prepend(x_418);
        val x_419 = listValMut_153;
        val x_420 = x_419.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_421 = x_420.argss;
        val x_422 = x_421(0);
        val x_423 = x_422(0);
        val x_424 = x_423.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_424;
        positionVar_164 = 22
      }
    else
      ();
    val x_425 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_426 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_425, 29);
    val x_427 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_426);
    resetData_1.prepend(x_427)
  }));
  data_165.update(40, (() => {
    val x_428 = listValMut_153;
    val x_429 = x_428.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_430 = x_429.methodId;
    val x_431 = x_430.==(4);
    if (x_431)
      positionVar_164 = 41
    else
      ();
    val x_432 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_433 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_432, 44);
    val x_434 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_433);
    resetData_1.prepend(x_434)
  }));
  data_165.update(41, (() => {
    val x_435 = this.knowledgeBase;
    resetData_0 = x_435;
    val x_436 = resetData_0;
    val x_437 = x_436.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_51 = x_437;
    val x_438 = example.epistemicLogicMC.MCHelper.seeAllNeighbor();
    resetData_0 = x_438;
    val x_439 = resetData_0;
    val x_440 = x_439.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    bindingMut_50 = x_440;
    val x_441 = bindingMut_50;
    val x_442 = x_441.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_443 = bindingMut_51;
    val x_444 = x_443.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_445 = x_444.know(x_442);
    resetData_0 = x_445;
    val x_446 = resetData_0;
    val x_447 = x_446.asInstanceOf[scala.Boolean];
    bindingMut_49 = x_447;
    positionVar_164 = 42
  }));
  data_165.update(42, (() => {
    val x_448 = bindingMut_49;
    val x_449 = x_448.asInstanceOf[scala.Boolean];
    val x_450 = x_449.`unary_!`;
    if (x_450)
      {
        val x_451 = resetData_1.remove(0);
        val x_455 = x_451.find(((x_452: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_453 = x_452._1;
          val x_454 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_453.==(x_454)
        }));
        val x_456 = x_455.get;
        val x_457 = x_456._2;
        positionVar_164 = x_457
      }
    else
      positionVar_164 = 45
  }));
  data_165.update(43, (() => {
    val x_458 = resetData_0;
    val x_459 = x_458.asInstanceOf[scala.Any];
    bindingMut_142 = x_459;
    val x_460 = bindingMut_142;
    val x_461 = x_460.asInstanceOf[scala.Any];
    val x_462 = listValMut_143;
    val x_463 = x_462.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_463.reply(this, x_461);
    resetData_0 = ();
    positionVar_164 = 18
  }));
  data_165.update(44, (() => {
    val x_464 = resetData_0;
    val x_465 = x_464.asInstanceOf[scala.Any];
    bindingMut_152 = x_465;
    val x_466 = bindingMut_152;
    val x_467 = x_466.asInstanceOf[scala.Any];
    val x_468 = listValMut_153;
    val x_469 = x_468.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_469.reply(this, x_467);
    resetData_0 = ();
    positionVar_164 = 30
  }));
  data_165.update(45, (() => {
    val x_470 = bindingMut_49;
    val x_471 = x_470.asInstanceOf[scala.Boolean];
    if (x_471)
      {
        val x_472 = this.epoch;
        resetData_0 = x_472;
        val x_473 = resetData_0;
        val x_474 = x_473.asInstanceOf[scala.Int];
        bindingMut_48 = x_474;
        val x_475 = bindingMut_48;
        val x_476 = x_475.asInstanceOf[scala.Int];
        val x_477 = x_476.+(1);
        resetData_0 = x_477;
        val x_478 = resetData_0;
        val x_479 = x_478.asInstanceOf[scala.Int];
        bindingMut_47 = x_479;
        val x_480 = bindingMut_47;
        val x_481 = x_480.asInstanceOf[scala.Int];
        this.`epoch_=`(x_481);
        resetData_0 = ();
        val x_482 = this.knowledgeBase;
        resetData_0 = x_482;
        val x_483 = resetData_0;
        val x_484 = x_483.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        bindingMut_46 = x_484;
        val x_485 = this.epoch;
        resetData_0 = x_485;
        val x_486 = resetData_0;
        val x_487 = x_486.asInstanceOf[scala.Int];
        bindingMut_45 = x_487;
        val x_488 = this.epoch;
        resetData_0 = x_488;
        val x_489 = resetData_0;
        val x_490 = x_489.asInstanceOf[scala.Int];
        bindingMut_44 = x_490;
        val x_491 = bindingMut_44;
        val x_492 = x_491.asInstanceOf[scala.Int];
        val x_493 = example.epistemicLogicMC.MCHelper.HearParent(x_492);
        resetData_0 = x_493;
        val x_494 = resetData_0;
        val x_495 = x_494.asInstanceOf[example.epistemicLogicMC.MCHelper.HearParent];
        bindingMut_43 = x_495;
        val x_496 = bindingMut_43;
        val x_497 = x_496.asInstanceOf[example.epistemicLogicMC.MCHelper.HearParent];
        val x_498 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.HearParent](x_497);
        resetData_0 = x_498;
        val x_499 = resetData_0;
        val x_500 = x_499.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent]];
        bindingMut_42 = x_500;
        val x_501 = bindingMut_42;
        val x_502 = x_501.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.HearParent]];
        val x_503 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_502);
        resetData_0 = x_503;
        val x_504 = resetData_0;
        val x_505 = x_504.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_41 = x_505;
        val x_506 = bindingMut_41;
        val x_507 = x_506.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_508 = bindingMut_45;
        val x_509 = x_508.asInstanceOf[scala.Int];
        val x_510 = bindingMut_46;
        val x_511 = x_510.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        x_511.recordLearning(x_509, x_507);
        resetData_0 = ();
        val x_512 = this.isForward;
        resetData_0 = x_512;
        val x_513 = resetData_0;
        val x_514 = x_513.asInstanceOf[scala.Boolean];
        bindingMut_40 = x_514;
        val x_515 = bindingMut_40;
        val x_516 = x_515.asInstanceOf[scala.Boolean];
        val x_517 = x_516.`unary_!`;
        resetData_0 = x_517;
        val x_518 = resetData_0;
        val x_519 = x_518.asInstanceOf[scala.Boolean];
        bindingMut_39 = x_519;
        positionVar_164 = 46
      }
    else
      ()
  }));
  data_165.update(46, (() => {
    val x_520 = bindingMut_39;
    val x_521 = x_520.asInstanceOf[scala.Boolean];
    val x_522 = x_521.`unary_!`;
    if (x_522)
      positionVar_164 = 47
    else
      positionVar_164 = 48
  }));
  data_165.update(47, (() => {
    val x_523 = resetData_1.remove(0);
    val x_527 = x_523.find(((x_524: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_525 = x_524._1;
      val x_526 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_525.==(x_526)
    }));
    val x_528 = x_527.get;
    val x_529 = x_528._2;
    positionVar_164 = x_529
  }));
  data_165.update(48, (() => {
    val x_530 = bindingMut_39;
    val x_531 = x_530.asInstanceOf[scala.Boolean];
    if (x_531)
      {
        val x_532 = this.knowledgeBase;
        resetData_0 = x_532;
        val x_533 = resetData_0;
        val x_534 = x_533.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        bindingMut_38 = x_534;
        val x_535 = this.epoch;
        resetData_0 = x_535;
        val x_536 = resetData_0;
        val x_537 = x_536.asInstanceOf[scala.Int];
        bindingMut_37 = x_537;
        val x_538 = this.neighborIds;
        resetData_0 = x_538;
        val x_539 = resetData_0;
        val x_540 = x_539.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        bindingMut_36 = x_540;
        val x_541 = this.id;
        resetData_0 = x_541;
        val x_542 = resetData_0;
        val x_543 = x_542.asInstanceOf[scala.Long];
        bindingMut_35 = x_543;
        val x_544 = bindingMut_35;
        val x_545 = x_544.asInstanceOf[scala.Long];
        val x_546 = scala.collection.immutable.List.apply[scala.Long](x_545);
        resetData_0 = x_546;
        val x_547 = resetData_0;
        val x_548 = x_547.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_34 = x_548;
        val x_549 = bindingMut_34;
        val x_550 = x_549.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_551 = bindingMut_36;
        val x_552 = x_551.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        val x_553 = x_552.++(x_550);
        resetData_0 = x_553;
        val x_554 = resetData_0;
        val x_555 = x_554.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        bindingMut_33 = x_555;
        val x_556 = bindingMut_33;
        val x_557 = x_556.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        val x_558 = x_557.toList;
        resetData_0 = x_558;
        val x_559 = resetData_0;
        val x_560 = x_559.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_32 = x_560;
        val x_561 = bindingMut_32;
        val x_562 = x_561.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_563 = example.epistemicLogicMC.MCHelper.announce(x_562);
        resetData_0 = x_563;
        val x_564 = resetData_0;
        val x_565 = x_564.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
        bindingMut_31 = x_565;
        val x_566 = bindingMut_31;
        val x_567 = x_566.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
        val x_568 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_567);
        resetData_0 = x_568;
        val x_569 = resetData_0;
        val x_570 = x_569.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_30 = x_570;
        val x_571 = bindingMut_30;
        val x_572 = x_571.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_573 = bindingMut_37;
        val x_574 = x_573.asInstanceOf[scala.Int];
        val x_575 = bindingMut_38;
        val x_576 = x_575.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        x_576.recordLearning(x_574, x_572);
        resetData_0 = ();
        val x_577 = this.isMuddy;
        resetData_0 = x_577;
        val x_578 = resetData_0;
        val x_579 = x_578.asInstanceOf[scala.Boolean];
        bindingMut_29 = x_579;
        positionVar_164 = 49
      }
    else
      ()
  }));
  data_165.update(49, (() => {
    val x_580 = bindingMut_29;
    val x_581 = x_580.asInstanceOf[scala.Boolean];
    if (x_581)
      {
        val x_582 = this.id;
        resetData_0 = x_582;
        val x_583 = resetData_0;
        val x_584 = x_583.asInstanceOf[scala.Long];
        bindingMut_28 = x_584;
        val x_585 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_585;
        val x_586 = resetData_0;
        val x_587 = x_586.asInstanceOf[scala.Boolean];
        bindingMut_27 = x_587;
        val x_588 = bindingMut_27;
        val x_589 = x_588.asInstanceOf[scala.Boolean];
        val x_590 = bindingMut_28;
        val x_591 = x_590.asInstanceOf[scala.Long];
        val x_592 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_591, x_589);
        resetData_0 = x_592;
        val x_593 = resetData_0;
        val x_594 = x_593.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_26 = x_594;
        val x_595 = bindingMut_26;
        val x_596 = x_595.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_597 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_596);
        resetData_0 = x_597;
        positionVar_164 = 50
      }
    else
      positionVar_164 = 57
  }));
  data_165.update(50, (() => {
    val x_598 = resetData_0;
    val x_599 = x_598.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    bindingMut_21 = x_599;
    val x_600 = this.knowledgeBase;
    resetData_0 = x_600;
    val x_601 = resetData_0;
    val x_602 = x_601.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_20 = x_602;
    val x_603 = bindingMut_20;
    val x_604 = x_603.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_605 = bindingMut_21;
    val x_606 = x_605.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_607 = x_604.know(x_606);
    resetData_0 = x_607;
    val x_608 = resetData_0;
    val x_609 = x_608.asInstanceOf[scala.Boolean];
    bindingMut_19 = x_609;
    positionVar_164 = 51
  }));
  data_165.update(51, (() => {
    val x_610 = bindingMut_19;
    val x_611 = x_610.asInstanceOf[scala.Boolean];
    if (x_611)
      {
        resetData_0 = true;
        positionVar_164 = 52
      }
    else
      positionVar_164 = 56
  }));
  data_165.update(52, (() => {
    val x_612 = resetData_0;
    val x_613 = x_612.asInstanceOf[scala.Boolean];
    bindingMut_15 = x_613;
    positionVar_164 = 53
  }));
  data_165.update(53, (() => {
    val x_614 = bindingMut_15;
    val x_615 = x_614.asInstanceOf[scala.Boolean];
    if (x_615)
      {
        this.`isForward_=`(true);
        resetData_0 = ();
        val x_616 = this.id;
        resetData_0 = x_616;
        val x_617 = resetData_0;
        val x_618 = x_617.asInstanceOf[scala.Long];
        bindingMut_14 = x_618;
        val x_619 = bindingMut_14;
        val x_620 = x_619.asInstanceOf[scala.Long];
        val x_621 = "Child ".+(x_620);
        resetData_0 = x_621;
        val x_622 = resetData_0;
        val x_623 = x_622.asInstanceOf[java.lang.String];
        bindingMut_13 = x_623;
        val x_624 = bindingMut_13;
        val x_625 = x_624.asInstanceOf[java.lang.String];
        val x_626 = x_625.+(" steps forward!");
        resetData_0 = x_626;
        val x_627 = resetData_0;
        val x_628 = x_627.asInstanceOf[java.lang.String];
        bindingMut_12 = x_628;
        val x_629 = bindingMut_12;
        val x_630 = x_629.asInstanceOf[java.lang.String];
        scala.Predef.println(x_630);
        resetData_0 = ();
        positionVar_164 = 54
      }
    else
      positionVar_164 = 55
  }));
  data_165.update(54, (() => positionVar_164 = 47));
  data_165.update(55, (() => {
    val x_631 = bindingMut_15;
    val x_632 = x_631.asInstanceOf[scala.Boolean];
    val x_633 = x_632.`unary_!`;
    if (x_633)
      {
        val x_634 = this.id;
        resetData_0 = x_634;
        val x_635 = resetData_0;
        val x_636 = x_635.asInstanceOf[scala.Long];
        bindingMut_11 = x_636;
        val x_637 = bindingMut_11;
        val x_638 = x_637.asInstanceOf[scala.Long];
        val x_639 = "Child ".+(x_638);
        resetData_0 = x_639;
        val x_640 = resetData_0;
        val x_641 = x_640.asInstanceOf[java.lang.String];
        bindingMut_10 = x_641;
        val x_642 = bindingMut_10;
        val x_643 = x_642.asInstanceOf[java.lang.String];
        val x_644 = x_643.+(" stays still!");
        resetData_0 = x_644;
        val x_645 = resetData_0;
        val x_646 = x_645.asInstanceOf[java.lang.String];
        bindingMut_9 = x_646;
        val x_647 = bindingMut_9;
        val x_648 = x_647.asInstanceOf[java.lang.String];
        scala.Predef.println(x_648);
        resetData_0 = ();
        positionVar_164 = 54
      }
    else
      ()
  }));
  data_165.update(56, (() => {
    val x_649 = bindingMut_19;
    val x_650 = x_649.asInstanceOf[scala.Boolean];
    val x_651 = x_650.`unary_!`;
    if (x_651)
      {
        val x_652 = this.knowledgeBase;
        resetData_0 = x_652;
        val x_653 = resetData_0;
        val x_654 = x_653.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        bindingMut_18 = x_654;
        val x_655 = this.id;
        resetData_0 = x_655;
        val x_656 = resetData_0;
        val x_657 = x_656.asInstanceOf[scala.Long];
        bindingMut_17 = x_657;
        val x_658 = bindingMut_17;
        val x_659 = x_658.asInstanceOf[scala.Long];
        val x_660 = bindingMut_21;
        val x_661 = x_660.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
        val x_662 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_659, x_661);
        resetData_0 = x_662;
        val x_663 = resetData_0;
        val x_664 = x_663.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        bindingMut_16 = x_664;
        val x_665 = bindingMut_16;
        val x_666 = x_665.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        val x_667 = bindingMut_18;
        val x_668 = x_667.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        val x_669 = x_668.know(x_666);
        resetData_0 = x_669;
        positionVar_164 = 52
      }
    else
      ()
  }));
  data_165.update(57, (() => {
    val x_670 = bindingMut_29;
    val x_671 = x_670.asInstanceOf[scala.Boolean];
    val x_672 = x_671.`unary_!`;
    if (x_672)
      {
        val x_673 = this.id;
        resetData_0 = x_673;
        val x_674 = resetData_0;
        val x_675 = x_674.asInstanceOf[scala.Long];
        bindingMut_25 = x_675;
        val x_676 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_676;
        val x_677 = resetData_0;
        val x_678 = x_677.asInstanceOf[scala.Boolean];
        bindingMut_24 = x_678;
        val x_679 = bindingMut_24;
        val x_680 = x_679.asInstanceOf[scala.Boolean];
        val x_681 = bindingMut_25;
        val x_682 = x_681.asInstanceOf[scala.Long];
        val x_683 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_682, x_680);
        resetData_0 = x_683;
        val x_684 = resetData_0;
        val x_685 = x_684.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_23 = x_685;
        val x_686 = bindingMut_23;
        val x_687 = x_686.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_688 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_687);
        resetData_0 = x_688;
        val x_689 = resetData_0;
        val x_690 = x_689.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_22 = x_690;
        val x_691 = bindingMut_22;
        val x_692 = x_691.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        val x_693 = lib.EpistemicLogic.Sentence.NotE(x_692);
        resetData_0 = x_693;
        positionVar_164 = 50
      }
    else
      ()
  }));
  data_165.update(58, (() => {
    val x_694 = listValMut_153;
    val x_695 = x_694.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_696 = x_695.methodId;
    val x_697 = x_696.==(3);
    if (x_697)
      positionVar_164 = 59
    else
      ();
    val x_698 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_699 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_698, 61);
    val x_700 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_699);
    resetData_1.prepend(x_700)
  }));
  data_165.update(59, (() => {
    val x_701 = this.id;
    resetData_0 = x_701;
    val x_702 = resetData_0;
    val x_703 = x_702.asInstanceOf[scala.Long];
    bindingMut_8 = x_703;
    val x_704 = this.isMuddy;
    resetData_0 = x_704;
    val x_705 = resetData_0;
    val x_706 = x_705.asInstanceOf[scala.Boolean];
    bindingMut_7 = x_706;
    val x_707 = this.isForward;
    resetData_0 = x_707;
    val x_708 = resetData_0;
    val x_709 = x_708.asInstanceOf[scala.Boolean];
    bindingMut_6 = x_709;
    val x_710 = this.epoch;
    resetData_0 = x_710;
    val x_711 = resetData_0;
    val x_712 = x_711.asInstanceOf[scala.Int];
    bindingMut_5 = x_712;
    val x_713 = bindingMut_5;
    val x_714 = x_713.asInstanceOf[scala.Int];
    val x_715 = bindingMut_6;
    val x_716 = x_715.asInstanceOf[scala.Boolean];
    val x_717 = bindingMut_7;
    val x_718 = x_717.asInstanceOf[scala.Boolean];
    val x_719 = bindingMut_8;
    val x_720 = x_719.asInstanceOf[scala.Long];
    val x_721 = example.epistemicLogicMC.MCHelper.ChildStatus.apply(x_720, x_718, x_716, x_714);
    resetData_0 = x_721;
    val x_722 = resetData_0;
    val x_723 = x_722.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    bindingMut_4 = x_723;
    val x_724 = bindingMut_4;
    val x_725 = x_724.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    val x_726 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildStatus](x_725);
    resetData_0 = x_726;
    val x_727 = resetData_1.remove(0);
    val x_731 = x_727.find(((x_728: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_729 = x_728._1;
      val x_730 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_729.==(x_730)
    }));
    val x_732 = x_731.get;
    val x_733 = x_732._2;
    positionVar_164 = x_733
  }));
  data_165.update(60, (() => {
    val x_734 = resetData_0;
    val x_735 = x_734.asInstanceOf[scala.Any];
    bindingMut_142 = x_735;
    val x_736 = bindingMut_142;
    val x_737 = x_736.asInstanceOf[scala.Any];
    val x_738 = listValMut_143;
    val x_739 = x_738.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_739.reply(this, x_737);
    resetData_0 = ();
    positionVar_164 = 18
  }));
  data_165.update(61, (() => {
    val x_740 = resetData_0;
    val x_741 = x_740.asInstanceOf[scala.Any];
    bindingMut_152 = x_741;
    val x_742 = bindingMut_152;
    val x_743 = x_742.asInstanceOf[scala.Any];
    val x_744 = listValMut_153;
    val x_745 = x_744.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_745.reply(this, x_743);
    resetData_0 = ();
    positionVar_164 = 30
  }));
  data_165.update(62, (() => {
    val x_746 = iterMut_154;
    val x_747 = x_746.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_748 = x_747.hasNext;
    val x_749 = x_748.`unary_!`;
    if (x_749)
      positionVar_164 = 63
    else
      ()
  }));
  data_165.update(63, (() => if (true)
    positionVar_164 = 4
  else
    positionVar_164 = 64));
  data_165.update(64, (() => {
    val x_750 = true.`unary_!`;
    if (x_750)
      {
        val x_751 = resetData_1.remove(0);
        val x_755 = x_751.find(((x_752: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_753 = x_752._1;
          val x_754 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_753.==(x_754)
        }));
        val x_756 = x_755.get;
        val x_757 = x_756._2;
        positionVar_164 = x_757
      }
    else
      ()
  }));
  data_165.update(65, (() => {
    val x_758 = resetData_0;
    val x_759 = x_758.asInstanceOf[scala.Any];
    bindingMut_152 = x_759;
    val x_760 = bindingMut_152;
    val x_761 = x_760.asInstanceOf[scala.Any];
    val x_762 = listValMut_153;
    val x_763 = x_762.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_763.reply(this, x_761);
    resetData_0 = ();
    positionVar_164 = 30
  }));
  data_165.update(66, (() => positionVar_164 = 67));
  data_165.update(67, (() => {
    positionVar_164 = 68;
    val x_764 = currentTurn;
    val x_765 = x_764.+(1);
    currentTurn = x_765
  }));
  data_165.update(68, (() => positionVar_164 = 67));
  data_165.update(69, (() => {
    val x_766 = resetData_0;
    val x_767 = x_766.asInstanceOf[scala.Any];
    bindingMut_142 = x_767;
    val x_768 = bindingMut_142;
    val x_769 = x_768.asInstanceOf[scala.Any];
    val x_770 = listValMut_143;
    val x_771 = x_770.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_771.reply(this, x_769);
    resetData_0 = ();
    positionVar_164 = 18
  }));
  data_165.update(70, (() => {
    x_3.remove(0);
    val x_772 = x_3.isEmpty;
    val x_773 = x_772.`unary_!`;
    if (x_773)
      {
        val x_774 = x_3(0);
        val x_775 = x_774.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_775
      }
    else
      ();
    positionVar_164 = 71
  }));
  data_165.update(71, (() => {
    val x_776 = iterMut_156;
    val x_777 = x_776.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_778 = x_777.hasNext;
    val x_779 = x_778.`unary_!`;
    if (x_779)
      {
        val x_780 = this.popRequestMessages;
        val x_781 = x_780.iterator;
        iterMut_154 = x_781;
        positionVar_164 = 31
      }
    else
      positionVar_164 = 72
  }));
  data_165.update(72, (() => {
    val x_782 = iterMut_156;
    val x_783 = x_782.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_784 = x_783.hasNext;
    if (x_784)
      {
        val x_785 = iterMut_156;
        val x_786 = x_785.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_787 = x_786.next();
        listValMut_155 = x_787;
        val x_788 = listValMut_155;
        val x_789 = x_788.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        x_3.prepend(x_789);
        val x_790 = listValMut_155;
        val x_791 = x_790.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_792 = x_791.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        methodArgsMut_52 = x_792;
        positionVar_164 = 22
      }
    else
      ();
    val x_793 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_794 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_793, 70);
    val x_795 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_794);
    resetData_1.prepend(x_795)
  }));
  data_165.update(73, (() => {
    val x_796 = bindingMut_91;
    val x_797 = x_796.asInstanceOf[scala.Boolean];
    if (x_797)
      {
        val x_798 = methodArgsMut_52;
        val x_799 = x_798.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_800 = x_799.proposition;
        resetData_0 = x_800;
        val x_801 = resetData_0;
        val x_802 = x_801.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_90 = x_802;
        val x_803 = bindingMut_90;
        val x_804 = x_803.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_805 = x_804.isForward;
        resetData_0 = x_805;
        val x_806 = resetData_0;
        val x_807 = x_806.asInstanceOf[scala.Boolean];
        bindingMut_89 = x_807;
        positionVar_164 = 74
      }
    else
      ()
  }));
  data_165.update(74, (() => {
    val x_808 = bindingMut_89;
    val x_809 = x_808.asInstanceOf[scala.Boolean];
    if (x_809)
      {
        val x_810 = methodArgsMut_52;
        val x_811 = x_810.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_812 = x_811.proposition;
        resetData_0 = x_812;
        val x_813 = resetData_0;
        val x_814 = x_813.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_88 = x_814;
        val x_815 = bindingMut_88;
        val x_816 = x_815.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_817 = x_816.isMuddy;
        resetData_0 = x_817;
        val x_818 = resetData_0;
        val x_819 = x_818.asInstanceOf[scala.Boolean];
        bindingMut_87 = x_819;
        positionVar_164 = 75
      }
    else
      positionVar_164 = 79
  }));
  data_165.update(75, (() => {
    val x_820 = bindingMut_87;
    val x_821 = x_820.asInstanceOf[scala.Boolean];
    if (x_821)
      {
        val x_822 = methodArgsMut_52;
        val x_823 = x_822.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_824 = x_823.proposition;
        resetData_0 = x_824;
        val x_825 = resetData_0;
        val x_826 = x_825.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_86 = x_826;
        val x_827 = bindingMut_86;
        val x_828 = x_827.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_829 = x_828.id;
        resetData_0 = x_829;
        val x_830 = resetData_0;
        val x_831 = x_830.asInstanceOf[scala.Long];
        bindingMut_85 = x_831;
        val x_832 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_832;
        val x_833 = resetData_0;
        val x_834 = x_833.asInstanceOf[scala.Boolean];
        bindingMut_84 = x_834;
        val x_835 = bindingMut_84;
        val x_836 = x_835.asInstanceOf[scala.Boolean];
        val x_837 = bindingMut_85;
        val x_838 = x_837.asInstanceOf[scala.Long];
        val x_839 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_838, x_836);
        resetData_0 = x_839;
        val x_840 = resetData_0;
        val x_841 = x_840.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_83 = x_841;
        val x_842 = bindingMut_83;
        val x_843 = x_842.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_844 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_843);
        resetData_0 = x_844;
        positionVar_164 = 76
      }
    else
      positionVar_164 = 78
  }));
  data_165.update(76, (() => {
    val x_845 = resetData_0;
    val x_846 = x_845.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    bindingMut_77 = x_846;
    val x_847 = this.knowledgeBase;
    resetData_0 = x_847;
    val x_848 = resetData_0;
    val x_849 = x_848.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_76 = x_849;
    val x_850 = this.epoch;
    resetData_0 = x_850;
    val x_851 = resetData_0;
    val x_852 = x_851.asInstanceOf[scala.Int];
    bindingMut_75 = x_852;
    val x_853 = methodArgsMut_52;
    val x_854 = x_853.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
    val x_855 = x_854.proposition;
    resetData_0 = x_855;
    val x_856 = resetData_0;
    val x_857 = x_856.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    bindingMut_74 = x_857;
    val x_858 = bindingMut_74;
    val x_859 = x_858.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    val x_860 = x_859.id;
    resetData_0 = x_860;
    val x_861 = resetData_0;
    val x_862 = x_861.asInstanceOf[scala.Long];
    bindingMut_73 = x_862;
    val x_863 = bindingMut_73;
    val x_864 = x_863.asInstanceOf[scala.Long];
    val x_865 = bindingMut_77;
    val x_866 = x_865.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_867 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_864, x_866);
    resetData_0 = x_867;
    val x_868 = resetData_0;
    val x_869 = x_868.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    bindingMut_72 = x_869;
    val x_870 = bindingMut_72;
    val x_871 = x_870.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    val x_872 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_871);
    resetData_0 = x_872;
    val x_873 = resetData_0;
    val x_874 = x_873.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_71 = x_874;
    val x_875 = bindingMut_71;
    val x_876 = x_875.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_877 = bindingMut_75;
    val x_878 = x_877.asInstanceOf[scala.Int];
    val x_879 = bindingMut_76;
    val x_880 = x_879.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    x_880.recordLearning(x_878, x_876);
    resetData_0 = ();
    val x_881 = this.knowledgeBase;
    resetData_0 = x_881;
    val x_882 = resetData_0;
    val x_883 = x_882.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_70 = x_883;
    val x_884 = methodArgsMut_52;
    val x_885 = x_884.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
    val x_886 = x_885.proposition;
    resetData_0 = x_886;
    val x_887 = resetData_0;
    val x_888 = x_887.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    bindingMut_69 = x_888;
    val x_889 = bindingMut_69;
    val x_890 = x_889.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
    val x_891 = x_890.id;
    resetData_0 = x_891;
    val x_892 = resetData_0;
    val x_893 = x_892.asInstanceOf[scala.Long];
    bindingMut_68 = x_893;
    val x_894 = bindingMut_77;
    val x_895 = x_894.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_896 = lib.EpistemicLogic.Sentence.NotE(x_895);
    resetData_0 = x_896;
    val x_897 = resetData_0;
    val x_898 = x_897.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
    bindingMut_67 = x_898;
    val x_899 = bindingMut_67;
    val x_900 = x_899.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
    val x_901 = bindingMut_68;
    val x_902 = x_901.asInstanceOf[scala.Long];
    val x_903 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_902, x_900);
    resetData_0 = x_903;
    val x_904 = resetData_0;
    val x_905 = x_904.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    bindingMut_66 = x_905;
    val x_906 = bindingMut_66;
    val x_907 = x_906.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    val x_908 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_907);
    resetData_0 = x_908;
    val x_909 = resetData_0;
    val x_910 = x_909.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_65 = x_910;
    val x_911 = bindingMut_65;
    val x_912 = x_911.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_913 = bindingMut_70;
    val x_914 = x_913.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_915 = x_914.speculate(x_912);
    resetData_0 = x_915;
    val x_916 = resetData_0;
    val x_917 = x_916.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_64 = x_917;
    val x_918 = bindingMut_64;
    val x_919 = x_918.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_920 = example.epistemicLogicMC.MCHelper.counterExampleLearning(x_919);
    resetData_0 = x_920;
    positionVar_164 = 77
  }));
  data_165.update(77, (() => {
    val x_921 = resetData_0;
    val x_922 = x_921.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_55 = x_922;
    val x_923 = this.knowledgeBase;
    resetData_0 = x_923;
    val x_924 = resetData_0;
    val x_925 = x_924.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_54 = x_925;
    val x_926 = this.epoch;
    resetData_0 = x_926;
    val x_927 = resetData_0;
    val x_928 = x_927.asInstanceOf[scala.Int];
    bindingMut_53 = x_928;
    val x_929 = bindingMut_53;
    val x_930 = x_929.asInstanceOf[scala.Int];
    val x_931 = bindingMut_54;
    val x_932 = x_931.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_933 = bindingMut_55;
    val x_934 = x_933.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    x_932.recordLearning(x_930, x_934);
    resetData_0 = ();
    val x_935 = resetData_1.remove(0);
    val x_939 = x_935.find(((x_936: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_937 = x_936._1;
      val x_938 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_937.==(x_938)
    }));
    val x_940 = x_939.get;
    val x_941 = x_940._2;
    positionVar_164 = x_941
  }));
  data_165.update(78, (() => {
    val x_942 = bindingMut_87;
    val x_943 = x_942.asInstanceOf[scala.Boolean];
    val x_944 = x_943.`unary_!`;
    if (x_944)
      {
        val x_945 = methodArgsMut_52;
        val x_946 = x_945.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_947 = x_946.proposition;
        resetData_0 = x_947;
        val x_948 = resetData_0;
        val x_949 = x_948.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_82 = x_949;
        val x_950 = bindingMut_82;
        val x_951 = x_950.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_952 = x_951.id;
        resetData_0 = x_952;
        val x_953 = resetData_0;
        val x_954 = x_953.asInstanceOf[scala.Long];
        bindingMut_81 = x_954;
        val x_955 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_955;
        val x_956 = resetData_0;
        val x_957 = x_956.asInstanceOf[scala.Boolean];
        bindingMut_80 = x_957;
        val x_958 = bindingMut_80;
        val x_959 = x_958.asInstanceOf[scala.Boolean];
        val x_960 = bindingMut_81;
        val x_961 = x_960.asInstanceOf[scala.Long];
        val x_962 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_961, x_959);
        resetData_0 = x_962;
        val x_963 = resetData_0;
        val x_964 = x_963.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_79 = x_964;
        val x_965 = bindingMut_79;
        val x_966 = x_965.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_967 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_966);
        resetData_0 = x_967;
        val x_968 = resetData_0;
        val x_969 = x_968.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_78 = x_969;
        val x_970 = bindingMut_78;
        val x_971 = x_970.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        val x_972 = lib.EpistemicLogic.Sentence.NotE(x_971);
        resetData_0 = x_972;
        positionVar_164 = 76
      }
    else
      ()
  }));
  data_165.update(79, (() => {
    val x_973 = bindingMut_89;
    val x_974 = x_973.asInstanceOf[scala.Boolean];
    val x_975 = x_974.`unary_!`;
    if (x_975)
      {
        val x_976 = methodArgsMut_52;
        val x_977 = x_976.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_978 = x_977.proposition;
        resetData_0 = x_978;
        val x_979 = resetData_0;
        val x_980 = x_979.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_63 = x_980;
        val x_981 = bindingMut_63;
        val x_982 = x_981.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_983 = x_982.id;
        resetData_0 = x_983;
        val x_984 = resetData_0;
        val x_985 = x_984.asInstanceOf[scala.Long];
        bindingMut_62 = x_985;
        val x_986 = this.id;
        resetData_0 = x_986;
        val x_987 = resetData_0;
        val x_988 = x_987.asInstanceOf[scala.Long];
        bindingMut_61 = x_988;
        val x_989 = this.neighborIds;
        resetData_0 = x_989;
        val x_990 = resetData_0;
        val x_991 = x_990.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        bindingMut_60 = x_991;
        val x_992 = bindingMut_60;
        val x_993 = x_992.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        val x_994 = x_993.toList;
        resetData_0 = x_994;
        val x_995 = resetData_0;
        val x_996 = x_995.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_59 = x_996;
        val x_997 = bindingMut_59;
        val x_998 = x_997.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_999 = bindingMut_61;
        val x_1000 = x_999.asInstanceOf[scala.Long];
        val x_1001 = x_998.::[scala.Long](x_1000);
        resetData_0 = x_1001;
        val x_1002 = resetData_0;
        val x_1003 = x_1002.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_58 = x_1003;
        val x_1004 = bindingMut_58;
        val x_1005 = x_1004.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_1011 = x_1005.filterNot(((x_1006: scala.Long) => {
          val x_1007 = methodArgsMut_52;
          val x_1008 = x_1007.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
          val x_1009 = x_1008.proposition;
          val x_1010 = x_1009.id;
          x_1006.==(x_1010)
        }));
        resetData_0 = x_1011;
        val x_1012 = resetData_0;
        val x_1013 = x_1012.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_57 = x_1013;
        val x_1014 = this.epoch;
        resetData_0 = x_1014;
        val x_1015 = resetData_0;
        val x_1016 = x_1015.asInstanceOf[scala.Int];
        bindingMut_56 = x_1016;
        val x_1017 = bindingMut_56;
        val x_1018 = x_1017.asInstanceOf[scala.Int];
        val x_1019 = bindingMut_57;
        val x_1020 = x_1019.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_1021 = bindingMut_62;
        val x_1022 = x_1021.asInstanceOf[scala.Long];
        val x_1023 = example.epistemicLogicMC.MCHelper.inferOtherAgent(x_1022, x_1020, x_1018);
        resetData_0 = x_1023;
        positionVar_164 = 77
      }
    else
      ()
  }));
  data_165.update(80, (() => {
    val x_1024 = bindingMut_95;
    val x_1025 = x_1024.asInstanceOf[scala.Boolean];
    if (x_1025)
      {
        val x_1026 = methodArgsMut_52;
        val x_1027 = x_1026.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_1028 = x_1027.proposition;
        resetData_0 = x_1028;
        val x_1029 = resetData_0;
        val x_1030 = x_1029.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_94 = x_1030;
        val x_1031 = bindingMut_94;
        val x_1032 = x_1031.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_1033 = x_1032.epoch;
        resetData_0 = x_1033;
        val x_1034 = resetData_0;
        val x_1035 = x_1034.asInstanceOf[scala.Int];
        bindingMut_93 = x_1035;
        val x_1036 = this.epoch;
        resetData_0 = x_1036;
        val x_1037 = resetData_0;
        val x_1038 = x_1037.asInstanceOf[scala.Int];
        bindingMut_92 = x_1038;
        val x_1039 = bindingMut_92;
        val x_1040 = x_1039.asInstanceOf[scala.Int];
        val x_1041 = bindingMut_93;
        val x_1042 = x_1041.asInstanceOf[scala.Int];
        val x_1043 = x_1042.==(x_1040);
        resetData_0 = x_1043;
        positionVar_164 = 26
      }
    else
      ()
  }));
  data_165.update(81, (() => {
    val x_1044 = bindingMut_97;
    val x_1045 = x_1044.asInstanceOf[scala.Boolean];
    if (x_1045)
      {
        val x_1046 = this.isForward;
        resetData_0 = x_1046;
        val x_1047 = resetData_0;
        val x_1048 = x_1047.asInstanceOf[scala.Boolean];
        bindingMut_96 = x_1048;
        val x_1049 = bindingMut_96;
        val x_1050 = x_1049.asInstanceOf[scala.Boolean];
        val x_1051 = x_1050.`unary_!`;
        resetData_0 = x_1051;
        positionVar_164 = 24
      }
    else
      ()
  }));
  data_165.update(82, (() => {
    val x_1052 = listValMut_143;
    val x_1053 = x_1052.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1054 = x_1053.methodId;
    val x_1055 = x_1054.==(4);
    if (x_1055)
      positionVar_164 = 41
    else
      ();
    val x_1056 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_1057 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1056, 43);
    val x_1058 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1057);
    resetData_1.prepend(x_1058)
  }));
  data_165.update(83, (() => {
    val x_1059 = listValMut_143;
    val x_1060 = x_1059.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_1061 = x_1060.methodId;
    val x_1062 = x_1061.==(3);
    if (x_1062)
      positionVar_164 = 59
    else
      ();
    val x_1063 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_1064 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_1063, 60);
    val x_1065 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_1064);
    resetData_1.prepend(x_1065)
  }));
  data_165.update(84, (() => {
    val x_1066 = iterMut_144;
    val x_1067 = x_1066.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_1068 = x_1067.hasNext;
    val x_1069 = x_1068.`unary_!`;
    if (x_1069)
      positionVar_164 = 85
    else
      ()
  }));
  data_165.update(85, (() => {
    val x_1070 = this.future_objs;
    val x_1071 = x_1070.toList;
    val x_1074 = x_1071.forall(((x_1072: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_1073 = x_1072.get;
      this.isCompleted(x_1073)
    }));
    val x_1075 = x_1074.`unary_!`;
    if (x_1075)
      positionVar_164 = 8
    else
      positionVar_164 = 86
  }));
  data_165.update(86, (() => {
    val x_1076 = this.future_objs;
    val x_1077 = x_1076.toList;
    val x_1080 = x_1077.forall(((x_1078: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_1079 = x_1078.get;
      this.isCompleted(x_1079)
    }));
    val x_1081 = x_1080.`unary_!`;
    val x_1082 = x_1081.`unary_!`;
    if (x_1082)
      positionVar_164 = 87
    else
      ()
  }));
  data_165.update(87, (() => {
    val x_1083 = this.knowledgeBase;
    resetData_0 = x_1083;
    val x_1084 = resetData_0;
    val x_1085 = x_1084.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_141 = x_1085;
    val x_1086 = example.epistemicLogicMC.MCHelper.seeAllNeighbor();
    resetData_0 = x_1086;
    val x_1087 = resetData_0;
    val x_1088 = x_1087.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    bindingMut_140 = x_1088;
    val x_1089 = bindingMut_140;
    val x_1090 = x_1089.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_1091 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_1090);
    resetData_0 = x_1091;
    val x_1092 = resetData_0;
    val x_1093 = x_1092.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_139 = x_1093;
    val x_1094 = bindingMut_139;
    val x_1095 = x_1094.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_1096 = bindingMut_141;
    val x_1097 = x_1096.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    x_1097.remember(x_1095);
    resetData_0 = ();
    val x_1098 = new scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]();
    resetData_0 = x_1098;
    val x_1099 = resetData_0;
    val x_1100 = x_1099.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    bindingMut_138 = x_1100;
    val x_1101 = this.future_objs;
    resetData_0 = x_1101;
    val x_1102 = resetData_0;
    val x_1103 = x_1102.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    bindingMut_137 = x_1103;
    val x_1104 = bindingMut_137;
    val x_1105 = x_1104.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1106 = x_1105.toList;
    resetData_0 = x_1106;
    val x_1107 = resetData_0;
    val x_1108 = x_1107.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    bindingMut_136 = x_1108;
    val x_1109 = bindingMut_136;
    val x_1110 = x_1109.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1111 = x_1110.iterator;
    iterMut_135 = x_1111;
    positionVar_164 = 88
  }));
  data_165.update(88, (() => {
    val x_1112 = iterMut_135;
    val x_1113 = x_1112.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1114 = x_1113.hasNext;
    if (x_1114)
      {
        val x_1115 = iterMut_135;
        val x_1116 = x_1115.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_1117 = x_1116.next();
        listValMut_134 = x_1117;
        val x_1118 = listValMut_134;
        val x_1119 = x_1118.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_1120 = x_1119.get;
        resetData_0 = x_1120;
        val x_1121 = resetData_0;
        val x_1122 = x_1121.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_133 = x_1122;
        val x_1123 = bindingMut_133;
        val x_1124 = x_1123.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1125 = this.getFutureValue[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]](x_1124);
        resetData_0 = x_1125;
        val x_1126 = resetData_0;
        val x_1127 = x_1126.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        bindingMut_132 = x_1127;
        val x_1128 = bindingMut_132;
        val x_1129 = x_1128.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_1130 = bindingMut_138;
        val x_1131 = x_1130.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        x_1131.append(x_1129);
        resetData_0 = ();
        positionVar_164 = 88
      }
    else
      positionVar_164 = 89
  }));
  data_165.update(89, (() => {
    val x_1132 = iterMut_135;
    val x_1133 = x_1132.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1134 = x_1133.hasNext;
    val x_1135 = x_1134.`unary_!`;
    if (x_1135)
      {
        val x_1136 = this.future_objs;
        resetData_0 = x_1136;
        val x_1137 = resetData_0;
        val x_1138 = x_1137.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_131 = x_1138;
        val x_1139 = bindingMut_131;
        val x_1140 = x_1139.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_1141 = x_1140.toList;
        resetData_0 = x_1141;
        val x_1142 = resetData_0;
        val x_1143 = x_1142.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_130 = x_1143;
        val x_1144 = bindingMut_130;
        val x_1145 = x_1144.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_1146 = x_1145.iterator;
        iterMut_129 = x_1146;
        positionVar_164 = 90
      }
    else
      ()
  }));
  data_165.update(90, (() => {
    val x_1147 = iterMut_129;
    val x_1148 = x_1147.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1149 = x_1148.hasNext;
    if (x_1149)
      {
        val x_1150 = iterMut_129;
        val x_1151 = x_1150.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_1152 = x_1151.next();
        listValMut_128 = x_1152;
        val x_1153 = listValMut_128;
        val x_1154 = x_1153.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_1155 = x_1154.get;
        resetData_0 = x_1155;
        val x_1156 = resetData_0;
        val x_1157 = x_1156.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_127 = x_1157;
        val x_1158 = bindingMut_127;
        val x_1159 = x_1158.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1160 = this.clearFutureObj(x_1159);
        resetData_0 = x_1160;
        positionVar_164 = 90
      }
    else
      positionVar_164 = 91
  }));
  data_165.update(91, (() => {
    val x_1161 = iterMut_129;
    val x_1162 = x_1161.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_1163 = x_1162.hasNext;
    val x_1164 = x_1163.`unary_!`;
    if (x_1164)
      {
        val x_1165 = this.future_objs;
        resetData_0 = x_1165;
        val x_1166 = resetData_0;
        val x_1167 = x_1166.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_126 = x_1167;
        val x_1168 = bindingMut_126;
        val x_1169 = x_1168.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        x_1169.clear();
        resetData_0 = ();
        val x_1170 = bindingMut_138;
        val x_1171 = x_1170.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1172 = x_1171.size;
        resetData_0 = x_1172;
        val x_1173 = resetData_0;
        val x_1174 = x_1173.asInstanceOf[scala.Int];
        bindingMut_125 = x_1174;
        val x_1175 = this.neighbors;
        resetData_0 = x_1175;
        val x_1176 = resetData_0;
        val x_1177 = x_1176.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
        bindingMut_124 = x_1177;
        val x_1178 = bindingMut_124;
        val x_1179 = x_1178.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
        val x_1180 = x_1179.size;
        resetData_0 = x_1180;
        val x_1181 = resetData_0;
        val x_1182 = x_1181.asInstanceOf[scala.Int];
        bindingMut_123 = x_1182;
        val x_1183 = bindingMut_123;
        val x_1184 = x_1183.asInstanceOf[scala.Int];
        val x_1185 = bindingMut_125;
        val x_1186 = x_1185.asInstanceOf[scala.Int];
        val x_1187 = x_1186.==(x_1184);
        resetData_0 = x_1187;
        val x_1188 = resetData_0;
        val x_1189 = x_1188.asInstanceOf[scala.Boolean];
        bindingMut_122 = x_1189;
        val x_1190 = bindingMut_122;
        val x_1191 = x_1190.asInstanceOf[scala.Boolean];
        scala.Predef.assert(x_1191);
        resetData_0 = ();
        val x_1192 = bindingMut_138;
        val x_1193 = x_1192.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1194 = x_1193.toList;
        resetData_0 = x_1194;
        val x_1195 = resetData_0;
        val x_1196 = x_1195.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_121 = x_1196;
        val x_1197 = bindingMut_121;
        val x_1198 = x_1197.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1199 = x_1198.iterator;
        iterMut_120 = x_1199;
        positionVar_164 = 92
      }
    else
      ()
  }));
  data_165.update(92, (() => {
    val x_1200 = iterMut_120;
    val x_1201 = x_1200.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_1202 = x_1201.hasNext;
    val x_1203 = x_1202.`unary_!`;
    if (x_1203)
      {
        val x_1204 = bindingMut_138;
        val x_1205 = x_1204.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1206 = x_1205.toList;
        resetData_0 = x_1206;
        val x_1207 = resetData_1.remove(0);
        val x_1211 = x_1207.find(((x_1208: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1209 = x_1208._1;
          val x_1210 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_1209.==(x_1210)
        }));
        val x_1212 = x_1211.get;
        val x_1213 = x_1212._2;
        positionVar_164 = x_1213
      }
    else
      positionVar_164 = 96
  }));
  data_165.update(93, (() => {
    val x_1214 = resetData_0;
    val x_1215 = x_1214.asInstanceOf[scala.Any];
    bindingMut_142 = x_1215;
    val x_1216 = bindingMut_142;
    val x_1217 = x_1216.asInstanceOf[scala.Any];
    val x_1218 = listValMut_143;
    val x_1219 = x_1218.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1219.reply(this, x_1217);
    resetData_0 = ();
    positionVar_164 = 18
  }));
  data_165.update(94, (() => {
    val x_1220 = resetData_0;
    val x_1221 = x_1220.asInstanceOf[scala.Any];
    bindingMut_152 = x_1221;
    val x_1222 = bindingMut_152;
    val x_1223 = x_1222.asInstanceOf[scala.Any];
    val x_1224 = listValMut_153;
    val x_1225 = x_1224.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_1225.reply(this, x_1223);
    resetData_0 = ();
    positionVar_164 = 30
  }));
  data_165.update(95, (() => {
    val x_1226 = resetData_0;
    val x_1227 = x_1226.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    bindingMut_157 = x_1227;
    val x_1228 = bindingMut_157;
    val x_1229 = x_1228.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_1230 = x_1229.iterator;
    iterMut_156 = x_1230;
    positionVar_164 = 71
  }));
  data_165.update(96, (() => {
    val x_1231 = iterMut_120;
    val x_1232 = x_1231.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_1233 = x_1232.hasNext;
    if (x_1233)
      {
        val x_1234 = iterMut_120;
        val x_1235 = x_1234.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_1236 = x_1235.next();
        listValMut_119 = x_1236;
        val x_1237 = listValMut_119;
        val x_1238 = x_1237.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_1239 = x_1238.proposition;
        resetData_0 = x_1239;
        val x_1240 = resetData_0;
        val x_1241 = x_1240.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_118 = x_1241;
        val x_1242 = bindingMut_118;
        val x_1243 = x_1242.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_1244 = x_1243.isMuddy;
        resetData_0 = x_1244;
        val x_1245 = resetData_0;
        val x_1246 = x_1245.asInstanceOf[scala.Boolean];
        bindingMut_117 = x_1246;
        positionVar_164 = 97
      }
    else
      ()
  }));
  data_165.update(97, (() => {
    val x_1247 = bindingMut_117;
    val x_1248 = x_1247.asInstanceOf[scala.Boolean];
    if (x_1248)
      {
        val x_1249 = listValMut_119;
        val x_1250 = x_1249.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_1251 = x_1250.proposition;
        resetData_0 = x_1251;
        val x_1252 = resetData_0;
        val x_1253 = x_1252.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_116 = x_1253;
        val x_1254 = bindingMut_116;
        val x_1255 = x_1254.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_1256 = x_1255.id;
        resetData_0 = x_1256;
        val x_1257 = resetData_0;
        val x_1258 = x_1257.asInstanceOf[scala.Long];
        bindingMut_115 = x_1258;
        val x_1259 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_1259;
        val x_1260 = resetData_0;
        val x_1261 = x_1260.asInstanceOf[scala.Boolean];
        bindingMut_114 = x_1261;
        val x_1262 = bindingMut_114;
        val x_1263 = x_1262.asInstanceOf[scala.Boolean];
        val x_1264 = bindingMut_115;
        val x_1265 = x_1264.asInstanceOf[scala.Long];
        val x_1266 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_1265, x_1263);
        resetData_0 = x_1266;
        val x_1267 = resetData_0;
        val x_1268 = x_1267.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_113 = x_1268;
        val x_1269 = bindingMut_113;
        val x_1270 = x_1269.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_1271 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_1270);
        resetData_0 = x_1271;
        positionVar_164 = 98
      }
    else
      positionVar_164 = 99
  }));
  data_165.update(98, (() => {
    val x_1272 = resetData_0;
    val x_1273 = x_1272.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    bindingMut_107 = x_1273;
    val x_1274 = this.knowledgeBase;
    resetData_0 = x_1274;
    val x_1275 = resetData_0;
    val x_1276 = x_1275.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_106 = x_1276;
    val x_1277 = this.epoch;
    resetData_0 = x_1277;
    val x_1278 = resetData_0;
    val x_1279 = x_1278.asInstanceOf[scala.Int];
    bindingMut_105 = x_1279;
    val x_1280 = this.id;
    resetData_0 = x_1280;
    val x_1281 = resetData_0;
    val x_1282 = x_1281.asInstanceOf[scala.Long];
    bindingMut_104 = x_1282;
    val x_1283 = bindingMut_104;
    val x_1284 = x_1283.asInstanceOf[scala.Long];
    val x_1285 = bindingMut_107;
    val x_1286 = x_1285.asInstanceOf[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_1287 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_1284, x_1286);
    resetData_0 = x_1287;
    val x_1288 = resetData_0;
    val x_1289 = x_1288.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    bindingMut_103 = x_1289;
    val x_1290 = bindingMut_103;
    val x_1291 = x_1290.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
    val x_1292 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_1291);
    resetData_0 = x_1292;
    val x_1293 = resetData_0;
    val x_1294 = x_1293.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_102 = x_1294;
    val x_1295 = bindingMut_102;
    val x_1296 = x_1295.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_1297 = bindingMut_105;
    val x_1298 = x_1297.asInstanceOf[scala.Int];
    val x_1299 = bindingMut_106;
    val x_1300 = x_1299.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    x_1300.recordLearning(x_1298, x_1296);
    resetData_0 = ();
    positionVar_164 = 92
  }));
  data_165.update(99, (() => {
    val x_1301 = bindingMut_117;
    val x_1302 = x_1301.asInstanceOf[scala.Boolean];
    val x_1303 = x_1302.`unary_!`;
    if (x_1303)
      {
        val x_1304 = listValMut_119;
        val x_1305 = x_1304.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_1306 = x_1305.proposition;
        resetData_0 = x_1306;
        val x_1307 = resetData_0;
        val x_1308 = x_1307.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_112 = x_1308;
        val x_1309 = bindingMut_112;
        val x_1310 = x_1309.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_1311 = x_1310.id;
        resetData_0 = x_1311;
        val x_1312 = resetData_0;
        val x_1313 = x_1312.asInstanceOf[scala.Long];
        bindingMut_111 = x_1313;
        val x_1314 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_1314;
        val x_1315 = resetData_0;
        val x_1316 = x_1315.asInstanceOf[scala.Boolean];
        bindingMut_110 = x_1316;
        val x_1317 = bindingMut_110;
        val x_1318 = x_1317.asInstanceOf[scala.Boolean];
        val x_1319 = bindingMut_111;
        val x_1320 = x_1319.asInstanceOf[scala.Long];
        val x_1321 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_1320, x_1318);
        resetData_0 = x_1321;
        val x_1322 = resetData_0;
        val x_1323 = x_1322.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_109 = x_1323;
        val x_1324 = bindingMut_109;
        val x_1325 = x_1324.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_1326 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_1325);
        resetData_0 = x_1326;
        val x_1327 = resetData_0;
        val x_1328 = x_1327.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_108 = x_1328;
        val x_1329 = bindingMut_108;
        val x_1330 = x_1329.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        val x_1331 = lib.EpistemicLogic.Sentence.NotE(x_1330);
        resetData_0 = x_1331;
        positionVar_164 = 98
      }
    else
      ()
  }));
  data_165.update(100, (() => {
    val x_1332 = this.future_objs;
    val x_1333 = x_1332.toList;
    val x_1336 = x_1333.forall(((x_1334: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_1335 = x_1334.get;
      this.isCompleted(x_1335)
    }));
    val x_1337 = x_1336.`unary_!`;
    val x_1338 = x_1337.`unary_!`;
    if (x_1338)
      positionVar_164 = 87
    else
      ()
  }));
  data_165.update(101, (() => {
    val x_1339 = iterMut_150;
    val x_1340 = x_1339.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_1341 = x_1340.hasNext;
    if (x_1341)
      {
        val x_1342 = iterMut_150;
        val x_1343 = x_1342.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
        val x_1344 = x_1343.next();
        listValMut_149 = x_1344;
        val x_1345 = this.future_objs;
        resetData_0 = x_1345;
        val x_1346 = resetData_0;
        val x_1347 = x_1346.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_148 = x_1347;
        val x_1348 = ((this): meta.deep.runtime.Actor).id;
        val x_1350 = {
          val x_1349 = listValMut_149;
          x_1349.asInstanceOf[generated.example.epistemicLogicMC.Child]
        };
        val x_1351 = x_1350.id;
        val x_1352 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_1353 = meta.deep.runtime.RequestMessage.apply(x_1348, x_1351, 3, x_1352);
        val x_1354 = x_1353.future;
        val x_1355 = x_1354.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        x_1353.`future_=`(x_1355);
        ((this): meta.deep.runtime.Actor).sendMessage(x_1353);
        val x_1356 = x_1353.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_1356, ((response_1357: meta.deep.runtime.Message) => {
          val x_1358 = x_1353.future;
          val x_1359 = response_1357.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_1360 = x_1359.arg;
          val x_1361 = x_1358.setValue[scala.Any](x_1360);
          val x_1362 = x_1361.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
          x_1353.`future_=`(x_1362);
          val x_1363 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_1364 = x_1353.future;
          val x_1365 = x_1364.id;
          val x_1366 = scala.Predef.ArrowAssoc[java.lang.String](x_1365);
          val x_1367 = x_1353.future;
          val x_1368 = x_1366.->[meta.deep.runtime.Future[scala.Any]](x_1367);
          val x_1369 = x_1363.+[meta.deep.runtime.Future[scala.Any]](x_1368);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_1369)
        }));
        val x_1370 = x_1353.future;
        val x_1371 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_1370);
        resetData_0 = x_1371;
        val x_1372 = resetData_0;
        val x_1373 = x_1372.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        bindingMut_147 = x_1373;
        val x_1374 = bindingMut_147;
        val x_1375 = x_1374.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_1376 = bindingMut_148;
        val x_1377 = x_1376.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        x_1377.append(x_1375);
        resetData_0 = ();
        positionVar_164 = 6
      }
    else
      ()
  }));
  data_165.update(102, (() => {
    val x_1378 = true.`unary_!`;
    if (x_1378)
      {
        val x_1379 = resetData_1.remove(0);
        val x_1383 = x_1379.find(((x_1380: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_1381 = x_1380._1;
          val x_1382 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_1381.==(x_1382)
        }));
        val x_1384 = x_1383.get;
        val x_1385 = x_1384._2;
        positionVar_164 = x_1385
      }
    else
      ()
  }));
  data_165.update(103, (() => {
    val x_1386 = iterMut_161;
    val x_1387 = x_1386.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_1388 = x_1387.hasNext;
    if (x_1388)
      {
        val x_1389 = iterMut_161;
        val x_1390 = x_1389.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
        val x_1391 = x_1390.next();
        listValMut_160 = x_1391;
        val x_1392 = this.neighborIds;
        resetData_0 = x_1392;
        val x_1393 = resetData_0;
        val x_1394 = x_1393.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        bindingMut_159 = x_1394;
        val x_1395 = listValMut_160;
        val x_1396 = x_1395.asInstanceOf[generated.example.epistemicLogicMC.Child];
        val x_1397 = x_1396.id;
        resetData_0 = x_1397;
        val x_1398 = resetData_0;
        val x_1399 = x_1398.asInstanceOf[scala.Long];
        bindingMut_158 = x_1399;
        val x_1400 = bindingMut_158;
        val x_1401 = x_1400.asInstanceOf[scala.Long];
        val x_1402 = bindingMut_159;
        val x_1403 = x_1402.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Long]];
        x_1403.append(x_1401);
        resetData_0 = ();
        positionVar_164 = 2
      }
    else
      ()
  }));
  data_165
}).apply();
  
  override def run_until(until_1405: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_1406 = currentTurn;
      val x_1407 = x_1406.<=(until_1405);
      x_1407.&&({
        val x_1408 = positionVar_164;
        val x_1409 = commands_1404.length;
        x_1408.<(x_1409)
      })
    }) 
      {
        val x_1410 = positionVar_164;
        val x_1411 = commands_1404.apply(x_1410);
        x_1411.apply()
      }
    ;
    this
  }
}
