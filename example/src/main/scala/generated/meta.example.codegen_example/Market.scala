package generated.meta.example.codegen_example

class Market () extends meta.deep.runtime.Actor {
  var goods: List[String] = scala.collection.immutable.Nil
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_6: scala.collection.immutable.List[scala.Int] = null;
  private var methodArgsMut_7: scala.Int = 0;
  private var methodArgsMut_8: scala.Int = 0;
  private var bindingMut_9: scala.Any = null;
  private var listValMut_10: meta.deep.runtime.RequestMessage = null;
  private var iterMut_11: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_12: scala.Int = 0;
  private var positionVar_14: scala.Int = 0;
  
  val commands_198 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_15.update(0, (() => {
    resetData_0 = 0;
    val x_16 = resetData_0;
    val x_17 = x_16.asInstanceOf[scala.Int];
    bindingMut_12 = x_17;
    positionVar_14 = 1
  }));
  data_15.update(1, (() => {
    val x_18 = this.popRequestMessages;
    val x_19 = x_18.iterator;
    iterMut_11 = x_19;
    positionVar_14 = 2
  }));
  data_15.update(2, (() => {
    val x_20 = iterMut_11;
    val x_21 = x_20.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_22 = x_21.hasNext;
    if (x_22)
      {
        val x_23 = iterMut_11;
        val x_24 = x_23.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_25 = x_24.next();
        listValMut_10 = x_25;
        positionVar_14 = 3
      }
    else
      positionVar_14 = 23
  }));
  data_15.update(3, (() => {
    val x_26 = listValMut_10;
    val x_27 = x_26.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_28 = x_27.methodId;
    val x_29 = x_28.==(1);
    val x_30 = x_29.`unary_!`;
    if (x_30)
      positionVar_14 = 4
    else
      positionVar_14 = 19
  }));
  data_15.update(4, (() => {
    val x_31 = listValMut_10;
    val x_32 = x_31.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_33 = x_32.methodId;
    val x_34 = x_33.==(2);
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_14 = 5
    else
      positionVar_14 = 18
  }));
  data_15.update(5, (() => {
    val x_36 = listValMut_10;
    val x_37 = x_36.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_38 = x_37.methodId;
    val x_39 = x_38.==(3);
    val x_40 = x_39.`unary_!`;
    if (x_40)
      positionVar_14 = 6
    else
      positionVar_14 = 7
  }));
  data_15.update(6, (() => {
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[scala.Any];
    bindingMut_9 = x_42;
    val x_43 = bindingMut_9;
    val x_44 = x_43.asInstanceOf[scala.Any];
    val x_45 = listValMut_10;
    val x_46 = x_45.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_46.reply(this, x_44);
    resetData_0 = ();
    positionVar_14 = 2
  }));
  data_15.update(7, (() => {
    val x_47 = listValMut_10;
    val x_48 = x_47.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_49 = x_48.methodId;
    val x_50 = x_49.==(3);
    if (x_50)
      {
        val x_51 = listValMut_10;
        val x_52 = x_51.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_53 = x_52.argss;
        val x_54 = x_53(0);
        val x_55 = x_54(0);
        x_3.prepend(x_55);
        val x_56 = listValMut_10;
        val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_58 = x_57.argss;
        val x_59 = x_58(0);
        val x_60 = x_59(0);
        val x_61 = x_60.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_6 = x_61;
        positionVar_14 = 8
      }
    else
      ();
    val x_62 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_63 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_62, 16);
    val x_64 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_63);
    resetData_1.prepend(x_64)
  }));
  data_15.update(8, (() => {
    val x_65 = methodArgsMut_6;
    val x_66 = x_65.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_67 = x_66.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_68 = x_67.tail;
    val x_69 = x_68.isEmpty;
    val x_70 = x_69.==(false);
    val x_71 = x_70.`unary_!`;
    if (x_71)
      positionVar_14 = 9
    else
      positionVar_14 = 17
  }));
  data_15.update(9, (() => {
    val x_72 = methodArgsMut_6;
    val x_73 = x_72.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_74 = x_73.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_75 = x_74.head;
    scala.Predef.println(x_75);
    resetData_0 = ();
    val x_76 = resetData_1.remove(0);
    val x_80 = x_76.find(((x_77: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_78 = x_77._1;
      val x_79 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_78.==(x_79)
    }));
    val x_81 = x_80.get;
    val x_82 = x_81._2;
    positionVar_14 = x_82
  }));
  data_15.update(10, (() => {
    x_3.remove(0);
    val x_83 = x_3.isEmpty;
    val x_84 = x_83.`unary_!`;
    if (x_84)
      {
        val x_85 = x_3(0);
        val x_86 = x_85.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_6 = x_86
      }
    else
      ();
    positionVar_14 = 9
  }));
  data_15.update(11, (() => {
    x_3.remove(0);
    val x_87 = x_3.isEmpty;
    val x_88 = x_87.`unary_!`;
    if (x_88)
      {
        val x_89 = x_3(0);
        val x_90 = x_89.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_6 = x_90
      }
    else
      ();
    positionVar_14 = 12;
    val x_91 = currentTurn;
    val x_92 = x_91.+(1);
    currentTurn = x_92
  }));
  data_15.update(12, (() => if (true)
    positionVar_14 = 1
  else
    positionVar_14 = 13));
  data_15.update(13, (() => {
    val x_93 = true.`unary_!`;
    if (x_93)
      positionVar_14 = 14
    else
      ()
  }));
  data_15.update(14, (() => {
    positionVar_14 = 15;
    val x_94 = currentTurn;
    val x_95 = x_94.+(1);
    currentTurn = x_95
  }));
  data_15.update(15, (() => positionVar_14 = 14));
  data_15.update(16, (() => {
    x_3.remove(0);
    val x_96 = x_3.isEmpty;
    val x_97 = x_96.`unary_!`;
    if (x_97)
      {
        val x_98 = x_3(0);
        val x_99 = x_98.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_6 = x_99
      }
    else
      ();
    positionVar_14 = 6
  }));
  data_15.update(17, (() => {
    val x_100 = methodArgsMut_6;
    val x_101 = x_100.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_102 = x_101.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_103 = x_102.tail;
    val x_104 = x_103.isEmpty;
    val x_105 = x_104.==(false);
    if (x_105)
      {
        val x_106 = methodArgsMut_6;
        val x_107 = x_106.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_108 = x_107.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_109 = x_108.tail;
        x_3.prepend(x_109);
        val x_110 = methodArgsMut_6;
        val x_111 = x_110.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_112 = x_111.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_113 = x_112.tail;
        val x_114 = x_113.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_6 = x_114;
        positionVar_14 = 8
      }
    else
      ();
    val x_115 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_116 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_115, 10);
    val x_117 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_116);
    resetData_1.prepend(x_117)
  }));
  data_15.update(18, (() => {
    val x_118 = listValMut_10;
    val x_119 = x_118.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_120 = x_119.methodId;
    val x_121 = x_120.==(2);
    if (x_121)
      {
        val x_122 = listValMut_10;
        val x_123 = x_122.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_124 = x_123.argss;
        val x_125 = x_124(0);
        val x_126 = x_125(0);
        x_4.prepend(x_126);
        val x_127 = listValMut_10;
        val x_128 = x_127.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_129 = x_128.argss;
        val x_130 = x_129(0);
        val x_131 = x_130(0);
        val x_132 = x_131.asInstanceOf[scala.Int];
        methodArgsMut_7 = x_132;
        val x_133 = methodArgsMut_7;
        val x_134 = x_133.asInstanceOf[scala.Int];
        val x_135 = "Market sells: ".+(x_134);
        scala.Predef.println(x_135);
        resetData_0 = 42;
        x_4.remove(0);
        val x_136 = x_4.isEmpty;
        val x_137 = x_136.`unary_!`;
        if (x_137)
          {
            val x_138 = x_4(0);
            val x_139 = x_138.asInstanceOf[scala.Int];
            methodArgsMut_7 = x_139
          }
        else
          ();
        positionVar_14 = 6
      }
    else
      ()
  }));
  data_15.update(19, (() => {
    val x_140 = listValMut_10;
    val x_141 = x_140.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_142 = x_141.methodId;
    val x_143 = x_142.==(1);
    if (x_143)
      {
        val x_144 = listValMut_10;
        val x_145 = x_144.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_146 = x_145.argss;
        val x_147 = x_146(0);
        val x_148 = x_147(0);
        x_5.prepend(x_148);
        val x_149 = listValMut_10;
        val x_150 = x_149.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_151 = x_150.argss;
        val x_152 = x_151(0);
        val x_153 = x_152(0);
        val x_154 = x_153.asInstanceOf[scala.Int];
        methodArgsMut_8 = x_154;
        positionVar_14 = 20
      }
    else
      ();
    val x_155 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_156 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_155, 22);
    val x_157 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_156);
    resetData_1.prepend(x_157)
  }));
  data_15.update(20, (() => {
    val x_158 = methodArgsMut_8;
    val x_159 = x_158.asInstanceOf[scala.Int];
    val x_160 = "Market sells: ".+(x_159);
    scala.Predef.println(x_160);
    resetData_0 = ();
    val x_161 = resetData_1.remove(0);
    val x_165 = x_161.find(((x_162: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_163 = x_162._1;
      val x_164 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_163.==(x_164)
    }));
    val x_166 = x_165.get;
    val x_167 = x_166._2;
    positionVar_14 = x_167
  }));
  data_15.update(21, (() => {
    x_5.remove(0);
    val x_168 = x_5.isEmpty;
    val x_169 = x_168.`unary_!`;
    if (x_169)
      {
        val x_170 = x_5(0);
        val x_171 = x_170.asInstanceOf[scala.Int];
        methodArgsMut_8 = x_171
      }
    else
      ();
    val x_172 = scala.collection.immutable.List.apply[scala.Int](10, 20, 30);
    x_3.prepend(x_172);
    val x_173 = scala.collection.immutable.List.apply[scala.Int](10, 20, 30);
    val x_174 = x_173.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    methodArgsMut_6 = x_174;
    positionVar_14 = 8;
    val x_175 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_176 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_175, 11);
    val x_177 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_176);
    resetData_1.prepend(x_177)
  }));
  data_15.update(22, (() => {
    x_5.remove(0);
    val x_178 = x_5.isEmpty;
    val x_179 = x_178.`unary_!`;
    if (x_179)
      {
        val x_180 = x_5(0);
        val x_181 = x_180.asInstanceOf[scala.Int];
        methodArgsMut_8 = x_181
      }
    else
      ();
    positionVar_14 = 6
  }));
  data_15.update(23, (() => {
    val x_182 = iterMut_11;
    val x_183 = x_182.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_184 = x_183.hasNext;
    val x_185 = x_184.`unary_!`;
    if (x_185)
      {
        val x_186 = bindingMut_12;
        val x_187 = x_186.asInstanceOf[scala.Int];
        val x_188 = x_187.+(1);
        resetData_0 = x_188;
        val x_189 = resetData_0;
        val x_190 = x_189.asInstanceOf[scala.Int];
        bindingMut_12 = x_190;
        val x_191 = bindingMut_12;
        val x_192 = x_191.asInstanceOf[scala.Int];
        val x_193 = scala.Tuple2.apply[java.lang.String, scala.Int]("Binding test:", x_192);
        scala.Predef.println(x_193);
        resetData_0 = ();
        x_5.prepend(10);
        val x_194 = 10.asInstanceOf[scala.Int];
        methodArgsMut_8 = x_194;
        positionVar_14 = 20
      }
    else
      ();
    val x_195 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_196 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_195, 21);
    val x_197 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_196);
    resetData_1.prepend(x_197)
  }));
  data_15
}).apply();
  
  override def run_until(until_199: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_200 = currentTurn;
      val x_201 = x_200.<=(until_199);
      x_201.&&({
        val x_202 = positionVar_14;
        val x_203 = commands_198.length;
        x_202.<(x_203)
      })
    }) 
      {
        val x_204 = positionVar_14;
        val x_205 = commands_198.apply(x_204);
        x_205.apply()
      }
    ;
    this
  }
}
