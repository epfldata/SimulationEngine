package generated.meta.example.monitor_example.agent_monitor

class monitorSim () extends meta.deep.runtime.Actor with Serializable {
  var aggregate: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Int = 0;
  private var bindingMut_5: scala.Int = 0;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: scala.Any = null;
  private var listValMut_9: meta.deep.runtime.RequestMessage = null;
  private var iterMut_10: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_11: scala.Int = 0;
  private var positionVar_13: scala.Int = 0;
  
  val commands_153 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](25);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 17);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 24));
  data_14.update(2, (() => {
    positionVar_13 = 3;
    val x_18 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_19 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_18, 20);
    val x_20 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_19);
    resetData_1.prepend(x_20)
  }));
  data_14.update(3, (() => {
    val x_21 = this.aggregate;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Int];
    bindingMut_7 = x_23;
    val x_24 = bindingMut_7;
    val x_25 = x_24.asInstanceOf[scala.Int];
    val x_26 = "Total wins: ".+(x_25);
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[java.lang.String];
    bindingMut_6 = x_28;
    val x_29 = bindingMut_6;
    val x_30 = x_29.asInstanceOf[java.lang.String];
    scala.Predef.println(x_30);
    resetData_0 = ();
    val x_31 = resetData_1.remove(0);
    val x_35 = x_31.find(((x_32: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
      val x_33 = x_32._1;
      val x_34 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
      x_33.==(x_34)
    }));
    val x_36 = x_35.get;
    val x_37 = x_36._2;
    positionVar_13 = x_37
  }));
  data_14.update(4, (() => {
    val x_38 = resetData_0;
    val x_39 = x_38.asInstanceOf[scala.Any];
    bindingMut_8 = x_39;
    val x_40 = bindingMut_8;
    val x_41 = x_40.asInstanceOf[scala.Any];
    val x_42 = listValMut_9;
    val x_43 = x_42.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_43.reply(this, x_41);
    resetData_0 = ();
    positionVar_13 = 5
  }));
  data_14.update(5, (() => positionVar_13 = 6));
  data_14.update(6, (() => {
    val x_44 = iterMut_10;
    val x_45 = x_44.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_46 = x_45.hasNext;
    if (x_46)
      {
        val x_47 = iterMut_10;
        val x_48 = x_47.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_49 = x_48.next();
        listValMut_9 = x_49;
        positionVar_13 = 7
      }
    else
      positionVar_13 = 13
  }));
  data_14.update(7, (() => {
    val x_50 = listValMut_9;
    val x_51 = x_50.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_52 = x_51.methodId;
    val x_53 = x_52.==(1);
    val x_54 = x_53.`unary_!`;
    if (x_54)
      positionVar_13 = 8
    else
      positionVar_13 = 12
  }));
  data_14.update(8, (() => {
    val x_55 = listValMut_9;
    val x_56 = x_55.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_57 = x_56.methodId;
    val x_58 = x_57.==(2);
    val x_59 = x_58.`unary_!`;
    if (x_59)
      positionVar_13 = 9
    else
      positionVar_13 = 11
  }));
  data_14.update(9, (() => {
    val x_60 = listValMut_9;
    val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_62 = x_61.methodId;
    val x_63 = x_62.==(3);
    val x_64 = x_63.`unary_!`;
    if (x_64)
      {
        val x_65 = listValMut_9;
        val x_66 = x_65.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_67 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_66);
        val x_68 = this.addReceiveMessages(x_67);
        resetData_0 = x_68;
        positionVar_13 = 5
      }
    else
      positionVar_13 = 10
  }));
  data_14.update(10, (() => {
    val x_69 = listValMut_9;
    val x_70 = x_69.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_71 = x_70.methodId;
    val x_72 = x_71.==(3);
    if (x_72)
      positionVar_13 = 1
    else
      ();
    val x_73 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_74 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_73, 16);
    val x_75 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_74);
    resetData_1.prepend(x_75)
  }));
  data_14.update(11, (() => {
    val x_76 = listValMut_9;
    val x_77 = x_76.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_78 = x_77.methodId;
    val x_79 = x_78.==(2);
    if (x_79)
      positionVar_13 = 3
    else
      ();
    val x_80 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_81 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_80, 4);
    val x_82 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_81);
    resetData_1.prepend(x_82)
  }));
  data_14.update(12, (() => {
    val x_83 = listValMut_9;
    val x_84 = x_83.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_85 = x_84.methodId;
    val x_86 = x_85.==(1);
    if (x_86)
      {
        val x_87 = this.aggregate;
        resetData_0 = x_87;
        val x_88 = resetData_0;
        val x_89 = x_88.asInstanceOf[scala.Int];
        bindingMut_5 = x_89;
        val x_90 = bindingMut_5;
        val x_91 = x_90.asInstanceOf[scala.Int];
        val x_92 = x_91.+(1);
        resetData_0 = x_92;
        val x_93 = resetData_0;
        val x_94 = x_93.asInstanceOf[scala.Int];
        bindingMut_4 = x_94;
        val x_95 = bindingMut_4;
        val x_96 = x_95.asInstanceOf[scala.Int];
        this.`aggregate_=`(x_96);
        resetData_0 = ();
        val x_97 = resetData_0;
        val x_98 = x_97.asInstanceOf[scala.Any];
        bindingMut_8 = x_98;
        val x_99 = bindingMut_8;
        val x_100 = x_99.asInstanceOf[scala.Any];
        val x_101 = listValMut_9;
        val x_102 = x_101.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_102.reply(this, x_100);
        resetData_0 = ();
        positionVar_13 = 5
      }
    else
      ()
  }));
  data_14.update(13, (() => {
    val x_103 = iterMut_10;
    val x_104 = x_103.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_105 = x_104.hasNext;
    val x_106 = x_105.`unary_!`;
    if (x_106)
      positionVar_13 = 14
    else
      ()
  }));
  data_14.update(14, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 15));
  data_14.update(15, (() => {
    val x_107 = true.`unary_!`;
    if (x_107)
      {
        val x_108 = resetData_1.remove(0);
        val x_112 = x_108.find(((x_109: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_110 = x_109._1;
          val x_111 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_110.==(x_111)
        }));
        val x_113 = x_112.get;
        val x_114 = x_113._2;
        positionVar_13 = x_114
      }
    else
      ()
  }));
  data_14.update(16, (() => {
    val x_115 = resetData_0;
    val x_116 = x_115.asInstanceOf[scala.Any];
    bindingMut_8 = x_116;
    val x_117 = bindingMut_8;
    val x_118 = x_117.asInstanceOf[scala.Any];
    val x_119 = listValMut_9;
    val x_120 = x_119.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_120.reply(this, x_118);
    resetData_0 = ();
    positionVar_13 = 5
  }));
  data_14.update(17, (() => positionVar_13 = 18));
  data_14.update(18, (() => {
    positionVar_13 = 19;
    val x_121 = currentTurn;
    val x_122 = x_121.+(1);
    currentTurn = x_122
  }));
  data_14.update(19, (() => positionVar_13 = 18));
  data_14.update(20, (() => {
    resetData_0 = 0;
    val x_123 = resetData_0;
    val x_124 = x_123.asInstanceOf[scala.Int];
    bindingMut_11 = x_124;
    positionVar_13 = 21
  }));
  data_14.update(21, (() => {
    val x_125 = bindingMut_11;
    val x_126 = x_125.asInstanceOf[scala.Int];
    val x_127 = (1).-(x_126);
    meta.deep.runtime.Actor.waitTurnList.append(x_127);
    resetData_0 = ();
    val x_128 = meta.deep.runtime.Actor.minTurn();
    val x_129 = bindingMut_11;
    val x_130 = x_129.asInstanceOf[scala.Int];
    val x_131 = x_130.+(x_128);
    resetData_0 = x_131;
    val x_132 = resetData_0;
    val x_133 = x_132.asInstanceOf[scala.Int];
    bindingMut_11 = x_133;
    positionVar_13 = 22;
    val x_134 = currentTurn;
    val x_135 = x_134.+(1);
    currentTurn = x_135
  }));
  data_14.update(22, (() => {
    val x_136 = bindingMut_11;
    val x_137 = x_136.asInstanceOf[scala.Int];
    val x_138 = x_137.<(1);
    if (x_138)
      positionVar_13 = 21
    else
      positionVar_13 = 23
  }));
  data_14.update(23, (() => {
    val x_139 = bindingMut_11;
    val x_140 = x_139.asInstanceOf[scala.Int];
    val x_141 = x_140.<(1);
    val x_142 = x_141.`unary_!`;
    if (x_142)
      {
        val x_143 = this.popRequestMessages;
        val x_144 = x_143.iterator;
        iterMut_10 = x_144;
        positionVar_13 = 6
      }
    else
      ()
  }));
  data_14.update(24, (() => {
    val x_145 = true.`unary_!`;
    if (x_145)
      {
        val x_146 = resetData_1.remove(0);
        val x_150 = x_146.find(((x_147: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_148 = x_147._1;
          val x_149 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_148.==(x_149)
        }));
        val x_151 = x_150.get;
        val x_152 = x_151._2;
        positionVar_13 = x_152
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_154: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_155 = currentTurn;
      val x_156 = x_155.<=(until_154);
      x_156.&&({
        val x_157 = positionVar_13;
        val x_158 = commands_153.length;
        x_157.<(x_158)
      })
    }) 
      {
        val x_159 = positionVar_13;
        val x_160 = commands_153.apply(x_159);
        x_160.apply()
      }
    ;
    this
  }
}
