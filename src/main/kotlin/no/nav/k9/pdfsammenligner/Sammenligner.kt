package no.nav.k9.pdfsammenligner

import de.redsix.pdfcompare.CompareResult
import de.redsix.pdfcompare.PdfComparator
import org.springframework.stereotype.Service

@Service
class Sammenligner(private val awsConsumer: AwsConsumer) {
    fun sammenlign(key: String, nyPdf: ByteArray) : CompareResult? {

        val eksisterendePdf = awsConsumer.getImage(key)
        if (eksisterendePdf == null) {
            awsConsumer.nyBaseline(key, nyPdf)
            return null
        }

        val comparator = PdfComparator(eksisterendePdf, nyPdf.inputStream(), null)
        return comparator.compare()
    }
}