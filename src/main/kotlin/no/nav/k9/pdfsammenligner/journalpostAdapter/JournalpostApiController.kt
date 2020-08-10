package no.nav.k9.pdfsammenligner.journalpostAdapter

import no.nav.k9.pdfsammenligner.journalpostAdapter.dto.OpprettJournalpostRequest
import no.nav.k9.pdfsammenligner.ResultatLogger
import no.nav.k9.pdfsammenligner.Sammenligner
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/journalpostapi/v1")
class JournalpostApiController(val sammenligner: Sammenligner,
                               val resultatLogger: ResultatLogger) {

    @PostMapping("/journalpost")
    fun postJournalpost(journalpostRequest: OpprettJournalpostRequest) {
        val fysiskeDokumenter = journalpostRequest.dokumenter.flatMap { dokument -> dokument.dokumentvarianter.map { variant -> variant.fysiskDokument } }
        val nyPdf = fysiskeDokumenter.first()
        val key = journalpostRequest.sak.fagsakId
        sammenligner.sammenlign(key, nyPdf).let { resultatLogger.logg(key, it) }
    }
}