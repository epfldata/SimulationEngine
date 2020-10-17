package generated.meta.example.helloWorld

class OutgoingPerson (var name: String) extends meta.deep.runtime.Actor with meta.example.helloWorld.Person {
  var outgoing: Boolean = true
  var rand: util.Random = new scala.util.Random()
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Any = null;
  private var listValMut_4: meta.deep.runtime.RequestMessage = null;
  private var iterMut_5: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Boolean = false;
  private var bindingMut_9: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var bindingMut_10: generated.meta.example.helloWorld.TimidPerson = null;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: generated.meta.example.helloWorld.TimidPerson = null;
  private var bindingMut_14: scala.Boolean = false;
  private var bindingMut_15: meta.example.helloWorld.Person = null;
  private var bindingMut_16: scala.Int = 0;
  private var bindingMut_17: scala.Int = 0;
  private var bindingMut_18: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var bindingMut_19: scala.util.Random = null;
  private var bindingMut_20: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var bindingMut_21: meta.example.helloWorld.Person = null;
  private var bindingMut_22: scala.Int = 0;
  private var bindingMut_23: scala.Int = 0;
  private var bindingMut_24: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var bindingMut_25: scala.util.Random = null;
  private var bindingMut_26: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var positionVar_28: scala.Int = 0;
  
