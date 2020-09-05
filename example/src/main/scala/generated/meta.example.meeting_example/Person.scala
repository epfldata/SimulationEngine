package generated.meta.example.meeting_example

class Person (var isBoss: Boolean) extends meta.deep.runtime.Actor with Serializable {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: scala.Boolean = false;
  private var bindingMut_10: java.lang.String = null;
  private var bindingMut_11: scala.Long = 0L;
  private var positionVar_13: scala.Int = 0;
  
  val commands_132 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](24);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 16);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 23));
  data_14.update(2, (() => {
    val x_18 = this.id;
    resetData_0 = x_18;
    val x_19 = resetData_0;
    val x_20 = x_19.asInstanceOf[scala.Long];
    bindingMut_11 = x_20;
    val x_21 = bindingMut_11;
    val x_22 = x_21.asInstanceOf[scala.Long];
    val x_23 = "Meeting attendee: ".+(x_22);
    resetData_0 = x_23;
    val x_24 = resetData_0;
    val x_25 = x_24.asInstanceOf[java.lang.String];
    bindingMut_10 = x_25;
    val x_26 = bindingMut_10;
    val x_27 = x_26.asInstanceOf[java.lang.String];
    scala.Predef.println(x_27);
    resetData_0 = ();
    val x_28 = this.isBoss;
    resetData_0 = x_28;
    val x_29 = resetData_0;
    val x_30 = x_29.asInstanceOf[scala.Boolean];
    bindingMut_9 = x_30;
    positionVar_13 = 3
  }));
  data_14.update(3, (() => {
    val x_31 = bindingMut_9;
    val x_32 = x_31.asInstanceOf[scala.Boolean];
    if (x_32)
      {
        resetData_0 = 0.0;
        val x_33 = resetData_0;
        val x_34 = x_33.asInstanceOf[scala.Double];
        bindingMut_8 = x_34;
        positionVar_13 = 4
      }
    else
      positionVar_13 = 19
  }));
  data_14.update(4, (() => {
    val x_35 = meta.deep.runtime.Actor.proceedLabel;
    val x_36 = x_35("MeetingGroup");
    val x_37 = bindingMut_8;
    val x_38 = x_37.asInstanceOf[scala.Double];
    val x_39 = x_38.+(x_36);
    resetData_0 = x_39;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Double];
    bindingMut_8 = x_41;
    val x_42 = meta.deep.runtime.Actor.labelVals("MeetingGroup");
    val x_43 = bindingMut_8;
    val x_44 = x_43.asInstanceOf[scala.Double];
    val x_45 = 2.0.-(x_44);
    x_42.append(x_45);
    resetData_0 = ();
    positionVar_13 = 5;
    val x_46 = currentTurn;
    val x_47 = x_46.+(1);
    currentTurn = x_47
  }));
  data_14.update(5, (() => {
    val x_48 = bindingMut_8;
    val x_49 = x_48.asInstanceOf[scala.Double];
    val x_50 = x_49.<(2.0);
    if (x_50)
      positionVar_13 = 4
    else
      positionVar_13 = 6
  }));
  data_14.update(6, (() => {
    val x_51 = bindingMut_8;
    val x_52 = x_51.asInstanceOf[scala.Double];
    val x_53 = x_52.<(2.0);
    val x_54 = x_53.`unary_!`;
    if (x_54)
      positionVar_13 = 7
    else
      ()
  }));
  data_14.update(7, (() => {
    val x_55 = this.popRequestMessages;
    val x_56 = x_55.iterator;
    iterMut_6 = x_56;
    positionVar_13 = 8
  }));
  data_14.update(8, (() => {
    val x_57 = iterMut_6;
    val x_58 = x_57.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_59 = x_58.hasNext;
    if (x_59)
      {
        val x_60 = iterMut_6;
        val x_61 = x_60.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_62 = x_61.next();
        listValMut_5 = x_62;
        positionVar_13 = 9
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(9, (() => {
    val x_63 = listValMut_5;
    val x_64 = x_63.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_65 = x_64.methodId;
    val x_66 = x_65.==(0);
    val x_67 = x_66.`unary_!`;
    if (x_67)
      {
        val x_68 = listValMut_5;
        val x_69 = x_68.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_70 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_69);
        val x_71 = this.addReceiveMessages(x_70);
        resetData_0 = x_71;
        positionVar_13 = 10
      }
    else
      positionVar_13 = 11
  }));
  data_14.update(10, (() => positionVar_13 = 8));
  data_14.update(11, (() => {
    val x_72 = listValMut_5;
    val x_73 = x_72.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_74 = x_73.methodId;
    val x_75 = x_74.==(0);
    if (x_75)
      positionVar_13 = 1
    else
      ();
    val x_76 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_77 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_76, 15);
    val x_78 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_77);
    resetData_1.prepend(x_78)
  }));
  data_14.update(12, (() => {
    val x_79 = iterMut_6;
    val x_80 = x_79.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_81 = x_80.hasNext;
    val x_82 = x_81.`unary_!`;
    if (x_82)
      positionVar_13 = 13
    else
      ()
  }));
  data_14.update(13, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 14));
  data_14.update(14, (() => {
    val x_83 = true.`unary_!`;
    if (x_83)
      {
        val x_84 = resetData_1.remove(0);
        val x_88 = x_84.find(((x_85: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_86 = x_85._1;
          val x_87 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_86.==(x_87)
        }));
        val x_89 = x_88.get;
        val x_90 = x_89._2;
        positionVar_13 = x_90
      }
    else
      ()
  }));
  data_14.update(15, (() => {
    val x_91 = resetData_0;
    val x_92 = x_91.asInstanceOf[scala.Any];
    bindingMut_4 = x_92;
    val x_93 = bindingMut_4;
    val x_94 = x_93.asInstanceOf[scala.Any];
    val x_95 = listValMut_5;
    val x_96 = x_95.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_96.reply(this, x_94);
    resetData_0 = ();
    positionVar_13 = 10
  }));
  data_14.update(16, (() => positionVar_13 = 17));
  data_14.update(17, (() => {
    positionVar_13 = 18;
    val x_97 = currentTurn;
    val x_98 = x_97.+(1);
    currentTurn = x_98
  }));
  data_14.update(18, (() => positionVar_13 = 17));
  data_14.update(19, (() => {
    val x_99 = bindingMut_9;
    val x_100 = x_99.asInstanceOf[scala.Boolean];
    val x_101 = x_100.`unary_!`;
    if (x_101)
      {
        resetData_0 = 0.0;
        val x_102 = resetData_0;
        val x_103 = x_102.asInstanceOf[scala.Double];
        bindingMut_7 = x_103;
        positionVar_13 = 20
      }
    else
      ()
  }));
  data_14.update(20, (() => {
    val x_104 = meta.deep.runtime.Actor.proceedLabel;
    val x_105 = x_104("MeetingGroup");
    val x_106 = bindingMut_7;
    val x_107 = x_106.asInstanceOf[scala.Double];
    val x_108 = x_107.+(x_105);
    resetData_0 = x_108;
    val x_109 = resetData_0;
    val x_110 = x_109.asInstanceOf[scala.Double];
    bindingMut_7 = x_110;
    val x_111 = meta.deep.runtime.Actor.labelVals("MeetingGroup");
    val x_112 = bindingMut_7;
    val x_113 = x_112.asInstanceOf[scala.Double];
    val x_114 = 1.0.-(x_113);
    x_111.append(x_114);
    resetData_0 = ();
    positionVar_13 = 21;
    val x_115 = currentTurn;
    val x_116 = x_115.+(1);
    currentTurn = x_116
  }));
  data_14.update(21, (() => {
    val x_117 = bindingMut_7;
    val x_118 = x_117.asInstanceOf[scala.Double];
    val x_119 = x_118.<(1.0);
    if (x_119)
      positionVar_13 = 20
    else
      positionVar_13 = 22
  }));
  data_14.update(22, (() => {
    val x_120 = bindingMut_7;
    val x_121 = x_120.asInstanceOf[scala.Double];
    val x_122 = x_121.<(1.0);
    val x_123 = x_122.`unary_!`;
    if (x_123)
      positionVar_13 = 7
    else
      ()
  }));
  data_14.update(23, (() => {
    val x_124 = true.`unary_!`;
    if (x_124)
      {
        val x_125 = resetData_1.remove(0);
        val x_129 = x_125.find(((x_126: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_127 = x_126._1;
          val x_128 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_127.==(x_128)
        }));
        val x_130 = x_129.get;
        val x_131 = x_130._2;
        positionVar_13 = x_131
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_133: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_134 = currentTurn;
      val x_135 = x_134.<=(until_133);
      x_135.&&({
        val x_136 = positionVar_13;
        val x_137 = commands_132.length;
        x_136.<(x_137)
      })
    }) 
      {
        val x_138 = positionVar_13;
        val x_139 = commands_132.apply(x_138);
        x_139.apply()
      }
    ;
    this
  }
}
