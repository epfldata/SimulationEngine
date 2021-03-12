package generated.example.distributedGraph.shortestPath.BellmanFord

class Node (val root: Boolean, val allPids: List[Long], val service: generated.example.distributedGraph.shortestPath.BellmanFord.DiscoverNeighborWithWeightService) extends meta.runtime.Actor with example.distributedGraph.TreeNode {

  var neighbors: List[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = scala.collection.immutable.Nil;
  var channels: List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel] = scala.collection.immutable.Nil;
  var lengths: scala.collection.mutable.Map[Long,Int] = scala.collection.mutable.Map.apply[scala.Long, scala.Int]();
  var lg: scala.collection.mutable.Map[Long,Int] = scala.collection.mutable.Map.apply[scala.Long, scala.Int]();
  var routing_to: scala.collection.mutable.Map[Long,generated.example.distributedGraph.shortestPath.BellmanFord.Node] = scala.collection.mutable.Map.apply[scala.Long, generated.example.distributedGraph.shortestPath.BellmanFord.Node]();
  var updated: Boolean = false;
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var bindingMut_4: scala.Boolean = false;
  private var bindingMut_5: scala.Long = 0L;
  private var bindingMut_6: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_7: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var listValMut_8: scala.Long = 0L;
  @transient private var iterMut_9: scala.collection.Iterator[scala.Long] = null;
  private var bindingMut_10: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_11: scala.Int = 0;
  private var bindingMut_12: scala.Long = 0L;
  private var bindingMut_13: example.distributedGraph.TreeNode = null;
  private var bindingMut_14: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_15: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_16: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_17: generated.example.distributedGraph.shortestPath.BellmanFord.Node = null;
  private var bindingMut_18: example.distributedGraph.TreeNode = null;
  private var listValMut_19: scala.Tuple2[example.distributedGraph.TreeNode, scala.Int] = null;
  @transient private var iterMut_20: scala.collection.Iterator[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  private var bindingMut_21: scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  private var bindingMut_22: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel] = null;
  private var bindingMut_23: scala.Long = 0L;
  private var bindingMut_24: generated.example.distributedGraph.shortestPath.BellmanFord.DiscoverNeighborWithWeightService = null;
  private var listValMut_25: generated.example.distributedGraph.shortestPath.BellmanFord.Node = null;
  @transient private var iterMut_26: scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_27: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_28: scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]] = null;
  private var bindingMut_29: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var methodArgsMut_30: scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]] = null;
  private var listValMut_31: generated.example.distributedGraph.shortestPath.BellmanFord.Node = null;
  @transient private var iterMut_32: scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_33: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_34: scala.Boolean = false;
  private var bindingMut_35: scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]] = null;
  private var bindingMut_36: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_37: scala.collection.mutable.Map[scala.Long, generated.example.distributedGraph.shortestPath.BellmanFord.Node] = null;
  private var bindingMut_38: scala.Int = 0;
  private var bindingMut_39: scala.Int = 0;
  private var bindingMut_40: scala.Int = 0;
  private var bindingMut_41: scala.Long = 0L;
  private var bindingMut_42: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_43: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_44: scala.Boolean = false;
  private var bindingMut_45: scala.Int = 0;
  private var bindingMut_46: scala.Int = 0;
  private var bindingMut_47: scala.Int = 0;
  private var bindingMut_48: scala.Long = 0L;
  private var bindingMut_49: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_50: scala.Int = 0;
  private var bindingMut_51: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_52: scala.Boolean = false;
  private var bindingMut_53: scala.Int = 0;
  private var listValMut_54: scala.Long = 0L;
  @transient private var iterMut_55: scala.collection.Iterator[scala.Long] = null;
  private var bindingMut_56: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_57: scala.collection.immutable.List[scala.Long] = null;
  private var bindingMut_58: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_59: generated.example.distributedGraph.shortestPath.BellmanFord.Node = null;
  private var bindingMut_60: java.lang.String = null;
  private var bindingMut_61: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_62: scala.Long = 0L;
  private var bindingMut_63: scala.StringContext = null;
  private var bindingMut_64: scala.Double = 0.0;
  private var bindingMut_65: scala.Any = null;
  private var listValMut_66: meta.runtime.RequestMessage = null;
  @transient private var iterMut_67: scala.collection.Iterator[meta.runtime.RequestMessage] = null;
  private var bindingMut_68: java.lang.String = null;
  private var bindingMut_69: scala.collection.mutable.Map[scala.Long, scala.Int] = null;
  private var bindingMut_70: scala.StringContext = null;

  private var positionVar_72: scala.Int = 0;
  
  val commands_638 = (() => {
  val data_73 = new scala.Array[scala.Function0[scala.Unit]](52);
  data_73.update(0, (() => {
    positionVar_72 = 1;
    val x_74 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_75 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_74, 48);
    val x_76 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_75);
    resetData_1.prepend(x_76)
  }));
  data_73.update(1, (() => {
    val x_77 = this.service;
    resetData_0 = x_77;
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.DiscoverNeighborWithWeightService];
    bindingMut_24 = x_79;
    val x_80 = this.id;
    resetData_0 = x_80;
    val x_81 = resetData_0;
    val x_82 = x_81.asInstanceOf[scala.Long];
    bindingMut_23 = x_82;
    val x_83 = this.channels;
    resetData_0 = x_83;
    val x_84 = resetData_0;
    val x_85 = x_84.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
    bindingMut_22 = x_85;
    val x_86 = ((this): meta.runtime.Actor).id;
    val x_88 = {
      val x_87 = bindingMut_24;
      x_87.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.DiscoverNeighborWithWeightService]
    };
    val x_89 = x_88.id;
    val x_90 = bindingMut_22;
    val x_91 = x_90.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
    val x_92 = scala.collection.immutable.Nil.::[scala.Any](x_91);
    val x_93 = bindingMut_23;
    val x_94 = x_93.asInstanceOf[scala.Long];
    val x_95 = x_92.::[scala.Any](x_94);
    val x_96 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_95);
    val x_97 = meta.runtime.RequestMessage.apply(x_86, x_89, 9, x_96);
    ((this): meta.runtime.Actor).sendMessage(x_97);
    val x_98 = x_97.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_98, ((response_99: meta.runtime.Message) => {
      val x_100 = response_99.asInstanceOf[meta.runtime.ResponseMessage];
      resetData_2 = x_100
    }));
    resetData_0 = null;
    positionVar_72 = 2
  }));
  data_73.update(2, (() => {
    positionVar_72 = 3;
    val x_101 = currentTurn;
    val x_102 = x_101.+(1);
    currentTurn = x_102
  }));
  data_73.update(3, (() => {
    val x_103 = resetData_2;
    val x_104 = x_103.==(null);
    if (x_104)
      positionVar_72 = 2
    else
      positionVar_72 = 4
  }));
  data_73.update(4, (() => {
    val x_105 = resetData_2;
    val x_106 = x_105.!=(null);
    if (x_106)
      {
        val x_107 = resetData_2;
        val x_108 = x_107.arg;
        resetData_0 = x_108;
        resetData_2 = null;
        val x_109 = resetData_0;
        val x_110 = x_109.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        bindingMut_21 = x_110;
        val x_111 = bindingMut_21;
        val x_112 = x_111.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_113 = x_112.iterator;
        iterMut_20 = x_113;
        positionVar_72 = 5
      }
    else
      ()
  }));
  data_73.update(5, (() => {
    val x_114 = iterMut_20;
    val x_115 = x_114.asInstanceOf[scala.collection.Iterator[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
    val x_116 = x_115.hasNext;
    if (x_116)
      {
        val x_117 = iterMut_20;
        val x_118 = x_117.asInstanceOf[scala.collection.Iterator[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_119 = x_118.next();
        listValMut_19 = x_119;
        val x_120 = listValMut_19;
        val x_121 = x_120.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_122 = x_121._1;
        resetData_0 = x_122;
        val x_123 = resetData_0;
        val x_124 = x_123.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_18 = x_124;
        val x_125 = bindingMut_18;
        val x_126 = x_125.asInstanceOf[example.distributedGraph.TreeNode];
        val x_127 = x_126.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        resetData_0 = x_127;
        val x_128 = resetData_0;
        val x_129 = x_128.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        bindingMut_17 = x_129;
        val x_130 = this.neighbors;
        resetData_0 = x_130;
        val x_131 = resetData_0;
        val x_132 = x_131.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        bindingMut_16 = x_132;
        val x_133 = bindingMut_16;
        val x_134 = x_133.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        val x_135 = bindingMut_17;
        val x_136 = x_135.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        val x_137 = x_134.::[generated.example.distributedGraph.shortestPath.BellmanFord.Node](x_136);
        resetData_0 = x_137;
        val x_138 = resetData_0;
        val x_139 = x_138.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        bindingMut_15 = x_139;
        val x_140 = bindingMut_15;
        val x_141 = x_140.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        this.`neighbors_=`(x_141);
        resetData_0 = ();
        val x_142 = this.lg;
        resetData_0 = x_142;
        val x_143 = resetData_0;
        val x_144 = x_143.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_14 = x_144;
        val x_145 = listValMut_19;
        val x_146 = x_145.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_147 = x_146._1;
        resetData_0 = x_147;
        val x_148 = resetData_0;
        val x_149 = x_148.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_13 = x_149;
        val x_150 = bindingMut_13;
        val x_151 = x_150.asInstanceOf[example.distributedGraph.TreeNode];
        val x_152 = x_151.id;
        resetData_0 = x_152;
        val x_153 = resetData_0;
        val x_154 = x_153.asInstanceOf[scala.Long];
        bindingMut_12 = x_154;
        val x_155 = listValMut_19;
        val x_156 = x_155.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_157 = x_156._2;
        resetData_0 = x_157;
        val x_158 = resetData_0;
        val x_159 = x_158.asInstanceOf[scala.Int];
        bindingMut_11 = x_159;
        val x_160 = bindingMut_11;
        val x_161 = x_160.asInstanceOf[scala.Int];
        val x_162 = bindingMut_12;
        val x_163 = x_162.asInstanceOf[scala.Long];
        val x_164 = bindingMut_14;
        val x_165 = x_164.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        x_165.update(x_163, x_161);
        resetData_0 = ();
        positionVar_72 = 5
      }
    else
      positionVar_72 = 6
  }));
  data_73.update(6, (() => {
    val x_166 = iterMut_20;
    val x_167 = x_166.asInstanceOf[scala.collection.Iterator[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
    val x_168 = x_167.hasNext;
    val x_169 = x_168.`unary_!`;
    if (x_169)
      {
        scala.Predef.println("Neighbors discovered! ");
        resetData_0 = ();
        val x_170 = this.allPids;
        resetData_0 = x_170;
        val x_171 = resetData_0;
        val x_172 = x_171.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_10 = x_172;
        val x_173 = bindingMut_10;
        val x_174 = x_173.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_175 = x_174.iterator;
        iterMut_9 = x_175;
        positionVar_72 = 7
      }
    else
      ()
  }));
  data_73.update(7, (() => {
    val x_176 = iterMut_9;
    val x_177 = x_176.asInstanceOf[scala.collection.Iterator[scala.Long]];
    val x_178 = x_177.hasNext;
    if (x_178)
      {
        val x_179 = iterMut_9;
        val x_180 = x_179.asInstanceOf[scala.collection.Iterator[scala.Long]];
        val x_181 = x_180.next();
        listValMut_8 = x_181;
        val x_182 = this.lengths;
        resetData_0 = x_182;
        val x_183 = resetData_0;
        val x_184 = x_183.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_7 = x_184;
        val x_185 = bindingMut_7;
        val x_186 = x_185.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_187 = listValMut_8;
        val x_188 = x_187.asInstanceOf[scala.Long];
        x_186.update(x_188, 2147483647);
        resetData_0 = ();
        positionVar_72 = 7
      }
    else
      positionVar_72 = 8
  }));
  data_73.update(8, (() => {
    val x_189 = iterMut_9;
    val x_190 = x_189.asInstanceOf[scala.collection.Iterator[scala.Long]];
    val x_191 = x_190.hasNext;
    val x_192 = x_191.`unary_!`;
    if (x_192)
      {
        val x_193 = this.lengths;
        resetData_0 = x_193;
        val x_194 = resetData_0;
        val x_195 = x_194.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_6 = x_195;
        val x_196 = this.id;
        resetData_0 = x_196;
        val x_197 = resetData_0;
        val x_198 = x_197.asInstanceOf[scala.Long];
        bindingMut_5 = x_198;
        val x_199 = bindingMut_5;
        val x_200 = x_199.asInstanceOf[scala.Long];
        val x_201 = bindingMut_6;
        val x_202 = x_201.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        x_202.update(x_200, 0);
        resetData_0 = ();
        val x_203 = this.root;
        resetData_0 = x_203;
        val x_204 = resetData_0;
        val x_205 = x_204.asInstanceOf[scala.Boolean];
        bindingMut_4 = x_205;
        positionVar_72 = 9
      }
    else
      ()
  }));
  data_73.update(9, (() => {
    val x_206 = bindingMut_4;
    val x_207 = x_206.asInstanceOf[scala.Boolean];
    val x_208 = x_207.`unary_!`;
    if (x_208)
      {
        val x_209 = resetData_1.remove(0);
        val x_213 = x_209.find(((x_210: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_211 = x_210._1;
          val x_212 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_211.==(x_212)
        }));
        val x_214 = x_213.get;
        val x_215 = x_214._2;
        positionVar_72 = x_215
      }
    else
      positionVar_72 = 51
  }));
  data_73.update(10, (() => {
    val x_216 = resetData_0;
    val x_217 = x_216.asInstanceOf[scala.Any];
    bindingMut_65 = x_217;
    val x_218 = bindingMut_65;
    val x_219 = x_218.asInstanceOf[scala.Any];
    val x_220 = listValMut_66;
    val x_221 = x_220.asInstanceOf[meta.runtime.RequestMessage];
    x_221.reply(this, x_219);
    resetData_0 = ();
    positionVar_72 = 11
  }));
  data_73.update(11, (() => positionVar_72 = 12));
  data_73.update(12, (() => {
    val x_222 = iterMut_67;
    val x_223 = x_222.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_224 = x_223.hasNext;
    if (x_224)
      {
        val x_225 = iterMut_67;
        val x_226 = x_225.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_227 = x_226.next();
        listValMut_66 = x_227;
        positionVar_72 = 13
      }
    else
      positionVar_72 = 37
  }));
  data_73.update(13, (() => {
    val x_228 = listValMut_66;
    val x_229 = x_228.asInstanceOf[meta.runtime.RequestMessage];
    val x_230 = x_229.methodId;
    val x_231 = x_230.==(2);
    val x_232 = x_231.`unary_!`;
    if (x_232)
      positionVar_72 = 14
    else
      positionVar_72 = 23
  }));
  data_73.update(14, (() => {
    val x_233 = listValMut_66;
    val x_234 = x_233.asInstanceOf[meta.runtime.RequestMessage];
    val x_235 = x_234.methodId;
    val x_236 = x_235.==(1);
    val x_237 = x_236.`unary_!`;
    if (x_237)
      positionVar_72 = 15
    else
      positionVar_72 = 17
  }));
  data_73.update(15, (() => {
    val x_238 = listValMut_66;
    val x_239 = x_238.asInstanceOf[meta.runtime.RequestMessage];
    val x_240 = x_239.methodId;
    val x_241 = x_240.==(0);
    val x_242 = x_241.`unary_!`;
    if (x_242)
      {
        val x_243 = listValMut_66;
        val x_244 = x_243.asInstanceOf[meta.runtime.RequestMessage];
        val x_245 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_244);
        val x_246 = this.addReceiveMessages(x_245);
        resetData_0 = x_246;
        positionVar_72 = 11
      }
    else
      positionVar_72 = 16
  }));
  data_73.update(16, (() => {
    val x_247 = listValMut_66;
    val x_248 = x_247.asInstanceOf[meta.runtime.RequestMessage];
    val x_249 = x_248.methodId;
    val x_250 = x_249.==(0);
    if (x_250)
      positionVar_72 = 1
    else
      ();
    val x_251 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_252 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_251, 10);
    val x_253 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_252);
    resetData_1.prepend(x_253)
  }));
  data_73.update(17, (() => {
    val x_254 = listValMut_66;
    val x_255 = x_254.asInstanceOf[meta.runtime.RequestMessage];
    val x_256 = x_255.methodId;
    val x_257 = x_256.==(1);
    if (x_257)
      positionVar_72 = 18
    else
      ();
    val x_258 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_259 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_258, 20);
    val x_260 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_259);
    resetData_1.prepend(x_260)
  }));
  data_73.update(18, (() => {
    val x_261 = this.lengths;
    resetData_0 = x_261;
    val x_262 = resetData_0;
    val x_263 = x_262.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
    bindingMut_29 = x_263;
    val x_264 = bindingMut_29;
    val x_265 = x_264.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
    val x_266 = scala.Tuple2.apply[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]](this, x_265);
    resetData_0 = x_266;
    val x_267 = resetData_0;
    val x_268 = x_267.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
    bindingMut_28 = x_268;
    val x_269 = this.neighbors;
    resetData_0 = x_269;
    val x_270 = resetData_0;
    val x_271 = x_270.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    bindingMut_27 = x_271;
    val x_272 = bindingMut_27;
    val x_273 = x_272.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    val x_274 = x_273.iterator;
    iterMut_26 = x_274;
    positionVar_72 = 19
  }));
  data_73.update(19, (() => {
    val x_275 = iterMut_26;
    val x_276 = x_275.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    val x_277 = x_276.hasNext;
    val x_278 = x_277.`unary_!`;
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
        positionVar_72 = x_285
      }
    else
      positionVar_72 = 22
  }));
  data_73.update(20, (() => {
    val x_286 = resetData_0;
    val x_287 = x_286.asInstanceOf[scala.Any];
    bindingMut_65 = x_287;
    val x_288 = bindingMut_65;
    val x_289 = x_288.asInstanceOf[scala.Any];
    val x_290 = listValMut_66;
    val x_291 = x_290.asInstanceOf[meta.runtime.RequestMessage];
    x_291.reply(this, x_289);
    resetData_0 = ();
    positionVar_72 = 11
  }));
  data_73.update(21, (() => {
    val x_292 = resetData_1.remove(0);
    val x_296 = x_292.find(((x_293: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_294 = x_293._1;
      val x_295 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_294.==(x_295)
    }));
    val x_297 = x_296.get;
    val x_298 = x_297._2;
    positionVar_72 = x_298
  }));
  data_73.update(22, (() => {
    val x_299 = iterMut_26;
    val x_300 = x_299.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    val x_301 = x_300.hasNext;
    if (x_301)
      {
        val x_302 = iterMut_26;
        val x_303 = x_302.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        val x_304 = x_303.next();
        listValMut_25 = x_304;
        val x_305 = ((this): meta.runtime.Actor).id;
        val x_307 = {
          val x_306 = listValMut_25;
          x_306.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node]
        };
        val x_308 = x_307.id;
        val x_309 = bindingMut_28;
        val x_310 = x_309.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        val x_311 = scala.collection.immutable.Nil.::[scala.Any](x_310);
        val x_312 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_311);
        val x_313 = meta.runtime.RequestMessage.apply(x_305, x_308, 2, x_312);
        val x_314 = x_313.future;
        val x_315 = x_314.asInstanceOf[meta.runtime.Future[scala.Unit]];
        x_313.`future_=`(x_315);
        ((this): meta.runtime.Actor).sendMessage(x_313);
        val x_316 = x_313.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_316, ((response_317: meta.runtime.Message) => {
          val x_318 = x_313.future;
          val x_319 = response_317.asInstanceOf[meta.runtime.ResponseMessage];
          val x_320 = x_319.arg;
          val x_321 = x_318.setValue[scala.Any](x_320);
          val x_322 = x_321.asInstanceOf[meta.runtime.Future[scala.Unit]];
          x_313.`future_=`(x_322);
          val x_323 = meta.runtime.SimRuntime.async_messages;
          val x_324 = x_313.future;
          val x_325 = x_324.id;
          val x_326 = scala.Predef.ArrowAssoc[java.lang.String](x_325);
          val x_327 = x_313.future;
          val x_328 = x_326.->[meta.runtime.Future[scala.Any]](x_327);
          val x_329 = x_323.+[meta.runtime.Future[scala.Any]](x_328);
          meta.runtime.SimRuntime.`async_messages_=`(x_329)
        }));
        val x_330 = x_313.future;
        val x_331 = scala.Some.apply[meta.runtime.Future[scala.Any]](x_330);
        resetData_0 = x_331;
        positionVar_72 = 19
      }
    else
      ()
  }));
  data_73.update(23, (() => {
    val x_332 = listValMut_66;
    val x_333 = x_332.asInstanceOf[meta.runtime.RequestMessage];
    val x_334 = x_333.methodId;
    val x_335 = x_334.==(2);
    if (x_335)
      {
        val x_336 = listValMut_66;
        val x_337 = x_336.asInstanceOf[meta.runtime.RequestMessage];
        val x_338 = x_337.argss;
        val x_339 = x_338(0);
        val x_340 = x_339(0);
        x_3.prepend(x_340);
        val x_341 = listValMut_66;
        val x_342 = x_341.asInstanceOf[meta.runtime.RequestMessage];
        val x_343 = x_342.argss;
        val x_344 = x_343(0);
        val x_345 = x_344(0);
        val x_346 = x_345.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        methodArgsMut_30 = x_346;
        val x_347 = methodArgsMut_30;
        val x_348 = x_347.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        val x_349 = x_348._1;
        resetData_0 = x_349;
        val x_350 = resetData_0;
        val x_351 = x_350.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        bindingMut_59 = x_351;
        val x_352 = methodArgsMut_30;
        val x_353 = x_352.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        val x_354 = x_353._2;
        resetData_0 = x_354;
        val x_355 = resetData_0;
        val x_356 = x_355.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_58 = x_356;
        this.`updated_=`(false);
        resetData_0 = ();
        val x_357 = this.allPids;
        resetData_0 = x_357;
        val x_358 = resetData_0;
        val x_359 = x_358.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_57 = x_359;
        val x_360 = bindingMut_57;
        val x_361 = x_360.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_364 = x_361.filter(((x_362: scala.Long) => {
          val x_363 = this.id;
          x_362.!=(x_363)
        }));
        resetData_0 = x_364;
        val x_365 = resetData_0;
        val x_366 = x_365.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        bindingMut_56 = x_366;
        val x_367 = bindingMut_56;
        val x_368 = x_367.asInstanceOf[scala.collection.immutable.List[scala.Long]];
        val x_369 = x_368.iterator;
        iterMut_55 = x_369;
        positionVar_72 = 24
      }
    else
      ()
  }));
  data_73.update(24, (() => {
    val x_370 = iterMut_55;
    val x_371 = x_370.asInstanceOf[scala.collection.Iterator[scala.Long]];
    val x_372 = x_371.hasNext;
    if (x_372)
      {
        val x_373 = iterMut_55;
        val x_374 = x_373.asInstanceOf[scala.collection.Iterator[scala.Long]];
        val x_375 = x_374.next();
        listValMut_54 = x_375;
        val x_376 = listValMut_54;
        val x_377 = x_376.asInstanceOf[scala.Long];
        val x_378 = bindingMut_58;
        val x_379 = x_378.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_380 = x_379(x_377);
        resetData_0 = x_380;
        val x_381 = resetData_0;
        val x_382 = x_381.asInstanceOf[scala.Int];
        bindingMut_53 = x_382;
        val x_383 = bindingMut_53;
        val x_384 = x_383.asInstanceOf[scala.Int];
        val x_385 = x_384.!=(2147483647);
        resetData_0 = x_385;
        val x_386 = resetData_0;
        val x_387 = x_386.asInstanceOf[scala.Boolean];
        bindingMut_52 = x_387;
        positionVar_72 = 25
      }
    else
      positionVar_72 = 31
  }));
  data_73.update(25, (() => {
    val x_388 = bindingMut_52;
    val x_389 = x_388.asInstanceOf[scala.Boolean];
    val x_390 = x_389.`unary_!`;
    if (x_390)
      {
        resetData_0 = false;
        positionVar_72 = 26
      }
    else
      positionVar_72 = 30
  }));
  data_73.update(26, (() => {
    val x_391 = resetData_0;
    val x_392 = x_391.asInstanceOf[scala.Boolean];
    bindingMut_44 = x_392;
    positionVar_72 = 27
  }));
  data_73.update(27, (() => {
    val x_393 = bindingMut_44;
    val x_394 = x_393.asInstanceOf[scala.Boolean];
    val x_395 = x_394.`unary_!`;
    if (x_395)
      positionVar_72 = 28
    else
      positionVar_72 = 29
  }));
  data_73.update(28, (() => positionVar_72 = 24));
  data_73.update(29, (() => {
    val x_396 = bindingMut_44;
    val x_397 = x_396.asInstanceOf[scala.Boolean];
    if (x_397)
      {
        val x_398 = this.lengths;
        resetData_0 = x_398;
        val x_399 = resetData_0;
        val x_400 = x_399.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_43 = x_400;
        val x_401 = this.lg;
        resetData_0 = x_401;
        val x_402 = resetData_0;
        val x_403 = x_402.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_42 = x_403;
        val x_404 = bindingMut_59;
        val x_405 = x_404.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        val x_406 = x_405.id;
        resetData_0 = x_406;
        val x_407 = resetData_0;
        val x_408 = x_407.asInstanceOf[scala.Long];
        bindingMut_41 = x_408;
        val x_409 = bindingMut_41;
        val x_410 = x_409.asInstanceOf[scala.Long];
        val x_411 = bindingMut_42;
        val x_412 = x_411.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_413 = x_412(x_410);
        resetData_0 = x_413;
        val x_414 = resetData_0;
        val x_415 = x_414.asInstanceOf[scala.Int];
        bindingMut_40 = x_415;
        val x_416 = listValMut_54;
        val x_417 = x_416.asInstanceOf[scala.Long];
        val x_418 = bindingMut_58;
        val x_419 = x_418.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_420 = x_419(x_417);
        resetData_0 = x_420;
        val x_421 = resetData_0;
        val x_422 = x_421.asInstanceOf[scala.Int];
        bindingMut_39 = x_422;
        val x_423 = bindingMut_39;
        val x_424 = x_423.asInstanceOf[scala.Int];
        val x_425 = bindingMut_40;
        val x_426 = x_425.asInstanceOf[scala.Int];
        val x_427 = x_426.+(x_424);
        resetData_0 = x_427;
        val x_428 = resetData_0;
        val x_429 = x_428.asInstanceOf[scala.Int];
        bindingMut_38 = x_429;
        val x_430 = bindingMut_38;
        val x_431 = x_430.asInstanceOf[scala.Int];
        val x_432 = bindingMut_43;
        val x_433 = x_432.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_434 = listValMut_54;
        val x_435 = x_434.asInstanceOf[scala.Long];
        x_433.update(x_435, x_431);
        resetData_0 = ();
        val x_436 = this.routing_to;
        resetData_0 = x_436;
        val x_437 = resetData_0;
        val x_438 = x_437.asInstanceOf[scala.collection.mutable.Map[scala.Long, generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        bindingMut_37 = x_438;
        val x_439 = bindingMut_37;
        val x_440 = x_439.asInstanceOf[scala.collection.mutable.Map[scala.Long, generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        val x_441 = listValMut_54;
        val x_442 = x_441.asInstanceOf[scala.Long];
        val x_443 = bindingMut_59;
        val x_444 = x_443.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        x_440.update(x_442, x_444);
        resetData_0 = ();
        this.`updated_=`(true);
        resetData_0 = ();
        positionVar_72 = 28
      }
    else
      ()
  }));
  data_73.update(30, (() => {
    val x_445 = bindingMut_52;
    val x_446 = x_445.asInstanceOf[scala.Boolean];
    if (x_446)
      {
        val x_447 = this.lengths;
        resetData_0 = x_447;
        val x_448 = resetData_0;
        val x_449 = x_448.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_51 = x_449;
        val x_450 = bindingMut_51;
        val x_451 = x_450.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_452 = listValMut_54;
        val x_453 = x_452.asInstanceOf[scala.Long];
        val x_454 = x_451(x_453);
        resetData_0 = x_454;
        val x_455 = resetData_0;
        val x_456 = x_455.asInstanceOf[scala.Int];
        bindingMut_50 = x_456;
        val x_457 = this.lg;
        resetData_0 = x_457;
        val x_458 = resetData_0;
        val x_459 = x_458.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_49 = x_459;
        val x_460 = bindingMut_59;
        val x_461 = x_460.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node];
        val x_462 = x_461.id;
        resetData_0 = x_462;
        val x_463 = resetData_0;
        val x_464 = x_463.asInstanceOf[scala.Long];
        bindingMut_48 = x_464;
        val x_465 = bindingMut_48;
        val x_466 = x_465.asInstanceOf[scala.Long];
        val x_467 = bindingMut_49;
        val x_468 = x_467.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_469 = x_468(x_466);
        resetData_0 = x_469;
        val x_470 = resetData_0;
        val x_471 = x_470.asInstanceOf[scala.Int];
        bindingMut_47 = x_471;
        val x_472 = listValMut_54;
        val x_473 = x_472.asInstanceOf[scala.Long];
        val x_474 = bindingMut_58;
        val x_475 = x_474.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_476 = x_475(x_473);
        resetData_0 = x_476;
        val x_477 = resetData_0;
        val x_478 = x_477.asInstanceOf[scala.Int];
        bindingMut_46 = x_478;
        val x_479 = bindingMut_46;
        val x_480 = x_479.asInstanceOf[scala.Int];
        val x_481 = bindingMut_47;
        val x_482 = x_481.asInstanceOf[scala.Int];
        val x_483 = x_482.+(x_480);
        resetData_0 = x_483;
        val x_484 = resetData_0;
        val x_485 = x_484.asInstanceOf[scala.Int];
        bindingMut_45 = x_485;
        val x_486 = bindingMut_45;
        val x_487 = x_486.asInstanceOf[scala.Int];
        val x_488 = bindingMut_50;
        val x_489 = x_488.asInstanceOf[scala.Int];
        val x_490 = x_489.>(x_487);
        resetData_0 = x_490;
        positionVar_72 = 26
      }
    else
      ()
  }));
  data_73.update(31, (() => {
    val x_491 = iterMut_55;
    val x_492 = x_491.asInstanceOf[scala.collection.Iterator[scala.Long]];
    val x_493 = x_492.hasNext;
    val x_494 = x_493.`unary_!`;
    if (x_494)
      {
        val x_495 = this.lengths;
        resetData_0 = x_495;
        val x_496 = resetData_0;
        val x_497 = x_496.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_36 = x_497;
        val x_498 = bindingMut_36;
        val x_499 = x_498.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_500 = scala.Tuple2.apply[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]](this, x_499);
        resetData_0 = x_500;
        val x_501 = resetData_0;
        val x_502 = x_501.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        bindingMut_35 = x_502;
        val x_503 = this.updated;
        resetData_0 = x_503;
        val x_504 = resetData_0;
        val x_505 = x_504.asInstanceOf[scala.Boolean];
        bindingMut_34 = x_505;
        positionVar_72 = 32
      }
    else
      ()
  }));
  data_73.update(32, (() => {
    val x_506 = bindingMut_34;
    val x_507 = x_506.asInstanceOf[scala.Boolean];
    val x_508 = x_507.`unary_!`;
    if (x_508)
      positionVar_72 = 33
    else
      positionVar_72 = 34
  }));
  data_73.update(33, (() => {
    x_3.remove(0);
    val x_509 = x_3.isEmpty;
    val x_510 = x_509.`unary_!`;
    if (x_510)
      {
        val x_511 = x_3(0);
        val x_512 = x_511.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        methodArgsMut_30 = x_512
      }
    else
      ();
    val x_513 = resetData_0;
    val x_514 = x_513.asInstanceOf[scala.Any];
    bindingMut_65 = x_514;
    val x_515 = bindingMut_65;
    val x_516 = x_515.asInstanceOf[scala.Any];
    val x_517 = listValMut_66;
    val x_518 = x_517.asInstanceOf[meta.runtime.RequestMessage];
    x_518.reply(this, x_516);
    resetData_0 = ();
    positionVar_72 = 11
  }));
  data_73.update(34, (() => {
    val x_519 = bindingMut_34;
    val x_520 = x_519.asInstanceOf[scala.Boolean];
    if (x_520)
      {
        val x_521 = this.neighbors;
        resetData_0 = x_521;
        val x_522 = resetData_0;
        val x_523 = x_522.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        bindingMut_33 = x_523;
        val x_524 = bindingMut_33;
        val x_525 = x_524.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        val x_526 = x_525.iterator;
        iterMut_32 = x_526;
        positionVar_72 = 35
      }
    else
      ()
  }));
  data_73.update(35, (() => {
    val x_527 = iterMut_32;
    val x_528 = x_527.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    val x_529 = x_528.hasNext;
    val x_530 = x_529.`unary_!`;
    if (x_530)
      positionVar_72 = 33
    else
      positionVar_72 = 36
  }));
  data_73.update(36, (() => {
    val x_531 = iterMut_32;
    val x_532 = x_531.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
    val x_533 = x_532.hasNext;
    if (x_533)
      {
        val x_534 = iterMut_32;
        val x_535 = x_534.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.Node]];
        val x_536 = x_535.next();
        listValMut_31 = x_536;
        val x_537 = ((this): meta.runtime.Actor).id;
        val x_539 = {
          val x_538 = listValMut_31;
          x_538.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.Node]
        };
        val x_540 = x_539.id;
        val x_541 = bindingMut_35;
        val x_542 = x_541.asInstanceOf[scala.Tuple2[generated.example.distributedGraph.shortestPath.BellmanFord.Node, scala.collection.mutable.Map[scala.Long, scala.Int]]];
        val x_543 = scala.collection.immutable.Nil.::[scala.Any](x_542);
        val x_544 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_543);
        val x_545 = meta.runtime.RequestMessage.apply(x_537, x_540, 2, x_544);
        val x_546 = x_545.future;
        val x_547 = x_546.asInstanceOf[meta.runtime.Future[scala.Unit]];
        x_545.`future_=`(x_547);
        ((this): meta.runtime.Actor).sendMessage(x_545);
        val x_548 = x_545.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_548, ((response_549: meta.runtime.Message) => {
          val x_550 = x_545.future;
          val x_551 = response_549.asInstanceOf[meta.runtime.ResponseMessage];
          val x_552 = x_551.arg;
          val x_553 = x_550.setValue[scala.Any](x_552);
          val x_554 = x_553.asInstanceOf[meta.runtime.Future[scala.Unit]];
          x_545.`future_=`(x_554);
          val x_555 = meta.runtime.SimRuntime.async_messages;
          val x_556 = x_545.future;
          val x_557 = x_556.id;
          val x_558 = scala.Predef.ArrowAssoc[java.lang.String](x_557);
          val x_559 = x_545.future;
          val x_560 = x_558.->[meta.runtime.Future[scala.Any]](x_559);
          val x_561 = x_555.+[meta.runtime.Future[scala.Any]](x_560);
          meta.runtime.SimRuntime.`async_messages_=`(x_561)
        }));
        val x_562 = x_545.future;
        val x_563 = scala.Some.apply[meta.runtime.Future[scala.Any]](x_562);
        resetData_0 = x_563;
        positionVar_72 = 35
      }
    else
      ()
  }));
  data_73.update(37, (() => {
    val x_564 = iterMut_67;
    val x_565 = x_564.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_566 = x_565.hasNext;
    val x_567 = x_566.`unary_!`;
    if (x_567)
      {
        resetData_0 = 0.0;
        val x_568 = resetData_0;
        val x_569 = x_568.asInstanceOf[scala.Double];
        bindingMut_64 = x_569;
        positionVar_72 = 38
      }
    else
      ()
  }));
  data_73.update(38, (() => {
    val x_570 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_571 = meta.runtime.SimRuntime.labelVals(x_570);
    val x_572 = bindingMut_64;
    val x_573 = x_572.asInstanceOf[scala.Double];
    val x_574 = 1.0.-(x_573);
    x_571.append(x_574);
    resetData_0 = ();
    positionVar_72 = 39;
    val x_575 = currentTurn;
    val x_576 = x_575.+(1);
    currentTurn = x_576
  }));
  data_73.update(39, (() => {
    val x_577 = meta.runtime.SimRuntime.proceedLabel;
    val x_578 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_579 = x_577(x_578);
    val x_580 = bindingMut_64;
    val x_581 = x_580.asInstanceOf[scala.Double];
    val x_582 = x_581.+(x_579);
    resetData_0 = x_582;
    val x_583 = resetData_0;
    val x_584 = x_583.asInstanceOf[scala.Double];
    bindingMut_64 = x_584;
    positionVar_72 = 40
  }));
  data_73.update(40, (() => {
    val x_585 = bindingMut_64;
    val x_586 = x_585.asInstanceOf[scala.Double];
    val x_587 = x_586.<(1.0);
    if (x_587)
      positionVar_72 = 38
    else
      positionVar_72 = 41
  }));
  data_73.update(41, (() => {
    val x_588 = bindingMut_64;
    val x_589 = x_588.asInstanceOf[scala.Double];
    val x_590 = x_589.<(1.0);
    val x_591 = x_590.`unary_!`;
    if (x_591)
      {
        val x_592 = scala.StringContext.apply("", ": Lengths: ", "");
        resetData_0 = x_592;
        val x_593 = resetData_0;
        val x_594 = x_593.asInstanceOf[scala.StringContext];
        bindingMut_63 = x_594;
        val x_595 = this.id;
        resetData_0 = x_595;
        val x_596 = resetData_0;
        val x_597 = x_596.asInstanceOf[scala.Long];
        bindingMut_62 = x_597;
        val x_598 = this.lengths;
        resetData_0 = x_598;
        val x_599 = resetData_0;
        val x_600 = x_599.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        bindingMut_61 = x_600;
        val x_601 = bindingMut_61;
        val x_602 = x_601.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
        val x_603 = bindingMut_62;
        val x_604 = x_603.asInstanceOf[scala.Long];
        val x_605 = bindingMut_63;
        val x_606 = x_605.asInstanceOf[scala.StringContext];
        val x_607 = x_606.s(x_604, x_602);
        resetData_0 = x_607;
        val x_608 = resetData_0;
        val x_609 = x_608.asInstanceOf[java.lang.String];
        bindingMut_60 = x_609;
        val x_610 = bindingMut_60;
        val x_611 = x_610.asInstanceOf[java.lang.String];
        scala.Predef.println(x_611);
        resetData_0 = ();
        positionVar_72 = 42
      }
    else
      ()
  }));
  data_73.update(42, (() => if (true)
    positionVar_72 = 43
  else
    positionVar_72 = 44));
  data_73.update(43, (() => {
    val x_612 = this.popRequestMessages;
    val x_613 = x_612.iterator;
    iterMut_67 = x_613;
    positionVar_72 = 12
  }));
  data_73.update(44, (() => {
    val x_614 = true.`unary_!`;
    if (x_614)
      positionVar_72 = 45
    else
      ()
  }));
  data_73.update(45, (() => positionVar_72 = 46));
  data_73.update(46, (() => {
    positionVar_72 = 47;
    val x_615 = currentTurn;
    val x_616 = x_615.+(1);
    currentTurn = x_616
  }));
  data_73.update(47, (() => positionVar_72 = 46));
  data_73.update(48, (() => {
    val x_617 = scala.StringContext.apply("Channel distance to neighbors : ", "");
    resetData_0 = x_617;
    val x_618 = resetData_0;
    val x_619 = x_618.asInstanceOf[scala.StringContext];
    bindingMut_70 = x_619;
    val x_620 = this.lg;
    resetData_0 = x_620;
    val x_621 = resetData_0;
    val x_622 = x_621.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
    bindingMut_69 = x_622;
    val x_623 = bindingMut_69;
    val x_624 = x_623.asInstanceOf[scala.collection.mutable.Map[scala.Long, scala.Int]];
    val x_625 = bindingMut_70;
    val x_626 = x_625.asInstanceOf[scala.StringContext];
    val x_627 = x_626.s(x_624);
    resetData_0 = x_627;
    val x_628 = resetData_0;
    val x_629 = x_628.asInstanceOf[java.lang.String];
    bindingMut_68 = x_629;
    val x_630 = bindingMut_68;
    val x_631 = x_630.asInstanceOf[java.lang.String];
    scala.Predef.println(x_631);
    resetData_0 = ();
    positionVar_72 = 49
  }));
  data_73.update(49, (() => if (true)
    positionVar_72 = 43
  else
    positionVar_72 = 50));
  data_73.update(50, (() => {
    val x_632 = true.`unary_!`;
    if (x_632)
      positionVar_72 = 45
    else
      ()
  }));
  data_73.update(51, (() => {
    val x_633 = bindingMut_4;
    val x_634 = x_633.asInstanceOf[scala.Boolean];
    if (x_634)
      positionVar_72 = 18
    else
      ();
    val x_635 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_636 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_635, 21);
    val x_637 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_636);
    resetData_1.prepend(x_637)
  }));
  data_73
}).apply();
  
  override def run_until(until_639: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_640 = currentTurn;
      val x_641 = x_640.<=(until_639);
      x_641.&&({
        val x_642 = positionVar_72;
        val x_643 = commands_638.length;
        x_642.<(x_643)
      })
    }) 
      {
        val x_644 = positionVar_72;
        val x_645 = commands_638.apply(x_644);
        x_645.apply()
      }
    ;
    this
  }
}
