package example.epidemic

import scala.util.Random
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import meta.runtime.JsonSerializable

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.ser.std.StdSerializer

// non-pharmaceutical intervention
@JsonSerialize(`using` = classOf[NPIJsonSerializer])
@JsonDeserialize(`using` = classOf[NPIJsonDeserializer])
sealed trait NPI extends JsonSerializable{
  def contactNumber(health: HealthStatus): Int 
} 

object NPI {
  case object NoNPI extends NPI {
    def contactNumber(health: HealthStatus): Int = {
      health match {
        case _ => List(Random.nextGaussian() + 10, 1).max.toInt
      }
    }
  }
  
  case object Quarantine extends NPI {
    def contactNumber(health: HealthStatus): Int = {
      health match {
        case Infectious => List(Random.nextGaussian() + 3, 0).max.toInt
        case Hospitalized => 0
        case _ => List(Random.nextGaussian() + 10, 1).max.toInt
      }
    }
  }

  case object Lockdown extends NPI {
    def contactNumber(health: HealthStatus): Int = {
      health match {
        case Infectious => List(Random.nextGaussian() + 3, 0).max.toInt
        case Hospitalized => 0
        case _ => List(Random.nextGaussian() + 3, 0).max.toInt
      }
    }  
  }
}

class NPIJsonSerializer extends StdSerializer[NPI](classOf[NPI]) {
  import NPI._

  override def serialize(value: NPI, gen: JsonGenerator, provider: SerializerProvider): Unit = {
    val strValue = value match {
      case NoNPI => "No"
      case Quarantine  => "Q"
      case Lockdown => "L"
    }
    gen.writeString(strValue)
  }
}

class NPIJsonDeserializer extends StdDeserializer[NPI](classOf[NPI]) {
  import NPI._

  override def deserialize(p: JsonParser, ctxt: DeserializationContext): NPI = {
    p.getText match {
      case "No" => NoNPI
      case "Q" => Quarantine
      case "L" => Lockdown
    }
  }
}