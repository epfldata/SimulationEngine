package meta.API

abstract class DeforestationStrategy {
  // a sequential worker applies the mapper to each agent
  def mapper(x: Serializable): Serializable = {
    x
  }

  // driver sends an Iterable[Serializable] to the log controller. Log controller collects Iterable[Iterable[Serializable]]
  // and applies the reducer method to reduce the intermediate data 
  def reducer(x: Iterable[Iterable[Serializable]]): Iterable[Serializable] = {
    x.flatten
  }

  // driver applies to the collected timeseries
  def transformer(x: Iterable[Serializable]): Iterable[Serializable] = {
    x
  }
}

object DeforestationStrategy {
  implicit object NoReduction extends DeforestationStrategy 
}
