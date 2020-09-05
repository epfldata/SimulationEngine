package generated.meta.example.stateless_server

class RandomPrinter () extends meta.deep.runtime.Actor with Serializable {
  var server: generated.meta.example.stateless_server.RandomNumberServer = null
  var i: Int = 0
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: scala.Tuple2[scala.Int, scala.Int] = null;
  private var bindingMut_5: scala.Int = 0;
  private var bindingMut_6: generated.meta.example.stateless_server.RandomNumberServer = null;
  private var bindingMut_7: scala.Int = 0;
  private var bindingMut_8: scala.Any = null;
  private var listValMut_9: meta.deep.runtime.RequestMessage = null;
  private var iterMut_10: scala.collection.Iterator[meta.deep.runtime.RequestMessage] = null;
  private var bindingMut_11: scala.Int = 0;
  private var positionVar_13: scala.Int = 0;
  
  val commands_152 = (() => {
  val data_14 = new scala.Array[scala.Function0[scala.Unit]](26);
  data_14.update(0, (() => {
    positionVar_13 = 1;
    val x_15 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_16 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_15, 18);
    val x_17 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_16);
    resetData_1.prepend(x_17)
  }));
  data_14.update(1, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 25));
  data_14.update(2, (() => {
    positionVar_13 = 3;
    val x_18 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_19 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_18, 21);
    val x_20 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_19);
    resetData_1.prepend(x_20)
  }));
  data_14.update(3, (() => {
    val x_21 = this.i;
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Int];
    bindingMut_7 = x_23;
    val x_24 = this.server;
    resetData_0 = x_24;
    val x_25 = resetData_0;
    val x_26 = x_25.asInstanceOf[generated.meta.example.stateless_server.RandomNumberServer];
    bindingMut_6 = x_26;
    val x_27 = ((this): meta.deep.runtime.Actor).id;
    val x_29 = {
      val x_28 = bindingMut_6;
      x_28.asInstanceOf[generated.meta.example.stateless_server.RandomNumberServer]
    };
    val x_30 = x_29.id;
    val x_31 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
    val x_32 = meta.deep.runtime.RequestMessage.apply(x_27, x_30, 2, x_31);
    ((this): meta.deep.runtime.Actor).sendMessage(x_32);
    val x_33 = x_32.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_33, ((response_34: meta.deep.runtime.Message) => {
      val x_35 = response_34.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_35
    }));
    resetData_0 = null;
    positionVar_13 = 4
  }));
  data_14.update(4, (() => {
    positionVar_13 = 5;
    val x_36 = currentTurn;
    val x_37 = x_36.+(1);
    currentTurn = x_37
  }));
  data_14.update(5, (() => {
    val x_38 = resetData_2;
    val x_39 = x_38.==(null);
    if (x_39)
      {
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_13 = 4
      }
    else
      positionVar_13 = 6
  }));
  data_14.update(6, (() => {
    val x_40 = resetData_2;
    val x_41 = x_40.!=(null);
    if (x_41)
      {
        val x_42 = resetData_2;
        val x_43 = x_42.arg;
        resetData_0 = x_43;
        resetData_2 = null;
        val x_44 = resetData_0;
        val x_45 = x_44.asInstanceOf[scala.Int];
        bindingMut_5 = x_45;
        val x_46 = bindingMut_5;
        val x_47 = x_46.asInstanceOf[scala.Int];
        val x_48 = bindingMut_7;
        val x_49 = x_48.asInstanceOf[scala.Int];
        val x_50 = scala.Tuple2.apply[scala.Int, scala.Int](x_49, x_47);
        resetData_0 = x_50;
        val x_51 = resetData_0;
        val x_52 = x_51.asInstanceOf[scala.Tuple2[scala.Int, scala.Int]];
        bindingMut_4 = x_52;
        val x_53 = bindingMut_4;
        val x_54 = x_53.asInstanceOf[scala.Tuple2[scala.Int, scala.Int]];
        scala.Predef.println(x_54);
        resetData_0 = ();
        val x_55 = resetData_1.remove(0);
        val x_59 = x_55.find(((x_56: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_57 = x_56._1;
          val x_58 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_57.==(x_58)
        }));
        val x_60 = x_59.get;
        val x_61 = x_60._2;
        positionVar_13 = x_61
      }
    else
      ()
  }));
  data_14.update(7, (() => {
    val x_62 = resetData_0;
    val x_63 = x_62.asInstanceOf[scala.Any];
    bindingMut_8 = x_63;
    val x_64 = bindingMut_8;
    val x_65 = x_64.asInstanceOf[scala.Any];
    val x_66 = listValMut_9;
    val x_67 = x_66.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_67.reply(this, x_65);
    resetData_0 = ();
    positionVar_13 = 8
  }));
  data_14.update(8, (() => positionVar_13 = 9));
  data_14.update(9, (() => {
    val x_68 = iterMut_10;
    val x_69 = x_68.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_70 = x_69.hasNext;
    if (x_70)
      {
        val x_71 = iterMut_10;
        val x_72 = x_71.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
        val x_73 = x_72.next();
        listValMut_9 = x_73;
        positionVar_13 = 10
      }
    else
      positionVar_13 = 14
  }));
  data_14.update(10, (() => {
    val x_74 = listValMut_9;
    val x_75 = x_74.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_76 = x_75.methodId;
    val x_77 = x_76.==(0);
    val x_78 = x_77.`unary_!`;
    if (x_78)
      positionVar_13 = 11
    else
      positionVar_13 = 13
  }));
  data_14.update(11, (() => {
    val x_79 = listValMut_9;
    val x_80 = x_79.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_81 = x_80.methodId;
    val x_82 = x_81.==(1);
    val x_83 = x_82.`unary_!`;
    if (x_83)
      {
        val x_84 = listValMut_9;
        val x_85 = x_84.asInstanceOf[meta.deep.runtime.RequestMessage];
        val x_86 = scala.collection.immutable.List.apply[meta.deep.runtime.RequestMessage](x_85);
        val x_87 = this.addReceiveMessages(x_86);
        resetData_0 = x_87;
        positionVar_13 = 8
      }
    else
      positionVar_13 = 12
  }));
  data_14.update(12, (() => {
    val x_88 = listValMut_9;
    val x_89 = x_88.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_90 = x_89.methodId;
    val x_91 = x_90.==(1);
    if (x_91)
      positionVar_13 = 1
    else
      ();
    val x_92 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_93 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_92, 17);
    val x_94 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_93);
    resetData_1.prepend(x_94)
  }));
  data_14.update(13, (() => {
    val x_95 = listValMut_9;
    val x_96 = x_95.asInstanceOf[meta.deep.runtime.RequestMessage];
    val x_97 = x_96.methodId;
    val x_98 = x_97.==(0);
    if (x_98)
      positionVar_13 = 3
    else
      ();
    val x_99 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_100 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_99, 7);
    val x_101 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_100);
    resetData_1.prepend(x_101)
  }));
  data_14.update(14, (() => {
    val x_102 = iterMut_10;
    val x_103 = x_102.asInstanceOf[scala.collection.Iterator[meta.deep.runtime.RequestMessage]];
    val x_104 = x_103.hasNext;
    val x_105 = x_104.`unary_!`;
    if (x_105)
      positionVar_13 = 15
    else
      ()
  }));
  data_14.update(15, (() => if (true)
    positionVar_13 = 2
  else
    positionVar_13 = 16));
  data_14.update(16, (() => {
    val x_106 = true.`unary_!`;
    if (x_106)
      {
        val x_107 = resetData_1.remove(0);
        val x_111 = x_107.find(((x_108: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_109 = x_108._1;
          val x_110 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_109.==(x_110)
        }));
        val x_112 = x_111.get;
        val x_113 = x_112._2;
        positionVar_13 = x_113
      }
    else
      ()
  }));
  data_14.update(17, (() => {
    val x_114 = resetData_0;
    val x_115 = x_114.asInstanceOf[scala.Any];
    bindingMut_8 = x_115;
    val x_116 = bindingMut_8;
    val x_117 = x_116.asInstanceOf[scala.Any];
    val x_118 = listValMut_9;
    val x_119 = x_118.asInstanceOf[meta.deep.runtime.RequestMessage];
    x_119.reply(this, x_117);
    resetData_0 = ();
    positionVar_13 = 8
  }));
  data_14.update(18, (() => positionVar_13 = 19));
  data_14.update(19, (() => {
    positionVar_13 = 20;
    val x_120 = currentTurn;
    val x_121 = x_120.+(1);
    currentTurn = x_121
  }));
  data_14.update(20, (() => positionVar_13 = 19));
  data_14.update(21, (() => {
    resetData_0 = 0;
    val x_122 = resetData_0;
    val x_123 = x_122.asInstanceOf[scala.Int];
    bindingMut_11 = x_123;
    positionVar_13 = 22
  }));
  data_14.update(22, (() => {
    val x_124 = bindingMut_11;
    val x_125 = x_124.asInstanceOf[scala.Int];
    val x_126 = (1).-(x_125);
    meta.deep.runtime.Actor.waitTurnList.append(x_126);
    resetData_0 = ();
    val x_127 = meta.deep.runtime.Actor.minTurn();
    val x_128 = bindingMut_11;
    val x_129 = x_128.asInstanceOf[scala.Int];
    val x_130 = x_129.+(x_127);
    resetData_0 = x_130;
    val x_131 = resetData_0;
    val x_132 = x_131.asInstanceOf[scala.Int];
    bindingMut_11 = x_132;
    positionVar_13 = 23;
    val x_133 = currentTurn;
    val x_134 = x_133.+(1);
    currentTurn = x_134
  }));
  data_14.update(23, (() => {
    val x_135 = bindingMut_11;
    val x_136 = x_135.asInstanceOf[scala.Int];
    val x_137 = x_136.<(1);
    if (x_137)
      positionVar_13 = 22
    else
      positionVar_13 = 24
  }));
  data_14.update(24, (() => {
    val x_138 = bindingMut_11;
    val x_139 = x_138.asInstanceOf[scala.Int];
    val x_140 = x_139.<(1);
    val x_141 = x_140.`unary_!`;
    if (x_141)
      {
        val x_142 = this.popRequestMessages;
        val x_143 = x_142.iterator;
        iterMut_10 = x_143;
        positionVar_13 = 9
      }
    else
      ()
  }));
  data_14.update(25, (() => {
    val x_144 = true.`unary_!`;
    if (x_144)
      {
        val x_145 = resetData_1.remove(0);
        val x_149 = x_145.find(((x_146: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_147 = x_146._1;
          val x_148 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_147.==(x_148)
        }));
        val x_150 = x_149.get;
        val x_151 = x_150._2;
        positionVar_13 = x_151
      }
    else
      ()
  }));
  data_14
}).apply();
  
  override def run_until(until_153: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_154 = currentTurn;
      val x_155 = x_154.<=(until_153);
      x_155.&&({
        val x_156 = positionVar_13;
        val x_157 = commands_152.length;
        x_156.<(x_157)
      })
    }) 
      {
        val x_158 = positionVar_13;
        val x_159 = commands_152.apply(x_158);
        x_159.apply()
      }
    ;
    this
  }
}
