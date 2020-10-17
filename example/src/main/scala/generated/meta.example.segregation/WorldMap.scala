package generated.meta.example.segregation

class WorldMap () extends meta.deep.runtime.Actor with meta.example.segregation.Torus2D {
  var width: Int = meta.example.segregation.segregationModel.worldWidth
  var height: Int = meta.example.segregation.segregationModel.worldHeight
  var radius: Int = meta.example.segregation.segregationModel.neighborhoodRadius
  var emptyLoc: scala.collection.mutable.ArrayBuffer[Int] = {
  val x_0 = scala.Predef.intWrapper(0);
  val x_1 = meta.example.segregation.segregationModel.worldWidth.*(meta.example.segregation.segregationModel.worldHeight);
  val x_2 = x_1.-(1);
  val x_3 = x_0.to(x_2);
  val x_4 = scala.collection.mutable.ArrayBuffer.canBuildFrom[scala.Int];
  x_3.to[scala.collection.mutable.ArrayBuffer](x_4)
}
  var similarities: scala.collection.mutable.Map[Int,Double] = scala.collection.mutable.Map.apply[scala.Int, scala.Double]()
  var world: scala.collection.mutable.Map[Int,generated.meta.example.segregation.Person] = scala.collection.mutable.Map.apply[scala.Int, generated.meta.example.segregation.Person]()
  var totalReports: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_6 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_7 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_8: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_9: scala.Int = 0;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: scala.Int = 0;
  private var bindingMut_12: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_13: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_14: generated.meta.example.segregation.Person = null;
  private var bindingMut_15: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: scala.Boolean = false;
  private var bindingMut_18: scala.Int = 0;
  private var bindingMut_19: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_20: scala.Int = 0;
  private var bindingMut_21: generated.meta.example.segregation.Person = null;
  private var bindingMut_22: scala.Boolean = false;
  private var bindingMut_23: scala.Option[generated.meta.example.segregation.Person] = null;
  private var bindingMut_24: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var listValMut_25: scala.Int = 0;
  private var iterMut_26: scala.collection.Iterator[scala.Int] = null;
  private var bindingMut_27: scala.collection.immutable.List[scala.Int] = null;
  private var bindingMut_28: scala.collection.immutable.Set[scala.Int] = null;
  private var bindingMut_29: scala.collection.immutable.Set[scala.Int] = null;
  private var bindingMut_30: scala.Int = 0;
  private var bindingMut_31: scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person] = null;
  private var methodArgsMut_32: scala.Int = 0;
  private var bindingMut_33: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_34: generated.meta.example.segregation.Person = null;
  private var bindingMut_35: scala.Option[generated.meta.example.segregation.Person] = null;
  private var bindingMut_36: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_37: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_38: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_39: scala.Int = 0;
  private var bindingMut_40: scala.Boolean = false;
  private var bindingMut_41: scala.Int = 0;
  private var bindingMut_42: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_43: scala.Double = 0.0;
  private var methodArgsMut_44: scala.Int = 0;
  private var bindingMut_45: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_46: scala.Int = 0;
  private var bindingMut_47: scala.Int = 0;
  private var bindingMut_48: scala.Int = 0;
  private var bindingMut_49: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_50: scala.Double = 0.0;
  private var bindingMut_51: scala.collection.Iterable[scala.Double] = null;
  private var bindingMut_52: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_53: scala.Double = 0.0;
  private var bindingMut_54: scala.Int = 0;
  private var bindingMut_55: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_56: scala.Double = 0.0;
  private var bindingMut_57: scala.collection.Iterable[scala.Double] = null;
  private var bindingMut_58: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_59: org.slf4j.Logger = null;
  private var bindingMut_60: scala.Boolean = false;
  private var bindingMut_61: scala.Int = 0;
  private var bindingMut_62: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_63: scala.Tuple3[scala.Int, scala.Int, scala.Long] = null;
  private var bindingMut_64: scala.Long = 0L;
  private var bindingMut_65: generated.meta.example.segregation.Person = null;
  private var bindingMut_66: scala.Int = 0;
  private var bindingMut_67: generated.meta.example.segregation.Person = null;
  private var bindingMut_68: scala.Int = 0;
  private var listValMut_69: scala.Tuple2[scala.Int, generated.meta.example.segregation.Person] = null;
  private var iterMut_70: scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_71: scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_72: scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_73: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_74: scala.Double = 0.0;
  private var bindingMut_75: scala.Double = 0.0;
  private var bindingMut_76: scala.Any = null;
  private var listValMut_77: meta.deep.runtime.RequestMessage = null;
  private var iterMut_78: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_80: scala.Int = 0;
  
  val commands_742 = (() => {
  val data_81 = new scala.Array[scala.Function0[scala.Unit]](54);
  data_81.update(0, (() => {
    positionVar_80 = 1;
    val x_82 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_83 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_82, 27);
    val x_84 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_83);
    resetData_1.prepend(x_84)
  }));
  data_81.update(1, (() => if (true)
    positionVar_80 = 2
  else
    positionVar_80 = 53));
  data_81.update(2, (() => {
    val x_85 = this.popRequestMessages;
    val x_86 = x_85.iterator;
    iterMut_78 = x_86;
    positionVar_80 = 3
  }));
  data_81.update(3, (() => {
    val x_87 = iterMut_78;
    val x_88 = x_87.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_89 = x_88.hasNext;
    if (x_89)
      {
        val x_90 = iterMut_78;
        val x_91 = x_90.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_92 = x_91.next();
        listValMut_77 = x_92;
        positionVar_80 = 4
      }
    else
      positionVar_80 = 52
  }));
  data_81.update(4, (() => {
    val x_93 = listValMut_77;
    val x_94 = x_93.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_95 = x_94.methodId;
    val x_96 = x_95.==(3);
    val x_97 = x_96.`unary_!`;
    if (x_97)
      positionVar_80 = 5
    else
      positionVar_80 = 51
  }));
  data_81.update(5, (() => {
    val x_98 = listValMut_77;
    val x_99 = x_98.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_100 = x_99.methodId;
    val x_101 = x_100.==(4);
    val x_102 = x_101.`unary_!`;
    if (x_102)
      positionVar_80 = 6
    else
      positionVar_80 = 48
  }));
  data_81.update(6, (() => {
    val x_103 = listValMut_77;
    val x_104 = x_103.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_105 = x_104.methodId;
    val x_106 = x_105.==(5);
    val x_107 = x_106.`unary_!`;
    if (x_107)
      positionVar_80 = 7
    else
      positionVar_80 = 42
  }));
  data_81.update(7, (() => {
    val x_108 = listValMut_77;
    val x_109 = x_108.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_110 = x_109.methodId;
    val x_111 = x_110.==(6);
    val x_112 = x_111.`unary_!`;
    if (x_112)
      positionVar_80 = 8
    else
      positionVar_80 = 33
  }));
  data_81.update(8, (() => {
    val x_113 = listValMut_77;
    val x_114 = x_113.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_115 = x_114.methodId;
    val x_116 = x_115.==(7);
    val x_117 = x_116.`unary_!`;
    if (x_117)
      positionVar_80 = 9
    else
      positionVar_80 = 32
  }));
  data_81.update(9, (() => {
    val x_118 = listValMut_77;
    val x_119 = x_118.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_120 = x_119.methodId;
    val x_121 = x_120.==(8);
    val x_122 = x_121.`unary_!`;
    if (x_122)
      positionVar_80 = 10
    else
      positionVar_80 = 17
  }));
  data_81.update(10, (() => {
    val x_123 = listValMut_77;
    val x_124 = x_123.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_125 = x_124.methodId;
    val x_126 = x_125.==(9);
    val x_127 = x_126.`unary_!`;
    if (x_127)
      positionVar_80 = 11
    else
      positionVar_80 = 14
  }));
  data_81.update(11, (() => {
    val x_128 = listValMut_77;
    val x_129 = x_128.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_130 = x_129.methodId;
    val x_131 = x_130.==(10);
    val x_132 = x_131.`unary_!`;
    if (x_132)
      {
        val x_133 = listValMut_77;
        val x_134 = x_133.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_135 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_134);
        val x_136 = this.addReceiveMessages(x_135);
        resetData_0 = x_136;
        positionVar_80 = 12
      }
    else
      positionVar_80 = 13
  }));
  data_81.update(12, (() => positionVar_80 = 3));
  data_81.update(13, (() => {
    val x_137 = listValMut_77;
    val x_138 = x_137.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_139 = x_138.methodId;
    val x_140 = x_139.==(10);
    if (x_140)
      positionVar_80 = 1
    else
      ();
    val x_141 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_142 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_141, 26);
    val x_143 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_142);
    resetData_1.prepend(x_143)
  }));
  data_81.update(14, (() => {
    val x_144 = listValMut_77;
    val x_145 = x_144.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_146 = x_145.methodId;
    val x_147 = x_146.==(9);
    if (x_147)
      {
        scala.Predef.println("***********");
        resetData_0 = ();
        scala.Predef.println("Loc View Id");
        resetData_0 = ();
        val x_148 = this.world;
        resetData_0 = x_148;
        val x_149 = resetData_0;
        val x_150 = x_149.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        bindingMut_73 = x_150;
        val x_151 = bindingMut_73;
        val x_152 = x_151.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_153 = x_152.toList;
        resetData_0 = x_153;
        val x_154 = resetData_0;
        val x_155 = x_154.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_72 = x_155;
        val x_156 = bindingMut_72;
        val x_157 = x_156.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_159 = x_157.sortBy[scala.Int](((pair_158: scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]) => pair_158._1))(scala.math.Ordering.Int);
        resetData_0 = x_159;
        val x_160 = resetData_0;
        val x_161 = x_160.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_71 = x_161;
        val x_162 = bindingMut_71;
        val x_163 = x_162.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_164 = x_163.iterator;
        iterMut_70 = x_164;
        positionVar_80 = 15
      }
    else
      ()
  }));
  data_81.update(15, (() => {
    val x_165 = iterMut_70;
    val x_166 = x_165.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
    val x_167 = x_166.hasNext;
    if (x_167)
      {
        val x_168 = iterMut_70;
        val x_169 = x_168.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_170 = x_169.next();
        listValMut_69 = x_170;
        val x_171 = listValMut_69;
        val x_172 = x_171.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_173 = x_172._1;
        resetData_0 = x_173;
        val x_174 = resetData_0;
        val x_175 = x_174.asInstanceOf[scala.Int];
        bindingMut_68 = x_175;
        val x_176 = listValMut_69;
        val x_177 = x_176.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_178 = x_177._2;
        resetData_0 = x_178;
        val x_179 = resetData_0;
        val x_180 = x_179.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_67 = x_180;
        val x_181 = bindingMut_67;
        val x_182 = x_181.asInstanceOf[generated.meta.example.segregation.Person];
        val x_183 = x_182.view;
        resetData_0 = x_183;
        val x_184 = resetData_0;
        val x_185 = x_184.asInstanceOf[scala.Int];
        bindingMut_66 = x_185;
        val x_186 = listValMut_69;
        val x_187 = x_186.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_188 = x_187._2;
        resetData_0 = x_188;
        val x_189 = resetData_0;
        val x_190 = x_189.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_65 = x_190;
        val x_191 = bindingMut_65;
        val x_192 = x_191.asInstanceOf[generated.meta.example.segregation.Person];
        val x_193 = x_192.id;
        resetData_0 = x_193;
        val x_194 = resetData_0;
        val x_195 = x_194.asInstanceOf[scala.Long];
        bindingMut_64 = x_195;
        val x_196 = bindingMut_64;
        val x_197 = x_196.asInstanceOf[scala.Long];
        val x_198 = bindingMut_66;
        val x_199 = x_198.asInstanceOf[scala.Int];
        val x_200 = bindingMut_68;
        val x_201 = x_200.asInstanceOf[scala.Int];
        val x_202 = scala.Tuple3.apply[scala.Int, scala.Int, scala.Long](x_201, x_199, x_197);
        resetData_0 = x_202;
        val x_203 = resetData_0;
        val x_204 = x_203.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        bindingMut_63 = x_204;
        val x_205 = bindingMut_63;
        val x_206 = x_205.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        scala.Predef.println(x_206);
        resetData_0 = ();
        positionVar_80 = 15
      }
    else
      positionVar_80 = 16
  }));
  data_81.update(16, (() => {
    val x_207 = iterMut_70;
    val x_208 = x_207.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
    val x_209 = x_208.hasNext;
    val x_210 = x_209.`unary_!`;
    if (x_210)
      {
        val x_211 = resetData_0;
        val x_212 = x_211.asInstanceOf[scala.Any];
        bindingMut_76 = x_212;
        val x_213 = bindingMut_76;
        val x_214 = x_213.asInstanceOf[scala.Any];
        val x_215 = listValMut_77;
        val x_216 = x_215.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_216.reply(this, x_214);
        resetData_0 = ();
        positionVar_80 = 12
      }
    else
      ()
  }));
  data_81.update(17, (() => {
    val x_217 = listValMut_77;
    val x_218 = x_217.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_219 = x_218.methodId;
    val x_220 = x_219.==(8);
    if (x_220)
      positionVar_80 = 18
    else
      ();
    val x_221 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_222 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_221, 30);
    val x_223 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_222);
    resetData_1.prepend(x_223)
  }));
  data_81.update(18, (() => {
    val x_224 = this.similarities;
    resetData_0 = x_224;
    val x_225 = resetData_0;
    val x_226 = x_225.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    bindingMut_62 = x_226;
    val x_227 = bindingMut_62;
    val x_228 = x_227.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_229 = x_228.size;
    resetData_0 = x_229;
    val x_230 = resetData_0;
    val x_231 = x_230.asInstanceOf[scala.Int];
    bindingMut_61 = x_231;
    val x_232 = bindingMut_61;
    val x_233 = x_232.asInstanceOf[scala.Int];
    val x_234 = x_233.>(0);
    resetData_0 = x_234;
    val x_235 = resetData_0;
    val x_236 = x_235.asInstanceOf[scala.Boolean];
    bindingMut_60 = x_236;
    positionVar_80 = 19
  }));
  data_81.update(19, (() => {
    val x_237 = bindingMut_60;
    val x_238 = x_237.asInstanceOf[scala.Boolean];
    val x_239 = x_238.`unary_!`;
    if (x_239)
      {
        resetData_0 = 0.0;
        val x_240 = resetData_1.remove(0);
        val x_244 = x_240.find(((x_241: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_242 = x_241._1;
          val x_243 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_242.==(x_243)
        }));
        val x_245 = x_244.get;
        val x_246 = x_245._2;
        positionVar_80 = x_246
      }
    else
      positionVar_80 = 31
  }));
  data_81.update(20, (() => {
    val x_247 = 1.toDouble;
    resetData_0 = x_247;
    val x_248 = resetData_0;
    val x_249 = x_248.asInstanceOf[scala.Double];
    bindingMut_75 = x_249;
    resetData_0 = 0.0;
    val x_250 = resetData_0;
    val x_251 = x_250.asInstanceOf[scala.Double];
    bindingMut_74 = x_251;
    positionVar_80 = 21
  }));
  data_81.update(21, (() => {
    val x_252 = meta.deep.runtime.Actor.proceedLabel;
    val x_253 = x_252("turn");
    val x_254 = bindingMut_74;
    val x_255 = x_254.asInstanceOf[scala.Double];
    val x_256 = x_255.+(x_253);
    resetData_0 = x_256;
    val x_257 = resetData_0;
    val x_258 = x_257.asInstanceOf[scala.Double];
    bindingMut_74 = x_258;
    val x_259 = meta.deep.runtime.Actor.labelVals("turn");
    val x_260 = bindingMut_74;
    val x_261 = x_260.asInstanceOf[scala.Double];
    val x_262 = bindingMut_75;
    val x_263 = x_262.asInstanceOf[scala.Double];
    val x_264 = x_263.-(x_261);
    x_259.append(x_264);
    resetData_0 = ();
    positionVar_80 = 22;
    val x_265 = currentTurn;
    val x_266 = x_265.+(1);
    currentTurn = x_266
  }));
  data_81.update(22, (() => {
    val x_267 = bindingMut_74;
    val x_268 = x_267.asInstanceOf[scala.Double];
    val x_269 = bindingMut_75;
    val x_270 = x_269.asInstanceOf[scala.Double];
    val x_271 = x_268.<(x_270);
    if (x_271)
      positionVar_80 = 21
    else
      positionVar_80 = 23
  }));
  data_81.update(23, (() => {
    val x_272 = bindingMut_74;
    val x_273 = x_272.asInstanceOf[scala.Double];
    val x_274 = bindingMut_75;
    val x_275 = x_274.asInstanceOf[scala.Double];
    val x_276 = x_273.<(x_275);
    val x_277 = x_276.`unary_!`;
    if (x_277)
      positionVar_80 = 24
    else
      ()
  }));
  data_81.update(24, (() => if (true)
    positionVar_80 = 2
  else
    positionVar_80 = 25));
  data_81.update(25, (() => {
    val x_278 = true.`unary_!`;
    if (x_278)
      {
        val x_279 = resetData_1.remove(0);
        val x_283 = x_279.find(((x_280: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_281 = x_280._1;
          val x_282 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_281.==(x_282)
        }));
        val x_284 = x_283.get;
        val x_285 = x_284._2;
        positionVar_80 = x_285
      }
    else
      ()
  }));
  data_81.update(26, (() => {
    val x_286 = resetData_0;
    val x_287 = x_286.asInstanceOf[scala.Any];
    bindingMut_76 = x_287;
    val x_288 = bindingMut_76;
    val x_289 = x_288.asInstanceOf[scala.Any];
    val x_290 = listValMut_77;
    val x_291 = x_290.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_291.reply(this, x_289);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(27, (() => positionVar_80 = 28));
  data_81.update(28, (() => {
    positionVar_80 = 29;
    val x_292 = currentTurn;
    val x_293 = x_292.+(1);
    currentTurn = x_293
  }));
  data_81.update(29, (() => positionVar_80 = 28));
  data_81.update(30, (() => {
    val x_294 = resetData_0;
    val x_295 = x_294.asInstanceOf[scala.Any];
    bindingMut_76 = x_295;
    val x_296 = bindingMut_76;
    val x_297 = x_296.asInstanceOf[scala.Any];
    val x_298 = listValMut_77;
    val x_299 = x_298.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_299.reply(this, x_297);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(31, (() => {
    val x_300 = bindingMut_60;
    val x_301 = x_300.asInstanceOf[scala.Boolean];
    if (x_301)
      {
        val x_302 = this.logger;
        resetData_0 = x_302;
        val x_303 = resetData_0;
        val x_304 = x_303.asInstanceOf[org.slf4j.Logger];
        bindingMut_59 = x_304;
        val x_305 = this.similarities;
        resetData_0 = x_305;
        val x_306 = resetData_0;
        val x_307 = x_306.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_58 = x_307;
        val x_308 = bindingMut_58;
        val x_309 = x_308.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_310 = x_309.values;
        resetData_0 = x_310;
        val x_311 = resetData_0;
        val x_312 = x_311.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_57 = x_312;
        val x_313 = bindingMut_57;
        val x_314 = x_313.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_315 = x_314.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_315;
        val x_316 = resetData_0;
        val x_317 = x_316.asInstanceOf[scala.Double];
        bindingMut_56 = x_317;
        val x_318 = this.similarities;
        resetData_0 = x_318;
        val x_319 = resetData_0;
        val x_320 = x_319.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_55 = x_320;
        val x_321 = bindingMut_55;
        val x_322 = x_321.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_323 = x_322.size;
        resetData_0 = x_323;
        val x_324 = resetData_0;
        val x_325 = x_324.asInstanceOf[scala.Int];
        bindingMut_54 = x_325;
        val x_326 = bindingMut_54;
        val x_327 = x_326.asInstanceOf[scala.Int];
        val x_328 = bindingMut_56;
        val x_329 = x_328.asInstanceOf[scala.Double];
        val x_330 = x_329./(x_327);
        resetData_0 = x_330;
        val x_331 = resetData_0;
        val x_332 = x_331.asInstanceOf[scala.Double];
        bindingMut_53 = x_332;
        val x_333 = bindingMut_53;
        val x_334 = x_333.asInstanceOf[scala.Double];
        val x_335 = bindingMut_59;
        val x_336 = x_335.asInstanceOf[org.slf4j.Logger];
        x_336.info("{}", x_334);
        resetData_0 = ();
        val x_337 = this.similarities;
        resetData_0 = x_337;
        val x_338 = resetData_0;
        val x_339 = x_338.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_52 = x_339;
        val x_340 = bindingMut_52;
        val x_341 = x_340.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_342 = x_341.values;
        resetData_0 = x_342;
        val x_343 = resetData_0;
        val x_344 = x_343.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_51 = x_344;
        val x_345 = bindingMut_51;
        val x_346 = x_345.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_347 = x_346.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_347;
        val x_348 = resetData_0;
        val x_349 = x_348.asInstanceOf[scala.Double];
        bindingMut_50 = x_349;
        val x_350 = this.similarities;
        resetData_0 = x_350;
        val x_351 = resetData_0;
        val x_352 = x_351.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_49 = x_352;
        val x_353 = bindingMut_49;
        val x_354 = x_353.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_355 = x_354.size;
        resetData_0 = x_355;
        val x_356 = resetData_0;
        val x_357 = x_356.asInstanceOf[scala.Int];
        bindingMut_48 = x_357;
        val x_358 = bindingMut_48;
        val x_359 = x_358.asInstanceOf[scala.Int];
        val x_360 = bindingMut_50;
        val x_361 = x_360.asInstanceOf[scala.Double];
        val x_362 = x_361./(x_359);
        resetData_0 = x_362;
        val x_363 = resetData_1.remove(0);
        val x_367 = x_363.find(((x_364: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_365 = x_364._1;
          val x_366 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_365.==(x_366)
        }));
        val x_368 = x_367.get;
        val x_369 = x_368._2;
        positionVar_80 = x_369
      }
    else
      ()
  }));
  data_81.update(32, (() => {
    val x_370 = listValMut_77;
    val x_371 = x_370.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_372 = x_371.methodId;
    val x_373 = x_372.==(7);
    if (x_373)
      {
        val x_374 = listValMut_77;
        val x_375 = x_374.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_376 = x_375.argss;
        val x_377 = x_376(0);
        val x_378 = x_377(0);
        x_7.prepend(x_378);
        val x_379 = listValMut_77;
        val x_380 = x_379.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_381 = x_380.argss;
        val x_382 = x_381(0);
        val x_383 = x_382(1);
        x_6.prepend(x_383);
        val x_384 = listValMut_77;
        val x_385 = x_384.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_386 = x_385.argss;
        val x_387 = x_386(0);
        val x_388 = x_387(0);
        val x_389 = x_388.asInstanceOf[scala.Int];
        methodArgsMut_44 = x_389;
        val x_390 = listValMut_77;
        val x_391 = x_390.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_392 = x_391.argss;
        val x_393 = x_392(0);
        val x_394 = x_393(1);
        val x_395 = x_394.asInstanceOf[scala.Double];
        methodArgsMut_43 = x_395;
        val x_396 = this.totalReports;
        resetData_0 = x_396;
        val x_397 = resetData_0;
        val x_398 = x_397.asInstanceOf[scala.Int];
        bindingMut_47 = x_398;
        val x_399 = bindingMut_47;
        val x_400 = x_399.asInstanceOf[scala.Int];
        val x_401 = x_400.+(1);
        resetData_0 = x_401;
        val x_402 = resetData_0;
        val x_403 = x_402.asInstanceOf[scala.Int];
        bindingMut_46 = x_403;
        val x_404 = bindingMut_46;
        val x_405 = x_404.asInstanceOf[scala.Int];
        this.`totalReports_=`(x_405);
        resetData_0 = ();
        val x_406 = this.similarities;
        resetData_0 = x_406;
        val x_407 = resetData_0;
        val x_408 = x_407.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_45 = x_408;
        val x_409 = methodArgsMut_43;
        val x_410 = x_409.asInstanceOf[scala.Double];
        val x_411 = methodArgsMut_44;
        val x_412 = x_411.asInstanceOf[scala.Int];
        val x_413 = bindingMut_45;
        val x_414 = x_413.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        x_414.update(x_412, x_410);
        resetData_0 = ();
        x_7.remove(0);
        val x_415 = x_7.isEmpty;
        val x_416 = x_415.`unary_!`;
        if (x_416)
          {
            val x_417 = x_7(0);
            val x_418 = x_417.asInstanceOf[scala.Int];
            methodArgsMut_44 = x_418
          }
        else
          ();
        x_6.remove(0);
        val x_419 = x_6.isEmpty;
        val x_420 = x_419.`unary_!`;
        if (x_420)
          {
            val x_421 = x_6(0);
            val x_422 = x_421.asInstanceOf[scala.Double];
            methodArgsMut_43 = x_422
          }
        else
          ();
        val x_423 = resetData_0;
        val x_424 = x_423.asInstanceOf[scala.Any];
        bindingMut_76 = x_424;
        val x_425 = bindingMut_76;
        val x_426 = x_425.asInstanceOf[scala.Any];
        val x_427 = listValMut_77;
        val x_428 = x_427.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_428.reply(this, x_426);
        resetData_0 = ();
        positionVar_80 = 12
      }
    else
      ()
  }));
  data_81.update(33, (() => {
    val x_429 = listValMut_77;
    val x_430 = x_429.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_431 = x_430.methodId;
    val x_432 = x_431.==(6);
    if (x_432)
      {
        val x_433 = listValMut_77;
        val x_434 = x_433.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_435 = x_434.argss;
        val x_436 = x_435(0);
        val x_437 = x_436(0);
        x_5.prepend(x_437);
        val x_438 = listValMut_77;
        val x_439 = x_438.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_440 = x_439.argss;
        val x_441 = x_440(0);
        val x_442 = x_441(0);
        val x_443 = x_442.asInstanceOf[scala.Int];
        methodArgsMut_32 = x_443;
        val x_444 = this.emptyLoc;
        resetData_0 = x_444;
        val x_445 = resetData_0;
        val x_446 = x_445.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_42 = x_446;
        val x_447 = bindingMut_42;
        val x_448 = x_447.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_449 = x_448.length;
        resetData_0 = x_449;
        val x_450 = resetData_0;
        val x_451 = x_450.asInstanceOf[scala.Int];
        bindingMut_41 = x_451;
        val x_452 = bindingMut_41;
        val x_453 = x_452.asInstanceOf[scala.Int];
        val x_454 = x_453.==(0);
        resetData_0 = x_454;
        val x_455 = resetData_0;
        val x_456 = x_455.asInstanceOf[scala.Boolean];
        bindingMut_40 = x_456;
        positionVar_80 = 34
      }
    else
      ()
  }));
  data_81.update(34, (() => {
    val x_457 = bindingMut_40;
    val x_458 = x_457.asInstanceOf[scala.Boolean];
    val x_459 = x_458.`unary_!`;
    if (x_459)
      positionVar_80 = 35
    else
      positionVar_80 = 41;
    val x_460 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_461 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_460, 38);
    val x_462 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_461);
    resetData_1.prepend(x_462)
  }));
  data_81.update(35, (() => {
    val x_463 = this.emptyLoc;
    resetData_0 = x_463;
    val x_464 = resetData_0;
    val x_465 = x_464.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_13 = x_465;
    val x_466 = this.emptyLoc;
    resetData_0 = x_466;
    val x_467 = resetData_0;
    val x_468 = x_467.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_12 = x_468;
    val x_469 = bindingMut_12;
    val x_470 = x_469.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_471 = x_470.length;
    resetData_0 = x_471;
    val x_472 = resetData_0;
    val x_473 = x_472.asInstanceOf[scala.Int];
    bindingMut_11 = x_473;
    val x_474 = bindingMut_11;
    val x_475 = x_474.asInstanceOf[scala.Int];
    val x_476 = scala.util.Random.nextInt(x_475);
    resetData_0 = x_476;
    val x_477 = resetData_0;
    val x_478 = x_477.asInstanceOf[scala.Int];
    bindingMut_10 = x_478;
    val x_479 = bindingMut_10;
    val x_480 = x_479.asInstanceOf[scala.Int];
    val x_481 = bindingMut_13;
    val x_482 = x_481.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_483 = x_482(x_480);
    resetData_0 = x_483;
    val x_484 = resetData_0;
    val x_485 = x_484.asInstanceOf[scala.Int];
    bindingMut_9 = x_485;
    val x_486 = this.emptyLoc;
    resetData_0 = x_486;
    val x_487 = resetData_0;
    val x_488 = x_487.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_8 = x_488;
    val x_489 = bindingMut_8;
    val x_490 = x_489.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_491 = bindingMut_9;
    val x_492 = x_491.asInstanceOf[scala.Int];
    val x_493 = x_490.-=(x_492);
    resetData_0 = x_493;
    val x_494 = bindingMut_9;
    val x_495 = x_494.asInstanceOf[scala.Int];
    resetData_0 = x_495;
    val x_496 = resetData_1.remove(0);
    val x_500 = x_496.find(((x_497: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_498 = x_497._1;
      val x_499 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_498.==(x_499)
    }));
    val x_501 = x_500.get;
    val x_502 = x_501._2;
    positionVar_80 = x_502
  }));
  data_81.update(36, (() => {
    val x_503 = resetData_0;
    val x_504 = x_503.asInstanceOf[scala.Int];
    bindingMut_16 = x_504;
    val x_505 = this.world;
    resetData_0 = x_505;
    val x_506 = resetData_0;
    val x_507 = x_506.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_15 = x_507;
    val x_508 = methodArgsMut_14;
    val x_509 = x_508.asInstanceOf[generated.meta.example.segregation.Person];
    val x_510 = bindingMut_15;
    val x_511 = x_510.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_512 = bindingMut_16;
    val x_513 = x_512.asInstanceOf[scala.Int];
    x_511.update(x_513, x_509);
    resetData_0 = ();
    val x_514 = bindingMut_16;
    val x_515 = x_514.asInstanceOf[scala.Int];
    resetData_0 = x_515;
    positionVar_80 = 37
  }));
  data_81.update(37, (() => {
    x_3.remove(0);
    val x_516 = x_3.isEmpty;
    val x_517 = x_516.`unary_!`;
    if (x_517)
      {
        val x_518 = x_3(0);
        val x_519 = x_518.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_14 = x_519
      }
    else
      ();
    val x_520 = resetData_0;
    val x_521 = x_520.asInstanceOf[scala.Any];
    bindingMut_76 = x_521;
    val x_522 = bindingMut_76;
    val x_523 = x_522.asInstanceOf[scala.Any];
    val x_524 = listValMut_77;
    val x_525 = x_524.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_525.reply(this, x_523);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(38, (() => {
    val x_526 = resetData_0;
    val x_527 = x_526.asInstanceOf[scala.Int];
    bindingMut_39 = x_527;
    val x_528 = this.emptyLoc;
    resetData_0 = x_528;
    val x_529 = resetData_0;
    val x_530 = x_529.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_38 = x_530;
    val x_531 = methodArgsMut_32;
    val x_532 = x_531.asInstanceOf[scala.Int];
    val x_533 = bindingMut_38;
    val x_534 = x_533.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    x_534.append(x_532);
    resetData_0 = ();
    val x_535 = this.world;
    resetData_0 = x_535;
    val x_536 = resetData_0;
    val x_537 = x_536.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_37 = x_537;
    val x_538 = this.world;
    resetData_0 = x_538;
    val x_539 = resetData_0;
    val x_540 = x_539.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_36 = x_540;
    val x_541 = methodArgsMut_32;
    val x_542 = x_541.asInstanceOf[scala.Int];
    val x_543 = bindingMut_36;
    val x_544 = x_543.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_545 = x_544.remove(x_542);
    resetData_0 = x_545;
    val x_546 = resetData_0;
    val x_547 = x_546.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    bindingMut_35 = x_547;
    val x_548 = bindingMut_35;
    val x_549 = x_548.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    val x_550 = x_549.get;
    resetData_0 = x_550;
    val x_551 = resetData_0;
    val x_552 = x_551.asInstanceOf[generated.meta.example.segregation.Person];
    bindingMut_34 = x_552;
    val x_553 = bindingMut_34;
    val x_554 = x_553.asInstanceOf[generated.meta.example.segregation.Person];
    val x_555 = bindingMut_37;
    val x_556 = x_555.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_557 = bindingMut_39;
    val x_558 = x_557.asInstanceOf[scala.Int];
    x_556.update(x_558, x_554);
    resetData_0 = ();
    val x_559 = this.similarities;
    resetData_0 = x_559;
    val x_560 = resetData_0;
    val x_561 = x_560.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    bindingMut_33 = x_561;
    val x_562 = methodArgsMut_32;
    val x_563 = x_562.asInstanceOf[scala.Int];
    val x_564 = bindingMut_33;
    val x_565 = x_564.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_566 = x_565.remove(x_563);
    resetData_0 = x_566;
    val x_567 = bindingMut_39;
    val x_568 = x_567.asInstanceOf[scala.Int];
    resetData_0 = x_568;
    positionVar_80 = 39
  }));
  data_81.update(39, (() => {
    x_5.remove(0);
    val x_569 = x_5.isEmpty;
    val x_570 = x_569.`unary_!`;
    if (x_570)
      {
        val x_571 = x_5(0);
        val x_572 = x_571.asInstanceOf[scala.Int];
        methodArgsMut_32 = x_572
      }
    else
      ();
    val x_573 = resetData_0;
    val x_574 = x_573.asInstanceOf[scala.Any];
    bindingMut_76 = x_574;
    val x_575 = bindingMut_76;
    val x_576 = x_575.asInstanceOf[scala.Any];
    val x_577 = listValMut_77;
    val x_578 = x_577.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_578.reply(this, x_576);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(40, (() => {
    val x_579 = resetData_0;
    val x_580 = x_579.asInstanceOf[scala.Any];
    bindingMut_76 = x_580;
    val x_581 = bindingMut_76;
    val x_582 = x_581.asInstanceOf[scala.Any];
    val x_583 = listValMut_77;
    val x_584 = x_583.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_584.reply(this, x_582);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(41, (() => {
    val x_585 = bindingMut_40;
    val x_586 = x_585.asInstanceOf[scala.Boolean];
    if (x_586)
      {
        val x_587 = methodArgsMut_32;
        val x_588 = x_587.asInstanceOf[scala.Int];
        resetData_0 = x_588;
        positionVar_80 = 39
      }
    else
      ()
  }));
  data_81.update(42, (() => {
    val x_589 = listValMut_77;
    val x_590 = x_589.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_591 = x_590.methodId;
    val x_592 = x_591.==(5);
    if (x_592)
      {
        val x_593 = listValMut_77;
        val x_594 = x_593.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_595 = x_594.argss;
        val x_596 = x_595(0);
        val x_597 = x_596(0);
        x_4.prepend(x_597);
        val x_598 = listValMut_77;
        val x_599 = x_598.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_600 = x_599.argss;
        val x_601 = x_600(0);
        val x_602 = x_601(0);
        val x_603 = x_602.asInstanceOf[scala.Int];
        methodArgsMut_20 = x_603;
        val x_604 = scala.collection.mutable.ArrayBuffer.apply[generated.meta.example.segregation.Person]();
        resetData_0 = x_604;
        val x_605 = resetData_0;
        val x_606 = x_605.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        bindingMut_31 = x_606;
        val x_607 = this.radius;
        resetData_0 = x_607;
        val x_608 = resetData_0;
        val x_609 = x_608.asInstanceOf[scala.Int];
        bindingMut_30 = x_609;
        val x_610 = methodArgsMut_20;
        val x_611 = x_610.asInstanceOf[scala.Int];
        val x_612 = bindingMut_30;
        val x_613 = x_612.asInstanceOf[scala.Int];
        val x_614 = this.neighborCells(x_611, x_613);
        resetData_0 = x_614;
        val x_615 = resetData_0;
        val x_616 = x_615.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_29 = x_616;
        val x_617 = methodArgsMut_20;
        val x_618 = x_617.asInstanceOf[scala.Int];
        val x_619 = bindingMut_29;
        val x_620 = x_619.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_621 = x_620.-(x_618);
        resetData_0 = x_621;
        val x_622 = resetData_0;
        val x_623 = x_622.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_28 = x_623;
        val x_624 = bindingMut_28;
        val x_625 = x_624.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_626 = x_625.toList;
        resetData_0 = x_626;
        val x_627 = resetData_0;
        val x_628 = x_627.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        bindingMut_27 = x_628;
        val x_629 = bindingMut_27;
        val x_630 = x_629.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_631 = x_630.iterator;
        iterMut_26 = x_631;
        positionVar_80 = 43
      }
    else
      ()
  }));
  data_81.update(43, (() => {
    val x_632 = iterMut_26;
    val x_633 = x_632.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_634 = x_633.hasNext;
    if (x_634)
      {
        val x_635 = iterMut_26;
        val x_636 = x_635.asInstanceOf[scala.collection.Iterator[scala.Int]];
        val x_637 = x_636.next();
        listValMut_25 = x_637;
        val x_638 = this.world;
        resetData_0 = x_638;
        val x_639 = resetData_0;
        val x_640 = x_639.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        bindingMut_24 = x_640;
        val x_641 = bindingMut_24;
        val x_642 = x_641.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_643 = listValMut_25;
        val x_644 = x_643.asInstanceOf[scala.Int];
        val x_645 = x_642.get(x_644);
        resetData_0 = x_645;
        val x_646 = resetData_0;
        val x_647 = x_646.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        bindingMut_23 = x_647;
        val x_648 = bindingMut_23;
        val x_649 = x_648.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_650 = x_649.isDefined;
        resetData_0 = x_650;
        val x_651 = resetData_0;
        val x_652 = x_651.asInstanceOf[scala.Boolean];
        bindingMut_22 = x_652;
        positionVar_80 = 44
      }
    else
      positionVar_80 = 47
  }));
  data_81.update(44, (() => {
    val x_653 = bindingMut_22;
    val x_654 = x_653.asInstanceOf[scala.Boolean];
    val x_655 = x_654.`unary_!`;
    if (x_655)
      positionVar_80 = 45
    else
      positionVar_80 = 46
  }));
  data_81.update(45, (() => positionVar_80 = 43));
  data_81.update(46, (() => {
    val x_656 = bindingMut_22;
    val x_657 = x_656.asInstanceOf[scala.Boolean];
    if (x_657)
      {
        val x_658 = bindingMut_23;
        val x_659 = x_658.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_660 = x_659.get;
        resetData_0 = x_660;
        val x_661 = resetData_0;
        val x_662 = x_661.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_21 = x_662;
        val x_663 = bindingMut_21;
        val x_664 = x_663.asInstanceOf[generated.meta.example.segregation.Person];
        val x_665 = bindingMut_31;
        val x_666 = x_665.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        x_666.append(x_664);
        resetData_0 = ();
        positionVar_80 = 45
      }
    else
      ()
  }));
  data_81.update(47, (() => {
    val x_667 = iterMut_26;
    val x_668 = x_667.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_669 = x_668.hasNext;
    val x_670 = x_669.`unary_!`;
    if (x_670)
      {
        val x_671 = bindingMut_31;
        val x_672 = x_671.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        val x_673 = x_672.toList;
        resetData_0 = x_673;
        x_4.remove(0);
        val x_674 = x_4.isEmpty;
        val x_675 = x_674.`unary_!`;
        if (x_675)
          {
            val x_676 = x_4(0);
            val x_677 = x_676.asInstanceOf[scala.Int];
            methodArgsMut_20 = x_677
          }
        else
          ();
        val x_678 = resetData_0;
        val x_679 = x_678.asInstanceOf[scala.Any];
        bindingMut_76 = x_679;
        val x_680 = bindingMut_76;
        val x_681 = x_680.asInstanceOf[scala.Any];
        val x_682 = listValMut_77;
        val x_683 = x_682.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_683.reply(this, x_681);
        resetData_0 = ();
        positionVar_80 = 12
      }
    else
      ()
  }));
  data_81.update(48, (() => {
    val x_684 = listValMut_77;
    val x_685 = x_684.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_686 = x_685.methodId;
    val x_687 = x_686.==(4);
    if (x_687)
      {
        val x_688 = listValMut_77;
        val x_689 = x_688.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_690 = x_689.argss;
        val x_691 = x_690(0);
        val x_692 = x_691(0);
        x_3.prepend(x_692);
        val x_693 = listValMut_77;
        val x_694 = x_693.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_695 = x_694.argss;
        val x_696 = x_695(0);
        val x_697 = x_696(0);
        val x_698 = x_697.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_14 = x_698;
        val x_699 = this.emptyLoc;
        resetData_0 = x_699;
        val x_700 = resetData_0;
        val x_701 = x_700.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_19 = x_701;
        val x_702 = bindingMut_19;
        val x_703 = x_702.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_704 = x_703.length;
        resetData_0 = x_704;
        val x_705 = resetData_0;
        val x_706 = x_705.asInstanceOf[scala.Int];
        bindingMut_18 = x_706;
        val x_707 = bindingMut_18;
        val x_708 = x_707.asInstanceOf[scala.Int];
        val x_709 = x_708.==(0);
        resetData_0 = x_709;
        val x_710 = resetData_0;
        val x_711 = x_710.asInstanceOf[scala.Boolean];
        bindingMut_17 = x_711;
        positionVar_80 = 49
      }
    else
      ()
  }));
  data_81.update(49, (() => {
    val x_712 = bindingMut_17;
    val x_713 = x_712.asInstanceOf[scala.Boolean];
    val x_714 = x_713.`unary_!`;
    if (x_714)
      positionVar_80 = 35
    else
      positionVar_80 = 50;
    val x_715 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_716 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_715, 36);
    val x_717 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_716);
    resetData_1.prepend(x_717)
  }));
  data_81.update(50, (() => {
    val x_718 = bindingMut_17;
    val x_719 = x_718.asInstanceOf[scala.Boolean];
    if (x_719)
      {
        resetData_0 = -1;
        positionVar_80 = 37
      }
    else
      ()
  }));
  data_81.update(51, (() => {
    val x_720 = listValMut_77;
    val x_721 = x_720.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_722 = x_721.methodId;
    val x_723 = x_722.==(3);
    if (x_723)
      positionVar_80 = 35
    else
      ();
    val x_724 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_725 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_724, 40);
    val x_726 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_725);
    resetData_1.prepend(x_726)
  }));
  data_81.update(52, (() => {
    val x_727 = iterMut_78;
    val x_728 = x_727.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_729 = x_728.hasNext;
    val x_730 = x_729.`unary_!`;
    if (x_730)
      positionVar_80 = 18
    else
      ();
    val x_731 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_732 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_731, 20);
    val x_733 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_732);
    resetData_1.prepend(x_733)
  }));
  data_81.update(53, (() => {
    val x_734 = true.`unary_!`;
    if (x_734)
      {
        val x_735 = resetData_1.remove(0);
        val x_739 = x_735.find(((x_736: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_737 = x_736._1;
          val x_738 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_737.==(x_738)
        }));
        val x_740 = x_739.get;
        val x_741 = x_740._2;
        positionVar_80 = x_741
      }
    else
      ()
  }));
  data_81
}).apply();
  
  override def run_until(until_743: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_744 = currentTurn;
      val x_745 = x_744.<=(until_743);
      x_745.&&({
        val x_746 = positionVar_80;
        val x_747 = commands_742.length;
        x_746.<(x_747)
      })
    }) 
      {
        val x_748 = positionVar_80;
        val x_749 = commands_742.apply(x_748);
        x_749.apply()
      }
    ;
    this
  }
}
