package ml_supplements

import java.io.File
import java.nio.file.{Files, Paths}

import breeze.linalg.DenseMatrix
import org.apache.commons.csv.{CSVFormat, CSVParser, CSVPrinter}

import scala.collection.JavaConverters._

object CsvManager {
  def readCsvFile(path: String): (DenseMatrix[Double], List[String]) = {
    var arrays: Array[Array[Double]] = Array()
    val reader = Files.newBufferedReader(Paths.get(path))
    val csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader.withIgnoreHeaderCase.withTrim)
    try {
      val itererator = csvParser.iterator()
      while (itererator.hasNext) {
        val csvRecord = itererator.next()
        var array = Array[Double]()
        for (i <- 0 until csvRecord.size()) {
          array :+= csvRecord.get(i).toDouble
        }
        arrays :+= array
      }
    } finally {
      if (reader != null) reader.close()
      if (csvParser != null) csvParser.close()
    }
    (DenseMatrix(arrays: _*), csvParser.getHeaderNames.asScala.toList)
  }

  def writeCsvFile(matrix: DenseMatrix[Double], filename: String, header: Array[String]) = {
    val path = Paths.get(filename)
    if (Files.notExists(path.getParent)) {
      new File(path.getParent.toUri).mkdirs()
    }
    val writer = Files.newBufferedWriter(path)
    val csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header: _*))
    try {
      for (i <- 0 until matrix.rows) {
        val array: Array[String] = matrix(i, ::).inner.map(_.toString).toArray
        csvPrinter.printRecord(array: _*)
      }
      csvPrinter.flush()
    } finally {
      if (writer != null) writer.close()
      if (csvPrinter != null) csvPrinter.close()
    }
  }
}
