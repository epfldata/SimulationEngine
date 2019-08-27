package bo

import java.nio.file.{Files, Paths}

import breeze.linalg.{DenseMatrix, DenseVector}
import org.apache.commons.csv.{CSVFormat, CSVParser, CSVPrinter}

object CsvManager {
  def readCsvFile(path: String, header: Array[String]): DenseMatrix[Double] = {
    var arrays: Array[Array[Double]] = Array()
    val reader = Files.newBufferedReader(Paths.get(path))
    val csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader.withIgnoreHeaderCase.withTrim)
    try {
      val itererator = csvParser.iterator()
      while (itererator.hasNext) {
        val csvRecord = itererator.next()
        var array = Array[Double]()
        for (name <- header) {
          array :+= csvRecord.get(name).toDouble
        }
        arrays :+= array
      }
    } finally {
      if (reader != null) reader.close()
      if (csvParser != null) csvParser.close()
    }
    DenseMatrix(arrays: _*)
  }

  def writeCsvFile(matrix: DenseMatrix[Double], path: String, header: Array[String]) = {
    val writer = Files.newBufferedWriter(Paths.get(path))
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
