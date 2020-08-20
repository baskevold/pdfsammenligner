package no.nav.k9.pdfsammenligner.journalpostAdapter

import no.nav.k9.pdfsammenligner.journalpostAdapter.dto.OpprettJournalpostRequest
import no.nav.k9.pdfsammenligner.ResultatLogger
import no.nav.k9.pdfsammenligner.Sammenligner
import no.nav.k9.pdfsammenligner.journalpostAdapter.dto.OpprettJournalpostResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.File
import kotlin.random.Random

@RestController
@RequestMapping("/rest/journalpostapi/v1")
class JournalpostApiController(val sammenligner: Sammenligner,
                               val resultatLogger: ResultatLogger) {

    @PostMapping("/journalpost")
    fun postJournalpost(@RequestBody journalpostRequest: OpprettJournalpostRequest) : OpprettJournalpostResponse {
        val fysiskeDokumenter = journalpostRequest.dokumenter.flatMap { dokument -> dokument.dokumentvarianter.map { variant -> variant.fysiskDokument } }
        val nyPdf = fysiskeDokumenter.first()

        val key = lagNøkkel(journalpostRequest)
        File("resultat/$key.pdf").writeBytes(nyPdf)
        sammenligner.sammenlign(key, nyPdf).let{
            resultatLogger.logg(key, it)
        }

        return OpprettJournalpostResponse(
                journalpostId = Random.nextInt().toString(),
                journalpostferdigstilt = true,
                melding = "",
                dokumenter = listOf(
                        OpprettJournalpostResponse.Dokument("Random.nextInt().toString()")
                )
        )
    }

    private fun lagNøkkel(journalpostRequest: OpprettJournalpostRequest): String {
        return if (journalpostRequest.tema != null) {
            "${journalpostRequest.tema}_${journalpostRequest.sak.fagsakId}_${journalpostRequest.tittel}"
        } else {
            "${journalpostRequest.sak.fagsakId}_${journalpostRequest.tittel}"
        }
    }
}