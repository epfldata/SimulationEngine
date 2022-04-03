package generated.meta.test.blockingMethodCall

class AgentWithBlockingCall(val n: generated.meta.test.blockingMethodCall.AgentWithBlockingCall) extends meta.runtime.Actor {

  var future: meta.runtime.Future[Boolean] = null;
  private var  reflectionIR_41: Int = -1
  var resetData_0: scala.Any = null
  val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
  var resetData_2: meta.runtime.ResponseMessage = null
  var bindingMut_3: java.lang.String = null
  var bindingMut_4: scala.Long = 0L
  var bindingMut_5: scala.Double = 0.0
  var bindingMut_6: java.lang.String = null
  var bindingMut_7: scala.Long = 0L
  var bindingMut_8: scala.Any = null
  var listValMut_9: meta.runtime.RequestMessage = null
  @transient var iterMut_10: scala.collection.Iterator[meta.runtime.RequestMessage] = null
  var bindingMut_11: java.lang.String = null
  var bindingMut_12: scala.Boolean = false
  var bindingMut_13: scala.Option[scala.Boolean] = null
  var bindingMut_14: meta.runtime.Future[scala.Boolean] = null
  var bindingMut_15: meta.runtime.Future[scala.Boolean] = null
  var bindingMut_16: java.lang.String = null
  var bindingMut_17: scala.Boolean = false
  var bindingMut_18: scala.Option[scala.Boolean] = null
  var bindingMut_19: meta.runtime.Future[scala.Boolean] = null
  var bindingMut_20: scala.Double = 0.0
  var bindingMut_21: meta.runtime.Future[scala.Boolean] = null
  var bindingMut_22: scala.Boolean = false
  var bindingMut_23: generated.meta.test.blockingMethodCall.AgentWithBlockingCall = null
  var unblockFlag_24: scala.Boolean = true
  var positionVar_25: scala.Int = 0
  
