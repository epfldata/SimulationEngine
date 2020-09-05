package generated.meta.example.codegen_example

class Market () extends meta.deep.runtime.Actor with Serializable {
  var goods: List[String] = scala.collection.immutable.Nil
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_6 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_7: scala.Int = 0;
  private var bindingMut_8: java.lang.String = null;
  private var methodArgsMut_9: scala.Int = 0;
  private var bindingMut_10: java.lang.String = null;
  private var methodArgsMut_11: scala.collection.immutable.List[scala.Int] = null;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: scala.collection.immutable.List[scala.Int] = null;
  private var bindingMut_14: scala.Boolean = false;
  private var bindingMut_15: scala.Int = 0;
  private var bindingMut_16: scala.collection.immutable.List[scala.Int] = null;
  private var bindingMut_17: scala.Int = 0;
  private var v_18: scala.Int = 0;
  private var bindingMut_19: scala.Int = 0;
  private var bindingMut_20: scala.Int = 0;
  private var bindingMut_21: scala.Int = 0;
  private var y_22: scala.Int = 0;
  private var bindingMut_23: scala.Int = 0;
  private var bindingMut_24: scala.Int = 0;
  private var x_25: scala.Int = 0;
  private var positionVar_27: scala.Int = 0;
  
  val commands_164 = (() => {
  val data_28 = new scala.Array[scala.Function0[scala.Unit]](17);
  data_28.update(0, (() => {
    resetData_0 = 21;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Int];
    x_25 = x_30;
    val x_31 = x_25;
    resetData_0 = x_31;
    val x_32 = resetData_0;
    val x_33 = x_32.asInstanceOf[scala.Int];
    bindingMut_24 = x_33;
    val x_34 = bindingMut_24;
    val x_35 = x_34.asInstanceOf[scala.Int];
    val x_36 = x_35.+(1);
    resetData_0 = x_36;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Int];
    bindingMut_23 = x_38;
    val x_39 = bindingMut_23;
    val x_40 = x_39.asInstanceOf[scala.Int];
    x_25 = x_40;
    resetData_0 = ();
    resetData_0 = 11;
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[scala.Int];
    y_22 = x_42;
    val x_43 = x_25;
    resetData_0 = x_43;
    val x_44 = resetData_0;
    val x_45 = x_44.asInstanceOf[scala.Int];
    bindingMut_21 = x_45;
    val x_46 = y_22;
    resetData_0 = x_46;
    val x_47 = resetData_0;
    val x_48 = x_47.asInstanceOf[scala.Int];
    bindingMut_20 = x_48;
    val x_49 = bindingMut_20;
    val x_50 = x_49.asInstanceOf[scala.Int];
    val x_51 = bindingMut_21;
    val x_52 = x_51.asInstanceOf[scala.Int];
    val x_53 = x_52.+(x_50);
    resetData_0 = x_53;
    val x_54 = resetData_0;
    val x_55 = x_54.asInstanceOf[scala.Int];
    bindingMut_19 = x_55;
    val x_56 = bindingMut_19;
    val x_57 = x_56.asInstanceOf[scala.Int];
    resetData_0 = x_57;
    val x_58 = resetData_0;
    val x_59 = x_58.asInstanceOf[scala.Int];
    v_18 = x_59;
    val x_60 = v_18;
    resetData_0 = x_60;
    val x_61 = resetData_0;
    val x_62 = x_61.asInstanceOf[scala.Int];
    bindingMut_17 = x_62;
    val x_63 = bindingMut_17;
    val x_64 = x_63.asInstanceOf[scala.Int];
    scala.Predef.println(x_64);
    resetData_0 = ();
    positionVar_27 = 1
  }));
  data_28.update(1, (() => if (true)
    positionVar_27 = 2
  else
    positionVar_27 = 16));
  data_28.update(2, (() => {
    x_4.prepend(10);
    val x_65 = 10.asInstanceOf[scala.Int];
    methodArgsMut_7 = x_65;
    val x_66 = methodArgsMut_7;
    val x_67 = x_66.asInstanceOf[scala.Int];
    val x_68 = "Market sells: ".+(x_67);
    resetData_0 = x_68;
    val x_69 = resetData_0;
    val x_70 = x_69.asInstanceOf[java.lang.String];
    bindingMut_8 = x_70;
    val x_71 = bindingMut_8;
    val x_72 = x_71.asInstanceOf[java.lang.String];
    scala.Predef.println(x_72);
    resetData_0 = ();
    x_4.remove(0);
    val x_73 = x_4.isEmpty;
    val x_74 = x_73.`unary_!`;
    if (x_74)
      {
        val x_75 = x_4(0);
        val x_76 = x_75.asInstanceOf[scala.Int];
        methodArgsMut_7 = x_76
      }
    else
      ();
    val x_77 = scala.collection.immutable.List.apply[scala.Int](10, 20, 30);
    resetData_0 = x_77;
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    bindingMut_16 = x_79;
    val x_80 = bindingMut_16;
    val x_81 = x_80.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    x_6.prepend(x_81);
    val x_82 = bindingMut_16;
    val x_83 = x_82.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_84 = x_83.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    methodArgsMut_11 = x_84;
    positionVar_27 = 3;
    val x_85 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_86 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_85, 6);
    val x_87 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_86);
    resetData_1.prepend(x_87)
  }));
  data_28.update(3, (() => {
    val x_88 = methodArgsMut_11;
    val x_89 = x_88.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_90 = x_89.isEmpty;
    resetData_0 = x_90;
    val x_91 = resetData_0;
    val x_92 = x_91.asInstanceOf[scala.Boolean];
    bindingMut_14 = x_92;
    positionVar_27 = 4
  }));
  data_28.update(4, (() => {
    val x_93 = bindingMut_14;
    val x_94 = x_93.asInstanceOf[scala.Boolean];
    if (x_94)
      {
        val x_95 = resetData_1.remove(0);
        val x_99 = x_95.find(((x_96: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_97 = x_96._1;
          val x_98 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_97.==(x_98)
        }));
        val x_100 = x_99.get;
        val x_101 = x_100._2;
        positionVar_27 = x_101
      }
    else
      positionVar_27 = 15
  }));
  data_28.update(5, (() => {
    x_6.remove(0);
    val x_102 = x_6.isEmpty;
    val x_103 = x_102.`unary_!`;
    if (x_103)
      {
        val x_104 = x_6(0);
        val x_105 = x_104.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_11 = x_105
      }
    else
      ();
    val x_106 = methodArgsMut_11;
    val x_107 = x_106.asInstanceOf[scala.collection.immutable.List[scala.Int]];
    val x_108 = x_107.head;
    resetData_0 = x_108;
    val x_109 = resetData_0;
    val x_110 = x_109.asInstanceOf[scala.Int];
    bindingMut_12 = x_110;
    val x_111 = bindingMut_12;
    val x_112 = x_111.asInstanceOf[scala.Int];
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
    positionVar_27 = x_119
  }));
  data_28.update(6, (() => {
    x_6.remove(0);
    val x_120 = x_6.isEmpty;
    val x_121 = x_120.`unary_!`;
    if (x_121)
      {
        val x_122 = x_6(0);
        val x_123 = x_122.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_11 = x_123
      }
    else
      ();
    resetData_0 = 0;
    val x_124 = resetData_0;
    val x_125 = x_124.asInstanceOf[scala.Int];
    bindingMut_15 = x_125;
    positionVar_27 = 7
  }));
  data_28.update(7, (() => {
    val x_126 = bindingMut_15;
    val x_127 = x_126.asInstanceOf[scala.Int];
    val x_128 = (1).-(x_127);
    meta.deep.runtime.Actor.waitTurnList.append(x_128);
    resetData_0 = ();
    val x_129 = meta.deep.runtime.Actor.minTurn();
    val x_130 = bindingMut_15;
    val x_131 = x_130.asInstanceOf[scala.Int];
    val x_132 = x_131.+(x_129);
    resetData_0 = x_132;
    val x_133 = resetData_0;
    val x_134 = x_133.asInstanceOf[scala.Int];
    bindingMut_15 = x_134;
    positionVar_27 = 8;
    val x_135 = currentTurn;
    val x_136 = x_135.+(1);
    currentTurn = x_136
  }));
  data_28.update(8, (() => {
    val x_137 = bindingMut_15;
    val x_138 = x_137.asInstanceOf[scala.Int];
    val x_139 = x_138.<(1);
    if (x_139)
      positionVar_27 = 7
    else
      positionVar_27 = 9
  }));
  data_28.update(9, (() => {
    val x_140 = bindingMut_15;
    val x_141 = x_140.asInstanceOf[scala.Int];
    val x_142 = x_141.<(1);
    val x_143 = x_142.`unary_!`;
    if (x_143)
      positionVar_27 = 10
    else
      ()
  }));
  data_28.update(10, (() => if (true)
    positionVar_27 = 2
  else
    positionVar_27 = 11));
  data_28.update(11, (() => {
    val x_144 = true.`unary_!`;
    if (x_144)
      positionVar_27 = 12
    else
      ()
  }));
  data_28.update(12, (() => positionVar_27 = 13));
  data_28.update(13, (() => {
    positionVar_27 = 14;
    val x_145 = currentTurn;
    val x_146 = x_145.+(1);
    currentTurn = x_146
  }));
  data_28.update(14, (() => positionVar_27 = 13));
  data_28.update(15, (() => {
    val x_147 = bindingMut_14;
    val x_148 = x_147.asInstanceOf[scala.Boolean];
    val x_149 = x_148.`unary_!`;
    if (x_149)
      {
        val x_150 = methodArgsMut_11;
        val x_151 = x_150.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_152 = x_151.tail;
        resetData_0 = x_152;
        val x_153 = resetData_0;
        val x_154 = x_153.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        bindingMut_13 = x_154;
        val x_155 = bindingMut_13;
        val x_156 = x_155.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        x_6.prepend(x_156);
        val x_157 = bindingMut_13;
        val x_158 = x_157.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        val x_159 = x_158.asInstanceOf[scala.collection.immutable.List[scala.Int]];
        methodArgsMut_11 = x_159;
        positionVar_27 = 3
      }
    else
      ();
    val x_160 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_161 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_160, 5);
    val x_162 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_161);
    resetData_1.prepend(x_162)
  }));
  data_28.update(16, (() => {
    val x_163 = true.`unary_!`;
    if (x_163)
      positionVar_27 = 12
    else
      ()
  }));
  data_28
}).apply();
  
  override def run_until(until_165: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_166 = currentTurn;
      val x_167 = x_166.<=(until_165);
      x_167.&&({
        val x_168 = positionVar_27;
        val x_169 = commands_164.length;
        x_168.<(x_169)
      })
    }) 
      {
        val x_170 = positionVar_27;
        val x_171 = commands_164.apply(x_170);
        x_171.apply()
      }
    ;
    this
  }
}
