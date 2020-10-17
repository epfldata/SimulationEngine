package generated.meta.example.segregation

class Person (var world: generated.meta.example.segregation.WorldMap, var view: Int) extends meta.deep.runtime.Actor {
  var location: Int = -1
  var similarity: Double = 1.0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Int = 0;
  private var bindingMut_4: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_5: scala.Double = 0.0;
  private var bindingMut_6: scala.Int = 0;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: scala.Double = 0.0;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_14: scala.Boolean = false;
  private var bindingMut_15: scala.Int = 0;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: scala.Int = 0;
  private var bindingMut_18: scala.Boolean = false;
  private var bindingMut_19: scala.Int = 0;
  private var bindingMut_20: scala.Int = 0;
  private var listValMut_21: generated.meta.example.segregation.Person = null;
  private var iterMut_22: scala.collection.Iterator[generated.meta.example.segregation.Person] = null;
  private var bindingMut_23: scala.collection.immutable.List[generated.meta.example.segregation.Person] = null;
  private var bindingMut_24: scala.Int = 0;
  private var bindingMut_25: generated.meta.example.segregation.WorldMap = null;
  private var sameView_26: scala.Int = 0;
  private var bindingMut_27: scala.Any = null;
  private var listValMut_28: meta.deep.runtime.RequestMessage = null;
  private var iterMut_29: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_30: scala.Double = 0.0;
  private var bindingMut_31: scala.Int = 0;
  private var bindingMut_32: scala.Int = 0;
  private var bindingMut_33: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_34: scala.Boolean = false;
  private var bindingMut_35: scala.Boolean = false;
  private var bindingMut_36: scala.Int = 0;
  private var positionVar_38: scala.Int = 0;
  
