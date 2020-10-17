package generated.meta.example.helloWorld

class TimidPerson (var name: String) extends meta.deep.runtime.Actor with meta.example.helloWorld.Person {
  var outgoing: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val x_3 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_4: java.lang.String = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.Function1[java.lang.String, java.lang.String] = null;
  private var bindingMut_7: org.slf4j.Logger = null;
  private var bindingMut_8: scala.Any = null;
  private var listValMut_9: meta.deep.runtime.RequestMessage = null;
  private var iterMut_10: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_11: scala.Double = 0.0;
  private var bindingMut_12: scala.Double = 0.0;
  private var positionVar_14: scala.Int = 0;
  
  val commands_149 = (() => {
  val data_15 = new scala.Array[scala.Function0[scala.Unit]](20);
  data_15.update(0, (() => {
    positionVar_14 = 1;
    val x_16 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_17 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_16, 16);
    val x_18 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_17);
    resetData_1.prepend(x_18)
  }));
  data_15.update(1, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 19));
  data_15.update(2, (() => {
    val x_19 = 3.toDouble;
    resetData_0 = x_19;
    val x_20 = resetData_0;
    val x_21 = x_20.asInstanceOf[scala.Double];
    bindingMut_12 = x_21;
    resetData_0 = 0.0;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Double];
    bindingMut_11 = x_23;
    positionVar_14 = 3
  }));
  data_15.update(3, (() => {
    val x_24 = meta.deep.runtime.Actor.proceedLabel;
    val x_25 = x_24("turn");
    val x_26 = bindingMut_11;
    val x_27 = x_26.asInstanceOf[scala.Double];
    val x_28 = x_27.+(x_25);
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Double];
    bindingMut_11 = x_30;
    val x_31 = meta.deep.runtime.Actor.labelVals("turn");
    val x_32 = bindingMut_11;
    val x_33 = x_32.asInstanceOf[scala.Double];
    val x_34 = bindingMut_12;
    val x_35 = x_34.asInstanceOf[scala.Double];
    val x_36 = x_35.-(x_33);
    x_31.append(x_36);
    resetData_0 = ();
    positionVar_14 = 4;
    val x_37 = currentTurn;
    val x_38 = x_37.+(1);
    currentTurn = x_38
  }));
  data_15.update(4, (() => {
    val x_39 = bindingMut_11;
    val x_40 = x_39.asInstanceOf[scala.Double];
    val x_41 = bindingMut_12;
    val x_42 = x_41.asInstanceOf[scala.Double];
    val x_43 = x_40.<(x_42);
    if (x_43)
      positionVar_14 = 3
    else
      positionVar_14 = 5
  }));
  data_15.update(5, (() => {
    val x_44 = bindingMut_11;
    val x_45 = x_44.asInstanceOf[scala.Double];
    val x_46 = bindingMut_12;
    val x_47 = x_46.asInstanceOf[scala.Double];
    val x_48 = x_45.<(x_47);
    val x_49 = x_48.`unary_!`;
    if (x_49)
      {
        val x_50 = this.popRequestMessages;
        val x_51 = x_50.iterator;
        iterMut_10 = x_51;
        positionVar_14 = 6
      }
    else
      ()
  }));
  data_15.update(6, (() => {
    val x_52 = iterMut_10;
    val x_53 = x_52.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_54 = x_53.hasNext;
    if (x_54)
      {
        val x_55 = iterMut_10;
        val x_56 = x_55.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_57 = x_56.next();
        listValMut_9 = x_57;
        positionVar_14 = 7
      }
    else
      positionVar_14 = 12
  }));
  data_15.update(7, (() => {
    val x_58 = listValMut_9;
    val x_59 = x_58.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_60 = x_59.methodId;
    val x_61 = x_60.==(0);
    val x_62 = x_61.`unary_!`;
    if (x_62)
      positionVar_14 = 8
    else
      positionVar_14 = 11
  }));
  data_15.update(8, (() => {
    val x_63 = listValMut_9;
    val x_64 = x_63.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_65 = x_64.methodId;
    val x_66 = x_65.==(1);
    val x_67 = x_66.`unary_!`;
    if (x_67)
      {
        val x_68 = listValMut_9;
        val x_69 = x_68.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_70 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_69);
        val x_71 = this.addReceiveMessages(x_70);
        resetData_0 = x_71;
        positionVar_14 = 9
      }
    else
      positionVar_14 = 10
  }));
  data_15.update(9, (() => positionVar_14 = 6));
  data_15.update(10, (() => {
    val x_72 = listValMut_9;
    val x_73 = x_72.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_74 = x_73.methodId;
    val x_75 = x_74.==(1);
    if (x_75)
      positionVar_14 = 1
    else
      ();
    val x_76 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_77 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_76, 15);
    val x_78 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_77);
    resetData_1.prepend(x_78)
  }));
  data_15.update(11, (() => {
    val x_79 = listValMut_9;
    val x_80 = x_79.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_81 = x_80.methodId;
    val x_82 = x_81.==(0);
    if (x_82)
      {
        val x_83 = listValMut_9;
        val x_84 = x_83.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_85 = x_84.argss;
        val x_86 = x_85(0);
        val x_87 = x_86(0);
        x_3.prepend(x_87);
        val x_88 = listValMut_9;
        val x_89 = x_88.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_90 = x_89.argss;
        val x_91 = x_90(0);
        val x_92 = x_91(0);
        val x_93 = x_92.asInstanceOf[java.lang.String];
        methodArgsMut_4 = x_93;
        val x_94 = this.logger;
        resetData_0 = x_94;
        val x_95 = resetData_0;
        val x_96 = x_95.asInstanceOf[org.slf4j.Logger];
        bindingMut_7 = x_96;
        val x_97 = meta.example.helloWorld.Person.selfIntroduction;
        resetData_0 = x_97;
        val x_98 = resetData_0;
        val x_99 = x_98.asInstanceOf[scala.Function1[java.lang.String, java.lang.String]];
        bindingMut_6 = x_99;
        val x_100 = methodArgsMut_4;
        val x_101 = x_100.asInstanceOf[java.lang.String];
        val x_102 = bindingMut_6;
        val x_103 = x_102.asInstanceOf[scala.Function1[java.lang.String, java.lang.String]];
        val x_104 = x_103(x_101);
        resetData_0 = x_104;
        val x_105 = resetData_0;
        val x_106 = x_105.asInstanceOf[java.lang.String];
        bindingMut_5 = x_106;
        val x_107 = bindingMut_5;
        val x_108 = x_107.asInstanceOf[java.lang.String];
        val x_109 = bindingMut_7;
        val x_110 = x_109.asInstanceOf[org.slf4j.Logger];
        x_110.info(x_108);
        resetData_0 = ();
        x_3.remove(0);
        val x_111 = x_3.isEmpty;
        val x_112 = x_111.`unary_!`;
        if (x_112)
          {
            val x_113 = x_3(0);
            val x_114 = x_113.asInstanceOf[java.lang.String];
            methodArgsMut_4 = x_114
          }
        else
          ();
        val x_115 = resetData_0;
        val x_116 = x_115.asInstanceOf[scala.Any];
        bindingMut_8 = x_116;
        val x_117 = bindingMut_8;
        val x_118 = x_117.asInstanceOf[scala.Any];
        val x_119 = listValMut_9;
        val x_120 = x_119.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_120.reply(this, x_118);
        resetData_0 = ();
        positionVar_14 = 9
      }
    else
      ()
  }));
  data_15.update(12, (() => {
    val x_121 = iterMut_10;
    val x_122 = x_121.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_123 = x_122.hasNext;
    val x_124 = x_123.`unary_!`;
    if (x_124)
      positionVar_14 = 13
    else
      ()
  }));
  data_15.update(13, (() => if (true)
    positionVar_14 = 2
  else
    positionVar_14 = 14));
  data_15.update(14, (() => {
    val x_125 = true.`unary_!`;
    if (x_125)
      {
        val x_126 = resetData_1.remove(0);
        val x_130 = x_126.find(((x_127: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_128 = x_127._1;
          val x_129 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_128.==(x_129)
        }));
        val x_131 = x_130.get;
        val x_132 = x_131._2;
        positionVar_14 = x_132
      }
    else
      ()
  }));
  data_15.update(15, (() => {
    val x_133 = resetData_0;
    val x_134 = x_133.asInstanceOf[scala.Any];
    bindingMut_8 = x_134;
    val x_135 = bindingMut_8;
    val x_136 = x_135.asInstanceOf[scala.Any];
    val x_137 = listValMut_9;
    val x_138 = x_137.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_138.reply(this, x_136);
    resetData_0 = ();
    positionVar_14 = 9
  }));
  data_15.update(16, (() => positionVar_14 = 17));
  data_15.update(17, (() => {
    positionVar_14 = 18;
    val x_139 = currentTurn;
    val x_140 = x_139.+(1);
    currentTurn = x_140
  }));
  data_15.update(18, (() => positionVar_14 = 17));
  data_15.update(19, (() => {
    val x_141 = true.`unary_!`;
    if (x_141)
      {
        val x_142 = resetData_1.remove(0);
        val x_146 = x_142.find(((x_143: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_144 = x_143._1;
          val x_145 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_144.==(x_145)
        }));
        val x_147 = x_146.get;
        val x_148 = x_147._2;
        positionVar_14 = x_148
      }
    else
      ()
  }));
  data_15
}).apply();
  
  override def run_until(until_150: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_151 = currentTurn;
      val x_152 = x_151.<=(until_150);
      x_152.&&({
        val x_153 = positionVar_14;
        val x_154 = commands_149.length;
        x_153.<(x_154)
      })
    }) 
      {
        val x_155 = positionVar_14;
        val x_156 = commands_149.apply(x_155);
        x_156.apply()
      }
    ;
    this
  }
}
