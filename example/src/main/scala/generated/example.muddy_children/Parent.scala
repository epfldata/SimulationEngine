package generated.example.muddy_children

class Parent (val env: List[generated.example.muddy_children.MuddyChild]) extends meta.deep.runtime.Actor {

  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.collection.immutable.List[generated.example.muddy_children.MuddyChild] = null;
  private var listValMut_4: generated.example.muddy_children.MuddyChild = null;
  private var iterMut_5: scala.collection.Iterator[generated.example.muddy_children.MuddyChild] = null;
  private var bindingMut_6: scala.collection.immutable.List[generated.example.muddy_children.MuddyChild] = null;
  private var bindingMut_7: scala.Double = 0.0;
  private var bindingMut_8: scala.Double = 0.0;
  private var bindingMut_9: scala.Boolean = false;
  private var positionVar_11: scala.Int = 0;
  
  val commands_86 = (() => {
  val data_12 = new scala.Array[scala.Function0[scala.Unit]](17);
  data_12.update(0, (() => positionVar_11 = 1));
  data_12.update(1, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 16));
  data_12.update(2, (() => {
    val x_13 = this.env;
    resetData_0 = x_13;
    val x_14 = resetData_0;
    val x_15 = x_14.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
    bindingMut_3 = x_15;
    val x_16 = bindingMut_3;
    val x_17 = x_16.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
    val x_21 = x_17.exists(((e_18: generated.example.muddy_children.MuddyChild) => {
      val x_19 = e_18.isMuddy;
      x_19.&&({
        val x_20 = e_18.isForward;
        x_20.`unary_!`
      })
    }));
    resetData_0 = x_21;
    val x_22 = resetData_0;
    val x_23 = x_22.asInstanceOf[scala.Boolean];
    bindingMut_9 = x_23;
    positionVar_11 = 3
  }));
  data_12.update(3, (() => {
    val x_24 = bindingMut_9;
    val x_25 = x_24.asInstanceOf[scala.Boolean];
    val x_26 = x_25.`unary_!`;
    if (x_26)
      {
        scala.Predef.println("All muddy children are forwarded!");
        resetData_0 = ();
        positionVar_11 = 4
      }
    else
      positionVar_11 = 13
  }));
  data_12.update(4, (() => {
    val x_27 = 2.toDouble;
    resetData_0 = x_27;
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[scala.Double];
    bindingMut_8 = x_29;
    resetData_0 = 0.0;
    val x_30 = resetData_0;
    val x_31 = x_30.asInstanceOf[scala.Double];
    bindingMut_7 = x_31;
    positionVar_11 = 5
  }));
  data_12.update(5, (() => {
    val x_32 = meta.deep.runtime.Actor.proceedLabel;
    val x_33 = x_32("turn");
    val x_34 = bindingMut_7;
    val x_35 = x_34.asInstanceOf[scala.Double];
    val x_36 = x_35.+(x_33);
    resetData_0 = x_36;
    val x_37 = resetData_0;
    val x_38 = x_37.asInstanceOf[scala.Double];
    bindingMut_7 = x_38;
    val x_39 = meta.deep.runtime.Actor.labelVals("turn");
    val x_40 = bindingMut_7;
    val x_41 = x_40.asInstanceOf[scala.Double];
    val x_42 = bindingMut_8;
    val x_43 = x_42.asInstanceOf[scala.Double];
    val x_44 = x_43.-(x_41);
    x_39.append(x_44);
    resetData_0 = ();
    positionVar_11 = 6;
    val x_45 = currentTurn;
    val x_46 = x_45.+(1);
    currentTurn = x_46
  }));
  data_12.update(6, (() => {
    val x_47 = bindingMut_7;
    val x_48 = x_47.asInstanceOf[scala.Double];
    val x_49 = bindingMut_8;
    val x_50 = x_49.asInstanceOf[scala.Double];
    val x_51 = x_48.<(x_50);
    if (x_51)
      positionVar_11 = 5
    else
      positionVar_11 = 7
  }));
  data_12.update(7, (() => {
    val x_52 = bindingMut_7;
    val x_53 = x_52.asInstanceOf[scala.Double];
    val x_54 = bindingMut_8;
    val x_55 = x_54.asInstanceOf[scala.Double];
    val x_56 = x_53.<(x_55);
    val x_57 = x_56.`unary_!`;
    if (x_57)
      positionVar_11 = 8
    else
      ()
  }));
  data_12.update(8, (() => if (true)
    positionVar_11 = 2
  else
    positionVar_11 = 9));
  data_12.update(9, (() => {
    val x_58 = true.`unary_!`;
    if (x_58)
      positionVar_11 = 10
    else
      ()
  }));
  data_12.update(10, (() => positionVar_11 = 11));
  data_12.update(11, (() => {
    positionVar_11 = 12;
    val x_59 = currentTurn;
    val x_60 = x_59.+(1);
    currentTurn = x_60
  }));
  data_12.update(12, (() => positionVar_11 = 11));
  data_12.update(13, (() => {
    val x_61 = bindingMut_9;
    val x_62 = x_61.asInstanceOf[scala.Boolean];
    if (x_62)
      {
        scala.Predef.println("There is at least one child with muddy forehead!");
        resetData_0 = ();
        val x_63 = this.env;
        resetData_0 = x_63;
        val x_64 = resetData_0;
        val x_65 = x_64.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
        bindingMut_6 = x_65;
        val x_66 = bindingMut_6;
        val x_67 = x_66.asInstanceOf[scala.collection.immutable.List[generated.example.muddy_children.MuddyChild]];
        val x_68 = x_67.iterator;
        iterMut_5 = x_68;
        positionVar_11 = 14
      }
    else
      ()
  }));
  data_12.update(14, (() => {
    val x_69 = iterMut_5;
    val x_70 = x_69.asInstanceOf[scala.collection.Iterator[generated.example.muddy_children.MuddyChild]];
    val x_71 = x_70.hasNext;
    if (x_71)
      {
        val x_72 = iterMut_5;
        val x_73 = x_72.asInstanceOf[scala.collection.Iterator[generated.example.muddy_children.MuddyChild]];
        val x_74 = x_73.next();
        listValMut_4 = x_74;
        val x_75 = ((this): meta.deep.runtime.Actor).id;
        val x_77 = {
          val x_76 = listValMut_4;
          x_76.asInstanceOf[generated.example.muddy_children.MuddyChild]
        };
        val x_78 = x_77.id;
        val x_79 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_80 = meta.deep.runtime.RequestMessage.apply(x_75, x_78, 0, x_79);
        ((this): meta.deep.runtime.Actor).sendMessage(x_80);
        resetData_0 = scala.None;
        positionVar_11 = 14
      }
    else
      positionVar_11 = 15
  }));
  data_12.update(15, (() => {
    val x_81 = iterMut_5;
    val x_82 = x_81.asInstanceOf[scala.collection.Iterator[generated.example.muddy_children.MuddyChild]];
    val x_83 = x_82.hasNext;
    val x_84 = x_83.`unary_!`;
    if (x_84)
      positionVar_11 = 4
    else
      ()
  }));
  data_12.update(16, (() => {
    val x_85 = true.`unary_!`;
    if (x_85)
      positionVar_11 = 10
    else
      ()
  }));
  data_12
}).apply();
  
  override def run_until(until_87: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_88 = currentTurn;
      val x_89 = x_88.<=(until_87);
      x_89.&&({
        val x_90 = positionVar_11;
        val x_91 = commands_86.length;
        x_90.<(x_91)
      })
    }) 
      {
        val x_92 = positionVar_11;
        val x_93 = commands_86.apply(x_92);
        x_93.apply()
      }
    ;
    this
  }
}
