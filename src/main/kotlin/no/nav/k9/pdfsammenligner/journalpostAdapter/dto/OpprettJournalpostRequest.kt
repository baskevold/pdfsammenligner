package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class OpprettJournalpostRequest(
        val journalpostType: String,
        val avsenderMottaker: AvsenderMottaker,
        val bruker: Bruker?,
        val tema: String?,
        val behandlingstema: String?,
        val tittel: String?,
        val kanal: String?,
        val journalfoerendeEnhet: String,
        val eksternReferanseId: String? = null,
        val tilleggsopplysninger: List<Any> = emptyList(),
        val sak: Sak,
        val dokumenter: List<Dokument>
)