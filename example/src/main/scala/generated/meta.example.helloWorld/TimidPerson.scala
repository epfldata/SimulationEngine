package generated.meta.example.helloWorld

class TimidPerson (var name: String) extends meta.deep.runtime.Actor with meta.example.helloWorld.Person with Serializable {
  var outgoing: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: scala.Function1[java.lang.String, java.lang.String] = null;
  private var bindingMut_7: org.slf4j.Logger = null;
  private var bindingMut_8: scala.Any = null;
  private var listValMut_9: meta.deep.runtime.RequestMessage = null;
  private var iterMut_10: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_11: scala.Int = 0;
  private var positionVar_13: scala.Int = 0;
  
  val commands_125 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](20);
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
    positionVar_13 = 19));
  data_14.update(2, (() => {
    resetData_0 = 0;
    val x_18 = resetData_0;
    val x_19 = x_18.asInstanceOf[scala.Int];
    bindingMut_11 = x_19;
    positionVar_13 = 3
  }));
  data_14.update(3, (() => {
    val x_20 = bindingMut_11;
    val x_21 = x_20.asInstanceOf[scala.Int];
    val x_22 = (3).-(x_21);
    meta.deep.runtime.Actor.waitTurnList.append(x_22);
    resetData_0 = ();
    val x_23 = meta.deep.runtime.Actor.minTurn();
    val x_24 = bindingMut_11;
    val x_25 = x_24.asInstanceOf[scala.Int];
    val x_26 = x_25.+(x_23);
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Int];
    bindingMut_11 = x_28;
    positionVar_13 = 4;
    val x_29 = currentTurn;
    val x_30 = x_29.+(1);
    currentTurn = x_30
  }));
  data_14.update(4, (() => {
    val x_31 = bindingMut_11;
    val x_32 = x_31.asInstanceOf[scala.Int];
    val x_33 = x_32.<(3);
    if (x_33)
      positionVar_13 = 3
    else
      positionVar_13 = 5
  }));
  data_14.update(5, (() => {
    val x_34 = bindingMut_11;
    val x_35 = x_34.asInstanceOf[scala.Int];
    val x_36 = x_35.<(3);
    val x_37 = x_36.`unary_!`;
    if (x_37)
      {
        val x_38 = this.popRequestMessages;
        val x_39 = x_38.iterator;
        iterMut_10 = x_39;
        positionVar_13 = 6
      }
    else
      ()
  }));
  data_14.update(6, (() => {
    val x_40 = iterMut_10;
    val x_41 = x_40.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_42 = x_41.hasNext;
    if (x_42)
      {
        val x_43 = iterMut_10;
        val x_44 = x_43.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_45 = x_44.next();
        listValMut_9 = x_45;
        positionVar_13 = 7
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(7, (() => {
    val x_46 = listValMut_9;
    val x_47 = x_46.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_48 = x_47.methodId;
    val x_49 = x_48.==(0);
    val x_50 = x_49.`unary_!`;
    if (x_50)
      positionVar_13 = 8
    else
      positionVar_13 = 11
  }));
  data_14.update(8, (() => {
    val x_51 = listValMut_9;
    val x_52 = x_51.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_53 = x_52.methodId;
    val x_54 = x_53.==(1);
    val x_55 = x_54.`unary_!`;
    if (x_55)
      {
        val x_56 = listValMut_9;
        val x_57 = x_56.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_58 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_57);
        val x_59 = this.addReceiveMessages(x_58);
        resetData_0 = x_59;
        positionVar_13 = 9
      }
    else
      positionVar_13 = 10
  }));
  data_14.update(9, (() => positionVar_13 = 6));
  data_14.update(10, (() => {
    val x_60 = listValMut_9;
    val x_61 = x_60.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_62 = x_61.methodId;
    val x_63 = x_62.==(1);
    if (x_63)
      positionVar_13 = 1
    else
      ();
    val x_64 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_65 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_64, 15);
    val x_66 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_65);
    resetData_1.prepend(x_66)
  }));
  data_14.update(11, (() => {
    val x_67 = listValMut_9;
    val x_68 = x_67.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_69 = x_68.methodId;
    val x_70 = x_69.==(0);
    if (x_70)
      {
        val x_71 = this.logger;
        resetData_0 = x_71;
        val x_72 = resetData_0;
        val x_73 = x_72.asInstanceOf[org.slf4j.Logger];
        bindingMut_7 = x_73;
        val x_74 = meta.example.helloWorld.Person.selfIntroduction;
        resetData_0 = x_74;
        val x_75 = resetData_0;
        val x_76 = x_75.asInstanceOf[scala.Function1[java.lang.String, java.lang.String]];
        bindingMut_6 = x_76;
        val x_77 = this.name;
        resetData_0 = x_77;
        val x_78 = resetData_0;
        val x_79 = x_78.asInstanceOf[java.lang.String];
        bindingMut_5 = x_79;
        val x_80 = bindingMut_5;
        val x_81 = x_80.asInstanceOf[java.lang.String];
        val x_82 = bindingMut_6;
        val x_83 = x_82.asInstanceOf[scala.Function1[java.lang.String, java.lang.String]];
        val x_84 = x_83(x_81);
        resetData_0 = x_84;
        val x_85 = resetData_0;
        val x_86 = x_85.asInstanceOf[java.lang.String];
        bindingMut_4 = x_86;
        val x_87 = bindingMut_4;
        val x_88 = x_87.asInstanceOf[java.lang.String];
        val x_89 = bindingMut_7;
        val x_90 = x_89.asInstanceOf[org.slf4j.Logger];
        x_90.info(x_88);
        resetData_0 = ();
        val x_91 = resetData_0;
        val x_92 = x_91.asInstanceOf[scala.Any];
        bindingMut_8 = x_92;
        val x_93 = bindingMut_8;
        val x_94 = x_93.asInstanceOf[scala.Any];
        val x_95 = listValMut_9;
        val x_96 = x_95.asInstanceOf[meta.deep.runtime.RequestMessage];
        x_96.reply(this, x_94);
        resetData_0 = ();
        positionVar_13 = 9
      }
    else
      ()
  }));
  data_14.update(12, (() => {
    val x_97 = iterMut_10;
    val x_98 = x_97.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_99 = x_98.hasNext;
    val x_100 = x_99.`unary_!`;
    if (x_100)
      positionVar_13 = 13
    else
      ()
  }));
  data_14.update(13, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 14));
  data_14.update(14, (() => {
    val x_101 = true.`unary_!`;
    if (x_101)
      {
        val x_102 = resetData_1.remove(0);
        val x_106 = x_102.find(((x_103: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_104 = x_103._1;
          val x_105 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_104.==(x_105)
        }));
        val x_107 = x_106.get;
        val x_108 = x_107._2;
        positionVar_13 = x_108
      }
    else
      ()
  }));
  data_14.update(15, (() => {
    val x_109 = resetData_0;
    val x_110 = x_109.asInstanceOf[scala.Any];
    bindingMut_8 = x_110;
    val x_111 = bindingMut_8;
    val x_112 = x_111.asInstanceOf[scala.Any];
    val x_113 = listValMut_9;
    val x_114 = x_113.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_114.reply(this, x_112);
    resetData_0 = ();
    positionVar_13 = 9
  }));
  data_14.update(16, (() => positionVar_13 = 17));
  data_14.update(17, (() => {
    positionVar_13 = 18;
    val x_115 = currentTurn;
    val x_116 = x_115.+(1);
    currentTurn = x_116
  }));
  data_14.update(18, (() => positionVar_13 = 17));
  data_14.update(19, (() => {
    val x_117 = true.`unary_!`;
    if (x_117)
      {
        val x_118 = resetData_1.remove(0);
        val x_122 = x_118.find(((x_119: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_120 = x_119._1;
          val x_121 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_120.==(x_121)
        }));
        val x_123 = x_122.get;
        val x_124 = x_123._2;
        positionVar_13 = x_124
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_126: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_127 = currentTurn;
      val x_128 = x_127.<=(until_126);
      x_128.&&({
        val x_129 = positionVar_13;
        val x_130 = commands_125.length;
        x_129.<(x_130)
      })
    }) 
      {
        val x_131 = positionVar_13;
        val x_132 = commands_125.apply(x_131);
        x_132.apply()
      }
    ;
    this
  }
}
