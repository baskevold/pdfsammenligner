package no.nav.k9.pdfsammenligner

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream
import java.io.FileNotFoundException

@Service
class AwsConsumer(private val properties: AwsProperties) {
   val s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(properties))
            .withRegion(Regions.US_EAST_2)
            .build()

    fun getImage(key: String): ByteArrayInputStream? {
        try {
            val s3Object = s3client.getObject(properties.bucket, key)
            val stream = s3Object.objectContent
            return stream.readAllBytes().inputStream()
        } catch (e: FileNotFoundException) {
            return null
        }
    }

    fun nyBaseline(key:String, nyPdf: ByteArray) {
        val metadata = ObjectMetadata()
        metadata.contentLength = nyPdf.size.toLong()
        s3client.putObject(properties.bucket, key, nyPdf.inputStream(), metadata)
    }
}