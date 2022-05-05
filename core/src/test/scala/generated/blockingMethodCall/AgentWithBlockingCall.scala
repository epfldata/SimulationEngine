package generated.meta.test.blockingMethodCall

class AgentWithBlockingCall(val n: generated.meta.test.blockingMethodCall.AgentWithBlockingCall) extends meta.runtime.Actor {

   var future: meta.runtime.Future[Boolean] = null;
  private var  reflectionIR_43: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: java.lang.String = null
private var bindingMut_4: scala.Long = 0L
private var bindingMut_5: scala.Double = 0.0
private var bindingMut_6: java.lang.String = null
private var bindingMut_7: scala.Long = 0L
private var bindingMut_8: scala.Any = null
private var listValMut_9: meta.runtime.RequestMessage = null
@transient private var iterMut_10: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_11: java.lang.String = null
private var bindingMut_12: scala.Boolean = false
private var bindingMut_13: scala.Option[scala.Boolean] = null
private var bindingMut_14: meta.runtime.Future[scala.Boolean] = null
private var bindingMut_15: meta.runtime.Future[scala.Boolean] = null
private var bindingMut_16: java.lang.String = null
private var bindingMut_17: scala.Boolean = false
private var bindingMut_18: scala.Option[scala.Boolean] = null
private var bindingMut_19: meta.runtime.Future[scala.Boolean] = null
private var bindingMut_20: scala.Double = 0.0
private var bindingMut_21: meta.runtime.Future[scala.Boolean] = null
private var bindingMut_22: scala.Boolean = false
private var bindingMut_23: generated.meta.test.blockingMethodCall.AgentWithBlockingCall = null
private var unblockFlag_24: scala.Boolean = true
private var positionVar_25: scala.Int = 0
private 
  val commands_248 = (() => {
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
    val x_53 = x_52.==("blockingMtd");
    if (x_53)
      positionVar_25 = 9
    else
      positionVar_25 = 18
  }));
  data_26.update(9, (() => positionVar_25 = 12));
  data_26.update(10, (() => positionVar_25 = 11));
  data_26.update(11, (() => positionVar_25 = 7));
  data_26.update(12, (() => positionVar_25 = 13));
  data_26.update(13, (() => {
    val x_54 = this.id;
    resetData_0 = x_54;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.Long];
    bindingMut_7 = x_56;
    val x_57 = bindingMut_7;
    val x_58 = x_57.+(" processes blocking mtd!");
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[java.lang.String];
    bindingMut_6 = x_60;
    val x_61 = bindingMut_6;
    scala.Predef.println(x_61);
    resetData_0 = ();
    resetData_0 = 0.0;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Double];
    bindingMut_5 = x_63;
    positionVar_25 = 14
  }));
  data_26.update(14, (() => {
    val x_64 = bindingMut_5;
    val x_65 = x_64.+(1);
    resetData_0 = x_65;
    val x_66 = resetData_0;
    val x_67 = x_66.asInstanceOf[scala.Double];
    bindingMut_5 = x_67;
    positionVar_25 = 15;
    unblockFlag_24 = false
  }));
  data_26.update(15, (() => {
    val x_68 = bindingMut_5;
    val x_69 = x_68.<(1.0);
    if (x_69)
      positionVar_25 = 14
    else
      positionVar_25 = 16
  }));
  data_26.update(16, (() => {
    val x_70 = bindingMut_5;
    val x_71 = x_70.<(1.0);
    val x_72 = x_71.`unary_!`;
    if (x_72)
      {
        val x_73 = this.id;
        resetData_0 = x_73;
        val x_74 = resetData_0;
        val x_75 = x_74.asInstanceOf[scala.Long];
        bindingMut_4 = x_75;
        val x_76 = bindingMut_4;
        val x_77 = x_76.+(" finishes processing!");
        resetData_0 = x_77;
        val x_78 = resetData_0;
        val x_79 = x_78.asInstanceOf[java.lang.String];
        bindingMut_3 = x_79;
        val x_80 = bindingMut_3;
        scala.Predef.println(x_80);
        resetData_0 = ();
        resetData_0 = true;
        positionVar_25 = 17
      }
    else
      ()
  }));
  data_26.update(17, (() => {
    val x_81 = resetData_0;
    val x_82 = x_81.asInstanceOf[scala.Any];
    bindingMut_8 = x_82;
    val x_83 = bindingMut_8;
    val x_84 = listValMut_9;
    x_84.reply(this, x_83);
    resetData_0 = ();
    positionVar_25 = 10
  }));
  data_26.update(18, (() => {
    val x_85 = listValMut_9;
    val x_86 = x_85.methodInfo;
    val x_87 = x_86.==("blockingMtd");
    val x_88 = x_87.`unary_!`;
    if (x_88)
      positionVar_25 = 19
    else
      ()
  }));
  data_26.update(19, (() => {
    val x_89 = listValMut_9;
    val x_90 = x_89.methodInfo;
    val x_91 = x_90.==("nonBlockingMtd");
    if (x_91)
      positionVar_25 = 20
    else
      positionVar_25 = 25
  }));
  data_26.update(20, (() => {
    val x_92 = listValMut_9;
    this.handleNonblockingMessage(x_92);
    resetData_0 = ();
    positionVar_25 = 21
  }));
  data_26.update(21, (() => positionVar_25 = 11));
  data_26.update(22, (() => positionVar_25 = 23));
  data_26.update(23, (() => {
    val x_93 = this.id;
    val x_94 = x_93.+(" processes an asynchrnous mtd!");
    scala.Predef.println(x_94);
    resetData_0 = false;
    positionVar_25 = 24
  }));
  data_26.update(24, (() => {
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[scala.Any];
    bindingMut_8 = x_96;
    val x_97 = bindingMut_8;
    val x_98 = listValMut_9;
    x_98.reply(this, x_97);
    resetData_0 = ();
    positionVar_25 = 21
  }));
  data_26.update(25, (() => {
    val x_99 = listValMut_9;
    val x_100 = x_99.methodInfo;
    val x_101 = x_100.==("nonBlockingMtd");
    val x_102 = x_101.`unary_!`;
    if (x_102)
      {
        val x_103 = listValMut_9;
        val x_104 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_103);
        val x_105 = this.addReceiveMessages(x_104);
        resetData_0 = x_105;
        positionVar_25 = 11
      }
    else
      ()
  }));
  data_26.update(26, (() => {
    val x_106 = iterMut_10;
    val x_107 = x_106.hasNext;
    val x_108 = x_107.`unary_!`;
    if (x_108)
      {
        val x_109 = resetData_1.remove(0);
        val x_113 = x_109.find(((x_110: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_111 = x_110._1;
          val x_112 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_111.==(x_112)
        }));
        val x_114 = x_113.get;
        val x_115 = x_114._2;
        positionVar_25 = x_115
      }
    else
      ()
  }));
  data_26.update(27, (() => positionVar_25 = 28));
  data_26.update(28, (() => {
    val x_116 = bindingMut_20;
    val x_117 = x_116.<(1.0);
    if (x_117)
      positionVar_25 = 4
    else
      positionVar_25 = 29
  }));
  data_26.update(29, (() => {
    val x_118 = bindingMut_20;
    val x_119 = x_118.<(1.0);
    val x_120 = x_119.`unary_!`;
    if (x_120)
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
    val x_121 = bindingMut_20;
    val x_122 = x_121.<(1.0);
    if (x_122)
      positionVar_25 = 36
    else
      positionVar_25 = 38
  }));
  data_26.update(36, (() => {
    val x_123 = bindingMut_20;
    val x_124 = x_123.+(1);
    resetData_0 = x_124;
    val x_125 = resetData_0;
    val x_126 = x_125.asInstanceOf[scala.Double];
    bindingMut_20 = x_126;
    positionVar_25 = 37;
    unblockFlag_24 = false
  }));
  data_26.update(37, (() => {
    positionVar_25 = 6;
    val x_127 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_128 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_127, 34);
    val x_129 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_128);
    resetData_1.prepend(x_129)
  }));
  data_26.update(38, (() => {
    val x_130 = bindingMut_20;
    val x_131 = x_130.<(1.0);
    val x_132 = x_131.`unary_!`;
    if (x_132)
      positionVar_25 = 39
    else
      ()
  }));
  data_26.update(39, (() => {
    val x_133 = this.future;
    val x_134 = x_133.isCompleted;
    val x_135 = x_134.`unary_!`;
    if (x_135)
      positionVar_25 = 40
    else
      positionVar_25 = 41
  }));
  data_26.update(40, (() => {
    resetData_0 = 0.0;
    val x_136 = resetData_0;
    val x_137 = x_136.asInstanceOf[scala.Double];
    bindingMut_20 = x_137;
    positionVar_25 = 36
  }));
  data_26.update(41, (() => {
    val x_138 = this.future;
    val x_139 = x_138.isCompleted;
    val x_140 = x_139.`unary_!`;
    val x_141 = x_140.`unary_!`;
    if (x_141)
      positionVar_25 = 42
    else
      ()
  }));
  data_26.update(42, (() => {
    val x_142 = this.future;
    resetData_0 = x_142;
    val x_143 = resetData_0;
    val x_144 = x_143.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_14 = x_144;
    val x_145 = bindingMut_14;
    val x_146 = x_145.popValue;
    resetData_0 = x_146;
    val x_147 = resetData_0;
    val x_148 = x_147.asInstanceOf[scala.Option[scala.Boolean]];
    bindingMut_13 = x_148;
    val x_149 = bindingMut_13;
    val x_150 = x_149.get;
    resetData_0 = x_150;
    val x_151 = resetData_0;
    val x_152 = x_151.asInstanceOf[scala.Boolean];
    bindingMut_12 = x_152;
    val x_153 = bindingMut_12;
    val x_154 = "Receive the reply for blockingMtd ".+(x_153);
    resetData_0 = x_154;
    val x_155 = resetData_0;
    val x_156 = x_155.asInstanceOf[java.lang.String];
    bindingMut_11 = x_156;
    val x_157 = bindingMut_11;
    scala.Predef.println(x_157);
    resetData_0 = ();
    positionVar_25 = 3
  }));
  data_26.update(43, (() => positionVar_25 = 44));
  data_26.update(44, (() => {
    val x_158 = bindingMut_20;
    val x_159 = x_158.<(1.0);
    if (x_159)
      positionVar_25 = 45
    else
      positionVar_25 = 47
  }));
  data_26.update(45, (() => {
    val x_160 = bindingMut_20;
    val x_161 = x_160.+(1);
    resetData_0 = x_161;
    val x_162 = resetData_0;
    val x_163 = x_162.asInstanceOf[scala.Double];
    bindingMut_20 = x_163;
    positionVar_25 = 46;
    unblockFlag_24 = false
  }));
  data_26.update(46, (() => {
    positionVar_25 = 6;
    val x_164 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_165 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_164, 43);
    val x_166 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_165);
    resetData_1.prepend(x_166)
  }));
  data_26.update(47, (() => {
    val x_167 = bindingMut_20;
    val x_168 = x_167.<(1.0);
    val x_169 = x_168.`unary_!`;
    if (x_169)
      positionVar_25 = 48
    else
      ()
  }));
  data_26.update(48, (() => {
    val x_170 = this.future;
    val x_171 = x_170.isCompleted;
    val x_172 = x_171.`unary_!`;
    if (x_172)
      positionVar_25 = 49
    else
      positionVar_25 = 50
  }));
  data_26.update(49, (() => {
    resetData_0 = 0.0;
    val x_173 = resetData_0;
    val x_174 = x_173.asInstanceOf[scala.Double];
    bindingMut_20 = x_174;
    positionVar_25 = 45
  }));
  data_26.update(50, (() => {
    val x_175 = this.future;
    val x_176 = x_175.isCompleted;
    val x_177 = x_176.`unary_!`;
    val x_178 = x_177.`unary_!`;
    if (x_178)
      positionVar_25 = 51
    else
      ()
  }));
  data_26.update(51, (() => {
    val x_179 = this.future;
    resetData_0 = x_179;
    val x_180 = resetData_0;
    val x_181 = x_180.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_19 = x_181;
    val x_182 = bindingMut_19;
    val x_183 = x_182.popValue;
    resetData_0 = x_183;
    val x_184 = resetData_0;
    val x_185 = x_184.asInstanceOf[scala.Option[scala.Boolean]];
    bindingMut_18 = x_185;
    val x_186 = bindingMut_18;
    val x_187 = x_186.get;
    resetData_0 = x_187;
    val x_188 = resetData_0;
    val x_189 = x_188.asInstanceOf[scala.Boolean];
    bindingMut_17 = x_189;
    val x_190 = bindingMut_17;
    val x_191 = "Receive the reply for nonBlockingMtd ".+(x_190);
    resetData_0 = x_191;
    val x_192 = resetData_0;
    val x_193 = x_192.asInstanceOf[java.lang.String];
    bindingMut_16 = x_193;
    val x_194 = bindingMut_16;
    scala.Predef.println(x_194);
    resetData_0 = ();
    val receiver_195 = this.n;
    val x_196 = ((this): meta.runtime.Actor).id;
    val x_197 = receiver_195.id;
    val x_198 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_199 = meta.runtime.RequestMessage.apply(x_196, x_197, false, "blockingMtd", x_198);
    val x_200 = x_199.sessionId;
    val x_201 = meta.runtime.Future.apply$default$2[scala.Boolean];
    val x_202 = meta.runtime.Future.apply[scala.Boolean](x_200, x_201);
    var v_203: meta.runtime.Future[scala.Boolean] = x_202;
    ((this): meta.runtime.Actor).sendMessage(x_199);
    val x_204 = x_199.sessionId;
    ((this): meta.runtime.Actor).setMessageResponseHandler(x_204, ((response_205: meta.runtime.Message) => {
      val x_206 = v_203;
      val x_207 = response_205.asInstanceOf[meta.runtime.ResponseMessage];
      val x_208 = x_207.arg;
      val x_209 = x_208.asInstanceOf[scala.Boolean];
      x_206.setValue(x_209)
    }));
    val x_210 = v_203;
    resetData_0 = x_210;
    val x_211 = resetData_0;
    val x_212 = x_211.asInstanceOf[meta.runtime.Future[scala.Boolean]];
    bindingMut_15 = x_212;
    val x_213 = bindingMut_15;
    this.`future_=`(x_213);
    resetData_0 = ();
    positionVar_25 = 52
  }));
  data_26.update(52, (() => {
    val x_214 = this.future;
    val x_215 = x_214.isCompleted;
    val x_216 = x_215.`unary_!`;
    if (x_216)
      positionVar_25 = 40
    else
      positionVar_25 = 53
  }));
  data_26.update(53, (() => {
    val x_217 = this.future;
    val x_218 = x_217.isCompleted;
    val x_219 = x_218.`unary_!`;
    val x_220 = x_219.`unary_!`;
    if (x_220)
      positionVar_25 = 42
    else
      ()
  }));
  data_26.update(54, (() => {
    val x_221 = bindingMut_22;
    squid.lib.`package`.IfThenElse[scala.Unit](x_221, {
      val receiver_222 = this.n;
      val x_223 = ((this): meta.runtime.Actor).id;
      val x_224 = receiver_222.id;
      val x_225 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
      val x_226 = meta.runtime.RequestMessage.apply(x_223, x_224, false, "nonBlockingMtd", x_225);
      val x_227 = x_226.sessionId;
      val x_228 = meta.runtime.Future.apply$default$2[scala.Boolean];
      val x_229 = meta.runtime.Future.apply[scala.Boolean](x_227, x_228);
      var v_230: meta.runtime.Future[scala.Boolean] = x_229;
      ((this): meta.runtime.Actor).sendMessage(x_226);
      val x_231 = x_226.sessionId;
      ((this): meta.runtime.Actor).setMessageResponseHandler(x_231, ((response_232: meta.runtime.Message) => {
        val x_233 = v_230;
        val x_234 = response_232.asInstanceOf[meta.runtime.ResponseMessage];
        val x_235 = x_234.arg;
        val x_236 = x_235.asInstanceOf[scala.Boolean];
        x_233.setValue(x_236)
      }));
      val x_237 = v_230;
      resetData_0 = x_237;
      val x_238 = resetData_0;
      val x_239 = x_238.asInstanceOf[meta.runtime.Future[scala.Boolean]];
      bindingMut_21 = x_239;
      val x_240 = bindingMut_21;
      this.`future_=`(x_240);
      resetData_0 = ();
      positionVar_25 = 55
    }, ())
  }));
  data_26.update(55, (() => {
    val x_241 = this.future;
    val x_242 = x_241.isCompleted;
    val x_243 = x_242.`unary_!`;
    if (x_243)
      positionVar_25 = 49
    else
      positionVar_25 = 56
  }));
  data_26.update(56, (() => {
    val x_244 = this.future;
    val x_245 = x_244.isCompleted;
    val x_246 = x_245.`unary_!`;
    val x_247 = x_246.`unary_!`;
    if (x_247)
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
  
  private def wrapper_nonBlockingMtd(args: List[Any]): Boolean = {
    
          
          nonBlockingMtd()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_24 = true
    while (unblockFlag_24 && (positionVar_25 < 58)) {
      commands_248(positionVar_25)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "nonBlockingMtd" => wrapper_nonBlockingMtd(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 5): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_24 = true

      if (reflectionIR_43 == -1){
        reflectionIR_43 = positionVar_25
        positionVar_25 = new_ir
      }

      while (positionVar_25 <= 25 && unblockFlag_24) {
        commands_248(positionVar_25)()
      }

      // reset instruction register when finishes processing
      if (positionVar_25 > 25) {
        positionVar_25 = reflectionIR_43
        reflectionIR_43 = -1
      }
      this
    }
    
override def SimClone(): AgentWithBlockingCall = {
  val newAgent = new AgentWithBlockingCall(n)
  newAgent.future = future
  newAgent
}

override def SimReset(): Unit = {
  positionVar_25 = 0
  val newAgent = new AgentWithBlockingCall(n)
  future = newAgent.future
}

}
