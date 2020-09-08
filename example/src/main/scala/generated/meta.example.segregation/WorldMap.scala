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
  private var bindingMut_60: org.slf4j.Logger = null;
  private var bindingMut_61: scala.Boolean = false;
  private var bindingMut_62: scala.Int = 0;
  private var bindingMut_63: scala.collection.mutable.Map[scala.Int, scala.Double] = null;
  private var bindingMut_64: scala.Tuple3[scala.Int, scala.Int, scala.Long] = null;
  private var bindingMut_65: scala.Long = 0L;
  private var bindingMut_66: generated.meta.example.segregation.Person = null;
  private var bindingMut_67: scala.Int = 0;
  private var bindingMut_68: generated.meta.example.segregation.Person = null;
  private var bindingMut_69: scala.Int = 0;
  private var listValMut_70: scala.Tuple2[scala.Int, generated.meta.example.segregation.Person] = null;
  private var iterMut_71: scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_72: scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_73: scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]] = null;
  private var bindingMut_74: scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person] = null;
  private var bindingMut_75: scala.Int = 0;
  private var bindingMut_76: scala.Any = null;
  private var listValMut_77: meta.deep.runtime.RequestMessage = null;
  private var iterMut_78: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_80: scala.Int = 0;
  
  val commands_731 = (() => {
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
        bindingMut_74 = x_150;
        val x_151 = bindingMut_74;
        val x_152 = x_151.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_153 = x_152.toList;
        resetData_0 = x_153;
        val x_154 = resetData_0;
        val x_155 = x_154.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_73 = x_155;
        val x_156 = bindingMut_73;
        val x_157 = x_156.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_159 = x_157.sortBy[scala.Int](((pair_158: scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]) => pair_158._1))(scala.math.Ordering.Int);
        resetData_0 = x_159;
        val x_160 = resetData_0;
        val x_161 = x_160.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        bindingMut_72 = x_161;
        val x_162 = bindingMut_72;
        val x_163 = x_162.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_164 = x_163.iterator;
        iterMut_71 = x_164;
        positionVar_80 = 15
      }
    else
      ()
  }));
  data_81.update(15, (() => {
    val x_165 = iterMut_71;
    val x_166 = x_165.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
    val x_167 = x_166.hasNext;
    if (x_167)
      {
        val x_168 = iterMut_71;
        val x_169 = x_168.asInstanceOf[scala.collection.Iterator[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]]];
        val x_170 = x_169.next();
        listValMut_70 = x_170;
        val x_171 = listValMut_70;
        val x_172 = x_171.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_173 = x_172._1;
        resetData_0 = x_173;
        val x_174 = resetData_0;
        val x_175 = x_174.asInstanceOf[scala.Int];
        bindingMut_69 = x_175;
        val x_176 = listValMut_70;
        val x_177 = x_176.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_178 = x_177._2;
        resetData_0 = x_178;
        val x_179 = resetData_0;
        val x_180 = x_179.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_68 = x_180;
        val x_181 = bindingMut_68;
        val x_182 = x_181.asInstanceOf[generated.meta.example.segregation.Person];
        val x_183 = x_182.view;
        resetData_0 = x_183;
        val x_184 = resetData_0;
        val x_185 = x_184.asInstanceOf[scala.Int];
        bindingMut_67 = x_185;
        val x_186 = listValMut_70;
        val x_187 = x_186.asInstanceOf[scala.Tuple2[scala.Int, generated.meta.example.segregation.Person]];
        val x_188 = x_187._2;
        resetData_0 = x_188;
        val x_189 = resetData_0;
        val x_190 = x_189.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_66 = x_190;
        val x_191 = bindingMut_66;
        val x_192 = x_191.asInstanceOf[generated.meta.example.segregation.Person];
        val x_193 = x_192.id;
        resetData_0 = x_193;
        val x_194 = resetData_0;
        val x_195 = x_194.asInstanceOf[scala.Long];
        bindingMut_65 = x_195;
        val x_196 = bindingMut_65;
        val x_197 = x_196.asInstanceOf[scala.Long];
        val x_198 = bindingMut_67;
        val x_199 = x_198.asInstanceOf[scala.Int];
        val x_200 = bindingMut_69;
        val x_201 = x_200.asInstanceOf[scala.Int];
        val x_202 = scala.Tuple3.apply[scala.Int, scala.Int, scala.Long](x_201, x_199, x_197);
        resetData_0 = x_202;
        val x_203 = resetData_0;
        val x_204 = x_203.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        bindingMut_64 = x_204;
        val x_205 = bindingMut_64;
        val x_206 = x_205.asInstanceOf[scala.Tuple3[scala.Int, scala.Int, scala.Long]];
        scala.Predef.println(x_206);
        resetData_0 = ();
        positionVar_80 = 15
      }
    else
      positionVar_80 = 16
  }));
  data_81.update(16, (() => {
    val x_207 = iterMut_71;
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
    bindingMut_63 = x_226;
    val x_227 = bindingMut_63;
    val x_228 = x_227.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_229 = x_228.size;
    resetData_0 = x_229;
    val x_230 = resetData_0;
    val x_231 = x_230.asInstanceOf[scala.Int];
    bindingMut_62 = x_231;
    val x_232 = bindingMut_62;
    val x_233 = x_232.asInstanceOf[scala.Int];
    val x_234 = x_233.>(0);
    resetData_0 = x_234;
    val x_235 = resetData_0;
    val x_236 = x_235.asInstanceOf[scala.Boolean];
    bindingMut_61 = x_236;
    positionVar_80 = 19
  }));
  data_81.update(19, (() => {
    val x_237 = bindingMut_61;
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
    resetData_0 = 0;
    val x_247 = resetData_0;
    val x_248 = x_247.asInstanceOf[scala.Int];
    bindingMut_75 = x_248;
    positionVar_80 = 21
  }));
  data_81.update(21, (() => {
    val x_249 = bindingMut_75;
    val x_250 = x_249.asInstanceOf[scala.Int];
    val x_251 = (1).-(x_250);
    meta.deep.runtime.Actor.waitTurnList.append(x_251);
    resetData_0 = ();
    val x_252 = meta.deep.runtime.Actor.minTurn();
    val x_253 = bindingMut_75;
    val x_254 = x_253.asInstanceOf[scala.Int];
    val x_255 = x_254.+(x_252);
    resetData_0 = x_255;
    val x_256 = resetData_0;
    val x_257 = x_256.asInstanceOf[scala.Int];
    bindingMut_75 = x_257;
    positionVar_80 = 22;
    val x_258 = currentTurn;
    val x_259 = x_258.+(1);
    currentTurn = x_259
  }));
  data_81.update(22, (() => {
    val x_260 = bindingMut_75;
    val x_261 = x_260.asInstanceOf[scala.Int];
    val x_262 = x_261.<(1);
    if (x_262)
      positionVar_80 = 21
    else
      positionVar_80 = 23
  }));
  data_81.update(23, (() => {
    val x_263 = bindingMut_75;
    val x_264 = x_263.asInstanceOf[scala.Int];
    val x_265 = x_264.<(1);
    val x_266 = x_265.`unary_!`;
    if (x_266)
      positionVar_80 = 24
    else
      ()
  }));
  data_81.update(24, (() => if (true)
    positionVar_80 = 2
  else
    positionVar_80 = 25));
  data_81.update(25, (() => {
    val x_267 = true.`unary_!`;
    if (x_267)
      {
        val x_268 = resetData_1.remove(0);
        val x_272 = x_268.find(((x_269: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_270 = x_269._1;
          val x_271 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_270.==(x_271)
        }));
        val x_273 = x_272.get;
        val x_274 = x_273._2;
        positionVar_80 = x_274
      }
    else
      ()
  }));
  data_81.update(26, (() => {
    val x_275 = resetData_0;
    val x_276 = x_275.asInstanceOf[scala.Any];
    bindingMut_76 = x_276;
    val x_277 = bindingMut_76;
    val x_278 = x_277.asInstanceOf[scala.Any];
    val x_279 = listValMut_77;
    val x_280 = x_279.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_280.reply(this, x_278);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(27, (() => positionVar_80 = 28));
  data_81.update(28, (() => {
    positionVar_80 = 29;
    val x_281 = currentTurn;
    val x_282 = x_281.+(1);
    currentTurn = x_282
  }));
  data_81.update(29, (() => positionVar_80 = 28));
  data_81.update(30, (() => {
    val x_283 = resetData_0;
    val x_284 = x_283.asInstanceOf[scala.Any];
    bindingMut_76 = x_284;
    val x_285 = bindingMut_76;
    val x_286 = x_285.asInstanceOf[scala.Any];
    val x_287 = listValMut_77;
    val x_288 = x_287.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_288.reply(this, x_286);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(31, (() => {
    val x_289 = bindingMut_61;
    val x_290 = x_289.asInstanceOf[scala.Boolean];
    if (x_290)
      {
        val x_291 = this.logger;
        resetData_0 = x_291;
        val x_292 = resetData_0;
        val x_293 = x_292.asInstanceOf[org.slf4j.Logger];
        bindingMut_60 = x_293;
        val x_294 = this.similarities;
        resetData_0 = x_294;
        val x_295 = resetData_0;
        val x_296 = x_295.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_59 = x_296;
        val x_297 = bindingMut_59;
        val x_298 = x_297.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_299 = x_298.values;
        resetData_0 = x_299;
        val x_300 = resetData_0;
        val x_301 = x_300.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_58 = x_301;
        val x_302 = bindingMut_58;
        val x_303 = x_302.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_304 = x_303.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_304;
        val x_305 = resetData_0;
        val x_306 = x_305.asInstanceOf[scala.Double];
        bindingMut_57 = x_306;
        val x_307 = this.similarities;
        resetData_0 = x_307;
        val x_308 = resetData_0;
        val x_309 = x_308.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_56 = x_309;
        val x_310 = bindingMut_56;
        val x_311 = x_310.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_312 = x_311.size;
        resetData_0 = x_312;
        val x_313 = resetData_0;
        val x_314 = x_313.asInstanceOf[scala.Int];
        bindingMut_55 = x_314;
        val x_315 = bindingMut_55;
        val x_316 = x_315.asInstanceOf[scala.Int];
        val x_317 = bindingMut_57;
        val x_318 = x_317.asInstanceOf[scala.Double];
        val x_319 = x_318./(x_316);
        resetData_0 = x_319;
        val x_320 = resetData_0;
        val x_321 = x_320.asInstanceOf[scala.Double];
        bindingMut_54 = x_321;
        val x_322 = bindingMut_54;
        val x_323 = x_322.asInstanceOf[scala.Double];
        val x_324 = bindingMut_60;
        val x_325 = x_324.asInstanceOf[org.slf4j.Logger];
        x_325.info("{}", x_323);
        resetData_0 = ();
        val x_326 = this.similarities;
        resetData_0 = x_326;
        val x_327 = resetData_0;
        val x_328 = x_327.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_53 = x_328;
        val x_329 = bindingMut_53;
        val x_330 = x_329.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_331 = x_330.values;
        resetData_0 = x_331;
        val x_332 = resetData_0;
        val x_333 = x_332.asInstanceOf[scala.collection.Iterable[scala.Double]];
        bindingMut_52 = x_333;
        val x_334 = bindingMut_52;
        val x_335 = x_334.asInstanceOf[scala.collection.Iterable[scala.Double]];
        val x_336 = x_335.sum[scala.Double](scala.math.Numeric.DoubleIsFractional);
        resetData_0 = x_336;
        val x_337 = resetData_0;
        val x_338 = x_337.asInstanceOf[scala.Double];
        bindingMut_51 = x_338;
        val x_339 = this.similarities;
        resetData_0 = x_339;
        val x_340 = resetData_0;
        val x_341 = x_340.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_50 = x_341;
        val x_342 = bindingMut_50;
        val x_343 = x_342.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        val x_344 = x_343.size;
        resetData_0 = x_344;
        val x_345 = resetData_0;
        val x_346 = x_345.asInstanceOf[scala.Int];
        bindingMut_49 = x_346;
        val x_347 = bindingMut_49;
        val x_348 = x_347.asInstanceOf[scala.Int];
        val x_349 = bindingMut_51;
        val x_350 = x_349.asInstanceOf[scala.Double];
        val x_351 = x_350./(x_348);
        resetData_0 = x_351;
        val x_352 = resetData_1.remove(0);
        val x_356 = x_352.find(((x_353: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_354 = x_353._1;
          val x_355 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_354.==(x_355)
        }));
        val x_357 = x_356.get;
        val x_358 = x_357._2;
        positionVar_80 = x_358
      }
    else
      ()
  }));
  data_81.update(32, (() => {
    val x_359 = listValMut_77;
    val x_360 = x_359.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_361 = x_360.methodId;
    val x_362 = x_361.==(7);
    if (x_362)
      {
        val x_363 = listValMut_77;
        val x_364 = x_363.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_365 = x_364.argss;
        val x_366 = x_365(0);
        val x_367 = x_366(0);
        x_8.prepend(x_367);
        val x_368 = listValMut_77;
        val x_369 = x_368.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_370 = x_369.argss;
        val x_371 = x_370(0);
        val x_372 = x_371(1);
        x_7.prepend(x_372);
        val x_373 = listValMut_77;
        val x_374 = x_373.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_375 = x_374.argss;
        val x_376 = x_375(0);
        val x_377 = x_376(0);
        val x_378 = x_377.asInstanceOf[scala.Int];
        methodArgsMut_45 = x_378;
        val x_379 = listValMut_77;
        val x_380 = x_379.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_381 = x_380.argss;
        val x_382 = x_381(0);
        val x_383 = x_382(1);
        val x_384 = x_383.asInstanceOf[scala.Double];
        methodArgsMut_44 = x_384;
        val x_385 = this.totalReports;
        resetData_0 = x_385;
        val x_386 = resetData_0;
        val x_387 = x_386.asInstanceOf[scala.Int];
        bindingMut_48 = x_387;
        val x_388 = bindingMut_48;
        val x_389 = x_388.asInstanceOf[scala.Int];
        val x_390 = x_389.+(1);
        resetData_0 = x_390;
        val x_391 = resetData_0;
        val x_392 = x_391.asInstanceOf[scala.Int];
        bindingMut_47 = x_392;
        val x_393 = bindingMut_47;
        val x_394 = x_393.asInstanceOf[scala.Int];
        this.`totalReports_=`(x_394);
        resetData_0 = ();
        val x_395 = this.similarities;
        resetData_0 = x_395;
        val x_396 = resetData_0;
        val x_397 = x_396.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        bindingMut_46 = x_397;
        val x_398 = methodArgsMut_44;
        val x_399 = x_398.asInstanceOf[scala.Double];
        val x_400 = methodArgsMut_45;
        val x_401 = x_400.asInstanceOf[scala.Int];
        val x_402 = bindingMut_46;
        val x_403 = x_402.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
        x_403.update(x_401, x_399);
        resetData_0 = ();
        x_8.remove(0);
        val x_404 = x_8.isEmpty;
        val x_405 = x_404.`unary_!`;
        if (x_405)
          {
            val x_406 = x_8(0);
            val x_407 = x_406.asInstanceOf[scala.Int];
            methodArgsMut_45 = x_407
          }
        else
          ();
        x_7.remove(0);
        val x_408 = x_7.isEmpty;
        val x_409 = x_408.`unary_!`;
        if (x_409)
          {
            val x_410 = x_7(0);
            val x_411 = x_410.asInstanceOf[scala.Double];
            methodArgsMut_44 = x_411
          }
        else
          ();
        val x_412 = resetData_0;
        val x_413 = x_412.asInstanceOf[scala.Any];
        bindingMut_76 = x_413;
        val x_414 = bindingMut_76;
        val x_415 = x_414.asInstanceOf[scala.Any];
        val x_416 = listValMut_77;
        val x_417 = x_416.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_417.reply(this, x_415);
        resetData_0 = ();
        positionVar_80 = 12
      }
    else
      ()
  }));
  data_81.update(33, (() => {
    val x_418 = listValMut_77;
    val x_419 = x_418.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_420 = x_419.methodId;
    val x_421 = x_420.==(6);
    if (x_421)
      {
        val x_422 = listValMut_77;
        val x_423 = x_422.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_424 = x_423.argss;
        val x_425 = x_424(0);
        val x_426 = x_425(0);
        x_6.prepend(x_426);
        val x_427 = listValMut_77;
        val x_428 = x_427.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_429 = x_428.argss;
        val x_430 = x_429(0);
        val x_431 = x_430(0);
        val x_432 = x_431.asInstanceOf[scala.Int];
        methodArgsMut_33 = x_432;
        val x_433 = this.emptyLoc;
        resetData_0 = x_433;
        val x_434 = resetData_0;
        val x_435 = x_434.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_43 = x_435;
        val x_436 = bindingMut_43;
        val x_437 = x_436.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_438 = x_437.length;
        resetData_0 = x_438;
        val x_439 = resetData_0;
        val x_440 = x_439.asInstanceOf[scala.Int];
        bindingMut_42 = x_440;
        val x_441 = bindingMut_42;
        val x_442 = x_441.asInstanceOf[scala.Int];
        val x_443 = x_442.==(0);
        resetData_0 = x_443;
        val x_444 = resetData_0;
        val x_445 = x_444.asInstanceOf[scala.Boolean];
        bindingMut_41 = x_445;
        positionVar_80 = 34
      }
    else
      ()
  }));
  data_81.update(34, (() => {
    val x_446 = bindingMut_41;
    val x_447 = x_446.asInstanceOf[scala.Boolean];
    val x_448 = x_447.`unary_!`;
    if (x_448)
      positionVar_80 = 35
    else
      positionVar_80 = 41;
    val x_449 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_450 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_449, 38);
    val x_451 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_450);
    resetData_1.prepend(x_451)
  }));
  data_81.update(35, (() => {
    val x_452 = this.emptyLoc;
    resetData_0 = x_452;
    val x_453 = resetData_0;
    val x_454 = x_453.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_14 = x_454;
    val x_455 = this.emptyLoc;
    resetData_0 = x_455;
    val x_456 = resetData_0;
    val x_457 = x_456.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_13 = x_457;
    val x_458 = bindingMut_13;
    val x_459 = x_458.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_460 = x_459.length;
    resetData_0 = x_460;
    val x_461 = resetData_0;
    val x_462 = x_461.asInstanceOf[scala.Int];
    bindingMut_12 = x_462;
    val x_463 = bindingMut_12;
    val x_464 = x_463.asInstanceOf[scala.Int];
    val x_465 = scala.util.Random.nextInt(x_464);
    resetData_0 = x_465;
    val x_466 = resetData_0;
    val x_467 = x_466.asInstanceOf[scala.Int];
    bindingMut_11 = x_467;
    val x_468 = bindingMut_11;
    val x_469 = x_468.asInstanceOf[scala.Int];
    val x_470 = bindingMut_14;
    val x_471 = x_470.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_472 = x_471(x_469);
    resetData_0 = x_472;
    val x_473 = resetData_0;
    val x_474 = x_473.asInstanceOf[scala.Int];
    bindingMut_10 = x_474;
    val x_475 = this.emptyLoc;
    resetData_0 = x_475;
    val x_476 = resetData_0;
    val x_477 = x_476.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_9 = x_477;
    val x_478 = bindingMut_9;
    val x_479 = x_478.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    val x_480 = bindingMut_10;
    val x_481 = x_480.asInstanceOf[scala.Int];
    val x_482 = x_479.-=(x_481);
    resetData_0 = x_482;
    val x_483 = bindingMut_10;
    val x_484 = x_483.asInstanceOf[scala.Int];
    resetData_0 = x_484;
    val x_485 = resetData_1.remove(0);
    val x_489 = x_485.find(((x_486: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_487 = x_486._1;
      val x_488 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_487.==(x_488)
    }));
    val x_490 = x_489.get;
    val x_491 = x_490._2;
    positionVar_80 = x_491
  }));
  data_81.update(36, (() => {
    val x_492 = resetData_0;
    val x_493 = x_492.asInstanceOf[scala.Int];
    bindingMut_17 = x_493;
    val x_494 = this.world;
    resetData_0 = x_494;
    val x_495 = resetData_0;
    val x_496 = x_495.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_16 = x_496;
    val x_497 = methodArgsMut_15;
    val x_498 = x_497.asInstanceOf[generated.meta.example.segregation.Person];
    val x_499 = bindingMut_16;
    val x_500 = x_499.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_501 = bindingMut_17;
    val x_502 = x_501.asInstanceOf[scala.Int];
    x_500.update(x_502, x_498);
    resetData_0 = ();
    val x_503 = bindingMut_17;
    val x_504 = x_503.asInstanceOf[scala.Int];
    resetData_0 = x_504;
    positionVar_80 = 37
  }));
  data_81.update(37, (() => {
    x_4.remove(0);
    val x_505 = x_4.isEmpty;
    val x_506 = x_505.`unary_!`;
    if (x_506)
      {
        val x_507 = x_4(0);
        val x_508 = x_507.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_15 = x_508
      }
    else
      ();
    val x_509 = resetData_0;
    val x_510 = x_509.asInstanceOf[scala.Any];
    bindingMut_76 = x_510;
    val x_511 = bindingMut_76;
    val x_512 = x_511.asInstanceOf[scala.Any];
    val x_513 = listValMut_77;
    val x_514 = x_513.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_514.reply(this, x_512);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(38, (() => {
    val x_515 = resetData_0;
    val x_516 = x_515.asInstanceOf[scala.Int];
    bindingMut_40 = x_516;
    val x_517 = this.emptyLoc;
    resetData_0 = x_517;
    val x_518 = resetData_0;
    val x_519 = x_518.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    bindingMut_39 = x_519;
    val x_520 = methodArgsMut_33;
    val x_521 = x_520.asInstanceOf[scala.Int];
    val x_522 = bindingMut_39;
    val x_523 = x_522.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
    x_523.append(x_521);
    resetData_0 = ();
    val x_524 = this.world;
    resetData_0 = x_524;
    val x_525 = resetData_0;
    val x_526 = x_525.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_38 = x_526;
    val x_527 = this.world;
    resetData_0 = x_527;
    val x_528 = resetData_0;
    val x_529 = x_528.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    bindingMut_37 = x_529;
    val x_530 = methodArgsMut_33;
    val x_531 = x_530.asInstanceOf[scala.Int];
    val x_532 = bindingMut_37;
    val x_533 = x_532.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_534 = x_533.remove(x_531);
    resetData_0 = x_534;
    val x_535 = resetData_0;
    val x_536 = x_535.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    bindingMut_36 = x_536;
    val x_537 = bindingMut_36;
    val x_538 = x_537.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
    val x_539 = x_538.get;
    resetData_0 = x_539;
    val x_540 = resetData_0;
    val x_541 = x_540.asInstanceOf[generated.meta.example.segregation.Person];
    bindingMut_35 = x_541;
    val x_542 = bindingMut_35;
    val x_543 = x_542.asInstanceOf[generated.meta.example.segregation.Person];
    val x_544 = bindingMut_38;
    val x_545 = x_544.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
    val x_546 = bindingMut_40;
    val x_547 = x_546.asInstanceOf[scala.Int];
    x_545.update(x_547, x_543);
    resetData_0 = ();
    val x_548 = this.similarities;
    resetData_0 = x_548;
    val x_549 = resetData_0;
    val x_550 = x_549.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    bindingMut_34 = x_550;
    val x_551 = methodArgsMut_33;
    val x_552 = x_551.asInstanceOf[scala.Int];
    val x_553 = bindingMut_34;
    val x_554 = x_553.asInstanceOf[scala.collection.mutable.Map[scala.Int, scala.Double]];
    val x_555 = x_554.remove(x_552);
    resetData_0 = x_555;
    val x_556 = bindingMut_40;
    val x_557 = x_556.asInstanceOf[scala.Int];
    resetData_0 = x_557;
    positionVar_80 = 39
  }));
  data_81.update(39, (() => {
    x_6.remove(0);
    val x_558 = x_6.isEmpty;
    val x_559 = x_558.`unary_!`;
    if (x_559)
      {
        val x_560 = x_6(0);
        val x_561 = x_560.asInstanceOf[scala.Int];
        methodArgsMut_33 = x_561
      }
    else
      ();
    val x_562 = resetData_0;
    val x_563 = x_562.asInstanceOf[scala.Any];
    bindingMut_76 = x_563;
    val x_564 = bindingMut_76;
    val x_565 = x_564.asInstanceOf[scala.Any];
    val x_566 = listValMut_77;
    val x_567 = x_566.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_567.reply(this, x_565);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(40, (() => {
    val x_568 = resetData_0;
    val x_569 = x_568.asInstanceOf[scala.Any];
    bindingMut_76 = x_569;
    val x_570 = bindingMut_76;
    val x_571 = x_570.asInstanceOf[scala.Any];
    val x_572 = listValMut_77;
    val x_573 = x_572.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_573.reply(this, x_571);
    resetData_0 = ();
    positionVar_80 = 12
  }));
  data_81.update(41, (() => {
    val x_574 = bindingMut_41;
    val x_575 = x_574.asInstanceOf[scala.Boolean];
    if (x_575)
      {
        val x_576 = methodArgsMut_33;
        val x_577 = x_576.asInstanceOf[scala.Int];
        resetData_0 = x_577;
        positionVar_80 = 39
      }
    else
      ()
  }));
  data_81.update(42, (() => {
    val x_578 = listValMut_77;
    val x_579 = x_578.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_580 = x_579.methodId;
    val x_581 = x_580.==(5);
    if (x_581)
      {
        val x_582 = listValMut_77;
        val x_583 = x_582.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_584 = x_583.argss;
        val x_585 = x_584(0);
        val x_586 = x_585(0);
        x_5.prepend(x_586);
        val x_587 = listValMut_77;
        val x_588 = x_587.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_589 = x_588.argss;
        val x_590 = x_589(0);
        val x_591 = x_590(0);
        val x_592 = x_591.asInstanceOf[scala.Int];
        methodArgsMut_21 = x_592;
        val x_593 = scala.collection.mutable.ArrayBuffer.apply[generated.meta.example.segregation.Person]();
        resetData_0 = x_593;
        val x_594 = resetData_0;
        val x_595 = x_594.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        bindingMut_32 = x_595;
        val x_596 = this.radius;
        resetData_0 = x_596;
        val x_597 = resetData_0;
        val x_598 = x_597.asInstanceOf[scala.Int];
        bindingMut_31 = x_598;
        val x_599 = methodArgsMut_21;
        val x_600 = x_599.asInstanceOf[scala.Int];
        val x_601 = bindingMut_31;
        val x_602 = x_601.asInstanceOf[scala.Int];
        val x_603 = this.neighborCells(x_600, x_602);
        resetData_0 = x_603;
        val x_604 = resetData_0;
        val x_605 = x_604.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_30 = x_605;
        val x_606 = methodArgsMut_21;
        val x_607 = x_606.asInstanceOf[scala.Int];
        val x_608 = bindingMut_30;
        val x_609 = x_608.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_610 = x_609.-(x_607);
        resetData_0 = x_610;
        val x_611 = resetData_0;
        val x_612 = x_611.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        bindingMut_29 = x_612;
        val x_613 = bindingMut_29;
        val x_614 = x_613.asInstanceOf[scala.collection.immutable.Set[scala.Int]];
        val x_615 = x_614.toList;
        resetData_0 = x_615;
        val x_616 = resetData_0;
        val x_617 = x_616.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        bindingMut_28 = x_617;
        val x_618 = bindingMut_28;
        val x_619 = x_618.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_620 = x_619.iterator;
        iterMut_27 = x_620;
        positionVar_80 = 43
      }
    else
      ()
  }));
  data_81.update(43, (() => {
    val x_621 = iterMut_27;
    val x_622 = x_621.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_623 = x_622.hasNext;
    if (x_623)
      {
        val x_624 = iterMut_27;
        val x_625 = x_624.asInstanceOf[scala.collection.Iterator[scala.Int]];
        val x_626 = x_625.next();
        listValMut_26 = x_626;
        val x_627 = this.world;
        resetData_0 = x_627;
        val x_628 = resetData_0;
        val x_629 = x_628.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        bindingMut_25 = x_629;
        val x_630 = bindingMut_25;
        val x_631 = x_630.asInstanceOf[scala.collection.mutable.Map[scala.Int, generated.meta.example.segregation.Person]];
        val x_632 = listValMut_26;
        val x_633 = x_632.asInstanceOf[scala.Int];
        val x_634 = x_631.get(x_633);
        resetData_0 = x_634;
        val x_635 = resetData_0;
        val x_636 = x_635.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        bindingMut_24 = x_636;
        val x_637 = bindingMut_24;
        val x_638 = x_637.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_639 = x_638.isDefined;
        resetData_0 = x_639;
        val x_640 = resetData_0;
        val x_641 = x_640.asInstanceOf[scala.Boolean];
        bindingMut_23 = x_641;
        positionVar_80 = 44
      }
    else
      positionVar_80 = 47
  }));
  data_81.update(44, (() => {
    val x_642 = bindingMut_23;
    val x_643 = x_642.asInstanceOf[scala.Boolean];
    val x_644 = x_643.`unary_!`;
    if (x_644)
      positionVar_80 = 45
    else
      positionVar_80 = 46
  }));
  data_81.update(45, (() => positionVar_80 = 43));
  data_81.update(46, (() => {
    val x_645 = bindingMut_23;
    val x_646 = x_645.asInstanceOf[scala.Boolean];
    if (x_646)
      {
        val x_647 = bindingMut_24;
        val x_648 = x_647.asInstanceOf[scala.Option[generated.meta.example.segregation.Person]];
        val x_649 = x_648.get;
        resetData_0 = x_649;
        val x_650 = resetData_0;
        val x_651 = x_650.asInstanceOf[generated.meta.example.segregation.Person];
        bindingMut_22 = x_651;
        val x_652 = bindingMut_22;
        val x_653 = x_652.asInstanceOf[generated.meta.example.segregation.Person];
        val x_654 = bindingMut_32;
        val x_655 = x_654.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        x_655.append(x_653);
        resetData_0 = ();
        positionVar_80 = 45
      }
    else
      ()
  }));
  data_81.update(47, (() => {
    val x_656 = iterMut_27;
    val x_657 = x_656.asInstanceOf[scala.collection.Iterator[scala.Int]];
    val x_658 = x_657.hasNext;
    val x_659 = x_658.`unary_!`;
    if (x_659)
      {
        val x_660 = bindingMut_32;
        val x_661 = x_660.asInstanceOf[scala.collection.mutable.ArrayBuffer[generated.meta.example.segregation.Person]];
        val x_662 = x_661.toList;
        resetData_0 = x_662;
        x_5.remove(0);
        val x_663 = x_5.isEmpty;
        val x_664 = x_663.`unary_!`;
        if (x_664)
          {
            val x_665 = x_5(0);
            val x_666 = x_665.asInstanceOf[scala.Int];
            methodArgsMut_21 = x_666
          }
        else
          ();
        val x_667 = resetData_0;
        val x_668 = x_667.asInstanceOf[scala.Any];
        bindingMut_76 = x_668;
        val x_669 = bindingMut_76;
        val x_670 = x_669.asInstanceOf[scala.Any];
        val x_671 = listValMut_77;
        val x_672 = x_671.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_672.reply(this, x_670);
        resetData_0 = ();
        positionVar_80 = 12
      }
    else
      ()
  }));
  data_81.update(48, (() => {
    val x_673 = listValMut_77;
    val x_674 = x_673.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_675 = x_674.methodId;
    val x_676 = x_675.==(4);
    if (x_676)
      {
        val x_677 = listValMut_77;
        val x_678 = x_677.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_679 = x_678.argss;
        val x_680 = x_679(0);
        val x_681 = x_680(0);
        x_4.prepend(x_681);
        val x_682 = listValMut_77;
        val x_683 = x_682.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_684 = x_683.argss;
        val x_685 = x_684(0);
        val x_686 = x_685(0);
        val x_687 = x_686.asInstanceOf[generated.meta.example.segregation.Person];
        methodArgsMut_15 = x_687;
        val x_688 = this.emptyLoc;
        resetData_0 = x_688;
        val x_689 = resetData_0;
        val x_690 = x_689.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        bindingMut_20 = x_690;
        val x_691 = bindingMut_20;
        val x_692 = x_691.asInstanceOf[scala.collection.mutable.ArrayBuffer[scala.Int]];
        val x_693 = x_692.length;
        resetData_0 = x_693;
        val x_694 = resetData_0;
        val x_695 = x_694.asInstanceOf[scala.Int];
        bindingMut_19 = x_695;
        val x_696 = bindingMut_19;
        val x_697 = x_696.asInstanceOf[scala.Int];
        val x_698 = x_697.==(0);
        resetData_0 = x_698;
        val x_699 = resetData_0;
        val x_700 = x_699.asInstanceOf[scala.Boolean];
        bindingMut_18 = x_700;
        positionVar_80 = 49
      }
    else
      ()
  }));
  data_81.update(49, (() => {
    val x_701 = bindingMut_18;
    val x_702 = x_701.asInstanceOf[scala.Boolean];
    val x_703 = x_702.`unary_!`;
    if (x_703)
      positionVar_80 = 35
    else
      positionVar_80 = 50;
    val x_704 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_705 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_704, 36);
    val x_706 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_705);
    resetData_1.prepend(x_706)
  }));
  data_81.update(50, (() => {
    val x_707 = bindingMut_18;
    val x_708 = x_707.asInstanceOf[scala.Boolean];
    if (x_708)
      {
        resetData_0 = -1;
        positionVar_80 = 37
      }
    else
      ()
  }));
  data_81.update(51, (() => {
    val x_709 = listValMut_77;
    val x_710 = x_709.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_711 = x_710.methodId;
    val x_712 = x_711.==(3);
    if (x_712)
      positionVar_80 = 35
    else
      ();
    val x_713 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_714 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_713, 40);
    val x_715 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_714);
    resetData_1.prepend(x_715)
  }));
  data_81.update(52, (() => {
    val x_716 = iterMut_78;
    val x_717 = x_716.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_718 = x_717.hasNext;
    val x_719 = x_718.`unary_!`;
    if (x_719)
      positionVar_80 = 18
    else
      ();
    val x_720 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_721 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_720, 20);
    val x_722 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_721);
    resetData_1.prepend(x_722)
  }));
  data_81.update(53, (() => {
    val x_723 = true.`unary_!`;
    if (x_723)
      {
        val x_724 = resetData_1.remove(0);
        val x_728 = x_724.find(((x_725: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_726 = x_725._1;
          val x_727 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_726.==(x_727)
        }));
        val x_729 = x_728.get;
        val x_730 = x_729._2;
        positionVar_80 = x_730
      }
    else
      ()
  }));
  data_81
}).apply();
  
  override def run_until(until_732: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_733 = currentTurn;
      val x_734 = x_733.<=(until_732);
      x_734.&&({
        val x_735 = positionVar_80;
        val x_736 = commands_731.length;
        x_735.<(x_736)
      })
    }) 
      {
        val x_737 = positionVar_80;
        val x_738 = commands_731.apply(x_737);
        x_738.apply()
      }
    ;
    this
  }
}
