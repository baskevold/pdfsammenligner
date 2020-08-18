package no.nav.k9.pdfsammenligner

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.FileNotFoundException

@Service
class AwsConsumer(@Value("\${s3.bucket}") private val bucket: String) {
    val s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(ProfileCredentialsProvider())
                .withRegion(Regions.EU_NORTH_1)
                .build()

    fun getImage(key: String): ByteArrayInputStream? {
        try {
            val s3Object = s3client.getObject(bucket, key)
            val stream = s3Object.objectContent
            val data = stream.readAllBytes().inputStream()
            stream.close()
            return data
        } catch (e: AmazonS3Exception) {
            if (e.errorCode == "NoSuchKey") {
                return null
            }
            throw e
        }
    }

    fun nyBaseline(key:String, nyPdf: ByteArray) {
        val metadata = ObjectMetadata()
        metadata.contentLength = nyPdf.size.toLong()
        s3client.putObject(bucket, key, nyPdf.inputStream(), metadata)

        println("Ny baseline laget for nøkkel $key")
    }
}