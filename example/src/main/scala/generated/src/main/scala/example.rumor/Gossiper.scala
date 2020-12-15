package generated.example.rumor

class Gossiper (val env: generated.example.rumor.Env, var heardRumor: Boolean, val spreadProb: Double) extends meta.deep.runtime.Actor with example.rumor.Person {
  var reported: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_4: scala.Double = 0.0;
  private var bindingMut_5: scala.Double = 0.0;
  private var bindingMut_6: scala.Boolean = false;
  private var bindingMut_7: scala.Boolean = false;
  private var bindingMut_8: scala.Boolean = false;
  private var bindingMut_9: scala.Boolean = false;
  private var methodArgsMut_10: generated.example.rumor.Gossiper = null;
  private var bindingMut_11: scala.Any = null;
  private var listValMut_12: meta.deep.runtime.RequestMessage = null;
  private var iterMut_13: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_14: scala.Double = 0.0;
  private var bindingMut_15: scala.Double = 0.0;
  private var bindingMut_16: generated.example.rumor.Gossiper = null;
  private var bindingMut_17: scala.Boolean = false;
  private var listValMut_18: example.rumor.Person = null;
  private var iterMut_19: scala.collection.Iterator[example.rumor.Person] = null;
  private var bindingMut_20: scala.collection.immutable.List[example.rumor.Person] = null;
  private var bindingMut_21: scala.collection.mutable.Set[example.rumor.Person] = null;
  private var bindingMut_22: scala.Boolean = false;
  private var positionVar_24: scala.Int = 0;
  
