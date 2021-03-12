package generated.example.distributedGraph.shortestPath.BellmanFord

class WeightedChannel (val sender: example.distributedGraph.TreeNode, val receiver: example.distributedGraph.TreeNode, val weight: Int) extends meta.runtime.Actor {


  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_4: scala.Long = 0L;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.StringContext = null;
  private var bindingMut_7: scala.Tuple2[example.distributedGraph.TreeNode, scala.Int] = null;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: example.distributedGraph.TreeNode = null;
  private var bindingMut_10: scala.Boolean = false;
  private var bindingMut_11: scala.Long = 0L;
  private var bindingMut_12: example.distributedGraph.TreeNode = null;
  private var bindingMut_13: scala.Tuple2[example.distributedGraph.TreeNode, scala.Int] = null;
  private var bindingMut_14: scala.Int = 0;
  private var bindingMut_15: example.distributedGraph.TreeNode = null;
  private var bindingMut_16: scala.Boolean = false;
  private var bindingMut_17: scala.Long = 0L;
  private var bindingMut_18: example.distributedGraph.TreeNode = null;
  private var bindingMut_19: scala.Double = 0.0;
  private var bindingMut_20: scala.Any = null;
  private var listValMut_21: meta.runtime.RequestMessage = null;
  @transient private var iterMut_22: scala.collection.Iterator[meta.runtime.RequestMessage] = null;

  private var positionVar_24: scala.Int = 0;
  
