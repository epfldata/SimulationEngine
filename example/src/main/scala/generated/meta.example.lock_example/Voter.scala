package generated.meta.example.lock_example

class Voter (var consensus_object: generated.meta.example.lock_example.Consensus) extends meta.deep.runtime.Actor with Serializable {
  var won: Boolean = false
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private val resetData_3 = scala.collection.mutable.Map.apply[java.lang.String, meta.deep.runtime.ResponseMessage]();
  private var bindingMut_4: java.lang.String = null;
  private var bindingMut_5: scala.Long = 0L;
  private var bindingMut_6: java.lang.String = null;
  private var bindingMut_7: java.lang.String = null;
  private var bindingMut_8: scala.Long = 0L;
  private var bindingMut_9: scala.Boolean = false;
  private var bindingMut_10: scala.Long = 0L;
  private var bindingMut_11: scala.Long = 0L;
  private var bindingMut_12: generated.meta.example.lock_example.Consensus = null;
  private var bindingMut_13: scala.Long = 0L;
  private var bindingMut_14: scala.Int = 0;
  private var positionVar_16: scala.Int = 0;
  
  val commands_118 = (() => {
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
    bindingMut_13 = x_22;
    val x_23 = this.consensus_object;
    resetData_0 = x_23;
    val x_24 = resetData_0;
    val x_25 = x_24.asInstanceOf[generated.meta.example.lock_example.Consensus];
    bindingMut_12 = x_25;
    val x_26 = this.id;
    resetData_0 = x_26;
    val x_27 = resetData_0;
    val x_28 = x_27.asInstanceOf[scala.Long];
    bindingMut_11 = x_28;
    val x_29 = ((this): meta.deep.runtime.Actor).id;
    val x_31 = {
      val x_30 = bindingMut_12;
      x_30.asInstanceOf[generated.meta.example.lock_example.Consensus]
    };
    val x_32 = x_31.id;
    val x_33 = bindingMut_11;
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
        meta.deep.runtime.Actor.waitTurnList.append(1);
        positionVar_16 = 3
      }
    else
      positionVar_16 = 5
  }));
  data_17.update(5, (() => {
    val x_45 = resetData_2;
    val x_46 = x_45.!=(null);
    if (x_46)
      {
        val x_47 = resetData_2;
        val x_48 = x_47.arg;
        resetData_0 = x_48;
        resetData_2 = null;
        val x_49 = resetData_0;
        val x_50 = x_49.asInstanceOf[scala.Long];
        bindingMut_10 = x_50;
        val x_51 = bindingMut_10;
        val x_52 = x_51.asInstanceOf[scala.Long];
        val x_53 = bindingMut_13;
        val x_54 = x_53.asInstanceOf[scala.Long];
        val x_55 = x_54.==(x_52);
        resetData_0 = x_55;
        val x_56 = resetData_0;
        val x_57 = x_56.asInstanceOf[scala.Boolean];
        bindingMut_9 = x_57;
        positionVar_16 = 6
      }
    else
      ()
  }));
  data_17.update(6, (() => {
    val x_58 = bindingMut_9;
    val x_59 = x_58.asInstanceOf[scala.Boolean];
    if (x_59)
      {
        val x_60 = this.id;
        resetData_0 = x_60;
        val x_61 = resetData_0;
        val x_62 = x_61.asInstanceOf[scala.Long];
        bindingMut_8 = x_62;
        val x_63 = bindingMut_8;
        val x_64 = x_63.asInstanceOf[scala.Long];
        val x_65 = "I win! ".+(x_64);
        resetData_0 = x_65;
        val x_66 = resetData_0;
        val x_67 = x_66.asInstanceOf[java.lang.String];
        bindingMut_7 = x_67;
        val x_68 = bindingMut_7;
        val x_69 = x_68.asInstanceOf[java.lang.String];
        val x_70 = x_69.+(" No more propose");
        resetData_0 = x_70;
        val x_71 = resetData_0;
        val x_72 = x_71.asInstanceOf[java.lang.String];
        bindingMut_6 = x_72;
        val x_73 = bindingMut_6;
        val x_74 = x_73.asInstanceOf[java.lang.String];
        scala.Predef.println(x_74);
        resetData_0 = ();
        this.`won_=`(true);
        resetData_0 = ();
        positionVar_16 = 7
      }
    else
      positionVar_16 = 16
  }));
  data_17.update(7, (() => {
    resetData_0 = 0;
    val x_75 = resetData_0;
    val x_76 = x_75.asInstanceOf[scala.Int];
    bindingMut_14 = x_76;
    positionVar_16 = 8
  }));
  data_17.update(8, (() => {
    val x_77 = bindingMut_14;
    val x_78 = x_77.asInstanceOf[scala.Int];
    val x_79 = (1).-(x_78);
    meta.deep.runtime.Actor.waitTurnList.append(x_79);
    resetData_0 = ();
    val x_80 = meta.deep.runtime.Actor.minTurn();
    val x_81 = bindingMut_14;
    val x_82 = x_81.asInstanceOf[scala.Int];
    val x_83 = x_82.+(x_80);
    resetData_0 = x_83;
    val x_84 = resetData_0;
    val x_85 = x_84.asInstanceOf[scala.Int];
    bindingMut_14 = x_85;
    positionVar_16 = 9;
    val x_86 = currentTurn;
    val x_87 = x_86.+(1);
    currentTurn = x_87
  }));
  data_17.update(9, (() => {
    val x_88 = bindingMut_14;
    val x_89 = x_88.asInstanceOf[scala.Int];
    val x_90 = x_89.<(1);
    if (x_90)
      positionVar_16 = 8
    else
      positionVar_16 = 10
  }));
  data_17.update(10, (() => {
    val x_91 = bindingMut_14;
    val x_92 = x_91.asInstanceOf[scala.Int];
    val x_93 = x_92.<(1);
    val x_94 = x_93.`unary_!`;
    if (x_94)
      positionVar_16 = 11
    else
      ()
  }));
  data_17.update(11, (() => {
    val x_95 = this.won;
    val x_96 = x_95.`unary_!`;
    if (x_96)
      positionVar_16 = 2
    else
      positionVar_16 = 12
  }));
  data_17.update(12, (() => {
    val x_97 = this.won;
    val x_98 = x_97.`unary_!`;
    val x_99 = x_98.`unary_!`;
    if (x_99)
      positionVar_16 = 13
    else
      ()
  }));
  data_17.update(13, (() => positionVar_16 = 14));
  data_17.update(14, (() => {
    positionVar_16 = 15;
    val x_100 = currentTurn;
    val x_101 = x_100.+(1);
    currentTurn = x_101
  }));
  data_17.update(15, (() => positionVar_16 = 14));
  data_17.update(16, (() => {
    val x_102 = bindingMut_9;
    val x_103 = x_102.asInstanceOf[scala.Boolean];
    val x_104 = x_103.`unary_!`;
    if (x_104)
      {
        val x_105 = this.id;
        resetData_0 = x_105;
        val x_106 = resetData_0;
        val x_107 = x_106.asInstanceOf[scala.Long];
        bindingMut_5 = x_107;
        val x_108 = bindingMut_5;
        val x_109 = x_108.asInstanceOf[scala.Long];
        val x_110 = "I lost. Try again! ".+(x_109);
        resetData_0 = x_110;
        val x_111 = resetData_0;
        val x_112 = x_111.asInstanceOf[java.lang.String];
        bindingMut_4 = x_112;
        val x_113 = bindingMut_4;
        val x_114 = x_113.asInstanceOf[java.lang.String];
        scala.Predef.println(x_114);
        resetData_0 = ();
        positionVar_16 = 7
      }
    else
      ()
  }));
  data_17.update(17, (() => {
    val x_115 = this.won;
    val x_116 = x_115.`unary_!`;
    val x_117 = x_116.`unary_!`;
    if (x_117)
      positionVar_16 = 13
    else
      ()
  }));
  data_17
}).apply();
  
  override def run_until(until_119: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_120 = currentTurn;
      val x_121 = x_120.<=(until_119);
      x_121.&&({
        val x_122 = positionVar_16;
        val x_123 = commands_118.length;
        x_122.<(x_123)
      })
    }) 
      {
        val x_124 = positionVar_16;
        val x_125 = commands_118.apply(x_124);
        x_125.apply()
      }
    ;
    this
  }
}
