package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import com.typesafe.config.ConfigFactory

class DriverWorkerTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "driver-worker.conf" should "get loaded" in {
        assert(ConfigFactory.load("driver-worker").getValue("driver-worker.total-workers").render().toInt==1)
    }
}
