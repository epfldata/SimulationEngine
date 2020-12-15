package generated.example.groupMessage

class Person (var name: String) extends meta.deep.runtime.Actor {
  var future_objs: scala.collection.mutable.ListBuffer[Option[meta.deep.runtime.Future[Boolean]]] = new scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]()
  var readByEveryone: Boolean = true
  var group: List[generated.example.groupMessage.Person] = scala.collection.immutable.Nil
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Option[meta.deep.runtime.Future[scala.Boolean]] = null;
  private var bindingMut_4: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var listValMut_5: generated.example.groupMessage.Person = null;
  private var iterMut_6: scala.collection.Iterator[generated.example.groupMessage.Person] = null;
  private var bindingMut_7: scala.collection.immutable.List[generated.example.groupMessage.Person] = null;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Boolean = false;
  private var bindingMut_11: scala.Boolean = false;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: java.lang.String = null;
  private var bindingMut_16: scala.Double = 0.0;
  private var bindingMut_17: scala.Double = 0.0;
  private var bindingMut_18: java.lang.String = null;
  private var bindingMut_19: java.lang.String = null;
  private var bindingMut_20: scala.Boolean = false;
  private var bindingMut_21: scala.Double = 0.0;
  private var bindingMut_22: scala.Double = 0.0;
  private var bindingMut_23: java.lang.String = null;
  private var bindingMut_24: java.lang.String = null;
  private var bindingMut_25: scala.Boolean = false;
  private var bindingMut_26: scala.Int = 0;
  private var bindingMut_27: java.lang.String = null;
  private var bindingMut_28: java.lang.String = null;
  private var bindingMut_29: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_30: meta.deep.runtime.Future[scala.Boolean] = null;
  private var listValMut_31: scala.Option[meta.deep.runtime.Future[scala.Boolean]] = null;
  private var iterMut_32: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_33: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_34: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_35: java.lang.String = null;
  private var bindingMut_36: java.lang.String = null;
  private var bindingMut_37: scala.Boolean = false;
  private var bindingMut_38: scala.Boolean = false;
  private var bindingMut_39: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_40: scala.Boolean = false;
  private var bindingMut_41: scala.Boolean = false;
  private var bindingMut_42: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_43: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]] = null;
  private var bindingMut_44: scala.Any = null;
  private var listValMut_45: meta.deep.runtime.RequestMessage = null;
  private var iterMut_46: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_48: scala.Int = 0;
  
  val commands_557 = (() => {
  val data_49 = new scala.Array[scala.Function0[scala.Unit]](58);
  data_49.update(0, (() => {
    positionVar_48 = 1;
    val x_50 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_51 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_50, 44);
    val x_52 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_51);
    resetData_1.prepend(x_52)
  }));
  data_49.update(1, (() => if (true)
    positionVar_48 = 2
  else
    positionVar_48 = 57));
  data_49.update(2, (() => {
    positionVar_48 = 3;
    val x_53 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_54 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_53, 47);
    val x_55 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_54);
    resetData_1.prepend(x_55)
  }));
  data_49.update(3, (() => {
    val x_56 = scala.util.Random.nextInt(6);
    resetData_0 = x_56;
    val x_57 = resetData_0;
    val x_58 = x_57.asInstanceOf[scala.Int];
    bindingMut_26 = x_58;
    val x_59 = bindingMut_26;
    val x_60 = x_59.asInstanceOf[scala.Int];
    val x_61 = x_60.<(3);
    resetData_0 = x_61;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Boolean];
    bindingMut_25 = x_63;
    positionVar_48 = 4
  }));
  data_49.update(4, (() => {
    val x_64 = bindingMut_25;
    val x_65 = x_64.asInstanceOf[scala.Boolean];
    if (x_65)
      {
        val x_66 = this.name;
        resetData_0 = x_66;
        val x_67 = resetData_0;
        val x_68 = x_67.asInstanceOf[java.lang.String];
        bindingMut_24 = x_68;
        val x_69 = bindingMut_24;
        val x_70 = x_69.asInstanceOf[java.lang.String];
        val x_71 = x_70.+(" works!");
        resetData_0 = x_71;
        val x_72 = resetData_0;
        val x_73 = x_72.asInstanceOf[java.lang.String];
        bindingMut_23 = x_73;
        val x_74 = bindingMut_23;
        val x_75 = x_74.asInstanceOf[java.lang.String];
        scala.Predef.println(x_75);
        resetData_0 = ();
        val x_76 = 3.toDouble;
        resetData_0 = x_76;
        val x_77 = resetData_0;
        val x_78 = x_77.asInstanceOf[scala.Double];
        bindingMut_22 = x_78;
        resetData_0 = 0.0;
        val x_79 = resetData_0;
        val x_80 = x_79.asInstanceOf[scala.Double];
        bindingMut_21 = x_80;
        positionVar_48 = 5
      }
    else
      positionVar_48 = 48
  }));
  data_49.update(5, (() => {
    val x_81 = meta.deep.runtime.Actor.proceedLabel;
    val x_82 = x_81("turn");
    val x_83 = bindingMut_21;
    val x_84 = x_83.asInstanceOf[scala.Double];
    val x_85 = x_84.+(x_82);
    resetData_0 = x_85;
    val x_86 = resetData_0;
    val x_87 = x_86.asInstanceOf[scala.Double];
    bindingMut_21 = x_87;
    val x_88 = meta.deep.runtime.Actor.labelVals("turn");
    val x_89 = bindingMut_21;
    val x_90 = x_89.asInstanceOf[scala.Double];
    val x_91 = bindingMut_22;
    val x_92 = x_91.asInstanceOf[scala.Double];
    val x_93 = x_92.-(x_90);
    x_88.append(x_93);
    resetData_0 = ();
    positionVar_48 = 6;
    val x_94 = currentTurn;
    val x_95 = x_94.+(1);
    currentTurn = x_95
  }));
  data_49.update(6, (() => {
    val x_96 = bindingMut_21;
    val x_97 = x_96.asInstanceOf[scala.Double];
    val x_98 = bindingMut_22;
    val x_99 = x_98.asInstanceOf[scala.Double];
    val x_100 = x_97.<(x_99);
    if (x_100)
      positionVar_48 = 5
    else
      positionVar_48 = 7
  }));
  data_49.update(7, (() => {
    val x_101 = bindingMut_21;
    val x_102 = x_101.asInstanceOf[scala.Double];
    val x_103 = bindingMut_22;
    val x_104 = x_103.asInstanceOf[scala.Double];
    val x_105 = x_102.<(x_104);
    val x_106 = x_105.`unary_!`;
    if (x_106)
      {
        val x_107 = resetData_1.remove(0);
        val x_111 = x_107.find(((x_108: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_109 = x_108._1;
          val x_110 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_109.==(x_110)
        }));
        val x_112 = x_111.get;
        val x_113 = x_112._2;
        positionVar_48 = x_113
      }
    else
      ()
  }));
  data_49.update(8, (() => {
    val x_114 = resetData_0;
    val x_115 = x_114.asInstanceOf[scala.Any];
    bindingMut_44 = x_115;
    val x_116 = bindingMut_44;
    val x_117 = x_116.asInstanceOf[scala.Any];
    val x_118 = listValMut_45;
    val x_119 = x_118.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_119.reply(this, x_117);
    resetData_0 = ();
    positionVar_48 = 9
  }));
  data_49.update(9, (() => positionVar_48 = 10));
  data_49.update(10, (() => {
    val x_120 = iterMut_46;
    val x_121 = x_120.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_122 = x_121.hasNext;
    if (x_122)
      {
        val x_123 = iterMut_46;
        val x_124 = x_123.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_125 = x_124.next();
        listValMut_45 = x_125;
        positionVar_48 = 11
      }
    else
      positionVar_48 = 40
  }));
  data_49.update(11, (() => {
    val x_126 = listValMut_45;
    val x_127 = x_126.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_128 = x_127.methodId;
    val x_129 = x_128.==(0);
    val x_130 = x_129.`unary_!`;
    if (x_130)
      positionVar_48 = 12
    else
      positionVar_48 = 29
  }));
  data_49.update(12, (() => {
    val x_131 = listValMut_45;
    val x_132 = x_131.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_133 = x_132.methodId;
    val x_134 = x_133.==(1);
    val x_135 = x_134.`unary_!`;
    if (x_135)
      positionVar_48 = 13
    else
      positionVar_48 = 28
  }));
  data_49.update(13, (() => {
    val x_136 = listValMut_45;
    val x_137 = x_136.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_138 = x_137.methodId;
    val x_139 = x_138.==(2);
    val x_140 = x_139.`unary_!`;
    if (x_140)
      positionVar_48 = 14
    else
      positionVar_48 = 27
  }));
  data_49.update(14, (() => {
    val x_141 = listValMut_45;
    val x_142 = x_141.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_143 = x_142.methodId;
    val x_144 = x_143.==(3);
    val x_145 = x_144.`unary_!`;
    if (x_145)
      positionVar_48 = 15
    else
      positionVar_48 = 17
  }));
  data_49.update(15, (() => {
    val x_146 = listValMut_45;
    val x_147 = x_146.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_148 = x_147.methodId;
    val x_149 = x_148.==(4);
    val x_150 = x_149.`unary_!`;
    if (x_150)
      {
        val x_151 = listValMut_45;
        val x_152 = x_151.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_153 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_152);
        val x_154 = this.addReceiveMessages(x_153);
        resetData_0 = x_154;
        positionVar_48 = 9
      }
    else
      positionVar_48 = 16
  }));
  data_49.update(16, (() => {
    val x_155 = listValMut_45;
    val x_156 = x_155.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_157 = x_156.methodId;
    val x_158 = x_157.==(4);
    if (x_158)
      positionVar_48 = 1
    else
      ();
    val x_159 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_160 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_159, 43);
    val x_161 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_160);
    resetData_1.prepend(x_161)
  }));
  data_49.update(17, (() => {
    val x_162 = listValMut_45;
    val x_163 = x_162.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_164 = x_163.methodId;
    val x_165 = x_164.==(3);
    if (x_165)
      positionVar_48 = 18
    else
      ();
    val x_166 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_167 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_166, 20);
    val x_168 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_167);
    resetData_1.prepend(x_168)
  }));
  data_49.update(18, (() => {
    val x_169 = this.future_objs;
    resetData_0 = x_169;
    val x_170 = resetData_0;
    val x_171 = x_170.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    bindingMut_43 = x_171;
    val x_172 = bindingMut_43;
    val x_173 = x_172.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    val x_174 = x_173.toList;
    resetData_0 = x_174;
    val x_175 = resetData_0;
    val x_176 = x_175.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    bindingMut_42 = x_176;
    val x_177 = bindingMut_42;
    val x_178 = x_177.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    val x_181 = x_178.forall(((x_179: scala.Option[meta.deep.runtime.Future[scala.Boolean]]) => {
      val x_180 = x_179.get;
      this.isCompleted(x_180)
    }));
    resetData_0 = x_181;
    val x_182 = resetData_0;
    val x_183 = x_182.asInstanceOf[scala.Boolean];
    bindingMut_41 = x_183;
    val x_184 = bindingMut_41;
    val x_185 = x_184.asInstanceOf[scala.Boolean];
    val x_186 = x_185.`unary_!`;
    resetData_0 = x_186;
    val x_187 = resetData_0;
    val x_188 = x_187.asInstanceOf[scala.Boolean];
    bindingMut_40 = x_188;
    positionVar_48 = 19
  }));
  data_49.update(19, (() => {
    val x_189 = bindingMut_40;
    val x_190 = x_189.asInstanceOf[scala.Boolean];
    if (x_190)
      {
        this.`readByEveryone_=`(false);
        resetData_0 = ();
        val x_191 = resetData_1.remove(0);
        val x_195 = x_191.find(((x_192: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_193 = x_192._1;
          val x_194 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_193.==(x_194)
        }));
        val x_196 = x_195.get;
        val x_197 = x_196._2;
        positionVar_48 = x_197
      }
    else
      positionVar_48 = 22
  }));
  data_49.update(20, (() => {
    val x_198 = resetData_0;
    val x_199 = x_198.asInstanceOf[scala.Any];
    bindingMut_44 = x_199;
    val x_200 = bindingMut_44;
    val x_201 = x_200.asInstanceOf[scala.Any];
    val x_202 = listValMut_45;
    val x_203 = x_202.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_203.reply(this, x_201);
    resetData_0 = ();
    positionVar_48 = 9
  }));
  data_49.update(21, (() => {
    val x_204 = this.popRequestMessages;
    val x_205 = x_204.iterator;
    iterMut_46 = x_205;
    positionVar_48 = 10
  }));
  data_49.update(22, (() => {
    val x_206 = bindingMut_40;
    val x_207 = x_206.asInstanceOf[scala.Boolean];
    val x_208 = x_207.`unary_!`;
    if (x_208)
      {
        val x_209 = this.future_objs;
        resetData_0 = x_209;
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        bindingMut_39 = x_211;
        val x_212 = bindingMut_39;
        val x_213 = x_212.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        val x_214 = x_213.isEmpty;
        resetData_0 = x_214;
        val x_215 = resetData_0;
        val x_216 = x_215.asInstanceOf[scala.Boolean];
        bindingMut_38 = x_216;
        val x_217 = bindingMut_38;
        val x_218 = x_217.asInstanceOf[scala.Boolean];
        val x_219 = x_218.`unary_!`;
        resetData_0 = x_219;
        val x_220 = resetData_0;
        val x_221 = x_220.asInstanceOf[scala.Boolean];
        bindingMut_37 = x_221;
        positionVar_48 = 23
      }
    else
      ()
  }));
  data_49.update(23, (() => {
    val x_222 = bindingMut_37;
    val x_223 = x_222.asInstanceOf[scala.Boolean];
    val x_224 = x_223.`unary_!`;
    if (x_224)
      {
        val x_225 = resetData_1.remove(0);
        val x_229 = x_225.find(((x_226: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_227 = x_226._1;
          val x_228 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_227.==(x_228)
        }));
        val x_230 = x_229.get;
        val x_231 = x_230._2;
        positionVar_48 = x_231
      }
    else
      positionVar_48 = 24
  }));
  data_49.update(24, (() => {
    val x_232 = bindingMut_37;
    val x_233 = x_232.asInstanceOf[scala.Boolean];
    if (x_233)
      {
        val x_234 = this.name;
        resetData_0 = x_234;
        val x_235 = resetData_0;
        val x_236 = x_235.asInstanceOf[java.lang.String];
        bindingMut_36 = x_236;
        val x_237 = bindingMut_36;
        val x_238 = x_237.asInstanceOf[java.lang.String];
        val x_239 = x_238.+(" message read by everyone");
        resetData_0 = x_239;
        val x_240 = resetData_0;
        val x_241 = x_240.asInstanceOf[java.lang.String];
        bindingMut_35 = x_241;
        val x_242 = bindingMut_35;
        val x_243 = x_242.asInstanceOf[java.lang.String];
        scala.Predef.println(x_243);
        resetData_0 = ();
        val x_244 = this.future_objs;
        resetData_0 = x_244;
        val x_245 = resetData_0;
        val x_246 = x_245.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        bindingMut_34 = x_246;
        val x_247 = bindingMut_34;
        val x_248 = x_247.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        val x_249 = x_248.toList;
        resetData_0 = x_249;
        val x_250 = resetData_0;
        val x_251 = x_250.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        bindingMut_33 = x_251;
        val x_252 = bindingMut_33;
        val x_253 = x_252.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        val x_254 = x_253.iterator;
        iterMut_32 = x_254;
        positionVar_48 = 25
      }
    else
      ()
  }));
  data_49.update(25, (() => {
    val x_255 = iterMut_32;
    val x_256 = x_255.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    val x_257 = x_256.hasNext;
    if (x_257)
      {
        val x_258 = iterMut_32;
        val x_259 = x_258.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        val x_260 = x_259.next();
        listValMut_31 = x_260;
        val x_261 = listValMut_31;
        val x_262 = x_261.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Boolean]]];
        val x_263 = x_262.get;
        resetData_0 = x_263;
        val x_264 = resetData_0;
        val x_265 = x_264.asInstanceOf[meta.deep.runtime.Future[scala.Boolean]];
        bindingMut_30 = x_265;
        val x_266 = bindingMut_30;
        val x_267 = x_266.asInstanceOf[meta.deep.runtime.Future[scala.Boolean]];
        val x_268 = this.clearFutureObj(x_267);
        resetData_0 = x_268;
        positionVar_48 = 25
      }
    else
      positionVar_48 = 26
  }));
  data_49.update(26, (() => {
    val x_269 = iterMut_32;
    val x_270 = x_269.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
    val x_271 = x_270.hasNext;
    val x_272 = x_271.`unary_!`;
    if (x_272)
      {
        val x_273 = this.future_objs;
        resetData_0 = x_273;
        val x_274 = resetData_0;
        val x_275 = x_274.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        bindingMut_29 = x_275;
        val x_276 = bindingMut_29;
        val x_277 = x_276.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        x_277.clear();
        resetData_0 = ();
        val x_278 = resetData_1.remove(0);
        val x_282 = x_278.find(((x_279: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_280 = x_279._1;
          val x_281 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_280.==(x_281)
        }));
        val x_283 = x_282.get;
        val x_284 = x_283._2;
        positionVar_48 = x_284
      }
    else
      ()
  }));
  data_49.update(27, (() => {
    val x_285 = listValMut_45;
    val x_286 = x_285.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_287 = x_286.methodId;
    val x_288 = x_287.==(2);
    if (x_288)
      {
        val x_289 = this.name;
        resetData_0 = x_289;
        val x_290 = resetData_0;
        val x_291 = x_290.asInstanceOf[java.lang.String];
        bindingMut_28 = x_291;
        val x_292 = bindingMut_28;
        val x_293 = x_292.asInstanceOf[java.lang.String];
        val x_294 = x_293.+(" receives the message!");
        resetData_0 = x_294;
        val x_295 = resetData_0;
        val x_296 = x_295.asInstanceOf[java.lang.String];
        bindingMut_27 = x_296;
        val x_297 = bindingMut_27;
        val x_298 = x_297.asInstanceOf[java.lang.String];
        scala.Predef.println(x_298);
        resetData_0 = ();
        resetData_0 = true;
        val x_299 = resetData_0;
        val x_300 = x_299.asInstanceOf[scala.Any];
        bindingMut_44 = x_300;
        val x_301 = bindingMut_44;
        val x_302 = x_301.asInstanceOf[scala.Any];
        val x_303 = listValMut_45;
        val x_304 = x_303.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_304.reply(this, x_302);
        resetData_0 = ();
        positionVar_48 = 9
      }
    else
      ()
  }));
  data_49.update(28, (() => {
    val x_305 = listValMut_45;
    val x_306 = x_305.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_307 = x_306.methodId;
    val x_308 = x_307.==(1);
    if (x_308)
      positionVar_48 = 3
    else
      ();
    val x_309 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_310 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_309, 8);
    val x_311 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_310);
    resetData_1.prepend(x_311)
  }));
  data_49.update(29, (() => {
    val x_312 = listValMut_45;
    val x_313 = x_312.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_314 = x_313.methodId;
    val x_315 = x_314.==(0);
    if (x_315)
      positionVar_48 = 30
    else
      ();
    val x_316 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_317 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_316, 34);
    val x_318 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_317);
    resetData_1.prepend(x_318)
  }));
  data_49.update(30, (() => {
    val x_319 = scala.util.Random.nextBoolean();
    resetData_0 = x_319;
    val x_320 = resetData_0;
    val x_321 = x_320.asInstanceOf[scala.Boolean];
    bindingMut_11 = x_321;
    positionVar_48 = 31
  }));
  data_49.update(31, (() => {
    val x_322 = bindingMut_11;
    val x_323 = x_322.asInstanceOf[scala.Boolean];
    val x_324 = x_323.`unary_!`;
    if (x_324)
      {
        resetData_0 = false;
        positionVar_48 = 32
      }
    else
      positionVar_48 = 39
  }));
  data_49.update(32, (() => {
    val x_325 = resetData_0;
    val x_326 = x_325.asInstanceOf[scala.Boolean];
    bindingMut_10 = x_326;
    positionVar_48 = 33
  }));
  data_49.update(33, (() => {
    val x_327 = bindingMut_10;
    val x_328 = x_327.asInstanceOf[scala.Boolean];
    val x_329 = x_328.`unary_!`;
    if (x_329)
      {
        val x_330 = resetData_1.remove(0);
        val x_334 = x_330.find(((x_331: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_332 = x_331._1;
          val x_333 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_332.==(x_333)
        }));
        val x_335 = x_334.get;
        val x_336 = x_335._2;
        positionVar_48 = x_336
      }
    else
      positionVar_48 = 36
  }));
  data_49.update(34, (() => {
    val x_337 = resetData_0;
    val x_338 = x_337.asInstanceOf[scala.Any];
    bindingMut_44 = x_338;
    val x_339 = bindingMut_44;
    val x_340 = x_339.asInstanceOf[scala.Any];
    val x_341 = listValMut_45;
    val x_342 = x_341.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_342.reply(this, x_340);
    resetData_0 = ();
    positionVar_48 = 9
  }));
  data_49.update(35, (() => {
    positionVar_48 = 18;
    val x_343 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_344 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_343, 21);
    val x_345 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_344);
    resetData_1.prepend(x_345)
  }));
  data_49.update(36, (() => {
    val x_346 = bindingMut_10;
    val x_347 = x_346.asInstanceOf[scala.Boolean];
    if (x_347)
      {
        val x_348 = this.name;
        resetData_0 = x_348;
        val x_349 = resetData_0;
        val x_350 = x_349.asInstanceOf[java.lang.String];
        bindingMut_9 = x_350;
        val x_351 = bindingMut_9;
        val x_352 = x_351.asInstanceOf[java.lang.String];
        val x_353 = x_352.+(" sends a group chat to friends!");
        resetData_0 = x_353;
        val x_354 = resetData_0;
        val x_355 = x_354.asInstanceOf[java.lang.String];
        bindingMut_8 = x_355;
        val x_356 = bindingMut_8;
        val x_357 = x_356.asInstanceOf[java.lang.String];
        scala.Predef.println(x_357);
        resetData_0 = ();
        val x_358 = this.group;
        resetData_0 = x_358;
        val x_359 = resetData_0;
        val x_360 = x_359.asInstanceOf[scala.collection.immutable.List[generated.example.groupMessage.Person]];
        bindingMut_7 = x_360;
        val x_361 = bindingMut_7;
        val x_362 = x_361.asInstanceOf[scala.collection.immutable.List[generated.example.groupMessage.Person]];
        val x_363 = x_362.iterator;
        iterMut_6 = x_363;
        positionVar_48 = 37
      }
    else
      ()
  }));
  data_49.update(37, (() => {
    val x_364 = iterMut_6;
    val x_365 = x_364.asInstanceOf[scala.collection.Iterator[generated.example.groupMessage.Person]];
    val x_366 = x_365.hasNext;
    val x_367 = x_366.`unary_!`;
    if (x_367)
      {
        val x_368 = resetData_1.remove(0);
        val x_372 = x_368.find(((x_369: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_370 = x_369._1;
          val x_371 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_370.==(x_371)
        }));
        val x_373 = x_372.get;
        val x_374 = x_373._2;
        positionVar_48 = x_374
      }
    else
      positionVar_48 = 38
  }));
  data_49.update(38, (() => {
    val x_375 = iterMut_6;
    val x_376 = x_375.asInstanceOf[scala.collection.Iterator[generated.example.groupMessage.Person]];
    val x_377 = x_376.hasNext;
    if (x_377)
      {
        val x_378 = iterMut_6;
        val x_379 = x_378.asInstanceOf[scala.collection.Iterator[generated.example.groupMessage.Person]];
        val x_380 = x_379.next();
        listValMut_5 = x_380;
        val x_381 = this.future_objs;
        resetData_0 = x_381;
        val x_382 = resetData_0;
        val x_383 = x_382.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        bindingMut_4 = x_383;
        val x_384 = ((this): meta.deep.runtime.Actor).id;
        val x_386 = {
          val x_385 = listValMut_5;
          x_385.asInstanceOf[generated.example.groupMessage.Person]
        };
        val x_387 = x_386.id;
        val x_388 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_389 = meta.deep.runtime.RequestMessage.apply(x_384, x_387, 2, x_388);
        val x_390 = x_389.future;
        val x_391 = x_390.asInstanceOf[meta.deep.runtime.Future[scala.Boolean]];
        x_389.`future_=`(x_391);
        ((this): meta.deep.runtime.Actor).sendMessage(x_389);
        val x_392 = x_389.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_392, ((response_393: meta.deep.runtime.Message) => {
          val x_394 = x_389.future;
          val x_395 = response_393.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_396 = x_395.arg;
          val x_397 = x_394.setValue[scala.Any](x_396);
          val x_398 = x_397.asInstanceOf[meta.deep.runtime.Future[scala.Boolean]];
          x_389.`future_=`(x_398);
          val x_399 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_400 = x_389.future;
          val x_401 = x_400.id;
          val x_402 = scala.Predef.ArrowAssoc[java.lang.String](x_401);
          val x_403 = x_389.future;
          val x_404 = x_402.->[meta.deep.runtime.Future[scala.Any]](x_403);
          val x_405 = x_399.+[meta.deep.runtime.Future[scala.Any]](x_404);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_405)
        }));
        val x_406 = x_389.future;
        val x_407 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_406);
        resetData_0 = x_407;
        val x_408 = resetData_0;
        val x_409 = x_408.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Boolean]]];
        bindingMut_3 = x_409;
        val x_410 = bindingMut_3;
        val x_411 = x_410.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Boolean]]];
        val x_412 = bindingMut_4;
        val x_413 = x_412.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Boolean]]]];
        x_413.append(x_411);
        resetData_0 = ();
        positionVar_48 = 37
      }
    else
      ()
  }));
  data_49.update(39, (() => {
    val x_414 = bindingMut_11;
    val x_415 = x_414.asInstanceOf[scala.Boolean];
    if (x_415)
      {
        val x_416 = this.readByEveryone;
        resetData_0 = x_416;
        positionVar_48 = 32
      }
    else
      ()
  }));
  data_49.update(40, (() => {
    val x_417 = iterMut_46;
    val x_418 = x_417.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_419 = x_418.hasNext;
    val x_420 = x_419.`unary_!`;
    if (x_420)
      positionVar_48 = 41
    else
      ()
  }));
  data_49.update(41, (() => if (true)
    positionVar_48 = 2
  else
    positionVar_48 = 42));
  data_49.update(42, (() => {
    val x_421 = true.`unary_!`;
    if (x_421)
      {
        val x_422 = resetData_1.remove(0);
        val x_426 = x_422.find(((x_423: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_424 = x_423._1;
          val x_425 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_424.==(x_425)
        }));
        val x_427 = x_426.get;
        val x_428 = x_427._2;
        positionVar_48 = x_428
      }
    else
      ()
  }));
  data_49.update(43, (() => {
    val x_429 = resetData_0;
    val x_430 = x_429.asInstanceOf[scala.Any];
    bindingMut_44 = x_430;
    val x_431 = bindingMut_44;
    val x_432 = x_431.asInstanceOf[scala.Any];
    val x_433 = listValMut_45;
    val x_434 = x_433.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_434.reply(this, x_432);
    resetData_0 = ();
    positionVar_48 = 9
  }));
  data_49.update(44, (() => positionVar_48 = 45));
  data_49.update(45, (() => {
    positionVar_48 = 46;
    val x_435 = currentTurn;
    val x_436 = x_435.+(1);
    currentTurn = x_436
  }));
  data_49.update(46, (() => positionVar_48 = 45));
  data_49.update(47, (() => {
    positionVar_48 = 30;
    val x_437 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_438 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_437, 35);
    val x_439 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_438);
    resetData_1.prepend(x_439)
  }));
  data_49.update(48, (() => {
    val x_440 = bindingMut_25;
    val x_441 = x_440.asInstanceOf[scala.Boolean];
    val x_442 = x_441.`unary_!`;
    if (x_442)
      {
        val x_443 = bindingMut_26;
        val x_444 = x_443.asInstanceOf[scala.Int];
        val x_445 = x_444.<(5);
        resetData_0 = x_445;
        val x_446 = resetData_0;
        val x_447 = x_446.asInstanceOf[scala.Boolean];
        bindingMut_20 = x_447;
        positionVar_48 = 49
      }
    else
      ()
  }));
  data_49.update(49, (() => {
    val x_448 = bindingMut_20;
    val x_449 = x_448.asInstanceOf[scala.Boolean];
    if (x_449)
      {
        val x_450 = this.name;
        resetData_0 = x_450;
        val x_451 = resetData_0;
        val x_452 = x_451.asInstanceOf[java.lang.String];
        bindingMut_19 = x_452;
        val x_453 = bindingMut_19;
        val x_454 = x_453.asInstanceOf[java.lang.String];
        val x_455 = x_454.+(" sleeps!");
        resetData_0 = x_455;
        val x_456 = resetData_0;
        val x_457 = x_456.asInstanceOf[java.lang.String];
        bindingMut_18 = x_457;
        val x_458 = bindingMut_18;
        val x_459 = x_458.asInstanceOf[java.lang.String];
        scala.Predef.println(x_459);
        resetData_0 = ();
        val x_460 = 2.toDouble;
        resetData_0 = x_460;
        val x_461 = resetData_0;
        val x_462 = x_461.asInstanceOf[scala.Double];
        bindingMut_17 = x_462;
        resetData_0 = 0.0;
        val x_463 = resetData_0;
        val x_464 = x_463.asInstanceOf[scala.Double];
        bindingMut_16 = x_464;
        positionVar_48 = 50
      }
    else
      positionVar_48 = 53
  }));
  data_49.update(50, (() => {
    val x_465 = meta.deep.runtime.Actor.proceedLabel;
    val x_466 = x_465("turn");
    val x_467 = bindingMut_16;
    val x_468 = x_467.asInstanceOf[scala.Double];
    val x_469 = x_468.+(x_466);
    resetData_0 = x_469;
    val x_470 = resetData_0;
    val x_471 = x_470.asInstanceOf[scala.Double];
    bindingMut_16 = x_471;
    val x_472 = meta.deep.runtime.Actor.labelVals("turn");
    val x_473 = bindingMut_16;
    val x_474 = x_473.asInstanceOf[scala.Double];
    val x_475 = bindingMut_17;
    val x_476 = x_475.asInstanceOf[scala.Double];
    val x_477 = x_476.-(x_474);
    x_472.append(x_477);
    resetData_0 = ();
    positionVar_48 = 51;
    val x_478 = currentTurn;
    val x_479 = x_478.+(1);
    currentTurn = x_479
  }));
  data_49.update(51, (() => {
    val x_480 = bindingMut_16;
    val x_481 = x_480.asInstanceOf[scala.Double];
    val x_482 = bindingMut_17;
    val x_483 = x_482.asInstanceOf[scala.Double];
    val x_484 = x_481.<(x_483);
    if (x_484)
      positionVar_48 = 50
    else
      positionVar_48 = 52
  }));
  data_49.update(52, (() => {
    val x_485 = bindingMut_16;
    val x_486 = x_485.asInstanceOf[scala.Double];
    val x_487 = bindingMut_17;
    val x_488 = x_487.asInstanceOf[scala.Double];
    val x_489 = x_486.<(x_488);
    val x_490 = x_489.`unary_!`;
    if (x_490)
      {
        val x_491 = resetData_1.remove(0);
        val x_495 = x_491.find(((x_492: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_493 = x_492._1;
          val x_494 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_493.==(x_494)
        }));
        val x_496 = x_495.get;
        val x_497 = x_496._2;
        positionVar_48 = x_497
      }
    else
      ()
  }));
  data_49.update(53, (() => {
    val x_498 = bindingMut_20;
    val x_499 = x_498.asInstanceOf[scala.Boolean];
    val x_500 = x_499.`unary_!`;
    if (x_500)
      {
        val x_501 = this.name;
        resetData_0 = x_501;
        val x_502 = resetData_0;
        val x_503 = x_502.asInstanceOf[java.lang.String];
        bindingMut_15 = x_503;
        val x_504 = bindingMut_15;
        val x_505 = x_504.asInstanceOf[java.lang.String];
        val x_506 = x_505.+(" eats and exercises!");
        resetData_0 = x_506;
        val x_507 = resetData_0;
        val x_508 = x_507.asInstanceOf[java.lang.String];
        bindingMut_14 = x_508;
        val x_509 = bindingMut_14;
        val x_510 = x_509.asInstanceOf[java.lang.String];
        scala.Predef.println(x_510);
        resetData_0 = ();
        val x_511 = 1.toDouble;
        resetData_0 = x_511;
        val x_512 = resetData_0;
        val x_513 = x_512.asInstanceOf[scala.Double];
        bindingMut_13 = x_513;
        resetData_0 = 0.0;
        val x_514 = resetData_0;
        val x_515 = x_514.asInstanceOf[scala.Double];
        bindingMut_12 = x_515;
        positionVar_48 = 54
      }
    else
      ()
  }));
  data_49.update(54, (() => {
    val x_516 = meta.deep.runtime.Actor.proceedLabel;
    val x_517 = x_516("turn");
    val x_518 = bindingMut_12;
    val x_519 = x_518.asInstanceOf[scala.Double];
    val x_520 = x_519.+(x_517);
    resetData_0 = x_520;
    val x_521 = resetData_0;
    val x_522 = x_521.asInstanceOf[scala.Double];
    bindingMut_12 = x_522;
    val x_523 = meta.deep.runtime.Actor.labelVals("turn");
    val x_524 = bindingMut_12;
    val x_525 = x_524.asInstanceOf[scala.Double];
    val x_526 = bindingMut_13;
    val x_527 = x_526.asInstanceOf[scala.Double];
    val x_528 = x_527.-(x_525);
    x_523.append(x_528);
    resetData_0 = ();
    positionVar_48 = 55;
    val x_529 = currentTurn;
    val x_530 = x_529.+(1);
    currentTurn = x_530
  }));
  data_49.update(55, (() => {
    val x_531 = bindingMut_12;
    val x_532 = x_531.asInstanceOf[scala.Double];
    val x_533 = bindingMut_13;
    val x_534 = x_533.asInstanceOf[scala.Double];
    val x_535 = x_532.<(x_534);
    if (x_535)
      positionVar_48 = 54
    else
      positionVar_48 = 56
  }));
  data_49.update(56, (() => {
    val x_536 = bindingMut_12;
    val x_537 = x_536.asInstanceOf[scala.Double];
    val x_538 = bindingMut_13;
    val x_539 = x_538.asInstanceOf[scala.Double];
    val x_540 = x_537.<(x_539);
    val x_541 = x_540.`unary_!`;
    if (x_541)
      {
        val x_542 = resetData_1.remove(0);
        val x_546 = x_542.find(((x_543: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_544 = x_543._1;
          val x_545 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_544.==(x_545)
        }));
        val x_547 = x_546.get;
        val x_548 = x_547._2;
        positionVar_48 = x_548
      }
    else
      ()
  }));
  data_49.update(57, (() => {
    val x_549 = true.`unary_!`;
    if (x_549)
      {
        val x_550 = resetData_1.remove(0);
        val x_554 = x_550.find(((x_551: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_552 = x_551._1;
          val x_553 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_552.==(x_553)
        }));
        val x_555 = x_554.get;
        val x_556 = x_555._2;
        positionVar_48 = x_556
      }
    else
      ()
  }));
  data_49
}).apply();
  
  override def run_until(until_558: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_559 = currentTurn;
      val x_560 = x_559.<=(until_558);
      x_560.&&({
        val x_561 = positionVar_48;
        val x_562 = commands_557.length;
        x_561.<(x_562)
      })
    }) 
      {
        val x_563 = positionVar_48;
        val x_564 = commands_557.apply(x_563);
        x_564.apply()
      }
    ;
    this
  }
}
