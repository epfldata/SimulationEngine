package generated.example.gameOfLife

class Cell(var alive : Boolean, var cfreq : Int) extends meta.runtime.Actor {

   var futures: List[meta.runtime.Future[Boolean]] = scala.collection.immutable.Nil;
  private var  reflectionIR_24: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]()
private var methodArgsMut_4: scala.collection.immutable.List[scala.Boolean] = null
private var bindingMut_5: scala.Boolean = false
private var bindingMut_6: scala.Boolean = false
private var bindingMut_7: scala.Boolean = false
private var bindingMut_8: scala.Boolean = false
private var bindingMut_9: scala.Boolean = false
private var bindingMut_10: scala.Boolean = false
private var bindingMut_11: scala.Int = 0
private var bindingMut_12: scala.collection.immutable.List[scala.Boolean] = null
private var bindingMut_13: scala.Any = null
private var listValMut_14: meta.runtime.RequestMessage = null
@transient private var iterMut_15: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_16: scala.Double = 0.0
private var bindingMut_17: scala.Double = 0.0
private var bindingMut_18: scala.Int = 0
private var bindingMut_19: scala.collection.immutable.List[scala.Boolean] = null
private var bindingMut_20: scala.collection.immutable.List[scala.Boolean] = null
private var bindingMut_21: scala.collection.Iterable[scala.Boolean] = null
private var bindingMut_22: scala.Boolean = false
private var bindingMut_23: scala.Option[scala.Boolean] = null
private var listValMut_24: meta.runtime.Future[scala.Boolean] = null
@transient private var iterMut_25: scala.collection.Iterator[meta.runtime.Future[scala.Boolean]] = null
private var bindingMut_26: scala.collection.Iterable[scala.Boolean] = null
@transient private var bindingMut_27: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Boolean, scala.collection.immutable.List[scala.Boolean]] = null
private var bindingMut_28: scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]] = null
private var bindingMut_29: scala.Double = 0.0
private var bindingMut_30: scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]] = null
private var bindingMut_31: scala.collection.Iterable[meta.runtime.Future[scala.Boolean]] = null
private var bindingMut_32: meta.runtime.Future[scala.Boolean] = null
private var listValMut_33: generated.example.gameOfLife.Cell = null
@transient private var iterMut_34: scala.collection.Iterator[generated.example.gameOfLife.Cell] = null
private var bindingMut_35: scala.collection.Iterable[meta.runtime.Future[scala.Boolean]] = null
@transient private var bindingMut_36: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], meta.runtime.Future[scala.Boolean], scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]]] = null
private var bindingMut_37: scala.collection.immutable.List[generated.example.gameOfLife.Cell] = null
private var bindingMut_38: scala.collection.Iterable[generated.example.gameOfLife.Cell] = null
private var bindingMut_39: generated.example.gameOfLife.Cell = null
private var listValMut_40: meta.runtime.Actor = null
@transient private var iterMut_41: scala.collection.Iterator[meta.runtime.Actor] = null
private var bindingMut_42: scala.collection.Iterable[generated.example.gameOfLife.Cell] = null
@transient private var bindingMut_43: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], generated.example.gameOfLife.Cell, scala.collection.immutable.List[generated.example.gameOfLife.Cell]] = null
private var bindingMut_44: scala.collection.immutable.List[meta.runtime.Actor] = null
private var unblockFlag_45: scala.Boolean = true
private var positionVar_46: scala.Int = 0
private 
  val commands_368 = (() => {
  val data_47 = new scala.Array[scala.Function0[scala.Unit]](66);
  data_47.update(0, (() => positionVar_46 = 1));
  data_47.update(1, (() => {
    val x_48 = this.connectedAgents;
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[scala.collection.immutable.List[meta.runtime.Actor]];
    bindingMut_44 = x_50;
    val x_51 = scala.collection.immutable.List.canBuildFrom[generated.example.gameOfLife.Cell];
    resetData_0 = x_51;
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], generated.example.gameOfLife.Cell, scala.collection.immutable.List[generated.example.gameOfLife.Cell]]];
    bindingMut_43 = x_53;
    val x_54 = scala.`package`.Iterable.apply[generated.example.gameOfLife.Cell]();
    resetData_0 = x_54;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.collection.Iterable[generated.example.gameOfLife.Cell]];
    bindingMut_42 = x_56;
    val x_57 = bindingMut_44;
    val x_58 = x_57.iterator;
    iterMut_41 = x_58;
    positionVar_46 = 2
  }));
  data_47.update(2, (() => {
    val x_59 = iterMut_41;
    val x_60 = x_59.hasNext;
    if (x_60)
      {
        val x_61 = iterMut_41;
        val x_62 = x_61.next();
        listValMut_40 = x_62;
        val x_63 = listValMut_40;
        val x_64 = x_63.asInstanceOf[generated.example.gameOfLife.Cell];
        resetData_0 = x_64;
        val x_65 = resetData_0;
        val x_66 = x_65.asInstanceOf[generated.example.gameOfLife.Cell];
        bindingMut_39 = x_66;
        val x_67 = bindingMut_39;
        val x_68 = scala.collection.immutable.List.apply[generated.example.gameOfLife.Cell](x_67);
        resetData_0 = x_68;
        val x_69 = resetData_0;
        val x_70 = x_69.asInstanceOf[scala.collection.Iterable[generated.example.gameOfLife.Cell]];
        bindingMut_38 = x_70;
        val x_71 = scala.collection.Iterable.canBuildFrom[generated.example.gameOfLife.Cell];
        val x_72 = bindingMut_38;
        val x_73 = bindingMut_42;
        val x_74 = x_73.++[generated.example.gameOfLife.Cell, scala.collection.Iterable[generated.example.gameOfLife.Cell]](x_72)(x_71);
        resetData_0 = x_74;
        val x_75 = resetData_0;
        val x_76 = x_75.asInstanceOf[scala.collection.Iterable[generated.example.gameOfLife.Cell]];
        bindingMut_42 = x_76;
        positionVar_46 = 2
      }
    else
      positionVar_46 = 3
  }));
  data_47.update(3, (() => {
    val x_77 = iterMut_41;
    val x_78 = x_77.hasNext;
    val x_79 = x_78.`unary_!`;
    if (x_79)
      {
        val x_80 = resetData_0;
        val x_81 = x_80.asInstanceOf[scala.collection.immutable.List[generated.example.gameOfLife.Cell]];
        bindingMut_37 = x_81;
        val x_82 = scala.collection.immutable.List.canBuildFrom[meta.runtime.Future[scala.Boolean]];
        resetData_0 = x_82;
        val x_83 = resetData_0;
        val x_84 = x_83.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], meta.runtime.Future[scala.Boolean], scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]]]];
        bindingMut_36 = x_84;
        val x_85 = scala.`package`.Iterable.apply[meta.runtime.Future[scala.Boolean]]();
        resetData_0 = x_85;
        val x_86 = resetData_0;
        val x_87 = x_86.asInstanceOf[scala.collection.Iterable[meta.runtime.Future[scala.Boolean]]];
        bindingMut_35 = x_87;
        val x_88 = bindingMut_37;
        val x_89 = x_88.iterator;
        iterMut_34 = x_89;
        positionVar_46 = 4
      }
    else
      ()
  }));
  data_47.update(4, (() => {
    val x_90 = iterMut_34;
    val x_91 = x_90.hasNext;
    if (x_91)
      {
        val x_92 = iterMut_34;
        val x_93 = x_92.next();
        listValMut_33 = x_93;
        val x_94 = ((this): meta.runtime.Actor).id;
        val x_95 = listValMut_33;
        val x_96 = x_95.id;
        val x_97 = ((this): meta.runtime.Actor).time;
        val x_98 = meta.runtime.RequestMessage.apply(x_94, x_96, false, false, "getValue", x_97, 1, scala.collection.immutable.Nil);
        val x_99 = x_98.sessionId;
        val x_100 = meta.runtime.Future.apply$default$2[scala.Boolean];
        val x_101 = meta.runtime.Future.apply[scala.Boolean](x_99, x_100);
        var v_102: meta.runtime.Future[scala.Boolean] = x_101;
        ((this): meta.runtime.Actor).sendMessage(x_98);
        val x_103 = x_98.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_103, ((response_104: meta.runtime.Message) => {
          val x_105 = v_102;
          val x_106 = response_104.asInstanceOf[meta.runtime.ResponseMessage];
          val x_107 = x_106.arg;
          val x_108 = x_107.asInstanceOf[scala.Boolean];
          x_105.setValue(x_108)
        }));
        val x_109 = v_102;
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[meta.runtime.Future[scala.Boolean]];
        bindingMut_32 = x_111;
        val x_112 = bindingMut_32;
        val x_113 = scala.collection.immutable.List.apply[meta.runtime.Future[scala.Boolean]](x_112);
        resetData_0 = x_113;
        val x_114 = resetData_0;
        val x_115 = x_114.asInstanceOf[scala.collection.Iterable[meta.runtime.Future[scala.Boolean]]];
        bindingMut_31 = x_115;
        val x_116 = scala.collection.Iterable.canBuildFrom[meta.runtime.Future[scala.Boolean]];
        val x_117 = bindingMut_31;
        val x_118 = bindingMut_35;
        val x_119 = x_118.++[meta.runtime.Future[scala.Boolean], scala.collection.Iterable[meta.runtime.Future[scala.Boolean]]](x_117)(x_116);
        resetData_0 = x_119;
        val x_120 = resetData_0;
        val x_121 = x_120.asInstanceOf[scala.collection.Iterable[meta.runtime.Future[scala.Boolean]]];
        bindingMut_35 = x_121;
        positionVar_46 = 4
      }
    else
      positionVar_46 = 5
  }));
  data_47.update(5, (() => {
    val x_122 = iterMut_34;
    val x_123 = x_122.hasNext;
    val x_124 = x_123.`unary_!`;
    if (x_124)
      {
        val x_125 = resetData_0;
        val x_126 = x_125.asInstanceOf[scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]]];
        bindingMut_30 = x_126;
        val x_127 = bindingMut_30;
        this.`futures_=`(x_127);
        resetData_0 = ();
        positionVar_46 = 6
      }
    else
      ()
  }));
  data_47.update(6, (() => {
    val x_128 = this.futures;
    val x_129 = x_128.nonEmpty;
    val x_132 = x_129.&&({
      val x_130 = this.futures;
      x_130.forall(((x_131: meta.runtime.Future[scala.Boolean]) => x_131.isCompleted))
    });
    val x_133 = x_132.`unary_!`;
    if (x_133)
      positionVar_46 = 7
    else
      positionVar_46 = 64
  }));
  data_47.update(7, (() => {
    resetData_0 = 0.0;
    val x_134 = resetData_0;
    val x_135 = x_134.asInstanceOf[scala.Double];
    bindingMut_29 = x_135;
    positionVar_46 = 8
  }));
  data_47.update(8, (() => {
    val x_136 = bindingMut_29;
    val x_137 = x_136.+(1);
    resetData_0 = x_137;
    val x_138 = resetData_0;
    val x_139 = x_138.asInstanceOf[scala.Double];
    bindingMut_29 = x_139;
    positionVar_46 = 9;
    unblockFlag_45 = false
  }));
  data_47.update(9, (() => {
    positionVar_46 = 10;
    val x_140 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_141 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_140, 56);
    val x_142 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_141);
    resetData_1.prepend(x_142)
  }));
  data_47.update(10, (() => {
    val x_143 = this.popRequestMessages;
    val x_144 = x_143.iterator;
    iterMut_15 = x_144;
    positionVar_46 = 11
  }));
  data_47.update(11, (() => {
    val x_145 = iterMut_15;
    val x_146 = x_145.hasNext;
    if (x_146)
      {
        val x_147 = iterMut_15;
        val x_148 = x_147.next();
        listValMut_14 = x_148;
        positionVar_46 = 12
      }
    else
      positionVar_46 = 48
  }));
  data_47.update(12, (() => {
    val x_149 = listValMut_14;
    val x_150 = x_149.methodInfo;
    val x_151 = x_150.==("getValue");
    if (x_151)
      positionVar_46 = 13
    else
      positionVar_46 = 21
  }));
  data_47.update(13, (() => positionVar_46 = 20));
  data_47.update(14, (() => {
    val x_152 = this.alive;
    resetData_0 = x_152;
    positionVar_46 = 15
  }));
  data_47.update(15, (() => {
    val x_153 = resetData_0;
    val x_154 = x_153.asInstanceOf[scala.Any];
    bindingMut_13 = x_154;
    positionVar_46 = 16
  }));
  data_47.update(16, (() => {
    val x_155 = listValMut_14;
    val x_156 = x_155.oneside;
    val x_157 = x_156.==(true);
    if (x_157)
      positionVar_46 = 17
    else
      positionVar_46 = 19
  }));
  data_47.update(17, (() => positionVar_46 = 18));
  data_47.update(18, (() => positionVar_46 = 11));
  data_47.update(19, (() => {
    val x_158 = listValMut_14;
    val x_159 = x_158.oneside;
    val x_160 = x_159.==(true);
    val x_161 = x_160.`unary_!`;
    if (x_161)
      {
        val x_162 = bindingMut_13;
        val x_163 = listValMut_14;
        x_163.reply(this, x_162);
        resetData_0 = ();
        positionVar_46 = 17
      }
    else
      ()
  }));
  data_47.update(20, (() => {
    val x_164 = listValMut_14;
    this.handleNonblockingMessage(x_164);
    resetData_0 = ();
    positionVar_46 = 17
  }));
  data_47.update(21, (() => {
    val x_165 = listValMut_14;
    val x_166 = x_165.methodInfo;
    val x_167 = x_166.==("getValue");
    val x_168 = x_167.`unary_!`;
    if (x_168)
      positionVar_46 = 22
    else
      ()
  }));
  data_47.update(22, (() => {
    val x_169 = listValMut_14;
    val x_170 = x_169.methodInfo;
    val x_171 = x_170.==("rule");
    if (x_171)
      positionVar_46 = 23
    else
      positionVar_46 = 47
  }));
  data_47.update(23, (() => {
    positionVar_46 = 46;
    val x_172 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_173 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_172, 32);
    val x_174 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_173);
    resetData_1.prepend(x_174)
  }));
  data_47.update(24, (() => {
    val x_175 = methodArgsMut_4;
    val x_177 = x_175.filter(((x_176: scala.Boolean) => x_176.==(true)));
    resetData_0 = x_177;
    val x_178 = resetData_0;
    val x_179 = x_178.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
    bindingMut_12 = x_179;
    val x_180 = bindingMut_12;
    val x_181 = x_180.size;
    resetData_0 = x_181;
    val x_182 = resetData_0;
    val x_183 = x_182.asInstanceOf[scala.Int];
    bindingMut_11 = x_183;
    val x_184 = this.alive;
    resetData_0 = x_184;
    val x_185 = resetData_0;
    val x_186 = x_185.asInstanceOf[scala.Boolean];
    bindingMut_10 = x_186;
    positionVar_46 = 25
  }));
  data_47.update(25, (() => {
    val x_187 = bindingMut_10;
    val x_188 = x_187.`unary_!`;
    if (x_188)
      {
        resetData_0 = false;
        positionVar_46 = 26
      }
    else
      positionVar_46 = 42
  }));
  data_47.update(26, (() => {
    val x_189 = resetData_0;
    val x_190 = x_189.asInstanceOf[scala.Boolean];
    bindingMut_8 = x_190;
    positionVar_46 = 27
  }));
  data_47.update(27, (() => {
    val x_191 = bindingMut_8;
    val x_192 = x_191.`unary_!`;
    if (x_192)
      positionVar_46 = 28
    else
      positionVar_46 = 41
  }));
  data_47.update(28, (() => {
    val x_193 = this.alive;
    resetData_0 = x_193;
    val x_194 = resetData_0;
    val x_195 = x_194.asInstanceOf[scala.Boolean];
    bindingMut_7 = x_195;
    val x_196 = bindingMut_7;
    val x_197 = x_196.`unary_!`;
    resetData_0 = x_197;
    val x_198 = resetData_0;
    val x_199 = x_198.asInstanceOf[scala.Boolean];
    bindingMut_6 = x_199;
    positionVar_46 = 29
  }));
  data_47.update(29, (() => {
    val x_200 = bindingMut_6;
    val x_201 = x_200.`unary_!`;
    if (x_201)
      {
        resetData_0 = false;
        positionVar_46 = 30
      }
    else
      positionVar_46 = 40
  }));
  data_47.update(30, (() => {
    val x_202 = resetData_0;
    val x_203 = x_202.asInstanceOf[scala.Boolean];
    bindingMut_5 = x_203;
    positionVar_46 = 31
  }));
  data_47.update(31, (() => {
    val x_204 = bindingMut_5;
    val x_205 = x_204.`unary_!`;
    if (x_205)
      {
        val x_206 = resetData_1.remove(0);
        val x_210 = x_206.find(((x_207: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_208 = x_207._1;
          val x_209 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_208.==(x_209)
        }));
        val x_211 = x_210.get;
        val x_212 = x_211._2;
        positionVar_46 = x_212
      }
    else
      positionVar_46 = 39
  }));
  data_47.update(32, (() => {
    x_3.remove(0);
    val x_213 = x_3.isEmpty;
    val x_214 = x_213.`unary_!`;
    if (x_214)
      {
        val x_215 = x_3(0);
        val x_216 = x_215.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        methodArgsMut_4 = x_216
      }
    else
      ();
    val x_217 = resetData_0;
    val x_218 = x_217.asInstanceOf[scala.Any];
    bindingMut_13 = x_218;
    positionVar_46 = 33
  }));
  data_47.update(33, (() => {
    val x_219 = listValMut_14;
    val x_220 = x_219.oneside;
    val x_221 = x_220.==(true);
    if (x_221)
      positionVar_46 = 34
    else
      positionVar_46 = 35
  }));
  data_47.update(34, (() => positionVar_46 = 18));
  data_47.update(35, (() => {
    val x_222 = listValMut_14;
    val x_223 = x_222.oneside;
    val x_224 = x_223.==(true);
    val x_225 = x_224.`unary_!`;
    if (x_225)
      {
        val x_226 = bindingMut_13;
        val x_227 = listValMut_14;
        x_227.reply(this, x_226);
        resetData_0 = ();
        positionVar_46 = 34
      }
    else
      ()
  }));
  data_47.update(36, (() => {
    x_3.remove(0);
    val x_228 = x_3.isEmpty;
    val x_229 = x_228.`unary_!`;
    if (x_229)
      {
        val x_230 = x_3(0);
        val x_231 = x_230.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        methodArgsMut_4 = x_231
      }
    else
      ();
    val x_232 = this.cfreq;
    resetData_0 = x_232;
    val x_233 = resetData_0;
    val x_234 = x_233.asInstanceOf[scala.Int];
    bindingMut_18 = x_234;
    val x_235 = bindingMut_18;
    val x_236 = x_235.toDouble;
    resetData_0 = x_236;
    val x_237 = resetData_0;
    val x_238 = x_237.asInstanceOf[scala.Double];
    bindingMut_17 = x_238;
    resetData_0 = 0.0;
    val x_239 = resetData_0;
    val x_240 = x_239.asInstanceOf[scala.Double];
    bindingMut_16 = x_240;
    positionVar_46 = 37
  }));
  data_47.update(37, (() => {
    val x_241 = bindingMut_16;
    val x_242 = x_241.+(1);
    resetData_0 = x_242;
    val x_243 = resetData_0;
    val x_244 = x_243.asInstanceOf[scala.Double];
    bindingMut_16 = x_244;
    positionVar_46 = 38;
    unblockFlag_45 = false
  }));
  data_47.update(38, (() => {
    positionVar_46 = 10;
    val x_245 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_246 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_245, 49);
    val x_247 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_246);
    resetData_1.prepend(x_247)
  }));
  data_47.update(39, (() => {
    val x_248 = bindingMut_5;
    squid.lib.`package`.IfThenElse[scala.Unit](x_248, {
      this.`alive_=`(true);
      resetData_0 = ();
      val x_249 = resetData_1.remove(0);
      val x_253 = x_249.find(((x_250: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
        val x_251 = x_250._1;
        val x_252 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
        x_251.==(x_252)
      }));
      val x_254 = x_253.get;
      val x_255 = x_254._2;
      positionVar_46 = x_255
    }, ())
  }));
  data_47.update(40, (() => {
    val x_256 = bindingMut_6;
    squid.lib.`package`.IfThenElse[scala.Unit](x_256, {
      val x_257 = bindingMut_11;
      val x_258 = x_257.==(3);
      resetData_0 = x_258;
      positionVar_46 = 30
    }, ())
  }));
  data_47.update(41, (() => {
    val x_259 = bindingMut_8;
    squid.lib.`package`.IfThenElse[scala.Unit](x_259, {
      this.`alive_=`(false);
      resetData_0 = ();
      positionVar_46 = 28
    }, ())
  }));
  data_47.update(42, (() => {
    val x_260 = bindingMut_10;
    squid.lib.`package`.IfThenElse[scala.Unit](x_260, {
      val x_261 = bindingMut_11;
      val x_262 = x_261.>(3);
      resetData_0 = x_262;
      val x_263 = resetData_0;
      val x_264 = x_263.asInstanceOf[scala.Boolean];
      bindingMut_9 = x_264;
      positionVar_46 = 43
    }, ())
  }));
  data_47.update(43, (() => {
    val x_265 = bindingMut_9;
    val x_266 = x_265.`unary_!`;
    if (x_266)
      {
        val x_267 = bindingMut_11;
        val x_268 = x_267.<(2);
        resetData_0 = x_268;
        positionVar_46 = 44
      }
    else
      positionVar_46 = 45
  }));
  data_47.update(44, (() => positionVar_46 = 26));
  data_47.update(45, (() => {
    val x_269 = bindingMut_9;
    squid.lib.`package`.IfThenElse[scala.Unit](x_269, {
      resetData_0 = true;
      positionVar_46 = 44
    }, ())
  }));
  data_47.update(46, (() => {
    val x_270 = listValMut_14;
    this.handleNonblockingMessage(x_270);
    resetData_0 = ();
    positionVar_46 = 34
  }));
  data_47.update(47, (() => {
    val x_271 = listValMut_14;
    val x_272 = x_271.methodInfo;
    val x_273 = x_272.==("rule");
    val x_274 = x_273.`unary_!`;
    if (x_274)
      {
        val x_275 = listValMut_14;
        val x_276 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_275);
        val x_277 = this.addReceiveMessages(x_276);
        resetData_0 = x_277;
        positionVar_46 = 18
      }
    else
      ()
  }));
  data_47.update(48, (() => {
    val x_278 = iterMut_15;
    val x_279 = x_278.hasNext;
    val x_280 = x_279.`unary_!`;
    if (x_280)
      {
        val x_281 = resetData_1.remove(0);
        val x_285 = x_281.find(((x_282: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_283 = x_282._1;
          val x_284 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_283.==(x_284)
        }));
        val x_286 = x_285.get;
        val x_287 = x_286._2;
        positionVar_46 = x_287
      }
    else
      ()
  }));
  data_47.update(49, (() => positionVar_46 = 50));
  data_47.update(50, (() => {
    val x_288 = bindingMut_16;
    val x_289 = bindingMut_17;
    val x_290 = x_288.<(x_289);
    if (x_290)
      positionVar_46 = 37
    else
      positionVar_46 = 51
  }));
  data_47.update(51, (() => {
    val x_291 = bindingMut_16;
    val x_292 = bindingMut_17;
    val x_293 = x_291.<(x_292);
    val x_294 = x_293.`unary_!`;
    if (x_294)
      positionVar_46 = 52
    else
      ()
  }));
  data_47.update(52, (() => positionVar_46 = 1));
  data_47.update(53, (() => positionVar_46 = 54));
  data_47.update(54, (() => {
    positionVar_46 = 55;
    unblockFlag_45 = false
  }));
  data_47.update(55, (() => positionVar_46 = 54));
  data_47.update(56, (() => positionVar_46 = 57));
  data_47.update(57, (() => {
    val x_295 = bindingMut_29;
    val x_296 = x_295.<(1.0);
    if (x_296)
      positionVar_46 = 8
    else
      positionVar_46 = 58
  }));
  data_47.update(58, (() => {
    val x_297 = bindingMut_29;
    val x_298 = x_297.<(1.0);
    val x_299 = x_298.`unary_!`;
    if (x_299)
      positionVar_46 = 59
    else
      ()
  }));
  data_47.update(59, (() => {
    val x_300 = this.futures;
    val x_301 = x_300.nonEmpty;
    val x_304 = x_301.&&({
      val x_302 = this.futures;
      x_302.forall(((x_303: meta.runtime.Future[scala.Boolean]) => x_303.isCompleted))
    });
    val x_305 = x_304.`unary_!`;
    if (x_305)
      positionVar_46 = 7
    else
      positionVar_46 = 60
  }));
  data_47.update(60, (() => {
    val x_306 = this.futures;
    val x_307 = x_306.nonEmpty;
    val x_310 = x_307.&&({
      val x_308 = this.futures;
      x_308.forall(((x_309: meta.runtime.Future[scala.Boolean]) => x_309.isCompleted))
    });
    val x_311 = x_310.`unary_!`;
    val x_312 = x_311.`unary_!`;
    if (x_312)
      positionVar_46 = 61
    else
      ()
  }));
  data_47.update(61, (() => {
    val x_313 = this.futures;
    resetData_0 = x_313;
    val x_314 = resetData_0;
    val x_315 = x_314.asInstanceOf[scala.collection.immutable.List[meta.runtime.Future[scala.Boolean]]];
    bindingMut_28 = x_315;
    val x_316 = scala.collection.immutable.List.canBuildFrom[scala.Boolean];
    resetData_0 = x_316;
    val x_317 = resetData_0;
    val x_318 = x_317.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Boolean, scala.collection.immutable.List[scala.Boolean]]];
    bindingMut_27 = x_318;
    val x_319 = scala.`package`.Iterable.apply[scala.Boolean]();
    resetData_0 = x_319;
    val x_320 = resetData_0;
    val x_321 = x_320.asInstanceOf[scala.collection.Iterable[scala.Boolean]];
    bindingMut_26 = x_321;
    val x_322 = bindingMut_28;
    val x_323 = x_322.iterator;
    iterMut_25 = x_323;
    positionVar_46 = 62
  }));
  data_47.update(62, (() => {
    val x_324 = iterMut_25;
    val x_325 = x_324.hasNext;
    if (x_325)
      {
        val x_326 = iterMut_25;
        val x_327 = x_326.next();
        listValMut_24 = x_327;
        val x_328 = listValMut_24;
        val x_329 = x_328.popValue;
        resetData_0 = x_329;
        val x_330 = resetData_0;
        val x_331 = x_330.asInstanceOf[scala.Option[scala.Boolean]];
        bindingMut_23 = x_331;
        val x_332 = bindingMut_23;
        val x_333 = x_332.get;
        resetData_0 = x_333;
        val x_334 = resetData_0;
        val x_335 = x_334.asInstanceOf[scala.Boolean];
        bindingMut_22 = x_335;
        val x_336 = bindingMut_22;
        val x_337 = scala.collection.immutable.List.apply[scala.Boolean](x_336);
        resetData_0 = x_337;
        val x_338 = resetData_0;
        val x_339 = x_338.asInstanceOf[scala.collection.Iterable[scala.Boolean]];
        bindingMut_21 = x_339;
        val x_340 = scala.collection.Iterable.canBuildFrom[scala.Boolean];
        val x_341 = bindingMut_21;
        val x_342 = bindingMut_26;
        val x_343 = x_342.++[scala.Boolean, scala.collection.Iterable[scala.Boolean]](x_341)(x_340);
        resetData_0 = x_343;
        val x_344 = resetData_0;
        val x_345 = x_344.asInstanceOf[scala.collection.Iterable[scala.Boolean]];
        bindingMut_26 = x_345;
        positionVar_46 = 62
      }
    else
      positionVar_46 = 63
  }));
  data_47.update(63, (() => {
    val x_346 = iterMut_25;
    val x_347 = x_346.hasNext;
    val x_348 = x_347.`unary_!`;
    if (x_348)
      {
        val x_349 = resetData_0;
        val x_350 = x_349.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        bindingMut_20 = x_350;
        val x_351 = bindingMut_20;
        val x_352 = x_351.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        resetData_0 = x_352;
        val x_353 = resetData_0;
        val x_354 = x_353.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        bindingMut_19 = x_354;
        val x_355 = bindingMut_19;
        x_3.prepend(x_355);
        val x_356 = bindingMut_19;
        val x_357 = x_356.asInstanceOf[scala.collection.immutable.List[scala.Boolean]];
        methodArgsMut_4 = x_357;
        positionVar_46 = 24
      }
    else
      ();
    val x_358 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_359 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_358, 36);
    val x_360 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_359);
    resetData_1.prepend(x_360)
  }));
  data_47.update(64, (() => {
    val x_361 = this.futures;
    val x_362 = x_361.nonEmpty;
    val x_365 = x_362.&&({
      val x_363 = this.futures;
      x_363.forall(((x_364: meta.runtime.Future[scala.Boolean]) => x_364.isCompleted))
    });
    val x_366 = x_365.`unary_!`;
    val x_367 = x_366.`unary_!`;
    if (x_367)
      positionVar_46 = 61
    else
      ()
  }));
  data_47.update(65, (() => positionVar_46 = 54));
  data_47
}).apply();
  

   def getValue : Boolean =
      this.alive
  
  private def wrapper_getValue(args: List[Any]): Boolean = {
    getValue
  }
  
   def rule(neighborsAlive: List[Boolean]) : Unit = 
      {
  val x_1 = neighborsAlive.filter(((x_0: scala.Boolean) => x_0.==(true)));
  val x_2 = x_1.size;
  val x_3 = this.alive;
  val x_5 = x_3.&&({
    val x_4 = x_2.>(3);
    x_4.||(x_2.<(2))
  });
  if (x_5)
    this.`alive_=`(false)
  else
    ();
  val x_6 = this.alive;
  val x_7 = x_6.`unary_!`;
  val x_8 = x_7.&&(x_2.==(3));
  if (x_8)
    this.`alive_=`(true)
  else
    ()
}
  
  private def wrapper_rule(args: List[Any]): Unit = {
    
          val neighborsAlive: List[Boolean] = args(0).asInstanceOf[List[Boolean]]
          rule(neighborsAlive)
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_45 = true
    while (unblockFlag_45 && (positionVar_46 < 66)) {
      commands_368(positionVar_46)()
    }
    time += 1
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "getValue" => wrapper_getValue(args)
    case "rule" => wrapper_rule(args)
    }
    if (!m.oneside){
      m.reply(this, response)
    }
  }
  
    override def gotoHandleMessages(new_ir: Int = 9): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_45 = true

      if (reflectionIR_24 == -1){
        reflectionIR_24 = positionVar_46
        positionVar_46 = new_ir
      }

      while (positionVar_46 <= 35 && unblockFlag_45) {
        commands_368(positionVar_46)()
      }

      // reset instruction register when finishes processing
      if (positionVar_46 > 35) {
        positionVar_46 = reflectionIR_24
        reflectionIR_24 = -1
      }
      this
    }
    
override def SimClone(cloned_variables: Set[String]): Cell = {
  val newAgent = new Cell(alive, cfreq)
  if (cloned_variables.contains("futures")) newAgent.futures = futures
  newAgent
}

override def SimReset(preserved_names: Set[String]): Unit = {
  positionVar_46 = 0
  val newAgent = new Cell(alive, cfreq)
  if (!preserved_names.contains("futures")) futures = newAgent.futures
}

}
