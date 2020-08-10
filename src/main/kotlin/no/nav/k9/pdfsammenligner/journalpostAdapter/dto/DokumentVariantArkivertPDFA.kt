package no.nav.k9.pdfsammenligner.journalpostAdapter.dto

data class DokumentVariantArkivertPDFA(
        val filtype: JournalpostFiltype = JournalpostFiltype.PDFA,
        val variantformat: String = "ARKIV",
        val fysiskDokument: ByteArray
) {
    enum class JournalpostFiltype {
        PDF, PDFA, XML, RTF, AFP, META, DLF, JPEG, TIFF, DOC, DOCX, XLS, XLSX, AXML, DXML, JSON, PNG
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DokumentVariantArkivertPDFA

        if (filtype != other.filtype) return false
        if (variantformat != other.variantformat) return false
        if (!fysiskDokument.contentEquals(other.fysiskDokument)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = filtype.hashCode()
        result = 31 * result + variantformat.hashCode()
        result = 31 * result + fysiskDokument.contentHashCode()
        return result
    }
}
