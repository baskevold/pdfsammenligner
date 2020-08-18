package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class OpprettJournalpostResponse(
        val journalpostId: String,
        val dokumenter: List<Dokument>,
        val journalpostferdigstilt: Boolean,
        val melding: String?) {

    fun erFerdigstilt(): Boolean {
        return journalpostferdigstilt
    }

    data class Dokument(
        val dokumentInfoId: String
    )
}