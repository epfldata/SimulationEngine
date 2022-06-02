package generated.example.test

import meta.API._

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