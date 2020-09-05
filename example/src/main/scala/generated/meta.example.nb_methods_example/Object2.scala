package generated.meta.example.nb_methods_example

class Object2 () extends meta.deep.runtime.Actor with Serializable {
  var deposit: Int = 500
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_5 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private val x_6 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_7: scala.Double = 0.0;
  private var methodArgsMut_8: scala.Double = 0.0;
  private var methodArgsMut_9: scala.Long = 0L;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: scala.Int = 0;
  private var bindingMut_13: scala.Any = null;
  private var listValMut_14: meta.deep.runtime.RequestMessage = null;
  private var iterMut_15: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_17: scala.Int = 0;
  
  val commands_182 = (() => {
  val data_18 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_18.update(0, (() => {
    positionVar_17 = 1;
    val x_19 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_20 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_19, 19);
    val x_21 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_20);
    resetData_1.prepend(x_21)
  }));
  data_18.update(1, (() => if (true)
    positionVar_17 = 2
  else
    positionVar_17 = 22));
  data_18.update(2, (() => {
    val x_22 = this.popRequestMessages;
    val x_23 = x_22.iterator;
    iterMut_15 = x_23;
    positionVar_17 = 3
  }));
  data_18.update(3, (() => {
    val x_24 = iterMut_15;
    val x_25 = x_24.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_26 = x_25.hasNext;
    if (x_26)
      {
        val x_27 = iterMut_15;
        val x_28 = x_27.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_29 = x_28.next();
        listValMut_14 = x_29;
        positionVar_17 = 4
      }
    else
      positionVar_17 = 12
  }));
  data_18.update(4, (() => {
    val x_30 = listValMut_14;
    val x_31 = x_30.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_32 = x_31.methodId;
    val x_33 = x_32.==(0);
    val x_34 = x_33.`unary_!`;
    if (x_34)
      positionVar_17 = 5
    else
      positionVar_17 = 8
  }));
  data_18.update(5, (() => {
    val x_35 = listValMut_14;
    val x_36 = x_35.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_37 = x_36.methodId;
    val x_38 = x_37.==(1);
    val x_39 = x_38.`unary_!`;
    if (x_39)
      {
        val x_40 = listValMut_14;
        val x_41 = x_40.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_42 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_41);
        val x_43 = this.addReceiveMessages(x_42);
        resetData_0 = x_43;
        positionVar_17 = 6
      }
    else
      positionVar_17 = 7
  }));
  data_18.update(6, (() => positionVar_17 = 3));
  data_18.update(7, (() => {
    val x_44 = listValMut_14;
    val x_45 = x_44.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_46 = x_45.methodId;
    val x_47 = x_46.==(1);
    if (x_47)
      positionVar_17 = 1
    else
      ();
    val x_48 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_49 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_48, 18);
    val x_50 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_49);
    resetData_1.prepend(x_50)
  }));
  data_18.update(8, (() => {
    val x_51 = listValMut_14;
    val x_52 = x_51.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_53 = x_52.methodId;
    val x_54 = x_53.==(0);
    if (x_54)
      {
        val x_55 = listValMut_14;
        val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_57 = x_56.argss;
        val x_58 = x_57(0);
        val x_59 = x_58(0);
        x_6.prepend(x_59);
        val x_60 = listValMut_14;
        val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_62 = x_61.argss;
        val x_63 = x_62(0);
        val x_64 = x_63(1);
        x_5.prepend(x_64);
        val x_65 = listValMut_14;
        val x_66 = x_65.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_67 = x_66.argss;
        val x_68 = x_67(0);
        val x_69 = x_68(2);
        x_4.prepend(x_69);
        val x_70 = listValMut_14;
        val x_71 = x_70.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_72 = x_71.argss;
        val x_73 = x_72(0);
        val x_74 = x_73(0);
        val x_75 = x_74.asInstanceOf[scala.Long];
        methodArgsMut_9 = x_75;
        val x_76 = listValMut_14;
        val x_77 = x_76.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_78 = x_77.argss;
        val x_79 = x_78(0);
        val x_80 = x_79(1);
        val x_81 = x_80.asInstanceOf[scala.Double];
        methodArgsMut_8 = x_81;
        val x_82 = listValMut_14;
        val x_83 = x_82.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_84 = x_83.argss;
        val x_85 = x_84(0);
        val x_86 = x_85(2);
        val x_87 = x_86.asInstanceOf[scala.Double];
        methodArgsMut_7 = x_87;
        val x_88 = methodArgsMut_9;
        val x_89 = x_88.asInstanceOf[scala.Long];
        val x_90 = "Async msg received from ".+(x_89);
        resetData_0 = x_90;
        val x_91 = resetData_0;
        val x_92 = x_91.asInstanceOf[java.lang.String];
        bindingMut_11 = x_92;
        val x_93 = bindingMut_11;
        val x_94 = x_93.asInstanceOf[java.lang.String];
        scala.Predef.println(x_94);
        resetData_0 = ();
        resetData_0 = 0;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[scala.Int];
        bindingMut_10 = x_96;
        positionVar_17 = 9
      }
    else
      ()
  }));
  data_18.update(9, (() => {
    val x_97 = bindingMut_10;
    val x_98 = x_97.asInstanceOf[scala.Int];
    val x_99 = (1).-(x_98);
    meta.deep.runtime.Actor.waitTurnList.append(x_99);
    resetData_0 = ();
    val x_100 = meta.deep.runtime.Actor.minTurn();
    val x_101 = bindingMut_10;
    val x_102 = x_101.asInstanceOf[scala.Int];
    val x_103 = x_102.+(x_100);
    resetData_0 = x_103;
    val x_104 = resetData_0;
    val x_105 = x_104.asInstanceOf[scala.Int];
    bindingMut_10 = x_105;
    positionVar_17 = 10;
    val x_106 = currentTurn;
    val x_107 = x_106.+(1);
    currentTurn = x_107
  }));
  data_18.update(10, (() => {
    val x_108 = bindingMut_10;
    val x_109 = x_108.asInstanceOf[scala.Int];
    val x_110 = x_109.<(1);
    if (x_110)
      positionVar_17 = 9
    else
      positionVar_17 = 11
  }));
  data_18.update(11, (() => {
    val x_111 = bindingMut_10;
    val x_112 = x_111.asInstanceOf[scala.Int];
    val x_113 = x_112.<(1);
    val x_114 = x_113.`unary_!`;
    if (x_114)
      {
        val x_115 = this.deposit;
        resetData_0 = x_115;
        x_6.remove(0);
        val x_116 = x_6.isEmpty;
        val x_117 = x_116.`unary_!`;
        if (x_117)
          {
            val x_118 = x_6(0);
            val x_119 = x_118.asInstanceOf[scala.Long];
            methodArgsMut_9 = x_119
          }
        else
          ();
        x_5.remove(0);
        val x_120 = x_5.isEmpty;
        val x_121 = x_120.`unary_!`;
        if (x_121)
          {
            val x_122 = x_5(0);
            val x_123 = x_122.asInstanceOf[scala.Double];
            methodArgsMut_8 = x_123
          }
        else
          ();
        x_4.remove(0);
        val x_124 = x_4.isEmpty;
        val x_125 = x_124.`unary_!`;
        if (x_125)
          {
            val x_126 = x_4(0);
            val x_127 = x_126.asInstanceOf[scala.Double];
            methodArgsMut_7 = x_127
          }
        else
          ();
        val x_128 = resetData_0;
        val x_129 = x_128.asInstanceOf[scala.Any];
        bindingMut_13 = x_129;
        val x_130 = bindingMut_13;
        val x_131 = x_130.asInstanceOf[scala.Any];
        val x_132 = listValMut_14;
        val x_133 = x_132.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_133.reply(this, x_131);
        resetData_0 = ();
        positionVar_17 = 6
      }
    else
      ()
  }));
  data_18.update(12, (() => {
    val x_134 = iterMut_15;
    val x_135 = x_134.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_136 = x_135.hasNext;
    val x_137 = x_136.`unary_!`;
    if (x_137)
      {
        resetData_0 = 0;
        val x_138 = resetData_0;
        val x_139 = x_138.asInstanceOf[scala.Int];
        bindingMut_12 = x_139;
        positionVar_17 = 13
      }
    else
      ()
  }));
  data_18.update(13, (() => {
    val x_140 = bindingMut_12;
    val x_141 = x_140.asInstanceOf[scala.Int];
    val x_142 = (1).-(x_141);
    meta.deep.runtime.Actor.waitTurnList.append(x_142);
    resetData_0 = ();
    val x_143 = meta.deep.runtime.Actor.minTurn();
    val x_144 = bindingMut_12;
    val x_145 = x_144.asInstanceOf[scala.Int];
    val x_146 = x_145.+(x_143);
    resetData_0 = x_146;
    val x_147 = resetData_0;
    val x_148 = x_147.asInstanceOf[scala.Int];
    bindingMut_12 = x_148;
    positionVar_17 = 14;
    val x_149 = currentTurn;
    val x_150 = x_149.+(1);
    currentTurn = x_150
  }));
  data_18.update(14, (() => {
    val x_151 = bindingMut_12;
    val x_152 = x_151.asInstanceOf[scala.Int];
    val x_153 = x_152.<(1);
    if (x_153)
      positionVar_17 = 13
    else
      positionVar_17 = 15
  }));
  data_18.update(15, (() => {
    val x_154 = bindingMut_12;
    val x_155 = x_154.asInstanceOf[scala.Int];
    val x_156 = x_155.<(1);
    val x_157 = x_156.`unary_!`;
    if (x_157)
      positionVar_17 = 16
    else
      ()
  }));
  data_18.update(16, (() => if (true)
    positionVar_17 = 2
  else
    positionVar_17 = 17));
  data_18.update(17, (() => {
    val x_158 = true.`unary_!`;
    if (x_158)
      {
        val x_159 = resetData_1.remove(0);
        val x_163 = x_159.find(((x_160: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_161 = x_160._1;
          val x_162 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_161.==(x_162)
        }));
        val x_164 = x_163.get;
        val x_165 = x_164._2;
        positionVar_17 = x_165
      }
    else
      ()
  }));
  data_18.update(18, (() => {
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[scala.Any];
    bindingMut_13 = x_167;
    val x_168 = bindingMut_13;
    val x_169 = x_168.asInstanceOf[scala.Any];
    val x_170 = listValMut_14;
    val x_171 = x_170.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_171.reply(this, x_169);
    resetData_0 = ();
    positionVar_17 = 6
  }));
  data_18.update(19, (() => positionVar_17 = 20));
  data_18.update(20, (() => {
    positionVar_17 = 21;
    val x_172 = currentTurn;
    val x_173 = x_172.+(1);
    currentTurn = x_173
  }));
  data_18.update(21, (() => positionVar_17 = 20));
  data_18.update(22, (() => {
    val x_174 = true.`unary_!`;
    if (x_174)
      {
        val x_175 = resetData_1.remove(0);
        val x_179 = x_175.find(((x_176: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_177 = x_176._1;
          val x_178 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_177.==(x_178)
        }));
        val x_180 = x_179.get;
        val x_181 = x_180._2;
        positionVar_17 = x_181
      }
    else
      ()
  }));
  data_18
}).apply();
  
  override def run_until(until_183: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_184 = currentTurn;
      val x_185 = x_184.<=(until_183);
      x_185.&&({
        val x_186 = positionVar_17;
        val x_187 = commands_182.length;
        x_186.<(x_187)
      })
    }) 
      {
        val x_188 = positionVar_17;
        val x_189 = commands_182.apply(x_188);
        x_189.apply()
      }
    ;
    this
  }
}
