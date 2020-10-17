package generated.meta.example.lock_example

class Voter (var consensus_object: generated.meta.example.lock_example.Consensus) extends meta.deep.runtime.Actor {
  var won: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: java.lang.String = null;
  private var bindingMut_4: scala.Long = 0L;
  private var bindingMut_5: java.lang.String = null;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: scala.Long = 0L;
  private var bindingMut_8: scala.Boolean = false;
  private var bindingMut_9: scala.Long = 0L;
  private var bindingMut_10: scala.Long = 0L;
  private var bindingMut_11: generated.meta.example.lock_example.Consensus = null;
  private var bindingMut_12: scala.Long = 0L;
  private var bindingMut_13: scala.Double = 0.0;
  private var bindingMut_14: scala.Double = 0.0;
  private var positionVar_16: scala.Int = 0;
  
  val commands_130 = (() => {
  val data_17 = new scala.Array[scala.Function0[scala.Unit]](18);
  data_17.update(0, (() => positionVar_16 = 1));
  data_17.update(1, (() => {
    val x_18 = this.won;
    val x_19 = x_18.`unary_!`;
    if (x_19)
      positionVar_16 = 2
    else
      positionVar_16 = 17
  }));
  data_17.update(2, (() => {
    val x_20 = this.id;
    resetData_0 = x_20;
    val x_21 = resetData_0;
    val x_22 = x_21.asInstanceOf[scala.Long];
    bindingMut_12 = x_22;
    val x_23 = this.consensus_object;
    resetData_0 = x_23;
    val x_24 = resetData_0;
    val x_25 = x_24.asInstanceOf[generated.meta.example.lock_example.Consensus];
    bindingMut_11 = x_25;
    val x_26 = this.id;
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Long];
    bindingMut_10 = x_28;
    val x_29 = ((this): meta.deep.runtime.Actor).id;
    val x_31 = {
      val x_30 = bindingMut_11;
      x_30.asInstanceOf[generated.meta.example.lock_example.Consensus]
    };
    val x_32 = x_31.id;
    val x_33 = bindingMut_10;
    val x_34 = x_33.asInstanceOf[scala.Long];
    val x_35 = scala.collection.immutable.Nil.::[scala.Any](x_34);
    val x_36 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](x_35);
    val x_37 = meta.deep.runtime.RequestMessage.apply(x_29, x_32, 0, x_36);
    ((this): meta.deep.runtime.Actor).sendMessage(x_37);
    val x_38 = x_37.sessionId;
    ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_38, ((response_39: meta.deep.runtime.Message) => {
      val x_40 = response_39.asInstanceOf[meta.deep.runtime.ResponseMessage];
      resetData_2 = x_40
    }));
    resetData_0 = null;
    positionVar_16 = 3
  }));
  data_17.update(3, (() => {
    positionVar_16 = 4;
    val x_41 = currentTurn;
    val x_42 = x_41.+(1);
    currentTurn = x_42
  }));
  data_17.update(4, (() => {
    val x_43 = resetData_2;
    val x_44 = x_43.==(null);
    if (x_44)
      {
        val x_45 = meta.deep.runtime.Actor.labelVals("turn");
        x_45.append(1.0);
        positionVar_16 = 3
      }
    else
      positionVar_16 = 5
  }));
  data_17.update(5, (() => {
    val x_46 = resetData_2;
    val x_47 = x_46.!=(null);
    if (x_47)
      {
        val x_48 = resetData_2;
        val x_49 = x_48.arg;
        resetData_0 = x_49;
        resetData_2 = null;
        val x_50 = resetData_0;
        val x_51 = x_50.asInstanceOf[scala.Long];
        bindingMut_9 = x_51;
        val x_52 = bindingMut_9;
        val x_53 = x_52.asInstanceOf[scala.Long];
        val x_54 = bindingMut_12;
        val x_55 = x_54.asInstanceOf[scala.Long];
        val x_56 = x_55.==(x_53);
        resetData_0 = x_56;
        val x_57 = resetData_0;
        val x_58 = x_57.asInstanceOf[scala.Boolean];
        bindingMut_8 = x_58;
        positionVar_16 = 6
      }
    else
      ()
  }));
  data_17.update(6, (() => {
    val x_59 = bindingMut_8;
    val x_60 = x_59.asInstanceOf[scala.Boolean];
    if (x_60)
      {
        val x_61 = this.id;
        resetData_0 = x_61;
        val x_62 = resetData_0;
        val x_63 = x_62.asInstanceOf[scala.Long];
        bindingMut_7 = x_63;
        val x_64 = bindingMut_7;
        val x_65 = x_64.asInstanceOf[scala.Long];
        val x_66 = "I win! ".+(x_65);
        resetData_0 = x_66;
        val x_67 = resetData_0;
        val x_68 = x_67.asInstanceOf[java.lang.String];
        bindingMut_6 = x_68;
        val x_69 = bindingMut_6;
        val x_70 = x_69.asInstanceOf[java.lang.String];
        val x_71 = x_70.+(" No more propose");
        resetData_0 = x_71;
        val x_72 = resetData_0;
        val x_73 = x_72.asInstanceOf[java.lang.String];
        bindingMut_5 = x_73;
        val x_74 = bindingMut_5;
        val x_75 = x_74.asInstanceOf[java.lang.String];
        scala.Predef.println(x_75);
        resetData_0 = ();
        this.`won_=`(true);
        resetData_0 = ();
        positionVar_16 = 7
      }
    else
      positionVar_16 = 16
  }));
  data_17.update(7, (() => {
    val x_76 = 1.toDouble;
    resetData_0 = x_76;
    val x_77 = resetData_0;
    val x_78 = x_77.asInstanceOf[scala.Double];
    bindingMut_14 = x_78;
    resetData_0 = 0.0;
    val x_79 = resetData_0;
    val x_80 = x_79.asInstanceOf[scala.Double];
    bindingMut_13 = x_80;
    positionVar_16 = 8
  }));
  data_17.update(8, (() => {
    val x_81 = meta.deep.runtime.Actor.proceedLabel;
    val x_82 = x_81("turn");
    val x_83 = bindingMut_13;
    val x_84 = x_83.asInstanceOf[scala.Double];
    val x_85 = x_84.+(x_82);
    resetData_0 = x_85;
    val x_86 = resetData_0;
    val x_87 = x_86.asInstanceOf[scala.Double];
    bindingMut_13 = x_87;
    val x_88 = meta.deep.runtime.Actor.labelVals("turn");
    val x_89 = bindingMut_13;
    val x_90 = x_89.asInstanceOf[scala.Double];
    val x_91 = bindingMut_14;
    val x_92 = x_91.asInstanceOf[scala.Double];
    val x_93 = x_92.-(x_90);
    x_88.append(x_93);
    resetData_0 = ();
    positionVar_16 = 9;
    val x_94 = currentTurn;
    val x_95 = x_94.+(1);
    currentTurn = x_95
  }));
  data_17.update(9, (() => {
    val x_96 = bindingMut_13;
    val x_97 = x_96.asInstanceOf[scala.Double];
    val x_98 = bindingMut_14;
    val x_99 = x_98.asInstanceOf[scala.Double];
    val x_100 = x_97.<(x_99);
    if (x_100)
      positionVar_16 = 8
    else
      positionVar_16 = 10
  }));
  data_17.update(10, (() => {
    val x_101 = bindingMut_13;
    val x_102 = x_101.asInstanceOf[scala.Double];
    val x_103 = bindingMut_14;
    val x_104 = x_103.asInstanceOf[scala.Double];
    val x_105 = x_102.<(x_104);
    val x_106 = x_105.`unary_!`;
    if (x_106)
      positionVar_16 = 11
    else
      ()
  }));
  data_17.update(11, (() => {
    val x_107 = this.won;
    val x_108 = x_107.`unary_!`;
    if (x_108)
      positionVar_16 = 2
    else
      positionVar_16 = 12
  }));
  data_17.update(12, (() => {
    val x_109 = this.won;
    val x_110 = x_109.`unary_!`;
    val x_111 = x_110.`unary_!`;
    if (x_111)
      positionVar_16 = 13
    else
      ()
  }));
  data_17.update(13, (() => positionVar_16 = 14));
  data_17.update(14, (() => {
    positionVar_16 = 15;
    val x_112 = currentTurn;
    val x_113 = x_112.+(1);
    currentTurn = x_113
  }));
  data_17.update(15, (() => positionVar_16 = 14));
  data_17.update(16, (() => {
    val x_114 = bindingMut_8;
    val x_115 = x_114.asInstanceOf[scala.Boolean];
    val x_116 = x_115.`unary_!`;
    if (x_116)
      {
        val x_117 = this.id;
        resetData_0 = x_117;
        val x_118 = resetData_0;
        val x_119 = x_118.asInstanceOf[scala.Long];
        bindingMut_4 = x_119;
        val x_120 = bindingMut_4;
        val x_121 = x_120.asInstanceOf[scala.Long];
        val x_122 = "I lost. Try again! ".+(x_121);
        resetData_0 = x_122;
        val x_123 = resetData_0;
        val x_124 = x_123.asInstanceOf[java.lang.String];
        bindingMut_3 = x_124;
        val x_125 = bindingMut_3;
        val x_126 = x_125.asInstanceOf[java.lang.String];
        scala.Predef.println(x_126);
        resetData_0 = ();
        positionVar_16 = 7
      }
    else
      ()
  }));
  data_17.update(17, (() => {
    val x_127 = this.won;
    val x_128 = x_127.`unary_!`;
    val x_129 = x_128.`unary_!`;
    if (x_129)
      positionVar_16 = 13
    else
      ()
  }));
  data_17
}).apply();
  
  override def run_until(until_131: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_132 = currentTurn;
      val x_133 = x_132.<=(until_131);
      x_133.&&({
        val x_134 = positionVar_16;
        val x_135 = commands_130.length;
        x_134.<(x_135)
      })
    }) 
      {
        val x_136 = positionVar_16;
        val x_137 = commands_130.apply(x_136);
        x_137.apply()
      }
    ;
    this
  }
}
