package generated.meta.example.stateless_server

class RandomNumberServer () extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Int = 0;
  private var bindingMut_5: scala.Any = null;
  private var listValMut_6: meta.deep.runtime.RequestMessage = null;
  private var iterMut_7: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_8: scala.Int = 0;
  private var positionVar_10: scala.Int = 0;
  
  val commands_139 = (() => {
  val data_11 = new scala.Array[scala.Function0[scala.Unit]](25);
  data_11.update(0, (() => {
    positionVar_10 = 1;
    val x_12 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_13 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_12, 21);
    val x_14 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_13);
    resetData_1.prepend(x_14)
  }));
  data_11.update(1, (() => if (true)
    positionVar_10 = 2
  else
    positionVar_10 = 24));
  data_11.update(2, (() => {
    resetData_0 = 0;
    val x_15 = resetData_0;
    val x_16 = x_15.asInstanceOf[scala.Int];
    bindingMut_8 = x_16;
    positionVar_10 = 3
  }));
  data_11.update(3, (() => {
    val x_17 = bindingMut_8;
    val x_18 = x_17.asInstanceOf[scala.Int];
    val x_19 = (1).-(x_18);
    meta.deep.runtime.Actor.waitTurnList.append(x_19);
    resetData_0 = ();
    val x_20 = meta.deep.runtime.Actor.minTurn();
    val x_21 = bindingMut_8;
    val x_22 = x_21.asInstanceOf[scala.Int];
    val x_23 = x_22.+(x_20);
    resetData_0 = x_23;
    val x_24 = resetData_0;
    val x_25 = x_24.asInstanceOf[scala.Int];
    bindingMut_8 = x_25;
    positionVar_10 = 4;
    val x_26 = currentTurn;
    val x_27 = x_26.+(1);
    currentTurn = x_27
  }));
  data_11.update(4, (() => {
    val x_28 = bindingMut_8;
    val x_29 = x_28.asInstanceOf[scala.Int];
    val x_30 = x_29.<(1);
    if (x_30)
      positionVar_10 = 3
    else
      positionVar_10 = 5
  }));
  data_11.update(5, (() => {
    val x_31 = bindingMut_8;
    val x_32 = x_31.asInstanceOf[scala.Int];
    val x_33 = x_32.<(1);
    val x_34 = x_33.`unary_!`;
    if (x_34)
      {
        val x_35 = this.popRequestMessages;
        val x_36 = x_35.iterator;
        iterMut_7 = x_36;
        positionVar_10 = 6
      }
    else
      ()
  }));
  data_11.update(6, (() => {
    val x_37 = iterMut_7;
    val x_38 = x_37.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_39 = x_38.hasNext;
    if (x_39)
      {
        val x_40 = iterMut_7;
        val x_41 = x_40.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_42 = x_41.next();
        listValMut_6 = x_42;
        positionVar_10 = 7
      }
    else
      positionVar_10 = 17
  }));
  data_11.update(7, (() => {
    val x_43 = listValMut_6;
    val x_44 = x_43.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_45 = x_44.methodId;
    val x_46 = x_45.==(2);
    val x_47 = x_46.`unary_!`;
    if (x_47)
      positionVar_10 = 8
    else
      positionVar_10 = 16
  }));
  data_11.update(8, (() => {
    val x_48 = listValMut_6;
    val x_49 = x_48.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_50 = x_49.methodId;
    val x_51 = x_50.==(3);
    val x_52 = x_51.`unary_!`;
    if (x_52)
      positionVar_10 = 9
    else
      positionVar_10 = 12
  }));
  data_11.update(9, (() => {
    val x_53 = listValMut_6;
    val x_54 = x_53.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_55 = x_54.methodId;
    val x_56 = x_55.==(4);
    val x_57 = x_56.`unary_!`;
    if (x_57)
      {
        val x_58 = listValMut_6;
        val x_59 = x_58.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_60 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_59);
        val x_61 = this.addReceiveMessages(x_60);
        resetData_0 = x_61;
        positionVar_10 = 10
      }
    else
      positionVar_10 = 11
  }));
  data_11.update(10, (() => positionVar_10 = 6));
  data_11.update(11, (() => {
    val x_62 = listValMut_6;
    val x_63 = x_62.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_64 = x_63.methodId;
    val x_65 = x_64.==(4);
    if (x_65)
      positionVar_10 = 1
    else
      ();
    val x_66 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_67 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_66, 20);
    val x_68 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_67);
    resetData_1.prepend(x_68)
  }));
  data_11.update(12, (() => {
    val x_69 = listValMut_6;
    val x_70 = x_69.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_71 = x_70.methodId;
    val x_72 = x_71.==(3);
    if (x_72)
      {
        resetData_0 = 0;
        val x_73 = resetData_0;
        val x_74 = x_73.asInstanceOf[scala.Int];
        bindingMut_4 = x_74;
        positionVar_10 = 13
      }
    else
      ()
  }));
  data_11.update(13, (() => {
    val x_75 = bindingMut_4;
    val x_76 = x_75.asInstanceOf[scala.Int];
    val x_77 = (1).-(x_76);
    meta.deep.runtime.Actor.waitTurnList.append(x_77);
    resetData_0 = ();
    val x_78 = meta.deep.runtime.Actor.minTurn();
    val x_79 = bindingMut_4;
    val x_80 = x_79.asInstanceOf[scala.Int];
    val x_81 = x_80.+(x_78);
    resetData_0 = x_81;
    val x_82 = resetData_0;
    val x_83 = x_82.asInstanceOf[scala.Int];
    bindingMut_4 = x_83;
    positionVar_10 = 14;
    val x_84 = currentTurn;
    val x_85 = x_84.+(1);
    currentTurn = x_85
  }));
  data_11.update(14, (() => {
    val x_86 = bindingMut_4;
    val x_87 = x_86.asInstanceOf[scala.Int];
    val x_88 = x_87.<(1);
    if (x_88)
      positionVar_10 = 13
    else
      positionVar_10 = 15
  }));
  data_11.update(15, (() => {
    val x_89 = bindingMut_4;
    val x_90 = x_89.asInstanceOf[scala.Int];
    val x_91 = x_90.<(1);
    val x_92 = x_91.`unary_!`;
    if (x_92)
      {
        val x_93 = scala.util.Random.nextInt(50);
        resetData_0 = x_93;
        val x_94 = resetData_0;
        val x_95 = x_94.asInstanceOf[scala.Any];
        bindingMut_5 = x_95;
        val x_96 = bindingMut_5;
        val x_97 = x_96.asInstanceOf[scala.Any];
        val x_98 = listValMut_6;
        val x_99 = x_98.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_99.reply(this, x_97);
        resetData_0 = ();
        positionVar_10 = 10
      }
    else
      ()
  }));
  data_11.update(16, (() => {
    val x_100 = listValMut_6;
    val x_101 = x_100.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_102 = x_101.methodId;
    val x_103 = x_102.==(2);
    if (x_103)
      {
        val x_104 = scala.util.Random.nextInt(1000);
        resetData_0 = x_104;
        val x_105 = resetData_0;
        val x_106 = x_105.asInstanceOf[scala.Any];
        bindingMut_5 = x_106;
        val x_107 = bindingMut_5;
        val x_108 = x_107.asInstanceOf[scala.Any];
        val x_109 = listValMut_6;
        val x_110 = x_109.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_110.reply(this, x_108);
        resetData_0 = ();
        positionVar_10 = 10
      }
    else
      ()
  }));
  data_11.update(17, (() => {
    val x_111 = iterMut_7;
    val x_112 = x_111.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_113 = x_112.hasNext;
    val x_114 = x_113.`unary_!`;
    if (x_114)
      positionVar_10 = 18
    else
      ()
  }));
  data_11.update(18, (() => if (true)
    positionVar_10 = 2
  else
    positionVar_10 = 19));
  data_11.update(19, (() => {
    val x_115 = true.`unary_!`;
    if (x_115)
      {
        val x_116 = resetData_1.remove(0);
        val x_120 = x_116.find(((x_117: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_118 = x_117._1;
          val x_119 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_118.==(x_119)
        }));
        val x_121 = x_120.get;
        val x_122 = x_121._2;
        positionVar_10 = x_122
      }
    else
      ()
  }));
  data_11.update(20, (() => {
    val x_123 = resetData_0;
    val x_124 = x_123.asInstanceOf[scala.Any];
    bindingMut_5 = x_124;
    val x_125 = bindingMut_5;
    val x_126 = x_125.asInstanceOf[scala.Any];
    val x_127 = listValMut_6;
    val x_128 = x_127.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_128.reply(this, x_126);
    resetData_0 = ();
    positionVar_10 = 10
  }));
  data_11.update(21, (() => positionVar_10 = 22));
  data_11.update(22, (() => {
    positionVar_10 = 23;
    val x_129 = currentTurn;
    val x_130 = x_129.+(1);
    currentTurn = x_130
  }));
  data_11.update(23, (() => positionVar_10 = 22));
  data_11.update(24, (() => {
    val x_131 = true.`unary_!`;
    if (x_131)
      {
        val x_132 = resetData_1.remove(0);
        val x_136 = x_132.find(((x_133: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_134 = x_133._1;
          val x_135 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_134.==(x_135)
        }));
        val x_137 = x_136.get;
        val x_138 = x_137._2;
        positionVar_10 = x_138
      }
    else
      ()
  }));
  data_11
}).apply();
  
  override def run_until(until_140: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_141 = currentTurn;
      val x_142 = x_141.<=(until_140);
      x_142.&&({
        val x_143 = positionVar_10;
        val x_144 = commands_139.length;
        x_143.<(x_144)
      })
    }) 
      {
        val x_145 = positionVar_10;
        val x_146 = commands_139.apply(x_145);
        x_146.apply()
      }
    ;
    this
  }
}
