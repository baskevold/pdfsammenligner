package no.nav.k9.pdfsammenligner

import de.redsix.pdfcompare.CompareResult
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.PrintWriter

@Service
class ResultatLogger {
    final val fw = FileWriter("Testresultat.txt", true);
    final val bw = BufferedWriter(fw)
    val out = PrintWriter(bw)

    fun logg(key: String, resultat: CompareResult?) {
        val linje = resultat?.isEqual ?: "Ny baseline"
        out.println("Saksnummer $key: $linje")
    }
}