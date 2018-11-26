package Timeseries;


case class LogList[T](
  var l : List[(Int, T)] = List[(Int, T)](),
  private var latest : Int = 0
) {

  def add(time: Int, record: T) {
    l = (time, record) :: l;

    assert(time >= latest); // mustn't receive records out of order
    if(time > latest) latest = time;
  }

  def toTimeseries : Timeseries[List[T]] = {
    val grps = l.groupBy(_._1).mapValues(_.map(_._2));
    new Timeseries(0, latest, ((t: Int) => grps.getOrElse(t, List())))
  }
}


