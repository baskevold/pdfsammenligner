package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class AvsenderMottaker(
        val id: String,
        val navn: String?,
        val land: String,
        val idType: IdType) {

    enum class IdType {
        FNR, ORGNR, HPRNR, UTL_ORG
    }
}