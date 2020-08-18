package no.nav.k9.pdfsammenligner

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/pdf")
class SammenlignerController(val sammenligner: Sammenligner, val resultatLogger: ResultatLogger) {

    @PostMapping("ny/{key}")
    fun nyPdf(@RequestBody pdf: ByteArray, @PathVariable key: String) {
        sammenligner.sammenlign(key, pdf)?.let{ resultatLogger.logg(key, it) }
    }
}