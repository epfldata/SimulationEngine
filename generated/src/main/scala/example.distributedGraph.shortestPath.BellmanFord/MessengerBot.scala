package generated.example.distributedGraph.shortestPath.BellmanFord

class MessengerBot () extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_5: scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]] = null;
  private var bindingMut_6: meta.runtime.Future[scala.Any] = null;
  private var listValMut_7: scala.Option[meta.runtime.Future[scala.Any]] = null;
  @transient private var iterMut_8: scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]] = null;
  private var bindingMut_9: scala.Double = 0.0;
  private var methodArgsMut_10: scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]] = null;
  private var bindingMut_11: meta.runtime.Future[scala.Any] = null;
  private var listValMut_12: scala.Option[meta.runtime.Future[scala.Any]] = null;
  @transient private var iterMut_13: scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]] = null;
  private var bindingMut_14: scala.collection.immutable.List[scala.Any] = null;
  private var bindingMut_15: scala.collection.immutable.List[scala.Any] = null;
  private var bindingMut_16: scala.Any = null;
  private var bindingMut_17: meta.runtime.Future[scala.Any] = null;
  private var listValMut_18: scala.Option[meta.runtime.Future[scala.Any]] = null;
  @transient private var iterMut_19: scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]] = null;
  private var bindingMut_20: scala.collection.immutable.List[scala.Any] = null;
  @transient private var bindingMut_21: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Any, scala.collection.immutable.List[scala.Any]] = null;
  private var bindingMut_22: scala.Double = 0.0;
  private var bindingMut_23: scala.Double = 0.0;
  private var bindingMut_24: scala.Any = null;
  private var listValMut_25: meta.runtime.RequestMessage = null;
  @transient private var iterMut_26: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_28: scala.Int = 0;
  
  val commands_352 = (() => {
  val data_29 = new scala.Array[scala.Function0[scala.Unit]](46);
  data_29.update(0, (() => positionVar_28 = 1));
  data_29.update(1, (() => {
    val x_30 = this.deleted;
    val x_31 = x_30.`unary_!`;
    if (x_31)
      positionVar_28 = 2
    else
      positionVar_28 = 45
  }));
  data_29.update(2, (() => {
    val x_32 = this.popRequestMessages;
    val x_33 = x_32.iterator;
    iterMut_26 = x_33;
    positionVar_28 = 3
  }));
  data_29.update(3, (() => {
    val x_34 = iterMut_26;
    val x_35 = x_34.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_36 = x_35.hasNext;
    if (x_36)
      {
        val x_37 = iterMut_26;
        val x_38 = x_37.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_39 = x_38.next();
        listValMut_25 = x_39;
        positionVar_28 = 4
      }
    else
      positionVar_28 = 35
  }));
  data_29.update(4, (() => {
    val x_40 = listValMut_25;
    val x_41 = x_40.asInstanceOf[meta.runtime.RequestMessage];
    val x_42 = x_41.methodId;
    val x_43 = x_42.==(7);
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_28 = 5
    else
      positionVar_28 = 20
  }));
  data_29.update(5, (() => {
    val x_45 = listValMut_25;
    val x_46 = x_45.asInstanceOf[meta.runtime.RequestMessage];
    val x_47 = x_46.methodId;
    val x_48 = x_47.==(6);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      {
        val x_50 = listValMut_25;
        val x_51 = x_50.asInstanceOf[meta.runtime.RequestMessage];
        val x_52 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_51);
        val x_53 = this.addReceiveMessages(x_52);
        resetData_0 = x_53;
        positionVar_28 = 6
      }
    else
      positionVar_28 = 7
  }));
  data_29.update(6, (() => positionVar_28 = 3));
  data_29.update(7, (() => {
    val x_54 = listValMut_25;
    val x_55 = x_54.asInstanceOf[meta.runtime.RequestMessage];
    val x_56 = x_55.methodId;
    val x_57 = x_56.==(6);
    if (x_57)
      {
        val x_58 = listValMut_25;
        val x_59 = x_58.asInstanceOf[meta.runtime.RequestMessage];
        val x_60 = x_59.argss;
        val x_61 = x_60(0);
        val x_62 = x_61(0);
        x_3.prepend(x_62);
        val x_63 = listValMut_25;
        val x_64 = x_63.asInstanceOf[meta.runtime.RequestMessage];
        val x_65 = x_64.argss;
        val x_66 = x_65(0);
        val x_67 = x_66(0);
        val x_68 = x_67.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
        methodArgsMut_5 = x_68;
        positionVar_28 = 8
      }
    else
      ()
  }));
  data_29.update(8, (() => {
    val x_69 = methodArgsMut_5;
    val x_70 = x_69.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_71 = x_70.nonEmpty;
    val x_76 = x_71.&&({
      val x_72 = methodArgsMut_5;
      val x_73 = x_72.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_73.forall(((x_74: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_75 = x_74.get;
        lib.`package`.SimRuntime.isCompleted(x_75)
      }))
    });
    val x_77 = x_76.`unary_!`;
    if (x_77)
      positionVar_28 = 9
    else
      positionVar_28 = 19
  }));
  data_29.update(9, (() => {
    resetData_0 = 0.0;
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[scala.Double];
    bindingMut_9 = x_79;
    positionVar_28 = 10
  }));
  data_29.update(10, (() => {
    val x_80 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_81 = meta.runtime.SimRuntime.labelVals(x_80);
    val x_82 = bindingMut_9;
    val x_83 = x_82.asInstanceOf[scala.Double];
    val x_84 = 1.0.-(x_83);
    x_81.append(x_84);
    resetData_0 = ();
    positionVar_28 = 11;
    val x_85 = currentTurn;
    val x_86 = x_85.+(1);
    currentTurn = x_86
  }));
  data_29.update(11, (() => {
    val x_87 = meta.runtime.SimRuntime.proceedLabel;
    val x_88 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_89 = x_87(x_88);
    val x_90 = bindingMut_9;
    val x_91 = x_90.asInstanceOf[scala.Double];
    val x_92 = x_91.+(x_89);
    resetData_0 = x_92;
    val x_93 = resetData_0;
    val x_94 = x_93.asInstanceOf[scala.Double];
    bindingMut_9 = x_94;
    positionVar_28 = 12
  }));
  data_29.update(12, (() => {
    val x_95 = bindingMut_9;
    val x_96 = x_95.asInstanceOf[scala.Double];
    val x_97 = x_96.<(1.0);
    if (x_97)
      positionVar_28 = 10
    else
      positionVar_28 = 13
  }));
  data_29.update(13, (() => {
    val x_98 = bindingMut_9;
    val x_99 = x_98.asInstanceOf[scala.Double];
    val x_100 = x_99.<(1.0);
    val x_101 = x_100.`unary_!`;
    if (x_101)
      positionVar_28 = 14
    else
      ()
  }));
  data_29.update(14, (() => {
    val x_102 = methodArgsMut_5;
    val x_103 = x_102.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_104 = x_103.nonEmpty;
    val x_109 = x_104.&&({
      val x_105 = methodArgsMut_5;
      val x_106 = x_105.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_106.forall(((x_107: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_108 = x_107.get;
        lib.`package`.SimRuntime.isCompleted(x_108)
      }))
    });
    val x_110 = x_109.`unary_!`;
    if (x_110)
      positionVar_28 = 9
    else
      positionVar_28 = 15
  }));
  data_29.update(15, (() => {
    val x_111 = methodArgsMut_5;
    val x_112 = x_111.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_113 = x_112.nonEmpty;
    val x_118 = x_113.&&({
      val x_114 = methodArgsMut_5;
      val x_115 = x_114.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_115.forall(((x_116: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_117 = x_116.get;
        lib.`package`.SimRuntime.isCompleted(x_117)
      }))
    });
    val x_119 = x_118.`unary_!`;
    val x_120 = x_119.`unary_!`;
    if (x_120)
      positionVar_28 = 16
    else
      ()
  }));
  data_29.update(16, (() => {
    val x_121 = methodArgsMut_5;
    val x_122 = x_121.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_123 = x_122.iterator;
    iterMut_8 = x_123;
    positionVar_28 = 17
  }));
  data_29.update(17, (() => {
    val x_124 = iterMut_8;
    val x_125 = x_124.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_126 = x_125.hasNext;
    if (x_126)
      {
        val x_127 = iterMut_8;
        val x_128 = x_127.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
        val x_129 = x_128.next();
        listValMut_7 = x_129;
        val x_130 = listValMut_7;
        val x_131 = x_130.asInstanceOf[scala.Option[meta.runtime.Future[scala.Any]]];
        val x_132 = x_131.get;
        resetData_0 = x_132;
        val x_133 = resetData_0;
        val x_134 = x_133.asInstanceOf[meta.runtime.Future[scala.Any]];
        bindingMut_6 = x_134;
        val x_135 = bindingMut_6;
        val x_136 = x_135.asInstanceOf[meta.runtime.Future[scala.Any]];
        val x_137 = lib.`package`.SimRuntime.clearFutureObj(x_136);
        resetData_0 = x_137;
        positionVar_28 = 17
      }
    else
      positionVar_28 = 18
  }));
  data_29.update(18, (() => {
    val x_138 = iterMut_8;
    val x_139 = x_138.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_140 = x_139.hasNext;
    val x_141 = x_140.`unary_!`;
    if (x_141)
      {
        this.`deleted_=`(true);
        resetData_0 = ();
        x_3.remove(0);
        val x_142 = x_3.isEmpty;
        val x_143 = x_142.`unary_!`;
        if (x_143)
          {
            val x_144 = x_3(0);
            val x_145 = x_144.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
            methodArgsMut_5 = x_145
          }
        else
          ();
        val x_146 = resetData_0;
        val x_147 = x_146.asInstanceOf[scala.Any];
        bindingMut_24 = x_147;
        val x_148 = bindingMut_24;
        val x_149 = x_148.asInstanceOf[scala.Any];
        val x_150 = listValMut_25;
        val x_151 = x_150.asInstanceOf[meta.runtime.RequestMessage];
        x_151.reply(this, x_149);
        resetData_0 = ();
        positionVar_28 = 6
      }
    else
      ()
  }));
  data_29.update(19, (() => {
    val x_152 = methodArgsMut_5;
    val x_153 = x_152.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_154 = x_153.nonEmpty;
    val x_159 = x_154.&&({
      val x_155 = methodArgsMut_5;
      val x_156 = x_155.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_156.forall(((x_157: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_158 = x_157.get;
        lib.`package`.SimRuntime.isCompleted(x_158)
      }))
    });
    val x_160 = x_159.`unary_!`;
    val x_161 = x_160.`unary_!`;
    if (x_161)
      positionVar_28 = 16
    else
      ()
  }));
  data_29.update(20, (() => {
    val x_162 = listValMut_25;
    val x_163 = x_162.asInstanceOf[meta.runtime.RequestMessage];
    val x_164 = x_163.methodId;
    val x_165 = x_164.==(7);
    if (x_165)
      {
        val x_166 = listValMut_25;
        val x_167 = x_166.asInstanceOf[meta.runtime.RequestMessage];
        val x_168 = x_167.argss;
        val x_169 = x_168(0);
        val x_170 = x_169(0);
        x_4.prepend(x_170);
        val x_171 = listValMut_25;
        val x_172 = x_171.asInstanceOf[meta.runtime.RequestMessage];
        val x_173 = x_172.argss;
        val x_174 = x_173(0);
        val x_175 = x_174(0);
        val x_176 = x_175.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
        methodArgsMut_10 = x_176;
        positionVar_28 = 21
      }
    else
      ()
  }));
  data_29.update(21, (() => {
    val x_177 = methodArgsMut_10;
    val x_178 = x_177.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_179 = x_178.nonEmpty;
    val x_184 = x_179.&&({
      val x_180 = methodArgsMut_10;
      val x_181 = x_180.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_181.forall(((x_182: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_183 = x_182.get;
        lib.`package`.SimRuntime.isCompleted(x_183)
      }))
    });
    val x_185 = x_184.`unary_!`;
    if (x_185)
      positionVar_28 = 22
    else
      positionVar_28 = 34
  }));
  data_29.update(22, (() => {
    resetData_0 = 0.0;
    val x_186 = resetData_0;
    val x_187 = x_186.asInstanceOf[scala.Double];
    bindingMut_22 = x_187;
    positionVar_28 = 23
  }));
  data_29.update(23, (() => {
    val x_188 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_189 = meta.runtime.SimRuntime.labelVals(x_188);
    val x_190 = bindingMut_22;
    val x_191 = x_190.asInstanceOf[scala.Double];
    val x_192 = 1.0.-(x_191);
    x_189.append(x_192);
    resetData_0 = ();
    positionVar_28 = 24;
    val x_193 = currentTurn;
    val x_194 = x_193.+(1);
    currentTurn = x_194
  }));
  data_29.update(24, (() => {
    val x_195 = meta.runtime.SimRuntime.proceedLabel;
    val x_196 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_197 = x_195(x_196);
    val x_198 = bindingMut_22;
    val x_199 = x_198.asInstanceOf[scala.Double];
    val x_200 = x_199.+(x_197);
    resetData_0 = x_200;
    val x_201 = resetData_0;
    val x_202 = x_201.asInstanceOf[scala.Double];
    bindingMut_22 = x_202;
    positionVar_28 = 25
  }));
  data_29.update(25, (() => {
    val x_203 = bindingMut_22;
    val x_204 = x_203.asInstanceOf[scala.Double];
    val x_205 = x_204.<(1.0);
    if (x_205)
      positionVar_28 = 23
    else
      positionVar_28 = 26
  }));
  data_29.update(26, (() => {
    val x_206 = bindingMut_22;
    val x_207 = x_206.asInstanceOf[scala.Double];
    val x_208 = x_207.<(1.0);
    val x_209 = x_208.`unary_!`;
    if (x_209)
      positionVar_28 = 27
    else
      ()
  }));
  data_29.update(27, (() => {
    val x_210 = methodArgsMut_10;
    val x_211 = x_210.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_212 = x_211.nonEmpty;
    val x_217 = x_212.&&({
      val x_213 = methodArgsMut_10;
      val x_214 = x_213.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_214.forall(((x_215: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_216 = x_215.get;
        lib.`package`.SimRuntime.isCompleted(x_216)
      }))
    });
    val x_218 = x_217.`unary_!`;
    if (x_218)
      positionVar_28 = 22
    else
      positionVar_28 = 28
  }));
  data_29.update(28, (() => {
    val x_219 = methodArgsMut_10;
    val x_220 = x_219.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_221 = x_220.nonEmpty;
    val x_226 = x_221.&&({
      val x_222 = methodArgsMut_10;
      val x_223 = x_222.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_223.forall(((x_224: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_225 = x_224.get;
        lib.`package`.SimRuntime.isCompleted(x_225)
      }))
    });
    val x_227 = x_226.`unary_!`;
    val x_228 = x_227.`unary_!`;
    if (x_228)
      positionVar_28 = 29
    else
      ()
  }));
  data_29.update(29, (() => {
    val x_229 = scala.collection.immutable.List.canBuildFrom[scala.Any];
    resetData_0 = x_229;
    val x_230 = resetData_0;
    val x_231 = x_230.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Any, scala.collection.immutable.List[scala.Any]]];
    bindingMut_21 = x_231;
    resetData_0 = scala.collection.immutable.Nil;
    val x_232 = resetData_0;
    val x_233 = x_232.asInstanceOf[scala.collection.immutable.List[scala.Any]];
    bindingMut_20 = x_233;
    val x_234 = methodArgsMut_10;
    val x_235 = x_234.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_236 = x_235.iterator;
    iterMut_19 = x_236;
    positionVar_28 = 30
  }));
  data_29.update(30, (() => {
    val x_237 = iterMut_19;
    val x_238 = x_237.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_239 = x_238.hasNext;
    if (x_239)
      {
        val x_240 = iterMut_19;
        val x_241 = x_240.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
        val x_242 = x_241.next();
        listValMut_18 = x_242;
        val x_243 = listValMut_18;
        val x_244 = x_243.asInstanceOf[scala.Option[meta.runtime.Future[scala.Any]]];
        val x_245 = x_244.get;
        resetData_0 = x_245;
        val x_246 = resetData_0;
        val x_247 = x_246.asInstanceOf[meta.runtime.Future[scala.Any]];
        bindingMut_17 = x_247;
        val x_248 = bindingMut_17;
        val x_249 = x_248.asInstanceOf[meta.runtime.Future[scala.Any]];
        val x_250 = lib.`package`.SimRuntime.getFutureValue[scala.Any](x_249);
        resetData_0 = x_250;
        val x_251 = resetData_0;
        val x_252 = x_251.asInstanceOf[scala.Any];
        bindingMut_16 = x_252;
        val x_253 = bindingMut_16;
        val x_254 = x_253.asInstanceOf[scala.Any];
        val x_255 = scala.collection.immutable.List.apply[scala.Any](x_254);
        resetData_0 = x_255;
        val x_256 = resetData_0;
        val x_257 = x_256.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        bindingMut_15 = x_257;
        val x_258 = bindingMut_15;
        val x_259 = x_258.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        val x_260 = bindingMut_20;
        val x_261 = x_260.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        val x_262 = x_259.:::[scala.Any](x_261);
        resetData_0 = x_262;
        val x_263 = resetData_0;
        val x_264 = x_263.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        bindingMut_20 = x_264;
        positionVar_28 = 30
      }
    else
      positionVar_28 = 31
  }));
  data_29.update(31, (() => {
    val x_265 = iterMut_19;
    val x_266 = x_265.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_267 = x_266.hasNext;
    val x_268 = x_267.`unary_!`;
    if (x_268)
      {
        val x_269 = resetData_0;
        val x_270 = x_269.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        bindingMut_14 = x_270;
        val x_271 = methodArgsMut_10;
        val x_272 = x_271.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
        val x_273 = x_272.iterator;
        iterMut_13 = x_273;
        positionVar_28 = 32
      }
    else
      ()
  }));
  data_29.update(32, (() => {
    val x_274 = iterMut_13;
    val x_275 = x_274.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_276 = x_275.hasNext;
    if (x_276)
      {
        val x_277 = iterMut_13;
        val x_278 = x_277.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
        val x_279 = x_278.next();
        listValMut_12 = x_279;
        val x_280 = listValMut_12;
        val x_281 = x_280.asInstanceOf[scala.Option[meta.runtime.Future[scala.Any]]];
        val x_282 = x_281.get;
        resetData_0 = x_282;
        val x_283 = resetData_0;
        val x_284 = x_283.asInstanceOf[meta.runtime.Future[scala.Any]];
        bindingMut_11 = x_284;
        val x_285 = bindingMut_11;
        val x_286 = x_285.asInstanceOf[meta.runtime.Future[scala.Any]];
        val x_287 = lib.`package`.SimRuntime.clearFutureObj(x_286);
        resetData_0 = x_287;
        positionVar_28 = 32
      }
    else
      positionVar_28 = 33
  }));
  data_29.update(33, (() => {
    val x_288 = iterMut_13;
    val x_289 = x_288.asInstanceOf[scala.collection.Iterator[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_290 = x_289.hasNext;
    val x_291 = x_290.`unary_!`;
    if (x_291)
      {
        this.`deleted_=`(true);
        resetData_0 = ();
        val x_292 = bindingMut_14;
        val x_293 = x_292.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        resetData_0 = x_293;
        x_4.remove(0);
        val x_294 = x_4.isEmpty;
        val x_295 = x_294.`unary_!`;
        if (x_295)
          {
            val x_296 = x_4(0);
            val x_297 = x_296.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
            methodArgsMut_10 = x_297
          }
        else
          ();
        val x_298 = resetData_0;
        val x_299 = x_298.asInstanceOf[scala.Any];
        bindingMut_24 = x_299;
        val x_300 = bindingMut_24;
        val x_301 = x_300.asInstanceOf[scala.Any];
        val x_302 = listValMut_25;
        val x_303 = x_302.asInstanceOf[meta.runtime.RequestMessage];
        x_303.reply(this, x_301);
        resetData_0 = ();
        positionVar_28 = 6
      }
    else
      ()
  }));
  data_29.update(34, (() => {
    val x_304 = methodArgsMut_10;
    val x_305 = x_304.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
    val x_306 = x_305.nonEmpty;
    val x_311 = x_306.&&({
      val x_307 = methodArgsMut_10;
      val x_308 = x_307.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Any]]]];
      x_308.forall(((x_309: scala.Option[meta.runtime.Future[scala.Any]]) => {
        val x_310 = x_309.get;
        lib.`package`.SimRuntime.isCompleted(x_310)
      }))
    });
    val x_312 = x_311.`unary_!`;
    val x_313 = x_312.`unary_!`;
    if (x_313)
      positionVar_28 = 29
    else
      ()
  }));
  data_29.update(35, (() => {
    val x_314 = iterMut_26;
    val x_315 = x_314.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_316 = x_315.hasNext;
    val x_317 = x_316.`unary_!`;
    if (x_317)
      {
        resetData_0 = 0.0;
        val x_318 = resetData_0;
        val x_319 = x_318.asInstanceOf[scala.Double];
        bindingMut_23 = x_319;
        positionVar_28 = 36
      }
    else
      ()
  }));
  data_29.update(36, (() => {
    val x_320 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_321 = meta.runtime.SimRuntime.labelVals(x_320);
    val x_322 = bindingMut_23;
    val x_323 = x_322.asInstanceOf[scala.Double];
    val x_324 = 1.0.-(x_323);
    x_321.append(x_324);
    resetData_0 = ();
    positionVar_28 = 37;
    val x_325 = currentTurn;
    val x_326 = x_325.+(1);
    currentTurn = x_326
  }));
  data_29.update(37, (() => {
    val x_327 = meta.runtime.SimRuntime.proceedLabel;
    val x_328 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_329 = x_327(x_328);
    val x_330 = bindingMut_23;
    val x_331 = x_330.asInstanceOf[scala.Double];
    val x_332 = x_331.+(x_329);
    resetData_0 = x_332;
    val x_333 = resetData_0;
    val x_334 = x_333.asInstanceOf[scala.Double];
    bindingMut_23 = x_334;
    positionVar_28 = 38
  }));
  data_29.update(38, (() => {
    val x_335 = bindingMut_23;
    val x_336 = x_335.asInstanceOf[scala.Double];
    val x_337 = x_336.<(1.0);
    if (x_337)
      positionVar_28 = 36
    else
      positionVar_28 = 39
  }));
  data_29.update(39, (() => {
    val x_338 = bindingMut_23;
    val x_339 = x_338.asInstanceOf[scala.Double];
    val x_340 = x_339.<(1.0);
    val x_341 = x_340.`unary_!`;
    if (x_341)
      positionVar_28 = 40
    else
      ()
  }));
  data_29.update(40, (() => {
    val x_342 = this.deleted;
    val x_343 = x_342.`unary_!`;
    if (x_343)
      positionVar_28 = 2
    else
      positionVar_28 = 41
  }));
  data_29.update(41, (() => {
    val x_344 = this.deleted;
    val x_345 = x_344.`unary_!`;
    val x_346 = x_345.`unary_!`;
    if (x_346)
      positionVar_28 = 42
    else
      ()
  }));
  data_29.update(42, (() => positionVar_28 = 43));
  data_29.update(43, (() => {
    positionVar_28 = 44;
    val x_347 = currentTurn;
    val x_348 = x_347.+(1);
    currentTurn = x_348
  }));
  data_29.update(44, (() => positionVar_28 = 43));
  data_29.update(45, (() => {
    val x_349 = this.deleted;
    val x_350 = x_349.`unary_!`;
    val x_351 = x_350.`unary_!`;
    if (x_351)
      positionVar_28 = 42
    else
      ()
  }));
  data_29
}).apply();
  
  override def run_until(until_353: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_354 = currentTurn;
      val x_355 = x_354.<=(until_353);
      x_355.&&({
        val x_356 = positionVar_28;
        val x_357 = commands_352.length;
        x_356.<(x_357)
      })
    }) 
      {
        val x_358 = positionVar_28;
        val x_359 = commands_352.apply(x_358);
        x_359.apply()
      }
    ;
    this
  }
}
