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
  
  val commands_341 = (() => {
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
    ((this): meta.deep.runtime.Actor).sendMessage(x_121);
    resetData_0 = scala.None;
    val x_122 = resetData_1.remove(0);
    val x_126 = x_122.find(((x_123: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_124 = x_123._1;
      val x_125 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_124.==(x_125)
    }));
    val x_127 = x_126.get;
    val x_128 = x_127._2;
    positionVar_24 = x_128
  }));
  data_25.update(18, (() => {
    x_3.remove(0);
    val x_129 = x_3.isEmpty;
    val x_130 = x_129.`unary_!`;
    if (x_130)
      {
        val x_131 = x_3(0);
        val x_132 = x_131.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_132
      }
    else
      ();
    val x_133 = resetData_0;
    val x_134 = x_133.asInstanceOf[scala.Any];
    bindingMut_11 = x_134;
    val x_135 = bindingMut_11;
    val x_136 = x_135.asInstanceOf[scala.Any];
    val x_137 = listValMut_12;
    val x_138 = x_137.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_138.reply(this, x_136);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(19, (() => {
    x_3.remove(0);
    val x_139 = x_3.isEmpty;
    val x_140 = x_139.`unary_!`;
    if (x_140)
      {
        val x_141 = x_3(0);
        val x_142 = x_141.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_142
      }
    else
      ();
    positionVar_24 = 20
  }));
  data_25.update(20, (() => positionVar_24 = 21));
  data_25.update(21, (() => {
    val x_143 = iterMut_19;
    val x_144 = x_143.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
    val x_145 = x_144.hasNext;
    val x_146 = x_145.`unary_!`;
    if (x_146)
      positionVar_24 = 22
    else
      positionVar_24 = 26
  }));
  data_25.update(22, (() => {
    val x_147 = 1.toDouble;
    resetData_0 = x_147;
    val x_148 = resetData_0;
    val x_149 = x_148.asInstanceOf[scala.Double];
    bindingMut_15 = x_149;
    resetData_0 = 0.0;
    val x_150 = resetData_0;
    val x_151 = x_150.asInstanceOf[scala.Double];
    bindingMut_14 = x_151;
    positionVar_24 = 23
  }));
  data_25.update(23, (() => {
    val x_152 = meta.deep.runtime.Actor.proceedLabel;
    val x_153 = x_152("turn");
    val x_154 = bindingMut_14;
    val x_155 = x_154.asInstanceOf[scala.Double];
    val x_156 = x_155.+(x_153);
    resetData_0 = x_156;
    val x_157 = resetData_0;
    val x_158 = x_157.asInstanceOf[scala.Double];
    bindingMut_14 = x_158;
    val x_159 = meta.deep.runtime.Actor.labelVals("turn");
    val x_160 = bindingMut_14;
    val x_161 = x_160.asInstanceOf[scala.Double];
    val x_162 = bindingMut_15;
    val x_163 = x_162.asInstanceOf[scala.Double];
    val x_164 = x_163.-(x_161);
    x_159.append(x_164);
    resetData_0 = ();
    positionVar_24 = 24;
    val x_165 = currentTurn;
    val x_166 = x_165.+(1);
    currentTurn = x_166
  }));
  data_25.update(24, (() => {
    val x_167 = bindingMut_14;
    val x_168 = x_167.asInstanceOf[scala.Double];
    val x_169 = bindingMut_15;
    val x_170 = x_169.asInstanceOf[scala.Double];
    val x_171 = x_168.<(x_170);
    if (x_171)
      positionVar_24 = 23
    else
      positionVar_24 = 25
  }));
  data_25.update(25, (() => {
    val x_172 = bindingMut_14;
    val x_173 = x_172.asInstanceOf[scala.Double];
    val x_174 = bindingMut_15;
    val x_175 = x_174.asInstanceOf[scala.Double];
    val x_176 = x_173.<(x_175);
    val x_177 = x_176.`unary_!`;
    if (x_177)
      {
        val x_178 = this.popRequestMessages;
        val x_179 = x_178.iterator;
        iterMut_13 = x_179;
        positionVar_24 = 9
      }
    else
      ()
  }));
  data_25.update(26, (() => {
    val x_180 = iterMut_19;
    val x_181 = x_180.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
    val x_182 = x_181.hasNext;
    if (x_182)
      {
        val x_183 = iterMut_19;
        val x_184 = x_183.asInstanceOf[scala.collection.Iterator[example.rumor.Person]];
        val x_185 = x_184.next();
        listValMut_18 = x_185;
        val x_186 = scala.util.Random.nextBoolean();
        resetData_0 = x_186;
        val x_187 = resetData_0;
        val x_188 = x_187.asInstanceOf[scala.Boolean];
        bindingMut_17 = x_188;
        positionVar_24 = 27
      }
    else
      ()
  }));
  data_25.update(27, (() => {
    val x_189 = bindingMut_17;
    val x_190 = x_189.asInstanceOf[scala.Boolean];
    val x_191 = x_190.`unary_!`;
    if (x_191)
      positionVar_24 = 20
    else
      positionVar_24 = 28
  }));
  data_25.update(28, (() => {
    val x_192 = bindingMut_17;
    val x_193 = x_192.asInstanceOf[scala.Boolean];
    if (x_193)
      {
        val x_194 = listValMut_18;
        val x_195 = x_194.asInstanceOf[example.rumor.Person];
        val x_196 = x_195.asInstanceOf[generated.example.rumor.Gossiper];
        resetData_0 = x_196;
        val x_197 = resetData_0;
        val x_198 = x_197.asInstanceOf[generated.example.rumor.Gossiper];
        bindingMut_16 = x_198;
        val x_199 = bindingMut_16;
        val x_200 = x_199.asInstanceOf[generated.example.rumor.Gossiper];
        x_3.prepend(x_200);
        val x_201 = bindingMut_16;
        val x_202 = x_201.asInstanceOf[generated.example.rumor.Gossiper];
        val x_203 = x_202.asInstanceOf[generated.example.rumor.Gossiper];
        methodArgsMut_10 = x_203;
        positionVar_24 = 17
      }
    else
      ();
    val x_204 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_205 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_204, 19);
    val x_206 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_205);
    resetData_1.prepend(x_206)
  }));
  data_25.update(29, (() => {
    val x_207 = listValMut_12;
    val x_208 = x_207.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_209 = x_208.methodId;
    val x_210 = x_209.==(2);
    if (x_210)
      positionVar_24 = 3
    else
      ();
    val x_211 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_212 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_211, 7);
    val x_213 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_212);
    resetData_1.prepend(x_213)
  }));
  data_25.update(30, (() => {
    val x_214 = listValMut_12;
    val x_215 = x_214.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_216 = x_215.methodId;
    val x_217 = x_216.==(1);
    if (x_217)
      {
        this.`heardRumor_=`(true);
        resetData_0 = ();
        val x_218 = resetData_0;
        val x_219 = x_218.asInstanceOf[scala.Any];
        bindingMut_11 = x_219;
        val x_220 = bindingMut_11;
        val x_221 = x_220.asInstanceOf[scala.Any];
        val x_222 = listValMut_12;
        val x_223 = x_222.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_223.reply(this, x_221);
        resetData_0 = ();
        positionVar_24 = 8
      }
    else
      ()
  }));
  data_25.update(31, (() => {
    val x_224 = listValMut_12;
    val x_225 = x_224.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_226 = x_225.methodId;
    val x_227 = x_226.==(0);
    if (x_227)
      positionVar_24 = 32
    else
      ();
    val x_228 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_229 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_228, 34);
    val x_230 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_229);
    resetData_1.prepend(x_230)
  }));
  data_25.update(32, (() => {
    val x_231 = this.heardRumor;
    resetData_0 = x_231;
    val x_232 = resetData_0;
    val x_233 = x_232.asInstanceOf[scala.Boolean];
    bindingMut_6 = x_233;
    positionVar_24 = 33
  }));
  data_25.update(33, (() => {
    val x_234 = bindingMut_6;
    val x_235 = x_234.asInstanceOf[scala.Boolean];
    val x_236 = x_235.`unary_!`;
    if (x_236)
      {
        resetData_0 = false;
        val x_237 = resetData_1.remove(0);
        val x_241 = x_237.find(((x_238: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_239 = x_238._1;
          val x_240 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_239.==(x_240)
        }));
        val x_242 = x_241.get;
        val x_243 = x_242._2;
        positionVar_24 = x_243
      }
    else
      positionVar_24 = 38
  }));
  data_25.update(34, (() => {
    val x_244 = resetData_0;
    val x_245 = x_244.asInstanceOf[scala.Any];
    bindingMut_11 = x_245;
    val x_246 = bindingMut_11;
    val x_247 = x_246.asInstanceOf[scala.Any];
    val x_248 = listValMut_12;
    val x_249 = x_248.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_249.reply(this, x_247);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(35, (() => {
    val x_250 = resetData_0;
    val x_251 = x_250.asInstanceOf[scala.Boolean];
    bindingMut_22 = x_251;
    positionVar_24 = 36
  }));
  data_25.update(36, (() => {
    val x_252 = bindingMut_22;
    val x_253 = x_252.asInstanceOf[scala.Boolean];
    val x_254 = x_253.`unary_!`;
    if (x_254)
      positionVar_24 = 22
    else
      positionVar_24 = 37
  }));
  data_25.update(37, (() => {
    val x_255 = bindingMut_22;
    val x_256 = x_255.asInstanceOf[scala.Boolean];
    if (x_256)
      {
        val x_257 = this.network;
        resetData_0 = x_257;
        val x_258 = resetData_0;
        val x_259 = x_258.asInstanceOf[scala.collection.mutable.Set[example.rumor.Person]];
        bindingMut_21 = x_259;
        val x_260 = bindingMut_21;
        val x_261 = x_260.asInstanceOf[scala.collection.mutable.Set[example.rumor.Person]];
        val x_262 = x_261.toList;
        resetData_0 = x_262;
        val x_263 = resetData_0;
        val x_264 = x_263.asInstanceOf[scala.collection.immutable.List[example.rumor.Person]];
        bindingMut_20 = x_264;
        val x_265 = bindingMut_20;
        val x_266 = x_265.asInstanceOf[scala.collection.immutable.List[example.rumor.Person]];
        val x_267 = x_266.iterator;
        iterMut_19 = x_267;
        positionVar_24 = 21
      }
    else
      ()
  }));
  data_25.update(38, (() => {
    val x_268 = bindingMut_6;
    val x_269 = x_268.asInstanceOf[scala.Boolean];
    if (x_269)
      {
        val x_270 = scala.util.Random.nextDouble();
        resetData_0 = x_270;
        val x_271 = resetData_0;
        val x_272 = x_271.asInstanceOf[scala.Double];
        bindingMut_5 = x_272;
        val x_273 = this.spreadProb;
        resetData_0 = x_273;
        val x_274 = resetData_0;
        val x_275 = x_274.asInstanceOf[scala.Double];
        bindingMut_4 = x_275;
        val x_276 = bindingMut_4;
        val x_277 = x_276.asInstanceOf[scala.Double];
        val x_278 = bindingMut_5;
        val x_279 = x_278.asInstanceOf[scala.Double];
        val x_280 = x_279.>(x_277);
        resetData_0 = x_280;
        val x_281 = resetData_1.remove(0);
        val x_285 = x_281.find(((x_282: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_283 = x_282._1;
          val x_284 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_283.==(x_284)
        }));
        val x_286 = x_285.get;
        val x_287 = x_286._2;
        positionVar_24 = x_287
      }
    else
      ()
  }));
  data_25.update(39, (() => {
    val x_288 = iterMut_13;
    val x_289 = x_288.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_290 = x_289.hasNext;
    val x_291 = x_290.`unary_!`;
    if (x_291)
      positionVar_24 = 40
    else
      ()
  }));
  data_25.update(40, (() => if (true)
    positionVar_24 = 2
  else
    positionVar_24 = 41));
  data_25.update(41, (() => {
    val x_292 = true.`unary_!`;
    if (x_292)
      {
        val x_293 = resetData_1.remove(0);
        val x_297 = x_293.find(((x_294: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_295 = x_294._1;
          val x_296 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_295.==(x_296)
        }));
        val x_298 = x_297.get;
        val x_299 = x_298._2;
        positionVar_24 = x_299
      }
    else
      ()
  }));
  data_25.update(42, (() => {
    val x_300 = resetData_0;
    val x_301 = x_300.asInstanceOf[scala.Any];
    bindingMut_11 = x_301;
    val x_302 = bindingMut_11;
    val x_303 = x_302.asInstanceOf[scala.Any];
    val x_304 = listValMut_12;
    val x_305 = x_304.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_305.reply(this, x_303);
    resetData_0 = ();
    positionVar_24 = 8
  }));
  data_25.update(43, (() => positionVar_24 = 44));
  data_25.update(44, (() => {
    positionVar_24 = 45;
    val x_306 = currentTurn;
    val x_307 = x_306.+(1);
    currentTurn = x_307
  }));
  data_25.update(45, (() => positionVar_24 = 44));
  data_25.update(46, (() => {
    positionVar_24 = 32;
    val x_308 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_309 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_308, 35);
    val x_310 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_309);
    resetData_1.prepend(x_310)
  }));
  data_25.update(47, (() => {
    val x_311 = bindingMut_7;
    val x_312 = x_311.asInstanceOf[scala.Boolean];
    if (x_312)
      {
        val receiver_313 = this.env;
        val x_314 = ((this): meta.deep.runtime.Actor).id;
        val x_315 = receiver_313.id;
        val x_316 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_317 = meta.deep.runtime.RequestMessage.apply(x_314, x_315, 5, x_316);
        ((this): meta.deep.runtime.Actor).sendMessage(x_317);
        resetData_0 = scala.None;
        this.`reported_=`(true);
        resetData_0 = ();
        val x_318 = resetData_1.remove(0);
        val x_322 = x_318.find(((x_319: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_320 = x_319._1;
          val x_321 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_320.==(x_321)
        }));
        val x_323 = x_322.get;
        val x_324 = x_323._2;
        positionVar_24 = x_324
      }
    else
      ()
  }));
  data_25.update(48, (() => {
    val x_325 = bindingMut_9;
    val x_326 = x_325.asInstanceOf[scala.Boolean];
    if (x_326)
      {
        val x_327 = this.reported;
        resetData_0 = x_327;
        val x_328 = resetData_0;
        val x_329 = x_328.asInstanceOf[scala.Boolean];
        bindingMut_8 = x_329;
        val x_330 = bindingMut_8;
        val x_331 = x_330.asInstanceOf[scala.Boolean];
        val x_332 = x_331.`unary_!`;
        resetData_0 = x_332;
        positionVar_24 = 5
      }
    else
      ()
  }));
  data_25.update(49, (() => {
    val x_333 = true.`unary_!`;
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
        positionVar_24 = x_340
      }
    else
      ()
  }));
  data_25
}).apply();
  
  override def run_until(until_342: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_343 = currentTurn;
      val x_344 = x_343.<=(until_342);
      x_344.&&({
        val x_345 = positionVar_24;
        val x_346 = commands_341.length;
        x_345.<(x_346)
      })
    }) 
      {
        val x_347 = positionVar_24;
        val x_348 = commands_341.apply(x_347);
        x_348.apply()
      }
    ;
    this
  }
}