  val commands_377 = (() => {
  val data_25 = new scala.Array[scala.Function0[scala.Unit]](50);
  data_25.update(0, (() => {
    positionVar_24 = 1;
    val x_26 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_27 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_26, 43);
    val x_28 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_27);
    resetData_1.prepend(x_28)
  }));
  data_25.update(1, (() => if (true)
    positionVar_24 = 2
  else
    positionVar_24 = 49));
  data_25.update(2, (() => {
    positionVar_24 = 3;
    val x_29 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_30 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_29, 46);
    val x_31 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_30);
    resetData_1.prepend(x_31)
  }));
  data_25.update(3, (() => {
    val x_32 = this.heardRumor;
    resetData_0 = x_32;
    val x_33 = resetData_0;
    val x_34 = x_33.asInstanceOf[scala.Boolean];
    bindingMut_9 = x_34;
    positionVar_24 = 4
  }));
  data_25.update(4, (() => {
    val x_35 = bindingMut_9;
    val x_36 = x_35.asInstanceOf[scala.Boolean];
    val x_37 = x_36.`unary_!`;
    if (x_37)
      {
        resetData_0 = false;
        positionVar_24 = 5
      }
    else
      positionVar_24 = 48
  }));
  data_25.update(5, (() => {
    val x_38 = resetData_0;
    val x_39 = x_38.asInstanceOf[scala.Boolean];
    bindingMut_7 = x_39;
    positionVar_24 = 6
  }));
  data_25.update(6, (() => {
    val x_40 = bindingMut_7;
    val x_41 = x_40.asInstanceOf[scala.Boolean];
    val x_42 = x_41.`unary_!`;
    if (x_42)
      {
        val x_43 = resetData_1.remove(0);
        val x_47 = x_43.find(((x_44: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_45 = x_44._1;
          val x_46 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_45.==(x_46)
        }));
        val x_48 = x_47.get;
        val x_49 = x_48._2;
        positionVar_24 = x_49
      }
    else
      positionVar_24 = 47
  }));
  data_25.update(7, (() => {
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Any];
    bindingMut_11 = x_51;
    val x_52 = bindingMut_11;
    val x_53 = x_52.asInstanceOf[scala.Any];
    val x_54 = listValMut_12;
    val x_55 = x_54.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_55.reply(this, x_53);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(8, (() => positionVar_24 = 9));
  data_25.update(9, (() => {
    val x_56 = iterMut_13;
    val x_57 = x_56.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_58 = x_57.hasNext;
    if (x_58)
      {
        val x_59 = iterMut_13;
        val x_60 = x_59.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_61 = x_60.next();
        listValMut_12 = x_61;
        positionVar_24 = 10
      }
    else
      positionVar_24 = 39
  }));
  data_25.update(10, (() => {
    val x_62 = listValMut_12;
    val x_63 = x_62.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_64 = x_63.methodId;
    val x_65 = x_64.==(0);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      positionVar_24 = 11
    else
      positionVar_24 = 31
  }));
  data_25.update(11, (() => {
    val x_67 = listValMut_12;
    val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_69 = x_68.methodId;
    val x_70 = x_69.==(1);
    val x_71 = x_70.`unary_!`;
    if (x_71)
      positionVar_24 = 12
    else
      positionVar_24 = 30
  }));
  data_25.update(12, (() => {
    val x_72 = listValMut_12;
    val x_73 = x_72.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_74 = x_73.methodId;
    val x_75 = x_74.==(2);
    val x_76 = x_75.`unary_!`;
    if (x_76)
      positionVar_24 = 13
    else
      positionVar_24 = 29
  }));
  data_25.update(13, (() => {
    val x_77 = listValMut_12;
    val x_78 = x_77.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_79 = x_78.methodId;
    val x_80 = x_79.==(3);
    val x_81 = x_80.`unary_!`;
    if (x_81)
      positionVar_24 = 14
    else
      positionVar_24 = 16
  }));
  data_25.update(14, (() => {
    val x_82 = listValMut_12;
    val x_83 = x_82.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_84 = x_83.methodId;
    val x_85 = x_84.==(4);
    val x_86 = x_85.`unary_!`;
    if (x_86)
      {
        val x_87 = listValMut_12;
        val x_88 = x_87.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_89 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_88);
        val x_90 = this.addReceiveMessages(x_89);
        resetData_0 = x_90;
        positionVar_24 = 8
      }
    else
      positionVar_24 = 15
  }));
  data_25.update(15, (() => {
    val x_91 = listValMut_12;
    val x_92 = x_91.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_93 = x_92.methodId;
    val x_94 = x_93.==(4);
    if (x_94)
      positionVar_24 = 1
    else
      ();
    val x_95 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_96 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_95, 42);
    val x_97 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_96);
    resetData_1.prepend(x_97)
  }));
  data_25.update(16, (() => {
    val x_98 = listValMut_12;
    val x_99 = x_98.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_100 = x_99.methodId;
    val x_101 = x_100.==(3);
    if (x_101)
      {
        val x_102 = listValMut_12;
        val x_103 = x_102.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_104 = x_103.argss;
        val x_105 = x_104(0);
        val x_106 = x_105(0);
        x_3.prepend(x_106);
        val x_107 = listValMut_12;
        val x_108 = x_107.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_109 = x_108.argss;
        val x_110 = x_109(0);
        val x_111 = x_110(0);
        val x_112 = x_111.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_112;
        positionVar_24 = 17
      }
    else
      ();
    val x_113 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_114 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_113, 18);
    val x_115 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_114);
    resetData_1.prepend(x_115)
  }));
  data_25.update(17, (() => {
    val x_116 = ((this): meta.deep.runtime.Actor).id;
    val x_118 = {
      val x_117 = methodArgsMut_10;
      x_117.asInstanceOf[generated.example.rumor.Gossiper]
    };
    val x_119 = x_118.id;
    val x_120 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_121 = meta.deep.runtime.RequestMessage.apply(x_116, x_119, 1, x_120);
    val x_122 = x_121.future;
    val x_123 = x_122.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
    x_121.`future_=`(x_123);
    ((this): meta.deep.runtime.Actor).sendMessage(x_121);
    val x_124 = x_121.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_124, ((response_125: meta.deep.runtime.Message) => {
      val x_126 = x_121.future;
      val x_127 = response_125.asInstanceOf[meta.deep.runtime.ResponseMessage];
      val x_128 = x_127.arg;
      val x_129 = x_126.setValue[scala.Any](x_128);
      val x_130 = x_129.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
      x_121.`future_=`(x_130);
      val x_131 = ((this): meta.deep.runtime.Actor).async_messages;
      val x_132 = x_121.future;
      val x_133 = x_132.id;
      val x_134 = scala.Predef.ArrowAssoc[java.lang.String](x_133);
      val x_135 = x_121.future;
      val x_136 = x_134.->[meta.deep.runtime.Future[scala.Any]](x_135);
      val x_137 = x_131.+[meta.deep.runtime.Future[scala.Any]](x_136);
      ((this): meta.deep.runtime.Actor).`async_messages_=`(x_137)
    }));
    val x_138 = x_121.future;
    val x_139 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_138);
    resetData_0 = x_139;
    val x_140 = resetData_1.remove(0);
    val x_144 = x_140.find(((x_141: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_142 = x_141._1;
      val x_143 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_142.==(x_143)
    }));
    val x_145 = x_144.get;
    val x_146 = x_145._2;
    positionVar_24 = x_146
  }));
  data_25.update(18, (() => {
    x_3.remove(0);
    val x_147 = x_3.isEmpty;
    val x_148 = x_147.`unary_!`;
    if (x_148)
      {
        val x_149 = x_3(0);
        val x_150 = x_149.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_150
      }
    else
      ();
    val x_151 = resetData_0;
    val x_152 = x_151.asInstanceOf[scala.Any];
    bindingMut_11 = x_152;
    val x_153 = bindingMut_11;
    val x_154 = x_153.asInstanceOf[scala.Any];
    val x_155 = listValMut_12;
    val x_156 = x_155.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_156.reply(this, x_154);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(19, (() => {
    x_3.remove(0);
    val x_157 = x_3.isEmpty;
    val x_158 = x_157.`unary_!`;
    if (x_158)
      {
        val x_159 = x_3(0);
        val x_160 = x_159.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_160
      }
    else
      ();
    positionVar_24 = 20
  }));
  data_25.update(20, (() => positionVar_24 = 21));
  data_25.update(21, (() => {
    val x_161 = iterMut_19;
    val x_162 = x_161.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
    val x_163 = x_162.hasNext;
    val x_164 = x_163.`unary_!`;
    if (x_164)
      positionVar_24 = 22
    else
      positionVar_24 = 26
  }));
  data_25.update(22, (() => {
    val x_165 = 1.toDouble;
    resetData_0 = x_165;
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[scala.Double];
    bindingMut_15 = x_167;
    resetData_0 = 0.0;
    val x_168 = resetData_0;
    val x_169 = x_168.asInstanceOf[scala.Double];
    bindingMut_14 = x_169;
    positionVar_24 = 23
  }));
  data_25.update(23, (() => {
    val x_170 = meta.deep.runtime.Actor.proceedLabel;
    val x_171 = x_170("turn");
    val x_172 = bindingMut_14;
    val x_173 = x_172.asInstanceOf[scala.Double];
    val x_174 = x_173.+(x_171);
    resetData_0 = x_174;
    val x_175 = resetData_0;
    val x_176 = x_175.asInstanceOf[scala.Double];
    bindingMut_14 = x_176;
    val x_177 = meta.deep.runtime.Actor.labelVals("turn");
    val x_178 = bindingMut_14;
    val x_179 = x_178.asInstanceOf[scala.Double];
    val x_180 = bindingMut_15;
    val x_181 = x_180.asInstanceOf[scala.Double];
    val x_182 = x_181.-(x_179);
    x_177.append(x_182);
    resetData_0 = ();
    positionVar_24 = 24;
    val x_183 = currentTurn;
    val x_184 = x_183.+(1);
    currentTurn = x_184
  }));
  data_25.update(24, (() => {
    val x_185 = bindingMut_14;
    val x_186 = x_185.asInstanceOf[scala.Double];
    val x_187 = bindingMut_15;
    val x_188 = x_187.asInstanceOf[scala.Double];
    val x_189 = x_186.<(x_188);
    if (x_189)
      positionVar_24 = 23
    else
      positionVar_24 = 25
  }));
  data_25.update(25, (() => {
    val x_190 = bindingMut_14;
    val x_191 = x_190.asInstanceOf[scala.Double];
    val x_192 = bindingMut_15;
    val x_193 = x_192.asInstanceOf[scala.Double];
    val x_194 = x_191.<(x_193);
    val x_195 = x_194.`unary_!`;
    if (x_195)
      {
        val x_196 = this.popRequestMessages;
        val x_197 = x_196.iterator;
        iterMut_13 = x_197;
        positionVar_24 = 9
      }
    else
      ()
  }));
  data_25.update(26, (() => {
    val x_198 = iterMut_19;
    val x_199 = x_198.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
    val x_200 = x_199.hasNext;
    if (x_200)
      {
        val x_201 = iterMut_19;
        val x_202 = x_201.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
        val x_203 = x_202.next();
        listValMut_18 = x_203;
        val x_204 = scala.util.Random.nextBoolean();
        resetData_0 = x_204;
        val x_205 = resetData_0;
        val x_206 = x_205.asInstanceOf[scala.Boolean];
        bindingMut_17 = x_206;
        positionVar_24 = 27
      }
    else
      ()
  }));
  data_25.update(27, (() => {
    val x_207 = bindingMut_17;
    val x_208 = x_207.asInstanceOf[scala.Boolean];
    val x_209 = x_208.`unary_!`;
    if (x_209)
      positionVar_24 = 20
    else
      positionVar_24 = 28
  }));
  data_25.update(28, (() => {
    val x_210 = bindingMut_17;
    val x_211 = x_210.asInstanceOf[scala.Boolean];
    if (x_211)
      {
        val x_212 = listValMut_18;
        val x_213 = x_212.asInstanceOf[example.rumor.Person];
        val x_214 = x_213.asInstanceOf[generated.example.rumor.Gossiper];
        resetData_0 = x_214;
        val x_215 = resetData_0;
        val x_216 = x_215.asInstanceOf[generated.example.rumor.Gossiper];
        bindingMut_16 = x_216;
        val x_217 = bindingMut_16;
        val x_218 = x_217.asInstanceOf[generated.example.rumor.Gossiper];
        x_3.prepend(x_218);
        val x_219 = bindingMut_16;
        val x_220 = x_219.asInstanceOf[generated.example.rumor.Gossiper];
        val x_221 = x_220.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_221;
        positionVar_24 = 17
      }
    else
      ();
    val x_222 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_223 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_222, 19);
    val x_224 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_223);
    resetData_1.prepend(x_224)
  }));
  data_25.update(29, (() => {
    val x_225 = listValMut_12;
    val x_226 = x_225.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_227 = x_226.methodId;
    val x_228 = x_227.==(2);
    if (x_228)
      positionVar_24 = 3
    else
      ();
    val x_229 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_230 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_229, 7);
    val x_231 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_230);
    resetData_1.prepend(x_231)
  }));
  data_25.update(30, (() => {
    val x_232 = listValMut_12;
    val x_233 = x_232.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_234 = x_233.methodId;
    val x_235 = x_234.==(1);
    if (x_235)
      {
        this.`heardRumor_=`(true);
        resetData_0 = ();
        val x_236 = resetData_0;
        val x_237 = x_236.asInstanceOf[scala.Any];
        bindingMut_11 = x_237;
        val x_238 = bindingMut_11;
        val x_239 = x_238.asInstanceOf[scala.Any];
        val x_240 = listValMut_12;
        val x_241 = x_240.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_241.reply(this, x_239);
        resetData_0 = ();
        positionVar_24 = 8
      }
    else
      ()
  }));
  data_25.update(31, (() => {
    val x_242 = listValMut_12;
    val x_243 = x_242.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_244 = x_243.methodId;
    val x_245 = x_244.==(0);
    if (x_245)
      positionVar_24 = 32
    else
      ();
    val x_246 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_247 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_246, 34);
    val x_248 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_247);
    resetData_1.prepend(x_248)
  }));
  data_25.update(32, (() => {
    val x_249 = this.heardRumor;
    resetData_0 = x_249;
    val x_250 = resetData_0;
    val x_251 = x_250.asInstanceOf[scala.Boolean];
    bindingMut_6 = x_251;
    positionVar_24 = 33
  }));
  data_25.update(33, (() => {
    val x_252 = bindingMut_6;
    val x_253 = x_252.asInstanceOf[scala.Boolean];
    val x_254 = x_253.`unary_!`;
    if (x_254)
      {
        resetData_0 = false;
        val x_255 = resetData_1.remove(0);
        val x_259 = x_255.find(((x_256: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_257 = x_256._1;
          val x_258 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_257.==(x_258)
        }));
        val x_260 = x_259.get;
        val x_261 = x_260._2;
        positionVar_24 = x_261
      }
    else
      positionVar_24 = 38
  }));
  data_25.update(34, (() => {
    val x_262 = resetData_0;
    val x_263 = x_262.asInstanceOf[scala.Any];
    bindingMut_11 = x_263;
    val x_264 = bindingMut_11;
    val x_265 = x_264.asInstanceOf[scala.Any];
    val x_266 = listValMut_12;
    val x_267 = x_266.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_267.reply(this, x_265);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(35, (() => {
    val x_268 = resetData_0;
    val x_269 = x_268.asInstanceOf[scala.Boolean];
    bindingMut_22 = x_269;
    positionVar_24 = 36
  }));
  data_25.update(36, (() => {
    val x_270 = bindingMut_22;
    val x_271 = x_270.asInstanceOf[scala.Boolean];
    val x_272 = x_271.`unary_!`;
    if (x_272)
      positionVar_24 = 22
    else
      positionVar_24 = 37
  }));
  data_25.update(37, (() => {
    val x_273 = bindingMut_22;
    val x_274 = x_273.asInstanceOf[scala.Boolean];
    if (x_274)
      {
        val x_275 = this.network;
        resetData_0 = x_275;
        val x_276 = resetData_0;
        val x_277 = x_276.asInstanceOf[scala.collection.mutable.Set[example.rumor.Person]];
        bindingMut_21 = x_277;
        val x_278 = bindingMut_21;
        val x_279 = x_278.asInstanceOf[scala.collection.mutable.Set[example.rumor.Person]];
        val x_280 = x_279.toList;
        resetData_0 = x_280;
        val x_281 = resetData_0;
        val x_282 = x_281.asInstanceOf[scala.collection.immutable.List[example.rumor.Person]];
        bindingMut_20 = x_282;
        val x_283 = bindingMut_20;
        val x_284 = x_283.asInstanceOf[scala.collection.immutable.List[example.rumor.Person]];
        val x_285 = x_284.iterator;
        iterMut_19 = x_285;
        positionVar_24 = 21
      }
    else
      ()
  }));
  data_25.update(38, (() => {
    val x_286 = bindingMut_6;
    val x_287 = x_286.asInstanceOf[scala.Boolean];
    if (x_287)
      {
        val x_288 = scala.util.Random.nextDouble();
        resetData_0 = x_288;
        val x_289 = resetData_0;
        val x_290 = x_289.asInstanceOf[scala.Double];
        bindingMut_5 = x_290;
        val x_291 = this.spreadProb;
        resetData_0 = x_291;
        val x_292 = resetData_0;
        val x_293 = x_292.asInstanceOf[scala.Double];
        bindingMut_4 = x_293;
        val x_294 = bindingMut_4;
        val x_295 = x_294.asInstanceOf[scala.Double];
        val x_296 = bindingMut_5;
        val x_297 = x_296.asInstanceOf[scala.Double];
        val x_298 = x_297.>(x_295);
        resetData_0 = x_298;
        val x_299 = resetData_1.remove(0);
        val x_303 = x_299.find(((x_300: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_301 = x_300._1;
          val x_302 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_301.==(x_302)
        }));
        val x_304 = x_303.get;
        val x_305 = x_304._2;
        positionVar_24 = x_305
      }
    else
      ()
  }));
  data_25.update(39, (() => {
    val x_306 = iterMut_13;
    val x_307 = x_306.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_308 = x_307.hasNext;
    val x_309 = x_308.`unary_!`;
    if (x_309)
      positionVar_24 = 40
    else
      ()
  }));
  data_25.update(40, (() => if (true)
    positionVar_24 = 2
  else
    positionVar_24 = 41));
  data_25.update(41, (() => {
    val x_310 = true.`unary_!`;
    if (x_310)
      {
        val x_311 = resetData_1.remove(0);
        val x_315 = x_311.find(((x_312: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_313 = x_312._1;
          val x_314 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_313.==(x_314)
        }));
        val x_316 = x_315.get;
        val x_317 = x_316._2;
        positionVar_24 = x_317
      }
    else
      ()
  }));
  data_25.update(42, (() => {
    val x_318 = resetData_0;
    val x_319 = x_318.asInstanceOf[scala.Any];
    bindingMut_11 = x_319;
    val x_320 = bindingMut_11;
    val x_321 = x_320.asInstanceOf[scala.Any];
    val x_322 = listValMut_12;
    val x_323 = x_322.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_323.reply(this, x_321);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(43, (() => positionVar_24 = 44));
  data_25.update(44, (() => {
    positionVar_24 = 45;
    val x_324 = currentTurn;
    val x_325 = x_324.+(1);
    currentTurn = x_325
  }));
  data_25.update(45, (() => positionVar_24 = 44));
  data_25.update(46, (() => {
    positionVar_24 = 32;
    val x_326 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_327 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_326, 35);
    val x_328 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_327);
    resetData_1.prepend(x_328)
  }));
  data_25.update(47, (() => {
    val x_329 = bindingMut_7;
    val x_330 = x_329.asInstanceOf[scala.Boolean];
    if (x_330)
      {
        val receiver_331 = this.env;
        val x_332 = ((this): meta.deep.runtime.Actor).id;
        val x_333 = receiver_331.id;
        val x_334 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_335 = meta.deep.runtime.RequestMessage.apply(x_332, x_333, 5, x_334);
        val x_336 = x_335.future;
        val x_337 = x_336.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
        x_335.`future_=`(x_337);
        ((this): meta.deep.runtime.Actor).sendMessage(x_335);
        val x_338 = x_335.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_338, ((response_339: meta.deep.runtime.Message) => {
          val x_340 = x_335.future;
          val x_341 = response_339.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_342 = x_341.arg;
          val x_343 = x_340.setValue[scala.Any](x_342);
          val x_344 = x_343.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
          x_335.`future_=`(x_344);
          val x_345 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_346 = x_335.future;
          val x_347 = x_346.id;
          val x_348 = scala.Predef.ArrowAssoc[java.lang.String](x_347);
          val x_349 = x_335.future;
          val x_350 = x_348.->[meta.deep.runtime.Future[scala.Any]](x_349);
          val x_351 = x_345.+[meta.deep.runtime.Future[scala.Any]](x_350);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_351)
        }));
        val x_352 = x_335.future;
        val x_353 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_352);
        resetData_0 = x_353;
        this.`reported_=`(true);
        resetData_0 = ();
        val x_354 = resetData_1.remove(0);
        val x_358 = x_354.find(((x_355: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_356 = x_355._1;
          val x_357 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_356.==(x_357)
        }));
        val x_359 = x_358.get;
        val x_360 = x_359._2;
        positionVar_24 = x_360
      }
    else
      ()
  }));
  data_25.update(48, (() => {
    val x_361 = bindingMut_9;
    val x_362 = x_361.asInstanceOf[scala.Boolean];
    if (x_362)
      {
        val x_363 = this.reported;
        resetData_0 = x_363;
        val x_364 = resetData_0;
        val x_365 = x_364.asInstanceOf[scala.Boolean];
        bindingMut_8 = x_365;
        val x_366 = bindingMut_8;
        val x_367 = x_366.asInstanceOf[scala.Boolean];
        val x_368 = x_367.`unary_!`;
        resetData_0 = x_368;
        positionVar_24 = 5
      }
    else
      ()
  }));
  data_25.update(49, (() => {
    val x_369 = true.`unary_!`;
    if (x_369)
      {
        val x_370 = resetData_1.remove(0);
        val x_374 = x_370.find(((x_371: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_372 = x_371._1;
          val x_373 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_372.==(x_373)
        }));
        val x_375 = x_374.get;
        val x_376 = x_375._2;
        positionVar_24 = x_376
      }
    else
      ()
  }));
  data_25
}).apply();
  
  override def run_until(until_378: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_379 = currentTurn;
      val x_380 = x_379.<=(until_378);
      x_380.&&({
        val x_381 = positionVar_24;
        val x_382 = commands_377.length;
        x_381.<(x_382)
      })
    }) 
      {
        val x_383 = positionVar_24;
        val x_384 = commands_377.apply(x_383);
        x_384.apply()
      }
    ;
    this
  }
}
