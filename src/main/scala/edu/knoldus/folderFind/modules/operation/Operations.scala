package edu.knoldus.folderFind.modules.operation
import java.io.File
import java.io.IOException

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class Operations {

  def listFiles(file: String): Future[List[File]] = {
    @tailrec
    def listFiles(files: List[File], resultList: List[File]): List[File] = files match {
      case Nil => resultList
      case head :: tail if head.isDirectory =>
        try {
          head.listFiles
        }
        catch {
          case exception@(_: IOException) => throw new Exception("Exception" + exception)
        }

        listFiles(Option(head.listFiles).map(_.toList ::: tail).fold(tail) {
          _.toList
        }, resultList)

      case head :: tail if head.isFile =>
        listFiles(tail, head :: resultList)
    }

    Future {
      listFiles(List(new File(file)), Nil)
    }
  }

}

  //  def findFiles(path: File): List[File] ={
  //    path :: path.listFiles.filter {
  //      _.isDirectory
  //    }.toList.flatMap {
  //      findFiles
  //    }
  //
  //
  //}
  //  def fetchFiles(path:String)(op:File => Unit):Unit = Future {
  //    for (file <- new File(path).listFiles if file.exists()){
  //      if (file.isDirectory)
  //        fetchFiles(file.getAbsolutePath)(op)
  //      else if(file.isFile)
  //        op(file)
  //    }
  //  }
  //
  //  def fetchFiles(path:String):List[String] = {

  //     def inner(path:String,list:List[String]):List[String]={
  //       val file =new File(path)
  //       if(!file.exists())
  //         list
  //       file.listFiles match{
  //         case null => list
  //         case files if files.i
  //
  //       }
  //
  //
  //       for (.listFiles if file.exists()) {
  //          if (file.isFile) {
  //           list
  //         }
  //          else if (file.isDirectory)
  //           inner(file.getAbsolutePath, file.getName :: list)
  //       }
  //     }
  //    inner(path,List[String]())
  //  }
  //
  //}







