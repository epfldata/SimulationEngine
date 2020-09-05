package generated.meta.example.segregation

class Person (var world: generated.meta.example.segregation.WorldMap, var view: Int) extends meta.deep.runtime.Actor with Serializable {
  var location: Int = -1
  var similarity: Double = 1.0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Int = 0;
  private var bindingMut_5: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: scala.Int = 0;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Int = 0;
  private var bindingMut_14: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_15: scala.Boolean = false;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: scala.Int = 0;
  private var bindingMut_18: scala.Int = 0;
  private var bindingMut_19: scala.Boolean = false;
  private var bindingMut_20: scala.Int = 0;
  private var bindingMut_21: scala.Int = 0;
  private var listValMut_22: generated.meta.example.segregation.Person = null;
  private var iterMut_23: scala.collection.Iterator[generated.meta.example.segregation.Person] = null;
  private var bindingMut_24: scala.collection.immutable.List[generated.meta.example.segregation.Person] = null;
  private var bindingMut_25: scala.Int = 0;
  private var bindingMut_26: generated.meta.example.segregation.WorldMap = null;
  private var sameView_27: scala.Int = 0;
  private var bindingMut_28: scala.Any = null;
  private var listValMut_29: meta.deep.runtime.RequestMessage = null;
  private var iterMut_30: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_31: scala.Double = 0.0;
  private var bindingMut_32: scala.Int = 0;
  private var bindingMut_33: scala.Int = 0;
  private var bindingMut_34: generated.meta.example.segregation.WorldMap = null;
  private var bindingMut_35: scala.Boolean = false;
  private var bindingMut_36: scala.Boolean = false;
  private var bindingMut_37: scala.Int = 0;
  private var positionVar_39: scala.Int = 0;
  
