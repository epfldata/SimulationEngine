package generated.example.muddy_children

class MuddyChild (val isMuddy: Boolean) extends meta.deep.runtime.Actor with example.muddy_children.Child {
  var env: List[generated.example.muddy_children.MuddyChild] = scala.collection.immutable.Nil
  var counter: Int = 0
  var shouldMove: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Boolean = false;
  private var bindingMut_4: scala.Boolean = false;
  private var bindingMut_5: scala.Boolean = false;
  private var bindingMut_6: scala.Int = 0;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: scala.collection.immutable.List[generated.example.muddy_children.MuddyChild] = null;
  private var bindingMut_9: scala.Int = 0;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Boolean = false;
  private var bindingMut_14: scala.Double = 0.0;
  private var bindingMut_15: scala.Double = 0.0;
  private var bindingMut_16: scala.Any = null;
  private var listValMut_17: meta.deep.runtime.RequestMessage = null;
  private var iterMut_18: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_20: scala.Int = 0;
  
  val commands_227 = (() => {
  val data_21 = new scala.Array[scala.Function0[scala.Unit]](33);
  data_21.update(0, (() => {
    positionVar_20 = 1;
    val x_22 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_23 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_22, 16);
    val x_24 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_23);
    resetData_1.prepend(x_24)
  }));
  data_21.update(1, (() => if (true)
    positionVar_20 = 2
  else
    positionVar_20 = 32));
  data_21.update(2, (() => {
    val x_25 = this.popRequestMessages;
    val x_26 = x_25.iterator;
    iterMut_18 = x_26;
    positionVar_20 = 3
  }));
  data_21.update(3, (() => {
    val x_27 = iterMut_18;
    val x_28 = x_27.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_29 = x_28.hasNext;
    if (x_29)
      {
        val x_30 = iterMut_18;
        val x_31 = x_30.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_32 = x_31.next();
        listValMut_17 = x_32;
        positionVar_20 = 4
      }
    else
      positionVar_20 = 28
  }));
  data_21.update(4, (() => {
    val x_33 = listValMut_17;
    val x_34 = x_33.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_35 = x_34.methodId;
    val x_36 = x_35.==(0);
    val x_37 = x_36.`unary_!`;
    if (x_37)
      positionVar_20 = 5
    else
      positionVar_20 = 21
  }));
  data_21.update(5, (() => {
    val x_38 = listValMut_17;
    val x_39 = x_38.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_40 = x_39.methodId;
    val x_41 = x_40.==(1);
    val x_42 = x_41.`unary_!`;
    if (x_42)
      positionVar_20 = 6
    else
      positionVar_20 = 9
  }));
  data_21.update(6, (() => {
    val x_43 = listValMut_17;
    val x_44 = x_43.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_45 = x_44.methodId;
    val x_46 = x_45.==(2);
    val x_47 = x_46.`unary_!`;
    if (x_47)
      {
        val x_48 = listValMut_17;
        val x_49 = x_48.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_50 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_49);
        val x_51 = this.addReceiveMessages(x_50);
        resetData_0 = x_51;
        positionVar_20 = 7
      }
    else
      positionVar_20 = 8
  }));
  data_21.update(7, (() => positionVar_20 = 3));
  data_21.update(8, (() => {
    val x_52 = listValMut_17;
    val x_53 = x_52.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_54 = x_53.methodId;
    val x_55 = x_54.==(2);
    if (x_55)
      positionVar_20 = 1
    else
      ();
    val x_56 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_57 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_56, 15);
    val x_58 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_57);
    resetData_1.prepend(x_58)
  }));
  data_21.update(9, (() => {
    val x_59 = listValMut_17;
    val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_61 = x_60.methodId;
    val x_62 = x_61.==(1);
    if (x_62)
      positionVar_20 = 10
    else
      ();
    val x_63 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_64 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_63, 19);
    val x_65 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_64);
    resetData_1.prepend(x_65)
  }));
  data_21.update(10, (() => {
    val x_66 = this.shouldMove;
    resetData_0 = x_66;
    val x_67 = resetData_0;
    val x_68 = x_67.asInstanceOf[scala.Boolean];
    bindingMut_13 = x_68;
    positionVar_20 = 11
  }));
  data_21.update(11, (() => {
    val x_69 = bindingMut_13;
    val x_70 = x_69.asInstanceOf[scala.Boolean];
    val x_71 = x_70.`unary_!`;
    if (x_71)
      {
        val x_72 = resetData_1.remove(0);
        val x_76 = x_72.find(((x_73: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_74 = x_73._1;
          val x_75 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_74.==(x_75)
        }));
        val x_77 = x_76.get;
        val x_78 = x_77._2;
        positionVar_20 = x_78
      }
    else
      positionVar_20 = 20
  }));
  data_21.update(12, (() => positionVar_20 = 13));
  data_21.update(13, (() => if (true)
    positionVar_20 = 2
  else
    positionVar_20 = 14));
  data_21.update(14, (() => {
    val x_79 = true.`unary_!`;
    if (x_79)
      {
        val x_80 = resetData_1.remove(0);
        val x_84 = x_80.find(((x_81: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_82 = x_81._1;
          val x_83 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_82.==(x_83)
        }));
        val x_85 = x_84.get;
        val x_86 = x_85._2;
        positionVar_20 = x_86
      }
    else
      ()
  }));
  data_21.update(15, (() => {
    val x_87 = resetData_0;
    val x_88 = x_87.asInstanceOf[scala.Any];
    bindingMut_16 = x_88;
    val x_89 = bindingMut_16;
    val x_90 = x_89.asInstanceOf[scala.Any];
    val x_91 = listValMut_17;
    val x_92 = x_91.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_92.reply(this, x_90);
    resetData_0 = ();
    positionVar_20 = 7
  }));
  data_21.update(16, (() => positionVar_20 = 17));
  data_21.update(17, (() => {
    positionVar_20 = 18;
    val x_93 = currentTurn;
    val x_94 = x_93.+(1);
    currentTurn = x_94
  }));
  data_21.update(18, (() => positionVar_20 = 17));
  data_21.update(19, (() => {
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[scala.Any];
    bindingMut_16 = x_96;
    val x_97 = bindingMut_16;
    val x_98 = x_97.asInstanceOf[scala.Any];
    val x_99 = listValMut_17;
    val x_100 = x_99.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_100.reply(this, x_98);
    resetData_0 = ();
    positionVar_20 = 7
  }));
  data_21.update(20, (() => {
    val x_101 = bindingMut_13;
    val x_102 = x_101.asInstanceOf[scala.Boolean];
    if (x_102)
      {
        this.`isForward_=`(true);
        resetData_0 = ();
        this.`shouldMove_=`(false);
        resetData_0 = ();
        val x_103 = this.toString();
        resetData_0 = x_103;
        val x_104 = resetData_0;
        val x_105 = x_104.asInstanceOf[java.lang.String];
        bindingMut_12 = x_105;
        val x_106 = bindingMut_12;
        val x_107 = x_106.asInstanceOf[java.lang.String];
        val x_108 = "Step forward! ".+(x_107);
        resetData_0 = x_108;
        val x_109 = resetData_0;
        val x_110 = x_109.asInstanceOf[java.lang.String];
        bindingMut_11 = x_110;
        val x_111 = bindingMut_11;
        val x_112 = x_111.asInstanceOf[java.lang.String];
        scala.Predef.println(x_112);
        resetData_0 = ();
        val x_113 = resetData_1.remove(0);
        val x_117 = x_113.find(((x_114: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_115 = x_114._1;
          val x_116 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_115.==(x_116)
        }));
        val x_118 = x_117.get;
        val x_119 = x_118._2;
        positionVar_20 = x_119
      }
    else
      ()
  }));
  data_21.update(21, (() => {
    val x_120 = listValMut_17;
    val x_121 = x_120.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_122 = x_121.methodId;
    val x_123 = x_122.==(0);
    if (x_123)
      {
        val x_124 = this.counter;
        resetData_0 = x_124;
        val x_125 = resetData_0;
        val x_126 = x_125.asInstanceOf[scala.Int];
        bindingMut_10 = x_126;
        val x_127 = bindingMut_10;
        val x_128 = x_127.asInstanceOf[scala.Int];
        val x_129 = x_128.+(1);
        resetData_0 = x_129;
        val x_130 = resetData_0;
        val x_131 = x_130.asInstanceOf[scala.Int];
        bindingMut_9 = x_131;
        val x_132 = bindingMut_9;
        val x_133 = x_132.asInstanceOf[scala.Int];
        this.`counter_=`(x_133);
        resetData_0 = ();
        val x_134 = this.env;
        resetData_0 = x_134;
        val x_135 = resetData_0;
        val x_136 = x_135.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
        bindingMut_8 = x_136;
        val x_137 = bindingMut_8;
        val x_138 = x_137.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
        val x_144 = x_138.foldLeft[scala.Int](0)(((x_139: scala.Int, y_140: generated.example.muddy_children.MuddyChild) => {
          val x_141 = y_140.isMuddy;
          val x_143 = x_141.&&({
            val x_142 = y_140.isForward;
            x_142.`unary_!`
          });
          if (x_143)
            x_139.+(1)
          else
            x_139
        }));
        resetData_0 = x_144;
        val x_145 = resetData_0;
        val x_146 = x_145.asInstanceOf[scala.Int];
        bindingMut_7 = x_146;
        val x_147 = this.counter;
        resetData_0 = x_147;
        val x_148 = resetData_0;
        val x_149 = x_148.asInstanceOf[scala.Int];
        bindingMut_6 = x_149;
        val x_150 = bindingMut_6;
        val x_151 = x_150.asInstanceOf[scala.Int];
        val x_152 = bindingMut_7;
        val x_153 = x_152.asInstanceOf[scala.Int];
        val x_154 = x_153.<(x_151);
        resetData_0 = x_154;
        val x_155 = resetData_0;
        val x_156 = x_155.asInstanceOf[scala.Boolean];
        bindingMut_5 = x_156;
        positionVar_20 = 22
      }
    else
      ()
  }));
  data_21.update(22, (() => {
    val x_157 = bindingMut_5;
    val x_158 = x_157.asInstanceOf[scala.Boolean];
    val x_159 = x_158.`unary_!`;
    if (x_159)
      {
        resetData_0 = false;
        positionVar_20 = 23
      }
    else
      positionVar_20 = 27
  }));
  data_21.update(23, (() => {
    val x_160 = resetData_0;
    val x_161 = x_160.asInstanceOf[scala.Boolean];
    bindingMut_3 = x_161;
    positionVar_20 = 24
  }));
  data_21.update(24, (() => {
    val x_162 = bindingMut_3;
    val x_163 = x_162.asInstanceOf[scala.Boolean];
    val x_164 = x_163.`unary_!`;
    if (x_164)
      positionVar_20 = 25
    else
      positionVar_20 = 26
  }));
  data_21.update(25, (() => {
    val x_165 = resetData_0;
    val x_166 = x_165.asInstanceOf[scala.Any];
    bindingMut_16 = x_166;
    val x_167 = bindingMut_16;
    val x_168 = x_167.asInstanceOf[scala.Any];
    val x_169 = listValMut_17;
    val x_170 = x_169.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_170.reply(this, x_168);
    resetData_0 = ();
    positionVar_20 = 7
  }));
  data_21.update(26, (() => {
    val x_171 = bindingMut_3;
    val x_172 = x_171.asInstanceOf[scala.Boolean];
    if (x_172)
      {
        this.`shouldMove_=`(true);
        resetData_0 = ();
        positionVar_20 = 25
      }
    else
      ()
  }));
  data_21.update(27, (() => {
    val x_173 = bindingMut_5;
    val x_174 = x_173.asInstanceOf[scala.Boolean];
    if (x_174)
      {
        val x_175 = this.isForward;
        resetData_0 = x_175;
        val x_176 = resetData_0;
        val x_177 = x_176.asInstanceOf[scala.Boolean];
        bindingMut_4 = x_177;
        val x_178 = bindingMut_4;
        val x_179 = x_178.asInstanceOf[scala.Boolean];
        val x_180 = x_179.`unary_!`;
        resetData_0 = x_180;
        positionVar_20 = 23
      }
    else
      ()
  }));
  data_21.update(28, (() => {
    val x_181 = iterMut_18;
    val x_182 = x_181.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_183 = x_182.hasNext;
    val x_184 = x_183.`unary_!`;
    if (x_184)
      {
        val x_185 = 1.toDouble;
        resetData_0 = x_185;
        val x_186 = resetData_0;
        val x_187 = x_186.asInstanceOf[scala.Double];
        bindingMut_15 = x_187;
        resetData_0 = 0.0;
        val x_188 = resetData_0;
        val x_189 = x_188.asInstanceOf[scala.Double];
        bindingMut_14 = x_189;
        positionVar_20 = 29
      }
    else
      ()
  }));
  data_21.update(29, (() => {
    val x_190 = meta.deep.runtime.Actor.proceedLabel;
    val x_191 = x_190("turn");
    val x_192 = bindingMut_14;
    val x_193 = x_192.asInstanceOf[scala.Double];
    val x_194 = x_193.+(x_191);
    resetData_0 = x_194;
    val x_195 = resetData_0;
    val x_196 = x_195.asInstanceOf[scala.Double];
    bindingMut_14 = x_196;
    val x_197 = meta.deep.runtime.Actor.labelVals("turn");
    val x_198 = bindingMut_14;
    val x_199 = x_198.asInstanceOf[scala.Double];
    val x_200 = bindingMut_15;
    val x_201 = x_200.asInstanceOf[scala.Double];
    val x_202 = x_201.-(x_199);
    x_197.append(x_202);
    resetData_0 = ();
    positionVar_20 = 30;
    val x_203 = currentTurn;
    val x_204 = x_203.+(1);
    currentTurn = x_204
  }));
  data_21.update(30, (() => {
    val x_205 = bindingMut_14;
    val x_206 = x_205.asInstanceOf[scala.Double];
    val x_207 = bindingMut_15;
    val x_208 = x_207.asInstanceOf[scala.Double];
    val x_209 = x_206.<(x_208);
    if (x_209)
      positionVar_20 = 29
    else
      positionVar_20 = 31
  }));
  data_21.update(31, (() => {
    val x_210 = bindingMut_14;
    val x_211 = x_210.asInstanceOf[scala.Double];
    val x_212 = bindingMut_15;
    val x_213 = x_212.asInstanceOf[scala.Double];
    val x_214 = x_211.<(x_213);
    val x_215 = x_214.`unary_!`;
    if (x_215)
      positionVar_20 = 10
    else
      ();
    val x_216 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_217 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_216, 12);
    val x_218 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_217);
    resetData_1.prepend(x_218)
  }));
  data_21.update(32, (() => {
    val x_219 = true.`unary_!`;
    if (x_219)
      {
        val x_220 = resetData_1.remove(0);
        val x_224 = x_220.find(((x_221: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_222 = x_221._1;
          val x_223 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_222.==(x_223)
        }));
        val x_225 = x_224.get;
        val x_226 = x_225._2;
        positionVar_20 = x_226
      }
    else
      ()
  }));
  data_21
}).apply();
  
  override def run_until(until_228: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_229 = currentTurn;
      val x_230 = x_229.<=(until_228);
      x_230.&&({
        val x_231 = positionVar_20;
        val x_232 = commands_227.length;
        x_231.<(x_232)
      })
    }) 
      {
        val x_233 = positionVar_20;
        val x_234 = commands_227.apply(x_233);
        x_234.apply()
      }
    ;
    this
  }
}
