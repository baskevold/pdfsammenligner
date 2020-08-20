package no.nav.k9.pdfsammenligner

import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/pdf")
class SammenlignerController(val sammenligner: Sammenligner, val resultatLogger: ResultatLogger) {

    @PostMapping("ny/{key}")
    fun nyPdf(@RequestBody nyPdf: ByteArray, @PathVariable key: String) {

        File("resultat/$key.pdf").writeBytes(nyPdf)
        sammenligner.sammenlign(key, nyPdf).let{
            resultatLogger.logg(key, it)
        }
    }
}