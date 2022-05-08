package generated.meta.test.methodNames

class FooBar() extends generated.meta.test.methodNames.FooA {

   val bar: generated.meta.test.methodNames.BarA = new generated.meta.test.methodNames.BarA();
   meta.runtime.SimRuntime.newActors.append(bar);
   val foo: generated.meta.test.methodNames.FooA = new generated.meta.test.methodNames.FooA();
   meta.runtime.SimRuntime.newActors.append(foo);
  private var  reflectionIR_76: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Boolean = false
private var bindingMut_7: generated.meta.test.methodNames.BarA = null
private var bindingMut_8: scala.Boolean = false
private var bindingMut_9: generated.meta.test.methodNames.BarA = null
private var bindingMut_10: scala.Boolean = false
private var bindingMut_11: generated.meta.test.methodNames.BarA = null
private var bindingMut_12: scala.Boolean = false
private var bindingMut_13: generated.meta.test.methodNames.FooA = null
private var bindingMut_14: scala.Boolean = false
private var bindingMut_15: generated.meta.test.methodNames.FooA = null
private var bindingMut_16: scala.Boolean = false
private var bindingMut_17: generated.meta.test.methodNames.FooA = null
private var unblockFlag_18: scala.Boolean = true
private var positionVar_19: scala.Int = 0
private 
  val commands_357 = (() => {
  val data_20 = new scala.Array[scala.Function0[scala.Unit]](70);
  data_20.update(0, (() => positionVar_19 = 1));
  data_20.update(1, (() => {
    scala.Predef.println("FooBar messages Foo!");
    resetData_0 = ();
    val x_21 = this.foo;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[generated.meta.test.methodNames.FooA];
    bindingMut_17 = x_23;
    val x_24 = this._container;
    val x_25 = x_24.!=(null);
    val x_30 = if (x_25)
      {
        val x_26 = this._container;
        val x_27 = x_26.proxyIds;
        val x_28 = bindingMut_17;
        val x_29 = x_28.id;
        x_27.contains[scala.Long](x_29)
      }
    else
      false;
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[scala.Boolean];
    bindingMut_16 = x_32;
    positionVar_19 = 2
  }));
  data_20.update(2, (() => {
    val x_33 = bindingMut_16;
    val x_34 = x_33.`unary_!`;
    if (x_34)
      {
        val x_35 = ((this): meta.runtime.Actor).id;
        val x_36 = bindingMut_17;
        val x_37 = x_36.id;
        val x_38 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_39 = meta.runtime.RequestMessage.apply(x_35, x_37, true, "get_Name__", x_38);
        ((this): meta.runtime.Actor).sendMessage(x_39);
        val x_40 = x_39.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_40, ((response_41: meta.runtime.Message) => {
          val x_42 = response_41.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_42
        }));
        resetData_0 = null;
        positionVar_19 = 3
      }
    else
      positionVar_19 = 41
  }));
  data_20.update(3, (() => {
    positionVar_19 = 4;
    unblockFlag_18 = false
  }));
  data_20.update(4, (() => {
    val x_43 = resetData_2;
    val x_44 = x_43.==(null);
    if (x_44)
      positionVar_19 = 3
    else
      positionVar_19 = 5
  }));
  data_20.update(5, (() => {
    val x_45 = resetData_2;
    val x_46 = x_45.!=(null);
    if (x_46)
      {
        val x_47 = resetData_2;
        val x_48 = x_47.arg;
        resetData_0 = x_48;
        resetData_2 = null;
        positionVar_19 = 6
      }
    else
      ()
  }));
  data_20.update(6, (() => {
    val x_49 = this.foo;
    resetData_0 = x_49;
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[generated.meta.test.methodNames.FooA];
    bindingMut_15 = x_51;
    val x_52 = this._container;
    val x_53 = x_52.!=(null);
    val x_58 = if (x_53)
      {
        val x_54 = this._container;
        val x_55 = x_54.proxyIds;
        val x_56 = bindingMut_15;
        val x_57 = x_56.id;
        x_55.contains[scala.Long](x_57)
      }
    else
      false;
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[scala.Boolean];
    bindingMut_14 = x_60;
    positionVar_19 = 7
  }));
  data_20.update(7, (() => {
    val x_61 = bindingMut_14;
    val x_62 = x_61.`unary_!`;
    if (x_62)
      {
        val x_63 = ((this): meta.runtime.Actor).id;
        val x_64 = bindingMut_15;
        val x_65 = x_64.id;
        val x_66 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_67 = meta.runtime.RequestMessage.apply(x_63, x_65, true, "__get___Name__", x_66);
        ((this): meta.runtime.Actor).sendMessage(x_67);
        val x_68 = x_67.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_68, ((response_69: meta.runtime.Message) => {
          val x_70 = response_69.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_70
        }));
        resetData_0 = null;
        positionVar_19 = 8
      }
    else
      positionVar_19 = 40
  }));
  data_20.update(8, (() => {
    positionVar_19 = 9;
    unblockFlag_18 = false
  }));
  data_20.update(9, (() => {
    val x_71 = resetData_2;
    val x_72 = x_71.==(null);
    if (x_72)
      positionVar_19 = 8
    else
      positionVar_19 = 10
  }));
  data_20.update(10, (() => {
    val x_73 = resetData_2;
    val x_74 = x_73.!=(null);
    if (x_74)
      {
        val x_75 = resetData_2;
        val x_76 = x_75.arg;
        resetData_0 = x_76;
        resetData_2 = null;
        positionVar_19 = 11
      }
    else
      ()
  }));
  data_20.update(11, (() => {
    val x_77 = this.foo;
    resetData_0 = x_77;
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[generated.meta.test.methodNames.FooA];
    bindingMut_13 = x_79;
    val x_80 = this._container;
    val x_81 = x_80.!=(null);
    val x_86 = if (x_81)
      {
        val x_82 = this._container;
        val x_83 = x_82.proxyIds;
        val x_84 = bindingMut_13;
        val x_85 = x_84.id;
        x_83.contains[scala.Long](x_85)
      }
    else
      false;
    resetData_0 = x_86;
    val x_87 = resetData_0;
    val x_88 = x_87.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_88;
    positionVar_19 = 12
  }));
  data_20.update(12, (() => {
    val x_89 = bindingMut_12;
    val x_90 = x_89.`unary_!`;
    if (x_90)
      {
        val x_91 = ((this): meta.runtime.Actor).id;
        val x_92 = bindingMut_13;
        val x_93 = x_92.id;
        val x_94 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_95 = meta.runtime.RequestMessage.apply(x_91, x_93, true, "___get_Name___", x_94);
        ((this): meta.runtime.Actor).sendMessage(x_95);
        val x_96 = x_95.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_96, ((response_97: meta.runtime.Message) => {
          val x_98 = response_97.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_98
        }));
        resetData_0 = null;
        positionVar_19 = 13
      }
    else
      positionVar_19 = 39
  }));
  data_20.update(13, (() => {
    positionVar_19 = 14;
    unblockFlag_18 = false
  }));
  data_20.update(14, (() => {
    val x_99 = resetData_2;
    val x_100 = x_99.==(null);
    if (x_100)
      positionVar_19 = 13
    else
      positionVar_19 = 15
  }));
  data_20.update(15, (() => {
    val x_101 = resetData_2;
    val x_102 = x_101.!=(null);
    if (x_102)
      {
        val x_103 = resetData_2;
        val x_104 = x_103.arg;
        resetData_0 = x_104;
        resetData_2 = null;
        positionVar_19 = 16
      }
    else
      ()
  }));
  data_20.update(16, (() => {
    scala.Predef.println("FooBar messages Bar!");
    resetData_0 = ();
    val x_105 = this.bar;
    resetData_0 = x_105;
    val x_106 = resetData_0;
    val x_107 = x_106.asInstanceOf[generated.meta.test.methodNames.BarA];
    bindingMut_11 = x_107;
    val x_108 = this._container;
    val x_109 = x_108.!=(null);
    val x_114 = if (x_109)
      {
        val x_110 = this._container;
        val x_111 = x_110.proxyIds;
        val x_112 = bindingMut_11;
        val x_113 = x_112.id;
        x_111.contains[scala.Long](x_113)
      }
    else
      false;
    resetData_0 = x_114;
    val x_115 = resetData_0;
    val x_116 = x_115.asInstanceOf[scala.Boolean];
    bindingMut_10 = x_116;
    positionVar_19 = 17
  }));
  data_20.update(17, (() => {
    val x_117 = bindingMut_10;
    val x_118 = x_117.`unary_!`;
    if (x_118)
      {
        val x_119 = ((this): meta.runtime.Actor).id;
        val x_120 = bindingMut_11;
        val x_121 = x_120.id;
        val x_122 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_123 = meta.runtime.RequestMessage.apply(x_119, x_121, true, "get_Name__", x_122);
        ((this): meta.runtime.Actor).sendMessage(x_123);
        val x_124 = x_123.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_124, ((response_125: meta.runtime.Message) => {
          val x_126 = response_125.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_126
        }));
        resetData_0 = null;
        positionVar_19 = 18
      }
    else
      positionVar_19 = 38
  }));
  data_20.update(18, (() => {
    positionVar_19 = 19;
    unblockFlag_18 = false
  }));
  data_20.update(19, (() => {
    val x_127 = resetData_2;
    val x_128 = x_127.==(null);
    if (x_128)
      positionVar_19 = 18
    else
      positionVar_19 = 20
  }));
  data_20.update(20, (() => {
    val x_129 = resetData_2;
    val x_130 = x_129.!=(null);
    if (x_130)
      {
        val x_131 = resetData_2;
        val x_132 = x_131.arg;
        resetData_0 = x_132;
        resetData_2 = null;
        positionVar_19 = 21
      }
    else
      ()
  }));
  data_20.update(21, (() => {
    val x_133 = this.bar;
    resetData_0 = x_133;
    val x_134 = resetData_0;
    val x_135 = x_134.asInstanceOf[generated.meta.test.methodNames.BarA];
    bindingMut_9 = x_135;
    val x_136 = this._container;
    val x_137 = x_136.!=(null);
    val x_142 = if (x_137)
      {
        val x_138 = this._container;
        val x_139 = x_138.proxyIds;
        val x_140 = bindingMut_9;
        val x_141 = x_140.id;
        x_139.contains[scala.Long](x_141)
      }
    else
      false;
    resetData_0 = x_142;
    val x_143 = resetData_0;
    val x_144 = x_143.asInstanceOf[scala.Boolean];
    bindingMut_8 = x_144;
    positionVar_19 = 22
  }));
  data_20.update(22, (() => {
    val x_145 = bindingMut_8;
    val x_146 = x_145.`unary_!`;
    if (x_146)
      {
        val x_147 = ((this): meta.runtime.Actor).id;
        val x_148 = bindingMut_9;
        val x_149 = x_148.id;
        val x_150 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_151 = meta.runtime.RequestMessage.apply(x_147, x_149, true, "__get___Name__", x_150);
        ((this): meta.runtime.Actor).sendMessage(x_151);
        val x_152 = x_151.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_152, ((response_153: meta.runtime.Message) => {
          val x_154 = response_153.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_154
        }));
        resetData_0 = null;
        positionVar_19 = 23
      }
    else
      positionVar_19 = 37
  }));
  data_20.update(23, (() => {
    positionVar_19 = 24;
    unblockFlag_18 = false
  }));
  data_20.update(24, (() => {
    val x_155 = resetData_2;
    val x_156 = x_155.==(null);
    if (x_156)
      positionVar_19 = 23
    else
      positionVar_19 = 25
  }));
  data_20.update(25, (() => {
    val x_157 = resetData_2;
    val x_158 = x_157.!=(null);
    if (x_158)
      {
        val x_159 = resetData_2;
        val x_160 = x_159.arg;
        resetData_0 = x_160;
        resetData_2 = null;
        positionVar_19 = 26
      }
    else
      ()
  }));
  data_20.update(26, (() => {
    val x_161 = this.bar;
    resetData_0 = x_161;
    val x_162 = resetData_0;
    val x_163 = x_162.asInstanceOf[generated.meta.test.methodNames.BarA];
    bindingMut_7 = x_163;
    val x_164 = this._container;
    val x_165 = x_164.!=(null);
    val x_170 = if (x_165)
      {
        val x_166 = this._container;
        val x_167 = x_166.proxyIds;
        val x_168 = bindingMut_7;
        val x_169 = x_168.id;
        x_167.contains[scala.Long](x_169)
      }
    else
      false;
    resetData_0 = x_170;
    val x_171 = resetData_0;
    val x_172 = x_171.asInstanceOf[scala.Boolean];
    bindingMut_6 = x_172;
    positionVar_19 = 27
  }));
  data_20.update(27, (() => {
    val x_173 = bindingMut_6;
    val x_174 = x_173.`unary_!`;
    if (x_174)
      {
        val x_175 = ((this): meta.runtime.Actor).id;
        val x_176 = bindingMut_7;
        val x_177 = x_176.id;
        val x_178 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_179 = meta.runtime.RequestMessage.apply(x_175, x_177, true, "___get_Name___", x_178);
        ((this): meta.runtime.Actor).sendMessage(x_179);
        val x_180 = x_179.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_180, ((response_181: meta.runtime.Message) => {
          val x_182 = response_181.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_182
        }));
        resetData_0 = null;
        positionVar_19 = 28
      }
    else
      positionVar_19 = 36
  }));
  data_20.update(28, (() => {
    positionVar_19 = 29;
    unblockFlag_18 = false
  }));
  data_20.update(29, (() => {
    val x_183 = resetData_2;
    val x_184 = x_183.==(null);
    if (x_184)
      positionVar_19 = 28
    else
      positionVar_19 = 30
  }));
  data_20.update(30, (() => {
    val x_185 = resetData_2;
    val x_186 = x_185.!=(null);
    if (x_186)
      {
        val x_187 = resetData_2;
        val x_188 = x_187.arg;
        resetData_0 = x_188;
        resetData_2 = null;
        positionVar_19 = 31
      }
    else
      ()
  }));
  data_20.update(31, (() => {
    scala.Predef.println("FooBar messages Foo asynchronously!");
    resetData_0 = ();
    val receiver_189 = this.foo;
    val x_190 = ((this): meta.runtime.Actor).id;
    val x_191 = receiver_189.id;
    val x_192 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_193 = meta.runtime.RequestMessage.apply(x_190, x_191, false, "get_Name__", x_192);
    val x_194 = x_193.sessionId;
    val x_195 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_196 = meta.runtime.Future.apply[java.lang.String](x_194, x_195);
    var v_197: meta.runtime.Future[java.lang.String] = x_196;
    ((this): meta.runtime.Actor).sendMessage(x_193);
    val x_198 = x_193.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_198, ((response_199: meta.runtime.Message) => {
      val x_200 = v_197;
      val x_201 = response_199.asInstanceOf[meta.runtime.ResponseMessage];
      val x_202 = x_201.arg;
      val x_203 = x_202.asInstanceOf[java.lang.String];
      x_200.setValue(x_203)
    }));
    val x_204 = v_197;
    resetData_0 = x_204;
    val receiver_205 = this.foo;
    val x_206 = ((this): meta.runtime.Actor).id;
    val x_207 = receiver_205.id;
    val x_208 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_209 = meta.runtime.RequestMessage.apply(x_206, x_207, false, "__get___Name__", x_208);
    val x_210 = x_209.sessionId;
    val x_211 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_212 = meta.runtime.Future.apply[java.lang.String](x_210, x_211);
    var v_213: meta.runtime.Future[java.lang.String] = x_212;
    ((this): meta.runtime.Actor).sendMessage(x_209);
    val x_214 = x_209.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_214, ((response_215: meta.runtime.Message) => {
      val x_216 = v_213;
      val x_217 = response_215.asInstanceOf[meta.runtime.ResponseMessage];
      val x_218 = x_217.arg;
      val x_219 = x_218.asInstanceOf[java.lang.String];
      x_216.setValue(x_219)
    }));
    val x_220 = v_213;
    resetData_0 = x_220;
    val receiver_221 = this.foo;
    val x_222 = ((this): meta.runtime.Actor).id;
    val x_223 = receiver_221.id;
    val x_224 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_225 = meta.runtime.RequestMessage.apply(x_222, x_223, false, "___get_Name___", x_224);
    val x_226 = x_225.sessionId;
    val x_227 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_228 = meta.runtime.Future.apply[java.lang.String](x_226, x_227);
    var v_229: meta.runtime.Future[java.lang.String] = x_228;
    ((this): meta.runtime.Actor).sendMessage(x_225);
    val x_230 = x_225.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_230, ((response_231: meta.runtime.Message) => {
      val x_232 = v_229;
      val x_233 = response_231.asInstanceOf[meta.runtime.ResponseMessage];
      val x_234 = x_233.arg;
      val x_235 = x_234.asInstanceOf[java.lang.String];
      x_232.setValue(x_235)
    }));
    val x_236 = v_229;
    resetData_0 = x_236;
    scala.Predef.println("FooBar messages Bar asynchronously!");
    resetData_0 = ();
    val receiver_237 = this.bar;
    val x_238 = ((this): meta.runtime.Actor).id;
    val x_239 = receiver_237.id;
    val x_240 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_241 = meta.runtime.RequestMessage.apply(x_238, x_239, false, "get_Name__", x_240);
    val x_242 = x_241.sessionId;
    val x_243 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_244 = meta.runtime.Future.apply[java.lang.String](x_242, x_243);
    var v_245: meta.runtime.Future[java.lang.String] = x_244;
    ((this): meta.runtime.Actor).sendMessage(x_241);
    val x_246 = x_241.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_246, ((response_247: meta.runtime.Message) => {
      val x_248 = v_245;
      val x_249 = response_247.asInstanceOf[meta.runtime.ResponseMessage];
      val x_250 = x_249.arg;
      val x_251 = x_250.asInstanceOf[java.lang.String];
      x_248.setValue(x_251)
    }));
    val x_252 = v_245;
    resetData_0 = x_252;
    val receiver_253 = this.bar;
    val x_254 = ((this): meta.runtime.Actor).id;
    val x_255 = receiver_253.id;
    val x_256 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_257 = meta.runtime.RequestMessage.apply(x_254, x_255, false, "__get___Name__", x_256);
    val x_258 = x_257.sessionId;
    val x_259 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_260 = meta.runtime.Future.apply[java.lang.String](x_258, x_259);
    var v_261: meta.runtime.Future[java.lang.String] = x_260;
    ((this): meta.runtime.Actor).sendMessage(x_257);
    val x_262 = x_257.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_262, ((response_263: meta.runtime.Message) => {
      val x_264 = v_261;
      val x_265 = response_263.asInstanceOf[meta.runtime.ResponseMessage];
      val x_266 = x_265.arg;
      val x_267 = x_266.asInstanceOf[java.lang.String];
      x_264.setValue(x_267)
    }));
    val x_268 = v_261;
    resetData_0 = x_268;
    val receiver_269 = this.bar;
    val x_270 = ((this): meta.runtime.Actor).id;
    val x_271 = receiver_269.id;
    val x_272 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_273 = meta.runtime.RequestMessage.apply(x_270, x_271, false, "___get_Name___", x_272);
    val x_274 = x_273.sessionId;
    val x_275 = meta.runtime.Future.apply$default$2[java.lang.String];
    val x_276 = meta.runtime.Future.apply[java.lang.String](x_274, x_275);
    var v_277: meta.runtime.Future[java.lang.String] = x_276;
    ((this): meta.runtime.Actor).sendMessage(x_273);
    val x_278 = x_273.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_278, ((response_279: meta.runtime.Message) => {
      val x_280 = v_277;
      val x_281 = response_279.asInstanceOf[meta.runtime.ResponseMessage];
      val x_282 = x_281.arg;
      val x_283 = x_282.asInstanceOf[java.lang.String];
      x_280.setValue(x_283)
    }));
    val x_284 = v_277;
    resetData_0 = x_284;
    positionVar_19 = 32
  }));
  data_20.update(32, (() => positionVar_19 = 1));
  data_20.update(33, (() => positionVar_19 = 34));
  data_20.update(34, (() => {
    positionVar_19 = 35;
    unblockFlag_18 = false
  }));
  data_20.update(35, (() => positionVar_19 = 34));
  data_20.update(36, (() => {
    val x_285 = bindingMut_6;
    squid.lib.`package`.IfThenElse[scala.Unit](x_285, {
      val x_286 = bindingMut_7;
      val x_287 = x_286.___get_Name___();
      resetData_0 = x_287;
      positionVar_19 = 31
    }, ())
  }));
  data_20.update(37, (() => {
    val x_288 = bindingMut_8;
    squid.lib.`package`.IfThenElse[scala.Unit](x_288, {
      val x_289 = bindingMut_9;
      val x_290 = x_289.__get___Name__();
      resetData_0 = x_290;
      positionVar_19 = 26
    }, ())
  }));
  data_20.update(38, (() => {
    val x_291 = bindingMut_10;
    squid.lib.`package`.IfThenElse[scala.Unit](x_291, {
      val x_292 = bindingMut_11;
      val x_293 = x_292.get_Name__();
      resetData_0 = x_293;
      positionVar_19 = 21
    }, ())
  }));
  data_20.update(39, (() => {
    val x_294 = bindingMut_12;
    squid.lib.`package`.IfThenElse[scala.Unit](x_294, {
      val x_295 = bindingMut_13;
      val x_296 = x_295.___get_Name___();
      resetData_0 = x_296;
      positionVar_19 = 16
    }, ())
  }));
  data_20.update(40, (() => {
    val x_297 = bindingMut_14;
    squid.lib.`package`.IfThenElse[scala.Unit](x_297, {
      val x_298 = bindingMut_15;
      val x_299 = x_298.__get___Name__();
      resetData_0 = x_299;
      positionVar_19 = 11
    }, ())
  }));
  data_20.update(41, (() => {
    val x_300 = bindingMut_16;
    squid.lib.`package`.IfThenElse[scala.Unit](x_300, {
      val x_301 = bindingMut_17;
      val x_302 = x_301.get_Name__();
      resetData_0 = x_302;
      positionVar_19 = 6
    }, ())
  }));
  data_20.update(42, (() => positionVar_19 = 34));
  data_20.update(43, (() => {
    val x_303 = this.popRequestMessages;
    val x_304 = x_303.iterator;
    iterMut_5 = x_304;
    positionVar_19 = 44
  }));
  data_20.update(44, (() => {
    val x_305 = iterMut_5;
    val x_306 = x_305.hasNext;
    if (x_306)
      {
        val x_307 = iterMut_5;
        val x_308 = x_307.next();
        listValMut_4 = x_308;
        positionVar_19 = 45
      }
    else
      positionVar_19 = 67
  }));
  data_20.update(45, (() => {
    val x_309 = listValMut_4;
    val x_310 = x_309.methodInfo;
    val x_311 = x_310.==("get_Name__");
    if (x_311)
      positionVar_19 = 46
    else
      positionVar_19 = 52
  }));
  data_20.update(46, (() => {
    val x_312 = listValMut_4;
    this.handleNonblockingMessage(x_312);
    resetData_0 = ();
    positionVar_19 = 47
  }));
  data_20.update(47, (() => positionVar_19 = 48));
  data_20.update(48, (() => positionVar_19 = 44));
  data_20.update(49, (() => positionVar_19 = 50));
  data_20.update(50, (() => {
    val x_313 = this.id;
    val x_314 = x_313.+(" get_Name__ is called!");
    scala.Predef.println(x_314);
    resetData_0 = "Foo!";
    positionVar_19 = 51
  }));
  data_20.update(51, (() => {
    val x_315 = resetData_0;
    val x_316 = x_315.asInstanceOf[scala.Any];
    bindingMut_3 = x_316;
    val x_317 = bindingMut_3;
    val x_318 = listValMut_4;
    x_318.reply(this, x_317);
    resetData_0 = ();
    positionVar_19 = 47
  }));
  data_20.update(52, (() => {
    val x_319 = listValMut_4;
    val x_320 = x_319.methodInfo;
    val x_321 = x_320.==("get_Name__");
    val x_322 = x_321.`unary_!`;
    if (x_322)
      positionVar_19 = 53
    else
      ()
  }));
  data_20.update(53, (() => {
    val x_323 = listValMut_4;
    val x_324 = x_323.methodInfo;
    val x_325 = x_324.==("__get___Name__");
    if (x_325)
      positionVar_19 = 54
    else
      positionVar_19 = 59
  }));
  data_20.update(54, (() => {
    val x_326 = listValMut_4;
    this.handleNonblockingMessage(x_326);
    resetData_0 = ();
    positionVar_19 = 55
  }));
  data_20.update(55, (() => positionVar_19 = 48));
  data_20.update(56, (() => positionVar_19 = 57));
  data_20.update(57, (() => {
    val x_327 = this.id;
    val x_328 = x_327.+(" __get___Name__ is called!");
    scala.Predef.println(x_328);
    resetData_0 = "Hello";
    positionVar_19 = 58
  }));
  data_20.update(58, (() => {
    val x_329 = resetData_0;
    val x_330 = x_329.asInstanceOf[scala.Any];
    bindingMut_3 = x_330;
    val x_331 = bindingMut_3;
    val x_332 = listValMut_4;
    x_332.reply(this, x_331);
    resetData_0 = ();
    positionVar_19 = 55
  }));
  data_20.update(59, (() => {
    val x_333 = listValMut_4;
    val x_334 = x_333.methodInfo;
    val x_335 = x_334.==("__get___Name__");
    val x_336 = x_335.`unary_!`;
    if (x_336)
      positionVar_19 = 60
    else
      ()
  }));
  data_20.update(60, (() => {
    val x_337 = listValMut_4;
    val x_338 = x_337.methodInfo;
    val x_339 = x_338.==("___get_Name___");
    if (x_339)
      positionVar_19 = 61
    else
      positionVar_19 = 66
  }));
  data_20.update(61, (() => {
    val x_340 = listValMut_4;
    this.handleNonblockingMessage(x_340);
    resetData_0 = ();
    positionVar_19 = 62
  }));
  data_20.update(62, (() => positionVar_19 = 48));
  data_20.update(63, (() => positionVar_19 = 64));
  data_20.update(64, (() => {
    val x_341 = this.id;
    val x_342 = x_341.+(" ___get_Name___ is called!");
    scala.Predef.println(x_342);
    resetData_0 = "World";
    positionVar_19 = 65
  }));
  data_20.update(65, (() => {
    val x_343 = resetData_0;
    val x_344 = x_343.asInstanceOf[scala.Any];
    bindingMut_3 = x_344;
    val x_345 = bindingMut_3;
    val x_346 = listValMut_4;
    x_346.reply(this, x_345);
    resetData_0 = ();
    positionVar_19 = 62
  }));
  data_20.update(66, (() => {
    val x_347 = listValMut_4;
    val x_348 = x_347.methodInfo;
    val x_349 = x_348.==("___get_Name___");
    val x_350 = x_349.`unary_!`;
    if (x_350)
      {
        val x_351 = listValMut_4;
        val x_352 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_351);
        val x_353 = this.addReceiveMessages(x_352);
        resetData_0 = x_353;
        positionVar_19 = 48
      }
    else
      ()
  }));
  data_20.update(67, (() => {
    val x_354 = iterMut_5;
    val x_355 = x_354.hasNext;
    val x_356 = x_355.`unary_!`;
    if (x_356)
      positionVar_19 = 68
    else
      ()
  }));
  data_20.update(68, (() => {
    positionVar_19 = 69;
    unblockFlag_18 = false
  }));
  data_20.update(69, (() => positionVar_19 = 68));
  data_20
}).apply();
  

  override def ___get_Name___(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" ___get_Name___ is called!");
  scala.Predef.println(x_1);
  "World"
}
  
  private def wrapper____get_Name___(args: List[Any]): String = {
    
          
          ___get_Name___()
          
  }
  
  override def get_Name__(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" get_Name__ is called!");
  scala.Predef.println(x_1);
  "Foo!"
}
  
  private def wrapper_get_Name__(args: List[Any]): String = {
    
          
          get_Name__()
          
  }
  
  override def __get___Name__(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" __get___Name__ is called!");
  scala.Predef.println(x_1);
  "Hello"
}
  
  private def wrapper___get___Name__(args: List[Any]): String = {
    
          
          __get___Name__()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_18 = true
    while (unblockFlag_18 && (positionVar_19 < 70)) {
      commands_357(positionVar_19)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "___get_Name___" => wrapper____get_Name___(args)
    case "get_Name__" => wrapper_get_Name__(args)
    case "__get___Name__" => wrapper___get___Name__(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 43): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_18 = true

      if (reflectionIR_76 == -1){
        reflectionIR_76 = positionVar_19
        positionVar_19 = new_ir
      }

      while (positionVar_19 <= 65 && unblockFlag_18) {
        commands_357(positionVar_19)()
      }

      // reset instruction register when finishes processing
      if (positionVar_19 > 65) {
        positionVar_19 = reflectionIR_76
        reflectionIR_76 = -1
      }
      this
    }
    
override def SimClone(): FooBar = {
  val newAgent = new FooBar()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_19 = 0
  
}

}
