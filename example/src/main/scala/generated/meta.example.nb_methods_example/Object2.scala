package generated.meta.example.nb_methods_example

class Object2 () extends meta.deep.runtime.Actor {
  var deposit: Int = 500
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_6: scala.Double = 0.0;
  private var methodArgsMut_7: scala.Double = 0.0;
  private var methodArgsMut_8: scala.Long = 0L;
  private var bindingMut_9: scala.Double = 0.0;
  private var bindingMut_10: scala.Double = 0.0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Double = 0.0;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: scala.Any = null;
  private var listValMut_15: meta.deep.runtime.RequestMessage = null;
  private var iterMut_16: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_18: scala.Int = 0;
  
  val commands_205 = (() => {
  val data_19 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_19.update(0, (() => {
    positionVar_18 = 1;
    val x_20 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_21 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_20, 19);
    val x_22 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_21);
    resetData_1.prepend(x_22)
  }));
  data_19.update(1, (() => if (true)
    positionVar_18 = 2
  else
    positionVar_18 = 22));
  data_19.update(2, (() => {
    val x_23 = this.popRequestMessages;
    val x_24 = x_23.iterator;
    iterMut_16 = x_24;
    positionVar_18 = 3
  }));
  data_19.update(3, (() => {
    val x_25 = iterMut_16;
    val x_26 = x_25.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_27 = x_26.hasNext;
    if (x_27)
      {
        val x_28 = iterMut_16;
        val x_29 = x_28.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_30 = x_29.next();
        listValMut_15 = x_30;
        positionVar_18 = 4
      }
    else
      positionVar_18 = 12
  }));
  data_19.update(4, (() => {
    val x_31 = listValMut_15;
    val x_32 = x_31.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_33 = x_32.methodId;
    val x_34 = x_33.==(0);
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_18 = 5
    else
      positionVar_18 = 8
  }));
  data_19.update(5, (() => {
    val x_36 = listValMut_15;
    val x_37 = x_36.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_38 = x_37.methodId;
    val x_39 = x_38.==(1);
    val x_40 = x_39.`unary_!`;
    if (x_40)
      {
        val x_41 = listValMut_15;
        val x_42 = x_41.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_43 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_42);
        val x_44 = this.addReceiveMessages(x_43);
        resetData_0 = x_44;
        positionVar_18 = 6
      }
    else
      positionVar_18 = 7
  }));
  data_19.update(6, (() => positionVar_18 = 3));
  data_19.update(7, (() => {
    val x_45 = listValMut_15;
    val x_46 = x_45.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_47 = x_46.methodId;
    val x_48 = x_47.==(1);
    if (x_48)
      positionVar_18 = 1
    else
      ();
    val x_49 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_50 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_49, 18);
    val x_51 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_50);
    resetData_1.prepend(x_51)
  }));
  data_19.update(8, (() => {
    val x_52 = listValMut_15;
    val x_53 = x_52.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_54 = x_53.methodId;
    val x_55 = x_54.==(0);
    if (x_55)
      {
        val x_56 = listValMut_15;
        val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_58 = x_57.argss;
        val x_59 = x_58(0);
        val x_60 = x_59(0);
        x_5.prepend(x_60);
        val x_61 = listValMut_15;
        val x_62 = x_61.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_63 = x_62.argss;
        val x_64 = x_63(0);
        val x_65 = x_64(1);
        x_4.prepend(x_65);
        val x_66 = listValMut_15;
        val x_67 = x_66.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_68 = x_67.argss;
        val x_69 = x_68(0);
        val x_70 = x_69(2);
        x_3.prepend(x_70);
        val x_71 = listValMut_15;
        val x_72 = x_71.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_73 = x_72.argss;
        val x_74 = x_73(0);
        val x_75 = x_74(0);
        val x_76 = x_75.asInstanceOf[scala.Long];
        methodArgsMut_8 = x_76;
        val x_77 = listValMut_15;
        val x_78 = x_77.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_79 = x_78.argss;
        val x_80 = x_79(0);
        val x_81 = x_80(1);
        val x_82 = x_81.asInstanceOf[scala.Double];
        methodArgsMut_7 = x_82;
        val x_83 = listValMut_15;
        val x_84 = x_83.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_85 = x_84.argss;
        val x_86 = x_85(0);
        val x_87 = x_86(2);
        val x_88 = x_87.asInstanceOf[scala.Double];
        methodArgsMut_6 = x_88;
        val x_89 = methodArgsMut_8;
        val x_90 = x_89.asInstanceOf[scala.Long];
        val x_91 = "Async msg received from ".+(x_90);
        resetData_0 = x_91;
        val x_92 = resetData_0;
        val x_93 = x_92.asInstanceOf[java.lang.String];
        bindingMut_11 = x_93;
        val x_94 = bindingMut_11;
        val x_95 = x_94.asInstanceOf[java.lang.String];
        scala.Predef.println(x_95);
        resetData_0 = ();
        val x_96 = 1.toDouble;
        resetData_0 = x_96;
        val x_97 = resetData_0;
        val x_98 = x_97.asInstanceOf[scala.Double];
        bindingMut_10 = x_98;
        resetData_0 = 0.0;
        val x_99 = resetData_0;
        val x_100 = x_99.asInstanceOf[scala.Double];
        bindingMut_9 = x_100;
        positionVar_18 = 9
      }
    else
      ()
  }));
  data_19.update(9, (() => {
    val x_101 = meta.deep.runtime.Actor.proceedLabel;
    val x_102 = x_101("turn");
    val x_103 = bindingMut_9;
    val x_104 = x_103.asInstanceOf[scala.Double];
    val x_105 = x_104.+(x_102);
    resetData_0 = x_105;
    val x_106 = resetData_0;
    val x_107 = x_106.asInstanceOf[scala.Double];
    bindingMut_9 = x_107;
    val x_108 = meta.deep.runtime.Actor.labelVals("turn");
    val x_109 = bindingMut_9;
    val x_110 = x_109.asInstanceOf[scala.Double];
    val x_111 = bindingMut_10;
    val x_112 = x_111.asInstanceOf[scala.Double];
    val x_113 = x_112.-(x_110);
    x_108.append(x_113);
    resetData_0 = ();
    positionVar_18 = 10;
    val x_114 = currentTurn;
    val x_115 = x_114.+(1);
    currentTurn = x_115
  }));
  data_19.update(10, (() => {
    val x_116 = bindingMut_9;
    val x_117 = x_116.asInstanceOf[scala.Double];
    val x_118 = bindingMut_10;
    val x_119 = x_118.asInstanceOf[scala.Double];
    val x_120 = x_117.<(x_119);
    if (x_120)
      positionVar_18 = 9
    else
      positionVar_18 = 11
  }));
  data_19.update(11, (() => {
    val x_121 = bindingMut_9;
    val x_122 = x_121.asInstanceOf[scala.Double];
    val x_123 = bindingMut_10;
    val x_124 = x_123.asInstanceOf[scala.Double];
    val x_125 = x_122.<(x_124);
    val x_126 = x_125.`unary_!`;
    if (x_126)
      {
        val x_127 = this.deposit;
        resetData_0 = x_127;
        x_5.remove(0);
        val x_128 = x_5.isEmpty;
        val x_129 = x_128.`unary_!`;
        if (x_129)
          {
            val x_130 = x_5(0);
            val x_131 = x_130.asInstanceOf[scala.Long];
            methodArgsMut_8 = x_131
          }
        else
          ();
        x_4.remove(0);
        val x_132 = x_4.isEmpty;
        val x_133 = x_132.`unary_!`;
        if (x_133)
          {
            val x_134 = x_4(0);
            val x_135 = x_134.asInstanceOf[scala.Double];
            methodArgsMut_7 = x_135
          }
        else
          ();
        x_3.remove(0);
        val x_136 = x_3.isEmpty;
        val x_137 = x_136.`unary_!`;
        if (x_137)
          {
            val x_138 = x_3(0);
            val x_139 = x_138.asInstanceOf[scala.Double];
            methodArgsMut_6 = x_139
          }
        else
          ();
        val x_140 = resetData_0;
        val x_141 = x_140.asInstanceOf[scala.Any];
        bindingMut_14 = x_141;
        val x_142 = bindingMut_14;
        val x_143 = x_142.asInstanceOf[scala.Any];
        val x_144 = listValMut_15;
        val x_145 = x_144.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_145.reply(this, x_143);
        resetData_0 = ();
        positionVar_18 = 6
      }
    else
      ()
  }));
  data_19.update(12, (() => {
    val x_146 = iterMut_16;
    val x_147 = x_146.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_148 = x_147.hasNext;
    val x_149 = x_148.`unary_!`;
    if (x_149)
      {
        val x_150 = 1.toDouble;
        resetData_0 = x_150;
        val x_151 = resetData_0;
        val x_152 = x_151.asInstanceOf[scala.Double];
        bindingMut_13 = x_152;
        resetData_0 = 0.0;
        val x_153 = resetData_0;
        val x_154 = x_153.asInstanceOf[scala.Double];
        bindingMut_12 = x_154;
        positionVar_18 = 13
      }
    else
      ()
  }));
  data_19.update(13, (() => {
    val x_155 = meta.deep.runtime.Actor.proceedLabel;
    val x_156 = x_155("turn");
    val x_157 = bindingMut_12;
    val x_158 = x_157.asInstanceOf[scala.Double];
    val x_159 = x_158.+(x_156);
    resetData_0 = x_159;
    val x_160 = resetData_0;
    val x_161 = x_160.asInstanceOf[scala.Double];
    bindingMut_12 = x_161;
    val x_162 = meta.deep.runtime.Actor.labelVals("turn");
    val x_163 = bindingMut_12;
    val x_164 = x_163.asInstanceOf[scala.Double];
    val x_165 = bindingMut_13;
    val x_166 = x_165.asInstanceOf[scala.Double];
    val x_167 = x_166.-(x_164);
    x_162.append(x_167);
    resetData_0 = ();
    positionVar_18 = 14;
    val x_168 = currentTurn;
    val x_169 = x_168.+(1);
    currentTurn = x_169
  }));
  data_19.update(14, (() => {
    val x_170 = bindingMut_12;
    val x_171 = x_170.asInstanceOf[scala.Double];
    val x_172 = bindingMut_13;
    val x_173 = x_172.asInstanceOf[scala.Double];
    val x_174 = x_171.<(x_173);
    if (x_174)
      positionVar_18 = 13
    else
      positionVar_18 = 15
  }));
  data_19.update(15, (() => {
    val x_175 = bindingMut_12;
    val x_176 = x_175.asInstanceOf[scala.Double];
    val x_177 = bindingMut_13;
    val x_178 = x_177.asInstanceOf[scala.Double];
    val x_179 = x_176.<(x_178);
    val x_180 = x_179.`unary_!`;
    if (x_180)
      positionVar_18 = 16
    else
      ()
  }));
  data_19.update(16, (() => if (true)
    positionVar_18 = 2
  else
    positionVar_18 = 17));
  data_19.update(17, (() => {
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
        positionVar_18 = x_188
      }
    else
      ()
  }));
  data_19.update(18, (() => {
    val x_189 = resetData_0;
    val x_190 = x_189.asInstanceOf[scala.Any];
    bindingMut_14 = x_190;
    val x_191 = bindingMut_14;
    val x_192 = x_191.asInstanceOf[scala.Any];
    val x_193 = listValMut_15;
    val x_194 = x_193.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_194.reply(this, x_192);
    resetData_0 = ();
    positionVar_18 = 6
  }));
  data_19.update(19, (() => positionVar_18 = 20));
  data_19.update(20, (() => {
    positionVar_18 = 21;
    val x_195 = currentTurn;
    val x_196 = x_195.+(1);
    currentTurn = x_196
  }));
  data_19.update(21, (() => positionVar_18 = 20));
  data_19.update(22, (() => {
    val x_197 = true.`unary_!`;
    if (x_197)
      {
        val x_198 = resetData_1.remove(0);
        val x_202 = x_198.find(((x_199: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_200 = x_199._1;
          val x_201 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_200.==(x_201)
        }));
        val x_203 = x_202.get;
        val x_204 = x_203._2;
        positionVar_18 = x_204
      }
    else
      ()
  }));
  data_19
}).apply();
  
  override def run_until(until_206: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_207 = currentTurn;
      val x_208 = x_207.<=(until_206);
      x_208.&&({
        val x_209 = positionVar_18;
        val x_210 = commands_205.length;
        x_209.<(x_210)
      })
    }) 
      {
        val x_211 = positionVar_18;
        val x_212 = commands_205.apply(x_211);
        x_212.apply()
      }
    ;
    this
  }
}