  val commands_244 = (() => {
  val data_29 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_29.update(0, (() => {
    positionVar_28 = 1;
    val x_30 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_31 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_30, 18);
    val x_32 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_31);
    resetData_1.prepend(x_32)
  }));
  data_29.update(1, (() => if (true)
    positionVar_28 = 2
  else
    positionVar_28 = 26));
  data_29.update(2, (() => {
    val x_33 = this.friendList;
    resetData_0 = x_33;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_26 = x_35;
    val x_36 = this.rand;
    resetData_0 = x_36;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.util.Random];
    bindingMut_25 = x_38;
    val x_39 = this.friendList;
    resetData_0 = x_39;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_24 = x_41;
    val x_42 = bindingMut_24;
    val x_43 = x_42.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_44 = x_43.length;
    resetData_0 = x_44;
    val x_45 = resetData_0;
    val x_46 = x_45.asInstanceOf[scala.Int];
    bindingMut_23 = x_46;
    val x_47 = bindingMut_23;
    val x_48 = x_47.asInstanceOf[scala.Int];
    val x_49 = bindingMut_25;
    val x_50 = x_49.asInstanceOf[scala.util.Random];
    val x_51 = x_50.nextInt(x_48);
    resetData_0 = x_51;
    val x_52 = resetData_0;
    val x_53 = x_52.asInstanceOf[scala.Int];
    bindingMut_22 = x_53;
    val x_54 = bindingMut_22;
    val x_55 = x_54.asInstanceOf[scala.Int];
    val x_56 = bindingMut_26;
    val x_57 = x_56.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_58 = x_57(x_55);
    resetData_0 = x_58;
    val x_59 = resetData_0;
    val x_60 = x_59.asInstanceOf[meta.example.helloWorld.Person];
    bindingMut_21 = x_60;
    val x_61 = this.friendList;
    resetData_0 = x_61;
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_20 = x_63;
    val x_64 = this.rand;
    resetData_0 = x_64;
    val x_65 = resetData_0;
    val x_66 = x_65.asInstanceOf[scala.util.Random];
    bindingMut_19 = x_66;
    val x_67 = this.friendList;
    resetData_0 = x_67;
    val x_68 = resetData_0;
    val x_69 = x_68.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_18 = x_69;
    val x_70 = bindingMut_18;
    val x_71 = x_70.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_72 = x_71.length;
    resetData_0 = x_72;
    val x_73 = resetData_0;
    val x_74 = x_73.asInstanceOf[scala.Int];
    bindingMut_17 = x_74;
    val x_75 = bindingMut_17;
    val x_76 = x_75.asInstanceOf[scala.Int];
    val x_77 = bindingMut_19;
    val x_78 = x_77.asInstanceOf[scala.util.Random];
    val x_79 = x_78.nextInt(x_76);
    resetData_0 = x_79;
    val x_80 = resetData_0;
    val x_81 = x_80.asInstanceOf[scala.Int];
    bindingMut_16 = x_81;
    val x_82 = bindingMut_16;
    val x_83 = x_82.asInstanceOf[scala.Int];
    val x_84 = bindingMut_20;
    val x_85 = x_84.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_86 = x_85(x_83);
    resetData_0 = x_86;
    val x_87 = resetData_0;
    val x_88 = x_87.asInstanceOf[meta.example.helloWorld.Person];
    bindingMut_15 = x_88;
    val x_89 = bindingMut_21;
    val x_90 = x_89.asInstanceOf[meta.example.helloWorld.Person];
    val x_91 = x_90.isInstanceOf[generated.meta.example.helloWorld.TimidPerson];
    resetData_0 = x_91;
    val x_92 = resetData_0;
    val x_93 = x_92.asInstanceOf[scala.Boolean];
    bindingMut_14 = x_93;
    positionVar_28 = 3
  }));
  data_29.update(3, (() => {
    val x_94 = bindingMut_14;
    val x_95 = x_94.asInstanceOf[scala.Boolean];
    val x_96 = x_95.`unary_!`;
    if (x_96)
      positionVar_28 = 4
    else
      positionVar_28 = 22
  }));
  data_29.update(4, (() => {
    val x_97 = scala.util.Random.nextString(5);
    resetData_0 = x_97;
    val x_98 = resetData_0;
    val x_99 = x_98.asInstanceOf[java.lang.String];
    bindingMut_11 = x_99;
    val x_100 = bindingMut_11;
    val x_101 = x_100.asInstanceOf[java.lang.String];
    val x_102 = new generated.meta.example.helloWorld.TimidPerson(x_101);
    meta.deep.runtime.Actor.newActors.append(x_102)
    resetData_0 = x_102;
    val x_103 = resetData_0;
    val x_104 = x_103.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
    bindingMut_10 = x_104;
    val x_105 = this.friendList;
    resetData_0 = x_105;
    val x_106 = resetData_0;
    val x_107 = x_106.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_9 = x_107;
    val x_108 = bindingMut_9;
    val x_109 = x_108.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_110 = bindingMut_10;
    val x_111 = x_110.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
    x_109.append(x_111);
    resetData_0 = ();
    val x_112 = bindingMut_15;
    val x_113 = x_112.asInstanceOf[meta.example.helloWorld.Person];
    val x_114 = bindingMut_21;
    val x_115 = x_114.asInstanceOf[meta.example.helloWorld.Person];
    val x_116 = x_115.!=(x_113);
    resetData_0 = x_116;
    val x_117 = resetData_0;
    val x_118 = x_117.asInstanceOf[scala.Boolean];
    bindingMut_8 = x_118;
    positionVar_28 = 5
  }));
  data_29.update(5, (() => {
    val x_119 = bindingMut_8;
    val x_120 = x_119.asInstanceOf[scala.Boolean];
    val x_121 = x_120.`unary_!`;
    if (x_121)
      positionVar_28 = 6
    else
      positionVar_28 = 21
  }));
  data_29.update(6, (() => {
    val x_122 = 2.toDouble;
    resetData_0 = x_122;
    val x_123 = resetData_0;
    val x_124 = x_123.asInstanceOf[scala.Double];
    bindingMut_7 = x_124;
    resetData_0 = 0.0;
    val x_125 = resetData_0;
    val x_126 = x_125.asInstanceOf[scala.Double];
    bindingMut_6 = x_126;
    positionVar_28 = 7
  }));
  data_29.update(7, (() => {
    val x_127 = meta.deep.runtime.Actor.proceedLabel;
    val x_128 = x_127("turn");
    val x_129 = bindingMut_6;
    val x_130 = x_129.asInstanceOf[scala.Double];
    val x_131 = x_130.+(x_128);
    resetData_0 = x_131;
    val x_132 = resetData_0;
    val x_133 = x_132.asInstanceOf[scala.Double];
    bindingMut_6 = x_133;
    val x_134 = meta.deep.runtime.Actor.labelVals("turn");
    val x_135 = bindingMut_6;
    val x_136 = x_135.asInstanceOf[scala.Double];
    val x_137 = bindingMut_7;
    val x_138 = x_137.asInstanceOf[scala.Double];
    val x_139 = x_138.-(x_136);
    x_134.append(x_139);
    resetData_0 = ();
    positionVar_28 = 8;
    val x_140 = currentTurn;
    val x_141 = x_140.+(1);
    currentTurn = x_141
  }));
  data_29.update(8, (() => {
    val x_142 = bindingMut_6;
    val x_143 = x_142.asInstanceOf[scala.Double];
    val x_144 = bindingMut_7;
    val x_145 = x_144.asInstanceOf[scala.Double];
    val x_146 = x_143.<(x_145);
    if (x_146)
      positionVar_28 = 7
    else
      positionVar_28 = 9
  }));
  data_29.update(9, (() => {
    val x_147 = bindingMut_6;
    val x_148 = x_147.asInstanceOf[scala.Double];
    val x_149 = bindingMut_7;
    val x_150 = x_149.asInstanceOf[scala.Double];
    val x_151 = x_148.<(x_150);
    val x_152 = x_151.`unary_!`;
    if (x_152)
      {
        val x_153 = this.popRequestMessages;
        val x_154 = x_153.iterator;
        iterMut_5 = x_154;
        positionVar_28 = 10
      }
    else
      ()
  }));
  data_29.update(10, (() => {
    val x_155 = iterMut_5;
    val x_156 = x_155.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_157 = x_156.hasNext;
    if (x_157)
      {
        val x_158 = iterMut_5;
        val x_159 = x_158.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_160 = x_159.next();
        listValMut_4 = x_160;
        positionVar_28 = 11
      }
    else
      positionVar_28 = 14
  }));
  data_29.update(11, (() => {
    val x_161 = listValMut_4;
    val x_162 = x_161.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_163 = x_162.methodId;
    val x_164 = x_163.==(2);
    val x_165 = x_164.`unary_!`;
    if (x_165)
      {
        val x_166 = listValMut_4;
        val x_167 = x_166.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_168 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_167);
        val x_169 = this.addReceiveMessages(x_168);
        resetData_0 = x_169;
        positionVar_28 = 12
      }
    else
      positionVar_28 = 13
  }));
  data_29.update(12, (() => positionVar_28 = 10));
  data_29.update(13, (() => {
    val x_170 = listValMut_4;
    val x_171 = x_170.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_172 = x_171.methodId;
    val x_173 = x_172.==(2);
    if (x_173)
      positionVar_28 = 1
    else
      ();
    val x_174 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_175 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_174, 17);
    val x_176 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_175);
    resetData_1.prepend(x_176)
  }));
  data_29.update(14, (() => {
    val x_177 = iterMut_5;
    val x_178 = x_177.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_179 = x_178.hasNext;
    val x_180 = x_179.`unary_!`;
    if (x_180)
      positionVar_28 = 15
    else
      ()
  }));
  data_29.update(15, (() => if (true)
    positionVar_28 = 2
  else
    positionVar_28 = 16));
  data_29.update(16, (() => {
    val x_181 = true.`unary_!`;
    if (x_181)
      {
        val x_182 = resetData_1.remove(0);
        val x_186 = x_182.find(((x_183: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_184 = x_183._1;
          val x_185 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_184.==(x_185)
        }));
        val x_187 = x_186.get;
        val x_188 = x_187._2;
        positionVar_28 = x_188
      }
    else
      ()
  }));
  data_29.update(17, (() => {
    val x_189 = resetData_0;
    val x_190 = x_189.asInstanceOf[scala.Any];
    bindingMut_3 = x_190;
    val x_191 = bindingMut_3;
    val x_192 = x_191.asInstanceOf[scala.Any];
    val x_193 = listValMut_4;
    val x_194 = x_193.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_194.reply(this, x_192);
    resetData_0 = ();
    positionVar_28 = 12
  }));
  data_29.update(18, (() => positionVar_28 = 19));
  data_29.update(19, (() => {
    positionVar_28 = 20;
    val x_195 = currentTurn;
    val x_196 = x_195.+(1);
    currentTurn = x_196
  }));
  data_29.update(20, (() => positionVar_28 = 19));
  data_29.update(21, (() => {
    val x_197 = bindingMut_8;
    val x_198 = x_197.asInstanceOf[scala.Boolean];
    if (x_198)
      {
        val x_199 = bindingMut_15;
        val x_200 = x_199.asInstanceOf[meta.example.helloWorld.Person];
        val x_201 = bindingMut_21;
        val x_202 = x_201.asInstanceOf[meta.example.helloWorld.Person];
        this.introduce(x_202, x_200);
        resetData_0 = ();
        positionVar_28 = 6
      }
    else
      ()
  }));
  data_29.update(22, (() => {
    val x_203 = bindingMut_14;
    val x_204 = x_203.asInstanceOf[scala.Boolean];
    if (x_204)
      {
        val x_205 = bindingMut_21;
        val x_206 = x_205.asInstanceOf[meta.example.helloWorld.Person];
        val x_207 = x_206.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
        resetData_0 = x_207;
        val x_208 = resetData_0;
        val x_209 = x_208.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
        bindingMut_13 = x_209;
        val x_210 = bindingMut_21;
        val x_211 = x_210.asInstanceOf[meta.example.helloWorld.Person];
        val x_212 = x_211.name;
        resetData_0 = x_212;
        val x_213 = resetData_0;
        val x_214 = x_213.asInstanceOf[java.lang.String];
        bindingMut_12 = x_214;
        val x_215 = ((this): meta.deep.runtime.Actor).id;
        val x_217 = {
          val x_216 = bindingMut_13;
          x_216.asInstanceOf[generated.meta.example.helloWorld.TimidPerson]
        };
        val x_218 = x_217.id;
        val x_219 = bindingMut_12;
        val x_220 = x_219.asInstanceOf[java.lang.String];
        val x_221 = scala.collection.immutable.Nil.::[scala.Any](x_220);
        val x_222 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_221);
        val x_223 = meta.deep.runtime.RequestMessage.apply(x_215, x_218, 0, x_222);
        ((this): meta.deep.runtime.Actor).sendMessage(x_223);
        val x_224 = x_223.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_224, ((response_225: meta.deep.runtime.Message) => {
          val x_226 = response_225.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_226
        }));
        resetData_0 = null;
        positionVar_28 = 23
      }
    else
      ()
  }));
  data_29.update(23, (() => {
    positionVar_28 = 24;
    val x_227 = currentTurn;
    val x_228 = x_227.+(1);
    currentTurn = x_228
  }));
  data_29.update(24, (() => {
    val x_229 = resetData_2;
    val x_230 = x_229.==(null);
    if (x_230)
      {
        val x_231 = meta.deep.runtime.Actor.labelVals("turn");
        x_231.append(1.0);
        positionVar_28 = 23
      }
    else
      positionVar_28 = 25
  }));
  data_29.update(25, (() => {
    val x_232 = resetData_2;
    val x_233 = x_232.!=(null);
    if (x_233)
      {
        val x_234 = resetData_2;
        val x_235 = x_234.arg;
        resetData_0 = x_235;
        resetData_2 = null;
        positionVar_28 = 4
      }
    else
      ()
  }));
  data_29.update(26, (() => {
    val x_236 = true.`unary_!`;
    if (x_236)
      {
        val x_237 = resetData_1.remove(0);
        val x_241 = x_237.find(((x_238: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_239 = x_238._1;
          val x_240 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_239.==(x_240)
        }));
        val x_242 = x_241.get;
        val x_243 = x_242._2;
        positionVar_28 = x_243
      }
    else
      ()
  }));
  data_29
}).apply();
  
  override def run_until(until_245: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_246 = currentTurn;
      val x_247 = x_246.<=(until_245);
      x_247.&&({
        val x_248 = positionVar_28;
        val x_249 = commands_244.length;
        x_248.<(x_249)
      })
    }) 
      {
        val x_250 = positionVar_28;
        val x_251 = commands_244.apply(x_250);
        x_251.apply()
      }
    ;
    this
  }
}
