import org.apache.spark.sql.Row
import org.apache.spark.sql._
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.kafka._

object BookingPassengers {
  private val intervalb = Seconds(5)		//choose the time interval for gathering streaming data

def main(args: Array[String]) {


   //define spark conf
   val sparkConf = new SparkConf().setMaster("local[4]").setAppName("BookingPassengers")

   //define spark context
   val sc2 = new SparkContext(sparkConf)

   //define sql context
   val sqlContext = new SQLContext(sc2)

   //define streaming context
   val ssc2 = new StreamingContext(sc2,intervalb)

   //initiate a kafka createstream utility
 val BookingPassengersstream = KafkaUtils.createStream(ssc2,"10.1.1.4:2181","my-consumer-group",Map("BookingPassengers" -> 1)).map(_._2)


 // for each RDD from the kafka streaming, create a dataframe and save as json file in hdfs
 BookingPassengersstream.foreachRDD { rdd =>
  val df = sqlContext.read.json(rdd.map(x => x))
  df.show()
  df.write.mode(SaveMode.Append).format("json").save("/user/cloudera/ChatBotStreams/BookingPassengers")
}


	  ssc2.start()
      ssc2.awaitTermination()

	  //Stopping spark context gracefully
	  sys.ShutdownHookThread {
	  
      ssc2.stop(true, true)	//first parameter stops spark context, second makes the stop gracefully

	  }
	  
	  }

	  }
