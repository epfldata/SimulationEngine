package generated.meta.example.segregation

class WorldMap () extends meta.deep.runtime.Actor with meta.example.segregation.Torus2D with Serializable {
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
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_6 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_7 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_8 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_9: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: scala.Int = 0;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_14: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_15: generated.meta.example.segregation.Person = null;
  private var bindingMut_16: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_17: scala.Int = 0;
  private var bindingMut_18: scala.Boolean = false;
  private var bindingMut_19: scala.Int = 0;
  private var bindingMut_20: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_21: scala.Int = 0;
  private var bindingMut_22: generated.meta.example.segregation.Person = null;
  private var bindingMut_23: scala.Boolean = false;
  private var bindingMut_24: scala.Option[generated.meta.example.segregation.Person] = null;
  private var bindingMut_25: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var listValMut_26: scala.Int = 0;
  private var iterMut_27: scala.collection.Iterator[scala.Int] = null;
  private var bindingMut_28: scala.collection.immutable.List[scala.Int] = null;
  private var bindingMut_29: scala.collection.immutable.Set[scala.Int] = null;
  private var bindingMut_30: scala.collection.immutable.Set[scala.Int] = null;
  private var bindingMut_31: scala.Int = 0;
  private var bindingMut_32: scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person] = null;
  private var methodArgsMut_33: scala.Int = 0;
  private var bindingMut_34: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_35: generated.meta.example.segregation.Person = null;
  private var bindingMut_36: scala.Option[generated.meta.example.segregation.Person] = null;
  private var bindingMut_37: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_38: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_39: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var bindingMut_40: scala.Int = 0;
  private var bindingMut_41: scala.Boolean = false;
  private var bindingMut_42: scala.Int = 0;
  private var bindingMut_43: scala.collection.mutable.ArrayBuffer[scala.Int] = null;
  private var methodArgsMut_44: scala.Double = 0.0;
  private var methodArgsMut_45: scala.Int = 0;
  private var bindingMut_46: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_47: scala.Int = 0;
  private var bindingMut_48: scala.Int = 0;
  private var bindingMut_49: scala.Int = 0;
  private var bindingMut_50: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_51: scala.Double = 0.0;
  private var bindingMut_52: scala.collection.Iterable[scala.Double] = null;
  private var bindingMut_53: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_54: scala.Double = 0.0;
  private var bindingMut_55: scala.Int = 0;
  private var bindingMut_56: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_57: scala.Double = 0.0;
  private var bindingMut_58: scala.collection.Iterable[scala.Double] = null;
  private var bindingMut_59: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
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
  private var bindingMut_74: scala.Int = 0;
  private var bindingMut_75: scala.Any = null;
  private var listValMut_76: meta.deep.runtime.RequestMessage = null;
  private var iterMut_77: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_79: scala.Int = 0;
  
  val commands_725 = (() => {
  val data_80 = new scala.Array[scala.Function0[scala.Unit]](54);
  data_80.update(0, (() => {
    positionVar_79 = 1;
    val x_81 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_82 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_81, 27);
    val x_83 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_82);
    resetData_1.prepend(x_83)
  }));
  data_80.update(1, (() => if (true)
    positionVar_79 = 2
  else
    positionVar_79 = 53));
  data_80.update(2, (() => {
    val x_84 = this.popRequestMessages;
    val x_85 = x_84.iterator;
    iterMut_77 = x_85;
    positionVar_79 = 3
  }));
  data_80.update(3, (() => {
    val x_86 = iterMut_77;
    val x_87 = x_86.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_88 = x_87.hasNext;
    if (x_88)
      {
        val x_89 = iterMut_77;
        val x_90 = x_89.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_91 = x_90.next();
        listValMut_76 = x_91;
        positionVar_79 = 4
      }
    else
      positionVar_79 = 52
  }));
  data_80.update(4, (() => {
    val x_92 = listValMut_76;
    val x_93 = x_92.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_94 = x_93.methodId;
    val x_95 = x_94.==(3);
    val x_96 = x_95.`unary_!`;
    if (x_96)
      positionVar_79 = 5
    else
      positionVar_79 = 51
  }));
  data_80.update(5, (() => {
    val x_97 = listValMut_76;
    val x_98 = x_97.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_99 = x_98.methodId;
    val x_100 = x_99.==(4);
    val x_101 = x_100.`unary_!`;
    if (x_101)
      positionVar_79 = 6
    else
      positionVar_79 = 48
  }));
  data_80.update(6, (() => {
    val x_102 = listValMut_76;
    val x_103 = x_102.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_104 = x_103.methodId;
    val x_105 = x_104.==(5);
    val x_106 = x_105.`unary_!`;
    if (x_106)
      positionVar_79 = 7
    else
      positionVar_79 = 42
  }));
  data_80.update(7, (() => {
    val x_107 = listValMut_76;
    val x_108 = x_107.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_109 = x_108.methodId;
    val x_110 = x_109.==(6);
    val x_111 = x_110.`unary_!`;
    if (x_111)
      positionVar_79 = 8
    else
      positionVar_79 = 33
  }));
  data_80.update(8, (() => {
    val x_112 = listValMut_76;
    val x_113 = x_112.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_114 = x_113.methodId;
    val x_115 = x_114.==(7);
    val x_116 = x_115.`unary_!`;
    if (x_116)
      positionVar_79 = 9
    else
      positionVar_79 = 32
  }));
  data_80.update(9, (() => {
    val x_117 = listValMut_76;
    val x_118 = x_117.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_119 = x_118.methodId;
    val x_120 = x_119.==(8);
    val x_121 = x_120.`unary_!`;
    if (x_121)
      positionVar_79 = 10
    else
      positionVar_79 = 17
  }));
  data_80.update(10, (() => {
    val x_122 = listValMut_76;
    val x_123 = x_122.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_124 = x_123.methodId;
    val x_125 = x_124.==(9);
    val x_126 = x_125.`unary_!`;
    if (x_126)
      positionVar_79 = 11
    else
      positionVar_79 = 14
  }));
  data_80.update(11, (() => {
    val x_127 = listValMut_76;
    val x_128 = x_127.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_129 = x_128.methodId;
    val x_130 = x_129.==(10);
    val x_131 = x_130.`unary_!`;
    if (x_131)
      {
        val x_132 = listValMut_76;
        val x_133 = x_132.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_134 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_133);
        val x_135 = this.addReceiveMessages(x_134);
        resetData_0 = x_135;
        positionVar_79 = 12
      }
    else
      positionVar_79 = 13
  }));
  data_80.update(12, (() => positionVar_79 = 3));
  data_80.update(13, (() => {
    val x_136 = listValMut_76;
    val x_137 = x_136.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_138 = x_137.methodId;
    val x_139 = x_138.==(10);
    if (x_139)
      positionVar_79 = 1
    else
      ();
    val x_140 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_141 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_140, 26);
    val x_142 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_141);
    resetData_1.prepend(x_142)
  }));
  data_80.update(14, (() => {
    val x_143 = listValMut_76;
    val x_144 = x_143.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_145 = x_144.methodId;
    val x_146 = x_145.==(9);
    if (x_146)
      {
        scala.Predef.println("***********");
        resetData_0 = ();
        scala.Predef.println("Loc View Id");
        resetData_0 = ();
        val x_147 = this.world;
        resetData_0 = x_147;
        val x_148 = resetData_0;
        val x_149 = x_148.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        bindingMut_73 = x_149;
        val x_150 = bindingMut_73;
        val x_151 = x_150.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_152 = x_151.toList;
        resetData_0 = x_152;
        val x_153 = resetData_0;
        val x_154 = x_153.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_72 = x_154;
        val x_155 = bindingMut_72;
        val x_156 = x_155.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_158 = x_156.sortBy[scala.Int](((pair_157: scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]) => pair_157._1))(scala.math.Ordering.Int);
        resetData_0 = x_158;
        val x_159 = resetData_0;
        val x_160 = x_159.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_71 = x_160;
        val x_161 = bindingMut_71;
        val x_162 = x_161.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_163 = x_162.iterator;
        iterMut_70 = x_163;
        positionVar_79 = 15
      }
    else
      ()
  }));
  data_80.update(15, (() => {
    val x_164 = iterMut_70;
    val x_165 = x_164.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
    val x_166 = x_165.hasNext;
    if (x_166)
      {
        val x_167 = iterMut_70;
        val x_168 = x_167.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_169 = x_168.next();
        listValMut_69 = x_169;
        val x_170 = listValMut_69;
        val x_171 = x_170.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_172 = x_171._1;
        resetData_0 = x_172;
        val x_173 = resetData_0;
        val x_174 = x_173.asInstanceOf[scala.Int];
        bindingMut_68 = x_174;
        val x_175 = listValMut_69;
        val x_176 = x_175.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_177 = x_176._2;
        resetData_0 = x_177;
        val x_178 = resetData_0;
        val x_179 = x_178.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_67 = x_179;
        val x_180 = bindingMut_67;
        val x_181 = x_180.asInstanceOf[generated.meta.example.segregation.Person];
        val x_182 = x_181.view;
        resetData_0 = x_182;
        val x_183 = resetData_0;
        val x_184 = x_183.asInstanceOf[scala.Int];
        bindingMut_66 = x_184;
        val x_185 = listValMut_69;
        val x_186 = x_185.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_187 = x_186._2;
        resetData_0 = x_187;
        val x_188 = resetData_0;
        val x_189 = x_188.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_65 = x_189;
        val x_190 = bindingMut_65;
        val x_191 = x_190.asInstanceOf[generated.meta.example.segregation.Person];
        val x_192 = x_191.id;
        resetData_0 = x_192;
        val x_193 = resetData_0;
        val x_194 = x_193.asInstanceOf[scala.Long];
        bindingMut_64 = x_194;
        val x_195 = bindingMut_64;
        val x_196 = x_195.asInstanceOf[scala.Long];
        val x_197 = bindingMut_66;
        val x_198 = x_197.asInstanceOf[scala.Int];
        val x_199 = bindingMut_68;
        val x_200 = x_199.asInstanceOf[scala.Int];
        val x_201 = scala.Tuple3.apply[scala.Int, scala.Int, scala.Long](x_200, x_198, x_196);
        resetData_0 = x_201;
        val x_202 = resetData_0;
        val x_203 = x_202.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        bindingMut_63 = x_203;
        val x_204 = bindingMut_63;
        val x_205 = x_204.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        scala.Predef.println(x_205);
        resetData_0 = ();
        positionVar_79 = 15
      }
    else
      positionVar_79 = 16
  }));
  data_80.update(16, (() => {
    val x_206 = iterMut_70;
    val x_207 = x_206.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
    val x_208 = x_207.hasNext;
    val x_209 = x_208.`unary_!`;
    if (x_209)
      {
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[scala.Any];
        bindingMut_75 = x_211;
        val x_212 = bindingMut_75;
        val x_213 = x_212.asInstanceOf[scala.Any];
        val x_214 = listValMut_76;
        val x_215 = x_214.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_215.reply(this, x_213);
        resetData_0 = ();
        positionVar_79 = 12
      }
    else
      ()
  }));
  data_80.update(17, (() => {
    val x_216 = listValMut_76;
    val x_217 = x_216.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_218 = x_217.methodId;
    val x_219 = x_218.==(8);
    if (x_219)
      positionVar_79 = 18
    else
      ();
    val x_220 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_221 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_220, 30);
    val x_222 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_221);
    resetData_1.prepend(x_222)
  }));
  data_80.update(18, (() => {
    val x_223 = this.similarities;
    resetData_0 = x_223;
    val x_224 = resetData_0;
    val x_225 = x_224.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    bindingMut_62 = x_225;
    val x_226 = bindingMut_62;
    val x_227 = x_226.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_228 = x_227.size;
    resetData_0 = x_228;
    val x_229 = resetData_0;
    val x_230 = x_229.asInstanceOf[scala.Int];
    bindingMut_61 = x_230;
    val x_231 = bindingMut_61;
    val x_232 = x_231.asInstanceOf[scala.Int];
    val x_233 = x_232.>(0);
    resetData_0 = x_233;
    val x_234 = resetData_0;
    val x_235 = x_234.asInstanceOf[scala.Boolean];
    bindingMut_60 = x_235;
    positionVar_79 = 19
  }));
  data_80.update(19, (() => {
    val x_236 = bindingMut_60;
    val x_237 = x_236.asInstanceOf[scala.Boolean];
    val x_238 = x_237.`unary_!`;
    if (x_238)
      {
        resetData_0 = 0.0;
        val x_239 = resetData_1.remove(0);
        val x_243 = x_239.find(((x_240: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_241 = x_240._1;
          val x_242 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_241.==(x_242)
        }));
        val x_244 = x_243.get;
        val x_245 = x_244._2;
        positionVar_79 = x_245
      }
    else
      positionVar_79 = 31
  }));
  data_80.update(20, (() => {
    resetData_0 = 0;
    val x_246 = resetData_0;
    val x_247 = x_246.asInstanceOf[scala.Int];
    bindingMut_74 = x_247;
    positionVar_79 = 21
  }));
  data_80.update(21, (() => {
    val x_248 = bindingMut_74;
    val x_249 = x_248.asInstanceOf[scala.Int];
    val x_250 = (1).-(x_249);
    meta.deep.runtime.Actor.waitTurnList.append(x_250);
    resetData_0 = ();
    val x_251 = meta.deep.runtime.Actor.minTurn();
    val x_252 = bindingMut_74;
    val x_253 = x_252.asInstanceOf[scala.Int];
    val x_254 = x_253.+(x_251);
    resetData_0 = x_254;
    val x_255 = resetData_0;
    val x_256 = x_255.asInstanceOf[scala.Int];
    bindingMut_74 = x_256;
    positionVar_79 = 22;
    val x_257 = currentTurn;
    val x_258 = x_257.+(1);
    currentTurn = x_258
  }));
  data_80.update(22, (() => {
    val x_259 = bindingMut_74;
    val x_260 = x_259.asInstanceOf[scala.Int];
    val x_261 = x_260.<(1);
    if (x_261)
      positionVar_79 = 21
    else
      positionVar_79 = 23
  }));
  data_80.update(23, (() => {
    val x_262 = bindingMut_74;
    val x_263 = x_262.asInstanceOf[scala.Int];
    val x_264 = x_263.<(1);
    val x_265 = x_264.`unary_!`;
    if (x_265)
      positionVar_79 = 24
    else
      ()
  }));
  data_80.update(24, (() => if (true)
    positionVar_79 = 2
  else
    positionVar_79 = 25));
  data_80.update(25, (() => {
    val x_266 = true.`unary_!`;
    if (x_266)
      {
        val x_267 = resetData_1.remove(0);
        val x_271 = x_267.find(((x_268: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_269 = x_268._1;
          val x_270 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_269.==(x_270)
        }));
        val x_272 = x_271.get;
        val x_273 = x_272._2;
        positionVar_79 = x_273
      }
    else
      ()
  }));
  data_80.update(26, (() => {
    val x_274 = resetData_0;
    val x_275 = x_274.asInstanceOf[scala.Any];
    bindingMut_75 = x_275;
    val x_276 = bindingMut_75;
    val x_277 = x_276.asInstanceOf[scala.Any];
    val x_278 = listValMut_76;
    val x_279 = x_278.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_279.reply(this, x_277);
    resetData_0 = ();
    positionVar_79 = 12
  }));
  data_80.update(27, (() => positionVar_79 = 28));
  data_80.update(28, (() => {
    positionVar_79 = 29;
    val x_280 = currentTurn;
    val x_281 = x_280.+(1);
    currentTurn = x_281
  }));
  data_80.update(29, (() => positionVar_79 = 28));
  data_80.update(30, (() => {
    val x_282 = resetData_0;
    val x_283 = x_282.asInstanceOf[scala.Any];
    bindingMut_75 = x_283;
    val x_284 = bindingMut_75;
    val x_285 = x_284.asInstanceOf[scala.Any];
    val x_286 = listValMut_76;
    val x_287 = x_286.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_287.reply(this, x_285);
    resetData_0 = ();
    positionVar_79 = 12
  }));
  data_80.update(31, (() => {
    val x_288 = bindingMut_60;
    val x_289 = x_288.asInstanceOf[scala.Boolean];
    if (x_289)
      {
        val x_290 = this.similarities;
        resetData_0 = x_290;
        val x_291 = resetData_0;
        val x_292 = x_291.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_59 = x_292;
        val x_293 = bindingMut_59;
        val x_294 = x_293.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_295 = x_294.values;
        resetData_0 = x_295;
        val x_296 = resetData_0;
        val x_297 = x_296.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_58 = x_297;
        val x_298 = bindingMut_58;
        val x_299 = x_298.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_300 = x_299.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_300;
        val x_301 = resetData_0;
        val x_302 = x_301.asInstanceOf[scala.Double];
        bindingMut_57 = x_302;
        val x_303 = this.similarities;
        resetData_0 = x_303;
        val x_304 = resetData_0;
        val x_305 = x_304.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_56 = x_305;
        val x_306 = bindingMut_56;
        val x_307 = x_306.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_308 = x_307.size;
        resetData_0 = x_308;
        val x_309 = resetData_0;
        val x_310 = x_309.asInstanceOf[scala.Int];
        bindingMut_55 = x_310;
        val x_311 = bindingMut_55;
        val x_312 = x_311.asInstanceOf[scala.Int];
        val x_313 = bindingMut_57;
        val x_314 = x_313.asInstanceOf[scala.Double];
        val x_315 = x_314./(x_312);
        resetData_0 = x_315;
        val x_316 = resetData_0;
        val x_317 = x_316.asInstanceOf[scala.Double];
        bindingMut_54 = x_317;
        val x_318 = bindingMut_54;
        val x_319 = x_318.asInstanceOf[scala.Double];
        scala.Predef.println(x_319);
        resetData_0 = ();
        val x_320 = this.similarities;
        resetData_0 = x_320;
        val x_321 = resetData_0;
        val x_322 = x_321.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_53 = x_322;
        val x_323 = bindingMut_53;
        val x_324 = x_323.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_325 = x_324.values;
        resetData_0 = x_325;
        val x_326 = resetData_0;
        val x_327 = x_326.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_52 = x_327;
        val x_328 = bindingMut_52;
        val x_329 = x_328.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_330 = x_329.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_330;
        val x_331 = resetData_0;
        val x_332 = x_331.asInstanceOf[scala.Double];
        bindingMut_51 = x_332;
        val x_333 = this.similarities;
        resetData_0 = x_333;
        val x_334 = resetData_0;
        val x_335 = x_334.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_50 = x_335;
        val x_336 = bindingMut_50;
        val x_337 = x_336.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_338 = x_337.size;
        resetData_0 = x_338;
        val x_339 = resetData_0;
        val x_340 = x_339.asInstanceOf[scala.Int];
        bindingMut_49 = x_340;
        val x_341 = bindingMut_49;
        val x_342 = x_341.asInstanceOf[scala.Int];
        val x_343 = bindingMut_51;
        val x_344 = x_343.asInstanceOf[scala.Double];
        val x_345 = x_344./(x_342);
        resetData_0 = x_345;
        val x_346 = resetData_1.remove(0);
        val x_350 = x_346.find(((x_347: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_348 = x_347._1;
          val x_349 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_348.==(x_349)
        }));
        val x_351 = x_350.get;
        val x_352 = x_351._2;
        positionVar_79 = x_352
      }
    else
      ()
  }));
  data_80.update(32, (() => {
    val x_353 = listValMut_76;
    val x_354 = x_353.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_355 = x_354.methodId;
    val x_356 = x_355.==(7);
    if (x_356)
      {
        val x_357 = listValMut_76;
        val x_358 = x_357.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_359 = x_358.argss;
        val x_360 = x_359(0);
        val x_361 = x_360(0);
        x_8.prepend(x_361);
        val x_362 = listValMut_76;
        val x_363 = x_362.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_364 = x_363.argss;
        val x_365 = x_364(0);
        val x_366 = x_365(1);
        x_7.prepend(x_366);
        val x_367 = listValMut_76;
        val x_368 = x_367.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_369 = x_368.argss;
        val x_370 = x_369(0);
        val x_371 = x_370(0);
        val x_372 = x_371.asInstanceOf[scala.Int];
        methodArgsMut_45 = x_372;
        val x_373 = listValMut_76;
        val x_374 = x_373.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_375 = x_374.argss;
        val x_376 = x_375(0);
        val x_377 = x_376(1);
        val x_378 = x_377.asInstanceOf[scala.Double];
        methodArgsMut_44 = x_378;
        val x_379 = this.totalReports;
        resetData_0 = x_379;
        val x_380 = resetData_0;
        val x_381 = x_380.asInstanceOf[scala.Int];
        bindingMut_48 = x_381;
        val x_382 = bindingMut_48;
        val x_383 = x_382.asInstanceOf[scala.Int];
        val x_384 = x_383.+(1);
        resetData_0 = x_384;
        val x_385 = resetData_0;
        val x_386 = x_385.asInstanceOf[scala.Int];
        bindingMut_47 = x_386;
        val x_387 = bindingMut_47;
        val x_388 = x_387.asInstanceOf[scala.Int];
        this.`totalReports_=`(x_388);
        resetData_0 = ();
        val x_389 = this.similarities;
        resetData_0 = x_389;
        val x_390 = resetData_0;
        val x_391 = x_390.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_46 = x_391;
        val x_392 = methodArgsMut_44;
        val x_393 = x_392.asInstanceOf[scala.Double];
        val x_394 = methodArgsMut_45;
        val x_395 = x_394.asInstanceOf[scala.Int];
        val x_396 = bindingMut_46;
        val x_397 = x_396.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        x_397.update(x_395, x_393);
        resetData_0 = ();
        x_8.remove(0);
        val x_398 = x_8.isEmpty;
        val x_399 = x_398.`unary_!`;
        if (x_399)
          {
            val x_400 = x_8(0);
            val x_401 = x_400.asInstanceOf[scala.Int];
            methodArgsMut_45 = x_401
          }
        else
          ();
        x_7.remove(0);
        val x_402 = x_7.isEmpty;
        val x_403 = x_402.`unary_!`;
        if (x_403)
          {
            val x_404 = x_7(0);
            val x_405 = x_404.asInstanceOf[scala.Double];
            methodArgsMut_44 = x_405
          }
        else
          ();
        val x_406 = resetData_0;
        val x_407 = x_406.asInstanceOf[scala.Any];
        bindingMut_75 = x_407;
        val x_408 = bindingMut_75;
        val x_409 = x_408.asInstanceOf[scala.Any];
        val x_410 = listValMut_76;
        val x_411 = x_410.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_411.reply(this, x_409);
        resetData_0 = ();
        positionVar_79 = 12
      }
    else
      ()
  }));
  data_80.update(33, (() => {
    val x_412 = listValMut_76;
    val x_413 = x_412.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_414 = x_413.methodId;
    val x_415 = x_414.==(6);
    if (x_415)
      {
        val x_416 = listValMut_76;
        val x_417 = x_416.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_418 = x_417.argss;
        val x_419 = x_418(0);
        val x_420 = x_419(0);
        x_6.prepend(x_420);
        val x_421 = listValMut_76;
        val x_422 = x_421.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_423 = x_422.argss;
        val x_424 = x_423(0);
        val x_425 = x_424(0);
        val x_426 = x_425.asInstanceOf[scala.Int];
        methodArgsMut_33 = x_426;
        val x_427 = this.emptyLoc;
        resetData_0 = x_427;
        val x_428 = resetData_0;
        val x_429 = x_428.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_43 = x_429;
        val x_430 = bindingMut_43;
        val x_431 = x_430.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_432 = x_431.length;
        resetData_0 = x_432;
        val x_433 = resetData_0;
        val x_434 = x_433.asInstanceOf[scala.Int];
        bindingMut_42 = x_434;
        val x_435 = bindingMut_42;
        val x_436 = x_435.asInstanceOf[scala.Int];
        val x_437 = x_436.==(0);
        resetData_0 = x_437;
        val x_438 = resetData_0;
        val x_439 = x_438.asInstanceOf[scala.Boolean];
        bindingMut_41 = x_439;
        positionVar_79 = 34
      }
    else
      ()
  }));
  data_80.update(34, (() => {
    val x_440 = bindingMut_41;
    val x_441 = x_440.asInstanceOf[scala.Boolean];
    val x_442 = x_441.`unary_!`;
    if (x_442)
      positionVar_79 = 35
    else
      positionVar_79 = 41;
    val x_443 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_444 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_443, 38);
    val x_445 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_444);
    resetData_1.prepend(x_445)
  }));
  data_80.update(35, (() => {
    val x_446 = this.emptyLoc;
    resetData_0 = x_446;
    val x_447 = resetData_0;
    val x_448 = x_447.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_14 = x_448;
    val x_449 = this.emptyLoc;
    resetData_0 = x_449;
    val x_450 = resetData_0;
    val x_451 = x_450.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_13 = x_451;
    val x_452 = bindingMut_13;
    val x_453 = x_452.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_454 = x_453.length;
    resetData_0 = x_454;
    val x_455 = resetData_0;
    val x_456 = x_455.asInstanceOf[scala.Int];
    bindingMut_12 = x_456;
    val x_457 = bindingMut_12;
    val x_458 = x_457.asInstanceOf[scala.Int];
    val x_459 = scala.util.Random.nextInt(x_458);
    resetData_0 = x_459;
    val x_460 = resetData_0;
    val x_461 = x_460.asInstanceOf[scala.Int];
    bindingMut_11 = x_461;
    val x_462 = bindingMut_11;
    val x_463 = x_462.asInstanceOf[scala.Int];
    val x_464 = bindingMut_14;
    val x_465 = x_464.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_466 = x_465(x_463);
    resetData_0 = x_466;
    val x_467 = resetData_0;
    val x_468 = x_467.asInstanceOf[scala.Int];
    bindingMut_10 = x_468;
    val x_469 = this.emptyLoc;
    resetData_0 = x_469;
    val x_470 = resetData_0;
    val x_471 = x_470.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_9 = x_471;
    val x_472 = bindingMut_9;
    val x_473 = x_472.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_474 = bindingMut_10;
    val x_475 = x_474.asInstanceOf[scala.Int];
    val x_476 = x_473.-=(x_475);
    resetData_0 = x_476;
    val x_477 = bindingMut_10;
    val x_478 = x_477.asInstanceOf[scala.Int];
    resetData_0 = x_478;
    val x_479 = resetData_1.remove(0);
    val x_483 = x_479.find(((x_480: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_481 = x_480._1;
      val x_482 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_481.==(x_482)
    }));
    val x_484 = x_483.get;
    val x_485 = x_484._2;
    positionVar_79 = x_485
  }));
  data_80.update(36, (() => {
    val x_486 = resetData_0;
    val x_487 = x_486.asInstanceOf[scala.Int];
    bindingMut_17 = x_487;
    val x_488 = this.world;
    resetData_0 = x_488;
    val x_489 = resetData_0;
    val x_490 = x_489.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_16 = x_490;
    val x_491 = methodArgsMut_15;
    val x_492 = x_491.asInstanceOf[generated.meta.example.segregation.Person];
    val x_493 = bindingMut_16;
    val x_494 = x_493.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_495 = bindingMut_17;
    val x_496 = x_495.asInstanceOf[scala.Int];
    x_494.update(x_496, x_492);
    resetData_0 = ();
    val x_497 = bindingMut_17;
    val x_498 = x_497.asInstanceOf[scala.Int];
    resetData_0 = x_498;
    positionVar_79 = 37
  }));
  data_80.update(37, (() => {
    x_4.remove(0);
    val x_499 = x_4.isEmpty;
    val x_500 = x_499.`unary_!`;
    if (x_500)
      {
        val x_501 = x_4(0);
        val x_502 = x_501.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_15 = x_502
      }
    else
      ();
    val x_503 = resetData_0;
    val x_504 = x_503.asInstanceOf[scala.Any];
    bindingMut_75 = x_504;
    val x_505 = bindingMut_75;
    val x_506 = x_505.asInstanceOf[scala.Any];
    val x_507 = listValMut_76;
    val x_508 = x_507.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_508.reply(this, x_506);
    resetData_0 = ();
    positionVar_79 = 12
  }));
  data_80.update(38, (() => {
    val x_509 = resetData_0;
    val x_510 = x_509.asInstanceOf[scala.Int];
    bindingMut_40 = x_510;
    val x_511 = this.emptyLoc;
    resetData_0 = x_511;
    val x_512 = resetData_0;
    val x_513 = x_512.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_39 = x_513;
    val x_514 = methodArgsMut_33;
    val x_515 = x_514.asInstanceOf[scala.Int];
    val x_516 = bindingMut_39;
    val x_517 = x_516.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    x_517.append(x_515);
    resetData_0 = ();
    val x_518 = this.world;
    resetData_0 = x_518;
    val x_519 = resetData_0;
    val x_520 = x_519.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_38 = x_520;
    val x_521 = this.world;
    resetData_0 = x_521;
    val x_522 = resetData_0;
    val x_523 = x_522.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_37 = x_523;
    val x_524 = methodArgsMut_33;
    val x_525 = x_524.asInstanceOf[scala.Int];
    val x_526 = bindingMut_37;
    val x_527 = x_526.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_528 = x_527.remove(x_525);
    resetData_0 = x_528;
    val x_529 = resetData_0;
    val x_530 = x_529.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    bindingMut_36 = x_530;
    val x_531 = bindingMut_36;
    val x_532 = x_531.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    val x_533 = x_532.get;
    resetData_0 = x_533;
    val x_534 = resetData_0;
    val x_535 = x_534.asInstanceOf[generated.meta.example.segregation.Person];
    bindingMut_35 = x_535;
    val x_536 = bindingMut_35;
    val x_537 = x_536.asInstanceOf[generated.meta.example.segregation.Person];
    val x_538 = bindingMut_38;
    val x_539 = x_538.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_540 = bindingMut_40;
    val x_541 = x_540.asInstanceOf[scala.Int];
    x_539.update(x_541, x_537);
    resetData_0 = ();
    val x_542 = this.similarities;
    resetData_0 = x_542;
    val x_543 = resetData_0;
    val x_544 = x_543.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    bindingMut_34 = x_544;
    val x_545 = methodArgsMut_33;
    val x_546 = x_545.asInstanceOf[scala.Int];
    val x_547 = bindingMut_34;
    val x_548 = x_547.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_549 = x_548.remove(x_546);
    resetData_0 = x_549;
    val x_550 = bindingMut_40;
    val x_551 = x_550.asInstanceOf[scala.Int];
    resetData_0 = x_551;
    positionVar_79 = 39
  }));
  data_80.update(39, (() => {
    x_6.remove(0);
    val x_552 = x_6.isEmpty;
    val x_553 = x_552.`unary_!`;
    if (x_553)
      {
        val x_554 = x_6(0);
        val x_555 = x_554.asInstanceOf[scala.Int];
        methodArgsMut_33 = x_555
      }
    else
      ();
    val x_556 = resetData_0;
    val x_557 = x_556.asInstanceOf[scala.Any];
    bindingMut_75 = x_557;
    val x_558 = bindingMut_75;
    val x_559 = x_558.asInstanceOf[scala.Any];
    val x_560 = listValMut_76;
    val x_561 = x_560.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_561.reply(this, x_559);
    resetData_0 = ();
    positionVar_79 = 12
  }));
  data_80.update(40, (() => {
    val x_562 = resetData_0;
    val x_563 = x_562.asInstanceOf[scala.Any];
    bindingMut_75 = x_563;
    val x_564 = bindingMut_75;
    val x_565 = x_564.asInstanceOf[scala.Any];
    val x_566 = listValMut_76;
    val x_567 = x_566.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_567.reply(this, x_565);
    resetData_0 = ();
    positionVar_79 = 12
  }));
  data_80.update(41, (() => {
    val x_568 = bindingMut_41;
    val x_569 = x_568.asInstanceOf[scala.Boolean];
    if (x_569)
      {
        val x_570 = methodArgsMut_33;
        val x_571 = x_570.asInstanceOf[scala.Int];
        resetData_0 = x_571;
        positionVar_79 = 39
      }
    else
      ()
  }));
  data_80.update(42, (() => {
    val x_572 = listValMut_76;
    val x_573 = x_572.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_574 = x_573.methodId;
    val x_575 = x_574.==(5);
    if (x_575)
      {
        val x_576 = listValMut_76;
        val x_577 = x_576.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_578 = x_577.argss;
        val x_579 = x_578(0);
        val x_580 = x_579(0);
        x_5.prepend(x_580);
        val x_581 = listValMut_76;
        val x_582 = x_581.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_583 = x_582.argss;
        val x_584 = x_583(0);
        val x_585 = x_584(0);
        val x_586 = x_585.asInstanceOf[scala.Int];
        methodArgsMut_21 = x_586;
        val x_587 = scala.collection.mutable.ArrayBuffer.apply[generated.meta.example.segregation.Person]();
        resetData_0 = x_587;
        val x_588 = resetData_0;
        val x_589 = x_588.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        bindingMut_32 = x_589;
        val x_590 = this.radius;
        resetData_0 = x_590;
        val x_591 = resetData_0;
        val x_592 = x_591.asInstanceOf[scala.Int];
        bindingMut_31 = x_592;
        val x_593 = methodArgsMut_21;
        val x_594 = x_593.asInstanceOf[scala.Int];
        val x_595 = bindingMut_31;
        val x_596 = x_595.asInstanceOf[scala.Int];
        val x_597 = this.neighborCells(x_594, x_596);
        resetData_0 = x_597;
        val x_598 = resetData_0;
        val x_599 = x_598.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_30 = x_599;
        val x_600 = methodArgsMut_21;
        val x_601 = x_600.asInstanceOf[scala.Int];
        val x_602 = bindingMut_30;
        val x_603 = x_602.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_604 = x_603.-(x_601);
        resetData_0 = x_604;
        val x_605 = resetData_0;
        val x_606 = x_605.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_29 = x_606;
        val x_607 = bindingMut_29;
        val x_608 = x_607.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_609 = x_608.toList;
        resetData_0 = x_609;
        val x_610 = resetData_0;
        val x_611 = x_610.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        bindingMut_28 = x_611;
        val x_612 = bindingMut_28;
        val x_613 = x_612.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_614 = x_613.iterator;
        iterMut_27 = x_614;
        positionVar_79 = 43
      }
    else
      ()
  }));
  data_80.update(43, (() => {
    val x_615 = iterMut_27;
    val x_616 = x_615.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_617 = x_616.hasNext;
    if (x_617)
      {
        val x_618 = iterMut_27;
        val x_619 = x_618.asInstanceOf[scala.collection.Iterator[scala.Int]];
        val x_620 = x_619.next();
        listValMut_26 = x_620;
        val x_621 = this.world;
        resetData_0 = x_621;
        val x_622 = resetData_0;
        val x_623 = x_622.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        bindingMut_25 = x_623;
        val x_624 = bindingMut_25;
        val x_625 = x_624.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_626 = listValMut_26;
        val x_627 = x_626.asInstanceOf[scala.Int];
        val x_628 = x_625.get(x_627);
        resetData_0 = x_628;
        val x_629 = resetData_0;
        val x_630 = x_629.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        bindingMut_24 = x_630;
        val x_631 = bindingMut_24;
        val x_632 = x_631.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_633 = x_632.isDefined;
        resetData_0 = x_633;
        val x_634 = resetData_0;
        val x_635 = x_634.asInstanceOf[scala.Boolean];
        bindingMut_23 = x_635;
        positionVar_79 = 44
      }
    else
      positionVar_79 = 47
  }));
  data_80.update(44, (() => {
    val x_636 = bindingMut_23;
    val x_637 = x_636.asInstanceOf[scala.Boolean];
    val x_638 = x_637.`unary_!`;
    if (x_638)
      positionVar_79 = 45
    else
      positionVar_79 = 46
  }));
  data_80.update(45, (() => positionVar_79 = 43));
  data_80.update(46, (() => {
    val x_639 = bindingMut_23;
    val x_640 = x_639.asInstanceOf[scala.Boolean];
    if (x_640)
      {
        val x_641 = bindingMut_24;
        val x_642 = x_641.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_643 = x_642.get;
        resetData_0 = x_643;
        val x_644 = resetData_0;
        val x_645 = x_644.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_22 = x_645;
        val x_646 = bindingMut_22;
        val x_647 = x_646.asInstanceOf[generated.meta.example.segregation.Person];
        val x_648 = bindingMut_32;
        val x_649 = x_648.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        x_649.append(x_647);
        resetData_0 = ();
        positionVar_79 = 45
      }
    else
      ()
  }));
  data_80.update(47, (() => {
    val x_650 = iterMut_27;
    val x_651 = x_650.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_652 = x_651.hasNext;
    val x_653 = x_652.`unary_!`;
    if (x_653)
      {
        val x_654 = bindingMut_32;
        val x_655 = x_654.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        val x_656 = x_655.toList;
        resetData_0 = x_656;
        x_5.remove(0);
        val x_657 = x_5.isEmpty;
        val x_658 = x_657.`unary_!`;
        if (x_658)
          {
            val x_659 = x_5(0);
            val x_660 = x_659.asInstanceOf[scala.Int];
            methodArgsMut_21 = x_660
          }
        else
          ();
        val x_661 = resetData_0;
        val x_662 = x_661.asInstanceOf[scala.Any];
        bindingMut_75 = x_662;
        val x_663 = bindingMut_75;
        val x_664 = x_663.asInstanceOf[scala.Any];
        val x_665 = listValMut_76;
        val x_666 = x_665.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_666.reply(this, x_664);
        resetData_0 = ();
        positionVar_79 = 12
      }
    else
      ()
  }));
  data_80.update(48, (() => {
    val x_667 = listValMut_76;
    val x_668 = x_667.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_669 = x_668.methodId;
    val x_670 = x_669.==(4);
    if (x_670)
      {
        val x_671 = listValMut_76;
        val x_672 = x_671.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_673 = x_672.argss;
        val x_674 = x_673(0);
        val x_675 = x_674(0);
        x_4.prepend(x_675);
        val x_676 = listValMut_76;
        val x_677 = x_676.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_678 = x_677.argss;
        val x_679 = x_678(0);
        val x_680 = x_679(0);
        val x_681 = x_680.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_15 = x_681;
        val x_682 = this.emptyLoc;
        resetData_0 = x_682;
        val x_683 = resetData_0;
        val x_684 = x_683.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_20 = x_684;
        val x_685 = bindingMut_20;
        val x_686 = x_685.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_687 = x_686.length;
        resetData_0 = x_687;
        val x_688 = resetData_0;
        val x_689 = x_688.asInstanceOf[scala.Int];
        bindingMut_19 = x_689;
        val x_690 = bindingMut_19;
        val x_691 = x_690.asInstanceOf[scala.Int];
        val x_692 = x_691.==(0);
        resetData_0 = x_692;
        val x_693 = resetData_0;
        val x_694 = x_693.asInstanceOf[scala.Boolean];
        bindingMut_18 = x_694;
        positionVar_79 = 49
      }
    else
      ()
  }));
  data_80.update(49, (() => {
    val x_695 = bindingMut_18;
    val x_696 = x_695.asInstanceOf[scala.Boolean];
    val x_697 = x_696.`unary_!`;
    if (x_697)
      positionVar_79 = 35
    else
      positionVar_79 = 50;
    val x_698 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_699 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_698, 36);
    val x_700 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_699);
    resetData_1.prepend(x_700)
  }));
  data_80.update(50, (() => {
    val x_701 = bindingMut_18;
    val x_702 = x_701.asInstanceOf[scala.Boolean];
    if (x_702)
      {
        resetData_0 = -1;
        positionVar_79 = 37
      }
    else
      ()
  }));
  data_80.update(51, (() => {
    val x_703 = listValMut_76;
    val x_704 = x_703.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_705 = x_704.methodId;
    val x_706 = x_705.==(3);
    if (x_706)
      positionVar_79 = 35
    else
      ();
    val x_707 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_708 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_707, 40);
    val x_709 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_708);
    resetData_1.prepend(x_709)
  }));
  data_80.update(52, (() => {
    val x_710 = iterMut_77;
    val x_711 = x_710.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_712 = x_711.hasNext;
    val x_713 = x_712.`unary_!`;
    if (x_713)
      positionVar_79 = 18
    else
      ();
    val x_714 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_715 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_714, 20);
    val x_716 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_715);
    resetData_1.prepend(x_716)
  }));
  data_80.update(53, (() => {
    val x_717 = true.`unary_!`;
    if (x_717)
      {
        val x_718 = resetData_1.remove(0);
        val x_722 = x_718.find(((x_719: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_720 = x_719._1;
          val x_721 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_720.==(x_721)
        }));
        val x_723 = x_722.get;
        val x_724 = x_723._2;
        positionVar_79 = x_724
      }
    else
      ()
  }));
  data_80
}).apply();
  
  override def run_until(until_726: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_727 = currentTurn;
      val x_728 = x_727.<=(until_726);
      x_728.&&({
        val x_729 = positionVar_79;
        val x_730 = commands_725.length;
        x_729.<(x_730)
      })
    }) 
      {
        val x_731 = positionVar_79;
        val x_732 = commands_725.apply(x_731);
        x_732.apply()
      }
    ;
    this
  }
}
