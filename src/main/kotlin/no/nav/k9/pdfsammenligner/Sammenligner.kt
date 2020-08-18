package no.nav.k9.pdfsammenligner

import de.redsix.pdfcompare.CompareResult
import de.redsix.pdfcompare.CompareResultImpl
import de.redsix.pdfcompare.PdfComparator
import org.springframework.stereotype.Service

@Service
class Sammenligner(private val awsConsumer: AwsConsumer) {
    fun sammenlign(key: String, nyPdf: ByteArray) : CompareResult? {

        println("\n------ Starter sammenligning for $key --------")
        val eksisterendePdf = awsConsumer.getImage(key)
        if (eksisterendePdf == null) {
            println("Nøkkelen '$key' finnes ikke fra før. Laster opp")
            awsConsumer.nyBaseline(key, nyPdf)
            return null
        }

        val comparator = PdfComparator(eksisterendePdf, nyPdf.inputStream(), CompareResultImpl())
        return comparator.compare()
    }
}