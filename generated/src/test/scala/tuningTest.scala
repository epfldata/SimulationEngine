package generated.example.test

import meta.API._
import meta.runtime.{Actor, Message}

// Tuning setting
// Test 500, 1000, 1500 agents
class gameOfLifeTuneSpark extends StaticPartitionTest[SparkMessagingLayer.type](
    "gameOfLife", 30, Set(1), Set(0), 
    List(Set(5, 10, 15), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class gameOfLifeTuneAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "gameOfLife", 30, Set(1), Set(0), 
    List(Set(5, 10, 15), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class gameOfLifeTuneSparkTimeseries extends TimeseriesTest[SparkMessagingLayer.type, List[Boolean]](
    "gameOfLife", 30,  
    List(Set(5, 10, 15), Set(100), Set(1)), 
    (actors: List[Actor], msgs: List[Message]) => actors.map(a => a.asInstanceOf[generated.example.gameOfLife.Cell].alive),
    generated.example.gameOfLife.InitData.wrapper,
    generated.example.gameOfLife.InitData.writeSchema)

class gameOfLifeTuneSparkTimeseriesDeforestation extends TimeseriesDeforestationTest[SparkMessagingLayer.type, Boolean, List[Boolean]](
    "gameOfLife", 30, 
    List(Set(5, 10, 15), Set(100), Set(1)), 
    (actor: Actor) => actor.asInstanceOf[generated.example.gameOfLife.Cell].alive,
    (r: List[Boolean]) => r,
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema)

class gameOfLifeTuneAkkaTimeseries extends TimeseriesTest[AkkaMessagingLayer.type, List[Boolean]](
    "gameOfLife", 30,  
    List(Set(5, 10, 15), Set(100), Set(1)), 
    (actors: List[Actor], msgs: List[Message]) => actors.map(a => a.asInstanceOf[generated.example.gameOfLife.Cell].alive),
    generated.example.gameOfLife.InitData.wrapper,
    generated.example.gameOfLife.InitData.writeSchema)

class gameOfLifeTuneAkkaTimeseriesDeforestation extends TimeseriesDeforestationTest[AkkaMessagingLayer.type, Boolean, List[Boolean]](
    "gameOfLife", 30, 
    List(Set(5, 10, 15), Set(100), Set(1)), 
    (actor: Actor) => actor.asInstanceOf[generated.example.gameOfLife.Cell].alive,
    (r: List[Boolean]) => r,
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema)