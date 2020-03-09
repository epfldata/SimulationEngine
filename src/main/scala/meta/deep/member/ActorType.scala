package meta.deep.member

import meta.deep.IR.Predef._
import meta.deep.algo.Algo
import meta.deep.runtime.Actor

/**
  * Class representation of an actor containing the data needed for code generation
  *
  * @param name      of the actor, must be unique
  * @param states    init variables (at the moment must be all var)
  * @param methods   lifted methods of the class
  * @param main      main algorithm of the class (use main class)
  * @param self      (variable referencing to itself)
  * @param X         containing type of Actor
  * @tparam X actor type
  */
case class ActorType[X <: Actor](
    name: String,
    parentNames: List[String],
    parameterList: List[(String, String)],
    var states: List[State[_]],
    methods: List[LiftedMethod[_]],
    main: Algo[_],
    self: Variable[X])(implicit val X: CodeType[X]) {}
