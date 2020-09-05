package generated.meta.example.helloWorld

class OutgoingPerson (var name: String) extends meta.deep.runtime.Actor with meta.example.helloWorld.Person with Serializable {
  var outgoing: Boolean = true
  var rand: util.Random = new scala.util.Random()
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Any = null;
  private var listValMut_5: meta.deep.runtime.RequestMessage = null;
  private var iterMut_6: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: scala.Boolean = false;
  private var bindingMut_9: generated.meta.example.helloWorld.TimidPerson = null;
  private var bindingMut_10: scala.Boolean = false;
  private var bindingMut_11: meta.example.helloWorld.Person = null;
  private var bindingMut_12: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var bindingMut_13: meta.example.helloWorld.Person = null;
  private var bindingMut_14: scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person] = null;
  private var positionVar_16: scala.Int = 0;
  
  val commands_157 = (() => {
  val data_17 = new scala.Array[scala.Function0[scala.Unit]](27);
  data_17.update(0, (() => {
    positionVar_16 = 1;
    val x_18 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_19 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_18, 18);
    val x_20 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_19);
    resetData_1.prepend(x_20)
  }));
  data_17.update(1, (() => if (true)
    positionVar_16 = 2
  else
    positionVar_16 = 26));
  data_17.update(2, (() => {
    val x_21 = this.friendList;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_14 = x_23;
    val x_24 = bindingMut_14;
    val x_25 = x_24.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_26 = x_25(0);
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[meta.example.helloWorld.Person];
    bindingMut_13 = x_28;
    val x_29 = this.friendList;
    resetData_0 = x_29;
    val x_30 = resetData_0;
    val x_31 = x_30.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    bindingMut_12 = x_31;
    val x_32 = bindingMut_12;
    val x_33 = x_32.asInstanceOf[scala.collection.mutable.ListBuffer[meta.example.helloWorld.Person]];
    val x_34 = x_33(1);
    resetData_0 = x_34;
    val x_35 = resetData_0;
    val x_36 = x_35.asInstanceOf[meta.example.helloWorld.Person];
    bindingMut_11 = x_36;
    val x_37 = bindingMut_13;
    val x_38 = x_37.asInstanceOf[meta.example.helloWorld.Person];
    val x_39 = x_38.isInstanceOf[generated.meta.example.helloWorld.TimidPerson];
    resetData_0 = x_39;
    val x_40 = resetData_0;
    val x_41 = x_40.asInstanceOf[scala.Boolean];
    bindingMut_10 = x_41;
    positionVar_16 = 3
  }));
  data_17.update(3, (() => {
    val x_42 = bindingMut_10;
    val x_43 = x_42.asInstanceOf[scala.Boolean];
    val x_44 = x_43.`unary_!`;
    if (x_44)
      positionVar_16 = 4
    else
      positionVar_16 = 22
  }));
  data_17.update(4, (() => {
    val x_45 = bindingMut_11;
    val x_46 = x_45.asInstanceOf[meta.example.helloWorld.Person];
    val x_47 = bindingMut_13;
    val x_48 = x_47.asInstanceOf[meta.example.helloWorld.Person];
    val x_49 = x_48.!=(x_46);
    resetData_0 = x_49;
    val x_50 = resetData_0;
    val x_51 = x_50.asInstanceOf[scala.Boolean];
    bindingMut_8 = x_51;
    positionVar_16 = 5
  }));
  data_17.update(5, (() => {
    val x_52 = bindingMut_8;
    val x_53 = x_52.asInstanceOf[scala.Boolean];
    val x_54 = x_53.`unary_!`;
    if (x_54)
      positionVar_16 = 6
    else
      positionVar_16 = 21
  }));
  data_17.update(6, (() => {
    resetData_0 = 0;
    val x_55 = resetData_0;
    val x_56 = x_55.asInstanceOf[scala.Int];
    bindingMut_7 = x_56;
    positionVar_16 = 7
  }));
  data_17.update(7, (() => {
    val x_57 = bindingMut_7;
    val x_58 = x_57.asInstanceOf[scala.Int];
    val x_59 = (2).-(x_58);
    meta.deep.runtime.Actor.waitTurnList.append(x_59);
    resetData_0 = ();
    val x_60 = meta.deep.runtime.Actor.minTurn();
    val x_61 = bindingMut_7;
    val x_62 = x_61.asInstanceOf[scala.Int];
    val x_63 = x_62.+(x_60);
    resetData_0 = x_63;
    val x_64 = resetData_0;
    val x_65 = x_64.asInstanceOf[scala.Int];
    bindingMut_7 = x_65;
    positionVar_16 = 8;
    val x_66 = currentTurn;
    val x_67 = x_66.+(1);
    currentTurn = x_67
  }));
  data_17.update(8, (() => {
    val x_68 = bindingMut_7;
    val x_69 = x_68.asInstanceOf[scala.Int];
    val x_70 = x_69.<(2);
    if (x_70)
      positionVar_16 = 7
    else
      positionVar_16 = 9
  }));
  data_17.update(9, (() => {
    val x_71 = bindingMut_7;
    val x_72 = x_71.asInstanceOf[scala.Int];
    val x_73 = x_72.<(2);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      {
        val x_75 = this.popRequestMessages;
        val x_76 = x_75.iterator;
        iterMut_6 = x_76;
        positionVar_16 = 10
      }
    else
      ()
  }));
  data_17.update(10, (() => {
    val x_77 = iterMut_6;
    val x_78 = x_77.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_79 = x_78.hasNext;
    if (x_79)
      {
        val x_80 = iterMut_6;
        val x_81 = x_80.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_82 = x_81.next();
        listValMut_5 = x_82;
        positionVar_16 = 11
      }
    else
      positionVar_16 = 14
  }));
  data_17.update(11, (() => {
    val x_83 = listValMut_5;
    val x_84 = x_83.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_85 = x_84.methodId;
    val x_86 = x_85.==(2);
    val x_87 = x_86.`unary_!`;
    if (x_87)
      {
        val x_88 = listValMut_5;
        val x_89 = x_88.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_90 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_89);
        val x_91 = this.addReceiveMessages(x_90);
        resetData_0 = x_91;
        positionVar_16 = 12
      }
    else
      positionVar_16 = 13
  }));
  data_17.update(12, (() => positionVar_16 = 10));
  data_17.update(13, (() => {
    val x_92 = listValMut_5;
    val x_93 = x_92.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_94 = x_93.methodId;
    val x_95 = x_94.==(2);
    if (x_95)
      positionVar_16 = 1
    else
      ();
    val x_96 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_97 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_96, 17);
    val x_98 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_97);
    resetData_1.prepend(x_98)
  }));
  data_17.update(14, (() => {
    val x_99 = iterMut_6;
    val x_100 = x_99.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_101 = x_100.hasNext;
    val x_102 = x_101.`unary_!`;
    if (x_102)
      positionVar_16 = 15
    else
      ()
  }));
  data_17.update(15, (() => if (true)
    positionVar_16 = 2
  else
    positionVar_16 = 16));
  data_17.update(16, (() => {
    val x_103 = true.`unary_!`;
    if (x_103)
      {
        val x_104 = resetData_1.remove(0);
        val x_108 = x_104.find(((x_105: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_106 = x_105._1;
          val x_107 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_106.==(x_107)
        }));
        val x_109 = x_108.get;
        val x_110 = x_109._2;
        positionVar_16 = x_110
      }
    else
      ()
  }));
  data_17.update(17, (() => {
    val x_111 = resetData_0;
    val x_112 = x_111.asInstanceOf[scala.Any];
    bindingMut_4 = x_112;
    val x_113 = bindingMut_4;
    val x_114 = x_113.asInstanceOf[scala.Any];
    val x_115 = listValMut_5;
    val x_116 = x_115.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_116.reply(this, x_114);
    resetData_0 = ();
    positionVar_16 = 12
  }));
  data_17.update(18, (() => positionVar_16 = 19));
  data_17.update(19, (() => {
    positionVar_16 = 20;
    val x_117 = currentTurn;
    val x_118 = x_117.+(1);
    currentTurn = x_118
  }));
  data_17.update(20, (() => positionVar_16 = 19));
  data_17.update(21, (() => {
    val x_119 = bindingMut_8;
    val x_120 = x_119.asInstanceOf[scala.Boolean];
    if (x_120)
      {
        val x_121 = bindingMut_11;
        val x_122 = x_121.asInstanceOf[meta.example.helloWorld.Person];
        val x_123 = bindingMut_13;
        val x_124 = x_123.asInstanceOf[meta.example.helloWorld.Person];
        this.introduce(x_124, x_122);
        resetData_0 = ();
        positionVar_16 = 6
      }
    else
      ()
  }));
  data_17.update(22, (() => {
    val x_125 = bindingMut_10;
    val x_126 = x_125.asInstanceOf[scala.Boolean];
    if (x_126)
      {
        val x_127 = bindingMut_13;
        val x_128 = x_127.asInstanceOf[meta.example.helloWorld.Person];
        val x_129 = x_128.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
        resetData_0 = x_129;
        val x_130 = resetData_0;
        val x_131 = x_130.asInstanceOf[generated.meta.example.helloWorld.TimidPerson];
        bindingMut_9 = x_131;
        val x_132 = ((this): meta.deep.runtime.Actor).id;
        val x_134 = {
          val x_133 = bindingMut_9;
          x_133.asInstanceOf[generated.meta.example.helloWorld.TimidPerson]
        };
        val x_135 = x_134.id;
        val x_136 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_137 = meta.deep.runtime.RequestMessage.apply(x_132, x_135, 0, x_136);
        ((this): meta.deep.runtime.Actor).sendMessage(x_137);
        val x_138 = x_137.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_138, ((response_139: meta.deep.runtime.Message) => {
          val x_140 = response_139.asInstanceOf[meta.deep.runtime.ResponseMessage];
          resetData_2 = x_140
        }));
        resetData_0 = null;
        positionVar_16 = 23
      }
    else
      ()
  }));
  data_17.update(23, (() => {
    positionVar_16 = 24;
    val x_141 = currentTurn;
    val x_142 = x_141.+(1);
    currentTurn = x_142
  }));
  data_17.update(24, (() => {
    val x_143 = resetData_2;
    val x_144 = x_143.==(null);
    if (x_144)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_16 = 23
      }
    else
      positionVar_16 = 25
  }));
  data_17.update(25, (() => {
    val x_145 = resetData_2;
    val x_146 = x_145.!=(null);
    if (x_146)
      {
        val x_147 = resetData_2;
        val x_148 = x_147.arg;
        resetData_0 = x_148;
        resetData_2 = null;
        positionVar_16 = 4
      }
    else
      ()
  }));
  data_17.update(26, (() => {
    val x_149 = true.`unary_!`;
    if (x_149)
      {
        val x_150 = resetData_1.remove(0);
        val x_154 = x_150.find(((x_151: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_152 = x_151._1;
          val x_153 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_152.==(x_153)
        }));
        val x_155 = x_154.get;
        val x_156 = x_155._2;
        positionVar_16 = x_156
      }
    else
      ()
  }));
  data_17
}).apply();
  
  override def run_until(until_158: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_159 = currentTurn;
      val x_160 = x_159.<=(until_158);
      x_160.&&({
        val x_161 = positionVar_16;
        val x_162 = commands_157.length;
        x_161.<(x_162)
      })
    }) 
      {
        val x_163 = positionVar_16;
        val x_164 = commands_157.apply(x_163);
        x_164.apply()
      }
    ;
    this
  }
}