  val commands_412 = (() => {
  val data_39 = new scala.Array[scala.Function0[scala.Unit]](50);
  data_39.update(0, (() => {
    positionVar_38 = 1;
    val x_40 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_41 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_40, 34);
    val x_42 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_41);
    resetData_1.prepend(x_42)
  }));
  data_39.update(1, (() => if (true)
    positionVar_38 = 2
  else
    positionVar_38 = 49));
  data_39.update(2, (() => {
    val x_43 = this.location;
    resetData_0 = x_43;
    val x_44 = resetData_0;
    val x_45 = x_44.asInstanceOf[scala.Int];
    bindingMut_36 = x_45;
    val x_46 = bindingMut_36;
    val x_47 = x_46.asInstanceOf[scala.Int];
    val x_48 = x_47.==(-1);
    resetData_0 = x_48;
    val x_49 = resetData_0;
    val x_50 = x_49.asInstanceOf[scala.Boolean];
    bindingMut_35 = x_50;
    positionVar_38 = 3
  }));
  data_39.update(3, (() => {
    val x_51 = bindingMut_35;
    val x_52 = x_51.asInstanceOf[scala.Boolean];
    val x_53 = x_52.`unary_!`;
    if (x_53)
      positionVar_38 = 4
    else
      positionVar_38 = 48;
    val x_54 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_55 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_54, 37);
    val x_56 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_55);
    resetData_1.prepend(x_56)
  }));
  data_39.update(4, (() => {
    resetData_0 = 0;
    val x_57 = resetData_0;
    val x_58 = x_57.asInstanceOf[scala.Int];
    sameView_26 = x_58;
    val x_59 = this.world;
    resetData_0 = x_59;
    val x_60 = resetData_0;
    val x_61 = x_60.asInstanceOf[generated.meta.example.segregation.WorldMap];
    bindingMut_25 = x_61;
    val x_62 = this.location;
    resetData_0 = x_62;
    val x_63 = resetData_0;
    val x_64 = x_63.asInstanceOf[scala.Int];
    bindingMut_24 = x_64;
    val x_65 = ((this): meta.deep.runtime.Actor).id;
    val x_67 = {
      val x_66 = bindingMut_25;
      x_66.asInstanceOf[generated.meta.example.segregation.WorldMap]
    };
    val x_68 = x_67.id;
    val x_69 = bindingMut_24;
    val x_70 = x_69.asInstanceOf[scala.Int];
    val x_71 = scala.collection.immutable.Nil.::[scala.Any](x_70);
    val x_72 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_71);
    val x_73 = meta.deep.runtime.RequestMessage.apply(x_65, x_68, 5, x_72);
    ((this): meta.deep.runtime.Actor).sendMessage(x_73);
    val x_74 = x_73.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_74, ((response_75: meta.deep.runtime.Message) => {
      val x_76 = response_75.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_76
    }));
    resetData_0 = null;
    positionVar_38 = 5
  }));
  data_39.update(5, (() => {
    positionVar_38 = 6;
    val x_77 = currentTurn;
    val x_78 = x_77.+(1);
    currentTurn = x_78
  }));
  data_39.update(6, (() => {
    val x_79 = resetData_2;
    val x_80 = x_79.==(null);
    if (x_80)
      {
        val x_81 = meta.deep.runtime.Actor.labelVals("turn");
        x_81.append(1.0);
        positionVar_38 = 5
      }
    else
      positionVar_38 = 7
  }));
  data_39.update(7, (() => {
    val x_82 = resetData_2;
    val x_83 = x_82.!=(null);
    if (x_83)
      {
        val x_84 = resetData_2;
        val x_85 = x_84.arg;
        resetData_0 = x_85;
        resetData_2 = null;
        val x_86 = resetData_0;
        val x_87 = x_86.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        bindingMut_23 = x_87;
        val x_88 = bindingMut_23;
        val x_89 = x_88.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_90 = x_89.iterator;
        iterMut_22 = x_90;
        positionVar_38 = 8
      }
    else
      ()
  }));
  data_39.update(8, (() => {
    val x_91 = iterMut_22;
    val x_92 = x_91.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
    val x_93 = x_92.hasNext;
    if (x_93)
      {
        val x_94 = iterMut_22;
        val x_95 = x_94.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
        val x_96 = x_95.next();
        listValMut_21 = x_96;
        val x_97 = listValMut_21;
        val x_98 = x_97.asInstanceOf[generated.meta.example.segregation.Person];
        val x_99 = x_98.view;
        resetData_0 = x_99;
        val x_100 = resetData_0;
        val x_101 = x_100.asInstanceOf[scala.Int];
        bindingMut_20 = x_101;
        val x_102 = this.view;
        resetData_0 = x_102;
        val x_103 = resetData_0;
        val x_104 = x_103.asInstanceOf[scala.Int];
        bindingMut_19 = x_104;
        val x_105 = bindingMut_19;
        val x_106 = x_105.asInstanceOf[scala.Int];
        val x_107 = bindingMut_20;
        val x_108 = x_107.asInstanceOf[scala.Int];
        val x_109 = x_108.==(x_106);
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Boolean];
        bindingMut_18 = x_111;
        positionVar_38 = 9
      }
    else
      positionVar_38 = 12
  }));
  data_39.update(9, (() => {
    val x_112 = bindingMut_18;
    val x_113 = x_112.asInstanceOf[scala.Boolean];
    val x_114 = x_113.`unary_!`;
    if (x_114)
      positionVar_38 = 10
    else
      positionVar_38 = 11
  }));
  data_39.update(10, (() => positionVar_38 = 8));
  data_39.update(11, (() => {
    val x_115 = bindingMut_18;
    val x_116 = x_115.asInstanceOf[scala.Boolean];
    if (x_116)
      {
        val x_117 = sameView_26;
        resetData_0 = x_117;
        val x_118 = resetData_0;
        val x_119 = x_118.asInstanceOf[scala.Int];
        bindingMut_17 = x_119;
        val x_120 = bindingMut_17;
        val x_121 = x_120.asInstanceOf[scala.Int];
        val x_122 = x_121.+(1);
        resetData_0 = x_122;
        val x_123 = resetData_0;
        val x_124 = x_123.asInstanceOf[scala.Int];
        bindingMut_16 = x_124;
        val x_125 = bindingMut_16;
        val x_126 = x_125.asInstanceOf[scala.Int];
        sameView_26 = x_126;
        resetData_0 = ();
        positionVar_38 = 10
      }
    else
      ()
  }));
  data_39.update(12, (() => {
    val x_127 = iterMut_22;
    val x_128 = x_127.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
    val x_129 = x_128.hasNext;
    val x_130 = x_129.`unary_!`;
    if (x_130)
      {
        val x_131 = bindingMut_23;
        val x_132 = x_131.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_133 = x_132.length;
        resetData_0 = x_133;
        val x_134 = resetData_0;
        val x_135 = x_134.asInstanceOf[scala.Int];
        bindingMut_15 = x_135;
        val x_136 = bindingMut_15;
        val x_137 = x_136.asInstanceOf[scala.Int];
        val x_138 = x_137.==(0);
        resetData_0 = x_138;
        val x_139 = resetData_0;
        val x_140 = x_139.asInstanceOf[scala.Boolean];
        bindingMut_14 = x_140;
        positionVar_38 = 13
      }
    else
      ()
  }));
  data_39.update(13, (() => {
    val x_141 = bindingMut_14;
    val x_142 = x_141.asInstanceOf[scala.Boolean];
    if (x_142)
      {
        this.`similarity_=`(1.0);
        resetData_0 = ();
        val x_143 = this.world;
        resetData_0 = x_143;
        val x_144 = resetData_0;
        val x_145 = x_144.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_13 = x_145;
        val x_146 = ((this): meta.deep.runtime.Actor).id;
        val x_148 = {
          val x_147 = bindingMut_13;
          x_147.asInstanceOf[generated.meta.example.segregation.WorldMap]
        };
        val x_149 = x_148.id;
        val x$1_150 = this.location;
        val x$1_151 = this.similarity;
        val x_152 = scala.collection.immutable.Nil.::[scala.Any](x$1_151);
        val x_153 = x_152.::[scala.Any](x$1_150);
        val x_154 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_153);
        val x_155 = meta.deep.runtime.RequestMessage.apply(x_146, x_149, 7, x_154);
        ((this): meta.deep.runtime.Actor).sendMessage(x_155);
        resetData_0 = scala.None;
        resetData_0 = false;
        val x_156 = resetData_1.remove(0);
        val x_160 = x_156.find(((x_157: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_158 = x_157._1;
          val x_159 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_158.==(x_159)
        }));
        val x_161 = x_160.get;
        val x_162 = x_161._2;
        positionVar_38 = x_162
      }
    else
      positionVar_38 = 47
  }));
  data_39.update(14, (() => {
    val x_163 = resetData_0;
    val x_164 = x_163.asInstanceOf[scala.Any];
    bindingMut_27 = x_164;
    val x_165 = bindingMut_27;
    val x_166 = x_165.asInstanceOf[scala.Any];
    val x_167 = listValMut_28;
    val x_168 = x_167.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_168.reply(this, x_166);
    resetData_0 = ();
    positionVar_38 = 15
  }));
  data_39.update(15, (() => positionVar_38 = 16));
  data_39.update(16, (() => {
    val x_169 = iterMut_29;
    val x_170 = x_169.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_171 = x_170.hasNext;
    if (x_171)
      {
        val x_172 = iterMut_29;
        val x_173 = x_172.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_174 = x_173.next();
        listValMut_28 = x_174;
        positionVar_38 = 17
      }
    else
      positionVar_38 = 30
  }));
  data_39.update(17, (() => {
    val x_175 = listValMut_28;
    val x_176 = x_175.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_177 = x_176.methodId;
    val x_178 = x_177.==(0);
    val x_179 = x_178.`unary_!`;
    if (x_179)
      positionVar_38 = 18
    else
      positionVar_38 = 22
  }));
  data_39.update(18, (() => {
    val x_180 = listValMut_28;
    val x_181 = x_180.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_182 = x_181.methodId;
    val x_183 = x_182.==(1);
    val x_184 = x_183.`unary_!`;
    if (x_184)
      positionVar_38 = 19
    else
      positionVar_38 = 21
  }));
  data_39.update(19, (() => {
    val x_185 = listValMut_28;
    val x_186 = x_185.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_187 = x_186.methodId;
    val x_188 = x_187.==(2);
    val x_189 = x_188.`unary_!`;
    if (x_189)
      {
        val x_190 = listValMut_28;
        val x_191 = x_190.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_192 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_191);
        val x_193 = this.addReceiveMessages(x_192);
        resetData_0 = x_193;
        positionVar_38 = 15
      }
    else
      positionVar_38 = 20
  }));
  data_39.update(20, (() => {
    val x_194 = listValMut_28;
    val x_195 = x_194.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_196 = x_195.methodId;
    val x_197 = x_196.==(2);
    if (x_197)
      positionVar_38 = 1
    else
      ();
    val x_198 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_199 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_198, 33);
    val x_200 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_199);
    resetData_1.prepend(x_200)
  }));
  data_39.update(21, (() => {
    val x_201 = listValMut_28;
    val x_202 = x_201.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_203 = x_202.methodId;
    val x_204 = x_203.==(1);
    if (x_204)
      positionVar_38 = 4
    else
      ();
    val x_205 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_206 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_205, 14);
    val x_207 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_206);
    resetData_1.prepend(x_207)
  }));
  data_39.update(22, (() => {
    val x_208 = listValMut_28;
    val x_209 = x_208.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_210 = x_209.methodId;
    val x_211 = x_210.==(0);
    if (x_211)
      positionVar_38 = 23
    else
      ();
    val x_212 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_213 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_212, 27);
    val x_214 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_213);
    resetData_1.prepend(x_214)
  }));
  data_39.update(23, (() => {
    val x_215 = this.world;
    resetData_0 = x_215;
    val x_216 = resetData_0;
    val x_217 = x_216.asInstanceOf[generated.meta.example.segregation.WorldMap];
    bindingMut_4 = x_217;
    val x_218 = ((this): meta.deep.runtime.Actor).id;
    val x_220 = {
      val x_219 = bindingMut_4;
      x_219.asInstanceOf[generated.meta.example.segregation.WorldMap]
    };
    val x_221 = x_220.id;
    val x$5_222 = scala.collection.immutable.Nil.::[scala.Any](this);
    val x_223 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x$5_222);
    val x_224 = meta.deep.runtime.RequestMessage.apply(x_218, x_221, 4, x_223);
    ((this): meta.deep.runtime.Actor).sendMessage(x_224);
    val x_225 = x_224.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_225, ((response_226: meta.deep.runtime.Message) => {
      val x_227 = response_226.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_227
    }));
    resetData_0 = null;
    positionVar_38 = 24
  }));
  data_39.update(24, (() => {
    positionVar_38 = 25;
    val x_228 = currentTurn;
    val x_229 = x_228.+(1);
    currentTurn = x_229
  }));
  data_39.update(25, (() => {
    val x_230 = resetData_2;
    val x_231 = x_230.==(null);
    if (x_231)
      {
        val x_232 = meta.deep.runtime.Actor.labelVals("turn");
        x_232.append(1.0);
        positionVar_38 = 24
      }
    else
      positionVar_38 = 26
  }));
  data_39.update(26, (() => {
    val x_233 = resetData_2;
    val x_234 = x_233.!=(null);
    if (x_234)
      {
        val x_235 = resetData_2;
        val x_236 = x_235.arg;
        resetData_0 = x_236;
        resetData_2 = null;
        val x_237 = resetData_0;
        val x_238 = x_237.asInstanceOf[scala.Int];
        bindingMut_3 = x_238;
        val x_239 = bindingMut_3;
        val x_240 = x_239.asInstanceOf[scala.Int];
        this.`location_=`(x_240);
        resetData_0 = ();
        val x_241 = resetData_1.remove(0);
        val x_245 = x_241.find(((x_242: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_243 = x_242._1;
          val x_244 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_243.==(x_244)
        }));
        val x_246 = x_245.get;
        val x_247 = x_246._2;
        positionVar_38 = x_247
      }
    else
      ()
  }));
  data_39.update(27, (() => {
    val x_248 = resetData_0;
    val x_249 = x_248.asInstanceOf[scala.Any];
    bindingMut_27 = x_249;
    val x_250 = bindingMut_27;
    val x_251 = x_250.asInstanceOf[scala.Any];
    val x_252 = listValMut_28;
    val x_253 = x_252.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_253.reply(this, x_251);
    resetData_0 = ();
    positionVar_38 = 15
  }));
  data_39.update(28, (() => positionVar_38 = 29));
  data_39.update(29, (() => {
    val x_254 = this.popRequestMessages;
    val x_255 = x_254.iterator;
    iterMut_29 = x_255;
    positionVar_38 = 16
  }));
  data_39.update(30, (() => {
    val x_256 = iterMut_29;
    val x_257 = x_256.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_258 = x_257.hasNext;
    val x_259 = x_258.`unary_!`;
    if (x_259)
      positionVar_38 = 31
    else
      ()
  }));
  data_39.update(31, (() => if (true)
    positionVar_38 = 2
  else
    positionVar_38 = 32));
  data_39.update(32, (() => {
    val x_260 = true.`unary_!`;
    if (x_260)
      {
        val x_261 = resetData_1.remove(0);
        val x_265 = x_261.find(((x_262: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_263 = x_262._1;
          val x_264 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_263.==(x_264)
        }));
        val x_266 = x_265.get;
        val x_267 = x_266._2;
        positionVar_38 = x_267
      }
    else
      ()
  }));
  data_39.update(33, (() => {
    val x_268 = resetData_0;
    val x_269 = x_268.asInstanceOf[scala.Any];
    bindingMut_27 = x_269;
    val x_270 = bindingMut_27;
    val x_271 = x_270.asInstanceOf[scala.Any];
    val x_272 = listValMut_28;
    val x_273 = x_272.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_273.reply(this, x_271);
    resetData_0 = ();
    positionVar_38 = 15
  }));
  data_39.update(34, (() => positionVar_38 = 35));
  data_39.update(35, (() => {
    positionVar_38 = 36;
    val x_274 = currentTurn;
    val x_275 = x_274.+(1);
    currentTurn = x_275
  }));
  data_39.update(36, (() => positionVar_38 = 35));
  data_39.update(37, (() => {
    val x_276 = resetData_0;
    val x_277 = x_276.asInstanceOf[scala.Boolean];
    bindingMut_34 = x_277;
    positionVar_38 = 38
  }));
  data_39.update(38, (() => {
    val x_278 = bindingMut_34;
    val x_279 = x_278.asInstanceOf[scala.Boolean];
    val x_280 = x_279.`unary_!`;
    if (x_280)
      positionVar_38 = 39
    else
      positionVar_38 = 43
  }));
  data_39.update(39, (() => {
    resetData_0 = 0.0;
    val x_281 = resetData_0;
    val x_282 = x_281.asInstanceOf[scala.Double];
    bindingMut_30 = x_282;
    positionVar_38 = 40
  }));
  data_39.update(40, (() => {
    val x_283 = meta.deep.runtime.Actor.proceedLabel;
    val x_284 = x_283("People");
    val x_285 = bindingMut_30;
    val x_286 = x_285.asInstanceOf[scala.Double];
    val x_287 = x_286.+(x_284);
    resetData_0 = x_287;
    val x_288 = resetData_0;
    val x_289 = x_288.asInstanceOf[scala.Double];
    bindingMut_30 = x_289;
    val x_290 = meta.deep.runtime.Actor.labelVals("People");
    val x_291 = bindingMut_30;
    val x_292 = x_291.asInstanceOf[scala.Double];
    val x_293 = 1.0.-(x_292);
    x_290.append(x_293);
    resetData_0 = ();
    positionVar_38 = 41;
    val x_294 = currentTurn;
    val x_295 = x_294.+(1);
    currentTurn = x_295
  }));
  data_39.update(41, (() => {
    val x_296 = bindingMut_30;
    val x_297 = x_296.asInstanceOf[scala.Double];
    val x_298 = x_297.<(1.0);
    if (x_298)
      positionVar_38 = 40
    else
      positionVar_38 = 42
  }));
  data_39.update(42, (() => {
    val x_299 = bindingMut_30;
    val x_300 = x_299.asInstanceOf[scala.Double];
    val x_301 = x_300.<(1.0);
    val x_302 = x_301.`unary_!`;
    if (x_302)
      positionVar_38 = 29
    else
      ()
  }));
  data_39.update(43, (() => {
    val x_303 = bindingMut_34;
    val x_304 = x_303.asInstanceOf[scala.Boolean];
    if (x_304)
      {
        val x_305 = this.world;
        resetData_0 = x_305;
        val x_306 = resetData_0;
        val x_307 = x_306.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_33 = x_307;
        val x_308 = this.location;
        resetData_0 = x_308;
        val x_309 = resetData_0;
        val x_310 = x_309.asInstanceOf[scala.Int];
        bindingMut_32 = x_310;
        val x_311 = ((this): meta.deep.runtime.Actor).id;
        val x_313 = {
          val x_312 = bindingMut_33;
          x_312.asInstanceOf[generated.meta.example.segregation.WorldMap]
        };
        val x_314 = x_313.id;
        val x_315 = bindingMut_32;
        val x_316 = x_315.asInstanceOf[scala.Int];
        val x_317 = scala.collection.immutable.Nil.::[scala.Any](x_316);
        val x_318 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_317);
        val x_319 = meta.deep.runtime.RequestMessage.apply(x_311, x_314, 6, x_318);
        ((this): meta.deep.runtime.Actor).sendMessage(x_319);
        val x_320 = x_319.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_320, ((response_321: meta.deep.runtime.Message) => {
          val x_322 = response_321.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_322
        }));
        resetData_0 = null;
        positionVar_38 = 44
      }
    else
      ()
  }));
  data_39.update(44, (() => {
    positionVar_38 = 45;
    val x_323 = currentTurn;
    val x_324 = x_323.+(1);
    currentTurn = x_324
  }));
  data_39.update(45, (() => {
    val x_325 = resetData_2;
    val x_326 = x_325.==(null);
    if (x_326)
      {
        val x_327 = meta.deep.runtime.Actor.labelVals("turn");
        x_327.append(1.0);
        positionVar_38 = 44
      }
    else
      positionVar_38 = 46
  }));
  data_39.update(46, (() => {
    val x_328 = resetData_2;
    val x_329 = x_328.!=(null);
    if (x_329)
      {
        val x_330 = resetData_2;
        val x_331 = x_330.arg;
        resetData_0 = x_331;
        resetData_2 = null;
        val x_332 = resetData_0;
        val x_333 = x_332.asInstanceOf[scala.Int];
        bindingMut_31 = x_333;
        val x_334 = bindingMut_31;
        val x_335 = x_334.asInstanceOf[scala.Int];
        this.`location_=`(x_335);
        resetData_0 = ();
        positionVar_38 = 39
      }
    else
      ()
  }));
  data_39.update(47, (() => {
    val x_336 = bindingMut_14;
    val x_337 = x_336.asInstanceOf[scala.Boolean];
    val x_338 = x_337.`unary_!`;
    if (x_338)
      {
        val x_339 = sameView_26;
        resetData_0 = x_339;
        val x_340 = resetData_0;
        val x_341 = x_340.asInstanceOf[scala.Int];
        bindingMut_12 = x_341;
        val x_342 = bindingMut_12;
        val x_343 = x_342.asInstanceOf[scala.Int];
        val x_344 = x_343.toDouble;
        resetData_0 = x_344;
        val x_345 = resetData_0;
        val x_346 = x_345.asInstanceOf[scala.Double];
        bindingMut_11 = x_346;
        val x_347 = bindingMut_23;
        val x_348 = x_347.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_349 = x_348.length;
        resetData_0 = x_349;
        val x_350 = resetData_0;
        val x_351 = x_350.asInstanceOf[scala.Int];
        bindingMut_10 = x_351;
        val x_352 = bindingMut_10;
        val x_353 = x_352.asInstanceOf[scala.Int];
        val x_354 = bindingMut_11;
        val x_355 = x_354.asInstanceOf[scala.Double];
        val x_356 = x_355./(x_353);
        resetData_0 = x_356;
        val x_357 = resetData_0;
        val x_358 = x_357.asInstanceOf[scala.Double];
        bindingMut_9 = x_358;
        val x_359 = bindingMut_9;
        val x_360 = x_359.asInstanceOf[scala.Double];
        this.`similarity_=`(x_360);
        resetData_0 = ();
        val x_361 = this.world;
        resetData_0 = x_361;
        val x_362 = resetData_0;
        val x_363 = x_362.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_8 = x_363;
        val x_364 = ((this): meta.deep.runtime.Actor).id;
        val x_366 = {
          val x_365 = bindingMut_8;
          x_365.asInstanceOf[generated.meta.example.segregation.WorldMap]
        };
        val x_367 = x_366.id;
        val x$1_368 = this.location;
        val x$1_369 = this.similarity;
        val x_370 = scala.collection.immutable.Nil.::[scala.Any](x$1_369);
        val x_371 = x_370.::[scala.Any](x$1_368);
        val x_372 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_371);
        val x_373 = meta.deep.runtime.RequestMessage.apply(x_364, x_367, 7, x_372);
        ((this): meta.deep.runtime.Actor).sendMessage(x_373);
        resetData_0 = scala.None;
        val x_374 = sameView_26;
        resetData_0 = x_374;
        val x_375 = resetData_0;
        val x_376 = x_375.asInstanceOf[scala.Int];
        bindingMut_7 = x_376;
        val x_377 = bindingMut_23;
        val x_378 = x_377.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_379 = x_378.length;
        resetData_0 = x_379;
        val x_380 = resetData_0;
        val x_381 = x_380.asInstanceOf[scala.Int];
        bindingMut_6 = x_381;
        val x_382 = bindingMut_6;
        val x_383 = x_382.asInstanceOf[scala.Int];
        val x_384 = meta.example.segregation.segregationModel.toleranceThreshold.*(x_383);
        resetData_0 = x_384;
        val x_385 = resetData_0;
        val x_386 = x_385.asInstanceOf[scala.Double];
        bindingMut_5 = x_386;
        val x_387 = bindingMut_5;
        val x_388 = x_387.asInstanceOf[scala.Double];
        val x_389 = bindingMut_7;
        val x_390 = x_389.asInstanceOf[scala.Int];
        val x_391 = x_390.<(x_388);
        resetData_0 = x_391;
        val x_392 = resetData_1.remove(0);
        val x_396 = x_392.find(((x_393: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_394 = x_393._1;
          val x_395 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_394.==(x_395)
        }));
        val x_397 = x_396.get;
        val x_398 = x_397._2;
        positionVar_38 = x_398
      }
    else
      ()
  }));
  data_39.update(48, (() => {
    val x_399 = bindingMut_35;
    val x_400 = x_399.asInstanceOf[scala.Boolean];
    if (x_400)
      positionVar_38 = 23
    else
      ();
    val x_401 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_402 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_401, 28);
    val x_403 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_402);
    resetData_1.prepend(x_403)
  }));
  data_39.update(49, (() => {
    val x_404 = true.`unary_!`;
    if (x_404)
      {
        val x_405 = resetData_1.remove(0);
        val x_409 = x_405.find(((x_406: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_407 = x_406._1;
          val x_408 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_407.==(x_408)
        }));
        val x_410 = x_409.get;
        val x_411 = x_410._2;
        positionVar_38 = x_411
      }
    else
      ()
  }));
  data_39
}).apply();
  
  override def run_until(until_413: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_414 = currentTurn;
      val x_415 = x_414.<=(until_413);
      x_415.&&({
        val x_416 = positionVar_38;
        val x_417 = commands_412.length;
        x_416.<(x_417)
      })
    }) 
      {
        val x_418 = positionVar_38;
        val x_419 = commands_412.apply(x_418);
        x_419.apply()
      }
    ;
    this
  }
}
