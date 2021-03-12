package generated.example.distributedGraph.shortestPath.BellmanFord

object InitData  {
  def initActors(): Unit = {
    
  val service_0 = new generated.example.distributedGraph.shortestPath.BellmanFord.DiscoverNeighborWithWeightService();
  meta.runtime.SimRuntime.newActors.append(service_0);
  val allPids_1 = scala.collection.immutable.List.apply[scala.Long](2L, 3L, 4L, 5L);
  val a_2 = new generated.example.distributedGraph.shortestPath.BellmanFord.Node(true, allPids_1, service_0);
  meta.runtime.SimRuntime.newActors.append(a_2);
  val b_3 = new generated.example.distributedGraph.shortestPath.BellmanFord.Node(false, allPids_1, service_0);
  meta.runtime.SimRuntime.newActors.append(b_3);
  val c_4 = new generated.example.distributedGraph.shortestPath.BellmanFord.Node(false, allPids_1, service_0);
  meta.runtime.SimRuntime.newActors.append(c_4);
  val d_5 = new generated.example.distributedGraph.shortestPath.BellmanFord.Node(false, allPids_1, service_0);
  meta.runtime.SimRuntime.newActors.append(d_5);
  val c1_6 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(a_2, b_3, 1);
  meta.runtime.SimRuntime.newActors.append(c1_6);
  val c2_7 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(a_2, c_4, 5);
  meta.runtime.SimRuntime.newActors.append(c2_7);
  val c3_8 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(a_2, d_5, 2);
  meta.runtime.SimRuntime.newActors.append(c3_8);
  val c4_9 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(b_3, c_4, 3);
  meta.runtime.SimRuntime.newActors.append(c4_9);
  val c5_10 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(b_3, d_5, 1);
  meta.runtime.SimRuntime.newActors.append(c5_10);
  val c6_11 = new generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel(c_4, d_5, 1);
  meta.runtime.SimRuntime.newActors.append(c6_11);
  val x_12 = scala.collection.immutable.List.apply[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel](c1_6, c2_7, c3_8);
  a_2.`channels_=`(x_12);
  val x_13 = scala.collection.immutable.List.apply[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel](c1_6, c4_9, c5_10);
  b_3.`channels_=`(x_13);
  val x_14 = scala.collection.immutable.List.apply[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel](c2_7, c4_9, c6_11);
  c_4.`channels_=`(x_14);
  val x_15 = scala.collection.immutable.List.apply[generated.example.distributedGraph.shortestPath.BellmanFord.WeightedChannel](c3_8, c5_10, c6_11);
  d_5.`channels_=`(x_15)

  }  
}