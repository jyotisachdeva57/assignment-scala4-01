package edu.knoldus.folderFind.application

import java.io.{File, IOException}


import scala.concurrent.ExecutionContext.Implicits.global
import edu.knoldus.folderFind.modules.operation.Operations

import scala.util.{Failure, Success}
import org.apache.log4j.Logger

object MenuLauncher {

  def main(args: Array[String]): Unit = {
    val log = Logger.getLogger(this.getClass)
    val time = 1000
    val obj = new Operations
    val futureVar = obj.listFiles("/home/knoldus/try")
    futureVar onComplete {
      case Success(files) => log.info("feeds " + files)
      case Failure(ex) => log.info("Error " + ex.getMessage)

    }

    Thread.sleep(time)
  }
}
