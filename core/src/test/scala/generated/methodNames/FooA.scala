package generated.meta.test.methodNames

class FooA() extends meta.runtime.Actor {


  private var  reflectionIR_58: Int = -1
private var resetData_0: scala.Any = null
private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]()
private var resetData_2: meta.runtime.ResponseMessage = null
private var bindingMut_3: scala.Any = null
private var listValMut_4: meta.runtime.RequestMessage = null
@transient private var iterMut_5: scala.collection.Iterator[meta.runtime.RequestMessage] = null
private var bindingMut_6: scala.Double = 0.0
private var unblockFlag_7: scala.Boolean = true
private var positionVar_8: scala.Int = 0
private 
  val commands_75 = (() => {
  val data_9 = new scala.Array[scala.Function0[scala.Unit]](37);
  data_9.update(0, (() => positionVar_8 = 1));
  data_9.update(1, (() => {
    resetData_0 = 0.0;
    val x_10 = resetData_0;
    val x_11 = x_10.asInstanceOf[scala.Double];
    bindingMut_6 = x_11;
    positionVar_8 = 2
  }));
  data_9.update(2, (() => {
    val x_12 = bindingMut_6;
    val x_13 = x_12.+(1);
    resetData_0 = x_13;
    val x_14 = resetData_0;
    val x_15 = x_14.asInstanceOf[scala.Double];
    bindingMut_6 = x_15;
    positionVar_8 = 3;
    unblockFlag_7 = false
  }));
  data_9.update(3, (() => positionVar_8 = 4));
  data_9.update(4, (() => {
    val x_16 = this.popRequestMessages;
    val x_17 = x_16.iterator;
    iterMut_5 = x_17;
    positionVar_8 = 5
  }));
  data_9.update(5, (() => {
    val x_18 = iterMut_5;
    val x_19 = x_18.hasNext;
    if (x_19)
      {
        val x_20 = iterMut_5;
        val x_21 = x_20.next();
        listValMut_4 = x_21;
        positionVar_8 = 6
      }
    else
      positionVar_8 = 28
  }));
  data_9.update(6, (() => {
    val x_22 = listValMut_4;
    val x_23 = x_22.methodInfo;
    val x_24 = x_23.==("get_Name__");
    if (x_24)
      positionVar_8 = 7
    else
      positionVar_8 = 13
  }));
  data_9.update(7, (() => {
    val x_25 = listValMut_4;
    this.handleNonblockingMessage(x_25);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(8, (() => positionVar_8 = 9));
  data_9.update(9, (() => positionVar_8 = 5));
  data_9.update(10, (() => positionVar_8 = 11));
  data_9.update(11, (() => {
    val x_26 = this.id;
    val x_27 = x_26.+(" get_Name__ is called!");
    scala.Predef.println(x_27);
    resetData_0 = "Foo!";
    positionVar_8 = 12
  }));
  data_9.update(12, (() => {
    val x_28 = resetData_0;
    val x_29 = x_28.asInstanceOf[scala.Any];
    bindingMut_3 = x_29;
    val x_30 = bindingMut_3;
    val x_31 = listValMut_4;
    x_31.reply(this, x_30);
    resetData_0 = ();
    positionVar_8 = 8
  }));
  data_9.update(13, (() => {
    val x_32 = listValMut_4;
    val x_33 = x_32.methodInfo;
    val x_34 = x_33.==("get_Name__");
    val x_35 = x_34.`unary_!`;
    if (x_35)
      positionVar_8 = 14
    else
      ()
  }));
  data_9.update(14, (() => {
    val x_36 = listValMut_4;
    val x_37 = x_36.methodInfo;
    val x_38 = x_37.==("__get___Name__");
    if (x_38)
      positionVar_8 = 15
    else
      positionVar_8 = 20
  }));
  data_9.update(15, (() => {
    val x_39 = listValMut_4;
    this.handleNonblockingMessage(x_39);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(16, (() => positionVar_8 = 9));
  data_9.update(17, (() => positionVar_8 = 18));
  data_9.update(18, (() => {
    val x_40 = this.id;
    val x_41 = x_40.+(" __get___Name__ is called!");
    scala.Predef.println(x_41);
    resetData_0 = "Hello";
    positionVar_8 = 19
  }));
  data_9.update(19, (() => {
    val x_42 = resetData_0;
    val x_43 = x_42.asInstanceOf[scala.Any];
    bindingMut_3 = x_43;
    val x_44 = bindingMut_3;
    val x_45 = listValMut_4;
    x_45.reply(this, x_44);
    resetData_0 = ();
    positionVar_8 = 16
  }));
  data_9.update(20, (() => {
    val x_46 = listValMut_4;
    val x_47 = x_46.methodInfo;
    val x_48 = x_47.==("__get___Name__");
    val x_49 = x_48.`unary_!`;
    if (x_49)
      positionVar_8 = 21
    else
      ()
  }));
  data_9.update(21, (() => {
    val x_50 = listValMut_4;
    val x_51 = x_50.methodInfo;
    val x_52 = x_51.==("___get_Name___");
    if (x_52)
      positionVar_8 = 22
    else
      positionVar_8 = 27
  }));
  data_9.update(22, (() => {
    val x_53 = listValMut_4;
    this.handleNonblockingMessage(x_53);
    resetData_0 = ();
    positionVar_8 = 23
  }));
  data_9.update(23, (() => positionVar_8 = 9));
  data_9.update(24, (() => positionVar_8 = 25));
  data_9.update(25, (() => {
    val x_54 = this.id;
    val x_55 = x_54.+(" ___get_Name___ is called!");
    scala.Predef.println(x_55);
    resetData_0 = "World";
    positionVar_8 = 26
  }));
  data_9.update(26, (() => {
    val x_56 = resetData_0;
    val x_57 = x_56.asInstanceOf[scala.Any];
    bindingMut_3 = x_57;
    val x_58 = bindingMut_3;
    val x_59 = listValMut_4;
    x_59.reply(this, x_58);
    resetData_0 = ();
    positionVar_8 = 23
  }));
  data_9.update(27, (() => {
    val x_60 = listValMut_4;
    val x_61 = x_60.methodInfo;
    val x_62 = x_61.==("___get_Name___");
    val x_63 = x_62.`unary_!`;
    if (x_63)
      {
        val x_64 = listValMut_4;
        val x_65 = scala.collection.immutable.List.apply[meta.runtime.RequestMessage](x_64);
        val x_66 = this.addReceiveMessages(x_65);
        resetData_0 = x_66;
        positionVar_8 = 9
      }
    else
      ()
  }));
  data_9.update(28, (() => {
    val x_67 = iterMut_5;
    val x_68 = x_67.hasNext;
    val x_69 = x_68.`unary_!`;
    if (x_69)
      positionVar_8 = 29
    else
      ()
  }));
  data_9.update(29, (() => positionVar_8 = 30));
  data_9.update(30, (() => {
    val x_70 = bindingMut_6;
    val x_71 = x_70.<(1.0);
    if (x_71)
      positionVar_8 = 2
    else
      positionVar_8 = 31
  }));
  data_9.update(31, (() => {
    val x_72 = bindingMut_6;
    val x_73 = x_72.<(1.0);
    val x_74 = x_73.`unary_!`;
    if (x_74)
      positionVar_8 = 32
    else
      ()
  }));
  data_9.update(32, (() => positionVar_8 = 1));
  data_9.update(33, (() => positionVar_8 = 34));
  data_9.update(34, (() => {
    positionVar_8 = 35;
    unblockFlag_7 = false
  }));
  data_9.update(35, (() => positionVar_8 = 34));
  data_9.update(36, (() => positionVar_8 = 34));
  data_9
}).apply();
  

   def get_Name__(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" get_Name__ is called!");
  scala.Predef.println(x_1);
  "Foo!"
}
  
  private def wrapper_get_Name__(args: List[Any]): String = {
    
          
          get_Name__()
          
  }
  
   def ___get_Name___(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" ___get_Name___ is called!");
  scala.Predef.println(x_1);
  "World"
}
  
  private def wrapper____get_Name___(args: List[Any]): String = {
    
          
          ___get_Name___()
          
  }
  
   def __get___Name__(): String = 
      {
  val x_0 = this.id;
  val x_1 = x_0.+(" __get___Name__ is called!");
  scala.Predef.println(x_1);
  "Hello"
}
  
  private def wrapper___get___Name__(args: List[Any]): String = {
    
          
          __get___Name__()
          
  }
  
  override def run(msgs: List[meta.runtime.Message]): (List[meta.runtime.Message], Int) = {
    addReceiveMessages(msgs)
    sendMessages.clear()
    unblockFlag_7 = true
    while (unblockFlag_7 && (positionVar_8 < 37)) {
      commands_75(positionVar_8)()
    }
    (sendMessages.toList, 1)
  }

  override def handleNonblockingMessage(m: meta.runtime.RequestMessage): Unit = {
    val args = m.argss.flatten
    val response = m.methodInfo match {
      case "get_Name__" => wrapper_get_Name__(args)
    case "___get_Name___" => wrapper____get_Name___(args)
    case "__get___Name__" => wrapper___get___Name__(args)
    }
    m.reply(this, response)
  }
  
    override def gotoHandleMessages(new_ir: Int = 3): meta.runtime.Actor = {
      // first entry, save the current IR to reflectionIR
      unblockFlag_7 = true

      if (reflectionIR_58 == -1){
        reflectionIR_58 = positionVar_8
        positionVar_8 = new_ir
      }

      while (positionVar_8 <= 27 && unblockFlag_7) {
        commands_75(positionVar_8)()
      }

      // reset instruction register when finishes processing
      if (positionVar_8 > 27) {
        positionVar_8 = reflectionIR_58
        reflectionIR_58 = -1
      }
      this
    }
    
override def SimClone(): FooA = {
  val newAgent = new FooA()

  newAgent
}

override def SimReset(): Unit = {
  positionVar_8 = 0
  
}

}
