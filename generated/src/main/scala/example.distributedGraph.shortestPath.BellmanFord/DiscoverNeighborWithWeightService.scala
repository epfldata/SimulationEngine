package generated.example.distributedGraph.shortestPath.BellmanFord

class DiscoverNeighborWithWeightService () extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_5: scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel] = null;
  private var methodArgsMut_6: scala.Long = 0L;
  private var bindingMut_7: scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  private var bindingMut_8: scala.Tuple2[example.distributedGraph.TreeNode, scala.Int] = null;
  private var listValMut_9: scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  @transient private var iterMut_10: scala.collection.Iterator[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  private var bindingMut_11: scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  @transient private var bindingMut_12: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Tuple2[example.distributedGraph.TreeNode, scala.Int], scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  private var bindingMut_13: scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  private var bindingMut_14: scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  private var bindingMut_15: scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  private var bindingMut_16: scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]] = null;
  private var listValMut_17: scala.Any = null;
  @transient private var iterMut_18: scala.collection.Iterator[scala.Any] = null;
  private var bindingMut_19: scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]] = null;
  @transient private var bindingMut_20: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]], scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]] = null;
  private var bindingMut_21: scala.collection.immutable.List[scala.Any] = null;
  private var bindingMut_22: scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]] = null;
  private var bindingMut_23: scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]] = null;
  private var bindingMut_24: scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]] = null;
  private var listValMut_25: generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel = null;
  @transient private var iterMut_26: scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel] = null;
  private var bindingMut_27: scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]] = null;
  @transient private var bindingMut_28: scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]], scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]] = null;
  private var bindingMut_29: generated.example.distributedGraph.shortestPath.BellmanFord.MessengerBot = null;
  private var bindingMut_30: scala.Double = 0.0;
  private var bindingMut_31: scala.Any = null;
  private var listValMut_32: meta.runtime.RequestMessage = null;
  @transient private var iterMut_33: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_35: scala.Int = 0;
  
  val commands_290 = (() => {
  val data_36 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_36.update(0, (() => positionVar_35 = 1));
  data_36.update(1, (() => if (true)
    positionVar_35 = 2
  else
    positionVar_35 = 26));
  data_36.update(2, (() => {
    val x_37 = this.popRequestMessages;
    val x_38 = x_37.iterator;
    iterMut_33 = x_38;
    positionVar_35 = 3
  }));
  data_36.update(3, (() => {
    val x_39 = iterMut_33;
    val x_40 = x_39.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_41 = x_40.hasNext;
    if (x_41)
      {
        val x_42 = iterMut_33;
        val x_43 = x_42.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_44 = x_43.next();
        listValMut_32 = x_44;
        positionVar_35 = 4
      }
    else
      positionVar_35 = 16
  }));
  data_36.update(4, (() => {
    val x_45 = listValMut_32;
    val x_46 = x_45.asInstanceOf[meta.runtime.RequestMessage];
    val x_47 = x_46.methodId;
    val x_48 = x_47.==(9);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      {
        val x_50 = listValMut_32;
        val x_51 = x_50.asInstanceOf[meta.runtime.RequestMessage];
        val x_52 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_51);
        val x_53 = this.addReceiveMessages(x_52);
        resetData_0 = x_53;
        positionVar_35 = 5
      }
    else
      positionVar_35 = 6
  }));
  data_36.update(5, (() => positionVar_35 = 3));
  data_36.update(6, (() => {
    val x_54 = listValMut_32;
    val x_55 = x_54.asInstanceOf[meta.runtime.RequestMessage];
    val x_56 = x_55.methodId;
    val x_57 = x_56.==(9);
    if (x_57)
      {
        val x_58 = listValMut_32;
        val x_59 = x_58.asInstanceOf[meta.runtime.RequestMessage];
        val x_60 = x_59.argss;
        val x_61 = x_60(0);
        val x_62 = x_61(0);
        x_4.prepend(x_62);
        val x_63 = listValMut_32;
        val x_64 = x_63.asInstanceOf[meta.runtime.RequestMessage];
        val x_65 = x_64.argss;
        val x_66 = x_65(0);
        val x_67 = x_66(1);
        x_3.prepend(x_67);
        val x_68 = listValMut_32;
        val x_69 = x_68.asInstanceOf[meta.runtime.RequestMessage];
        val x_70 = x_69.argss;
        val x_71 = x_70(0);
        val x_72 = x_71(0);
        val x_73 = x_72.asInstanceOf[scala.Long];
        methodArgsMut_6 = x_73;
        val x_74 = listValMut_32;
        val x_75 = x_74.asInstanceOf[meta.runtime.RequestMessage];
        val x_76 = x_75.argss;
        val x_77 = x_76(0);
        val x_78 = x_77(1);
        val x_79 = x_78.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
        methodArgsMut_5 = x_79;
        val x_80 = new generated.example.distributedGraph.shortestPath.BellmanFord.MessengerBot();
        meta.runtime.SimRuntime.newActors.append(x_80);
        resetData_0 = x_80;
        val x_81 = resetData_0;
        val x_82 = x_81.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.MessengerBot];
        bindingMut_29 = x_82;
        val x_83 = scala.collection.immutable.List.canBuildFrom[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]];
        resetData_0 = x_83;
        val x_84 = resetData_0;
        val x_85 = x_84.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]], scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]]];
        bindingMut_28 = x_85;
        resetData_0 = scala.collection.immutable.Nil;
        val x_86 = resetData_0;
        val x_87 = x_86.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        bindingMut_27 = x_87;
        val x_88 = methodArgsMut_5;
        val x_89 = x_88.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
        val x_90 = x_89.iterator;
        iterMut_26 = x_90;
        positionVar_35 = 7
      }
    else
      ()
  }));
  data_36.update(7, (() => {
    val x_91 = iterMut_26;
    val x_92 = x_91.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
    val x_93 = x_92.hasNext;
    if (x_93)
      {
        val x_94 = iterMut_26;
        val x_95 = x_94.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
        val x_96 = x_95.next();
        listValMut_25 = x_96;
        val x_97 = ((this): meta.runtime.Actor).id;
        val x_99 = {
          val x_98 = listValMut_25;
          x_98.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]
        };
        val x_100 = x_99.id;
        val x_101 = methodArgsMut_6;
        val x_102 = x_101.asInstanceOf[scala.Long];
        val x_103 = scala.collection.immutable.Nil.::[scala.Any](x_102);
        val x_104 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_103);
        val x_105 = meta.runtime.RequestMessage.apply(x_97, x_100, 4, x_104);
        val x_106 = x_105.future;
        val x_107 = x_106.asInstanceOf[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        x_105.`future_=`(x_107);
        ((this): meta.runtime.Actor).sendMessage(x_105);
        val x_108 = x_105.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_108, ((response_109: meta.runtime.Message) => {
          val x_110 = x_105.future;
          val x_111 = response_109.asInstanceOf[meta.runtime.ResponseMessage];
          val x_112 = x_111.arg;
          val x_113 = x_110.setValue[scala.Any](x_112);
          val x_114 = x_113.asInstanceOf[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
          x_105.`future_=`(x_114);
          val x_115 = meta.runtime.SimRuntime.async_messages;
          val x_116 = x_105.future;
          val x_117 = x_116.id;
          val x_118 = scala.Predef.ArrowAssoc[java.lang.String](x_117);
          val x_119 = x_105.future;
          val x_120 = x_118.->[meta.runtime.Future[scala.Any]](x_119);
          val x_121 = x_115.+[meta.runtime.Future[scala.Any]](x_120);
          meta.runtime.SimRuntime.`async_messages_=`(x_121)
        }));
        val x_122 = x_105.future;
        val x_123 = scala.Some.apply[meta.runtime.Future[scala.Any]](x_122);
        resetData_0 = x_123;
        val x_124 = resetData_0;
        val x_125 = x_124.asInstanceOf[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]];
        bindingMut_24 = x_125;
        val x_126 = bindingMut_24;
        val x_127 = x_126.asInstanceOf[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]];
        val x_128 = scala.collection.immutable.List.apply[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]](x_127);
        resetData_0 = x_128;
        val x_129 = resetData_0;
        val x_130 = x_129.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        bindingMut_23 = x_130;
        val x_131 = bindingMut_23;
        val x_132 = x_131.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        val x_133 = bindingMut_27;
        val x_134 = x_133.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        val x_135 = x_132.:::[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]](x_134);
        resetData_0 = x_135;
        val x_136 = resetData_0;
        val x_137 = x_136.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        bindingMut_27 = x_137;
        positionVar_35 = 7
      }
    else
      positionVar_35 = 8
  }));
  data_36.update(8, (() => {
    val x_138 = iterMut_26;
    val x_139 = x_138.asInstanceOf[scala.collection.Iterator[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
    val x_140 = x_139.hasNext;
    val x_141 = x_140.`unary_!`;
    if (x_141)
      {
        val x_142 = resetData_0;
        val x_143 = x_142.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        bindingMut_22 = x_143;
        val x_144 = ((this): meta.runtime.Actor).id;
        val x_146 = {
          val x_145 = bindingMut_29;
          x_145.asInstanceOf[generated.example.distributedGraph.shortestPath.BellmanFord.MessengerBot]
        };
        val x_147 = x_146.id;
        val x_148 = bindingMut_22;
        val x_149 = x_148.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.runtime.Future[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]]];
        val x_150 = scala.collection.immutable.Nil.::[scala.Any](x_149);
        val x_151 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_150);
        val x_152 = meta.runtime.RequestMessage.apply(x_144, x_147, 7, x_151);
        ((this): meta.runtime.Actor).sendMessage(x_152);
        val x_153 = x_152.sessionId;
        ((this): meta.runtime.Actor).setMessageResponseHandler(x_153, ((response_154: meta.runtime.Message) => {
          val x_155 = response_154.asInstanceOf[meta.runtime.ResponseMessage];
          resetData_2 = x_155
        }));
        resetData_0 = null;
        positionVar_35 = 9
      }
    else
      ()
  }));
  data_36.update(9, (() => {
    positionVar_35 = 10;
    val x_156 = currentTurn;
    val x_157 = x_156.+(1);
    currentTurn = x_157
  }));
  data_36.update(10, (() => {
    val x_158 = resetData_2;
    val x_159 = x_158.==(null);
    if (x_159)
      positionVar_35 = 9
    else
      positionVar_35 = 11
  }));
  data_36.update(11, (() => {
    val x_160 = resetData_2;
    val x_161 = x_160.!=(null);
    if (x_161)
      {
        val x_162 = resetData_2;
        val x_163 = x_162.arg;
        resetData_0 = x_163;
        resetData_2 = null;
        val x_164 = resetData_0;
        val x_165 = x_164.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        bindingMut_21 = x_165;
        val x_166 = scala.collection.immutable.List.canBuildFrom[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        resetData_0 = x_166;
        val x_167 = resetData_0;
        val x_168 = x_167.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]], scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]]];
        bindingMut_20 = x_168;
        resetData_0 = scala.collection.immutable.Nil;
        val x_169 = resetData_0;
        val x_170 = x_169.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_19 = x_170;
        val x_171 = bindingMut_21;
        val x_172 = x_171.asInstanceOf[scala.collection.immutable.List[scala.Any]];
        val x_173 = x_172.iterator;
        iterMut_18 = x_173;
        positionVar_35 = 12
      }
    else
      ()
  }));
  data_36.update(12, (() => {
    val x_174 = iterMut_18;
    val x_175 = x_174.asInstanceOf[scala.collection.Iterator[scala.Any]];
    val x_176 = x_175.hasNext;
    if (x_176)
      {
        val x_177 = iterMut_18;
        val x_178 = x_177.asInstanceOf[scala.collection.Iterator[scala.Any]];
        val x_179 = x_178.next();
        listValMut_17 = x_179;
        val x_180 = listValMut_17;
        val x_181 = x_180.asInstanceOf[scala.Any];
        val x_182 = x_181.asInstanceOf[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        resetData_0 = x_182;
        val x_183 = resetData_0;
        val x_184 = x_183.asInstanceOf[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        bindingMut_16 = x_184;
        val x_185 = bindingMut_16;
        val x_186 = x_185.asInstanceOf[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_187 = scala.collection.immutable.List.apply[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]](x_186);
        resetData_0 = x_187;
        val x_188 = resetData_0;
        val x_189 = x_188.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_15 = x_189;
        val x_190 = bindingMut_15;
        val x_191 = x_190.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        val x_192 = bindingMut_19;
        val x_193 = x_192.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        val x_194 = x_191.:::[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]](x_193);
        resetData_0 = x_194;
        val x_195 = resetData_0;
        val x_196 = x_195.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_19 = x_196;
        positionVar_35 = 12
      }
    else
      positionVar_35 = 13
  }));
  data_36.update(13, (() => {
    val x_197 = iterMut_18;
    val x_198 = x_197.asInstanceOf[scala.collection.Iterator[scala.Any]];
    val x_199 = x_198.hasNext;
    val x_200 = x_199.`unary_!`;
    if (x_200)
      {
        val x_201 = resetData_0;
        val x_202 = x_201.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_14 = x_202;
        val x_203 = bindingMut_14;
        val x_204 = x_203.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        val x_206 = x_204.filter(((x_205: scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]) => x_205.isDefined));
        resetData_0 = x_206;
        val x_207 = resetData_0;
        val x_208 = x_207.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_13 = x_208;
        val x_209 = scala.collection.immutable.List.canBuildFrom[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        resetData_0 = x_209;
        val x_210 = resetData_0;
        val x_211 = x_210.asInstanceOf[scala.collection.generic.CanBuildFrom[scala.collection.immutable.List[_], scala.Tuple2[example.distributedGraph.TreeNode, scala.Int], scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        bindingMut_12 = x_211;
        resetData_0 = scala.collection.immutable.Nil;
        val x_212 = resetData_0;
        val x_213 = x_212.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        bindingMut_11 = x_213;
        val x_214 = bindingMut_13;
        val x_215 = x_214.asInstanceOf[scala.collection.immutable.List[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        val x_216 = x_215.iterator;
        iterMut_10 = x_216;
        positionVar_35 = 14
      }
    else
      ()
  }));
  data_36.update(14, (() => {
    val x_217 = iterMut_10;
    val x_218 = x_217.asInstanceOf[scala.collection.Iterator[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
    val x_219 = x_218.hasNext;
    if (x_219)
      {
        val x_220 = iterMut_10;
        val x_221 = x_220.asInstanceOf[scala.collection.Iterator[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
        val x_222 = x_221.next();
        listValMut_9 = x_222;
        val x_223 = listValMut_9;
        val x_224 = x_223.asInstanceOf[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_225 = x_224.get;
        resetData_0 = x_225;
        val x_226 = resetData_0;
        val x_227 = x_226.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        bindingMut_8 = x_227;
        val x_228 = bindingMut_8;
        val x_229 = x_228.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_230 = scala.collection.immutable.List.apply[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]](x_229);
        resetData_0 = x_230;
        val x_231 = resetData_0;
        val x_232 = x_231.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        bindingMut_7 = x_232;
        val x_233 = bindingMut_7;
        val x_234 = x_233.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_235 = bindingMut_11;
        val x_236 = x_235.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        val x_237 = x_234.:::[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]](x_236);
        resetData_0 = x_237;
        val x_238 = resetData_0;
        val x_239 = x_238.asInstanceOf[scala.collection.immutable.List[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]];
        bindingMut_11 = x_239;
        positionVar_35 = 14
      }
    else
      positionVar_35 = 15
  }));
  data_36.update(15, (() => {
    val x_240 = iterMut_10;
    val x_241 = x_240.asInstanceOf[scala.collection.Iterator[scala.Option[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]]]];
    val x_242 = x_241.hasNext;
    val x_243 = x_242.`unary_!`;
    if (x_243)
      {
        x_4.remove(0);
        val x_244 = x_4.isEmpty;
        val x_245 = x_244.`unary_!`;
        if (x_245)
          {
            val x_246 = x_4(0);
            val x_247 = x_246.asInstanceOf[scala.Long];
            methodArgsMut_6 = x_247
          }
        else
          ();
        x_3.remove(0);
        val x_248 = x_3.isEmpty;
        val x_249 = x_248.`unary_!`;
        if (x_249)
          {
            val x_250 = x_3(0);
            val x_251 = x_250.asInstanceOf[scala.collection.immutable.List[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel]];
            methodArgsMut_5 = x_251
          }
        else
          ();
        val x_252 = resetData_0;
        val x_253 = x_252.asInstanceOf[scala.Any];
        bindingMut_31 = x_253;
        val x_254 = bindingMut_31;
        val x_255 = x_254.asInstanceOf[scala.Any];
        val x_256 = listValMut_32;
        val x_257 = x_256.asInstanceOf[meta.runtime.RequestMessage];
        x_257.reply(this, x_255);
        resetData_0 = ();
        positionVar_35 = 5
      }
    else
      ()
  }));
  data_36.update(16, (() => {
    val x_258 = iterMut_33;
    val x_259 = x_258.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_260 = x_259.hasNext;
    val x_261 = x_260.`unary_!`;
    if (x_261)
      {
        resetData_0 = 0.0;
        val x_262 = resetData_0;
        val x_263 = x_262.asInstanceOf[scala.Double];
        bindingMut_30 = x_263;
        positionVar_35 = 17
      }
    else
      ()
  }));
  data_36.update(17, (() => {
    val x_264 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_265 = meta.runtime.SimRuntime.labelVals(x_264);
    val x_266 = bindingMut_30;
    val x_267 = x_266.asInstanceOf[scala.Double];
    val x_268 = 1.0.-(x_267);
    x_265.append(x_268);
    resetData_0 = ();
    positionVar_35 = 18;
    val x_269 = currentTurn;
    val x_270 = x_269.+(1);
    currentTurn = x_270
  }));
  data_36.update(18, (() => {
    val x_271 = meta.runtime.SimRuntime.proceedLabel;
    val x_272 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_273 = x_271(x_272);
    val x_274 = bindingMut_30;
    val x_275 = x_274.asInstanceOf[scala.Double];
    val x_276 = x_275.+(x_273);
    resetData_0 = x_276;
    val x_277 = resetData_0;
    val x_278 = x_277.asInstanceOf[scala.Double];
    bindingMut_30 = x_278;
    positionVar_35 = 19
  }));
  data_36.update(19, (() => {
    val x_279 = bindingMut_30;
    val x_280 = x_279.asInstanceOf[scala.Double];
    val x_281 = x_280.<(1.0);
    if (x_281)
      positionVar_35 = 17
    else
      positionVar_35 = 20
  }));
  data_36.update(20, (() => {
    val x_282 = bindingMut_30;
    val x_283 = x_282.asInstanceOf[scala.Double];
    val x_284 = x_283.<(1.0);
    val x_285 = x_284.`unary_!`;
    if (x_285)
      positionVar_35 = 21
    else
      ()
  }));
  data_36.update(21, (() => if (true)
    positionVar_35 = 2
  else
    positionVar_35 = 22));
  data_36.update(22, (() => {
    val x_286 = true.`unary_!`;
    if (x_286)
      positionVar_35 = 23
    else
      ()
  }));
  data_36.update(23, (() => positionVar_35 = 24));
  data_36.update(24, (() => {
    positionVar_35 = 25;
    val x_287 = currentTurn;
    val x_288 = x_287.+(1);
    currentTurn = x_288
  }));
  data_36.update(25, (() => positionVar_35 = 24));
  data_36.update(26, (() => {
    val x_289 = true.`unary_!`;
    if (x_289)
      positionVar_35 = 23
    else
      ()
  }));
  data_36
}).apply();
  
  override def run_until(until_291: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_292 = currentTurn;
      val x_293 = x_292.<=(until_291);
      x_293.&&({
        val x_294 = positionVar_35;
        val x_295 = commands_290.length;
        x_294.<(x_295)
      })
    }) 
      {
        val x_296 = positionVar_35;
        val x_297 = commands_290.apply(x_296);
        x_297.apply()
      }
    ;
    this
  }
}