  val commands_254 = (() => {
  val data_26 = new scala.Array[scala.Function0[scala.Unit]](58);
  data_26.update(0, (() => positionVar_25 = 1));
  data_26.update(1, (() => {
    val x_27 = this.n;
    resetData_0 = x_27;
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[generated.meta.test.blockingMethodCall.AgentWithBlockingCall];
    bindingMut_23 = x_29;
    val x_30 = bindingMut_23;
    val x_31 = x_30.!=(null);
    resetData_0 = x_31;
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[scala.Boolean];
    bindingMut_22 = x_33;
    positionVar_25 = 2
  }));
  data_26.update(2, (() => {
    val x_34 = bindingMut_22;
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_25 = 3
    else
      positionVar_25 = 54
  }));
  data_26.update(3, (() => {
    resetData_0 = 0.0;
    val x_36 = resetData_0;
    val x_37 = x_36.asInstanceOf[scala.Double];
    bindingMut_20 = x_37;
    positionVar_25 = 4
  }));
  data_26.update(4, (() => {
    val x_38 = bindingMut_20;
    val x_39 = x_38.+(1);
    resetData_0 = x_39;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Double];
    bindingMut_20 = x_41;
    positionVar_25 = 5;
    unblockFlag_24 = false
  }));
  data_26.update(5, (() => {
    positionVar_25 = 6;
    val x_42 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_43 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_42, 27);
    val x_44 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_43);
    resetData_1.prepend(x_44)
  }));
  data_26.update(6, (() => {
    val x_45 = this.popRequestMessages;
    val x_46 = x_45.iterator;
    iterMut_10 = x_46;
    positionVar_25 = 7
  }));
  data_26.update(7, (() => {
    val x_47 = iterMut_10;
    val x_48 = x_47.hasNext;
    if (x_48)
      {
        val x_49 = iterMut_10;
        val x_50 = x_49.next();
        listValMut_9 = x_50;
        positionVar_25 = 8
      }
    else
      positionVar_25 = 26
  }));
  data_26.update(8, (() => {
    val x_51 = listValMut_9;
    val x_52 = x_51.methodInfo;
    val x_53 = scala.`package`.Right.apply[scala.Nothing, scala.Int](1);
    val x_54 = x_52.==(x_53);
    if (x_54)
      positionVar_25 = 9
    else
      positionVar_25 = 15
  }));
  data_26.update(9, (() => {
    val x_55 = listValMut_9;
    this.handleNonblockingMessage(x_55);
    resetData_0 = ();
    positionVar_25 = 10
  }));
  data_26.update(10, (() => positionVar_25 = 11));
  data_26.update(11, (() => positionVar_25 = 7));
  data_26.update(12, (() => positionVar_25 = 13));
  data_26.update(13, (() => {
    val x_56 = this.id;
    val x_57 = x_56.+(" processes an asynchrnous mtd!");
    scala.Predef.println(x_57);
    resetData_0 = false;
    positionVar_25 = 14
  }));
  data_26.update(14, (() => {
    val x_58 = resetData_0;
    val x_59 = x_58.asInstanceOf[scala.Any];
    bindingMut_8 = x_59;
    val x_60 = bindingMut_8;
    val x_61 = listValMut_9;
    x_61.reply(this, x_60);
    resetData_0 = ();
    positionVar_25 = 10
  }));
  data_26.update(15, (() => {
    val x_62 = listValMut_9;
    val x_63 = x_62.methodInfo;
    val x_64 = scala.`package`.Right.apply[scala.Nothing, scala.Int](1);
    val x_65 = x_63.==(x_64);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      positionVar_25 = 16
    else
      ()
  }));
  data_26.update(16, (() => {
    val x_67 = listValMut_9;
    val x_68 = x_67.methodInfo;
    val x_69 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_70 = x_68.==(x_69);
    if (x_70)
      positionVar_25 = 17
    else
      positionVar_25 = 25
  }));
  data_26.update(17, (() => positionVar_25 = 19));
  data_26.update(18, (() => positionVar_25 = 11));
  data_26.update(19, (() => positionVar_25 = 20));
  data_26.update(20, (() => {
    val x_71 = this.id;
    resetData_0 = x_71;
    val x_72 = resetData_0;
    val x_73 = x_72.asInstanceOf[scala.Long];
    bindingMut_7 = x_73;
    val x_74 = bindingMut_7;
    val x_75 = x_74.+(" processes blocking mtd!");
    resetData_0 = x_75;
    val x_76 = resetData_0;
    val x_77 = x_76.asInstanceOf[java.lang.String];
    bindingMut_6 = x_77;
    val x_78 = bindingMut_6;
    scala.Predef.println(x_78);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_79 = resetData_0;
    val x_80 = x_79.asInstanceOf[scala.Double];
    bindingMut_5 = x_80;
    positionVar_25 = 21
  }));
  data_26.update(21, (() => {
    val x_81 = bindingMut_5;
    val x_82 = x_81.+(1);
    resetData_0 = x_82;
    val x_83 = resetData_0;
    val x_84 = x_83.asInstanceOf[scala.Double];
    bindingMut_5 = x_84;
    positionVar_25 = 22;
    unblockFlag_24 = false
  }));
  data_26.update(22, (() => {
    val x_85 = bindingMut_5;
    val x_86 = x_85.<(1.0);
    if (x_86)
      positionVar_25 = 21
    else
      positionVar_25 = 23
  }));
  data_26.update(23, (() => {
    val x_87 = bindingMut_5;
    val x_88 = x_87.<(1.0);
    val x_89 = x_88.`unary_!`;
    if (x_89)
      {
        val x_90 = this.id;
        resetData_0 = x_90;
        val x_91 = resetData_0;
        val x_92 = x_91.asInstanceOf[scala.Long];
        bindingMut_4 = x_92;
        val x_93 = bindingMut_4;
        val x_94 = x_93.+(" finishes processing!");
        resetData_0 = x_94;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[java.lang.String];
        bindingMut_3 = x_96;
        val x_97 = bindingMut_3;
        scala.Predef.println(x_97);
        resetData_0 = ();
        resetData_0 = true;
        positionVar_25 = 24
      }
    else
      ()
  }));
  data_26.update(24, (() => {
    val x_98 = resetData_0;
    val x_99 = x_98.asInstanceOf[scala.Any];
    bindingMut_8 = x_99;
    val x_100 = bindingMut_8;
    val x_101 = listValMut_9;
    x_101.reply(this, x_100);
    resetData_0 = ();
    positionVar_25 = 18
  }));
  data_26.update(25, (() => {
    val x_102 = listValMut_9;
    val x_103 = x_102.methodInfo;
    val x_104 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_105 = x_103.==(x_104);
    val x_106 = x_105.`unary_!`;
    if (x_106)
      {
        val x_107 = listValMut_9;
        val x_108 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_107);
        val x_109 = this.addReceiveMessages(x_108);
        resetData_0 = x_109;
        positionVar_25 = 11
      }
    else
      ()
  }));
  data_26.update(26, (() => {
    val x_110 = iterMut_10;
    val x_111 = x_110.hasNext;
    val x_112 = x_111.`unary_!`;
    if (x_112)
      {
        val x_113 = resetData_1.remove(0);
        val x_117 = x_113.find(((x_114: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_115 = x_114._1;
          val x_116 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_115.==(x_116)
        }));
        val x_118 = x_117.get;
        val x_119 = x_118._2;
        positionVar_25 = x_119
      }
    else
      ()
  }));
  data_26.update(27, (() => positionVar_25 = 28));
  data_26.update(28, (() => {
    val x_120 = bindingMut_20;
    val x_121 = x_120.<(1.0);
    if (x_121)
      positionVar_25 = 4
    else
      positionVar_25 = 29
  }));
  data_26.update(29, (() => {
    val x_122 = bindingMut_20;
    val x_123 = x_122.<(1.0);
    val x_124 = x_123.`unary_!`;
    if (x_124)
      positionVar_25 = 30
    else
      ()
  }));
  data_26.update(30, (() => positionVar_25 = 1));
  data_26.update(31, (() => positionVar_25 = 32));
  data_26.update(32, (() => {
    positionVar_25 = 33;
    unblockFlag_24 = false
  }));
  data_26.update(33, (() => positionVar_25 = 32));
  data_26.update(34, (() => positionVar_25 = 35));
  data_26.update(35, (() => {
    val x_125 = bindingMut_20;
    val x_126 = x_125.<(1.0);
    if (x_126)
      positionVar_25 = 36
    else
      positionVar_25 = 38
  }));
  data_26.update(36, (() => {
    val x_127 = bindingMut_20;
    val x_128 = x_127.+(1);
    resetData_0 = x_128;
    val x_129 = resetData_0;
    val x_130 = x_129.asInstanceOf[scala.Double];
    bindingMut_20 = x_130;
    positionVar_25 = 37;
    unblockFlag_24 = false
  }));
  data_26.update(37, (() => {
    positionVar_25 = 6;
    val x_131 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_132 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_131, 34);
    val x_133 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_132);
    resetData_1.prepend(x_133)
  }));
  data_26.update(38, (() => {
    val x_134 = bindingMut_20;
    val x_135 = x_134.<(1.0);
    val x_136 = x_135.`unary_!`;
    if (x_136)
      positionVar_25 = 39
    else
      ()
  }));
  data_26.update(39, (() => {
    val x_137 = this.future;
    val x_138 = x_137.isCompleted;
    val x_139 = x_138.`unary_!`;
    if (x_139)
      positionVar_25 = 40
    else
      positionVar_25 = 41
  }));
  data_26.update(40, (() => {
    resetData_0 = 0.0;
    val x_140 = resetData_0;
    val x_141 = x_140.asInstanceOf[scala.Double];
    bindingMut_20 = x_141;
    positionVar_25 = 36
  }));
  data_26.update(41, (() => {
    val x_142 = this.future;
    val x_143 = x_142.isCompleted;
    val x_144 = x_143.`unary_!`;
    val x_145 = x_144.`unary_!`;
    if (x_145)
      positionVar_25 = 42
    else
      ()
  }));
  data_26.update(42, (() => {
    val x_146 = this.future;
    resetData_0 = x_146;
    val x_147 = resetData_0;
    val x_148 = x_147.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_14 = x_148;
    val x_149 = bindingMut_14;
    val x_150 = x_149.popValue;
    resetData_0 = x_150;
    val x_151 = resetData_0;
    val x_152 = x_151.asInstanceOf[scala.Option[scala.Boolean]];
    bindingMut_13 = x_152;
    val x_153 = bindingMut_13;
    val x_154 = x_153.get;
    resetData_0 = x_154;
    val x_155 = resetData_0;
    val x_156 = x_155.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_156;
    val x_157 = bindingMut_12;
    val x_158 = "Receive the reply for blockingMtd ".+(x_157);
    resetData_0 = x_158;
    val x_159 = resetData_0;
    val x_160 = x_159.asInstanceOf[java.lang.String];
    bindingMut_11 = x_160;
    val x_161 = bindingMut_11;
    scala.Predef.println(x_161);
    resetData_0 = ();
    positionVar_25 = 3
  }));
  data_26.update(43, (() => positionVar_25 = 44));
  data_26.update(44, (() => {
    val x_162 = bindingMut_20;
    val x_163 = x_162.<(1.0);
    if (x_163)
      positionVar_25 = 45
    else
      positionVar_25 = 47
  }));
  data_26.update(45, (() => {
    val x_164 = bindingMut_20;
    val x_165 = x_164.+(1);
    resetData_0 = x_165;
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[scala.Double];
    bindingMut_20 = x_167;
    positionVar_25 = 46;
    unblockFlag_24 = false
  }));
  data_26.update(46, (() => {
    positionVar_25 = 6;
    val x_168 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_169 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_168, 43);
    val x_170 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_169);
    resetData_1.prepend(x_170)
  }));
  data_26.update(47, (() => {
    val x_171 = bindingMut_20;
    val x_172 = x_171.<(1.0);
    val x_173 = x_172.`unary_!`;
    if (x_173)
      positionVar_25 = 48
    else
      ()
  }));
  data_26.update(48, (() => {
    val x_174 = this.future;
    val x_175 = x_174.isCompleted;
    val x_176 = x_175.`unary_!`;
    if (x_176)
      positionVar_25 = 49
    else
      positionVar_25 = 50
  }));
  data_26.update(49, (() => {
    resetData_0 = 0.0;
    val x_177 = resetData_0;
    val x_178 = x_177.asInstanceOf[scala.Double];
    bindingMut_20 = x_178;
    positionVar_25 = 45
  }));
  data_26.update(50, (() => {
    val x_179 = this.future;
    val x_180 = x_179.isCompleted;
    val x_181 = x_180.`unary_!`;
    val x_182 = x_181.`unary_!`;
    if (x_182)
      positionVar_25 = 51
    else
      ()
  }));
  data_26.update(51, (() => {
    val x_183 = this.future;
    resetData_0 = x_183;
    val x_184 = resetData_0;
    val x_185 = x_184.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_19 = x_185;
    val x_186 = bindingMut_19;
    val x_187 = x_186.popValue;
    resetData_0 = x_187;
    val x_188 = resetData_0;
    val x_189 = x_188.asInstanceOf[scala.Option[scala.Boolean]];
    bindingMut_18 = x_189;
    val x_190 = bindingMut_18;
    val x_191 = x_190.get;
    resetData_0 = x_191;
    val x_192 = resetData_0;
    val x_193 = x_192.asInstanceOf[scala.Boolean];
    bindingMut_17 = x_193;
    val x_194 = bindingMut_17;
    val x_195 = "Receive the reply for nonBlockingMtd ".+(x_194);
    resetData_0 = x_195;
    val x_196 = resetData_0;
    val x_197 = x_196.asInstanceOf[java.lang.String];
    bindingMut_16 = x_197;
    val x_198 = bindingMut_16;
    scala.Predef.println(x_198);
    resetData_0 = ();
    val receiver_199 = this.n;
    val x_200 = ((this): meta.runtime.Actor).id;
    val x_201 = receiver_199.id;
    val x_202 = scala.`package`.Right.apply[scala.Nothing, scala.Int](0);
    val x_203 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_204 = meta.runtime.RequestMessage.apply(x_200, x_201, false, x_202, x_203);
    val x_205 = x_204.sessionId;
    val x_206 = meta.runtime.Future.apply$default$2[scala.Boolean];
    val x_207 = meta.runtime.Future.apply[scala.Boolean](x_205, x_206);
    var v_208: meta.runtime.Future[scala.Boolean] = x_207;
    ((this): meta.runtime.Actor).sendMessage(x_204);
    val x_209 = x_204.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_209, ((response_210: meta.runtime.Message) => {
      val x_211 = v_208;
      val x_212 = response_210.asInstanceOf[meta.runtime.ResponseMessage];
      val x_213 = x_212.arg;
      val x_214 = x_213.asInstanceOf[scala.Boolean];
      x_211.setValue(x_214)
    }));
    val x_215 = v_208;
    resetData_0 = x_215;
    val x_216 = resetData_0;
    val x_217 = x_216.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_15 = x_217;
    val x_218 = bindingMut_15;
    this.`future_=`(x_218);
    resetData_0 = ();
    positionVar_25 = 52
  }));
  data_26.update(52, (() => {
    val x_219 = this.future;
    val x_220 = x_219.isCompleted;
    val x_221 = x_220.`unary_!`;
    if (x_221)
      positionVar_25 = 40
    else
      positionVar_25 = 53
  }));
  data_26.update(53, (() => {
    val x_222 = this.future;
    val x_223 = x_222.isCompleted;
    val x_224 = x_223.`unary_!`;
    val x_225 = x_224.`unary_!`;
    if (x_225)
      positionVar_25 = 42
    else
      ()
  }));
  data_26.update(54, (() => {
    val x_226 = bindingMut_22;
    squid.lib.`package`.IfThenElse[scala.Unit](x_226, {
      val receiver_227 = this.n;
      val x_228 = ((this): meta.runtime.Actor).id;
      val x_229 = receiver_227.id;
      val x_230 = scala.`package`.Right.apply[scala.Nothing, scala.Int](1);
      val x_231 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_232 = meta.runtime.RequestMessage.apply(x_228, x_229, false, x_230, x_231);
      val x_233 = x_232.sessionId;
      val x_234 = meta.runtime.Future.apply$default$2[scala.Boolean];
      val x_235 = meta.runtime.Future.apply[scala.Boolean](x_233, x_234);
      var v_236: meta.runtime.Future[scala.Boolean] = x_235;
      ((this): meta.runtime.Actor).sendMessage(x_232);
      val x_237 = x_232.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_237, ((response_238: meta.runtime.Message) => {
        val x_239 = v_236;
        val x_240 = response_238.asInstanceOf[meta.runtime.ResponseMessage];
        val x_241 = x_240.arg;
        val x_242 = x_241.asInstanceOf[scala.Boolean];
        x_239.setValue(x_242)
      }));
      val x_243 = v_236;
      resetData_0 = x_243;
      val x_244 = resetData_0;
      val x_245 = x_244.asInstanceOf[meta.runtime.Future[scala.Boolean]];
      bindingMut_21 = x_245;
      val x_246 = bindingMut_21;
      this.`future_=`(x_246);
      resetData_0 = ();
      positionVar_25 = 55
    }, ())
  }));
  data_26.update(55, (() => {
    val x_247 = this.future;
    val x_248 = x_247.isCompleted;
    val x_249 = x_248.`unary_!`;
    if (x_249)
      positionVar_25 = 49
    else
      positionVar_25 = 56
  }));
  data_26.update(56, (() => {
    val x_250 = this.future;
    val x_251 = x_250.isCompleted;
    val x_252 = x_251.`unary_!`;
    val x_253 = x_252.`unary_!`;
    if (x_253)
      positionVar_25 = 51
    else
      ()
  }));
  data_26.update(57, (() => positionVar_25 = 32));
  data_26
}).apply();
  

  def nonBlockingMtd(): Boolean = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" processes an asynchrnous mtd!");
  scala.Predef.println(x_1);
  false
}
  
  def wrapper_nonBlockingMtd(args: List[Any]): Boolean = {
    
          
          nonBlockingMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_24 = true
    while (unblockFlag_24 && (positionVar_25 < 58)) {
      commands_254(positionVar_25)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case Right(x) => {
        x match {
          case 1 => wrapper_nonBlockingMtd(args)
        }
      }
      case Left(x) => println("For staged implementation only")
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_24 = true

      if (reflectionIR_41 == -1){
        reflectionIR_41 = positionVar_25
        positionVar_25 = new_ir
      }

      while (positionVar_25 <= 25 && unblockFlag_24) {
        commands_254(positionVar_25)()
      }

      // reset instruction register when finishes processing
      if (positionVar_25 > 25) {
        positionVar_25 = reflectionIR_41
        reflectionIR_41 = -1
      }
      this
    }
    
override def SimClone(): AgentWithBlockingCall = {
  val newAgent = new AgentWithBlockingCall(n)
  newAgent.future = future
  newAgent
}

}
