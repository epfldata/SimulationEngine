package generated.example.epistemicLogicMC

class Adult (val children: List[generated.example.epistemicLogicMC.Child]) extends meta.deep.runtime.Actor {
  var knowledgeBase: lib.EpistemicLogic.KnowledgeBase = new lib.EpistemicLogic.KnowledgeBase()
  var future_objs: scala.collection.mutable.ListBuffer[Option[meta.deep.runtime.Future[Unit]]] = new scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]()
  var future_objs1: scala.collection.mutable.ListBuffer[Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = new scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]()
  var allChildrenMuddy: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence] = new scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]()
  var allChildrenUnaware: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence] = new scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]()
  private var resetData_0: scala.Any = null;
  private val resetData_1 = scala.collection.mutable.ListBuffer.apply[scala.collection.immutable.List[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]]]();
  private var resetData_2: meta.deep.runtime.ResponseMessage = null;
  private var bindingMut_3: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_4: lib.EpistemicLogic.Sentence.NotE = null;
  private var bindingMut_5: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_6: scala.Long = 0L;
  private var bindingMut_7: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_8: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_9: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_10: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_11: scala.Long = 0L;
  private var bindingMut_12: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_13: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_14: scala.Boolean = false;
  private var bindingMut_15: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_16: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_17: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_18: scala.Boolean = false;
  private var bindingMut_19: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var bindingMut_20: scala.Long = 0L;
  private var bindingMut_21: example.epistemicLogicMC.MCHelper.ChildStatus = null;
  private var listValMut_22: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var iterMut_23: scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_24: scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_25: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_26: meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var listValMut_27: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var iterMut_28: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_29: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_30: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_31: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus] = null;
  private var bindingMut_32: meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var listValMut_33: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var iterMut_34: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_35: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_36: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var bindingMut_37: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]] = null;
  private var bindingMut_38: scala.Double = 0.0;
  private var bindingMut_39: scala.Double = 0.0;
  private var bindingMut_40: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]] = null;
  private var bindingMut_41: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]] = null;
  private var listValMut_42: generated.example.epistemicLogicMC.Child = null;
  private var iterMut_43: scala.collection.Iterator[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_44: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_45: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_46: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]] = null;
  private var bindingMut_47: meta.deep.runtime.Future[scala.Unit] = null;
  private var listValMut_48: scala.Option[meta.deep.runtime.Future[scala.Unit]] = null;
  private var iterMut_49: scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Unit]]] = null;
  private var bindingMut_50: scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Unit]]] = null;
  private var bindingMut_51: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]] = null;
  private var bindingMut_52: scala.Double = 0.0;
  private var bindingMut_53: scala.Double = 0.0;
  private var bindingMut_54: scala.Option[meta.deep.runtime.Future[scala.Unit]] = null;
  private var bindingMut_55: scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]] = null;
  private var listValMut_56: generated.example.epistemicLogicMC.Child = null;
  private var iterMut_57: scala.collection.Iterator[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_58: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_59: scala.Double = 0.0;
  private var bindingMut_60: scala.Double = 0.0;
  private var bindingMut_61: scala.Boolean = false;
  private var bindingMut_62: scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_63: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_64: lib.EpistemicLogic.KnowledgeBase = null;
  private var bindingMut_65: lib.EpistemicLogic.Sentence.NotE = null;
  private var bindingMut_66: lib.EpistemicLogic.Sentence.Ka[scala.Long] = null;
  private var bindingMut_67: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_68: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_69: scala.Boolean = false;
  private var bindingMut_70: scala.Long = 0L;
  private var bindingMut_71: scala.Long = 0L;
  private var bindingMut_72: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var bindingMut_73: lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy] = null;
  private var bindingMut_74: example.epistemicLogicMC.MCHelper.ChildMuddy = null;
  private var bindingMut_75: scala.Boolean = false;
  private var bindingMut_76: scala.Long = 0L;
  private var bindingMut_77: scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence] = null;
  private var listValMut_78: generated.example.epistemicLogicMC.Child = null;
  private var iterMut_79: scala.collection.Iterator[generated.example.epistemicLogicMC.Child] = null;
  private var bindingMut_80: scala.collection.immutable.List[generated.example.epistemicLogicMC.Child] = null;
  private var positionVar_82: scala.Int = 0;
  
  val commands_725 = (() => {
  val data_83 = new scala.Array[scala.Function0[scala.Unit]](59);
  data_83.update(0, (() => {
    val x_84 = this.children;
    resetData_0 = x_84;
    val x_85 = resetData_0;
    val x_86 = x_85.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    bindingMut_80 = x_86;
    val x_87 = bindingMut_80;
    val x_88 = x_87.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    val x_89 = x_88.iterator;
    iterMut_79 = x_89;
    positionVar_82 = 1
  }));
  data_83.update(1, (() => {
    val x_90 = iterMut_79;
    val x_91 = x_90.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_92 = x_91.hasNext;
    val x_93 = x_92.`unary_!`;
    if (x_93)
      positionVar_82 = 2
    else
      positionVar_82 = 58
  }));
  data_83.update(2, (() => if (true)
    positionVar_82 = 3
  else
    positionVar_82 = 57));
  data_83.update(3, (() => {
    positionVar_82 = 4;
    val x_94 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_95 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_94, 46);
    val x_96 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_95);
    resetData_1.prepend(x_96)
  }));
  data_83.update(4, (() => {
    val x_97 = this.knowledgeBase;
    resetData_0 = x_97;
    val x_98 = resetData_0;
    val x_99 = x_98.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_45 = x_99;
    val x_100 = bindingMut_45;
    val x_101 = x_100.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    x_101.forgetAll();
    resetData_0 = ();
    val x_102 = this.children;
    resetData_0 = x_102;
    val x_103 = resetData_0;
    val x_104 = x_103.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    bindingMut_44 = x_104;
    val x_105 = bindingMut_44;
    val x_106 = x_105.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    val x_107 = x_106.iterator;
    iterMut_43 = x_107;
    positionVar_82 = 5
  }));
  data_83.update(5, (() => {
    val x_108 = iterMut_43;
    val x_109 = x_108.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_110 = x_109.hasNext;
    val x_111 = x_110.`unary_!`;
    if (x_111)
      positionVar_82 = 6
    else
      positionVar_82 = 56
  }));
  data_83.update(6, (() => {
    val x_112 = this.future_objs1;
    val x_113 = x_112.toList;
    val x_116 = x_113.forall(((x_114: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_115 = x_114.get;
      this.isCompleted(x_115)
    }));
    val x_117 = x_116.`unary_!`;
    if (x_117)
      positionVar_82 = 7
    else
      positionVar_82 = 55
  }));
  data_83.update(7, (() => {
    val x_118 = 1.toDouble;
    resetData_0 = x_118;
    val x_119 = resetData_0;
    val x_120 = x_119.asInstanceOf[scala.Double];
    bindingMut_39 = x_120;
    resetData_0 = 0.0;
    val x_121 = resetData_0;
    val x_122 = x_121.asInstanceOf[scala.Double];
    bindingMut_38 = x_122;
    positionVar_82 = 8
  }));
  data_83.update(8, (() => {
    val x_123 = meta.deep.runtime.Actor.proceedLabel;
    val x_124 = x_123("turn");
    val x_125 = bindingMut_38;
    val x_126 = x_125.asInstanceOf[scala.Double];
    val x_127 = x_126.+(x_124);
    resetData_0 = x_127;
    val x_128 = resetData_0;
    val x_129 = x_128.asInstanceOf[scala.Double];
    bindingMut_38 = x_129;
    val x_130 = meta.deep.runtime.Actor.labelVals("turn");
    val x_131 = bindingMut_38;
    val x_132 = x_131.asInstanceOf[scala.Double];
    val x_133 = bindingMut_39;
    val x_134 = x_133.asInstanceOf[scala.Double];
    val x_135 = x_134.-(x_132);
    x_130.append(x_135);
    resetData_0 = ();
    positionVar_82 = 9;
    val x_136 = currentTurn;
    val x_137 = x_136.+(1);
    currentTurn = x_137
  }));
  data_83.update(9, (() => {
    val x_138 = bindingMut_38;
    val x_139 = x_138.asInstanceOf[scala.Double];
    val x_140 = bindingMut_39;
    val x_141 = x_140.asInstanceOf[scala.Double];
    val x_142 = x_139.<(x_141);
    if (x_142)
      positionVar_82 = 8
    else
      positionVar_82 = 10
  }));
  data_83.update(10, (() => {
    val x_143 = bindingMut_38;
    val x_144 = x_143.asInstanceOf[scala.Double];
    val x_145 = bindingMut_39;
    val x_146 = x_145.asInstanceOf[scala.Double];
    val x_147 = x_144.<(x_146);
    val x_148 = x_147.`unary_!`;
    if (x_148)
      positionVar_82 = 11
    else
      ()
  }));
  data_83.update(11, (() => {
    val x_149 = this.future_objs1;
    val x_150 = x_149.toList;
    val x_153 = x_150.forall(((x_151: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_152 = x_151.get;
      this.isCompleted(x_152)
    }));
    val x_154 = x_153.`unary_!`;
    if (x_154)
      positionVar_82 = 7
    else
      positionVar_82 = 12
  }));
  data_83.update(12, (() => {
    val x_155 = this.future_objs1;
    val x_156 = x_155.toList;
    val x_159 = x_156.forall(((x_157: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_158 = x_157.get;
      this.isCompleted(x_158)
    }));
    val x_160 = x_159.`unary_!`;
    val x_161 = x_160.`unary_!`;
    if (x_161)
      positionVar_82 = 13
    else
      ()
  }));
  data_83.update(13, (() => {
    val x_162 = new scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]();
    resetData_0 = x_162;
    val x_163 = resetData_0;
    val x_164 = x_163.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    bindingMut_37 = x_164;
    val x_165 = this.future_objs1;
    resetData_0 = x_165;
    val x_166 = resetData_0;
    val x_167 = x_166.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    bindingMut_36 = x_167;
    val x_168 = bindingMut_36;
    val x_169 = x_168.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_170 = x_169.toList;
    resetData_0 = x_170;
    val x_171 = resetData_0;
    val x_172 = x_171.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    bindingMut_35 = x_172;
    val x_173 = bindingMut_35;
    val x_174 = x_173.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_175 = x_174.iterator;
    iterMut_34 = x_175;
    positionVar_82 = 14
  }));
  data_83.update(14, (() => {
    val x_176 = iterMut_34;
    val x_177 = x_176.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_178 = x_177.hasNext;
    if (x_178)
      {
        val x_179 = iterMut_34;
        val x_180 = x_179.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_181 = x_180.next();
        listValMut_33 = x_181;
        val x_182 = listValMut_33;
        val x_183 = x_182.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_184 = x_183.get;
        resetData_0 = x_184;
        val x_185 = resetData_0;
        val x_186 = x_185.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_32 = x_186;
        val x_187 = bindingMut_32;
        val x_188 = x_187.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_189 = this.getFutureValue[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]](x_188);
        resetData_0 = x_189;
        val x_190 = resetData_0;
        val x_191 = x_190.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        bindingMut_31 = x_191;
        val x_192 = bindingMut_31;
        val x_193 = x_192.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_194 = bindingMut_37;
        val x_195 = x_194.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        x_195.append(x_193);
        resetData_0 = ();
        positionVar_82 = 14
      }
    else
      positionVar_82 = 15
  }));
  data_83.update(15, (() => {
    val x_196 = iterMut_34;
    val x_197 = x_196.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_198 = x_197.hasNext;
    val x_199 = x_198.`unary_!`;
    if (x_199)
      {
        val x_200 = this.future_objs1;
        resetData_0 = x_200;
        val x_201 = resetData_0;
        val x_202 = x_201.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_30 = x_202;
        val x_203 = bindingMut_30;
        val x_204 = x_203.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_205 = x_204.toList;
        resetData_0 = x_205;
        val x_206 = resetData_0;
        val x_207 = x_206.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_29 = x_207;
        val x_208 = bindingMut_29;
        val x_209 = x_208.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_210 = x_209.iterator;
        iterMut_28 = x_210;
        positionVar_82 = 16
      }
    else
      ()
  }));
  data_83.update(16, (() => {
    val x_211 = iterMut_28;
    val x_212 = x_211.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_213 = x_212.hasNext;
    if (x_213)
      {
        val x_214 = iterMut_28;
        val x_215 = x_214.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        val x_216 = x_215.next();
        listValMut_27 = x_216;
        val x_217 = listValMut_27;
        val x_218 = x_217.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_219 = x_218.get;
        resetData_0 = x_219;
        val x_220 = resetData_0;
        val x_221 = x_220.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_26 = x_221;
        val x_222 = bindingMut_26;
        val x_223 = x_222.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_224 = this.clearFutureObj(x_223);
        resetData_0 = x_224;
        positionVar_82 = 16
      }
    else
      positionVar_82 = 17
  }));
  data_83.update(17, (() => {
    val x_225 = iterMut_28;
    val x_226 = x_225.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
    val x_227 = x_226.hasNext;
    val x_228 = x_227.`unary_!`;
    if (x_228)
      {
        val x_229 = this.future_objs1;
        resetData_0 = x_229;
        val x_230 = resetData_0;
        val x_231 = x_230.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_25 = x_231;
        val x_232 = bindingMut_25;
        val x_233 = x_232.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        x_233.clear();
        resetData_0 = ();
        val x_234 = bindingMut_37;
        val x_235 = x_234.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_236 = x_235.toList;
        resetData_0 = x_236;
        val x_237 = resetData_0;
        val x_238 = x_237.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        bindingMut_24 = x_238;
        val x_239 = bindingMut_24;
        val x_240 = x_239.asInstanceOf[scala.collection.immutable.List[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_241 = x_240.iterator;
        iterMut_23 = x_241;
        positionVar_82 = 18
      }
    else
      ()
  }));
  data_83.update(18, (() => {
    val x_242 = iterMut_23;
    val x_243 = x_242.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_244 = x_243.hasNext;
    val x_245 = x_244.`unary_!`;
    if (x_245)
      {
        val x_246 = resetData_1.remove(0);
        val x_250 = x_246.find(((x_247: scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]) => {
          val x_248 = x_247._1;
          val x_249 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
          x_248.==(x_249)
        }));
        val x_251 = x_250.get;
        val x_252 = x_251._2;
        positionVar_82 = x_252
      }
    else
      positionVar_82 = 51
  }));
  data_83.update(19, (() => positionVar_82 = 20));
  data_83.update(20, (() => {
    val x_253 = this.knowledgeBase;
    val x_254 = this.allChildrenUnaware;
    val x_255 = x_254.toSet[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_256 = x_253.knowAny(x_255);
    if (x_256)
      positionVar_82 = 21
    else
      positionVar_82 = 35
  }));
  data_83.update(21, (() => {
    scala.Predef.println("There is at least one muddy child.");
    resetData_0 = ();
    scala.Predef.println("Step up if you know whether you are clean or muddy");
    resetData_0 = ();
    val x_257 = this.children;
    resetData_0 = x_257;
    val x_258 = resetData_0;
    val x_259 = x_258.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    bindingMut_58 = x_259;
    val x_260 = bindingMut_58;
    val x_261 = x_260.asInstanceOf[scala.collection.immutable.List[generated.example.epistemicLogicMC.Child]];
    val x_262 = x_261.iterator;
    iterMut_57 = x_262;
    positionVar_82 = 22
  }));
  data_83.update(22, (() => {
    val x_263 = iterMut_57;
    val x_264 = x_263.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_265 = x_264.hasNext;
    val x_266 = x_265.`unary_!`;
    if (x_266)
      positionVar_82 = 23
    else
      positionVar_82 = 34
  }));
  data_83.update(23, (() => {
    val x_267 = this.future_objs;
    val x_268 = x_267.nonEmpty;
    val x_273 = x_268.&&({
      val x_269 = this.future_objs;
      val x_270 = x_269.toList;
      x_270.forall(((x_271: scala.Option[meta.deep.runtime.Future[scala.Unit]]) => {
        val x_272 = x_271.get;
        this.isCompleted(x_272)
      }))
    });
    val x_274 = x_273.`unary_!`;
    if (x_274)
      positionVar_82 = 24
    else
      positionVar_82 = 33
  }));
  data_83.update(24, (() => {
    val x_275 = 1.toDouble;
    resetData_0 = x_275;
    val x_276 = resetData_0;
    val x_277 = x_276.asInstanceOf[scala.Double];
    bindingMut_53 = x_277;
    resetData_0 = 0.0;
    val x_278 = resetData_0;
    val x_279 = x_278.asInstanceOf[scala.Double];
    bindingMut_52 = x_279;
    positionVar_82 = 25
  }));
  data_83.update(25, (() => {
    val x_280 = meta.deep.runtime.Actor.proceedLabel;
    val x_281 = x_280("turn");
    val x_282 = bindingMut_52;
    val x_283 = x_282.asInstanceOf[scala.Double];
    val x_284 = x_283.+(x_281);
    resetData_0 = x_284;
    val x_285 = resetData_0;
    val x_286 = x_285.asInstanceOf[scala.Double];
    bindingMut_52 = x_286;
    val x_287 = meta.deep.runtime.Actor.labelVals("turn");
    val x_288 = bindingMut_52;
    val x_289 = x_288.asInstanceOf[scala.Double];
    val x_290 = bindingMut_53;
    val x_291 = x_290.asInstanceOf[scala.Double];
    val x_292 = x_291.-(x_289);
    x_287.append(x_292);
    resetData_0 = ();
    positionVar_82 = 26;
    val x_293 = currentTurn;
    val x_294 = x_293.+(1);
    currentTurn = x_294
  }));
  data_83.update(26, (() => {
    val x_295 = bindingMut_52;
    val x_296 = x_295.asInstanceOf[scala.Double];
    val x_297 = bindingMut_53;
    val x_298 = x_297.asInstanceOf[scala.Double];
    val x_299 = x_296.<(x_298);
    if (x_299)
      positionVar_82 = 25
    else
      positionVar_82 = 27
  }));
  data_83.update(27, (() => {
    val x_300 = bindingMut_52;
    val x_301 = x_300.asInstanceOf[scala.Double];
    val x_302 = bindingMut_53;
    val x_303 = x_302.asInstanceOf[scala.Double];
    val x_304 = x_301.<(x_303);
    val x_305 = x_304.`unary_!`;
    if (x_305)
      positionVar_82 = 28
    else
      ()
  }));
  data_83.update(28, (() => {
    val x_306 = this.future_objs;
    val x_307 = x_306.nonEmpty;
    val x_312 = x_307.&&({
      val x_308 = this.future_objs;
      val x_309 = x_308.toList;
      x_309.forall(((x_310: scala.Option[meta.deep.runtime.Future[scala.Unit]]) => {
        val x_311 = x_310.get;
        this.isCompleted(x_311)
      }))
    });
    val x_313 = x_312.`unary_!`;
    if (x_313)
      positionVar_82 = 24
    else
      positionVar_82 = 29
  }));
  data_83.update(29, (() => {
    val x_314 = this.future_objs;
    val x_315 = x_314.nonEmpty;
    val x_320 = x_315.&&({
      val x_316 = this.future_objs;
      val x_317 = x_316.toList;
      x_317.forall(((x_318: scala.Option[meta.deep.runtime.Future[scala.Unit]]) => {
        val x_319 = x_318.get;
        this.isCompleted(x_319)
      }))
    });
    val x_321 = x_320.`unary_!`;
    val x_322 = x_321.`unary_!`;
    if (x_322)
      positionVar_82 = 30
    else
      ()
  }));
  data_83.update(30, (() => {
    val x_323 = this.future_objs;
    resetData_0 = x_323;
    val x_324 = resetData_0;
    val x_325 = x_324.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    bindingMut_51 = x_325;
    val x_326 = bindingMut_51;
    val x_327 = x_326.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    val x_328 = x_327.toList;
    resetData_0 = x_328;
    val x_329 = resetData_0;
    val x_330 = x_329.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    bindingMut_50 = x_330;
    val x_331 = bindingMut_50;
    val x_332 = x_331.asInstanceOf[scala.collection.immutable.List[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    val x_333 = x_332.iterator;
    iterMut_49 = x_333;
    positionVar_82 = 31
  }));
  data_83.update(31, (() => {
    val x_334 = iterMut_49;
    val x_335 = x_334.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    val x_336 = x_335.hasNext;
    if (x_336)
      {
        val x_337 = iterMut_49;
        val x_338 = x_337.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
        val x_339 = x_338.next();
        listValMut_48 = x_339;
        val x_340 = listValMut_48;
        val x_341 = x_340.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Unit]]];
        val x_342 = x_341.get;
        resetData_0 = x_342;
        val x_343 = resetData_0;
        val x_344 = x_343.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
        bindingMut_47 = x_344;
        val x_345 = bindingMut_47;
        val x_346 = x_345.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
        val x_347 = this.clearFutureObj(x_346);
        resetData_0 = x_347;
        positionVar_82 = 31
      }
    else
      positionVar_82 = 32
  }));
  data_83.update(32, (() => {
    val x_348 = iterMut_49;
    val x_349 = x_348.asInstanceOf[scala.collection.Iterator[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
    val x_350 = x_349.hasNext;
    val x_351 = x_350.`unary_!`;
    if (x_351)
      {
        val x_352 = this.future_objs;
        resetData_0 = x_352;
        val x_353 = resetData_0;
        val x_354 = x_353.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
        bindingMut_46 = x_354;
        val x_355 = bindingMut_46;
        val x_356 = x_355.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
        x_356.clear();
        resetData_0 = ();
        positionVar_82 = 4
      }
    else
      ();
    val x_357 = scala.Tuple2.apply[scala.Int, scala.Int](-1, -1);
    val x_358 = scala.Tuple2.apply[scala.Tuple2[scala.Int, scala.Int], scala.Int](x_357, 19);
    val x_359 = scala.collection.immutable.Nil.::[scala.Tuple2[scala.Tuple2[scala.Int, scala.Int], scala.Int]](x_358);
    resetData_1.prepend(x_359)
  }));
  data_83.update(33, (() => {
    val x_360 = this.future_objs;
    val x_361 = x_360.nonEmpty;
    val x_366 = x_361.&&({
      val x_362 = this.future_objs;
      val x_363 = x_362.toList;
      x_363.forall(((x_364: scala.Option[meta.deep.runtime.Future[scala.Unit]]) => {
        val x_365 = x_364.get;
        this.isCompleted(x_365)
      }))
    });
    val x_367 = x_366.`unary_!`;
    val x_368 = x_367.`unary_!`;
    if (x_368)
      positionVar_82 = 30
    else
      ()
  }));
  data_83.update(34, (() => {
    val x_369 = iterMut_57;
    val x_370 = x_369.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_371 = x_370.hasNext;
    if (x_371)
      {
        val x_372 = iterMut_57;
        val x_373 = x_372.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
        val x_374 = x_373.next();
        listValMut_56 = x_374;
        val x_375 = this.future_objs;
        resetData_0 = x_375;
        val x_376 = resetData_0;
        val x_377 = x_376.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
        bindingMut_55 = x_377;
        val x_378 = ((this): meta.deep.runtime.Actor).id;
        val x_380 = {
          val x_379 = listValMut_56;
          x_379.asInstanceOf[generated.example.epistemicLogicMC.Child]
        };
        val x_381 = x_380.id;
        val x_382 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_383 = meta.deep.runtime.RequestMessage.apply(x_378, x_381, 4, x_382);
        val x_384 = x_383.future;
        val x_385 = x_384.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
        x_383.`future_=`(x_385);
        ((this): meta.deep.runtime.Actor).sendMessage(x_383);
        val x_386 = x_383.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_386, ((response_387: meta.deep.runtime.Message) => {
          val x_388 = x_383.future;
          val x_389 = response_387.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_390 = x_389.arg;
          val x_391 = x_388.setValue[scala.Any](x_390);
          val x_392 = x_391.asInstanceOf[meta.deep.runtime.Future[scala.Unit]];
          x_383.`future_=`(x_392);
          val x_393 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_394 = x_383.future;
          val x_395 = x_394.id;
          val x_396 = scala.Predef.ArrowAssoc[java.lang.String](x_395);
          val x_397 = x_383.future;
          val x_398 = x_396.->[meta.deep.runtime.Future[scala.Any]](x_397);
          val x_399 = x_393.+[meta.deep.runtime.Future[scala.Any]](x_398);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_399)
        }));
        val x_400 = x_383.future;
        val x_401 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_400);
        resetData_0 = x_401;
        val x_402 = resetData_0;
        val x_403 = x_402.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Unit]]];
        bindingMut_54 = x_403;
        val x_404 = bindingMut_54;
        val x_405 = x_404.asInstanceOf[scala.Option[meta.deep.runtime.Future[scala.Unit]]];
        val x_406 = bindingMut_55;
        val x_407 = x_406.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[scala.Unit]]]];
        x_407.append(x_405);
        resetData_0 = ();
        positionVar_82 = 22
      }
    else
      ()
  }));
  data_83.update(35, (() => {
    val x_408 = this.knowledgeBase;
    val x_409 = this.allChildrenUnaware;
    val x_410 = x_409.toSet[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_411 = x_408.knowAny(x_410);
    val x_412 = x_411.`unary_!`;
    if (x_412)
      positionVar_82 = 36
    else
      ()
  }));
  data_83.update(36, (() => positionVar_82 = 37));
  data_83.update(37, (() => {
    val x_413 = 1.toDouble;
    resetData_0 = x_413;
    val x_414 = resetData_0;
    val x_415 = x_414.asInstanceOf[scala.Double];
    bindingMut_60 = x_415;
    resetData_0 = 0.0;
    val x_416 = resetData_0;
    val x_417 = x_416.asInstanceOf[scala.Double];
    bindingMut_59 = x_417;
    positionVar_82 = 38
  }));
  data_83.update(38, (() => {
    val x_418 = meta.deep.runtime.Actor.proceedLabel;
    val x_419 = x_418("turn");
    val x_420 = bindingMut_59;
    val x_421 = x_420.asInstanceOf[scala.Double];
    val x_422 = x_421.+(x_419);
    resetData_0 = x_422;
    val x_423 = resetData_0;
    val x_424 = x_423.asInstanceOf[scala.Double];
    bindingMut_59 = x_424;
    val x_425 = meta.deep.runtime.Actor.labelVals("turn");
    val x_426 = bindingMut_59;
    val x_427 = x_426.asInstanceOf[scala.Double];
    val x_428 = bindingMut_60;
    val x_429 = x_428.asInstanceOf[scala.Double];
    val x_430 = x_429.-(x_427);
    x_425.append(x_430);
    resetData_0 = ();
    positionVar_82 = 39;
    val x_431 = currentTurn;
    val x_432 = x_431.+(1);
    currentTurn = x_432
  }));
  data_83.update(39, (() => {
    val x_433 = bindingMut_59;
    val x_434 = x_433.asInstanceOf[scala.Double];
    val x_435 = bindingMut_60;
    val x_436 = x_435.asInstanceOf[scala.Double];
    val x_437 = x_434.<(x_436);
    if (x_437)
      positionVar_82 = 38
    else
      positionVar_82 = 40
  }));
  data_83.update(40, (() => {
    val x_438 = bindingMut_59;
    val x_439 = x_438.asInstanceOf[scala.Double];
    val x_440 = bindingMut_60;
    val x_441 = x_440.asInstanceOf[scala.Double];
    val x_442 = x_439.<(x_441);
    val x_443 = x_442.`unary_!`;
    if (x_443)
      positionVar_82 = 41
    else
      ()
  }));
  data_83.update(41, (() => if (true)
    positionVar_82 = 3
  else
    positionVar_82 = 42));
  data_83.update(42, (() => {
    val x_444 = true.`unary_!`;
    if (x_444)
      positionVar_82 = 43
    else
      ()
  }));
  data_83.update(43, (() => positionVar_82 = 44));
  data_83.update(44, (() => {
    positionVar_82 = 45;
    val x_445 = currentTurn;
    val x_446 = x_445.+(1);
    currentTurn = x_446
  }));
  data_83.update(45, (() => positionVar_82 = 44));
  data_83.update(46, (() => {
    val x_447 = this.knowledgeBase;
    resetData_0 = x_447;
    val x_448 = resetData_0;
    val x_449 = x_448.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    bindingMut_64 = x_449;
    val x_450 = this.allChildrenMuddy;
    resetData_0 = x_450;
    val x_451 = resetData_0;
    val x_452 = x_451.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_63 = x_452;
    val x_453 = bindingMut_63;
    val x_454 = x_453.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_455 = x_454.toSet[lib.EpistemicLogic.Sentence.EpistemicSentence];
    resetData_0 = x_455;
    val x_456 = resetData_0;
    val x_457 = x_456.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    bindingMut_62 = x_457;
    val x_458 = bindingMut_62;
    val x_459 = x_458.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
    val x_460 = bindingMut_64;
    val x_461 = x_460.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
    val x_462 = x_461.knowAny(x_459);
    resetData_0 = x_462;
    val x_463 = resetData_0;
    val x_464 = x_463.asInstanceOf[scala.Boolean];
    bindingMut_61 = x_464;
    positionVar_82 = 47
  }));
  data_83.update(47, (() => {
    val x_465 = bindingMut_61;
    val x_466 = x_465.asInstanceOf[scala.Boolean];
    if (x_466)
      positionVar_82 = 48
    else
      positionVar_82 = 50
  }));
  data_83.update(48, (() => {
    val x_467 = this.knowledgeBase;
    val x_468 = this.allChildrenUnaware;
    val x_469 = x_468.toSet[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_470 = x_467.knowAny(x_469);
    if (x_470)
      positionVar_82 = 21
    else
      positionVar_82 = 49
  }));
  data_83.update(49, (() => {
    val x_471 = this.knowledgeBase;
    val x_472 = this.allChildrenUnaware;
    val x_473 = x_472.toSet[lib.EpistemicLogic.Sentence.EpistemicSentence];
    val x_474 = x_471.knowAny(x_473);
    val x_475 = x_474.`unary_!`;
    if (x_475)
      positionVar_82 = 36
    else
      ()
  }));
  data_83.update(50, (() => {
    val x_476 = bindingMut_61;
    val x_477 = x_476.asInstanceOf[scala.Boolean];
    val x_478 = x_477.`unary_!`;
    if (x_478)
      positionVar_82 = 37
    else
      ()
  }));
  data_83.update(51, (() => {
    val x_479 = iterMut_23;
    val x_480 = x_479.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
    val x_481 = x_480.hasNext;
    if (x_481)
      {
        val x_482 = iterMut_23;
        val x_483 = x_482.asInstanceOf[scala.collection.Iterator[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        val x_484 = x_483.next();
        listValMut_22 = x_484;
        val x_485 = listValMut_22;
        val x_486 = x_485.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_487 = x_486.proposition;
        resetData_0 = x_487;
        val x_488 = resetData_0;
        val x_489 = x_488.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_21 = x_489;
        val x_490 = bindingMut_21;
        val x_491 = x_490.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_492 = x_491.id;
        resetData_0 = x_492;
        val x_493 = resetData_0;
        val x_494 = x_493.asInstanceOf[scala.Long];
        bindingMut_20 = x_494;
        val x_495 = listValMut_22;
        val x_496 = x_495.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_497 = x_496.proposition;
        resetData_0 = x_497;
        val x_498 = resetData_0;
        val x_499 = x_498.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_19 = x_499;
        val x_500 = bindingMut_19;
        val x_501 = x_500.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_502 = x_501.isMuddy;
        resetData_0 = x_502;
        val x_503 = resetData_0;
        val x_504 = x_503.asInstanceOf[scala.Boolean];
        bindingMut_18 = x_504;
        val x_505 = bindingMut_18;
        val x_506 = x_505.asInstanceOf[scala.Boolean];
        val x_507 = bindingMut_20;
        val x_508 = x_507.asInstanceOf[scala.Long];
        val x_509 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_508, x_506);
        resetData_0 = x_509;
        val x_510 = resetData_0;
        val x_511 = x_510.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_17 = x_511;
        val x_512 = bindingMut_17;
        val x_513 = x_512.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_514 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_513);
        resetData_0 = x_514;
        val x_515 = resetData_0;
        val x_516 = x_515.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_16 = x_516;
        val x_517 = listValMut_22;
        val x_518 = x_517.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_519 = x_518.proposition;
        resetData_0 = x_519;
        val x_520 = resetData_0;
        val x_521 = x_520.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_15 = x_521;
        val x_522 = bindingMut_15;
        val x_523 = x_522.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_524 = x_523.isForward;
        resetData_0 = x_524;
        val x_525 = resetData_0;
        val x_526 = x_525.asInstanceOf[scala.Boolean];
        bindingMut_14 = x_526;
        positionVar_82 = 52
      }
    else
      ()
  }));
  data_83.update(52, (() => {
    val x_527 = bindingMut_14;
    val x_528 = x_527.asInstanceOf[scala.Boolean];
    if (x_528)
      {
        val x_529 = this.knowledgeBase;
        resetData_0 = x_529;
        val x_530 = resetData_0;
        val x_531 = x_530.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        bindingMut_13 = x_531;
        val x_532 = listValMut_22;
        val x_533 = x_532.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_534 = x_533.proposition;
        resetData_0 = x_534;
        val x_535 = resetData_0;
        val x_536 = x_535.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_12 = x_536;
        val x_537 = bindingMut_12;
        val x_538 = x_537.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_539 = x_538.id;
        resetData_0 = x_539;
        val x_540 = resetData_0;
        val x_541 = x_540.asInstanceOf[scala.Long];
        bindingMut_11 = x_541;
        val x_542 = bindingMut_11;
        val x_543 = x_542.asInstanceOf[scala.Long];
        val x_545 = {
          val x_544 = bindingMut_16;
          x_544.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]]
        };
        val x_546 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_543, x_545);
        resetData_0 = x_546;
        val x_547 = resetData_0;
        val x_548 = x_547.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        bindingMut_10 = x_548;
        val x_549 = bindingMut_10;
        val x_550 = x_549.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        val x_552 = {
          val x_551 = bindingMut_16;
          x_551.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]]
        };
        val x_553 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_552, x_550);
        resetData_0 = x_553;
        val x_554 = resetData_0;
        val x_555 = x_554.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_9 = x_555;
        val x_556 = bindingMut_9;
        val x_557 = x_556.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_558 = bindingMut_13;
        val x_559 = x_558.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        x_559.remember(x_557);
        resetData_0 = ();
        positionVar_82 = 53
      }
    else
      positionVar_82 = 54
  }));
  data_83.update(53, (() => positionVar_82 = 18));
  data_83.update(54, (() => {
    val x_560 = bindingMut_14;
    val x_561 = x_560.asInstanceOf[scala.Boolean];
    val x_562 = x_561.`unary_!`;
    if (x_562)
      {
        val x_563 = this.knowledgeBase;
        resetData_0 = x_563;
        val x_564 = resetData_0;
        val x_565 = x_564.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        bindingMut_8 = x_565;
        val x_566 = listValMut_22;
        val x_567 = x_566.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]];
        val x_568 = x_567.proposition;
        resetData_0 = x_568;
        val x_569 = resetData_0;
        val x_570 = x_569.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        bindingMut_7 = x_570;
        val x_571 = bindingMut_7;
        val x_572 = x_571.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildStatus];
        val x_573 = x_572.id;
        resetData_0 = x_573;
        val x_574 = resetData_0;
        val x_575 = x_574.asInstanceOf[scala.Long];
        bindingMut_6 = x_575;
        val x_576 = bindingMut_6;
        val x_577 = x_576.asInstanceOf[scala.Long];
        val x_579 = {
          val x_578 = bindingMut_16;
          x_578.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]]
        };
        val x_580 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_577, x_579);
        resetData_0 = x_580;
        val x_581 = resetData_0;
        val x_582 = x_581.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        bindingMut_5 = x_582;
        val x_583 = bindingMut_5;
        val x_584 = x_583.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        val x_585 = lib.EpistemicLogic.Sentence.NotE(x_584);
        resetData_0 = x_585;
        val x_586 = resetData_0;
        val x_587 = x_586.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
        bindingMut_4 = x_587;
        val x_588 = bindingMut_4;
        val x_589 = x_588.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
        val x_591 = {
          val x_590 = bindingMut_16;
          x_590.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]]
        };
        val x_592 = scala.Predef.Set.apply[lib.EpistemicLogic.Sentence.EpistemicSentence](x_591, x_589);
        resetData_0 = x_592;
        val x_593 = resetData_0;
        val x_594 = x_593.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_3 = x_594;
        val x_595 = bindingMut_3;
        val x_596 = x_595.asInstanceOf[scala.collection.immutable.Set[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        val x_597 = bindingMut_8;
        val x_598 = x_597.asInstanceOf[lib.EpistemicLogic.KnowledgeBase];
        x_598.remember(x_596);
        resetData_0 = ();
        positionVar_82 = 53
      }
    else
      ()
  }));
  data_83.update(55, (() => {
    val x_599 = this.future_objs1;
    val x_600 = x_599.toList;
    val x_603 = x_600.forall(((x_601: scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]) => {
      val x_602 = x_601.get;
      this.isCompleted(x_602)
    }));
    val x_604 = x_603.`unary_!`;
    val x_605 = x_604.`unary_!`;
    if (x_605)
      positionVar_82 = 13
    else
      ()
  }));
  data_83.update(56, (() => {
    val x_606 = iterMut_43;
    val x_607 = x_606.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_608 = x_607.hasNext;
    if (x_608)
      {
        val x_609 = iterMut_43;
        val x_610 = x_609.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
        val x_611 = x_610.next();
        listValMut_42 = x_611;
        val x_612 = this.future_objs1;
        resetData_0 = x_612;
        val x_613 = resetData_0;
        val x_614 = x_613.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        bindingMut_41 = x_614;
        val x_615 = ((this): meta.deep.runtime.Actor).id;
        val x_617 = {
          val x_616 = listValMut_42;
          x_616.asInstanceOf[generated.example.epistemicLogicMC.Child]
        };
        val x_618 = x_617.id;
        val x_619 = scala.collection.immutable.Nil.::[scala.collection.immutable.List[scala.Any]](((scala.collection.immutable.Nil): scala.collection.immutable.List[scala.Any]));
        val x_620 = meta.deep.runtime.RequestMessage.apply(x_615, x_618, 3, x_619);
        val x_621 = x_620.future;
        val x_622 = x_621.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
        x_620.`future_=`(x_622);
        ((this): meta.deep.runtime.Actor).sendMessage(x_620);
        val x_623 = x_620.sessionId;
        ((this): meta.deep.runtime.Actor).setMessageResponseHandler(x_623, ((response_624: meta.deep.runtime.Message) => {
          val x_625 = x_620.future;
          val x_626 = response_624.asInstanceOf[meta.deep.runtime.ResponseMessage];
          val x_627 = x_626.arg;
          val x_628 = x_625.setValue[scala.Any](x_627);
          val x_629 = x_628.asInstanceOf[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]];
          x_620.`future_=`(x_629);
          val x_630 = ((this): meta.deep.runtime.Actor).async_messages;
          val x_631 = x_620.future;
          val x_632 = x_631.id;
          val x_633 = scala.Predef.ArrowAssoc[java.lang.String](x_632);
          val x_634 = x_620.future;
          val x_635 = x_633.->[meta.deep.runtime.Future[scala.Any]](x_634);
          val x_636 = x_630.+[meta.deep.runtime.Future[scala.Any]](x_635);
          ((this): meta.deep.runtime.Actor).`async_messages_=`(x_636)
        }));
        val x_637 = x_620.future;
        val x_638 = scala.Some.apply[meta.deep.runtime.Future[scala.Any]](x_637);
        resetData_0 = x_638;
        val x_639 = resetData_0;
        val x_640 = x_639.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        bindingMut_40 = x_640;
        val x_641 = bindingMut_40;
        val x_642 = x_641.asInstanceOf[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]];
        val x_643 = bindingMut_41;
        val x_644 = x_643.asInstanceOf[scala.collection.mutable.ListBuffer[scala.Option[meta.deep.runtime.Future[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildStatus]]]]];
        x_644.append(x_642);
        resetData_0 = ();
        positionVar_82 = 5
      }
    else
      ()
  }));
  data_83.update(57, (() => {
    val x_645 = true.`unary_!`;
    if (x_645)
      positionVar_82 = 43
    else
      ()
  }));
  data_83.update(58, (() => {
    val x_646 = iterMut_79;
    val x_647 = x_646.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
    val x_648 = x_647.hasNext;
    if (x_648)
      {
        val x_649 = iterMut_79;
        val x_650 = x_649.asInstanceOf[scala.collection.Iterator[generated.example.epistemicLogicMC.Child]];
        val x_651 = x_650.next();
        listValMut_78 = x_651;
        val x_652 = this.allChildrenMuddy;
        resetData_0 = x_652;
        val x_653 = resetData_0;
        val x_654 = x_653.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_77 = x_654;
        val x_655 = listValMut_78;
        val x_656 = x_655.asInstanceOf[generated.example.epistemicLogicMC.Child];
        val x_657 = x_656.id;
        resetData_0 = x_657;
        val x_658 = resetData_0;
        val x_659 = x_658.asInstanceOf[scala.Long];
        bindingMut_76 = x_659;
        val x_660 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply$default$2;
        resetData_0 = x_660;
        val x_661 = resetData_0;
        val x_662 = x_661.asInstanceOf[scala.Boolean];
        bindingMut_75 = x_662;
        val x_663 = bindingMut_75;
        val x_664 = x_663.asInstanceOf[scala.Boolean];
        val x_665 = bindingMut_76;
        val x_666 = x_665.asInstanceOf[scala.Long];
        val x_667 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_666, x_664);
        resetData_0 = x_667;
        val x_668 = resetData_0;
        val x_669 = x_668.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_74 = x_669;
        val x_670 = bindingMut_74;
        val x_671 = x_670.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_672 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_671);
        resetData_0 = x_672;
        val x_673 = resetData_0;
        val x_674 = x_673.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_73 = x_674;
        val x_675 = bindingMut_73;
        val x_676 = x_675.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        val x_677 = bindingMut_77;
        val x_678 = x_677.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        x_678.append(x_676);
        resetData_0 = ();
        val x_679 = this.allChildrenUnaware;
        resetData_0 = x_679;
        val x_680 = resetData_0;
        val x_681 = x_680.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        bindingMut_72 = x_681;
        val x_682 = listValMut_78;
        val x_683 = x_682.asInstanceOf[generated.example.epistemicLogicMC.Child];
        val x_684 = x_683.id;
        resetData_0 = x_684;
        val x_685 = resetData_0;
        val x_686 = x_685.asInstanceOf[scala.Long];
        bindingMut_71 = x_686;
        val x_687 = listValMut_78;
        val x_688 = x_687.asInstanceOf[generated.example.epistemicLogicMC.Child];
        val x_689 = x_688.id;
        resetData_0 = x_689;
        val x_690 = resetData_0;
        val x_691 = x_690.asInstanceOf[scala.Long];
        bindingMut_70 = x_691;
        val x_692 = listValMut_78;
        val x_693 = x_692.asInstanceOf[generated.example.epistemicLogicMC.Child];
        val x_694 = x_693.isMuddy;
        resetData_0 = x_694;
        val x_695 = resetData_0;
        val x_696 = x_695.asInstanceOf[scala.Boolean];
        bindingMut_69 = x_696;
        val x_697 = bindingMut_69;
        val x_698 = x_697.asInstanceOf[scala.Boolean];
        val x_699 = bindingMut_70;
        val x_700 = x_699.asInstanceOf[scala.Long];
        val x_701 = example.epistemicLogicMC.MCHelper.ChildMuddy.apply(x_700, x_698);
        resetData_0 = x_701;
        val x_702 = resetData_0;
        val x_703 = x_702.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        bindingMut_68 = x_703;
        val x_704 = bindingMut_68;
        val x_705 = x_704.asInstanceOf[example.epistemicLogicMC.MCHelper.ChildMuddy];
        val x_706 = lib.EpistemicLogic.Sentence.P.apply[example.epistemicLogicMC.MCHelper.ChildMuddy](x_705);
        resetData_0 = x_706;
        val x_707 = resetData_0;
        val x_708 = x_707.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        bindingMut_67 = x_708;
        val x_709 = bindingMut_67;
        val x_710 = x_709.asInstanceOf[lib.EpistemicLogic.Sentence.P[example.epistemicLogicMC.MCHelper.ChildMuddy]];
        val x_711 = bindingMut_71;
        val x_712 = x_711.asInstanceOf[scala.Long];
        val x_713 = lib.EpistemicLogic.Sentence.Ka.apply[scala.Long](x_712, x_710);
        resetData_0 = x_713;
        val x_714 = resetData_0;
        val x_715 = x_714.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        bindingMut_66 = x_715;
        val x_716 = bindingMut_66;
        val x_717 = x_716.asInstanceOf[lib.EpistemicLogic.Sentence.Ka[scala.Long]];
        val x_718 = lib.EpistemicLogic.Sentence.NotE(x_717);
        resetData_0 = x_718;
        val x_719 = resetData_0;
        val x_720 = x_719.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
        bindingMut_65 = x_720;
        val x_721 = bindingMut_65;
        val x_722 = x_721.asInstanceOf[lib.EpistemicLogic.Sentence.NotE];
        val x_723 = bindingMut_72;
        val x_724 = x_723.asInstanceOf[scala.collection.mutable.ListBuffer[lib.EpistemicLogic.Sentence.EpistemicSentence]];
        x_724.append(x_722);
        resetData_0 = ();
        positionVar_82 = 1
      }
    else
      ()
  }));
  data_83
}).apply();
  
  override def run_until(until_726: scala.Int) : meta.deep.runtime.Actor =  {
    while ({
      val x_727 = currentTurn;
      val x_728 = x_727.<=(until_726);
      x_728.&&({
        val x_729 = positionVar_82;
        val x_730 = commands_725.length;
        x_729.<(x_730)
      })
    }) 
      {
        val x_731 = positionVar_82;
        val x_732 = commands_725.apply(x_731);
        x_732.apply()
      }
    ;
    this
  }
}