  val commands_184 = (() => {
  val data_25 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_25.update(0, (() => positionVar_24 = 1));
  data_25.update(1, (() => if (true)
    positionVar_24 = 2
  else
    positionVar_24 = 22));
  data_25.update(2, (() => {
    val x_26 = this.popRequestMessages;
    val x_27 = x_26.iterator;
    iterMut_22 = x_27;
    positionVar_24 = 3
  }));
  data_25.update(3, (() => {
    val x_28 = iterMut_22;
    val x_29 = x_28.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_30 = x_29.hasNext;
    if (x_30)
      {
        val x_31 = iterMut_22;
        val x_32 = x_31.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
        val x_33 = x_32.next();
        listValMut_21 = x_33;
        positionVar_24 = 4
      }
    else
      positionVar_24 = 12
  }));
  data_25.update(4, (() => {
    val x_34 = listValMut_21;
    val x_35 = x_34.asInstanceOf[meta.runtime.RequestMessage];
    val x_36 = x_35.methodId;
    val x_37 = x_36.==(4);
    val x_38 = x_37.`unary_!`;
    if (x_38)
      {
        val x_39 = listValMut_21;
        val x_40 = x_39.asInstanceOf[meta.runtime.RequestMessage];
        val x_41 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_40);
        val x_42 = this.addReceiveMessages(x_41);
        resetData_0 = x_42;
        positionVar_24 = 5
      }
    else
      positionVar_24 = 6
  }));
  data_25.update(5, (() => positionVar_24 = 3));
  data_25.update(6, (() => {
    val x_43 = listValMut_21;
    val x_44 = x_43.asInstanceOf[meta.runtime.RequestMessage];
    val x_45 = x_44.methodId;
    val x_46 = x_45.==(4);
    if (x_46)
      {
        val x_47 = listValMut_21;
        val x_48 = x_47.asInstanceOf[meta.runtime.RequestMessage];
        val x_49 = x_48.argss;
        val x_50 = x_49(0);
        val x_51 = x_50(0);
        x_3.prepend(x_51);
        val x_52 = listValMut_21;
        val x_53 = x_52.asInstanceOf[meta.runtime.RequestMessage];
        val x_54 = x_53.argss;
        val x_55 = x_54(0);
        val x_56 = x_55(0);
        val x_57 = x_56.asInstanceOf[scala.Long];
        methodArgsMut_4 = x_57;
        val x_58 = this.sender;
        resetData_0 = x_58;
        val x_59 = resetData_0;
        val x_60 = x_59.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_18 = x_60;
        val x_61 = bindingMut_18;
        val x_62 = x_61.asInstanceOf[example.distributedGraph.TreeNode];
        val x_63 = x_62.id;
        resetData_0 = x_63;
        val x_64 = resetData_0;
        val x_65 = x_64.asInstanceOf[scala.Long];
        bindingMut_17 = x_65;
        val x_66 = methodArgsMut_4;
        val x_67 = x_66.asInstanceOf[scala.Long];
        val x_68 = bindingMut_17;
        val x_69 = x_68.asInstanceOf[scala.Long];
        val x_70 = x_69.==(x_67);
        resetData_0 = x_70;
        val x_71 = resetData_0;
        val x_72 = x_71.asInstanceOf[scala.Boolean];
        bindingMut_16 = x_72;
        positionVar_24 = 7
      }
    else
      ()
  }));
  data_25.update(7, (() => {
    val x_73 = bindingMut_16;
    val x_74 = x_73.asInstanceOf[scala.Boolean];
    if (x_74)
      {
        val x_75 = this.receiver;
        resetData_0 = x_75;
        val x_76 = resetData_0;
        val x_77 = x_76.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_15 = x_77;
        val x_78 = this.weight;
        resetData_0 = x_78;
        val x_79 = resetData_0;
        val x_80 = x_79.asInstanceOf[scala.Int];
        bindingMut_14 = x_80;
        val x_81 = bindingMut_14;
        val x_82 = x_81.asInstanceOf[scala.Int];
        val x_83 = bindingMut_15;
        val x_84 = x_83.asInstanceOf[example.distributedGraph.TreeNode];
        val x_85 = scala.Tuple2.apply[example.distributedGraph.TreeNode, scala.Int](x_84, x_82);
        resetData_0 = x_85;
        val x_86 = resetData_0;
        val x_87 = x_86.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        bindingMut_13 = x_87;
        val x_88 = bindingMut_13;
        val x_89 = x_88.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_90 = scala.Some.apply[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]](x_89);
        resetData_0 = x_90;
        positionVar_24 = 8
      }
    else
      positionVar_24 = 9
  }));
  data_25.update(8, (() => {
    x_3.remove(0);
    val x_91 = x_3.isEmpty;
    val x_92 = x_91.`unary_!`;
    if (x_92)
      {
        val x_93 = x_3(0);
        val x_94 = x_93.asInstanceOf[scala.Long];
        methodArgsMut_4 = x_94
      }
    else
      ();
    val x_95 = resetData_0;
    val x_96 = x_95.asInstanceOf[scala.Any];
    bindingMut_20 = x_96;
    val x_97 = bindingMut_20;
    val x_98 = x_97.asInstanceOf[scala.Any];
    val x_99 = listValMut_21;
    val x_100 = x_99.asInstanceOf[meta.runtime.RequestMessage];
    x_100.reply(this, x_98);
    resetData_0 = ();
    positionVar_24 = 5
  }));
  data_25.update(9, (() => {
    val x_101 = bindingMut_16;
    val x_102 = x_101.asInstanceOf[scala.Boolean];
    val x_103 = x_102.`unary_!`;
    if (x_103)
      {
        val x_104 = this.receiver;
        resetData_0 = x_104;
        val x_105 = resetData_0;
        val x_106 = x_105.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_12 = x_106;
        val x_107 = bindingMut_12;
        val x_108 = x_107.asInstanceOf[example.distributedGraph.TreeNode];
        val x_109 = x_108.id;
        resetData_0 = x_109;
        val x_110 = resetData_0;
        val x_111 = x_110.asInstanceOf[scala.Long];
        bindingMut_11 = x_111;
        val x_112 = methodArgsMut_4;
        val x_113 = x_112.asInstanceOf[scala.Long];
        val x_114 = bindingMut_11;
        val x_115 = x_114.asInstanceOf[scala.Long];
        val x_116 = x_115.==(x_113);
        resetData_0 = x_116;
        val x_117 = resetData_0;
        val x_118 = x_117.asInstanceOf[scala.Boolean];
        bindingMut_10 = x_118;
        positionVar_24 = 10
      }
    else
      ()
  }));
  data_25.update(10, (() => {
    val x_119 = bindingMut_10;
    val x_120 = x_119.asInstanceOf[scala.Boolean];
    if (x_120)
      {
        val x_121 = this.sender;
        resetData_0 = x_121;
        val x_122 = resetData_0;
        val x_123 = x_122.asInstanceOf[example.distributedGraph.TreeNode];
        bindingMut_9 = x_123;
        val x_124 = this.weight;
        resetData_0 = x_124;
        val x_125 = resetData_0;
        val x_126 = x_125.asInstanceOf[scala.Int];
        bindingMut_8 = x_126;
        val x_127 = bindingMut_8;
        val x_128 = x_127.asInstanceOf[scala.Int];
        val x_129 = bindingMut_9;
        val x_130 = x_129.asInstanceOf[example.distributedGraph.TreeNode];
        val x_131 = scala.Tuple2.apply[example.distributedGraph.TreeNode, scala.Int](x_130, x_128);
        resetData_0 = x_131;
        val x_132 = resetData_0;
        val x_133 = x_132.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        bindingMut_7 = x_133;
        val x_134 = bindingMut_7;
        val x_135 = x_134.asInstanceOf[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]];
        val x_136 = scala.Some.apply[scala.Tuple2[example.distributedGraph.TreeNode, scala.Int]](x_135);
        resetData_0 = x_136;
        positionVar_24 = 8
      }
    else
      positionVar_24 = 11
  }));
  data_25.update(11, (() => {
    val x_137 = bindingMut_10;
    val x_138 = x_137.asInstanceOf[scala.Boolean];
    val x_139 = x_138.`unary_!`;
    if (x_139)
      {
        val x_140 = scala.StringContext.apply("Invalid channel for ", "!");
        resetData_0 = x_140;
        val x_141 = resetData_0;
        val x_142 = x_141.asInstanceOf[scala.StringContext];
        bindingMut_6 = x_142;
        val x_143 = methodArgsMut_4;
        val x_144 = x_143.asInstanceOf[scala.Long];
        val x_145 = bindingMut_6;
        val x_146 = x_145.asInstanceOf[scala.StringContext];
        val x_147 = x_146.s(x_144);
        resetData_0 = x_147;
        val x_148 = resetData_0;
        val x_149 = x_148.asInstanceOf[java.lang.String];
        bindingMut_5 = x_149;
        val x_150 = bindingMut_5;
        val x_151 = x_150.asInstanceOf[java.lang.String];
        scala.Predef.println(x_151);
        resetData_0 = ();
        resetData_0 = scala.None;
        positionVar_24 = 8
      }
    else
      ()
  }));
  data_25.update(12, (() => {
    val x_152 = iterMut_22;
    val x_153 = x_152.asInstanceOf[scala.collection.Iterator[meta.runtime.RequestMessage]];
    val x_154 = x_153.hasNext;
    val x_155 = x_154.`unary_!`;
    if (x_155)
      {
        resetData_0 = 0.0;
        val x_156 = resetData_0;
        val x_157 = x_156.asInstanceOf[scala.Double];
        bindingMut_19 = x_157;
        positionVar_24 = 13
      }
    else
      ()
  }));
  data_25.update(13, (() => {
    val x_158 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_159 = meta.runtime.SimRuntime.labelVals(x_158);
    val x_160 = bindingMut_19;
    val x_161 = x_160.asInstanceOf[scala.Double];
    val x_162 = 1.0.-(x_161);
    x_159.append(x_162);
    resetData_0 = ();
    positionVar_24 = 14;
    val x_163 = currentTurn;
    val x_164 = x_163.+(1);
    currentTurn = x_164
  }));
  data_25.update(14, (() => {
    val x_165 = meta.runtime.SimRuntime.proceedLabel;
    val x_166 = meta.classLifting.SpecialInstructions.Turn.toString();
    val x_167 = x_165(x_166);
    val x_168 = bindingMut_19;
    val x_169 = x_168.asInstanceOf[scala.Double];
    val x_170 = x_169.+(x_167);
    resetData_0 = x_170;
    val x_171 = resetData_0;
    val x_172 = x_171.asInstanceOf[scala.Double];
    bindingMut_19 = x_172;
    positionVar_24 = 15
  }));
  data_25.update(15, (() => {
    val x_173 = bindingMut_19;
    val x_174 = x_173.asInstanceOf[scala.Double];
    val x_175 = x_174.<(1.0);
    if (x_175)
      positionVar_24 = 13
    else
      positionVar_24 = 16
  }));
  data_25.update(16, (() => {
    val x_176 = bindingMut_19;
    val x_177 = x_176.asInstanceOf[scala.Double];
    val x_178 = x_177.<(1.0);
    val x_179 = x_178.`unary_!`;
    if (x_179)
      positionVar_24 = 17
    else
      ()
  }));
  data_25.update(17, (() => if (true)
    positionVar_24 = 2
  else
    positionVar_24 = 18));
  data_25.update(18, (() => {
    val x_180 = true.`unary_!`;
    if (x_180)
      positionVar_24 = 19
    else
      ()
  }));
  data_25.update(19, (() => positionVar_24 = 20));
  data_25.update(20, (() => {
    positionVar_24 = 21;
    val x_181 = currentTurn;
    val x_182 = x_181.+(1);
    currentTurn = x_182
  }));
  data_25.update(21, (() => positionVar_24 = 20));
  data_25.update(22, (() => {
    val x_183 = true.`unary_!`;
    if (x_183)
      positionVar_24 = 19
    else
      ()
  }));
  data_25
}).apply();
  
  override def run_until(until_185: scala.Int) : meta.runtime.Actor =  {
    while ({
      val x_186 = currentTurn;
      val x_187 = x_186.<=(until_185);
      x_187.&&({
        val x_188 = positionVar_24;
        val x_189 = commands_184.length;
        x_188.<(x_189)
      })
    }) 
      {
        val x_190 = positionVar_24;
        val x_191 = commands_184.apply(x_190);
        x_191.apply()
      }
    ;
    this
  }
}