  val commands_410 = (() => {
  val data_40 = new scala.Array[scala.Function0[scala.Unit]](50);
  data_40.update(0, (() => {
    positionVar_39 = 1;
    val x_41 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_42 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_41, 34);
    val x_43 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_42);
    resetData_1.prepend(x_43)
  }));
  data_40.update(1, (() => if (true)
    positionVar_39 = 2
  else
    positionVar_39 = 49));
  data_40.update(2, (() => {
    val x_44 = this.location;
    resetData_0 = x_44;
    val x_45 = resetData_0;
    val x_46 = x_45.asInstanceOf[scala.Int];
    bindingMut_37 = x_46;
    val x_47 = bindingMut_37;
    val x_48 = x_47.asInstanceOf[scala.Int];
    val x_49 = x_48.==(-1);
    resetData_0 = x_49;
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Boolean];
    bindingMut_36 = x_51;
    positionVar_39 = 3
  }));
  data_40.update(3, (() => {
    val x_52 = bindingMut_36;
    val x_53 = x_52.asInstanceOf[scala.Boolean];
    val x_54 = x_53.`unary_!`;
    if (x_54)
      positionVar_39 = 4
    else
      positionVar_39 = 48;
    val x_55 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_56 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_55, 37);
    val x_57 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_56);
    resetData_1.prepend(x_57)
  }));
  data_40.update(4, (() => {
    resetData_0 = 0;
    val x_58 = resetData_0;
    val x_59 = x_58.asInstanceOf[scala.Int];
    sameView_27 = x_59;
    val x_60 = this.world;
    resetData_0 = x_60;
    val x_61 = resetData_0;
    val x_62 = x_61.asInstanceOf[generated.meta.example.segregation.WorldMap];
    bindingMut_26 = x_62;
    val x_63 = this.location;
    resetData_0 = x_63;
    val x_64 = resetData_0;
    val x_65 = x_64.asInstanceOf[scala.Int];
    bindingMut_25 = x_65;
    val x_66 = ((this): meta.deep.runtime.Actor).id;
    val x_68 = {
      val x_67 = bindingMut_26;
      x_67.asInstanceOf[generated.meta.example.segregation.WorldMap]
    };
    val x_69 = x_68.id;
    val x_70 = bindingMut_25;
    val x_71 = x_70.asInstanceOf[scala.Int];
    val x_72 = scala.collection.immutable.Nil.::[scala.Any](x_71);
    val x_73 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_72);
    val x_74 = meta.deep.runtime.RequestMessage.apply(x_66, x_69, 5, x_73);
    ((this): meta.deep.runtime.Actor).sendMessage(x_74);
    val x_75 = x_74.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_75, ((response_76: meta.deep.runtime.Message) => {
      val x_77 = response_76.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_77
    }));
    resetData_0 = null;
    positionVar_39 = 5
  }));
  data_40.update(5, (() => {
    positionVar_39 = 6;
    val x_78 = currentTurn;
    val x_79 = x_78.+(1);
    currentTurn = x_79
  }));
  data_40.update(6, (() => {
    val x_80 = resetData_2;
    val x_81 = x_80.==(null);
    if (x_81)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_39 = 5
      }
    else
      positionVar_39 = 7
  }));
  data_40.update(7, (() => {
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
        bindingMut_24 = x_87;
        val x_88 = bindingMut_24;
        val x_89 = x_88.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_90 = x_89.iterator;
        iterMut_23 = x_90;
        positionVar_39 = 8
      }
    else
      ()
  }));
  data_40.update(8, (() => {
    val x_91 = iterMut_23;
    val x_92 = x_91.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
    val x_93 = x_92.hasNext;
    if (x_93)
      {
        val x_94 = iterMut_23;
        val x_95 = x_94.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
        val x_96 = x_95.next();
        listValMut_22 = x_96;
        val x_97 = listValMut_22;
        val x_98 = x_97.asInstanceOf[generated.meta.example.segregation.Person];
        val x_99 = x_98.view;
        resetData_0 = x_99;
        val x_100 = resetData_0;
        val x_101 = x_100.asInstanceOf[scala.Int];
        bindingMut_21 = x_101;
        val x_102 = this.view;
        resetData_0 = x_102;
        val x_103 = resetData_0;
        val x_104 = x_103.asInstanceOf[scala.Int];
        bindingMut_20 = x_104;
        val x_105 = bindingMut_20;
        val x_106 = x_105.asInstanceOf[scala.Int];
        val x_107 = bindingMut_21;
        val x_108 = x_107.asInstanceOf[scala.Int];
        val x_109 = x_108.==(x_106);
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Boolean];
        bindingMut_19 = x_111;
        positionVar_39 = 9
      }
    else
      positionVar_39 = 12
  }));
  data_40.update(9, (() => {
    val x_112 = bindingMut_19;
    val x_113 = x_112.asInstanceOf[scala.Boolean];
    val x_114 = x_113.`unary_!`;
    if (x_114)
      positionVar_39 = 10
    else
      positionVar_39 = 11
  }));
  data_40.update(10, (() => positionVar_39 = 8));
  data_40.update(11, (() => {
    val x_115 = bindingMut_19;
    val x_116 = x_115.asInstanceOf[scala.Boolean];
    if (x_116)
      {
        val x_117 = sameView_27;
        resetData_0 = x_117;
        val x_118 = resetData_0;
        val x_119 = x_118.asInstanceOf[scala.Int];
        bindingMut_18 = x_119;
        val x_120 = bindingMut_18;
        val x_121 = x_120.asInstanceOf[scala.Int];
        val x_122 = x_121.+(1);
        resetData_0 = x_122;
        val x_123 = resetData_0;
        val x_124 = x_123.asInstanceOf[scala.Int];
        bindingMut_17 = x_124;
        val x_125 = bindingMut_17;
        val x_126 = x_125.asInstanceOf[scala.Int];
        sameView_27 = x_126;
        resetData_0 = ();
        positionVar_39 = 10
      }
    else
      ()
  }));
  data_40.update(12, (() => {
    val x_127 = iterMut_23;
    val x_128 = x_127.asInstanceOf[scala.collection.Iterator[generated.meta.example.segregation.Person]];
    val x_129 = x_128.hasNext;
    val x_130 = x_129.`unary_!`;
    if (x_130)
      {
        val x_131 = bindingMut_24;
        val x_132 = x_131.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_133 = x_132.length;
        resetData_0 = x_133;
        val x_134 = resetData_0;
        val x_135 = x_134.asInstanceOf[scala.Int];
        bindingMut_16 = x_135;
        val x_136 = bindingMut_16;
        val x_137 = x_136.asInstanceOf[scala.Int];
        val x_138 = x_137.==(0);
        resetData_0 = x_138;
        val x_139 = resetData_0;
        val x_140 = x_139.asInstanceOf[scala.Boolean];
        bindingMut_15 = x_140;
        positionVar_39 = 13
      }
    else
      ()
  }));
  data_40.update(13, (() => {
    val x_141 = bindingMut_15;
    val x_142 = x_141.asInstanceOf[scala.Boolean];
    if (x_142)
      {
        this.`similarity_=`(1.0);
        resetData_0 = ();
        val x_143 = this.world;
        resetData_0 = x_143;
        val x_144 = resetData_0;
        val x_145 = x_144.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_14 = x_145;
        val x_146 = ((this): meta.deep.runtime.Actor).id;
        val x_148 = {
          val x_147 = bindingMut_14;
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
        positionVar_39 = x_162
      }
    else
      positionVar_39 = 47
  }));
  data_40.update(14, (() => {
    val x_163 = resetData_0;
    val x_164 = x_163.asInstanceOf[scala.Any];
    bindingMut_28 = x_164;
    val x_165 = bindingMut_28;
    val x_166 = x_165.asInstanceOf[scala.Any];
    val x_167 = listValMut_29;
    val x_168 = x_167.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_168.reply(this, x_166);
    resetData_0 = ();
    positionVar_39 = 15
  }));
  data_40.update(15, (() => positionVar_39 = 16));
  data_40.update(16, (() => {
    val x_169 = iterMut_30;
    val x_170 = x_169.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_171 = x_170.hasNext;
    if (x_171)
      {
        val x_172 = iterMut_30;
        val x_173 = x_172.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_174 = x_173.next();
        listValMut_29 = x_174;
        positionVar_39 = 17
      }
    else
      positionVar_39 = 30
  }));
  data_40.update(17, (() => {
    val x_175 = listValMut_29;
    val x_176 = x_175.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_177 = x_176.methodId;
    val x_178 = x_177.==(0);
    val x_179 = x_178.`unary_!`;
    if (x_179)
      positionVar_39 = 18
    else
      positionVar_39 = 22
  }));
  data_40.update(18, (() => {
    val x_180 = listValMut_29;
    val x_181 = x_180.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_182 = x_181.methodId;
    val x_183 = x_182.==(1);
    val x_184 = x_183.`unary_!`;
    if (x_184)
      positionVar_39 = 19
    else
      positionVar_39 = 21
  }));
  data_40.update(19, (() => {
    val x_185 = listValMut_29;
    val x_186 = x_185.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_187 = x_186.methodId;
    val x_188 = x_187.==(2);
    val x_189 = x_188.`unary_!`;
    if (x_189)
      {
        val x_190 = listValMut_29;
        val x_191 = x_190.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_192 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_191);
        val x_193 = this.addReceiveMessages(x_192);
        resetData_0 = x_193;
        positionVar_39 = 15
      }
    else
      positionVar_39 = 20
  }));
  data_40.update(20, (() => {
    val x_194 = listValMut_29;
    val x_195 = x_194.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_196 = x_195.methodId;
    val x_197 = x_196.==(2);
    if (x_197)
      positionVar_39 = 1
    else
      ();
    val x_198 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_199 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_198, 33);
    val x_200 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_199);
    resetData_1.prepend(x_200)
  }));
  data_40.update(21, (() => {
    val x_201 = listValMut_29;
    val x_202 = x_201.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_203 = x_202.methodId;
    val x_204 = x_203.==(1);
    if (x_204)
      positionVar_39 = 4
    else
      ();
    val x_205 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_206 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_205, 14);
    val x_207 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_206);
    resetData_1.prepend(x_207)
  }));
  data_40.update(22, (() => {
    val x_208 = listValMut_29;
    val x_209 = x_208.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_210 = x_209.methodId;
    val x_211 = x_210.==(0);
    if (x_211)
      positionVar_39 = 23
    else
      ();
    val x_212 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_213 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_212, 27);
    val x_214 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_213);
    resetData_1.prepend(x_214)
  }));
  data_40.update(23, (() => {
    val x_215 = this.world;
    resetData_0 = x_215;
    val x_216 = resetData_0;
    val x_217 = x_216.asInstanceOf[generated.meta.example.segregation.WorldMap];
    bindingMut_5 = x_217;
    val x_218 = ((this): meta.deep.runtime.Actor).id;
    val x_220 = {
      val x_219 = bindingMut_5;
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
    positionVar_39 = 24
  }));
  data_40.update(24, (() => {
    positionVar_39 = 25;
    val x_228 = currentTurn;
    val x_229 = x_228.+(1);
    currentTurn = x_229
  }));
  data_40.update(25, (() => {
    val x_230 = resetData_2;
    val x_231 = x_230.==(null);
    if (x_231)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_39 = 24
      }
    else
      positionVar_39 = 26
  }));
  data_40.update(26, (() => {
    val x_232 = resetData_2;
    val x_233 = x_232.!=(null);
    if (x_233)
      {
        val x_234 = resetData_2;
        val x_235 = x_234.arg;
        resetData_0 = x_235;
        resetData_2 = null;
        val x_236 = resetData_0;
        val x_237 = x_236.asInstanceOf[scala.Int];
        bindingMut_4 = x_237;
        val x_238 = bindingMut_4;
        val x_239 = x_238.asInstanceOf[scala.Int];
        this.`location_=`(x_239);
        resetData_0 = ();
        val x_240 = resetData_1.remove(0);
        val x_244 = x_240.find(((x_241: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_242 = x_241._1;
          val x_243 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_242.==(x_243)
        }));
        val x_245 = x_244.get;
        val x_246 = x_245._2;
        positionVar_39 = x_246
      }
    else
      ()
  }));
  data_40.update(27, (() => {
    val x_247 = resetData_0;
    val x_248 = x_247.asInstanceOf[scala.Any];
    bindingMut_28 = x_248;
    val x_249 = bindingMut_28;
    val x_250 = x_249.asInstanceOf[scala.Any];
    val x_251 = listValMut_29;
    val x_252 = x_251.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_252.reply(this, x_250);
    resetData_0 = ();
    positionVar_39 = 15
  }));
  data_40.update(28, (() => positionVar_39 = 29));
  data_40.update(29, (() => {
    val x_253 = this.popRequestMessages;
    val x_254 = x_253.iterator;
    iterMut_30 = x_254;
    positionVar_39 = 16
  }));
  data_40.update(30, (() => {
    val x_255 = iterMut_30;
    val x_256 = x_255.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_257 = x_256.hasNext;
    val x_258 = x_257.`unary_!`;
    if (x_258)
      positionVar_39 = 31
    else
      ()
  }));
  data_40.update(31, (() => if (true)
    positionVar_39 = 2
  else
    positionVar_39 = 32));
  data_40.update(32, (() => {
    val x_259 = true.`unary_!`;
    if (x_259)
      {
        val x_260 = resetData_1.remove(0);
        val x_264 = x_260.find(((x_261: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_262 = x_261._1;
          val x_263 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_262.==(x_263)
        }));
        val x_265 = x_264.get;
        val x_266 = x_265._2;
        positionVar_39 = x_266
      }
    else
      ()
  }));
  data_40.update(33, (() => {
    val x_267 = resetData_0;
    val x_268 = x_267.asInstanceOf[scala.Any];
    bindingMut_28 = x_268;
    val x_269 = bindingMut_28;
    val x_270 = x_269.asInstanceOf[scala.Any];
    val x_271 = listValMut_29;
    val x_272 = x_271.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_272.reply(this, x_270);
    resetData_0 = ();
    positionVar_39 = 15
  }));
  data_40.update(34, (() => positionVar_39 = 35));
  data_40.update(35, (() => {
    positionVar_39 = 36;
    val x_273 = currentTurn;
    val x_274 = x_273.+(1);
    currentTurn = x_274
  }));
  data_40.update(36, (() => positionVar_39 = 35));
  data_40.update(37, (() => {
    val x_275 = resetData_0;
    val x_276 = x_275.asInstanceOf[scala.Boolean];
    bindingMut_35 = x_276;
    positionVar_39 = 38
  }));
  data_40.update(38, (() => {
    val x_277 = bindingMut_35;
    val x_278 = x_277.asInstanceOf[scala.Boolean];
    val x_279 = x_278.`unary_!`;
    if (x_279)
      positionVar_39 = 39
    else
      positionVar_39 = 43
  }));
  data_40.update(39, (() => {
    resetData_0 = 0.0;
    val x_280 = resetData_0;
    val x_281 = x_280.asInstanceOf[scala.Double];
    bindingMut_31 = x_281;
    positionVar_39 = 40
  }));
  data_40.update(40, (() => {
    val x_282 = meta.deep.runtime.Actor.proceedLabel;
    val x_283 = x_282("People");
    val x_284 = bindingMut_31;
    val x_285 = x_284.asInstanceOf[scala.Double];
    val x_286 = x_285.+(x_283);
    resetData_0 = x_286;
    val x_287 = resetData_0;
    val x_288 = x_287.asInstanceOf[scala.Double];
    bindingMut_31 = x_288;
    val x_289 = meta.deep.runtime.Actor.labelVals("People");
    val x_290 = bindingMut_31;
    val x_291 = x_290.asInstanceOf[scala.Double];
    val x_292 = 1.0.-(x_291);
    x_289.append(x_292);
    resetData_0 = ();
    positionVar_39 = 41;
    val x_293 = currentTurn;
    val x_294 = x_293.+(1);
    currentTurn = x_294
  }));
  data_40.update(41, (() => {
    val x_295 = bindingMut_31;
    val x_296 = x_295.asInstanceOf[scala.Double];
    val x_297 = x_296.<(1.0);
    if (x_297)
      positionVar_39 = 40
    else
      positionVar_39 = 42
  }));
  data_40.update(42, (() => {
    val x_298 = bindingMut_31;
    val x_299 = x_298.asInstanceOf[scala.Double];
    val x_300 = x_299.<(1.0);
    val x_301 = x_300.`unary_!`;
    if (x_301)
      positionVar_39 = 29
    else
      ()
  }));
  data_40.update(43, (() => {
    val x_302 = bindingMut_35;
    val x_303 = x_302.asInstanceOf[scala.Boolean];
    if (x_303)
      {
        val x_304 = this.world;
        resetData_0 = x_304;
        val x_305 = resetData_0;
        val x_306 = x_305.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_34 = x_306;
        val x_307 = this.location;
        resetData_0 = x_307;
        val x_308 = resetData_0;
        val x_309 = x_308.asInstanceOf[scala.Int];
        bindingMut_33 = x_309;
        val x_310 = ((this): meta.deep.runtime.Actor).id;
        val x_312 = {
          val x_311 = bindingMut_34;
          x_311.asInstanceOf[generated.meta.example.segregation.WorldMap]
        };
        val x_313 = x_312.id;
        val x_314 = bindingMut_33;
        val x_315 = x_314.asInstanceOf[scala.Int];
        val x_316 = scala.collection.immutable.Nil.::[scala.Any](x_315);
        val x_317 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_316);
        val x_318 = meta.deep.runtime.RequestMessage.apply(x_310, x_313, 6, x_317);
        ((this): meta.deep.runtime.Actor).sendMessage(x_318);
        val x_319 = x_318.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_319, ((response_320: meta.deep.runtime.Message) => {
          val x_321 = response_320.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_321
        }));
        resetData_0 = null;
        positionVar_39 = 44
      }
    else
      ()
  }));
  data_40.update(44, (() => {
    positionVar_39 = 45;
    val x_322 = currentTurn;
    val x_323 = x_322.+(1);
    currentTurn = x_323
  }));
  data_40.update(45, (() => {
    val x_324 = resetData_2;
    val x_325 = x_324.==(null);
    if (x_325)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_39 = 44
      }
    else
      positionVar_39 = 46
  }));
  data_40.update(46, (() => {
    val x_326 = resetData_2;
    val x_327 = x_326.!=(null);
    if (x_327)
      {
        val x_328 = resetData_2;
        val x_329 = x_328.arg;
        resetData_0 = x_329;
        resetData_2 = null;
        val x_330 = resetData_0;
        val x_331 = x_330.asInstanceOf[scala.Int];
        bindingMut_32 = x_331;
        val x_332 = bindingMut_32;
        val x_333 = x_332.asInstanceOf[scala.Int];
        this.`location_=`(x_333);
        resetData_0 = ();
        positionVar_39 = 39
      }
    else
      ()
  }));
  data_40.update(47, (() => {
    val x_334 = bindingMut_15;
    val x_335 = x_334.asInstanceOf[scala.Boolean];
    val x_336 = x_335.`unary_!`;
    if (x_336)
      {
        val x_337 = sameView_27;
        resetData_0 = x_337;
        val x_338 = resetData_0;
        val x_339 = x_338.asInstanceOf[scala.Int];
        bindingMut_13 = x_339;
        val x_340 = bindingMut_13;
        val x_341 = x_340.asInstanceOf[scala.Int];
        val x_342 = x_341.toDouble;
        resetData_0 = x_342;
        val x_343 = resetData_0;
        val x_344 = x_343.asInstanceOf[scala.Double];
        bindingMut_12 = x_344;
        val x_345 = bindingMut_24;
        val x_346 = x_345.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_347 = x_346.length;
        resetData_0 = x_347;
        val x_348 = resetData_0;
        val x_349 = x_348.asInstanceOf[scala.Int];
        bindingMut_11 = x_349;
        val x_350 = bindingMut_11;
        val x_351 = x_350.asInstanceOf[scala.Int];
        val x_352 = bindingMut_12;
        val x_353 = x_352.asInstanceOf[scala.Double];
        val x_354 = x_353./(x_351);
        resetData_0 = x_354;
        val x_355 = resetData_0;
        val x_356 = x_355.asInstanceOf[scala.Double];
        bindingMut_10 = x_356;
        val x_357 = bindingMut_10;
        val x_358 = x_357.asInstanceOf[scala.Double];
        this.`similarity_=`(x_358);
        resetData_0 = ();
        val x_359 = this.world;
        resetData_0 = x_359;
        val x_360 = resetData_0;
        val x_361 = x_360.asInstanceOf[generated.meta.example.segregation.WorldMap];
        bindingMut_9 = x_361;
        val x_362 = ((this): meta.deep.runtime.Actor).id;
        val x_364 = {
          val x_363 = bindingMut_9;
          x_363.asInstanceOf[generated.meta.example.segregation.WorldMap]
        };
        val x_365 = x_364.id;
        val x$1_366 = this.location;
        val x$1_367 = this.similarity;
        val x_368 = scala.collection.immutable.Nil.::[scala.Any](x$1_367);
        val x_369 = x_368.::[scala.Any](x$1_366);
        val x_370 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_369);
        val x_371 = meta.deep.runtime.RequestMessage.apply(x_362, x_365, 7, x_370);
        ((this): meta.deep.runtime.Actor).sendMessage(x_371);
        resetData_0 = scala.None;
        val x_372 = sameView_27;
        resetData_0 = x_372;
        val x_373 = resetData_0;
        val x_374 = x_373.asInstanceOf[scala.Int];
        bindingMut_8 = x_374;
        val x_375 = bindingMut_24;
        val x_376 = x_375.asInstanceOf[scala.collection.immutable.List[generated.meta.example.segregation.Person]];
        val x_377 = x_376.length;
        resetData_0 = x_377;
        val x_378 = resetData_0;
        val x_379 = x_378.asInstanceOf[scala.Int];
        bindingMut_7 = x_379;
        val x_380 = bindingMut_7;
        val x_381 = x_380.asInstanceOf[scala.Int];
        val x_382 = meta.example.segregation.segregationModel.toleranceThreshold.*(x_381);
        resetData_0 = x_382;
        val x_383 = resetData_0;
        val x_384 = x_383.asInstanceOf[scala.Double];
        bindingMut_6 = x_384;
        val x_385 = bindingMut_6;
        val x_386 = x_385.asInstanceOf[scala.Double];
        val x_387 = bindingMut_8;
        val x_388 = x_387.asInstanceOf[scala.Int];
        val x_389 = x_388.<(x_386);
        resetData_0 = x_389;
        val x_390 = resetData_1.remove(0);
        val x_394 = x_390.find(((x_391: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_392 = x_391._1;
          val x_393 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_392.==(x_393)
        }));
        val x_395 = x_394.get;
        val x_396 = x_395._2;
        positionVar_39 = x_396
      }
    else
      ()
  }));
  data_40.update(48, (() => {
    val x_397 = bindingMut_36;
    val x_398 = x_397.asInstanceOf[scala.Boolean];
    if (x_398)
      positionVar_39 = 23
    else
      ();
    val x_399 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_400 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_399, 28);
    val x_401 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_400);
    resetData_1.prepend(x_401)
  }));
  data_40.update(49, (() => {
    val x_402 = true.`unary_!`;
    if (x_402)
      {
        val x_403 = resetData_1.remove(0);
        val x_407 = x_403.find(((x_404: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_405 = x_404._1;
          val x_406 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_405.==(x_406)
        }));
        val x_408 = x_407.get;
        val x_409 = x_408._2;
        positionVar_39 = x_409
      }
    else
      ()
  }));
  data_40
}).apply();
  
  override def run_until(until_411: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_412 = currentTurn;
      val x_413 = x_412.<=(until_411);
      x_413.&&({
        val x_414 = positionVar_39;
        val x_415 = commands_410.length;
        x_414.<(x_415)
      })
    }) 
      {
        val x_416 = positionVar_39;
        val x_417 = commands_410.apply(x_416);
        x_417.apply()
      }
    ;
    this
  }
}
