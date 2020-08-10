package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class Dokument(
    val tittel: String?,
    val brevkode: String?,
    val dokumentKategori: String?,
    val dokumentvarianter: List<DokumentVariantArkivertPDFA>
)

fun lagDokumentMedPdf(tittel: String?, pdfData: ByteArray, brevkode: String): Dokument {
    return Dokument(
            tittel = tittel,
            dokumentvarianter = listOf(DokumentVariantArkivertPDFA(fysiskDokument = pdfData)),
            brevkode = brevkode,
            dokumentKategori = null
    )
}