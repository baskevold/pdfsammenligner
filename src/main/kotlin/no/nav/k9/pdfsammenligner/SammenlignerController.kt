package no.nav.k9.pdfsammenligner

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/pdf")
class SammenlignerController(val sammenligner: Sammenligner) {

    @GetMapping("ny/{key}")
    fun nyPdf(pdf: ByteArray, key: String) : Mono<Boolean> {
        return Mono.just(sammenligner.sammenlign(key, pdf)?.isEqual ?: true)
    }
}