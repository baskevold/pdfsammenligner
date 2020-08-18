package no.nav.k9.pdfsammenligner

import de.redsix.pdfcompare.CompareResult
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class ResultatLogger {
    final val fw = FileWriter("resultat/testresultat.txt", true);
    final val bw = BufferedWriter(fw)

    fun logg(key: String, resultat: CompareResult?) {
        val out = PrintWriter(bw)
        val formatertResultat = resultat?.isEqual ?: "Ny baseline"
        val linje = "${LocalDateTime.now().withNano(0)} Nøkkel '$key' : $formatertResultat"

        out.println(linje)
        out.flush()
        println(linje)

        if (resultat?.isNotEqual == true)  {
            resultat.writeTo("resultat/$key.diff")
        }
    }
}