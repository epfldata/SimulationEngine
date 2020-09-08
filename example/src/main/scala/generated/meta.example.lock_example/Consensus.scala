package generated.meta.example.lock_example

class Consensus () extends meta.deep.runtime.Actor with Serializable {
  var isLocked: Boolean = false
  var winner: Long = -1L
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private val x_4 = scala.collection.mutable.ListBuffer.apply[scala.Any]();
  private var methodArgsMut_5: scala.Long = 0L;
  private var bindingMut_6: scala.Boolean = false;
  private var bindingMut_7: scala.Boolean = false;
  private var bindingMut_8: scala.Int = 0;
  private var bindingMut_9: scala.Any = null;
  private var listValMut_10: meta.deep.runtime.RequestMessage = null;
  private var iterMut_11: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var positionVar_13: scala.Int = 0;
  
  val commands_136 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](23);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 19);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 22));
  data_14.update(2, (() => {
    val x_18 = this.popRequestMessages;
    val x_19 = x_18.iterator;
    iterMut_11 = x_19;
    positionVar_13 = 3
  }));
  data_14.update(3, (() => {
    val x_20 = iterMut_11;
    val x_21 = x_20.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_22 = x_21.hasNext;
    if (x_22)
      {
        val x_23 = iterMut_11;
        val x_24 = x_23.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_25 = x_24.next();
        listValMut_10 = x_25;
        positionVar_13 = 4
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(4, (() => {
    val x_26 = listValMut_10;
    val x_27 = x_26.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_28 = x_27.methodId;
    val x_29 = x_28.==(0);
    val x_30 = x_29.`unary_!`;
    if (x_30)
      positionVar_13 = 5
    else
      positionVar_13 = 8
  }));
  data_14.update(5, (() => {
    val x_31 = listValMut_10;
    val x_32 = x_31.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_33 = x_32.methodId;
    val x_34 = x_33.==(1);
    val x_35 = x_34.`unary_!`;
    if (x_35)
      {
        val x_36 = listValMut_10;
        val x_37 = x_36.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_38 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_37);
        val x_39 = this.addReceiveMessages(x_38);
        resetData_0 = x_39;
        positionVar_13 = 6
      }
    else
      positionVar_13 = 7
  }));
  data_14.update(6, (() => positionVar_13 = 3));
  data_14.update(7, (() => {
    val x_40 = listValMut_10;
    val x_41 = x_40.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_42 = x_41.methodId;
    val x_43 = x_42.==(1);
    if (x_43)
      positionVar_13 = 1
    else
      ();
    val x_44 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_45 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_44, 18);
    val x_46 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_45);
    resetData_1.prepend(x_46)
  }));
  data_14.update(8, (() => {
    val x_47 = listValMut_10;
    val x_48 = x_47.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_49 = x_48.methodId;
    val x_50 = x_49.==(0);
    if (x_50)
      {
        val x_51 = listValMut_10;
        val x_52 = x_51.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_53 = x_52.argss;
        val x_54 = x_53(0);
        val x_55 = x_54(0);
        x_4.prepend(x_55);
        val x_56 = listValMut_10;
        val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_58 = x_57.argss;
        val x_59 = x_58(0);
        val x_60 = x_59(0);
        val x_61 = x_60.asInstanceOf[scala.Long];
        methodArgsMut_5 = x_61;
        val x_62 = this.isLocked;
        resetData_0 = x_62;
        val x_63 = resetData_0;
        val x_64 = x_63.asInstanceOf[scala.Boolean];
        bindingMut_7 = x_64;
        val x_65 = bindingMut_7;
        val x_66 = x_65.asInstanceOf[scala.Boolean];
        val x_67 = x_66.`unary_!`;
        resetData_0 = x_67;
        val x_68 = resetData_0;
        val x_69 = x_68.asInstanceOf[scala.Boolean];
        bindingMut_6 = x_69;
        positionVar_13 = 9
      }
    else
      ()
  }));
  data_14.update(9, (() => {
    val x_70 = bindingMut_6;
    val x_71 = x_70.asInstanceOf[scala.Boolean];
    val x_72 = x_71.`unary_!`;
    if (x_72)
      positionVar_13 = 10
    else
      positionVar_13 = 11
  }));
  data_14.update(10, (() => {
    val x_73 = this.winner;
    resetData_0 = x_73;
    x_4.remove(0);
    val x_74 = x_4.isEmpty;
    val x_75 = x_74.`unary_!`;
    if (x_75)
      {
        val x_76 = x_4(0);
        val x_77 = x_76.asInstanceOf[scala.Long];
        methodArgsMut_5 = x_77
      }
    else
      ();
    val x_78 = resetData_0;
    val x_79 = x_78.asInstanceOf[scala.Any];
    bindingMut_9 = x_79;
    val x_80 = bindingMut_9;
    val x_81 = x_80.asInstanceOf[scala.Any];
    val x_82 = listValMut_10;
    val x_83 = x_82.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_83.reply(this, x_81);
    resetData_0 = ();
    positionVar_13 = 6
  }));
  data_14.update(11, (() => {
    val x_84 = bindingMut_6;
    val x_85 = x_84.asInstanceOf[scala.Boolean];
    if (x_85)
      {
        this.`isLocked_=`(true);
        resetData_0 = ();
        val x_86 = methodArgsMut_5;
        val x_87 = x_86.asInstanceOf[scala.Long];
        this.`winner_=`(x_87);
        resetData_0 = ();
        positionVar_13 = 10
      }
    else
      ()
  }));
  data_14.update(12, (() => {
    val x_88 = iterMut_11;
    val x_89 = x_88.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_90 = x_89.hasNext;
    val x_91 = x_90.`unary_!`;
    if (x_91)
      {
        this.`isLocked_=`(false);
        resetData_0 = ();
        resetData_0 = 0;
        val x_92 = resetData_0;
        val x_93 = x_92.asInstanceOf[scala.Int];
        bindingMut_8 = x_93;
        positionVar_13 = 13
      }
    else
      ()
  }));
  data_14.update(13, (() => {
    val x_94 = bindingMut_8;
    val x_95 = x_94.asInstanceOf[scala.Int];
    val x_96 = (1).-(x_95);
    meta.deep.runtime.Actor.waitTurnList.append(x_96);
    resetData_0 = ();
    val x_97 = meta.deep.runtime.Actor.minTurn();
    val x_98 = bindingMut_8;
    val x_99 = x_98.asInstanceOf[scala.Int];
    val x_100 = x_99.+(x_97);
    resetData_0 = x_100;
    val x_101 = resetData_0;
    val x_102 = x_101.asInstanceOf[scala.Int];
    bindingMut_8 = x_102;
    positionVar_13 = 14;
    val x_103 = currentTurn;
    val x_104 = x_103.+(1);
    currentTurn = x_104
  }));
  data_14.update(14, (() => {
    val x_105 = bindingMut_8;
    val x_106 = x_105.asInstanceOf[scala.Int];
    val x_107 = x_106.<(1);
    if (x_107)
      positionVar_13 = 13
    else
      positionVar_13 = 15
  }));
  data_14.update(15, (() => {
    val x_108 = bindingMut_8;
    val x_109 = x_108.asInstanceOf[scala.Int];
    val x_110 = x_109.<(1);
    val x_111 = x_110.`unary_!`;
    if (x_111)
      positionVar_13 = 16
    else
      ()
  }));
  data_14.update(16, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 17));
  data_14.update(17, (() => {
    val x_112 = true.`unary_!`;
    if (x_112)
      {
        val x_113 = resetData_1.remove(0);
        val x_117 = x_113.find(((x_114: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_115 = x_114._1;
          val x_116 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_115.==(x_116)
        }));
        val x_118 = x_117.get;
        val x_119 = x_118._2;
        positionVar_13 = x_119
      }
    else
      ()
  }));
  data_14.update(18, (() => {
    val x_120 = resetData_0;
    val x_121 = x_120.asInstanceOf[scala.Any];
    bindingMut_9 = x_121;
    val x_122 = bindingMut_9;
    val x_123 = x_122.asInstanceOf[scala.Any];
    val x_124 = listValMut_10;
    val x_125 = x_124.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_125.reply(this, x_123);
    resetData_0 = ();
    positionVar_13 = 6
  }));
  data_14.update(19, (() => positionVar_13 = 20));
  data_14.update(20, (() => {
    positionVar_13 = 21;
    val x_126 = currentTurn;
    val x_127 = x_126.+(1);
    currentTurn = x_127
  }));
  data_14.update(21, (() => positionVar_13 = 20));
  data_14.update(22, (() => {
    val x_128 = true.`unary_!`;
    if (x_128)
      {
        val x_129 = resetData_1.remove(0);
        val x_133 = x_129.find(((x_130: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_131 = x_130._1;
          val x_132 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_131.==(x_132)
        }));
        val x_134 = x_133.get;
        val x_135 = x_134._2;
        positionVar_13 = x_135
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_137: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_138 = currentTurn;
      val x_139 = x_138.<=(until_137);
      x_139.&&({
        val x_140 = positionVar_13;
        val x_141 = commands_136.length;
        x_140.<(x_141)
      })
    }) 
      {
        val x_142 = positionVar_13;
        val x_143 = commands_136.apply(x_142);
        x_143.apply()
      }
    ;
    this
  }
}