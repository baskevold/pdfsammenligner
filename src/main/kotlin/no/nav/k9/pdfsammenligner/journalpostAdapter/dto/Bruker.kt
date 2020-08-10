package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class Bruker(val id: String,
                  val idType: BrukerIdType) {

    enum class BrukerIdType {
        FNR, ORGNR
    }
}