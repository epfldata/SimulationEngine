package generated.meta.example.latency

class Server (var processTime: Double) extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_5: scala.Long = 0L;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: java.lang.String = null;
  private var bindingMut_9: scala.Int = 0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: scala.Double = 0.0;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: java.lang.String = null;
  private var bindingMut_15: java.lang.String = null;
  private var bindingMut_16: scala.Double = 0.0;
  private var bindingMut_17: scala.Any = null;
  private var listValMut_18: meta.deep.runtime.RequestMessage = null;
  private var iterMut_19: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_21: scala.Int = 0;
  
  val commands_207 = (() => {
  val data_22 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_22.update(0, (() => {
    positionVar_21 = 1;
    val x_23 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_24 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_23, 19);
    val x_25 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_24);
    resetData_1.prepend(x_25)
  }));
  data_22.update(1, (() => if (true)
    positionVar_21 = 2
  else
    positionVar_21 = 22));
  data_22.update(2, (() => {
    val x_26 = this.popRequestMessages;
    val x_27 = x_26.iterator;
    iterMut_19 = x_27;
    positionVar_21 = 3
  }));
  data_22.update(3, (() => {
    val x_28 = iterMut_19;
    val x_29 = x_28.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_30 = x_29.hasNext;
    if (x_30)
      {
        val x_31 = iterMut_19;
        val x_32 = x_31.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_33 = x_32.next();
        listValMut_18 = x_33;
        positionVar_21 = 4
      }
    else
      positionVar_21 = 12
  }));
  data_22.update(4, (() => {
    val x_34 = listValMut_18;
    val x_35 = x_34.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_36 = x_35.methodId;
    val x_37 = x_36.==(0);
    val x_38 = x_37.`unary_!`;
    if (x_38)
      positionVar_21 = 5
    else
      positionVar_21 = 8
  }));
  data_22.update(5, (() => {
    val x_39 = listValMut_18;
    val x_40 = x_39.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_41 = x_40.methodId;
    val x_42 = x_41.==(1);
    val x_43 = x_42.`unary_!`;
    if (x_43)
      {
        val x_44 = listValMut_18;
        val x_45 = x_44.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_46 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_45);
        val x_47 = this.addReceiveMessages(x_46);
        resetData_0 = x_47;
        positionVar_21 = 6
      }
    else
      positionVar_21 = 7
  }));
  data_22.update(6, (() => positionVar_21 = 3));
  data_22.update(7, (() => {
    val x_48 = listValMut_18;
    val x_49 = x_48.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_50 = x_49.methodId;
    val x_51 = x_50.==(1);
    if (x_51)
      positionVar_21 = 1
    else
      ();
    val x_52 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_53 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_52, 18);
    val x_54 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_53);
    resetData_1.prepend(x_54)
  }));
  data_22.update(8, (() => {
    val x_55 = listValMut_18;
    val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_57 = x_56.methodId;
    val x_58 = x_57.==(0);
    if (x_58)
      {
        val x_59 = listValMut_18;
        val x_60 = x_59.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_61 = x_60.argss;
        val x_62 = x_61(0);
        val x_63 = x_62(0);
        x_4.prepend(x_63);
        val x_64 = listValMut_18;
        val x_65 = x_64.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_66 = x_65.argss;
        val x_67 = x_66(0);
        val x_68 = x_67(0);
        val x_69 = x_68.asInstanceOf[scala.Long];
        methodArgsMut_5 = x_69;
        val x_70 = methodArgsMut_5;
        val x_71 = x_70.asInstanceOf[scala.Long];
        val x_72 = "Server processing request for ".+(x_71);
        resetData_0 = x_72;
        val x_73 = resetData_0;
        val x_74 = x_73.asInstanceOf[java.lang.String];
        bindingMut_15 = x_74;
        val x_75 = bindingMut_15;
        val x_76 = x_75.asInstanceOf[java.lang.String];
        val x_77 = x_76.+("; take ");
        resetData_0 = x_77;
        val x_78 = resetData_0;
        val x_79 = x_78.asInstanceOf[java.lang.String];
        bindingMut_14 = x_79;
        val x_80 = this.processTime;
        resetData_0 = x_80;
        val x_81 = resetData_0;
        val x_82 = x_81.asInstanceOf[scala.Double];
        bindingMut_13 = x_82;
        val x_83 = bindingMut_13;
        val x_84 = x_83.asInstanceOf[scala.Double];
        val x_85 = bindingMut_14;
        val x_86 = x_85.asInstanceOf[java.lang.String];
        val x_87 = x_86.+(x_84);
        resetData_0 = x_87;
        val x_88 = resetData_0;
        val x_89 = x_88.asInstanceOf[java.lang.String];
        bindingMut_12 = x_89;
        val x_90 = bindingMut_12;
        val x_91 = x_90.asInstanceOf[java.lang.String];
        scala.Predef.println(x_91);
        resetData_0 = ();
        val x_92 = this.processTime;
        resetData_0 = x_92;
        val x_93 = resetData_0;
        val x_94 = x_93.asInstanceOf[scala.Double];
        bindingMut_11 = x_94;
        resetData_0 = 0.0;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[scala.Double];
        bindingMut_10 = x_96;
        positionVar_21 = 9
      }
    else
      ()
  }));
  data_22.update(9, (() => {
    val x_97 = meta.deep.runtime.Actor.proceedLabel;
    val x_98 = x_97("time");
    val x_99 = bindingMut_10;
    val x_100 = x_99.asInstanceOf[scala.Double];
    val x_101 = x_100.+(x_98);
    resetData_0 = x_101;
    val x_102 = resetData_0;
    val x_103 = x_102.asInstanceOf[scala.Double];
    bindingMut_10 = x_103;
    val x_104 = meta.deep.runtime.Actor.labelVals("time");
    val x_105 = bindingMut_10;
    val x_106 = x_105.asInstanceOf[scala.Double];
    val x_107 = bindingMut_11;
    val x_108 = x_107.asInstanceOf[scala.Double];
    val x_109 = x_108.-(x_106);
    x_104.append(x_109);
    resetData_0 = ();
    positionVar_21 = 10;
    val x_110 = currentTurn;
    val x_111 = x_110.+(1);
    currentTurn = x_111
  }));
  data_22.update(10, (() => {
    val x_112 = bindingMut_10;
    val x_113 = x_112.asInstanceOf[scala.Double];
    val x_114 = bindingMut_11;
    val x_115 = x_114.asInstanceOf[scala.Double];
    val x_116 = x_113.<(x_115);
    if (x_116)
      positionVar_21 = 9
    else
      positionVar_21 = 11
  }));
  data_22.update(11, (() => {
    val x_117 = bindingMut_10;
    val x_118 = x_117.asInstanceOf[scala.Double];
    val x_119 = bindingMut_11;
    val x_120 = x_119.asInstanceOf[scala.Double];
    val x_121 = x_118.<(x_120);
    val x_122 = x_121.`unary_!`;
    if (x_122)
      {
        val x_123 = scala.util.Random.nextInt(100);
        resetData_0 = x_123;
        val x_124 = resetData_0;
        val x_125 = x_124.asInstanceOf[scala.Int];
        bindingMut_9 = x_125;
        val x_126 = methodArgsMut_5;
        val x_127 = x_126.asInstanceOf[scala.Long];
        val x_128 = "Response for client id ".+(x_127);
        resetData_0 = x_128;
        val x_129 = resetData_0;
        val x_130 = x_129.asInstanceOf[java.lang.String];
        bindingMut_8 = x_130;
        val x_131 = bindingMut_8;
        val x_132 = x_131.asInstanceOf[java.lang.String];
        val x_133 = x_132.+(" is ");
        resetData_0 = x_133;
        val x_134 = resetData_0;
        val x_135 = x_134.asInstanceOf[java.lang.String];
        bindingMut_7 = x_135;
        val x_136 = bindingMut_7;
        val x_137 = x_136.asInstanceOf[java.lang.String];
        val x_138 = bindingMut_9;
        val x_139 = x_138.asInstanceOf[scala.Int];
        val x_140 = x_137.+(x_139);
        resetData_0 = x_140;
        val x_141 = resetData_0;
        val x_142 = x_141.asInstanceOf[java.lang.String];
        bindingMut_6 = x_142;
        val x_143 = bindingMut_6;
        val x_144 = x_143.asInstanceOf[java.lang.String];
        scala.Predef.println(x_144);
        resetData_0 = ();
        val x_145 = bindingMut_9;
        val x_146 = x_145.asInstanceOf[scala.Int];
        resetData_0 = x_146;
        x_4.remove(0);
        val x_147 = x_4.isEmpty;
        val x_148 = x_147.`unary_!`;
        if (x_148)
          {
            val x_149 = x_4(0);
            val x_150 = x_149.asInstanceOf[scala.Long];
            methodArgsMut_5 = x_150
          }
        else
          ();
        val x_151 = resetData_0;
        val x_152 = x_151.asInstanceOf[scala.Any];
        bindingMut_17 = x_152;
        val x_153 = bindingMut_17;
        val x_154 = x_153.asInstanceOf[scala.Any];
        val x_155 = listValMut_18;
        val x_156 = x_155.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_156.reply(this, x_154);
        resetData_0 = ();
        positionVar_21 = 6
      }
    else
      ()
  }));
  data_22.update(12, (() => {
    val x_157 = iterMut_19;
    val x_158 = x_157.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_159 = x_158.hasNext;
    val x_160 = x_159.`unary_!`;
    if (x_160)
      {
        resetData_0 = 0.0;
        val x_161 = resetData_0;
        val x_162 = x_161.asInstanceOf[scala.Double];
        bindingMut_16 = x_162;
        positionVar_21 = 13
      }
    else
      ()
  }));
  data_22.update(13, (() => {
    val x_163 = meta.deep.runtime.Actor.proceedLabel;
    val x_164 = x_163("time");
    val x_165 = bindingMut_16;
    val x_166 = x_165.asInstanceOf[scala.Double];
    val x_167 = x_166.+(x_164);
    resetData_0 = x_167;
    val x_168 = resetData_0;
    val x_169 = x_168.asInstanceOf[scala.Double];
    bindingMut_16 = x_169;
    val x_170 = meta.deep.runtime.Actor.labelVals("time");
    val x_171 = bindingMut_16;
    val x_172 = x_171.asInstanceOf[scala.Double];
    val x_173 = 0.1.-(x_172);
    x_170.append(x_173);
    resetData_0 = ();
    positionVar_21 = 14;
    val x_174 = currentTurn;
    val x_175 = x_174.+(1);
    currentTurn = x_175
  }));
  data_22.update(14, (() => {
    val x_176 = bindingMut_16;
    val x_177 = x_176.asInstanceOf[scala.Double];
    val x_178 = x_177.<(0.1);
    if (x_178)
      positionVar_21 = 13
    else
      positionVar_21 = 15
  }));
  data_22.update(15, (() => {
    val x_179 = bindingMut_16;
    val x_180 = x_179.asInstanceOf[scala.Double];
    val x_181 = x_180.<(0.1);
    val x_182 = x_181.`unary_!`;
    if (x_182)
      positionVar_21 = 16
    else
      ()
  }));
  data_22.update(16, (() => if (true)
    positionVar_21 = 2
  else
    positionVar_21 = 17));
  data_22.update(17, (() => {
    val x_183 = true.`unary_!`;
    if (x_183)
      {
        val x_184 = resetData_1.remove(0);
        val x_188 = x_184.find(((x_185: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_186 = x_185._1;
          val x_187 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_186.==(x_187)
        }));
        val x_189 = x_188.get;
        val x_190 = x_189._2;
        positionVar_21 = x_190
      }
    else
      ()
  }));
  data_22.update(18, (() => {
    val x_191 = resetData_0;
    val x_192 = x_191.asInstanceOf[scala.Any];
    bindingMut_17 = x_192;
    val x_193 = bindingMut_17;
    val x_194 = x_193.asInstanceOf[scala.Any];
    val x_195 = listValMut_18;
    val x_196 = x_195.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_196.reply(this, x_194);
    resetData_0 = ();
    positionVar_21 = 6
  }));
  data_22.update(19, (() => positionVar_21 = 20));
  data_22.update(20, (() => {
    positionVar_21 = 21;
    val x_197 = currentTurn;
    val x_198 = x_197.+(1);
    currentTurn = x_198
  }));
  data_22.update(21, (() => positionVar_21 = 20));
  data_22.update(22, (() => {
    val x_199 = true.`unary_!`;
    if (x_199)
      {
        val x_200 = resetData_1.remove(0);
        val x_204 = x_200.find(((x_201: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_202 = x_201._1;
          val x_203 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_202.==(x_203)
        }));
        val x_205 = x_204.get;
        val x_206 = x_205._2;
        positionVar_21 = x_206
      }
    else
      ()
  }));
  data_22
}).apply();
  
  override def run_until(until_208: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_209 = currentTurn;
      val x_210 = x_209.<=(until_208);
      x_210.&&({
        val x_211 = positionVar_21;
        val x_212 = commands_207.length;
        x_211.<(x_212)
      })
    }) 
      {
        val x_213 = positionVar_21;
        val x_214 = commands_207.apply(x_213);
        x_214.apply()
      }
    ;
    this
  }
}
