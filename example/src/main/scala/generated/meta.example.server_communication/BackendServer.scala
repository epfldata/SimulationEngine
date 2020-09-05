package generated.meta.example.server_communication

class BackendServer () extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Long = 0L;
  private var bindingMut_5: scala.Any = null;
  private var listValMut_6: meta.deep.runtime.RequestMessage = null;
  private var iterMut_7: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Int = 0;
  private var bindingMut_11: java.lang.String = null;
  private var bindingMut_12: java.lang.String = null;
  private var bindingMut_13: scala.Long = 0L;
  private var positionVar_15: scala.Int = 0;
  
  val commands_138 = (() => {
  val data_16 = new scala.Array[scala.Function0[scala.Unit]](20);
  data_16.update(0, (() => {
    positionVar_15 = 1;
    val x_17 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_18 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_17, 16);
    val x_19 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_18);
    resetData_1.prepend(x_19)
  }));
  data_16.update(1, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 19));
  data_16.update(2, (() => {
    val x_20 = this.id;
    resetData_0 = x_20;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Long];
    bindingMut_13 = x_22;
    val x_23 = bindingMut_13;
    val x_24 = x_23.asInstanceOf[scala.Long];
    val x_25 = "Hello world! Backend ".+(x_24);
    resetData_0 = x_25;
    val x_26 = resetData_0;
    val x_27 = x_26.asInstanceOf[java.lang.String];
    bindingMut_12 = x_27;
    val x_28 = bindingMut_12;
    val x_29 = x_28.asInstanceOf[java.lang.String];
    val x_30 = x_29.+(" Turn ");
    resetData_0 = x_30;
    val x_31 = resetData_0;
    val x_32 = x_31.asInstanceOf[java.lang.String];
    bindingMut_11 = x_32;
    val x_33 = this.currentTurn;
    resetData_0 = x_33;
    val x_34 = resetData_0;
    val x_35 = x_34.asInstanceOf[scala.Int];
    bindingMut_10 = x_35;
    val x_36 = bindingMut_10;
    val x_37 = x_36.asInstanceOf[scala.Int];
    val x_38 = bindingMut_11;
    val x_39 = x_38.asInstanceOf[java.lang.String];
    val x_40 = x_39.+(x_37);
    resetData_0 = x_40;
    val x_41 = resetData_0;
    val x_42 = x_41.asInstanceOf[java.lang.String];
    bindingMut_9 = x_42;
    val x_43 = bindingMut_9;
    val x_44 = x_43.asInstanceOf[java.lang.String];
    scala.Predef.println(x_44);
    resetData_0 = ();
    resetData_0 = 0;
    val x_45 = resetData_0;
    val x_46 = x_45.asInstanceOf[scala.Int];
    bindingMut_8 = x_46;
    positionVar_15 = 3
  }));
  data_16.update(3, (() => {
    val x_47 = bindingMut_8;
    val x_48 = x_47.asInstanceOf[scala.Int];
    val x_49 = (1).-(x_48);
    meta.deep.runtime.Actor.waitTurnList.append(x_49);
    resetData_0 = ();
    val x_50 = meta.deep.runtime.Actor.minTurn();
    val x_51 = bindingMut_8;
    val x_52 = x_51.asInstanceOf[scala.Int];
    val x_53 = x_52.+(x_50);
    resetData_0 = x_53;
    val x_54 = resetData_0;
    val x_55 = x_54.asInstanceOf[scala.Int];
    bindingMut_8 = x_55;
    positionVar_15 = 4;
    val x_56 = currentTurn;
    val x_57 = x_56.+(1);
    currentTurn = x_57
  }));
  data_16.update(4, (() => {
    val x_58 = bindingMut_8;
    val x_59 = x_58.asInstanceOf[scala.Int];
    val x_60 = x_59.<(1);
    if (x_60)
      positionVar_15 = 3
    else
      positionVar_15 = 5
  }));
  data_16.update(5, (() => {
    val x_61 = bindingMut_8;
    val x_62 = x_61.asInstanceOf[scala.Int];
    val x_63 = x_62.<(1);
    val x_64 = x_63.`unary_!`;
    if (x_64)
      {
        val x_65 = this.popRequestMessages;
        val x_66 = x_65.iterator;
        iterMut_7 = x_66;
        positionVar_15 = 6
      }
    else
      ()
  }));
  data_16.update(6, (() => {
    val x_67 = iterMut_7;
    val x_68 = x_67.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_69 = x_68.hasNext;
    if (x_69)
      {
        val x_70 = iterMut_7;
        val x_71 = x_70.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_72 = x_71.next();
        listValMut_6 = x_72;
        positionVar_15 = 7
      }
    else
      positionVar_15 = 12
  }));
  data_16.update(7, (() => {
    val x_73 = listValMut_6;
    val x_74 = x_73.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_75 = x_74.methodId;
    val x_76 = x_75.==(0);
    val x_77 = x_76.`unary_!`;
    if (x_77)
      positionVar_15 = 8
    else
      positionVar_15 = 11
  }));
  data_16.update(8, (() => {
    val x_78 = listValMut_6;
    val x_79 = x_78.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_80 = x_79.methodId;
    val x_81 = x_80.==(1);
    val x_82 = x_81.`unary_!`;
    if (x_82)
      {
        val x_83 = listValMut_6;
        val x_84 = x_83.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_85 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_84);
        val x_86 = this.addReceiveMessages(x_85);
        resetData_0 = x_86;
        positionVar_15 = 9
      }
    else
      positionVar_15 = 10
  }));
  data_16.update(9, (() => positionVar_15 = 6));
  data_16.update(10, (() => {
    val x_87 = listValMut_6;
    val x_88 = x_87.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_89 = x_88.methodId;
    val x_90 = x_89.==(1);
    if (x_90)
      positionVar_15 = 1
    else
      ();
    val x_91 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_92 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_91, 15);
    val x_93 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_92);
    resetData_1.prepend(x_93)
  }));
  data_16.update(11, (() => {
    val x_94 = listValMut_6;
    val x_95 = x_94.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_96 = x_95.methodId;
    val x_97 = x_96.==(0);
    if (x_97)
      {
        val x_98 = java.lang.System.nanoTime();
        resetData_0 = x_98;
        val x_99 = resetData_0;
        val x_100 = x_99.asInstanceOf[scala.Long];
        bindingMut_4 = x_100;
        val x_101 = bindingMut_4;
        val x_102 = x_101.asInstanceOf[scala.Long];
        val x_103 = x_102.toString();
        resetData_0 = x_103;
        val x_104 = resetData_0;
        val x_105 = x_104.asInstanceOf[scala.Any];
        bindingMut_5 = x_105;
        val x_106 = bindingMut_5;
        val x_107 = x_106.asInstanceOf[scala.Any];
        val x_108 = listValMut_6;
        val x_109 = x_108.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_109.reply(this, x_107);
        resetData_0 = ();
        positionVar_15 = 9
      }
    else
      ()
  }));
  data_16.update(12, (() => {
    val x_110 = iterMut_7;
    val x_111 = x_110.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_112 = x_111.hasNext;
    val x_113 = x_112.`unary_!`;
    if (x_113)
      positionVar_15 = 13
    else
      ()
  }));
  data_16.update(13, (() => if (true)
    positionVar_15 = 2
  else
    positionVar_15 = 14));
  data_16.update(14, (() => {
    val x_114 = true.`unary_!`;
    if (x_114)
      {
        val x_115 = resetData_1.remove(0);
        val x_119 = x_115.find(((x_116: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_117 = x_116._1;
          val x_118 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_117.==(x_118)
        }));
        val x_120 = x_119.get;
        val x_121 = x_120._2;
        positionVar_15 = x_121
      }
    else
      ()
  }));
  data_16.update(15, (() => {
    val x_122 = resetData_0;
    val x_123 = x_122.asInstanceOf[scala.Any];
    bindingMut_5 = x_123;
    val x_124 = bindingMut_5;
    val x_125 = x_124.asInstanceOf[scala.Any];
    val x_126 = listValMut_6;
    val x_127 = x_126.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_127.reply(this, x_125);
    resetData_0 = ();
    positionVar_15 = 9
  }));
  data_16.update(16, (() => positionVar_15 = 17));
  data_16.update(17, (() => {
    positionVar_15 = 18;
    val x_128 = currentTurn;
    val x_129 = x_128.+(1);
    currentTurn = x_129
  }));
  data_16.update(18, (() => positionVar_15 = 17));
  data_16.update(19, (() => {
    val x_130 = true.`unary_!`;
    if (x_130)
      {
        val x_131 = resetData_1.remove(0);
        val x_135 = x_131.find(((x_132: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_133 = x_132._1;
          val x_134 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_133.==(x_134)
        }));
        val x_136 = x_135.get;
        val x_137 = x_136._2;
        positionVar_15 = x_137
      }
    else
      ()
  }));
  data_16
}).apply();
  
  override def run_until(until_139: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_140 = currentTurn;
      val x_141 = x_140.<=(until_139);
      x_141.&&({
        val x_142 = positionVar_15;
        val x_143 = commands_138.length;
        x_142.<(x_143)
      })
    }) 
      {
        val x_144 = positionVar_15;
        val x_145 = commands_138.apply(x_144);
        x_145.apply()
      }
    ;
    this
  }
}
