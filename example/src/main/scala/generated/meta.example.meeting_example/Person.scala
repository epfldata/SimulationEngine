package generated.meta.example.meeting_example

class Person (var isBoss: Boolean) extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.Any = null;
  private var listValMut_4: meta.deep.runtime.RequestMessage = null;
  private var iterMut_5: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_6: scala.Double = 0.0;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Boolean = false;
  private var bindingMut_9: java.lang.String = null;
  private var bindingMut_10: scala.Long = 0L;
  private var positionVar_12: scala.Int = 0;
  
  val commands_131 = (() => {
  val data_13 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_13.update(0, (() => {
    positionVar_12 = 1;
    val x_14 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_15 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_14, 16);
    val x_16 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_15);
    resetData_1.prepend(x_16)
  }));
  data_13.update(1, (() => if (true)
    positionVar_12 = 2
  else
    positionVar_12 = 23));
  data_13.update(2, (() => {
    val x_17 = this.id;
    resetData_0 = x_17;
    val x_18 = resetData_0;
    val x_19 = x_18.asInstanceOf[scala.Long];
    bindingMut_10 = x_19;
    val x_20 = bindingMut_10;
    val x_21 = x_20.asInstanceOf[scala.Long];
    val x_22 = "Meeting attendee: ".+(x_21);
    resetData_0 = x_22;
    val x_23 = resetData_0;
    val x_24 = x_23.asInstanceOf[java.lang.String];
    bindingMut_9 = x_24;
    val x_25 = bindingMut_9;
    val x_26 = x_25.asInstanceOf[java.lang.String];
    scala.Predef.println(x_26);
    resetData_0 = ();
    val x_27 = this.isBoss;
    resetData_0 = x_27;
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[scala.Boolean];
    bindingMut_8 = x_29;
    positionVar_12 = 3
  }));
  data_13.update(3, (() => {
    val x_30 = bindingMut_8;
    val x_31 = x_30.asInstanceOf[scala.Boolean];
    if (x_31)
      {
        resetData_0 = 0.0;
        val x_32 = resetData_0;
        val x_33 = x_32.asInstanceOf[scala.Double];
        bindingMut_7 = x_33;
        positionVar_12 = 4
      }
    else
      positionVar_12 = 19
  }));
  data_13.update(4, (() => {
    val x_34 = meta.deep.runtime.Actor.proceedLabel;
    val x_35 = x_34("MeetingGroup");
    val x_36 = bindingMut_7;
    val x_37 = x_36.asInstanceOf[scala.Double];
    val x_38 = x_37.+(x_35);
    resetData_0 = x_38;
    val x_39 = resetData_0;
    val x_40 = x_39.asInstanceOf[scala.Double];
    bindingMut_7 = x_40;
    val x_41 = meta.deep.runtime.Actor.labelVals("MeetingGroup");
    val x_42 = bindingMut_7;
    val x_43 = x_42.asInstanceOf[scala.Double];
    val x_44 = 2.0.-(x_43);
    x_41.append(x_44);
    resetData_0 = ();
    positionVar_12 = 5;
    val x_45 = currentTurn;
    val x_46 = x_45.+(1);
    currentTurn = x_46
  }));
  data_13.update(5, (() => {
    val x_47 = bindingMut_7;
    val x_48 = x_47.asInstanceOf[scala.Double];
    val x_49 = x_48.<(2.0);
    if (x_49)
      positionVar_12 = 4
    else
      positionVar_12 = 6
  }));
  data_13.update(6, (() => {
    val x_50 = bindingMut_7;
    val x_51 = x_50.asInstanceOf[scala.Double];
    val x_52 = x_51.<(2.0);
    val x_53 = x_52.`unary_!`;
    if (x_53)
      positionVar_12 = 7
    else
      ()
  }));
  data_13.update(7, (() => {
    val x_54 = this.popRequestMessages;
    val x_55 = x_54.iterator;
    iterMut_5 = x_55;
    positionVar_12 = 8
  }));
  data_13.update(8, (() => {
    val x_56 = iterMut_5;
    val x_57 = x_56.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_58 = x_57.hasNext;
    if (x_58)
      {
        val x_59 = iterMut_5;
        val x_60 = x_59.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_61 = x_60.next();
        listValMut_4 = x_61;
        positionVar_12 = 9
      }
    else
      positionVar_12 = 12
  }));
  data_13.update(9, (() => {
    val x_62 = listValMut_4;
    val x_63 = x_62.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_64 = x_63.methodId;
    val x_65 = x_64.==(0);
    val x_66 = x_65.`unary_!`;
    if (x_66)
      {
        val x_67 = listValMut_4;
        val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_69 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_68);
        val x_70 = this.addReceiveMessages(x_69);
        resetData_0 = x_70;
        positionVar_12 = 10
      }
    else
      positionVar_12 = 11
  }));
  data_13.update(10, (() => positionVar_12 = 8));
  data_13.update(11, (() => {
    val x_71 = listValMut_4;
    val x_72 = x_71.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_73 = x_72.methodId;
    val x_74 = x_73.==(0);
    if (x_74)
      positionVar_12 = 1
    else
      ();
    val x_75 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_76 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_75, 15);
    val x_77 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_76);
    resetData_1.prepend(x_77)
  }));
  data_13.update(12, (() => {
    val x_78 = iterMut_5;
    val x_79 = x_78.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_80 = x_79.hasNext;
    val x_81 = x_80.`unary_!`;
    if (x_81)
      positionVar_12 = 13
    else
      ()
  }));
  data_13.update(13, (() => if (true)
    positionVar_12 = 2
  else
    positionVar_12 = 14));
  data_13.update(14, (() => {
    val x_82 = true.`unary_!`;
    if (x_82)
      {
        val x_83 = resetData_1.remove(0);
        val x_87 = x_83.find(((x_84: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_85 = x_84._1;
          val x_86 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_85.==(x_86)
        }));
        val x_88 = x_87.get;
        val x_89 = x_88._2;
        positionVar_12 = x_89
      }
    else
      ()
  }));
  data_13.update(15, (() => {
    val x_90 = resetData_0;
    val x_91 = x_90.asInstanceOf[scala.Any];
    bindingMut_3 = x_91;
    val x_92 = bindingMut_3;
    val x_93 = x_92.asInstanceOf[scala.Any];
    val x_94 = listValMut_4;
    val x_95 = x_94.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_95.reply(this, x_93);
    resetData_0 = ();
    positionVar_12 = 10
  }));
  data_13.update(16, (() => positionVar_12 = 17));
  data_13.update(17, (() => {
    positionVar_12 = 18;
    val x_96 = currentTurn;
    val x_97 = x_96.+(1);
    currentTurn = x_97
  }));
  data_13.update(18, (() => positionVar_12 = 17));
  data_13.update(19, (() => {
    val x_98 = bindingMut_8;
    val x_99 = x_98.asInstanceOf[scala.Boolean];
    val x_100 = x_99.`unary_!`;
    if (x_100)
      {
        resetData_0 = 0.0;
        val x_101 = resetData_0;
        val x_102 = x_101.asInstanceOf[scala.Double];
        bindingMut_6 = x_102;
        positionVar_12 = 20
      }
    else
      ()
  }));
  data_13.update(20, (() => {
    val x_103 = meta.deep.runtime.Actor.proceedLabel;
    val x_104 = x_103("MeetingGroup");
    val x_105 = bindingMut_6;
    val x_106 = x_105.asInstanceOf[scala.Double];
    val x_107 = x_106.+(x_104);
    resetData_0 = x_107;
    val x_108 = resetData_0;
    val x_109 = x_108.asInstanceOf[scala.Double];
    bindingMut_6 = x_109;
    val x_110 = meta.deep.runtime.Actor.labelVals("MeetingGroup");
    val x_111 = bindingMut_6;
    val x_112 = x_111.asInstanceOf[scala.Double];
    val x_113 = 1.0.-(x_112);
    x_110.append(x_113);
    resetData_0 = ();
    positionVar_12 = 21;
    val x_114 = currentTurn;
    val x_115 = x_114.+(1);
    currentTurn = x_115
  }));
  data_13.update(21, (() => {
    val x_116 = bindingMut_6;
    val x_117 = x_116.asInstanceOf[scala.Double];
    val x_118 = x_117.<(1.0);
    if (x_118)
      positionVar_12 = 20
    else
      positionVar_12 = 22
  }));
  data_13.update(22, (() => {
    val x_119 = bindingMut_6;
    val x_120 = x_119.asInstanceOf[scala.Double];
    val x_121 = x_120.<(1.0);
    val x_122 = x_121.`unary_!`;
    if (x_122)
      positionVar_12 = 7
    else
      ()
  }));
  data_13.update(23, (() => {
    val x_123 = true.`unary_!`;
    if (x_123)
      {
        val x_124 = resetData_1.remove(0);
        val x_128 = x_124.find(((x_125: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_126 = x_125._1;
          val x_127 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_126.==(x_127)
        }));
        val x_129 = x_128.get;
        val x_130 = x_129._2;
        positionVar_12 = x_130
      }
    else
      ()
  }));
  data_13
}).apply();
  
  override def run_until(until_132: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_133 = currentTurn;
      val x_134 = x_133.<=(until_132);
      x_134.&&({
        val x_135 = positionVar_12;
        val x_136 = commands_131.length;
        x_135.<(x_136)
      })
    }) 
      {
        val x_137 = positionVar_12;
        val x_138 = commands_131.apply(x_137);
        x_138.apply()
      }
    ;
    this
  }
}
